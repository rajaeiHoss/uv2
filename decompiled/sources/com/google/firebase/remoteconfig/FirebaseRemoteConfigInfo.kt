package com.google.firebase.remoteconfig

interface FirebaseRemoteConfigInfo {
    val configSettings: FirebaseRemoteConfigSettings

    val fetchTimeMillis: Long

    val lastFetchStatus: Int
}
