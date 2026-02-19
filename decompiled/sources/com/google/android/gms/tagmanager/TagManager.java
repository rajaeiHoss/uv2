package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.tagmanager.DataLayer;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TagManager {
    private static TagManager zzkuc;
    private final Context mContext;
    private final DataLayer zzknd;
    private final zzal zzksc;
    private final zza zzktz;
    private final zzfn zzkua;
    private final ConcurrentMap<String, zzv> zzkub = new ConcurrentHashMap();

    public interface zza {
        zzy zza(Context context, TagManager tagManager, Looper looper, String str, int i, zzal zzal);
    }

    private TagManager(Context context, zza zza2, DataLayer dataLayer, zzfn zzfn) {
        Objects.requireNonNull(context, "context cannot be null");
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.zzkua = zzfn;
        this.zzktz = zza2;
        this.zzknd = dataLayer;
        dataLayer.zza((DataLayer.zzb) new zzgb(this));
        dataLayer.zza((DataLayer.zzb) new zzg(applicationContext));
        this.zzksc = new zzal();
        applicationContext.registerComponentCallbacks(new zzgd(this));
        com.google.android.gms.tagmanager.zza.zzeg(applicationContext);
    }

    public static TagManager getInstance(Context context) {
        TagManager tagManager;
        synchronized (TagManager.class) {
            if (zzkuc == null) {
                if (context != null) {
                    zzkuc = new TagManager(context, new zzgc(), new DataLayer(new zzat(context)), zzfo.zzbhz());
                } else {
                    zzdj.e("TagManager.getInstance requires non-null context.");
                    throw new NullPointerException();
                }
            }
            tagManager = zzkuc;
        }
        return tagManager;
    }

    /* access modifiers changed from: private */
    public final void zzme(String str) {
        for (zzv zzlj : this.zzkub.values()) {
            zzlj.zzlj(str);
        }
    }

    public void dispatch() {
        this.zzkua.dispatch();
    }

    public DataLayer getDataLayer() {
        return this.zzknd;
    }

    public PendingResult<ContainerHolder> loadContainerDefaultOnly(String str, int i) {
        zzy zza2 = this.zzktz.zza(this.mContext, this, (Looper) null, str, i, this.zzksc);
        zza2.zzbfq();
        return zza2;
    }

    public PendingResult<ContainerHolder> loadContainerDefaultOnly(String str, int i, Handler handler) {
        zzy zza2 = this.zzktz.zza(this.mContext, this, handler.getLooper(), str, i, this.zzksc);
        zza2.zzbfq();
        return zza2;
    }

    public PendingResult<ContainerHolder> loadContainerPreferFresh(String str, int i) {
        zzy zza2 = this.zzktz.zza(this.mContext, this, (Looper) null, str, i, this.zzksc);
        zza2.zzbfs();
        return zza2;
    }

    public PendingResult<ContainerHolder> loadContainerPreferFresh(String str, int i, Handler handler) {
        zzy zza2 = this.zzktz.zza(this.mContext, this, handler.getLooper(), str, i, this.zzksc);
        zza2.zzbfs();
        return zza2;
    }

    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String str, int i) {
        zzy zza2 = this.zzktz.zza(this.mContext, this, (Looper) null, str, i, this.zzksc);
        zza2.zzbfr();
        return zza2;
    }

    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String str, int i, Handler handler) {
        zzy zza2 = this.zzktz.zza(this.mContext, this, handler.getLooper(), str, i, this.zzksc);
        zza2.zzbfr();
        return zza2;
    }

    public void setVerboseLoggingEnabled(boolean z) {
        zzdj.setLogLevel(z ? 2 : 5);
    }

    public final int zza(zzv zzv) {
        this.zzkub.put(zzv.getContainerId(), zzv);
        return this.zzkub.size();
    }

    public final boolean zzb(zzv zzv) {
        return this.zzkub.remove(zzv.getContainerId()) != null;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0070, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean zzq(android.net.Uri r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            com.google.android.gms.tagmanager.zzei r0 = com.google.android.gms.tagmanager.zzei.zzbhh()     // Catch:{ all -> 0x0074 }
            boolean r7 = r0.zzq(r7)     // Catch:{ all -> 0x0074 }
            if (r7 == 0) goto L_0x0071
            java.lang.String r7 = r0.getContainerId()     // Catch:{ all -> 0x0074 }
            int[] r1 = com.google.android.gms.tagmanager.zzge.zzkue     // Catch:{ all -> 0x0074 }
            com.google.android.gms.tagmanager.zzei$zza r2 = r0.zzbhi()     // Catch:{ all -> 0x0074 }
            int r2 = r2.ordinal()     // Catch:{ all -> 0x0074 }
            r1 = r1[r2]     // Catch:{ all -> 0x0074 }
            r2 = 0
            r3 = 1
            if (r1 == r3) goto L_0x005f
            r4 = 2
            if (r1 == r4) goto L_0x0026
            r4 = 3
            if (r1 == r4) goto L_0x0026
            goto L_0x006f
        L_0x0026:
            java.util.concurrent.ConcurrentMap<java.lang.String, com.google.android.gms.tagmanager.zzv> r1 = r6.zzkub     // Catch:{ all -> 0x0074 }
            java.util.Set r1 = r1.keySet()     // Catch:{ all -> 0x0074 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0074 }
        L_0x0030:
            boolean r4 = r1.hasNext()     // Catch:{ all -> 0x0074 }
            if (r4 == 0) goto L_0x006f
            java.lang.Object r4 = r1.next()     // Catch:{ all -> 0x0074 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x0074 }
            java.util.concurrent.ConcurrentMap<java.lang.String, com.google.android.gms.tagmanager.zzv> r5 = r6.zzkub     // Catch:{ all -> 0x0074 }
            java.lang.Object r5 = r5.get(r4)     // Catch:{ all -> 0x0074 }
            com.google.android.gms.tagmanager.zzv r5 = (com.google.android.gms.tagmanager.zzv) r5     // Catch:{ all -> 0x0074 }
            boolean r4 = r4.equals(r7)     // Catch:{ all -> 0x0074 }
            if (r4 == 0) goto L_0x0055
            java.lang.String r4 = r0.zzbhj()     // Catch:{ all -> 0x0074 }
            r5.zzlk(r4)     // Catch:{ all -> 0x0074 }
        L_0x0051:
            r5.refresh()     // Catch:{ all -> 0x0074 }
            goto L_0x0030
        L_0x0055:
            java.lang.String r4 = r5.zzbfn()     // Catch:{ all -> 0x0074 }
            if (r4 == 0) goto L_0x0030
            r5.zzlk(r2)     // Catch:{ all -> 0x0074 }
            goto L_0x0051
        L_0x005f:
            java.util.concurrent.ConcurrentMap<java.lang.String, com.google.android.gms.tagmanager.zzv> r0 = r6.zzkub     // Catch:{ all -> 0x0074 }
            java.lang.Object r7 = r0.get(r7)     // Catch:{ all -> 0x0074 }
            com.google.android.gms.tagmanager.zzv r7 = (com.google.android.gms.tagmanager.zzv) r7     // Catch:{ all -> 0x0074 }
            if (r7 == 0) goto L_0x006f
            r7.zzlk(r2)     // Catch:{ all -> 0x0074 }
            r7.refresh()     // Catch:{ all -> 0x0074 }
        L_0x006f:
            monitor-exit(r6)
            return r3
        L_0x0071:
            r7 = 0
            monitor-exit(r6)
            return r7
        L_0x0074:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.TagManager.zzq(android.net.Uri):boolean");
    }
}
