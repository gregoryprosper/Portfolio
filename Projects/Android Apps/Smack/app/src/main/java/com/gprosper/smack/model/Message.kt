package com.gprosper.smack.model

import java.util.*

/**
 * Created by gprosper on 2/7/18.
 */
class Message(val id: String,
              val messageBody: String,
              val userId: String,
              val channelId: String,
              val userName: String,
              val userAvatar: String,
              val userAvatarColor: String,
              val timeStamp: Date) {
}