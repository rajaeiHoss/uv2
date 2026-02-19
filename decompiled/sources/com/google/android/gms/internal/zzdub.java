package com.google.android.gms.internal;

import java.security.GeneralSecurityException;

final class zzdub implements zzdsz<zzdtd> {
    public final zzdtf<zzdtd> zzd(String str, String str2, int i) throws GeneralSecurityException {
        String lowerCase = str2.toLowerCase();
        lowerCase.hashCode();
        if (!lowerCase.equals("hybriddecrypt")) {
            throw new GeneralSecurityException(String.format("No support for primitive '%s'.", new Object[]{str2}));
        }
        str.hashCode();
        if (!str.equals("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey")) {
            throw new GeneralSecurityException(String.format("No support for primitive 'HybridEncrypt' with key type '%s'.", new Object[]{str}));
        }
        zzdty zzdty = new zzdty();
        if (i <= 0) {
            return zzdty;
        }
        throw new GeneralSecurityException(String.format("No key manager for key type '%s' with version at least %d.", new Object[]{str, Integer.valueOf(i)}));
    }
}
