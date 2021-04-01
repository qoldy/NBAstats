package com.example.nbastats.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class Player (
        @SerializedName("personId")
        val id:String,
        @SerializedName("teamId")
        val teamId:String="",
        @SerializedName("firstName")
        val firstName:String,
        @SerializedName("lastName")
        val lastName:String,
        @SerializedName("jersey")
        val jersey:String,
        @SerializedName("isActive")
        val isActive:Boolean=true,
        @SerializedName("pos")
        val position:String,
        @SerializedName("heightMeters")
        val height:String="",
        @SerializedName("weightKilograms")
        val weight:String="",
        @SerializedName("dateOfBirthUTC")
        val dateOfBirth:String,
        @SerializedName("nbaDebutYear")
        val nbaDebut: String,
        @SerializedName("yearsPro")
        val yearsPro:String,
        @SerializedName("collegeName")
        val college:String,
        @SerializedName("country")
        val country:String
        )