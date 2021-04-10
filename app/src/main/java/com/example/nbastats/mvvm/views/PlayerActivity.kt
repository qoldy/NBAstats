package com.example.nbastats.mvvm.views

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.example.nbastats.R
import com.example.nbastats.data.Player
import com.example.nbastats.mvvm.viewmodels.PlayersVM
import com.example.nbastats.mvvm.viewmodels.TeamsVM
import com.example.nbastats.utils.VMFactory
import com.squareup.picasso.Picasso

class PlayerActivity:AppCompatActivity() {
    private lateinit var playerText:TextView
    private lateinit var playersVM:PlayersVM
    private lateinit var playerId:String
    private lateinit var teamId:String
    private lateinit var teamsVM: TeamsVM
    private lateinit var toolbar: Toolbar
    private lateinit var headShot: ImageView

    private var infoFragment = PlayerInfoFragment()
    private var seasonFragment = PlayerStatsFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.player_activity)
        init()
    }

    private fun init(){
        intent.getStringExtra("playerId").also{playerId=it}
        intent.getStringExtra("teamId").also { teamId=it }
        playerText=findViewById(R.id.player)
        toolbar=findViewById(R.id.toolbar)
        toolbar.findViewById<ImageButton>(R.id.button_navigation).setOnClickListener {
            this.findViewById<DrawerLayout>(R.id.main_layout).openDrawer(GravityCompat.START)
        }
        headShot=findViewById<ImageView>(R.id.headshot)
        Picasso.get()
                .load("https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/${teamId}/2020/260x190/${playerId}.png")
                .into(headShot)

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

    fun onBasicClicked(view: View){
        val bundle=Bundle()
        bundle.putString("playerId", playerId)
        val team = teamsVM.getTeam(teamId)
        bundle.putString("teamName", "${team?.city} ${team?.name}")
        infoFragment.arguments=bundle
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, infoFragment)
                .commit()
    }
    fun onSeasonClicked(view:View){
        val bundle=Bundle()
        bundle.putString("playerId", playerId)
        seasonFragment.arguments=bundle
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, seasonFragment)
                .commit()
    }
}