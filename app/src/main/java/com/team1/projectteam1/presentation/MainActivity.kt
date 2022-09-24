package com.team1.projectteam1.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.team1.projectteam1.R
import com.team1.projectteam1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupBottomNavigationView()

    }


    // bottomNavigationView
    private fun setupBottomNavigationView() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.fragment_look -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, LookFragment())
                        .commit()
                    true
                }
                R.id.fragment_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, HomeFragment())
                        .commit()
                    true
                }
                R.id.fragment_mypage -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, MypageFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}