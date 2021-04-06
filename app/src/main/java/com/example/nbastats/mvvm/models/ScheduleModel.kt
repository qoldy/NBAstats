package com.example.nbastats.mvvm.models

import android.content.Context
import android.util.Log
import com.example.nbastats.data.Schedule
import com.example.nbastats.data.Team
import com.example.nbastats.networking.RetrofitService
import com.example.nbastats.networking.responses.ResponseLeaguesSc
import com.example.nbastats.util_interfaces.VMI
import com.example.nbastats.utils.SQLiteHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ScheduleModel {
    private lateinit var vmi: VMI<Schedule>
    private lateinit var sqlHelper: SQLiteHelper
    fun attach(vmi:VMI<Schedule>, context:Context){
        this.vmi=vmi
        sqlHelper= SQLiteHelper(context)
    }
    fun getSchedule(){
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
                RetrofitService.getInstance().getSchedule()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({response->onResponse(response)},{error->onError(error)})
        )
    }

    private fun onError(error:Throwable){
        Log.e("scheduleError",error.message.toString())
    }

    private fun onResponse(response:ResponseLeaguesSc){
        sqlHelper.fillSchedule(response.leagues.schedule)
        vmi.onResponse(response.leagues.schedule)
    }

    fun getWeekSchedule(date:String):ArrayList<Schedule>{
        return sqlHelper.getSchedule7Days(date)
    }

    fun getTeams():ArrayList<Team>{
        return sqlHelper.getAllTeams()
    }
}