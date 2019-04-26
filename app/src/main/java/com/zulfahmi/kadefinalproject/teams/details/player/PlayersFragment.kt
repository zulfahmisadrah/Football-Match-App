package com.zulfahmi.kadefinalproject.teams.details.player

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.zulfahmi.kadefinalproject.R
import com.zulfahmi.kadefinalproject.api.ApiRepository
import com.zulfahmi.kadefinalproject.model.Player
import com.zulfahmi.kadefinalproject.teams.details.player.detail.PlayerDetailActivity
import com.zulfahmi.kadefinalproject.util.invisible
import com.zulfahmi.kadefinalproject.util.visible
import kotlinx.android.synthetic.main.fragment_players.*
import org.jetbrains.anko.*

class PlayersFragment: Fragment(), PlayersView{
    private var players: MutableList<Player> = mutableListOf()
    private lateinit var presenter: PlayersPresenter
    private lateinit var adapter: PlayersAdapter
    private lateinit var teamId: String

    companion object {
        fun newFragment(teamId: String): PlayersFragment{
            val fragment = PlayersFragment()
            fragment.teamId = teamId
            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayersPresenter(this, request, gson)
        presenter.getPlayerList(teamId)

        adapter = PlayersAdapter(players){
            PlayerDetailActivity.start(context, it)
        }

        rvMatchList.adapter = adapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_players, container, false)
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPlayerList(data: List<Player>) {
        hideLoading()
        players.clear()
        players.addAll(data)
        adapter.notifyDataSetChanged()
    }
}