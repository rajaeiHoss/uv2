package com.google.firebase.auth

import com.google.android.gms.internal.zzbgp

interface FirebaseUserMetadata : zzbgp {
    val creationTimestamp: Long

    val lastSignInTimestamp: Long
}
