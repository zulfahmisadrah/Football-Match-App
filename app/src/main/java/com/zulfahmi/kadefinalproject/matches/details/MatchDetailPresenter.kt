package com.zulfahmi.kadefinalproject.matches.details

import com.google.gson.Gson
import com.zulfahmi.kadefinalproject.model.MatchDetailResponse
import com.zulfahmi.kadefinalproject.model.TeamResponse
import com.zulfahmi.kadefinalproject.api.ApiRepository
import com.zulfahmi.kadefinalproject.api.TheSportDBApi
import com.zulfahmi.kadefinalproject.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchDetailPresenter(private val view: MatchDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()){
    fun getMatchDetail(eventId: String){
        view.showLoading()
        GlobalScope.async(context.main) {
            val data = bg{
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getEventDetail(eventId)),
                MatchDetailResponse::class.java)}

            view.showMatchDetail(data.await().events[0])
            view.hideLoading()
        }

    }

    fun getTeamBadge(teamId: String, isHomeTeam: Boolean = true) {
        view.showLoading()
        GlobalScope.async(context.main) {
            val data = bg{
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamDetail(teamId)),
                    TeamResponse::class.java
                )}

            view.showTeamBadge(data.await().teams[0], isHomeTeam)
            view.hideLoading()
        }
    }


}