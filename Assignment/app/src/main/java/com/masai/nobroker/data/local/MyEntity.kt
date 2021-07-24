package com.masai.nobroker.data.local

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * This is the structure of our table in the database
 */
@Entity(tableName = "my_post_table")
data class MyEntity(
    @ColumnInfo(name = "image") val image: Bitmap,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "subTitle") val subTitle: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}