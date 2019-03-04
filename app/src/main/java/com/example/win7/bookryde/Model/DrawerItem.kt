package com.example.win7.bookryde.Model

import android.graphics.drawable.Drawable
import com.example.win7.bookryde.DrawerEum.DrawerEnum

class DrawerItem{
    var itemImage:Drawable?=null
    var itemName:String?=null
    var drawerEnum: DrawerEnum? = null
    var count: Int = 0
    var isChecked: Boolean = false
}
