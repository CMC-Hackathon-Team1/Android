package com.team1.projectteam1.presentation.home.post

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.team1.projectteam1.R
import com.team1.projectteam1.databinding.ActivityPostBinding
import com.team1.projectteam1.presentation.MainActivity
import com.team1.projectteam1.presentation.home.HomeFragment

class PostActivity : AppCompatActivity() {

    private val binding: ActivityPostBinding by lazy {
        ActivityPostBinding.inflate(layoutInflater)
    }
    //권한 가져오기
    val CAMERA_PERMISSION = arrayOf(Manifest.permission.CAMERA)
    val STORAGE_PERMISSION = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    //권한 플래그값 정의
    val PERM_CAMERA = 98
    val PERM_STORAGE = 99

    //카메라와 갤러리를 호출하는 플래그
    val FLAG_REQ_CAMERA = 101
    val FLAG_REA_STORAGE = 102

    private val postViewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //저장소 권한체크
        if(checkPermission(STORAGE_PERMISSION,PERM_STORAGE)){
            setViews()
        }
        val button = findViewById<ImageView>(R.id.btn_ctgin)
        button.setOnClickListener{
            val bottomSheet = BottomSheet()
            bottomSheet.show(supportFragmentManager,bottomSheet.tag)

        }

        val textViewCategory = findViewById<TextView>(R.id.textView_category)

        postViewModel.selectedCategory.observe(this){
            textViewCategory.text = it
        }
        val postDone = findViewById<Button>(R.id.post_done)
        postDone.setOnClickListener{
            finish()
        }
//        postDone.setOnClickListener {
//            var isPrivate = "private"
//            if(binding.checkBox.isChecked == false){
//                isPrivate = "active"
//            }
//
//            val data = PostModel(
//                textViewCategory.text.toString(),
//                binding.editTextTag.text.toString(),
//                "예전의 어린 나는 가슴 속에 나침반이 하나 있었다. 그래서 어디로 가야 할지 모를 때 가슴 속의 나침반이 나의 길로 나를 이끌었다. 언제부터인가 나는 돈에 집착하기 시작했고 가슴 속의 나침반은 어이상 작동하지 않았다. 몸에 쇳가루가 많이 묻으면 나침반은 돌지 않는법. 나의 순결한 나침반이 우울증을 앓던 날 나는 그렇게 나의 길을 잃었다. \n" +
//                        "박광수, <참 서툰 사람들>",
//                isPrivate,
//                "https://hackathon-node.s3.ap-northeast-2.amazonaws.com/%EB%8F%85%EC%84%9C.png"
//            )
//
////            Log.d("log", data.toString())
//            api.post_posting(data).enqueue(object : Callback<PostResult> {
//                override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
//                    Log.d("log", data.toString())
//                    Log.d("log", response.code().toString())
//                    Log.d("log", response.body().toString())
//
//                }
//
//                override fun onFailure(call: Call<PostResult>, t: Throwable) {
//                    // 실패
//                    Log.d("err",t.message.toString())
//                    Log.d("err","fail")
//                }
//            })
//        }

    }

    private fun setViews() {
        //카메라 버튼을 클릭하면
        binding.btnCamera.setOnClickListener {
            //카메라 열기
            openCamera()
            binding.btnCamera.visibility = View.INVISIBLE
        }
    }

    private fun openCamera() {
        //카메라 권한 확인
        if(checkPermission(CAMERA_PERMISSION,PERM_CAMERA)){
            //권한이 있으면 카메라를 실행시킵니다.
            val intent: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent,FLAG_REQ_CAMERA)
        }
    }

    //권한 체크 메소드
    fun checkPermission(permissions:Array<out String>,flag:Int):Boolean{
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            for(permission in permissions){
                //만약 권한이 승인되어 있지 않다면 권한승인 요청을 사용에 화면에 호출합니다.
                if(ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this,permissions,flag)
                    return false
                }
            }
        }
        return true
    }

    //checkPermission() 에서 ActivityCompat.requestPermissions 을 호출한 다음 사용자가 권한 허용여부를 선택하면 해당 메소드로 값이 전달 됩니다.
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            PERM_STORAGE ->{
                for(grant in grantResults){
                    if(grant != PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this,"저장소 권한을 승인하지않으면 앱을 실행할수없습니다", Toast.LENGTH_SHORT).show()
                        finish()
                        return
                    }
                }
                //카메라 호출 메소드
                setViews()
            }
            PERM_CAMERA ->{
                for(grant in grantResults){
                    if(grant != PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this,"카메라 권한을 승인해야지만 카메라를 사용할 수 있습니다.", Toast.LENGTH_SHORT).show()
                        return
                    }
                }
                openCamera()
            }
        }
    }

    //startActivityForResult 을 사용한 다음 돌아오는 결과값을 해당 메소드로 호출합니다.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                FLAG_REQ_CAMERA ->{
                    if(data?.extras?.get("data") != null){
                        //카메라로 방금 촬영한 이미지를 미리 만들어 놓은 이미지뷰로 전달 합니다.
                        val bitmap = data?.extras?.get("data") as Bitmap
                        binding.imageView2.setImageBitmap(bitmap)
                    }
                }
            }
        }
    }



}
