package com.kmdev.sandesh.core.login

import android.app.Activity
import com.google.firebase.auth.FirebaseUser

/**
 * Created by Kajal_Mittal on 30/08/17.
 */
interface LoginContract {
    interface LoginPresenter {
        fun performLoginWithEmailAndPassword(activity: Activity, email: String, password: String)
    }

    interface LoginView {
        fun onLoginSuccess(message: String)
        fun onLoginFailure(message: String)
    }


}