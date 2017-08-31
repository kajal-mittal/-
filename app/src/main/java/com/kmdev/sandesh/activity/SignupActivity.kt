package com.kmdev.sandesh.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView

import com.kmdev.sandesh.R
import android.widget.Toast
import com.kmdev.sandesh.utils.Utils
import com.google.firebase.auth.FirebaseUser
import com.kmdev.sandesh.core.adduser.AddUserContract
import com.kmdev.sandesh.core.adduser.AddUserPresenter
import com.kmdev.sandesh.core.getotp.GetOTPContract
import com.kmdev.sandesh.core.getotp.GetOTPPresenter
import com.kmdev.sandesh.core.registration.RegistrationContract
import com.kmdev.sandesh.core.registration.RegistrationPresenter
import com.kmdev.sandesh.utils.Constants
import com.kmdev.sandesh.utils.Validation

class SignupActivity : AppCompatActivity(), View.OnClickListener, RegistrationContract.RegisterView, GetOTPContract.GetOTPView, AddUserContract.AddUserView {
    var mEtEmail: EditText? = null
    var mEtPassword: EditText? = null
    var mEtPhoneNumber: EditText? = null
    var mTvSignup: TextView? = null
    var mEtName: EditText? = null
    var mAddUserPresenter: AddUserPresenter? = null
    var mRegisterPresenter: RegistrationPresenter? = null
    var mGetOTPPresenter: GetOTPPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        findViewsById()

    }

    private fun findViewsById() {
        mTvSignup = findViewById(R.id.tv_signup) as TextView
        mEtEmail = findViewById(R.id.edittext_email) as EditText
        mEtPassword = findViewById(R.id.edittext_password) as EditText
        mEtPhoneNumber = findViewById(R.id.edittext_phone_number) as EditText
        mEtName = findViewById(R.id.edittext_name) as EditText

        mRegisterPresenter = RegistrationPresenter(this)
        mAddUserPresenter = AddUserPresenter(this)
        mGetOTPPresenter = GetOTPPresenter(this)
        mTvSignup?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        //when replaces the switch operator of C-like languages. In the simplest form it looks like this
        when (p0!!.id) {
            R.id.tv_signup -> {
                val name = mEtName?.text?.toString()
                val password = mEtPassword?.text?.toString()
                val phoneNumber = mEtPhoneNumber?.text?.toString()
                val email = mEtEmail?.text?.toString()
                val validEmail = Validation.checkEmail(email!!) as Boolean
                if (TextUtils.isEmpty(email) || !validEmail) {
                    Utils.displayDialog(this, "", getString(R.string.string_valid_email), false)
                } else if (password?.length!! < 6) {
                    Utils.displayDialog(this, "", getString(R.string.string_valid_password), false)
                } else if (TextUtils.isEmpty(name)) {
                    Utils.displayDialog(this, "", getString(R.string.string_valid_name), false)
                } else if (!TextUtils.isEmpty(name)
                        && !TextUtils.isEmpty(password)
                        && !TextUtils.isEmpty(phoneNumber)
                        && !TextUtils.isEmpty(email)
                        && validEmail) {
                    Utils.displayLoadingDialog(this, false)
                    mRegisterPresenter?.registerOnFirebase(this, email!!, password!!)
                }
            }

        }
    }

    override fun onRegisterLSuccess(firebaseUser: FirebaseUser) {
        mAddUserPresenter?.addUser(this, firebaseUser)
    }

    override fun onRegisterFailure(message: String) {
        Utils.dismissLoadingDialog()
        Toast.makeText(this, message, 1000).show()

    }

    override fun onAddUserSuccess(message: String) {
        //phone number format like +911234567890
        mGetOTPPresenter?.verifyNumber(this, mEtPhoneNumber?.text.toString())

    }

    override fun onAddUserFailure(message: String) {
        Utils.dismissLoadingDialog()
        Toast.makeText(this, message, 1000).show()
    }

    override fun onSuccess(message: String) {
        Utils.dismissLoadingDialog()
        Toast.makeText(this, R.string.string_user_successfully_added, 1000).show()
        val intentVerify = Intent(this, OTPVerifyActivity::class.java)
        intentVerify?.putExtra(Constants.ARG_PHONE_NUMBER, message)
        startActivity(intentVerify)

    }

    override fun onFailure(message: String) {
        Utils.dismissLoadingDialog()
        Toast.makeText(this, message, 1000).show()


    }


}

