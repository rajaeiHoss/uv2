package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaid;
import com.google.android.gms.internal.zzako;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzcr;
import com.google.android.gms.internal.zzcu;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzoi;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

@zzabh
public final class zzaf implements zzcr, Runnable {
    private Context zzaiq;
    private final List<Object[]> zzaoz;
    private final AtomicReference<zzcr> zzapa;
    private zzala zzapb;
    private CountDownLatch zzapc;

    private zzaf(Context context, zzala zzala) {
        this.zzaoz = new Vector();
        this.zzapa = new AtomicReference<>();
        this.zzapc = new CountDownLatch(1);
        this.zzaiq = context;
        this.zzapb = zzala;
        zzlc.zzij();
        if (zzako.zzsa()) {
            zzaid.zzb(this);
        } else {
            run();
        }
    }

    public zzaf(zzbu zzbu) {
        this(zzbu.zzaiq, zzbu.zzatz);
    }

    private static Context zzd(Context context) {
        Context applicationContext = context.getApplicationContext();
        return applicationContext == null ? context : applicationContext;
    }

    private final boolean zzdd() {
        try {
            this.zzapc.await();
            return true;
        } catch (InterruptedException e) {
            zzahw.zzc("Interrupted during GADSignals creation.", e);
            return false;
        }
    }

    private final void zzde() {
        if (!this.zzaoz.isEmpty()) {
            for (Object[] next : this.zzaoz) {
                if (next.length == 1) {
                    this.zzapa.get().zza((MotionEvent) next[0]);
                } else if (next.length == 3) {
                    this.zzapa.get().zza(((Integer) next[0]).intValue(), ((Integer) next[1]).intValue(), ((Integer) next[2]).intValue());
                }
            }
            this.zzaoz.clear();
        }
    }

    public final void run() {
        boolean z = false;
        try {
            boolean z2 = this.zzapb.zzdjb;
            if (!((Boolean) zzlc.zzio().zzd(zzoi.zzbpf)).booleanValue() && z2) {
                z = true;
            }
            this.zzapa.set(zzcu.zza(this.zzapb.zzcu, zzd(this.zzaiq), z));
        } finally {
            this.zzapc.countDown();
            this.zzaiq = null;
            this.zzapb = null;
        }
    }

    public final String zza(Context context) {
        zzcr zzcr;
        if (!zzdd() || (zzcr = this.zzapa.get()) == null) {
            return "";
        }
        zzde();
        return zzcr.zza(zzd(context));
    }

    public final String zza(Context context, String str, View view) {
        return zza(context, str, view, (Activity) null);
    }

    public final String zza(Context context, String str, View view, Activity activity) {
        zzcr zzcr;
        if (!zzdd() || (zzcr = this.zzapa.get()) == null) {
            return "";
        }
        zzde();
        return zzcr.zza(zzd(context), str, view, activity);
    }

    public final void zza(int i, int i2, int i3) {
        zzcr zzcr = this.zzapa.get();
        if (zzcr != null) {
            zzde();
            zzcr.zza(i, i2, i3);
            return;
        }
        this.zzaoz.add(new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
    }

    public final void zza(MotionEvent motionEvent) {
        zzcr zzcr = this.zzapa.get();
        if (zzcr != null) {
            zzde();
            zzcr.zza(motionEvent);
            return;
        }
        this.zzaoz.add(new Object[]{motionEvent});
    }

    public final void zzb(View view) {
        zzcr zzcr = this.zzapa.get();
        if (zzcr != null) {
            zzcr.zzb(view);
        }
    }
}
