package com.example.navigationdrawerapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.navigationdrawerapp.databinding.FragmentRegistrationBinding


class RegistrationFragment : Fragment() {
   lateinit var binding: FragmentRegistrationBinding
    private val appSharedViewModel: SharedViewModelApp by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentRegistrationBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()


        binding.buttonSave.setOnClickListener {

                appSharedViewModel.user= User(binding.editTextName.text.toString(),
                binding.editTextNationalId.text.toString(),
                binding.editTextPhone.text.toString())

        }
    }

    private fun initViews() {
        if (appSharedViewModel.user!=null)
        {
            binding.editTextName.setText(appSharedViewModel.user!!.name)
            binding.editTextNationalId.setText(appSharedViewModel.user!!.nationalID)
            binding.editTextPhone.setText(appSharedViewModel.user!!.phone)
        }
    }


}