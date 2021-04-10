package com.example.nbastats.mvvm.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nbastats.R
import com.example.nbastats.data.PlayerStats
import com.example.nbastats.mvvm.viewmodels.PlayerStatsVM
import com.example.nbastats.utils.VMFactory

class PlayerStatsFragment:Fragment() {
    private lateinit var ppgText:TextView
    private lateinit var rpgText:TextView
    private lateinit var apgText:TextView
    private lateinit var mpgText:TextView
    private lateinit var spgText:TextView
    private lateinit var topgText:TextView
    private lateinit var bpgText:TextView
    private lateinit var fgpText:TextView
    private lateinit var tppText:TextView
    private lateinit var ftpText:TextView
    private lateinit var assistsText:TextView
    private lateinit var blocksText:TextView
    private lateinit var stealsText:TextView
    private lateinit var turnoversText:TextView
    private lateinit var offRebText:TextView
    private lateinit var defRebText:TextView
    private lateinit var rebText:TextView
    private lateinit var fgmText:TextView
    private lateinit var fgaText:TextView
    private lateinit var tpmText:TextView
    private lateinit var tpaText:TextView
    private lateinit var ftmText:TextView
    private lateinit var ftaText:TextView
    private lateinit var foulsText:TextView
    private lateinit var pointsText:TextView
    private lateinit var playedText:TextView
    private lateinit var startedText:TextView
    private lateinit var plusMinusText:TextView
    private lateinit var playerStatsVM: PlayerStatsVM
    private lateinit var playerId:String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_season_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(view)
        init()
    }

    private fun initViews(view: View){
        ppgText=view.findViewById(R.id.ppg)
        rpgText=view.findViewById(R.id.rpg)
        apgText=view.findViewById(R.id.apg)
        mpgText=view.findViewById(R.id.mpg)
        spgText=view.findViewById(R.id.spg)
        topgText=view.findViewById(R.id.topg)
        bpgText=view.findViewById(R.id.bpg)
        fgpText=view.findViewById(R.id.fgp)
        tppText=view.findViewById(R.id.tpp)
        ftpText=view.findViewById(R.id.ftp)
        assistsText=view.findViewById(R.id.assists)
        blocksText=view.findViewById(R.id.blocks)
        stealsText=view.findViewById(R.id.steals)
        turnoversText=view.findViewById(R.id.turnovers)
        offRebText=view.findViewById(R.id.off_reb)
        defRebText=view.findViewById(R.id.def_reb)
        rebText=view.findViewById(R.id.reb)
        fgmText=view.findViewById(R.id.fgm)
        fgaText=view.findViewById(R.id.fga)
        tpmText=view.findViewById(R.id.tpm)
        tpaText=view.findViewById(R.id.tpa)
        ftmText=view.findViewById(R.id.ftm)
        ftaText=view.findViewById(R.id.fta)
        foulsText=view.findViewById(R.id.fouls)
        pointsText=view.findViewById(R.id.points)
        playedText=view.findViewById(R.id.played)
        startedText=view.findViewById(R.id.started)
        plusMinusText=view.findViewById(R.id.plus_minus)
    }

    private fun init(){
        playerId= this.arguments?.getString("playerId").toString()
        val factory = VMFactory()
        val provider = ViewModelProvider(this, factory)
        playerStatsVM=provider.get(PlayerStatsVM::class.java)
        observeData()
        playerStatsVM.init(playerId)
    }
    private fun observeData(){
        playerStatsVM.liveData.observe(this,{
            initText(playerStatsVM.getLastStats())
        })
    }
    private fun initText(playerStats:PlayerStats){
        ppgText.text=playerStats.ppg
        rpgText.text=playerStats.rpg
        apgText.text=playerStats.apg
        mpgText.text=playerStats.mpg
        spgText.text=playerStats.spg
        topgText.text=playerStats.topg
        bpgText.text=playerStats.bpg
        (playerStats.fgp+"%").also { fgpText.text = it }
        (playerStats.tpp+"%").also { tppText.text = it }
        (playerStats.ftp+"%").also { ftpText.text = it }
        assistsText.text=playerStats.assists
        blocksText.text=playerStats.blocks
        stealsText.text=playerStats.steals
        turnoversText.text=playerStats.turnovers
        offRebText.text=playerStats.offReb
        defRebText.text=playerStats.defReb
        rebText.text=playerStats.rebounds
        fgmText.text=playerStats.fgm
        fgaText.text=playerStats.fga
        tpmText.text=playerStats.tpm
        tpaText.text=playerStats.tpa
        ftmText.text=playerStats.ftm
        ftaText.text=playerStats.fta
        foulsText.text=playerStats.fouls
        pointsText.text=playerStats.points
        playedText.text=playerStats.gamesPlayed
        startedText.text=playerStats.gamesStarted
        plusMinusText.text=playerStats.plusMinus
    }
}