package com.gprosper.smack.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.gprosper.smack.R
import com.gprosper.smack.extensions.hideKeyboard
import com.gprosper.smack.services.ApiService
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    // region Lifecycle Methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    // endregion

    // region Actions

    fun loginButtonClicked(view: View) {
        val email = when {
            loginEmailEditText.text.toString().isEmpty() -> {
                Toast.makeText(this, "Please enter email.", Toast.LENGTH_SHORT).show()
                return
            }
            else -> loginEmailEditText.text.toString()
        }
        val password = when {
            loginPasswordEditText.text.toString().isEmpty() -> {
                Toast.makeText(this, "Please enter password.", Toast.LENGTH_SHORT).show()
                return
            }
            else -> loginPasswordEditText.text.toString()
        }

        val loginErrorAction: () -> Unit = {
            enableUserInput()
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
        }

        hideKeyboard()
        disableUserInput()
        ApiService.loginUser(email, password){
            when(it){
                true -> ApiService.getUserDataByEmail(email){
                    when(it){
                        true -> finish()
                        else -> loginErrorAction()
                    }
                }
                else -> loginErrorAction()
            }
        }
    }

    fun signupButtonClicked(view: View) {
        val signupIntent = Intent(this, SignupActivity::class.java)
        startActivity(signupIntent)
        finish()
    }

    // endregion

    // region Helper Methods

    private fun disableUserInput() {
        loginLoginButton.isEnabled = false
        loginSignupButton.isEnabled = false
        loginProgressBar.visibility = View.VISIBLE
    }

    private fun enableUserInput() {
        loginLoginButton.isEnabled = true
        loginSignupButton.isEnabled = true
        loginProgressBar.visibility = View.GONE
    }

    // endregion
}
