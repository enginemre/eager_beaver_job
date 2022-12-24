package com.engin.eagerbeaver.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.engin.eagerbeaver.R
import com.engin.eagerbeaver.common.CustomSnackBar
import com.engin.eagerbeaver.common.SnackType
import com.engin.eagerbeaver.common.domain.model.UserRole
import com.engin.eagerbeaver.common.domain.preferences.Preferences
import com.engin.eagerbeaver.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var preferences: Preferences

    private lateinit var binding:ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var gso:GoogleSignInOptions
    private lateinit var gsc: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }


    private fun init(){
        val navHostFragment  = supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        navController = navHostFragment.navController
        // TODO for employee menu
        if(preferences.userType() == UserRole.EMPLOYEE){
            binding.bottomNavigationView.menu.clear()
            binding.bottomNavigationView.inflateMenu(R.menu.bottom_employee_menu)
            appBarConfiguration = AppBarConfiguration(setOf(R.id.searchFragment,R.id.advertsFragment,R.id.home_fragment))
        }else{
            appBarConfiguration = AppBarConfiguration(setOf(R.id.searchFragment,R.id.applicantFragment,R.id.profile_fragment,R.id.home_fragment))
        }
        setSupportActionBar(binding.toolbarLayout)
        setupActionBarWithNavController(navController,appBarConfiguration)
        binding.bottomNavigationView.setupWithNavController(navController)
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        gsc = GoogleSignIn.getClient(this,gso)
        val accnt = GoogleSignIn.getLastSignedInAccount(this)
        /*if(accnt != null){
            CustomSnackBar.make(applicationContext,binding.root,accnt.email.toString(), SnackType.WARNING).show()
        }*/
    }




    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)  || super.onSupportNavigateUp()
    }
}