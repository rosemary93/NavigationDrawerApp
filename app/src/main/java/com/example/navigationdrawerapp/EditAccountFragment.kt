package com.example.navigationdrawerapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.navigationdrawerapp.databinding.FragmentEditAccountBinding
import com.google.gson.Gson


class EditAccountFragment : Fragment() {
    lateinit var binding: FragmentEditAccountBinding
    var cardNumber = ""
    var bank = ""
    var account: Account? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            bank = it.getString("bank").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        account = getDataFromShared(bank)
        if (account != null) {
            initViews()
        }else{
            enableRadioButtons()
        }

        binding.buttonSaveAccount.setOnClickListener {
            if (areValidInputs()) {
                val tempAccount = Account(
                    cardNumber,
                    binding.editTextNumberAcountNumber.text.toString(),
                    binding.editTextNumberShaba.text.toString()
                )
                if (binding.radioButtonTejarat.isChecked) {
                    tempAccount.bank = "tejarat"
                } else if (binding.radioButtonParsian.isChecked) {
                    tempAccount.bank = "parsian"
                } else if (binding.radioButtonMelli.isChecked) {
                    tempAccount.bank = "melli"
                } else {
                    tempAccount.bank = "mellat"
                }
                setDataInShared(tempAccount)
                Toast.makeText(requireContext(), "account saved", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_editAccountFragment_to_settingFragment)
            }
        }

        binding.buttonBackAccount.setOnClickListener {
            findNavController().navigate(R.id.action_editAccountFragment_to_settingFragment)
        }
    }

    private fun initViews() {

        binding.editTextNumberShaba.setText(account?.SHABA)
        binding.editTextNumberAcountNumber.setText(account?.accountNumber)
        binding.editTextNumber14.setText(account?.cardNumber?.substring(0..3))
        binding.editTextNumber58.setText(account?.cardNumber?.substring(4..7))
        binding.editTextNumber912.setText(account?.cardNumber?.substring(8..11))
        binding.editTextNumber1316.setText(account?.cardNumber?.substring(12..15))
        when (account?.bank) {
            "melli" -> {
                binding.radioButtonMelli.isChecked = true
            }
            "parsian" -> {
                binding.radioButtonParsian.isChecked = true
            }
            "tejarat" -> {
                binding.radioButtonTejarat.isChecked = true
            }
            "mellat" -> binding.radioButtonMellat.isChecked = true
        }
        disabaleRadioButtons()

    }

    fun setDataInShared(account: Account) {
        val sharedPreference =
            requireActivity().getSharedPreferences("Accounts", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        val gson = Gson()
        val stringJson = gson.toJson(account)
        editor.putString(account.bank, stringJson)
        editor.apply()

    }

    private fun getDataFromShared(bank: String): Account? {
        val sharedPreference =
            requireActivity().getSharedPreferences("Accounts", Context.MODE_PRIVATE)
        val gson = Gson()
        val stringJson = sharedPreference.getString(bank, "")
        return gson.fromJson(stringJson, Account::class.java)
    }

    fun areValidInputs(): Boolean {
        if (!binding.radioButtonMellat.isChecked && !binding.radioButtonMelli.isChecked && !binding.radioButtonParsian.isChecked && !binding.radioButtonTejarat.isChecked
        ) {
            Toast.makeText(requireContext(), "please choose a bank", Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.editTextNumber14.text.isNullOrBlank() || binding.editTextNumber58.text.isNullOrBlank() || binding.editTextNumber912.text.isNullOrBlank() || binding.editTextNumber1316.text.isNullOrBlank()) {
            Toast.makeText(
                requireContext(),
                "please fill all the fields in card number",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        if (binding.editTextNumber14.text.length != 4 || binding.editTextNumber58.text.length != 4 || binding.editTextNumber912.text.length != 4 || binding.editTextNumber1316.text.length != 4) {
            Toast.makeText(
                requireContext(),
                "All the fields must be of length 4",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        if (binding.editTextNumberAcountNumber.text.isNullOrBlank()) {
            binding.editTextNumberAcountNumber.error = "please fill here"
            return false
        }
        if (binding.editTextNumberAcountNumber.text.length < 5) {
            binding.editTextNumberAcountNumber.error = "account number is too short"
            return false
        }
        if (binding.editTextNumberShaba.text.isNullOrBlank()) {
            binding.editTextNumberShaba.error = "please fill here"
            return false
        }
        if (binding.editTextNumberShaba.text.length != 24) {
            binding.editTextNumberShaba.error = "this field must be of length 24"
            return false
        }
        cardNumber =
            binding.editTextNumber14.text.toString() + binding.editTextNumber58.text.toString() + binding.editTextNumber912.text.toString() + binding.editTextNumber1316.text.toString()
        return true
    }

    fun disabaleRadioButtons(){
        binding.radioButtonMelli.isEnabled = false
        binding.radioButtonParsian.isEnabled = false
        binding.radioButtonTejarat.isEnabled = false
        binding.radioButtonMellat.isEnabled = false
    }
    fun enableRadioButtons(){
        binding.radioButtonMelli.isEnabled = true
        binding.radioButtonParsian.isEnabled = true
        binding.radioButtonTejarat.isEnabled = true
        binding.radioButtonMellat.isEnabled = true
    }
}