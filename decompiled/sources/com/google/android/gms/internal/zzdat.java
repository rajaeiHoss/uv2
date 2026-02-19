package com.google.android.gms.internal;

import android.net.Uri;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class zzdat {
    private static zzdat zzkxj;
    private volatile String zzknc = null;
    private volatile String zzkro = null;
    private volatile int zzkxk = zza.zzkxl;

    /* 'enum' modifier removed */
    static final class zza {
        public static final int zzkxl = 1;
        public static final int zzkxm = 2;
        private static final /* synthetic */ int[] zzkxn = {1, 2};
    }

    zzdat() {
    }

    public static zzdat zzbja() {
        zzdat zzdat;
        synchronized (zzdat.class) {
            if (zzkxj == null) {
                zzkxj = new zzdat();
            }
            zzdat = zzkxj;
        }
        return zzdat;
    }

    public final String getContainerId() {
        return this.zzknc;
    }

    public final boolean isPreview() {
        return this.zzkxk == zza.zzkxm;
    }

    public final String zzbjb() {
        return this.zzkro;
    }

    public final synchronized boolean zzc(String str, Uri uri) {
        try {
            String decode = URLDecoder.decode(uri.toString(), "UTF-8");
            if (!decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\S+")) {
                String valueOf = String.valueOf(decode);
                zzdal.zzcz(valueOf.length() != 0 ? "Bad preview url: ".concat(valueOf) : new String("Bad preview url: "));
                return false;
            }
            String queryParameter = uri.getQueryParameter("id");
            String queryParameter2 = uri.getQueryParameter("gtm_auth");
            String queryParameter3 = uri.getQueryParameter("gtm_preview");
            if (!str.equals(queryParameter)) {
                zzdal.zzcz("Preview fails (container doesn't match the container specified by the asset)");
                return false;
            }
            if (queryParameter != null) {
                if (queryParameter.length() > 0) {
                    if (queryParameter3 == null || queryParameter3.length() != 0) {
                        if (queryParameter3 != null) {
                            if (queryParameter3.length() > 0 && queryParameter2 != null && queryParameter2.length() > 0) {
                                this.zzkxk = zza.zzkxm;
                                this.zzkro = uri.getQuery();
                                this.zzknc = queryParameter;
                            }
                        }
                        String valueOf2 = String.valueOf(decode);
                        zzdal.zzcz(valueOf2.length() != 0 ? "Bad preview url: ".concat(valueOf2) : new String("Bad preview url: "));
                        return false;
                    }
                    if (queryParameter.equals(this.zzknc)) {
                        if (this.zzkxk != zza.zzkxl) {
                            String valueOf3 = String.valueOf(this.zzknc);
                            zzdal.v(valueOf3.length() != 0 ? "Exit preview mode for container: ".concat(valueOf3) : new String("Exit preview mode for container: "));
                            this.zzkxk = zza.zzkxl;
                            this.zzknc = null;
                            this.zzkro = null;
                        }
                        return true;
                    }
                    zzdal.zzcz("Error in exiting preview mode. The container is not in preview.");
                    return false;
                }
            }
            String valueOf4 = String.valueOf(decode);
            zzdal.zzcz(valueOf4.length() != 0 ? "Bad preview url: ".concat(valueOf4) : new String("Bad preview url: "));
            return false;
        } catch (UnsupportedEncodingException e) {
            String valueOf5 = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf5).length() + 32);
            sb.append("Error decoding the preview url: ");
            sb.append(valueOf5);
            zzdal.zzcz(sb.toString());
            return false;
        }
    }

    public final boolean zzmq(String str) {
        return isPreview() && this.zzknc.equals(str);
    }
}
