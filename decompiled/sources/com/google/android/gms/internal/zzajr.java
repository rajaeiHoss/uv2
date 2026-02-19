package com.google.android.gms.internal;

import android.content.Context;
import java.io.File;
import java.util.Map;

@zzabh
public final class zzajr {
    private static zzv zzdha;
    private static final Object zzdhb = new Object();
    @Deprecated
    private static zzajx<Void> zzdhc = new zzajs();

    public zzajr(Context context) {
        zzay(context);
    }

    private static zzv zzay(Context context) {
        zzv zzv;
        zzv zzv2;
        synchronized (zzdhb) {
            if (zzdha == null) {
                Context applicationContext = context.getApplicationContext();
                zzoi.initialize(applicationContext);
                if (((Boolean) zzlc.zzio().zzd(zzoi.zzbul)).booleanValue()) {
                    zzv2 = zzajl.zzax(applicationContext);
                } else {
                    zzv2 = new zzv(new zzam(new File(applicationContext.getCacheDir(), "volley")), new zzaj((zzai) new zzas()));
                    zzv2.start();
                }
                zzdha = zzv2;
            }
            zzv = zzdha;
        }
        return zzv;
    }

    public final zzalt<String> zza(int i, String str, Map<String, String> map, byte[] bArr) {
        String str2 = str;
        zzajy zzajy = new zzajy((zzajs) null);
        zzajv zzajv = new zzajv(this, str2, zzajy);
        zzaks zzaks = new zzaks((String) null);
        zzajw zzajw = new zzajw(this, i, str, zzajy, zzajv, bArr, map, zzaks);
        if (zzaks.isEnabled()) {
            try {
                zzaks.zza(str2, "GET", zzajw.getHeaders(), zzajw.zzf());
            } catch (zza e) {
                zzahw.zzcz(e.getMessage());
            }
        }
        zzdha.zze(zzajw);
        return zzajy;
    }

    @Deprecated
    public final <T> zzalt<T> zza(String str, zzajx<T> zzajx) {
        zzamd zzamd = new zzamd();
        zzdha.zze(new zzajz(str, zzamd));
        return zzali.zza(zzali.zza(zzamd, new zzaju(this, zzajx), zzaly.zzdju), Throwable.class, new zzajt(this, zzajx), zzaly.zzdju);
    }

    public final zzalt<String> zzb(String str, Map<String, String> map) {
        return zza(0, str, map, (byte[]) null);
    }
}
