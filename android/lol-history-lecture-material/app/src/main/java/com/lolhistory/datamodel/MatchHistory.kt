package com.lolhistory.datamodel

import com.google.gson.annotations.SerializedName

data class MatchHistory (
    @SerializedName("info")
    var info: Info,
) {
    data class Info (
        @SerializedName("gameCreation")
        var gameCreation: Long,

        @SerializedName("gameDuration")
        var gameDuration: Long,

        @SerializedName("gameVersion")
        var gameVersion: String,

        @SerializedName("queueId")
        var queueId: Int,

        @SerializedName("participants")
        var participants: List<Participants>,
    ) {
        data class Participants (
            @SerializedName("assists")
            var assists: Int,

            @SerializedName("championLevel")
            var championLevel: Int,

            @SerializedName("championId")
            var championId: Int,

            @SerializedName("championName")
            var championName: String,

            @SerializedName("deaths")
            var deaths: Int,

            @SerializedName("item0")
            var item0: Int,

            @SerializedName("item1")
            var item1: Int,

            @SerializedName("item2")
            var item2: Int,

            @SerializedName("item3")
            var item3: Int,

            @SerializedName("item4")
            var item4: Int,

            @SerializedName("item5")
            var item5: Int,

            @SerializedName("item6")
            var item6: Int,

            @SerializedName("kills")
            var kills: Int,

            @SerializedName("neutralMinionsKilled")
            var neutralMinionsKilled: Int,

            @SerializedName("puuid")
            var puuid: String,

            @SerializedName("summoner1Id")
            var summoner1Id: Int,

            @SerializedName("summoner2Id")
            var summoner2Id: Int,

            @SerializedName("summonerName")
            var summonerName: String,

            @SerializedName("summonerLevel")
            var summonerLevel: Int,

            @SerializedName("totalMinionsKilled")
            var totalMinionsKilled: Int,

            @SerializedName("win")
            var win: Boolean,

            @SerializedName("perks")
            var perks: Perks,
        ) {
            data class Perks (
                @SerializedName("styles")
                var styles: List<Styles>,
            ) {
                data class Styles (
                    @SerializedName("selections")
                    var selections: List<Selections>,
                ) {
                    data class Selections (
                        @SerializedName("perk")
                        var perk: Int,

                        @SerializedName("var1")
                        var var1: Int,

                        @SerializedName("var2")
                        var var2: Int,

                        @SerializedName("var3")
                        var var3: Int,
                    )
                }
            }
        }


    }
}