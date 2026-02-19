package com.google.firebase.appindexing.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.streamax.config.constant.Constants;

public final class zzb extends zzbgl {
    public static final Parcelable.Creator<zzb> CREATOR = new zzy();
    private int zzegm = 0;
    private final String zzgog;
    private final boolean zzmnt;
    private final boolean zzmnu;
    private final String zzmoa;
    private final byte[] zzmob;

    zzb(int i, boolean z, String str, String str2, byte[] bArr, boolean z2) {
        this.zzegm = i;
        this.zzmnt = z;
        this.zzmoa = str;
        this.zzgog = str2;
        this.zzmob = bArr;
        this.zzmnu = z2;
    }

    public zzb(boolean z, String str, String str2, byte[] bArr, boolean z2) {
        this.zzmnt = z;
        this.zzmoa = null;
        this.zzgog = null;
        this.zzmob = null;
        this.zzmnu = false;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MetadataImpl { ");
        sb.append("{ eventStatus: '");
        sb.append(this.zzegm);
        sb.append("' } ");
        sb.append("{ uploadable: '");
        sb.append(this.zzmnt);
        sb.append("' } ");
        if (this.zzmoa != null) {
            sb.append("{ completionToken: '");
            sb.append(this.zzmoa);
            sb.append("' } ");
        }
        if (this.zzgog != null) {
            sb.append("{ accountName: '");
            sb.append(this.zzgog);
            sb.append("' } ");
        }
        if (this.zzmob != null) {
            sb.append("{ ssbContext: [ ");
            for (byte hexString : this.zzmob) {
                sb.append("0x");
                sb.append(Integer.toHexString(hexString));
                sb.append(" ");
            }
            sb.append("] } ");
        }
        sb.append("{ contextOnly: '");
        sb.append(this.zzmnu);
        sb.append("' } ");
        sb.append(Constants.JsonSstringSuffix);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzegm);
        zzbgo.zza(parcel, 2, this.zzmnt);
        zzbgo.zza(parcel, 3, this.zzmoa, false);
        zzbgo.zza(parcel, 4, this.zzgog, false);
        zzbgo.zza(parcel, 5, this.zzmob, false);
        zzbgo.zza(parcel, 6, this.zzmnu);
        zzbgo.zzai(parcel, zze);
    }

    public final void zzhb(int i) {
        this.zzegm = i;
    }
}
