package com.lolhistory

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lolhistory.databinding.ActivityMainBinding
import com.lolhistory.datamodel.SummonerRankInfo
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    private var puuid: String = ""

    private var isVisibleLayoutInfo = false

    private val inputMetodManager by lazy {
        this@MainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etInputSummoner.setOnKeyListener { view, i, keyEvent ->
            when (i) {
                KeyEvent.KEYCODE_ENTER -> binding.btnInputSummoner.callOnClick()
            }
            return@setOnKeyListener false
        }


        binding.btnInputSummoner.setOnClickListener {
            binding.pbLoading.visibility = View.VISIBLE
            viewModel.getSummonerIdInfo(binding.etInputSummoner.text.toString().trim())
            binding.etInputSummoner.setText("")
            inputMetodManager.hideSoftInputFromWindow(binding.etInputSummoner.windowToken, 0)
        }

        binding.rvHistory.layoutManager = LinearLayoutManager(this)
        binding.rvHistory.setHasFixedSize(true)

        binding.layoutSwipe.setOnRefreshListener {
            viewModel.getSummonerIdInfo(binding.tvSummonerName.text.toString().trim())
        }

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.summonerIDInfoLiveData.observe(this) {
            if (it == null) {
                Toast.makeText(
                    applicationContext,
                    R.string.not_exist_summoner,
                    Toast.LENGTH_SHORT
                ).show()
                binding.pbLoading.visibility = View.GONE
            } else {
                puuid = it.puuid
            }
        }



        viewModel.summonerRankInfoLiveData.observe(this) {
            if (it != null) {
                setRankInfo(it)
            } else {
                binding.pbLoading.visibility= View.GONE

            }
        }


        viewModel.matchHistoryListLiveData.observe(this) {
            if (it.isEmpty()) {
            } else {
                val historyAdapter = HistoryAdapter(ArrayList(it), puuid)
                binding.rvHistory.adapter = historyAdapter
                binding.layoutSwipe.isRefreshing = false
                binding.layoutInput.visibility = View.GONE
            }
            binding.pbLoading.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
        if (isVisibleLayoutInfo) {
            binding.layoutInfo.visibility = View.GONE
            binding.layoutInput.visibility = View.VISIBLE
            isVisibleLayoutInfo = !isVisibleLayoutInfo
        }
        else {
            finish()
        }
    }

    private fun setRankInfo(summonerRankInfo: SummonerRankInfo) {
        binding.tvSummonerName.text = summonerRankInfo.summonerName
        val tierRank = summonerRankInfo.tier + " " + summonerRankInfo.rank
        binding.tvTier.text = tierRank

        if (summonerRankInfo.tier == "UNRANKED") {
            binding.tvLp.text = ""
            binding.tvRankType.text = ""
            binding.tvTotalWinLose.text = ""
            binding.tvTotalWinRate.text = ""
        } else {
            binding.tvRankType.text = summonerRankInfo.queueType
            val point = summonerRankInfo.leaguePoints.toString() + " LP"
            binding.tvLp.text = point
            val rate = summonerRankInfo.wins / (summonerRankInfo.wins + summonerRankInfo.losses).toFloat() * 100
            val winlose = summonerRankInfo.wins.toString() + " " + getString(R.string.win) + " / " + summonerRankInfo.losses + " " + getString(R.string.defeat)
            binding.tvTotalWinLose.text = winlose
            binding.tvTotalWinRate.text = String.format(Locale.getDefault(), "%.2f", rate)
        }
        setTierEmblem(summonerRankInfo.tier)

        binding.layoutInfo.visibility = View.VISIBLE
        binding.layoutInput.visibility = View.GONE
        isVisibleLayoutInfo = true
    }

    private fun setTierEmblem(tier: String) {
        when (tier) {
            "UNRANKED" -> binding.ivTierEmblem.setImageResource(R.drawable.emblem_unranked)
            "IRON" -> binding.ivTierEmblem.setImageResource(R.drawable.emblem_iron)
            "BRONZE" -> binding.ivTierEmblem.setImageResource(R.drawable.emblem_bronze)
            "SILVER" -> binding.ivTierEmblem.setImageResource(R.drawable.emblem_silver)
            "GOLD" ->  binding.ivTierEmblem.setImageResource(R.drawable.emblem_gold)
            "PLATINUM" -> binding.ivTierEmblem.setImageResource(R.drawable.emblem_platinum)
            "EMERALD" -> binding.ivTierEmblem.setImageResource(R.drawable.emblem_emerald)
            "DIAMOND" -> binding.ivTierEmblem.setImageResource(R.drawable.emblem_diamond)
            "MASTER" -> binding.ivTierEmblem.setImageResource(R.drawable.emblem_master)
            "GRANDMASTER" -> binding.ivTierEmblem.setImageResource(R.drawable.emblem_grandmaster)
            "CHALLENGER" -> binding.ivTierEmblem.setImageResource(R.drawable.emblem_challenger)
        }
    }
}