package com.team1.projectteam1.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.team1.projectteam1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

    }
}