package com.masai.nobroker.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [MyEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class MyDatabase : RoomDatabase() {


    /**
     * This function will used for initialize the Dao class
     */
    abstract fun getMyDao(): MyDao

    companion object {

        private var INSTANCE: MyDatabase? = null


        /**
         * This function return the database reference
         */
        fun getDatabase(context: Context): MyDatabase {
            if (INSTANCE == null) {
                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "my_database"
                )

                builder.fallbackToDestructiveMigration()
                INSTANCE = builder.build()

                return INSTANCE!!
            } else {
                return INSTANCE!!
            }
        }

    }
}