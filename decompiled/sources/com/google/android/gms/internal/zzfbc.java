package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;
import com.google.firebase.FirebaseApp;
import java.util.Random;

public final class zzfbc {
    private static zze zzemg = zzi.zzanq();
    private static Random zzlqy = new Random();
    private static zzfbe zzoxp = new zzfbf();
    private volatile boolean zzam;
    private FirebaseApp zzotv;
    private long zzoxq;

    public zzfbc(FirebaseApp firebaseApp, long j) {
        this.zzotv = firebaseApp;
        this.zzoxq = j;
    }

    public static boolean zzjd(int i) {
        return (i >= 500 && i < 600) || i == -2 || i == 429 || i == 408;
    }

    public final void cancel() {
        this.zzam = true;
    }

    public final void reset() {
        this.zzam = false;
    }

    public final void zza(zzfbn zzfbn, boolean z) {
        zzbq.checkNotNull(zzfbn);
        long elapsedRealtime = zzemg.elapsedRealtime() + this.zzoxq;
        String zzh = zzfbh.zzh(this.zzotv);
        if (z) {
            zzfbn.zze(zzh, this.zzotv.getApplicationContext());
        } else {
            zzfbn.zzss(zzh);
        }
        int i = 1000;
        while (zzemg.elapsedRealtime() + ((long) i) <= elapsedRealtime && !zzfbn.zzcos() && zzjd(zzfbn.getResultCode())) {
            try {
                zzoxp.zzje(zzlqy.nextInt(250) + i);
                if (i < 30000) {
                    if (zzfbn.getResultCode() != -2) {
                        i <<= 1;
                        Log.w("ExponenentialBackoff", "network error occurred, backing off/sleeping.");
                    } else {
                        Log.w("ExponenentialBackoff", "network unavailable, sleeping.");
                        i = 1000;
                    }
                }
                if (!this.zzam) {
                    zzfbn.reset();
                    String zzh2 = zzfbh.zzh(this.zzotv);
                    if (z) {
                        zzfbn.zze(zzh2, this.zzotv.getApplicationContext());
                    } else {
                        zzfbn.zzss(zzh2);
                    }
                } else {
                    return;
                }
            } catch (InterruptedException unused) {
                Log.w("ExponenentialBackoff", "thread interrupted during exponential backoff.");
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
