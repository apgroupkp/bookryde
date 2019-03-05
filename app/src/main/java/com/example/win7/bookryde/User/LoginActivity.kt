package com.example.win7.bookryde.User

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.win7.bookryde.BaseActivity
import com.example.win7.bookryde.Interface.OnDialog
import com.example.win7.bookryde.MainActivity
import com.example.win7.bookryde.R
import com.example.win7.bookryde.Utils
import com.example.win7.bookryde.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity(), View.OnClickListener {

    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater, baseBinding.frmContainer, true)
        init()
    }

    private fun init() {
        setToolbarTitle(getString(R.string.login))
        setFullScreen()
        binding.btnLogin.setOnClickListener(this)
        binding.txtSignUp.setOnClickListener(this)
        binding.txxForgotPassword.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btnLogin -> {
                Utils.hideSoftKeyboard(me)
                if(isValid()) {
                }
            }
            R.id.txtSignUp->{
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
            R.id.txxForgotPassword->{
                val intent = Intent(this, ForgotPasswordActivity::class.java)
                startActivity(intent)
            }
            else -> {
               super.onClick(v)
            }
        }
    }

    fun  isValid(): Boolean {
        if (binding.edtEmail.text.isEmpty()) {
            showSimpleDialog( getString(R.string.please_enter_email),object :OnDialog{
                override fun onOk() {
                }
            })
            return  false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.edtEmail.text.toString()).matches()) {
            showSimpleDialog(getString(R.string.email_pattren),object :OnDialog{
                override fun onOk() {
                }
            })
            return  false
        } else if (binding.edtPassword.text.isEmpty()) {
            showSimpleDialog(getString(R.string.please_enter_password),object :OnDialog{
                override fun onOk() {
                }
            })
            return  false
        } else if (binding.edtPassword.text.length < 8) {
            showSimpleDialog(getString(R.string.password_validation),object :OnDialog{
                override fun onOk() {
                }
            })
            return false
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            //  Utils.setEmailAddress(me, binding.edtEmailaddress.text.toString())
            return  true
        }
    }
}
