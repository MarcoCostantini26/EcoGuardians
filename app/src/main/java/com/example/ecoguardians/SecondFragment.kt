package com.example.ecoguardians

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecoguardians.viewModel.AnimalViewModel
import com.example.ecoguardians.viewModel.AnimalViewModelFactory
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

        // Accedi all'attività
        val activity = activity as? MainActivity

        // Applica lo stile al titolo della Toolbar
        activity?.findViewById<MaterialToolbar>(R.id.toolbar)?.setTitleTextAppearance(requireContext(), R.style.ToolbarTitle)

        // Aggiorna il titolo della Toolbar
        activity?.findViewById<MaterialToolbar>(R.id.toolbar)?.title = "List of animals"

        animalShowcaseList =ArrayList()

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                // Avvia la richiesta asincrona
                    animalNames = withContext(Dispatchers.IO) {
                    animalViewModel.getName()
                }
                withContext(Dispatchers.Main) {
                    if (view != null && isAdded) {
                        handleAnimalNames(animalNames, animalViewModel)
                    }
                }
            } catch (_: Exception) {

            }
        }

        initRecyclerView(view)
        return view

    }

    @SuppressLint("NotifyDataSetChanged")
    //provare a mettere qua le query
    private suspend fun handleAnimalNames(names: List<String>, animalViewModel : AnimalViewModel) {
        if (names.isNotEmpty()) {
            animalShowcaseList.add(AnimalShowcase(animalViewModel.getImage(names[0]),names[0],
                animalViewModel.getNumberSpecies(names[0]), animalViewModel.getClassification(names[0]),
                animalViewModel.getPosition(names[0]), animalViewModel.getAverageLife(names[0]),
                animalViewModel.getDescription(names[0]), animalViewModel.getThreats(names[0]),
                animalViewModel.getWhatYouCanDo(names[0]), animalViewModel.getSeriousLink(names[0]),
                animalViewModel.isAnimalFavorite(names[0]), animalViewModel.getLatitude(names[0]),
                animalViewModel.getLongitude(names[0])))

            animalShowcaseList.add(AnimalShowcase(animalViewModel.getImage(names[1]),names[1],
                animalViewModel.getNumberSpecies(names[1]), animalViewModel.getClassification(names[1]),
                animalViewModel.getPosition(names[1]), animalViewModel.getAverageLife(names[1]),
                animalViewModel.getDescription(names[1]), animalViewModel.getThreats(names[1]),
                animalViewModel.getWhatYouCanDo(names[1]), animalViewModel.getSeriousLink(names[1]),
                animalViewModel.isAnimalFavorite(names[1]), animalViewModel.getLatitude(names[1]),
                animalViewModel.getLongitude(names[1])))
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
            animalShowcase.classification, animalShowcase.position, animalShowcase.averageLife, animalShowcase.description,
            animalShowcase.threats, animalShowcase.whatYouCanDo, animalShowcase.seriousLink, animalShowcase.latitude, animalShowcase.longitude)
        val transaction : FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragmentDetailed)
        transaction.commit()
    }

}