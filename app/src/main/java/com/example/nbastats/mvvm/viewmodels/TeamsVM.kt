package com.example.nbastats.mvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nbastats.data.Team
import com.example.nbastats.mvvm.models.TeamsModel
import com.example.nbastats.util_interfaces.VMI

class TeamsVM:ViewModel(), VMI<Team> {
    var liveData = MutableLiveData<ArrayList<Team>>()
    private var model = TeamsModel()

    fun init(){
        model.attach(this)
        model.getTeams()
    }

    override fun onResponse(response:ArrayList<Team>) {
        liveData.value=response
    }
}