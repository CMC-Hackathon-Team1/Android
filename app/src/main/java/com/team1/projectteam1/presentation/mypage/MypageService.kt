package com.team1.projectteam1.presentation.mypage

import android.util.Log
import com.team1.projectteam1.domain.data.MypageRetroInterface
import com.team1.projectteam1.domain.model.ApiClient.getRetrofit
import retrofit2.Call
import retrofit2.Response

class MypageService {

    private lateinit var mypageView : MypageView

    fun setMypageView(mypageView: MypageView){
        this.mypageView = mypageView
    }

    fun getMyPage(userIdx : Int, year : Int, month : Int){
        val myPageService = getRetrofit().create(MypageRetroInterface::class.java)

        myPageService.getMyPage(userIdx, year, month).enqueue(object : retrofit2.Callback<GetMypageResponse> {
            override fun onResponse(
                call: Call<GetMypageResponse>,
                response: Response<GetMypageResponse>
            ) {
                Log.d("GetMyPage/SUCCESS", response.toString())
                val resp : GetMypageResponse = response.body()!!

                when(val code=resp.code) {
                    200 -> Log.d("GetMyPageResponse_200", response.errorBody()?.string()!!)
                    1000 -> mypageView.onGetMypageSuccess(code, resp.result!!)
                }
            }

            override fun onFailure(call: Call<GetMypageResponse>, t: Throwable) {
                Log.d("GetMyPage/FAILURE",t.message.toString())
            }
        })
        Log.d("GetMyPage","finish")
    }

}