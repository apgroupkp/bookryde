package com.example.win7.bookryde.Drawer

import android.os.Bundle
import android.view.View
import com.example.win7.bookryde.BaseActivity
import com.example.win7.bookryde.Interface.OnDialog
import com.example.win7.bookryde.R
import com.example.win7.bookryde.databinding.ActivityChangePasswordBinding

class ChangePasswordActivity : BaseActivity(),View.OnClickListener {
lateinit var binding:ActivityChangePasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater, baseBinding.frmContainer, true)
        init()
    }
    fun init(){
        setToolbarTitle(getString(R.string.change_password))
        baseBinding.imgBack.setOnClickListener(this)
        binding.btnChangePassword.setOnClickListener(this)
        setBackButton(true)
    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnChangePassword -> {
                isValid()
            }
            R.id.imgBack -> {
                onBackPressed()
            }
            else -> {
                super.onClick(v)
            }
        }
    }
    fun isValid(){
        if (binding.edtoldPassword.text.isEmpty()) {
            showSimpleDialog(getString(R.string.please_enter_old_password), object : OnDialog {
                override fun onOk() {
                }
            })
        } else if(binding.edtnewPassword.text.isEmpty()){
            showSimpleDialog(getString(R.string.please_enter_new_password), object : OnDialog {
                override fun onOk() {
                }
            })
        }else if(binding.edtnewPassword.text.length<8){
            showSimpleDialog(getString(R.string.password_validation), object : OnDialog {
                override fun onOk() {
                }
            })
        }
        else if(binding.edtConfirmnewPassword.text.isEmpty()){
            showSimpleDialog(getString(R.string.please_enter_new_password), object : OnDialog {
                override fun onOk() {
                }
            })
        }else if(binding.edtConfirmnewPassword.text.length<8){
            showSimpleDialog(getString(R.string.password_validation), object : OnDialog {
                override fun onOk() {
                }
            })
        }else if (binding.edtConfirmnewPassword.text.toString() != binding.edtnewPassword.text.toString()) {
            showSimpleDialog(getString(R.string.confirm_password_same_as_password), object : OnDialog {
                override fun onOk() {
                }
            })
        }
        else {
            /*val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)*/
        }
    }
}
