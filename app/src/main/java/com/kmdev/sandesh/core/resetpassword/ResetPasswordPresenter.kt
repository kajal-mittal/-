package com.kmdev.sandesh.core.resetpassword

import android.app.Activity
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.kmdev.sandesh.utils.Utils

/**
 * Created by Kajal_Mittal on 31/08/17.
 */
class ResetPasswordPresenter(private val mResetPasswordView: ResetPasswordContract.ResetPasswordView) : ResetPasswordContract.ResetPasswordPresenter {
    override fun onResetPassword(activity: Activity, email: String) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email!!)
                .addOnCompleteListener(OnCompleteListener<Void> { task ->
                    Utils.dismissLoadingDialog()
                    if (task.isSuccessful) {
                        mResetPasswordView.onResetSuccess(task.result.toString())
                    } else {
                        mResetPasswordView.onResetFailure(task.exception?.localizedMessage!!)
                    }
                })
    }

}