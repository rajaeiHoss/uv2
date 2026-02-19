package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;

abstract class zzcwl extends Plus.zza<People.LoadPeopleResult> {
    private zzcwl(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* synthetic */ zzcwl(GoogleApiClient googleApiClient, zzcwg zzcwg) {
        this(googleApiClient);
    }

    public final People.LoadPeopleResult zzb(Status status) {
        return new zzcwm(this, status);
    }
}
