package com.example.win7.bookryde

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.win7.bookryde.databinding.ActivityNoInternetBinding

class NoInternetActivity : BaseActivity() {
    lateinit var binding: ActivityNoInternetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoInternetBinding.inflate(layoutInflater, baseBinding.frmContainer, true)
    }
}
