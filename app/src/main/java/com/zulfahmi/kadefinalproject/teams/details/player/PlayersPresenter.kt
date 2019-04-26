package com.zulfahmi.kadefinalproject.teams.details.player

import com.google.gson.Gson
import com.zulfahmi.kadefinalproject.api.ApiRepository
import com.zulfahmi.kadefinalproject.api.TheSportDBApi
import com.zulfahmi.kadefinalproject.model.PlayerResponse
import com.zulfahmi.kadefinalproject.util.CoroutineContextProvider
import org.jetbrains.anko.coroutines.experimental.bg
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class PlayersPresenter(private val view: PlayersView,
                       private val apiRepository: ApiRepository,
                       private val gson: Gson,
                       private val context: CoroutineContextProvider = CoroutineContextProvider()
){

    fun getPlayerList(id: String){
        view.showLoading()
        val api = TheSportDBApi.getListPlayer(id)

        GlobalScope.async(context.main){
            val data = bg {
                gson.fromJson(apiRepository.doRequest(api), PlayerResponse::class.java)
            }

            view.hideLoading()
            view.showPlayerList(data.await().player)
        }
    }
}