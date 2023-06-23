package com.lasuak.navigationcontroller.project1.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lasuak.navigationcontroller.databinding.FragmentDialogBinding

class DialogFragment : BottomSheetDialogFragment() {
    private lateinit var binding : FragmentDialogBinding
    private val args: DialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialogBinding.inflate(inflater, container, false)

        val receiverName = args.receiverName
        val amount = args.amount

        binding.detailText.text = "Do you want to send Rs.$amount to $receiverName?"
        binding.btnYes.setOnClickListener {
            Toast.makeText(requireContext(),"Rs.$amount Received by $receiverName",Toast.LENGTH_SHORT).show()
            dismiss()
        }
        binding.btnNo.setOnClickListener {
            dismiss()
        }
        return binding.root
    }
}