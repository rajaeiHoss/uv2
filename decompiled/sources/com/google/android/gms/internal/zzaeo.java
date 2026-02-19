package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.ads.internal.zzbu;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.zzn;
import java.util.HashMap;
import java.util.Map;

@zzabh
public final class zzaeo {
    private static final zzwe zzczl = new zzwe();
    private final zzwf zzczm;
    private final zzbu zzczn;
    private final Map<String, zzafy> zzczo = new HashMap();
    private final zzafs zzczp;
    private final zzzn zzczq;

    public zzaeo(zzbu zzbu, zzwf zzwf, zzafs zzafs, zzzn zzzn) {
        this.zzczn = zzbu;
        this.zzczm = zzwf;
        this.zzczp = zzafs;
        this.zzczq = zzzn;
    }

    public static boolean zza(zzahd zzahd, zzahd zzahd2) {
        return true;
    }

    public final void destroy() {
        zzbq.zzgn("destroy must be called on the main UI thread.");
        for (String next : this.zzczo.keySet()) {
            try {
                zzafy zzafy = this.zzczo.get(next);
                if (!(zzafy == null || zzafy.zzpc() == null)) {
                    zzafy.zzpc().destroy();
                }
            } catch (RemoteException unused) {
                String valueOf = String.valueOf(next);
                zzahw.zzcz(valueOf.length() != 0 ? "Fail to destroy adapter: ".concat(valueOf) : new String("Fail to destroy adapter: "));
            }
        }
    }

    public final void onContextChanged(Context context) {
        for (zzafy zzpc : this.zzczo.values()) {
            try {
                zzpc.zzpc().zzg(zzn.zzz(context));
            } catch (RemoteException e) {
                zzahw.zzb("Unable to call Adapter.onContextChanged.", e);
            }
        }
    }

    public final void pause() {
        zzbq.zzgn("pause must be called on the main UI thread.");
        for (String next : this.zzczo.keySet()) {
            try {
                zzafy zzafy = this.zzczo.get(next);
                if (!(zzafy == null || zzafy.zzpc() == null)) {
                    zzafy.zzpc().pause();
                }
            } catch (RemoteException unused) {
                String valueOf = String.valueOf(next);
                zzahw.zzcz(valueOf.length() != 0 ? "Fail to pause adapter: ".concat(valueOf) : new String("Fail to pause adapter: "));
            }
        }
    }

    public final void resume() {
        zzbq.zzgn("resume must be called on the main UI thread.");
        for (String next : this.zzczo.keySet()) {
            try {
                zzafy zzafy = this.zzczo.get(next);
                if (!(zzafy == null || zzafy.zzpc() == null)) {
                    zzafy.zzpc().resume();
                }
            } catch (RemoteException unused) {
                String valueOf = String.valueOf(next);
                zzahw.zzcz(valueOf.length() != 0 ? "Fail to resume adapter: ".concat(valueOf) : new String("Fail to resume adapter: "));
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzafy zzbq(java.lang.String r5) {
        /*
            r4 = this;
            java.util.Map<java.lang.String, com.google.android.gms.internal.zzafy> r0 = r4.zzczo
            java.lang.Object r0 = r0.get(r5)
            com.google.android.gms.internal.zzafy r0 = (com.google.android.gms.internal.zzafy) r0
            if (r0 != 0) goto L_0x0045
            com.google.android.gms.internal.zzwf r1 = r4.zzczm     // Catch:{ Exception -> 0x002b }
            java.lang.String r2 = "com.google.ads.mediation.admob.AdMobAdapter"
            boolean r2 = r2.equals(r5)     // Catch:{ Exception -> 0x002b }
            if (r2 == 0) goto L_0x0016
            com.google.android.gms.internal.zzwe r1 = zzczl     // Catch:{ Exception -> 0x002b }
        L_0x0016:
            com.google.android.gms.internal.zzafy r2 = new com.google.android.gms.internal.zzafy     // Catch:{ Exception -> 0x002b }
            com.google.android.gms.internal.zzwi r1 = r1.zzbg(r5)     // Catch:{ Exception -> 0x002b }
            com.google.android.gms.internal.zzafs r3 = r4.zzczp     // Catch:{ Exception -> 0x002b }
            r2.<init>(r1, r3)     // Catch:{ Exception -> 0x002b }
            java.util.Map<java.lang.String, com.google.android.gms.internal.zzafy> r0 = r4.zzczo     // Catch:{ Exception -> 0x0028 }
            r0.put(r5, r2)     // Catch:{ Exception -> 0x0028 }
            r0 = r2
            goto L_0x0045
        L_0x0028:
            r1 = move-exception
            r0 = r2
            goto L_0x002c
        L_0x002b:
            r1 = move-exception
        L_0x002c:
            java.lang.String r2 = "Fail to instantiate adapter "
            java.lang.String r5 = java.lang.String.valueOf(r5)
            int r3 = r5.length()
            if (r3 == 0) goto L_0x003d
            java.lang.String r5 = r2.concat(r5)
            goto L_0x0042
        L_0x003d:
            java.lang.String r5 = new java.lang.String
            r5.<init>(r2)
        L_0x0042:
            com.google.android.gms.internal.zzahw.zzc(r5, r1)
        L_0x0045:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaeo.zzbq(java.lang.String):com.google.android.gms.internal.zzafy");
    }

    public final zzagd zzd(zzagd zzagd) {
        if (!(this.zzczn.zzaue == null || this.zzczn.zzaue.zzdcj == null || TextUtils.isEmpty(this.zzczn.zzaue.zzdcj.zzcid))) {
            zzagd = new zzagd(this.zzczn.zzaue.zzdcj.zzcid, this.zzczn.zzaue.zzdcj.zzcie);
        }
        if (!(this.zzczn.zzaue == null || this.zzczn.zzaue.zzcje == null)) {
            zzbt.zzfd();
            zzvy.zza(this.zzczn.zzaiq, this.zzczn.zzatz.zzcu, this.zzczn.zzaue.zzcje.zzchm, this.zzczn.zzaux, zzagd);
        }
        return zzagd;
    }

    public final zzzn zzoq() {
        return this.zzczq;
    }

    public final void zzor() {
        this.zzczn.zzavb = 0;
        zzbu zzbu = this.zzczn;
        zzbt.zzek();
        zzafv zzafv = new zzafv(this.zzczn.zzaiq, this.zzczn.zzauf, this);
        String valueOf = String.valueOf(zzafv.getClass().getName());
        zzahw.zzby(valueOf.length() != 0 ? "AdRenderer: ".concat(valueOf) : new String("AdRenderer: "));
        zzafv.zzns();
        zzbu.zzauc = zzafv;
    }

    public final void zzos() {
        if (this.zzczn.zzaue != null && this.zzczn.zzaue.zzcje != null) {
            zzbt.zzfd();
            zzvy.zza(this.zzczn.zzaiq, this.zzczn.zzatz.zzcu, this.zzczn.zzaue, this.zzczn.zzatx, false, this.zzczn.zzaue.zzcje.zzchl);
        }
    }

    public final void zzot() {
        if (this.zzczn.zzaue != null && this.zzczn.zzaue.zzcje != null) {
            zzbt.zzfd();
            zzvy.zza(this.zzczn.zzaiq, this.zzczn.zzatz.zzcu, this.zzczn.zzaue, this.zzczn.zzatx, false, this.zzczn.zzaue.zzcje.zzchn);
        }
    }

    public final void zzv(boolean z) {
        zzafy zzbq = zzbq(this.zzczn.zzaue.zzcjg);
        if (zzbq != null && zzbq.zzpc() != null) {
            try {
                zzbq.zzpc().setImmersiveMode(z);
                zzbq.zzpc().showVideo();
            } catch (RemoteException e) {
                zzahw.zzc("Could not call showVideo.", e);
            }
        }
    }
}
