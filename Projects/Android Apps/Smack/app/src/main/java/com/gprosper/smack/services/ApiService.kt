package com.gprosper.smack.services

import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.gprosper.smack.application.MyApplication
import com.gprosper.smack.model.Channel
import com.gprosper.smack.model.Message
import com.gprosper.smack.model.User
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by gprosper on 1/30/18.
 */

typealias CompleteCallback = (Boolean) -> Unit

object ApiService {
    private val baseUrl = "https://com-prosper-smack.herokuapp.com/v1"
    private var requestQueue: RequestQueue = Volley.newRequestQueue(MyApplication.instance)

    // region User Methods

    fun registerUser(email: String, password: String, complete: CompleteCallback){
        val url = "${baseUrl}/account/register"

        val jsonBody = JSONObject().apply {
            put("email", email)
            put("password", password)
        }

        val request = object : StringRequest(Method.POST, url, Response.Listener { resp ->
            Log.d("Register Success", resp)
            complete(true)
        },Response.ErrorListener { error ->
            Log.d("Error", "Couldn't register user: ${error.localizedMessage}")
            complete(false)
        }){
            override fun getBodyContentType() = "application/json; charset=utf-8"
            override fun getBody() = jsonBody.toString().toByteArray()
        }

        requestQueue.add(request)
    }

    fun loginUser(email: String, password: String, complete: CompleteCallback){
        val url = "${baseUrl}/account/login"

        val jsonBody = JSONObject().apply {
            put("email", email)
            put("password", password)
        }

        val request = object : JsonObjectRequest(Method.POST, url, jsonBody, Response.Listener { resp ->
            Log.d("Login Success", resp.toString(2))
            try {
                LoginManager.authToken = resp.getString("token")
                LoginManager.authEmail = email
                complete(true)
            }
            catch (ex: JSONException){
                Log.d("JSON", ex.localizedMessage)
                complete(false)
            }
        },Response.ErrorListener { error ->
            Log.d("Error", "Couldn't login user: ${error.localizedMessage}")
            complete(false)
        }){}

        requestQueue.add(request)
    }

    fun addUser(user: User, complete: CompleteCallback){
        val url = "${baseUrl}/user/add"

        val jsonBody = with(user){
            JSONObject().apply {
                put("name", name)
                put("email", email)
                put("avatarName", avatarName)
                put("avatarColor", avatarColor)
            }
        }

        val request = object : JsonObjectRequest(Method.POST, url, jsonBody, Response.Listener { resp ->
            Log.d("Add User Success", resp.toString(2))
            try {
                LoginManager.isLoggedIn = true
                UserDataService.id = resp.getString("_id")
                UserDataService.name = resp.getString("name")
                UserDataService.email = resp.getString("email")
                UserDataService.avatarName = resp.getString("avatarName")
                UserDataService.avatarColor = resp.getString("avatarColor")
                UserDataService.broadcastChange()
                complete(true)
            }
            catch (ex: JSONException){
                Log.d("JSON", ex.localizedMessage)
                complete(false)
            }
        },Response.ErrorListener { error ->
            Log.d("Error", "Couldn't add user: ${error.localizedMessage}")
            complete(false)
        }){
            override fun getHeaders() = mutableMapOf("Authorization" to "Bearer ${LoginManager.authToken}")
        }

        requestQueue.add(request)
    }

    fun getUserDataByEmail(email: String, complete: CompleteCallback){
        val url = "${baseUrl}/user/byemail/${email}"

        val request = object : JsonObjectRequest(Method.GET, url, null, Response.Listener { resp ->
            Log.d("Find User Success", resp.toString(2))
            try {
                LoginManager.isLoggedIn = true
                UserDataService.id = resp.getString("_id")
                UserDataService.name = resp.getString("name")
                UserDataService.email = resp.getString("email")
                UserDataService.avatarName = resp.getString("avatarName")
                UserDataService.avatarColor = resp.getString("avatarColor")
                UserDataService.broadcastChange()
                complete(true)
            }
            catch (ex: JSONException){
                Log.d("JSON", ex.localizedMessage)
                complete(false)
            }
        },Response.ErrorListener { error ->
            Log.d("Error", "Couldn't get user data: ${error.localizedMessage}")
            complete(false)
        }){
            override fun getHeaders() = mutableMapOf("Authorization" to "Bearer ${LoginManager.authToken}")
        }

        requestQueue.add(request)
    }

    // endregion

    // region Channel Methods

    fun getChannels(complete: (List<Channel>?) -> Unit){
        val url = "${baseUrl}/channel"

        val request = object : JsonArrayRequest(Method.GET, url, null, Response.Listener { resp ->
            Log.d("Get Channels Success", resp.toString(2))
            try {
                val channels = mutableListOf<Channel>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    val id = item.getString("_id")
                    val name = item.getString("name")
                    val description = item.getString("description")
                    val c = Channel(name, description, id)
                    channels.add(c);
                }
                complete(channels)
            }
            catch (ex: JSONException){
                Log.d("JSON", ex.localizedMessage)
                complete(null)
            }
        },Response.ErrorListener { error ->
            Log.d("Error", "Couldn't get channels: ${error.localizedMessage}")
            complete(null)
        }){
            override fun getHeaders() = mutableMapOf("Authorization" to "Bearer ${LoginManager.authToken}")
        }

        requestQueue.add(request)
    }

    // endregion

    // region Message Methods

    fun getMessages(channel: String, complete: (List<Message>?) -> Unit){
        val url = "${baseUrl}/message/byChannel/${channel}"

        val request = object : JsonArrayRequest(Method.GET, url, null, Response.Listener { resp ->
            Log.d("Get Messages Success", resp.toString(2))
            try {
                val messages = mutableListOf<Message>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    val id = item.getString("_id")
                    val messageBody = item.getString("messageBody")
                    val userId = item.getString("userId")
                    val channelId = item.getString("channelId")
                    val userName = item.getString("userName")
                    val userAvatar = item.getString("userAvatar")
                    val userAvatarColor = item.getString("userAvatarColor")
                    val timeStamp = DateFormatter.iosFormatter.parse(item.getString("timeStamp"))
                    val m = Message(messageBody = messageBody,
                            userId = userId,
                            channelId = channelId,
                            userName = userName,
                            userAvatar = userAvatar,
                            userAvatarColor =  userAvatarColor,
                            timeStamp = timeStamp,
                            id = id)
                    messages.add(m);
                }
                complete(messages)
            }
            catch (ex: JSONException){
                Log.d("JSON", ex.localizedMessage)
                complete(null)
            }
        },Response.ErrorListener { error ->
            Log.d("Error", "Failed to get messages: ${error.localizedMessage}")
            complete(null)
        }){
            override fun getHeaders() = mutableMapOf("Authorization" to "Bearer ${LoginManager.authToken}")
        }

        requestQueue.add(request)
    }

    // endregion
}