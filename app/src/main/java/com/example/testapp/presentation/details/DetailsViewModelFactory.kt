package com.example.testapp.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testapp.data.details.DetailsRepository

class DetailsViewModelFactory constructor(private val repository: DetailsRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DetailsViewModel::class.java)){
            DetailsViewModel(this.repository) as T
        } else {
            throw IllegalAccessException("ViewModel Not Found")
        }
    }

}
