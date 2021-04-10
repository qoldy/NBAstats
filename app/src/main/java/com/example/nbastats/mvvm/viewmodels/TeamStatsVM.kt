package com.example.nbastats.mvvm.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nbastats.data.TeamStats
import com.example.nbastats.mvvm.models.TeamStatsModel
import com.example.nbastats.util_interfaces.VMI

class TeamStatsVM:ViewModel(), VMI<TeamStats> {
    var liveData = MutableLiveData<ArrayList<TeamStats>>()
    private var model=TeamStatsModel()
    fun init(){
        model.attach(this)
        model.getTeamStats()
    }
    fun getTeamStats(teamId:String):TeamStats{
        for(stats in liveData.value!!){
            if(stats.teamId==teamId)
                return stats
        }
        return liveData.value!![0]
    }
    override fun onResponse(response: ArrayList<TeamStats>) {
        liveData.value = response
    }
}