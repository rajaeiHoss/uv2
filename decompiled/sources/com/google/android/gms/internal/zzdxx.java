package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdxy;
import com.google.android.gms.security.ProviderInstaller;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.Mac;

public final class zzdxx<T_WRAPPER extends zzdxy<T_ENGINE>, T_ENGINE> {
    private static final Logger logger = Logger.getLogger(zzdxx.class.getName());
    private static final List<Provider> zzmlj;
    public static final zzdxx<zzdxz, Cipher> zzmlk = new zzdxx<>(new zzdxz());
    public static final zzdxx<zzdyd, Mac> zzmll = new zzdxx<>(new zzdyd());
    private static zzdxx<zzdyf, Signature> zzmlm = new zzdxx<>(new zzdyf());
    private static zzdxx<zzdye, MessageDigest> zzmln = new zzdxx<>(new zzdye());
    public static final zzdxx<zzdya, KeyAgreement> zzmlo = new zzdxx<>(new zzdya());
    public static final zzdxx<zzdyc, KeyPairGenerator> zzmlp = new zzdxx<>(new zzdyc());
    public static final zzdxx<zzdyb, KeyFactory> zzmlq = new zzdxx<>(new zzdyb());
    private T_WRAPPER zzmlr;
    private List<Provider> zzmls = zzmlj;
    private boolean zzmlt = true;

    static {
        if (zzdyo.zzakt()) {
            String[] strArr = {ProviderInstaller.PROVIDER_NAME, "AndroidOpenSSL"};
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < 2; i++) {
                String str = strArr[i];
                Provider provider = Security.getProvider(str);
                if (provider != null) {
                    arrayList.add(provider);
                } else {
                    logger.logp(Level.INFO, "com.google.crypto.tink.subtle.EngineFactory", "toProviderList", String.format("Provider %s not available", new Object[]{str}));
                }
            }
            zzmlj = arrayList;
        } else {
            zzmlj = new ArrayList();
        }
    }

    private zzdxx(T_WRAPPER t_wrapper) {
        this.zzmlr = t_wrapper;
    }

    private final boolean zza(String str, Provider provider) {
        try {
            this.zzmlr.zzb(str, provider);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public final T_ENGINE zzoy(String str) throws GeneralSecurityException {
        T_WRAPPER t_wrapper;
        Provider provider;
        Iterator<Provider> it = this.zzmls.iterator();
        while (true) {
            if (it.hasNext()) {
                provider = it.next();
                if (zza(str, provider)) {
                    t_wrapper = this.zzmlr;
                    break;
                }
            } else if (this.zzmlt) {
                t_wrapper = this.zzmlr;
                provider = null;
            } else {
                throw new GeneralSecurityException("No good Provider found.");
            }
        }
        return t_wrapper.zzb(str, provider);
    }
}
