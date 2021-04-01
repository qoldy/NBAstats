package com.example.nbastats.utils

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.nbastats.data.Player
import com.example.nbastats.data.Standings
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
    }
    private val CREATE_TABLE_TEAMS=
            "CREATE TABLE ${TeamEntry.TABLE_NAME} (" +
                    "${TeamEntry.COLUMN_ID} TEXT, " +
                    "${TeamEntry.COLUMN_CITY} TEXT, " +
                    "${TeamEntry.COLUMN_NAME} TEXT, " +
                    "${TeamEntry.COLUMN_TRICODE} TEXT, " +
                    "${TeamEntry.COLUMN_CONFERENCE} TEXT, " +
                    "${TeamEntry.COLUMN_DIVISION} TEXT)"

    private val DROP_TABLE_TEAMS="DROP TABLE IF EXISTS ${TeamEntry.TABLE_NAME}"

    private val CLEAR_TABLE_TEAMS="DELETE FROM ${TeamEntry.TABLE_NAME}"

    object PlayerEntry:BaseColumns{
        const val TABLE_NAME="players"
        const val COLUMN_ID="id"
        const val COLUMN_TEAM_ID="team_id"
        const val COLUMN_FIRST_NAME="first_name"
        const val COLUMN_LAST_NAME="last_name"
        const val COLUMN_JERSEY="jersey"
        const val COLUMN_POSITION="position"
        const val COLUMN_HEIGHT="height"
        const val COLUMN_WEIGHT="weight"
        const val COLUMN_DATE_OF_BIRTH="date_of_birth"
        const val COLUMN_NBA_DEBUT="nba_debut"
        const val COLUMN_YEARS_PRO="years_pro"
        const val COLUMN_COLLEGE="college"
        const val COLUMN_COUNTRY="country"
    }

    private val CREATE_TABLE_PLAYERS=
            "CREATE TABLE ${PlayerEntry.TABLE_NAME} (" +
                    "${PlayerEntry.COLUMN_ID} TEXT, " +
                    "${PlayerEntry.COLUMN_TEAM_ID} TEXT, " +
                    "${PlayerEntry.COLUMN_FIRST_NAME} TEXT, " +
                    "${PlayerEntry.COLUMN_LAST_NAME} TEXT, " +
                    "${PlayerEntry.COLUMN_JERSEY} TEXT, " +
                    "${PlayerEntry.COLUMN_POSITION} TEXT, " +
                    "${PlayerEntry.COLUMN_HEIGHT} TEXT, " +
                    "${PlayerEntry.COLUMN_WEIGHT} TEXT, " +
                    "${PlayerEntry.COLUMN_DATE_OF_BIRTH} TEXT, " +
                    "${PlayerEntry.COLUMN_NBA_DEBUT} TEXT, " +
                    "${PlayerEntry.COLUMN_YEARS_PRO} TEXT, " +
                    "${PlayerEntry.COLUMN_COLLEGE} TEXT, " +
                    "${PlayerEntry.COLUMN_COUNTRY} TEXT)"

    private val DROP_TABLE_PLAYERS="DROP TABLE IF EXISTS ${PlayerEntry.TABLE_NAME}"

    private val CLEAR_TABLE_PLAYERS="DELETE FROM ${PlayerEntry.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_TEAMS)
        db.execSQL(CREATE_TABLE_PLAYERS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_TABLE_TEAMS)
        db.execSQL(DROP_TABLE_PLAYERS)
        onCreate(db)
    }

    fun fillTeams(teams:ArrayList<Team>){
        val db = this.writableDatabase
        db.execSQL(CLEAR_TABLE_TEAMS)
        for(team in teams) {
            val values = ContentValues().apply {
                put(TeamEntry.COLUMN_ID, team.id)
                put(TeamEntry.COLUMN_CITY, team.city)
                put(TeamEntry.COLUMN_NAME, team.name)
                put(TeamEntry.COLUMN_TRICODE, team.tricode)
                put(TeamEntry.COLUMN_CONFERENCE, team.conference)
                put(TeamEntry.COLUMN_DIVISION, team.division)
            }
            db?.insert(TeamEntry.TABLE_NAME, null, values)
        }
    }

    fun fillPlayers(players:ArrayList<Player>){
        val db=this.writableDatabase
        db.execSQL(CLEAR_TABLE_PLAYERS)
        for(player in players){
            if(!player.isActive)
                continue
            val values = ContentValues().apply {
                put(PlayerEntry.COLUMN_ID, player.id)
                put(PlayerEntry.COLUMN_TEAM_ID, player.teamId)
                put(PlayerEntry.COLUMN_FIRST_NAME, player.firstName)
                put(PlayerEntry.COLUMN_LAST_NAME, player.lastName)
                put(PlayerEntry.COLUMN_JERSEY, player.jersey)
                put(PlayerEntry.COLUMN_POSITION, player.position)
                put(PlayerEntry.COLUMN_HEIGHT, player.height)
                put(PlayerEntry.COLUMN_WEIGHT, player.weight)
                put(PlayerEntry.COLUMN_DATE_OF_BIRTH, player.dateOfBirth)
                put(PlayerEntry.COLUMN_NBA_DEBUT, player.nbaDebut)
                put(PlayerEntry.COLUMN_YEARS_PRO, player.yearsPro)
                put(PlayerEntry.COLUMN_COLLEGE, player.college)
                put(PlayerEntry.COLUMN_COUNTRY, player.country)
            }
            db?.insert(PlayerEntry.TABLE_NAME, null, values)
        }
    }

    fun getTeamsByConference(conference: String):ArrayList<Team>{
        val db=this.readableDatabase
        val list=ArrayList<Team>()
        val query = "SELECT * FROM ${TeamEntry.TABLE_NAME} WHERE ${TeamEntry.COLUMN_CONFERENCE}='${conference}'"
        val result = db.rawQuery(query, null)
        if(result.moveToFirst()){
            do{
                val team = Team(
                        result.getString(result.getColumnIndex(TeamEntry.COLUMN_ID)),
                        result.getString(result.getColumnIndex(TeamEntry.COLUMN_CITY)),
                        result.getString(result.getColumnIndex(TeamEntry.COLUMN_NAME)),
                        result.getString(result.getColumnIndex(TeamEntry.COLUMN_TRICODE)),
                        result.getString(result.getColumnIndex(TeamEntry.COLUMN_CONFERENCE)),
                        result.getString(result.getColumnIndex(TeamEntry.COLUMN_DIVISION))
                )
                list.add(team)
            }while (result.moveToNext())
        }
        return list
    }
}