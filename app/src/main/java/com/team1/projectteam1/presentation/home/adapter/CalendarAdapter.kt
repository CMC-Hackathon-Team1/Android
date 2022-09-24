package com.team1.projectteam1.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team1.projectteam1.databinding.ItemCalendarBinding
import com.team1.projectteam1.domain.model.Calendar

class CalendarAdapter : ListAdapter<Calendar, CalendarAdapter.CalendarViewHolder>(
    CalendarDiffUtil
) {

    inner class CalendarViewHolder(val binding: ItemCalendarBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(calendar: Calendar) { // databinding 수정하기
            binding.calendar = calendar
            if (calendar.isCurrentMonth) {
                binding.tvDay.visibility = View.VISIBLE
            } else {
                binding.tvDay.visibility = View.INVISIBLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val binding =
            ItemCalendarBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CalendarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object CalendarDiffUtil : DiffUtil.ItemCallback<Calendar>() {
        override fun areItemsTheSame(oldItem: Calendar, newItem: Calendar): Boolean {
            return (oldItem.day == newItem.day) && (oldItem.isCurrentMonth == newItem.isCurrentMonth)
        }

        override fun areContentsTheSame(oldItem: Calendar, newItem: Calendar) =
            oldItem == newItem
    }
}