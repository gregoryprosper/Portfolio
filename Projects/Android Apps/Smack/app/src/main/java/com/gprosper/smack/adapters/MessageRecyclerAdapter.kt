package com.gprosper.smack.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.gprosper.smack.R
import com.gprosper.smack.model.Message
import com.gprosper.smack.services.DateFormatter
import com.gprosper.smack.services.UserDataService
import java.time.ZoneId

/**
 * Created by gprosper on 1/15/18.
 */
class MessageRecycleAdapter(val context: Context, val messages: List<Message>)
    : RecyclerView.Adapter<MessageRecycleAdapter.Holder>() {

    var itemSelectedListener: ((category: Message) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.message_list_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        val category = messages[position]
        holder?.bindCategory(category)
    }

    override fun getItemCount(): Int = messages.count()

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val profileImageView = itemView?.findViewById<ImageView>(R.id.profileImage)
        val messageTextView = itemView?.findViewById<TextView>(R.id.messageTextView)
        val userNameTextView = itemView?.findViewById<TextView>(R.id.userNameTextView)
        val timeStampTextView = itemView?.findViewById<TextView>(R.id.timeStampTextView)

        fun bindCategory(message: Message) {
            messageTextView?.text = message.messageBody
            userNameTextView?.text = message.userName
            timeStampTextView?.text = DateFormatter.localDateFormatter.format(message.timeStamp);

            val id = context.resources.getIdentifier(message.userAvatar, "drawable", context.packageName)
            profileImageView?.setImageResource(id)
            profileImageView?.setBackgroundColor(UserDataService.returnAvatarColor(message.userAvatarColor))

            itemSelectedListener?.let { listener ->
                itemView?.setOnClickListener {
                    listener(message)
                }
            }
        }
    }
}