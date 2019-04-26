package com.zulfahmi.kadefinalproject.teams.details.player.detail

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.squareup.picasso.Picasso
import com.zulfahmi.kadefinalproject.R.layout.activity_players_detail
import com.zulfahmi.kadefinalproject.model.Player

import kotlinx.android.synthetic.main.activity_players_detail.*

import org.jetbrains.anko.startActivity

class PlayerDetailActivity : AppCompatActivity(){

    companion object {
        private const val EXTRA_PARAM = "EXTRA_PARAM"

        fun start(context: Context?, player: Player) {
            context?.startActivity<PlayerDetailActivity>(EXTRA_PARAM to player)
        }
    }

    private lateinit var player: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_players_detail)

        player = intent.getParcelableExtra(EXTRA_PARAM)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = player.playerName

        Picasso.get()
            .load(player.playerImage)
            .into(bg)

        weight.text = player.playerWeight
        height.text = player.playerHeight
        position.text = player.playerPosition
        description.text = player.playerDescription
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
