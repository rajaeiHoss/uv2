package com.google.android.gms.maps.internal;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamite.DynamiteModule;

public class zzbz {
    private static final String TAG = "zzbz";
    private static Context zzjcs;
    private static zze zzjct;

    private static <T> T zza(ClassLoader classLoader, String str) {
        try {
            return zzd(((ClassLoader) zzbq.checkNotNull(classLoader)).loadClass(str));
        } catch (ClassNotFoundException unused) {
            String valueOf = String.valueOf(str);
            throw new IllegalStateException(valueOf.length() != 0 ? "Unable to find dynamic class ".concat(valueOf) : new String("Unable to find dynamic class "));
        }
    }

    private static <T> T zzd(Class<?> cls) {
        try {
            @SuppressWarnings("unchecked")
            T instance = (T) cls.newInstance();
            return instance;
        } catch (InstantiationException unused) {
            String valueOf = String.valueOf(cls.getName());
            throw new IllegalStateException(valueOf.length() != 0 ? "Unable to instantiate the dynamic class ".concat(valueOf) : new String("Unable to instantiate the dynamic class "));
        } catch (IllegalAccessException unused2) {
            String valueOf2 = String.valueOf(cls.getName());
            throw new IllegalStateException(valueOf2.length() != 0 ? "Unable to call the default constructor of ".concat(valueOf2) : new String("Unable to call the default constructor of "));
        }
    }

    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.maps.internal.zze zzdz(android.content.Context r3) throws com.google.android.gms.common.GooglePlayServicesNotAvailableException {
        /*
            com.google.android.gms.common.internal.zzbq.checkNotNull(r3)
            com.google.android.gms.maps.internal.zze r0 = zzjct
            if (r0 == 0) goto L_0x0008
            return r0
        L_0x0008:
            int r0 = com.google.android.gms.common.GooglePlayServicesUtil.isGooglePlayServicesAvailable(r3)
            if (r0 != 0) goto L_0x005a
            java.lang.String r0 = TAG
            java.lang.String r1 = "Making Creator dynamically"
            android.util.Log.i(r0, r1)
            android.content.Context r0 = zzea(r3)
            java.lang.ClassLoader r0 = r0.getClassLoader()
            java.lang.String r1 = "com.google.android.gms.maps.internal.CreatorImpl"
            java.lang.Object r0 = zza(r0, r1)
            android.os.IBinder r0 = (android.os.IBinder) r0
            if (r0 != 0) goto L_0x0029
            r0 = 0
            goto L_0x003d
        L_0x0029:
            java.lang.String r1 = "com.google.android.gms.maps.internal.ICreator"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.maps.internal.zze
            if (r2 == 0) goto L_0x0037
            r0 = r1
            com.google.android.gms.maps.internal.zze r0 = (com.google.android.gms.maps.internal.zze) r0
            goto L_0x003d
        L_0x0037:
            com.google.android.gms.maps.internal.zzf r1 = new com.google.android.gms.maps.internal.zzf
            r1.<init>(r0)
            r0 = r1
        L_0x003d:
            zzjct = r0
            android.content.Context r3 = zzea(r3)     // Catch:{ RemoteException -> 0x0053 }
            android.content.res.Resources r3 = r3.getResources()     // Catch:{ RemoteException -> 0x0053 }
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.zzn.zzz(r3)     // Catch:{ RemoteException -> 0x0053 }
            int r1 = com.google.android.gms.common.GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE     // Catch:{ RemoteException -> 0x0053 }
            r0.zzi(r3, r1)     // Catch:{ RemoteException -> 0x0053 }
            com.google.android.gms.maps.internal.zze r3 = zzjct
            return r3
        L_0x0053:
            r3 = move-exception
            com.google.android.gms.maps.model.RuntimeRemoteException r0 = new com.google.android.gms.maps.model.RuntimeRemoteException
            r0.<init>(r3)
            throw r0
        L_0x005a:
            com.google.android.gms.common.GooglePlayServicesNotAvailableException r3 = new com.google.android.gms.common.GooglePlayServicesNotAvailableException
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zzbz.zzdz(android.content.Context):com.google.android.gms.maps.internal.zze");
    }

    private static Context zzea(Context context) {
        Context context2 = zzjcs;
        if (context2 != null) {
            return context2;
        }
        Context zzeb = zzeb(context);
        zzjcs = zzeb;
        return zzeb;
    }

    private static Context zzeb(Context context) {
        try {
            return DynamiteModule.zza(context, DynamiteModule.zzhdi, "com.google.android.gms.maps_dynamite").zzarl();
        } catch (Throwable th) {
            Log.e(TAG, "Failed to load maps module, use legacy", th);
            return GooglePlayServicesUtil.getRemoteContext(context);
        }
    }
}
