package com.zulfahmi.kadefinalproject.teams.details.overview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zulfahmi.kadefinalproject.R
import com.zulfahmi.kadefinalproject.model.Team
import kotlinx.android.synthetic.main.fragment_overview.*


class OverviewFragment: Fragment(){
    private lateinit var team: Team

    companion object {
        fun newFragment(team: Team): OverviewFragment {
            val fragment = OverviewFragment()
            fragment.team = team

            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        teamDesc.text = team.teamDescription
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

}