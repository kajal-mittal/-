package com.kmdev.sandesh.fcm

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import com.kmdev.sandesh.utils.Constants
import com.kmdev.sandesh.utils.SharedPrefUtil

/**
 * Created by Kajal_Mittal on 30/08/17.
 */
class MyFirebaseInstanceIdService : FirebaseInstanceIdService(){
    override fun onTokenRefresh() {
        super.onTokenRefresh()
        val token=FirebaseInstanceId.getInstance().token
        sendRegisterToSenver(token)

    }

    private fun sendRegisterToSenver(token: String?) {
        if (token != null) {
            SharedPrefUtil(applicationContext).saveString(Constants.ARG_FIREBASE_TOKEN,token)
        }
    }

}