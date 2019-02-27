package com.example.win7.bookryde

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.WindowManager
import com.example.win7.bookryde.databinding.ActivityBaseBinding

open class BaseActivity : AbstactBaseActivity(), View.OnClickListener {

    lateinit var baseBinding: ActivityBaseBinding
    lateinit var me: BaseActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base)
        init()
    }

    private fun init() {
        //setToolbar(baseBinding.mToolbar)
        me = this

    }
    /*private fun setToolbar(mToolbar: Toolbar) {
        try {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
            window.setWindowAnimations(0)
            setSupportActionBar(mToolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_white)
//            baseBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }*/
    fun setFullScreen() {
        try {
            baseBinding.mToolbar.visibility=View.GONE
            supportActionBar?.hide()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    protected fun setToolbarTitle(title: String) {
        baseBinding.toolbarTitle.text = title
    }

    override fun onClick(v: View?) {

    }
}
