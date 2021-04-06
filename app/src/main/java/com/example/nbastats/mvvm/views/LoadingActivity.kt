package com.example.nbastats.mvvm.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nbastats.R
import com.example.nbastats.mvvm.viewmodels.CoachesVM
import com.example.nbastats.mvvm.viewmodels.PlayersVM
import com.example.nbastats.mvvm.viewmodels.ScheduleVM
import com.example.nbastats.mvvm.viewmodels.TeamsVM
import com.example.nbastats.utils.VMFactory

class LoadingActivity:AppCompatActivity() {
    private lateinit var teamsVM:TeamsVM
    private lateinit var playersVM: PlayersVM
    private lateinit var coachesVM: CoachesVM
    private lateinit var scheduleVM: ScheduleVM
    private var flagTeams = false
    private var flagPlayers = false
    private var flagCoaches = false
    private var flagSchedule = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loading_activity)
        init()
    }
    private fun init(){
        val factory = VMFactory()
        val provider = ViewModelProvider(this, factory)
        teamsVM = provider.get(TeamsVM::class.java)
        playersVM = provider.get(PlayersVM::class.java)
        coachesVM = provider.get(CoachesVM::class.java)
        scheduleVM = provider.get(ScheduleVM::class.java)
        observeData()
        teamsVM.init(this)
        teamsVM.getTeams()
        playersVM.init(this)
        playersVM.getPlayers()
        coachesVM.init(this)
        coachesVM.getCoaches()
        scheduleVM.init(this)
        scheduleVM.getSchedule()
    }

    private fun observeData(){
        teamsVM.liveData.observe(this, Observer {
            flagTeams=true
            if(flagPlayers&&flagCoaches&&flagSchedule)
                start()
        })
        playersVM.liveData.observe(this, Observer {
            flagPlayers=true
            if(flagTeams&&flagCoaches&&flagSchedule)
                start()
        })
        coachesVM.liveData.observe(this, {
            flagCoaches=true
            if(flagPlayers&&flagTeams&&flagSchedule)
                start()
        })
        scheduleVM.liveData.observe(this,{
            flagSchedule=true
            if(flagCoaches&&flagTeams&&flagPlayers)
                start()
        })
    }

    private fun start(){
        val intent= Intent(this, StandingsActivity::class.java)
        startActivityForResult(intent, 0)
    }
}