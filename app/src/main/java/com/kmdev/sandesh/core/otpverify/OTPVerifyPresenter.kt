package com.kmdev.sandesh.core.otpverify

import android.app.Activity
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.kmdev.sandesh.utils.Utils

/**
 * Created by Kajal_Mittal on 31/08/17.
 */
class OTPVerifyPresenter(private val mOTPVerifyView: OTPVerifyContract.OTPVerifyView) : OTPVerifyContract.OTPVerifyPresenter {
    override fun verifyOTP(activity: Activity, credential: PhoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(activity, OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = task.result.user
                        mOTPVerifyView.onOTPVerifySuccess(user.toString())
                        // ...
                    } else {
                        // Sign in failed, display a message and update the UI
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                            mOTPVerifyView.onOTPVerifyFailure(task?.exception?.localizedMessage!!)

                        }
                    }
                })
    }
}