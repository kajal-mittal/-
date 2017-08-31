package com.kmdev.sandesh.core.login

import android.app.Activity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

/**
 * Created by Kajal_Mittal on 30/08/17.
 */
class LoginPresenter(private val mLoginView: LoginContract.LoginView) : LoginContract.LoginPresenter {

    override fun performLoginWithEmailAndPassword(activity: Activity, email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, OnCompleteListener<AuthResult> { task ->
                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
                    if (!task.isSuccessful) {
                        // there was an error
                        if (password.length < 6) {
                        } else {
                            mLoginView.onLoginFailure(task.exception?.localizedMessage!!)
                        }
                    } else {
                        mLoginView.onLoginSuccess(task.result.toString())

                    }
                })
    }

}