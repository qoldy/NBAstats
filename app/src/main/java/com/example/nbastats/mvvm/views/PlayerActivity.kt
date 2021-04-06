package com.example.nbastats.mvvm.views

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.nbastats.R
import com.example.nbastats.data.Player
import com.example.nbastats.mvvm.viewmodels.PlayersVM
import com.example.nbastats.mvvm.viewmodels.TeamsVM
import com.example.nbastats.utils.VMFactory

class PlayerActivity:AppCompatActivity() {
    private lateinit var playerText:TextView
    private lateinit var playersVM:PlayersVM
    private lateinit var playerId:String
    private lateinit var teamId:String
    private lateinit var teamsVM: TeamsVM
    private var infoFragment = PlayerInfoFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.player_activity)
        init()
    }

    private fun init(){
        intent.getStringExtra("playerId").also{playerId=it}
        intent.getStringExtra("teamId").also { teamId=it }
        playerText=findViewById(R.id.player)

        val factory = VMFactory()
        val provider = ViewModelProvider(this, factory)
        playersVM=provider.get(PlayersVM::class.java)
        playersVM.init(this)
        initText(playersVM.getPlayerById(playerId))
        teamsVM=provider.get(TeamsVM::class.java)
        teamsVM.init(this)
        val bundle=Bundle()
        bundle.putString("playerId", playerId)
        val team = teamsVM.getTeam(teamId)
        bundle.putString("teamName", "${team?.city} ${team?.name}")
        infoFragment.arguments=bundle
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, infoFragment)
                .commit()
    }

    private fun initText(player: Player){
        "${player.firstName} ${player.lastName}".also { playerText.text = it }
    }
}