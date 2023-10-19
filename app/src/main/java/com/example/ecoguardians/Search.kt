package com.example.ecoguardians

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecoguardians.viewModel.AnimalViewModel
import com.example.ecoguardians.viewModel.AnimalViewModelFactory
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.collections.filter

class Search : Fragment(), AnimalAdapter.ItemClickListener {

    private lateinit var editTextSearch: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: AnimalAdapter
    private lateinit var animalShowcaseList: ArrayList<AnimalShowcase>
    private lateinit var filteredItems: ArrayList<AnimalShowcase>
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var favoriteOption: TextView
    private lateinit var allAnimalOption: Button
    private var isFav: ArrayList<Boolean> = ArrayList()
    private val animalViewModel by viewModels<AnimalViewModel> {
        AnimalViewModelFactory(repository = (requireActivity().application as EcoGuardiansApplication).animalRepository)
    }

        @SuppressLint("NotifyDataSetChanged")
        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        val animalViewModel by viewModels<AnimalViewModel> {
            AnimalViewModelFactory(repository = (requireActivity().application as EcoGuardiansApplication).animalRepository)
        }
        sharedPreferences = requireContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

        animalShowcaseList = ArrayList()
        // Creo una coroutine per richiamare le query suspend getName
        CoroutineScope(Dispatchers.Main).launch {
            val name = animalViewModel.getName()[0]
            val name2 = animalViewModel.getName()[1]
            val tigre = animalViewModel.getName()[2]
            animalShowcaseList.add(AnimalShowcase(R.drawable.koala, name, animalViewModel.isAnimalFavorite(name)))
            animalShowcaseList.add(AnimalShowcase(R.drawable.orsopolare, name2, animalViewModel.isAnimalFavorite(name)))
            animalShowcaseList.add(AnimalShowcase(R.drawable.tigre, tigre, animalViewModel.isAnimalFavorite(name)))
            isFav.add(animalViewModel.isAnimalFavorite(name))
            isFav.add(animalViewModel.isAnimalFavorite(name2))
            isFav.add(animalViewModel.isAnimalFavorite(tigre))
            onIsFavChanged(isFav)
            itemAdapter.notifyDataSetChanged()
        }

        editTextSearch = view.findViewById(R.id.editTextSearch)
        recyclerView = view.findViewById(R.id.recyclerView)
        favoriteOption = view.findViewById(R.id.favoriteFilter)
        allAnimalOption = view.findViewById(R.id.allAnimalFilter)

        itemAdapter = AnimalAdapter(animalShowcaseList, this, this, isFav)
        recyclerView.adapter = itemAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Aggiungi un TextWatcher per la ricerca in tempo reale
        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // Filtra gli elementi in base al testo inserito
                val searchText = s.toString().lowercase()
                filteredItems = animalShowcaseList.filter { it.name.lowercase().contains(searchText) } as ArrayList<AnimalShowcase>
                itemAdapter = AnimalAdapter(filteredItems, this@Search, this@Search, isFav)
                recyclerView.adapter = itemAdapter
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

         // Aggiungi un listener per il cambio di stato della tastiera
        editTextSearch.setOnFocusChangeListener { _, hasFocus ->
            updateBottomAppBarAndFabVisibility(hasFocus)
        }

        favoriteOption.setOnClickListener {
            // Applica il filtro e aggiorna la visualizzazione del RecyclerView
            viewLifecycleOwner.lifecycleScope.launch {
                val filteredList = animalShowcaseList.filter { animalViewModel.getFavoritesNames().contains(it.name) }
                updateRecyclerView(filteredList)
            }
        }

        allAnimalOption.setOnClickListener {
            // Applica il filtro e aggiorna la visualizzazione del RecyclerView
            viewLifecycleOwner.lifecycleScope.launch {
                val filteredList = animalShowcaseList.filter { animalViewModel.getName().contains(it.name) }
                updateRecyclerView(filteredList)
            }
        }

        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateRecyclerView(filteredAnimals: List<AnimalShowcase>) {
        val filteredFavorite = ArrayList<Boolean>()
        viewLifecycleOwner.lifecycleScope.launch {
            for (i in filteredAnimals) {
                filteredFavorite.add(animalViewModel.isAnimalFavorite(i.name))    // legge sempre falso
            }
            itemAdapter.filter(filteredAnimals, filteredFavorite)
            itemAdapter.notifyDataSetChanged()
        }
    }

    private fun onIsFavChanged(newIsFav: ArrayList<Boolean>) {
        isFav = newIsFav

        // Aggiorno la vista con il nuovo valore di isFav
        updateAdapterWithIsFav(isFav)
    }

    private fun updateAdapterWithIsFav(isFav: ArrayList<Boolean>) {
        // Aggiorno l'adapter con il nuovo valore di isFav
        itemAdapter = AnimalAdapter(animalShowcaseList, this, this, isFav)
        recyclerView.adapter = itemAdapter
    }

    // Quando esce la tastiera per la ricerca "nascondo" la bottomAppBar e il floatingActionBar
    private fun updateBottomAppBarAndFabVisibility(isKeyboardOpen: Boolean) {
        val bottomAppBar = requireActivity().findViewById<BottomAppBar>(R.id.bottom_menu)
        val fab = requireActivity().findViewById<FloatingActionButton>(R.id.fab)

        bottomAppBar.visibility = if (isKeyboardOpen) View.GONE else View.VISIBLE
        fab.visibility = if (isKeyboardOpen) View.GONE else View.VISIBLE
    }

    override fun onItemClick(animalShowcase: AnimalShowcase) {
        val fragmentDetailed = DetailedFragment.newInstance(animalShowcase.image, animalShowcase.name, animalShowcase.numberSpecies,
            animalShowcase.classification, animalShowcase.averageLife, animalShowcase.position, animalShowcase.description,
            animalShowcase.threats, animalShowcase.whatYouCanDo, animalShowcase.seriousLink, animalShowcase.latitude,
            animalShowcase.longitude)
        val transaction : FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragmentDetailed)
        transaction.commit()
    }

    @SuppressLint("NotifyDataSetChanged")
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
                isFav.add(animalViewModel.isAnimalFavorite(animalShowcase.name))
            }else {
                animalViewModel.removeFavoriteAnimal(animalShowcase.name)
                isFav.remove(animalViewModel.isAnimalFavorite(animalShowcase.name))
            }

            val favoriteDrawable = AppCompatResources.getDrawable(btnFavorite.context, iconResource)
            btnFavorite.setImageDrawable(favoriteDrawable)

            // Aggiorna l'elenco dei preferiti nell'adapter e notifica il cambiamento
            isFav.clear()
            val name = animalViewModel.getName()[0]
            val name2 = animalViewModel.getName()[1]
            val tigre = animalViewModel.getName()[2]
            isFav.add(animalViewModel.isAnimalFavorite(name))
            isFav.add(animalViewModel.isAnimalFavorite(name2))
            isFav.add(animalViewModel.isAnimalFavorite(tigre))
            itemAdapter.updateFavorites(isFav)
        }
    }

}
