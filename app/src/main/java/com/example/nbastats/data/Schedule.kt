package com.example.nbastats.data

import com.google.gson.annotations.SerializedName

data class Schedule(
        @SerializedName("gameId")
        var id:String,
        @SerializedName("startDateEastern")
        var date:String,
        @SerializedName("startTimeEastern")
        var time:String,
        @SerializedName("hTeam")
        var home:ScheduleTeam,
        @SerializedName("vTeam")
        var guest:ScheduleTeam
)

data class ScheduleTeam(
        @SerializedName("teamId")
        var id:String,
)
