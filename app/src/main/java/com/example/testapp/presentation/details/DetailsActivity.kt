package com.example.testapp.presentation.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.testapp.data.Common
import com.example.testapp.data.details.DetailsRepository
import com.example.testapp.databinding.ActivityDetailsBinding


class DetailsActivity : AppCompatActivity() {


    private lateinit var binding: ActivityDetailsBinding
    private lateinit var vm: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitService = Common.retrofitService
        val detailsRepository = DetailsRepository(retrofitService)
        vm = ViewModelProvider(
            this,
            DetailsViewModelFactory(detailsRepository)
        ).get(DetailsViewModel::class.java)

        val bundle: String = intent.getStringExtra("id").toString()
        bundle?.let {
            vm.getUserDetails(it)
        }

        vm.user.observe(this) {
            Glide.with(this@DetailsActivity).load(it.avatar_url)
                .into(binding.ivAvatar)
            binding.tvLogin.text = it.login
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
    }
}





