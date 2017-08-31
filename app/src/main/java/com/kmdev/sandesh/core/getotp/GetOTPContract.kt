package com.kmdev.sandesh.core.getotp

import android.app.Activity

/**
 * Created by Kajal_Mittal on 31/08/17.
 */
interface GetOTPContract {
    interface GetOTPPresenter {
        fun verifyNumber(activity: Activity, phoneNumber: String)
    }

    interface GetOTPView {
        fun onSuccess(message: String)
        fun onFailure(message: String)
    }
}