package com.zulfahmi.kadefinalproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zulfahmi.kadefinalproject.R.id.*
import com.zulfahmi.kadefinalproject.R.layout.activity_home
import com.zulfahmi.kadefinalproject.favorites.FavoriteFragment
import com.zulfahmi.kadefinalproject.favorites.FavoriteMatchFragment
import com.zulfahmi.kadefinalproject.matches.MatchFragment
import com.zulfahmi.kadefinalproject.teams.TeamsFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_home)
        val actionBar = supportActionBar
        actionBar?.setElevation(0f)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                matches -> loadMatchesFragment(savedInstanceState)
                teams -> loadTeamsFragment(savedInstanceState)
                favorites -> loadFavoritesFragment(savedInstanceState)
            }
            true
        }
        bottom_navigation.selectedItemId = matches
    }

    private fun loadMatchesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container,
                    MatchFragment(), MatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadTeamsFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container,
                    TeamsFragment(), TeamsFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container,
                    FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                .commit()
        }
    }
}
