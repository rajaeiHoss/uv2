package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.tagmanager.ModuleDescriptor;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.HashMap;
import java.util.Map;

final class zzbf {
    private static volatile DynamiteModule zzkpg;
    private static volatile zzcq zzkph;
    /* access modifiers changed from: private */
    public static final Map<String, CustomTagProvider> zzkpi = new HashMap();
    /* access modifiers changed from: private */
    public static final Map<String, CustomVariableProvider> zzkpj = new HashMap();

    private zzbf() {
    }

    static void zza(Intent intent, Context context) {
        zzcq zzek = zzek(context);
        synchronized (zzbf.class) {
            try {
                zzek.previewIntent(intent, zzn.zzz(context), zzn.zzz(zzkpg.zzarl()), zzem(context), new zzbj());
            } catch (RemoteException e) {
                throw new IllegalStateException(e);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0062 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object zzb(java.lang.String r8, java.lang.Class<?> r9) {
        /*
            java.lang.String r0 = " doesn't have an accessible no-arg constructor"
            java.lang.String r1 = "GoogleTagManagerAPI"
            java.lang.Class r2 = java.lang.Class.forName(r8)     // Catch:{ ClassNotFoundException -> 0x0085 }
            java.lang.Class[] r3 = r2.getInterfaces()     // Catch:{ ClassNotFoundException -> 0x0085 }
            int r4 = r3.length     // Catch:{ ClassNotFoundException -> 0x0085 }
            r5 = 0
            r6 = 0
        L_0x000f:
            if (r6 >= r4) goto L_0x001e
            r7 = r3[r6]     // Catch:{ ClassNotFoundException -> 0x0085 }
            boolean r7 = r7.equals(r9)     // Catch:{ ClassNotFoundException -> 0x0085 }
            if (r7 == 0) goto L_0x001b
            r3 = 1
            goto L_0x001f
        L_0x001b:
            int r6 = r6 + 1
            goto L_0x000f
        L_0x001e:
            r3 = 0
        L_0x001f:
            if (r3 != 0) goto L_0x0055
            java.lang.String r9 = r9.getCanonicalName()     // Catch:{ ClassNotFoundException -> 0x0085 }
            java.lang.String r0 = java.lang.String.valueOf(r8)     // Catch:{ ClassNotFoundException -> 0x0085 }
            int r0 = r0.length()     // Catch:{ ClassNotFoundException -> 0x0085 }
            int r0 = r0 + 30
            java.lang.String r2 = java.lang.String.valueOf(r9)     // Catch:{ ClassNotFoundException -> 0x0085 }
            int r2 = r2.length()     // Catch:{ ClassNotFoundException -> 0x0085 }
            int r0 = r0 + r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x0085 }
            r2.<init>(r0)     // Catch:{ ClassNotFoundException -> 0x0085 }
            r2.append(r8)     // Catch:{ ClassNotFoundException -> 0x0085 }
            java.lang.String r0 = " doesn't implement "
            r2.append(r0)     // Catch:{ ClassNotFoundException -> 0x0085 }
            r2.append(r9)     // Catch:{ ClassNotFoundException -> 0x0085 }
            java.lang.String r9 = " interface."
            r2.append(r9)     // Catch:{ ClassNotFoundException -> 0x0085 }
            java.lang.String r9 = r2.toString()     // Catch:{ ClassNotFoundException -> 0x0085 }
        L_0x0051:
            android.util.Log.e(r1, r9)     // Catch:{ ClassNotFoundException -> 0x0085 }
            goto L_0x0092
        L_0x0055:
            java.lang.Class[] r9 = new java.lang.Class[r5]     // Catch:{ NoSuchMethodException -> 0x007e, SecurityException -> 0x0079, InvocationTargetException -> 0x0072, InstantiationException -> 0x006b, IllegalAccessException -> 0x0062 }
            java.lang.reflect.Constructor r9 = r2.getConstructor(r9)     // Catch:{ NoSuchMethodException -> 0x007e, SecurityException -> 0x0079, InvocationTargetException -> 0x0072, InstantiationException -> 0x006b, IllegalAccessException -> 0x0062 }
            java.lang.Object[] r2 = new java.lang.Object[r5]     // Catch:{ NoSuchMethodException -> 0x007e, SecurityException -> 0x0079, InvocationTargetException -> 0x0072, InstantiationException -> 0x006b, IllegalAccessException -> 0x0062 }
            java.lang.Object r8 = r9.newInstance(r2)     // Catch:{ NoSuchMethodException -> 0x007e, SecurityException -> 0x0079, InvocationTargetException -> 0x0072, InstantiationException -> 0x006b, IllegalAccessException -> 0x0062 }
            goto L_0x0093
        L_0x0062:
            java.lang.String r9 = java.lang.String.valueOf(r8)     // Catch:{ ClassNotFoundException -> 0x0085 }
        L_0x0066:
            java.lang.String r9 = r9.concat(r0)     // Catch:{ ClassNotFoundException -> 0x0085 }
            goto L_0x0051
        L_0x006b:
            java.lang.String r9 = java.lang.String.valueOf(r8)     // Catch:{ ClassNotFoundException -> 0x0085 }
            java.lang.String r0 = " is an abstract class."
            goto L_0x0066
        L_0x0072:
            java.lang.String r9 = java.lang.String.valueOf(r8)     // Catch:{ ClassNotFoundException -> 0x0085 }
            java.lang.String r0 = " construction threw an exception."
            goto L_0x0066
        L_0x0079:
            java.lang.String r9 = java.lang.String.valueOf(r8)     // Catch:{ ClassNotFoundException -> 0x0085 }
            goto L_0x0066
        L_0x007e:
            java.lang.String r9 = java.lang.String.valueOf(r8)     // Catch:{ ClassNotFoundException -> 0x0085 }
            java.lang.String r0 = " doesn't have a valid no-arg constructor"
            goto L_0x0066
        L_0x0085:
            java.lang.String r8 = java.lang.String.valueOf(r8)
            java.lang.String r9 = " can't be found in the application."
            java.lang.String r8 = r8.concat(r9)
            android.util.Log.e(r1, r8)
        L_0x0092:
            r8 = 0
        L_0x0093:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzbf.zzb(java.lang.String, java.lang.Class):java.lang.Object");
    }

    static IBinder zzei(Context context) {
        try {
            return zzcu.asInterface(zzel(context).zzhk("com.google.android.gms.tagmanager.TagManagerServiceProviderImpl")).getService(zzn.zzz(context), zzem(context), new zzbj()).asBinder();
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        } catch (DynamiteModule.zzc e2) {
            throw new RuntimeException(e2);
        }
    }

    static void zzej(Context context) {
        zzcq zzek = zzek(context);
        synchronized (zzbf.class) {
            try {
                zzek.initialize(zzn.zzz(context), zzem(context), new zzbj());
            } catch (RemoteException e) {
                throw new IllegalStateException(e);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private static zzcq zzek(Context context) {
        zzcq zzcq = zzkph;
        if (zzcq == null) {
            synchronized (zzbf.class) {
                zzcq = zzkph;
                if (zzcq == null) {
                    try {
                        zzcq asInterface = zzcr.asInterface(zzel(context).zzhk("com.google.android.gms.tagmanager.TagManagerApiImpl"));
                        zzkph = asInterface;
                        zzcq = asInterface;
                    } catch (DynamiteModule.zzc e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return zzcq;
    }

    private static DynamiteModule zzel(Context context) throws DynamiteModule.zzc {
        DynamiteModule dynamiteModule = zzkpg;
        if (dynamiteModule == null) {
            synchronized (zzbf.class) {
                dynamiteModule = zzkpg;
                if (zzkpg == null) {
                    DynamiteModule zza = DynamiteModule.zza(context, DynamiteModule.zzhdm, ModuleDescriptor.MODULE_ID);
                    zzkpg = zza;
                    dynamiteModule = zza;
                }
            }
        }
        return dynamiteModule;
    }

    private static zzcn zzem(Context context) {
        return new zzbg(AppMeasurement.getInstance(context));
    }
}
