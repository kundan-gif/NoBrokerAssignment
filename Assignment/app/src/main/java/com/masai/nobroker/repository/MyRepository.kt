package com.masai.nobroker.repository

import androidx.lifecycle.LiveData
import com.masai.nobroker.data.local.MyDao
import com.masai.nobroker.data.local.MyEntity
import com.masai.nobroker.data.remote.ApiServices

class MyRepository(val myDao: MyDao) {
    val api= ApiServices.instance
   suspend fun insertPosts(){
      val result=api.getPost()
       for (i in 0..result.size) {
           val myEntity = MyEntity(result[i].image, result[i].title, result[i].subTitle)
           myDao.insertPosts(myEntity)
       }

   }
    fun getPosts(): LiveData<List<MyEntity>> {
        return myDao.getPosts()
    }
}