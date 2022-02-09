package com.example.testapp.presentation.main


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.Common
import com.example.testapp.domain.UserModel
import com.example.testapp.domain.UserService
import com.example.testapp.data.main.MainRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    var mService: UserService = Common.retrofitService
    var job: Job? = null
    val userList = MutableLiveData<MutableList<UserModel>>()
    val loading = MutableLiveData<Boolean>()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: \${throwable.localizedMessage}")
    }

    fun getAllUserList() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val result = mainRepository.getAllInformation()
            if (result.isSuccessful) {
                userList.postValue(result.body())
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