package com.example.navigationdrawerapp

import androidx.lifecycle.ViewModel

class SharedViewModelApp:ViewModel() {
    var numberOfItems=0
    var colorTheme=ThemeColor.Purple
    var user:User?=null
    val itemList=ArrayList<Item>()
    init {
        numberOfItems=6
        itemList.add(Item("Takhte Jamshid","description1",R.drawable.river))
        itemList.add(Item("Sio-se-pol","description2",R.drawable.river))
        itemList.add(Item("Bagh-e-fin","description3",R.drawable.river))
        itemList.add(Item("Nasir-ol-molk mosque","description4",R.drawable.river))
        itemList.add(Item("Baghe-e-eram","description5",R.drawable.river))
        itemList.add(Item("Badab-surt-spring","description6",R.drawable.river))
    }

}
data class User(var name:String, var nationalID:String, var phone:String)
data class Item(var title:String, var description:String, var imageId:Int)
enum class ThemeColor{
    Black,Red,Purple
}