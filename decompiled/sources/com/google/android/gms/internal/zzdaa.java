package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

final class zzdaa extends Thread implements zzczz {
    private static zzdaa zzkwr;
    private volatile boolean mClosed = false;
    /* access modifiers changed from: private */
    public final Context mContext;
    private final zze zzata = zzi.zzanq();
    private volatile boolean zzcnf = false;
    private final LinkedBlockingQueue<Runnable> zzkpx = new LinkedBlockingQueue<>();
    /* access modifiers changed from: private */
    public volatile zzdac zzkws;

    private zzdaa(Context context) {
        super("GAThread");
        this.mContext = context != null ? context.getApplicationContext() : context;
        start();
    }

    static zzdaa zzer(Context context) {
        if (zzkwr == null) {
            zzkwr = new zzdaa(context);
        }
        return zzkwr;
    }

    public final void run() {
        while (true) {
            try {
                Runnable take = this.zzkpx.take();
                if (!this.zzcnf) {
                    take.run();
                }
            } catch (InterruptedException e) {
                zzdal.zzcy(e.toString());
            } catch (Throwable th) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                PrintStream printStream = new PrintStream(byteArrayOutputStream);
                zzdyq.zza(th, printStream);
                printStream.flush();
                String str = new String(byteArrayOutputStream.toByteArray());
                zzdal.e(str.length() != 0 ? "Error on Google TagManager Thread: ".concat(str) : new String("Error on Google TagManager Thread: "));
                zzdal.e("Google TagManager is shutting down.");
                this.zzcnf = true;
            }
        }
    }

    public final void zzb(String str, String str2, String str3, Map<String, String> map, String str4) {
        zzm(new zzdab(this, this, this.zzata.currentTimeMillis(), str, str2, str3, map, str4));
    }

    public final void zzm(Runnable runnable) {
        this.zzkpx.add(runnable);
    }
}
