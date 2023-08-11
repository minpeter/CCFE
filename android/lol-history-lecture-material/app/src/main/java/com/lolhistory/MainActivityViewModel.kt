package com.lolhistory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lolhistory.datamodel.MatchHistory
import com.lolhistory.datamodel.SummonerIdInfo
import com.lolhistory.datamodel.SummonerRankInfo
import com.lolhistory.repository.RiotRepository
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class MainActivityViewModel: ViewModel() {
    private val repo = RiotRepository

    private val _summonerIDInfoLiveData = MutableLiveData<SummonerIdInfo>()
    val summonerIDInfoLiveData: LiveData<SummonerIdInfo> get() = _summonerIDInfoLiveData

    private val _summonerRankInfoLiveData = MutableLiveData<SummonerRankInfo>()
    val summonerRankInfoLiveData: LiveData<SummonerRankInfo> get() = _summonerRankInfoLiveData

    private val _matchHistoryListLiveData = MutableLiveData<List<MatchHistory>>()
    val matchHistoryListLiveData: LiveData<List<MatchHistory>> get() = _matchHistoryListLiveData

    private val matchHistoryies: ArrayList<MatchHistory> = ArrayList()

    fun getSummonerIdInfo(name: String) {
        if (name.isEmpty()) _summonerIDInfoLiveData.value = null
        matchHistoryies.clear()
        repo.getSummonerIdInfo(name).subscribe(object :SingleObserver<SummonerIdInfo> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onSuccess(t: SummonerIdInfo) {
                _summonerIDInfoLiveData.value = t
                getSummonerRankInfo(t.id)

                getMatchList(t.puuid)
            }

            override fun onError(e: Throwable) {
                Log.d("TESTLOG", "[getSummonerIdInfo] exception: $e")
                _summonerIDInfoLiveData.value = null
            }
        })
    }



    private fun getSummonerRankInfo(summonerId: String) {
        repo.getSummonerRankInfo(summonerId).subscribe(object: SingleObserver<List<SummonerRankInfo>> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onSuccess(summonerRankInfos: List<SummonerRankInfo>) {
                Log.d("TESTLOG", "[getSummonerRankInfo] summonerRankInfos: $summonerRankInfos")
                setSummonerRankInfo(summonerRankInfos)
            }

            override fun onError(e: Throwable) {
                Log.d("TESTLOG", "[getSummonerRankInfo] exception: $e")
            }
        })
    }

    private fun getMatchList(puuid: String) {
        RiotRepository
            .getMatchHistoryList(puuid, 0, 20)
            .subscribe(object: SingleObserver<List<String>> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onSuccess(t: List<String>) {
                    for (match in t) {
                        getMatchHistory(match)
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d("TESTLOG", "[getMatchList] exception: $e")
                }
            })
    }

    private fun getMatchHistory(matchId: String) {
        RiotRepository
            .getMatchHistory(matchId)
            .subscribe(object : SingleObserver<MatchHistory>{
                override fun onSubscribe(d: Disposable) {
                }

                override fun onSuccess(t: MatchHistory) {
                    matchHistoryies.add(t)
                    if (matchHistoryies.size < 15) {
                        _matchHistoryListLiveData.value = matchHistoryies
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d("TESTLOG", "[getMatchHistory] exception: $e")
                }
            })
    }

    private fun setSummonerRankInfo(summonerRankInfoList: List<SummonerRankInfo>) {
        var soloRankInfo: SummonerRankInfo? = null
        var flexRankInfo: SummonerRankInfo? = null
        var soloRankTier = 0
        var flexRankTier = 0
        val summonerRankInfo: SummonerRankInfo
        if (summonerRankInfoList.isEmpty()) {
            // 언랭
            Log.d("TESTLOG", "언랭")
            summonerRankInfo = SummonerRankInfo(_summonerIDInfoLiveData.value!!.name, "", "UNRANKED", "", 0, 0, 0)

        } else if (summonerRankInfoList[0].queueType == "CHERRY" && summonerRankInfoList.size == 1) {
            // 아레나
            summonerRankInfo = SummonerRankInfo(_summonerIDInfoLiveData.value!!.name, "CHERRY", "UNRANKED", "", summonerRankInfoList[0].leaguePoints, summonerRankInfoList[0].wins, summonerRankInfoList[0].losses)
        } else {
            for (info in summonerRankInfoList) {
                if (info.queueType == "RANKED_SOLO_5x5") {
                    // 솔랭
                    soloRankInfo = info
                    soloRankTier = calcTier(info.tier, info.rank, info.leaguePoints)
                } else if (info.queueType == "RANKED_FLEX_SR") {
                    // 자랭
                    flexRankInfo = info
                    flexRankTier = calcTier(info.tier, info.rank, info.leaguePoints)
                }
            }
            summonerRankInfo = if (soloRankTier < flexRankTier) {
                // 자랭 티어가 더 높을 때
                flexRankInfo!!
            } else {
                // 솔랭 티어가 더 높거나 솔랭과 자랭의 티어가 같을 때
                soloRankInfo!!
            }
        }
        _summonerRankInfoLiveData.value = summonerRankInfo
    }

    private fun calcTier(tier: String, rank: String, lp: Int): Int {
        var tierNum = 0
        when (tier) {
            "IRON" -> { }
            "BRONZE" -> tierNum = 1000
            "SILVER" -> tierNum = 2000
            "GOLD" -> tierNum = 3000
            "PLATINUM" -> tierNum = 4000
            "EMERALD" -> tierNum = 5000
            "DIAMOND" -> tierNum = 6000
            "MASTER" -> tierNum = 7000
            "GRANDMASTER" -> tierNum = 8000
            "CHALLENGER" -> tierNum = 9000
        }
        when (rank) {
            "I" -> tierNum += 700
            "II" -> tierNum += 500
            "III" -> tierNum += 300
            "IV" -> tierNum += 100
        }
        tierNum += lp
        return tierNum
    }
}