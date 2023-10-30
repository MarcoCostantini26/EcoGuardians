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
import com.example.ecoguardians.viewModel.UserViewModel
import com.example.ecoguardians.viewModel.UserViewModelFactory
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
    private lateinit var favoriteOption: TextView
    private lateinit var allAnimalOption: Button
    private lateinit var isFav: ArrayList<Boolean>
    private val animalViewModel by viewModels<AnimalViewModel> {
        AnimalViewModelFactory(repository = (requireActivity().application as EcoGuardiansApplication).animalRepository)
    }
    private val userViewModel by viewModels<UserViewModel> {
        UserViewModelFactory(
            repository = (requireActivity().application as EcoGuardiansApplication).userRepository,
            animalRepository = (requireActivity().application as EcoGuardiansApplication).animalRepository
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isFav = ArrayList()
    }
        @SuppressLint("NotifyDataSetChanged")
        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        animalShowcaseList = ArrayList()

        // Creo una coroutine per richiamare le query suspend
        CoroutineScope(Dispatchers.Main).launch {
            val animalsByUser = userViewModel.getUserWithAnimals(userViewModel.getEmail())
            val countUser = userViewModel.countUsers()

            val name = animalsByUser.animals[0].animal
            val orsoPolare = animalsByUser.animals[1].animal
            val tigre = animalsByUser.animals[2].animal
            animalShowcaseList.add(AnimalShowcase(R.drawable.koala, name, animalsByUser.animals[0].favorite))
            animalShowcaseList.add(AnimalShowcase(R.drawable.orsopolare, orsoPolare, animalsByUser.animals[1].favorite))
            animalShowcaseList.add(AnimalShowcase(R.drawable.tigre, tigre, animalsByUser.animals[2].favorite))
            isFav.add(animalsByUser.animals[0].favorite)
            isFav.add(animalsByUser.animals[1].favorite)
            isFav.add(animalsByUser.animals[2].favorite)

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

        // Filtri
        favoriteOption.setOnClickListener {
            // Applica il filtro e aggiorna la visualizzazione del RecyclerView
            viewLifecycleOwner.lifecycleScope.launch {
                val filteredList = animalShowcaseList.filter { animalViewModel.getFavoritesNames(userViewModel.getEmail()).contains(it.name) }
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
                filteredFavorite.add(animalViewModel.isAnimalFavorite(i.name, userViewModel.getEmail()))
            }
            itemAdapter.filter(filteredAnimals, filteredFavorite)
            itemAdapter.notifyDataSetChanged()
        }
    }

    private fun onIsFavChanged(newIsFav: ArrayList<Boolean>) {
        isFav = newIsFav
        updateAdapterWithIsFav(isFav)
    }

    private fun updateAdapterWithIsFav(isFav: ArrayList<Boolean>) {
        // Aggiorno l'adapter con il nuovo valore di isFav
        itemAdapter = AnimalAdapter(animalShowcaseList, this, this, isFav)
        recyclerView.adapter = itemAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
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
            val iconResource = if(!animalViewModel.getFavoritesNames(userViewModel.getEmail()).contains(animalShowcase.name)) {
                R.drawable.favorite_fill_icon
            }else {
                R.drawable.favorite_icon
            }
            if(!animalViewModel.getFavoritesNames(userViewModel.getEmail()).contains(animalShowcase.name)) {
                animalViewModel.addFavoriteAnimal(animalShowcase.name, userViewModel.getEmail())
            }else {
                animalViewModel.removeFavoriteAnimal(animalShowcase.name, userViewModel.getEmail())
            }

            val favoriteDrawable = AppCompatResources.getDrawable(btnFavorite.context, iconResource)
            btnFavorite.setImageDrawable(favoriteDrawable)

            // Aggiorna l'elenco dei preferiti nell'adapter e notifica il cambiamento
            val newIsFav = animalShowcaseList.map { animal ->
                animalViewModel.isAnimalFavorite(animal.name, userViewModel.getEmail())
            } as ArrayList<Boolean>
            onIsFavChanged(newIsFav)
            itemAdapter.updateFavorites(isFav)
        }
    }

}
