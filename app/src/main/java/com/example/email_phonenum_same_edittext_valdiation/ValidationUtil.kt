package com.ordermeal.android.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

/**
 * Created by rajshekhar on 9/7/19.
 */

class ValidationUtil (private val context: Context){

    fun isEmptyField(textInputEditText: EditText, message: String): Boolean {
        val value = textInputEditText.text.toString().trim()
        if (value.isEmpty()) {
            //Snackbar.make(textInputEditText, message, Snackbar.LENGTH_LONG).show()
            textInputEditText.error = message
            textInputEditText.isFocusable = true
            hideKeyboardFrom(textInputEditText)

            return false
        }
        return true
    }

    /*fun checkValidation(): Boolean {
        val input = et_email_phone.getText().toString()
        return if (input.contains("@")) {
            android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches()
        } else {
            android.util.Patterns.PHONE.matcher(input).matches()
        }
    }*/


    /**
     * method to check InputEditText has valid email .
     *
     * @param textInputEditText
     * @param textInputLayout
     * @param message
     * @return
     */
    fun isInputEditTextEmail(textInputEditText: EditText, message: String): Boolean {
        val value = textInputEditText.text.toString().trim()
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            Snackbar.make(textInputEditText, message, Snackbar.LENGTH_LONG).show()
            hideKeyboardFrom(textInputEditText)
            return false
        }
        return true
    }

     fun checkValidation(textInputEditText: EditText): Boolean {
        val input = textInputEditText.getText().toString().trim()
        return if (input.contains("@")) {
            android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches()

        } else {
            android.util.Patterns.PHONE.matcher(input).matches()
        }
    }

    /**
     * method to check both InputEditText value matches.
     *
     * @param textInputEditText1
     * @param textInputEditText2
     * @param textInputLayout
     * @param message
     * @return
     */
    fun isLenghtMatch(textInputEditText: EditText, message: String): Boolean {
        val value = textInputEditText.text.toString().trim()
        if (value.length < 6){
            Snackbar.make(textInputEditText, message, Snackbar.LENGTH_LONG).show()
            hideKeyboardFrom(textInputEditText)
            return false
        }
        return true
    }
    fun isPasswordMatches(textInputEditText1: EditText, textInputEditText2 : EditText, message: String): Boolean {
        val value1 = textInputEditText1.text.toString().trim()
        val value2 = textInputEditText2.text.toString().trim()
        if (!value1.contentEquals(value2)) {
            Snackbar.make(textInputEditText1, message, Snackbar.LENGTH_LONG).show()
            hideKeyboardFrom(textInputEditText2)
            return false
        }
        return true
    }

    /**
     * method to Hide keyboard
     *
     * @param view
     */
    private fun hideKeyboardFrom(view: View) {
        //val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //imm.hideSoftInputFromWindow(view.windowToken, WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }

}