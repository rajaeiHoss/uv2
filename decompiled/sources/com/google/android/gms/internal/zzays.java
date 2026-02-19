package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.awareness.state.HeadphoneState;

public final class zzays extends zzbgl implements HeadphoneState {
    public static final Parcelable.Creator<zzays> CREATOR = new zzayt();
    private final int zzerc;

    public zzays(int i) {
        this.zzerc = i;
    }

    public final int getState() {
        return this.zzerc;
    }

    public final String toString() {
        return Integer.toString(this.zzerc);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, getState());
        zzbgo.zzai(parcel, zze);
    }
}
