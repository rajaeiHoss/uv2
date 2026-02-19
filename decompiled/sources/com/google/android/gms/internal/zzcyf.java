package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.search.GoogleNowAuthState;
import com.google.android.gms.search.SearchAuthApi;

final class zzcyf implements SearchAuthApi.GoogleNowAuthResult {
    private final Status zzefs;
    private final GoogleNowAuthState zzklj;

    zzcyf(Status status, GoogleNowAuthState googleNowAuthState) {
        this.zzefs = status;
        this.zzklj = googleNowAuthState;
    }

    public final GoogleNowAuthState getGoogleNowAuthState() {
        return this.zzklj;
    }

    public final Status getStatus() {
        return this.zzefs;
    }
}
