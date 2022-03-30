package com.example.navigationdrawerapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
            if (areValidInputs()) {
                appSharedViewModel.user = User(
                    binding.editTextName.text.toString(),
                    binding.editTextNationalId.text.toString(),
                    binding.editTextPhone.text.toString()
                )
                if (binding.radioButtonYes.isChecked){
                    appSharedViewModel.user!!.keepPrivate=true
                }
                findNavController().navigate(R.id.action_registrationFragment_to_homeFragment)
            }
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
            if (appSharedViewModel.user!!.keepPrivate){
                binding.radioButtonYes.isChecked=true
            }else{
                binding.radioButtonNo.isChecked=true
            }
        }
    }

    private fun areValidInputs():Boolean{
        if(binding.editTextName.text.isNullOrBlank()){
            binding.editTextName.error="fill here"
            return false
        }
        if (binding.editTextNationalId.text.isNullOrBlank()){
            binding.editTextNationalId.error="fill here"
            return false
        }
        if (binding.editTextNationalId.text.length!=10){
            binding.editTextNationalId.error="National ID must be of length 10"
            return false
        }
        if (binding.editTextPhone.text.isNullOrBlank()){
            binding.editTextPhone.error="fill here"
            return false
        }
        if (binding.editTextPhone.text.length<8){
            binding.editTextPhone.error="Phone number is too short"
            return false
        }
        if (binding.radioButtonNo.isChecked==binding.radioButtonYes.isChecked){
            Toast.makeText(requireContext(),"please choose privacy",Toast.LENGTH_SHORT).show()
            return false
        }
        return true
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