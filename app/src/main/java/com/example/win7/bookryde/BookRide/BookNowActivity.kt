package com.example.win7.bookryde.BookRide

import android.os.Bundle
import android.view.View
import com.example.win7.bookryde.BaseActivity
import com.example.win7.bookryde.R
import com.example.win7.bookryde.databinding.ActivityBookNowBinding

class BookNowActivity : BaseActivity(), View.OnClickListener {
lateinit var binding:ActivityBookNowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookNowBinding.inflate(layoutInflater, baseBinding.frmContainer, true)
        init()
    }
    fun init(){
        setNavigationView()
        setToolbarTitle(getString(R.string.book_now))
        setBackButton(true)
        baseBinding.imgBack.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.imgBack -> {
              finish()
            }
        }
        //super.onClick(v)
    }
}
