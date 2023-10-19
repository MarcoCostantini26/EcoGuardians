package com.example.ecoguardians

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.ecoguardians.data.Animal
import com.example.ecoguardians.viewModel.AnimalViewModel
import com.example.ecoguardians.viewModel.AnimalViewModelFactory
import com.example.ecoguardians.viewModel.UserViewModel
import com.example.ecoguardians.viewModel.UserViewModelFactory
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.PermissionListener
import org.json.JSONObject
import android.provider.Settings
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Applica lo stile al titolo della Toolbar
        findViewById<MaterialToolbar>(R.id.toolbar)?.setTitleTextAppearance(application, R.style.ToolbarTitle)

        // Aggiorna il titolo della Toolbar
        findViewById<MaterialToolbar>(R.id.toolbar)?.title = "Home"

        val animalViewModel by viewModels<AnimalViewModel> {
            AnimalViewModelFactory(repository = (application as EcoGuardiansApplication).animalRepository)
        }

        val userViewModel by viewModels<UserViewModel> {
            UserViewModelFactory(repository = (application as EcoGuardiansApplication).userRepository)
        }

        //animal db population
        val json = JSONObject(JsonAnimal().animal1)
        animalViewModel.addAnimal(Animal(json.getString("name"), R.drawable.koala, json.getString("position"),
            json.getString("numberSpecies"), json.getString("classification"), json.getString("averageLife"),
            json.getString("animalDescription"), json.getString("threats"), json.getString("whatYouCanDo"),
            json.getString("seriousLink"), json.getDouble("latitude"), json.getDouble("longitude"),false))

        val json2 = JSONObject(JsonAnimal().animal2)
        animalViewModel.addAnimal(Animal(json2.getString("name"), R.drawable.orsopolare, json2.getString("position"),
            json2.getString("numberSpecies"), json2.getString("classification"), json2.getString("averageLife"),
            json2.getString("animalDescription"), json2.getString("threats"), json2.getString("whatYouCanDo"),
            json2.getString("seriousLink"), json2.getDouble("latitude"), json2.getDouble("longitude"),false))

        val json3 = JSONObject(JsonAnimal().animal3)
        animalViewModel.addAnimal(Animal(json3.getString("name"), R.drawable.tigre, json3.getString("position"),
            json3.getString("numberSpecies"), json3.getString("classification"), json3.getString("averageLife"),
            json3.getString("animalDescription"), json3.getString("threats"), json3.getString("whatYouCanDo"),
            json3.getString("seriousLink"), json3.getDouble("latitude"), json3.getDouble("longitude"),false))
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

