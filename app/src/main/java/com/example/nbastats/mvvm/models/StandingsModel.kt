package com.example.nbastats.mvvm.models

import android.content.Context
import android.util.Log
import com.example.nbastats.data.Standings
import com.example.nbastats.data.Team
import com.example.nbastats.networking.RetrofitService
import com.example.nbastats.networking.responses.ResponseLeaguesS
import com.example.nbastats.util_interfaces.VMI
import com.example.nbastats.utils.SQLiteHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class StandingsModel {
    private lateinit var vmi: VMI<Standings>
    private lateinit var sqlHelper: SQLiteHelper
    private lateinit var conference: String
    fun attach(vmi: VMI<Standings>, context: Context, conference:String){
        this.vmi=vmi
        this.sqlHelper=SQLiteHelper(context)
        this.conference=conference
    }

    fun getStandings(){
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
                RetrofitService.getInstance().getStandings()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({response->onResponse(response)}, {error->onFailure(error)})
        )
    }
    private fun onFailure(error:Throwable){
        Log.e("standingsError",error.message.toString())
    }

    private fun onResponse(response: ResponseLeaguesS){
        if(conference=="East")
            vmi.onResponse(response.leagues.conferences.conference.east)
        else
            vmi.onResponse(response.leagues.conferences.conference.west)
    }

    fun getTeams():ArrayList<Team>{
        return sqlHelper.getTeamsByConference(conference)
    }
}