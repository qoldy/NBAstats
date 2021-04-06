package com.example.nbastats.networking.responses

import com.example.nbastats.data.TeamStats
import com.google.gson.annotations.SerializedName

data class ResponseTeamStats (
        @SerializedName("teams")
        var stats:ArrayList<TeamStats>
        )
data class ResponseRegTeams(
        @SerializedName("regularSeason")
        var teams:ResponseTeamStats
)
data class ResponseStandard(
        @SerializedName("standard")
        var response:ResponseRegTeams
)
data class ResponseLeaguesTS(
        @SerializedName("league")
        var leagues:ResponseStandard
)