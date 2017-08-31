package com.kmdev.sandesh.core.getotp

import android.app.Activity
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

/**
 * Created by Kajal_Mittal on 31/08/17.
 */
class GetOTPPresenter(private val mGetOTPView: GetOTPContract.GetOTPView) : GetOTPContract.GetOTPPresenter {
    private var mVerificationId: String? = null
    private var mResendToken: PhoneAuthProvider.ForceResendingToken? = null
    private var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks? = null

    override fun verifyNumber(activity: Activity, phoneNumber: String) {
        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verificaiton without
                //     user action.
            }

            override fun onVerificationFailed(e: FirebaseException) {

                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.

                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // ...
                    mGetOTPView.onFailure(e.localizedMessage)
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // ...
                    mGetOTPView.onFailure(e.localizedMessage)
                }

            }

            override fun onCodeSent(verificationId: String?,
                                    token: PhoneAuthProvider.ForceResendingToken?) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId
                mResendToken = token
                mGetOTPView.onSuccess(mVerificationId.toString())

                // ...
            }
        }
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91xxx", // Phone number to verify enter phone number
                60, // Timeout duration
                TimeUnit.SECONDS, // Unit of timeout
                activity, // Activity (for callback binding)
                mCallbacks as PhoneAuthProvider.OnVerificationStateChangedCallbacks)

    }

}


