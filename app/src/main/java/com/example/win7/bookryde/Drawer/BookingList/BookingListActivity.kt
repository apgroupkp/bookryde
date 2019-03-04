package com.example.win7.bookryde.Drawer.BookingList

import android.os.Bundle
import com.example.win7.bookryde.BaseActivity
import com.example.win7.bookryde.databinding.ActivityBookingListBinding

class BookingListActivity : BaseActivity() {
lateinit var binding : ActivityBookingListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingListBinding.inflate(layoutInflater, baseBinding.frmContainer, true)
        init()
    }
    fun init(){

    }
}
