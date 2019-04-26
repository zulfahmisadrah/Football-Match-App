package com.zulfahmi.kadefinalproject.matches

import com.google.gson.Gson
import com.zulfahmi.kadefinalproject.model.MatchResponse
import com.zulfahmi.kadefinalproject.api.ApiRepository
import com.zulfahmi.kadefinalproject.api.TheSportDBApi
import com.zulfahmi.kadefinalproject.model.LeagueResponse
import com.zulfahmi.kadefinalproject.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchListPresenter(private val view: MatchListView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getLeagueList(){
        view.showLoading()
        GlobalScope.async(context.main){
            val data = bg {gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getLeague()),
                LeagueResponse::class.java)
            }

            view.showLeagueList(data.await().countrys)
            view.hideLoading()
        }
    }

    fun getLast15EventsList(leagueId: String?) {
        view.showLoading()
        GlobalScope.async(context.main) {
            val data = bg {gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getLast15Events(leagueId)),
                MatchResponse::class.java)
            }

            view.showMatchList(data.await().events)
            view.hideLoading()
        }
    }

    fun getNext15EventsList(leagueId: String?) {
        view.showLoading()
        GlobalScope.async(context.main) {
            val data = bg {gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getNext15Events(leagueId)),
                MatchResponse::class.java)
            }

            view.showMatchList(data.await().events)
            view.hideLoading()
        }
    }
}