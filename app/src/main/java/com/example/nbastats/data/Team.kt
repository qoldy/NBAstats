package com.example.nbastats.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Team(
        @SerializedName("teamId")
        val id: String,
        @SerializedName("city")
        val city: String,
        @SerializedName("nickname")
        val name: String,
        @SerializedName("tricode")
        val tricode: String,
        @SerializedName("confName")
        val conference: String,
        @SerializedName("divName")
        val division: String
)