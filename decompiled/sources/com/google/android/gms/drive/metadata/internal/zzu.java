package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.UserMetadata;
import java.util.Arrays;
import java.util.Collections;

public final class zzu extends zzm<UserMetadata> {
    public zzu(String str, int i) {
        super(str, Arrays.asList(new String[]{zzy(str, "permissionId"), zzy(str, "displayName"), zzy(str, "picture"), zzy(str, "isAuthenticatedUser"), zzy(str, "emailAddress")}), Collections.emptyList(), 6000000);
    }

    private final String zzhi(String str) {
        return zzy(getName(), str);
    }

    private static String zzy(String str, String str2) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(str2).length());
        sb.append(str);
        sb.append(".");
        sb.append(str2);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public final boolean zzb(DataHolder dataHolder, int i, int i2) {
        return dataHolder.zzgj(zzhi("permissionId")) && !dataHolder.zzh(zzhi("permissionId"), i, i2);
    }

    /* access modifiers changed from: protected */
    public final UserMetadata zzc(DataHolder dataHolder, int i, int i2) {
        String zzd = dataHolder.zzd(zzhi("permissionId"), i, i2);
        if (zzd == null) {
            return null;
        }
        String zzd2 = dataHolder.zzd(zzhi("displayName"), i, i2);
        String zzd3 = dataHolder.zzd(zzhi("picture"), i, i2);
        Boolean valueOf = Boolean.valueOf(dataHolder.zze(zzhi("isAuthenticatedUser"), i, i2));
        return new UserMetadata(zzd, zzd2, zzd3, valueOf.booleanValue(), dataHolder.zzd(zzhi("emailAddress"), i, i2));
    }
}
