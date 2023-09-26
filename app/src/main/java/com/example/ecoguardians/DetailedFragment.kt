package com.example.ecoguardians

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


/**
 * A simple [Fragment] subclass.
 * Use the [DetailedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detailed, container, false)

        val textView : TextView = view.findViewById(R.id.detailedActivityTV)
        val imageView : ImageView = view.findViewById(R.id.detailedActivityIV)

        textView.text = arguments?.getString(ARG_PARAM2)
        arguments?.let { imageView.setImageResource(it.getInt(ARG_PARAM1)) }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailedFragment.
         */
        @JvmStatic
        fun newInstance(image : Int, name: String) =
            DetailedFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, image)
                    putString(ARG_PARAM2, name)
                }
            }
    }
}