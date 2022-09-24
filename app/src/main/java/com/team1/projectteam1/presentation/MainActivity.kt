package com.team1.projectteam1.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.team1.projectteam1.R
import com.team1.projectteam1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var mainNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupBottomNavigationView()
    }


    // bottomNavigationView
    private fun setupBottomNavigationView() {
        binding.mainBnv.itemIconTintList = null

        supportFragmentManager.findFragmentById(R.id.main_fcv)?.findNavController()?.let {
            mainNavController = it
        }
        binding.mainBnv.setupWithNavController(mainNavController)
    }
}