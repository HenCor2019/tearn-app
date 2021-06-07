package com.tearnsv.tearnapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavHost
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tearnsv.tearnapp.R

class NavControllerActivity : AppCompatActivity() {

    private lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_controller)
        supportActionBar?.hide()

        bind()

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_controller_fragment) as NavHost
        val navController = navHostFragment.navController

        bottomNav.selectedItemId = R.id.page_3

        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.page_1 ->{
                    navController.navigate(R.id.searchFragment)
                    true
                }
                R.id.page_2 ->{
                    navController.navigate(R.id.chatsFragment)
                    true
                }
                R.id.page_3 ->{
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.page_4 ->{
                    navController.navigate(R.id.accountFragment)
                    true
                }
                else -> true
            }
        }
    }

    fun bind(){
        bottomNav = findViewById(R.id.bottom_navigation)
    }

}