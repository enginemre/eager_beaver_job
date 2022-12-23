package com.engin.eagerbeaver.presentation

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.engin.eagerbeaver.R
import com.engin.eagerbeaver.common.domain.preferences.Preferences
import com.engin.eagerbeaver.databinding.ActivitySplashBinding
import com.engin.eagerbeaver.presentation.auth.AuthActivity
import com.engin.eagerbeaver.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySplashBinding

    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launchWhenStarted {
            delay(1500L)
            checkLogin()
        }
    }

    private fun checkLogin(){
        if(preferences.isLogin()){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
            finish()
        }else{
            val intent = Intent(this,AuthActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
            finish()
        }

    }
}