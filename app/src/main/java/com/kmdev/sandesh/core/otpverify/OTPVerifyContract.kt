package com.kmdev.sandesh.core.otpverify

import android.app.Activity
import android.net.wifi.hotspot2.pps.Credential
import com.google.firebase.auth.PhoneAuthCredential

/**
 * Created by Kajal_Mittal on 31/08/17.
 */
interface OTPVerifyContract {
    interface OTPVerifyView {
        fun onOTPVerifySuccess(message: String)
        fun onOTPVerifyFailure(message: String)
    }

    interface OTPVerifyPresenter {
        fun verifyOTP(activity: Activity, credential: PhoneAuthCredential)
    }
}