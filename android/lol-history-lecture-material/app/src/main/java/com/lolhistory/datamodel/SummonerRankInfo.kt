package com.lolhistory.datamodel

import com.google.gson.annotations.SerializedName

data class SummonerRankInfo (
    @SerializedName("summonerName")
    var summonerName: String,

    @SerializedName("queueType")
    var queueType: String,

    @SerializedName("tier")
    var tier: String,

    @SerializedName("rank")
    var rank: String,

    @SerializedName("leaguePoints")
    var leaguePoints: Int,

    @SerializedName("wins")
    var wins: Int,

    @SerializedName("losses")
    var losses: Int,
)