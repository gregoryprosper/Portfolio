package com.example.jonnyb.swoosh.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.jonnyb.swoosh.R
import constants.selections.EXTRA_PLAYER
import kotlinx.android.synthetic.main.activity_finish.*
import model.Player

class FinishActivity : BaseActivity() {

    // region Lifecycle Methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)
        getInfo()
    }

    // endregion

    // region Actions

    private fun getInfo() {
        val player = intent.getParcelableExtra<Player>(EXTRA_PLAYER)
        searchLeaguesText.text = "Looking for a ${player.league} ${player.skill} league near you..."
    }

    // endregion
}
