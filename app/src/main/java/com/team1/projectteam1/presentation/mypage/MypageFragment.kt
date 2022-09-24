package com.team1.projectteam1.presentation.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.team1.projectteam1.R
import com.team1.projectteam1.databinding.FragmentHomeBinding
import com.team1.projectteam1.databinding.FragmentMypageBinding
import com.team1.projectteam1.domain.model.Write

class MypageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding!!

    private var writeList = ArrayList<Write>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
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
    }

    private fun addDummy() {
        writeList.apply {
            add(
                Write(
                    "2022-09-02",
                    "#시 #에세이",
                    8,
                    null,
                    "첫번째 더미"
                )
            )
            add(
                Write(
                    "2022-09-08",
                    "#시 #에세이",
                    9,
                    null,
                    "두번째 더미"
                )
            )
            add(
                Write(
                    "2022-09-14",
                    "#시 #에세이",
                    10,
                    null,
                    "세번째 더미"
                )
            )
            add(
                Write(
                    "2022-09-22",
                    "#시 #에세이",
                    8,
                    null,
                    "네번째 더미"
                )
            )
            add(
                Write(
                    "2022-09-25",
                    "#시 #에세이",
                    8,
                    null,
                    "졸리다"
                )
            )
        }
    }

}