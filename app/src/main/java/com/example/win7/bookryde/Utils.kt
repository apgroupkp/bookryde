package com.example.win7.bookryde

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log

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
    }



}