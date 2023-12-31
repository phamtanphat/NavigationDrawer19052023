package com.example.navigationdrawer19052023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var toolBar: Toolbar
    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolBar = findViewById(R.id.toolbar)
        navigationView = findViewById(R.id.navigation_view)
        drawerLayout = findViewById(R.id.drawer_layout)

        setSupportActionBar(toolBar)
        toolBar.title = "Navigation drawer"
        toolBar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size)
        toolBar.setNavigationOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.close()
            } else {
                drawerLayout.open()
            }
        }

        navigationView.setNavigationItemSelectedListener {
            val fragment: Fragment = when (it.itemId) {
                R.id.menu_item_gallery -> GalleryFragment()
                R.id.menu_item_import -> ImportFragment()
                R.id.menu_item_slideshow -> SlideShowFragment()
                else -> ToolsFragment()
            }
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, fragment)
            fragmentTransaction.commit()
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.close()
            }
            return@setNavigationItemSelectedListener true
        }
    }

}