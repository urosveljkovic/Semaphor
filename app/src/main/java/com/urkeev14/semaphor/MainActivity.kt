package com.urkeev14.semaphor

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.urkeev14.semaphor.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    @SuppressLint("ResourceAsColor")
    override fun onResume() {
        super.onResume()
        viewModel.activeLight.observe(this, {
            when (it.color) {
                R.color.red -> {
                    binding.light1.setCardBackgroundColor(ContextCompat.getColor(this, it.color))
                    binding.light2.setCardBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray))
                    binding.light3.setCardBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray))
                }
                R.color.yellow -> {
                    binding.light1.setCardBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray))
                    binding.light2.setCardBackgroundColor(ContextCompat.getColor(this, it.color))
                    binding.light3.setCardBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray))
                }
                else -> {
                    binding.light1.setCardBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray))
                    binding.light2.setCardBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray))
                    binding.light3.setCardBackgroundColor(ContextCompat.getColor(this, it.color))
                }
            }
        })
    }
}