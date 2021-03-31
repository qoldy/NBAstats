package com.example.nbastats.mvvm.models

import android.util.Log
import com.example.nbastats.data.Team
import com.example.nbastats.networking.RetrofitService
import com.example.nbastats.networking.responses.ResponseLeaguesT
import com.example.nbastats.util_interfaces.VMI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TeamsModel {
    private lateinit var vmi: VMI<Team>
    fun attach(vmi:VMI<Team>){
        this.vmi=vmi
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

    private fun onFailure(error:Throwable){

    }

    private fun onResponse(response: ResponseLeaguesT){
        vmi.onResponse(response.leagues.teams)
    }
}