package com.example.win7.bookryde

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Uri
import android.preference.PreferenceManager
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.inputmethod.InputMethodManager

open class Utils {
    companion object {
        private const val ENABLE_LOG = true


        fun checkConnectivity(context: Context): Boolean {
            try {
                val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val info = cm.activeNetworkInfo
                if (info == null) {
                    return false
                } else if (info.isConnectedOrConnecting) {
                    return true
                }
            } catch (e: Exception) {
                printMsg("Error ", e.toString())
            }

            return false
        }// End of checkConnectivity
        fun printMsg(tag: String, msg: String) {
            if (Companion.ENABLE_LOG) {
                Log.e(tag, msg)
            }
        }
        fun hideSoftKeyboard(me: Activity) {
            if (me.currentFocus != null) {
                val inputMethodManager = me.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(me.currentFocus!!.windowToken, 0)
            }
        }

        fun getGCMToken(mContext: Context): String {
            val prefs = PreferenceManager.getDefaultSharedPreferences(mContext)
            return prefs.getString("GCMToken", "")
        }

        fun setGCMToken(GCMToken: String) {
            val prefs = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance())
            prefs.edit().putString("GCMToken", GCMToken).apply()
        }
        fun checkPermission(mContext: Context, permission: String): Boolean {
            return ContextCompat.checkSelfPermission(mContext, permission) == PackageManager.PERMISSION_GRANTED
        }
        fun openMap(context: Context, latitude: Double, longitude: Double, Name: String) {
            try {
                val mUri = "geo:$latitude,$longitude?q=$latitude,$longitude($Name)"
                //String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mUri))
                context.startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }



}