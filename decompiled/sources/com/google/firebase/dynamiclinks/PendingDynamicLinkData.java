package com.google.firebase.dynamiclinks;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.internal.zzepi;

public class PendingDynamicLinkData {
    private final zzepi zznsa;

    public PendingDynamicLinkData(zzepi zzepi) {
        if (zzepi == null) {
            this.zznsa = null;
            return;
        }
        if (zzepi.getClickTimestamp() == 0) {
            zzepi.zzcb(zzi.zzanq().currentTimeMillis());
        }
        this.zznsa = zzepi;
    }

    protected PendingDynamicLinkData(String str, int i, long j, Uri uri) {
        this.zznsa = new zzepi((String) null, str, i, j, (Bundle) null, uri);
    }

    private final Uri zzcdt() {
        zzepi zzepi = this.zznsa;
        if (zzepi == null) {
            return null;
        }
        return zzepi.zzcdt();
    }

    public long getClickTimestamp() {
        zzepi zzepi = this.zznsa;
        if (zzepi == null) {
            return 0;
        }
        return zzepi.getClickTimestamp();
    }

    public Uri getLink() {
        String zzcdu;
        zzepi zzepi = this.zznsa;
        if (zzepi == null || (zzcdu = zzepi.zzcdu()) == null) {
            return null;
        }
        return Uri.parse(zzcdu);
    }

    public int getMinimumAppVersion() {
        zzepi zzepi = this.zznsa;
        if (zzepi == null) {
            return 0;
        }
        return zzepi.zzcdv();
    }

    public Intent getUpdateAppIntent(Context context) {
        if (getMinimumAppVersion() == 0) {
            return null;
        }
        try {
            if (context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 0).versionCode < getMinimumAppVersion() && zzcdt() != null) {
                return new Intent("android.intent.action.VIEW").setData(zzcdt()).setPackage("com.android.vending");
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return null;
    }

    public final Bundle zzcds() {
        zzepi zzepi = this.zznsa;
        return zzepi == null ? new Bundle() : zzepi.zzcdw();
    }
}
