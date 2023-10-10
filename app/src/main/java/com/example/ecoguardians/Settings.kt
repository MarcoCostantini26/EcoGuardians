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
import androidx.fragment.app.FragmentTransaction
import com.example.ecoguardians.ui.login.SigninFragment
import com.google.android.material.appbar.MaterialToolbar
import kotlin.properties.Delegates

class Settings : Fragment() {

    private lateinit var switchMode : SwitchCompat
    private lateinit var  editor : SharedPreferences.Editor
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        switchMode = view.findViewById(R.id.switchMode)
        sharedPreferences = requireContext().getSharedPreferences("MODE", Context.MODE_PRIVATE)
        val isNightMode = sharedPreferences.getBoolean("night_mode", false)

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