package com.example.ecoguardians

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecoguardians.viewModel.AnimalViewModel
import com.example.ecoguardians.viewModel.AnimalViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment(), AnimalAdapter.ItemClickListener{

    private lateinit var animalShowcaseList: ArrayList<AnimalShowcase>
    private lateinit var recycleView: RecyclerView
    private lateinit var animalAdapter : AnimalAdapter
    private var animalNames: List<String> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view : View = inflater.inflate(R.layout.fragment_second, container, false)
        val animalViewModel by viewModels<AnimalViewModel> {
            AnimalViewModelFactory(repository = (requireActivity().application as EcoGuardiansApplication).animalRepository)
        }

        animalShowcaseList =ArrayList()

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                // Avvia la richiesta asincrona
                    animalNames = withContext(Dispatchers.IO) {
                    animalViewModel.getName()
                }
                withContext(Dispatchers.Main) {
                    if (view != null && isAdded) {
                        handleAnimalNames(animalViewModel.getImage(animalNames[0]),animalNames,
                            animalViewModel.getNumberSpecies(animalNames[0]), animalViewModel.getClassification(animalNames[0]),
                                animalViewModel.getPosition(animalNames[0]), animalViewModel.getAverageLife(animalNames[0]),
                                animalViewModel.getDescription(animalNames[0]), animalViewModel.getThreats(animalNames[0]),
                                animalViewModel.getWhatYouCanDo(animalNames[0]), animalViewModel.getSeriousLink(animalNames[0]))
                    }
                }
            } catch (e: Exception) {

            }
        }

        initRecyclerView(view)
        return view

    }

    private fun handleAnimalNames(image: Int, names: List<String>, numberSpecies: String, classification: String,
                                  position: String, averageLife: String, description: String, threats: String,
                                  whatYouCanDo: String, seriousLink: String) {
        if (names.isNotEmpty()) {
            animalShowcaseList.add(AnimalShowcase(image, names[0], numberSpecies, classification, position, averageLife, description,
                threats, whatYouCanDo, seriousLink))
        }
        animalAdapter.notifyDataSetChanged()
    }

    private fun initRecyclerView(view : View){
        recycleView = view.findViewById(R.id.recycleView)
        var linearLayoutManager : LinearLayoutManager = LinearLayoutManager(activity)

        recycleView.layoutManager = linearLayoutManager
        animalAdapter = AnimalAdapter(animalShowcaseList, this)
        recycleView.adapter = animalAdapter
    }

    override fun onItemClick(animalShowcase: AnimalShowcase) {
        val fragmentDetailed = DetailedFragment.newInstance(animalShowcase.image, animalShowcase.name, animalShowcase.numberSpecies,
            animalShowcase.classification, animalShowcase.averageLife, animalShowcase.position, animalShowcase.description,
            animalShowcase.threats, animalShowcase.whatYouCanDo, animalShowcase.seriousLink)
        val transaction : FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragmentDetailed)
        transaction.commit()
    }

}