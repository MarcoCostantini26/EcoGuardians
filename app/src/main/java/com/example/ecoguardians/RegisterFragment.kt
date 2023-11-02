package com.example.ecoguardians

import android.content.ContentResolver
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
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
import com.example.ecoguardians.data.Badge
import com.example.ecoguardians.data.User
import com.example.ecoguardians.viewModel.BadgeViewModel
import com.example.ecoguardians.viewModel.BadgeViewModelFactory
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
            UserViewModelFactory(
                repository = (requireActivity().application as EcoGuardiansApplication).userRepository,
                animalRepository = (requireActivity().application as EcoGuardiansApplication).animalRepository
            )
        }

        val view = inflater.inflate(R.layout.fragment_register, container, false)
        view.findViewById<TextView>(R.id.alreadyHaveAnAccount).setOnClickListener(){
            val fragmentList = LoginFragment()
            val transaction2 : FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction2.replace(R.id.main_container, fragmentList)
            transaction2.commit()
        }

        val badgeViewModel by requireActivity().viewModels<BadgeViewModel> {
            BadgeViewModelFactory(repository = (requireActivity().application as EcoGuardiansApplication).badgeRepository)
        }

        val username = view.findViewById<EditText>(R.id.inputUsername)
        val email = view.findViewById<EditText>(R.id.inputEmail)
        val password = view.findViewById<EditText>(R.id.inputPassword)
        view.findViewById<Button>(R.id.registerButton).setOnClickListener{
            viewLifecycleOwner.lifecycleScope.launch {
                try{
                    if(!TextUtils.isEmpty(username.text) && !TextUtils.isEmpty(email.text) &&
                        !TextUtils.isEmpty(password.text)){
                        if(userViewModel.doesUserExists(email.text.toString()) == 0){
                            val profilePictureDrawable = R.drawable.profilepicture
                            userViewModel.addUser(User(email.text.toString(), password.text.toString(), username.text.toString(), false,
                                Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                                "://" + resources.getResourcePackageName(profilePictureDrawable) +
                                    "/" + resources.getResourceTypeName(profilePictureDrawable)
                                        + '/' + resources.getResourceEntryName(profilePictureDrawable))))
                            //badge db population
                            badgeViewModel.addBadge(
                                Badge(1, true, false, "Benvenuto Guardiano!", email.text.toString())
                            )
                            badgeViewModel.addBadge(
                                Badge(2, false, false, "Guardiano della natura", email.text.toString())
                            )
                            badgeViewModel.addBadge(
                                Badge(3, false, "Compagno ideale", email.text.toString())
                            )
                            badgeViewModel.addBadge(
                                Badge(4, false, "Salvatore di specie", email.text.toString())
                            )
                            val fragmentLogin = LoginFragment()
                            val transaction : FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                            transaction.replace(R.id.main_container, fragmentLogin)
                            transaction.commit()
                        }else{
                            email.error = "Email già utilizzata"
                        }
                    }else{
                        if(TextUtils.isEmpty(username.text)){
                            username.error = "Campo necessario"
                        }
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
