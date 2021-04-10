package com.example.nbastats.data

import com.google.gson.annotations.SerializedName

data class Game(
        @SerializedName("gameId")
        var gameId:String,
        @SerializedName("hTeam")
        var homeTeam:GameTeam,
        @SerializedName("vTeam")
        var guestTeam:GameTeam
)

data class GameTeam(
        @SerializedName("teamId")
        var teamId:String,
        @SerializedName("triCode")
        var triCode:String,
        @SerializedName("score")
        var score:String
)