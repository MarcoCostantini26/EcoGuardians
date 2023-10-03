package com.example.ecoguardians

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment(), AnimalAdapter.ItemClickListener{

    private lateinit var animalShowcaseList: ArrayList<AnimalShowcase>
    private lateinit var recycleView: RecyclerView
    private lateinit var animalAdapter : AnimalAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view : View = inflater.inflate(R.layout.fragment_second, container, false)
        animalShowcaseList =ArrayList()

        animalShowcaseList.add(AnimalShowcase(R.drawable.eco__1_, "Pene1"))
        animalShowcaseList.add(AnimalShowcase(R.drawable.eco__1_, "Pene2"))
        animalShowcaseList.add(AnimalShowcase(R.drawable.eco__1_, "Pene3"))
        animalShowcaseList.add(AnimalShowcase(R.drawable.eco__1_, "Pene4"))
        animalShowcaseList.add(AnimalShowcase(R.drawable.eco__1_, "Pene5"))
        animalShowcaseList.add(AnimalShowcase(R.drawable.eco__1_, "Pene6"))
        animalShowcaseList.add(AnimalShowcase(R.drawable.eco__1_, "Pene7"))
        animalShowcaseList.add(AnimalShowcase(R.drawable.eco__1_, "Pene8"))
        animalShowcaseList.add(AnimalShowcase(R.drawable.eco__1_, "Pene9"))

        initRecyclerView(view)

        return view

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