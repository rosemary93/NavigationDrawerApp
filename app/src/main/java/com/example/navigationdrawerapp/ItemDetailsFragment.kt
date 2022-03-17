package com.example.navigationdrawerapp

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.navigationdrawerapp.databinding.FragmentItemDetailsBinding


class ItemDetailsFragment : Fragment() {
    private lateinit var binding: FragmentItemDetailsBinding
    private val appSharedViewModel:SharedViewModelApp by activityViewModels()
    var index=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        index=it.getInt("index")
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentItemDetailsBinding.inflate(layoutInflater,container,false)
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
        Glide.with(requireContext())
            .load(appSharedViewModel.itemList[index].imageId)
            .circleCrop()
            .into(binding.imageViewItem)
        binding.textViewTitle.text=appSharedViewModel.itemList[index].title
        binding.textViewDescription.text=appSharedViewModel.itemList[index].description
        binding.imageViewItem.setImageResource(appSharedViewModel.itemList[index].imageId)
    }


    //for adding menu and set items listeners
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.share_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.title_share -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    val titleAndDescrption=appSharedViewModel.itemList[index].title + "\n"+appSharedViewModel.itemList[index].description
                    putExtra(Intent.EXTRA_TEXT, titleAndDescrption)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }
}