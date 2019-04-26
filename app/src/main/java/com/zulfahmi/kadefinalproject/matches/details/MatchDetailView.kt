package com.zulfahmi.kadefinalproject.matches.details

import com.zulfahmi.kadefinalproject.model.MatchDetail
import com.zulfahmi.kadefinalproject.model.Team

interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
    fun showMatchDetail(data: MatchDetail)
    fun showTeamBadge(data: Team, isHomeTeam: Boolean)

}