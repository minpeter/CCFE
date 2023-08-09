package com.lolhistory.retrofit

import com.lolhistory.datamodel.MatchHistory
import com.lolhistory.datamodel.SummonerIdInfo
import com.lolhistory.datamodel.SummonerRankInfo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RiotAPI {
    @Headers(
        "Content-Type: application/json;charset=utf-8",
        "Accept: application/json",
        "X-Riot-Token: " + BaseUrl.RIOT_API_KEY
    )
    @GET(BaseUrl.RIOT_API_GET_SUMMONER + "{userId}")
    fun getSummonerIdInfo(@Path("userId") userId: String): Single<SummonerIdInfo>

    @Headers(
        "Content-Type: application/json;charset=utf-8",
        "Accept: application/json",
        "X-Riot-Token: " + BaseUrl.RIOT_API_KEY
    )
    @GET(BaseUrl.RIOT_API_GET_RANK + "{userId}")
    fun getSummonerRankInfo(@Path("userId") userId: String): Single<List<SummonerRankInfo>>

    @Headers(
        "Content-Type: application/json;charset=utf-8",
        "Accept: application/json",
        "X-Riot-Token: " + BaseUrl.RIOT_API_KEY
    )
    @GET(BaseUrl.RIOT_API_GET_MATCH_LIST + "{puuid}/ids")
    fun getMatchIdList(
        @Path("puuid") puuid: String,
        @Query("start") start: Int,
        @Query("count") count: Int
    ): Single<List<String>>

    @Headers(
        "Content-Type: application/json;charset=utf-8",
        "Accept: application/json",
        "X-Riot-Token: " + BaseUrl.RIOT_API_KEY
    )
    @GET(BaseUrl.RIOT_API_GET_MATCH + "{matchId}")
    fun getMatchInfo(@Path("matchId") matchId: String): Single<MatchHistory>
}