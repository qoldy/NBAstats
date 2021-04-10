package com.example.nbastats.networking

import com.example.nbastats.data.PlayerStats
import com.example.nbastats.networking.responses.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("v1/2020/teams.json")
    fun getTeams(): Observable<ResponseLeaguesT>

    @GET("v1/2020/players.json")
    fun getPlayers():Observable<ResponseLeaguesP>

    @GET("v1/current/standings_conference.json")
    fun getStandings():Observable<ResponseLeaguesS>

    @GET("v1/2020/coaches.json")
    fun getCoaches():Observable<ResponseLeaguesC>

    @GET("v1/2020/team_stats_rankings.json")
    fun getStats():Observable<ResponseLeaguesTS>

    @GET("v1/2020/schedule.json")
    fun getSchedule():Observable<ResponseLeaguesSc>

    @GET("v1/2020/players/{player_id}_profile.json")
    fun getPlayerStats(@Path("player_id") playerId:String):Observable<ResponseLeaguesPS>

    @GET("v2/{game_date}/scoreboard.json")
    fun getGamesByDate(@Path("game_date")gameDate:String):Observable<ResponseGames>
}