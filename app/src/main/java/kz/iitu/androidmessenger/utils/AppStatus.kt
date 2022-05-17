package kz.iitu.androidmessenger.utils

import android.util.Log

enum class AppStatus(val status: String) {
    ONLINE("online"),
    OFFLINE("last seen recently"),
    TYPING("typing...");

    companion object {
        fun updateStatus(appStatus: AppStatus) {
            REF_DATABASE_ROOT.child(NODE_USERS)
                .child(UID)
                .child(CHILD_STATUS)
                .setValue(appStatus.status)
                .addOnSuccessListener {
                    USER.status = appStatus.status
                }
                .addOnFailureListener {
                    Log.e("APP_STATUS", it.message.toString())
                }

        }
    }
}