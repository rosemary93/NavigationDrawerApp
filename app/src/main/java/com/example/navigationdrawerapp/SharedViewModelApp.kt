package com.example.navigationdrawerapp

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.ViewModel

class SharedViewModelApp:ViewModel() {
    var numberOfItems=0
    var colorTheme=ThemeColor.Purple
    var user:User?=null
    val itemList=ArrayList<Item>()
    var isInAnotherFragment=false

    init {
        numberOfItems=6
        itemList.add(Item("Takhte Jamshid","Takhte Jamshid is in Shiraz",R.drawable.tj))
        itemList.add(Item("Sio-se-pol","Sio-se-pol is in Isfahan",R.drawable.sspol))
        itemList.add(Item("Bagh-e-fin","Bagh-e-fin is in Kashan",R.drawable.fin))
        itemList.add(Item("Nasir-ol-molk mosque","Nasir-ol-molk mosque is in Shiraz",R.drawable.nasirmmosque))
        itemList.add(Item("Baghe-e-eram","Baghe-e-eram is in Shiraz",R.drawable.bakheram))
        itemList.add(Item("Badab-surt-spring","Badab-surt-spring is in Mazandaran",R.drawable.badabspring))
    }
    /*fun loadImages(context: Context, imageView: ImageView, index:Int) {

    }*/

}
data class User(var name:String, var nationalID:String, var phone:String)
data class Item(var title:String, var description:String, var imageId:Int)
data class appInfos(var numberOfItems:Int,var themeColor: ThemeColor){
    val user:User?=null
}
enum class ThemeColor{
    Black,Red,Purple
}