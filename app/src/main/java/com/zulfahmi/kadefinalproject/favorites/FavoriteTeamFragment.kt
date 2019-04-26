package com.zulfahmi.kadefinalproject.favorites

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zulfahmi.kadefinalproject.R
import com.zulfahmi.kadefinalproject.db.database
import com.zulfahmi.kadefinalproject.model.Team
import com.zulfahmi.kadefinalproject.teams.details.TeamsDetailActivity
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteTeamFragment : Fragment(){
    private var favoriteTeams: MutableList<FavoriteTeams> = mutableListOf()
    private lateinit var adapter: FavoriteTeamsAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        swipeRefreshLayout.setOnRefreshListener {
            getFavorite()
        }
        adapter = FavoriteTeamsAdapter(favoriteTeams) {
            val team = Team(
                it.teamId,
                it.teamName,
                it.teamBadge,
                it.teamStadium,
                it.teamFormedYear,
                it.teamDescription
            )
            TeamsDetailActivity.start(context, team)
        }
        rvMatchList_fav.adapter = adapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onResume() {
        super.onResume()
        getFavorite()
    }

    private fun getFavorite() {
        favoriteTeams.clear()
        context?.database?.use {
            swipeRefreshLayout.isRefreshing = false
            val result = select(FavoriteTeams.TABLE_TEAMS)
            val favorite = result.parseList(classParser<FavoriteTeams>())
            favoriteTeams.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }
}