package com.zulfahmi.kadefinalproject.presenter

import com.google.gson.Gson
import com.zulfahmi.kadefinalproject.TestContextProvider
import com.zulfahmi.kadefinalproject.api.ApiRepository
import com.zulfahmi.kadefinalproject.api.TheSportDBApi
import com.zulfahmi.kadefinalproject.matches.details.MatchDetailPresenter
import com.zulfahmi.kadefinalproject.model.*
import com.zulfahmi.kadefinalproject.matches.details.MatchDetailView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest= Config.NONE)
class MatchDetailPresenterTest {

    @Mock private lateinit var view: MatchDetailView
    @Mock private lateinit var apiRepository: ApiRepository
    @Mock private lateinit var gson: Gson

    private lateinit var  presenter: MatchDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchDetailPresenter(
            view,
            apiRepository,
            gson,
            TestContextProvider()
        )
    }

    @Test
    fun getMatchDetail() {
        val eventId = "584475"
        val events:List<MatchDetail> = listOf(MatchDetail())
        val response = MatchDetailResponse(events)

        `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getEventDetail(eventId)),
            MatchDetailResponse::class.java)).thenReturn(response)

        presenter.getMatchDetail(eventId)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showMatchDetail(response.events[0])
        Mockito.verify(view).hideLoading()
    }

    //Pilih salah satu
    /*@Test
    fun getTeamBadge() {
        val teamId = "1"
        val isHomeTeam = false
        val teams:List<Team> = listOf(Team())
        val response = TeamResponse(teams)

        `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamDetail(teamId)),
            TeamResponse::class.java)).thenReturn(response)
        presenter.getTeamBadge(teamId,isHomeTeam)


        Mockito.verify(view).showLoading()
        Mockito.verify(view).showTeamBadge(response.teams[0], isHomeTeam)
        Mockito.verify(view).hideLoading()
    }*/
}