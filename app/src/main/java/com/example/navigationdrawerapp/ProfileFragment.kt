package com.example.navigationdrawerapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.navigationdrawerapp.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding
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
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (appSharedViewModel.user == null) {
            findNavController().navigate(R.id.action_profileFragment_to_registrationFragment)
        }
        initViews()
        //
        Glide.with(requireContext())
            .load(R.drawable.river)
            .circleCrop()
            .into(binding.imageView)
    }

    private fun initViews() {
        binding.textViewName.text = appSharedViewModel.user?.name
        binding.textViewNationalID.text = appSharedViewModel.user?.nationalID
        binding.textViewPhone.text = appSharedViewModel.user?.phone
    }

}