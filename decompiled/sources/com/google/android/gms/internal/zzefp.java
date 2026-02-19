package com.google.android.gms.internal;

import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

final class zzefp {
    private static long zznca;
    /* access modifiers changed from: private */
    public final ScheduledExecutorService zzmxn;
    /* access modifiers changed from: private */
    public final zzemm zzmxz;
    /* access modifiers changed from: private */
    public zzeft zzncb;
    /* access modifiers changed from: private */
    public boolean zzncc = false;
    private boolean zzncd = false;
    private long zznce = 0;
    private zzegc zzncf;
    private zzefs zzncg;
    private ScheduledFuture<?> zznch;
    /* access modifiers changed from: private */
    public ScheduledFuture<?> zznci;
    private final zzees zzncj;

    public zzefp(zzees zzees, zzeeu zzeeu, String str, zzefs zzefs, String str2) {
        this.zzncj = zzees;
        this.zzmxn = zzees.zzbwm();
        this.zzncg = zzefs;
        long j = zznca;
        zznca = 1 + j;
        zzemn zzbwk = zzees.zzbwk();
        StringBuilder sb = new StringBuilder(23);
        sb.append("ws_");
        sb.append(j);
        this.zzmxz = new zzemm(zzbwk, "WebSocket", sb.toString());
        str = str == null ? zzeeu.getHost() : str;
        boolean isSecure = zzeeu.isSecure();
        String namespace = zzeeu.getNamespace();
        String str3 = isSecure ? "wss" : "ws";
        StringBuilder sb2 = new StringBuilder(str3.length() + 15 + String.valueOf(str).length() + String.valueOf(namespace).length());
        sb2.append(str3);
        sb2.append("://");
        sb2.append(str);
        sb2.append("/.ws?ns=");
        sb2.append(namespace);
        sb2.append("&v=5");
        String sb3 = sb2.toString();
        if (str2 != null) {
            String valueOf = String.valueOf(sb3);
            StringBuilder sb4 = new StringBuilder(String.valueOf(valueOf).length() + 4 + String.valueOf(str2).length());
            sb4.append(valueOf);
            sb4.append("&ls=");
            sb4.append(str2);
            sb3 = sb4.toString();
        }
        URI create = URI.create(sb3);
        HashMap hashMap = new HashMap();
        hashMap.put("User-Agent", zzees.zzbwo());
        this.zzncb = new zzefu(this, new zzeoe(zzees, create, (String) null, hashMap), (zzefq) null);
    }

    private final void shutdown() {
        this.zzncd = true;
        this.zzncg.zzcr(this.zzncc);
    }

    /* access modifiers changed from: private */
    public final void zzbxm() {
        if (!this.zzncd) {
            ScheduledFuture<?> scheduledFuture = this.zznch;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
                if (this.zzmxz.zzcbu()) {
                    zzemm zzemm = this.zzmxz;
                    long delay = this.zznch.getDelay(TimeUnit.MILLISECONDS);
                    StringBuilder sb = new StringBuilder(48);
                    sb.append("Reset keepAlive. Remaining: ");
                    sb.append(delay);
                    zzemm.zzb(sb.toString(), (Throwable) null, new Object[0]);
                }
            } else if (this.zzmxz.zzcbu()) {
                this.zzmxz.zzb("Reset keepAlive", (Throwable) null, new Object[0]);
            }
            this.zznch = this.zzmxn.schedule(new zzefr(this), 45000, TimeUnit.MILLISECONDS);
        }
    }

    /* access modifiers changed from: private */
    public final void zzbxn() {
        if (!this.zzncd) {
            if (this.zzmxz.zzcbu()) {
                this.zzmxz.zzb("closing itself", (Throwable) null, new Object[0]);
            }
            shutdown();
        }
        this.zzncb = null;
        ScheduledFuture<?> scheduledFuture = this.zznch;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
    }

    /* access modifiers changed from: private */
    public final void zzbxo() {
        if (!this.zzncc && !this.zzncd) {
            if (this.zzmxz.zzcbu()) {
                this.zzmxz.zzb("timed out on connect", (Throwable) null, new Object[0]);
            }
            this.zzncb.close();
        }
    }

    private final void zzhh(int i) {
        this.zznce = (long) i;
        this.zzncf = new zzegc();
        if (this.zzmxz.zzcbu()) {
            zzemm zzemm = this.zzmxz;
            long j = this.zznce;
            StringBuilder sb = new StringBuilder(41);
            sb.append("HandleNewFrameCount: ");
            sb.append(j);
            zzemm.zzb(sb.toString(), (Throwable) null, new Object[0]);
        }
    }

    private final void zzpv(String str) {
        this.zzncf.zzpz(str);
        long j = this.zznce - 1;
        this.zznce = j;
        if (j == 0) {
            try {
                this.zzncf.zzbxu();
                Map<String, Object> zzqh = zzeor.zzqh(this.zzncf.toString());
                this.zzncf = null;
                if (this.zzmxz.zzcbu()) {
                    zzemm zzemm = this.zzmxz;
                    String valueOf = String.valueOf(zzqh);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 36);
                    sb.append("handleIncomingFrame complete frame: ");
                    sb.append(valueOf);
                    zzemm.zzb(sb.toString(), (Throwable) null, new Object[0]);
                }
                this.zzncg.zzai(zzqh);
            } catch (IOException e) {
                zzemm zzemm2 = this.zzmxz;
                String valueOf2 = String.valueOf(this.zzncf.toString());
                zzemm2.zze(valueOf2.length() != 0 ? "Error parsing frame: ".concat(valueOf2) : new String("Error parsing frame: "), e);
                close();
                shutdown();
            } catch (ClassCastException e2) {
                zzemm zzemm3 = this.zzmxz;
                String valueOf3 = String.valueOf(this.zzncf.toString());
                zzemm3.zze(valueOf3.length() != 0 ? "Error parsing frame (cast error): ".concat(valueOf3) : new String("Error parsing frame (cast error): "), e2);
                close();
                shutdown();
            }
        }
    }

    private final String zzpw(String str) {
        if (str.length() <= 6) {
            try {
                int parseInt = Integer.parseInt(str);
                if (parseInt <= 0) {
                    return null;
                }
                zzhh(parseInt);
                return null;
            } catch (NumberFormatException unused) {
            }
        }
        zzhh(1);
        return str;
    }

    /* access modifiers changed from: private */
    public final void zzpx(String str) {
        if (!this.zzncd) {
            zzbxm();
            if (this.zzncf != null) {
                zzpv(str);
                return;
            }
            String zzpw = zzpw(str);
            if (zzpw != null) {
                zzpv(zzpw);
            }
        }
    }

    public final void close() {
        if (this.zzmxz.zzcbu()) {
            this.zzmxz.zzb("websocket is being closed", (Throwable) null, new Object[0]);
        }
        this.zzncd = true;
        this.zzncb.close();
        ScheduledFuture<?> scheduledFuture = this.zznci;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
        ScheduledFuture<?> scheduledFuture2 = this.zznch;
        if (scheduledFuture2 != null) {
            scheduledFuture2.cancel(true);
        }
    }

    public final void open() {
        this.zzncb.connect();
        this.zznci = this.zzmxn.schedule(new zzefq(this), NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS, TimeUnit.MILLISECONDS);
    }

    /* JADX WARNING: type inference failed for: r0v8, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void send(java.util.Map<java.lang.String, java.lang.Object> r8) {
        /*
            r7 = this;
            r7.zzbxm()
            java.lang.String r0 = com.google.android.gms.internal.zzeor.zzbx(r8)     // Catch:{ IOException -> 0x0067 }
            int r1 = r0.length()     // Catch:{ IOException -> 0x0067 }
            r2 = 16384(0x4000, float:2.2959E-41)
            r3 = 0
            r4 = 1
            if (r1 > r2) goto L_0x0016
            java.lang.String[] r1 = new java.lang.String[r4]     // Catch:{ IOException -> 0x0067 }
            r1[r3] = r0     // Catch:{ IOException -> 0x0067 }
            goto L_0x0042
        L_0x0016:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ IOException -> 0x0067 }
            r1.<init>()     // Catch:{ IOException -> 0x0067 }
            r2 = 0
        L_0x001c:
            int r5 = r0.length()     // Catch:{ IOException -> 0x0067 }
            if (r2 >= r5) goto L_0x0035
            int r5 = r2 + 16384
            int r6 = r0.length()     // Catch:{ IOException -> 0x0067 }
            int r6 = java.lang.Math.min(r5, r6)     // Catch:{ IOException -> 0x0067 }
            java.lang.String r2 = r0.substring(r2, r6)     // Catch:{ IOException -> 0x0067 }
            r1.add(r2)     // Catch:{ IOException -> 0x0067 }
            r2 = r5
            goto L_0x001c
        L_0x0035:
            int r0 = r1.size()     // Catch:{ IOException -> 0x0067 }
            java.lang.String[] r0 = new java.lang.String[r0]     // Catch:{ IOException -> 0x0067 }
            java.lang.Object[] r0 = r1.toArray(r0)     // Catch:{ IOException -> 0x0067 }
            r1 = r0
            java.lang.String[] r1 = (java.lang.String[]) r1     // Catch:{ IOException -> 0x0067 }
        L_0x0042:
            int r0 = r1.length     // Catch:{ IOException -> 0x0067 }
            if (r0 <= r4) goto L_0x0059
            com.google.android.gms.internal.zzeft r0 = r7.zzncb     // Catch:{ IOException -> 0x0067 }
            int r2 = r1.length     // Catch:{ IOException -> 0x0067 }
            r4 = 11
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0067 }
            r5.<init>(r4)     // Catch:{ IOException -> 0x0067 }
            r5.append(r2)     // Catch:{ IOException -> 0x0067 }
            java.lang.String r2 = r5.toString()     // Catch:{ IOException -> 0x0067 }
            r0.zzpy(r2)     // Catch:{ IOException -> 0x0067 }
        L_0x0059:
            int r0 = r1.length     // Catch:{ IOException -> 0x0067 }
            if (r3 >= r0) goto L_0x0066
            com.google.android.gms.internal.zzeft r0 = r7.zzncb     // Catch:{ IOException -> 0x0067 }
            r2 = r1[r3]     // Catch:{ IOException -> 0x0067 }
            r0.zzpy(r2)     // Catch:{ IOException -> 0x0067 }
            int r3 = r3 + 1
            goto L_0x0059
        L_0x0066:
            return
        L_0x0067:
            r0 = move-exception
            com.google.android.gms.internal.zzemm r1 = r7.zzmxz
            java.lang.String r2 = "Failed to serialize message: "
            java.lang.String r8 = r8.toString()
            java.lang.String r8 = java.lang.String.valueOf(r8)
            int r3 = r8.length()
            if (r3 == 0) goto L_0x007f
            java.lang.String r8 = r2.concat(r8)
            goto L_0x0084
        L_0x007f:
            java.lang.String r8 = new java.lang.String
            r8.<init>(r2)
        L_0x0084:
            r1.zze(r8, r0)
            r7.shutdown()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzefp.send(java.util.Map):void");
    }
}
