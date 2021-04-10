package com.example.nbastats.utils.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.nbastats.R
import com.example.nbastats.data.Game
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class MatchHistoryAdapter (context: Context, var games:ArrayList<Game>)
    : ArrayAdapter<Game>(context, R.layout.item_match_history, games){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView=inflater.inflate(R.layout.item_match_history, null, true)
        val homeText=rowView.findViewById<TextView>(R.id.tricode_home)
        val guestText=rowView.findViewById<TextView>(R.id.tricode_guest)
        val scoreText=rowView.findViewById<TextView>(R.id.score)
        val homeLogo=rowView.findViewById<ImageView>(R.id.logo_home)
        val guestLogo=rowView.findViewById<ImageView>(R.id.logo_guest)
        homeText.text=games[position].homeTeam.triCode
        guestText.text=games[position].guestTeam.triCode
        homeLogo.loadSvg("https://cdn.nba.com/logos/nba/${games[position].homeTeam.teamId}/global/L/logo.svg")
        guestLogo.loadSvg("https://cdn.nba.com/logos/nba/${games[position].guestTeam.teamId}/global/L/logo.svg")
        "${games[position].homeTeam.score}-${games[position].guestTeam.score}".also { scoreText.text = it }
        return rowView
    }

    fun ImageView.loadSvg(url:String?){
        GlideToVectorYou.init()
                .with(this.context)
                .load(Uri.parse(url),this)
    }
}