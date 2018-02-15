package com.gprosper.smack.services

import android.app.Application
import android.content.Intent
import android.graphics.Color
import android.support.v4.content.LocalBroadcastManager
import com.gprosper.smack.application.MyApplication
import com.gprosper.smack.utilities.BROADCAST_USER_DATA_CHANGED
import java.util.*

/**
 * Created by gprosper on 2/1/18.
 */
object UserDataService {
    var id: String? = null
    var name: String? = null
    var email: String? = null
    var avatarName: String? = null
    var avatarColor: String? = null

    fun returnAvatarColor(components: String): Int {
        val strippedColor = components
                .replace("[", "")
                .replace("]", "")
                .replace(",", "")

        var r = 0
        var g = 0
        var b = 0

        val scanner = Scanner(strippedColor)
        if (scanner.hasNext()){
            r = (scanner.nextDouble() * 255).toInt()
            g = (scanner.nextDouble() * 255).toInt()
            b = (scanner.nextDouble() * 255).toInt()
        }

        return Color.rgb(r, g, b)
    }

    fun logout() {
        id = null
        name = null
        email = null
        avatarName = null
        avatarColor = null
        broadcastChange()
    }

    fun broadcastChange() {
        val userDataChange = Intent(BROADCAST_USER_DATA_CHANGED)
        LocalBroadcastManager.getInstance(MyApplication.instance).sendBroadcast(userDataChange)
    }
}