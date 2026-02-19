package com.google.android.gms.internal;

import java.io.PrintStream;
import java.io.PrintWriter;

public final class zzdyq {
    private static zzdyr zzmmf;
    private static int zzmmg;

    static final class zza extends zzdyr {
        zza() {
        }

        public final void zza(Throwable th, PrintStream printStream) {
            th.printStackTrace(printStream);
        }

        public final void zza(Throwable th, PrintWriter printWriter) {
            th.printStackTrace(printWriter);
        }
    }

    static {
        zzdyr zzdyr;
        Integer zzbss = null;
        int i = 1;
        try {
            zzbss = zzbss();
            if (zzbss != null && zzbss.intValue() >= 19) {
                zzdyr = new zzdyv();
            } else if (!Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic")) {
                zzdyr = new zzdyu();
            } else {
                zzdyr = new zza();
            }
        } catch (Throwable th) {
            PrintStream printStream = System.err;
            String name = zza.class.getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 132);
            sb.append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ");
            sb.append(name);
            sb.append("will be used. The error is: ");
            printStream.println(sb.toString());
            th.printStackTrace(System.err);
            zzdyr = new zza();
        }
        zzmmf = zzdyr;
        if (zzbss != null) {
            i = zzbss.intValue();
        }
        zzmmg = i;
    }

    public static void zza(Throwable th, PrintStream printStream) {
        zzmmf.zza(th, printStream);
    }

    public static void zza(Throwable th, PrintWriter printWriter) {
        zzmmf.zza(th, printWriter);
    }

    private static Integer zzbss() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get((Object) null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }
}
