package com.example.ecoguardians

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.ecoguardians.data.Animal
import com.example.ecoguardians.data.Badge
import com.example.ecoguardians.viewModel.BadgeViewModel
import com.example.ecoguardians.viewModel.BadgeViewModelFactory
import com.example.ecoguardians.viewModel.UserViewModel
import com.example.ecoguardians.viewModel.UserViewModelFactory
import kotlinx.coroutines.launch
import org.json.JSONObject
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.viewModels
import com.example.ecoguardians.viewModel.AnimalViewModel
import com.example.ecoguardians.viewModel.AnimalViewModelFactory
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Home : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private lateinit var userViewModel: UserViewModel

    private val animalViewModel by viewModels<AnimalViewModel> {
        AnimalViewModelFactory(repository = (requireActivity().application as EcoGuardiansApplication).animalRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        updateBottomAppBarAndFabVisibility()

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        userViewModel = viewModels<UserViewModel> {
            UserViewModelFactory(
                repository = (requireActivity().application as EcoGuardiansApplication).userRepository,
                animalRepository = (requireActivity().application as EcoGuardiansApplication).animalRepository
            )
        }.value

        val userViewModel by requireActivity().viewModels<UserViewModel> {
            UserViewModelFactory(repository = (requireActivity().application as EcoGuardiansApplication).userRepository, animalRepository = (requireActivity().application as EcoGuardiansApplication).animalRepository)
        }

        val badgeViewModel by requireActivity().viewModels<BadgeViewModel> {
            BadgeViewModelFactory(repository = (requireActivity().application as EcoGuardiansApplication).badgeRepository)
        }

        lifecycleScope.launch{
            //badge db population
            badgeViewModel.addBadge(
                Badge(1, true, false,"Benvenuto Guardiano!", userViewModel.getEmail())
            )

            if(!badgeViewModel.firstComplete(1, userViewModel.getEmail())) {
                // Send notification when badge is completed
                badgeViewModel.setFirstComplete(1, userViewModel.getEmail())
                sendBadgeNotificationCompleted(1, badgeViewModel)
            }

            //animal db population
            val json = JSONObject(JsonAnimal().animal1)
            val newAnimal = Animal(
                animal = json.getString("name"),
                image = R.drawable.koala,
                position = json.getString("position"),
                numberSpecies =  json.getString("numberSpecies"),
                classification = json.getString("classification"),
                averageLife = json.getString("averageLife"),
                animalDescription = json.getString("animalDescription"),
                threats = json.getString("threats"),
                whatYouCanDo = json.getString("whatYouCanDo"),
                seriousLink = json.getString("seriousLink"),
                latitude = json.getDouble("latitude"),
                longitude = json.getDouble("longitude"),
                favorite = userViewModel.isAnimalFavorite(json.getString("name"), userViewModel.getEmail()),
                isVisited = animalViewModel.isVisited(json.getString("name"), userViewModel.getEmail()),
                email = userViewModel.getEmail()
            )

            userViewModel.addAnimal(newAnimal)

            val json2 = JSONObject(JsonAnimal().animal2)
            val newAnimal2 = Animal(
                animal = json2.getString("name"),
                image = R.drawable.orsopolare,
                position = json2.getString("position"),
                numberSpecies =  json2.getString("numberSpecies"),
                classification = json2.getString("classification"),
                averageLife = json2.getString("averageLife"),
                animalDescription = json2.getString("animalDescription"),
                threats = json2.getString("threats"),
                whatYouCanDo = json2.getString("whatYouCanDo"),
                seriousLink = json2.getString("seriousLink"),
                latitude = json2.getDouble("latitude"),
                longitude = json2.getDouble("longitude"),
                favorite = userViewModel.isAnimalFavorite(json2.getString("name"), userViewModel.getEmail()),
                isVisited = animalViewModel.isVisited(json2.getString("name"), userViewModel.getEmail()),
                email = userViewModel.getEmail()
            )
            userViewModel.addAnimal(newAnimal2)

            val json3 = JSONObject(JsonAnimal().animal3)
            val newAnimal3 = Animal(
                animal = json3.getString("name"),
                image = R.drawable.tigre,
                position = json3.getString("position"),
                numberSpecies =  json3.getString("numberSpecies"),
                classification = json3.getString("classification"),
                averageLife = json3.getString("averageLife"),
                animalDescription = json3.getString("animalDescription"),
                threats = json3.getString("threats"),
                whatYouCanDo = json3.getString("whatYouCanDo"),
                seriousLink = json3.getString("seriousLink"),
                latitude = json3.getDouble("latitude"),
                longitude = json3.getDouble("longitude"),
                favorite = userViewModel.isAnimalFavorite(json3.getString("name"), userViewModel.getEmail()),
                isVisited = animalViewModel.isVisited(json3.getString("name"), userViewModel.getEmail()),
                email = userViewModel.getEmail()
            )
            userViewModel.addAnimal(newAnimal3)


            val json4 = JSONObject(JsonAnimal().animal4)
            val rino = Animal(
                animal = json4.getString("name"),
                image = R.drawable.pinguino,
                position = json4.getString("position"),
                numberSpecies =  json4.getString("numberSpecies"),
                classification = json4.getString("classification"),
                averageLife = json4.getString("averageLife"),
                animalDescription = json4.getString("animalDescription"),
                threats = json4.getString("threats"),
                whatYouCanDo = json4.getString("whatYouCanDo"),
                seriousLink = json4.getString("seriousLink"),
                latitude = json4.getDouble("latitude"),
                longitude = json4.getDouble("longitude"),
                favorite = userViewModel.isAnimalFavorite(json4.getString("name"), userViewModel.getEmail()),
                isVisited = false,
                email = userViewModel.getEmail()
            )
            userViewModel.addAnimal(rino)

            val json5 = JSONObject(JsonAnimal().animal5)
            val elefante = Animal(
                animal = json5.getString("name"),
                image = R.drawable.elefante_sumatra,
                position = json5.getString("position"),
                numberSpecies =  json5.getString("numberSpecies"),
                classification = json5.getString("classification"),
                averageLife = json5.getString("averageLife"),
                animalDescription = json5.getString("animalDescription"),
                threats = json5.getString("threats"),
                whatYouCanDo = json5.getString("whatYouCanDo"),
                seriousLink = json5.getString("seriousLink"),
                latitude = json5.getDouble("latitude"),
                longitude = json5.getDouble("longitude"),
                favorite = userViewModel.isAnimalFavorite(json5.getString("name"), userViewModel.getEmail()),
                isVisited = false,
                email = userViewModel.getEmail()
            )
            userViewModel.addAnimal(elefante)

            val json6 = JSONObject(JsonAnimal().animal6)
            val stambecco = Animal(
                animal = json6.getString("name"),
                image = R.drawable.stambecco,
                position = json6.getString("position"),
                numberSpecies =  json6.getString("numberSpecies"),
                classification = json6.getString("classification"),
                averageLife = json6.getString("averageLife"),
                animalDescription = json6.getString("animalDescription"),
                threats = json6.getString("threats"),
                whatYouCanDo = json6.getString("whatYouCanDo"),
                seriousLink = json6.getString("seriousLink"),
                latitude = json6.getDouble("latitude"),
                longitude = json6.getDouble("longitude"),
                favorite = userViewModel.isAnimalFavorite(json6.getString("name"), userViewModel.getEmail()),
                isVisited = false,
                email = userViewModel.getEmail()
            )
            userViewModel.addAnimal(stambecco)

            val json7 = JSONObject(JsonAnimal().animal7)
            val orso = Animal(
                animal = json7.getString("name"),
                image = R.drawable.orso_marsicano,
                position = json7.getString("position"),
                numberSpecies =  json7.getString("numberSpecies"),
                classification = json7.getString("classification"),
                averageLife = json7.getString("averageLife"),
                animalDescription = json7.getString("animalDescription"),
                threats = json7.getString("threats"),
                whatYouCanDo = json7.getString("whatYouCanDo"),
                seriousLink = json7.getString("seriousLink"),
                latitude = json7.getDouble("latitude"),
                longitude = json7.getDouble("longitude"),
                favorite = userViewModel.isAnimalFavorite(json7.getString("name"), userViewModel.getEmail()),
                isVisited = false,
                email = userViewModel.getEmail()
            )
            userViewModel.addAnimal(orso)

            val json8 = JSONObject(JsonAnimal().animal8)
            val saola = Animal(
                animal = json8.getString("name"),
                image = R.drawable.saola,
                position = json8.getString("position"),
                numberSpecies =  json8.getString("numberSpecies"),
                classification = json8.getString("classification"),
                averageLife = json8.getString("averageLife"),
                animalDescription = json8.getString("animalDescription"),
                threats = json8.getString("threats"),
                whatYouCanDo = json8.getString("whatYouCanDo"),
                seriousLink = json8.getString("seriousLink"),
                latitude = json8.getDouble("latitude"),
                longitude = json8.getDouble("longitude"),
                favorite = userViewModel.isAnimalFavorite(json8.getString("name"), userViewModel.getEmail()),
                isVisited = false,
                email = userViewModel.getEmail()
            )
            userViewModel.addAnimal(saola)

            val json9 = JSONObject(JsonAnimal().animal9)
            val balenottera = Animal(
                animal = json9.getString("name"),
                image = R.drawable.balenottera,
                position = json9.getString("position"),
                numberSpecies =  json9.getString("numberSpecies"),
                classification = json9.getString("classification"),
                averageLife = json9.getString("averageLife"),
                animalDescription = json9.getString("animalDescription"),
                threats = json9.getString("threats"),
                whatYouCanDo = json9.getString("whatYouCanDo"),
                seriousLink = json9.getString("seriousLink"),
                latitude = json9.getDouble("latitude"),
                longitude = json9.getDouble("longitude"),
                favorite = userViewModel.isAnimalFavorite(json9.getString("name"), userViewModel.getEmail()),
                isVisited = false,
                email = userViewModel.getEmail()
            )
            userViewModel.addAnimal(balenottera)

            val json10 = JSONObject(JsonAnimal().animal10)
            val foca = Animal(
                animal = json10.getString("name"),
                image = R.drawable.foca_monaca,
                position = json10.getString("position"),
                numberSpecies =  json10.getString("numberSpecies"),
                classification = json10.getString("classification"),
                averageLife = json10.getString("averageLife"),
                animalDescription = json10.getString("animalDescription"),
                threats = json10.getString("threats"),
                whatYouCanDo = json10.getString("whatYouCanDo"),
                seriousLink = json10.getString("seriousLink"),
                latitude = json10.getDouble("latitude"),
                longitude = json10.getDouble("longitude"),
                favorite = userViewModel.isAnimalFavorite(json10.getString("name"), userViewModel.getEmail()),
                isVisited = false,
                email = userViewModel.getEmail()
            )
            userViewModel.addAnimal(foca)
        }
        // Inflate the layout for this fragment
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

    private fun updateBottomAppBarAndFabVisibility() {
        val bottomAppBar = requireActivity().findViewById<BottomAppBar>(R.id.bottom_menu)
        val fab = requireActivity().findViewById<FloatingActionButton>(R.id.fab)

        bottomAppBar.visibility = View.VISIBLE
        fab.visibility = View.VISIBLE
    }

}
