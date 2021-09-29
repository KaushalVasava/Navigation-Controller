package com.lasuak.navigationcontroller.Project1.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.lasuak.navigationcontroller.R
import com.lasuak.navigationcontroller.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment(R.layout.fragment_notifications)
{
    private lateinit var binding: FragmentNotificationsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationsBinding.inflate(inflater,container,false)
        binding.listView.adapter = ArrayAdapter(requireContext(),android.R.layout.simple_dropdown_item_1line,getNotification())
        return binding.root
    }

    private fun getNotification(): List<String> {
        val notifications = mutableListOf<String>()
        for(i in 1..20){
            notifications.add("Notification #$i")
        }
        return notifications
    }
}