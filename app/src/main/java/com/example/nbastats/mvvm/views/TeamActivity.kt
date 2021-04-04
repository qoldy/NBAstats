package com.example.nbastats.mvvm.views

import android.os.Bundle
import android.widget.ImageButton
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

class TeamActivity:AppCompatActivity() {
    private lateinit var fragmentContainer:LinearLayout
    private var fragmentRoster=RosterFragment()
    private lateinit var teamText:TextView
    private lateinit var conferenceText:TextView
    private lateinit var divisionText:TextView
    private lateinit var headText:TextView
    private lateinit var teamId:String
    private lateinit var teamsVM:TeamsVM
    private lateinit var coachesVM:CoachesVM
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team_activity)
        init()
    }
    private fun init(){
        fragmentContainer=findViewById(R.id.fragment_container)
        teamId=intent.getStringExtra("teamId")
        teamText=findViewById(R.id.team_name)
        conferenceText=findViewById(R.id.conference)
        divisionText=findViewById(R.id.division)
        headText=findViewById(R.id.head)
        toolbar=findViewById(R.id.toolbar)
        toolbar.findViewById<ImageButton>(R.id.button_navigation).setOnClickListener {
            this.findViewById<DrawerLayout>(R.id.main_layout).openDrawer(GravityCompat.START)
        }

        val bundle=Bundle()
        bundle.putString("teamId", teamId)
        fragmentRoster.arguments=bundle
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragmentRoster)
                .addToBackStack("roster")
                .commit()

        val factory = VMFactory()
        val provider = ViewModelProvider(this, factory)
        teamsVM=provider.get(TeamsVM::class.java)
        coachesVM=provider.get(CoachesVM::class.java)
        teamsVM.init(this)
        coachesVM.init(this)
        teamsVM.getTeam(teamId)?.let { initText(it) }
    }

    private fun initText(team: Team){
        teamText.text= "${team.city} ${team.name}"
        conferenceText.text="${team.conference}  Conference"
        divisionText.text="${team.division} Division"
        var coach = coachesVM.getCoach(team.id)
        headText.text="${coach?.firstName} ${coach?.lastName}"
    }

}