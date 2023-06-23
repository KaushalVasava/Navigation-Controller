package com.lasuak.navigationcontroller.project2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lasuak.navigationcontroller.R
import com.lasuak.navigationcontroller.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater,container,false)

        binding.btnShowProfile.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToProfileFragment()
            findNavController().navigate(action)
        }
        return binding.root
    }
}