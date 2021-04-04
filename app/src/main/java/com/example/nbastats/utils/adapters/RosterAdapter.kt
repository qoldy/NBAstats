package com.example.nbastats.utils.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.nbastats.R
import com.example.nbastats.data.Player

class RosterAdapter(context: Context, var players:ArrayList<Player>)
    : ArrayAdapter<Player>(context, R.layout.item_player_roster, players) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView=inflater.inflate(R.layout.item_player_roster, null, true)

        val jerseyText=rowView.findViewById<TextView>(R.id.jersey)
        val nameText=rowView.findViewById<TextView>(R.id.player)
        val posText=rowView.findViewById<TextView>(R.id.player_pos)

        jerseyText.text = players[position].jersey
        nameText.text = "${players[position].firstName} ${players[position].lastName}"
        posText.text=players[position].position
        return rowView
    }
}