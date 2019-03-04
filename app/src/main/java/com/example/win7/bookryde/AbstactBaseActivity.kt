package com.example.win7.bookryde

import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import com.example.win7.bookryde.Interface.OnConfirmation
import com.example.win7.bookryde.Interface.OnDialog
import com.example.win7.bookryde.databinding.DailogConfirmationBinding
import com.example.win7.bookryde.databinding.DailogSimpleBinding

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
    fun showConfirmationDialog(msg: String, onConfirmation: OnConfirmation?) {

        val dialog = Dialog(this)//, R.style.customDialog
        val dialogConfirmationBinding =DailogConfirmationBinding.inflate(layoutInflater)
        dialogConfirmationBinding.msg = msg
        dialogConfirmationBinding.executePendingBindings()
        dialogConfirmationBinding.btnYes.setOnClickListener {
            dialog.dismiss()
            onConfirmation?.onYes()
        }
        dialogConfirmationBinding.btnNo.setOnClickListener {
            dialog.dismiss()
            onConfirmation?.onNo()
        }
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(dialogConfirmationBinding.root)
        dialog.setCancelable(false)
        val window = dialog.window
        window!!.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.CENTER)
        dialog.show()
        val windowManager = getWindow().attributes
        windowManager.dimAmount = 0.2f
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
    }
    fun showSimpleDialog(msg: String, onDialog: OnDialog?) {

        val dialog = Dialog(this)//, R.style.customDialog
        val dialogSimpleBinding =DailogSimpleBinding.inflate(layoutInflater)
        dialogSimpleBinding.msg = msg
        dialogSimpleBinding.executePendingBindings()
        dialogSimpleBinding.btnOk.setOnClickListener {
            dialog.dismiss()
            onDialog?.onOk()
        }
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(dialogSimpleBinding.root)
        dialog.setCancelable(false)
        val window = dialog.window
        window!!.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.CENTER)
        dialog.show()
        val windowManager = getWindow().attributes
        windowManager.dimAmount = 0.2f
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
    }
}