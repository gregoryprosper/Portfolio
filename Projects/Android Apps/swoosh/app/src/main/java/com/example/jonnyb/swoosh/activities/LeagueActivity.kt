package com.example.jonnyb.swoosh.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.jonnyb.swoosh.R
import kotlinx.android.synthetic.main.activity_league.*
import constants.selections.EXTRA_PLAYER
import model.Player

class LeagueActivity : BaseActivity() {
    var player = Player()

    val toggleAction: (view: View) -> Unit = { view ->
        player.league = null
        mensButton.isChecked = false
        womensButton.isChecked = false
        coedButton.isChecked = false

        when (view.id){
            R.id.mensButton -> {
                mensButton.isChecked = true
                player.league = "Men's"
            }
            R.id.womensButton -> {
                womensButton.isChecked = true
                player.league = "Women's"
            }
            R.id.coedButton -> {
                coedButton.isChecked = true
                player.league = "Coed"
            }
        }
    }

    // region Lifecycle Methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league)

        mensButton.setOnClickListener(toggleAction)
        womensButton.setOnClickListener(toggleAction)
        coedButton.setOnClickListener(toggleAction)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(EXTRA_PLAYER, player)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null){
            player = savedInstanceState.getParcelable<Player>(EXTRA_PLAYER)
        }
    }

    // endregion

    // region Actions

    fun leagueNextClicked(view: View) {
        if (player.league == null) {
            Toast.makeText(this, "Please select League!", Toast.LENGTH_SHORT).show()
            return
        }

        val skillActivity = Intent(this, SkillActivity::class.java)
        skillActivity.putExtra(EXTRA_PLAYER, player)
        startActivity(skillActivity)
    }

    // endregion
}
