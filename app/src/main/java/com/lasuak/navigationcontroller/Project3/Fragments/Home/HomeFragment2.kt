package com.lasuak.navigationcontroller.Project3.Fragments.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.lasuak.navigationcontroller.R
import com.lasuak.navigationcontroller.databinding.FragmentHomeBinding

class HomeFragment2 : Fragment(R.layout.fragment_home) {
    lateinit var binding : FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.viewBalance.setOnClickListener {
          /**  NAVIGATION METHODS
          Method-1. Navigation id
            (Without Animation) ->  it.findNavController().navigate(R.id.viewBalanceFragment)
            (With Animation) ->
                val navOptions  = NavOptions.Builder()
                    .setEnterAnim(R.anim.slide_in_right)
                    .setExitAnim(R.anim.slide_out_left)
                    .setPopEnterAnim(R.anim.slide_in_left)
                    .setPopEnterAnim(R.anim.slide_out_right)
                    .build()
                it.findNavController().navigate(R.id.viewBalanceFragment,null,navOptions)

          Method-2. Action id-> it.findNavController().navigate(R.id.action_homeFragment_to_viewBalanceFragment)
          Method-3. Action object with type safe args ,below */
          val action = HomeFragment2Directions.actionHomeFragment2ToViewBalanceFragment2()
            it.findNavController().navigate(action)
        }
        binding.viewTransition.setOnClickListener {
            val action =HomeFragment2Directions.actionHomeFragment2ToViewTransactionFragment2()
            it.findNavController().navigate(action)
        }
        binding.sendMoney.setOnClickListener {
            val action =HomeFragment2Directions.actionHomeFragment2ToChooseReceiverFragment2()
            it.findNavController().navigate(action)
        }
        return view
    }
}