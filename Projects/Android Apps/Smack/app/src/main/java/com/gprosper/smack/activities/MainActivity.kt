package com.gprosper.smack.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.github.nkzawa.socketio.client.IO
import com.gprosper.smack.R
import com.gprosper.smack.adapters.MessageRecycleAdapter
import com.gprosper.smack.dialogs.AddChannelDialog
import com.gprosper.smack.extensions.hideKeyboard
import com.gprosper.smack.model.Channel
import com.gprosper.smack.model.Message
import com.gprosper.smack.services.ApiService
import com.gprosper.smack.services.DateFormatter
import com.gprosper.smack.services.LoginManager
import com.gprosper.smack.services.UserDataService
import com.gprosper.smack.utilities.BROADCAST_USER_DATA_CHANGED
import com.gprosper.smack.utilities.SOCKET_URL
import com.gprosper.smack.utilities.SocketEvent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity(), AddChannelDialog.AddChannelDialogInterface {

    private val userDataChangedReciever = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            if (LoginManager.isLoggedIn){
                with(UserDataService){
                    usernameNavHeader.text = name
                    emailNavHeader.text = email

                    val id = resources.getIdentifier(avatarName, "drawable", packageName)
                    profileImageNavHeader.setImageResource(id)
                    profileImageNavHeader.setBackgroundColor(returnAvatarColor(avatarColor!!))
                }

                loginBtn.text = getString(R.string.logout)

                getChannels()
                setupSockets()
            }
            else {
                usernameNavHeader.text = ""
                emailNavHeader.text = ""
                mainChannelName.text = "Please Log In."
                profileImageNavHeader.setImageResource(R.drawable.profiledefault)
                profileImageNavHeader.setBackgroundColor(Color.TRANSPARENT)
                loginBtn.text = getString(R.string.login)
            }
        }
    }

    private val socket = IO.socket(SOCKET_URL)

    private val channels = ArrayList<Channel>()
    private val messages = ArrayList<Message>()

    private lateinit var channelListAdapter: ArrayAdapter<Channel>
    private lateinit var messageListAdapter: MessageRecycleAdapter

    private var selectedChannel: Channel? = null
        set(value) {
            field = value
            mainChannelName.text = value?.toString()
            getMessages()
        }

    // region Lifecycle Methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        LocalBroadcastManager.getInstance(this).registerReceiver(userDataChangedReciever, IntentFilter(BROADCAST_USER_DATA_CHANGED))

        setupDrawer()
        setupMessageList()
        setupChannelList()

        restoreLoggedInState()
    }

    override fun onResume() {
        super.onResume()
        getChannels()
        getMessages()
        socket.connect()
    }

    override fun onPause() {
        super.onPause()
        socket.disconnect()
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(userDataChangedReciever)
    }

    // endregion

    // region Actions

    fun getMessages() {
        selectedChannel?.let {
            ApiService.getMessages(it.id!!){ downloadedMessages ->
                downloadedMessages?.let {
                    messages.clear()
                    messages.addAll(it)
                    messageListAdapter.notifyDataSetChanged()
                    messageListView.scrollToPosition(messages.count() - 1)
                }
            }
        }
    }

    private fun getChannels() {
        if (LoginManager.isLoggedIn){
            ApiService.getChannels(){ preExistChannels ->
                preExistChannels?.let {
                    channels.clear()
                    channels.addAll(it)
                    channelListAdapter.notifyDataSetChanged()

                    if (selectedChannel == null)
                        selectedChannel = preExistChannels.firstOrNull()
                }
            }
        }
    }

    private fun restoreLoggedInState() {
        if (!LoginManager.isLoggedIn && !LoginManager.authToken.isNullOrBlank()){
            LoginManager.authEmail?.let {
                ApiService.getUserDataByEmail(it){ }
            }
        }
    }

    // endregion

    // region Setup

    private fun setupDrawer() {
        ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close).apply {
            drawerLayout.addDrawerListener(this)
            syncState()
        }
    }

    private fun setupChannelList() {
        channelListAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, channels)
        channelList.adapter = channelListAdapter
        channelList.setOnItemClickListener { adapterView, view, position, id ->
            drawerLayout.closeDrawer(GravityCompat.START)
            selectedChannel = channels[position]
        }
    }

    private fun setupMessageList() {
        messageListAdapter = MessageRecycleAdapter(this, messages)

        with(messageListView){
            val l = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(false)
            adapter = messageListAdapter
            layoutManager = l
        }
    }

    private fun setupSockets() {
        socket.on(SocketEvent.CHANNEL_CREATED){
            val channel = Channel(it[0] as String, it[1] as String, it[2] as String)
            channels.add(channel)
            runOnUiThread {
                channelListAdapter.notifyDataSetChanged()
            }
        }

        socket.on(SocketEvent.MESSAGE_CREATED){
            val message = Message(messageBody = it[0] as String,
                    userId = it[1] as String,
                    channelId = it[2] as String,
                    userName = it[3] as String,
                    userAvatar = it[4] as String,
                    userAvatarColor = it[5] as String,
                    id = it[6] as String,
                    timeStamp = DateFormatter.iosFormatter.parse(it[7] as String))

            if (selectedChannel?.id == message.channelId){
                messages.add(message)
                runOnUiThread {
                    messageListAdapter.notifyDataSetChanged()
                    messageListView.smoothScrollToPosition(messages.count() - 1)
                }
            }
        }
    }

    // endregion

    // region Button Click Listeners

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    fun loginBtnNavClicked(view: View) {
        if (LoginManager.isLoggedIn) {
            LoginManager.logout()
            selectedChannel = null
            channels.clear()
            channelListAdapter.notifyDataSetChanged()
            messages.clear()
            messageListAdapter.notifyDataSetChanged()
        } else {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }
    }

    fun addChannelBtnNavClicked(view: View) {
        if (!LoginManager.isLoggedIn)
            return

        val dialog = AddChannelDialog()
        dialog.show(fragmentManager, "AddChannelDialog")
    }

    fun sendMessageBtnClicked(view: View) {
        val text = messageTextView.text.toString()
        if (text.isEmpty()){
            Toast.makeText(this, "Please enter message.", Toast.LENGTH_SHORT).show()
            return
        }

        lateinit var params: Array<String>
        try {
            params = arrayOf(
                    text,
                    UserDataService.id!!,
                    selectedChannel!!.id!!,
                    UserDataService.name!!,
                    UserDataService.avatarName!!,
                    UserDataService.avatarColor!!
            )
        } catch (ex: NullPointerException) {
            Toast.makeText(this, "Failed to send message.", Toast.LENGTH_SHORT).show()
            return
        }

        socket.emit(SocketEvent.NEW_MESSAGE, *params)
        messageTextView.text.clear()
        hideKeyboard()
    }

    // endregion

    // region AddChannelDialogInterface

    override fun onChannelAdded(channel: Channel) {
        socket.emit(SocketEvent.NEW_CHANNEL, channel.name, channel.desc)
    }

    override fun onChannelAddCanceled() {
        Log.d("MainActivity", "Add channel canceled.")
    }

    // endregion
}
