package com.example.nbastats.mvvm.models

import android.util.Log
import com.example.nbastats.data.TeamStats
import com.example.nbastats.networking.RetrofitService
import com.example.nbastats.networking.responses.ResponseLeaguesTS
import com.example.nbastats.util_interfaces.VMI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TeamStatsModel {
    private lateinit var vmi: VMI<TeamStats>
    fun attach(vmi:VMI<TeamStats>){
        this.vmi=vmi
    }

    fun getTeamStats(){
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
                RetrofitService.getInstance().getStats()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({response -> onResponse(response)}, {error -> onError(error)})
        )
    }

    private fun onError(error:Throwable){
        Log.e("statsError",error.message.toString())
    }

    private fun onResponse(response:ResponseLeaguesTS){
        vmi.onResponse(response.leagues.response.teams.stats)
    }
}