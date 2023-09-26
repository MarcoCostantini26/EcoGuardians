package com.example.ecoguardians

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecoguardians.databinding.FragmentSecondBinding
import com.example.ecoguardians.ui.login.SigninFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment(), AnimalAdapter.ItemClickListener{

    private lateinit var animalList: ArrayList<Animal>
    private lateinit var recycleView: RecyclerView
    private lateinit var animalAdapter : AnimalAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view : View = inflater.inflate(R.layout.fragment_second, container, false)
        animalList =ArrayList()

        animalList.add(Animal(R.drawable.eco__1_, "Pene1"))
        animalList.add(Animal(R.drawable.eco__1_, "Pene2"))
        animalList.add(Animal(R.drawable.eco__1_, "Pene3"))
        animalList.add(Animal(R.drawable.eco__1_, "Pene4"))
        animalList.add(Animal(R.drawable.eco__1_, "Pene5"))
        animalList.add(Animal(R.drawable.eco__1_, "Pene6"))
        animalList.add(Animal(R.drawable.eco__1_, "Pene7"))
        animalList.add(Animal(R.drawable.eco__1_, "Pene8"))
        animalList.add(Animal(R.drawable.eco__1_, "Pene9"))

        initRecyclerView(view)

        return view

    }

    private fun initRecyclerView(view : View){
        recycleView = view.findViewById(R.id.recycleView)
        var linearLayoutManager : LinearLayoutManager = LinearLayoutManager(activity)

        recycleView.layoutManager = linearLayoutManager
        animalAdapter = AnimalAdapter(animalList, this)
        recycleView.adapter = animalAdapter
    }

    override fun onItemClick(animal: Animal) {
        val fragmentDetailed = DetailedFragment.newInstance(animal.image, animal.name)
        val transaction : FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragmentDetailed)
        transaction.commit()
    }
}