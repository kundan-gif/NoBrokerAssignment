package com.masai.nobroker.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.masai.nobroker.data.local.MyEntity
import com.masai.nobroker.repository.MyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MyViewModel(
    private val repository: MyRepository
) : ViewModel() {

    fun insertPosts(){

        CoroutineScope(Dispatchers.IO).launch {
            repository.insertPosts()
        }
    }
    fun getPosts(): LiveData<List<MyEntity>> {
        return repository.getPosts()
    }

    fun getCount():Int{
        return repository.getCount()
    }
    fun searchDatabase(searchQuery: String): LiveData<List<MyEntity>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }
}