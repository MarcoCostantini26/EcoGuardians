package com.example.ecoguardians

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.ecoguardians.viewModel.UserViewModel
import com.example.ecoguardians.viewModel.UserViewModelFactory
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<MaterialToolbar>(R.id.toolbar)?.setTitleTextAppearance(application, R.style.ToolbarTitle)
        findViewById<MaterialToolbar>(R.id.toolbar)?.title = "Home"

        val userViewModel by viewModels<UserViewModel> {
            UserViewModelFactory(
                repository = (application as EcoGuardiansApplication).userRepository,
                animalRepository = (application as EcoGuardiansApplication).animalRepository
            )
        }

        // SignIn fragment
        lifecycleScope.launch{
            if(userViewModel.countUserInSession() == 0){
                val fragmentLogIn = LoginFragment()
                val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.main_container, fragmentLogIn)
                transaction.commit()
            }else{
                val fragmentHome = Home()
                val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.main_container, fragmentHome)
                transaction.commit()
            }
        }



        // Transaction to the list of animals
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener(){
            val fragmentList = SecondFragment()
            val transaction2 : FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction2.replace(R.id.main_container, fragmentList)
            transaction2.commit()
        }

        // Transaction to the user profile/home/settings/search
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.user -> { findViewById<MaterialToolbar>(R.id.toolbar)?.title = "User profile"
                                val fragmentList = UserProfile()
                                val transaction2 : FragmentTransaction = supportFragmentManager.beginTransaction()
                                transaction2.replace(R.id.main_container, fragmentList)
                                transaction2.commit() }
                R.id.home -> { findViewById<MaterialToolbar>(R.id.toolbar)?.title = "Home"
                                val fragmentList = Home()
                                val transaction2 : FragmentTransaction = supportFragmentManager.beginTransaction()
                                transaction2.replace(R.id.main_container, fragmentList)
                                transaction2.commit() }
                R.id.settings -> { findViewById<MaterialToolbar>(R.id.toolbar)?.title = "Settings"
                                val fragmentList = SettingsPage()
                                val transaction2 : FragmentTransaction = supportFragmentManager.beginTransaction()
                                transaction2.replace(R.id.main_container, fragmentList)
                                transaction2.commit() }
                R.id.search -> { findViewById<MaterialToolbar>(R.id.toolbar)?.title = "Searching.."
                                val searchFragment = Search()
                                val transaction2 : FragmentTransaction = supportFragmentManager.beginTransaction()
                                transaction2.replace(R.id.main_container, searchFragment)
                                transaction2.commit() }
            }
            true
        }

    }

}
