package com.example.jonnyb.swoosh.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.jonnyb.swoosh.R
import kotlinx.android.synthetic.main.activity_skill.*
import constants.selections.EXTRA_PLAYER
import model.Player

class SkillActivity : AppCompatActivity() {
    lateinit private var player: Player

    val toggleAction: (view: View) -> Unit = { view ->
        player.skill = null
        beginnerButton.isChecked = false
        ballerButton.isChecked = false

        when (view.id){
            R.id.beginnerButton -> {
                beginnerButton.isChecked = true
                player.skill = "Beginner"
            }
            R.id.ballerButton -> {
                ballerButton.isChecked = true
                player.skill = "Baller"
            }
        }
    }

    // region Lifecycle Methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skill)

        // player = savedInstanceState?.getParcelable<Player>(EXTRA_SKILL)
        beginnerButton.setOnClickListener(toggleAction)
        ballerButton.setOnClickListener(toggleAction)

        getPlayer()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(EXTRA_PLAYER, player)
    }

    // endregion

    // region Actions

    private fun getPlayer() {
        player = intent.getParcelableExtra<Player>(EXTRA_PLAYER)
    }

    fun skillNextClicked(view: View) {
        if (player.skill == null) {
            Toast.makeText(this, "Please select skill!", Toast.LENGTH_SHORT).show()
            return
        }

        val finishActivity = Intent(this, FinishActivity::class.java)
        finishActivity.putExtra(EXTRA_PLAYER, player)
        startActivity(finishActivity)
    }

    // endregion
}
