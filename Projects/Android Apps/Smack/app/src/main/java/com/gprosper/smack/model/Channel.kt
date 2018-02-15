package com.gprosper.smack.model

/**
 * Created by gprosper on 2/4/18.
 */
data class Channel(val name: String, val desc: String, val id: String? = null){
    override fun toString() = "#${name}"
}