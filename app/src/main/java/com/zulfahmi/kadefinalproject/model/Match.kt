package com.zulfahmi.kadefinalproject.model

import com.google.gson.annotations.SerializedName

data class Match (

    @SerializedName("strSport")
    var sportName: String? = null,

    @SerializedName("idEvent")
    var eventId: String? = null,

    @SerializedName("strDate")
    var eventDate: String? = null,

    @SerializedName("strTime")
    var eventTime: String? = null,

    @SerializedName("strHomeTeam")
    var homeTeam: String? = null,

    @SerializedName("idHomeTeam")
    var homeTeamId: String? = null,

    @SerializedName("intHomeScore")
    var homeScore: String? = null,

    @SerializedName("strAwayTeam")
    var awayTeam: String? = null,

    @SerializedName("idAwayTeam")
    var awayTeamId: String? = null,

    @SerializedName("intAwayScore")
    var awayScore: String? = null
)