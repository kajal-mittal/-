package com.kmdev.sandesh.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.kmdev.sandesh.R
import com.kmdev.sandesh.utils.Constants
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.*
import com.kmdev.sandesh.core.otpverify.OTPVerifyContract
import com.kmdev.sandesh.core.otpverify.OTPVerifyPresenter
import com.kmdev.sandesh.utils.Utils


class OTPVerifyActivity : AppCompatActivity(), View.OnClickListener, OTPVerifyContract.OTPVerifyView {


    var mTvVerify: TextView? = null
    var mEtOTP: EditText? = null
    var verificationId: String? = null
    var mOTPVerifyPresenter: OTPVerifyPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpverify)
        findViewsById()
    }

    private fun findViewsById() {
        mTvVerify = findViewById(R.id.tv_verify) as TextView
        mEtOTP = findViewById(R.id.edittext_otp) as EditText
        verificationId = intent.getStringExtra(Constants.ARG_VERFICATION_ID)
        mOTPVerifyPresenter = OTPVerifyPresenter(this)
        mTvVerify?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.tv_verify -> {
                val code = mEtOTP?.text.toString()
                if(!TextUtils.isEmpty(code)) {
                    Utils.displayLoadingDialog(this, false)
                    val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
                    mOTPVerifyPresenter?.verifyOTP(this, credential)
                }else{
                    Utils.displayDialog(this,"",getString(R.string.string_fill_code),false)
                }
            }
        }

    }

    override fun onOTPVerifySuccess(message: String) {
        Utils.dismissLoadingDialog()
        Toast.makeText(this, R.string.string_otp_verify, 1000).show()
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onOTPVerifyFailure(message: String) {
        Utils.dismissLoadingDialog()
        Utils.displayDialog(this, "", message, false)
    }


}
