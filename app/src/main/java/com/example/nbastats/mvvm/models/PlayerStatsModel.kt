package com.example.nbastats.mvvm.models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.nbastats.data.PlayerStats
import com.example.nbastats.networking.RetrofitService
import com.example.nbastats.networking.responses.ResponseLeaguesPS
import com.example.nbastats.networking.responses.ResponsePlayerStats
import com.example.nbastats.util_interfaces.VMI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PlayerStatsModel {
    private lateinit var vmi: VMI<PlayerStats>
    fun attach(vmi:VMI<PlayerStats>){
        this.vmi=vmi
    }
    fun getPlayerStats(playerId:String){
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
                RetrofitService.getInstance().getPlayerStats(playerId)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({response -> onResponse(response)}, {error -> onError(error)})
        )
    }
    private fun onError(error:Throwable){
        Log.e("playerStatsError",error.message.toString())
    }

    private fun onResponse(response: ResponseLeaguesPS){
        val list = ArrayList<PlayerStats>()
        for(season in response.league.standard.stats.regular.seasons){
            val stats = season.stats
            stats.season=season.seasonYear
            list.add(stats)
        }
        vmi.onResponse(list)
    }
}