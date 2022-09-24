package com.team1.projectteam1.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team1.projectteam1.databinding.ItemMyPostBinding
import com.team1.projectteam1.domain.model.MyPostHome

class MyPostHomeAdapter :
    ListAdapter<MyPostHome, MyPostHomeAdapter.MyPostHomeViewHolder>(MyPostHomeDiffUtil) {

    inner class MyPostHomeViewHolder(val binding: ItemMyPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(myPostHome: MyPostHome) { // databinding 수정하기
            binding.tvImageMypost.text = myPostHome.imageText
            binding.tvMypost.text = "${myPostHome.hashtag} 관련 글을 ${myPostHome.count}개 올리셨어요!"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPostHomeViewHolder {
        val binding =
            ItemMyPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        var params = binding.root.layoutParams
        params.width = parent.measuredWidth / 3

        return MyPostHomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyPostHomeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object MyPostHomeDiffUtil : DiffUtil.ItemCallback<MyPostHome>() {
        override fun areItemsTheSame(oldItem: MyPostHome, newItem: MyPostHome): Boolean {
            return oldItem.hashtag == newItem.hashtag
        }

        override fun areContentsTheSame(oldItem: MyPostHome, newItem: MyPostHome) =
            oldItem == newItem
    }
}