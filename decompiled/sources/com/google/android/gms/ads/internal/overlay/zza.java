package com.google.android.gms.ads.internal.overlay;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzoi;

@zzabh
public final class zza {
    private static boolean zza(Context context, Intent intent, zzt zzt) {
        try {
            String valueOf = String.valueOf(intent.toURI());
            zzahw.v(valueOf.length() != 0 ? "Launching an intent: ".concat(valueOf) : new String("Launching an intent: "));
            zzbt.zzel();
            zzaij.zza(context, intent);
            if (zzt == null) {
                return true;
            }
            zzt.zzbr();
            return true;
        } catch (ActivityNotFoundException e) {
            zzahw.zzcz(e.getMessage());
            return false;
        }
    }

    public static boolean zza(Context context, zzc zzc, zzt zzt) {
        String str;
        int i = 0;
        if (zzc == null) {
            str = "No intent data for launcher overlay.";
        } else {
            zzoi.initialize(context);
            if (zzc.intent != null) {
                return zza(context, zzc.intent, zzt);
            }
            Intent intent = new Intent();
            if (TextUtils.isEmpty(zzc.url)) {
                str = "Open GMSG did not contain a URL.";
            } else {
                if (!TextUtils.isEmpty(zzc.mimeType)) {
                    intent.setDataAndType(Uri.parse(zzc.url), zzc.mimeType);
                } else {
                    intent.setData(Uri.parse(zzc.url));
                }
                intent.setAction("android.intent.action.VIEW");
                if (!TextUtils.isEmpty(zzc.packageName)) {
                    intent.setPackage(zzc.packageName);
                }
                if (!TextUtils.isEmpty(zzc.zzcmh)) {
                    String[] split = zzc.zzcmh.split("/", 2);
                    if (split.length < 2) {
                        String valueOf = String.valueOf(zzc.zzcmh);
                        zzahw.zzcz(valueOf.length() != 0 ? "Could not parse component name from open GMSG: ".concat(valueOf) : new String("Could not parse component name from open GMSG: "));
                        return false;
                    }
                    intent.setClassName(split[0], split[1]);
                }
                String str2 = zzc.zzcmi;
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        i = Integer.parseInt(str2);
                    } catch (NumberFormatException unused) {
                        zzahw.zzcz("Could not parse intent flags.");
                    }
                    intent.addFlags(i);
                }
                if (((Boolean) zzlc.zzio().zzd(zzoi.zzbuq)).booleanValue()) {
                    intent.addFlags(DriveFile.MODE_READ_ONLY);
                    intent.putExtra("android.support.customtabs.extra.user_opt_out", true);
                } else {
                    if (((Boolean) zzlc.zzio().zzd(zzoi.zzbup)).booleanValue()) {
                        zzbt.zzel();
                        zzaij.zzb(context, intent);
                    }
                }
                return zza(context, intent, zzt);
            }
        }
        zzahw.zzcz(str);
        return false;
    }
}
