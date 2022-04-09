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
import com.bumptech.glide.Glide
import com.example.navigationdrawerapp.databinding.FragmentProfileBinding
import com.google.gson.Gson


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
        if (getUserDataFromShared() == null) {
            findNavController().navigate(R.id.action_profileFragment_to_registrationFragment)
        }
        if (getUserDataFromShared()?.keepPrivate == true){
            binding.LlayoutUserInfo.visibility=View.GONE
            Toast.makeText(requireContext(),"Sorry user info is private",Toast.LENGTH_SHORT).show()
        }else{
            initViews()
            binding.LlayoutUserInfo.visibility=View.VISIBLE
        }

        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
        }

    }

    private fun initViews() {
        val tempUser=getUserDataFromShared()
        binding.textViewName.text = "name : " + tempUser?.name
        binding.textViewNationalID.text = "national ID: " +  tempUser?.nationalID
        binding.textViewPhone.text = "phone: " +  tempUser?.phone
        Glide.with(requireContext())
            .load(R.drawable.river)
            .into(binding.imageView)
    }

    private fun getUserDataFromShared(): User? {
        val sharedPreference =
            requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val gson = Gson()
        val stringJson = sharedPreference.getString("user", "")
        return gson.fromJson(stringJson, User::class.java)
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