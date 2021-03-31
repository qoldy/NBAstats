package com.example.nbastats.data

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class Player (
        @SerializedName("personId")
        val id:Long,
        @SerializedName("teamId")
        val teamId:Long,
        @SerializedName("firstName")
        val firstName:String,
        @SerializedName("lastName")
        val secondName:String,
        @SerializedName("jersey")
        val jersey:Int,
        @SerializedName("isActive")
        val isActive:Boolean,
        @SerializedName("pos")
        val position:String,
        @SerializedName("heightMeters")
        val height:Double,
        @SerializedName("weightKilograms")
        val weight:Double,
        @SerializedName("dateOfBirthUTC")
        val dateOfBirth:String,
        @SerializedName("nbaDebutYear")
        val nbaDebut: Int,
        @SerializedName("yearsPro")
        val yearsPro:Int,
        @SerializedName("collegeName")
        val college:String,
        @SerializedName("country")
        val country:String
        )