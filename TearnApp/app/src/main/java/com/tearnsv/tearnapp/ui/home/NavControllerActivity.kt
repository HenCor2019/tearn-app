package com.tearnsv.tearnapp.ui.home

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavHost
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tearnsv.tearnapp.R


class NavControllerActivity : AppCompatActivity() {

  private lateinit var bottomNav: BottomNavigationView

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
      when (it.itemId) {
        R.id.page_1 -> {
          navController.navigate(R.id.searchFragment)
          true
        }
        R.id.page_2 -> {
          navController.navigate(R.id.chatsFragment)
          true
        }
        R.id.page_3 -> {
          navController.navigate(R.id.homeFragment)
          true
        }
        R.id.page_4 -> {
          navController.navigate(R.id.accountFragment)
          true
        }
        else -> true
      }
    }
  }

  fun bind() {
    bottomNav = findViewById(R.id.bottom_navigation)
  }

  /*
  override fun onConfigurationChanged(newConfig: Configuration) {
    super.onConfigurationChanged(newConfig)
    val orientation = resources.configuration.orientation
    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
      Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show()
      val navigation = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
      navigation.rotation = 90f
      navigation.layoutParams.width = 2000
      navigation.requestLayout()
      navigation.y = 1600f
      navigation.x = -1200f
      navigation.requestLayout();
      val menuView = navigation.getChildAt(0) as BottomNavigationMenuView
      for (i in 0 until menuView.childCount) {
        val iconView: View = menuView.getChildAt(i)
        iconView.rotation = -90f
      }
    } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
      recreate()
    }
}

   */


}