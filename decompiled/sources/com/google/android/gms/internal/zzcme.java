package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.stats.zza;
import com.google.android.gms.common.util.zze;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public final class zzcme extends zzcli {
    /* access modifiers changed from: private */
    public final zzcms zzjrb;
    /* access modifiers changed from: private */
    public zzcjb zzjrc;
    private volatile Boolean zzjrd;
    private final zzcip zzjre;
    private final zzcni zzjrf;
    private final List<Runnable> zzjrg = new ArrayList();
    private final zzcip zzjrh;

    protected zzcme(zzckj zzckj) {
        super(zzckj);
        this.zzjrf = new zzcni(zzckj.zzxx());
        this.zzjrb = new zzcms(this);
        this.zzjre = new zzcmf(this, zzckj);
        this.zzjrh = new zzcmk(this, zzckj);
    }

    /* access modifiers changed from: private */
    public final void onServiceDisconnected(ComponentName componentName) {
        zzwj();
        if (this.zzjrc != null) {
            this.zzjrc = null;
            zzayp().zzbba().zzj("Disconnected from device MeasurementService", componentName);
            zzwj();
            zzzh();
        }
    }

    /* access modifiers changed from: private */
    public final void zzbcl() {
        zzwj();
        zzayp().zzbba().zzj("Processing queued up service tasks", Integer.valueOf(this.zzjrg.size()));
        for (Runnable run : this.zzjrg) {
            try {
                run.run();
            } catch (Throwable th) {
                zzayp().zzbau().zzj("Task exception while flushing queue", th);
            }
        }
        this.zzjrg.clear();
        this.zzjrh.cancel();
    }

    private final zzcif zzbw(boolean z) {
        return zzaye().zzjo(z ? zzayp().zzbbc() : null);
    }

    private final void zzk(Runnable runnable) throws IllegalStateException {
        zzwj();
        if (isConnected()) {
            runnable.run();
        } else if (((long) this.zzjrg.size()) >= 1000) {
            zzayp().zzbau().log("Discarding data. Max runnable queue size reached");
        } else {
            this.zzjrg.add(runnable);
            this.zzjrh.zzs(60000);
            zzzh();
        }
    }

    /* access modifiers changed from: private */
    public final void zzyw() {
        zzwj();
        this.zzjrf.start();
        this.zzjre.zzs(zzciz.zzjjt.get().longValue());
    }

    /* access modifiers changed from: private */
    public final void zzyx() {
        zzwj();
        if (isConnected()) {
            zzayp().zzbba().log("Inactivity, disconnecting from the service");
            disconnect();
        }
    }

    public final void disconnect() {
        zzwj();
        zzyk();
        try {
            zza.zzanm();
            getContext().unbindService(this.zzjrb);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
        this.zzjrc = null;
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final boolean isConnected() {
        zzwj();
        zzyk();
        return this.zzjrc != null;
    }

    /* access modifiers changed from: protected */
    public final void resetAnalyticsData() {
        zzwj();
        zzyk();
        zzcif zzbw = zzbw(false);
        zzayi().resetAnalyticsData();
        zzk(new zzcmg(this, zzbw));
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcjb zzcjb) {
        zzwj();
        zzbq.checkNotNull(zzcjb);
        this.zzjrc = zzcjb;
        zzyw();
        zzbcl();
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzcjb zzcjb, zzbgl zzbgl, zzcif zzcif) {
        int i;
        zzcjl zzbau;
        String str;
        zzwj();
        zzyk();
        int i2 = 0;
        int i3 = 100;
        while (i2 < 1001 && i3 == 100) {
            ArrayList arrayList = new ArrayList();
            List<zzbgl> zzep = zzayi().zzep(100);
            if (zzep != null) {
                arrayList.addAll(zzep);
                i = zzep.size();
            } else {
                i = 0;
            }
            if (zzbgl != null && i < 100) {
                arrayList.add(zzbgl);
            }
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i4 = 0;
            while (i4 < size) {
                Object obj = arrayList2.get(i4);
                i4++;
                zzbgl zzbgl2 = (zzbgl) obj;
                if (zzbgl2 instanceof zzcix) {
                    try {
                        zzcjb.zza((zzcix) zzbgl2, zzcif);
                    } catch (RemoteException e) {
                        zzayp().zzbau().zzj("Failed to send event to the service", e);
                    }
                } else if (zzbgl2 instanceof zzcnl) {
                    try {
                        zzcjb.zza((zzcnl) zzbgl2, zzcif);
                    } catch (RemoteException e2) {
                        zzayp().zzbau().zzj("Failed to send attribute to the service", e2);
                    }
                } else if (zzbgl2 instanceof zzcii) {
                    try {
                        zzcjb.zza((zzcii) zzbgl2, zzcif);
                    } catch (RemoteException e3) {
                        zzayp().zzbau().zzj("Failed to send conditional property to the service", e3);
                    }
                } else {
                    zzayp().zzbau().log("Discarding data. Unrecognized parcel type.");
                }
            }
            i2++;
            i3 = i;
        }
        return;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzclz zzclz) {
        zzwj();
        zzyk();
        zzk(new zzcmj(this, zzclz));
    }

    public final void zza(AtomicReference<String> atomicReference) {
        zzwj();
        zzyk();
        zzk(new zzcmh(this, atomicReference, zzbw(false)));
    }

    /* access modifiers changed from: protected */
    public final void zza(AtomicReference<List<zzcii>> atomicReference, String str, String str2, String str3) {
        zzwj();
        zzyk();
        zzk(new zzcmo(this, atomicReference, str, str2, str3, zzbw(false)));
    }

    /* access modifiers changed from: protected */
    public final void zza(AtomicReference<List<zzcnl>> atomicReference, String str, String str2, String str3, boolean z) {
        zzwj();
        zzyk();
        zzk(new zzcmp(this, atomicReference, str, str2, str3, z, zzbw(false)));
    }

    /* access modifiers changed from: protected */
    public final void zza(AtomicReference<List<zzcnl>> atomicReference, boolean z) {
        zzwj();
        zzyk();
        zzk(new zzcmr(this, atomicReference, zzbw(false), z));
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

    /* access modifiers changed from: protected */
    public final boolean zzazq() {
        return false;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzcnl zzcnl) {
        zzwj();
        zzyk();
        zzk(new zzcmq(this, zzayi().zza(zzcnl), zzcnl, zzbw(true)));
    }

    /* access modifiers changed from: protected */
    public final void zzbci() {
        zzwj();
        zzyk();
        zzk(new zzcml(this, zzbw(true)));
    }

    /* access modifiers changed from: protected */
    public final void zzbcj() {
        zzwj();
        zzyk();
        zzk(new zzcmi(this, zzbw(true)));
    }

    /* access modifiers changed from: package-private */
    public final Boolean zzbck() {
        return this.zzjrd;
    }

    /* access modifiers changed from: protected */
    public final void zzc(zzcix zzcix, String str) {
        zzbq.checkNotNull(zzcix);
        zzwj();
        zzyk();
        zzk(new zzcmm(this, true, zzayi().zza(zzcix), zzcix, zzbw(true), str));
    }

    /* access modifiers changed from: protected */
    public final void zzf(zzcii zzcii) {
        zzbq.checkNotNull(zzcii);
        zzwj();
        zzyk();
        zzk(new zzcmn(this, true, zzayi().zzc(zzcii), new zzcii(zzcii), zzbw(true), zzcii));
    }

    public final /* bridge */ /* synthetic */ void zzwj() {
        super.zzwj();
    }

    public final /* bridge */ /* synthetic */ zze zzxx() {
        return super.zzxx();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00e1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzzh() {
        /*
            r6 = this;
            r6.zzwj()
            r6.zzyk()
            boolean r0 = r6.isConnected()
            if (r0 == 0) goto L_0x000d
            return
        L_0x000d:
            java.lang.Boolean r0 = r6.zzjrd
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x00ee
            r6.zzwj()
            r6.zzyk()
            com.google.android.gms.internal.zzcju r0 = r6.zzayq()
            java.lang.Boolean r0 = r0.zzbbg()
            if (r0 == 0) goto L_0x002c
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x002c
            r0 = 1
            goto L_0x00e8
        L_0x002c:
            com.google.android.gms.internal.zzcje r0 = r6.zzaye()
            int r0 = r0.zzbas()
            if (r0 != r2) goto L_0x003a
        L_0x0036:
            r0 = 1
        L_0x0037:
            r3 = 1
            goto L_0x00df
        L_0x003a:
            com.google.android.gms.internal.zzcjj r0 = r6.zzayp()
            com.google.android.gms.internal.zzcjl r0 = r0.zzbba()
            java.lang.String r3 = "Checking service availability"
            r0.log(r3)
            com.google.android.gms.internal.zzcno r0 = r6.zzayl()
            com.google.android.gms.common.zzf r3 = com.google.android.gms.common.zzf.zzahf()
            android.content.Context r0 = r0.getContext()
            int r0 = r3.isGooglePlayServicesAvailable(r0)
            if (r0 == 0) goto L_0x00d0
            if (r0 == r2) goto L_0x00c0
            r3 = 2
            if (r0 == r3) goto L_0x00a1
            r3 = 3
            if (r0 == r3) goto L_0x0091
            r3 = 9
            if (r0 == r3) goto L_0x0086
            r3 = 18
            if (r0 == r3) goto L_0x007b
            com.google.android.gms.internal.zzcjj r3 = r6.zzayp()
            com.google.android.gms.internal.zzcjl r3 = r3.zzbaw()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r4 = "Unexpected service status"
            r3.zzj(r4, r0)
            goto L_0x009e
        L_0x007b:
            com.google.android.gms.internal.zzcjj r0 = r6.zzayp()
            com.google.android.gms.internal.zzcjl r0 = r0.zzbaw()
            java.lang.String r3 = "Service updating"
            goto L_0x00da
        L_0x0086:
            com.google.android.gms.internal.zzcjj r0 = r6.zzayp()
            com.google.android.gms.internal.zzcjl r0 = r0.zzbaw()
            java.lang.String r3 = "Service invalid"
            goto L_0x009b
        L_0x0091:
            com.google.android.gms.internal.zzcjj r0 = r6.zzayp()
            com.google.android.gms.internal.zzcjl r0 = r0.zzbaw()
            java.lang.String r3 = "Service disabled"
        L_0x009b:
            r0.log(r3)
        L_0x009e:
            r0 = 0
        L_0x009f:
            r3 = 0
            goto L_0x00df
        L_0x00a1:
            com.google.android.gms.internal.zzcjj r0 = r6.zzayp()
            com.google.android.gms.internal.zzcjl r0 = r0.zzbaz()
            java.lang.String r3 = "Service container out of date"
            r0.log(r3)
            com.google.android.gms.internal.zzcju r0 = r6.zzayq()
            java.lang.Boolean r0 = r0.zzbbg()
            if (r0 == 0) goto L_0x00be
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x009e
        L_0x00be:
            r0 = 1
            goto L_0x009f
        L_0x00c0:
            com.google.android.gms.internal.zzcjj r0 = r6.zzayp()
            com.google.android.gms.internal.zzcjl r0 = r0.zzbba()
            java.lang.String r3 = "Service missing"
            r0.log(r3)
            r0 = 0
            goto L_0x0037
        L_0x00d0:
            com.google.android.gms.internal.zzcjj r0 = r6.zzayp()
            com.google.android.gms.internal.zzcjl r0 = r0.zzbba()
            java.lang.String r3 = "Service available"
        L_0x00da:
            r0.log(r3)
            goto L_0x0036
        L_0x00df:
            if (r3 == 0) goto L_0x00e8
            com.google.android.gms.internal.zzcju r3 = r6.zzayq()
            r3.zzbr(r0)
        L_0x00e8:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r6.zzjrd = r0
        L_0x00ee:
            java.lang.Boolean r0 = r6.zzjrd
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x00fc
            com.google.android.gms.internal.zzcms r0 = r6.zzjrb
            r0.zzbcm()
            return
        L_0x00fc:
            android.content.Context r0 = r6.getContext()
            android.content.pm.PackageManager r0 = r0.getPackageManager()
            android.content.Intent r3 = new android.content.Intent
            r3.<init>()
            android.content.Context r4 = r6.getContext()
            java.lang.String r5 = "com.google.android.gms.measurement.AppMeasurementService"
            android.content.Intent r3 = r3.setClassName(r4, r5)
            r4 = 65536(0x10000, float:9.18355E-41)
            java.util.List r0 = r0.queryIntentServices(r3, r4)
            if (r0 == 0) goto L_0x0122
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x0122
            r1 = 1
        L_0x0122:
            if (r1 == 0) goto L_0x013d
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "com.google.android.gms.measurement.START"
            r0.<init>(r1)
            android.content.ComponentName r1 = new android.content.ComponentName
            android.content.Context r2 = r6.getContext()
            r1.<init>(r2, r5)
            r0.setComponent(r1)
            com.google.android.gms.internal.zzcms r1 = r6.zzjrb
            r1.zzm(r0)
            return
        L_0x013d:
            com.google.android.gms.internal.zzcjj r0 = r6.zzayp()
            com.google.android.gms.internal.zzcjl r0 = r0.zzbau()
            java.lang.String r1 = "Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest"
            r0.log(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcme.zzzh():void");
    }
}
