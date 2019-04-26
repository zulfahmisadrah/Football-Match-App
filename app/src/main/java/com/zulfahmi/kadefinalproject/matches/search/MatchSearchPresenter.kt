package com.zulfahmi.kadefinalproject.matches.search

import com.google.gson.Gson
import com.zulfahmi.kadefinalproject.api.ApiRepository
import com.zulfahmi.kadefinalproject.api.TheSportDBApi
import com.zulfahmi.kadefinalproject.model.MatchResponse
import com.zulfahmi.kadefinalproject.model.MatchSearchResponse
import com.zulfahmi.kadefinalproject.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchSearchPresenter(private val view: MatchSearchView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getEventsSearch(query: String?="") {
        view.showLoading()
        GlobalScope.async(context.main) {
            val data = bg{gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getEvents(query)),
                MatchSearchResponse::class.java)}

            view.hideLoading()
            view.showMatchList(data.await().event)

        }
    }
}
