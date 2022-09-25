package com.team1.projectteam1.presentation.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.team1.projectteam1.R
import com.team1.projectteam1.databinding.FragmentHomeBinding
import com.team1.projectteam1.databinding.FragmentMypageBinding
import com.team1.projectteam1.domain.model.Write
import com.team1.projectteam1.presentation.MainActivity
import com.team1.projectteam1.presentation.home.post.EditActivity

class MypageFragment : Fragment(), MypageView {
    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding!!

    private var writeList = ArrayList<GetMypageResult>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
//        getMyPageData()


        initRecyclerView()
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initRecyclerView(){
        addDummy()
        val mypageRVAdapter = MypageRVAdapter(writeList)
        binding.mypageWriteRv.adapter = mypageRVAdapter
        binding.mypageWriteRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        mypageRVAdapter.setMyItemClickListener(object : MypageRVAdapter.ItemClickListener {
            override fun onItemClick(resule: GetMypageResult) {
                val intent = Intent(context as MainActivity, EditActivity()::class.java)
                startActivity(intent)

            }
        })

    }

    private fun getMyPageData(){
        val myPageService = MypageService()
        myPageService.setMypageView(this)

        myPageService.getMyPage(1,2022,9)
    }

    override fun onGetMypageSuccess(code: Int, result: ArrayList<GetMypageResult>) {
        when(code){
            1000 -> {
                Log.d("GetMyPageData","in mypage fragment, success")
                Toast.makeText(context, "마이페이지 조회 완료", Toast.LENGTH_SHORT).show()
                writeList = result
                binding.mypageProfilNameTv.text = writeList.get(0).profileName
                initRecyclerView()
            }
        }
    }

    private fun addDummy() {
        writeList.apply {
            add(
                GetMypageResult(
                    1,
                    1,
                    "4",
                    1,
                    2,
                    "",
                    "으악",
                    "아크",
                    "디자이너",
                    "독서의 계절.. ㅎㅎ",
                    0,
                    "2022/09/25",
                    "#개발 #기획 #디자인"
                )
            )
            add(
                GetMypageResult(
                    2,
                    1,
                    "40",
                    1,
                    2,
                    "",
                    "으악",
                    "아크",
                    "디자이너",
                    "너무 졸려요",
                    0,
                    "2022/09/24",
                    "#개발 #기획 #디자인"
                )
            )
            add(
                GetMypageResult(
                    1,
                    1,
                    "12",
                    1,
                    2,
                    "",
                    "으악",
                    "아크",
                    "디자이너",
                    "안드로이드 화이팅",
                    0,
                    "2022/09/23",
                    "#개발 #기획 #디자인"
                )
            )
            add(
                GetMypageResult(
                    1,
                    1,
                    "8",
                    1,
                    2,
                    "",
                    "으악",
                    "아크",
                    "디자이너",
                    "서버 화이팅",
                    0,
                    "2022/09/22",
                    "#개발 #기획 #디자인"
                )
            )
            add(
                GetMypageResult(
                    1,
                    1,
                    "10",
                    1,
                    2,
                    "",
                    "으악",
                    "아크",
                    "디자이너",
                    "IOS 화이팅",
                    0,
                    "2022/09/21",
                    "#개발 #기획 #디자인"
                )
            )
            add(
                GetMypageResult(
                    1,
                    1,
                    "8",
                    1,
                    2,
                    "",
                    "으악",
                    "아크",
                    "디자이너",
                    "웹 화이팅",
                    0,
                    "2022/09/20",
                    "#개발 #기획 #디자인"
                )
            )
        }
    }

}