package com.google.android.gms.location.places;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public class PlacePhotoResult extends zzbgl implements Result {
    public static final Parcelable.Creator<PlacePhotoResult> CREATOR = new zzk();
    private final Bitmap mBitmap;
    private final Status mStatus;
    private BitmapTeleporter zzivy;

    public PlacePhotoResult(Status status, BitmapTeleporter bitmapTeleporter) {
        this.mStatus = status;
        this.zzivy = bitmapTeleporter;
        this.mBitmap = bitmapTeleporter != null ? bitmapTeleporter.zzalf() : null;
    }

    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    public Status getStatus() {
        return this.mStatus;
    }

    public String toString() {
        return zzbg.zzx(this).zzg("status", this.mStatus).zzg("bitmap", this.mBitmap).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) getStatus(), i, false);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzivy, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
