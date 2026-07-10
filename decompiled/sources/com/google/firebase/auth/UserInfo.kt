package com.google.firebase.auth

import android.net.Uri

interface UserInfo {
    val displayName: String?

    val email: String?

    val phoneNumber: String?

    val photoUrl: Uri?

    val providerId: String

    val uid: String

    val isEmailVerified: Boolean
}
