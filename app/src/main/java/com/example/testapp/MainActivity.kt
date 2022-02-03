package com.example.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mService: UserService
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: RecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mService =  Common.retrofitService
        binding.recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        getAllUserList()

    }


   private fun getAllUserList() {

      mService.getUserInfo().enqueue(object : Callback<MutableList<UserModel>> {
          override fun onResponse(
              call: Call<MutableList<UserModel>>,
              response: Response<MutableList<UserModel>>
          ) {
              adapter = RecyclerAdapter(baseContext, response.body() as MutableList<UserModel>)
              adapter.notifyDataSetChanged()
              binding.recyclerView.adapter = adapter
          }

          override fun onFailure(call: Call<MutableList<UserModel>>, t: Throwable) {}
      })

    }
}