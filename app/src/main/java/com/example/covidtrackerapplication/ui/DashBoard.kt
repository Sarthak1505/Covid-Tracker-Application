package com.example.covidtrackerapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.covidtrackerapplication.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashBoard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        changeFragment()
    }

  private fun changeFragment(){
        val fragmentTransaction = supportFragmentManager
        fragmentTransaction.beginTransaction().replace(R.id.fragmentView,CovidCases()).commit()
    }
}