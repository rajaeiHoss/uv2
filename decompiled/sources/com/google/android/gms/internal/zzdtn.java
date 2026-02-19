package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdwg;
import com.google.android.gms.internal.zzdwp;
import java.security.GeneralSecurityException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class zzdtn {
    private static final Logger logger = Logger.getLogger(zzdtn.class.getName());
    private static final ConcurrentMap<String, zzdtf> zzmfb = new ConcurrentHashMap();
    private static final ConcurrentMap<String, Boolean> zzmfc = new ConcurrentHashMap();
    private static final ConcurrentMap<String, zzdsz> zzmfd = new ConcurrentHashMap();

    public static <P> zzdtl<P> zza(zzdtg zzdtg, zzdtf<P> zzdtf) throws GeneralSecurityException {
        zzdwp zzboe = zzdtg.zzboe();
        if (zzboe.zzbrl() != 0) {
            int zzbrj = zzboe.zzbrj();
            boolean z = false;
            boolean z2 = true;
            for (zzdwp.zzb next : zzboe.zzbrk()) {
                if (!next.zzbrn()) {
                    throw new GeneralSecurityException(String.format("key %d has no key data", new Object[]{Integer.valueOf(next.zzbrq())}));
                } else if (next.zzbrr() == zzdxb.UNKNOWN_PREFIX) {
                    throw new GeneralSecurityException(String.format("key %d has unknown prefix", new Object[]{Integer.valueOf(next.zzbrq())}));
                } else if (next.zzbrp() != zzdwj.UNKNOWN_STATUS) {
                    if (next.zzbrp() == zzdwj.ENABLED && next.zzbrq() == zzbrj) {
                        if (!z) {
                            z = true;
                        } else {
                            throw new GeneralSecurityException("keyset contains multiple primary keys");
                        }
                    }
                    if (next.zzbro().zzbqw() != zzdwg.zzb.ASYMMETRIC_PUBLIC) {
                        z2 = false;
                    }
                } else {
                    throw new GeneralSecurityException(String.format("key %d has unknown status", new Object[]{Integer.valueOf(next.zzbrq())}));
                }
            }
            if (z || z2) {
                zzdtl<P> zzdtl = new zzdtl<>();
                for (zzdwp.zzb next2 : zzdtg.zzboe().zzbrk()) {
                    if (next2.zzbrp() == zzdwj.ENABLED) {
                        zzdtm<P> zza = zzdtl.zza(zza(next2.zzbro().zzbqu(), next2.zzbro().zzbqv()), next2);
                        if (next2.zzbrq() == zzdtg.zzboe().zzbrj()) {
                            zzdtl.zza(zza);
                        }
                    }
                }
                return zzdtl;
            }
            throw new GeneralSecurityException("keyset doesn't contain a valid primary key");
        }
        throw new GeneralSecurityException("empty keyset");
    }

    public static <P> zzdwg zza(zzdwl zzdwl) throws GeneralSecurityException {
        zzdtf zzon = zzon(zzdwl.zzbqu());
        if (((Boolean) zzmfc.get(zzdwl.zzbqu())).booleanValue()) {
            return zzon.zzc(zzdwl.zzbqv());
        }
        String valueOf = String.valueOf(zzdwl.zzbqu());
        throw new GeneralSecurityException(valueOf.length() != 0 ? "newKey-operation not permitted for key type ".concat(valueOf) : new String("newKey-operation not permitted for key type "));
    }

    public static <P> zzfjc zza(String str, zzfjc zzfjc) throws GeneralSecurityException {
        zzdtf zzon = zzon(str);
        if (((Boolean) zzmfc.get(str)).booleanValue()) {
            return zzon.zzb(zzfjc);
        }
        String valueOf = String.valueOf(str);
        throw new GeneralSecurityException(valueOf.length() != 0 ? "newKey-operation not permitted for key type ".concat(valueOf) : new String("newKey-operation not permitted for key type "));
    }

    private static <P> P zza(String str, zzfgs zzfgs) throws GeneralSecurityException {
        return (P) zzon(str).zza(zzfgs);
    }

    public static synchronized <P> void zza(String str, zzdsz<P> zzdsz) throws GeneralSecurityException {
        synchronized (zzdtn.class) {
            ConcurrentMap<String, zzdsz> concurrentMap = zzmfd;
            if (!concurrentMap.containsKey(str.toLowerCase()) || zzdsz.getClass().equals(((zzdsz) concurrentMap.get(str.toLowerCase())).getClass())) {
                concurrentMap.put(str.toLowerCase(), zzdsz);
            } else {
                Logger logger2 = logger;
                Level level = Level.WARNING;
                String valueOf = String.valueOf(str);
                logger2.logp(level, "com.google.crypto.tink.Registry", "addCatalogue", valueOf.length() != 0 ? "Attempted overwrite of a catalogueName catalogue for name ".concat(valueOf) : new String("Attempted overwrite of a catalogueName catalogue for name "));
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 47);
                sb.append("catalogue for name ");
                sb.append(str);
                sb.append(" has been already registered");
                throw new GeneralSecurityException(sb.toString());
            }
        }
    }

    public static <P> void zza(String str, zzdtf<P> zzdtf) throws GeneralSecurityException {
        zza(str, zzdtf, true);
    }

    public static synchronized <P> void zza(String str, zzdtf<P> zzdtf, boolean z) throws GeneralSecurityException {
        synchronized (zzdtn.class) {
            if (zzdtf != null) {
                ConcurrentMap<String, zzdtf> concurrentMap = zzmfb;
                if (concurrentMap.containsKey(str)) {
                    zzdtf zzon = zzon(str);
                    boolean booleanValue = ((Boolean) zzmfc.get(str)).booleanValue();
                    if (!zzdtf.getClass().equals(zzon.getClass()) || (!booleanValue && z)) {
                        Logger logger2 = logger;
                        Level level = Level.WARNING;
                        String valueOf = String.valueOf(str);
                        logger2.logp(level, "com.google.crypto.tink.Registry", "registerKeyManager", valueOf.length() != 0 ? "Attempted overwrite of a registered key manager for key type ".concat(valueOf) : new String("Attempted overwrite of a registered key manager for key type "));
                        throw new GeneralSecurityException(String.format("typeUrl (%s) is already registered with %s, cannot be re-registered with %s", new Object[]{str, zzon.getClass().getName(), zzdtf.getClass().getName()}));
                    }
                }
                concurrentMap.put(str, zzdtf);
                zzmfc.put(str, Boolean.valueOf(z));
            } else {
                throw new IllegalArgumentException("key manager must be non-null.");
            }
        }
    }

    public static <P> zzfjc zzb(zzdwl zzdwl) throws GeneralSecurityException {
        zzdtf zzon = zzon(zzdwl.zzbqu());
        if (((Boolean) zzmfc.get(zzdwl.zzbqu())).booleanValue()) {
            return zzon.zzb(zzdwl.zzbqv());
        }
        String valueOf = String.valueOf(zzdwl.zzbqu());
        throw new GeneralSecurityException(valueOf.length() != 0 ? "newKey-operation not permitted for key type ".concat(valueOf) : new String("newKey-operation not permitted for key type "));
    }

    public static <P> P zzb(String str, zzfjc zzfjc) throws GeneralSecurityException {
        return (P) zzon(str).zza(zzfjc);
    }

    public static <P> P zzf(String str, byte[] bArr) throws GeneralSecurityException {
        return zza(str, zzfgs.zzaz(bArr));
    }

    public static <P> zzdsz<P> zzom(String str) throws GeneralSecurityException {
        String str2;
        String str3;
        if (str != null) {
            zzdsz<P> zzdsz = (zzdsz) zzmfd.get(str.toLowerCase());
            if (zzdsz != null) {
                return zzdsz;
            }
            String format = String.format("no catalogue found for %s. ", new Object[]{str});
            if (str.toLowerCase().startsWith("tinkaead")) {
                format = String.valueOf(format).concat("Maybe call AeadConfig.init().");
            }
            if (str.toLowerCase().startsWith("tinkdeterministicaead")) {
                str2 = String.valueOf(format);
                str3 = "Maybe call DeterministicAeadConfig.init().";
            } else if (str.toLowerCase().startsWith("tinkstreamingaead")) {
                str2 = String.valueOf(format);
                str3 = "Maybe call StreamingAeadConfig.init().";
            } else if (str.toLowerCase().startsWith("tinkhybriddecrypt") || str.toLowerCase().startsWith("tinkhybridencrypt")) {
                str2 = String.valueOf(format);
                str3 = "Maybe call HybridConfig.init().";
            } else if (str.toLowerCase().startsWith("tinkmac")) {
                str2 = String.valueOf(format);
                str3 = "Maybe call MacConfig.init().";
            } else if (str.toLowerCase().startsWith("tinkpublickeysign") || str.toLowerCase().startsWith("tinkpublickeyverify")) {
                str2 = String.valueOf(format);
                str3 = "Maybe call SignatureConfig.init().";
            } else {
                if (str.toLowerCase().startsWith("tink")) {
                    str2 = String.valueOf(format);
                    str3 = "Maybe call TinkConfig.init().";
                }
                throw new GeneralSecurityException(format);
            }
            format = str2.concat(str3);
            throw new GeneralSecurityException(format);
        }
        throw new IllegalArgumentException("catalogueName must be non-null.");
    }

    private static <P> zzdtf<P> zzon(String str) throws GeneralSecurityException {
        zzdtf<P> zzdtf = (zzdtf) zzmfb.get(str);
        if (zzdtf != null) {
            return zzdtf;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 78);
        sb.append("No key manager found for key type: ");
        sb.append(str);
        sb.append(".  Check the configuration of the registry.");
        throw new GeneralSecurityException(sb.toString());
    }
}
