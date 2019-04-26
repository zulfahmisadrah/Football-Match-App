package com.zulfahmi.kadefinalproject.api

import com.zulfahmi.kadefinalproject.BuildConfig

object TheSportDBApi {

    private fun buildUrl(path: String, id: String? = null): String{
        val url = BuildConfig.BASE_URL+"api/v1/json/"+BuildConfig.TSDB_API_KEY+"/"+path
        return if(id.isNullOrEmpty()) url else url+"?id="+id
    }

    fun getLast15Events(leagueId: String?) = buildUrl("eventspastleague.php", leagueId)
    fun getNext15Events(leagueId: String?) = buildUrl("eventsnextleague.php", leagueId)
    fun getEventDetail(eventId: String?) = buildUrl("lookupevent.php", eventId)
    fun getListTeams(leagueCountry: String?) = buildUrl("search_all_teams.php?s=Soccer&c=" + leagueCountry)
    fun getLeague() = buildUrl("search_all_leagues.php?s=Soccer")
    fun getTeamDetail(teamId: String?) = buildUrl("lookupteam.php", teamId)
    fun getListPlayer(teamId: String?) = buildUrl("lookup_all_players.php", teamId)
    fun getEvents(keyword: String?) = buildUrl("searchevents.php?e="+keyword)
    fun getTeams(keyword: String?) = buildUrl("searchteams.php?t="+keyword)
}