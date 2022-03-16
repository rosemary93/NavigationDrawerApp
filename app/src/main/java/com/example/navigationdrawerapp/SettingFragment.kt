package com.example.navigationdrawerapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.navigationdrawerapp.databinding.FragmentSettingBinding


class SettingFragment : Fragment() {
    lateinit var binding: FragmentSettingBinding
    private val appSharedViewModel: SharedViewModelApp by activityViewModels()
    private val itemsNumberButtonList = ArrayList<RadioButton>()
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
        createButtonsArrays()
        initViews()
        setButtonsListeners()
    }

    private fun initViews() {
        when (appSharedViewModel.colorTheme) {
            ThemeColor.Black -> {
                binding.radioButtonBlack.isChecked
            }
            ThemeColor.Red -> {
                binding.radioButtonRed.isChecked
            }
            else -> {
                binding.radioButtonPurple.isChecked
            }
        }

        for (i in 0 until 6) {
            if (appSharedViewModel.numberOfItems - 1 == i)
                itemsNumberButtonList[i].isChecked = true
        }
    }
    fun setButtonsListeners(){
        setItemsNumberButtonsListeners()
        setThemeButtonsListeners()
        binding.buttonEditInfo.setOnClickListener {
           // findNavController().
        }
    }

    private fun setItemsNumberButtonsListeners(){
        for (i in 0..5)
            itemsNumberButtonList[i].setOnClickListener {
                appSharedViewModel.numberOfItems=i+1
            }
    }

    private fun setThemeButtonsListeners(){
        binding.radioButtonBlack.setOnClickListener { appSharedViewModel.colorTheme=ThemeColor.Black }
        binding.radioButtonRed.setOnClickListener { appSharedViewModel.colorTheme=ThemeColor.Red }
        binding.radioButtonPurple.setOnClickListener { appSharedViewModel.colorTheme=ThemeColor.Purple }
    }

    private fun createButtonsArrays() {
        itemsNumberButtonList.add(binding.radioButton1)
        itemsNumberButtonList.add(binding.radioButton2)
        itemsNumberButtonList.add(binding.radioButton3)
        itemsNumberButtonList.add(binding.radioButton4)
        itemsNumberButtonList.add(binding.radioButton5)
        itemsNumberButtonList.add(binding.radioButton6)
    }

}