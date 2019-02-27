package com.example.win7.bookryde.User

import android.os.Bundle
import com.example.win7.bookryde.BaseActivity
import com.example.win7.bookryde.databinding.ActivitRegisterBinding

class RegisterActivity : BaseActivity() {
    lateinit var binding:ActivitRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitRegisterBinding.inflate(layoutInflater, baseBinding.frmContainer, true)
        init()
    }
    fun init(){
        setFullScreen()
    }
}
