package com.example.testapp


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class MainViewModel constructor(private val MainFactory: MainRepository): ViewModel() {

    val errorMessage = MutableLiveData<String>()
    var mService: UserService = Common.retrofitService
    var job: Job? = null
    val userList = MutableLiveData<MutableList<UserModel>>()
    val loading = MutableLiveData<Boolean>()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: \${throwable.localizedMessage}")
    }



    fun getAllUserList() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val result = mService.getUserInfo()
            if (result.isSuccessful) {
                CoroutineScope(Dispatchers.Main).launch {
                    userList.postValue(result.body())
                    loading.value = false

//                    adapter = RecyclerAdapter(result.body() as MutableList<UserModel>) { id ->
//                        val intent = Intent(this@MainActivity, DetailsActivity::class.java)
//                        intent.putExtra("id", id)
//                        startActivity(intent)
//
//                    }
//                    adapter.notifyDataSetChanged()
//                    binding.recyclerView.adapter = adapter
                }
            } else {
                onError("Error : ${result.message()}")
                //Log.e("Error", result.errorBody().toString())
            }
        }

    }

    private fun onError(message: String){
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


}