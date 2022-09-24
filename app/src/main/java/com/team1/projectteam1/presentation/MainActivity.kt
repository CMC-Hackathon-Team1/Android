package com.team1.projectteam1.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.team1.projectteam1.R
import com.team1.projectteam1.databinding.ActivityMainBinding
import com.team1.projectteam1.util.calculateCurrentMonth
import com.team1.projectteam1.util.calculateCurrentYear


class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var mainNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initDate()
        setupBottomNavigationView()
    }

    // 앱 실행 시점 기준 년도, 월 설정하기
    private fun initDate() {
        mainViewModel.currentYear = calculateCurrentYear()
        mainViewModel.currentMonth = calculateCurrentMonth()
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