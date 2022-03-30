package com.example.navigationdrawerapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.navigationdrawerapp.databinding.FragmentAccountDetailsBinding
import com.google.gson.Gson


class AccountDetailsFragment : Fragment() {
    lateinit var binding: FragmentAccountDetailsBinding
    private var account: Account?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.LlayoutAccountDetails.visibility = View.GONE
        setButtonsListener()
    }

    fun setButtonsListener(){
        binding.buttonMellat.setOnClickListener {
            account = getDataFromShared("mellat")
            initViews(account)
        }
        binding.buttonMelli.setOnClickListener {
            account = getDataFromShared("melli")
            initViews(account)
        }
        binding.buttonParsian.setOnClickListener {
            account = getDataFromShared("parsian")
            initViews(account)
        }
        binding.buttonTejarat.setOnClickListener {
            account = getDataFromShared("tejarat")
            initViews(account)
        }
        binding.buttonBackAd.setOnClickListener {
            findNavController().navigate(R.id.action_accountDetailsFragment_to_homeFragment)
        }
    }

    private fun initViews(account: Account?) {
        if (account != null) {
            binding.textViewBank.text = account.bank
            binding.textViewAccountNumber.text = account.accountNumber
            binding.textViewCardNumber.text = account.cardNumber
            binding.textViewShaba.text = account.SHABA
            binding.LlayoutAccountDetails.visibility = View.VISIBLE
        } else {
            binding.LlayoutAccountDetails.visibility = View.GONE
            Toast.makeText(
                requireContext(),
                "Account of following bank doesn't exist",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun getDataFromShared(bank: String): Account? {
        val sharedPreference =
            requireActivity().getSharedPreferences("Accounts", Context.MODE_PRIVATE)
        val gson = Gson()
        val stringJson = sharedPreference.getString(bank, "")
        return gson.fromJson(stringJson, Account::class.java)
    }

}