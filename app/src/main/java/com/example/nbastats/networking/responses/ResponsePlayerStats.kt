package com.example.nbastats.networking.responses

import com.example.nbastats.data.PlayerStats
import com.google.gson.annotations.SerializedName

data class ResponsePlayerStats (
        @SerializedName("seasonYear")
        var seasonYear:String,
        @SerializedName("total")
        var stats:PlayerStats
        )

data class ResponseSeason(
        @SerializedName("season")
        var seasons:ArrayList<ResponsePlayerStats>
)

data class ResponseStatsType(
        @SerializedName("regularSeason")
        var regular:ResponseSeason
)

data class ResponseStats(
        @SerializedName("stats")
        var stats:ResponseStatsType
)

data class ResponseStandardPS(
        @SerializedName("standard")
        var standard:ResponseStats
)

data class ResponseLeaguesPS (
        @SerializedName("league")
        var league:ResponseStandardPS
        )