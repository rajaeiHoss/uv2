package com.google.android.gms.internal;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public final class zzdiv {
    private String zzkog = "https://www.google-analytics.com";

    private static String zzmm(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException unused) {
            String valueOf = String.valueOf(str);
            zzdal.e(valueOf.length() != 0 ? "Cannot encode the string: ".concat(valueOf) : new String("Cannot encode the string: "));
            return "";
        }
    }

    public final String zzb(zzdia zzdia) {
        String str = this.zzkog;
        String str2 = "";
        if (zzdia.zzbjp()) {
            str2 = zzdia.zzbjq();
        } else if (zzdia != null) {
            String trim = !zzdia.zzbjr().trim().equals(str2) ? zzdia.zzbjr().trim() : "-1";
            StringBuilder sb = new StringBuilder();
            sb.append(zzdia.zzbjn() != null ? zzdia.zzbjn() : "id");
            sb.append("=");
            sb.append(zzmm(zzdia.getContainerId()));
            sb.append("&pv=");
            sb.append(zzmm(trim));
            sb.append("&rv=5.0");
            if (zzdia.zzbjp()) {
                sb.append("&gtm_debug=x");
            }
            str2 = sb.toString();
        }
        StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 13 + String.valueOf(str2).length());
        sb2.append(str);
        sb2.append("/gtm/android?");
        sb2.append(str2);
        return sb2.toString();
    }
}
