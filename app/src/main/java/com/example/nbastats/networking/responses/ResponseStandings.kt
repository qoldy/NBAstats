package com.example.nbastats.networking.responses

import com.example.nbastats.data.ConfStandings
import com.example.nbastats.data.Conferences
import com.google.gson.annotations.SerializedName

data class ResponseStandings(
        @SerializedName("standard")
        val conferences: Conferences
)


data class ResponseLeaguesS(
        @SerializedName("league")
        val leagues: ResponseStandings
        )

