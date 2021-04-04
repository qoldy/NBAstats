package com.example.nbastats.data

import com.google.gson.annotations.SerializedName

data class Coach (
        @SerializedName("personId")
        var id:String,
        @SerializedName("teamId")
        var teamId:String,
        @SerializedName("firstName")
        var firstName:String,
        @SerializedName("lastName")
        var lastName:String,
        @SerializedName("isAssistant")
        var isAssistant: Boolean =false
        )