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
                Toast.makeText(context, "??????????????? ?????? ??????", Toast.LENGTH_SHORT).show()
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
                    "??????",
                    "??????",
                    "????????????",
                    "????????? ??????.. ??????",
                    0,
                    "2022/09/25",
                    "#?????? #?????? #?????????"
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
                    "??????",
                    "??????",
                    "????????????",
                    "?????? ?????????",
                    0,
                    "2022/09/24",
                    "#?????? #?????? #?????????"
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
                    "??????",
                    "??????",
                    "????????????",
                    "??????????????? ?????????",
                    0,
                    "2022/09/23",
                    "#?????? #?????? #?????????"
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
                    "??????",
                    "??????",
                    "????????????",
                    "?????? ?????????",
                    0,
                    "2022/09/22",
                    "#?????? #?????? #?????????"
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
                    "??????",
                    "??????",
                    "????????????",
                    "IOS ?????????",
                    0,
                    "2022/09/21",
                    "#?????? #?????? #?????????"
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
                    "??????",
                    "??????",
                    "????????????",
                    "??? ?????????",
                    0,
                    "2022/09/20",
                    "#?????? #?????? #?????????"
                )
            )
        }
    }

}