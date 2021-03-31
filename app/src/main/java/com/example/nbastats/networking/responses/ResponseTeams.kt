package com.example.nbastats.networking.responses

import com.example.nbastats.data.Team
import com.google.gson.annotations.SerializedName

data class ResponseTeams(
    @SerializedName("standard")
    val teams:ArrayList<Team>
)
data class ResponseLeaguesT(
        @SerializedName("league")
        val leagues: ResponseTeams
)
