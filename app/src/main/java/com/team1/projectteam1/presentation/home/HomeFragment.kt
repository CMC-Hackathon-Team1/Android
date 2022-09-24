package com.team1.projectteam1.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.team1.projectteam1.R
import com.team1.projectteam1.databinding.FragmentHomeBinding
import com.team1.projectteam1.presentation.home.post.PostActivity
import com.team1.projectteam1.presentation.home.profile.ProfileActivity

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        setProfileButton()
        setPostButton()
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}