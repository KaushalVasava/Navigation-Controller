package com.lasuak.navigationcontroller.Project1.activity
//EXAMPLE-1 : NAVIGATION WITH SINGLE NAV-GRAPH

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.lasuak.navigationcontroller.MainNavGraphDirections
import com.lasuak.navigationcontroller.R
import com.lasuak.navigationcontroller.databinding.ActivityMainBinding

/** DEEP LINKS
 *  THERE ARE TWO TYPES OF DEEP LINKS :
 *  1. Implicit deep link -> In this type of deep link
       we can always go to one destination or fragment
       or always open specific fragment attached with that deep link

  *  2. Explicit deep link -> In this type of deep link we can open more than one
       fragments or destination with that deep links
 */

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)

        //This is use for top level destination and using this we can remove back icon on fragments
        //--> For Example : bottom Navigation View Home,Settings,Notifications
        //2nd Argument for show hamburger icon of navigation drawer
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.settingsFragment, R.id.notificationsFragment),
            binding.drawerLayout
        )
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment

        navController= navHostFragment.navController

        setupActionBarWithNavController(navController,appBarConfiguration)

        //This below method is used with toolbar but may be a bug
        // that home fragment name not shown first time and
        // you don't have to define below override onSupportNavigateUp() for backward navigation
        // binding.toolbar.setupWithNavController(navController)

        //bottom navigation view
        binding.bottomNavView.setupWithNavController(navController)

        //Navigation Drawer
        binding.navDrawer.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        //Pass argument appBarConfiguration in navigateUp() method
        // for hamburger icon respond to click events
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // USE ID of fragments in menu
        return when(item.itemId){
            R.id.about_app ->{
                val action =
                    com.lasuak.navigationcontroller.MainNavGraphDirections.actionGlobalAboutAppFragment()
                navController.navigate(action)
                return true
            }
            else -> item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

}