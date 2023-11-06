package com.example.ecoguardians

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.annotation.RequiresApi
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
import com.example.ecoguardians.viewModel.UserViewModelFactory
import com.example.ecoguardians.viewModel.UserViewModel
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondFragment : Fragment(), AnimalAdapter.ItemClickListener{

    private lateinit var animalShowcaseList: ArrayList<AnimalShowcase>
    private lateinit var recycleView: RecyclerView
    private lateinit var animalAdapter : AnimalAdapter
    private var isFav: ArrayList<Boolean> = ArrayList()
    private var animalNames: List<String> = emptyList()
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

    @RequiresApi(34)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_second, container, false)
        val animalViewModel by viewModels<AnimalViewModel> {
            AnimalViewModelFactory(repository = (requireActivity().application as EcoGuardiansApplication).animalRepository)
        }

        // Accedi all'attività
        val activity = activity as? MainActivity

        // Apply the style to the Toolbar title
        activity?.findViewById<MaterialToolbar>(R.id.toolbar)?.setTitleTextAppearance(requireContext(), R.style.ToolbarTitle)

        // Update the Toolbar title
        activity?.findViewById<MaterialToolbar>(R.id.toolbar)?.title = "List of animals"

        animalShowcaseList =ArrayList()

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                // Start the asynchronous request
                animalNames = withContext(Dispatchers.IO) {
                    animalViewModel.getName()
                }
                animalNames = animalNames.distinct()
                withContext(Dispatchers.Main) {
                    if (isAdded) {
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
                animalViewModel.isAnimalFavorite(names[0], userViewModel.getEmail()), animalViewModel.getLatitude(names[0]),
                animalViewModel.getLongitude(names[0])))

            animalShowcaseList.add(AnimalShowcase(animalViewModel.getImage(names[1]),names[1],
                animalViewModel.getNumberSpecies(names[1]), animalViewModel.getClassification(names[1]),
                animalViewModel.getPosition(names[1]), animalViewModel.getAverageLife(names[1]),
                animalViewModel.getDescription(names[1]), animalViewModel.getThreats(names[1]),
                animalViewModel.getWhatYouCanDo(names[1]), animalViewModel.getSeriousLink(names[1]),
                animalViewModel.isAnimalFavorite(names[1], userViewModel.getEmail()),
                animalViewModel.getLatitude(names[1]), animalViewModel.getLongitude(names[1])))

            animalShowcaseList.add(AnimalShowcase(animalViewModel.getImage(names[2]),names[2],
                animalViewModel.getNumberSpecies(names[2]), animalViewModel.getClassification(names[2]),
                animalViewModel.getPosition(names[2]), animalViewModel.getAverageLife(names[2]),
                animalViewModel.getDescription(names[2]), animalViewModel.getThreats(names[2]),
                animalViewModel.getWhatYouCanDo(names[2]), animalViewModel.getSeriousLink(names[2]),
                animalViewModel.isAnimalFavorite(names[2], userViewModel.getEmail()),
                animalViewModel.getLatitude(names[2]), animalViewModel.getLongitude(names[2])))

            isFav.add(animalViewModel.isAnimalFavorite(names[0], userViewModel.getEmail()))
            isFav.add(animalViewModel.isAnimalFavorite(names[1], userViewModel.getEmail()))
            isFav.add(animalViewModel.isAnimalFavorite(names[2], userViewModel.getEmail()))
        }
        animalAdapter.notifyDataSetChanged()
    }

    private fun initRecyclerView(view : View){
        recycleView = view.findViewById(R.id.recycleView)
        val linearLayoutManager : LinearLayoutManager = LinearLayoutManager(activity)

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

    override fun toogleFavoriteState(btnFavorite: ImageButton, animalShowcase: AnimalShowcase) {
        viewLifecycleOwner.lifecycleScope.launch {
            // Change the bookmark icon
            val iconResource = if(!animalViewModel.getFavoritesNames(userViewModel.getEmail()).contains(animalShowcase.name)) {
                R.drawable.favorite_fill_icon
            }else {
                R.drawable.favorite_icon
            }
            if(!animalViewModel.getFavoritesNames(userViewModel.getEmail()).contains(animalShowcase.name)) {
                animalViewModel.addFavoriteAnimal(animalShowcase.name, userViewModel.getEmail())
                if(!badgeViewModel.firstComplete(2, userViewModel.getEmail())) {
                    // Send notification when badge is completed
                    badgeViewModel.setFirstComplete(2, userViewModel.getEmail())
                    sendBadgeNotificationCompleted(2, badgeViewModel)
                }
            }else {
                animalViewModel.removeFavoriteAnimal(animalShowcase.name, userViewModel.getEmail())
            }
            val favoriteDrawable = AppCompatResources.getDrawable(btnFavorite.context, iconResource)
            btnFavorite.setImageDrawable(favoriteDrawable)
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
