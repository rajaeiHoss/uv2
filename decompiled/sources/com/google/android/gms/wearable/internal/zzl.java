package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.wearable.zzd;

public final class zzl extends zzbgl implements zzd {
    public static final Parcelable.Creator<zzl> CREATOR = new zzm();
    private final String mAppId;
    private int mId;
    private final String mPackageName;
    private final String zzemi;
    private final String zzesj;
    private final String zzevt;
    private final String zzlsg;
    private final String zzlsh;
    private final byte zzlsi;
    private final byte zzlsj;
    private final byte zzlsk;
    private final byte zzlsl;

    public zzl(int i, String str, String str2, String str3, String str4, String str5, String str6, byte b, byte b2, byte b3, byte b4, String str7) {
        this.mId = i;
        this.mAppId = str;
        this.zzlsg = str2;
        this.zzevt = str3;
        this.zzesj = str4;
        this.zzlsh = str5;
        this.zzemi = str6;
        this.zzlsi = b;
        this.zzlsj = b2;
        this.zzlsk = b3;
        this.zzlsl = b4;
        this.mPackageName = str7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzl zzl = (zzl) obj;
            if (this.mId != zzl.mId || this.zzlsi != zzl.zzlsi || this.zzlsj != zzl.zzlsj || this.zzlsk != zzl.zzlsk || this.zzlsl != zzl.zzlsl || !this.mAppId.equals(zzl.mAppId)) {
                return false;
            }
            String str = this.zzlsg;
            if (str == null ? zzl.zzlsg != null : !str.equals(zzl.zzlsg)) {
                return false;
            }
            if (!this.zzevt.equals(zzl.zzevt) || !this.zzesj.equals(zzl.zzesj) || !this.zzlsh.equals(zzl.zzlsh)) {
                return false;
            }
            String str2 = this.zzemi;
            if (str2 == null ? zzl.zzemi != null : !str2.equals(zzl.zzemi)) {
                return false;
            }
            String str3 = this.mPackageName;
            String str4 = zzl.mPackageName;
            if (str3 != null) {
                return str3.equals(str4);
            }
            if (str4 == null) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (((this.mId + 31) * 31) + this.mAppId.hashCode()) * 31;
        String str = this.zzlsg;
        int i = 0;
        int hashCode2 = (((((((hashCode + (str != null ? str.hashCode() : 0)) * 31) + this.zzevt.hashCode()) * 31) + this.zzesj.hashCode()) * 31) + this.zzlsh.hashCode()) * 31;
        String str2 = this.zzemi;
        int hashCode3 = (((((((((hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + this.zzlsi) * 31) + this.zzlsj) * 31) + this.zzlsk) * 31) + this.zzlsl) * 31;
        String str3 = this.mPackageName;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode3 + i;
    }

    public final String toString() {
        int i = this.mId;
        String str = this.mAppId;
        String str2 = this.zzlsg;
        String str3 = this.zzevt;
        String str4 = this.zzesj;
        String str5 = this.zzlsh;
        String str6 = this.zzemi;
        byte b = this.zzlsi;
        byte b2 = this.zzlsj;
        byte b3 = this.zzlsk;
        byte b4 = this.zzlsl;
        String str7 = this.mPackageName;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 211 + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str4).length() + String.valueOf(str5).length() + String.valueOf(str6).length() + String.valueOf(str7).length());
        sb.append("AncsNotificationParcelable{, id=");
        sb.append(i);
        sb.append(", appId='");
        sb.append(str);
        sb.append('\'');
        sb.append(", dateTime='");
        sb.append(str2);
        sb.append('\'');
        sb.append(", notificationText='");
        sb.append(str3);
        sb.append('\'');
        sb.append(", title='");
        sb.append(str4);
        sb.append('\'');
        sb.append(", subtitle='");
        sb.append(str5);
        sb.append('\'');
        sb.append(", displayName='");
        sb.append(str6);
        sb.append('\'');
        sb.append(", eventId=");
        sb.append(b);
        sb.append(", eventFlags=");
        sb.append(b2);
        sb.append(", categoryId=");
        sb.append(b3);
        sb.append(", categoryCount=");
        sb.append(b4);
        sb.append(", packageName='");
        sb.append(str7);
        sb.append('\'');
        sb.append('}');
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.mId);
        zzbgo.zza(parcel, 3, this.mAppId, false);
        zzbgo.zza(parcel, 4, this.zzlsg, false);
        zzbgo.zza(parcel, 5, this.zzevt, false);
        zzbgo.zza(parcel, 6, this.zzesj, false);
        zzbgo.zza(parcel, 7, this.zzlsh, false);
        String str = this.zzemi;
        if (str == null) {
            str = this.mAppId;
        }
        zzbgo.zza(parcel, 8, str, false);
        zzbgo.zza(parcel, 9, this.zzlsi);
        zzbgo.zza(parcel, 10, this.zzlsj);
        zzbgo.zza(parcel, 11, this.zzlsk);
        zzbgo.zza(parcel, 12, this.zzlsl);
        zzbgo.zza(parcel, 13, this.mPackageName, false);
        zzbgo.zzai(parcel, zze);
    }
}
