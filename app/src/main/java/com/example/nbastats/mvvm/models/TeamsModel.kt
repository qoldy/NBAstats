package com.example.nbastats.mvvm.models

import android.content.Context
import android.util.Log
import com.example.nbastats.data.Team
import com.example.nbastats.networking.RetrofitService
import com.example.nbastats.networking.responses.ResponseLeaguesT
import com.example.nbastats.util_interfaces.VMI
import com.example.nbastats.utils.SQLiteHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TeamsModel {
    private lateinit var vmi: VMI<Team>
    private lateinit var sqlHelper: SQLiteHelper
    fun attach(vmi:VMI<Team>, context: Context){
        this.vmi=vmi
        sqlHelper=SQLiteHelper(context)
    }
    fun getTeams(){
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            RetrofitService.getInstance().getTeams()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({response->onResponse(response)}, {error->onFailure(error)})
        )
    }

    fun getTeam(teamId:String): Team? {
        return sqlHelper.getTeam(teamId)
    }

    private fun onFailure(error:Throwable){

    }

    private fun onResponse(response: ResponseLeaguesT){
        sqlHelper.fillTeams(response.leagues.teams)
        vmi.onResponse(response.leagues.teams)
    }
}