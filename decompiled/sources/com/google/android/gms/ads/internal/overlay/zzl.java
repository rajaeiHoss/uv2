package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzaij;

@zzabh
public final class zzl {
    public static void zza(Context context, AdOverlayInfoParcel adOverlayInfoParcel, boolean z) {
        if (adOverlayInfoParcel.zzcns == 4 && adOverlayInfoParcel.zzcnl == null) {
            if (adOverlayInfoParcel.zzcnk != null) {
                adOverlayInfoParcel.zzcnk.onAdClicked();
            }
            zzbt.zzei();
            zza.zza(context, adOverlayInfoParcel.zzcnj, adOverlayInfoParcel.zzcnr);
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(context, AdActivity.CLASS_NAME);
        intent.putExtra("com.google.android.gms.ads.internal.overlay.useClientJar", adOverlayInfoParcel.zzatz.zzdjb);
        intent.putExtra("shouldCallOnOverlayOpened", z);
        AdOverlayInfoParcel.zza(intent, adOverlayInfoParcel);
        if (!zzs.zzanx()) {
            intent.addFlags(524288);
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(DriveFile.MODE_READ_ONLY);
        }
        zzbt.zzel();
        zzaij.zza(context, intent);
    }
}
