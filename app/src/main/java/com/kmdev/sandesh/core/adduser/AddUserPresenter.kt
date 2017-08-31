package com.kmdev.sandesh.core.adduser

import android.app.Activity
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.kmdev.sandesh.R
import com.kmdev.sandesh.model.User
import com.kmdev.sandesh.utils.Constants
import com.kmdev.sandesh.utils.SharedPrefUtil
import com.kmdev.sandesh.utils.Utils

/**
 * Created by Kajal_Mittal on 31/08/17.
 */
class  AddUserPresenter(private val mAddUserView:AddUserContract.AddUserView):AddUserContract.AddUserPresenter{
    override fun addUser(activity: Activity, firebaseUser:FirebaseUser) {
        val database = FirebaseDatabase.getInstance().reference
        val user = User(firebaseUser?.uid!!,
                firebaseUser?.email!!,
                SharedPrefUtil(activity).getString(Constants.ARG_FIREBASE_TOKEN))
        database.child(Constants.ARG_USERS)
                .child(firebaseUser?.uid)
                .setValue(user)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                     mAddUserView?.onAddUserSuccess(activity.getString(R.string.string_user_successfully_added))
                    } else {
                        mAddUserView?.onAddUserFailure(task.exception?.localizedMessage!!)
                    }
                }
    }


}