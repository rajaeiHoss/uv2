package com.google.firebase.appindexing.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.firebase.appindexing.Action;
import com.streamax.config.constant.Constants;

public final class zza extends zzbgl implements Action {
    public static final Parcelable.Creator<zza> CREATOR = new zzc();
    private final String zzmnn;
    private final String zzmno;
    private final String zzmnp;
    private final String zzmnq;
    private final zzb zzmnr;
    private final String zzmns;

    public zza(String str, String str2, String str3, String str4, zzb zzb, String str5) {
        this.zzmnn = str;
        this.zzmno = str2;
        this.zzmnp = str3;
        this.zzmnq = str4;
        this.zzmnr = zzb;
        this.zzmns = str5;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ActionImpl { ");
        sb.append("{ actionType: '");
        sb.append(this.zzmnn);
        sb.append("' } ");
        sb.append("{ objectName: '");
        sb.append(this.zzmno);
        sb.append("' } ");
        sb.append("{ objectUrl: '");
        sb.append(this.zzmnp);
        sb.append("' } ");
        if (this.zzmnq != null) {
            sb.append("{ objectSameAs: '");
            sb.append(this.zzmnq);
            sb.append("' } ");
        }
        if (this.zzmnr != null) {
            sb.append("{ metadata: '");
            sb.append(this.zzmnr.toString());
            sb.append("' } ");
        }
        if (this.zzmns != null) {
            sb.append("{ actionStatus: '");
            sb.append(this.zzmns);
            sb.append("' } ");
        }
        sb.append(Constants.JsonSstringSuffix);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzmnn, false);
        zzbgo.zza(parcel, 2, this.zzmno, false);
        zzbgo.zza(parcel, 3, this.zzmnp, false);
        zzbgo.zza(parcel, 4, this.zzmnq, false);
        zzbgo.zza(parcel, 5, (Parcelable) this.zzmnr, i, false);
        zzbgo.zza(parcel, 6, this.zzmns, false);
        zzbgo.zzai(parcel, zze);
    }

    public final zzb zzbtf() {
        return this.zzmnr;
    }
}
