package com.example.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.testapp.databinding.ActivityDetailsBinding
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.item_rv.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {

    private lateinit var mService: UserService
    private lateinit var binding: ActivityDetailsBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mService = Common.retrofitService
        getUserDetails()
    }

    private fun getUserDetails() {
        val bundle: String = intent.getStringExtra("id").toString()
        Log.e("Error", "First $bundle")
        bundle?.let {
            Log.e("Error", "Second")
            mService.getUser(it).enqueue(object : Callback<UserModel> {
                override fun onResponse(
                    call: Call<UserModel>,
                    response: Response<UserModel>
                ) {
                    Glide.with(this@DetailsActivity).load(response.body()?.avatar_url).into(iv_avatar)
                    tv_login.text = response.body()?.login
                }

                override fun onFailure(call: Call<UserModel>, t: Throwable) {}
            })
        }
    }
}




