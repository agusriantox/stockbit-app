package com.stockbit.app.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.stockbit.app.R
import com.stockbit.app.data.local.AuthRepository
import com.stockbit.app.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navController = findNavController(R.id.nav_host_fragment)

        /* Setup Bottom Navigation */
        val navViewBottom: BottomNavigationView = findViewById(R.id.nav_view_bottom)
        navViewBottom.setupWithNavController(navController)

        /* Setup Left Drawer Navigation */
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navViewDrawer: NavigationView = findViewById(R.id.nav_view_drawer)
        navViewDrawer.setupWithNavController(navController)
        navViewDrawer.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_logout -> {
                    AuthRepository.logout()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                else -> {
                    NavigationUI.onNavDestinationSelected(item, navController)
                }
            }
            return@setNavigationItemSelectedListener true
        }

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_watchlist,
                R.id.navigation_stream,
                R.id.navigation_search,
                R.id.navigation_chat,
                R.id.navigation_portfolio
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.appbar_main_menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}