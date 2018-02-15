package com.gprosper.smack.application

import android.app.Application

/**
 * Created by gprosper on 1/30/18.
 */
class MyApplication: Application() {
    companion object {
        lateinit var instance: MyApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}