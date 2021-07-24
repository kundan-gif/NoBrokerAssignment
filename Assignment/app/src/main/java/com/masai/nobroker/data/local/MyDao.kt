package com.masai.nobroker.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MyDao {

    /**
     * This function will insert all the items in the Database
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPosts(myEntity: MyEntity)

    /**
     * This function will return all the items from the Database
     */
    @Query("select * from my_post_table")
    fun getPosts(): LiveData<List<MyEntity>>

    /**
     * This function will return  the total number of row from database table
     */
    @Query("SELECT COUNT(*) FROM my_post_table")
    fun count(): Int

    /**
     * This function will will return the row of items based on the query string from the database
     */
    @Query("SELECT * FROM my_post_table WHERE title LIKE :searchQuery OR subTitle LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<MyEntity>>
}