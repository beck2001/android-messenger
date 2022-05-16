package kz.iitu.androidmessenger.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kz.iitu.androidmessenger.model.User

lateinit var AUTH : FirebaseAuth
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var USER: User
lateinit var UID: String

const val NODE_USERS = "users"
const val CHILD_ID = "id"
const val CHILD_PHONE_NUMBER = "phone"
const val CHILD_USERNAME = "username"
const val CHILD_FULL_NAME = "fullName"

fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase
        .getInstance("https://androidmessenger-ddaca-default-rtdb.asia-southeast1.firebasedatabase.app/")
        .reference
    USER = User()
    UID = AUTH.currentUser?.uid.toString()
}