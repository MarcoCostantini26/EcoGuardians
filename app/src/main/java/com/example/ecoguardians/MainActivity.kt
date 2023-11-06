package com.example.ecoguardians

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import com.example.ecoguardians.viewModel.UserViewModel
import com.example.ecoguardians.viewModel.UserViewModelFactory
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch


var isFirstOnCreate = true

class MainActivity : AppCompatActivity() {

    companion object {
        const val CHANNEL_ID = "BadgeChannel"  // A unique identifier for the notification channel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userViewModel by viewModels<UserViewModel> {
            UserViewModelFactory(
                repository = (application as EcoGuardiansApplication).userRepository,
                animalRepository = (application as EcoGuardiansApplication).animalRepository
            )
        }

        if (isFirstOnCreate) {
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

            isFirstOnCreate = false
        }


        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        findViewById<MaterialToolbar>(R.id.toolbar)?.logo = ContextCompat.getDrawable(this, R.drawable.logotoolbar)
        val logoImageView = toolbar.getChildAt(0) as ImageView

        val yOffset = resources.getDimensionPixelSize(R.dimen.fragment_vertical_margin)

        val layoutParams = logoImageView.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.topMargin = yOffset
        logoImageView.layoutParams = layoutParams


        // Create a notification channel
        createNotificationChannel()


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

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "BadgeChannel"
            val descriptionText = "Canale per le notifiche di completamento badge"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}
