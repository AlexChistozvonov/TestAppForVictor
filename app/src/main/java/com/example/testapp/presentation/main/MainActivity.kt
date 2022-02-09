package com.example.testapp.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.*
import com.example.testapp.data.main.MainRepository
import com.example.testapp.data.main.MainViewModelFactory
import com.example.testapp.databinding.ActivityMainBinding
import com.example.testapp.domain.UserService
import com.example.testapp.presentation.details.DetailsActivity


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mService: UserService
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: RecyclerAdapter
    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofitService = Common.retrofitService
        val mainRepository = MainRepository(retrofitService)
        vm = ViewModelProvider(
            this,
            MainViewModelFactory(mainRepository)
        ).get(MainViewModel::class.java)

        mService = Common.retrofitService
        binding.recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        adapter = RecyclerAdapter(emptyList()) { id ->
            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }

        binding.recyclerView.adapter = adapter

        vm.userList.observe(this) {

            adapter.setUser(it)
            adapter.notifyDataSetChanged()

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

    }
}