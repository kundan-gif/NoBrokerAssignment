package com.masai.nobroker.views

import android.app.Application
import com.masai.nobroker.data.local.MyDatabase
import com.masai.nobroker.repository.MyRepository

class ApplicationClass:Application() {
    val myDao by lazy {
        val roomDatabase=MyDatabase.getDatabase(this)
        roomDatabase.getMyDao()
    }
    val repository:MyRepository by lazy {
        MyRepository(myDao)
    }

}