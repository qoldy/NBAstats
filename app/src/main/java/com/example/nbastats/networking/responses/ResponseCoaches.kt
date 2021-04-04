package com.example.nbastats.networking.responses

import com.example.nbastats.data.Coach
import com.google.gson.annotations.SerializedName

data class ResponseCoaches(
        @SerializedName("standard")
        val coaches:ArrayList<Coach>
)

data class ResponseLeaguesC(
        @SerializedName("league")
        val leagues:ResponseCoaches
)
