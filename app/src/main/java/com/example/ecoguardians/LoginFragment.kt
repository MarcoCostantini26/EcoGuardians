package com.example.ecoguardians

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.ecoguardians.viewModel.UserViewModel
import com.example.ecoguardians.viewModel.UserViewModelFactory
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val userViewModel by viewModels<UserViewModel> {
            UserViewModelFactory(
                repository = (requireActivity().application as EcoGuardiansApplication).userRepository,
                animalRepository = (requireActivity().application as EcoGuardiansApplication).animalRepository
            )
        }

        val view = inflater.inflate(R.layout.fragment_login, container, false)
        view.findViewById<TextView>(R.id.donthaveaccount).setOnClickListener(){
            val fragmentList = RegisterFragment()
            val transaction2 : FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction2.replace(R.id.main_container, fragmentList)
            transaction2.commit()
        }

        val email = view.findViewById<EditText>(R.id.inputEmailLogin)
        val password = view.findViewById<EditText>(R.id.inputPasswordLogin)
        view.findViewById<Button>(R.id.loginButton).setOnClickListener{
            viewLifecycleOwner.lifecycleScope.launch {
                try{
                    if(!TextUtils.isEmpty(email.text) && !TextUtils.isEmpty(password.text)){

                        if(userViewModel.isPasswordCorrect(email.text.toString(), password.text.toString()) == 1){
                            userViewModel.setSessionTrue(email.text.toString())

                            val fragmentHome = Home()
                            val transaction : FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                            transaction.replace(R.id.main_container, fragmentHome)
                            transaction.commit()
                        }else{
                            password.error = "Password errata"
                        }
                    }else{
                        if(TextUtils.isEmpty(email.text)){
                            email.error = "Campo necessario"
                        }
                        if(TextUtils.isEmpty(password.text)){
                            password.error = "Campo necessario"
                        }
                    }
                }catch (_: Exception){}
            }
        }
        // Inflate the layout for this fragment
        return view
    }
}