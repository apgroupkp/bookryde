package com.example.win7.bookryde.Drawer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.win7.bookryde.BaseActivity
import com.example.win7.bookryde.R
import com.example.win7.bookryde.binding
import com.example.win7.bookryde.databinding.ActivityReferAFriendBinding
import com.example.win7.bookryde.databinding.ActivityTermsConditionBinding

class TermsConditionActivity : BaseActivity() ,View.OnClickListener{
lateinit var binding: ActivityTermsConditionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsConditionBinding.inflate(layoutInflater, baseBinding.frmContainer, true)
        init()
    }
    fun init(){
        setToolbarTitle(getString(R.string.terms_and_condition))
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
