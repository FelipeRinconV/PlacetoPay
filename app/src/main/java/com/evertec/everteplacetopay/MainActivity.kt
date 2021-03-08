package com.evertec.everteplacetopay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.evertec.everteplacetopay.databinding.MainActivityBinding
import com.evertec.everteplacetopay.ui.login.viewmodel.LoginViewModel
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //private val viewModel: LoginViewModel by viewModels()

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    override fun onStart() {
        super.onStart()
        navController = findNavController(R.id.nav_host_fragment)
     
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp()
    }

}