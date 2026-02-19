package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.internal.zzbt;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

@zzabh
public final class zzadn extends zzaco {
    private static final Object sLock = new Object();
    private static zzadn zzcwb;
    private final Context mContext;
    private final zzadm zzcwc;
    private final ScheduledExecutorService zzcwd = Executors.newSingleThreadScheduledExecutor();

    private zzadn(Context context, zzadm zzadm) {
        this.mContext = context;
        this.zzcwc = zzadm;
    }

    private static zzacj zza(Context context, zzadm zzadm, zzacf zzacf, ScheduledExecutorService scheduledExecutorService) {
        String string;
        Context context2 = context;
        zzadm zzadm2 = zzadm;
        zzacf zzacf2 = zzacf;
        ScheduledExecutorService scheduledExecutorService2 = scheduledExecutorService;
        zzahw.zzby("Starting ad request from service using: google.afma.request.getAdDictionary");
        zzov zzov = new zzov(((Boolean) zzlc.zzio().zzd(zzoi.zzbne)).booleanValue(), "load_ad", zzacf2.zzaud.zzbia);
        if (zzacf2.versionCode > 10 && zzacf2.zzcsl != -1) {
            zzov.zza(zzov.zzd(zzacf2.zzcsl), "cts");
        }
        zzot zzjo = zzov.zzjo();
        zzalt<Bundle> zza = zzali.zza(zzadm2.zzcvy.zzi(context2), ((Long) zzlc.zzio().zzd(zzoi.zzbtv)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService2);
        zzalt<zzaek> zza2 = zzali.zza(zzadm2.zzcvx.zzp(context2), ((Long) zzlc.zzio().zzd(zzoi.zzbra)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService2);
        zzalt<String> zzbz = zzadm2.zzcvs.zzbz(zzacf2.zzcrw.packageName);
        zzalt<String> zza3 = zzadm2.zzcvz.zza(zzacf2.zzcrx, zzacf2.zzcrw);
        Future<zzaea> zzo = zzbt.zzew().zzo(context2);
        zzalt<Location> zzh = zzali.zzh(null);
        Bundle bundle = zzacf2.zzcrv.extras;
        boolean z = (bundle == null || bundle.getString("_ad") == null) ? false : true;
        if (zzacf2.zzcsr && !z) {
            zzh = zzadm2.zzcvv.zza(zzacf2.applicationInfo);
        }
        zzov zzov2 = zzov;
        zzot zzot = zzjo;
        zzalt<Location> zza4 = zzali.zza(zzh, ((Long) zzlc.zzio().zzd(zzoi.zzbte)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService2);
        zzalt<AdvertisingIdClient.Info> zzh2 = zzali.zzh(null);
        if (((Boolean) zzlc.zzio().zzd(zzoi.zzbpd)).booleanValue()) {
            zzh2 = zzali.zza(zzadm2.zzcvz.zzac(context2), ((Long) zzlc.zzio().zzd(zzoi.zzbpe)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService2);
        }
        Bundle bundle2 = (zzacf2.versionCode < 4 || zzacf2.zzcsc == null) ? null : zzacf2.zzcsc;
        ((Boolean) zzlc.zzio().zzd(zzoi.zzbnu)).booleanValue();
        zzbt.zzel();
        if (zzaij.zzd(context2, context.getPackageName(), "android.permission.ACCESS_NETWORK_STATE") && ((ConnectivityManager) context2.getSystemService("connectivity")).getActiveNetworkInfo() == null) {
            zzahw.zzby("Device is offline.");
        }
        String uuid = zzacf2.versionCode >= 7 ? zzacf2.zzcsi : UUID.randomUUID().toString();
        zzov zzov3 = zzov2;
        new zzadt(context2, uuid, zzacf2.applicationInfo.packageName);
        if (zzacf2.zzcrv.extras != null && (string = zzacf2.zzcrv.extras.getString("_ad")) != null) {
            return zzads.zza(context2, zzacf2, string);
        }
        List<String> zzf = zzadm2.zzcvt.zzf(zzacf2.zzcsj);
        String str = uuid;
        Bundle bundle3 = (Bundle) zzali.zza(zza, null, ((Long) zzlc.zzio().zzd(zzoi.zzbtv)).longValue(), TimeUnit.MILLISECONDS);
        zzaek zzaek = (zzaek) zzali.zza(zza2, null);
        Location location = (Location) zzali.zza(zza4, null);
        AdvertisingIdClient.Info info = (AdvertisingIdClient.Info) zzali.zza(zzh2, null);
        String str2 = (String) zzali.zza(zza3, null);
        String str3 = (String) zzali.zza(zzbz, null);
        zzaea zzaea = (zzaea) zzali.zza(zzo, null);
        if (zzaea == null) {
            zzahw.zzcz("Error fetching device info. This is not recoverable.");
            return new zzacj(0);
        }
        zzadl zzadl = new zzadl();
        zzadl.zzcvm = zzacf2;
        zzadl.zzcvn = zzaea;
        zzadl.zzcvj = zzaek;
        zzadl.zzbhd = location;
        zzadl.zzcvi = bundle3;
        zzadl.zzcrx = str2;
        zzadl.zzcvl = info;
        if (zzf == null) {
            zzadl.zzcsj.clear();
        }
        zzadl.zzcsj = zzf;
        zzadl.zzcsc = bundle2;
        zzadl.zzcvk = str3;
        zzadl.zzcvo = zzadm2.zzcvr.zze(context2);
        zzadl.zzcvp = zzadm2.zzcvp;
        JSONObject zza5 = zzads.zza(context2, zzadl);
        if (zza5 == null) {
            return new zzacj(0);
        }
        if (zzacf2.versionCode < 7) {
            try {
                zza5.put("request_id", str);
            } catch (JSONException unused) {
            }
        }
        zza5.toString();
        zzot zzot2 = zzot;
        zzov zzov4 = zzov3;
        zzov4.zza(zzot2, "arc");
        zzov4.zzjo();
        ScheduledExecutorService scheduledExecutorService3 = scheduledExecutorService;
        zzalt<zzadz> zza6 = zzali.zza(zzali.zza(zzadm2.zzcwa.zzoe().zzf(zza5), zzado.zzaoy, (Executor) scheduledExecutorService3), 10, TimeUnit.SECONDS, scheduledExecutorService3);
        zzalt<Void> zzon = zzadm2.zzcvu.zzon();
        if (zzon != null) {
            zzalg.zza(zzon, "AdRequestServiceImpl.loadAd.flags");
        }
        zzadz zzadz = (zzadz) zzali.zza(zza6, null);
        if (zzadz == null) {
            return new zzacj(0);
        }
        if (zzadz.getErrorCode() != -2) {
            return new zzacj(zzadz.getErrorCode());
        }
        zzov4.zzjr();
        zzacj zza7 = !TextUtils.isEmpty(zzadz.zzok()) ? zzads.zza(context2, zzacf2, zzadz.zzok()) : null;
        if (zza7 == null && !TextUtils.isEmpty(zzadz.getUrl())) {
            zza7 = zza(zzacf, context, zzacf2.zzatz.zzcu, zzadz.getUrl(), str3, zzadz, zzov4, zzadm);
        }
        if (zza7 == null) {
            zza7 = new zzacj(0);
        }
        zzov4.zza(zzot2, "tts");
        zza7.zzcub = zzov4.zzjp();
        return zza7;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00d5, code lost:
        r0 = r6.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        r6 = new java.io.InputStreamReader(r11.getInputStream());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        com.google.android.gms.ads.internal.zzbt.zzel();
        r10 = com.google.android.gms.internal.zzaij.zza(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        com.google.android.gms.common.util.zzp.closeQuietly(r6);
        r3.zzcw(r10);
        zza(r0, (java.util.Map<java.lang.String, java.util.List<java.lang.String>>) r12, r10, r9);
        r5.zza(r0, r12, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00f5, code lost:
        if (r1 == null) goto L_0x0100;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00f7, code lost:
        r1.zza(r4, "ufe");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0100, code lost:
        r0 = r5.zza(r7, r22.zzol());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:?, code lost:
        r11.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x010b, code lost:
        if (r2 == null) goto L_0x0112;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x010d, code lost:
        r2.zzcvw.zzop();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0112, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0113, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0114, code lost:
        r3 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0116, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0117, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        com.google.android.gms.common.util.zzp.closeQuietly(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x011b, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0136, code lost:
        com.google.android.gms.internal.zzahw.zzcz("No location header to follow redirect.");
        r0 = new com.google.android.gms.internal.zzacj(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:?, code lost:
        r11.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0144, code lost:
        if (r2 == null) goto L_0x014b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0146, code lost:
        r2.zzcvw.zzop();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x014b, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0165, code lost:
        com.google.android.gms.internal.zzahw.zzcz("Too many redirects.");
        r0 = new com.google.android.gms.internal.zzacj(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:?, code lost:
        r11.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0173, code lost:
        if (r2 == null) goto L_0x017a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0175, code lost:
        r2.zzcvw.zzop();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x017a, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.zzacj zza(com.google.android.gms.internal.zzacf r17, android.content.Context r18, java.lang.String r19, java.lang.String r20, java.lang.String r21, com.google.android.gms.internal.zzadz r22, com.google.android.gms.internal.zzov r23, com.google.android.gms.internal.zzadm r24) {
        /*
            r0 = r17
            r1 = r23
            r2 = r24
            if (r1 == 0) goto L_0x000d
            com.google.android.gms.internal.zzot r4 = r23.zzjo()
            goto L_0x000e
        L_0x000d:
            r4 = 0
        L_0x000e:
            com.google.android.gms.internal.zzadx r5 = new com.google.android.gms.internal.zzadx     // Catch:{ IOException -> 0x01c0 }
            java.lang.String r6 = r22.zzoh()     // Catch:{ IOException -> 0x01c0 }
            r5.<init>(r0, r6)     // Catch:{ IOException -> 0x01c0 }
            java.lang.String r6 = "AdRequestServiceImpl: Sending request: "
            java.lang.String r7 = java.lang.String.valueOf(r20)     // Catch:{ IOException -> 0x01c0 }
            int r8 = r7.length()     // Catch:{ IOException -> 0x01c0 }
            if (r8 == 0) goto L_0x0028
            java.lang.String r6 = r6.concat(r7)     // Catch:{ IOException -> 0x01c0 }
            goto L_0x002e
        L_0x0028:
            java.lang.String r7 = new java.lang.String     // Catch:{ IOException -> 0x01c0 }
            r7.<init>(r6)     // Catch:{ IOException -> 0x01c0 }
            r6 = r7
        L_0x002e:
            com.google.android.gms.internal.zzahw.zzby(r6)     // Catch:{ IOException -> 0x01c0 }
            java.net.URL r6 = new java.net.URL     // Catch:{ IOException -> 0x01c0 }
            r7 = r20
            r6.<init>(r7)     // Catch:{ IOException -> 0x01c0 }
            com.google.android.gms.common.util.zze r7 = com.google.android.gms.ads.internal.zzbt.zzes()     // Catch:{ IOException -> 0x01c0 }
            long r7 = r7.elapsedRealtime()     // Catch:{ IOException -> 0x01c0 }
            r9 = 0
            r10 = 0
        L_0x0042:
            if (r2 == 0) goto L_0x0049
            com.google.android.gms.internal.zzaei r11 = r2.zzcvw     // Catch:{ IOException -> 0x01c0 }
            r11.zzoo()     // Catch:{ IOException -> 0x01c0 }
        L_0x0049:
            java.net.URLConnection r11 = r6.openConnection()     // Catch:{ IOException -> 0x01c0 }
            java.net.HttpURLConnection r11 = (java.net.HttpURLConnection) r11     // Catch:{ IOException -> 0x01c0 }
            com.google.android.gms.internal.zzaij r12 = com.google.android.gms.ads.internal.zzbt.zzel()     // Catch:{ all -> 0x01b4 }
            r13 = r18
            r14 = r19
            r12.zza((android.content.Context) r13, (java.lang.String) r14, (boolean) r9, (java.net.HttpURLConnection) r11)     // Catch:{ all -> 0x01b4 }
            boolean r12 = android.text.TextUtils.isEmpty(r21)     // Catch:{ all -> 0x01b4 }
            if (r12 != 0) goto L_0x006e
            boolean r12 = r22.zzoj()     // Catch:{ all -> 0x01b4 }
            if (r12 == 0) goto L_0x006e
            java.lang.String r12 = "x-afma-drt-cookie"
            r15 = r21
            r11.addRequestProperty(r12, r15)     // Catch:{ all -> 0x01b4 }
            goto L_0x0070
        L_0x006e:
            r15 = r21
        L_0x0070:
            java.lang.String r12 = r0.zzcss     // Catch:{ all -> 0x01b4 }
            boolean r16 = android.text.TextUtils.isEmpty(r12)     // Catch:{ all -> 0x01b4 }
            if (r16 != 0) goto L_0x0082
            java.lang.String r16 = "Sending webview cookie in ad request header."
            com.google.android.gms.internal.zzahw.zzby(r16)     // Catch:{ all -> 0x01b4 }
            java.lang.String r9 = "Cookie"
            r11.addRequestProperty(r9, r12)     // Catch:{ all -> 0x01b4 }
        L_0x0082:
            r9 = 1
            if (r22 == 0) goto L_0x00b7
            java.lang.String r12 = r22.zzoi()     // Catch:{ all -> 0x01b4 }
            boolean r12 = android.text.TextUtils.isEmpty(r12)     // Catch:{ all -> 0x01b4 }
            if (r12 != 0) goto L_0x00b7
            r11.setDoOutput(r9)     // Catch:{ all -> 0x01b4 }
            java.lang.String r12 = r22.zzoi()     // Catch:{ all -> 0x01b4 }
            byte[] r12 = r12.getBytes()     // Catch:{ all -> 0x01b4 }
            int r9 = r12.length     // Catch:{ all -> 0x01b4 }
            r11.setFixedLengthStreamingMode(r9)     // Catch:{ all -> 0x01b4 }
            java.io.BufferedOutputStream r9 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x00b1 }
            java.io.OutputStream r3 = r11.getOutputStream()     // Catch:{ all -> 0x00b1 }
            r9.<init>(r3)     // Catch:{ all -> 0x00b1 }
            r9.write(r12)     // Catch:{ all -> 0x00ae }
            com.google.android.gms.common.util.zzp.closeQuietly(r9)     // Catch:{ all -> 0x01b4 }
            goto L_0x00b8
        L_0x00ae:
            r0 = move-exception
            r3 = r9
            goto L_0x00b3
        L_0x00b1:
            r0 = move-exception
            r3 = 0
        L_0x00b3:
            com.google.android.gms.common.util.zzp.closeQuietly(r3)     // Catch:{ all -> 0x01b4 }
            throw r0     // Catch:{ all -> 0x01b4 }
        L_0x00b7:
            r12 = 0
        L_0x00b8:
            com.google.android.gms.internal.zzaks r3 = new com.google.android.gms.internal.zzaks     // Catch:{ all -> 0x01b4 }
            java.lang.String r9 = r0.zzcsi     // Catch:{ all -> 0x01b4 }
            r3.<init>(r9)     // Catch:{ all -> 0x01b4 }
            r3.zza((java.net.HttpURLConnection) r11, (byte[]) r12)     // Catch:{ all -> 0x01b4 }
            int r9 = r11.getResponseCode()     // Catch:{ all -> 0x01b4 }
            java.util.Map r12 = r11.getHeaderFields()     // Catch:{ all -> 0x01b4 }
            r3.zza((java.net.HttpURLConnection) r11, (int) r9)     // Catch:{ all -> 0x01b4 }
            r0 = 200(0xc8, float:2.8E-43)
            r13 = 300(0x12c, float:4.2E-43)
            if (r9 < r0) goto L_0x011c
            if (r9 >= r13) goto L_0x011c
            java.lang.String r0 = r6.toString()     // Catch:{ all -> 0x01b4 }
            java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch:{ all -> 0x0116 }
            java.io.InputStream r10 = r11.getInputStream()     // Catch:{ all -> 0x0116 }
            r6.<init>(r10)     // Catch:{ all -> 0x0116 }
            com.google.android.gms.ads.internal.zzbt.zzel()     // Catch:{ all -> 0x0113 }
            java.lang.String r10 = com.google.android.gms.internal.zzaij.zza((java.io.InputStreamReader) r6)     // Catch:{ all -> 0x0113 }
            com.google.android.gms.common.util.zzp.closeQuietly(r6)     // Catch:{ all -> 0x01b4 }
            r3.zzcw(r10)     // Catch:{ all -> 0x01b4 }
            zza((java.lang.String) r0, (java.util.Map<java.lang.String, java.util.List<java.lang.String>>) r12, (java.lang.String) r10, (int) r9)     // Catch:{ all -> 0x01b4 }
            r5.zza(r0, r12, r10)     // Catch:{ all -> 0x01b4 }
            if (r1 == 0) goto L_0x0100
            java.lang.String r0 = "ufe"
            java.lang.String[] r0 = new java.lang.String[]{r0}     // Catch:{ all -> 0x01b4 }
            r1.zza(r4, r0)     // Catch:{ all -> 0x01b4 }
        L_0x0100:
            boolean r0 = r22.zzol()     // Catch:{ all -> 0x01b4 }
            com.google.android.gms.internal.zzacj r0 = r5.zza((long) r7, (boolean) r0)     // Catch:{ all -> 0x01b4 }
            r11.disconnect()     // Catch:{ IOException -> 0x01c0 }
            if (r2 == 0) goto L_0x0112
            com.google.android.gms.internal.zzaei r1 = r2.zzcvw     // Catch:{ IOException -> 0x01c0 }
            r1.zzop()     // Catch:{ IOException -> 0x01c0 }
        L_0x0112:
            return r0
        L_0x0113:
            r0 = move-exception
            r3 = r6
            goto L_0x0118
        L_0x0116:
            r0 = move-exception
            r3 = 0
        L_0x0118:
            com.google.android.gms.common.util.zzp.closeQuietly(r3)     // Catch:{ all -> 0x01b4 }
            throw r0     // Catch:{ all -> 0x01b4 }
        L_0x011c:
            java.lang.String r0 = r6.toString()     // Catch:{ all -> 0x01b4 }
            r3 = 0
            zza((java.lang.String) r0, (java.util.Map<java.lang.String, java.util.List<java.lang.String>>) r12, (java.lang.String) r3, (int) r9)     // Catch:{ all -> 0x01b4 }
            if (r9 < r13) goto L_0x018d
            r0 = 400(0x190, float:5.6E-43)
            if (r9 >= r0) goto L_0x018d
            java.lang.String r0 = "Location"
            java.lang.String r0 = r11.getHeaderField(r0)     // Catch:{ all -> 0x01b4 }
            boolean r6 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x01b4 }
            if (r6 == 0) goto L_0x014c
            java.lang.String r0 = "No location header to follow redirect."
            com.google.android.gms.internal.zzahw.zzcz(r0)     // Catch:{ all -> 0x01b4 }
            com.google.android.gms.internal.zzacj r0 = new com.google.android.gms.internal.zzacj     // Catch:{ all -> 0x01b4 }
            r1 = 0
            r0.<init>(r1)     // Catch:{ all -> 0x01b4 }
            r11.disconnect()     // Catch:{ IOException -> 0x01c0 }
            if (r2 == 0) goto L_0x014b
            com.google.android.gms.internal.zzaei r1 = r2.zzcvw     // Catch:{ IOException -> 0x01c0 }
            r1.zzop()     // Catch:{ IOException -> 0x01c0 }
        L_0x014b:
            return r0
        L_0x014c:
            java.net.URL r6 = new java.net.URL     // Catch:{ all -> 0x01b4 }
            r6.<init>(r0)     // Catch:{ all -> 0x01b4 }
            r0 = 1
            int r10 = r10 + r0
            com.google.android.gms.internal.zzny<java.lang.Integer> r0 = com.google.android.gms.internal.zzoi.zzbvf     // Catch:{ all -> 0x01b4 }
            com.google.android.gms.internal.zzog r9 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ all -> 0x01b4 }
            java.lang.Object r0 = r9.zzd(r0)     // Catch:{ all -> 0x01b4 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ all -> 0x01b4 }
            int r0 = r0.intValue()     // Catch:{ all -> 0x01b4 }
            if (r10 <= r0) goto L_0x017b
            java.lang.String r0 = "Too many redirects."
            com.google.android.gms.internal.zzahw.zzcz(r0)     // Catch:{ all -> 0x01b4 }
            com.google.android.gms.internal.zzacj r0 = new com.google.android.gms.internal.zzacj     // Catch:{ all -> 0x01b4 }
            r1 = 0
            r0.<init>(r1)     // Catch:{ all -> 0x01b4 }
            r11.disconnect()     // Catch:{ IOException -> 0x01c0 }
            if (r2 == 0) goto L_0x017a
            com.google.android.gms.internal.zzaei r1 = r2.zzcvw     // Catch:{ IOException -> 0x01c0 }
            r1.zzop()     // Catch:{ IOException -> 0x01c0 }
        L_0x017a:
            return r0
        L_0x017b:
            r5.zzo(r12)     // Catch:{ all -> 0x01b4 }
            r11.disconnect()     // Catch:{ IOException -> 0x01c0 }
            if (r2 == 0) goto L_0x0188
            com.google.android.gms.internal.zzaei r0 = r2.zzcvw     // Catch:{ IOException -> 0x01c0 }
            r0.zzop()     // Catch:{ IOException -> 0x01c0 }
        L_0x0188:
            r0 = r17
            r9 = 0
            goto L_0x0042
        L_0x018d:
            r0 = 46
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x01b4 }
            r1.<init>(r0)     // Catch:{ all -> 0x01b4 }
            java.lang.String r0 = "Received error HTTP response code: "
            r1.append(r0)     // Catch:{ all -> 0x01b4 }
            r1.append(r9)     // Catch:{ all -> 0x01b4 }
            java.lang.String r0 = r1.toString()     // Catch:{ all -> 0x01b4 }
            com.google.android.gms.internal.zzahw.zzcz(r0)     // Catch:{ all -> 0x01b4 }
            com.google.android.gms.internal.zzacj r0 = new com.google.android.gms.internal.zzacj     // Catch:{ all -> 0x01b4 }
            r1 = 0
            r0.<init>(r1)     // Catch:{ all -> 0x01b4 }
            r11.disconnect()     // Catch:{ IOException -> 0x01c0 }
            if (r2 == 0) goto L_0x01b3
            com.google.android.gms.internal.zzaei r1 = r2.zzcvw     // Catch:{ IOException -> 0x01c0 }
            r1.zzop()     // Catch:{ IOException -> 0x01c0 }
        L_0x01b3:
            return r0
        L_0x01b4:
            r0 = move-exception
            r11.disconnect()     // Catch:{ IOException -> 0x01c0 }
            if (r2 == 0) goto L_0x01bf
            com.google.android.gms.internal.zzaei r1 = r2.zzcvw     // Catch:{ IOException -> 0x01c0 }
            r1.zzop()     // Catch:{ IOException -> 0x01c0 }
        L_0x01bf:
            throw r0     // Catch:{ IOException -> 0x01c0 }
        L_0x01c0:
            r0 = move-exception
            java.lang.String r1 = "Error while connecting to ad server: "
            java.lang.String r0 = r0.getMessage()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r2 = r0.length()
            if (r2 == 0) goto L_0x01d6
            java.lang.String r0 = r1.concat(r0)
            goto L_0x01db
        L_0x01d6:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r1)
        L_0x01db:
            com.google.android.gms.internal.zzahw.zzcz(r0)
            com.google.android.gms.internal.zzacj r0 = new com.google.android.gms.internal.zzacj
            r1 = 2
            r0.<init>(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzadn.zza(com.google.android.gms.internal.zzacf, android.content.Context, java.lang.String, java.lang.String, java.lang.String, com.google.android.gms.internal.zzadz, com.google.android.gms.internal.zzov, com.google.android.gms.internal.zzadm):com.google.android.gms.internal.zzacj");
    }

    public static zzadn zza(Context context, zzadm zzadm) {
        zzadn zzadn;
        synchronized (sLock) {
            if (zzcwb == null) {
                if (context.getApplicationContext() != null) {
                    context = context.getApplicationContext();
                }
                zzoi.initialize(context);
                zzcwb = new zzadn(context, zzadm);
                if (context.getApplicationContext() != null) {
                    zzbt.zzel().zzai(context);
                }
                zzahu.zzaf(context);
            }
            zzadn = zzcwb;
        }
        return zzadn;
    }

    private static void zza(String str, Map<String, List<String>> map, String str2, int i) {
        if (zzahw.zzae(2)) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 39);
            sb.append("Http Response: {\n  URL:\n    ");
            sb.append(str);
            sb.append("\n  Headers:");
            zzahw.v(sb.toString());
            if (map != null) {
                for (String next : map.keySet()) {
                    StringBuilder sb2 = new StringBuilder(String.valueOf(next).length() + 5);
                    sb2.append("    ");
                    sb2.append(next);
                    sb2.append(":");
                    zzahw.v(sb2.toString());
                    for (String valueOf : map.get(next)) {
                        String valueOf2 = String.valueOf(valueOf);
                        zzahw.v(valueOf2.length() != 0 ? "      ".concat(valueOf2) : new String("      "));
                    }
                }
            }
            zzahw.v("  Body:");
            if (str2 != null) {
                int i2 = 0;
                while (i2 < Math.min(str2.length(), 100000)) {
                    int i3 = i2 + 1000;
                    zzahw.v(str2.substring(i2, Math.min(str2.length(), i3)));
                    i2 = i3;
                }
            } else {
                zzahw.v("    null");
            }
            StringBuilder sb3 = new StringBuilder(34);
            sb3.append("  Response Code:\n    ");
            sb3.append(i);
            sb3.append("\n}");
            zzahw.v(sb3.toString());
        }
    }

    public final void zza(zzacf zzacf, zzacq zzacq) {
        zzbt.zzep().zzd(this.mContext, zzacf.zzatz);
        zzalt<Void> zzb = zzaid.zzb(new zzadp(this, zzacf, zzacq));
        zzbt.zzfa().zzrt();
        zzbt.zzfa().getHandler().postDelayed(new zzadq(this, zzb), 60000);
    }

    public final void zza(zzacy zzacy, zzact zzact) {
        zzahw.v("Nonagon code path entered in octagon");
        throw new IllegalArgumentException();
    }

    public final zzacj zzb(zzacf zzacf) {
        return zza(this.mContext, this.zzcwc, zzacf, this.zzcwd);
    }
}
