package com.example.nbastats.utils

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.nbastats.data.Team

class SQLiteHelper(context:Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        const val DATABASE_NAME="nba.db"
        const val DATABASE_VERSION=1
    }
    object TeamEntry:BaseColumns{
        const val TABLE_NAME="teams"
        const val COLUMN_ID="id"
        const val COLUMN_CITY="city"
        const val COLUMN_NAME="name"
        const val COLUMN_TRICODE="tricode"
        const val COLUMN_CONFERENCE="conference"
        const val COLUMN_DIVISION="division"
        const val COLUMN_YEAR="year"
    }
    private val CREATE_TABLE_TEAMS=
            "CREATE TABLE ${TeamEntry.TABLE_NAME} (" +
                    "${TeamEntry.COLUMN_ID} INTEGER PRIMARY KEY, " +
                    "${TeamEntry.COLUMN_CITY} TEXT, " +
                    "${TeamEntry.COLUMN_NAME} TEXT, " +
                    "${TeamEntry.COLUMN_TRICODE} TEXT, " +
                    "${TeamEntry.COLUMN_CONFERENCE} TEXT, " +
                    "${TeamEntry.COLUMN_DIVISION} TEXT)" +
                    "${TeamEntry.COLUMN_YEAR} INTEGER"

    private val DROP_TABLE_TEAMS="DROP TABLE IF EXISTS ${TeamEntry.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_TEAMS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_TABLE_TEAMS)
        onCreate(db)
    }

    fun fillTeams(teams:ArrayList<Team>){
        val db = this.writableDatabase
        for(team in teams) {
            val values = ContentValues().apply {
                put(TeamEntry.COLUMN_ID, team.id)
                put(TeamEntry.COLUMN_CITY, team.city)
                put(TeamEntry.COLUMN_NAME, team.name)
                put(TeamEntry.COLUMN_TRICODE, team.tricode)
                put(TeamEntry.COLUMN_CONFERENCE, team.conference)
                put(TeamEntry.COLUMN_DIVISION, team.division)
                put(TeamEntry.COLUMN_YEAR, team.year)
            }
        }
    }
}