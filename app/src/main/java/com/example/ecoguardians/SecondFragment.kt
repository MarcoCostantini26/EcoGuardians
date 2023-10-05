package com.example.ecoguardians

import android.os.Bundle
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
                        handleAnimalNames(animalNames)
                    }
                }
            } catch (e: Exception) {
            }
        }

        initRecyclerView(view)

        return view

    }

    private fun handleAnimalNames(names: List<String>) {
        if (names.isNotEmpty()) {
            animalShowcaseList.add(AnimalShowcase(R.drawable.eco__1_, names[0]))
        }else{
            animalShowcaseList.add(AnimalShowcase(R.drawable.eco__1_, "Nessun animale disponibile"))
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
        val fragmentDetailed = DetailedFragment.newInstance(animalShowcase.image, animalShowcase.name)
        val transaction : FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragmentDetailed)
        transaction.commit()
    }

}