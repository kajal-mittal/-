package com.kmdev.sandesh.fcm

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * Created by Kajal_Mittal on 30/08/17.
 */

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        if (remoteMessage?.data?.size!! > 0) {
            val title = remoteMessage.data["title"]
            val message = remoteMessage.data["text"]
            val username = remoteMessage.data["username"]
            val uid = remoteMessage.data["uid"]
            val fcmToken = remoteMessage.data["fcm_token"]

        }
    }


}