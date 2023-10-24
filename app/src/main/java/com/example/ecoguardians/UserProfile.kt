package com.example.ecoguardians

import android.annotation.SuppressLint
import android.os.Bundle
import android.Manifest
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.ecoguardians.viewModel.UserViewModel
import com.example.ecoguardians.viewModel.UserViewModelFactory
import kotlinx.coroutines.launch
import com.example.ecoguardians.Map
import com.example.ecoguardians.viewModel.BadgeViewModel
import com.example.ecoguardians.viewModel.BadgeViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener


class UserProfile : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_profile, container, false)
        val userPosition : ImageButton = view.findViewById(R.id.userLocation)

        val userViewModel by viewModels<UserViewModel> {
            UserViewModelFactory(repository = (requireActivity().application as EcoGuardiansApplication).userRepository)
        }

        val badgeViewModel by requireActivity().viewModels<BadgeViewModel> {
            BadgeViewModelFactory(repository = (requireActivity().application as EcoGuardiansApplication).badgeRepository)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            val username = userViewModel.getUsername()
            val usernameView = view.findViewById<TextView>(R.id.username)
            usernameView.text = username
        }

        userPosition.setOnClickListener {
            checkLocationPermissionAndOpenMap()
        }

        val progressBarObiettivo1 = view.findViewById<ProgressBar>(R.id.progressBarObiettivo1)
        val textViewObiettivo1 = view.findViewById<TextView>(R.id.textViewObiettivo1)

        viewLifecycleOwner.lifecycleScope.launch {
            val isCompleted = badgeViewModel.isCompleted(userViewModel.getEmail())
            if(isCompleted){
                progressBarObiettivo1.progress = 100
                textViewObiettivo1.text = "1/1"
            }
        }

        return view
    }
    private fun checkLocationPermissionAndOpenMap() {
        // Check for location permission
        Dexter.withContext(requireContext()).withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(permissionGrantedResponse: PermissionGrantedResponse) {
                    // Permission granted, get current location and open the map
                    getCurrentLocationAndOpenMap()
                }

                override fun onPermissionDenied(permissionDeniedResponse: PermissionDeniedResponse) {
                    // Permission denied, show a message to the user
                    // You may want to explain why you need location access
                    // and provide an option to open settings for the user to grant permission
                    Toast.makeText(
                        requireContext(),
                        "Location permission denied. Please enable it in settings.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissionRequest: PermissionRequest,
                    permissionToken: PermissionToken
                ) {
                    // Provide rationale to the user and continue with the request
                    permissionToken.continuePermissionRequest()
                }
            }).check()
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocationAndOpenMap() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            // Check if location is not null
            if (location != null) {
                val mapFragment = Map.newInstance(location.latitude, location.longitude)
                val transaction: FragmentTransaction =
                    requireActivity().supportFragmentManager.beginTransaction()

                transaction.replace(R.id.main_container, mapFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            } else {
                // Handle the case where the location is null
                Toast.makeText(
                    requireContext(),
                    "Unable to retrieve current location.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}