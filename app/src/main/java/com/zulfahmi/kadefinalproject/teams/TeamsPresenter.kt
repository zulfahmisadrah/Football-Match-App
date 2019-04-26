package com.zulfahmi.kadefinalproject.teams

import com.google.gson.Gson
import com.zulfahmi.kadefinalproject.api.ApiRepository
import com.zulfahmi.kadefinalproject.api.TheSportDBApi
import com.zulfahmi.kadefinalproject.model.LeagueResponse
import com.zulfahmi.kadefinalproject.model.TeamResponse
import com.zulfahmi.kadefinalproject.util.CoroutineContextProvider
import org.jetbrains.anko.coroutines.experimental.bg
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class TeamsPresenter(private val view: TeamsView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getLeague(){
        view.showLoading()
        GlobalScope.async(context.main){
            val data = bg {gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getLeague()),
                LeagueResponse::class.java)
            }
            view.showLeague(data.await().countrys)
            view.hideLoading()
        }
    }

    fun getTeamList(query: String?, type: Int = 1){
        view.showLoading()
        val api = if(type == 1) TheSportDBApi.getListTeams(query) else TheSportDBApi.getTeams(query)
        GlobalScope.async(context.main){
            val data = bg {gson.fromJson(apiRepository
                .doRequest(api),TeamResponse::class.java)
            }
            view.showTeamList(data.await().teams)
            view.hideLoading()
        }
    }
}