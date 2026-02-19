package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.common.zzf;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzabh
public final class zzagf implements zzagq {
    /* access modifiers changed from: private */
    public static List<Future<Void>> zzday = Collections.synchronizedList(new ArrayList());
    private static ScheduledExecutorService zzdaz = Executors.newSingleThreadScheduledExecutor();
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    private final zzagn zzcxr;
    /* access modifiers changed from: private */
    public final zzflw zzdba;
    private final LinkedHashMap<String, zzfme> zzdbb;
    private final zzags zzdbc;
    private boolean zzdbd;
    private HashSet<String> zzdbe = new HashSet<>();
    private boolean zzdbf = false;
    private boolean zzdbg = false;
    private boolean zzdbh = false;

    public zzagf(Context context, zzala zzala, zzagn zzagn, String str, zzags zzags) {
        zzbq.checkNotNull(zzagn, "SafeBrowsing config is not present.");
        this.mContext = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.zzdbb = new LinkedHashMap<>();
        this.zzdbc = zzags;
        this.zzcxr = zzagn;
        for (String lowerCase : zzagn.zzdbr) {
            this.zzdbe.add(lowerCase.toLowerCase(Locale.ENGLISH));
        }
        this.zzdbe.remove("cookie".toLowerCase(Locale.ENGLISH));
        zzflw zzflw = new zzflw();
        zzflw.zzbdh = 8;
        zzflw.url = str;
        zzflw.zzpwg = str;
        zzflw.zzpwi = new zzflx();
        zzflw.zzpwi.zzdbn = this.zzcxr.zzdbn;
        zzfmf zzfmf = new zzfmf();
        zzfmf.zzpxp = zzala.zzcu;
        zzfmf.zzpxr = Boolean.valueOf(zzbih.zzdd(this.mContext).zzaoe());
        zzf.zzahf();
        long zzcg = (long) zzf.zzcg(this.mContext);
        if (zzcg > 0) {
            zzfmf.zzpxq = Long.valueOf(zzcg);
        }
        zzflw.zzpws = zzfmf;
        this.zzdba = zzflw;
    }

    private final zzfme zzbw(String str) {
        zzfme zzfme;
        synchronized (this.mLock) {
            zzfme = this.zzdbb.get(str);
        }
        return zzfme;
    }

    static final /* synthetic */ Void zzbx(String str) {
        return null;
    }

    private final zzalt<Void> zzpi() {
        zzalt<Void> zza;
        if (!((this.zzdbd && this.zzcxr.zzdbt) || (this.zzdbh && this.zzcxr.zzdbs) || (!this.zzdbd && this.zzcxr.zzdbq))) {
            return zzali.zzh(null);
        }
        synchronized (this.mLock) {
            this.zzdba.zzpwj = new zzfme[this.zzdbb.size()];
            this.zzdbb.values().toArray(this.zzdba.zzpwj);
            if (zzagp.isEnabled()) {
                String str = this.zzdba.url;
                String str2 = this.zzdba.zzpwk;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 53 + String.valueOf(str2).length());
                sb.append("Sending SB report\n  url: ");
                sb.append(str);
                sb.append("\n  clickUrl: ");
                sb.append(str2);
                sb.append("\n  resources: \n");
                StringBuilder sb2 = new StringBuilder(sb.toString());
                for (zzfme zzfme : this.zzdba.zzpwj) {
                    sb2.append("    [");
                    sb2.append(zzfme.zzpxo.length);
                    sb2.append("] ");
                    sb2.append(zzfme.url);
                }
                zzagp.zzby(sb2.toString());
            }
            zzalt<String> zza2 = new zzajr(this.mContext).zza(1, this.zzcxr.zzdbo, (Map<String, String>) null, zzfls.zzc(this.zzdba));
            if (zzagp.isEnabled()) {
                zza2.zza(new zzagk(this), zzaid.zzdfi);
            }
            zza = zzali.zza(zza2, zzagh.zzdbj, zzaly.zzdju);
        }
        return zza;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0022, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(java.lang.String r7, java.util.Map<java.lang.String, java.lang.String> r8, int r9) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.mLock
            monitor-enter(r0)
            r1 = 3
            if (r9 != r1) goto L_0x0009
            r2 = 1
            r6.zzdbh = r2     // Catch:{ all -> 0x00c6 }
        L_0x0009:
            java.util.LinkedHashMap<java.lang.String, com.google.android.gms.internal.zzfme> r2 = r6.zzdbb     // Catch:{ all -> 0x00c6 }
            boolean r2 = r2.containsKey(r7)     // Catch:{ all -> 0x00c6 }
            if (r2 == 0) goto L_0x0023
            if (r9 != r1) goto L_0x0021
            java.util.LinkedHashMap<java.lang.String, com.google.android.gms.internal.zzfme> r8 = r6.zzdbb     // Catch:{ all -> 0x00c6 }
            java.lang.Object r7 = r8.get(r7)     // Catch:{ all -> 0x00c6 }
            com.google.android.gms.internal.zzfme r7 = (com.google.android.gms.internal.zzfme) r7     // Catch:{ all -> 0x00c6 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x00c6 }
            r7.zzpxn = r8     // Catch:{ all -> 0x00c6 }
        L_0x0021:
            monitor-exit(r0)     // Catch:{ all -> 0x00c6 }
            return
        L_0x0023:
            com.google.android.gms.internal.zzfme r1 = new com.google.android.gms.internal.zzfme     // Catch:{ all -> 0x00c6 }
            r1.<init>()     // Catch:{ all -> 0x00c6 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x00c6 }
            r1.zzpxn = r9     // Catch:{ all -> 0x00c6 }
            java.util.LinkedHashMap<java.lang.String, com.google.android.gms.internal.zzfme> r9 = r6.zzdbb     // Catch:{ all -> 0x00c6 }
            int r9 = r9.size()     // Catch:{ all -> 0x00c6 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x00c6 }
            r1.zzjsx = r9     // Catch:{ all -> 0x00c6 }
            r1.url = r7     // Catch:{ all -> 0x00c6 }
            com.google.android.gms.internal.zzflz r9 = new com.google.android.gms.internal.zzflz     // Catch:{ all -> 0x00c6 }
            r9.<init>()     // Catch:{ all -> 0x00c6 }
            r1.zzpxi = r9     // Catch:{ all -> 0x00c6 }
            java.util.HashSet<java.lang.String> r9 = r6.zzdbe     // Catch:{ all -> 0x00c6 }
            int r9 = r9.size()     // Catch:{ all -> 0x00c6 }
            if (r9 <= 0) goto L_0x00bf
            if (r8 == 0) goto L_0x00bf
            java.util.LinkedList r9 = new java.util.LinkedList     // Catch:{ all -> 0x00c6 }
            r9.<init>()     // Catch:{ all -> 0x00c6 }
            java.util.Set r8 = r8.entrySet()     // Catch:{ all -> 0x00c6 }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ all -> 0x00c6 }
        L_0x005a:
            boolean r2 = r8.hasNext()     // Catch:{ all -> 0x00c6 }
            if (r2 == 0) goto L_0x00b2
            java.lang.Object r2 = r8.next()     // Catch:{ all -> 0x00c6 }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ all -> 0x00c6 }
            java.lang.Object r3 = r2.getKey()     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            if (r3 == 0) goto L_0x0073
            java.lang.Object r3 = r2.getKey()     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            goto L_0x0075
        L_0x0073:
            java.lang.String r3 = ""
        L_0x0075:
            java.lang.Object r4 = r2.getValue()     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            if (r4 == 0) goto L_0x0082
            java.lang.Object r2 = r2.getValue()     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            goto L_0x0084
        L_0x0082:
            java.lang.String r2 = ""
        L_0x0084:
            java.util.Locale r4 = java.util.Locale.ENGLISH     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            java.lang.String r4 = r3.toLowerCase(r4)     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            java.util.HashSet<java.lang.String> r5 = r6.zzdbe     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            boolean r4 = r5.contains(r4)     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            if (r4 != 0) goto L_0x0093
            goto L_0x005a
        L_0x0093:
            com.google.android.gms.internal.zzfly r4 = new com.google.android.gms.internal.zzfly     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            r4.<init>()     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            java.lang.String r5 = "UTF-8"
            byte[] r3 = r3.getBytes(r5)     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            r4.zzpwu = r3     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            java.lang.String r3 = "UTF-8"
            byte[] r2 = r2.getBytes(r3)     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            r4.zzosz = r2     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            r9.add(r4)     // Catch:{ UnsupportedEncodingException -> 0x00ac }
            goto L_0x005a
        L_0x00ac:
            java.lang.String r2 = "Cannot convert string to bytes, skip header."
            com.google.android.gms.internal.zzagp.zzby(r2)     // Catch:{ all -> 0x00c6 }
            goto L_0x005a
        L_0x00b2:
            int r8 = r9.size()     // Catch:{ all -> 0x00c6 }
            com.google.android.gms.internal.zzfly[] r8 = new com.google.android.gms.internal.zzfly[r8]     // Catch:{ all -> 0x00c6 }
            r9.toArray(r8)     // Catch:{ all -> 0x00c6 }
            com.google.android.gms.internal.zzflz r9 = r1.zzpxi     // Catch:{ all -> 0x00c6 }
            r9.zzpww = r8     // Catch:{ all -> 0x00c6 }
        L_0x00bf:
            java.util.LinkedHashMap<java.lang.String, com.google.android.gms.internal.zzfme> r8 = r6.zzdbb     // Catch:{ all -> 0x00c6 }
            r8.put(r7, r1)     // Catch:{ all -> 0x00c6 }
            monitor-exit(r0)     // Catch:{ all -> 0x00c6 }
            return
        L_0x00c6:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00c6 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzagf.zza(java.lang.String, java.util.Map, int):void");
    }

    public final void zzbv(String str) {
        synchronized (this.mLock) {
            this.zzdba.zzpwk = str;
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzalt<Void> zzp(Map<String, String> map) throws Exception {
        if (map != null) {
            try {
                for (String str : map.keySet()) {
                    JSONArray optJSONArray = new JSONObject((String) map.get(str)).optJSONArray("matches");
                    if (optJSONArray != null) {
                        synchronized (this.mLock) {
                            int length = optJSONArray.length();
                            zzfme zzbw = zzbw(str);
                            if (zzbw == null) {
                                String valueOf = String.valueOf(str);
                                zzagp.zzby(valueOf.length() != 0 ? "Cannot find the corresponding resource object for ".concat(valueOf) : new String("Cannot find the corresponding resource object for "));
                            } else {
                                zzbw.zzpxo = new String[length];
                                boolean z = false;
                                for (int i = 0; i < length; i++) {
                                    zzbw.zzpxo[i] = optJSONArray.getJSONObject(i).getString("threat_type");
                                }
                                boolean z2 = this.zzdbd;
                                if (length > 0) {
                                    z = true;
                                }
                                this.zzdbd = z | z2;
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                if (((Boolean) zzlc.zzio().zzd(zzoi.zzbty)).booleanValue()) {
                    zzahw.zza("Failed to get SafeBrowsing metadata", e);
                }
                return zzali.zzd(new Exception("Safebrowsing report transmission failed."));
            }
        }
        if (this.zzdbd) {
            synchronized (this.mLock) {
                this.zzdba.zzbdh = 9;
            }
        }
        return zzpi();
    }

    public final zzagn zzpe() {
        return this.zzcxr;
    }

    public final boolean zzpf() {
        return zzs.zzanv() && this.zzcxr.zzdbp && !this.zzdbg;
    }

    public final void zzpg() {
        this.zzdbf = true;
    }

    public final void zzph() {
        synchronized (this.mLock) {
            zzalt<Void> zza = zzali.zza(this.zzdbc.zza(this.mContext, this.zzdbb.keySet()), new zzagg(this), zzaly.zzdju);
            zzalt<Void> zza2 = zzali.zza(zza, 10, TimeUnit.SECONDS, zzdaz);
            zzali.zza(zza, new zzagj(this, zza2), zzaly.zzdju);
            zzday.add(zza2);
        }
    }

    public final void zzq(View view) {
        if (this.zzcxr.zzdbp && !this.zzdbg) {
            zzbt.zzel();
            Bitmap zzs = zzaij.zzs(view);
            if (zzs == null) {
                zzagp.zzby("Failed to capture the webview bitmap.");
                return;
            }
            this.zzdbg = true;
            zzaij.zzc(new zzagi(this, zzs));
        }
    }
}
