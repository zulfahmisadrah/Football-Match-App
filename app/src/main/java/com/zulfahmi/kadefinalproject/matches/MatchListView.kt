package com.zulfahmi.kadefinalproject.matches

import com.zulfahmi.kadefinalproject.model.League
import com.zulfahmi.kadefinalproject.model.Match

interface MatchListView{
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Match>)
    fun showLeagueList(data: List<League>)
}