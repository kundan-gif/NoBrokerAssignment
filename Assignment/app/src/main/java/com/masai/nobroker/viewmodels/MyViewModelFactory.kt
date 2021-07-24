package com.masai.nobroker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.masai.nobroker.repository.MyRepository

class MyViewModelFactory(private val myRepository: MyRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyViewModel(myRepository) as T
    }
}