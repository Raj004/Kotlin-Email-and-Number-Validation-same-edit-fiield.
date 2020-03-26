package com.example.email_phonenum_same_edittext_valdiation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.orbitindustrialservicemaintenance.util.AppUtilities
import com.ordermeal.android.util.ValidationUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var validationUtil: ValidationUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        validationUtil = ValidationUtil(this)
        AppUtilities.hideKeyboard(this)
        main_view?.setOnClickListener {
            AppUtilities.hideKeyboard(this)
        }
        btn.setOnClickListener {
            validation()
        }

    }

    private fun validation() {
        when {
            !validationUtil.isEmptyField(etvalue!!, getString(R.string.hint_email)) -> return
            !checkForEmail() -> return
        }
        Toast.makeText(getApplicationContext(), "Success Validation", Toast.LENGTH_SHORT).show();

    }

    fun checkForEmail(): Boolean {
        val str = etvalue.text.toString()
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(str).matches()) {
            return true
        } else if (android.util.Patterns.PHONE.matcher(str).matches()) {
            if (str.length > 6 && str.length.equals(10)) {
                return true
            } else {
                etvalue.setError("Invalid Phone Number")
                return false
            }
        }
        etvalue.setError("Invalid Email ")
        Toast.makeText(this, "Email or Phone is not valid...", Toast.LENGTH_LONG).show()
        return false
    }
}
