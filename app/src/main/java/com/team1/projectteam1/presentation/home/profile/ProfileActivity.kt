package com.team1.projectteam1.presentation.home.profile


import android.os.Bundle
import android.Manifest
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.team1.projectteam1.databinding.ActivityProfileBinding


class ProfileActivity : AppCompatActivity() {

    private val binding: ActivityProfileBinding by lazy {
        ActivityProfileBinding.inflate(layoutInflater)
    }

    private val permissionList = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
    private val checkPermission = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
        result.forEach {
            if(!it.value) {
                Toast.makeText(applicationContext, "권한 동의 필요!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private val readImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        Glide.with(this)
            .load(uri)
            .into(binding.profileIv)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        checkPermission.launch(permissionList)

        binding.profileIv.setOnClickListener {
            readImage.launch("image/*")
        }

        binding.finishTv.setOnClickListener {
            val pesonas = binding.personasEt.toString()
            val nickname = binding.nicknameEt.toString()
            val oneline = binding.onelineEt.toString()
        }

    }
}