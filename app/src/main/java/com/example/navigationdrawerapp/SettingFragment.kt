package com.example.navigationdrawerapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.navigationdrawerapp.databinding.FragmentSettingBinding


class SettingFragment : Fragment() {
    lateinit var binding: FragmentSettingBinding
    private val appSharedViewModel: SharedViewModelApp by activityViewModels()

    //private val itemsNumberButtonList = ArrayList<Button>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //createItemNumbersButtonArray()
        initViews()
        setButtonsListeners()
    }

    private fun initViews() {
        when (appSharedViewModel.colorTheme) {
            ThemeColor.Black -> {
                binding.radioButtonBlack.isChecked = true
            }
            ThemeColor.Red -> {
                binding.radioButtonRed.isChecked = true
            }
            else -> {
                binding.radioButtonPurple.isChecked = true
            }
        }
        when (appSharedViewModel.numberOfItems) {
            1 -> binding.radioButton1.isChecked = true
            2 -> binding.radioButton2.isChecked = true
            3 -> binding.radioButton3.isChecked = true
            4 -> binding.radioButton4.isChecked = true
            5 -> binding.radioButton5.isChecked = true
            else -> {
                binding.radioButton6.isChecked = true
            }
        }
    }

    private fun setButtonsListeners() {

        binding.buttonSetItemNumbers.setOnClickListener {
            if (binding.radioButton1.isChecked) {
                appSharedViewModel.numberOfItems = 1
            } else if (binding.radioButton2.isChecked) {
                appSharedViewModel.numberOfItems = 2
            } else if (binding.radioButton3.isChecked) {
                appSharedViewModel.numberOfItems = 3
            } else if (binding.radioButton4.isChecked) {
                appSharedViewModel.numberOfItems = 4
            } else if (binding.radioButton5.isChecked) {
                appSharedViewModel.numberOfItems = 5
            } else {
                appSharedViewModel.numberOfItems = 6
            }
            Toast.makeText(activity, "number of items set", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_settingFragment_to_homeFragment)
        }
        binding.buttonSetTheme.setOnClickListener {
            if (binding.radioButtonBlack.isChecked) {
                appSharedViewModel.colorTheme = ThemeColor.Black
            } else if (binding.radioButtonRed.isChecked) {
                appSharedViewModel.colorTheme = ThemeColor.Red
            } else {
                appSharedViewModel.colorTheme = ThemeColor.Purple
            }
            Toast.makeText(activity, "theme color set", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_settingFragment_to_homeFragment)

        }

        binding.buttonEditInfo.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_registrationFragment)
        }

        binding.buttonMelliS.setOnClickListener {
        val action=SettingFragmentDirections.actionSettingFragmentToEditAccountFragment("melli")
            findNavController().navigate(action)
        }
        binding.buttonMellatS.setOnClickListener {
            val action=SettingFragmentDirections.actionSettingFragmentToEditAccountFragment("mellat")
            findNavController().navigate(action)
        }
        binding.buttonParsianS.setOnClickListener {
            val action=SettingFragmentDirections.actionSettingFragmentToEditAccountFragment("parsian")
            findNavController().navigate(action)
        }
        binding.buttonTejaratS.setOnClickListener {
            val action=SettingFragmentDirections.actionSettingFragmentToEditAccountFragment("tejarat")
            findNavController().navigate(action)
        }



        binding.buttonBackSetting.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_homeFragment)
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