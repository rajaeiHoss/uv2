package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ProviderInfo;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import org.jivesoftware.smackx.Form;

public final class zzcev {
    private static zzcev zzips;
    private final Context mContext;

    private zzcev(Context context) {
        this.mContext = context;
    }

    public static synchronized zzcev zzdw(Context context) {
        zzcev zzcev;
        synchronized (zzcev.class) {
            if (zzips == null) {
                Context applicationContext = context.getApplicationContext();
                zzcev zzcev2 = null;
                if (Build.VERSION.SDK_INT >= 16) {
                    if (zzcfh.zzdy(applicationContext)) {
                        ProviderInfo resolveContentProvider = applicationContext.getPackageManager().resolveContentProvider(zzcew.zzipt.getAuthority(), 0);
                        if (resolveContentProvider != null) {
                            if (!resolveContentProvider.packageName.equals("com.google.android.gms")) {
                                String str = resolveContentProvider.packageName;
                                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 85);
                                sb.append("Package ");
                                sb.append(str);
                                sb.append(" is invalid for instant apps content provider; instant apps will be disabled.");
                                Log.e("IAMetadataClient", sb.toString());
                            } else {
                                zzcev2 = new zzcev(applicationContext);
                            }
                        }
                    }
                }
                zzips = zzcev2;
            }
            zzcev = zzips;
        }
        return zzcev;
    }

    private final Bundle zzg(String str, Bundle bundle) throws RemoteException {
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Bundle call = this.mContext.getContentResolver().call(zzcew.zzipt, str, (String) null, bundle);
            if (call != null) {
                return call;
            }
            throw new RemoteException();
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }

    public final ApplicationInfo getApplicationInfo(String str, int i) throws RemoteException {
        Bundle bundle = new Bundle();
        bundle.putString("packageName", str);
        bundle.putInt("flags", i);
        return (ApplicationInfo) zzg("getWHApplicationInfo", bundle).getParcelable(Form.TYPE_RESULT);
    }

    public final int getInstantAppCookieMaxSize() throws RemoteException {
        return zzg("getInstantAppCookieMaxSize", new Bundle()).getInt(Form.TYPE_RESULT);
    }

    public final PackageInfo getPackageInfo(String str, int i) throws RemoteException {
        Bundle bundle = new Bundle();
        bundle.putString("packageName", str);
        bundle.putInt("flags", i);
        return (PackageInfo) zzg("getWHPackageInfo", bundle).getParcelable(Form.TYPE_RESULT);
    }

    public final boolean isInstantApp(String str) throws RemoteException {
        Bundle bundle = new Bundle();
        bundle.putString("packageName", str);
        return zzg("isInstantApp", bundle).getBoolean(Form.TYPE_RESULT);
    }

    public final boolean zza(int i, byte[] bArr) throws RemoteException {
        Bundle bundle = new Bundle();
        bundle.putInt("uid", i);
        bundle.putByteArray("cookie", bArr);
        return zzg("setInstantAppCookie", bundle).getBoolean(Form.TYPE_RESULT);
    }

    public final String zzef(int i) throws RemoteException {
        Bundle bundle = new Bundle();
        bundle.putInt("uid", i);
        return zzg("getAppPackageForUid", bundle).getString(Form.TYPE_RESULT);
    }

    public final byte[] zzeg(int i) throws RemoteException {
        Bundle bundle = new Bundle();
        bundle.putInt("uid", i);
        return zzg("getInstantAppCookie", bundle).getByteArray(Form.TYPE_RESULT);
    }

    public final int zzij(String str) throws RemoteException {
        Bundle bundle = new Bundle();
        bundle.putString("packageName", str);
        return zzg("getUidForPackage", bundle).getInt(Form.TYPE_RESULT);
    }

    public final String zzik(String str) throws RemoteException {
        Bundle bundle = new Bundle();
        bundle.putString("packageName", str);
        return zzg("getApplicationLabel", bundle).getString(Form.TYPE_RESULT);
    }

    public final ComponentName zzil(String str) throws RemoteException {
        Bundle bundle = new Bundle();
        bundle.putString("shadowActivity", str);
        return (ComponentName) zzg("getCallingActivity", bundle).getParcelable(Form.TYPE_RESULT);
    }
}
