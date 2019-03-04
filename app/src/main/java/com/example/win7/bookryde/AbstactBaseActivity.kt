package com.example.win7.bookryde

import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView

abstract class AbstactBaseActivity : AppCompatActivity(){
    companion object {
        var dialog: Dialog? = null
    }

    inner class InternetReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            if (!Utils.checkConnectivity(context)) {
                if (this@AbstactBaseActivity is NoInternetActivity) return
                startActivity(Intent(this@AbstactBaseActivity, NoInternetActivity::class.java))
            } else (this@AbstactBaseActivity as? NoInternetActivity)?.finish()
        }
    }
    fun showSnackBar(context: Context, msg: String) {

        val snackbar = android.support.design.widget.Snackbar.make(window.decorView.rootView.findViewById(R.id.frmContainer), msg, android.support.design.widget.Snackbar.LENGTH_LONG)
        val tv = snackbar.view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        tv.setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
        tv.textAlignment = View.TEXT_ALIGNMENT_CENTER
        snackbar.view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorRed))
        snackbar.show()
    }
}