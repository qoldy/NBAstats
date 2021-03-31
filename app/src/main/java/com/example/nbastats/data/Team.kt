package com.example.nbastats.data

import com.google.gson.annotations.SerializedName


data class Team(
    @SerializedName("teamId")
    val id: Long,
    @SerializedName("city")
    val city: String,
    @SerializedName("nickname")
    val name: String,
    @SerializedName("tricode")
    val tricode: String,
    @SerializedName("confName")
    val conference: String,
    @SerializedName("divName")
    val division: String,
    val year: Int = 2020
)