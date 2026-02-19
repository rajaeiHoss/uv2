package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.Intent;

final class zzdas implements DialogInterface.OnClickListener {
    private /* synthetic */ zzdar zzkxi;

    zzdas(zzdar zzdar) {
        this.zzkxi = zzdar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        String packageName = this.zzkxi.zzevc.getPackageName();
        Intent launchIntentForPackage = this.zzkxi.zzevc.getPackageManager().getLaunchIntentForPackage(packageName);
        if (launchIntentForPackage != null) {
            String valueOf = String.valueOf(packageName);
            zzdal.zzcy(valueOf.length() != 0 ? "Invoke the launch activity for package name: ".concat(valueOf) : new String("Invoke the launch activity for package name: "));
            this.zzkxi.zzevc.startActivity(launchIntentForPackage);
            return;
        }
        String valueOf2 = String.valueOf(packageName);
        zzdal.zzcz(valueOf2.length() != 0 ? "No launch activity found for package name: ".concat(valueOf2) : new String("No launch activity found for package name: "));
    }
}
