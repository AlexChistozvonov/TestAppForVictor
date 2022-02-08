package com.example.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.testapp.databinding.ActivityDetailsBinding
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class DetailsActivity : AppCompatActivity() {

    private lateinit var mService: UserService
    private lateinit var binding: ActivityDetailsBinding
    var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mService = Common.retrofitService
        getUserDetails()
    }

    private fun getUserDetails() {

        job = CoroutineScope(Dispatchers.IO).launch {
            val bundle: String = intent.getStringExtra("id").toString()
            bundle?.let {
                val result = mService.getUser(it)
                if (result.isSuccessful) {
                    CoroutineScope(Dispatchers.Main).launch {
                        Glide.with(this@DetailsActivity).load(result.body()?.avatar_url)
                            .into(iv_avatar)
                        tv_login.text = result.body()?.login
                    }
                } else{
                    Log.e("Error", result.errorBody().toString())}
            }
        }}

//    private fun getUserDetails() {
//
//        job = CoroutineScope(Dispatchers.IO).launch {
//            val bundle: String = intent.getStringExtra("id").toString()
//            bundle?.let {
//                val result = mService.getUser(it)
//                if (result.isSuccessful) {
//                    CoroutineScope(Dispatchers.Main).launch {
//                        Glide.with(this@DetailsActivity).load(result.body()?.avatar_url)
//                            .into(iv_avatar)
//                        tv_login.text = result.body()?.login
//                    }
//                } else{
//                    Log.e("Error", result.errorBody().toString())}
//            }
//    }}
}





