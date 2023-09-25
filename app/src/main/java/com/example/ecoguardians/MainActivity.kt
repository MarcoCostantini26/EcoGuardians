package com.example.ecoguardians

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.ecoguardians.adapters.ViewPagerAdapter
import com.example.ecoguardians.databinding.ActivityMainBinding
import com.example.ecoguardians.ui.login.SigninFragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentLogIn = SigninFragment()
        var transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragmentLogIn)
        transaction.commit()

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener(){
            val fragmentList = SecondFragment()
            var transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main_container, fragmentList)
            transaction.commit()
        }


    }

}