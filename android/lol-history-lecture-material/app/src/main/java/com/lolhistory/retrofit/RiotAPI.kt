package com.lolhistory.retrofit

import com.lolhistory.datamodel.SummonerIdInfo
import com.lolhistory.datamodel.SummonerRankInfo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

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
}