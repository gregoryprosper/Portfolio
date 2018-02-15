package com.gprosper.smack.extensions

import android.app.DialogFragment
import android.view.inputmethod.InputMethodManager

/**
 * Created by gprosper on 2/4/18.
 */
fun DialogFragment.hideKeyboard() {
    val inputManager = activity.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (inputManager.isAcceptingText)
        inputManager.hideSoftInputFromWindow(activity.currentFocus.windowToken, 0)
}