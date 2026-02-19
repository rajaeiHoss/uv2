package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public final class zzebs extends zzbgl {
    public static final Parcelable.Creator<zzebs> CREATOR = new zzebt();
    private boolean mRegistered;
    private String zzmov;
    private String zzmsg;
    private boolean zzmsh;
    private zzecg zzmsi;
    private List<String> zzmsj;

    public zzebs() {
        this.zzmsi = zzecg.zzbul();
    }

    public zzebs(String str, boolean z, String str2, boolean z2, zzecg zzecg, List<String> list) {
        this.zzmsg = str;
        this.mRegistered = z;
        this.zzmov = str2;
        this.zzmsh = z2;
        this.zzmsi = zzecg == null ? zzecg.zzbul() : zzecg.zza(zzecg);
        this.zzmsj = list;
    }

    public final List<String> getAllProviders() {
        return this.zzmsi.zzbuk();
    }

    public final List<String> getSignInMethods() {
        return this.zzmsj;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzmsg, false);
        zzbgo.zza(parcel, 3, this.mRegistered);
        zzbgo.zza(parcel, 4, this.zzmov, false);
        zzbgo.zza(parcel, 5, this.zzmsh);
        zzbgo.zza(parcel, 6, (Parcelable) this.zzmsi, i, false);
        zzbgo.zzb(parcel, 7, this.zzmsj, false);
        zzbgo.zzai(parcel, zze);
    }
}
