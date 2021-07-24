package com.masai.nobroker.data.remote

import com.masai.nobroker.data.models.MyResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


const val BASE_URL="https://gist.githubusercontent.com/"

interface ApiInterface{

    @GET("shivarajp/2cbe00030c04472c9d8ad4b0ec112dbe/raw/c651391e428182f08d60d59e79073f3fcf79b858/nobroker")
     suspend fun getPost() : MyResponse
}
object ApiServices {
    val instance:ApiInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        instance = retrofit.create(ApiInterface::class.java)

    }
}