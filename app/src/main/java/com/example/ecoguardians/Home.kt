package com.example.ecoguardians

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.ecoguardians.data.Animal
import com.example.ecoguardians.viewModel.AnimalViewModel
import com.example.ecoguardians.viewModel.AnimalViewModelFactory
import com.example.ecoguardians.viewModel.UserViewModel
import com.example.ecoguardians.viewModel.UserViewModelFactory
import kotlinx.coroutines.launch
import org.json.JSONObject

class Home : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val animalViewModel by requireActivity().viewModels<AnimalViewModel> {
            AnimalViewModelFactory(repository = (requireActivity().application as EcoGuardiansApplication).animalRepository)
        }

        val userViewModel by requireActivity().viewModels<UserViewModel> {
            UserViewModelFactory(repository = (requireActivity().application as EcoGuardiansApplication).userRepository)
        }
        lifecycleScope.launch{
            //animal db population
            val json = JSONObject(JsonAnimal().animal1)
            animalViewModel.addAnimal(
                Animal(json.getString("name"), R.drawable.koala, json.getString("position"),
                json.getString("numberSpecies"), json.getString("classification"), json.getString("averageLife"),
                json.getString("animalDescription"), json.getString("threats"), json.getString("whatYouCanDo"),
                json.getString("seriousLink"), json.getDouble("latitude"), json.getDouble("longitude"),false, false,
                userViewModel.getEmail())
            )

            val json2 = JSONObject(JsonAnimal().animal2)
            animalViewModel.addAnimal(
                Animal(json2.getString("name"), R.drawable.orsopolare, json2.getString("position"),
                json2.getString("numberSpecies"), json2.getString("classification"), json2.getString("averageLife"),
                json2.getString("animalDescription"), json2.getString("threats"), json2.getString("whatYouCanDo"),
                json2.getString("seriousLink"), json2.getDouble("latitude"), json2.getDouble("longitude"),false, false,
                userViewModel.getEmail())
            )

            val json3 = JSONObject(JsonAnimal().animal3)
            animalViewModel.addAnimal(
                Animal(json3.getString("name"), R.drawable.tigre, json3.getString("position"),
                json3.getString("numberSpecies"), json3.getString("classification"), json3.getString("averageLife"),
                json3.getString("animalDescription"), json3.getString("threats"), json3.getString("whatYouCanDo"),
                json3.getString("seriousLink"), json3.getDouble("latitude"), json3.getDouble("longitude"),false, false,
                userViewModel.getEmail())
            )

        }
        // Inflate the layout for this fragment
        return view
    }
}