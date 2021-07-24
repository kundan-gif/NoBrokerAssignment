package com.masai.nobroker.data.remote

import com.masai.nobroker.data.models.MyResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


const val BASE_URL="https://gist.githubusercontent.com/"


/**
 * The getPost method will return the Response which we are getting from Api
 */
interface ApiInterface{

    @GET("shivarajp/2cbe00030c04472c9d8ad4b0ec112dbe/raw/c651391e428182f08d60d59e79073f3fcf79b858/nobroker")
     suspend fun getPost() : MyResponse
}

/**
 * The ApiServices class is responsible for making the api call by using Retrofit
 */
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