package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgo;
import com.google.firebase.auth.FirebaseUserMetadata;

public final class zzm implements FirebaseUserMetadata {
    public static final Parcelable.Creator<zzm> CREATOR = new zzn();
    private long mCreationTimestamp;
    private long zzmtw;

    public zzm(long j, long j2) {
        this.zzmtw = j;
        this.mCreationTimestamp = j2;
    }

    public final int describeContents() {
        return 0;
    }

    public final long getCreationTimestamp() {
        return this.mCreationTimestamp;
    }

    public final long getLastSignInTimestamp() {
        return this.zzmtw;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getLastSignInTimestamp());
        zzbgo.zza(parcel, 2, getCreationTimestamp());
        zzbgo.zzai(parcel, zze);
    }
}
