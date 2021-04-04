package com.example.nbastats.mvvm.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nbastats.data.Coach
import com.example.nbastats.mvvm.models.CoachesModel
import com.example.nbastats.util_interfaces.VMI

class CoachesVM:ViewModel(),VMI<Coach> {
    var liveData = MutableLiveData<ArrayList<Coach>>()
    private var model=CoachesModel()

    fun init(context:Context){
        model.attach(this, context)
    }

    fun getCoaches(){
        model.getCoaches()
    }

    fun getCoach(teamId:String):Coach?{
        return model.getCoach(teamId)
    }
    override fun onResponse(response: ArrayList<Coach>) {
        liveData.value=response
    }

}