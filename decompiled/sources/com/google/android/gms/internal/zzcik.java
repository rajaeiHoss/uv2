package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzu;
import java.lang.reflect.InvocationTargetException;

public final class zzcik extends zzclh {
    private Boolean zzeba;

    zzcik(zzckj zzckj) {
        super(zzckj);
    }

    public static long zzazs() {
        return zzciz.zzjjq.get().longValue();
    }

    public static long zzazt() {
        return zzciz.zzjiq.get().longValue();
    }

    public static boolean zzazv() {
        return zzciz.zzjil.get().booleanValue();
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final long zza(String str, zzcja<Long> zzcja) {
        if (str != null) {
            String zzam = zzaym().zzam(str, zzcja.getKey());
            if (!TextUtils.isEmpty(zzam)) {
                try {
                    return zzcja.get(Long.valueOf(Long.valueOf(zzam).longValue())).longValue();
                } catch (NumberFormatException unused) {
                }
            }
        }
        return zzcja.get().longValue();
    }

    public final /* bridge */ /* synthetic */ void zzaxz() {
        super.zzaxz();
    }

    public final /* bridge */ /* synthetic */ void zzaya() {
        super.zzaya();
    }

    public final /* bridge */ /* synthetic */ zzcia zzayb() {
        return super.zzayb();
    }

    public final /* bridge */ /* synthetic */ zzcih zzayc() {
        return super.zzayc();
    }

    public final /* bridge */ /* synthetic */ zzclk zzayd() {
        return super.zzayd();
    }

    public final /* bridge */ /* synthetic */ zzcje zzaye() {
        return super.zzaye();
    }

    public final /* bridge */ /* synthetic */ zzcir zzayf() {
        return super.zzayf();
    }

    public final /* bridge */ /* synthetic */ zzcme zzayg() {
        return super.zzayg();
    }

    public final /* bridge */ /* synthetic */ zzcma zzayh() {
        return super.zzayh();
    }

    public final /* bridge */ /* synthetic */ zzcjf zzayi() {
        return super.zzayi();
    }

    public final /* bridge */ /* synthetic */ zzcil zzayj() {
        return super.zzayj();
    }

    public final /* bridge */ /* synthetic */ zzcjh zzayk() {
        return super.zzayk();
    }

    public final /* bridge */ /* synthetic */ zzcno zzayl() {
        return super.zzayl();
    }

    public final /* bridge */ /* synthetic */ zzckd zzaym() {
        return super.zzaym();
    }

    public final /* bridge */ /* synthetic */ zzcnd zzayn() {
        return super.zzayn();
    }

    public final /* bridge */ /* synthetic */ zzcke zzayo() {
        return super.zzayo();
    }

    public final /* bridge */ /* synthetic */ zzcjj zzayp() {
        return super.zzayp();
    }

    public final /* bridge */ /* synthetic */ zzcju zzayq() {
        return super.zzayq();
    }

    public final /* bridge */ /* synthetic */ zzcik zzayr() {
        return super.zzayr();
    }

    public final boolean zzazr() {
        Boolean zzjf = zzjf("firebase_analytics_collection_deactivated");
        return zzjf != null && zzjf.booleanValue();
    }

    public final String zzazu() {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke((Object) null, new Object[]{"debug.firebase.analytics.app", ""});
        } catch (ClassNotFoundException e) {
            zzayp().zzbau().zzj("Could not find SystemProperties class", e);
            return "";
        } catch (NoSuchMethodException e2) {
            zzayp().zzbau().zzj("Could not find SystemProperties.get() method", e2);
            return "";
        } catch (IllegalAccessException e3) {
            zzayp().zzbau().zzj("Could not access SystemProperties.get()", e3);
            return "";
        } catch (InvocationTargetException e4) {
            zzayp().zzbau().zzj("SystemProperties.get() threw an exception", e4);
            return "";
        }
    }

    public final int zzb(String str, zzcja<Integer> zzcja) {
        if (str != null) {
            String zzam = zzaym().zzam(str, zzcja.getKey());
            if (!TextUtils.isEmpty(zzam)) {
                try {
                    return zzcja.get(Integer.valueOf(Integer.valueOf(zzam).intValue())).intValue();
                } catch (NumberFormatException unused) {
                }
            }
        }
        return zzcja.get().intValue();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [com.google.android.gms.internal.zzcja<java.lang.Boolean>, com.google.android.gms.internal.zzcja] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzc(java.lang.String r3, com.google.android.gms.internal.zzcja<java.lang.Boolean> r4) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x000d
        L_0x0002:
            java.lang.Object r3 = r4.get()
        L_0x0006:
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            return r3
        L_0x000d:
            com.google.android.gms.internal.zzckd r0 = r2.zzaym()
            java.lang.String r1 = r4.getKey()
            java.lang.String r3 = r0.zzam(r3, r1)
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 == 0) goto L_0x0020
            goto L_0x0002
        L_0x0020:
            boolean r3 = java.lang.Boolean.parseBoolean(r3)
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            java.lang.Object r3 = r4.get(r3)
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcik.zzc(java.lang.String, com.google.android.gms.internal.zzcja):boolean");
    }

    public final int zzje(String str) {
        return zzb(str, zzciz.zzjjb);
    }

    /* access modifiers changed from: package-private */
    public final Boolean zzjf(String str) {
        zzbq.zzgv(str);
        try {
            if (getContext().getPackageManager() == null) {
                zzayp().zzbau().log("Failed to load metadata: PackageManager is null");
                return null;
            }
            ApplicationInfo applicationInfo = zzbih.zzdd(getContext()).getApplicationInfo(getContext().getPackageName(), 128);
            if (applicationInfo == null) {
                zzayp().zzbau().log("Failed to load metadata: ApplicationInfo is null");
                return null;
            } else if (applicationInfo.metaData == null) {
                zzayp().zzbau().log("Failed to load metadata: Metadata bundle is null");
                return null;
            } else if (!applicationInfo.metaData.containsKey(str)) {
                return null;
            } else {
                return Boolean.valueOf(applicationInfo.metaData.getBoolean(str));
            }
        } catch (PackageManager.NameNotFoundException e) {
            zzayp().zzbau().zzj("Failed to load metadata: Package name not found", e);
            return null;
        }
    }

    public final boolean zzjg(String str) {
        return "1".equals(zzaym().zzam(str, "gaia_collection_enabled"));
    }

    /* access modifiers changed from: package-private */
    public final boolean zzjh(String str) {
        return zzc(str, zzciz.zzjju);
    }

    public final /* bridge */ /* synthetic */ void zzwj() {
        super.zzwj();
    }

    public final /* bridge */ /* synthetic */ zze zzxx() {
        return super.zzxx();
    }

    public final boolean zzzu() {
        if (this.zzeba == null) {
            synchronized (this) {
                if (this.zzeba == null) {
                    ApplicationInfo applicationInfo = getContext().getApplicationInfo();
                    String zzany = zzu.zzany();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        this.zzeba = Boolean.valueOf(str != null && str.equals(zzany));
                    }
                    if (this.zzeba == null) {
                        this.zzeba = Boolean.TRUE;
                        zzayp().zzbau().log("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzeba.booleanValue();
    }
}
