package com.masai.nobroker.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.masai.nobroker.R
import com.masai.nobroker.data.models.MyResponse
import com.masai.nobroker.data.remote.ApiServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callAPi()
    }
    private fun callAPi() {
        val posts= ApiServices.instance.getPost()
        posts.enqueue(object : Callback<MyResponse> {
            override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                val posts=response.body()
                if(posts!=null){
                    Log.d("tag",posts.toString())
                }
            }

            override fun onFailure(call: Call<MyResponse>, t: Throwable) {
                Log.d("tag","Api failed to call")
            }
        })
    }
}