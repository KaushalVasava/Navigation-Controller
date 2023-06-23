package com.lasuak.navigationcontroller.project1.Fragments

import android.app.PendingIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.findNavController
import com.lasuak.navigationcontroller.project1.CHANNEL_ID
import com.lasuak.navigationcontroller.R
import com.lasuak.navigationcontroller.databinding.FragmentChooseReceiverBinding

class ChooseReceiverFragment : Fragment(R.layout.fragment_choose_receiver){
    lateinit var binding : FragmentChooseReceiverBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseReceiverBinding.inflate(inflater,container,false)

        binding.btnNext.setOnClickListener {
            val receiverName = binding.receiverName.text.toString()

            //EXPLICIT DEEP LINK
            //  .setArguments(SendCashFragmentArgs(receiverName).toBundle())
            // above line throw exception when Direct use SendCashFragmentArgs().ToBundle()
            // so we use SendCashFragmentArgs.Builder(receiverName).build().toBundle()
            val pendingIntent = NavDeepLinkBuilder(requireContext())
                .setGraph(R.navigation.main_nav_graph)
                .setDestination(R.id.sendCashFragment)
                .setArguments(SendCashFragmentArgs.Builder(
                        receiverName
                    ).build().toBundle())
                .createPendingIntent()

//            val pendingIntent = it.findNavController()
//                .createDeepLink()
//                .setGraph(R.navigation.main_nav_graph)
//                .setDestination(R.id.sendCashFragment)
//                .setArguments(SendCashFragmentArgs(receiverName).toBundle())
//                .createPendingIntent()

            showNotification(pendingIntent,receiverName)

        /**  ---- ARGUMENTS PASS FROM ONE FRAGMENT TO ANOTHER FRAGMENT ----
             Argument receiverName is mandatory,
             If you give a default value to the argument then it is optional to pass in below method

            There are 2 types of Default values for argument
             1. Make default value for args in destination
             2. Make default value for args in particular sender fragments
             (or in particular action) and destination fragment args is not default
             --> Precedence of action default argument is higher than destination default argument
        */
            val action =ChooseReceiverFragmentDirections.actionChooseReceiverFragmentToSendCashFragment(
                receiverName
            )
            it.findNavController().navigate(action)
        }
        binding.btnCancel.setOnClickListener {
            //go to home fragment
            it.findNavController().popBackStack()
        }
        return binding.root
    }
    private fun showNotification(pendingIntent: PendingIntent,receiverName: String){
        val notification = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle("Complete Transaction")
            .setContentText("Send money to $receiverName")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(requireContext()).notify(102,notification)
    }
}