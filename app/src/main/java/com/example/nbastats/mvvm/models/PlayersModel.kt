package com.example.nbastats.mvvm.models

import android.content.Context
import android.util.Log
import com.example.nbastats.data.Player
import com.example.nbastats.networking.RetrofitService
import com.example.nbastats.networking.responses.ResponseLeaguesP
import com.example.nbastats.util_interfaces.VMI
import com.example.nbastats.utils.SQLiteHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PlayersModel {
    private lateinit var vmi: VMI<Player>
    private lateinit var sqlHelper: SQLiteHelper
    fun attach(vmi:VMI<Player>, context: Context){
        this.vmi=vmi
        this.sqlHelper=SQLiteHelper(context)
    }
    fun getPlayers(){
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
                RetrofitService.getInstance().getPlayers()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({response->onResponse(response)}, {error->onError(error)})
        )
    }
    private fun onError(error:Throwable){
        Log.e("playersError",error.message.toString())
    }

    private fun onResponse(response: ResponseLeaguesP){
        sqlHelper.fillPlayers(response.leagues.players)
        vmi.onResponse(response.leagues.players)
    }
    fun getTeamRoster(teamId:String):ArrayList<Player>{
        return sqlHelper.getRoster(teamId)
    }

    fun getPlayerById(playerId:String):Player{
        return sqlHelper.getPlayerById(playerId)
    }
}