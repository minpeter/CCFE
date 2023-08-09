package com.lolhistory.retrofit

class BaseUrl {
    companion object {
        const val RIOT_API_BASE_URL = "https://kr.api.riotgames.com/"
        const val RIOT_API_V5_BASE_URL = "https://asia.api.riotgames.com/"
        const val RIOT_API_KEY = "RGAPI-99aa1d3b-1992-4243-8c98-9c27048e3e67"

        const val RIOT_API_GET_SUMMONER_BY_ENCRYPTION_SUMMONER_ID = "lol/summoner/v4/summoners/"
        const val RIOT_API_GET_SUMMONER = "lol/summoner/v4/summoners/by-name/"
        const val RIOT_API_GET_RANK = "lol/league/v4/entries/by-summoner/"
        const val RIOT_API_GET_MATCH_LIST = "lol/match/v5/matches/by-puuid/"
        const val RIOT_API_GET_MATCH = "lol/match/v5/matches/"

        const val RIOT_DATA_DRAGON_GET_CHAMPION_PORTRAIT = "https://ddragon.leagueoflegends.com/cdn/13.15.1/img/champion/"
        const val RIOT_DATA_DRAGON_GET_ITEM_IMAGE = "https://ddragon.leagueoflegends.com/cdn/13.15.1/img/item/"
        const val RIOT_DATA_DRAGON_GET_SPELL_IMAGE = "https://ddragon.leagueoflegends.com/cdn/13.15.1/img/spell/"
        const val RIOT_DATA_DRAGON_GET_RUNE_IMAGE = "https://ddragon.leagueoflegends.com/cdn/img/"
    }
}