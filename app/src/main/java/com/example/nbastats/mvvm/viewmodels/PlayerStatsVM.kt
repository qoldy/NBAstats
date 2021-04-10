package com.example.nbastats.mvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nbastats.data.PlayerStats
import com.example.nbastats.mvvm.models.PlayerStatsModel
import com.example.nbastats.util_interfaces.VMI

class PlayerStatsVM:ViewModel(),VMI<PlayerStats> {
    var liveData = MutableLiveData<ArrayList<PlayerStats>>()
    private var model = PlayerStatsModel()
    fun init(playerId:String){
        model.attach(this)
        model.getPlayerStats(playerId)
    }
    fun getLastStats(): PlayerStats {
        return liveData.value!![0]
    }
    override fun onResponse(response: ArrayList<PlayerStats>) {
        liveData.value=response
    }


}