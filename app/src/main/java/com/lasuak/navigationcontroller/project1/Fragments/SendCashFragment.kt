package com.lasuak.navigationcontroller.project1.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.lasuak.navigationcontroller.project1.DefaultData
import com.lasuak.navigationcontroller.R
import com.lasuak.navigationcontroller.databinding.FragmentSendCashBinding

class SendCashFragment : Fragment(R.layout.fragment_send_cash) {
    lateinit var binding: FragmentSendCashBinding
    private val args: SendCashFragmentArgs by navArgs()
    //DATA RECEIVED USING SAFE-ARGS PLUGIN

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSendCashBinding.inflate(inflater, container, false)
//        val receiverName = arguments?.getString("name")

            //after live data when default value already exist
        binding.receiverText.setText(DefaultData.defaultValue.value.toString())
        DefaultData.defaultValue.observe(viewLifecycleOwner){
            binding.receiverText.setText(it.toString())
        }

        val receiverName = args.receiverName
        binding.receiverName.text = "Cash received by $receiverName"

        // val amount  = binding.receiverText.text.toString().toLong()
        binding.btnSend.setOnClickListener {
            if(binding.receiverText.text.isEmpty())
                return@setOnClickListener
            val amount = binding.receiverText.text.toString()
            val action =SendCashFragmentDirections.actionSendCashFragmentToDialogFragment(
                    receiverName,
                    amount.toLong()
                )
            it.findNavController().navigate(action)
        }
        binding.btnCancel.setOnClickListener {
            //go to home fragment and remove previous fragment from back-stack
            it.findNavController().popBackStack(R.id.homeFragment,true)
        }
        binding.btnDone.setOnClickListener{
            /** Go to Home Fragment
             * 1. popUpTo -> This method remove fragments between source to destination and clear back stack
                             or remove previous fragments from back stack whenever rich to home fragment or main fragment.
             * 2. popUpToInclude -> If it is 'true' then destination or Home Fragment do not pop up for 'two' times
             * --> This methods use for remove circular process between fragments.
             * EXAMPLE -> For application like 'Instagram' we can use this circular fragments
               for retain our back fragments in the back stack for previous navigation
             */
            val action =SendCashFragmentDirections.actionSendCashFragmentToHomeFragment()
            it.findNavController().navigate(action)
        }
        return binding.root
    }
}