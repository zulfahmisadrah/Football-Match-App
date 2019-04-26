package com.zulfahmi.kadefinalproject.teams

import com.zulfahmi.kadefinalproject.model.League
import com.zulfahmi.kadefinalproject.model.Team

interface TeamsView{
    fun showLoading()
    fun hideLoading()
    fun showLeague(data: List<League>)
    fun showTeamList(data: List<Team>)
}