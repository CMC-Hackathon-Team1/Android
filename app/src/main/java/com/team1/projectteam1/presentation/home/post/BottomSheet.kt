package com.team1.projectteam1.presentation.home.post

import android.graphics.drawable.DrawableContainer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.team1.projectteam1.R

class BottomSheet : BottomSheetDialogFragment() {

    private val postViewModel: PostViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category,container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn1 = getView()?.findViewById<Button>(R.id.textView)
        val btn2 = getView()?.findViewById<Button>(R.id.textView2)
        val btn3 = getView()?.findViewById<Button>(R.id.textView3)
        val btn4 = getView()?.findViewById<Button>(R.id.textView4)

        btn1?.setOnClickListener {
            postViewModel.selectedCategory.value = "문화/예술"
            dismiss()
        }
        btn2?.setOnClickListener {
            postViewModel.selectedCategory.value = "스포츠"
            dismiss()
        }
        btn3?.setOnClickListener {
            postViewModel.selectedCategory.value = "자기계발"
            dismiss()
        }
        btn4?.setOnClickListener {
            postViewModel.selectedCategory.value = "기타"
            dismiss()
        }

    }

}