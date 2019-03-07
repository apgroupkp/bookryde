package com.example.win7.bookryde.User

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.win7.bookryde.BaseActivity
import com.example.win7.bookryde.Interface.OnDialog
import com.example.win7.bookryde.R
import com.example.win7.bookryde.databinding.ActivityResetPasswordBinding

class ResetPasswordActivity : BaseActivity(),View.OnClickListener {
lateinit var binding: ActivityResetPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater, baseBinding.frmContainer, true)
        init()
    }
    fun init(){
        setToolbarTitle(getString(R.string.reset_password))
        binding.btnResetPassword.setOnClickListener(this)
        baseBinding.imgBack.setOnClickListener(this)
        setBackButton(true)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btnResetPassword -> {
                isvalid()
            }
            R.id.imgBack->{
                onBackPressed()
            }
            else->super.onClick(v)
        }
    }
    fun isvalid(){
        if (binding.edtPassword.text.toString().isEmpty()) {
            showSimpleDialog(getString(R.string.please_enter_password), object : OnDialog {
                override fun onOk() {
                }
            })
        }  else if (binding.edtPassword.text.length<8) {
            showSimpleDialog(getString(R.string.password_validation), object : OnDialog {
                override fun onOk() {
                }
            })

        }else if (binding.edtConfirmPassword.text.toString().isEmpty()) {
            showSimpleDialog(getString(R.string.plaese_enter_confirm_password), object : OnDialog {
                override fun onOk() {
                }
            })
        }
        else if (binding.edtConfirmPassword.text.length<8) {
            showSimpleDialog(getString(R.string.confirm_password_validation), object : OnDialog {
                override fun onOk() {
                }
            })
        }else if (binding.edtConfirmPassword.text.toString() != binding.edtPassword.text.toString()) {

            showSimpleDialog(getString(R.string.confirm_password_same_as_password), object : OnDialog {
                override fun onOk() {
                }
            })
        }
        else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
