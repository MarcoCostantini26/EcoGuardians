package com.example.ecoguardians

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.ecoguardians.viewModel.UserViewModel
import com.example.ecoguardians.viewModel.UserViewModelFactory
import kotlinx.coroutines.launch

class SettingsPage : Fragment() {

    private lateinit var notificationToggleButton: ToggleButton
    private lateinit var switchMode : SwitchCompat
    private lateinit var editor : SharedPreferences.Editor
    private lateinit var sharedPreferences: SharedPreferences
    private var isNotificationInitialized = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        sharedPreferences = requireContext().getSharedPreferences("MODE", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        // Verifica se la variabile è già stata inizializzata
        if (!isNotificationInitialized) {
            notificationToggleButton = view.findViewById(R.id.changeNotification)
            isNotificationInitialized = true

            notificationToggleButton.isChecked =
                sharedPreferences.getBoolean("notification_enabled", true)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        switchMode = view.findViewById(R.id.switchMode)
        val isNightMode = sharedPreferences.getBoolean("night_mode", false)

        val logout = requireActivity().findViewById<ImageButton>(R.id.logoutButton)

        val userViewModel by viewModels<UserViewModel> {
            UserViewModelFactory(
                repository = (requireActivity().application as EcoGuardiansApplication).userRepository,
                animalRepository = (requireActivity().application as EcoGuardiansApplication).animalRepository
            )
        }

        logout.setOnClickListener() {
            viewLifecycleOwner.lifecycleScope.launch {
                userViewModel.setSessionFalse(userViewModel.getEmail())
                val login = LoginFragment()
                val transaction : FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.main_container, login)
                transaction.commit()
            }
        }

        notificationToggleButton.setOnCheckedChangeListener() { _, isChecked ->
            viewLifecycleOwner.lifecycleScope.launch {
                editor = sharedPreferences.edit()
                editor.putBoolean("notification_enabled", isChecked)
                editor.apply()
                if (isChecked) {
                    // Attiva le notifiche
                    userViewModel.notificationOn(userViewModel.getEmail())
                } else {
                    // Disattiva le notifiche
                    userViewModel.notificationOff(userViewModel.getEmail())
                }
            }
        }

        switchMode.isChecked = isNightMode
        switchMode.setOnCheckedChangeListener{_,isChecked ->

            if(isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            editor = sharedPreferences.edit()
            editor.putBoolean("night_mode", isChecked)
            editor.apply()
        }
    }

}
