package com.kmdev.sandesh.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.kmdev.sandesh.R
import com.kmdev.sandesh.utils.Utils
import android.widget.Toast
import com.kmdev.sandesh.core.login.LoginContract
import com.kmdev.sandesh.core.login.LoginPresenter
import com.kmdev.sandesh.utils.Validation


class LoginActivity : AppCompatActivity(), View.OnClickListener, LoginContract.LoginView {
    var mTvLogin: TextView? = null
    var mTvSignup: TextView? = null
    var mTvFOrgotPassword: TextView? = null
    var mEtEmail: EditText? = null
    var mEtPassword: EditText? = null
    var mLoginPresenter: LoginPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mTvLogin = findViewById(R.id.tv_login) as TextView
        mTvSignup = findViewById(R.id.tv_signup) as TextView
        mTvFOrgotPassword = findViewById(R.id.tv_forgot_password) as TextView
        mEtEmail = findViewById(R.id.edittext_email) as EditText
        mEtPassword = findViewById(R.id.edittext_password) as EditText

        mLoginPresenter = LoginPresenter(this)
        mTvLogin?.setOnClickListener(this)
        mTvFOrgotPassword?.setOnClickListener(this)
        mTvSignup?.setOnClickListener(this)


    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.tv_signup -> {
                startActivity(Intent(this, SignupActivity::class.java))
            }
            R.id.tv_forgot_password -> {
                startActivity(Intent(this, ResetPasswordActivity::class.java))
            }
            R.id.tv_login -> {
                val email = mEtEmail?.text.toString()
                val password = mEtPassword?.text.toString()
                val validEmail = Validation.checkEmail(email)
                if (TextUtils.isEmpty(email) || !validEmail) {
                    Utils.displayDialog(this, "", getString(R.string.string_valid_email), false)
                } else if (TextUtils.isEmpty(password) || password.length < 6) {
                    Utils.displayDialog(this, "", getString(R.string.string_valid_password), false)
                } else {
                    Utils.displayLoadingDialog(this, false)
                    mLoginPresenter?.performLoginWithEmailAndPassword(this, email, password)
                }
            }
        }
    }

    override fun onLoginSuccess(message: String) {
        Utils.dismissLoadingDialog()
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onLoginFailure(message: String) {
        Utils.dismissLoadingDialog()
        Toast.makeText(this, message, 1000).show()
    }
}
