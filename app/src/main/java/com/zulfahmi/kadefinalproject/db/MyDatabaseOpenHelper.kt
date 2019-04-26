package com.zulfahmi.kadefinalproject.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.zulfahmi.kadefinalproject.favorites.Favorite
import com.zulfahmi.kadefinalproject.favorites.FavoriteTeams
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(context: Context): ManagedSQLiteOpenHelper(context, "Favorite.db", null, 1){
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(context: Context): MyDatabaseOpenHelper {
            if(instance ==null)
                instance =
                        MyDatabaseOpenHelper(context.applicationContext)
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            Favorite.TABLE_FAVORITE, true,
            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.MATCH_ID to TEXT + UNIQUE,
            Favorite.MATCH_DATE to TEXT,
            Favorite.MATCH_TIME to TEXT,
            Favorite.HOME_TEAM_ID to TEXT,
            Favorite.HOME_TEAM_NAME to TEXT,
            Favorite.HOME_SCORE to TEXT,
            Favorite.AWAY_TEAM_ID to TEXT,
            Favorite.AWAY_TEAM_NAME to TEXT,
            Favorite.AWAY_SCORE to TEXT)

        db.createTable(
            FavoriteTeams.TABLE_TEAMS, true,
            FavoriteTeams.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteTeams.ID_TEAM to TEXT,
            FavoriteTeams.TEAM to TEXT,
            FavoriteTeams.TEAM_BADGE to TEXT,
            FavoriteTeams.STADIUM to TEXT,
            FavoriteTeams.FORMED_YEAR to TEXT,
            FavoriteTeams.DESCRIPTION to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVerion: Int, newVerson: Int){
        db.dropTable(Favorite.TABLE_FAVORITE, true)
        db.dropTable(FavoriteTeams.TABLE_TEAMS, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)
