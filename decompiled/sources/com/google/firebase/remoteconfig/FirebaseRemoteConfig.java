package com.google.firebase.remoteconfig;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.XmlResourceParser;
import android.os.AsyncTask;
import android.util.Log;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.internal.zzbii;
import com.google.android.gms.internal.zzbin;
import com.google.android.gms.internal.zzbio;
import com.google.android.gms.internal.zzbiz;
import com.google.android.gms.internal.zzdyx;
import com.google.android.gms.internal.zzfam;
import com.google.android.gms.internal.zzfan;
import com.google.android.gms.internal.zzfao;
import com.google.android.gms.internal.zzfap;
import com.google.android.gms.internal.zzfaq;
import com.google.android.gms.internal.zzfar;
import com.google.android.gms.internal.zzfas;
import com.google.android.gms.internal.zzfat;
import com.google.android.gms.internal.zzfau;
import com.google.android.gms.internal.zzfav;
import com.google.android.gms.internal.zzfaw;
import com.google.android.gms.internal.zzfax;
import com.google.android.gms.internal.zzfay;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.xbill.DNS.TTL;

public class FirebaseRemoteConfig {
    public static final boolean DEFAULT_VALUE_FOR_BOOLEAN = false;
    public static final byte[] DEFAULT_VALUE_FOR_BYTE_ARRAY = new byte[0];
    public static final double DEFAULT_VALUE_FOR_DOUBLE = 0.0d;
    public static final long DEFAULT_VALUE_FOR_LONG = 0;
    public static final String DEFAULT_VALUE_FOR_STRING = "";
    public static final int LAST_FETCH_STATUS_FAILURE = 1;
    public static final int LAST_FETCH_STATUS_NO_FETCH_YET = 0;
    public static final int LAST_FETCH_STATUS_SUCCESS = -1;
    public static final int LAST_FETCH_STATUS_THROTTLED = 2;
    public static final int VALUE_SOURCE_DEFAULT = 1;
    public static final int VALUE_SOURCE_REMOTE = 2;
    public static final int VALUE_SOURCE_STATIC = 0;
    private static FirebaseRemoteConfig zzosa;
    private final Context mContext;
    private zzfap zzosb;
    private zzfap zzosc;
    private zzfap zzosd;
    private zzfas zzose;
    private final ReadWriteLock zzosf;

    private FirebaseRemoteConfig(Context context) {
        this(context, (zzfap) null, (zzfap) null, (zzfap) null, (zzfas) null);
    }

    private FirebaseRemoteConfig(Context context, zzfap zzfap, zzfap zzfap2, zzfap zzfap3, zzfas zzfas) {
        this.zzosf = new ReentrantReadWriteLock(true);
        this.mContext = context;
        this.zzose = zzfas == null ? new zzfas() : zzfas;
        this.zzose.zzco(zzfi(context));
        if (zzfap != null) {
            this.zzosb = zzfap;
        }
        if (zzfap2 != null) {
            this.zzosc = zzfap2;
        }
        if (zzfap3 != null) {
            this.zzosd = zzfap3;
        }
    }

    public static FirebaseRemoteConfig getInstance() {
        zzfas zzfas;
        FirebaseRemoteConfig firebaseRemoteConfig;
        FirebaseRemoteConfig firebaseRemoteConfig2 = zzosa;
        if (firebaseRemoteConfig2 != null) {
            return firebaseRemoteConfig2;
        }
        FirebaseApp instance = FirebaseApp.getInstance();
        if (instance != null) {
            Context applicationContext = instance.getApplicationContext();
            if (zzosa == null) {
                zzfax zzfj = zzfj(applicationContext);
                if (zzfj == null) {
                    if (Log.isLoggable("FirebaseRemoteConfig", 3)) {
                        Log.d("FirebaseRemoteConfig", "No persisted config was found. Initializing from scratch.");
                    }
                    firebaseRemoteConfig = new FirebaseRemoteConfig(applicationContext);
                } else {
                    if (Log.isLoggable("FirebaseRemoteConfig", 3)) {
                        Log.d("FirebaseRemoteConfig", "Initializing from persisted config.");
                    }
                    zzfap zza = zza(zzfj.zzotf);
                    zzfap zza2 = zza(zzfj.zzotg);
                    zzfap zza3 = zza(zzfj.zzoth);
                    zzfav zzfav = zzfj.zzoti;
                    if (zzfav == null) {
                        zzfas = null;
                    } else {
                        zzfas = new zzfas();
                        zzfas.zziy(zzfav.zzota);
                        zzfas.zzdd(zzfav.zzotb);
                        zzfas.zzcp(zzfav.zzotc);
                    }
                    if (zzfas != null) {
                        zzfas.zzav(zza(zzfj.zzotj));
                    }
                    firebaseRemoteConfig = new FirebaseRemoteConfig(applicationContext, zza, zza2, zza3, zzfas);
                }
                zzosa = firebaseRemoteConfig;
            }
            return zzosa;
        }
        throw new IllegalStateException("FirebaseApp has not been initialized.");
    }

    private static long zza(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return j;
            }
            outputStream.write(bArr, 0, read);
            j += (long) read;
        }
    }

    private static zzfap zza(zzfat zzfat) {
        if (zzfat == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (zzfaw zzfaw : zzfat.zzosw) {
            String str = zzfaw.zzkal;
            HashMap hashMap2 = new HashMap();
            for (zzfau zzfau : zzfaw.zzote) {
                hashMap2.put(zzfau.key, zzfau.zzosz);
            }
            hashMap.put(str, hashMap2);
        }
        byte[][] bArr = zzfat.zzosx;
        ArrayList arrayList = new ArrayList();
        for (byte[] add : bArr) {
            arrayList.add(add);
        }
        return new zzfap(hashMap, zzfat.timestamp, arrayList);
    }

    private static Map<String, zzfam> zza(zzfay[] zzfayArr) {
        HashMap hashMap = new HashMap();
        if (zzfayArr == null) {
            return hashMap;
        }
        for (zzfay zzfay : zzfayArr) {
            hashMap.put(zzfay.zzkal, new zzfam(zzfay.resourceId, zzfay.zzotl));
        }
        return hashMap;
    }

    private final void zzc(Map<String, Object> map, String str, boolean z) {
        zzfap zzfap = null;
        long currentTimeMillis = 0;
        if (str != null) {
            boolean z2 = map == null || map.isEmpty();
            HashMap hashMap = new HashMap();
            if (!z2) {
                for (String next : map.keySet()) {
                    Object obj = map.get(next);
                    if (obj instanceof String) {
                        hashMap.put(next, ((String) obj).getBytes(zzfar.UTF_8));
                    } else if (obj instanceof Long) {
                        hashMap.put(next, ((Long) obj).toString().getBytes(zzfar.UTF_8));
                    } else if (obj instanceof Integer) {
                        hashMap.put(next, ((Integer) obj).toString().getBytes(zzfar.UTF_8));
                    } else if (obj instanceof Double) {
                        hashMap.put(next, ((Double) obj).toString().getBytes(zzfar.UTF_8));
                    } else if (obj instanceof Float) {
                        hashMap.put(next, ((Float) obj).toString().getBytes(zzfar.UTF_8));
                    } else if (obj instanceof byte[]) {
                        hashMap.put(next, (byte[]) obj);
                    } else if (obj instanceof Boolean) {
                        hashMap.put(next, ((Boolean) obj).toString().getBytes(zzfar.UTF_8));
                    } else {
                        throw new IllegalArgumentException("The type of a default value needs to beone of String, Long, Double, Boolean, or byte[].");
                    }
                }
            }
            this.zzosf.writeLock().lock();
            try {
                if (z2) {
                    zzfap zzfap2 = this.zzosd;
                    if (zzfap2 != null && zzfap2.zzsk(str)) {
                        this.zzosd.zzi((Map<String, byte[]>) null, str);
                        zzfap = this.zzosd;
                        currentTimeMillis = System.currentTimeMillis();
                    }
                } else {
                    if (this.zzosd == null) {
                        this.zzosd = new zzfap(new HashMap(), System.currentTimeMillis(), (List<byte[]>) null);
                    }
                    this.zzosd.zzi(hashMap, str);
                    zzfap = this.zzosd;
                    currentTimeMillis = System.currentTimeMillis();
                }
                if (zzfap != null) {
                    zzfap.setTimestamp(currentTimeMillis);
                }
                if (z) {
                    this.zzose.zzsl(str);
                }
                zzcnj();
            } finally {
                this.zzosf.writeLock().unlock();
            }
        }
    }

    private final void zzcnj() {
        this.zzosf.readLock().lock();
        try {
            zzs(new zzfao(this.mContext, this.zzosb, this.zzosc, this.zzosd, this.zzose));
        } finally {
            this.zzosf.readLock().unlock();
        }
    }

    private final long zzfi(Context context) {
        try {
            return this.mContext.getPackageManager().getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
        } catch (PackageManager.NameNotFoundException unused) {
            String packageName = context.getPackageName();
            StringBuilder sb = new StringBuilder(String.valueOf(packageName).length() + 25);
            sb.append("Package [");
            sb.append(packageName);
            sb.append("] was not found!");
            Log.e("FirebaseRemoteConfig", sb.toString());
            return 0;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0047 A[SYNTHETIC, Splitter:B:25:0x0047] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0059 A[Catch:{ all -> 0x0033 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0060 A[SYNTHETIC, Splitter:B:38:0x0060] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x006b A[SYNTHETIC, Splitter:B:44:0x006b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.internal.zzfax zzfj(android.content.Context r7) {
        /*
            java.lang.String r0 = "Failed to close persisted config file."
            java.lang.String r1 = "FirebaseRemoteConfig"
            r2 = 0
            if (r7 != 0) goto L_0x0008
            return r2
        L_0x0008:
            java.lang.String r3 = "persisted_config"
            java.io.FileInputStream r7 = r7.openFileInput(r3)     // Catch:{ FileNotFoundException -> 0x0050, IOException -> 0x003e, all -> 0x0039 }
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ FileNotFoundException -> 0x0037, IOException -> 0x0035 }
            r3.<init>()     // Catch:{ FileNotFoundException -> 0x0037, IOException -> 0x0035 }
            zza((java.io.InputStream) r7, (java.io.OutputStream) r3)     // Catch:{ FileNotFoundException -> 0x0037, IOException -> 0x0035 }
            byte[] r3 = r3.toByteArray()     // Catch:{ FileNotFoundException -> 0x0037, IOException -> 0x0035 }
            r4 = 0
            int r5 = r3.length     // Catch:{ FileNotFoundException -> 0x0037, IOException -> 0x0035 }
            com.google.android.gms.internal.zzflj r3 = com.google.android.gms.internal.zzflj.zzo(r3, r4, r5)     // Catch:{ FileNotFoundException -> 0x0037, IOException -> 0x0035 }
            com.google.android.gms.internal.zzfax r4 = new com.google.android.gms.internal.zzfax     // Catch:{ FileNotFoundException -> 0x0037, IOException -> 0x0035 }
            r4.<init>()     // Catch:{ FileNotFoundException -> 0x0037, IOException -> 0x0035 }
            r4.zza((com.google.android.gms.internal.zzflj) r3)     // Catch:{ FileNotFoundException -> 0x0037, IOException -> 0x0035 }
            if (r7 == 0) goto L_0x0032
            r7.close()     // Catch:{ IOException -> 0x002e }
            goto L_0x0032
        L_0x002e:
            r7 = move-exception
            android.util.Log.e(r1, r0, r7)
        L_0x0032:
            return r4
        L_0x0033:
            r2 = move-exception
            goto L_0x0069
        L_0x0035:
            r3 = move-exception
            goto L_0x0040
        L_0x0037:
            r3 = move-exception
            goto L_0x0052
        L_0x0039:
            r7 = move-exception
            r6 = r2
            r2 = r7
            r7 = r6
            goto L_0x0069
        L_0x003e:
            r3 = move-exception
            r7 = r2
        L_0x0040:
            java.lang.String r4 = "Cannot initialize from persisted config."
            android.util.Log.e(r1, r4, r3)     // Catch:{ all -> 0x0033 }
            if (r7 == 0) goto L_0x004f
            r7.close()     // Catch:{ IOException -> 0x004b }
            goto L_0x004f
        L_0x004b:
            r7 = move-exception
            android.util.Log.e(r1, r0, r7)
        L_0x004f:
            return r2
        L_0x0050:
            r3 = move-exception
            r7 = r2
        L_0x0052:
            r4 = 3
            boolean r4 = android.util.Log.isLoggable(r1, r4)     // Catch:{ all -> 0x0033 }
            if (r4 == 0) goto L_0x005e
            java.lang.String r4 = "Persisted config file was not found."
            android.util.Log.d(r1, r4, r3)     // Catch:{ all -> 0x0033 }
        L_0x005e:
            if (r7 == 0) goto L_0x0068
            r7.close()     // Catch:{ IOException -> 0x0064 }
            goto L_0x0068
        L_0x0064:
            r7 = move-exception
            android.util.Log.e(r1, r0, r7)
        L_0x0068:
            return r2
        L_0x0069:
            if (r7 == 0) goto L_0x0073
            r7.close()     // Catch:{ IOException -> 0x006f }
            goto L_0x0073
        L_0x006f:
            r7 = move-exception
            android.util.Log.e(r1, r0, r7)
        L_0x0073:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.remoteconfig.FirebaseRemoteConfig.zzfj(android.content.Context):com.google.android.gms.internal.zzfax");
    }

    private static void zzs(Runnable runnable) {
        AsyncTask.SERIAL_EXECUTOR.execute(runnable);
    }

    public boolean activateFetched() {
        this.zzosf.writeLock().lock();
        try {
            if (this.zzosb != null) {
                zzfap zzfap = this.zzosc;
                if (zzfap == null || zzfap.getTimestamp() < this.zzosb.getTimestamp()) {
                    long timestamp = this.zzosb.getTimestamp();
                    zzfap zzfap2 = this.zzosb;
                    this.zzosc = zzfap2;
                    zzfap2.setTimestamp(System.currentTimeMillis());
                    this.zzosb = new zzfap((Map<String, Map<String, byte[]>>) null, timestamp, (List<byte[]>) null);
                    long zzcnp = this.zzose.zzcnp();
                    this.zzose.zzcp(zzdyx.zza(zzcnp, this.zzosc.zzaol()));
                    zzs(new zzfan(this.mContext, this.zzosc.zzaol(), zzcnp));
                    zzcnj();
                    this.zzosf.writeLock().unlock();
                    return true;
                }
            }
            return false;
        } finally {
            this.zzosf.writeLock().unlock();
        }
    }

    public Task<Void> fetch() {
        return fetch(43200);
    }

    /* JADX INFO: finally extract failed */
    public Task<Void> fetch(long j) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzosf.readLock().lock();
        try {
            zzbin zzbin = new zzbin();
            zzbin.zzaa(j);
            if (this.zzose.isDeveloperModeEnabled()) {
                zzbin.zzx("_rcn_developer", "true");
            }
            zzbin.zzcj(10300);
            zzfap zzfap = this.zzosc;
            int i = Integer.MAX_VALUE;
            if (!(zzfap == null || zzfap.getTimestamp() == -1)) {
                long convert = TimeUnit.SECONDS.convert(System.currentTimeMillis() - this.zzosc.getTimestamp(), TimeUnit.MILLISECONDS);
                zzbin.zzcl(convert < TTL.MAX_VALUE ? (int) convert : Integer.MAX_VALUE);
            }
            zzfap zzfap2 = this.zzosb;
            if (!(zzfap2 == null || zzfap2.getTimestamp() == -1)) {
                long convert2 = TimeUnit.SECONDS.convert(System.currentTimeMillis() - this.zzosb.getTimestamp(), TimeUnit.MILLISECONDS);
                if (convert2 < TTL.MAX_VALUE) {
                    i = (int) convert2;
                }
                zzbin.zzck(i);
            }
            zzbii.zzglu.zza(new zzbiz(this.mContext).zzahw(), zzbin.zzaok()).setResultCallback(new zza(this, taskCompletionSource));
            this.zzosf.readLock().unlock();
            return taskCompletionSource.getTask();
        } catch (Throwable th) {
            this.zzosf.readLock().unlock();
            throw th;
        }
    }

    public boolean getBoolean(String str) {
        return getBoolean(str, "configns:firebase");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0045, code lost:
        if (com.google.android.gms.internal.zzfar.zzgmc.matcher(r1).matches() != false) goto L_0x0047;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean getBoolean(java.lang.String r6, java.lang.String r7) {
        /*
            r5 = this;
            r0 = 0
            if (r7 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.util.concurrent.locks.ReadWriteLock r1 = r5.zzosf
            java.util.concurrent.locks.Lock r1 = r1.readLock()
            r1.lock()
            com.google.android.gms.internal.zzfap r1 = r5.zzosc     // Catch:{ all -> 0x0080 }
            r2 = 1
            if (r1 == 0) goto L_0x0051
            boolean r1 = r1.zzbl(r6, r7)     // Catch:{ all -> 0x0080 }
            if (r1 == 0) goto L_0x0051
            java.lang.String r1 = new java.lang.String     // Catch:{ all -> 0x0080 }
            com.google.android.gms.internal.zzfap r3 = r5.zzosc     // Catch:{ all -> 0x0080 }
            byte[] r3 = r3.zzbm(r6, r7)     // Catch:{ all -> 0x0080 }
            java.nio.charset.Charset r4 = com.google.android.gms.internal.zzfar.UTF_8     // Catch:{ all -> 0x0080 }
            r1.<init>(r3, r4)     // Catch:{ all -> 0x0080 }
            java.util.regex.Pattern r3 = com.google.android.gms.internal.zzfar.zzgmb     // Catch:{ all -> 0x0080 }
            java.util.regex.Matcher r3 = r3.matcher(r1)     // Catch:{ all -> 0x0080 }
            boolean r3 = r3.matches()     // Catch:{ all -> 0x0080 }
            if (r3 == 0) goto L_0x003b
        L_0x0031:
            java.util.concurrent.locks.ReadWriteLock r6 = r5.zzosf
            java.util.concurrent.locks.Lock r6 = r6.readLock()
            r6.unlock()
            return r2
        L_0x003b:
            java.util.regex.Pattern r3 = com.google.android.gms.internal.zzfar.zzgmc     // Catch:{ all -> 0x0080 }
            java.util.regex.Matcher r1 = r3.matcher(r1)     // Catch:{ all -> 0x0080 }
            boolean r1 = r1.matches()     // Catch:{ all -> 0x0080 }
            if (r1 == 0) goto L_0x0051
        L_0x0047:
            java.util.concurrent.locks.ReadWriteLock r6 = r5.zzosf
            java.util.concurrent.locks.Lock r6 = r6.readLock()
            r6.unlock()
            return r0
        L_0x0051:
            com.google.android.gms.internal.zzfap r1 = r5.zzosd     // Catch:{ all -> 0x0080 }
            if (r1 == 0) goto L_0x0047
            boolean r1 = r1.zzbl(r6, r7)     // Catch:{ all -> 0x0080 }
            if (r1 == 0) goto L_0x0047
            java.lang.String r1 = new java.lang.String     // Catch:{ all -> 0x0080 }
            com.google.android.gms.internal.zzfap r3 = r5.zzosd     // Catch:{ all -> 0x0080 }
            byte[] r6 = r3.zzbm(r6, r7)     // Catch:{ all -> 0x0080 }
            java.nio.charset.Charset r7 = com.google.android.gms.internal.zzfar.UTF_8     // Catch:{ all -> 0x0080 }
            r1.<init>(r6, r7)     // Catch:{ all -> 0x0080 }
            java.util.regex.Pattern r6 = com.google.android.gms.internal.zzfar.zzgmb     // Catch:{ all -> 0x0080 }
            java.util.regex.Matcher r6 = r6.matcher(r1)     // Catch:{ all -> 0x0080 }
            boolean r6 = r6.matches()     // Catch:{ all -> 0x0080 }
            if (r6 == 0) goto L_0x0075
            goto L_0x0031
        L_0x0075:
            java.util.regex.Pattern r6 = com.google.android.gms.internal.zzfar.zzgmc     // Catch:{ all -> 0x0080 }
            java.util.regex.Matcher r6 = r6.matcher(r1)     // Catch:{ all -> 0x0080 }
            boolean r6 = r6.matches()     // Catch:{ all -> 0x0080 }
            goto L_0x0047
        L_0x0080:
            r6 = move-exception
            java.util.concurrent.locks.ReadWriteLock r7 = r5.zzosf
            java.util.concurrent.locks.Lock r7 = r7.readLock()
            r7.unlock()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.remoteconfig.FirebaseRemoteConfig.getBoolean(java.lang.String, java.lang.String):boolean");
    }

    public byte[] getByteArray(String str) {
        return getByteArray(str, "configns:firebase");
    }

    public byte[] getByteArray(String str, String str2) {
        byte[] bArr;
        zzfap zzfap;
        if (str2 == null) {
            return DEFAULT_VALUE_FOR_BYTE_ARRAY;
        }
        this.zzosf.readLock().lock();
        try {
            zzfap zzfap2 = this.zzosc;
            if (zzfap2 == null || !zzfap2.zzbl(str, str2)) {
                zzfap zzfap3 = this.zzosd;
                if (zzfap3 == null || !zzfap3.zzbl(str, str2)) {
                    bArr = DEFAULT_VALUE_FOR_BYTE_ARRAY;
                    return bArr;
                }
                zzfap = this.zzosd;
            } else {
                zzfap = this.zzosc;
            }
            bArr = zzfap.zzbm(str, str2);
            return bArr;
        } finally {
            this.zzosf.readLock().unlock();
        }
    }

    public double getDouble(String str) {
        return getDouble(str, "configns:firebase");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:4|5|(5:9|10|11|12|13)|14|15|(5:19|20|21|12|13)|22|24) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0037 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public double getDouble(java.lang.String r6, java.lang.String r7) {
        /*
            r5 = this;
            r0 = 0
            if (r7 != 0) goto L_0x0005
            return r0
        L_0x0005:
            java.util.concurrent.locks.ReadWriteLock r2 = r5.zzosf
            java.util.concurrent.locks.Lock r2 = r2.readLock()
            r2.lock()
            com.google.android.gms.internal.zzfap r2 = r5.zzosc     // Catch:{ all -> 0x0061 }
            if (r2 == 0) goto L_0x0037
            boolean r2 = r2.zzbl(r6, r7)     // Catch:{ all -> 0x0061 }
            if (r2 == 0) goto L_0x0037
            java.lang.String r2 = new java.lang.String     // Catch:{ all -> 0x0061 }
            com.google.android.gms.internal.zzfap r3 = r5.zzosc     // Catch:{ all -> 0x0061 }
            byte[] r3 = r3.zzbm(r6, r7)     // Catch:{ all -> 0x0061 }
            java.nio.charset.Charset r4 = com.google.android.gms.internal.zzfar.UTF_8     // Catch:{ all -> 0x0061 }
            r2.<init>(r3, r4)     // Catch:{ all -> 0x0061 }
            java.lang.Double r2 = java.lang.Double.valueOf(r2)     // Catch:{ NumberFormatException -> 0x0037 }
            double r6 = r2.doubleValue()     // Catch:{ NumberFormatException -> 0x0037 }
        L_0x002d:
            java.util.concurrent.locks.ReadWriteLock r0 = r5.zzosf
            java.util.concurrent.locks.Lock r0 = r0.readLock()
            r0.unlock()
            return r6
        L_0x0037:
            com.google.android.gms.internal.zzfap r2 = r5.zzosd     // Catch:{ all -> 0x0061 }
            if (r2 == 0) goto L_0x0057
            boolean r2 = r2.zzbl(r6, r7)     // Catch:{ all -> 0x0061 }
            if (r2 == 0) goto L_0x0057
            java.lang.String r2 = new java.lang.String     // Catch:{ all -> 0x0061 }
            com.google.android.gms.internal.zzfap r3 = r5.zzosd     // Catch:{ all -> 0x0061 }
            byte[] r6 = r3.zzbm(r6, r7)     // Catch:{ all -> 0x0061 }
            java.nio.charset.Charset r7 = com.google.android.gms.internal.zzfar.UTF_8     // Catch:{ all -> 0x0061 }
            r2.<init>(r6, r7)     // Catch:{ all -> 0x0061 }
            java.lang.Double r6 = java.lang.Double.valueOf(r2)     // Catch:{ NumberFormatException -> 0x0057 }
            double r6 = r6.doubleValue()     // Catch:{ NumberFormatException -> 0x0057 }
            goto L_0x002d
        L_0x0057:
            java.util.concurrent.locks.ReadWriteLock r6 = r5.zzosf
            java.util.concurrent.locks.Lock r6 = r6.readLock()
            r6.unlock()
            return r0
        L_0x0061:
            r6 = move-exception
            java.util.concurrent.locks.ReadWriteLock r7 = r5.zzosf
            java.util.concurrent.locks.Lock r7 = r7.readLock()
            r7.unlock()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.remoteconfig.FirebaseRemoteConfig.getDouble(java.lang.String, java.lang.String):double");
    }

    public FirebaseRemoteConfigInfo getInfo() {
        zzfaq zzfaq = new zzfaq();
        this.zzosf.readLock().lock();
        try {
            zzfap zzfap = this.zzosb;
            zzfaq.zzcn(zzfap == null ? -1 : zzfap.getTimestamp());
            zzfaq.zziy(this.zzose.getLastFetchStatus());
            zzfaq.setConfigSettings(new FirebaseRemoteConfigSettings.Builder().setDeveloperModeEnabled(this.zzose.isDeveloperModeEnabled()).build());
            return zzfaq;
        } finally {
            this.zzosf.readLock().unlock();
        }
    }

    public Set<String> getKeysByPrefix(String str) {
        return getKeysByPrefix(str, "configns:firebase");
    }

    public Set<String> getKeysByPrefix(String str, String str2) {
        this.zzosf.readLock().lock();
        try {
            zzfap zzfap = this.zzosc;
            return zzfap == null ? new TreeSet<>() : zzfap.zzbn(str, str2);
        } finally {
            this.zzosf.readLock().unlock();
        }
    }

    public long getLong(String str) {
        return getLong(str, "configns:firebase");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:4|5|(5:9|10|11|12|13)|14|15|(5:19|20|21|12|13)|22|24) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0037 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long getLong(java.lang.String r6, java.lang.String r7) {
        /*
            r5 = this;
            r0 = 0
            if (r7 != 0) goto L_0x0005
            return r0
        L_0x0005:
            java.util.concurrent.locks.ReadWriteLock r2 = r5.zzosf
            java.util.concurrent.locks.Lock r2 = r2.readLock()
            r2.lock()
            com.google.android.gms.internal.zzfap r2 = r5.zzosc     // Catch:{ all -> 0x0061 }
            if (r2 == 0) goto L_0x0037
            boolean r2 = r2.zzbl(r6, r7)     // Catch:{ all -> 0x0061 }
            if (r2 == 0) goto L_0x0037
            java.lang.String r2 = new java.lang.String     // Catch:{ all -> 0x0061 }
            com.google.android.gms.internal.zzfap r3 = r5.zzosc     // Catch:{ all -> 0x0061 }
            byte[] r3 = r3.zzbm(r6, r7)     // Catch:{ all -> 0x0061 }
            java.nio.charset.Charset r4 = com.google.android.gms.internal.zzfar.UTF_8     // Catch:{ all -> 0x0061 }
            r2.<init>(r3, r4)     // Catch:{ all -> 0x0061 }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ NumberFormatException -> 0x0037 }
            long r6 = r2.longValue()     // Catch:{ NumberFormatException -> 0x0037 }
        L_0x002d:
            java.util.concurrent.locks.ReadWriteLock r0 = r5.zzosf
            java.util.concurrent.locks.Lock r0 = r0.readLock()
            r0.unlock()
            return r6
        L_0x0037:
            com.google.android.gms.internal.zzfap r2 = r5.zzosd     // Catch:{ all -> 0x0061 }
            if (r2 == 0) goto L_0x0057
            boolean r2 = r2.zzbl(r6, r7)     // Catch:{ all -> 0x0061 }
            if (r2 == 0) goto L_0x0057
            java.lang.String r2 = new java.lang.String     // Catch:{ all -> 0x0061 }
            com.google.android.gms.internal.zzfap r3 = r5.zzosd     // Catch:{ all -> 0x0061 }
            byte[] r6 = r3.zzbm(r6, r7)     // Catch:{ all -> 0x0061 }
            java.nio.charset.Charset r7 = com.google.android.gms.internal.zzfar.UTF_8     // Catch:{ all -> 0x0061 }
            r2.<init>(r6, r7)     // Catch:{ all -> 0x0061 }
            java.lang.Long r6 = java.lang.Long.valueOf(r2)     // Catch:{ NumberFormatException -> 0x0057 }
            long r6 = r6.longValue()     // Catch:{ NumberFormatException -> 0x0057 }
            goto L_0x002d
        L_0x0057:
            java.util.concurrent.locks.ReadWriteLock r6 = r5.zzosf
            java.util.concurrent.locks.Lock r6 = r6.readLock()
            r6.unlock()
            return r0
        L_0x0061:
            r6 = move-exception
            java.util.concurrent.locks.ReadWriteLock r7 = r5.zzosf
            java.util.concurrent.locks.Lock r7 = r7.readLock()
            r7.unlock()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.remoteconfig.FirebaseRemoteConfig.getLong(java.lang.String, java.lang.String):long");
    }

    public String getString(String str) {
        return getString(str, "configns:firebase");
    }

    public String getString(String str, String str2) {
        String str3 = "";
        if (str2 == null) {
            return str3;
        }
        this.zzosf.readLock().lock();
        try {
            zzfap zzfap = this.zzosc;
            if (zzfap == null || !zzfap.zzbl(str, str2)) {
                zzfap zzfap2 = this.zzosd;
                if (zzfap2 != null && zzfap2.zzbl(str, str2)) {
                    str3 = new String(this.zzosd.zzbm(str, str2), zzfar.UTF_8);
                }
            } else {
                str3 = new String(this.zzosc.zzbm(str, str2), zzfar.UTF_8);
            }
            return str3;
        } finally {
            this.zzosf.readLock().unlock();
        }
    }

    public FirebaseRemoteConfigValue getValue(String str) {
        return getValue(str, "configns:firebase");
    }

    public FirebaseRemoteConfigValue getValue(String str, String str2) {
        zzfar zzfar;
        if (str2 == null) {
            return new zzfar(DEFAULT_VALUE_FOR_BYTE_ARRAY, 0);
        }
        this.zzosf.readLock().lock();
        try {
            zzfap zzfap = this.zzosc;
            if (zzfap == null || !zzfap.zzbl(str, str2)) {
                zzfap zzfap2 = this.zzosd;
                if (zzfap2 == null || !zzfap2.zzbl(str, str2)) {
                    zzfar zzfar2 = new zzfar(DEFAULT_VALUE_FOR_BYTE_ARRAY, 0);
                    this.zzosf.readLock().unlock();
                    return zzfar2;
                }
                zzfar = new zzfar(this.zzosd.zzbm(str, str2), 1);
            } else {
                zzfar = new zzfar(this.zzosc.zzbm(str, str2), 2);
            }
            return zzfar;
        } finally {
            this.zzosf.readLock().unlock();
        }
    }

    public void setConfigSettings(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        this.zzosf.writeLock().lock();
        try {
            boolean isDeveloperModeEnabled = this.zzose.isDeveloperModeEnabled();
            boolean isDeveloperModeEnabled2 = firebaseRemoteConfigSettings == null ? false : firebaseRemoteConfigSettings.isDeveloperModeEnabled();
            this.zzose.zzdd(isDeveloperModeEnabled2);
            if (isDeveloperModeEnabled != isDeveloperModeEnabled2) {
                zzcnj();
            }
        } finally {
            this.zzosf.writeLock().unlock();
        }
    }

    public void setDefaults(int i) {
        setDefaults(i, "configns:firebase");
    }

    public void setDefaults(int i, String str) {
        if (str != null) {
            this.zzosf.readLock().lock();
            try {
                zzfas zzfas = this.zzose;
                if (!(zzfas == null || zzfas.zzcnn() == null || this.zzose.zzcnn().get(str) == null)) {
                    zzfam zzfam = this.zzose.zzcnn().get(str);
                    if (i == zzfam.getResourceId() && this.zzose.zzcno() == zzfam.zzcnk()) {
                        if (Log.isLoggable("FirebaseRemoteConfig", 3)) {
                            Log.d("FirebaseRemoteConfig", "Skipped setting defaults from resource file as this resource file was already applied.");
                        }
                        return;
                    }
                }
                this.zzosf.readLock().unlock();
                HashMap hashMap = new HashMap();
                try {
                    XmlResourceParser xml = this.mContext.getResources().getXml(i);
                    String str2 = null;
                    String str3 = null;
                    String str4 = null;
                    for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                        if (eventType == 2) {
                            str2 = xml.getName();
                        } else if (eventType == 3) {
                            if (!(!"entry".equals(xml.getName()) || str3 == null || str4 == null)) {
                                hashMap.put(str3, str4);
                                str3 = null;
                                str4 = null;
                            }
                            str2 = null;
                        } else if (eventType == 4) {
                            if ("key".equals(str2)) {
                                str3 = xml.getText();
                            } else if (FirebaseAnalytics.Param.VALUE.equals(str2)) {
                                str4 = xml.getText();
                            }
                        }
                    }
                    this.zzose.zza(str, new zzfam(i, this.zzose.zzcno()));
                    zzc(hashMap, str, false);
                } catch (Exception e) {
                    Log.e("FirebaseRemoteConfig", "Caught exception while parsing XML resource. Skipping setDefaults.", e);
                }
            } finally {
                this.zzosf.readLock().unlock();
            }
        } else if (Log.isLoggable("FirebaseRemoteConfig", 3)) {
            Log.d("FirebaseRemoteConfig", "namespace cannot be null for setDefaults.");
        }
    }

    public void setDefaults(Map<String, Object> map) {
        setDefaults(map, "configns:firebase");
    }

    public void setDefaults(Map<String, Object> map, String str) {
        zzc(map, str, true);
    }

    /* access modifiers changed from: package-private */
    public final void zza(TaskCompletionSource<Void> taskCompletionSource, zzbio zzbio) {
        if (zzbio == null || zzbio.getStatus() == null) {
            this.zzose.zziy(1);
            taskCompletionSource.setException(new FirebaseRemoteConfigFetchException());
            zzcnj();
            return;
        }
        int statusCode = zzbio.getStatus().getStatusCode();
        this.zzosf.writeLock().lock();
        if (statusCode != -6508) {
            if (statusCode != 6507) {
                if (statusCode != -6506) {
                    if (statusCode != -6505) {
                        switch (statusCode) {
                            case GamesStatusCodes.STATUS_MATCH_ERROR_INVALID_PARTICIPANT_STATE:
                            case GamesStatusCodes.STATUS_MATCH_ERROR_INACTIVE_MATCH:
                            case GamesStatusCodes.STATUS_MATCH_ERROR_OUT_OF_DATE_VERSION:
                            case GamesStatusCodes.STATUS_MATCH_ERROR_INVALID_MATCH_RESULTS:
                                this.zzose.zziy(1);
                                taskCompletionSource.setException(new FirebaseRemoteConfigFetchException());
                                break;
                            case GamesStatusCodes.STATUS_MATCH_ERROR_INVALID_MATCH_STATE:
                                break;
                            default:
                                try {
                                    if (zzbio.getStatus().isSuccess()) {
                                        StringBuilder sb = new StringBuilder(45);
                                        sb.append("Unknown (successful) status code: ");
                                        sb.append(statusCode);
                                        Log.w("FirebaseRemoteConfig", sb.toString());
                                    }
                                    this.zzose.zziy(1);
                                    taskCompletionSource.setException(new FirebaseRemoteConfigFetchException());
                                    break;
                                } catch (Throwable th) {
                                    this.zzosf.writeLock().unlock();
                                    throw th;
                                }
                        }
                    } else {
                        Map<String, Set<String>> zzaom = zzbio.zzaom();
                        HashMap hashMap = new HashMap();
                        for (String next : zzaom.keySet()) {
                            HashMap hashMap2 = new HashMap();
                            for (String str : zzaom.get(next)) {
                                hashMap2.put(str, zzbio.zza(str, (byte[]) null, next));
                            }
                            hashMap.put(next, hashMap2);
                        }
                        this.zzosb = new zzfap(hashMap, System.currentTimeMillis(), zzbio.zzaol());
                        this.zzose.zziy(-1);
                        taskCompletionSource.setResult(null);
                    }
                    zzcnj();
                    this.zzosf.writeLock().unlock();
                }
            }
            this.zzose.zziy(2);
            taskCompletionSource.setException(new FirebaseRemoteConfigFetchThrottledException(zzbio.getThrottleEndTimeMillis()));
            zzcnj();
            this.zzosf.writeLock().unlock();
        }
        this.zzose.zziy(-1);
        zzfap zzfap = this.zzosb;
        if (zzfap != null && !zzfap.zzcnm()) {
            Map<String, Set<String>> zzaom2 = zzbio.zzaom();
            HashMap hashMap3 = new HashMap();
            for (String next2 : zzaom2.keySet()) {
                HashMap hashMap4 = new HashMap();
                for (String str2 : zzaom2.get(next2)) {
                    hashMap4.put(str2, zzbio.zza(str2, (byte[]) null, next2));
                }
                hashMap3.put(next2, hashMap4);
            }
            this.zzosb = new zzfap(hashMap3, this.zzosb.getTimestamp(), zzbio.zzaol());
        }
        taskCompletionSource.setResult(null);
        zzcnj();
        this.zzosf.writeLock().unlock();
    }
}
