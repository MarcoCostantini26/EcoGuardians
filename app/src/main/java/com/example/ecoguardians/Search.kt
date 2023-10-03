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
    /*private lateinit var animalsLt: ListView
    // creating array adapter for listview
    lateinit var listAdapter: ArrayAdapter<String>
    // creating array list for listview
    lateinit var animalsList: ArrayList<String>;
    // creating variable for search-view
    private lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        // initializing variables of list view with their ids.
        animalsLt = view.findViewById(R.id.list_of_animals_view)
        searchView = view.findViewById(R.id.search_animal)
        // initializing list and adding data to list
        animalsList = ArrayList()
        animalsList.add("Koala")
        animalsList.add("Leone")
        animalsList.add("Lince")
        animalsList.add("Ghepardo")
        // initializing list adapter and setting layout
        // for each list view item and adding array list to it.
        val context = requireContext()
        listAdapter = ArrayAdapter<String>(
            context,
            android.R.layout.simple_list_item_1,
            animalsList
        )
        // on below line setting list
        // adapter to our list view.
        animalsLt.adapter = listAdapter
        // on below line we are adding on query
        // listener for our search view.
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // on below line we are checking
                // if query exist or not.
                if (animalsList.contains(query)) {
                    // if query exist within list we
                    // are filtering our list adapter.
                    listAdapter.filter.filter(query)
                } else {
                    // if query is not present we are displaying
                    // a toast message as no  data found..
                    Toast.makeText(context, "No Language found..", Toast.LENGTH_LONG)
                        .show()
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                // if query text is change in that case we
                // are filtering our adapter with
                // new text on below line.
                listAdapter.filter.filter(newText)
                return false
            }
        })
        return view
    }*/
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
        animalShowcaseList = ArrayList()
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

        return view
    }

    override fun onItemClick(animalShowcase: AnimalShowcase) {
        val fragmentDetailed = DetailedFragment.newInstance(animalShowcase.image, animalShowcase.name)
        val transaction : FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragmentDetailed)
        transaction.commit()
    }
}