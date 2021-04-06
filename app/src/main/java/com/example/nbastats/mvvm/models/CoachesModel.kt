package com.example.nbastats.mvvm.models

import android.content.Context
import android.util.Log
import com.example.nbastats.data.Coach
import com.example.nbastats.networking.RetrofitService
import com.example.nbastats.networking.responses.ResponseLeaguesC
import com.example.nbastats.networking.responses.ResponseLeaguesT
import com.example.nbastats.util_interfaces.VMI
import com.example.nbastats.utils.SQLiteHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CoachesModel {
    private lateinit var vmi: VMI<Coach>
    private lateinit var sqlHelper:SQLiteHelper

    fun attach(vmi:VMI<Coach>, context: Context){
        this.vmi=vmi
        sqlHelper=SQLiteHelper(context)
    }
    fun getCoaches(){
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
                RetrofitService.getInstance().getCoaches()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({response->onResponse(response)}, {error->onError(error)})
        )
    }

    fun getCoach(teamId:String):Coach?{
        return sqlHelper.getCoach(teamId)
    }

    private fun onError(error:Throwable){
        Log.e("coachesError",error.message.toString())
    }

    private fun onResponse(response: ResponseLeaguesC){
        sqlHelper.fillCoaches(response.leagues.coaches)
        vmi.onResponse(response.leagues.coaches)
    }
}