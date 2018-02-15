package com.gprosper.smack.activities

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.gprosper.smack.R
import com.gprosper.smack.extensions.hideKeyboard
import com.gprosper.smack.model.User
import com.gprosper.smack.services.ApiService
import kotlinx.android.synthetic.main.activity_signup.*
import java.util.*

class SignupActivity : AppCompatActivity() {

    val random: Random by lazy {
         Random()
    }

    var userAvatar = "profileDefault"
    var avatarColor = "[0.5, 0.5, 0.5, 1]"

    // region Lifecycle Methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
    }

    // endregion

    // region Avatar Methods

    fun generateAvatar(view: View) {
        val color = random.nextInt(2)
        val avatar = random.nextInt(28)

        userAvatar = when(color){
            0 -> "light${avatar}"
            1 -> "dark${avatar}"
            else -> "profileDefault"
        }

        val resourceId = resources.getIdentifier(userAvatar, "drawable", packageName)
        signupGenIconButton.setImageResource(resourceId)
    }

    fun generateColor(view: View) {
        val r = random.nextInt(255)
        val g = random.nextInt(255)
        val b = random.nextInt(255)

        avatarColor = "${r.toDouble() / 255}, ${g.toDouble() / 255}, ${b.toDouble() / 255}, 1"
        signupGenIconButton.setBackgroundColor(Color.rgb(r, g, b))
    }

    // endregion

    // region Actions

    fun createUserButtonClicked(view: View) {
        val username = when {
            signupUsernameEditText.text.toString().isEmpty() -> {
                Toast.makeText(this, "Please enter username.", Toast.LENGTH_SHORT).show()
                return
            }
            else -> signupUsernameEditText.text.toString()
        }
        val email = when {
            signupEmailEditText.text.toString().isEmpty() -> {
                Toast.makeText(this, "Please enter email.", Toast.LENGTH_SHORT).show()
                return
            }
            else -> signupEmailEditText.text.toString()
        }
        val password = when {
            signupPasswordEditText.text.toString().isEmpty() -> {
                Toast.makeText(this, "Please enter password.", Toast.LENGTH_SHORT).show()
                return
            }
            else -> signupPasswordEditText.text.toString()
        }

        val errorAction: () -> Unit = {
            enableUserInput()
            Toast.makeText(this, "Failed To Register", Toast.LENGTH_LONG).show()
        }

        hideKeyboard()
        disableUserInput()
        ApiService.registerUser(email, password){
            when(it){
                true -> ApiService.loginUser(email, password){
                    when(it){
                        true -> {
                            val user = User(name = username, email = email, avatarName = userAvatar, avatarColor = avatarColor)
                            ApiService.addUser(user){
                                when(it){
                                    true -> {
                                        finish()
                                    }
                                    else -> errorAction()
                                }
                            }
                        }
                        else -> errorAction()
                    }
                }
                else -> errorAction()
            }
        }
    }

    // endregion

    // region Helper Methods

    private fun disableUserInput() {
        signupGenIconButton.isEnabled = false
        signupGenColorButton.isEnabled = false
        signupCreateUserButton.isEnabled = false
        signupProgressBar.visibility = View.VISIBLE
    }

    private fun enableUserInput() {
        signupGenIconButton.isEnabled = true
        signupGenColorButton.isEnabled = true
        signupCreateUserButton.isEnabled = true
        signupProgressBar.visibility = View.GONE
    }

    // endregion
}
