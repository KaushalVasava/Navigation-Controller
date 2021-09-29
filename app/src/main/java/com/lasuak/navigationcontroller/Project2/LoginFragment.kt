package com.lasuak.navigationcontroller.Project2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lasuak.navigationcontroller.R
import com.lasuak.navigationcontroller.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding

    companion object{
        const val LOGIN_SUCCESSFUL = "LOGIN_SUCCESSFUL"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater,container,false)

        /** STORE LOGIN STATE USING SAVED-STATE-HANDLE
         *  This is use to store login status
         *  EXAMPLE --> If user is don't want to login then we have to provide mainFragment as a Destination
            for that we have to store previous back-stack entry of fragment using 'previousBackStackEntry!!.savedStateHandle'
         * --> SaveStateHandle work same as a SavedInstance State
        */
        val savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
        savedStateHandle.set(LOGIN_SUCCESSFUL,false)

        //Make User null for first time login
        UserLoginInfo.user = null

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            //authentication

            UserLoginInfo.user = User(username)
            //2nd last fragment in backstack is profile fragment
            //so when login is successful then goto profile fragment for that call popBackStack()
            findNavController().popBackStack()
        }
         return binding.root
    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
//        savedStateHandle.set(LOGIN_SUCCESSFUL,false)
//
//
//    }
}