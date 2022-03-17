package com.example.navigationdrawerapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
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
        if (appSharedViewModel.colorTheme == ThemeColor.Black) {
            activity?.setTheme(R.style.Theme_NavigationDrawerAppBlack)
        } else if (appSharedViewModel.colorTheme == ThemeColor.Red) {
            activity?.setTheme(R.style.Theme_NavigationDrawerAppRed)

        } else {
            activity?.setTheme(R.style.Theme_NavigationDrawerApp)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()


        binding.buttonSave.setOnClickListener {

                appSharedViewModel.user= User(binding.editTextName.text.toString(),
                binding.editTextNationalId.text.toString(),
                binding.editTextPhone.text.toString())
            findNavController().navigate(R.id.action_registrationFragment_to_homeFragment)

        }

        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_homeFragment)
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

    override fun onGetLayoutInflater(savedInstanceState: Bundle?): LayoutInflater {
        val inflater = super.onGetLayoutInflater(savedInstanceState)
        lateinit var contextThemeWrapper: Context
        when (appSharedViewModel.colorTheme) {
            ThemeColor.Red -> {
                contextThemeWrapper =
                    ContextThemeWrapper(requireContext(), R.style.Theme_NavigationDrawerAppRed)
            }
            ThemeColor.Black -> {
                contextThemeWrapper =
                    ContextThemeWrapper(requireContext(), R.style.Theme_NavigationDrawerAppBlack)
            }
            else -> {
                contextThemeWrapper =
                    ContextThemeWrapper(requireContext(), R.style.Theme_NavigationDrawerApp)
            }
        }
        return inflater.cloneInContext(contextThemeWrapper)
    }

}