package com.example.orbitindustrialservicemaintenance.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity

object AppUtilities {
    private var progressDialog: ProgressDialog? = null


    @SuppressLint("NewApi")
    fun hideKeyboard(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // Check if no view has focus
        val currentFocusedView = activity.currentFocus
        currentFocusedView?.let {
            inputMethodManager.hideSoftInputFromWindow(
                currentFocusedView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }


    fun showProgress(context: Context, message: String, isCancellable: Boolean) {

        try {
            progressDialog = ProgressDialog(context)
            progressDialog!!.setTitle("")
            progressDialog!!.setIndeterminate(false)
            progressDialog!!.setMessage(message)
            progressDialog!!.setCancelable(isCancellable)
            progressDialog!!.show()
        } catch (e: Exception) {

        }
    }

    fun dismissProgress(requireActivity: FragmentActivity, s: String, b: Boolean) {
        try {
            if (progressDialog != null && progressDialog!!.isShowing()) {
                progressDialog!!.dismiss()
                progressDialog = null
            }
        } catch (e: Exception) {

        }

    }

}