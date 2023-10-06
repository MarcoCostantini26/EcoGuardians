package com.example.ecoguardians

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Search : Fragment(), AnimalAdapter.ItemClickListener {

    private lateinit var editTextSearch: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: AnimalAdapter
    private lateinit var animalShowcaseList: ArrayList<AnimalShowcase>
    private lateinit var filteredItems: ArrayList<AnimalShowcase>

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        /*animalShowcaseList = ArrayList()
        animalShowcaseList.add(AnimalShowcase(R.drawable.eco__1_, "Animale1"))
        animalShowcaseList.add(AnimalShowcase(R.drawable.eco__1_, "Animale2"))
        animalShowcaseList.add(AnimalShowcase(R.drawable.eco__1_, "Animale3"))
        animalShowcaseList.add(AnimalShowcase(R.drawable.eco__1_, "Animale4"))
        animalShowcaseList.add(AnimalShowcase(R.drawable.eco__1_, "Animale5"))
        animalShowcaseList.add(AnimalShowcase(R.drawable.eco__1_, "Animale6"))

        editTextSearch = view.findViewById(R.id.editTextSearch)
        recyclerView = view.findViewById(R.id.recyclerView)

        itemAdapter = AnimalAdapter(animalShowcaseList, this)
        recyclerView.adapter = itemAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Aggiungi un TextWatcher per la ricerca in tempo reale
        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // Filtra gli elementi in base al testo inserito
                val searchText = s.toString().toLowerCase()
                filteredItems = animalShowcaseList.filter { it.name.toLowerCase().contains(searchText) } as ArrayList<AnimalShowcase>
                itemAdapter = AnimalAdapter(filteredItems, this@Search)
                recyclerView.adapter = itemAdapter
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
*/
        return view
    }

    override fun onItemClick(animalShowcase: AnimalShowcase) {
        /*
        val fragmentDetailed = DetailedFragment.newInstance(animalShowcase.image, animalShowcase.name)
        val transaction : FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragmentDetailed)
        transaction.commit()

         */
    }


}