package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public class PlacePhotoMetadataResult extends zzbgl implements Result {
    public static final Parcelable.Creator<PlacePhotoMetadataResult> CREATOR = new zzj();
    private final Status mStatus;
    private DataHolder zzivw;
    private final PlacePhotoMetadataBuffer zzivx;

    public PlacePhotoMetadataResult(Status status, DataHolder dataHolder) {
        this.mStatus = status;
        this.zzivw = dataHolder;
        this.zzivx = dataHolder == null ? null : new PlacePhotoMetadataBuffer(dataHolder);
    }

    public PlacePhotoMetadataBuffer getPhotoMetadata() {
        return this.zzivx;
    }

    public Status getStatus() {
        return this.mStatus;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) getStatus(), i, false);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzivw, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
