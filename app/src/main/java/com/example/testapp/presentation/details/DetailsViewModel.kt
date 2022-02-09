package com.example.testapp.presentation.details


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.domain.UserModel
import com.example.testapp.data.details.DetailsRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailsViewModel constructor(private val detailsRepository: DetailsRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    var job: Job? = null
    val user = MutableLiveData<UserModel>()
    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        onError("Exception handled: \${throwable.localizedMessage}")
    }

    fun getUserDetails(id: String) {

        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val result = detailsRepository.getOneUserInformation(id)
            if (result.isSuccessful) {
                user.postValue(result.body())
                loading.postValue(false)
            } else {
                onError("Error : ${result.message()}")
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}