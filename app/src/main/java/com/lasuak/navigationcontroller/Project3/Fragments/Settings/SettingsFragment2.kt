package com.lasuak.navigationcontroller.Project3.Fragments.Settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.lasuak.navigationcontroller.Project3.DefaultData
import com.lasuak.navigationcontroller.R
import com.lasuak.navigationcontroller.databinding.FragmentSettingsBinding

class SettingsFragment2 : Fragment(R.layout.fragment_settings) {
    lateinit var binding : FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding.defaultAmount.setText(DefaultData.defaultValue.value.toString())
        binding.btnSave.setOnClickListener {
            val defaultAmount = binding.defaultAmount.text.toString().toLong()
            DefaultData.defaultValue.value = defaultAmount
        }
        binding.btnAbout.setOnClickListener {
            //MainNavGraphDirection use for global action
            /** GLOBAL ACTIONS
             *  When we need to navigation from different fragments
                to one destination fragment then we use global action.
             *  We can pass arguments like in other local actions in this global action.
             */
            val action = SettingsFragment2Directions.actionSettingsFragment2ToAboutAppFragment2()
            it.findNavController().navigate(action)
        }
        return binding.root
    }
}