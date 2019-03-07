package com.example.win7.bookryde.User


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import com.example.win7.bookryde.BaseActivity
import com.example.win7.bookryde.Interface.OnDialog
import com.example.win7.bookryde.MainActivity
import com.example.win7.bookryde.R
import com.example.win7.bookryde.databinding.ActivityPhoneVerificationBinding

class VerificationActivity : BaseActivity()/* ,View.OnClickListener , View.OnKeyListener*/{
    lateinit var binding : ActivityPhoneVerificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneVerificationBinding.inflate(layoutInflater, baseBinding.frmContainer, true)
        init()
    }

    fun init()
    {
      /*  binding.fristdigit.addTextChangedListener(GenericTextWatcher(binding.fristdigit))
        binding.seconddigit.addTextChangedListener(GenericTextWatcher(binding.seconddigit))
        binding.thrddigit.addTextChangedListener(GenericTextWatcher(binding.thrddigit))
        binding.thrddigit.addTextChangedListener(GenericTextWatcher(binding.fourdigit))
        binding.btnSend.setOnClickListener(this)

        binding.fristdigit.setOnKeyListener(this)
        binding.seconddigit.setOnKeyListener(this)
        binding.thrddigit.setOnKeyListener(this)
        binding.thrddigit.setOnKeyListener(this)*/
    }
    override fun onClick(v: View?) {
        when(v?.id) {

           /* R.id.btnSend->{
                chackValid()
            }*/
            else->super.onClick(v)
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

 /*   override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            val id = v.id
            when (id) {
                R.id.fristdigit -> if (keyCode == KeyEvent.KEYCODE_DEL) {
                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        binding.fristdigit.setText("")
                        *//*binding.btnSubmit.setText(Label.getLabel(R.string.resendCode))
                        isResend = true*//*
                        return true
                    }
                }
                R.id.seconddigit -> if (keyCode == KeyEvent.KEYCODE_DEL) {
                    binding.seconddigit.setText("")
                   // binding.edtTwo.setBackgroundResource(R.drawable.otp_box_black)
                    binding.fristdigit.requestFocus()
                    //                        binding.edtTwo.setEnabled(false);
                    //                        binding.edtTwo.setClickable(false);
                    return true
                }
                R.id.thrddigit -> if (keyCode == KeyEvent.KEYCODE_DEL) {
                    binding.thrddigit.setText("")
                  //  binding.edtThree.setBackgroundResource(R.drawable.otp_box_black)
                    binding.seconddigit.requestFocus()
                    //                        binding.edtThree.setEnabled(false);
                    //                        binding.edtThree.setClickable(false);
                    return true
                }
                R.id.fourdigit -> if (keyCode == KeyEvent.KEYCODE_DEL) {
                    binding.fourdigit.setText("")
                 //   binding.edtFour.setBackgroundResource(R.drawable.otp_box_black)
                    binding.thrddigit.requestFocus()
                    //                        binding.edtFour.setEnabled(false);
                    //                        binding.edtFour.setClickable(false);
                    return true
                }
                else -> {
                }
            }
        }
        return false
    }*/
    inner class GenericTextWatcher internal constructor(private val view: View) : TextWatcher {

        override fun afterTextChanged(editable: Editable) {
            // TODO Auto-generated method stub
            val text = editable.toString()
            when (view.id) {

                R.id.fristdigit -> if (text.length == 1) {
                    binding.seconddigit.setEnabled(true)
                    binding.seconddigit.setClickable(true)
                    binding.seconddigit.requestFocus()
                  //  binding.btnSubmit.setText(Label.getLabel(R.string.submit_OTP))
                    //isResend = false
                 //   binding.txtNotGetCode.setVisibility(View.GONE)
                }/* else if (text.length == 0) {
                    binding.txtNotGetCode.setVisibility(View.VISIBLE)
                    binding.btnSubmit.setText(Label.getLabel(R.string.resendCode))
                }*/
                R.id.seconddigit -> if (text.length == 1) {
                    binding.thrddigit.setEnabled(true)
                    binding.thrddigit.setClickable(true)
                    binding.thrddigit.requestFocus()
                }
                R.id.thrddigit -> if (text.length == 1) {
                    binding.fourdigit.setEnabled(true)
                    binding.fourdigit.setClickable(true)
                    binding.fourdigit.requestFocus()
                }
                R.id.fourdigit ->  {

                }
                // callVerifyAPI()
            }
        }

        override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
            // TODO Auto-generated method stub
        }

        override fun onTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
            // TODO Auto-generated method stub

            //String text = editable.toString();
            when (view.id) {

                R.id.fourdigit -> if (binding.fristdigit.length() === 1) {
                    binding.seconddigit.setEnabled(true)
                    binding.seconddigit.setClickable(true)
                    binding.seconddigit.requestFocus()
                   /* binding.btnSubmit.setText(Label.getLabel(R.string.submit_OTP))
                    isResend = false
                    binding.txtNotGetCode.setVisibility(View.GONE)*/
                } /*else if (binding.edtOne.length() === 0) {
                    binding.txtNotGetCode.setVisibility(View.VISIBLE)
                    binding.btnSubmit.setText(Label.getLabel(R.string.resendCode))
                }*/
                R.id.seconddigit -> if (binding.seconddigit.length() === 1) {
                    binding.thrddigit.setEnabled(true)
                    binding.thrddigit.setClickable(true)
                    binding.thrddigit.requestFocus()
                }
                R.id.thrddigit -> if (binding.thrddigit.length() === 1) {
                    binding.fourdigit.setEnabled(true)
                    binding.fourdigit.setClickable(true)
                    binding.fourdigit.requestFocus()
                }
                R.id.fourdigit ->{

                }
                    //callVerifyAPI()
            }
        }
    }
}
