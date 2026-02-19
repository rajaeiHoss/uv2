package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public final class zzdia {
    private final String zzknc;
    private final String zzksd;
    private final String zzkvo;
    private final String zzlav;
    private final boolean zzlaw;
    private final String zzlax;

    public zzdia(String str, String str2, String str3, boolean z, String str4) {
        this(str, str2, str3, z, str4, "");
    }

    private zzdia(String str, String str2, String str3, boolean z, String str4, String str5) {
        zzbq.checkNotNull(str);
        zzbq.checkNotNull(str5);
        this.zzknc = str;
        this.zzkvo = str2;
        this.zzlav = str3;
        this.zzlaw = z;
        this.zzlax = str4;
        this.zzksd = str5;
    }

    public final String getContainerId() {
        return this.zzknc;
    }

    public final String zzbjm() {
        return this.zzkvo;
    }

    public final String zzbjn() {
        return this.zzlav;
    }

    public final String zzbjo() {
        String str = this.zzlav;
        if (str == null) {
            return this.zzknc;
        }
        String str2 = this.zzknc;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(str2).length());
        sb.append(str);
        sb.append("_");
        sb.append(str2);
        return sb.toString();
    }

    public final boolean zzbjp() {
        return this.zzlaw;
    }

    public final String zzbjq() {
        return this.zzlax;
    }

    public final String zzbjr() {
        return this.zzksd;
    }
}
