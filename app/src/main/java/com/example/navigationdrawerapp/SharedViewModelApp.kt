package com.example.navigationdrawerapp

import android.os.Handler
import androidx.lifecycle.ViewModel


class SharedViewModelApp:ViewModel() {
    var numberOfItems=0
    var colorTheme=ThemeColor.Purple
    var user:User?=null
    val itemList=ArrayList<Item>()
    val dayTips=ArrayList<String>()

    init {
        numberOfItems=6
        itemList.add(Item("Takhte Jamshid","Takhte Jamshid is in Shiraz",R.drawable.tj))
        itemList.add(Item("Sio-se-pol","Sio-se-pol is in Isfahan",R.drawable.sspol))
        itemList.add(Item("Bagh-e-fin","Bagh-e-fin is in Kashan",R.drawable.fin))
        itemList.add(Item("Nasir-ol-molk mosque","Nasir-ol-molk mosque is in Shiraz",R.drawable.nasirmmosque))
        itemList.add(Item("Baghe-e-eram","Baghe-e-eram is in Shiraz",R.drawable.bakheram))
        itemList.add(Item("Badab-surt-spring","Badab-surt-spring is in Mazandaran",R.drawable.badabspring))
        dayTips.add("بزرگترین عیب برای دنیا همین بس که بیوفاست. (حضرت علی علیه السلام)")
        dayTips.add("بهتر است روی پای خود بمیری تا روی زانوهایت زندگی کنی. (رودی)")
        dayTips.add(" بر روی زمین چیزی بزرگتر از انسان نیست و در انسان چیزی بزرگتر از فکر او. (همیلتون)")
        dayTips.add("چیزی ساده تر از بزرگی نیست آری ساده بودن همانا بزرگ بودن است. (امرسون)")
        dayTips.add("هنر، کلید فهم زندگی است. (اسکار وایله)")
        dayTips.add("تغییر دهندگان اثر گذار در جهان کسانی هستند که بر خلاف جریان شنا میکنند. (والترنیس)")
    }


}
data class User(var name:String, var nationalID:String, var phone:String){
     var keepPrivate=false
}
data class Item(var title:String, var description:String, var imageId:Int)
data class appInfos(var numberOfItems:Int,var themeColor: ThemeColor){
    val user:User?=null
}
data class Account(var cardNumber:String,var accountNumber:String,var SHABA:String){
    var bank:String=""
}
enum class ThemeColor{
    Black,Red,Purple
}