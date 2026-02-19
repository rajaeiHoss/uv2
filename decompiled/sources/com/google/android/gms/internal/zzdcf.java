package com.google.android.gms.internal;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

final class zzdcf {

    static class zza {
        private static volatile ExecutorService zzkzp;

        private zza() {
        }

        public static ExecutorService zzes(Context context) {
            if (zzkzp == null) {
                synchronized (zza.class) {
                    if (zzkzp == null) {
                        zzkzp = new zzczv(context, 1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new zzdcg());
                    }
                }
            }
            return zzkzp;
        }
    }
}
