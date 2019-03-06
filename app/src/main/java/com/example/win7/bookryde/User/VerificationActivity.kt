package com.example.win7.bookryde.User


import android.content.Intent
import android.os.Bundle
import com.example.win7.bookryde.BaseActivity
import com.example.win7.bookryde.Interface.OnDialog
import com.example.win7.bookryde.MainActivity
import com.example.win7.bookryde.R
import com.example.win7.bookryde.databinding.ActivityPhoneVerificationBinding

class VerificationActivity : BaseActivity() {
    lateinit var binding : ActivityPhoneVerificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneVerificationBinding.inflate(layoutInflater, baseBinding.frmContainer, true)
        init()
      binding.btnSend.setOnClickListener {

          chackValid()

       }

    }

    private fun chackValid() {

    if (binding.fristdigit.text.toString().isEmpty()|| binding.seconddigit.text.toString().isEmpty()|| binding.thrddigit.text.toString().isEmpty()|| binding.fourdigit.text.toString().isEmpty() ){
        showSimpleDialog(getString(R.string.setcode), object : OnDialog {
            override fun onOk() {
            }
        })
    }else
    {
      val intent= Intent(this, MainActivity::class.java)
        startActivity(intent)

        finish()
    }
    }


    fun init()
    {


    }
}
