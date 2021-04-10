package com.example.nbastats.mvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nbastats.data.Game
import com.example.nbastats.mvvm.models.GameModel
import com.example.nbastats.util_interfaces.VMI
import java.time.LocalDate

class GameVM:ViewModel(), VMI<Game> {
    var livaData = MutableLiveData<ArrayList<Game>>()
    private var model = GameModel()
    private var localDate = LocalDate.now()

    fun init(){
        model.attach(this)
    }
    fun getGames(){
        localDate = localDate.minusDays(1)
        var date=if(localDate.monthValue<10)
            "${localDate.year}0${localDate.monthValue}"
        else
            "${localDate.year}${localDate.monthValue}"
        date += if(localDate.dayOfMonth<10)
            "0${localDate.dayOfMonth}"
        else "${localDate.dayOfMonth}"
        model.getGamesByDate(date)
    }
    override fun onResponse(response: ArrayList<Game>) {
        livaData.value=response
    }
}