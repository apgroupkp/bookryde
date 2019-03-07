package com.example.win7.bookryde.Drawer.BookingList

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.win7.bookryde.BaseActivity
import com.example.win7.bookryde.Model.BookingHistoryData
import com.example.win7.bookryde.R
import com.example.win7.bookryde.databinding.ActivityBookingListBinding

class BookingListActivity : BaseActivity() , View.OnClickListener{
    private val bookinglist: ArrayList<BookingHistoryData> = arrayListOf()
    private val bookingaddresslist: ArrayList<BookingHistoryData> = arrayListOf()
    var bookingadpter : BookingDateAdpter?=null
    var bookingListAdapter : BookingListAdapter?=null
    lateinit var binding: ActivityBookingListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingListBinding.inflate(layoutInflater, baseBinding.frmContainer, true)
        init()
    }

    fun init() {
        setToolbarTitle(getString(R.string.booking_history))
        setBackButton(true)
        baseBinding.imgBack.setOnClickListener(this)
        setDateData()
        setItemData()
    }
    fun setDateData(){
        val bookinghistory = BookingHistoryData()
        bookinghistory.datetime="10 sep 2019,2:15"
        bookinglist.add(bookinghistory)

        val bookinghistory1 = BookingHistoryData()
        bookinghistory1.datetime="13 nov 2019,2:15"
        bookinglist.add(bookinghistory1)

        val bookinghistory2 = BookingHistoryData()
        bookinghistory2.datetime="20 Feb 2019,2:15"
        bookinglist.add(bookinghistory2)

        val mLayoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.recDateList.layoutManager = mLayoutManager
        bookingadpter = BookingDateAdpter(me,bookinglist)
        binding.recDateList.adapter=bookingadpter

    }
    fun setItemData(){
        val bookinghistory = BookingHistoryData()
        bookinghistory.datetime="10 sep 2019,2:15"
        bookinghistory.bookingid="12150"
        bookinghistory.dropaddress="prahladnagar"
        bookinghistory.pickupaddress="isckon"
        bookingaddresslist.add(bookinghistory)

        val bookinghistoryitem = BookingHistoryData()
        bookinghistoryitem.datetime="13 nov 2019,2:15"
        bookinghistoryitem.bookingid="14545"
        bookinghistoryitem.dropaddress="shivrajni"
        bookinghistoryitem.pickupaddress="isckon"
        bookingaddresslist.add(bookinghistoryitem)

        val bookinghistory2 = BookingHistoryData()
        bookinghistory2.datetime="20 Feb 2019,2:15"
        bookinghistory2.bookingid="154454"
        bookinghistory2.dropaddress="prahladnagar"
        bookinghistory2.pickupaddress="isckon"
        bookingaddresslist.add(bookinghistory2)

        val mLayoutManager = LinearLayoutManager(this)
        binding.recBookingList.layoutManager = mLayoutManager
        bookingListAdapter = BookingListAdapter(me,bookingaddresslist)
        binding.recBookingList.adapter=bookingListAdapter

    }
    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.imgBack->{
                onBackPressed()
            }
            else -> {
                super.onClick(v)
            }
        }
    }
}
