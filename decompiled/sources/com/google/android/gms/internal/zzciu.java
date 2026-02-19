package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Iterator;

public final class zzciu extends zzbgl implements Iterable<String> {
    public static final Parcelable.Creator<zzciu> CREATOR = new zzciw();
    /* access modifiers changed from: private */
    public final Bundle zzegt;

    zzciu(Bundle bundle) {
        this.zzegt = bundle;
    }

    /* access modifiers changed from: package-private */
    public final Object get(String str) {
        return this.zzegt.get(str);
    }

    /* access modifiers changed from: package-private */
    public final Double getDouble(String str) {
        return Double.valueOf(this.zzegt.getDouble(str));
    }

    /* access modifiers changed from: package-private */
    public final Long getLong(String str) {
        return Long.valueOf(this.zzegt.getLong(str));
    }

    /* access modifiers changed from: package-private */
    public final String getString(String str) {
        return this.zzegt.getString(str);
    }

    public final Iterator<String> iterator() {
        return new zzciv(this);
    }

    public final int size() {
        return this.zzegt.size();
    }

    public final String toString() {
        return this.zzegt.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, zzbao(), false);
        zzbgo.zzai(parcel, zze);
    }

    public final Bundle zzbao() {
        return new Bundle(this.zzegt);
    }
}
