package com.kmdev.sandesh.core.adduser

import android.app.Activity
import com.google.firebase.auth.FirebaseUser

/**
 * Created by Kajal_Mittal on 31/08/17.
 */
interface AddUserContract {
    interface AddUserPresenter {
        fun addUser(activity: Activity, firebaseUser:FirebaseUser)
    }

    interface AddUserView {
        fun onAddUserSuccess(message: String)
        fun onAddUserFailure(message: String)

    }
}