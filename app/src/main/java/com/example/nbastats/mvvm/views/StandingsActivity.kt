package com.example.nbastats.mvvm.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nbastats.R
import com.example.nbastats.mvvm.viewmodels.StandingsVM
import com.example.nbastats.utils.adapters.ConfStandingsAdapter
import com.example.nbastats.utils.VMFactory

class StandingsActivity:AppCompatActivity() {
    private lateinit var standingsVM: StandingsVM
    private var conference = "East"
    private lateinit var confListView:ListView
    private lateinit var confStandingsAdapter: ConfStandingsAdapter
    private lateinit var toolbar:Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.standings_activity)
        init()
    }
    private fun init(){
        confListView=findViewById(R.id.conf_list_view)
        confListView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id -> itemClicked(id.toInt()) }
        toolbar=findViewById(R.id.toolbar)
        toolbar.findViewById<ImageButton>(R.id.button_navigation).setOnClickListener {
            this.findViewById<DrawerLayout>(R.id.main_layout).openDrawer(GravityCompat.START)
        }

        val factory = VMFactory()
        val provider = ViewModelProvider(this, factory)
        standingsVM=provider.get(StandingsVM::class.java)
        observeData()
        standingsVM.init(this, conference)
    }

    private fun observeData(){
        standingsVM.liveData.observe(this, Observer {
            confStandingsAdapter= ConfStandingsAdapter(this,  standingsVM.standings, it)
            confListView.adapter=confStandingsAdapter
        })
    }
    fun onEastClicked(view: View){
        if(conference=="East")
            return
        conference="East"
        standingsVM.init(this, conference)
    }
    fun onWestClicked(view: View){
        if(conference=="West")
            return
        conference="West"
        standingsVM.init(this, conference)
    }

    fun itemClicked(id:Int){
        intent = Intent(this, TeamActivity::class.java)
        intent.putExtra("teamId",standingsVM.getTeam(id))
        startActivityForResult(intent,0)
    }
}