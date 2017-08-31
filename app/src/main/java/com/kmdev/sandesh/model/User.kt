package com.kmdev.sandesh.model

/**
 * Created by Kajal_Mittal on 29/08/17.
 */
class User {
    var email: String? = ""
    var uid: String? = ""
    var firebaseToken: String? = ""

    constructor(email: String, uid: String, firebaseToken: String) {
        this.email = email
        this.uid = uid
        this.firebaseToken = firebaseToken
    }
}