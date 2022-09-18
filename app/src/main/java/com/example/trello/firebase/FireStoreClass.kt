package com.example.trello.firebase

import com.example.trello.activitys.SignInActivity
import com.example.trello.activitys.SignUpActivity
import com.example.trello.models.User
import com.example.trello.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FireStoreClass {

    private val mFireStore = FirebaseFirestore.getInstance()

    fun registerUser(activity: SignUpActivity, userInfo: User){
        mFireStore.collection(Constants.USERS).document(getCurrentUserId())
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegisteredSuccess()
            }
    }

    fun getCurrentUserId(): String{
        return FirebaseAuth.getInstance().currentUser!!.uid
    }
}