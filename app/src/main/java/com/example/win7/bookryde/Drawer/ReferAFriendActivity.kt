package com.example.win7.bookryde.Drawer

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.win7.bookryde.BaseActivity
import com.example.win7.bookryde.MainActivity
import com.example.win7.bookryde.R
import com.example.win7.bookryde.databinding.ActivityReferAFriendBinding

class ReferAFriendActivity : BaseActivity(), View.OnClickListener{
lateinit var binding: ActivityReferAFriendBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReferAFriendBinding.inflate(layoutInflater, baseBinding.frmContainer, true)
        init()
    }
    fun init(){
        setToolbarTitle(getString(R.string.refer_a_friend))
        baseBinding.imgBack.setOnClickListener(this)
        setBackButton(true)
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
