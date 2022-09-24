package com.team1.projectteam1.presentation.home.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.team1.projectteam1.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private val binding: ActivityProfileBinding by lazy {
        ActivityProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}