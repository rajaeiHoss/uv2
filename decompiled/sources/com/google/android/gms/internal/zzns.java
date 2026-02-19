package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.VideoOptions;

@zzabh
public final class zzns extends zzbgl {
    public static final Parcelable.Creator<zzns> CREATOR = new zznt();
    public final boolean zzbkn;
    public final boolean zzbko;
    public final boolean zzbkp;

    public zzns(VideoOptions videoOptions) {
        this(videoOptions.getStartMuted(), videoOptions.getCustomControlsRequested(), videoOptions.getClickToExpandRequested());
    }

    public zzns(boolean z, boolean z2, boolean z3) {
        this.zzbkn = z;
        this.zzbko = z2;
        this.zzbkp = z3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzbkn);
        zzbgo.zza(parcel, 3, this.zzbko);
        zzbgo.zza(parcel, 4, this.zzbkp);
        zzbgo.zzai(parcel, zze);
    }
}
