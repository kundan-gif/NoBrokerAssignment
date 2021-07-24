package com.masai.nobroker.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPosts(myEntity: MyEntity)

    @Query("select * from my_post_table")
    fun getPosts(): LiveData<List<MyEntity>>
}