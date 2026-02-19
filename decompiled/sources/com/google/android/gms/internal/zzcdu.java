package com.google.android.gms.internal;

import android.app.Activity;
import android.content.ComponentName;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.instantapps.ActivityCompat;

public final class zzcdu implements ActivityCompat {
    private final Activity zzakg;

    public zzcdu(Activity activity) {
        this.zzakg = activity;
    }

    public final ComponentName getCallingActivity() {
        zzcev zzdw;
        ComponentName callingActivity = this.zzakg.getCallingActivity();
        if (!(callingActivity == null || callingActivity.getPackageName() == null || !callingActivity.getPackageName().equals("com.google.android.instantapps.supervisor") || (zzdw = zzcev.zzdw(this.zzakg)) == null)) {
            try {
                ComponentName zzil = zzdw.zzil(callingActivity.getClassName());
                if (zzil != null) {
                    return zzil;
                }
            } catch (RemoteException e) {
                Log.e("IAActivityCompat", "Error getting calling activity", e);
            }
        }
        return callingActivity;
    }

    public final String getCallingPackage() {
        ComponentName callingActivity = getCallingActivity();
        if (callingActivity != null) {
            return callingActivity.getPackageName();
        }
        return null;
    }
}
