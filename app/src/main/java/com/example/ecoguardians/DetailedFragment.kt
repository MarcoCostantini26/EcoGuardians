package com.example.ecoguardians

import android.Manifest
import android.animation.Animator
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.ecoguardians.viewModel.AnimalViewModel
import com.example.ecoguardians.viewModel.AnimalViewModelFactory
import com.example.ecoguardians.viewModel.BadgeViewModel
import com.example.ecoguardians.viewModel.BadgeViewModelFactory
import com.example.ecoguardians.viewModel.UserViewModel
import com.example.ecoguardians.viewModel.UserViewModelFactory
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [DetailedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

const val ARG_PARAM1 = "param1"
const val ARG_PARAM2 = "param2"
const val ARG_PARAM3 = "param3"
const val ARG_PARAM4 = "param4"
const val ARG_PARAM5 = "param5"
const val ARG_PARAM6 = "param6"
const val ARG_PARAM7 = "param7"
const val ARG_PARAM8 = "param8"
const val ARG_PARAM9 = "param9"
const val ARG_PARAM10 = "param10"
const val ARG_PARAM11 = "param11"
const val ARG_PARAM12 = "param12"
class DetailedFragment : Fragment() {

    private var currentAnimator: Animator? = null

    private var shortAnimationDuration: Int = 0

    private val userViewModel by viewModels<UserViewModel> {
        UserViewModelFactory(
            repository = (requireActivity().application as EcoGuardiansApplication).userRepository,
            animalRepository = (requireActivity().application as EcoGuardiansApplication).animalRepository
        )
    }

    private val badgeViewModel by viewModels<BadgeViewModel> {
        BadgeViewModelFactory(repository = (requireActivity().application as EcoGuardiansApplication).badgeRepository)
    }

    private val animalViewModel by viewModels<AnimalViewModel> {
        AnimalViewModelFactory(repository = (requireActivity().application as EcoGuardiansApplication).animalRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detailed, container, false)

        val scrollView = view.findViewById<ScrollView>(R.id.scrollView)
        val section1 = view.findViewById<TextView>(R.id.section1)
        val section2 = view.findViewById<TextView>(R.id.section2)
        val section3 = view.findViewById<TextView>(R.id.section3)
        val section4 = view.findViewById<TextView>(R.id.section4)

        val textView : TextView = view.findViewById(R.id.detailedActivityTV)
        val imageView : ImageView = view.findViewById(R.id.detailedActivityIV)
        val numberSpeciesTV : TextView = view.findViewById(R.id.numberSpecies)
        val classificationTV : TextView = view.findViewById(R.id.classification)
        val averageLifeTV : TextView = view.findViewById(R.id.averageLife)
        val positionTV : TextView = view.findViewById(R.id.position)

        textView.text = arguments?.getString(ARG_PARAM2)
        arguments?.let { imageView.setImageResource(it.getInt(ARG_PARAM1)) }
        numberSpeciesTV.text = arguments?.getString(ARG_PARAM3)
        classificationTV.text = arguments?.getString(ARG_PARAM4)
        averageLifeTV.text = arguments?.getString(ARG_PARAM6)
        positionTV.text = arguments?.getString(ARG_PARAM5)


        val descriptionTV: TextView = view.findViewById(R.id.content1)
        descriptionTV.text = arguments?.getString(ARG_PARAM7)
        val threatsTV: TextView = view.findViewById(R.id.content2)
        threatsTV.text = arguments?.getString(ARG_PARAM8)
        val whatYouCanDoTV: TextView = view.findViewById(R.id.content3)
        whatYouCanDoTV.text = arguments?.getString(ARG_PARAM9)
        val seriousLinkTV: TextView = view.findViewById(R.id.content4)
        seriousLinkTV.text = arguments?.getString(ARG_PARAM10)

        viewLifecycleOwner.lifecycleScope.launch{
            animalViewModel.setIsVisitedTrue(textView.text.toString(), userViewModel.getEmail())
            if(animalViewModel.countIsVisited(userViewModel.getEmail()) == 10){
                badgeViewModel.setCompletedTrue(userViewModel.getEmail(), 6)
                if(!badgeViewModel.firstComplete(6, userViewModel.getEmail())) {
                    badgeViewModel.setFirstComplete(6, userViewModel.getEmail())
                    val notificationId = 1
                    val badgeDescription = badgeViewModel.getDescription(6)

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
        }

        section1.setOnClickListener {
            scrollView.scrollTo(0, view.findViewById<View>(R.id.content1).top)
        }

        section2.setOnClickListener {
            scrollView.scrollTo(0, view.findViewById<View>(R.id.content2).top)
        }
        section3.setOnClickListener {
            scrollView.scrollTo(0, view.findViewById<View>(R.id.content3).top)
        }

        section4.setOnClickListener {
            scrollView.scrollTo(0, view.findViewById<View>(R.id.content4).top)
        }

        // Retrieve and cache the system's default "short" animation time.
        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)

        val animalPositionButton : ImageButton = view.findViewById(R.id.positionButton)



        animalPositionButton.setOnClickListener{
            viewLifecycleOwner.lifecycleScope.launch{
                badgeViewModel.setCompletedTrue(userViewModel.getEmail(), 5)
                if(!badgeViewModel.firstComplete(5, userViewModel.getEmail())) {
                    badgeViewModel.setFirstComplete(5, userViewModel.getEmail())
                    val notificationId = 1
                    val badgeDescription = badgeViewModel.getDescription(5)

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
                val mapFragment = Map.newInstance(arguments?.getDouble(ARG_PARAM11)!!,
                    arguments?.getDouble(ARG_PARAM12)!!)
                val transaction : FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.main_container, mapFragment)
                transaction.commit()
            }

        }

        val link = view.findViewById<TextView>(R.id.content4)

        link.setOnClickListener{
            viewLifecycleOwner.lifecycleScope.launch {
                badgeViewModel.setCompletedTrue(userViewModel.getEmail(), 4)
                if(!badgeViewModel.firstComplete(4, userViewModel.getEmail())) {
                    badgeViewModel.setFirstComplete(4, userViewModel.getEmail())
                    val notificationId = 1
                    val badgeDescription = badgeViewModel.getDescription(4)

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
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(image : Int, name: String, numberSpecies: String, classification: String,
                        position: String, averageLife: String, description: String, threats: String,
                        whatYouCanDo: String, seriousLink: String, latitude: Double, longitude: Double) =
            DetailedFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, image)
                    putString(ARG_PARAM2, name)
                    putString(ARG_PARAM3, numberSpecies)
                    putString(ARG_PARAM4, classification)
                    putString(ARG_PARAM5, position)
                    putString(ARG_PARAM6, averageLife)
                    putString(ARG_PARAM7, description)
                    putString(ARG_PARAM8, threats)
                    putString(ARG_PARAM9, whatYouCanDo)
                    putString(ARG_PARAM10, seriousLink)
                    putDouble(ARG_PARAM11, latitude)
                    putDouble(ARG_PARAM12, longitude)
                }
            }
    }
}
