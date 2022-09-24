package com.team1.projectteam1.presentation.home

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.team1.projectteam1.databinding.FragmentHomeBinding
import com.team1.projectteam1.presentation.MainViewModel
import com.team1.projectteam1.presentation.home.adapter.CalendarAdapter
import com.team1.projectteam1.presentation.home.post.PostActivity
import com.team1.projectteam1.presentation.home.profile.ProfileActivity
import com.team1.projectteam1.util.calculateCurrentMonthDayCount
import com.team1.projectteam1.util.calculateCurrentMonthStartDay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var calendarAdapter: CalendarAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel.currentYear = mainViewModel.currentYear
        homeViewModel.currentMonth = mainViewModel.currentMonth
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView() {
        setProfileButton()
        setPostButton()
        setDate()
        setCalendar()
        setCalendarRecyclerView()
        observeData()
    }

    private fun setProfileButton() { // 추후 이벤트를 감지해서 액티비티 이동하는 것으로 수정하기
        binding.btnCreateProfile.setOnClickListener {
            startActivity(Intent(requireContext(), ProfileActivity::class.java))
        }
    }

    private fun setPostButton() {
        binding.btnPost.setOnClickListener {
            startActivity(Intent(requireContext(), PostActivity::class.java))
        }
    }

    private fun setDate() {
        binding.tvDate.text = homeViewModel.getDateString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setCalendar() {
        homeViewModel.dayCount = calculateCurrentMonthDayCount(
            homeViewModel.currentYear,
            homeViewModel.currentMonth
        )
        homeViewModel.startDay = calculateCurrentMonthStartDay(
            homeViewModel.currentYear,
            homeViewModel.currentMonth
        )

        // 이전 달 몇일까지 있는지 구하기 위함
        if (homeViewModel.currentMonth == 1) {
            homeViewModel.preMonthDayCount = calculateCurrentMonthDayCount(
                homeViewModel.currentYear - 1,
                12
            )
        } else {
            homeViewModel.preMonthDayCount = calculateCurrentMonthDayCount(
                homeViewModel.currentYear,
                homeViewModel.currentMonth - 1
            )
        }

        // 네트워크 통신 예정
        homeViewModel.setCalendar()
    }

    private fun setCalendarRecyclerView() {
        calendarAdapter = CalendarAdapter()
        binding.rvCalendar.apply {
            adapter = calendarAdapter
            layoutManager = GridLayoutManager(requireContext(), 7)
            itemAnimator = null
        }
    }

    private fun observeData() {
        observeCalendar()
    }

    private fun observeCalendar() {
        homeViewModel.calendarDataListFlow.flowWithLifecycle(lifecycle).onEach {
           calendarAdapter.submitList(it)
        }.launchIn(lifecycleScope)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}