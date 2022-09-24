package com.team1.projectteam1.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team1.projectteam1.databinding.ItemMyProfileAddBinding
import com.team1.projectteam1.databinding.ItemMyProfileBinding
import com.team1.projectteam1.domain.model.MyProfile

class MyProfileAdapter(private val addProfileClick: () -> Unit) :
    ListAdapter<MyProfile, RecyclerView.ViewHolder>(MyProfileDiffUtil) {

    inner class MyProfileViewHolder(val binding: ItemMyProfileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(myProfile: MyProfile, position: Int) { // databinding 수정하기
            binding.tvRelevantUser.text = myProfile.name
            binding.viewLeft.isVisible = position == 0
        }
    }

    inner class MyProfileAddViewHolder(val binding: ItemMyProfileAddBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(myProfile: MyProfile, addProfileClick: () -> Unit) {
            binding.viewRight.isVisible = true
            binding.root.setOnClickListener {
                addProfileClick()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            0 -> {
                val binding =
                    ItemMyProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)

                return MyProfileViewHolder(binding)
            }
            else -> {
                val binding =
                    ItemMyProfileAddBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )

                return MyProfileAddViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MyProfileViewHolder -> {
                holder.bind(getItem(position), position)
            }
            is MyProfileAddViewHolder -> {
                holder.bind(getItem(position), addProfileClick)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (currentList[position].isAddItem) return 1 // 프로필 추가 아이템
        else return 0 // 프로필 아이템
    }

    companion object MyProfileDiffUtil : DiffUtil.ItemCallback<MyProfile>() {
        override fun areItemsTheSame(oldItem: MyProfile, newItem: MyProfile): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: MyProfile, newItem: MyProfile) =
            oldItem == newItem
    }
}