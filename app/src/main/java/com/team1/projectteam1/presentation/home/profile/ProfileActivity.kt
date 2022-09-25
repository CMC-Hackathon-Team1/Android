package com.team1.projectteam1.presentation.home.profile


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDirections
import com.bumptech.glide.Glide
import com.team1.projectteam1.databinding.ActivityProfileBinding
import com.team1.projectteam1.presentation.home.HomeFragment


class ProfileActivity : AppCompatActivity(), ProfileView {

    private val binding: ActivityProfileBinding by lazy {
        ActivityProfileBinding.inflate(layoutInflater)
    }

    lateinit var profile: Profile
    var personaId: Int = 0
    lateinit var string: String

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

        binding.arrowIv.setOnClickListener {
            finish()
        }


        binding.finishTv.setOnClickListener {
            if( binding.personasEt.length()!=0 && binding.nicknameEt.length()!=0 && binding.onelineEt.length()!=0) {
                if (binding.onelineEt.length() <= 30) {

                    if (binding.personasEt.toString() == "기획자") {
                        personaId = 1
                    }
                    else if(binding.personasEt.toString() == "디자이너") {
                        personaId = 2
                    }
                    else if(binding.personasEt.toString() == "개발자") {
                        personaId = 3
                    }

                    profile = Profile(3, personaId, binding.nicknameEt.toString(), binding.onelineEt.toString(), binding.profileIv.toString() )
                    getProfileData()
                    HomeFragment()

                } else {
                    Toast.makeText(applicationContext, "한줄소개는 30자를 넘길 수 없습니다", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(applicationContext, "모든 항목을 입력해주세요", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun getProfileData(){
        val profileService = ProfileService()
        profileService.setProfileView(this)

        profileService.createProfileView()

    }

    override fun onInputProfileSuccess(code: Int, result: CreateProfileResult) {
        when(code){
            1000 ->{
                HomeFragment()
            }
        }
    }
}