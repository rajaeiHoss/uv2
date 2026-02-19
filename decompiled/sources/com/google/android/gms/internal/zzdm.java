package com.google.android.gms.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzdm {
    private static final String TAG = "zzdm";
    private volatile boolean zzahq;
    protected Context zzaiq;
    private ExecutorService zzair;
    private DexClassLoader zzais;
    private zzcx zzait;
    private byte[] zzaiu;
    private volatile AdvertisingIdClient zzaiv = null;
    private Future zzaiw;
    private boolean zzaix;
    /* access modifiers changed from: private */
    public volatile zzba zzaiy;
    private Future zzaiz;
    private zzcp zzaja;
    private boolean zzajb;
    private boolean zzajc;
    private Map<Pair<String, String>, zzes> zzajd;
    private boolean zzaje;
    /* access modifiers changed from: private */
    public boolean zzajf;
    private boolean zzajg;

    final class zza extends BroadcastReceiver {
        private zza() {
        }

        /* synthetic */ zza(zzdm zzdm, zzdn zzdn) {
            this();
        }

        public final void onReceive(Context context, Intent intent) {
            if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
                boolean unused = zzdm.this.zzajf = true;
            } else if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                boolean unused2 = zzdm.this.zzajf = false;
            }
        }
    }

    private zzdm(Context context) {
        boolean z = false;
        this.zzahq = false;
        this.zzaiw = null;
        this.zzaiy = null;
        this.zzaiz = null;
        this.zzajb = false;
        this.zzajc = false;
        this.zzaje = false;
        this.zzajf = true;
        this.zzajg = false;
        Context applicationContext = context.getApplicationContext();
        z = applicationContext != null ? true : z;
        this.zzaix = z;
        this.zzaiq = z ? applicationContext : context;
        this.zzajd = new HashMap();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(33:0|1|2|(1:4)|5|6|7|8|(1:10)(1:11)|12|(1:14)(1:15)|16|17|18|(2:20|(1:22)(2:23|24))|25|26|27|28|29|(2:31|(1:33)(2:34|35))|36|(1:38)|39|40|41|42|43|44|45|(2:47|(1:49)(1:50))|51|72) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0048 */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0051 A[Catch:{ zzcy -> 0x015c, zzdj -> 0x0163 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0082 A[Catch:{ all -> 0x012b, FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ad A[Catch:{ all -> 0x012b, FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0101 A[Catch:{ zzcy -> 0x015c, zzdj -> 0x0163 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.zzdm zza(android.content.Context r9, java.lang.String r10, java.lang.String r11, boolean r12) {
        /*
            java.lang.String r0 = "%s/%s.dex"
            com.google.android.gms.internal.zzdm r1 = new com.google.android.gms.internal.zzdm
            r1.<init>(r9)
            java.util.concurrent.ExecutorService r9 = java.util.concurrent.Executors.newCachedThreadPool()     // Catch:{ zzdj -> 0x0163 }
            r1.zzair = r9     // Catch:{ zzdj -> 0x0163 }
            r1.zzahq = r12     // Catch:{ zzdj -> 0x0163 }
            if (r12 == 0) goto L_0x001e
            java.util.concurrent.ExecutorService r9 = r1.zzair     // Catch:{ zzdj -> 0x0163 }
            com.google.android.gms.internal.zzdn r12 = new com.google.android.gms.internal.zzdn     // Catch:{ zzdj -> 0x0163 }
            r12.<init>(r1)     // Catch:{ zzdj -> 0x0163 }
            java.util.concurrent.Future r9 = r9.submit(r12)     // Catch:{ zzdj -> 0x0163 }
            r1.zzaiw = r9     // Catch:{ zzdj -> 0x0163 }
        L_0x001e:
            java.util.concurrent.ExecutorService r9 = r1.zzair     // Catch:{ zzdj -> 0x0163 }
            com.google.android.gms.internal.zzdp r12 = new com.google.android.gms.internal.zzdp     // Catch:{ zzdj -> 0x0163 }
            r12.<init>(r1)     // Catch:{ zzdj -> 0x0163 }
            r9.execute(r12)     // Catch:{ zzdj -> 0x0163 }
            r9 = 1
            r12 = 0
            com.google.android.gms.common.zzf r2 = com.google.android.gms.common.zzf.zzahf()     // Catch:{ all -> 0x0048 }
            android.content.Context r3 = r1.zzaiq     // Catch:{ all -> 0x0048 }
            int r3 = com.google.android.gms.common.zzf.zzcg(r3)     // Catch:{ all -> 0x0048 }
            if (r3 <= 0) goto L_0x0038
            r3 = 1
            goto L_0x0039
        L_0x0038:
            r3 = 0
        L_0x0039:
            r1.zzajb = r3     // Catch:{ all -> 0x0048 }
            android.content.Context r3 = r1.zzaiq     // Catch:{ all -> 0x0048 }
            int r2 = r2.isGooglePlayServicesAvailable(r3)     // Catch:{ all -> 0x0048 }
            if (r2 != 0) goto L_0x0045
            r2 = 1
            goto L_0x0046
        L_0x0045:
            r2 = 0
        L_0x0046:
            r1.zzajc = r2     // Catch:{ all -> 0x0048 }
        L_0x0048:
            r1.zza((int) r12, (boolean) r9)     // Catch:{ zzdj -> 0x0163 }
            boolean r2 = com.google.android.gms.internal.zzds.zzas()     // Catch:{ zzdj -> 0x0163 }
            if (r2 == 0) goto L_0x006c
            com.google.android.gms.internal.zzny<java.lang.Boolean> r2 = com.google.android.gms.internal.zzoi.zzbrs     // Catch:{ zzdj -> 0x0163 }
            com.google.android.gms.internal.zzog r3 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ zzdj -> 0x0163 }
            java.lang.Object r2 = r3.zzd(r2)     // Catch:{ zzdj -> 0x0163 }
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ zzdj -> 0x0163 }
            boolean r2 = r2.booleanValue()     // Catch:{ zzdj -> 0x0163 }
            if (r2 != 0) goto L_0x0064
            goto L_0x006c
        L_0x0064:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch:{ zzdj -> 0x0163 }
            java.lang.String r10 = "Task Context initialization must not be called from the UI thread."
            r9.<init>(r10)     // Catch:{ zzdj -> 0x0163 }
            throw r9     // Catch:{ zzdj -> 0x0163 }
        L_0x006c:
            com.google.android.gms.internal.zzcx r2 = new com.google.android.gms.internal.zzcx     // Catch:{ zzdj -> 0x0163 }
            r3 = 0
            r2.<init>(r3)     // Catch:{ zzdj -> 0x0163 }
            r1.zzait = r2     // Catch:{ zzdj -> 0x0163 }
            byte[] r10 = r2.zzl(r10)     // Catch:{ zzcy -> 0x015c }
            r1.zzaiu = r10     // Catch:{ zzcy -> 0x015c }
            android.content.Context r10 = r1.zzaiq     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            java.io.File r10 = r10.getCacheDir()     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            if (r10 != 0) goto L_0x0093
            android.content.Context r10 = r1.zzaiq     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            java.lang.String r2 = "dex"
            java.io.File r10 = r10.getDir(r2, r12)     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            if (r10 == 0) goto L_0x008d
            goto L_0x0093
        L_0x008d:
            com.google.android.gms.internal.zzdj r9 = new com.google.android.gms.internal.zzdj     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            r9.<init>()     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            throw r9     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
        L_0x0093:
            java.lang.String r2 = "1510898742191"
            java.io.File r4 = new java.io.File     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            java.lang.String r5 = "%s/%s.jar"
            r6 = 2
            java.lang.Object[] r7 = new java.lang.Object[r6]     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            r7[r12] = r10     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            r7[r9] = r2     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            java.lang.String r5 = java.lang.String.format(r5, r7)     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            r4.<init>(r5)     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            boolean r5 = r4.exists()     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            if (r5 != 0) goto L_0x00c4
            com.google.android.gms.internal.zzcx r5 = r1.zzait     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            byte[] r7 = r1.zzaiu     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            byte[] r11 = r5.zzb(r7, r11)     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            r4.createNewFile()     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            r5.<init>(r4)     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            int r7 = r11.length     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            r5.write(r11, r12, r7)     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            r5.close()     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
        L_0x00c4:
            r1.zzb((java.io.File) r10, (java.lang.String) r2)     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            dalvik.system.DexClassLoader r11 = new dalvik.system.DexClassLoader     // Catch:{ all -> 0x012b }
            java.lang.String r5 = r4.getAbsolutePath()     // Catch:{ all -> 0x012b }
            java.lang.String r7 = r10.getAbsolutePath()     // Catch:{ all -> 0x012b }
            android.content.Context r8 = r1.zzaiq     // Catch:{ all -> 0x012b }
            java.lang.ClassLoader r8 = r8.getClassLoader()     // Catch:{ all -> 0x012b }
            r11.<init>(r5, r7, r3, r8)     // Catch:{ all -> 0x012b }
            r1.zzais = r11     // Catch:{ all -> 0x012b }
            zzb(r4)     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            r1.zza((java.io.File) r10, (java.lang.String) r2)     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            java.lang.Object[] r11 = new java.lang.Object[r6]     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            r11[r12] = r10     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            r11[r9] = r2     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            java.lang.String r10 = java.lang.String.format(r0, r11)     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            zzm(r10)     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            com.google.android.gms.internal.zzny<java.lang.Boolean> r10 = com.google.android.gms.internal.zzoi.zzbrk     // Catch:{ zzdj -> 0x0163 }
            com.google.android.gms.internal.zzog r11 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ zzdj -> 0x0163 }
            java.lang.Object r10 = r11.zzd(r10)     // Catch:{ zzdj -> 0x0163 }
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ zzdj -> 0x0163 }
            boolean r10 = r10.booleanValue()     // Catch:{ zzdj -> 0x0163 }
            if (r10 == 0) goto L_0x0121
            boolean r10 = r1.zzajg     // Catch:{ zzdj -> 0x0163 }
            if (r10 == 0) goto L_0x0106
            goto L_0x0121
        L_0x0106:
            android.content.IntentFilter r10 = new android.content.IntentFilter     // Catch:{ zzdj -> 0x0163 }
            r10.<init>()     // Catch:{ zzdj -> 0x0163 }
            java.lang.String r11 = "android.intent.action.USER_PRESENT"
            r10.addAction(r11)     // Catch:{ zzdj -> 0x0163 }
            java.lang.String r11 = "android.intent.action.SCREEN_OFF"
            r10.addAction(r11)     // Catch:{ zzdj -> 0x0163 }
            android.content.Context r11 = r1.zzaiq     // Catch:{ zzdj -> 0x0163 }
            com.google.android.gms.internal.zzdm$zza r12 = new com.google.android.gms.internal.zzdm$zza     // Catch:{ zzdj -> 0x0163 }
            r12.<init>(r1, r3)     // Catch:{ zzdj -> 0x0163 }
            r11.registerReceiver(r12, r10)     // Catch:{ zzdj -> 0x0163 }
            r1.zzajg = r9     // Catch:{ zzdj -> 0x0163 }
        L_0x0121:
            com.google.android.gms.internal.zzcp r10 = new com.google.android.gms.internal.zzcp     // Catch:{ zzdj -> 0x0163 }
            r10.<init>(r1)     // Catch:{ zzdj -> 0x0163 }
            r1.zzaja = r10     // Catch:{ zzdj -> 0x0163 }
            r1.zzaje = r9     // Catch:{ zzdj -> 0x0163 }
            goto L_0x0163
        L_0x012b:
            r11 = move-exception
            zzb(r4)     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            r1.zza((java.io.File) r10, (java.lang.String) r2)     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            java.lang.Object[] r3 = new java.lang.Object[r6]     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            r3[r12] = r10     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            r3[r9] = r2     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            java.lang.String r9 = java.lang.String.format(r0, r3)     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            zzm(r9)     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
            throw r11     // Catch:{ FileNotFoundException -> 0x0155, IOException -> 0x014e, zzcy -> 0x0147, NullPointerException -> 0x0140 }
        L_0x0140:
            r9 = move-exception
            com.google.android.gms.internal.zzdj r10 = new com.google.android.gms.internal.zzdj     // Catch:{ zzdj -> 0x0163 }
            r10.<init>(r9)     // Catch:{ zzdj -> 0x0163 }
            throw r10     // Catch:{ zzdj -> 0x0163 }
        L_0x0147:
            r9 = move-exception
            com.google.android.gms.internal.zzdj r10 = new com.google.android.gms.internal.zzdj     // Catch:{ zzdj -> 0x0163 }
            r10.<init>(r9)     // Catch:{ zzdj -> 0x0163 }
            throw r10     // Catch:{ zzdj -> 0x0163 }
        L_0x014e:
            r9 = move-exception
            com.google.android.gms.internal.zzdj r10 = new com.google.android.gms.internal.zzdj     // Catch:{ zzdj -> 0x0163 }
            r10.<init>(r9)     // Catch:{ zzdj -> 0x0163 }
            throw r10     // Catch:{ zzdj -> 0x0163 }
        L_0x0155:
            r9 = move-exception
            com.google.android.gms.internal.zzdj r10 = new com.google.android.gms.internal.zzdj     // Catch:{ zzdj -> 0x0163 }
            r10.<init>(r9)     // Catch:{ zzdj -> 0x0163 }
            throw r10     // Catch:{ zzdj -> 0x0163 }
        L_0x015c:
            r9 = move-exception
            com.google.android.gms.internal.zzdj r10 = new com.google.android.gms.internal.zzdj     // Catch:{ zzdj -> 0x0163 }
            r10.<init>(r9)     // Catch:{ zzdj -> 0x0163 }
            throw r10     // Catch:{ zzdj -> 0x0163 }
        L_0x0163:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdm.zza(android.content.Context, java.lang.String, java.lang.String, boolean):com.google.android.gms.internal.zzdm");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:20|21|22|23|24|25|26|27|28|30) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x0091 */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a5 A[SYNTHETIC, Splitter:B:42:0x00a5] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00ac A[SYNTHETIC, Splitter:B:46:0x00ac] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00b6 A[SYNTHETIC, Splitter:B:54:0x00b6] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00bd A[SYNTHETIC, Splitter:B:58:0x00bd] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zza(java.io.File r9, java.lang.String r10) {
        /*
            r8 = this;
            java.io.File r0 = new java.io.File
            r1 = 2
            java.lang.Object[] r2 = new java.lang.Object[r1]
            r3 = 0
            r2[r3] = r9
            r4 = 1
            r2[r4] = r10
            java.lang.String r5 = "%s/%s.tmp"
            java.lang.String r2 = java.lang.String.format(r5, r2)
            r0.<init>(r2)
            boolean r2 = r0.exists()
            if (r2 == 0) goto L_0x001b
            return
        L_0x001b:
            java.io.File r2 = new java.io.File
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r1[r3] = r9
            r1[r4] = r10
            java.lang.String r9 = "%s/%s.dex"
            java.lang.String r9 = java.lang.String.format(r9, r1)
            r2.<init>(r9)
            boolean r9 = r2.exists()
            if (r9 != 0) goto L_0x0033
            return
        L_0x0033:
            long r4 = r2.length()
            r6 = 0
            int r9 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r9 > 0) goto L_0x003e
            return
        L_0x003e:
            int r9 = (int) r4
            byte[] r9 = new byte[r9]
            r1 = 0
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00b3, all -> 0x00a1 }
            r4.<init>(r2)     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00b3, all -> 0x00a1 }
            int r5 = r4.read(r9)     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x009e, all -> 0x009a }
            if (r5 > 0) goto L_0x0054
            r4.close()     // Catch:{ IOException -> 0x0050 }
        L_0x0050:
            zzb(r2)
            return
        L_0x0054:
            com.google.android.gms.internal.zzbe r5 = new com.google.android.gms.internal.zzbe     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x009e, all -> 0x009a }
            r5.<init>()     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x009e, all -> 0x009a }
            java.lang.String r6 = android.os.Build.VERSION.SDK     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x009e, all -> 0x009a }
            byte[] r6 = r6.getBytes()     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x009e, all -> 0x009a }
            r5.zzgm = r6     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x009e, all -> 0x009a }
            byte[] r10 = r10.getBytes()     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x009e, all -> 0x009a }
            r5.zzgl = r10     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x009e, all -> 0x009a }
            com.google.android.gms.internal.zzcx r10 = r8.zzait     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x009e, all -> 0x009a }
            byte[] r6 = r8.zzaiu     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x009e, all -> 0x009a }
            java.lang.String r9 = r10.zzc(r6, r9)     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x009e, all -> 0x009a }
            byte[] r9 = r9.getBytes()     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x009e, all -> 0x009a }
            r5.data = r9     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x009e, all -> 0x009a }
            byte[] r9 = com.google.android.gms.internal.zzbx.zzb(r9)     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x009e, all -> 0x009a }
            r5.zzgk = r9     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x009e, all -> 0x009a }
            r0.createNewFile()     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x009e, all -> 0x009a }
            java.io.FileOutputStream r9 = new java.io.FileOutputStream     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x009e, all -> 0x009a }
            r9.<init>(r0)     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x009e, all -> 0x009a }
            byte[] r10 = com.google.android.gms.internal.zzfls.zzc(r5)     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x009f, all -> 0x0098 }
            int r0 = r10.length     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x009f, all -> 0x0098 }
            r9.write(r10, r3, r0)     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x009f, all -> 0x0098 }
            r9.close()     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x009f, all -> 0x0098 }
            r4.close()     // Catch:{ IOException -> 0x0091 }
        L_0x0091:
            r9.close()     // Catch:{ IOException -> 0x0094 }
        L_0x0094:
            zzb(r2)
            return
        L_0x0098:
            r10 = move-exception
            goto L_0x009c
        L_0x009a:
            r10 = move-exception
            r9 = r1
        L_0x009c:
            r1 = r4
            goto L_0x00a3
        L_0x009e:
            r9 = r1
        L_0x009f:
            r1 = r4
            goto L_0x00b4
        L_0x00a1:
            r10 = move-exception
            r9 = r1
        L_0x00a3:
            if (r1 == 0) goto L_0x00aa
            r1.close()     // Catch:{ IOException -> 0x00a9 }
            goto L_0x00aa
        L_0x00a9:
        L_0x00aa:
            if (r9 == 0) goto L_0x00af
            r9.close()     // Catch:{ IOException -> 0x00af }
        L_0x00af:
            zzb(r2)
            throw r10
        L_0x00b3:
            r9 = r1
        L_0x00b4:
            if (r1 == 0) goto L_0x00bb
            r1.close()     // Catch:{ IOException -> 0x00ba }
            goto L_0x00bb
        L_0x00ba:
        L_0x00bb:
            if (r9 == 0) goto L_0x00c0
            r9.close()     // Catch:{ IOException -> 0x00c0 }
        L_0x00c0:
            zzb(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdm.zza(java.io.File, java.lang.String):void");
    }

    /* access modifiers changed from: private */
    public static boolean zza(int i, zzba zzba) {
        if (i >= 4) {
            return false;
        }
        if (zzba == null) {
            return true;
        }
        if (((Boolean) zzlc.zzio().zzd(zzoi.zzbrv)).booleanValue() && (zzba.zzcv == null || zzba.zzcv.equals("0000000000000000000000000000000000000000000000000000000000000000"))) {
            return true;
        }
        if (((Boolean) zzlc.zzio().zzd(zzoi.zzbrw)).booleanValue()) {
            return zzba.zzfh == null || zzba.zzfh.zzgf == null || zzba.zzfh.zzgf.longValue() == -2;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public final void zzao() {
        try {
            if (this.zzaiv == null && this.zzaix) {
                AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(this.zzaiq);
                advertisingIdClient.start();
                this.zzaiv = advertisingIdClient;
            }
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException unused) {
            this.zzaiv = null;
        }
    }

    private final zzba zzap() {
        try {
            PackageInfo packageInfo = this.zzaiq.getPackageManager().getPackageInfo(this.zzaiq.getPackageName(), 0);
            Context context = this.zzaiq;
            return zzcda.zzm(context, context.getPackageName(), Integer.toString(packageInfo.versionCode));
        } catch (Throwable unused) {
            return null;
        }
    }

    private static void zzb(File file) {
        if (!file.exists()) {
            Log.d(TAG, String.format("File %s not found. No need for deletion", new Object[]{file.getAbsolutePath()}));
            return;
        }
        file.delete();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:29|30|31|32|33|34|35|36) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:34:0x00b1 */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00c9 A[SYNTHETIC, Splitter:B:55:0x00c9] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00d0 A[SYNTHETIC, Splitter:B:59:0x00d0] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00d7 A[SYNTHETIC, Splitter:B:66:0x00d7] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00de A[SYNTHETIC, Splitter:B:70:0x00de] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzb(java.io.File r10, java.lang.String r11) {
        /*
            r9 = this;
            java.io.File r0 = new java.io.File
            r1 = 2
            java.lang.Object[] r2 = new java.lang.Object[r1]
            r3 = 0
            r2[r3] = r10
            r4 = 1
            r2[r4] = r11
            java.lang.String r5 = "%s/%s.tmp"
            java.lang.String r2 = java.lang.String.format(r5, r2)
            r0.<init>(r2)
            boolean r2 = r0.exists()
            if (r2 != 0) goto L_0x001b
            return r3
        L_0x001b:
            java.io.File r2 = new java.io.File
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r1[r3] = r10
            r1[r4] = r11
            java.lang.String r10 = "%s/%s.dex"
            java.lang.String r10 = java.lang.String.format(r10, r1)
            r2.<init>(r10)
            boolean r10 = r2.exists()
            if (r10 == 0) goto L_0x0033
            return r3
        L_0x0033:
            r10 = 0
            long r5 = r0.length()     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00d4, all -> 0x00c5 }
            r7 = 0
            int r1 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r1 > 0) goto L_0x0042
            zzb(r0)     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00d4, all -> 0x00c5 }
            return r3
        L_0x0042:
            int r1 = (int) r5     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00d4, all -> 0x00c5 }
            byte[] r1 = new byte[r1]     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00d4, all -> 0x00c5 }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00d4, all -> 0x00c5 }
            r5.<init>(r0)     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00d4, all -> 0x00c5 }
            int r6 = r5.read(r1)     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            if (r6 > 0) goto L_0x005e
            java.lang.String r11 = TAG     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            java.lang.String r1 = "Cannot read the cache data."
            android.util.Log.d(r11, r1)     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            zzb(r0)     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            r5.close()     // Catch:{ IOException -> 0x005d }
        L_0x005d:
            return r3
        L_0x005e:
            com.google.android.gms.internal.zzbe r6 = new com.google.android.gms.internal.zzbe     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            r6.<init>()     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            com.google.android.gms.internal.zzfls r1 = com.google.android.gms.internal.zzfls.zza(r6, r1)     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            com.google.android.gms.internal.zzbe r1 = (com.google.android.gms.internal.zzbe) r1     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            java.lang.String r6 = new java.lang.String     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            byte[] r7 = r1.zzgl     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            r6.<init>(r7)     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            boolean r11 = r11.equals(r6)     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            if (r11 == 0) goto L_0x00b7
            byte[] r11 = r1.zzgk     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            byte[] r6 = r1.data     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            byte[] r6 = com.google.android.gms.internal.zzbx.zzb(r6)     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            boolean r11 = java.util.Arrays.equals(r11, r6)     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            if (r11 == 0) goto L_0x00b7
            byte[] r11 = r1.zzgm     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            java.lang.String r6 = android.os.Build.VERSION.SDK     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            byte[] r6 = r6.getBytes()     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            boolean r11 = java.util.Arrays.equals(r11, r6)     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            if (r11 != 0) goto L_0x0093
            goto L_0x00b7
        L_0x0093:
            com.google.android.gms.internal.zzcx r11 = r9.zzait     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            byte[] r0 = r9.zzaiu     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            java.lang.String r6 = new java.lang.String     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            byte[] r1 = r1.data     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            r6.<init>(r1)     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            byte[] r11 = r11.zzb(r0, r6)     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            r2.createNewFile()     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            r0.<init>(r2)     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            int r10 = r11.length     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c3, all -> 0x00b5 }
            r0.write(r11, r3, r10)     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c3, all -> 0x00b5 }
            r5.close()     // Catch:{ IOException -> 0x00b1 }
        L_0x00b1:
            r0.close()     // Catch:{ IOException -> 0x00b4 }
        L_0x00b4:
            return r4
        L_0x00b5:
            r11 = move-exception
            goto L_0x00c0
        L_0x00b7:
            zzb(r0)     // Catch:{ zzcy | IOException | NoSuchAlgorithmException -> 0x00c2, all -> 0x00be }
            r5.close()     // Catch:{ IOException -> 0x00bd }
        L_0x00bd:
            return r3
        L_0x00be:
            r11 = move-exception
            r0 = r10
        L_0x00c0:
            r10 = r5
            goto L_0x00c7
        L_0x00c2:
            r0 = r10
        L_0x00c3:
            r10 = r5
            goto L_0x00d5
        L_0x00c5:
            r11 = move-exception
            r0 = r10
        L_0x00c7:
            if (r10 == 0) goto L_0x00ce
            r10.close()     // Catch:{ IOException -> 0x00cd }
            goto L_0x00ce
        L_0x00cd:
        L_0x00ce:
            if (r0 == 0) goto L_0x00d3
            r0.close()     // Catch:{ IOException -> 0x00d3 }
        L_0x00d3:
            throw r11
        L_0x00d4:
            r0 = r10
        L_0x00d5:
            if (r10 == 0) goto L_0x00dc
            r10.close()     // Catch:{ IOException -> 0x00db }
            goto L_0x00dc
        L_0x00db:
        L_0x00dc:
            if (r0 == 0) goto L_0x00e1
            r0.close()     // Catch:{ IOException -> 0x00e1 }
        L_0x00e1:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdm.zzb(java.io.File, java.lang.String):boolean");
    }

    private static void zzm(String str) {
        zzb(new File(str));
    }

    public final Context getContext() {
        return this.zzaiq;
    }

    public final ExecutorService getExecutorService() {
        return this.zzair;
    }

    public final boolean isInitialized() {
        return this.zzaje;
    }

    public final Method zza(String str, String str2) {
        zzes zzes = this.zzajd.get(new Pair(str, str2));
        if (zzes == null) {
            return null;
        }
        return zzes.zzbb();
    }

    /* access modifiers changed from: package-private */
    public final void zza(int i, boolean z) {
        if (this.zzajc) {
            Future<?> submit = this.zzair.submit(new zzdo(this, i, z));
            if (i == 0) {
                this.zzaiz = submit;
            }
        }
    }

    public final boolean zza(String str, String str2, Class<?>... clsArr) {
        if (this.zzajd.containsKey(new Pair(str, str2))) {
            return false;
        }
        this.zzajd.put(new Pair(str, str2), new zzes(this, str, str2, clsArr));
        return true;
    }

    public final int zzab() {
        if (this.zzaja != null) {
            return zzcp.zzab();
        }
        return Integer.MIN_VALUE;
    }

    public final DexClassLoader zzaf() {
        return this.zzais;
    }

    public final zzcx zzag() {
        return this.zzait;
    }

    public final byte[] zzah() {
        return this.zzaiu;
    }

    public final boolean zzai() {
        return this.zzajb;
    }

    public final zzcp zzaj() {
        return this.zzaja;
    }

    public final boolean zzak() {
        return this.zzajc;
    }

    public final boolean zzal() {
        return this.zzajf;
    }

    public final zzba zzam() {
        return this.zzaiy;
    }

    public final Future zzan() {
        return this.zzaiz;
    }

    public final AdvertisingIdClient zzaq() {
        if (!this.zzahq) {
            return null;
        }
        if (this.zzaiv != null) {
            return this.zzaiv;
        }
        Future future = this.zzaiw;
        if (future != null) {
            try {
                future.get(2000, TimeUnit.MILLISECONDS);
                this.zzaiw = null;
            } catch (InterruptedException | ExecutionException unused) {
            } catch (TimeoutException unused2) {
                this.zzaiw.cancel(true);
            }
        }
        return this.zzaiv;
    }

    /* access modifiers changed from: package-private */
    public final zzba zzb(int i, boolean z) {
        if (i > 0 && z) {
            try {
                Thread.sleep((long) (i * 1000));
            } catch (InterruptedException unused) {
            }
        }
        return zzap();
    }
}
