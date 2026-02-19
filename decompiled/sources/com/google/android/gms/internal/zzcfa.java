package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.instantapps.PackageManagerCompat;

public final class zzcfa implements PackageManagerCompat {
    private static zzcfa zzipz;
    private final Context zzaiq;
    private final boolean zziqa = true;

    private zzcfa(Context context, boolean z) {
        this.zzaiq = context;
    }

    public static synchronized zzcfa zzf(Context context, boolean z) {
        zzcfa zzcfa;
        synchronized (zzcfa.class) {
            Context applicationContext = context.getApplicationContext();
            zzcfa zzcfa2 = zzipz;
            if (!(zzcfa2 != null && zzcfa2.zzaiq == applicationContext && zzcfa2.zziqa)) {
                zzipz = new zzcfa(applicationContext, true);
            }
            zzcfa = zzipz;
        }
        return zzcfa;
    }

    public final ApplicationInfo getApplicationInfo(String str, int i) throws PackageManager.NameNotFoundException {
        if (this.zziqa) {
            try {
                return this.zzaiq.getPackageManager().getApplicationInfo(str, i);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        zzcev zzdw = zzcev.zzdw(this.zzaiq);
        if (zzdw != null) {
            try {
                ApplicationInfo applicationInfo = zzdw.getApplicationInfo(str, i);
                if (applicationInfo != null) {
                    return applicationInfo;
                }
            } catch (RemoteException e) {
                Log.e("InstantAppsPMW", "Error getting application info", e);
            }
        }
        throw new PackageManager.NameNotFoundException();
    }

    public final CharSequence getApplicationLabel(ApplicationInfo applicationInfo) {
        if (this.zziqa && this.zzaiq.getPackageManager().getPackagesForUid(applicationInfo.uid) != null) {
            return this.zzaiq.getPackageManager().getApplicationLabel(applicationInfo);
        }
        zzcev zzdw = zzcev.zzdw(this.zzaiq);
        if (zzdw == null) {
            return null;
        }
        try {
            return zzdw.zzik(applicationInfo.packageName);
        } catch (RemoteException e) {
            Log.e("InstantAppsPMW", "Error getting application label", e);
            return null;
        }
    }

    public final String getInstallerPackageName(String str) {
        IllegalArgumentException e;
        if (this.zziqa) {
            try {
                return this.zzaiq.getPackageManager().getInstallerPackageName(str);
            } catch (IllegalArgumentException e2) {
                e = e2;
                throw e;
            }
        } else {
            e = null;
            zzcev zzdw = zzcev.zzdw(this.zzaiq);
            if (zzdw != null) {
                try {
                    if (zzdw.zzij(str) != 0) {
                        return "com.android.vending";
                    }
                } catch (RemoteException e3) {
                    Log.e("InstantAppsPMW", "Error getting UID for app package", e3);
                }
            }
            if (e == null) {
                e = new IllegalArgumentException();
            }
            throw e;
        }
    }

    public final byte[] getInstantAppCookie() {
        zzcev zzdw = zzcev.zzdw(this.zzaiq);
        if (zzdw == null) {
            return null;
        }
        try {
            return zzdw.zzeg(Process.myUid());
        } catch (RemoteException e) {
            Log.e("InstantAppsPMW", "Error setting cookie", e);
            return null;
        }
    }

    public final int getInstantAppCookieMaxSize() {
        zzcev zzdw = zzcev.zzdw(this.zzaiq);
        if (zzdw == null) {
            return 0;
        }
        try {
            return zzdw.getInstantAppCookieMaxSize();
        } catch (RemoteException e) {
            Log.e("InstantAppsPMW", "Error fetching max cookie size", e);
            return 0;
        }
    }

    public final PackageInfo getPackageInfo(String str, int i) throws PackageManager.NameNotFoundException {
        if (this.zziqa) {
            try {
                return this.zzaiq.getPackageManager().getPackageInfo(str, i);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        zzcev zzdw = zzcev.zzdw(this.zzaiq);
        if (zzdw != null) {
            try {
                PackageInfo packageInfo = zzdw.getPackageInfo(str, i);
                if (packageInfo != null) {
                    return packageInfo;
                }
            } catch (RemoteException e) {
                Log.e("InstantAppsPMW", "Error getting package info", e);
            }
        }
        throw new PackageManager.NameNotFoundException();
    }

    public final String[] getPackagesForUid(int i) {
        String[] packagesForUid;
        if (this.zziqa && (packagesForUid = this.zzaiq.getPackageManager().getPackagesForUid(i)) != null) {
            return packagesForUid;
        }
        zzcev zzdw = zzcev.zzdw(this.zzaiq);
        if (zzdw != null) {
            try {
                String zzef = zzdw.zzef(i);
                if (zzef == null) {
                    return null;
                }
                return new String[]{zzef};
            } catch (RemoteException e) {
                Log.e("InstantAppsPMW", "Error getting app package for UID", e);
            }
        }
        return null;
    }

    public final boolean isInstantApp() {
        return isInstantApp(this.zzaiq.getPackageName());
    }

    public final boolean isInstantApp(String str) {
        zzcev zzdw = zzcev.zzdw(this.zzaiq);
        if (zzdw == null) {
            return false;
        }
        try {
            return zzdw.isInstantApp(str);
        } catch (RemoteException e) {
            Log.e("InstantAppsPMW", "Error checking if app is instant app", e);
            return false;
        }
    }

    public final boolean setInstantAppCookie(byte[] bArr) {
        zzcev zzdw = zzcev.zzdw(this.zzaiq);
        if (zzdw == null) {
            return false;
        }
        try {
            return zzdw.zza(Process.myUid(), bArr);
        } catch (RemoteException e) {
            Log.e("InstantAppsPMW", "Error setting cookie", e);
            return false;
        }
    }
}
