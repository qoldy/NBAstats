package com.example.nbastats.mvvm.models

import android.util.Log
import com.example.nbastats.data.Game
import com.example.nbastats.networking.RetrofitService
import com.example.nbastats.networking.responses.ResponseGames
import com.example.nbastats.networking.responses.ResponseLeaguesP
import com.example.nbastats.util_interfaces.VMI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GameModel {
    private lateinit var vmi: VMI<Game>
    fun attach(vmi:VMI<Game>){
        this.vmi=vmi
    }
    fun getGamesByDate(date:String){
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
                RetrofitService.getInstance().getGamesByDate(date)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({response->onResponse(response)}, {error->onError(error)})
        )
    }
    private fun onError(error:Throwable){
        Log.e("playerStatsError",error.message.toString())
    }

    private fun onResponse(response: ResponseGames){
        Log.i("games", response.games.toString())
        vmi.onResponse(response.games)
    }
}