package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Map;

@zzabh
public final class zzsy extends zzbgl {
    public static final Parcelable.Creator<zzsy> CREATOR = new zzsz();
    private String url;
    private String[] zzccx;
    private String[] zzccy;

    zzsy(String str, String[] strArr, String[] strArr2) {
        this.url = str;
        this.zzccx = strArr;
        this.zzccy = strArr2;
    }

    public static zzsy zzh(zzr zzr) throws zza {
        Map<String, String> headers = zzr.getHeaders();
        int size = headers.size();
        String[] strArr = new String[size];
        String[] strArr2 = new String[size];
        int i = 0;
        for (Map.Entry next : headers.entrySet()) {
            strArr[i] = (String) next.getKey();
            strArr2[i] = (String) next.getValue();
            i++;
        }
        return new zzsy(zzr.getUrl(), strArr, strArr2);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.url, false);
        zzbgo.zza(parcel, 2, this.zzccx, false);
        zzbgo.zza(parcel, 3, this.zzccy, false);
        zzbgo.zzai(parcel, zze);
    }
}
