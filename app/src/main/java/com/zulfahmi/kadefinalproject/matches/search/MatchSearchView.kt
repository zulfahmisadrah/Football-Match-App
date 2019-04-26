package com.zulfahmi.kadefinalproject.matches.search

import com.zulfahmi.kadefinalproject.model.Match

interface MatchSearchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Match>)
}
