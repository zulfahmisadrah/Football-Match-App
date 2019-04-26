package com.zulfahmi.kadefinalproject.matches

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.gson.Gson
import com.zulfahmi.kadefinalproject.matches.details.MatchDetailActivity
import com.zulfahmi.kadefinalproject.R
import com.zulfahmi.kadefinalproject.adapter.MatchListAdapter
import com.zulfahmi.kadefinalproject.api.ApiRepository
import com.zulfahmi.kadefinalproject.model.League
import com.zulfahmi.kadefinalproject.model.Match
import com.zulfahmi.kadefinalproject.util.invisible
import com.zulfahmi.kadefinalproject.util.visible
import kotlinx.android.synthetic.main.fragment_list_match_tab.*
import org.jetbrains.anko.startActivity

class MatchListTabFragment : Fragment(), MatchListView {

    private val match: MutableList<Match> = mutableListOf()
    private val leagues: MutableList<League> = mutableListOf()

    private lateinit var presenter: MatchListPresenter
    private lateinit var adapter: MatchListAdapter

    private var leagueId = "4335"
    private var tab = 1

    companion object {
        fun newFragment(tab: Int, leagueId: String): MatchListTabFragment {
            val fragment = MatchListTabFragment()
            fragment.tab = tab
            fragment.leagueId = leagueId
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MatchListPresenter(this, ApiRepository(), Gson())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter.getLeagueList()

        swipeRefreshLayout.setOnRefreshListener {
            getEventsList()
        }

        adapter = MatchListAdapter(match) {
            context?.startActivity<MatchDetailActivity>(
                "MATCH_ID" to it.eventId,
                "HOME_TEAM_ID" to it.homeTeamId,
                "AWAY_TEAM_ID" to it.awayTeamId
            )
        }
        rvMatchList.adapter = adapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_match_tab, container, false)
    }

    private fun getEventsList() {
        if(tab==1)
            presenter.getLast15EventsList(leagueId)
        else
            presenter.getNext15EventsList(leagueId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getEventsList()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchList(data: List<Match>) {
        swipeRefreshLayout.isRefreshing = false
        match.clear()
        match.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showLeagueList(data: List<League>) {
        hideLoading()
        leagues.clear()
        leagues.addAll(data)

        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, leagues)
        spinner.adapter = spinnerAdapter

        spinner.setSelection(spinnerAdapter.getPosition(League("4335", "Spain","Spanish La Liga")))

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val league = spinner.selectedItem as League

                if(tab==1)
                    presenter.getLast15EventsList(league.idLeague)
                else
                    presenter.getNext15EventsList(league.idLeague)

            }
        }
    }
}
