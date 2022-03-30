package com.example.navigationdrawerapp

import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.navigationdrawerapp.databinding.FragmentHomeBinding
import kotlin.random.Random

//const val SHARED_PREFERENCE_NAME = "appInfo"

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    val layoutList = ArrayList<ConstraintLayout>()
    val textViewList = ArrayList<TextView>()
    val imageViewList = ArrayList<ImageView>()
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
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
       /* if (appSharedViewModel.colorTheme == ThemeColor.Black) {
            activity?.setTheme(R.style.Theme_NavigationDrawerAppBlack)
        } else if (appSharedViewModel.colorTheme == ThemeColor.Red) {
            activity?.setTheme(R.style.Theme_NavigationDrawerAppRed)

        } else {
            activity?.setTheme(R.style.Theme_NavigationDrawerApp)
        }*/
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createViewsArrays()
        initViews()
        binding.textViewDayTips.text = appSharedViewModel.dayTips[Random.nextInt(0,6)]




        for (i in 0..5) {
            imageViewList[i].setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToItemDetailsFragment(i)
                findNavController().navigate(action)
            }
        }


    }



    private fun initViews() {
        for (i in 0..5) {
            textViewList[i].text = appSharedViewModel.itemList[i].title
            imageViewList[i].setImageResource(appSharedViewModel.itemList[i].imageId)
            Glide.with(requireContext())
                .load(appSharedViewModel.itemList[i].imageId)
                .into(imageViewList[i])
        }
        for (i in 0..5) {
            if (i < appSharedViewModel.numberOfItems)
                continue
            layoutList[i].visibility = View.GONE

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


    fun createViewsArrays() {
        createLayoutArray()
        createTextViewArray()
        createImageViewArray()
    }

    private fun createLayoutArray() {
        layoutList.add(binding.constrLayout1)
        layoutList.add(binding.constrLayout2)
        layoutList.add(binding.constrLayout3)
        layoutList.add(binding.constrLayout4)
        layoutList.add(binding.constrLayout5)
        layoutList.add(binding.constrLayout6)
    }

    private fun createTextViewArray() {
        textViewList.add(binding.textView1)
        textViewList.add(binding.textView2)
        textViewList.add(binding.textView3)
        textViewList.add(binding.textView4)
        textViewList.add(binding.textView5)
        textViewList.add(binding.textView6)
    }

    private fun createImageViewArray() {
        imageViewList.add(binding.imageView1)
        imageViewList.add(binding.imageView2)
        imageViewList.add(binding.imageView3)
        imageViewList.add(binding.imageView4)
        imageViewList.add(binding.imageView5)
        imageViewList.add(binding.imageView6)
    }


}
/*
class MyDrawerListener: DrawerLayout.DrawerListener {

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
        TODO("Not yet implemented")
    }

    override fun onDrawerOpened(drawerView: View) {
        TODO("Not yet implemented")
    }

    override fun onDrawerClosed(drawerView: View) {

    }

    override fun onDrawerStateChanged(newState: Int) {
        TODO("Not yet implemented")
    }
}*/
