package com.kmdev.sandesh.core.resetpassword

import android.app.Activity

/**
 * Created by Kajal_Mittal on 31/08/17.
 */
interface ResetPasswordContract {
    interface ResetPasswordView {
        fun onResetSuccess(message: String)
        fun onResetFailure(message: String)
    }

    interface ResetPasswordPresenter {
        fun onResetPassword(activity: Activity, email: String)

    }
}