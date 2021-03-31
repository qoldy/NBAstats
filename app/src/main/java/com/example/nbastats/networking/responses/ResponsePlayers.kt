package com.example.nbastats.networking.responses

import com.example.nbastats.data.Player
import com.google.gson.annotations.SerializedName

data class ResponsePlayers(
        @SerializedName("standard")
        val players:ArrayList<Player>
        )
data class ResponseLeaguesP(
        @SerializedName("league")
        val leagues: ResponsePlayers
)

