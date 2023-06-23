package com.lasuak.navigationcontroller.project3
// EXAMPLE-3 : Multiple BackStacks with BottomNav View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lasuak.navigationcontroller.R
import com.lasuak.navigationcontroller.databinding.ActivityMain3Binding


class MainActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityMain3Binding
    private var navController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)

        //When app first time start or create
        if(savedInstanceState==null)
            setUpBottomNav()

    }

    //when app resume
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setUpBottomNav()
    }
    //This method is use for multi nav-graph support at runtime
    private fun setUpBottomNav() {
        val graphsId =
            listOf(R.navigation.home_nav_graph, R.navigation.setting_nav_graph, R.navigation.notification_nav_graph)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)

        //This 'SetupWithNavController' is provided by google(NavigationExtension.kt) file for multiple navigation with retain state of each fragment
        val controller = bottomNav.setupWithNavController(
            graphsId,
            supportFragmentManager,
            R.id.nav_host_fragment_container,
            intent
        )
        //Observer for navigation changes observe
        controller.observe(this){
            setupActionBarWithNavController(it)
        }
        navController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController?.value?.navigateUp()!! || super.onSupportNavigateUp()
    }
}