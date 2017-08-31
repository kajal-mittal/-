package com.kmdev.sandesh.core.registration

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.kmdev.sandesh.R
import com.kmdev.sandesh.activity.OTPVerifyActivity
import com.kmdev.sandesh.model.User
import com.kmdev.sandesh.utils.Constants
import com.kmdev.sandesh.utils.SharedPrefUtil
import com.kmdev.sandesh.utils.Utils

/**
 * Created by Kajal_Mittal on 30/08/17.
 */
class RegistrationPresenter(private val mREgisterView: RegistrationContract.RegisterView) : RegistrationContract.RegisterPresenter {
    override fun registerOnFirebase(activity: Activity, email: String, password: String) {
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, OnCompleteListener<AuthResult> { task ->
                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
                    if (!task.isSuccessful) {
                        mREgisterView.onRegisterFailure(task.exception?.localizedMessage!!)

                    } else {
                        mREgisterView.onRegisterLSuccess(task.result.user)
                        // Add the user to users table.
                        /*DatabaseReference database= FirebaseDatabase.getInstance().getReference();
                  User user = new User(task.getResult().getUser().getUid(), email);
                    database.child("users").push().setValue(user);*/


                    }

                })
    }

}