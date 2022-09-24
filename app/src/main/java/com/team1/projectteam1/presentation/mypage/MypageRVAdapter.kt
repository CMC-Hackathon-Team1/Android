package com.team1.projectteam1.presentation.mypage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.team1.projectteam1.databinding.ItemMypageRvBinding
import com.team1.projectteam1.domain.model.Write

class MypageRVAdapter(private val writeList : ArrayList<Write>) : RecyclerView.Adapter<MypageRVAdapter.ViewHolder>() {

    interface ItemClickListener {
        fun onItemClick(write: Write)
    }
    private lateinit var mItemClickListener : ItemClickListener

    fun setMyItemClickListener (itemClickListener : ItemClickListener){
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : ItemMypageRvBinding = ItemMypageRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(writeList[position])
//        holder.itemView.setOnClickListener {
//            mItemClickListener.onItemClick(writeList[position])
//        }
    }

    override fun getItemCount(): Int = writeList.size

    inner class ViewHolder(val binding : ItemMypageRvBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(write : Write){
            binding.itemMypageRvDateTv.text = write.date
            binding.itemMypageRvCategoryTv.text = write.category
            binding.itemMypageRvLikeCntTv.text = write.like.toString()
            if (write.img!=null) binding.itemMypageRvContentImgIv.setImageResource(write.img!!)
            else binding.itemMypageRvContentImgIv.visibility = View.GONE
            binding.itemMypageRvContentTextTv.text =write.text
        }
    }
}