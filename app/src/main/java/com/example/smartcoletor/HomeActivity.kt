package com.example.smartcoletor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class HomeActivity: AppCompatActivity(){

    lateinit var btGoToUserHome:Button
    lateinit var btGoToColectorHome:Button
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigation:NavigationView
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        drawerLayout = findViewById(R.id.drower_home)
        navigation = findViewById(R.id.nav_home)
        toolbar = findViewById(R.id.home_toolbar)
        setSupportActionBar(toolbar)

        var toogle:ActionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toogle)
        navigation.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId){
                (R.id.nav_user_home) -> {
                    var intent:Intent = Intent(this, UserHomeActivity::class.java)
                    startActivity(intent)
                }
                (R.id.nav_colector_home) -> {
                    var intent:Intent = Intent(this, ColetorHomeActivity::class.java)
                    startActivity(intent)
                }
                (R.id.nav_logout) -> {
                    var intent:Intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                (R.id.nav_user_config) -> {
                    Toast.makeText(this,"Feature Not Implemented",Toast.LENGTH_LONG).show()
                }
            }
            true
        })
        toogle.syncState()

        btGoToUserHome = findViewById(R.id.bt_to_user_home)
        btGoToColectorHome = findViewById(R.id.bt_to_colector_home)

        btGoToUserHome.setOnClickListener(View.OnClickListener { view ->
            var intent:Intent = Intent(this, UserHomeActivity::class.java)
            startActivity(intent)
        })
        btGoToColectorHome.setOnClickListener(View.OnClickListener { view ->
            var intent:Intent = Intent(this, ColetorHomeActivity::class.java)
            startActivity(intent)
        })
    }


}