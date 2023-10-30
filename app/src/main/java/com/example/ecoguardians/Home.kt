package com.example.ecoguardians

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.ecoguardians.data.Animal
import com.example.ecoguardians.data.Badge
import com.example.ecoguardians.viewModel.AnimalViewModel
import com.example.ecoguardians.viewModel.AnimalViewModelFactory
import com.example.ecoguardians.viewModel.BadgeViewModel
import com.example.ecoguardians.viewModel.BadgeViewModelFactory
import com.example.ecoguardians.viewModel.UserViewModel
import com.example.ecoguardians.viewModel.UserViewModelFactory
import kotlinx.coroutines.launch
import org.json.JSONObject
import android.util.Log
import androidx.fragment.app.viewModels

class Home : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        userViewModel = viewModels<UserViewModel> {
            UserViewModelFactory(
                repository = (requireActivity().application as EcoGuardiansApplication).userRepository,
                animalRepository = (requireActivity().application as EcoGuardiansApplication).animalRepository
            )
        }.value
        
        val animalViewModel by requireActivity().viewModels<AnimalViewModel> {
            AnimalViewModelFactory(repository = (requireActivity().application as EcoGuardiansApplication).animalRepository)
        }

        val userViewModel by requireActivity().viewModels<UserViewModel> {
            UserViewModelFactory(repository = (requireActivity().application as EcoGuardiansApplication).userRepository)
        }

        val badgeViewModel by requireActivity().viewModels<BadgeViewModel> {
            BadgeViewModelFactory(repository = (requireActivity().application as EcoGuardiansApplication).badgeRepository)
        }

        lifecycleScope.launch{
            //badge db population
            badgeViewModel.addBadge(
                Badge(1, true, "Benvenuto Guardiano!", userViewModel.getEmail())
            )

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
                isVisited = false,
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
                isVisited = false,
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
                isVisited = false,
                email = userViewModel.getEmail()
            )
            userViewModel.addAnimal(newAnimal3)
        }
        // Inflate the layout for this fragment
        return view
    }
}