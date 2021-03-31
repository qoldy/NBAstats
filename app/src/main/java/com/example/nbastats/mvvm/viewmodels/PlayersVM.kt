package com.example.nbastats.mvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nbastats.data.Player
import com.example.nbastats.mvvm.models.PlayersModel
import com.example.nbastats.util_interfaces.VMI

class PlayersVM:ViewModel(), VMI<Player> {
    var liveData = MutableLiveData<ArrayList<Player>>()
    private var model  = PlayersModel()

    fun init(){
        model.attach(this)
        model.getPlayers()
    }
    override fun onResponse(response:ArrayList<Player>) {
        liveData.value=response
    }
}