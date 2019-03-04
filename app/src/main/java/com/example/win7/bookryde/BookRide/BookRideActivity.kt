package com.example.win7.bookryde.BookRide

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.win7.bookryde.BaseActivity
import com.example.win7.bookryde.DrawerEum.DrawerEnum
import com.example.win7.bookryde.Model.DrawerItem
import com.example.win7.bookryde.Model.VehicleData
import com.example.win7.bookryde.Notification.NotificationListActivity
import com.example.win7.bookryde.R
import com.example.win7.bookryde.User.LoginActivity
import com.example.win7.bookryde.binding
import com.example.win7.bookryde.databinding.ActivityBookRideBinding

class BookRideActivity : BaseActivity() ,View.OnClickListener{
    lateinit var binding:ActivityBookRideBinding
    private val vehicleList: ArrayList<VehicleData> = arrayListOf()
    lateinit var bookRideAdapter: BookRideAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookRideBinding.inflate(layoutInflater, baseBinding.frmContainer, true)
        init()
    }
    fun init(){
        setNavigationView()
        setToolbarTitle(getString(R.string.book_ride))
        setNotificationButton(true)
        baseBinding.imgNotification.setOnClickListener(this)
        setVehicleData()

    }
    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.imgNotification -> {
                startActivity(Intent(me, NotificationListActivity::class.java))
            }
        }
    }
    fun setVehicleData(){
        vehicleList.clear()
        var item = VehicleData()
        item.vehicleName="MINI"
        item.vehicleImage=getDrawable(R.drawable.cab1)
        vehicleList.add(item)
        var item1 = VehicleData()
        item1.vehicleName="MICRO"
        item1.vehicleImage=getDrawable(R.drawable.cab1)
        vehicleList.add(item1)

        var item2 = VehicleData()
        item2.vehicleName="SUV"
        item2.vehicleImage=getDrawable(R.drawable.cab1)
        vehicleList.add(item2)

        var item3 = VehicleData()
        item3.vehicleName="SEDAN"
        item3.vehicleImage=getDrawable(R.drawable.cab1)
        vehicleList.add(item3)

        bookRideAdapter = BookRideAdapter(this@BookRideActivity,vehicleList)
        val mLayoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.recCablist.layoutManager = mLayoutManager
        binding.recCablist.adapter = bookRideAdapter
    }
}
