package com.example.nbastats.mvvm.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nbastats.R
import com.example.nbastats.data.Player
import com.example.nbastats.mvvm.viewmodels.PlayersVM
import com.example.nbastats.utils.VMFactory

class PlayerInfoFragment:Fragment() {
    private lateinit var playersVM: PlayersVM
    private lateinit var fNameText:TextView
    private lateinit var lNameText:TextView
    private lateinit var teamNameText:TextView
    private lateinit var jerseyText:TextView
    private lateinit var posText:TextView
    private lateinit var dateText:TextView
    private lateinit var heightText:TextView
    private lateinit var weightText:TextView
    private lateinit var debutText:TextView
    private lateinit var yearsText:TextView
    private lateinit var countryText:TextView
    private lateinit var collegeText:TextView

    private lateinit var playerId:String
    private lateinit var teamName:String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.player_info_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init(view)
        setText(playersVM.getPlayerById(playerId))
    }

    private fun init(view:View){
        playerId= this.arguments?.getString("playerId").toString()
        teamName= this.arguments?.getString("teamName").toString()

        fNameText=view.findViewById(R.id.first_name)
        lNameText=view.findViewById(R.id.last_name)
        teamNameText=view.findViewById(R.id.team)
        jerseyText=view.findViewById(R.id.jersey)
        posText=view.findViewById(R.id.position)
        dateText=view.findViewById(R.id.date_of_birth)
        heightText=view.findViewById(R.id.height)
        weightText=view.findViewById(R.id.weight)
        debutText=view.findViewById(R.id.debut)
        yearsText=view.findViewById(R.id.years_pro)
        countryText=view.findViewById(R.id.country)
        collegeText=view.findViewById(R.id.college)


        val factory = VMFactory()
        val provider = ViewModelProvider(this, factory)
        playersVM=provider.get(PlayersVM::class.java)
        context?.let { playersVM.init(it) }
    }

    private fun setText(player: Player){
        fNameText.text = player.firstName
        lNameText.text = player.lastName
        teamNameText.text = teamName
        jerseyText.text = player.jersey
        posText.text = player.position
        dateText.text = player.dateOfBirth
        heightText.text = player.height
        weightText.text = player.weight
        debutText.text = player.nbaDebut
        yearsText.text = player.yearsPro
        countryText.text = player.country
        collegeText.text = player.college
    }
}