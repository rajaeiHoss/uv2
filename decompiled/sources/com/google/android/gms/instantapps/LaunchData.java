package com.google.android.gms.instantapps;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public class LaunchData extends zzbgl {
    public static final Parcelable.Creator<LaunchData> CREATOR = new zzh();
    private final Intent intent;
    private final String packageName;
    private final String zzioh;
    private BitmapTeleporter zzioi;
    private final Bitmap zzioj;

    public LaunchData(Intent intent2, String str, String str2, BitmapTeleporter bitmapTeleporter) {
        this.intent = intent2;
        this.packageName = str;
        this.zzioh = str2;
        this.zzioi = bitmapTeleporter;
        this.zzioj = bitmapTeleporter != null ? bitmapTeleporter.zzalf() : null;
    }

    public Bitmap getApplicationIcon() {
        return this.zzioj;
    }

    public String getApplicationLabel() {
        return this.zzioh;
    }

    public Intent getIntent() {
        return this.intent;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) getIntent(), i, false);
        zzbgo.zza(parcel, 3, getPackageName(), false);
        zzbgo.zza(parcel, 4, getApplicationLabel(), false);
        zzbgo.zza(parcel, 5, (Parcelable) this.zzioi, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
