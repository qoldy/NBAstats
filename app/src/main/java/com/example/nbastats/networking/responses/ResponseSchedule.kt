package com.example.nbastats.networking.responses


import com.example.nbastats.data.Schedule
import com.google.gson.annotations.SerializedName

data class ResponseSchedule(
        @SerializedName("standard")
        val schedule:ArrayList<Schedule>
)


data class ResponseLeaguesSc(
        @SerializedName("league")
        val leagues: ResponseSchedule
)