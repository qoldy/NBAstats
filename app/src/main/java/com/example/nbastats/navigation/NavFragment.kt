package com.example.nbastats.navigation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.nbastats.R
import com.example.nbastats.mvvm.views.MatchHistoryActivity
import com.example.nbastats.mvvm.views.ScheduleActivity
import com.example.nbastats.mvvm.views.StandingsActivity


class NavFragment: Fragment() {
    private lateinit var buttonConfStandings:Button
    private lateinit var buttonSchedule:Button
    private lateinit var buttonMatchHistory:Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.side_menu_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonConfStandings=view.findViewById(R.id.button_conf_standings)
        buttonConfStandings.setOnClickListener {
            val intent = Intent(context, StandingsActivity::class.java)
            startActivityForResult(intent, 0)
        }
        buttonSchedule=view.findViewById(R.id.button_schedule)
        buttonSchedule.setOnClickListener {
            val intent = Intent(context, ScheduleActivity::class.java)
            startActivityForResult(intent, 0)
        }
        buttonMatchHistory=view.findViewById(R.id.button_match_history)
        buttonMatchHistory.setOnClickListener {
            val intent = Intent(context, MatchHistoryActivity::class.java)
            startActivityForResult(intent, 0)
        }
    }
}