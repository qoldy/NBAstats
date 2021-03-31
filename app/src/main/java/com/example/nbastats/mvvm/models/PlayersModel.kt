package com.example.nbastats.mvvm.models

import android.util.Log
import com.example.nbastats.data.Player
import com.example.nbastats.networking.RetrofitService
import com.example.nbastats.networking.responses.ResponseLeaguesP
import com.example.nbastats.util_interfaces.VMI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PlayersModel {
    private lateinit var vmi: VMI<Player>
    fun attach(vmi:VMI<Player>){
        this.vmi=vmi
    }
    fun getPlayers(){
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
                RetrofitService.getInstance().getPlayers()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({response->onResponse(response)}, {error->onFailure(error)})
        )
    }
    private fun onFailure(error:Throwable){
        Log.v("wtf",error.message.toString())
        Log.v("wtffff", error.stackTraceToString())
    }

    private fun onResponse(response: ResponseLeaguesP){
        Log.v("modelplayers", "ok")
        vmi.onResponse(response.leagues.players)
    }
}