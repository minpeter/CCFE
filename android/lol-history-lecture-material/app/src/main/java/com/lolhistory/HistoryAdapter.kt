package com.lolhistory

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lolhistory.databinding.ItemMatchHistoryBinding
import com.lolhistory.datamodel.MatchHistory
import com.lolhistory.parser.QueueParser
import com.lolhistory.parser.RuneParser
import com.lolhistory.parser.SpellParser
import com.lolhistory.retrofit.BaseUrl

class HistoryAdapter(
    private var matchHistories: ArrayList<MatchHistory>,
    private val puuid: String
):RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(
        private val binding: ItemMatchHistoryBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(matchHistory: MatchHistory) {
            val playerIndex = getPlayerIndex(matchHistory)
            if (matchHistory.info.participants[playerIndex].win) {
                binding.tvResult.text = context.getString(R.string.win)
                binding.layoutResult.setBackgroundColor(context.getColor(R.color.colorWin))
            } else {
                binding.tvResult.text = context.getString(R.string.defeat)
                binding.layoutResult.setBackgroundColor(context.getColor(R.color.colorDefeat))
            }
            binding.tvDurationTime.text = getDurationTime(matchHistory.info.gameDuration)
            binding.tvGameType.text = getQueueType(matchHistory.info.queueId)

            Glide.with(context)
                .load(getChampionPortraitUrl(matchHistory.info.participants[playerIndex]))
                .into(binding.ivChampionPortrait)

            binding.tvKda.text = getKda(matchHistory.info.participants[playerIndex])

            Glide.with(context)
                .load(getSpellImageUrl(matchHistory.info.participants[playerIndex], 1))
                .into(binding.ivSummonerSpell1)
            Glide.with(context)
                .load(getSpellImageUrl(matchHistory.info.participants[playerIndex], 2))
                .into(binding.ivSummonerSpell2)

            Glide.with(context)
                .load(getRuneImageUrl(matchHistory.info.participants[playerIndex], 1))
                .into(binding.ivKeystoneRune)
            Glide.with(context)
                .load(getRuneImageUrl(matchHistory.info.participants[playerIndex], 2))
                .into(binding.ivSecondaryRune)

            val itemArray = arrayOf(
                binding.ivItem0,
                binding.ivItem1,
                binding.ivItem2,
                binding.ivItem3,
                binding.ivItem4,
                binding.ivItem5,
                binding.ivItem6,
            )

            for (i in itemArray.indices) {
                val itemUrl = getItemImageUrl(matchHistory.info.participants[playerIndex], i)
                if (itemUrl != "") {
                    Glide.with(context)
                        .load(itemUrl)
                        .into(itemArray[i])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        matchHistories.sortByDescending { it.info.gameCreation }
        val binding = ItemMatchHistoryBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val matchHistory = matchHistories[position]
        holder.bind(matchHistory)
    }

    override fun getItemCount(): Int {
        return matchHistories.size
    }

    private fun getPlayerIndex(matchHistory: MatchHistory): Int {
        var index = 0
        for (i in matchHistory.info.participants.indices) {
            if (puuid == matchHistory.info.participants[i].puuid) {
                index = i
                break
            }
        }
        return index
    }

    private fun getDurationTime(secoundTime: Long): String {
        val hour = secoundTime / 3600
        val minute = (secoundTime % 3600) / 60
        val second = secoundTime % 60

        return if (hour == 0L) {
            String.format("%02d:%02d", minute, second)
        } else {
            String.format("%02d:%02d:%02d", hour, minute, second)
        }
    }

    private fun getQueueType(queueId: Int): String {
        val parser = QueueParser(context)
        return parser.getQueueType(queueId)
    }

    private fun getChampionPortraitUrl(participants: MatchHistory.Info.Participants): String {
        val championName = participants.championName
        return BaseUrl.RIOT_DATA_DRAGON_GET_CHAMPION_PORTRAIT + "$championName.png"
    }

    private fun getKda(participants: MatchHistory.Info.Participants): String {
        val kill = participants.kills
        val death = participants.deaths
        val assist = participants.assists

        return "$kill / $death / $assist"
    }

    private fun getSpellImageUrl(participants: MatchHistory.Info.Participants, spellIndex: Int): String {
        var spellId = 0
        when (spellIndex) {
            1 -> spellId = participants.summoner1Id
            2 -> spellId = participants.summoner2Id
        }

        val parser = SpellParser(context)
        val spellName = parser.getSpellName(spellId)

        return BaseUrl.RIOT_DATA_DRAGON_GET_SPELL_IMAGE + "$spellName.png"
    }

    private fun getRuneImageUrl(participants: MatchHistory.Info.Participants, runeIndex: Int): String {
        var runeId = 0

        for (style in participants.perks.styles) {
            when (runeIndex) {
                1 -> if (style.description == "primaryStyle") runeId = style.selections[0].perk
                2 -> if (style.description == "subStyle") runeId = style.style
            }
        }
        val parser = RuneParser(context)
        val runeicon = parser.getRuneIcon(runeId)

        return BaseUrl.RIOT_DATA_DRAGON_GET_RUNE_IMAGE + runeicon
    }

    private fun getItemImageUrl(participants: MatchHistory.Info.Participants, itemIndex: Int): String {
        var itemId = 0
        when (itemIndex) {
            0 -> itemId = participants.item0
            1 -> itemId = participants.item1
            2 -> itemId = participants.item2
            3 -> itemId = participants.item3
            4 -> itemId = participants.item4
            5 -> itemId = participants.item5
            6 -> itemId = participants.item6
        }
        return if (itemId == 0) {
            ""
        } else {
            BaseUrl.RIOT_DATA_DRAGON_GET_ITEM_IMAGE + "$itemId.png"
        }
    }
}