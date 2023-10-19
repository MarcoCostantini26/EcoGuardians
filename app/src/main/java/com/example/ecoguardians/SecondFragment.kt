package com.example.ecoguardians

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.content.res.AppCompatResources
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
import kotlin.properties.Delegates

class SecondFragment : Fragment(), AnimalAdapter.ItemClickListener{

    private lateinit var animalShowcaseList: ArrayList<AnimalShowcase>
    private lateinit var recycleView: RecyclerView
    private lateinit var animalAdapter : AnimalAdapter
    private lateinit var sharedPreferences: SharedPreferences
    private var isFav: ArrayList<Boolean> = ArrayList()
    private var animalNames: List<String> = emptyList()
    private val animalViewModel by viewModels<AnimalViewModel> {
        AnimalViewModelFactory(repository = (requireActivity().application as EcoGuardiansApplication).animalRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view : View = inflater.inflate(R.layout.fragment_second, container, false)
        val animalViewModel by viewModels<AnimalViewModel> {
            AnimalViewModelFactory(repository = (requireActivity().application as EcoGuardiansApplication).animalRepository)
        }
        sharedPreferences = requireContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

        // Inizializzazione di isFav con il valore salvato nelle SharedPreferences
        // isFav.add(sharedPreferences.getBoolean("isFav", false))

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
                animalViewModel.isAnimalFavorite(names[1]),
                animalViewModel.getLatitude(names[1]), animalViewModel.getLongitude(names[1])))

            animalShowcaseList.add(AnimalShowcase(animalViewModel.getImage(names[2]),names[2],
                animalViewModel.getNumberSpecies(names[2]), animalViewModel.getClassification(names[2]),
                animalViewModel.getPosition(names[2]), animalViewModel.getAverageLife(names[2]),
                animalViewModel.getDescription(names[2]), animalViewModel.getThreats(names[2]),
                animalViewModel.getWhatYouCanDo(names[2]), animalViewModel.getSeriousLink(names[2]),
                animalViewModel.isAnimalFavorite(names[2]),
                animalViewModel.getLatitude(names[2]), animalViewModel.getLongitude(names[2])))

            isFav.add(animalViewModel.isAnimalFavorite(names[0]))
            isFav.add(animalViewModel.isAnimalFavorite(names[1]))
            isFav.add(animalViewModel.isAnimalFavorite(names[2]))
        }
        animalAdapter.notifyDataSetChanged()
    }

    private fun initRecyclerView(view : View){
        recycleView = view.findViewById(R.id.recycleView)
        var linearLayoutManager : LinearLayoutManager = LinearLayoutManager(activity)

        // TODO controllare il valore isFavorite passato al AnimalAdapter
        recycleView.layoutManager = linearLayoutManager
        animalAdapter = AnimalAdapter(animalShowcaseList, this, this, isFav)
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

    // TODO rivedere la funzione per piu animali
    override fun toogleFavoriteState(btnFavorite: ImageButton, animalShowcase: AnimalShowcase) {
        viewLifecycleOwner.lifecycleScope.launch {
            //Cambia l'icona del preferito
            val iconResource = if(!animalViewModel.getFavoritesNames().contains(animalShowcase.name)) {
                R.drawable.favorite_fill_icon
            }else {
                R.drawable.favorite_icon
            }
            if(!animalViewModel.getFavoritesNames().contains(animalShowcase.name)) {
                animalViewModel.addFavoriteAnimal(animalShowcase.name)
            }else {
                animalViewModel.removeFavoriteAnimal(animalShowcase.name)
            }
            val favoriteDrawable = AppCompatResources.getDrawable(btnFavorite.context, iconResource)
            btnFavorite.setImageDrawable(favoriteDrawable)
            val favoritesNames: List<String> = animalViewModel.getFavoritesNames()
            if (favoritesNames != null && !favoritesNames.isEmpty()) {
                val firstFavoriteName = favoritesNames[0]
            }else {
            }
        }
    }

}