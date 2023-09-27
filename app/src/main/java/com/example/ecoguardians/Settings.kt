package com.example.ecoguardians

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.NightMode
import androidx.appcompat.widget.SwitchCompat

class Settings : Fragment() {

    private lateinit var  editor : SharedPreferences.Editor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        val isNightMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES

        val switchMode : SwitchCompat = view.findViewById(R.id.switchMode)
        switchMode.isChecked = isNightMode

        switchMode.setOnCheckedChangeListener{_, isChecked ->
            AppCompatDelegate.setDefaultNightMode(
                if(isChecked) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )

        }

        return view
    }

}