package com.example.win7.bookryde.User

import android.content.Intent
import android.opengl.ETC1.isValid
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.win7.bookryde.BaseActivity
import com.example.win7.bookryde.MainActivity
import com.example.win7.bookryde.R
import com.example.win7.bookryde.Utils
import com.example.win7.bookryde.databinding.ActivityLoginBinding
import com.example.win7.bookryde.databinding.ActivityNoInternetBinding

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
            showSnackBar(me, getString(R.string.please_enter_email))
            return  false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.edtEmail.text.toString()).matches()) {
            showSnackBar(me, getString(R.string.email_patren))
            return  false
        } else if (binding.edtPassword.text.isEmpty()) {
            showSnackBar(me, getString(R.string.please_enter_password))
            return  false
        } else if (binding.edtPassword.text.length < 8) {
            showSnackBar(me, getString(R.string.password_validation))
            return false
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            //  Utils.setEmailAddress(me, binding.edtEmailaddress.text.toString())
            return  true
        }
    }
}
