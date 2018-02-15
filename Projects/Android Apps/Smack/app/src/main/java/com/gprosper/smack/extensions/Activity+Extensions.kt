package com.gprosper.smack.extensions

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

/**
 * Created by gprosper on 2/4/18.
 */
fun Activity.hideKeyboard() {
    val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (inputManager.isAcceptingText)
        inputManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
}