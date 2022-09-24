package com.team1.projectteam1.presentation.home.post

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.team1.projectteam1.R
import com.team1.projectteam1.databinding.ActivityPostBinding

class PostActivity : AppCompatActivity() {

    private val binding: ActivityPostBinding by lazy {
        ActivityPostBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val button = findViewById<Button>(R.id.btn_ctgin)
        button.setOnClickListener{
            val bottomSheet = BottomSheet()
            bottomSheet.show(supportFragmentManager,bottomSheet.tag)

        }


    }



}
