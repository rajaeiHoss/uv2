package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@zzabh
public final class zzabb implements zzabf {
    private static final Object sLock = new Object();
    private static zzabf zzcqv;
    private static zzabf zzcqw;
    private final ExecutorService zzair;
    private final zzala zzarg;
    private final Context zzbky;
    private final Object zzcqx;
    private final WeakHashMap<Thread, Boolean> zzcqy;

    private zzabb(Context context) {
        this(context, zzala.zzse());
    }

    private zzabb(Context context, zzala zzala) {
        this.zzcqx = new Object();
        this.zzcqy = new WeakHashMap<>();
        this.zzair = Executors.newCachedThreadPool();
        this.zzbky = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.zzarg = zzala;
    }

    private final Uri.Builder zza(String str, String str2, String str3, int i) {
        boolean z;
        String str4;
        try {
            z = zzbih.zzdd(this.zzbky).zzaoe();
        } catch (Throwable th) {
            zzaky.zzb("Error fetching instant app info", th);
            z = false;
        }
        try {
            str4 = this.zzbky.getPackageName();
        } catch (Throwable unused) {
            zzaky.zzcz("Cannot obtain package name, proceeding.");
            str4 = "unknown";
        }
        Uri.Builder appendQueryParameter = new Uri.Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("is_aia", Boolean.toString(z)).appendQueryParameter("id", "gmob-apps-report-exception").appendQueryParameter("os", Build.VERSION.RELEASE).appendQueryParameter("api", String.valueOf(Build.VERSION.SDK_INT));
        String str5 = Build.MANUFACTURER;
        String str6 = Build.MODEL;
        if (!str6.startsWith(str5)) {
            StringBuilder sb = new StringBuilder(String.valueOf(str5).length() + 1 + String.valueOf(str6).length());
            sb.append(str5);
            sb.append(" ");
            sb.append(str6);
            str6 = sb.toString();
        }
        return appendQueryParameter.appendQueryParameter("device", str6).appendQueryParameter("js", this.zzarg.zzcu).appendQueryParameter("appid", str4).appendQueryParameter("exceptiontype", str).appendQueryParameter("stacktrace", str2).appendQueryParameter("eids", TextUtils.join(",", zzoi.zzjf())).appendQueryParameter("exceptionkey", str3).appendQueryParameter("cl", "190237664").appendQueryParameter("rc", "dev").appendQueryParameter("session_id", zzlc.zzil()).appendQueryParameter("sampling_rate", Integer.toString(1)).appendQueryParameter("pb_tm", String.valueOf(zzlc.zzio().zzd(zzoi.zzbvt)));
    }

    public static zzabf zzc(Context context, zzala zzala) {
        synchronized (sLock) {
            if (zzcqw == null) {
                if (((Boolean) zzlc.zzio().zzd(zzoi.zzbli)).booleanValue()) {
                    zzabb zzabb = new zzabb(context, zzala);
                    Thread thread = Looper.getMainLooper().getThread();
                    if (thread != null) {
                        synchronized (zzabb.zzcqx) {
                            zzabb.zzcqy.put(thread, true);
                        }
                        thread.setUncaughtExceptionHandler(new zzabd(zzabb, thread.getUncaughtExceptionHandler()));
                    }
                    Thread.setDefaultUncaughtExceptionHandler(new zzabc(zzabb, Thread.getDefaultUncaughtExceptionHandler()));
                    zzcqw = zzabb;
                } else {
                    zzcqw = new zzabg();
                }
            }
        }
        return zzcqw;
    }

    public static zzabf zzj(Context context) {
        synchronized (sLock) {
            if (zzcqv == null) {
                if (((Boolean) zzlc.zzio().zzd(zzoi.zzbli)).booleanValue()) {
                    zzcqv = new zzabb(context);
                } else {
                    zzcqv = new zzabg();
                }
            }
        }
        return zzcqv;
    }

    private final void zzo(List<String> list) {
        for (String zzabe : list) {
            this.zzair.submit(new zzabe(this, new zzakz(), zzabe));
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(java.lang.Thread r10, java.lang.Throwable r11) {
        /*
            r9 = this;
            r10 = 0
            r0 = 1
            if (r11 == 0) goto L_0x003f
            r1 = r11
            r2 = 0
            r3 = 0
        L_0x0007:
            if (r1 == 0) goto L_0x0039
            java.lang.StackTraceElement[] r4 = r1.getStackTrace()
            int r5 = r4.length
            r6 = 0
        L_0x000f:
            if (r6 >= r5) goto L_0x0034
            r7 = r4[r6]
            java.lang.String r8 = r7.getClassName()
            boolean r8 = com.google.android.gms.internal.zzako.zzcv(r8)
            if (r8 == 0) goto L_0x001e
            r2 = 1
        L_0x001e:
            java.lang.Class r8 = r9.getClass()
            java.lang.String r8 = r8.getName()
            java.lang.String r7 = r7.getClassName()
            boolean r7 = r8.equals(r7)
            if (r7 == 0) goto L_0x0031
            r3 = 1
        L_0x0031:
            int r6 = r6 + 1
            goto L_0x000f
        L_0x0034:
            java.lang.Throwable r1 = r1.getCause()
            goto L_0x0007
        L_0x0039:
            if (r2 == 0) goto L_0x003f
            if (r3 != 0) goto L_0x003f
            r1 = 1
            goto L_0x0040
        L_0x003f:
            r1 = 0
        L_0x0040:
            if (r1 == 0) goto L_0x0083
            java.lang.Throwable r1 = com.google.android.gms.internal.zzako.zzc(r11)
            if (r1 == 0) goto L_0x0083
            java.lang.Class r1 = r11.getClass()
            java.lang.String r1 = r1.getName()
            java.io.StringWriter r2 = new java.io.StringWriter
            r2.<init>()
            java.io.PrintWriter r3 = new java.io.PrintWriter
            r3.<init>(r2)
            com.google.android.gms.internal.zzdyq.zza((java.lang.Throwable) r11, (java.io.PrintWriter) r3)
            java.lang.String r11 = r2.toString()
            double r2 = java.lang.Math.random()
            r4 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x006c
            r10 = 1
        L_0x006c:
            if (r10 == 0) goto L_0x0083
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.lang.String r2 = ""
            android.net.Uri$Builder r11 = r9.zza(r1, r11, r2, r0)
            java.lang.String r11 = r11.toString()
            r10.add(r11)
            r9.zzo(r10)
        L_0x0083:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzabb.zza(java.lang.Thread, java.lang.Throwable):void");
    }

    public final void zza(Throwable th, String str) {
        if (zzako.zzc(th) != null) {
            String name = th.getClass().getName();
            StringWriter stringWriter = new StringWriter();
            zzdyq.zza(th, new PrintWriter(stringWriter));
            String stringWriter2 = stringWriter.toString();
            if (Math.random() < 1.0d) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(zza(name, stringWriter2, str, 1).toString());
                zzo(arrayList);
            }
        }
    }
}
