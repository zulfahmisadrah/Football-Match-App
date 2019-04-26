package com.zulfahmi.kadefinalproject.matches.details

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.zulfahmi.kadefinalproject.R
import com.zulfahmi.kadefinalproject.api.ApiRepository
import com.zulfahmi.kadefinalproject.db.database
import com.zulfahmi.kadefinalproject.model.MatchDetail
import com.zulfahmi.kadefinalproject.model.Team
import com.zulfahmi.kadefinalproject.R.menu.detail_menu
import com.zulfahmi.kadefinalproject.R.drawable.ic_add_favorite
import com.zulfahmi.kadefinalproject.R.drawable.ic_added_favorite
import com.zulfahmi.kadefinalproject.R.id.add_to_favorites
import com.zulfahmi.kadefinalproject.favorites.Favorite
import com.zulfahmi.kadefinalproject.util.*
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class MatchDetailActivity : AppCompatActivity(), MatchDetailView {
    private val presenter: MatchDetailPresenter =
        MatchDetailPresenter(this, ApiRepository(), Gson())
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var match: MatchDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        val actionbar = supportActionBar
        actionbar?.title = "Match Detail"
        actionbar?.setDisplayHomeAsUpEnabled(true)

        val matchId = intent.getStringExtra("MATCH_ID")
        val homeTeamId = intent.getStringExtra("HOME_TEAM_ID")
        val awayTeamId = intent.getStringExtra("AWAY_TEAM_ID")

        favoriteState(matchId)
        presenter.getMatchDetail(matchId)
        presenter.getTeamBadge(homeTeamId, true)
        presenter.getTeamBadge(awayTeamId, false)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorites -> {
                if (isFavorite)
                    removeFromFavorite()
                else
                    addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading() {
        progressBarDetail.visible()
    }

    override fun hideLoading() {
        progressBarDetail.invisible()
    }

    override fun showMatchDetail(data: MatchDetail) {
        match = data
        date.text = data.eventDate?.formatDate()
        time.text = getTimeFormat(data.eventTime)
        homeTeam.text = data.homeTeam
        awayTeam.text = data.awayTeam
        homeScore.text = data.homeScore
        awayScore.text = data.awayScore
        homeGoals.text = data.homeGoalDetails?.parseGoals()
        awayGoals.text = data.awayGoalDetails?.parseGoals()
        homeShots.text = data.homeShots?.parse()
        awayShots.text = data.awayShots?.parse()
        homeGK.text = data.homeGoalKeeper?.parse()
        awayGK.text = data.awayGoalKeeper?.parse()
        homeDF.text = data.homeDefense?.parse()
        awayDF.text = data.awayDefense?.parse()
        homeMF.text = data.homeMidfield?.parse()
        awayMF.text = data.awayMidfield?.parse()
        homeFW.text = data.homeForward?.parse()
        awayFW.text = data.awayForward?.parse()
        homeSubs.text = data.homeSubstitutes?.parse()
        awaySubs.text = data.awaySubstitutes?.parse()
    }

    override fun showTeamBadge(data: Team, isHomeTeam: Boolean) {
        Picasso.get().load(data.teamBadge).into(
            if(isHomeTeam) homeBadge else awayBadge
        )
    }

    private fun favoriteState(matchId: String){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs("(MATCH_ID = {id})",
                    "id" to matchId)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.MATCH_ID to match.eventId,
                    Favorite.MATCH_DATE to match.eventDate,
                    Favorite.MATCH_TIME to match.eventTime,
                    Favorite.HOME_TEAM_ID to match.homeTeamId,
                    Favorite.HOME_TEAM_NAME to match.homeTeam,
                    Favorite.HOME_SCORE to match.homeScore,
                    Favorite.AWAY_TEAM_ID to match.awayTeamId,
                    Favorite.AWAY_TEAM_NAME to match.awayTeam,
                    Favorite.AWAY_SCORE to match.awayScore)
            }
            Snackbar.make( linearLayout,"Added to favorite", Snackbar.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
            Snackbar.make( linearLayout,e.localizedMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(
                    Favorite.TABLE_FAVORITE, "(MATCH_ID = {id})",
                    "id" to match.eventId.toString())
            }
            Snackbar.make( linearLayout,"Removed from favorite", Snackbar.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
            Snackbar.make( linearLayout,e.localizedMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_favorite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_favorite)
    }
}
