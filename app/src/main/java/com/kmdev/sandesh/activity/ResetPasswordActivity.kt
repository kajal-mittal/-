package com.kmdev.sandesh.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.kmdev.sandesh.R
import android.widget.Toast
import android.widget.EditText
import android.widget.TextView
import com.kmdev.sandesh.core.resetpassword.ResetPasswordContract
import com.kmdev.sandesh.core.resetpassword.ResetPasswordPresenter
import com.kmdev.sandesh.utils.Utils
import com.kmdev.sandesh.utils.Validation


class ResetPasswordActivity : AppCompatActivity(), View.OnClickListener, ResetPasswordContract.ResetPasswordView {
    var mEtEmail: EditText? = null
    var mTvResetPassword: TextView? = null
    var mResetPasswordPresenter: ResetPasswordPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        findViewsById()

    }

    private fun findViewsById() {
        mResetPasswordPresenter = ResetPasswordPresenter(this)
        mEtEmail = findViewById(R.id.edittext_email) as EditText
        mTvResetPassword = findViewById(R.id.tv_reset_password) as TextView

        mTvResetPassword?.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_reset_password -> {
                val email = mEtEmail?.text?.toString()
                val validEmail = Validation.checkEmail(email!!)
                if (TextUtils.isEmpty(email) || !validEmail) {
                    Utils.displayDialog(this, "", getString(R.string.string_valid_password), false)
                } else {
                    Utils.displayLoadingDialog(this, false)
                    mResetPasswordPresenter?.onResetPassword(this, email!!)
                }
            }
        }

    }


    override fun onResetSuccess(message: String) {
        Utils.dismissLoadingDialog()
        Toast.makeText(this, R.string.string_gmail_instructions, 1000).show()
    }

    override fun onResetFailure(message: String) {
        Utils.dismissLoadingDialog()
        Toast.makeText(this, message, 1000).show()
    }

}
