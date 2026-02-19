package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.Locale;

public final class ClientAppContext extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<ClientAppContext> CREATOR = new zzd();
    private int versionCode;
    private boolean zzkaw;
    private String zzkay;
    private String zzkct;
    private String zzkcu;
    @Deprecated
    public final int zzkcv;

    ClientAppContext(int i, String str, String str2, boolean z, int i2, String str3) {
        this.versionCode = i;
        this.zzkct = (String) zzbq.checkNotNull(str);
        if (str2 != null && !str2.isEmpty() && !str2.startsWith("0p:")) {
            Log.w("NearbyMessages", String.format(Locale.US, "ClientAppContext: 0P identifier(%s) without 0P prefix(%s)", new Object[]{str2, "0p:"}));
            String valueOf = String.valueOf(str2);
            str2 = valueOf.length() != 0 ? "0p:".concat(valueOf) : new String("0p:");
        }
        this.zzkcu = str2;
        this.zzkaw = z;
        this.zzkcv = i2;
        this.zzkay = str3;
    }

    public ClientAppContext(String str, String str2, boolean z, String str3, int i) {
        this(1, str, str2, z, i, str3);
    }

    static final ClientAppContext zza(ClientAppContext clientAppContext, String str, String str2, boolean z) {
        if (clientAppContext != null) {
            return clientAppContext;
        }
        if (str == null && str2 == null) {
            return null;
        }
        return new ClientAppContext(str, str2, z, (String) null, 0);
    }

    private static boolean zzav(String str, String str2) {
        return TextUtils.isEmpty(str) ? TextUtils.isEmpty(str2) : str.equals(str2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ClientAppContext)) {
            return false;
        }
        ClientAppContext clientAppContext = (ClientAppContext) obj;
        return zzav(this.zzkct, clientAppContext.zzkct) && zzav(this.zzkcu, clientAppContext.zzkcu) && this.zzkaw == clientAppContext.zzkaw && zzav(this.zzkay, clientAppContext.zzkay) && this.zzkcv == clientAppContext.zzkcv;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzkct, this.zzkcu, Boolean.valueOf(this.zzkaw), this.zzkay, Integer.valueOf(this.zzkcv)});
    }

    public final String toString() {
        return String.format(Locale.US, "{realClientPackageName: %s, zeroPartyIdentifier: %s, useRealClientApiKey: %b, apiKey: %s, callingContext: %d}", new Object[]{this.zzkct, this.zzkcu, Boolean.valueOf(this.zzkaw), this.zzkay, Integer.valueOf(this.zzkcv)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.versionCode);
        zzbgo.zza(parcel, 2, this.zzkct, false);
        zzbgo.zza(parcel, 3, this.zzkcu, false);
        zzbgo.zza(parcel, 4, this.zzkaw);
        zzbgo.zzc(parcel, 5, this.zzkcv);
        zzbgo.zza(parcel, 6, this.zzkay, false);
        zzbgo.zzai(parcel, zze);
    }
}
