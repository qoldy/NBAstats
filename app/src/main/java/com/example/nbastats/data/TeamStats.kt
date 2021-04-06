package com.example.nbastats.data

import com.google.gson.annotations.SerializedName

data class TeamStats(
        @SerializedName("teamId")
        var teamId:String,
        @SerializedName("fgp")
        var fgp:FGP,
        @SerializedName("tpp")
        var tpp:TPP,
        @SerializedName("ftp")
        var ftp:FTP,
        @SerializedName("orpg")
        var orpg: ORPG,
        @SerializedName("drpg")
        var drpg: DRPG,
        @SerializedName("trpg")
        var trpg: TRPG,
        @SerializedName("apg")
        var apg:APG,
        @SerializedName("tpg")
        var tpg: TPG,
        @SerializedName("spg")
        var spg: SPG,
        @SerializedName("bpg")
        var bpg: BPG,
        @SerializedName("pfpg")
        var pfpg: PFPG,
        @SerializedName("ppg")
        var ppg: PPG,
        @SerializedName("oppg")
        var oppg: OPPG
)

data class FGP(
        @SerializedName("avg")
        var avg:String,
        @SerializedName("rank")
        var rank:String
        )

data class TPP(
        @SerializedName("avg")
        var avg:String,
        @SerializedName("rank")
        var rank:String
        )

data class FTP(
        @SerializedName("avg")
        var avg:String,
        @SerializedName("rank")
        var rank:String
)

data class ORPG(
        @SerializedName("avg")
        var avg:String,
        @SerializedName("rank")
        var rank:String
        )

data class DRPG(
        @SerializedName("avg")
        var avg:String,
        @SerializedName("rank")
        var rank:String
)

data class TRPG(
        @SerializedName("avg")
        var avg:String,
        @SerializedName("rank")
        var rank:String
)

data class APG(
        @SerializedName("avg")
        var avg:String,
        @SerializedName("rank")
        var rank:String
)

data class TPG(
        @SerializedName("avg")
        var avg:String,
        @SerializedName("rank")
        var rank:String
)

data class SPG(
        @SerializedName("avg")
        var avg:String,
        @SerializedName("rank")
        var rank:String
)

data class BPG(
        @SerializedName("avg")
        var avg:String,
        @SerializedName("rank")
        var rank:String
)

data class PFPG(
        @SerializedName("avg")
        var avg:String,
        @SerializedName("rank")
        var rank:String
)

data class PPG(
        @SerializedName("avg")
        var avg:String,
        @SerializedName("rank")
        var rank:String
)

data class OPPG(
        @SerializedName("avg")
        var avg:String,
        @SerializedName("rank")
        var rank:String
)