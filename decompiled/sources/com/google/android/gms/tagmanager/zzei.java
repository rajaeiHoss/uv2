package com.google.android.gms.tagmanager;

import android.net.Uri;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

class zzei {
    private static zzei zzkrl;
    private volatile String zzknc = null;
    private volatile zza zzkrm = zza.NONE;
    private volatile String zzkrn = null;
    private volatile String zzkro = null;

    enum zza {
        NONE,
        CONTAINER,
        CONTAINER_DEBUG
    }

    zzei() {
    }

    static zzei zzbhh() {
        zzei zzei;
        synchronized (zzei.class) {
            if (zzkrl == null) {
                zzkrl = new zzei();
            }
            zzei = zzkrl;
        }
        return zzei;
    }

    private static String zzma(String str) {
        return str.split("&")[0].split("=")[1];
    }

    /* access modifiers changed from: package-private */
    public final String getContainerId() {
        return this.zzknc;
    }

    /* access modifiers changed from: package-private */
    public final zza zzbhi() {
        return this.zzkrm;
    }

    /* access modifiers changed from: package-private */
    public final String zzbhj() {
        return this.zzkrn;
    }

    /* access modifiers changed from: package-private */
    public final synchronized boolean zzq(Uri uri) {
        try {
            String decode = URLDecoder.decode(uri.toString(), "UTF-8");
            if (decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_auth=\\S+&gtm_preview=\\d+(&gtm_debug=x)?$")) {
                String valueOf = String.valueOf(decode);
                zzdj.v(valueOf.length() != 0 ? "Container preview url: ".concat(valueOf) : new String("Container preview url: "));
                this.zzkrm = decode.matches(".*?&gtm_debug=x$") ? zza.CONTAINER_DEBUG : zza.CONTAINER;
                this.zzkro = uri.getQuery().replace("&gtm_debug=x", "");
                if (this.zzkrm == zza.CONTAINER || this.zzkrm == zza.CONTAINER_DEBUG) {
                    String valueOf2 = String.valueOf(this.zzkro);
                    this.zzkrn = valueOf2.length() != 0 ? "/r?".concat(valueOf2) : new String("/r?");
                }
                this.zzknc = zzma(this.zzkro);
                return true;
            } else if (!decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_preview=$")) {
                String valueOf3 = String.valueOf(decode);
                zzdj.zzcz(valueOf3.length() != 0 ? "Invalid preview uri: ".concat(valueOf3) : new String("Invalid preview uri: "));
                return false;
            } else if (!zzma(uri.getQuery()).equals(this.zzknc)) {
                return false;
            } else {
                String valueOf4 = String.valueOf(this.zzknc);
                zzdj.v(valueOf4.length() != 0 ? "Exit preview mode for container: ".concat(valueOf4) : new String("Exit preview mode for container: "));
                this.zzkrm = zza.NONE;
                this.zzkrn = null;
                return true;
            }
        } catch (UnsupportedEncodingException unused) {
            return false;
        }
    }
}
