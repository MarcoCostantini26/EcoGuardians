package com.example.ecoguardians

import android.annotation.SuppressLint
import android.os.Bundle
import android.Manifest
import android.app.Activity
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.ecoguardians.viewModel.AnimalViewModel
import com.example.ecoguardians.viewModel.AnimalViewModelFactory
import com.example.ecoguardians.viewModel.UserViewModel
import com.example.ecoguardians.viewModel.UserViewModelFactory
import kotlinx.coroutines.launch
import com.example.ecoguardians.viewModel.BadgeViewModel
import com.example.ecoguardians.viewModel.BadgeViewModelFactory
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

class UserProfile : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val userViewModel by viewModels<UserViewModel> {
        UserViewModelFactory(repository = (requireActivity().application as EcoGuardiansApplication).userRepository, animalRepository = (requireActivity().application as EcoGuardiansApplication).animalRepository)
    }

    private val animalViewModel by viewModels<AnimalViewModel> {
        AnimalViewModelFactory(repository = (requireActivity().application as EcoGuardiansApplication).animalRepository)
    }

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
            UserViewModelFactory(
                repository = (requireActivity().application as EcoGuardiansApplication).userRepository,
                animalRepository = (requireActivity().application as EcoGuardiansApplication).animalRepository
            )
        }

        val badgeViewModel by requireActivity().viewModels<BadgeViewModel> {
            BadgeViewModelFactory(repository = (requireActivity().application as EcoGuardiansApplication).badgeRepository)
        }

        val profilePictureIV = view.findViewById<ImageView>(R.id.setting_profile_image)
        viewLifecycleOwner.lifecycleScope.launch {
            profilePictureIV.setImageURI(userViewModel.getProfilePicture())
        }

        val changeProfilePicture = view.findViewById<FloatingActionButton>(R.id.changeProfilePicture)

        changeProfilePicture.setOnClickListener{
            ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
            viewLifecycleOwner.lifecycleScope.launch{
                badgeViewModel.setCompletedTrue(userViewModel.getEmail(), 2)
            }
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
            val isCompleted = badgeViewModel.isCompleted(userViewModel.getEmail(), 1)
            if(isCompleted){
                progressBarObiettivo1.progress = 100
                textViewObiettivo1.text = "1/1"
            }
        }

        val progressBarObiettivo2 = view.findViewById<ProgressBar>(R.id.progressBarObiettivo2)
        val textViewObiettivo2 = view.findViewById<TextView>(R.id.textViewObiettivo2)

        viewLifecycleOwner.lifecycleScope.launch {
            val isCompleted = badgeViewModel.isCompleted(userViewModel.getEmail(), 2)
            if(isCompleted){
                progressBarObiettivo2.progress = 100
                textViewObiettivo2.text = "1/1"

                if(!badgeViewModel.firstComplete(2, userViewModel.getEmail())) {
                    // Send notification when badge is completed
                    badgeViewModel.setFirstComplete(2, userViewModel.getEmail())
                    sendBadgeNotificationCompleted(2, badgeViewModel)
                }
            }
        }

        val progressBarObiettivo3 = view.findViewById<ProgressBar>(R.id.progressBarObiettivo3)
        val textViewObiettivo3 = view.findViewById<TextView>(R.id.textViewObiettivo3)

        viewLifecycleOwner.lifecycleScope.launch {
            val isCompleted = badgeViewModel.isCompleted(userViewModel.getEmail(), 3)
            if(isCompleted){
                progressBarObiettivo3.progress = 100
                textViewObiettivo3.text = "1/1"
            }
        }

        val progressBarObiettivo5 = view.findViewById<ProgressBar>(R.id.progressBarObiettivo5)
        val textViewObiettivo5 = view.findViewById<TextView>(R.id.textViewObiettivo5)

        viewLifecycleOwner.lifecycleScope.launch {
            val isCompleted = badgeViewModel.isCompleted(userViewModel.getEmail(), 5)
            if(isCompleted){
                progressBarObiettivo5.progress = 100
                textViewObiettivo5.text = "1/1"
            }
        }

        val progressBarObiettivo6 = view.findViewById<ProgressBar>(R.id.progressBarObiettivo6)
        val textViewObiettivo6 = view.findViewById<TextView>(R.id.textViewObiettivo6)

        viewLifecycleOwner.lifecycleScope.launch {
            progressBarObiettivo6.progress = animalViewModel.countIsVisited(userViewModel.getEmail()) * 10
            textViewObiettivo6.text = "${animalViewModel.countIsVisited(userViewModel.getEmail())}" + "/10"
        }

        return view
    }

    private fun sendBadgeNotificationCompleted(badgeId: Int, badgeViewModel: BadgeViewModel) {
        val notificationId = 1 // A unique identifier for the notification

        viewLifecycleOwner.lifecycleScope.launch {
            val badgeDescription = badgeViewModel.getDescription(badgeId)

            if (userViewModel.notificationEnabled(userViewModel.getEmail())) {
                // Build the notification
                val notification = NotificationCompat.Builder(
                    requireActivity().applicationContext,
                    MainActivity.CHANNEL_ID
                )
                    .setSmallIcon(R.drawable.ic_stat_name)
                    .setContentTitle("EcoGuardians")
                    .setContentText("Missione completata: $badgeDescription")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .build()

                // Send the notification
                with(NotificationManagerCompat.from(requireContext())) {
                    // notificationId is a unique int for each notification that you must define.
                    if (ActivityCompat.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.POST_NOTIFICATIONS
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {  }
                    notify(notificationId, notification)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val uri: Uri = data?.data!!

            // Use Uri object instead of File to avoid storage permissions
            viewLifecycleOwner.lifecycleScope.launch{
                userViewModel.updateProfilePicture(uri)
            }
            val fragmentUser = UserProfile()
            val transaction : FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main_container, fragmentUser)
            transaction.commit()
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
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
