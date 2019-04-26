package com.zulfahmi.kadefinalproject.favorites

class FavoriteTeams (val id:Long?,
                     val teamId:String?,
                     val teamName: String?,
                     val teamBadge: String?,
                     val teamStadium: String?,
                     val teamFormedYear: String?,
                     val teamDescription: String?){

    companion object {
        const val TABLE_TEAMS = "TABLE_TEAMS"
        const val ID = "ID_"
        const val ID_TEAM = "ID_TEAM"
        const val TEAM = "TEAM"
        const val TEAM_BADGE = "TEAM_BADGE"
        const val STADIUM = "STADIUM"
        const val FORMED_YEAR = "FORMED_YEAR"
        const val DESCRIPTION = "DESCRIPTION"
    }
}
