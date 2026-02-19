package com.google.android.gms.phenotype;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.UserManager;
import android.util.Log;
import androidx.core.content.PermissionChecker;
import com.google.android.gms.internal.zzdnm;
import com.google.android.gms.internal.zzdob;

@Deprecated
public abstract class PhenotypeFlag<T> {
    static Context zzaiq = null;
    private static boolean zzciw = false;
    private static final Object zzkgs = new Object();
    private static Boolean zzkgt;
    private final T zzinq;
    private final Factory zzkgu;
    final String zzkgv;
    private final String zzkgw;
    private T zzkgx;

    public static class Factory {
        /* access modifiers changed from: private */
        public final String zzkhb;
        /* access modifiers changed from: private */
        public final Uri zzkhc;
        /* access modifiers changed from: private */
        public final String zzkhd;
        /* access modifiers changed from: private */
        public final String zzkhe;
        /* access modifiers changed from: private */
        public final boolean zzkhf;
        /* access modifiers changed from: private */
        public final boolean zzkhg;

        public Factory(Uri uri) {
            this((String) null, uri, "", "", false, false);
        }

        private Factory(String str, Uri uri, String str2, String str3, boolean z, boolean z2) {
            this.zzkhb = str;
            this.zzkhc = uri;
            this.zzkhd = str2;
            this.zzkhe = str3;
            this.zzkhf = z;
            this.zzkhg = z2;
        }

        public PhenotypeFlag<String> createFlag(String str, String str2) {
            return PhenotypeFlag.zza(this, str, str2);
        }

        public Factory withGservicePrefix(String str) {
            boolean z = this.zzkhf;
            if (!z) {
                return new Factory(this.zzkhb, this.zzkhc, str, this.zzkhe, z, this.zzkhg);
            }
            throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
        }

        public Factory withPhenotypePrefix(String str) {
            return new Factory(this.zzkhb, this.zzkhc, this.zzkhd, str, this.zzkhf, this.zzkhg);
        }
    }

    interface zza<V> {
        V zzbel();
    }

    private PhenotypeFlag(Factory factory, String str, T t) {
        this.zzkgx = null;
        if (factory.zzkhb == null && factory.zzkhc == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        } else if (factory.zzkhb == null || factory.zzkhc == null) {
            this.zzkgu = factory;
            String valueOf = String.valueOf(factory.zzkhd);
            String valueOf2 = String.valueOf(str);
            this.zzkgw = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
            String valueOf3 = String.valueOf(factory.zzkhe);
            String valueOf4 = String.valueOf(str);
            this.zzkgv = valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
            this.zzinq = t;
        } else {
            throw new IllegalArgumentException("Must pass one of SharedPreferences file name or ContentProvider URI");
        }
    }

    /* synthetic */ PhenotypeFlag(Factory factory, String str, Object obj, zzr zzr) {
        this(factory, str, (T) obj);
    }

    public static void maybeInit(Context context) {
        zzdob.maybeInit(context);
        if (zzaiq == null) {
            zzdob.zzch(context);
            synchronized (zzkgs) {
                if (Build.VERSION.SDK_INT < 24 || !context.isDeviceProtectedStorage()) {
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext != null) {
                        context = applicationContext;
                    }
                }
                if (zzaiq != context) {
                    zzkgt = null;
                }
                zzaiq = context;
            }
            zzciw = false;
        }
    }

    /* access modifiers changed from: private */
    public static PhenotypeFlag<String> zza(Factory factory, String str, String str2) {
        return new zzs(factory, str, str2);
    }

    private static <V> V zza(zza<V> zza2) {
        try {
            return zza2.zzbel();
        } catch (SecurityException unused) {
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                return zza2.zzbel();
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        }
    }

    private final T zzbeh() {
        if (zzh("gms:phenotype:phenotype_flag:debug_bypass_phenotype", false)) {
            String valueOf = String.valueOf(this.zzkgv);
            Log.w("PhenotypeFlag", valueOf.length() != 0 ? "Bypass reading Phenotype values for flag: ".concat(valueOf) : new String("Bypass reading Phenotype values for flag: "));
        } else if (this.zzkgu.zzkhc != null) {
            String str = (String) zza(new zzo(this, com.google.android.gms.phenotype.zza.zza(zzaiq.getContentResolver(), this.zzkgu.zzkhc)));
            if (str != null) {
                return zzkz(str);
            }
        } else if (this.zzkgu.zzkhb == null || (Build.VERSION.SDK_INT >= 24 && !zzaiq.isDeviceProtectedStorage() && !((UserManager) zzaiq.getSystemService(UserManager.class)).isUserUnlocked())) {
            return null;
        } else {
            SharedPreferences sharedPreferences = zzaiq.getSharedPreferences(this.zzkgu.zzkhb, 0);
            if (sharedPreferences.contains(this.zzkgv)) {
                return zzb(sharedPreferences);
            }
        }
        return null;
    }

    private final T zzbei() {
        String str;
        if (this.zzkgu.zzkhf || !zzbej() || (str = (String) zza(new zzp(this))) == null) {
            return null;
        }
        return zzkz(str);
    }

    private static boolean zzbej() {
        if (zzkgt == null) {
            Context context = zzaiq;
            boolean z = false;
            if (context == null) {
                return false;
            }
            if (PermissionChecker.checkCallingOrSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0) {
                z = true;
            }
            zzkgt = Boolean.valueOf(z);
        }
        return zzkgt.booleanValue();
    }

    static boolean zzh(String str, boolean z) {
        if (zzbej()) {
            return ((Boolean) zza(new zzq(str, false))).booleanValue();
        }
        return false;
    }

    public T get() {
        if (zzaiq != null) {
            if (this.zzkgu.zzkhg) {
                T zzbei = zzbei();
                if (zzbei != null) {
                    return zzbei;
                }
                T zzbeh = zzbeh();
                if (zzbeh != null) {
                    return zzbeh;
                }
            } else {
                T zzbeh2 = zzbeh();
                if (zzbeh2 != null) {
                    return zzbeh2;
                }
                T zzbei2 = zzbei();
                if (zzbei2 != null) {
                    return zzbei2;
                }
            }
            return this.zzinq;
        }
        throw new IllegalStateException("Must call PhenotypeFlag.init() first");
    }

    public abstract T zzb(SharedPreferences sharedPreferences);

    /* access modifiers changed from: package-private */
    public final /* synthetic */ String zzbek() {
        return zzdnm.zza(zzaiq.getContentResolver(), this.zzkgw, (String) null);
    }

    public abstract T zzkz(String str);
}
