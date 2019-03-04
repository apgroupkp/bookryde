package com.example.win7.bookryde

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.win7.bookryde.BookRide.BookRideActivity
import com.example.win7.bookryde.databinding.ActivityLoginBinding
import com.example.win7.bookryde.databinding.ActivityMainBinding

lateinit var binding:ActivityMainBinding
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater, baseBinding.frmContainer, true)
        init()
    }
    fun init(){
        setNavigationView()
        setToolbarTitle(getString(R.string.cab_booking))
        binding.btnBookRide.setOnClickListener{
            val intent = Intent(this, BookRideActivity::class.java)
            startActivity(intent)
        }
        binding.btnShareRide.setOnClickListener{

        }
    }
}
