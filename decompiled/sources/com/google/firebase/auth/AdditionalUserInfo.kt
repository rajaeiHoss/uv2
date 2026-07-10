package com.google.firebase.auth

import com.google.android.gms.internal.zzbgp

interface AdditionalUserInfo : zzbgp {
    val profile: Map<String, Any?>?

    val providerId: String

    val username: String?

    val isNewUser: Boolean
}
