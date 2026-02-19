package com.google.android.gms.internal;

import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ProviderInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.instantapps.InstantAppIntentData;
import java.util.ArrayList;
import java.util.Collections;

public final class zzceu {
    private static Boolean zzipq;
    private static ContentProviderClient zzipr;

    private static synchronized void reset() {
        synchronized (zzceu.class) {
            ContentProviderClient contentProviderClient = zzipr;
            if (contentProviderClient != null) {
                contentProviderClient.release();
                zzipr = null;
            }
            zzipq = null;
        }
    }

    private static synchronized Bundle zza(Context context, String str, String str2, Bundle bundle) throws RemoteException {
        synchronized (zzceu.class) {
            if (zzipr == null) {
                Bundle call = context.getContentResolver().call(zzcdz.zzioz, str, str2, bundle);
                return call;
            }
            Bundle zzc = zzc(str, str2, bundle);
            return zzc;
        }
    }

    static InstantAppIntentData zza(Context context, String str, Intent intent) {
        return zza(context, str, (Parcelable) intent, true);
    }

    private static InstantAppIntentData zza(Context context, String str, Parcelable parcelable, boolean z) {
        Bundle bundle;
        while (context != null && str != null) {
            zzdoj zzed = zzcec.zzed(1401);
            if (!zzdu(context)) {
                return InstantAppIntentData.zziod;
            }
            zzdoi zzdoi = null;
            if (parcelable != null) {
                bundle = new Bundle(1);
                bundle.putParcelable("key_fallbackIntent", parcelable);
            } else {
                bundle = null;
            }
            try {
                Bundle zza = zza(context, "method_getInstantAppIntentData", str, bundle);
                if (zza == null) {
                    return InstantAppIntentData.zziod;
                }
                byte[] byteArray = zza.getByteArray("key_instantAppIntentData");
                if (byteArray == null) {
                    return InstantAppIntentData.zziod;
                }
                InstantAppIntentData instantAppIntentData = (InstantAppIntentData) zzbgq.zza(byteArray, InstantAppIntentData.CREATOR);
                if (!(instantAppIntentData == null || instantAppIntentData.getIntent() == null || !instantAppIntentData.getIntent().hasExtra("key_eventListProtoBytes"))) {
                    Intent intent = instantAppIntentData.getIntent();
                    zzdoj zzed2 = zzcec.zzed(1402);
                    byte[] byteArrayExtra = intent.getByteArrayExtra("key_eventListProtoBytes");
                    if (byteArrayExtra != null) {
                        try {
                            zzdoi = (zzdoi) zzfls.zza(new zzdoi(), byteArrayExtra);
                        } catch (zzflr e) {
                            Log.e("EventLogHelper", "Could not read event list proto", e);
                        }
                    }
                    if (zzdoi == null) {
                        zzdoi = new zzdoi();
                        zzdoi.zzlyl = new zzdoj[]{zzed, zzed2};
                    } else {
                        int length = zzdoi.zzlyl.length + 2;
                        ArrayList arrayList = new ArrayList(length);
                        arrayList.add(zzed);
                        Collections.addAll(arrayList, zzdoi.zzlyl);
                        arrayList.add(zzed2);
                        zzdoi.zzlyl = (zzdoj[]) arrayList.toArray(new zzdoj[length]);
                    }
                    intent.putExtra("key_eventListProtoBytes", zzdoi.zzc(zzdoi));
                }
                return instantAppIntentData;
            } catch (DeadObjectException e2) {
                Log.e("InstantAppsApi", String.format("While calling %s %s:\n", new Object[]{zzcdz.zzioz, "method_getInstantAppIntentData"}), e2);
                reset();
                if (!z) {
                    return InstantAppIntentData.zziod;
                }
                z = false;
            } catch (RemoteException | IllegalArgumentException e3) {
                Log.e("InstantAppsApi", String.format("While calling %s %s:\n", new Object[]{zzcdz.zzioz, "method_getInstantAppIntentData"}), e3);
                return InstantAppIntentData.zziod;
            }
        }
        throw new IllegalArgumentException("Parameter is null");
    }

    private static synchronized Bundle zzc(String str, String str2, Bundle bundle) throws RemoteException {
        Bundle call;
        synchronized (zzceu.class) {
            call = zzipr.call(str, str2, bundle);
        }
        return call;
    }

    private static synchronized boolean zzdt(Context context) {
        boolean z;
        synchronized (zzceu.class) {
            if (zzipr == null) {
                zzipr = context.getApplicationContext().getContentResolver().acquireUnstableContentProviderClient(zzcdz.zzioz);
            }
            z = zzipr != null;
        }
        return z;
    }

    static synchronized boolean zzdu(Context context) {
        synchronized (zzceu.class) {
            if (context != null) {
                Boolean bool = zzipq;
                if (bool != null) {
                    boolean booleanValue = bool.booleanValue();
                    return booleanValue;
                }
                Boolean valueOf = Boolean.valueOf(zzdv(context));
                zzipq = valueOf;
                boolean booleanValue2 = valueOf.booleanValue();
                return booleanValue2;
            }
            throw new IllegalArgumentException("Parameter is null");
        }
    }

    private static synchronized boolean zzdv(Context context) {
        synchronized (zzceu.class) {
            if (!zzcfh.zzdy(context)) {
                return false;
            }
            ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider(zzcdz.zzioz.getAuthority(), 0);
            if (resolveContentProvider == null) {
                return false;
            }
            if (resolveContentProvider.packageName.equals("com.google.android.gms")) {
                return Build.VERSION.SDK_INT < 17 || zzdt(context);
            }
            String valueOf = String.valueOf(resolveContentProvider.packageName);
            Log.e("InstantAppsApi", valueOf.length() != 0 ? "Incorrect package name for instant apps content provider: ".concat(valueOf) : new String("Incorrect package name for instant apps content provider: "));
            return false;
        }
    }
}
