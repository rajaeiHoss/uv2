package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.mediarouter.media.MediaRouteProviderProtocol;

public final class zzatu extends zzari {
    private boolean zzdum;
    private String zzdwo;
    private String zzdwp;
    private int zzebd;
    protected int zzedg;
    protected boolean zzeez;
    private boolean zzefa;

    public zzatu(zzark zzark) {
        super(zzark);
    }

    public final boolean zzabm() {
        zzyk();
        return false;
    }

    public final boolean zzabn() {
        zzyk();
        return this.zzefa;
    }

    public final boolean zzabo() {
        zzyk();
        return this.zzdum;
    }

    /* access modifiers changed from: protected */
    public final void zzwk() {
        ApplicationInfo applicationInfo;
        int i;
        zzasx zzasx;
        Context context = getContext();
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 129);
        } catch (PackageManager.NameNotFoundException e) {
            zzd("PackageManager doesn't know about the app package", e);
            applicationInfo = null;
        }
        if (applicationInfo == null) {
            zzed("Couldn't get ApplicationInfo to load global config");
            return;
        }
        Bundle bundle = applicationInfo.metaData;
        if (bundle != null && (i = bundle.getInt("com.google.android.gms.analytics.globalConfigResource")) > 0 && (zzasx = (zzasx) new zzasv(zzxw()).zzav(i)) != null) {
            zzea("Loading global XML config values");
            boolean z = false;
            if (zzasx.zzdwo != null) {
                String str = zzasx.zzdwo;
                this.zzdwo = str;
                zzb("XML config - app name", str);
            }
            if (zzasx.zzdwp != null) {
                String str2 = zzasx.zzdwp;
                this.zzdwp = str2;
                zzb("XML config - app version", str2);
            }
            if (zzasx.zzedf != null) {
                String lowerCase = zzasx.zzedf.toLowerCase();
                int i2 = "verbose".equals(lowerCase) ? 0 : "info".equals(lowerCase) ? 1 : "warning".equals(lowerCase) ? 2 : MediaRouteProviderProtocol.SERVICE_DATA_ERROR.equals(lowerCase) ? 3 : -1;
                if (i2 >= 0) {
                    this.zzebd = i2;
                    zza("XML config - log level", Integer.valueOf(i2));
                }
            }
            if (zzasx.zzedg >= 0) {
                int i3 = zzasx.zzedg;
                this.zzedg = i3;
                this.zzeez = true;
                zzb("XML config - dispatch period (sec)", Integer.valueOf(i3));
            }
            if (zzasx.zzedh != -1) {
                if (zzasx.zzedh == 1) {
                    z = true;
                }
                this.zzdum = z;
                this.zzefa = true;
                zzb("XML config - dry run", Boolean.valueOf(z));
            }
        }
    }

    public final String zzwn() {
        zzyk();
        return this.zzdwo;
    }

    public final String zzwo() {
        zzyk();
        return this.zzdwp;
    }
}
