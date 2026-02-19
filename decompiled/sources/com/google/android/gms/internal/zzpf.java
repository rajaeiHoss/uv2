package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import androidx.browser.customtabs.CustomTabsCallback;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import androidx.browser.customtabs.CustomTabsSession;
import java.util.List;

@zzabh
public final class zzpf implements zzfxf {
    private CustomTabsSession zzbxb;
    private CustomTabsClient zzbxc;
    private CustomTabsServiceConnection zzbxd;
    private zzpg zzbxe;

    public static boolean zzh(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        if (!(queryIntentActivities == null || resolveActivity == null)) {
            for (int i = 0; i < queryIntentActivities.size(); i++) {
                if (resolveActivity.activityInfo.name.equals(queryIntentActivities.get(i).activityInfo.name)) {
                    return resolveActivity.activityInfo.packageName.equals(zzfxd.zzfk(context));
                }
            }
        }
        return false;
    }

    public final boolean mayLaunchUrl(Uri uri, Bundle bundle, List<Bundle> list) {
        CustomTabsClient customTabsClient = this.zzbxc;
        if (customTabsClient == null) {
            return false;
        }
        if (customTabsClient == null) {
            this.zzbxb = null;
        } else if (this.zzbxb == null) {
            this.zzbxb = customTabsClient.newSession((CustomTabsCallback) null);
        }
        CustomTabsSession customTabsSession = this.zzbxb;
        if (customTabsSession == null) {
            return false;
        }
        return customTabsSession.mayLaunchUrl(uri, (Bundle) null, (List) null);
    }

    public final void zza(CustomTabsClient customTabsClient) {
        this.zzbxc = customTabsClient;
        customTabsClient.warmup(0);
        zzpg zzpg = this.zzbxe;
        if (zzpg != null) {
            zzpg.zzju();
        }
    }

    public final void zza(zzpg zzpg) {
        this.zzbxe = zzpg;
    }

    public final void zzc(Activity activity) {
        CustomTabsServiceConnection customTabsServiceConnection = this.zzbxd;
        if (customTabsServiceConnection != null) {
            activity.unbindService(customTabsServiceConnection);
            this.zzbxc = null;
            this.zzbxb = null;
            this.zzbxd = null;
        }
    }

    public final void zzd(Activity activity) {
        String zzfk;
        if (this.zzbxc == null && (zzfk = zzfxd.zzfk(activity)) != null) {
            zzfxe zzfxe = new zzfxe(this);
            this.zzbxd = zzfxe;
            CustomTabsClient.bindCustomTabsService(activity, zzfk, zzfxe);
        }
    }

    public final void zzjt() {
        this.zzbxc = null;
        this.zzbxb = null;
        zzpg zzpg = this.zzbxe;
        if (zzpg != null) {
            zzpg.zzjv();
        }
    }
}
