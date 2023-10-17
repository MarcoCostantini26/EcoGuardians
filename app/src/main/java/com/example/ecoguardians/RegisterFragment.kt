package com.example.ecoguardians

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.ecoguardians.data.User
import com.example.ecoguardians.viewModel.UserViewModel
import com.example.ecoguardians.viewModel.UserViewModelFactory
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val userViewModel by viewModels<UserViewModel> {
            UserViewModelFactory(repository = (requireActivity().application as EcoGuardiansApplication).userRepository)
        }

        val view = inflater.inflate(R.layout.fragment_register, container, false)
        view.findViewById<TextView>(R.id.alreadyHaveAnAccount).setOnClickListener(){
            val fragmentList = LoginFragment()
            val transaction2 : FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction2.replace(R.id.main_container, fragmentList)
            transaction2.commit()
        }

        val username = view.findViewById<EditText>(R.id.inputUsername)
        val email = view.findViewById<EditText>(R.id.inputEmail)
        val password = view.findViewById<EditText>(R.id.inputPassword)
        //controllare se l'email è gia usata
        view.findViewById<Button>(R.id.registerButton).setOnClickListener{
            viewLifecycleOwner.lifecycleScope.launch {
                try{

                    if(userViewModel.doesUserExists(email.text.toString()) == 0){

                            userViewModel.addUser(User(email.text.toString(), password.text.toString(), username.text.toString(), false))

                    }else{
                        email.error = "Email già utilizzata"
                    }

                }catch (_: Exception){}
            }
        }

        // Inflate the layout for this fragment
        return view
    }
}