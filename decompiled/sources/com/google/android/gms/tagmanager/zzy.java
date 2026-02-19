package com.google.android.gms.tagmanager;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.internal.zzbp;
import com.google.android.gms.internal.zzbs;
import com.google.android.gms.internal.zzdkf;
import com.google.android.gms.internal.zzdkg;
import com.google.android.gms.internal.zzdkl;
import com.google.android.gms.tagmanager.zzei;

public final class zzy extends BasePendingResult<ContainerHolder> {
    private final Context mContext;
    private final Looper zzalj;
    /* access modifiers changed from: private */
    public final zze zzata;
    private final String zzknc;
    /* access modifiers changed from: private */
    public long zzknh;
    private final TagManager zzkno;
    private final zzaf zzknr;
    /* access modifiers changed from: private */
    public final zzek zzkns;
    private final int zzknt;
    /* access modifiers changed from: private */
    public final zzai zzknu;
    private zzah zzknv;
    private zzdkg zzknw;
    /* access modifiers changed from: private */
    public volatile zzv zzknx;
    /* access modifiers changed from: private */
    public volatile boolean zzkny;
    /* access modifiers changed from: private */
    public zzbs zzknz;
    private String zzkoa;
    private zzag zzkob;
    private zzac zzkoc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private zzy(Context context, TagManager tagManager, Looper looper, String str, int i, zzah zzah, zzag zzag, zzdkg zzdkg, zze zze, zzek zzek, zzai zzai) {
        super(looper == null ? Looper.getMainLooper() : looper);
        this.mContext = context;
        this.zzkno = tagManager;
        this.zzalj = looper == null ? Looper.getMainLooper() : looper;
        this.zzknc = str;
        this.zzknt = i;
        this.zzknv = zzah;
        this.zzkob = zzag;
        this.zzknw = zzdkg;
        this.zzknr = new zzaf(this, (zzz) null);
        this.zzknz = new zzbs();
        this.zzata = zze;
        this.zzkns = zzek;
        this.zzknu = zzai;
        if (zzbft()) {
            zzlk(zzei.zzbhh().zzbhj());
        }
    }

    public zzy(Context context, TagManager tagManager, Looper looper, String str, int i, zzal zzal) {
        this(
                context,
                tagManager,
                looper,
                str,
                i,
                new zzey(context, str),
                new zzet(context, str, zzal),
                new zzdkg(context),
                com.google.android.gms.common.util.zzi.zzanq(),
                new zzdh(1, 5, 900000, 5000, "refreshing", com.google.android.gms.common.util.zzi.zzanq()),
                new zzai(context, str)
        );
        this.zzknw.zznm(zzal.zzbgb());
    }

    /* access modifiers changed from: private */
    public final synchronized void zza(zzbs zzbs) {
        if (this.zzknv != null) {
            zzdkf zzdkf = new zzdkf();
            zzdkf.zzldl = this.zzknh;
            zzdkf.zzyi = new zzbp();
            zzdkf.zzldm = zzbs;
            this.zzknv.zza(zzdkf);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zza(com.google.android.gms.internal.zzbs r10, long r11, boolean r13) {
        /*
            r9 = this;
            monitor-enter(r9)
            boolean r13 = r9.isReady()     // Catch:{ all -> 0x006c }
            if (r13 == 0) goto L_0x000d
            com.google.android.gms.tagmanager.zzv r13 = r9.zzknx     // Catch:{ all -> 0x006c }
            if (r13 != 0) goto L_0x000d
            monitor-exit(r9)
            return
        L_0x000d:
            r9.zzknz = r10     // Catch:{ all -> 0x006c }
            r9.zzknh = r11     // Catch:{ all -> 0x006c }
            com.google.android.gms.tagmanager.zzai r13 = r9.zzknu     // Catch:{ all -> 0x006c }
            long r0 = r13.zzbfw()     // Catch:{ all -> 0x006c }
            r2 = 0
            long r4 = r9.zzknh     // Catch:{ all -> 0x006c }
            long r4 = r4 + r0
            com.google.android.gms.common.util.zze r13 = r9.zzata     // Catch:{ all -> 0x006c }
            long r6 = r13.currentTimeMillis()     // Catch:{ all -> 0x006c }
            long r4 = r4 - r6
            long r0 = java.lang.Math.min(r0, r4)     // Catch:{ all -> 0x006c }
            long r0 = java.lang.Math.max(r2, r0)     // Catch:{ all -> 0x006c }
            r9.zzbg(r0)     // Catch:{ all -> 0x006c }
            com.google.android.gms.tagmanager.Container r13 = new com.google.android.gms.tagmanager.Container     // Catch:{ all -> 0x006c }
            android.content.Context r3 = r9.mContext     // Catch:{ all -> 0x006c }
            com.google.android.gms.tagmanager.TagManager r0 = r9.zzkno     // Catch:{ all -> 0x006c }
            com.google.android.gms.tagmanager.DataLayer r4 = r0.getDataLayer()     // Catch:{ all -> 0x006c }
            java.lang.String r5 = r9.zzknc     // Catch:{ all -> 0x006c }
            r2 = r13
            r6 = r11
            r8 = r10
            r2.<init>((android.content.Context) r3, (com.google.android.gms.tagmanager.DataLayer) r4, (java.lang.String) r5, (long) r6, (com.google.android.gms.internal.zzbs) r8)     // Catch:{ all -> 0x006c }
            com.google.android.gms.tagmanager.zzv r10 = r9.zzknx     // Catch:{ all -> 0x006c }
            if (r10 != 0) goto L_0x0052
            com.google.android.gms.tagmanager.zzv r10 = new com.google.android.gms.tagmanager.zzv     // Catch:{ all -> 0x006c }
            com.google.android.gms.tagmanager.TagManager r11 = r9.zzkno     // Catch:{ all -> 0x006c }
            android.os.Looper r12 = r9.zzalj     // Catch:{ all -> 0x006c }
            com.google.android.gms.tagmanager.zzaf r0 = r9.zzknr     // Catch:{ all -> 0x006c }
            r10.<init>(r11, r12, r13, r0)     // Catch:{ all -> 0x006c }
            r9.zzknx = r10     // Catch:{ all -> 0x006c }
            goto L_0x0057
        L_0x0052:
            com.google.android.gms.tagmanager.zzv r10 = r9.zzknx     // Catch:{ all -> 0x006c }
            r10.zza(r13)     // Catch:{ all -> 0x006c }
        L_0x0057:
            boolean r10 = r9.isReady()     // Catch:{ all -> 0x006c }
            if (r10 != 0) goto L_0x006a
            com.google.android.gms.tagmanager.zzac r10 = r9.zzkoc     // Catch:{ all -> 0x006c }
            boolean r10 = r10.zzb(r13)     // Catch:{ all -> 0x006c }
            if (r10 == 0) goto L_0x006a
            com.google.android.gms.tagmanager.zzv r10 = r9.zzknx     // Catch:{ all -> 0x006c }
            r9.setResult(r10)     // Catch:{ all -> 0x006c }
        L_0x006a:
            monitor-exit(r9)
            return
        L_0x006c:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzy.zza(com.google.android.gms.internal.zzbs, long, boolean):void");
    }

    /* access modifiers changed from: private */
    public final boolean zzbft() {
        zzei zzbhh = zzei.zzbhh();
        return (zzbhh.zzbhi() == zzei.zza.CONTAINER || zzbhh.zzbhi() == zzei.zza.CONTAINER_DEBUG) && this.zzknc.equals(zzbhh.getContainerId());
    }

    /* access modifiers changed from: private */
    public final synchronized void zzbg(long j) {
        zzag zzag = this.zzkob;
        if (zzag == null) {
            zzdj.zzcz("Refresh requested, but no network load scheduler.");
        } else {
            zzag.zza(j, this.zzknz.zzyj);
        }
    }

    private final void zzby(boolean z) {
        this.zzknv.zza((zzdi<zzdkf>) new zzad(this, (zzz) null));
        this.zzkob.zza(new zzae(this, (zzz) null));
        zzdkl zzey = this.zzknv.zzey(this.zzknt);
        if (zzey != null) {
            TagManager tagManager = this.zzkno;
            this.zzknx = new zzv(tagManager, this.zzalj, new Container(this.mContext, tagManager.getDataLayer(), this.zzknc, 0, zzey), this.zzknr);
        }
        this.zzkoc = new zzab(this, z);
        if (zzbft()) {
            this.zzkob.zza(0, "");
        } else {
            this.zzknv.zzbfv();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzau */
    public final ContainerHolder zzb(Status status) {
        if (this.zzknx != null) {
            return this.zzknx;
        }
        if (status == Status.zzftt) {
            zzdj.e("timer expired: setting result to failure");
        }
        return new zzv(status);
    }

    /* access modifiers changed from: package-private */
    public final synchronized String zzbfn() {
        return this.zzkoa;
    }

    public final void zzbfq() {
        zzdkl zzey = this.zzknv.zzey(this.zzknt);
        if (zzey != null) {
            setResult(new zzv(this.zzkno, this.zzalj, new Container(this.mContext, this.zzkno.getDataLayer(), this.zzknc, 0, zzey), new zzaa(this)));
        } else {
            zzdj.e("Default was requested, but no default container was found");
            setResult(zzb(new Status(10, "Default was requested, but no default container was found", (PendingIntent) null)));
        }
        this.zzkob = null;
        this.zzknv = null;
    }

    public final void zzbfr() {
        zzby(false);
    }

    public final void zzbfs() {
        zzby(true);
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zzlk(String str) {
        this.zzkoa = str;
        zzag zzag = this.zzkob;
        if (zzag != null) {
            zzag.zzll(str);
        }
    }
}
