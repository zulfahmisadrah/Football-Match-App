package com.zulfahmi.kadefinalproject.presenter

import com.google.gson.Gson
import com.zulfahmi.kadefinalproject.TestContextProvider
import com.zulfahmi.kadefinalproject.api.ApiRepository
import com.zulfahmi.kadefinalproject.api.TheSportDBApi
import com.zulfahmi.kadefinalproject.matches.MatchListPresenter
import com.zulfahmi.kadefinalproject.model.Match
import com.zulfahmi.kadefinalproject.model.MatchResponse
import com.zulfahmi.kadefinalproject.matches.MatchListView
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MatchListPresenterTest {

    @Mock private lateinit var view: MatchListView
    @Mock private lateinit var apiRepository: ApiRepository
    @Mock private lateinit var gson: Gson

    private lateinit var presenter: MatchListPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchListPresenter(
            view,
            apiRepository,
            gson,
            TestContextProvider()
        )
    }

    @Test
    fun getLast15EventsList() {

        val leagueId = 4335
        val events:MutableList<Match> = mutableListOf()
        val response = MatchResponse(events)

        `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getLast15Events(leagueId)),
                MatchResponse::class.java)).thenReturn(response)

        presenter.getLast15EventsList(leagueId)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showMatchList(events)
        Mockito.verify(view).hideLoading()

    }

    //Pilih salah satu
    /*@Test
    fun getNext15EventsList() {
        val leagueId = 4335
        val events:MutableList<Match> = mutableListOf()
        val response = MatchResponse(events)

        `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getNext15Events(leagueId)),
                MatchResponse::class.java)).thenReturn(response)

        presenter.getNext15EventsList(leagueId)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showMatchList(events)
        Mockito.verify(view).hideLoading()
    }*/
}