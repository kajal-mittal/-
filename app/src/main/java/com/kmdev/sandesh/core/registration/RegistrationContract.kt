package com.kmdev.sandesh.core.registration

import android.app.Activity
import android.provider.ContactsContract
import com.google.firebase.auth.FirebaseUser

/**
 * Created by Kajal_Mittal on 30/08/17.
 */
interface RegistrationContract {
    interface RegisterPresenter {
        fun registerOnFirebase(activity: Activity, email: String, password: String)
    }

    interface RegisterView {
        fun onRegisterLSuccess(firebaseUser: FirebaseUser)
        fun onRegisterFailure(message: String)

    }
}