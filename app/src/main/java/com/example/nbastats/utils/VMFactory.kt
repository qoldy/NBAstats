package com.example.nbastats.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nbastats.mvvm.viewmodels.CoachesVM
import com.example.nbastats.mvvm.viewmodels.PlayersVM
import com.example.nbastats.mvvm.viewmodels.StandingsVM
import com.example.nbastats.mvvm.viewmodels.TeamsVM

class VMFactory:ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TeamsVM::class.java)){
            return TeamsVM() as T
        }
        else if(modelClass.isAssignableFrom(PlayersVM::class.java))
            return PlayersVM() as T
        else if(modelClass.isAssignableFrom(StandingsVM::class.java))
            return StandingsVM() as T
        else if(modelClass.isAssignableFrom(CoachesVM::class.java))
            return CoachesVM() as T
        throw IllegalArgumentException ("UnknownViewModel")
    }
}