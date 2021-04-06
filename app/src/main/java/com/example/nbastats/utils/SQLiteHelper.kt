package com.example.nbastats.utils

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log
import com.example.nbastats.data.*
import java.time.LocalDate

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

    object CoachEntry:BaseColumns{
        const val TABLE_NAME="coaches"
        const val COLUMN_ID="id"
        const val COLUMN_TEAM_ID="team_id"
        const val COLUMN_FIRST_NAME="first_name"
        const val COLUMN_LAST_NAME="last_name"
    }

    private val CREATE_TABLE_COACHES=
            "CREATE TABLE ${CoachEntry.TABLE_NAME} (" +
                    "${CoachEntry.COLUMN_ID} TEXT," +
                    "${CoachEntry.COLUMN_TEAM_ID} TEXT," +
                    "${CoachEntry.COLUMN_FIRST_NAME} TEXT," +
                    "${CoachEntry.COLUMN_LAST_NAME} TEXT)"

    private val DROP_TABLE_COACHES="DROP TABLE IF EXISTS ${CoachEntry.TABLE_NAME}"

    private val CLEAR_TABLE_COACHES="DELETE FROM ${CoachEntry.TABLE_NAME}"

    object ScheduleEntry:BaseColumns{
        const val TABLE_NAME="schedule"
        const val COLUMN_ID="id"
        const val COLUMN_DATE="date"
        const val COLUMN_TIME="time"
        const val COLUMN_HOME="home_id"
        const val COLUMN_GUEST="guest_id"
    }

    private val CREATE_TABLE_SCHEDULE=
            "CREATE TABLE ${ScheduleEntry.TABLE_NAME} (" +
                    "${ScheduleEntry.COLUMN_ID} TEXT," +
                    "${ScheduleEntry.COLUMN_DATE} TEXT," +
                    "${ScheduleEntry.COLUMN_TIME} TEXT," +
                    "${ScheduleEntry.COLUMN_HOME} TEXT," +
                    "${ScheduleEntry.COLUMN_GUEST} TEXT)"
    private val DROP_TABLE_SCHEDULE="DROP TABLE IF EXISTS ${ScheduleEntry.TABLE_NAME}"
    private val CLEAR_TABLE_SCHEDULE="DELETE FROM ${ScheduleEntry.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_TEAMS)
        db.execSQL(CREATE_TABLE_PLAYERS)
        db.execSQL(CREATE_TABLE_COACHES)
        db.execSQL(CREATE_TABLE_SCHEDULE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_TABLE_TEAMS)
        db.execSQL(DROP_TABLE_PLAYERS)
        db.execSQL(DROP_TABLE_COACHES)
        db.execSQL(DROP_TABLE_SCHEDULE)
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

    fun fillCoaches(coaches:ArrayList<Coach>){
        val db=this.writableDatabase
        db.execSQL(CLEAR_TABLE_COACHES)
        for(coach in coaches){
            if(coach.isAssistant)
                continue
            val values = ContentValues().apply {
                put(CoachEntry.COLUMN_ID, coach.id)
                put(CoachEntry.COLUMN_TEAM_ID, coach.teamId)
                put(CoachEntry.COLUMN_FIRST_NAME, coach.firstName)
                put(CoachEntry.COLUMN_LAST_NAME, coach.lastName)
            }
            db?.insert(CoachEntry.TABLE_NAME, null, values)
        }
    }

    fun fillSchedule(schedule:ArrayList<Schedule>){
        val db=this.writableDatabase
        db.execSQL(CLEAR_TABLE_SCHEDULE)
        for(game in schedule){
            if(game.date=="")
                continue
            val values = ContentValues().apply {
                put(ScheduleEntry.COLUMN_ID, game.id)
                put(ScheduleEntry.COLUMN_DATE, game.date)
                put(ScheduleEntry.COLUMN_TIME, game.time)
                put(ScheduleEntry.COLUMN_HOME, game.home.id)
                put(ScheduleEntry.COLUMN_GUEST, game.guest.id)
            }
            db?.insert(ScheduleEntry.TABLE_NAME, null, values)
        }
    }

    fun getAllTeams():ArrayList<Team>{
        val db=this.readableDatabase
        val list=ArrayList<Team>()
        val query = "SELECT * FROM ${TeamEntry.TABLE_NAME}"
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

    fun getRoster(teamId:String):ArrayList<Player>{
        val db=this.readableDatabase
        val list=ArrayList<Player>()
        val query = "SELECT * FROM ${PlayerEntry.TABLE_NAME} WHERE ${PlayerEntry.COLUMN_TEAM_ID}='${teamId}'"
        val result = db.rawQuery(query, null)
        if(result.moveToFirst()){
            do{
                val player=Player(
                        result.getString(result.getColumnIndex(PlayerEntry.COLUMN_ID)),
                        result.getString(result.getColumnIndex(PlayerEntry.COLUMN_TEAM_ID)),
                        result.getString(result.getColumnIndex(PlayerEntry.COLUMN_FIRST_NAME)),
                        result.getString(result.getColumnIndex(PlayerEntry.COLUMN_LAST_NAME)),
                        result.getString(result.getColumnIndex(PlayerEntry.COLUMN_JERSEY)),
                        true,
                        result.getString(result.getColumnIndex(PlayerEntry.COLUMN_POSITION)),
                        result.getString(result.getColumnIndex(PlayerEntry.COLUMN_HEIGHT)),
                        result.getString(result.getColumnIndex(PlayerEntry.COLUMN_WEIGHT)),
                        result.getString(result.getColumnIndex(PlayerEntry.COLUMN_DATE_OF_BIRTH)),
                        result.getString(result.getColumnIndex(PlayerEntry.COLUMN_NBA_DEBUT)),
                        result.getString(result.getColumnIndex(PlayerEntry.COLUMN_YEARS_PRO)),
                        result.getString(result.getColumnIndex(PlayerEntry.COLUMN_COLLEGE)),
                        result.getString(result.getColumnIndex(PlayerEntry.COLUMN_COUNTRY)),
                )
                list.add(player)
            } while(result.moveToNext())
        }
        return list
    }

    fun getTeam(teamId: String):Team?{
        val db=this.readableDatabase
        val query = "SELECT * FROM ${TeamEntry.TABLE_NAME} WHERE ${TeamEntry.COLUMN_ID} = '${teamId}'"
        val result = db.rawQuery(query, null)
        if(result.moveToFirst())
            return Team(
                    result.getString(result.getColumnIndex(TeamEntry.COLUMN_ID)),
                    result.getString(result.getColumnIndex(TeamEntry.COLUMN_CITY)),
                    result.getString(result.getColumnIndex(TeamEntry.COLUMN_NAME)),
                    result.getString(result.getColumnIndex(TeamEntry.COLUMN_TRICODE)),
                    result.getString(result.getColumnIndex(TeamEntry.COLUMN_CONFERENCE)),
                    result.getString(result.getColumnIndex(TeamEntry.COLUMN_DIVISION))
            )
        return null
    }

    fun getCoach(teamId:String):Coach?{
        val db=this.readableDatabase
        val query = "SELECT * FROM ${CoachEntry.TABLE_NAME} WHERE ${CoachEntry.COLUMN_TEAM_ID} = '${teamId}'"
        val result = db.rawQuery(query, null)
        if(result.moveToFirst())
            return Coach(
                    result.getString(result.getColumnIndex(CoachEntry.COLUMN_ID)),
                    result.getString(result.getColumnIndex(CoachEntry.COLUMN_TEAM_ID)),
                    result.getString(result.getColumnIndex(CoachEntry.COLUMN_FIRST_NAME)),
                    result.getString(result.getColumnIndex(CoachEntry.COLUMN_LAST_NAME))
            )
        return null
    }

    fun getSchedule7Days(date:String):ArrayList<Schedule>{
        val list=ArrayList<Schedule>()
        val db=this.readableDatabase
        var localDate=LocalDate.of(date.substring(0,4).toInt(), date.substring(4,6).toInt(), date.substring(6,8).toInt())
        localDate=localDate.plusDays(7)
        var endDate=if(localDate.monthValue<10)
            "${localDate.year}0${localDate.monthValue}"
        else
            "${localDate.year}${localDate.monthValue}"
        endDate += if(localDate.dayOfMonth<10)
            "0${localDate.dayOfMonth}"
        else "${localDate.dayOfMonth}"
        val query= "SELECT * FROM ${ScheduleEntry.TABLE_NAME} WHERE ${ScheduleEntry.COLUMN_DATE}>='${date}' AND ${ScheduleEntry.COLUMN_DATE}<=${endDate}"
        val result=db.rawQuery(query, null)
        if(result.moveToFirst()){
            do {
                list.add(
                        Schedule(
                                result.getString(result.getColumnIndex(ScheduleEntry.COLUMN_ID)),
                                result.getString(result.getColumnIndex(ScheduleEntry.COLUMN_DATE)),
                                result.getString(result.getColumnIndex(ScheduleEntry.COLUMN_TIME)),
                                ScheduleTeam(result.getString(result.getColumnIndex(ScheduleEntry.COLUMN_HOME))),
                                ScheduleTeam(result.getString(result.getColumnIndex(ScheduleEntry.COLUMN_GUEST)))
                        )
                )
            }while (result.moveToNext())
        }
        Log.v("listSchedule",list.toString())
        return list
    }

    fun getPlayerById(playerId:String):Player{
        val db=this.readableDatabase
        val query = "SELECT * FROM ${PlayerEntry.TABLE_NAME} WHERE ${PlayerEntry.COLUMN_ID}='${playerId}'"
        val result = db.rawQuery(query, null)
        result.moveToFirst()
        return Player(
                    result.getString(result.getColumnIndex(PlayerEntry.COLUMN_ID)),
                    result.getString(result.getColumnIndex(PlayerEntry.COLUMN_TEAM_ID)),
                    result.getString(result.getColumnIndex(PlayerEntry.COLUMN_FIRST_NAME)),
                    result.getString(result.getColumnIndex(PlayerEntry.COLUMN_LAST_NAME)),
                    result.getString(result.getColumnIndex(PlayerEntry.COLUMN_JERSEY)),
                    true,
                    result.getString(result.getColumnIndex(PlayerEntry.COLUMN_POSITION)),
                    result.getString(result.getColumnIndex(PlayerEntry.COLUMN_HEIGHT)),
                    result.getString(result.getColumnIndex(PlayerEntry.COLUMN_WEIGHT)),
                    result.getString(result.getColumnIndex(PlayerEntry.COLUMN_DATE_OF_BIRTH)),
                    result.getString(result.getColumnIndex(PlayerEntry.COLUMN_NBA_DEBUT)),
                    result.getString(result.getColumnIndex(PlayerEntry.COLUMN_YEARS_PRO)),
                    result.getString(result.getColumnIndex(PlayerEntry.COLUMN_COLLEGE)),
                    result.getString(result.getColumnIndex(PlayerEntry.COLUMN_COUNTRY))
        )
    }
}