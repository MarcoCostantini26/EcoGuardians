package com.example.ecoguardians

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.content.res.AppCompatResources
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
import kotlin.properties.Delegates

class Search : Fragment(), AnimalAdapter.ItemClickListener {

    private lateinit var editTextSearch: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: AnimalAdapter
    private lateinit var animalShowcaseList: ArrayList<AnimalShowcase>
    private lateinit var filteredItems: ArrayList<AnimalShowcase>
    private lateinit var sharedPreferences: SharedPreferences
    private var isFav by Delegates.notNull<Boolean>()
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

        // Inizializzazione di isFav con il valore salvato nelle SharedPreferences
        isFav = sharedPreferences.getBoolean("isFav", false)

        animalShowcaseList = ArrayList()

        // Creo una coroutine per richiamare le query suspend getName
        CoroutineScope(Dispatchers.Main).launch {
            val name = animalViewModel.getName()[0]
            animalShowcaseList.add(AnimalShowcase(R.drawable.eco__1_, name))
            isFav = animalViewModel.isAnimalFavorite(name)
            onIsFavChanged(isFav)
            itemAdapter.notifyDataSetChanged()
        }

        editTextSearch = view.findViewById(R.id.editTextSearch)
        recyclerView = view.findViewById(R.id.recyclerView)

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

        return view
    }

    private fun onIsFavChanged(newIsFav: Boolean) {
        isFav = newIsFav

        // Aggiorno la vista con il nuovo valore di isFav
        updateAdapterWithIsFav(isFav)
    }

    private fun updateAdapterWithIsFav(isFav: Boolean) {
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
