package com.masai.nobroker.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import com.masai.nobroker.data.local.MyDao
import com.masai.nobroker.data.local.MyEntity
import com.masai.nobroker.data.remote.ApiServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.net.URL


/**
 * This is Repository class which is responsible for interacting with the database
 */
class MyRepository(private val myDao: MyDao) {


    val api = ApiServices.instance

    /**
     * This function will insert all the items in the Room Database
     */
    suspend fun insertPosts() {
        val result = api.getPost()
        for (i in 0 until result.size) {
            val myEntity =
                MyEntity(gettingBitmap(result[i].image), result[i].title, result[i].subTitle)
            myDao.insertPosts(myEntity)
        }

    }

    /**
     * This function will return all the items from the Database
     */
    fun getPosts(): LiveData<List<MyEntity>> {
        return myDao.getPosts()
    }

    /**
     * This function will convert url to Bitmap
     */
    private fun gettingBitmap(url: String): Bitmap {
        val mUrl = URL(url)
        val image: Bitmap = BitmapFactory.decodeStream(mUrl.openConnection().getInputStream())
        return image
    }

    /**
     * This function will return  the total number of row from database table
     */
    fun getCount(): Int {

        return myDao.count()

    }

    /**
     * This function will will return the row of items based on the query string from the database
     */
    fun searchDatabase(searchQuery: String): Flow<List<MyEntity>> {
        return myDao.searchDatabase(searchQuery)
    }
}