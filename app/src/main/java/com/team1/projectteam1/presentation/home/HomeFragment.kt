package com.team1.projectteam1.presentation.home

import android.content.Intent
import android.os.Build
import android.os.Bundle
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.team1.projectteam1.databinding.FragmentHomeBinding
import com.team1.projectteam1.presentation.MainViewModel
import com.team1.projectteam1.presentation.home.adapter.CalendarAdapter
import com.team1.projectteam1.presentation.home.adapter.MyPostHomeAdapter
import com.team1.projectteam1.presentation.home.adapter.MyProfileAdapter
import com.team1.projectteam1.presentation.home.adapter.RelevantUserAdapter
import com.team1.projectteam1.presentation.home.post.PostActivity
import com.team1.projectteam1.presentation.home.profile.ProfileActivity
import com.team1.projectteam1.util.calculateCurrentMonthDayCount
import com.team1.projectteam1.util.calculateCurrentMonthStartDay
import com.team1.projectteam1.util.printLog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var myProfileAdapter: MyProfileAdapter
    private lateinit var calendarAdapter: CalendarAdapter
    private lateinit var myPostHomeAdapter: MyPostHomeAdapter
    private lateinit var relevantUserAdapter: RelevantUserAdapter

    private val datePicker =
        MaterialDatePicker.Builder.datePicker()
            .setTitleText("날짜를 선택하세요")
            .build()

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
        setPostButton()
        setDate()
        setCalendar()
        setDatePickerListener()
        setCalendarSpinnerClick()
        setMyProfileRecyclerView()
        setCalendarRecyclerView()
        setMyPostHomeRecyclerView()
        setRelevantUserRecyclerView()
        getData()
        observeData()
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setDatePickerListener() {
        datePicker.addOnPositiveButtonClickListener {
            val dateStr = convertLongToDate(it)
            val dateStrList = dateStr.split('-')

            homeViewModel.currentYear = dateStrList[0].toInt()
            homeViewModel.currentMonth = dateStrList[1].toInt()

            printLog("currentYear : ${homeViewModel.currentYear}, currentMonth : ${homeViewModel.currentMonth}")
            setDate()
            setCalendar()
        }
    }

    private fun convertLongToDate(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)

        return format.format(date)
    }

    private fun setCalendarSpinnerClick() {
        binding.tvDate.setOnClickListener {
            datePicker.show(childFragmentManager, "date")
        }
    }

    private fun setMyProfileRecyclerView() {
        myProfileAdapter = MyProfileAdapter {
            startActivity(Intent(requireContext(), ProfileActivity::class.java))
        }
        binding.rvMyProfile.apply {
            adapter = myProfileAdapter
            layoutManager = LinearLayoutManager(requireContext()).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
            }
        }

        myProfileAdapter.submitList(homeViewModel.getMyProfileDummy().toList())
    }

    private fun setCalendarRecyclerView() {
        calendarAdapter = CalendarAdapter()
        binding.rvCalendar.apply {
            adapter = calendarAdapter
            layoutManager = GridLayoutManager(requireContext(), 7)
            itemAnimator = null
        }
    }

    private fun setMyPostHomeRecyclerView() {
        myPostHomeAdapter = MyPostHomeAdapter()
        binding.rvMyPost.apply {
            adapter = myPostHomeAdapter
            layoutManager = LinearLayoutManager(requireContext()).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
            }
        }

        myPostHomeAdapter.submitList(homeViewModel.getMyPostHomeDummy().toList())
    }

    private fun setRelevantUserRecyclerView() {
        relevantUserAdapter = RelevantUserAdapter()
        binding.rvRelevantUser.apply {
            adapter = relevantUserAdapter
            layoutManager = LinearLayoutManager(requireContext()).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
            }
        }

        relevantUserAdapter.submitList(homeViewModel.getRelevantUserDummy().toList())
    }

    private fun getData() {
        homeViewModel.getStatistics()
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