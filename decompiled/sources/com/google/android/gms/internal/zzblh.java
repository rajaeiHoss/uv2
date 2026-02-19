package com.google.android.gms.internal;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import android.text.TextUtils;
import com.google.android.gms.awareness.AwarenessOptions;
import com.google.android.gms.common.util.zzd;
import java.util.Arrays;

public final class zzblh extends zzbgl {
    public static final Parcelable.Creator<zzblh> CREATOR = new zzbli();
    public final String moduleId;
    public final String packageName;
    private int pid;
    private int uid;
    public final String zzgog;
    private int zzgoh;
    private int zzgoi;
    private String zzgoj;
    private String zzgok;
    private int zzgol;
    private zzfb zzgom;

    public zzblh(String str, String str2, int i, String str3, int i2, int i3, String str4, String str5, int i4, int i5) {
        this.zzgog = str;
        this.packageName = str2;
        this.uid = i;
        this.moduleId = str3;
        this.zzgoh = i2;
        this.zzgoi = i3;
        this.zzgoj = str4;
        this.zzgok = str5;
        this.zzgol = i4;
        this.pid = i5;
    }

    public static zzblh zza(Context context, String str, AwarenessOptions awarenessOptions) {
        if (awarenessOptions.getAccount() != null) {
            str = awarenessOptions.getAccount().name;
        }
        return new zzblh(str, context.getPackageName(), Process.myUid(), awarenessOptions.zzada(), zzd.zzt(context, context.getPackageName()), awarenessOptions.zzadb(), awarenessOptions.zzadc(), awarenessOptions.zzadd(), awarenessOptions.zzade(), Process.myPid());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzblh)) {
            return false;
        }
        zzblh zzblh = (zzblh) obj;
        return this.uid == zzblh.uid && this.zzgoh == zzblh.zzgoh && this.zzgoi == zzblh.zzgoi && this.zzgol == zzblh.zzgol && TextUtils.equals(this.zzgog, zzblh.zzgog) && TextUtils.equals(this.packageName, zzblh.packageName) && TextUtils.equals(this.moduleId, zzblh.moduleId) && TextUtils.equals(this.zzgoj, zzblh.zzgoj) && TextUtils.equals(this.zzgok, zzblh.zzgok);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzgog, this.packageName, Integer.valueOf(this.uid), this.moduleId, Integer.valueOf(this.zzgoh), Integer.valueOf(this.zzgoi), this.zzgoj, this.zzgok, Integer.valueOf(this.zzgol)});
    }

    public final String toString() {
        zzfb zzfb;
        if (this.zzgog == null) {
            zzfb = null;
        } else {
            if (this.zzgom == null) {
                this.zzgom = new zzfb(this.zzgog);
            }
            zzfb = this.zzgom;
        }
        String valueOf = String.valueOf(zzfb);
        String str = this.packageName;
        int i = this.uid;
        String str2 = this.moduleId;
        int i2 = this.zzgoh;
        String num = Integer.toString(this.zzgoi);
        String str3 = this.zzgoj;
        String str4 = this.zzgok;
        int i3 = this.pid;
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 89 + String.valueOf(str).length() + String.valueOf(str2).length() + String.valueOf(num).length() + String.valueOf(str3).length() + String.valueOf(str4).length());
        sb.append("(accnt=");
        sb.append(valueOf);
        sb.append(", ");
        sb.append(str);
        sb.append("(");
        sb.append(i);
        sb.append("):");
        sb.append(str2);
        sb.append(", vrsn=");
        sb.append(i2);
        sb.append(", ");
        sb.append(num);
        sb.append(", 3pPkg = ");
        sb.append(str3);
        sb.append(" ,  3pMdlId = ");
        sb.append(str4);
        sb.append(" ,  pid = ");
        sb.append(i3);
        sb.append(")");
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzgog, false);
        zzbgo.zza(parcel, 3, this.packageName, false);
        zzbgo.zzc(parcel, 4, this.uid);
        zzbgo.zza(parcel, 5, this.moduleId, false);
        zzbgo.zzc(parcel, 6, this.zzgoh);
        zzbgo.zzc(parcel, 7, this.zzgoi);
        zzbgo.zza(parcel, 8, this.zzgoj, false);
        zzbgo.zza(parcel, 9, this.zzgok, false);
        zzbgo.zzc(parcel, 10, this.zzgol);
        zzbgo.zzc(parcel, 11, this.pid);
        zzbgo.zzai(parcel, zze);
    }
}
