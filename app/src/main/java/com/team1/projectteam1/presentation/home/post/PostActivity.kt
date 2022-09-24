package com.team1.projectteam1.presentation.home.post

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.team1.projectteam1.databinding.ActivityPostBinding

class PostActivity : AppCompatActivity() {

    private val binding: ActivityPostBinding by lazy {
        ActivityPostBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }

}