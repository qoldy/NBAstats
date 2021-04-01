package com.example.nbastats.data

import com.google.gson.annotations.SerializedName

data class Standings(
        @SerializedName("teamId")
        val teamId:String,
        @SerializedName("win")
        val wins:Int,
        @SerializedName("loss")
        val loss:Int,
        @SerializedName("gamesBehind")
        val gamesBehind:Double
)

data class ConfStandings(
        @SerializedName("east")
        val east:ArrayList<Standings>,
        @SerializedName("west")
        val west:ArrayList<Standings>
)
data class Conferences(
        @SerializedName("conference")
        val conference:ConfStandings
)