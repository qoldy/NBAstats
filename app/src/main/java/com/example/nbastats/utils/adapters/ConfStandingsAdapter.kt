package com.example.nbastats.utils.adapters

import android.app.Activity
import android.net.Uri
import android.provider.ContactsContract
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.nbastats.R
import com.example.nbastats.data.Standings
import com.example.nbastats.data.Team
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.squareup.picasso.Picasso

class ConfStandingsAdapter(private val context: Activity, private val standings: ArrayList<Standings>,
                           private val teams:ArrayList<Team>)
    : ArrayAdapter<Standings>(context, R.layout.item_team_standing, standings) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.item_team_standing, null, true)
        var team = teams[0]
        for(t in teams) {
            if(t.id==standings[position].teamId){
                team=t
                break
            }
        }

        val positionText = rowView.findViewById(R.id.position) as TextView
        val teamText = rowView.findViewById(R.id.team) as TextView
        val winsText = rowView.findViewById(R.id.wins) as TextView
        val lossText = rowView.findViewById(R.id.loss) as TextView
        val gamesBehindText = rowView.findViewById(R.id.games_behind) as TextView
        val logo = rowView.findViewById<ImageView>(R.id.logo)

        positionText.text=(position+1).toString()
        "${team.city} ${team.name}".also { teamText.text = it }
        winsText.text=standings[position].wins.toString()
        lossText.text=standings[position].loss.toString()
        gamesBehindText.text=standings[position].gamesBehind.toString()
        logo.loadSvg("https://cdn.nba.com/logos/nba/${team.id}/global/L/logo.svg")
        return rowView
    }

    fun ImageView.loadSvg(url:String?){
        GlideToVectorYou.init()
                .with(this.context)
                .load(Uri.parse(url),this)
    }
}