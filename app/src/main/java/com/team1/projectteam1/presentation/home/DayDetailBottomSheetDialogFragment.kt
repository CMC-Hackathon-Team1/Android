package com.team1.projectteam1.presentation.home

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.team1.projectteam1.R
import com.team1.projectteam1.databinding.DialogFragmentDayDetailBinding
import com.team1.projectteam1.presentation.home.adapter.DayDetailAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DayDetailBottomSheetDialogFragment: BottomSheetDialogFragment() {

    private var _binding: DialogFragmentDayDetailBinding? = null
    private val binding: DialogFragmentDayDetailBinding get() = checkNotNull(_binding)

    private val homeViewModel: HomeViewModel by activityViewModels()

    private lateinit var dayDetailAdapter: DayDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            setupRatio(bottomSheetDialog)
        }
        return dialog
    }

    private fun setupRatio(bottomSheetDialog: BottomSheetDialog) {
        //id = com.google.android.material.R.id.design_bottom_sheet for Material Components
        //id = android.support.design.R.id.design_bottom_sheet for support librares

        val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as View
        val behavior = BottomSheetBehavior.from(bottomSheet)
        val layoutParams = bottomSheet!!.layoutParams
        layoutParams.height = getBottomSheetDialogDefaultHeight()
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun getBottomSheetDialogDefaultHeight(): Int {
        return getWindowHeight() * 600 / 700
    }

    private fun getWindowHeight(): Int {
        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics) // deprecated 수정하기
        return displayMetrics.heightPixels
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogFragmentDayDetailBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this.viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerview()
        observeData()
        setCloseBtn()
    }

    private fun setCloseBtn() {
        binding.ivClose.setOnClickListener {
            dismiss()
        }
    }

    private fun setRecyclerview() {
        dayDetailAdapter = DayDetailAdapter()
        binding.rvDayDetail.apply {
            adapter = dayDetailAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeData() {
        homeViewModel.dayDetailListFlow.flowWithLifecycle(lifecycle).onEach {
            dayDetailAdapter.submitList(it.toList())
        }.launchIn(lifecycleScope)
    }
}