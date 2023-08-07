package com.lolhistory.datamodel

import com.google.gson.annotations.SerializedName

data class SummonerIdInfo (
    @SerializedName("id")
    var id: String,

    @SerializedName("accountId")
    var accountId: String,

    @SerializedName("puuid")
    var puuid: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("profileIconId")
    var profileIconId: Int,

    @SerializedName("revisionDate")
    var revisionDate: Long,

    @SerializedName("summonerLevel")
    var summonerLevel: Long
)