package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataItemAsset;
import java.util.HashMap;
import java.util.Map;

public final class zzdd extends zzbgl implements DataItem {
    public static final Parcelable.Creator<zzdd> CREATOR = new zzde();
    private final Uri mUri;
    private byte[] zzigl;
    private final Map<String, DataItemAsset> zzluf;

    zzdd(Uri uri, Bundle bundle, byte[] bArr) {
        this.mUri = uri;
        HashMap hashMap = new HashMap();
        bundle.setClassLoader(DataItemAssetParcelable.class.getClassLoader());
        for (String str : bundle.keySet()) {
            hashMap.put(str, (DataItemAssetParcelable) bundle.getParcelable(str));
        }
        this.zzluf = hashMap;
        this.zzigl = bArr;
    }

    public final DataItem freeze() {
        return this;
    }

    public final Map<String, DataItemAsset> getAssets() {
        return this.zzluf;
    }

    public final byte[] getData() {
        return this.zzigl;
    }

    public final Uri getUri() {
        return this.mUri;
    }

    public final boolean isDataValid() {
        return true;
    }

    public final /* synthetic */ DataItem setData(byte[] bArr) {
        this.zzigl = bArr;
        return this;
    }

    public final String toString() {
        String str;
        boolean isLoggable = Log.isLoggable("DataItem", 3);
        StringBuilder sb = new StringBuilder("DataItemParcelable[");
        sb.append("@");
        sb.append(Integer.toHexString(hashCode()));
        byte[] bArr = this.zzigl;
        String valueOf = String.valueOf(bArr == null ? "null" : Integer.valueOf(bArr.length));
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 8);
        sb2.append(",dataSz=");
        sb2.append(valueOf);
        sb.append(sb2.toString());
        int size = this.zzluf.size();
        StringBuilder sb3 = new StringBuilder(23);
        sb3.append(", numAssets=");
        sb3.append(size);
        sb.append(sb3.toString());
        String valueOf2 = String.valueOf(this.mUri);
        StringBuilder sb4 = new StringBuilder(String.valueOf(valueOf2).length() + 6);
        sb4.append(", uri=");
        sb4.append(valueOf2);
        sb.append(sb4.toString());
        if (!isLoggable) {
            str = "]";
        } else {
            sb.append("]\n  assets: ");
            for (String next : this.zzluf.keySet()) {
                String valueOf3 = String.valueOf(this.zzluf.get(next));
                StringBuilder sb5 = new StringBuilder(String.valueOf(next).length() + 7 + String.valueOf(valueOf3).length());
                sb5.append("\n    ");
                sb5.append(next);
                sb5.append(": ");
                sb5.append(valueOf3);
                sb.append(sb5.toString());
            }
            str = "\n  ]";
        }
        sb.append(str);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) getUri(), i, false);
        Bundle bundle = new Bundle();
        bundle.setClassLoader(DataItemAssetParcelable.class.getClassLoader());
        for (Map.Entry next : this.zzluf.entrySet()) {
            bundle.putParcelable((String) next.getKey(), new DataItemAssetParcelable((DataItemAsset) next.getValue()));
        }
        zzbgo.zza(parcel, 4, bundle, false);
        zzbgo.zza(parcel, 5, getData(), false);
        zzbgo.zzai(parcel, zze);
    }
}
