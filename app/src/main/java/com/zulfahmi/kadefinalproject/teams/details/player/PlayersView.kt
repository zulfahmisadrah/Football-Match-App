package com.zulfahmi.kadefinalproject.teams.details.player

import com.zulfahmi.kadefinalproject.model.Player

interface PlayersView{
    fun showLoading()
    fun hideLoading()
    fun showPlayerList(data: List<Player>)
}