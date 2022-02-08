package com.example.testapp

import android.util.Log
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.testapp.databinding.ActivityDetailsBinding
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailsViewModel: ViewModel() {

    override fun onCleared() {
        super.onCleared()
    }
}