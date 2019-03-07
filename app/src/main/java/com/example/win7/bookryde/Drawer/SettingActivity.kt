package com.example.win7.bookryde.Drawer

import android.os.Bundle
import android.view.View
import com.example.win7.bookryde.BaseActivity
import com.example.win7.bookryde.R
import com.example.win7.bookryde.databinding.ActivitySettingBinding

class SettingActivity : BaseActivity() , View.OnClickListener{
lateinit var binding : ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater, baseBinding.frmContainer, true)
        init()
    }
     fun init() {
         setToolbarTitle(getString(R.string.setting))
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
