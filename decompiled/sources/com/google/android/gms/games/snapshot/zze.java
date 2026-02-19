package com.google.android.gms.games.snapshot;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.games.internal.zzc;
import com.google.android.gms.internal.zzbgo;

public final class zze extends zzc implements SnapshotMetadataChange {
    public static final Parcelable.Creator<zze> CREATOR = new zzd();
    private final String zzdxh;
    private final Long zzihn;
    private final Uri zzihp;
    private final Long zzihq;
    private BitmapTeleporter zzihr;

    zze() {
        this((String) null, (Long) null, (BitmapTeleporter) null, (Uri) null, (Long) null);
    }

    zze(String str, Long l, BitmapTeleporter bitmapTeleporter, Uri uri, Long l2) {
        this.zzdxh = str;
        this.zzihq = l;
        this.zzihr = bitmapTeleporter;
        this.zzihp = uri;
        this.zzihn = l2;
        boolean z = true;
        if (bitmapTeleporter != null) {
            zzbq.zza(uri != null ? false : z, (Object) "Cannot set both a URI and an image");
        } else if (uri != null) {
            zzbq.zza(bitmapTeleporter != null ? false : z, (Object) "Cannot set both a URI and an image");
        }
    }

    public final Bitmap getCoverImage() {
        BitmapTeleporter bitmapTeleporter = this.zzihr;
        if (bitmapTeleporter == null) {
            return null;
        }
        return bitmapTeleporter.zzalf();
    }

    public final String getDescription() {
        return this.zzdxh;
    }

    public final Long getPlayedTimeMillis() {
        return this.zzihq;
    }

    public final Long getProgressValue() {
        return this.zzihn;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getDescription(), false);
        zzbgo.zza(parcel, 2, getPlayedTimeMillis(), false);
        zzbgo.zza(parcel, 4, (Parcelable) this.zzihp, i, false);
        zzbgo.zza(parcel, 5, (Parcelable) this.zzihr, i, false);
        zzbgo.zza(parcel, 6, getProgressValue(), false);
        zzbgo.zzai(parcel, zze);
    }

    public final BitmapTeleporter zzavy() {
        return this.zzihr;
    }
}
