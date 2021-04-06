package com.example.nbastats.mvvm.views

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.example.nbastats.R
import com.example.nbastats.mvvm.viewmodels.ScheduleVM
import com.example.nbastats.utils.VMFactory
import com.example.nbastats.utils.adapters.ScheduleAdapter
import java.time.LocalDate

class ScheduleActivity:AppCompatActivity() {
    private lateinit var scheduleList: ListView
    private lateinit var scheduleAdapter: ScheduleAdapter
    private lateinit var scheduleVM: ScheduleVM
    private lateinit var toolbar: Toolbar
    private lateinit var date:String
    private lateinit var localDate: LocalDate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_activity)
        init()
    }
    private fun init(){
        scheduleList=findViewById(R.id.schedule_list_view)
        toolbar=findViewById(R.id.toolbar)
        toolbar.findViewById<ImageButton>(R.id.button_navigation).setOnClickListener {
            this.findViewById<DrawerLayout>(R.id.main_layout).openDrawer(GravityCompat.START)
        }

        val factory = VMFactory()
        val provider = ViewModelProvider(this, factory)
        scheduleVM=provider.get(ScheduleVM::class.java)
        scheduleVM.init(this)

        localDate=LocalDate.now()
        date=if(localDate.monthValue<10)
            "${localDate.year}0${localDate.monthValue}"
        else
            "${localDate.year}${localDate.monthValue}"
        date += if(localDate.dayOfMonth<10)
            "0${localDate.dayOfMonth}"
        else "${localDate.dayOfMonth}"
        scheduleAdapter= ScheduleAdapter(this, scheduleVM.getNextWeek(date), scheduleVM.getTeams())
        scheduleList.adapter=scheduleAdapter
    }
}