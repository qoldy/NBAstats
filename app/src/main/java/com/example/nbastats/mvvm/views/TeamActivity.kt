package com.example.nbastats.mvvm.views

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.example.nbastats.R
import com.example.nbastats.data.Team
import com.example.nbastats.mvvm.viewmodels.CoachesVM
import com.example.nbastats.mvvm.viewmodels.TeamsVM
import com.example.nbastats.utils.VMFactory
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class TeamActivity:AppCompatActivity() {
    private lateinit var fragmentContainer:LinearLayout
    private var fragmentRoster=RosterFragment()
    private var fragmentStats = TeamStatsFragment()
    private lateinit var teamText:TextView
    private lateinit var conferenceText:TextView
    private lateinit var divisionText:TextView
    private lateinit var headText:TextView
    private lateinit var teamId:String
    private lateinit var teamsVM:TeamsVM
    private lateinit var coachesVM:CoachesVM
    private lateinit var toolbar: Toolbar
    private lateinit var logo: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team_activity)
        init()
    }
    private fun init(){
        fragmentContainer=findViewById(R.id.fragment_container)
        intent.getStringExtra("teamId").also { teamId = it }
        teamText=findViewById(R.id.team_name)
        conferenceText=findViewById(R.id.conference)
        divisionText=findViewById(R.id.division)
        headText=findViewById(R.id.head)
        toolbar=findViewById(R.id.toolbar)
        toolbar.findViewById<ImageButton>(R.id.button_navigation).setOnClickListener {
            this.findViewById<DrawerLayout>(R.id.main_layout).openDrawer(GravityCompat.START)
        }
        logo=findViewById(R.id.logo)

        val bundle=Bundle()
        bundle.putString("teamId", teamId)
        fragmentRoster.arguments=bundle
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragmentRoster)
                .commit()

        val factory = VMFactory()
        val provider = ViewModelProvider(this, factory)
        logo.loadSvg("https://cdn.nba.com/logos/nba/${teamId}/global/L/logo.svg")
        teamsVM=provider.get(TeamsVM::class.java)
        coachesVM=provider.get(CoachesVM::class.java)
        teamsVM.init(this)
        coachesVM.init(this)
        teamsVM.getTeam(teamId)?.let { initText(it) }
    }

    private fun initText(team: Team){
        "${team.city} ${team.name}".also { teamText.text = it }
        "${team.conference}ern  Conference".also { conferenceText.text = it }
        "${team.division} Division".also { divisionText.text = it }
        val coach = coachesVM.getCoach(team.id)
        "${coach?.firstName} ${coach?.lastName}".also { headText.text = it }
    }

    fun onRosterClick(view:View){
        val bundle=Bundle()
        bundle.putString("teamId", teamId)
        fragmentRoster.arguments=bundle
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragmentRoster)
                .commit()
    }

    fun onStatsClick(view:View){
        val bundle=Bundle()
        bundle.putString("teamId", teamId)
        fragmentStats.arguments=bundle
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragmentStats)
                .commit()
    }
    fun ImageView.loadSvg(url:String?){
        GlideToVectorYou.init()
                .with(this.context)
                .load(Uri.parse(url),this)
    }

}