package com.lasuak.navigationcontroller.Project2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.lasuak.navigationcontroller.R
import com.lasuak.navigationcontroller.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /** RETRIEVE SAVED-HANDLE-VALUE
         * 1.  For that we have to fetch currentBackStackEntry in backstack using 'currentBackStackEntry'
            and fetch savedStateHandle value using 'currentBackStackEntry!!.savedStateHandle'
         * 2. After fetching savedStateHandle value we have to observe value and
              if value of "LOGIN_SUCCESSFUL" is false then go to the main fragment and clear back-stack.
         */
        val currentBackStackEntry = findNavController().currentBackStackEntry
        val savedStateHandle = currentBackStackEntry!!.savedStateHandle


        savedStateHandle.getLiveData<Boolean>(LoginFragment.LOGIN_SUCCESSFUL)
            .observe(currentBackStackEntry) {
                if (!it) {
                    Toast.makeText(
                        requireContext(),
                        "Please login to see your profile",
                        Toast.LENGTH_SHORT
                    ).show()
                    //fetching startDestination
                    val startDest = findNavController().graph.startDestination
                    val navOption = NavOptions.Builder()
                        .setPopUpTo(startDest, true)
                        .build()
                    findNavController().navigate(startDest, null, navOption)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        if (UserLoginInfo.user == null) {
            Toast.makeText(requireContext(), "Please login first", Toast.LENGTH_SHORT).show()

            val navOptions = NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_right)
                .setExitAnim(R.anim.slide_out_left)
                .setPopEnterAnim(R.anim.slide_in_left)
                .setPopExitAnim(R.anim.slide_out_right)
                .build()

            findNavController().navigate(R.id.loginFragment, null, navOptions)
        } else {
            val username = UserLoginInfo.user!!.username
            Toast.makeText(requireContext(), "Hi, $username", Toast.LENGTH_SHORT).show()

            //Pass argument with String resource
            val text = getString(R.string.welcome, username)
            binding.tvWelcome.text = text
        }

        return binding.root
    }
}