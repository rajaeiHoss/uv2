package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzu;
import java.util.HashSet;
import java.util.Set;

public final class zzasl {
    private final zzark zzdtw;
    private volatile Boolean zzeba;
    private String zzebb;
    private Set<Integer> zzebc;

    protected zzasl(zzark zzark) {
        zzbq.checkNotNull(zzark);
        this.zzdtw = zzark;
    }

    public static int zzaaa() {
        return zzast.zzeca.get().intValue();
    }

    public static String zzaab() {
        return zzast.zzecc.get();
    }

    public static String zzaac() {
        return zzast.zzecb.get();
    }

    public static String zzaad() {
        return zzast.zzecd.get();
    }

    public static long zzaaf() {
        return zzast.zzecr.get().longValue();
    }

    public static boolean zzzv() {
        return zzast.zzebm.get().booleanValue();
    }

    public static int zzzw() {
        return zzast.zzecj.get().intValue();
    }

    public static long zzzx() {
        return zzast.zzebu.get().longValue();
    }

    public static long zzzy() {
        return zzast.zzebx.get().longValue();
    }

    public static int zzzz() {
        return zzast.zzebz.get().intValue();
    }

    public final Set<Integer> zzaae() {
        String str;
        String str2 = zzast.zzecm.get();
        if (this.zzebc == null || (str = this.zzebb) == null || !str.equals(str2)) {
            String[] split = TextUtils.split(str2, ",");
            HashSet hashSet = new HashSet();
            for (String parseInt : split) {
                try {
                    hashSet.add(Integer.valueOf(Integer.parseInt(parseInt)));
                } catch (NumberFormatException unused) {
                }
            }
            this.zzebb = str2;
            this.zzebc = hashSet;
        }
        return this.zzebc;
    }

    public final boolean zzzu() {
        if (this.zzeba == null) {
            synchronized (this) {
                if (this.zzeba == null) {
                    ApplicationInfo applicationInfo = this.zzdtw.getContext().getApplicationInfo();
                    String zzany = zzu.zzany();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        this.zzeba = Boolean.valueOf(str != null && str.equals(zzany));
                    }
                    if ((this.zzeba == null || !this.zzeba.booleanValue()) && "com.google.android.gms.analytics".equals(zzany)) {
                        this.zzeba = Boolean.TRUE;
                    }
                    if (this.zzeba == null) {
                        this.zzeba = Boolean.TRUE;
                        this.zzdtw.zzxy().zzee("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzeba.booleanValue();
    }
}
