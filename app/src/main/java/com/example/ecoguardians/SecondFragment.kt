package com.example.ecoguardians

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecoguardians.databinding.FragmentSecondBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private lateinit var recycleView: RecyclerView
    private lateinit var animalList: ArrayList<Animal>
    private lateinit var animalAdapter: AnimalAdapter


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
         return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycleView = view.findViewById(R.id.recycleView)
        recycleView.setHasFixedSize(true)
        recycleView.layoutManager = LinearLayoutManager(this.context)

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

        animalAdapter = AnimalAdapter(animalList)
        recycleView.adapter = animalAdapter

        animalAdapter.onItemClick = {
            val intent = Intent(context, DetailedActivity2::class.java)
            intent.putExtra("animal", it)
            startActivity(intent)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}