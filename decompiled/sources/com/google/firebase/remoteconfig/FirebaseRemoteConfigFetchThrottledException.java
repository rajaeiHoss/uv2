package com.google.firebase.remoteconfig;

public class FirebaseRemoteConfigFetchThrottledException extends FirebaseRemoteConfigFetchException {
    private final long zzgmh;

    public FirebaseRemoteConfigFetchThrottledException(long j) {
        this.zzgmh = j;
    }

    public long getThrottleEndTimeMillis() {
        return this.zzgmh;
    }
}
