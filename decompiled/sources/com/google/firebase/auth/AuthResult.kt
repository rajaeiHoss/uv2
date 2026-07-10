package com.google.firebase.auth

import com.google.android.gms.internal.zzbgp

interface AuthResult : zzbgp {
    val additionalUserInfo: AdditionalUserInfo?

    val user: FirebaseUser
}
