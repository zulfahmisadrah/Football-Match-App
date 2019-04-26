package com.zulfahmi.kadefinalproject.favorites

data class Favorite(val id:Long?,
                    val matchId:String?,
                    val matchDate: String?,
                    val matchTime: String?,
                    val homeTeamId: String?,
                    val homeTeam: String?,
                    val homeScore: String?,
                    val awayTeamId: String?,
                    val awayTeam: String?,
                    val awayScore: String?){

    companion object {
        const val TABLE_FAVORITE = "TABLE_FAVORITE"
        const val ID = "ID_"
        const val MATCH_ID = "MATCH_ID"
        const val MATCH_DATE = "MATCH_DATE"
        const val MATCH_TIME = "MATCH_TIME"
        const val HOME_TEAM_ID: String = "HOME_TEAM_ID"
        const val HOME_TEAM_NAME = "HOME_TEAM_NAME"
        const val HOME_SCORE = "HOME_SCORE"
        const val AWAY_TEAM_ID: String = "AWAY_TEAM_ID"
        const val AWAY_TEAM_NAME = "AWAY_TEAM_NAME"
        const val AWAY_SCORE = "AWAY_SCORE"
    }
}