package com.example.nbastats.mvvm.views

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nbastats.R
import com.example.nbastats.mvvm.viewmodels.PlayersVM
import com.example.nbastats.mvvm.viewmodels.TeamsVM
import com.example.nbastats.utils.VMFactory

class LoadingActivity:AppCompatActivity() {
    private lateinit var teamsVM:TeamsVM
    private lateinit var playersVM: PlayersVM
    private var flagTeams = false
    private var flagPlayers = false

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
        observeData()
        teamsVM.init()
        playersVM.init()
    }

    fun observeData(){
        teamsVM.liveData.observe(this, Observer {
            Log.v("gotTeams", it.toString())
            flagTeams=true
        })
        playersVM.liveData.observe(this, Observer {
            Log.v("gotPlayers", it.toString())
            flagPlayers=true
        })
    }
}