package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.internal.zziw;
import org.jivesoftware.smackx.GroupChatInvitation;
import org.json.JSONObject;

@zzabh
public final class zzabk extends zzahs implements zzabx {
    private final Context mContext;
    private zzvq zzcir;
    private zzacf zzcjk;
    private zzacj zzcoc;
    /* access modifiers changed from: private */
    public Runnable zzcod;
    /* access modifiers changed from: private */
    public final Object zzcoe = new Object();
    private final zzabj zzcre;
    /* access modifiers changed from: private */
    public final zzacg zzcrf;
    private final zziu zzcrg;
    private final zziz zzcrh;
    zzajb zzcri;

    public zzabk(Context context, zzacg zzacg, zzabj zzabj, zziz zziz) {
        this.zzcre = zzabj;
        this.mContext = context;
        this.zzcrf = zzacg;
        this.zzcrh = zziz;
        zziu zziu = new zziu(zziz);
        this.zzcrg = zziu;
        zziu.zza((zziv) new zzabl(this));
        zzjv zzjv = new zzjv();
        zzjv.zzbfv = Integer.valueOf(zzacg.zzatz.zzdiz);
        zzjv.zzbfw = Integer.valueOf(zzacg.zzatz.zzdja);
        zzjv.zzbfx = Integer.valueOf(zzacg.zzatz.zzdjb ? 0 : 2);
        zziu.zza((zziv) new zzabm(zzjv));
        if (zzacg.zzcrw != null) {
            zziu.zza((zziv) new zzabn(this));
        }
        zzko zzko = zzacg.zzaud;
        zziu.zza((!zzko.zzbib || !"interstitial_mb".equals(zzko.zzbia)) ? (!zzko.zzbib || !"reward_mb".equals(zzko.zzbia)) ? (zzko.zzbid || zzko.zzbib) ? zzabr.zzcrl : zzabq.zzcrl : zzabp.zzcrl : zzabo.zzcrl);
        zziu.zza(zziw.zza.zzb.AD_REQUEST);
    }

    private final zzko zza(zzacf zzacf) throws zzabu {
        zzvq zzvq;
        zzacf zzacf2 = this.zzcjk;
        if (((zzacf2 == null || zzacf2.zzauu == null || this.zzcjk.zzauu.size() <= 1) ? false : true) && (zzvq = this.zzcir) != null && !zzvq.zzcim) {
            return null;
        }
        if (this.zzcoc.zzbie) {
            for (zzko zzko : zzacf.zzaud.zzbic) {
                if (zzko.zzbie) {
                    return new zzko(zzko, zzacf.zzaud.zzbic);
                }
            }
        }
        if (this.zzcoc.zzctr != null) {
            String[] split = this.zzcoc.zzctr.split(GroupChatInvitation.ELEMENT_NAME);
            if (split.length != 2) {
                String valueOf = String.valueOf(this.zzcoc.zzctr);
                throw new zzabu(valueOf.length() != 0 ? "Invalid ad size format from the ad response: ".concat(valueOf) : new String("Invalid ad size format from the ad response: "), 0);
            }
            try {
                int parseInt = Integer.parseInt(split[0]);
                int parseInt2 = Integer.parseInt(split[1]);
                for (zzko zzko2 : zzacf.zzaud.zzbic) {
                    float f = this.mContext.getResources().getDisplayMetrics().density;
                    int i = zzko2.width == -1 ? (int) (((float) zzko2.widthPixels) / f) : zzko2.width;
                    int i2 = zzko2.height == -2 ? (int) (((float) zzko2.heightPixels) / f) : zzko2.height;
                    if (parseInt == i && parseInt2 == i2 && !zzko2.zzbie) {
                        return new zzko(zzko2, zzacf.zzaud.zzbic);
                    }
                }
                String valueOf2 = String.valueOf(this.zzcoc.zzctr);
                throw new zzabu(valueOf2.length() != 0 ? "The ad size from the ad response was not one of the requested sizes: ".concat(valueOf2) : new String("The ad size from the ad response was not one of the requested sizes: "), 0);
            } catch (NumberFormatException unused) {
                String valueOf3 = String.valueOf(this.zzcoc.zzctr);
                throw new zzabu(valueOf3.length() != 0 ? "Invalid ad size number from the ad response: ".concat(valueOf3) : new String("Invalid ad size number from the ad response: "), 0);
            }
        } else {
            throw new zzabu("The ad response must specify one of the supported ad sizes.", 0);
        }
    }

    /* access modifiers changed from: private */
    public final void zzc(int i, String str) {
        int i2 = i;
        if (i2 == 3 || i2 == -1) {
            zzahw.zzcy(str);
        } else {
            zzahw.zzcz(str);
        }
        this.zzcoc = this.zzcoc == null ? new zzacj(i2) : new zzacj(i2, this.zzcoc.zzcic);
        zzacf zzacf = this.zzcjk;
        if (zzacf == null) {
            zzacf = new zzacf(this.zzcrf, -1, (String) null, (String) null, (String) null);
        }
        zzacj zzacj = this.zzcoc;
        this.zzcre.zza(new zzahe(zzacf, zzacj, this.zzcir, (zzko) null, i, -1, zzacj.zzcts, (JSONObject) null, this.zzcrg, (Boolean) null));
    }

    public final void onStop() {
        synchronized (this.zzcoe) {
            zzajb zzajb = this.zzcri;
            if (zzajb != null) {
                zzajb.cancel();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final zzajb zza(zzala zzala, zzamf<zzacf> zzamf) {
        Context context = this.mContext;
        if (new zzabw(context).zza(zzala)) {
            zzahw.zzby("Fetching ad response from local ad request service.");
            zzacc zzacc = new zzacc(context, zzamf, this);
            zzacc.zzns();
            return zzacc;
        }
        zzahw.zzby("Fetching ad response from remote ad request service.");
        zzlc.zzij();
        if (zzako.zzbb(context)) {
            return new zzacd(context, zzala, zzamf, this);
        }
        zzahw.zzcz("Failed to connect to remote ad request service.");
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:62:0x018e  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x01d3  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01dd  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01eb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.zzacj r14) {
        /*
            r13 = this;
            java.lang.String r0 = "Received ad response."
            com.google.android.gms.internal.zzahw.zzby(r0)
            r13.zzcoc = r14
            com.google.android.gms.common.util.zze r14 = com.google.android.gms.ads.internal.zzbt.zzes()
            long r6 = r14.elapsedRealtime()
            java.lang.Object r14 = r13.zzcoe
            monitor-enter(r14)
            r0 = 0
            r13.zzcri = r0     // Catch:{ all -> 0x0217 }
            monitor-exit(r14)     // Catch:{ all -> 0x0217 }
            com.google.android.gms.internal.zzahi r14 = com.google.android.gms.ads.internal.zzbt.zzep()
            com.google.android.gms.internal.zzahy r14 = r14.zzqe()
            com.google.android.gms.internal.zzacj r1 = r13.zzcoc
            boolean r1 = r1.zzcsr
            r14.zzad((boolean) r1)
            com.google.android.gms.internal.zzny<java.lang.Boolean> r14 = com.google.android.gms.internal.zzoi.zzbpr
            com.google.android.gms.internal.zzog r1 = com.google.android.gms.internal.zzlc.zzio()
            java.lang.Object r14 = r1.zzd(r14)
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            boolean r14 = r14.booleanValue()
            if (r14 == 0) goto L_0x005c
            com.google.android.gms.internal.zzacj r14 = r13.zzcoc
            boolean r14 = r14.zzctd
            if (r14 == 0) goto L_0x004d
            com.google.android.gms.internal.zzahi r14 = com.google.android.gms.ads.internal.zzbt.zzep()
            com.google.android.gms.internal.zzahy r14 = r14.zzqe()
            com.google.android.gms.internal.zzacf r1 = r13.zzcjk
            java.lang.String r1 = r1.zzatx
            r14.zzcd(r1)
            goto L_0x005c
        L_0x004d:
            com.google.android.gms.internal.zzahi r14 = com.google.android.gms.ads.internal.zzbt.zzep()
            com.google.android.gms.internal.zzahy r14 = r14.zzqe()
            com.google.android.gms.internal.zzacf r1 = r13.zzcjk
            java.lang.String r1 = r1.zzatx
            r14.zzce(r1)
        L_0x005c:
            com.google.android.gms.internal.zzacj r14 = r13.zzcoc     // Catch:{ zzabu -> 0x020a }
            int r14 = r14.errorCode     // Catch:{ zzabu -> 0x020a }
            r1 = -2
            r2 = -3
            if (r14 == r1) goto L_0x008c
            com.google.android.gms.internal.zzacj r14 = r13.zzcoc     // Catch:{ zzabu -> 0x020a }
            int r14 = r14.errorCode     // Catch:{ zzabu -> 0x020a }
            if (r14 != r2) goto L_0x006b
            goto L_0x008c
        L_0x006b:
            com.google.android.gms.internal.zzabu r14 = new com.google.android.gms.internal.zzabu     // Catch:{ zzabu -> 0x020a }
            com.google.android.gms.internal.zzacj r0 = r13.zzcoc     // Catch:{ zzabu -> 0x020a }
            int r0 = r0.errorCode     // Catch:{ zzabu -> 0x020a }
            r1 = 66
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ zzabu -> 0x020a }
            r2.<init>(r1)     // Catch:{ zzabu -> 0x020a }
            java.lang.String r1 = "There was a problem getting an ad response. ErrorCode: "
            r2.append(r1)     // Catch:{ zzabu -> 0x020a }
            r2.append(r0)     // Catch:{ zzabu -> 0x020a }
            java.lang.String r0 = r2.toString()     // Catch:{ zzabu -> 0x020a }
            com.google.android.gms.internal.zzacj r1 = r13.zzcoc     // Catch:{ zzabu -> 0x020a }
            int r1 = r1.errorCode     // Catch:{ zzabu -> 0x020a }
            r14.<init>(r0, r1)     // Catch:{ zzabu -> 0x020a }
            throw r14     // Catch:{ zzabu -> 0x020a }
        L_0x008c:
            com.google.android.gms.internal.zzacj r14 = r13.zzcoc     // Catch:{ zzabu -> 0x020a }
            int r14 = r14.errorCode     // Catch:{ zzabu -> 0x020a }
            r1 = 0
            if (r14 == r2) goto L_0x013b
            com.google.android.gms.internal.zzacj r14 = r13.zzcoc     // Catch:{ zzabu -> 0x020a }
            java.lang.String r14 = r14.body     // Catch:{ zzabu -> 0x020a }
            boolean r14 = android.text.TextUtils.isEmpty(r14)     // Catch:{ zzabu -> 0x020a }
            if (r14 != 0) goto L_0x0132
            com.google.android.gms.internal.zzahi r14 = com.google.android.gms.ads.internal.zzbt.zzep()     // Catch:{ zzabu -> 0x020a }
            com.google.android.gms.internal.zzahy r14 = r14.zzqe()     // Catch:{ zzabu -> 0x020a }
            com.google.android.gms.internal.zzacj r2 = r13.zzcoc     // Catch:{ zzabu -> 0x020a }
            boolean r2 = r2.zzcsd     // Catch:{ zzabu -> 0x020a }
            r14.zzaa(r2)     // Catch:{ zzabu -> 0x020a }
            com.google.android.gms.internal.zzacj r14 = r13.zzcoc     // Catch:{ zzabu -> 0x020a }
            boolean r14 = r14.zzcto     // Catch:{ zzabu -> 0x020a }
            if (r14 == 0) goto L_0x00f0
            com.google.android.gms.internal.zzvq r14 = new com.google.android.gms.internal.zzvq     // Catch:{ JSONException -> 0x00c9 }
            com.google.android.gms.internal.zzacj r2 = r13.zzcoc     // Catch:{ JSONException -> 0x00c9 }
            java.lang.String r2 = r2.body     // Catch:{ JSONException -> 0x00c9 }
            r14.<init>((java.lang.String) r2)     // Catch:{ JSONException -> 0x00c9 }
            r13.zzcir = r14     // Catch:{ JSONException -> 0x00c9 }
            com.google.android.gms.internal.zzahi r14 = com.google.android.gms.ads.internal.zzbt.zzep()     // Catch:{ JSONException -> 0x00c9 }
            com.google.android.gms.internal.zzvq r2 = r13.zzcir     // Catch:{ JSONException -> 0x00c9 }
            boolean r2 = r2.zzcia     // Catch:{ JSONException -> 0x00c9 }
            r14.zzz(r2)     // Catch:{ JSONException -> 0x00c9 }
            goto L_0x00fb
        L_0x00c9:
            r14 = move-exception
            java.lang.String r0 = "Could not parse mediation config."
            com.google.android.gms.internal.zzahw.zzb(r0, r14)     // Catch:{ zzabu -> 0x020a }
            com.google.android.gms.internal.zzabu r14 = new com.google.android.gms.internal.zzabu     // Catch:{ zzabu -> 0x020a }
            java.lang.String r0 = "Could not parse mediation config: "
            com.google.android.gms.internal.zzacj r2 = r13.zzcoc     // Catch:{ zzabu -> 0x020a }
            java.lang.String r2 = r2.body     // Catch:{ zzabu -> 0x020a }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ zzabu -> 0x020a }
            int r3 = r2.length()     // Catch:{ zzabu -> 0x020a }
            if (r3 == 0) goto L_0x00e6
            java.lang.String r0 = r0.concat(r2)     // Catch:{ zzabu -> 0x020a }
            goto L_0x00ec
        L_0x00e6:
            java.lang.String r2 = new java.lang.String     // Catch:{ zzabu -> 0x020a }
            r2.<init>(r0)     // Catch:{ zzabu -> 0x020a }
            r0 = r2
        L_0x00ec:
            r14.<init>(r0, r1)     // Catch:{ zzabu -> 0x020a }
            throw r14     // Catch:{ zzabu -> 0x020a }
        L_0x00f0:
            com.google.android.gms.internal.zzahi r14 = com.google.android.gms.ads.internal.zzbt.zzep()     // Catch:{ zzabu -> 0x020a }
            com.google.android.gms.internal.zzacj r2 = r13.zzcoc     // Catch:{ zzabu -> 0x020a }
            boolean r2 = r2.zzcia     // Catch:{ zzabu -> 0x020a }
            r14.zzz(r2)     // Catch:{ zzabu -> 0x020a }
        L_0x00fb:
            com.google.android.gms.internal.zzacj r14 = r13.zzcoc     // Catch:{ zzabu -> 0x020a }
            java.lang.String r14 = r14.zzcss     // Catch:{ zzabu -> 0x020a }
            boolean r14 = android.text.TextUtils.isEmpty(r14)     // Catch:{ zzabu -> 0x020a }
            if (r14 != 0) goto L_0x013b
            com.google.android.gms.internal.zzny<java.lang.Boolean> r14 = com.google.android.gms.internal.zzoi.zzbtz     // Catch:{ zzabu -> 0x020a }
            com.google.android.gms.internal.zzog r2 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ zzabu -> 0x020a }
            java.lang.Object r14 = r2.zzd(r14)     // Catch:{ zzabu -> 0x020a }
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ zzabu -> 0x020a }
            boolean r14 = r14.booleanValue()     // Catch:{ zzabu -> 0x020a }
            if (r14 == 0) goto L_0x013b
            java.lang.String r14 = "Received cookie from server. Setting webview cookie in CookieManager."
            com.google.android.gms.internal.zzahw.zzby(r14)     // Catch:{ zzabu -> 0x020a }
            com.google.android.gms.internal.zzaip r14 = com.google.android.gms.ads.internal.zzbt.zzen()     // Catch:{ zzabu -> 0x020a }
            android.content.Context r2 = r13.mContext     // Catch:{ zzabu -> 0x020a }
            android.webkit.CookieManager r14 = r14.zzau(r2)     // Catch:{ zzabu -> 0x020a }
            if (r14 == 0) goto L_0x013b
            java.lang.String r2 = "googleads.g.doubleclick.net"
            com.google.android.gms.internal.zzacj r3 = r13.zzcoc     // Catch:{ zzabu -> 0x020a }
            java.lang.String r3 = r3.zzcss     // Catch:{ zzabu -> 0x020a }
            r14.setCookie(r2, r3)     // Catch:{ zzabu -> 0x020a }
            goto L_0x013b
        L_0x0132:
            com.google.android.gms.internal.zzabu r14 = new com.google.android.gms.internal.zzabu     // Catch:{ zzabu -> 0x020a }
            java.lang.String r0 = "No fill from ad server."
            r1 = 3
            r14.<init>(r0, r1)     // Catch:{ zzabu -> 0x020a }
            throw r14     // Catch:{ zzabu -> 0x020a }
        L_0x013b:
            com.google.android.gms.internal.zzacf r14 = r13.zzcjk     // Catch:{ zzabu -> 0x020a }
            com.google.android.gms.internal.zzko r14 = r14.zzaud     // Catch:{ zzabu -> 0x020a }
            com.google.android.gms.internal.zzko[] r14 = r14.zzbic     // Catch:{ zzabu -> 0x020a }
            if (r14 == 0) goto L_0x014b
            com.google.android.gms.internal.zzacf r14 = r13.zzcjk     // Catch:{ zzabu -> 0x020a }
            com.google.android.gms.internal.zzko r14 = r13.zza((com.google.android.gms.internal.zzacf) r14)     // Catch:{ zzabu -> 0x020a }
            r4 = r14
            goto L_0x014c
        L_0x014b:
            r4 = r0
        L_0x014c:
            com.google.android.gms.internal.zzahi r14 = com.google.android.gms.ads.internal.zzbt.zzep()
            com.google.android.gms.internal.zzahy r14 = r14.zzqe()
            com.google.android.gms.internal.zzacj r2 = r13.zzcoc
            boolean r2 = r2.zzcty
            r14.zzab(r2)
            com.google.android.gms.internal.zzahi r14 = com.google.android.gms.ads.internal.zzbt.zzep()
            com.google.android.gms.internal.zzahy r14 = r14.zzqe()
            com.google.android.gms.internal.zzacj r2 = r13.zzcoc
            boolean r2 = r2.zzcuk
            r14.zzac((boolean) r2)
            com.google.android.gms.internal.zzacj r14 = r13.zzcoc
            java.lang.String r14 = r14.zzctw
            boolean r14 = android.text.TextUtils.isEmpty(r14)
            if (r14 != 0) goto L_0x0185
            org.json.JSONObject r14 = new org.json.JSONObject     // Catch:{ Exception -> 0x017f }
            com.google.android.gms.internal.zzacj r2 = r13.zzcoc     // Catch:{ Exception -> 0x017f }
            java.lang.String r2 = r2.zzctw     // Catch:{ Exception -> 0x017f }
            r14.<init>(r2)     // Catch:{ Exception -> 0x017f }
            r10 = r14
            goto L_0x0186
        L_0x017f:
            r14 = move-exception
            java.lang.String r2 = "Error parsing the JSON for Active View."
            com.google.android.gms.internal.zzahw.zzb(r2, r14)
        L_0x0185:
            r10 = r0
        L_0x0186:
            com.google.android.gms.internal.zzacj r14 = r13.zzcoc
            int r14 = r14.zzcum
            r2 = 2
            r3 = 1
            if (r14 != r2) goto L_0x01cd
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r3)
            com.google.android.gms.internal.zzacf r14 = r13.zzcjk
            com.google.android.gms.internal.zzkk r14 = r14.zzcrv
            android.os.Bundle r2 = r14.zzbhf
            if (r2 == 0) goto L_0x019d
            android.os.Bundle r14 = r14.zzbhf
            goto L_0x01a2
        L_0x019d:
            android.os.Bundle r14 = new android.os.Bundle
            r14.<init>()
        L_0x01a2:
            java.lang.Class<com.google.ads.mediation.admob.AdMobAdapter> r2 = com.google.ads.mediation.admob.AdMobAdapter.class
            java.lang.String r2 = r2.getName()
            android.os.Bundle r2 = r14.getBundle(r2)
            if (r2 == 0) goto L_0x01b9
            java.lang.Class<com.google.ads.mediation.admob.AdMobAdapter> r2 = com.google.ads.mediation.admob.AdMobAdapter.class
            java.lang.String r2 = r2.getName()
            android.os.Bundle r14 = r14.getBundle(r2)
            goto L_0x01c8
        L_0x01b9:
            android.os.Bundle r2 = new android.os.Bundle
            r2.<init>()
            java.lang.Class<com.google.ads.mediation.admob.AdMobAdapter> r5 = com.google.ads.mediation.admob.AdMobAdapter.class
            java.lang.String r5 = r5.getName()
            r14.putBundle(r5, r2)
            r14 = r2
        L_0x01c8:
            java.lang.String r2 = "render_test_label"
            r14.putBoolean(r2, r3)
        L_0x01cd:
            com.google.android.gms.internal.zzacj r14 = r13.zzcoc
            int r14 = r14.zzcum
            if (r14 != r3) goto L_0x01d7
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
        L_0x01d7:
            com.google.android.gms.internal.zzacj r14 = r13.zzcoc
            int r14 = r14.zzcum
            if (r14 != 0) goto L_0x01eb
            com.google.android.gms.internal.zzacf r14 = r13.zzcjk
            com.google.android.gms.internal.zzkk r14 = r14.zzcrv
            boolean r14 = com.google.android.gms.internal.zzakh.zzo(r14)
            java.lang.Boolean r14 = java.lang.Boolean.valueOf(r14)
            r12 = r14
            goto L_0x01ec
        L_0x01eb:
            r12 = r0
        L_0x01ec:
            com.google.android.gms.internal.zzahe r14 = new com.google.android.gms.internal.zzahe
            com.google.android.gms.internal.zzacf r1 = r13.zzcjk
            com.google.android.gms.internal.zzacj r2 = r13.zzcoc
            com.google.android.gms.internal.zzvq r3 = r13.zzcir
            r5 = -2
            long r8 = r2.zzcts
            com.google.android.gms.internal.zziu r11 = r13.zzcrg
            r0 = r14
            r0.<init>(r1, r2, r3, r4, r5, r6, r8, r10, r11, r12)
            com.google.android.gms.internal.zzabj r0 = r13.zzcre
            r0.zza(r14)
        L_0x0202:
            android.os.Handler r14 = com.google.android.gms.internal.zzaij.zzdfn
            java.lang.Runnable r0 = r13.zzcod
            r14.removeCallbacks(r0)
            return
        L_0x020a:
            r14 = move-exception
            int r0 = r14.getErrorCode()
            java.lang.String r14 = r14.getMessage()
            r13.zzc(r0, r14)
            goto L_0x0202
        L_0x0217:
            r0 = move-exception
            monitor-exit(r14)     // Catch:{ all -> 0x0217 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzabk.zza(com.google.android.gms.internal.zzacj):void");
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(zzjk zzjk) {
        zzjk.zzbeo.zzbdw = this.zzcrf.zzcrw.packageName;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(zzjk zzjk) {
        zzjk.zzbej = this.zzcrf.zzcsi;
    }

    public final void zzdo() {
        String string;
        zzahw.zzby("AdLoaderBackgroundTask started.");
        this.zzcod = new zzabs(this);
        zzaij.zzdfn.postDelayed(this.zzcod, ((Long) zzlc.zzio().zzd(zzoi.zzbrf)).longValue());
        long elapsedRealtime = zzbt.zzes().elapsedRealtime();
        if (!((Boolean) zzlc.zzio().zzd(zzoi.zzbrd)).booleanValue() || this.zzcrf.zzcrv.extras == null || (string = this.zzcrf.zzcrv.extras.getString("_ad")) == null) {
            zzamj zzamj = new zzamj();
            zzaid.zzb(new zzabt(this, zzamj));
            String zzx = zzbt.zzfh().zzx(this.mContext);
            String zzy = zzbt.zzfh().zzy(this.mContext);
            String zzz = zzbt.zzfh().zzz(this.mContext);
            zzbt.zzfh().zzg(this.mContext, zzz);
            zzacf zzacf = new zzacf(this.zzcrf, elapsedRealtime, zzx, zzy, zzz);
            this.zzcjk = zzacf;
            zzamj.zzj(zzacf);
            return;
        }
        zzacf zzacf2 = new zzacf(this.zzcrf, elapsedRealtime, (String) null, (String) null, (String) null);
        this.zzcjk = zzacf2;
        zza(zzads.zza(this.mContext, zzacf2, string));
    }
}
