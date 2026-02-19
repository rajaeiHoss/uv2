package com.google.android.gms.internal;

import android.content.Context;
import java.io.File;
import java.util.regex.Pattern;

@zzabh
public final class zzajl extends zzaj {
    private final Context mContext;

    private zzajl(Context context, zzar zzar) {
        super(zzar);
        this.mContext = context;
    }

    public static zzv zzax(Context context) {
        zzv zzv = new zzv(new zzam(new File(context.getCacheDir(), "admob_volley")), new zzajl(context, new zzas()));
        zzv.start();
        return zzv;
    }

    public final zzp zzc(zzr<?> zzr) throws zzae {
        if (zzr.zzg() && zzr.getMethod() == 0) {
            if (Pattern.matches((String) zzlc.zzio().zzd(zzoi.zzbum), zzr.getUrl())) {
                zzlc.zzij();
                if (zzako.zzbb(this.mContext)) {
                    zzp zzc = new zzte(this.mContext).zzc(zzr);
                    if (zzc != null) {
                        String valueOf = String.valueOf(zzr.getUrl());
                        zzahw.v(valueOf.length() != 0 ? "Got gmscore asset response: ".concat(valueOf) : new String("Got gmscore asset response: "));
                        return zzc;
                    }
                    String valueOf2 = String.valueOf(zzr.getUrl());
                    zzahw.v(valueOf2.length() != 0 ? "Failed to get gmscore asset response: ".concat(valueOf2) : new String("Failed to get gmscore asset response: "));
                }
            }
        }
        return super.zzc(zzr);
    }
}
