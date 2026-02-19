package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.formats.NativeAdOptions;

@zzabh
public final class zzqh extends zzbgl {
    public static final Parcelable.Creator<zzqh> CREATOR = new zzqi();
    public final int versionCode;
    public final boolean zzbzj;
    public final int zzbzk;
    public final boolean zzbzl;
    public final int zzbzm;
    public final zzns zzbzn;

    public zzqh(int i, boolean z, int i2, boolean z2, int i3, zzns zzns) {
        this.versionCode = i;
        this.zzbzj = z;
        this.zzbzk = i2;
        this.zzbzl = z2;
        this.zzbzm = i3;
        this.zzbzn = zzns;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public zzqh(NativeAdOptions nativeAdOptions) {
        this(3, nativeAdOptions.shouldReturnUrlsForImageAssets(), nativeAdOptions.getImageOrientation(), nativeAdOptions.shouldRequestMultipleImages(), nativeAdOptions.getAdChoicesPlacement(), nativeAdOptions.getVideoOptions() != null ? new zzns(nativeAdOptions.getVideoOptions()) : null);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.versionCode);
        zzbgo.zza(parcel, 2, this.zzbzj);
        zzbgo.zzc(parcel, 3, this.zzbzk);
        zzbgo.zza(parcel, 4, this.zzbzl);
        zzbgo.zzc(parcel, 5, this.zzbzm);
        zzbgo.zza(parcel, 6, (Parcelable) this.zzbzn, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
