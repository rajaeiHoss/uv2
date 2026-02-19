package com.google.android.gms.tagmanager;

import android.content.Context;

public final class zzbb implements zzby {
    private static final Object zzkmq = new Object();
    private static zzbb zzkpe;
    private zzek zzkns;
    private zzbz zzkpf;

    private zzbb(Context context) {
        this(zzca.zzen(context), new zzfm());
    }

    private zzbb(zzbz zzbz, zzek zzek) {
        this.zzkpf = zzbz;
        this.zzkns = zzek;
    }

    public static zzby zzeh(Context context) {
        zzbb zzbb;
        synchronized (zzkmq) {
            if (zzkpe == null) {
                zzkpe = new zzbb(context);
            }
            zzbb = zzkpe;
        }
        return zzbb;
    }

    public final boolean zzlr(String str) {
        if (!this.zzkns.zzaas()) {
            zzdj.zzcz("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
            return false;
        }
        this.zzkpf.zzlw(str);
        return true;
    }
}
