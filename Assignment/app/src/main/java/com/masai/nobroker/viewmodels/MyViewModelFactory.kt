package com.masai.nobroker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.masai.nobroker.repository.MyRepository
/**
 * This class is responsible for creating object of viewModel
 */
class MyViewModelFactory(private val myRepository: MyRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyViewModel(myRepository) as T
    }
}