package com.example.nbastats.mvvm.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nbastats.R
import com.example.nbastats.data.Player
import com.example.nbastats.mvvm.viewmodels.PlayersVM
import com.example.nbastats.utils.VMFactory
import com.example.nbastats.utils.adapters.RosterAdapter

class RosterFragment: Fragment() {
    private var roster=ArrayList<Player>()
    private lateinit var rosterListView:ListView
    private lateinit var playersVM:PlayersVM
    private lateinit var adapter:RosterAdapter
    private var teamId=""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.roster_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init(view)
    }

    private fun init(view:View){
        teamId= this.arguments?.getString("teamId").toString()
        rosterListView=view.findViewById(R.id.list)
        val factory = VMFactory()
        val provider = ViewModelProvider(this, factory)
        playersVM=provider.get(PlayersVM::class.java)
        context?.let { playersVM.init(it) }
        setUpRoster()
    }
    private fun setUpRoster(){
        Log.v("id", teamId)
        roster = playersVM.getRoster(teamId)
        adapter= context?.let { RosterAdapter(it,roster) }!!
        rosterListView.adapter=adapter
    }
}