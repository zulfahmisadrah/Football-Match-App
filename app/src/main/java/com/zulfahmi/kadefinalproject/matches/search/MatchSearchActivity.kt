package com.zulfahmi.kadefinalproject.matches.search

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.support.v7.widget.SearchView
import android.view.MenuItem

import com.google.gson.Gson
import com.zulfahmi.kadefinalproject.R.id.action_search
import com.zulfahmi.kadefinalproject.R.layout.fragment_search_match
import com.zulfahmi.kadefinalproject.R.menu.search_view
import com.zulfahmi.kadefinalproject.adapter.MatchListAdapter
import com.zulfahmi.kadefinalproject.api.ApiRepository
import com.zulfahmi.kadefinalproject.matches.details.MatchDetailActivity
import com.zulfahmi.kadefinalproject.model.Match
import com.zulfahmi.kadefinalproject.util.invisible
import com.zulfahmi.kadefinalproject.util.visible
import kotlinx.android.synthetic.main.fragment_search_match.*
import org.jetbrains.anko.startActivity

class MatchSearchActivity : AppCompatActivity(), MatchSearchView {
    private var match: MutableList<Match> = mutableListOf()
    private lateinit var presenter: MatchSearchPresenter
    private lateinit var adapter: MatchListAdapter
    private lateinit var query: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(fragment_search_match)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = MatchSearchPresenter(this,  ApiRepository(), Gson())

        adapter = MatchListAdapter(match) {
            startActivity<MatchDetailActivity>(
                "MATCH_ID" to it.eventId,
                "HOME_TEAM_ID" to it.homeTeamId,
                "AWAY_TEAM_ID" to it.awayTeamId
            )
        }

        rvMatchList.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener {
            presenter.getEventsSearch(query)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(search_view, menu)

        val searchMenu = menu?.findItem(action_search)
        val searchView = searchMenu?.actionView as SearchView
        searchView.setIconifiedByDefault(false)
        searchView.isIconified = false
        searchView.requestFocusFromTouch()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                if(text?.length!! > 2) {
                    query = text
                    presenter.getEventsSearch(query)
                }
                return false
            }
        })

        return true
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchList(data: List<Match>) {
        hideLoading()
        match.clear()
        data.forEach {
            if(it.sportName.equals("Soccer")){
                match.add(it)
            }
        }
        adapter.notifyDataSetChanged()
        rvMatchList.scrollToPosition(0)
    }

}
