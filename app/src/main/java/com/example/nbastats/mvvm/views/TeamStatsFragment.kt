package com.example.nbastats.mvvm.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nbastats.R
import com.example.nbastats.data.TeamStats
import com.example.nbastats.mvvm.viewmodels.TeamStatsVM
import com.example.nbastats.utils.VMFactory

class TeamStatsFragment:Fragment() {
    private lateinit var teamStatsVM: TeamStatsVM
    private lateinit var fgpText:TextView
    private lateinit var tppText:TextView
    private lateinit var ftpText:TextView
    private lateinit var orpgText:TextView
    private lateinit var drpgText:TextView
    private lateinit var trpgText:TextView
    private lateinit var tpgText:TextView
    private lateinit var spgText:TextView
    private lateinit var apgText:TextView
    private lateinit var bpgText:TextView
    private lateinit var pfpgText:TextView
    private lateinit var ppgText:TextView
    private lateinit var oppgText:TextView
    private lateinit var teamId:String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.team_stats_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init(view)
    }

    private fun init(view:View){
        teamId= this.arguments?.getString("teamId").toString()
        fgpText=view.findViewById(R.id.fgp)
        tppText=view.findViewById(R.id.tpp)
        ftpText=view.findViewById(R.id.ftp)
        orpgText=view.findViewById(R.id.orpg)
        drpgText=view.findViewById(R.id.drpg)
        trpgText=view.findViewById(R.id.trpg)
        tpgText=view.findViewById(R.id.tpg)
        spgText=view.findViewById(R.id.spg)
        apgText=view.findViewById(R.id.apg)
        bpgText=view.findViewById(R.id.bpg)
        pfpgText=view.findViewById(R.id.pfpg)
        ppgText=view.findViewById(R.id.ppg)
        oppgText=view.findViewById(R.id.oppg)

        val factory = VMFactory()
        val provider = ViewModelProvider(this, factory)
        teamStatsVM=provider.get(TeamStatsVM::class.java)
        observeData()
        teamStatsVM.init()
    }

    private fun observeData(){
        teamStatsVM.liveData.observe(this, Observer {
            setText(teamStatsVM.getTeamStats(teamId))
        })
    }
    private fun setText(stats:TeamStats){
        (stats.fgp.avg+"% / NBA Rank:"+stats.fgp.rank).also { fgpText.text = it }
        (stats.tpp.avg+"% / NBA Rank:"+stats.tpp.rank).also { tppText.text = it }
        (stats.ftp.avg+"% / NBA Rank:"+stats.ftp.rank).also { ftpText.text = it }
        (stats.orpg.avg+" / NBA Rank:"+stats.orpg.rank).also { orpgText.text = it }
        (stats.drpg.avg+" / NBA Rank:"+stats.drpg.rank).also { drpgText.text = it }
        (stats.trpg.avg+" / NBA Rank:"+stats.trpg.rank).also { trpgText.text = it }
        (stats.tpg.avg+" / NBA Rank:"+stats.tpg.rank).also { tpgText.text = it }
        (stats.spg.avg+" / NBA Rank:"+stats.spg.rank).also { spgText.text = it }
        (stats.apg.avg+" / NBA Rank:"+stats.apg.rank).also { apgText.text = it }
        (stats.bpg.avg+" / NBA Rank:"+stats.bpg.rank).also { bpgText.text = it }
        (stats.pfpg.avg+" / NBA Rank:"+stats.pfpg.rank).also { pfpgText.text = it }
        (stats.ppg.avg+" / NBA Rank:"+stats.ppg.rank).also { ppgText.text = it }
        (stats.oppg.avg+" / NBA Rank:"+stats.oppg.rank).also { oppgText.text = it }
    }
}