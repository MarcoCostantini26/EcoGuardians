package com.example.ecoguardians

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.ecoguardians.databinding.FragmentFirstBinding


/**
 * A simple [Fragment] subclass.
 * Use the [DetailedActivity.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailedActivity : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.detailed_activity, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animal = activity?.intent?.getParcelableExtra("animal", Animal::class.java)
        if(animal != null){
            val textView : TextView = view.findViewById(R.id.detailedActivityTV)
            val imageView : ImageView = view.findViewById(R.id.detailedActivityIV)

            textView.text = animal.name
            imageView.setImageResource(animal.image)

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}