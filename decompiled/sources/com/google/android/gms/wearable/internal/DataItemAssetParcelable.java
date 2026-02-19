package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.wearable.DataItemAsset;

public class DataItemAssetParcelable extends zzbgl implements ReflectedParcelable, DataItemAsset {
    public static final Parcelable.Creator<DataItemAssetParcelable> CREATOR = new zzda();
    private final String zzbkr;
    private final String zzbzd;

    public DataItemAssetParcelable(DataItemAsset dataItemAsset) {
        this.zzbzd = (String) zzbq.checkNotNull(dataItemAsset.getId());
        this.zzbkr = (String) zzbq.checkNotNull(dataItemAsset.getDataItemKey());
    }

    DataItemAssetParcelable(String str, String str2) {
        this.zzbzd = str;
        this.zzbkr = str2;
    }

    public DataItemAsset freeze() {
        return this;
    }

    public String getDataItemKey() {
        return this.zzbkr;
    }

    public String getId() {
        return this.zzbzd;
    }

    public boolean isDataValid() {
        return true;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("DataItemAssetParcelable[");
        sb.append("@");
        sb.append(Integer.toHexString(hashCode()));
        if (this.zzbzd == null) {
            str = ",noid";
        } else {
            sb.append(",");
            str = this.zzbzd;
        }
        sb.append(str);
        sb.append(", key=");
        sb.append(this.zzbkr);
        sb.append("]");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, getId(), false);
        zzbgo.zza(parcel, 3, getDataItemKey(), false);
        zzbgo.zzai(parcel, zze);
    }
}
