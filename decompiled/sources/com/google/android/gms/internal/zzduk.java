package com.google.android.gms.internal;

import java.security.GeneralSecurityException;

final class zzduk implements zzdsz<zzdtj> {
    public final zzdtf<zzdtj> zzd(String str, String str2, int i) throws GeneralSecurityException {
        String lowerCase = str2.toLowerCase();
        lowerCase.hashCode();
        if (!lowerCase.equals("mac")) {
            throw new GeneralSecurityException(String.format("No support for primitive '%s'.", new Object[]{str2}));
        }
        str.hashCode();
        if (!str.equals("type.googleapis.com/google.crypto.tink.HmacKey")) {
            throw new GeneralSecurityException(String.format("No support for primitive 'Mac' with key type '%s'.", new Object[]{str}));
        }
        zzdui zzdui = new zzdui();
        if (i <= 0) {
            return zzdui;
        }
        throw new GeneralSecurityException(String.format("No key manager for key type '%s' with version at least %d.", new Object[]{str, Integer.valueOf(i)}));
    }
}
