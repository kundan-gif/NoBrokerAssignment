package com.masai.nobroker.repository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import com.masai.nobroker.data.local.MyDao
import com.masai.nobroker.data.local.MyEntity
import com.masai.nobroker.data.remote.ApiServices
import java.net.URL

class MyRepository(private val context: Context, private val myDao: MyDao) {
    val api= ApiServices.instance
   suspend fun insertPosts(){
      val result=api.getPost()
       for (i in 0 until result.size) {
           val myEntity = MyEntity(gettingBitmap(result[i].image), result[i].title, result[i].subTitle)
           myDao.insertPosts(myEntity)
       }

   }
    fun getPosts(): LiveData<List<MyEntity>> {
        return myDao.getPosts()
    }
    fun gettingBitmap(url:String):Bitmap{
        val mUrl=URL(url)
        val image:Bitmap=BitmapFactory.decodeStream(mUrl.openConnection().getInputStream())
        return image
    }
    fun getCount():Int{
        return myDao.count()
    }
}