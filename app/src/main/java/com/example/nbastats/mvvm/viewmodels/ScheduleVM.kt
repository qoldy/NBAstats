package com.example.nbastats.mvvm.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nbastats.data.Schedule
import com.example.nbastats.data.Team
import com.example.nbastats.mvvm.models.ScheduleModel
import com.example.nbastats.util_interfaces.VMI

class ScheduleVM:ViewModel(),VMI<Schedule> {
    var liveData=MutableLiveData<ArrayList<Schedule>>()
    var model=ScheduleModel()
    fun init(context: Context){
        model.attach(this, context)
    }
    fun getSchedule(){
        model.getSchedule()
    }
    override fun onResponse(response: ArrayList<Schedule>) {
        liveData.value=response
    }
    fun getNextWeek(date:String):ArrayList<Schedule>{
        return model.getWeekSchedule(date)
    }
    fun getTeams():ArrayList<Team>{
        return model.getTeams()
    }
}