package com.example.win7.bookryde.User

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import com.example.win7.bookryde.BaseActivity
import com.example.win7.bookryde.MainActivity
import com.example.win7.bookryde.R
import com.example.win7.bookryde.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : BaseActivity(), View.OnClickListener  {
lateinit var binding:ActivityForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater, baseBinding.frmContainer, true)
        init()
    }
    fun init(){
        setToolbarTitle(getString(R.string.forgot_password))
        binding.btnSend.setOnClickListener(this)
        baseBinding.imgBack.setOnClickListener(this)
        setBackButton(true)
    }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btnSend -> {
                if (binding.edtEmail.text.isEmpty()) {
                    showSnackBar(me, getString(R.string.please_enter_email))
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.edtEmail.text.toString()).matches()) {
                    showSnackBar(me, getString(R.string.email_pattren))
                }else{
                    val intent = Intent(this, ResetPasswordActivity::class.java)
                    startActivity(intent)
                }
            }
            R.id.imgBack->{
                onBackPressed()
            }
            else -> {
                super.onClick(v)
            }
        }
    }
}
