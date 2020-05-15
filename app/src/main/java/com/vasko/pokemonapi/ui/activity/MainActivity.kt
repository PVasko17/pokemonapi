package com.vasko.pokemonapi.ui.activity

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.vasko.pokemonapi.R
import com.vasko.pokemonapi.di.component.ActivityComponent
import com.vasko.pokemonapi.ui.base.BaseActivity
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : BaseActivity() {

    override fun findContentView(): Int = R.layout.main_activity

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        Initialising base navigation controller
        val navController = findNavController(R.id.nav_host_fragment)

//        Setting up root destination of the controller
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.dest_list)
        )

//        Attaching toolbar to activity and navigation controller
        setSupportActionBar(toolbar)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}
