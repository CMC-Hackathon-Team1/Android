package com.team1.projectteam1.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.team1.projectteam1.databinding.ItemDayDetailBinding
import com.team1.projectteam1.domain.model.DayDetail

class DayDetailAdapter :
    ListAdapter<DayDetail, DayDetailAdapter.DayDetailViewHolder>(DayDetailDiffUtil) {

    inner class DayDetailViewHolder(val binding: ItemDayDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dayDetail: DayDetail) { // databinding 수정하기
            binding.tvDate.text = dayDetail.createdAt
            binding.tvName.text = dayDetail.personaName
            binding.tvLikecount.text = dayDetail.likeCount
            binding.tvHashTag.text = "#문화/예술  #에세이  #책  #작가" // 더미
            binding.tvContent.text = dayDetail.content

            Glide.with(binding.root.context)
                .load(dayDetail.profileImgUrl)
                .into(binding.sivProfile)

            Glide.with(binding.root.context)
                .load(dayDetail.ImgUrl)
                .into(binding.ivPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayDetailViewHolder {
        val binding =
            ItemDayDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return DayDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DayDetailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DayDetailDiffUtil : DiffUtil.ItemCallback<DayDetail>() {
        override fun areItemsTheSame(oldItem: DayDetail, newItem: DayDetail): Boolean {
            return oldItem.feedId == newItem.feedId
        }

        override fun areContentsTheSame(oldItem: DayDetail, newItem: DayDetail) =
            oldItem == newItem
    }

}