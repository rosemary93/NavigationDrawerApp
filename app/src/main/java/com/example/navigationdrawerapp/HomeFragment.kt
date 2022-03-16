package com.example.navigationdrawerapp

import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.activityViewModels
import com.example.navigationdrawerapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    lateinit var binding:FragmentHomeBinding
    val layoutList=ArrayList<ConstraintLayout>()
    val textViewList=ArrayList<TextView>()
    val imageViewList=ArrayList<ImageView>()
    private val appSharedViewModel:SharedViewModelApp by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createViewsArrays()
    }

    fun createViewsArrays(){
        createLayoutArray()
        createTextViewArray()
        createImageViewArray()
    }
    private fun createLayoutArray(){
        layoutList.add(binding.constrLayout1)
        layoutList.add(binding.constrLayout2)
        layoutList.add(binding.constrLayout3)
        layoutList.add(binding.constrLayout4)
        layoutList.add(binding.constrLayout5)
        layoutList.add(binding.constrLayout6)
    }
    private fun createTextViewArray()
    {
        textViewList.add(binding.textView1)
        textViewList.add(binding.textView2)
        textViewList.add(binding.textView3)
        textViewList.add(binding.textView4)
        textViewList.add(binding.textView5)
        textViewList.add(binding.textView6)
    }

    private fun createImageViewArray(){
        imageViewList.add(binding.imageView1)
        imageViewList.add(binding.imageView2)
        imageViewList.add(binding.imageView3)
        imageViewList.add(binding.imageView4)
        imageViewList.add(binding.imageView5)
        imageViewList.add(binding.imageView6)
    }

    override fun onResume() {
        super.onResume()
        for (i in appSharedViewModel.numberOfItems ..5)
        {
            layoutList[i].visibility=View.GONE
        }
    }


}