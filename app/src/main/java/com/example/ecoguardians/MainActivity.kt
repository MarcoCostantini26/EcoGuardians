package com.example.ecoguardians

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.ecoguardians.data.Animal
import com.example.ecoguardians.ui.login.SigninFragment
import com.example.ecoguardians.viewModel.AnimalViewModel
import com.example.ecoguardians.viewModel.AnimalViewModelFactory
import com.example.ecoguardians.viewModel.UserViewModel
import com.example.ecoguardians.viewModel.UserViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONObject

var isLogged : Boolean = false

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userViewModel by viewModels<UserViewModel> {
            UserViewModelFactory(repository = (application as EcoGuardiansApplication).userRepository)
        }

        val animalViewModel by viewModels<AnimalViewModel> {
            AnimalViewModelFactory(repository = (application as EcoGuardiansApplication).animalRepository)
        }

        //animal db popolation
        val json = JSONObject(JsonAnimal().animal1)
        animalViewModel.addAnimal(Animal(json.getString("name"), R.drawable.eco__1_, json.getString("position"),
            json.getString("numberSpecies"), json.getString("classification"), json.getString("averageLife"),
            json.getString("animalDescription"), json.getString("threats"), json.getString("whatYouCanDo"),
            json.getString("seriousLink"), false))

        // SignIn fragment
        if(!isLogged){
            val fragmentLogIn = SigninFragment()
            val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main_container, fragmentLogIn)
            transaction.commit()
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
                R.id.user -> { val fragmentList = UserProfile()
                                val transaction2 : FragmentTransaction = supportFragmentManager.beginTransaction()
                                transaction2.replace(R.id.main_container, fragmentList)
                                transaction2.commit() }
                R.id.home -> { val fragmentList = Home()
                                val transaction2 : FragmentTransaction = supportFragmentManager.beginTransaction()
                                transaction2.replace(R.id.main_container, fragmentList)
                                transaction2.commit() }
                R.id.settings -> { val fragmentList = Settings()
                                val transaction2 : FragmentTransaction = supportFragmentManager.beginTransaction()
                                transaction2.replace(R.id.main_container, fragmentList)
                                transaction2.commit() }
                R.id.search -> { val searchFragment = Search()
                                val transaction2 : FragmentTransaction = supportFragmentManager.beginTransaction()
                                transaction2.replace(R.id.main_container, searchFragment)
                                transaction2.commit() }
            }
            true
        }

    }
}
