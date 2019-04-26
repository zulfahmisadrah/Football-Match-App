package com.zulfahmi.kadefinalproject.teams

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.gson.Gson
import com.zulfahmi.kadefinalproject.R
import com.zulfahmi.kadefinalproject.R.id.action_search
import com.zulfahmi.kadefinalproject.R.menu.search_view
import com.zulfahmi.kadefinalproject.api.ApiRepository
import com.zulfahmi.kadefinalproject.model.League
import com.zulfahmi.kadefinalproject.model.Team
import com.zulfahmi.kadefinalproject.teams.details.TeamsDetailActivity
import com.zulfahmi.kadefinalproject.util.invisible
import com.zulfahmi.kadefinalproject.util.visible
import kotlinx.android.synthetic.main.fragment_teams.*

class TeamsFragment: Fragment(), TeamsView{
    private var teams: MutableList<Team> = mutableListOf()
    private var leagues: MutableList<League> = mutableListOf()
    private lateinit var presenter: TeamsPresenter
    private lateinit var adapter: TeamsAdapter
    private var leagueCountry = "Spain"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = TeamsPresenter(this, ApiRepository(), Gson())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter.getLeague()

        adapter = TeamsAdapter(teams){
            TeamsDetailActivity.start(context, it)
        }
        rvMatchList.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener {
            presenter.getTeamList(leagueCountry)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        presenter.getTeamList(leagueCountry)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(search_view, menu)

        val searchItem = menu?.findItem(action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                if(text?.length!! > 2) {
                    presenter.getTeamList(text, 2)
                }
                return false
            }

        })
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showLeague(data: List<League>) {
        hideLoading()
        leagues.clear()
        leagues.addAll(data)

        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, leagues)
        spinner.adapter = spinnerAdapter

        spinner.setSelection(spinnerAdapter.getPosition(League("4335", "Spain", "Spanish La Liga")))

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                val league = spinner.selectedItem as League
                presenter.getTeamList(league.strCountry)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    override fun showTeamList(data: List<Team>) {
        swipeRefreshLayout.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

}