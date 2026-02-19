package com.google.android.gms.internal;

import java.security.GeneralSecurityException;

final class zzdto implements zzdsz<zzdsy> {
    public final zzdtf<zzdsy> zzd(String str, String str2, int i) throws GeneralSecurityException {
        zzdtf<zzdsy> zzdtf;
        String lowerCase = str2.toLowerCase();
        lowerCase.hashCode();
        if (!lowerCase.equals("aead")) {
            throw new GeneralSecurityException(String.format("No support for primitive '%s'.", new Object[]{str2}));
        }
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 360753376:
                if (str.equals("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key")) {
                    c = 0;
                    break;
                }
                break;
            case 1215885937:
                if (str.equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
                    c = 1;
                    break;
                }
                break;
            case 1469984853:
                if (str.equals("type.googleapis.com/google.crypto.tink.KmsAeadKey")) {
                    c = 2;
                    break;
                }
                break;
            case 1797113348:
                if (str.equals("type.googleapis.com/google.crypto.tink.AesEaxKey")) {
                    c = 3;
                    break;
                }
                break;
            case 1855890991:
                if (str.equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
                    c = 4;
                    break;
                }
                break;
            case 2079211877:
                if (str.equals("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey")) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                zzdtf = new zzdtu();
                break;
            case 1:
                zzdtf = new zzdtq();
                break;
            case 2:
                zzdtf = new zzdtv();
                break;
            case 3:
                zzdtf = new zzdts();
                break;
            case 4:
                zzdtf = new zzdtt();
                break;
            case 5:
                zzdtf = new zzdtx();
                break;
            default:
                throw new GeneralSecurityException(String.format("No support for primitive 'Aead' with key type '%s'.", new Object[]{str}));
        }
        if (zzdtf.getVersion() >= i) {
            return zzdtf;
        }
        throw new GeneralSecurityException(String.format("No key manager for key type '%s' with version at least %d.", new Object[]{str, Integer.valueOf(i)}));
    }
}
