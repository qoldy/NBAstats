package com.example.nbastats.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PlayerStats(
        @Expose(deserialize = false)
        var season: String ="2020",
        @SerializedName("ppg")
        var ppg:String,
        @SerializedName("rpg")
        var rpg:String,
        @SerializedName("apg")
        var apg:String,
        @SerializedName("mpg")
        var mpg:String,
        @SerializedName("topg")
        var topg:String,
        @SerializedName("spg")
        var spg:String,
        @SerializedName("bpg")
        var bpg:String,
        @SerializedName("tpp")
        var tpp:String,
        @SerializedName("ftp")
        var ftp:String,
        @SerializedName("fgp")
        var fgp:String,
        @SerializedName("assists")
        var assists:String,
        @SerializedName("blocks")
        var blocks:String,
        @SerializedName("steals")
        var steals:String,
        @SerializedName("turnovers")
        var turnovers:String,
        @SerializedName("offReb")
        var offReb:String,
        @SerializedName("defReb")
        var defReb:String,
        @SerializedName("totReb")
        var rebounds:String,
        @SerializedName("fgm")
        var fgm:String,
        @SerializedName("fga")
        var fga:String,
        @SerializedName("tpm")
        var tpm:String,
        @SerializedName("tpa")
        var tpa:String,
        @SerializedName("ftm")
        var ftm:String,
        @SerializedName("fta")
        var fta:String,
        @SerializedName("pFouls")
        var fouls:String,
        @SerializedName("points")
        var points:String,
        @SerializedName("gamesPlayed")
        var gamesPlayed:String,
        @SerializedName("gamesStarted")
        var gamesStarted:String,
        @SerializedName("plusMinus")
        var plusMinus:String
)