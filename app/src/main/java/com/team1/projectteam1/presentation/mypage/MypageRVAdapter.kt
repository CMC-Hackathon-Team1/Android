package com.team1.projectteam1.presentation.mypage

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.team1.projectteam1.databinding.ItemMypageRvBinding
import com.team1.projectteam1.domain.model.Write
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class MypageRVAdapter(private val writeList : ArrayList<GetMypageResult>) : RecyclerView.Adapter<MypageRVAdapter.ViewHolder>() {

    lateinit var bitmap : Bitmap

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
        fun bind(write : GetMypageResult){
            binding.itemMypageRvDateTv.text = write.createdAt
            binding.itemMypageRvCategoryTv.text = write.hashTagStr
            binding.itemMypageRvLikeCntTv.text = write.likeCount
            if (write.profileImgUrl == "") binding.itemMypageRvContentImgIv.visibility = View.GONE
//            if (write.profileImgUrl != null) getImg(write.profileImgUrl, binding.itemMypageRvContentImgIv)
//            else binding.itemMypageRvContentImgIv.visibility = View.GONE
//            if (write.img!=null) binding.itemMypageRvContentImgIv.setImageResource(write.img!!)
//            else binding.itemMypageRvContentImgIv.visibility = View.GONE
            binding.itemMypageRvContentTextTv.text =write.content
        }
    }

    private fun getImg(url : String, imgview : ImageView) {
        val uThread = Thread()
        uThread.run(){
            try {
                val url = URL(url)

                val conn = url.openConnection() as HttpURLConnection
                conn.doInput = true
                conn.connect() //연결된 곳에 접속할 때

                val link : InputStream = conn.inputStream
                bitmap = BitmapFactory.decodeStream(link)
            }catch (e : MalformedURLException){
                e.printStackTrace()
            } catch (e : IOException){
                e.printStackTrace()
            }
        }

        uThread.start()

        try {
            uThread.join()
            imgview.setImageBitmap(bitmap)
        } catch (e : InterruptedException) {
            e.printStackTrace()
        }
    }
}