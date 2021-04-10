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
import com.example.nbastats.data.Schedule
import com.example.nbastats.data.Team
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class ScheduleAdapter(context: Context, var schedule:ArrayList<Schedule>, var teams:ArrayList<Team>)
    :ArrayAdapter<Schedule>(context, R.layout.item_schedule, schedule){
    private lateinit var home:Team
    private lateinit var guest:Team

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView=inflater.inflate(R.layout.item_schedule, null, true)
        val homeText=rowView.findViewById<TextView>(R.id.tricode_home)
        val guestText=rowView.findViewById<TextView>(R.id.tricode_guest)
        val dateText=rowView.findViewById<TextView>(R.id.game_date)
        val timeText=rowView.findViewById<TextView>(R.id.game_time)
        val homeLogo=rowView.findViewById<ImageView>(R.id.logo_home)
        val guestLogo=rowView.findViewById<ImageView>(R.id.logo_guest)
        for(team in teams){
            if(team.id==schedule[position].home.id)
                home=team
            else if(team.id==schedule[position].guest.id)
                guest=team
        }
        homeText.text=home.tricode
        guestText.text=guest.tricode
        homeLogo.loadSvg("https://cdn.nba.com/logos/nba/${home.id}/global/L/logo.svg")
        guestLogo.loadSvg("https://cdn.nba.com/logos/nba/${guest.id}/global/L/logo.svg")

        "${schedule[position].date.substring(0,4)}-${schedule[position].date.substring(4,6)}-${schedule[position].date.substring(6,8)}".also { dateText.text = it }
        timeText.text=schedule[position].time
        return rowView
    }

    fun ImageView.loadSvg(url:String?){
        GlideToVectorYou.init()
                .with(this.context)
                .load(Uri.parse(url),this)
    }
}