package com.team1.projectteam1

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.team1.projectteam1.databinding.ActivitySplashBinding
import com.team1.projectteam1.presentation.home.profile.ProfileActivity

class SplashActivity: AppCompatActivity() {

    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            val Intent = Intent(this, ProfileActivity::class.java)
            Intent.putExtra("splash", "스플래시")
            startActivity(Intent)
        }

    }

}