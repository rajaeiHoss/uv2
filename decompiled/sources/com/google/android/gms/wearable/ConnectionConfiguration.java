package com.google.android.gms.wearable;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public class ConnectionConfiguration extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<ConnectionConfiguration> CREATOR = new zzg();
    private final String mName;
    private volatile boolean zzedw;
    private final int zzenu;
    private final int zzgrc;
    private final String zziuy;
    private final boolean zzlqr;
    private volatile String zzlqs;
    private boolean zzlqt;
    private String zzlqu;

    ConnectionConfiguration(String str, String str2, int i, int i2, boolean z, boolean z2, String str3, boolean z3, String str4) {
        this.mName = str;
        this.zziuy = str2;
        this.zzenu = i;
        this.zzgrc = i2;
        this.zzlqr = z;
        this.zzedw = z2;
        this.zzlqs = str3;
        this.zzlqt = z3;
        this.zzlqu = str4;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ConnectionConfiguration)) {
            return false;
        }
        ConnectionConfiguration connectionConfiguration = (ConnectionConfiguration) obj;
        return zzbg.equal(this.mName, connectionConfiguration.mName) && zzbg.equal(this.zziuy, connectionConfiguration.zziuy) && zzbg.equal(Integer.valueOf(this.zzenu), Integer.valueOf(connectionConfiguration.zzenu)) && zzbg.equal(Integer.valueOf(this.zzgrc), Integer.valueOf(connectionConfiguration.zzgrc)) && zzbg.equal(Boolean.valueOf(this.zzlqr), Boolean.valueOf(connectionConfiguration.zzlqr)) && zzbg.equal(Boolean.valueOf(this.zzlqt), Boolean.valueOf(connectionConfiguration.zzlqt));
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.mName, this.zziuy, Integer.valueOf(this.zzenu), Integer.valueOf(this.zzgrc), Boolean.valueOf(this.zzlqr), Boolean.valueOf(this.zzlqt)});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ConnectionConfiguration[ ");
        String valueOf = String.valueOf(this.mName);
        sb.append(valueOf.length() != 0 ? "mName=".concat(valueOf) : new String("mName="));
        String valueOf2 = String.valueOf(this.zziuy);
        sb.append(valueOf2.length() != 0 ? ", mAddress=".concat(valueOf2) : new String(", mAddress="));
        int i = this.zzenu;
        StringBuilder sb2 = new StringBuilder(19);
        sb2.append(", mType=");
        sb2.append(i);
        sb.append(sb2.toString());
        int i2 = this.zzgrc;
        StringBuilder sb3 = new StringBuilder(19);
        sb3.append(", mRole=");
        sb3.append(i2);
        sb.append(sb3.toString());
        boolean z = this.zzlqr;
        StringBuilder sb4 = new StringBuilder(16);
        sb4.append(", mEnabled=");
        sb4.append(z);
        sb.append(sb4.toString());
        boolean z2 = this.zzedw;
        StringBuilder sb5 = new StringBuilder(20);
        sb5.append(", mIsConnected=");
        sb5.append(z2);
        sb.append(sb5.toString());
        String valueOf3 = String.valueOf(this.zzlqs);
        sb.append(valueOf3.length() != 0 ? ", mPeerNodeId=".concat(valueOf3) : new String(", mPeerNodeId="));
        boolean z3 = this.zzlqt;
        StringBuilder sb6 = new StringBuilder(21);
        sb6.append(", mBtlePriority=");
        sb6.append(z3);
        sb.append(sb6.toString());
        String valueOf4 = String.valueOf(this.zzlqu);
        sb.append(valueOf4.length() != 0 ? ", mNodeId=".concat(valueOf4) : new String(", mNodeId="));
        sb.append("]");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.mName, false);
        zzbgo.zza(parcel, 3, this.zziuy, false);
        zzbgo.zzc(parcel, 4, this.zzenu);
        zzbgo.zzc(parcel, 5, this.zzgrc);
        zzbgo.zza(parcel, 6, this.zzlqr);
        zzbgo.zza(parcel, 7, this.zzedw);
        zzbgo.zza(parcel, 8, this.zzlqs, false);
        zzbgo.zza(parcel, 9, this.zzlqt);
        zzbgo.zza(parcel, 10, this.zzlqu, false);
        zzbgo.zzai(parcel, zze);
    }
}
