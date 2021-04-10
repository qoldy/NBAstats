package com.example.nbastats.mvvm.views

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.example.nbastats.R
import com.example.nbastats.data.Game
import com.example.nbastats.mvvm.viewmodels.GameVM
import com.example.nbastats.utils.VMFactory
import com.example.nbastats.utils.adapters.MatchHistoryAdapter

class MatchHistoryActivity:AppCompatActivity() {
    private lateinit var historyList:ListView
    private lateinit var matchHistoryAdapter: MatchHistoryAdapter
    private lateinit var gameVM:GameVM
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.match_history_activity)
        init()
    }
    private fun init(){
        historyList=findViewById(R.id.match_history_list_view)
        toolbar=findViewById(R.id.toolbar)
        toolbar.findViewById<ImageButton>(R.id.button_navigation).setOnClickListener {
            this.findViewById<DrawerLayout>(R.id.main_layout).openDrawer(GravityCompat.START)
        }
        val factory = VMFactory()
        val provider = ViewModelProvider(this, factory)
        gameVM=provider.get(GameVM::class.java)
        gameVM.init()
        observeData()
        gameVM.getGames()
    }
    private fun observeData(){
        gameVM.livaData.observe(this,{
            if(it.isEmpty())
                return@observe
            matchHistoryAdapter= MatchHistoryAdapter(this,it)
            historyList.adapter=matchHistoryAdapter
        })
    }
}