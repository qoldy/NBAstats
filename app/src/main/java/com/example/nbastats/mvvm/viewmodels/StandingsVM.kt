package com.example.nbastats.mvvm.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nbastats.data.Standings
import com.example.nbastats.data.Team
import com.example.nbastats.mvvm.models.StandingsModel
import com.example.nbastats.util_interfaces.VMI

class StandingsVM: ViewModel(), VMI<Standings> {
    var liveData=MutableLiveData<ArrayList<Team>>()
    lateinit var standings:ArrayList<Standings>
    private var model = StandingsModel()

    fun init(context: Context, conference:String){
        model.attach(this, context, conference)
        model.getStandings()
    }
    override fun onResponse(response: ArrayList<Standings>) {
        standings=response
        liveData.value=model.getTeams()
    }
    fun getTeam(id:Int):String{
        return standings[id].teamId
    }
}