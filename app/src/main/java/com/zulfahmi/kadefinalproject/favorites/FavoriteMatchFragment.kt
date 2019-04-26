package com.zulfahmi.kadefinalproject.favorites

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zulfahmi.kadefinalproject.matches.details.MatchDetailActivity
import com.zulfahmi.kadefinalproject.R
import com.zulfahmi.kadefinalproject.db.database
import com.zulfahmi.kadefinalproject.matches.MatchListTabFragment
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity

class FavoriteMatchFragment : Fragment(){
    private var favoriteMatch: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoriteAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        swipeRefreshLayout.setOnRefreshListener {
            getFavorite()
        }
        adapter = FavoriteAdapter(favoriteMatch) {
            context?.startActivity<MatchDetailActivity>(
                "MATCH_ID" to it.matchId,
                "HOME_TEAM_ID" to it.homeTeamId,
                "AWAY_TEAM_ID" to it.awayTeamId
            )
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
        favoriteMatch.clear()
        context?.database?.use {
            swipeRefreshLayout.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favoriteMatch.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }
}

