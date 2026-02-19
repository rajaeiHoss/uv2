package com.google.android.gms.cast.framework.media;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public class ImageHints extends zzbgl {
    public static final Parcelable.Creator<ImageHints> CREATOR = new zzi();
    private final int zzenu;
    private final int zzfdv;
    private final int zzfdw;

    public ImageHints(int i, int i2, int i3) {
        this.zzenu = i;
        this.zzfdv = i2;
        this.zzfdw = i3;
    }

    public int getHeightInPixels() {
        return this.zzfdw;
    }

    public int getType() {
        return this.zzenu;
    }

    public int getWidthInPixels() {
        return this.zzfdv;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, getType());
        zzbgo.zzc(parcel, 3, getWidthInPixels());
        zzbgo.zzc(parcel, 4, getHeightInPixels());
        zzbgo.zzai(parcel, zze);
    }
}
