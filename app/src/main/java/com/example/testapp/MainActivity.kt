package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.Common.retrofitService
import com.example.testapp.databinding.ActivityMainBinding
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mService: UserService
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: RecyclerAdapter
    var job: Job? = null
    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofitService = Common.retrofitService
        val mainRepository = MainRepository(retrofitService)
        vm = ViewModelProvider(this,  MainViewModelFactory(mainRepository)).get(MainViewModel::class.java)

        mService = Common.retrofitService
        binding.recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager


        vm.userList.observe(this) {
            val result = mService.getUserInfo()
            adapter = RecyclerAdapter(result.body() as MutableList<UserModel>) { id ->
                val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)

            }
            adapter.notifyDataSetChanged()
            binding.recyclerView.adapter = adapter
        }


        vm.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        vm.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })

        vm.getAllUserList()


        //getAllUserList()

    }



//    private fun getAllUserList() {
//        job = CoroutineScope(Dispatchers.IO).launch {
//            val result = mService.getUserInfo()
//            if (result.isSuccessful) {
//                CoroutineScope(Dispatchers.Main).launch {
//                    adapter = RecyclerAdapter(result.body() as MutableList<UserModel>) { id ->
//                        val intent = Intent(this@MainActivity, DetailsActivity::class.java)
//                        intent.putExtra("id", id)
//                        startActivity(intent)
//
//                    }
//                    adapter.notifyDataSetChanged()
//                    binding.recyclerView.adapter = adapter
//                }
//            } else {
//                Log.e("Error", result.errorBody().toString())
//            }
//        }
//
//    }

}