package com.engin.eagerbeaver.presentation.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.engin.eagerbeaver.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Binding view
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}