package com.zulfahmi.kadefinalproject.teams.details

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.squareup.picasso.Picasso
import com.zulfahmi.kadefinalproject.R
import com.zulfahmi.kadefinalproject.R.id.add_to_favorites
import com.zulfahmi.kadefinalproject.adapter.PagerAdapter
import com.zulfahmi.kadefinalproject.db.database
import com.zulfahmi.kadefinalproject.favorites.FavoriteTeams
import com.zulfahmi.kadefinalproject.model.Team
import com.zulfahmi.kadefinalproject.teams.details.overview.OverviewFragment
import com.zulfahmi.kadefinalproject.teams.details.player.PlayersFragment
import kotlinx.android.synthetic.main.activity_teams_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity

class TeamsDetailActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_PARAM = "EXTRA_PARAM"

        fun start(context: Context?, team: Team) {
            context?.startActivity<TeamsDetailActivity>(EXTRA_PARAM to team)
        }

    }

    private lateinit var team: Team

    private var menuFavorites: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams_detail)

        val actionbar = supportActionBar
        actionbar?.setElevation(0f)
        actionbar?.title = ""
        actionbar?.setDisplayHomeAsUpEnabled(true)

        team = intent.getParcelableExtra(EXTRA_PARAM)

        Picasso.get().load(team.teamBadge).into(badge)
        name.text = team.teamName
        year.text = team.teamFormedYear
        stadium.text = team.teamStadium

        favoriteState()

        val adapter = PagerAdapter(supportFragmentManager)
        adapter.addFrag(OverviewFragment.newFragment(team), "OVERVIEW")
        adapter.addFrag(PlayersFragment.newFragment(team.teamId!!), "PLAYERS")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuFavorites = menu
        setFavorite()

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
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

    private fun favoriteState(){
        database.use {
            val result = select(FavoriteTeams.TABLE_TEAMS)
                .whereArgs("(ID_TEAM = {id})",
                    "id" to team.teamId!!)
            val favorite = result.parseList(classParser<FavoriteTeams>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(
                    FavoriteTeams.TABLE_TEAMS,
                    FavoriteTeams.ID_TEAM to team.teamId,
                    FavoriteTeams.TEAM to team.teamName,
                    FavoriteTeams.TEAM_BADGE to team.teamBadge,
                    FavoriteTeams.STADIUM to team.teamStadium,
                    FavoriteTeams.FORMED_YEAR to team.teamFormedYear,
                    FavoriteTeams.DESCRIPTION to team.teamDescription)
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
                    FavoriteTeams.TABLE_TEAMS, "(ID_TEAM = {id})",
                    "id" to team.teamId!!)
            }
            Snackbar.make( linearLayout,"Removed from favorite", Snackbar.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
            Snackbar.make( linearLayout,e.localizedMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            menuFavorites?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_favorite)
        } else {
            menuFavorites?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_favorite)
        }
    }
}
