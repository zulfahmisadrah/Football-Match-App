package com.zulfahmi.kadefinalproject.model

import com.google.gson.annotations.SerializedName

data class League (

    @SerializedName("idLeague")
    var idLeague: String? = null,

    @SerializedName("strCountry")
    var strCountry: String? = null,

    @SerializedName("strLeague")
    var strLeague: String? = null){

        override fun toString(): String {
            return strLeague.orEmpty()
        }
    }
