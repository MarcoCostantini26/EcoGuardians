package com.example.ecoguardians

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecoguardians.viewModel.AnimalViewModel
import com.example.ecoguardians.viewModel.AnimalViewModelFactory
import com.example.ecoguardians.viewModel.BadgeViewModel
import com.example.ecoguardians.viewModel.BadgeViewModelFactory
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

    private val badgeViewModel by viewModels<BadgeViewModel> {
        BadgeViewModelFactory(repository = (requireActivity().application as EcoGuardiansApplication).badgeRepository)
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

        // Create a coroutine to call suspend queries
        CoroutineScope(Dispatchers.Main).launch {
            val animalsByUser = userViewModel.getUserWithAnimals(userViewModel.getEmail())
            val name = animalsByUser.animals[0].animal
            val orsoPolare = animalsByUser.animals[1].animal
            val tigre = animalsByUser.animals[2].animal
            val rino = animalsByUser.animals[3].animal
            val elefante = animalsByUser.animals[4].animal
            val stambecco = animalsByUser.animals[5].animal
            val orsoMarsicano = animalsByUser.animals[6].animal
            val saola = animalsByUser.animals[7].animal
            val balenottera = animalsByUser.animals[8].animal
            val focaMonaca = animalsByUser.animals[9].animal
            animalShowcaseList.add(AnimalShowcase(R.drawable.koala, name, animalsByUser.animals[0].favorite))
            animalShowcaseList.add(AnimalShowcase(R.drawable.orsopolare, orsoPolare, animalsByUser.animals[1].favorite))
            animalShowcaseList.add(AnimalShowcase(R.drawable.tigre, tigre, animalsByUser.animals[2].favorite))
            animalShowcaseList.add(AnimalShowcase(R.drawable.rino_giava, rino, animalsByUser.animals[3].favorite))
            animalShowcaseList.add(AnimalShowcase(R.drawable.elefante_sumatra, elefante, animalsByUser.animals[4].favorite))
            animalShowcaseList.add(AnimalShowcase(R.drawable.stambecco, stambecco, animalsByUser.animals[5].favorite))
            animalShowcaseList.add(AnimalShowcase(R.drawable.orso_marsicano, orsoMarsicano, animalsByUser.animals[6].favorite))
            animalShowcaseList.add(AnimalShowcase(R.drawable.saola, saola, animalsByUser.animals[7].favorite))
            animalShowcaseList.add(AnimalShowcase(R.drawable.balenottera, balenottera, animalsByUser.animals[8].favorite))
            animalShowcaseList.add(AnimalShowcase(R.drawable.foca_monaca, focaMonaca, animalsByUser.animals[9].favorite))
            isFav.add(animalsByUser.animals[0].favorite)
            isFav.add(animalsByUser.animals[1].favorite)
            isFav.add(animalsByUser.animals[2].favorite)
            isFav.add(animalsByUser.animals[3].favorite)
            isFav.add(animalsByUser.animals[4].favorite)
            isFav.add(animalsByUser.animals[5].favorite)
            isFav.add(animalsByUser.animals[6].favorite)
            isFav.add(animalsByUser.animals[7].favorite)
            isFav.add(animalsByUser.animals[8].favorite)
            isFav.add(animalsByUser.animals[9].favorite)

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

        // Add a TextWatcher for real-time search
        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // Filter items based on entered text
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

        // Add a listener for keyboard state changes
        editTextSearch.setOnFocusChangeListener { _, hasFocus ->
            updateBottomAppBarAndFabVisibility(hasFocus)
        }

        // Filters
        favoriteOption.setOnClickListener {
            // Apply the filter and update the RecyclerView display
            viewLifecycleOwner.lifecycleScope.launch {
                val filteredList = animalShowcaseList.filter { animalViewModel.getFavoritesNames(userViewModel.getEmail()).contains(it.name) }
                updateRecyclerView(filteredList)
            }
        }

        allAnimalOption.setOnClickListener {
            // Apply the filter and update the RecyclerView display
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
        // Update the adapter with the new value of isFav
        itemAdapter = AnimalAdapter(animalShowcaseList, this, this, isFav)
        recyclerView.adapter = itemAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    // When the search keyboard comes out I "hide" the bottomAppBar and the floatingActionBar
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

            badgeViewModel.setCompletedTrue(userViewModel.getEmail(), 3)
            // Change the bookmark icon
            val iconResource = if(!animalViewModel.getFavoritesNames(userViewModel.getEmail()).contains(animalShowcase.name)) {
                R.drawable.favorite_fill_icon
            }else {
                R.drawable.favorite_icon
            }
            if(!animalViewModel.getFavoritesNames(userViewModel.getEmail()).contains(animalShowcase.name)) {
                animalViewModel.addFavoriteAnimal(animalShowcase.name, userViewModel.getEmail())
                if(!badgeViewModel.firstComplete(3, userViewModel.getEmail())) {
                    // Send notification when badge is completed
                    badgeViewModel.setFirstComplete(3, userViewModel.getEmail())
                    sendBadgeNotificationCompleted(3, badgeViewModel)
                }
            }else {
                animalViewModel.removeFavoriteAnimal(animalShowcase.name, userViewModel.getEmail())
            }

            val favoriteDrawable = AppCompatResources.getDrawable(btnFavorite.context, iconResource)
            btnFavorite.setImageDrawable(favoriteDrawable)

            // Updates the favorites list in the adapter and notifies you of the change
            val newIsFav = animalShowcaseList.map { animal ->
                animalViewModel.isAnimalFavorite(animal.name, userViewModel.getEmail())
            } as ArrayList<Boolean>
            onIsFavChanged(newIsFav)
            itemAdapter.updateFavorites(isFav)
        }
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

}
