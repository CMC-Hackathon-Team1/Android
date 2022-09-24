package com.team1.projectteam1.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team1.projectteam1.databinding.ItemRelevantUserBinding
import com.team1.projectteam1.domain.model.RelevantUser

class RelevantUserAdapter :
    ListAdapter<RelevantUser, RelevantUserAdapter.RelevantUserViewHolder>(RelevantUserDiffUtil) {

    inner class RelevantUserViewHolder(val binding: ItemRelevantUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(relevantUser: RelevantUser, position: Int) { // databinding 수정하기
            binding.tvRelevantUser.text = relevantUser.name
            binding.viewLeft.isVisible = position == 0
            binding.viewRight.isVisible = position == currentList.size - 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelevantUserViewHolder {
        val binding =
            ItemRelevantUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RelevantUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RelevantUserViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    companion object RelevantUserDiffUtil : DiffUtil.ItemCallback<RelevantUser>() {
        override fun areItemsTheSame(oldItem: RelevantUser, newItem: RelevantUser): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: RelevantUser, newItem: RelevantUser) =
            oldItem == newItem
    }
}