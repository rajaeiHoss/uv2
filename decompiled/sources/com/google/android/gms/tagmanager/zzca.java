package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzdyq;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.LinkedBlockingQueue;

final class zzca extends Thread implements zzbz {
    private static zzca zzkpy;
    private volatile boolean mClosed = false;
    /* access modifiers changed from: private */
    public final Context mContext;
    private volatile boolean zzcnf = false;
    private final LinkedBlockingQueue<Runnable> zzkpx = new LinkedBlockingQueue<>();
    /* access modifiers changed from: private */
    public volatile zzcc zzkpz;

    private zzca(Context context) {
        super("GAThread");
        this.mContext = context != null ? context.getApplicationContext() : context;
        start();
    }

    static zzca zzen(Context context) {
        if (zzkpy == null) {
            zzkpy = new zzca(context);
        }
        return zzkpy;
    }

    public final void run() {
        while (true) {
            try {
                Runnable take = this.zzkpx.take();
                if (!this.zzcnf) {
                    take.run();
                }
            } catch (InterruptedException e) {
                zzdj.zzcy(e.toString());
            } catch (Throwable th) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                PrintStream printStream = new PrintStream(byteArrayOutputStream);
                zzdyq.zza(th, printStream);
                printStream.flush();
                String str = new String(byteArrayOutputStream.toByteArray());
                zzdj.e(str.length() != 0 ? "Error on Google TagManager Thread: ".concat(str) : new String("Error on Google TagManager Thread: "));
                zzdj.e("Google TagManager is shutting down.");
                this.zzcnf = true;
            }
        }
    }

    public final void zzlw(String str) {
        zzm(new zzcb(this, this, System.currentTimeMillis(), str));
    }

    public final void zzm(Runnable runnable) {
        this.zzkpx.add(runnable);
    }
}
