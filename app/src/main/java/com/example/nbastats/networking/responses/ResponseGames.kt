package com.example.nbastats.networking.responses

import com.example.nbastats.data.Game
import com.google.gson.annotations.SerializedName

data class ResponseGames(
        @SerializedName("games")
        var games:ArrayList<Game>
)
