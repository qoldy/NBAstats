package com.example.nbastats.networking

import com.example.nbastats.networking.responses.ResponseLeaguesP
import com.example.nbastats.networking.responses.ResponseLeaguesT
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {
    @GET("v1/2020/teams.json")
    fun getTeams(): Observable<ResponseLeaguesT>

    @GET("v1/2020/players.json")
    fun getPlayers():Observable<ResponseLeaguesP>
}