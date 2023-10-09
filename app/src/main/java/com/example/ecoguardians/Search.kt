package com.example.ecoguardians

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton

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
                val searchText = s.toString().lowercase()
                filteredItems = animalShowcaseList.filter { it.name.lowercase().contains(searchText) } as ArrayList<AnimalShowcase>
                itemAdapter = AnimalAdapter(filteredItems, this@Search)
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

    // Quando esce la tastiera per la ricerca "nascondo" la bottomAppBar e il floatingActionBar
    private fun updateBottomAppBarAndFabVisibility(isKeyboardOpen: Boolean) {
        val bottomAppBar = requireActivity().findViewById<BottomAppBar>(R.id.bottom_menu)
        val fab = requireActivity().findViewById<FloatingActionButton>(R.id.fab)

        bottomAppBar.visibility = if (isKeyboardOpen) View.GONE else View.VISIBLE
        fab.visibility = if (isKeyboardOpen) View.GONE else View.VISIBLE
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
