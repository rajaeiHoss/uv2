package com.google.android.gms.internal;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import androidx.collection.ArrayMap;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.common.api.internal.zzbz;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

public class zzckj {
    private static volatile zzckj zzjnr;
    private boolean initialized = false;
    private final Context zzaiq;
    private final zze zzdir;
    private final long zzjgk;
    private final zzcik zzjns;
    private final zzcju zzjnt;
    private final zzcjj zzjnu;
    private final zzcke zzjnv;
    private final zzcnd zzjnw;
    private final zzckd zzjnx;
    private final AppMeasurement zzjny;
    private final FirebaseAnalytics zzjnz;
    private final zzcno zzjoa;
    private final zzcjh zzjob;
    private final zzcjn zzjoc;
    private final zzcma zzjod;
    private final zzclk zzjoe;
    private final zzcia zzjof;
    private zzcil zzjog;
    private zzcjf zzjoh;
    private zzcme zzjoi;
    private zzcir zzjoj;
    private zzcje zzjok;
    private zzcjs zzjol;
    private zzcnj zzjom;
    private zzcih zzjon;
    private boolean zzjoo;
    private Boolean zzjop;
    private long zzjoq;
    private FileLock zzjor;
    private FileChannel zzjos;
    private List<Long> zzjot;
    private List<Runnable> zzjou;
    private int zzjov;
    private int zzjow;
    private long zzjox;
    private long zzjoy;
    private boolean zzjoz;
    private boolean zzjpa;
    private boolean zzjpb;

    class zza implements zzcin {
        List<zzcob> zzaoz;
        zzcoe zzjpe;
        List<Long> zzjpf;
        private long zzjpg;

        private zza() {
        }

        /* synthetic */ zza(zzckj zzckj, zzckk zzckk) {
            this();
        }

        private long zza(zzcob zzcob) {
            return ((zzcob.zzjuj.longValue() / 1000) / 60) / 60;
        }

        public final boolean zza(long j, zzcob zzcob) {
            zzbq.checkNotNull(zzcob);
            if (this.zzaoz == null) {
                this.zzaoz = new ArrayList();
            }
            if (this.zzjpf == null) {
                this.zzjpf = new ArrayList();
            }
            if (this.zzaoz.size() > 0 && zza(this.zzaoz.get(0)) != zza(zzcob)) {
                return false;
            }
            long zzhs = this.zzjpg + ((long) zzcob.zzhs());
            if (zzhs >= ((long) Math.max(0, zzciz.zzjiv.get().intValue()))) {
                return false;
            }
            this.zzjpg = zzhs;
            this.zzaoz.add(zzcob);
            this.zzjpf.add(Long.valueOf(j));
            return this.zzaoz.size() < Math.max(1, zzciz.zzjiw.get().intValue());
        }

        public final void zzb(zzcoe zzcoe) {
            zzbq.checkNotNull(zzcoe);
            this.zzjpe = zzcoe;
        }
    }

    private zzckj(zzclj zzclj) {
        String str;
        zzcjl zzcjl;
        zzbq.checkNotNull(zzclj);
        Context context = zzclj.zzaiq;
        this.zzaiq = context;
        this.zzjox = -1;
        zze zzanq = zzi.zzanq();
        this.zzdir = zzanq;
        this.zzjgk = zzanq.currentTimeMillis();
        this.zzjns = new zzcik(this);
        zzcju zzcju = new zzcju(this);
        zzcju.initialize();
        this.zzjnt = zzcju;
        zzcjj zzcjj = new zzcjj(this);
        zzcjj.initialize();
        this.zzjnu = zzcjj;
        zzcno zzcno = new zzcno(this);
        zzcno.initialize();
        this.zzjoa = zzcno;
        zzcjh zzcjh = new zzcjh(this);
        zzcjh.initialize();
        this.zzjob = zzcjh;
        this.zzjof = new zzcia(this);
        zzcjn zzcjn = new zzcjn(this);
        zzcjn.initialize();
        this.zzjoc = zzcjn;
        zzcma zzcma = new zzcma(this);
        zzcma.initialize();
        this.zzjod = zzcma;
        zzclk zzclk = new zzclk(this);
        zzclk.initialize();
        this.zzjoe = zzclk;
        this.zzjny = new AppMeasurement(this);
        this.zzjnz = new FirebaseAnalytics(this);
        zzcnd zzcnd = new zzcnd(this);
        zzcnd.initialize();
        this.zzjnw = zzcnd;
        zzckd zzckd = new zzckd(this);
        zzckd.initialize();
        this.zzjnx = zzckd;
        zzcke zzcke = new zzcke(this);
        zzcke.initialize();
        this.zzjnv = zzcke;
        if (context.getApplicationContext() instanceof Application) {
            zzclk zzayd = zzayd();
            if (zzayd.getContext().getApplicationContext() instanceof Application) {
                Application application = (Application) zzayd.getContext().getApplicationContext();
                if (zzayd.zzjpt == null) {
                    zzayd.zzjpt = new zzcly(zzayd, (zzcll) null);
                }
                application.unregisterActivityLifecycleCallbacks(zzayd.zzjpt);
                application.registerActivityLifecycleCallbacks(zzayd.zzjpt);
                zzcjl = zzayd.zzayp().zzbba();
                str = "Registered activity lifecycle callback";
            }
            zzcke.zzh(new zzckk(this, zzclj));
        }
        zzcjl = zzayp().zzbaw();
        str = "Application context is not an Application";
        zzcjl.log(str);
        zzcke.zzh(new zzckk(this, zzclj));
    }

    private final int zza(FileChannel fileChannel) {
        zzayo().zzwj();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzayp().zzbau().log("Bad channel to read from");
            return 0;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        try {
            fileChannel.position(0);
            int read = fileChannel.read(allocate);
            if (read != 4) {
                if (read != -1) {
                    zzayp().zzbaw().zzj("Unexpected data length. Bytes read", Integer.valueOf(read));
                }
                return 0;
            }
            allocate.flip();
            return allocate.getInt();
        } catch (IOException e) {
            zzayp().zzbau().zzj("Failed to read from channel", e);
            return 0;
        }
    }

    private final zzcif zza(Context context, String str, String str2, boolean z, boolean z2) {
        String str3;
        String str4;
        int i;
        String str5 = str;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            zzayp().zzbau().log("PackageManager is null, can not log app install information");
            return null;
        }
        try {
            str3 = packageManager.getInstallerPackageName(str5);
        } catch (IllegalArgumentException unused) {
            zzayp().zzbau().zzj("Error retrieving installer package name. appId", zzcjj.zzjs(str));
            str3 = "Unknown";
        }
        if (str3 == null) {
            str3 = "manual_install";
        } else if ("com.android.vending".equals(str3)) {
            str3 = "";
        }
        String str6 = str3;
        try {
            PackageInfo packageInfo = zzbih.zzdd(context).getPackageInfo(str5, 0);
            if (packageInfo != null) {
                CharSequence zzhc = zzbih.zzdd(context).zzhc(str5);
                if (!TextUtils.isEmpty(zzhc)) {
                    String charSequence = zzhc.toString();
                }
                String str7 = packageInfo.versionName;
                i = packageInfo.versionCode;
                str4 = str7;
            } else {
                i = Integer.MIN_VALUE;
                str4 = "Unknown";
            }
            return new zzcif(str, str2, str4, (long) i, str6, 12211, zzayl().zzab(context, str5), (String) null, z, false, "", 0, 0, 0, z2);
        } catch (PackageManager.NameNotFoundException unused2) {
            zzayp().zzbau().zze("Error retrieving newly installed package info. appId, appName", zzcjj.zzjs(str), "Unknown");
            return null;
        }
    }

    private static void zza(zzclh zzclh) {
        if (zzclh == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    private static void zza(zzcli zzcli) {
        if (zzcli == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzcli.isInitialized()) {
            String valueOf = String.valueOf(zzcli.getClass());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
            sb.append("Component not initialized: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    /* access modifiers changed from: private */
    public final void zza(zzclj zzclj) {
        zzcjl zzcjl;
        String str;
        zzayo().zzwj();
        zzcir zzcir = new zzcir(this);
        zzcir.initialize();
        this.zzjoj = zzcir;
        zzcje zzcje = new zzcje(this);
        zzcje.initialize();
        this.zzjok = zzcje;
        zzcil zzcil = new zzcil(this);
        zzcil.initialize();
        this.zzjog = zzcil;
        zzcjf zzcjf = new zzcjf(this);
        zzcjf.initialize();
        this.zzjoh = zzcjf;
        zzcih zzcih = new zzcih(this);
        zzcih.initialize();
        this.zzjon = zzcih;
        zzcme zzcme = new zzcme(this);
        zzcme.initialize();
        this.zzjoi = zzcme;
        zzcnj zzcnj = new zzcnj(this);
        zzcnj.initialize();
        this.zzjom = zzcnj;
        this.zzjol = new zzcjs(this);
        this.zzjoa.zzbcf();
        this.zzjnt.zzbcf();
        this.zzjok.zzbcf();
        zzayp().zzbay().zzj("App measurement is starting up, version", 12211L);
        zzayp().zzbay().log("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        String appId = zzcje.getAppId();
        if (zzayl().zzkq(appId)) {
            zzcjl = zzayp().zzbay();
            str = "Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.";
        } else {
            zzcjl zzbay = zzayp().zzbay();
            String valueOf = String.valueOf(appId);
            zzcjl zzcjl2 = zzbay;
            str = valueOf.length() != 0 ? "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ".concat(valueOf) : new String("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ");
            zzcjl = zzcjl2;
        }
        zzcjl.log(str);
        zzayp().zzbaz().log("Debug-level message logging enabled");
        if (this.zzjov != this.zzjow) {
            zzayp().zzbau().zze("Not all components initialized", Integer.valueOf(this.zzjov), Integer.valueOf(this.zzjow));
        }
        this.initialized = true;
    }

    private final boolean zza(int i, FileChannel fileChannel) {
        zzayo().zzwj();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzayp().zzbau().log("Bad channel to read from");
            return false;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        allocate.flip();
        try {
            fileChannel.truncate(0);
            fileChannel.write(allocate);
            fileChannel.force(true);
            if (fileChannel.size() != 4) {
                zzayp().zzbau().zzj("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            }
            return true;
        } catch (IOException e) {
            zzayp().zzbau().zzj("Failed to write to channel", e);
            return false;
        }
    }

    private static boolean zza(zzcob zzcob, String str, Object obj) {
        if (!TextUtils.isEmpty(str) && obj != null) {
            zzcoc[] zzcocArr = zzcob.zzjui;
            int length = zzcocArr.length;
            int i = 0;
            while (i < length) {
                zzcoc zzcoc = zzcocArr[i];
                if (!str.equals(zzcoc.name)) {
                    i++;
                } else if ((obj instanceof Long) && obj.equals(zzcoc.zzjum)) {
                    return true;
                } else {
                    if (!(obj instanceof String) || !obj.equals(zzcoc.zzgim)) {
                        return (obj instanceof Double) && obj.equals(zzcoc.zzjsl);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private final boolean zza(String str, zzcix zzcix) {
        long j;
        zzcnn zzcnn;
        String string = zzcix.zzjhr.getString(FirebaseAnalytics.Param.CURRENCY);
        if (FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(zzcix.name)) {
            double doubleValue = zzcix.zzjhr.getDouble(FirebaseAnalytics.Param.VALUE).doubleValue() * 1000000.0d;
            if (doubleValue == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                doubleValue = ((double) zzcix.zzjhr.getLong(FirebaseAnalytics.Param.VALUE).longValue()) * 1000000.0d;
            }
            if (doubleValue > 9.223372036854776E18d || doubleValue < -9.223372036854776E18d) {
                zzayp().zzbaw().zze("Data lost. Currency value is too big. appId", zzcjj.zzjs(str), Double.valueOf(doubleValue));
                return false;
            }
            j = Math.round(doubleValue);
        } else {
            j = zzcix.zzjhr.getLong(FirebaseAnalytics.Param.VALUE).longValue();
        }
        if (!TextUtils.isEmpty(string)) {
            String upperCase = string.toUpperCase(Locale.US);
            if (upperCase.matches("[A-Z]{3}")) {
                String valueOf = String.valueOf(upperCase);
                String concat = valueOf.length() != 0 ? "_ltv_".concat(valueOf) : new String("_ltv_");
                zzcnn zzag = zzayj().zzag(str, concat);
                if (zzag == null || !(zzag.value instanceof Long)) {
                    zzcil zzayj = zzayj();
                    int zzb = this.zzjns.zzb(str, zzciz.zzjjr) - 1;
                    zzbq.zzgv(str);
                    zzayj.zzwj();
                    zzayj.zzyk();
                    try {
                        zzayj.getWritableDatabase().execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);", new String[]{str, str, String.valueOf(zzb)});
                    } catch (SQLiteException e) {
                        zzayj.zzayp().zzbau().zze("Error pruning currencies. appId", zzcjj.zzjs(str), e);
                    }
                    zzcnn = new zzcnn(str, zzcix.zzjgm, concat, this.zzdir.currentTimeMillis(), Long.valueOf(j));
                } else {
                    zzcnn = new zzcnn(str, zzcix.zzjgm, concat, this.zzdir.currentTimeMillis(), Long.valueOf(((Long) zzag.value).longValue() + j));
                }
                if (!zzayj().zza(zzcnn)) {
                    zzayp().zzbau().zzd("Too many unique user properties are set. Ignoring user property. appId", zzcjj.zzjs(str), zzayk().zzjr(zzcnn.name), zzcnn.value);
                    zzayl().zza(str, 9, (String) null, (String) null, 0);
                }
            }
        }
        return true;
    }

    private final zzcoa[] zza(String str, zzcog[] zzcogArr, zzcob[] zzcobArr) {
        zzbq.zzgv(str);
        return zzayc().zza(str, zzcobArr, zzcogArr);
    }

    static void zzaxz() {
        throw new IllegalStateException("Unexpected call on client side");
    }

    private final void zzb(zzcie zzcie) {
        ArrayMap arrayMap;
        zzayo().zzwj();
        if (TextUtils.isEmpty(zzcie.getGmpAppId())) {
            zzb(zzcie.getAppId(), 204, (Throwable) null, (byte[]) null, (Map<String, List<String>>) null);
            return;
        }
        String gmpAppId = zzcie.getGmpAppId();
        String appInstanceId = zzcie.getAppInstanceId();
        Uri.Builder builder = new Uri.Builder();
        Uri.Builder encodedAuthority = builder.scheme(zzciz.zzjir.get()).encodedAuthority(zzciz.zzjis.get());
        String valueOf = String.valueOf(gmpAppId);
        encodedAuthority.path(valueOf.length() != 0 ? "config/app/".concat(valueOf) : new String("config/app/")).appendQueryParameter("app_instance_id", appInstanceId).appendQueryParameter("platform", "android").appendQueryParameter("gmp_version", "12211");
        String uri = builder.build().toString();
        try {
            URL url = new URL(uri);
            zzayp().zzbba().zzj("Fetching remote configuration", zzcie.getAppId());
            zzcny zzka = zzaym().zzka(zzcie.getAppId());
            String zzkb = zzaym().zzkb(zzcie.getAppId());
            if (zzka == null || TextUtils.isEmpty(zzkb)) {
                arrayMap = null;
            } else {
                ArrayMap arrayMap2 = new ArrayMap();
                arrayMap2.put("If-Modified-Since", zzkb);
                arrayMap = arrayMap2;
            }
            this.zzjoz = true;
            zzcjn zzbbs = zzbbs();
            String appId = zzcie.getAppId();
            zzckn zzckn = new zzckn(this);
            zzbbs.zzwj();
            zzbbs.zzyk();
            zzbq.checkNotNull(url);
            zzbq.checkNotNull(zzckn);
            zzbbs.zzayo().zzi(new zzcjr(zzbbs, appId, url, (byte[]) null, arrayMap, zzckn));
        } catch (MalformedURLException unused) {
            zzayp().zzbau().zze("Failed to parse config URL. Not fetching. appId", zzcjj.zzjs(zzcie.getAppId()), uri);
        }
    }

    private final zzcjs zzbbt() {
        zzcjs zzcjs = this.zzjol;
        if (zzcjs != null) {
            return zzcjs;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    private final zzcnj zzbbu() {
        zza((zzcli) this.zzjom);
        return this.zzjom;
    }

    private final boolean zzbbv() {
        zzayo().zzwj();
        try {
            FileChannel channel = new RandomAccessFile(new File(this.zzaiq.getFilesDir(), "google_app_measurement.db"), "rw").getChannel();
            this.zzjos = channel;
            FileLock tryLock = channel.tryLock();
            this.zzjor = tryLock;
            if (tryLock != null) {
                zzayp().zzbba().log("Storage concurrent access okay");
                return true;
            }
            zzayp().zzbau().log("Storage concurrent data access panic");
            return false;
        } catch (FileNotFoundException e) {
            zzayp().zzbau().zzj("Failed to acquire storage lock", e);
            return false;
        } catch (IOException e2) {
            zzayp().zzbau().zzj("Failed to access storage lock file", e2);
            return false;
        }
    }

    private final long zzbbx() {
        long currentTimeMillis = this.zzdir.currentTimeMillis();
        zzcju zzayq = zzayq();
        zzayq.zzyk();
        zzayq.zzwj();
        long j = zzayq.zzjlr.get();
        if (j == 0) {
            j = (long) (zzayq.zzayl().zzbcr().nextInt(86400000) + 1);
            zzayq.zzjlr.set(j);
        }
        return ((((currentTimeMillis + j) / 1000) / 60) / 60) / 24;
    }

    private final boolean zzbbz() {
        zzayo().zzwj();
        zzyk();
        return zzayj().zzbab() || !TextUtils.isEmpty(zzayj().zzazw());
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x017f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzbca() {
        /*
            r19 = this;
            r0 = r19
            com.google.android.gms.internal.zzcke r1 = r19.zzayo()
            r1.zzwj()
            r19.zzyk()
            boolean r1 = r19.zzbcd()
            if (r1 != 0) goto L_0x0013
            return
        L_0x0013:
            long r1 = r0.zzjoy
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x0052
            com.google.android.gms.common.util.zze r1 = r0.zzdir
            long r1 = r1.elapsedRealtime()
            r5 = 3600000(0x36ee80, double:1.7786363E-317)
            long r7 = r0.zzjoy
            long r1 = r1 - r7
            long r1 = java.lang.Math.abs(r1)
            long r5 = r5 - r1
            int r1 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x0050
            com.google.android.gms.internal.zzcjj r1 = r19.zzayp()
            com.google.android.gms.internal.zzcjl r1 = r1.zzbba()
            java.lang.Long r2 = java.lang.Long.valueOf(r5)
            java.lang.String r3 = "Upload has been suspended. Will update scheduling later in approximately ms"
            r1.zzj(r3, r2)
            com.google.android.gms.internal.zzcjs r1 = r19.zzbbt()
            r1.unregister()
            com.google.android.gms.internal.zzcnj r1 = r19.zzbbu()
            r1.cancel()
            return
        L_0x0050:
            r0.zzjoy = r3
        L_0x0052:
            boolean r1 = r19.zzbbn()
            if (r1 == 0) goto L_0x0218
            boolean r1 = r19.zzbbz()
            if (r1 != 0) goto L_0x0060
            goto L_0x0218
        L_0x0060:
            com.google.android.gms.common.util.zze r1 = r0.zzdir
            long r1 = r1.currentTimeMillis()
            com.google.android.gms.internal.zzcja<java.lang.Long> r5 = com.google.android.gms.internal.zzciz.zzjjn
            java.lang.Object r5 = r5.get()
            java.lang.Long r5 = (java.lang.Long) r5
            long r5 = r5.longValue()
            long r5 = java.lang.Math.max(r3, r5)
            com.google.android.gms.internal.zzcil r7 = r19.zzayj()
            boolean r7 = r7.zzbac()
            if (r7 != 0) goto L_0x008d
            com.google.android.gms.internal.zzcil r7 = r19.zzayj()
            boolean r7 = r7.zzazx()
            if (r7 == 0) goto L_0x008b
            goto L_0x008d
        L_0x008b:
            r7 = 0
            goto L_0x008e
        L_0x008d:
            r7 = 1
        L_0x008e:
            if (r7 == 0) goto L_0x00aa
            com.google.android.gms.internal.zzcik r10 = r0.zzjns
            java.lang.String r10 = r10.zzazu()
            boolean r11 = android.text.TextUtils.isEmpty(r10)
            if (r11 != 0) goto L_0x00a7
            java.lang.String r11 = ".none."
            boolean r10 = r11.equals(r10)
            if (r10 != 0) goto L_0x00a7
            com.google.android.gms.internal.zzcja<java.lang.Long> r10 = com.google.android.gms.internal.zzciz.zzjji
            goto L_0x00ac
        L_0x00a7:
            com.google.android.gms.internal.zzcja<java.lang.Long> r10 = com.google.android.gms.internal.zzciz.zzjjh
            goto L_0x00ac
        L_0x00aa:
            com.google.android.gms.internal.zzcja<java.lang.Long> r10 = com.google.android.gms.internal.zzciz.zzjjg
        L_0x00ac:
            java.lang.Object r10 = r10.get()
            java.lang.Long r10 = (java.lang.Long) r10
            long r10 = r10.longValue()
            long r10 = java.lang.Math.max(r3, r10)
            com.google.android.gms.internal.zzcju r12 = r19.zzayq()
            com.google.android.gms.internal.zzcjx r12 = r12.zzjln
            long r12 = r12.get()
            com.google.android.gms.internal.zzcju r14 = r19.zzayq()
            com.google.android.gms.internal.zzcjx r14 = r14.zzjlo
            long r14 = r14.get()
            com.google.android.gms.internal.zzcil r16 = r19.zzayj()
            long r8 = r16.zzazz()
            com.google.android.gms.internal.zzcil r16 = r19.zzayj()
            r17 = r10
            long r10 = r16.zzbaa()
            long r8 = java.lang.Math.max(r8, r10)
            int r10 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r10 != 0) goto L_0x00eb
        L_0x00e8:
            r5 = r3
            goto L_0x015f
        L_0x00eb:
            long r8 = r8 - r1
            long r8 = java.lang.Math.abs(r8)
            long r8 = r1 - r8
            long r12 = r12 - r1
            long r10 = java.lang.Math.abs(r12)
            long r10 = r1 - r10
            long r14 = r14 - r1
            long r12 = java.lang.Math.abs(r14)
            long r1 = r1 - r12
            long r10 = java.lang.Math.max(r10, r1)
            long r5 = r5 + r8
            if (r7 == 0) goto L_0x0110
            int r7 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r7 <= 0) goto L_0x0110
            long r5 = java.lang.Math.min(r8, r10)
            long r5 = r5 + r17
        L_0x0110:
            com.google.android.gms.internal.zzcno r7 = r19.zzayl()
            r12 = r17
            boolean r7 = r7.zzf(r10, r12)
            if (r7 != 0) goto L_0x011e
            long r5 = r10 + r12
        L_0x011e:
            int r7 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r7 == 0) goto L_0x015f
            int r7 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r7 < 0) goto L_0x015f
            r7 = 0
        L_0x0127:
            r8 = 20
            com.google.android.gms.internal.zzcja<java.lang.Integer> r9 = com.google.android.gms.internal.zzciz.zzjjp
            java.lang.Object r9 = r9.get()
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r10 = 0
            int r9 = java.lang.Math.max(r10, r9)
            int r8 = java.lang.Math.min(r8, r9)
            if (r7 >= r8) goto L_0x00e8
            r8 = 1
            int r9 = r8 << r7
            long r11 = (long) r9
            com.google.android.gms.internal.zzcja<java.lang.Long> r9 = com.google.android.gms.internal.zzciz.zzjjo
            java.lang.Object r9 = r9.get()
            java.lang.Long r9 = (java.lang.Long) r9
            long r13 = r9.longValue()
            long r13 = java.lang.Math.max(r3, r13)
            long r13 = r13 * r11
            long r5 = r5 + r13
            int r9 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r9 <= 0) goto L_0x015c
            goto L_0x015f
        L_0x015c:
            int r7 = r7 + 1
            goto L_0x0127
        L_0x015f:
            int r1 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x017f
            com.google.android.gms.internal.zzcjj r1 = r19.zzayp()
            com.google.android.gms.internal.zzcjl r1 = r1.zzbba()
            java.lang.String r2 = "Next upload time is 0"
            r1.log(r2)
            com.google.android.gms.internal.zzcjs r1 = r19.zzbbt()
            r1.unregister()
            com.google.android.gms.internal.zzcnj r1 = r19.zzbbu()
            r1.cancel()
            return
        L_0x017f:
            com.google.android.gms.internal.zzcjn r1 = r19.zzbbs()
            boolean r1 = r1.zzaax()
            if (r1 != 0) goto L_0x01a5
            com.google.android.gms.internal.zzcjj r1 = r19.zzayp()
            com.google.android.gms.internal.zzcjl r1 = r1.zzbba()
            java.lang.String r2 = "No network"
            r1.log(r2)
            com.google.android.gms.internal.zzcjs r1 = r19.zzbbt()
            r1.zzaau()
            com.google.android.gms.internal.zzcnj r1 = r19.zzbbu()
            r1.cancel()
            return
        L_0x01a5:
            com.google.android.gms.internal.zzcju r1 = r19.zzayq()
            com.google.android.gms.internal.zzcjx r1 = r1.zzjlp
            long r1 = r1.get()
            com.google.android.gms.internal.zzcja<java.lang.Long> r7 = com.google.android.gms.internal.zzciz.zzjje
            java.lang.Object r7 = r7.get()
            java.lang.Long r7 = (java.lang.Long) r7
            long r7 = r7.longValue()
            long r7 = java.lang.Math.max(r3, r7)
            com.google.android.gms.internal.zzcno r9 = r19.zzayl()
            boolean r9 = r9.zzf(r1, r7)
            if (r9 != 0) goto L_0x01ce
            long r1 = r1 + r7
            long r5 = java.lang.Math.max(r5, r1)
        L_0x01ce:
            com.google.android.gms.internal.zzcjs r1 = r19.zzbbt()
            r1.unregister()
            com.google.android.gms.common.util.zze r1 = r0.zzdir
            long r1 = r1.currentTimeMillis()
            long r5 = r5 - r1
            int r1 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r1 > 0) goto L_0x01ff
            com.google.android.gms.internal.zzcja<java.lang.Long> r1 = com.google.android.gms.internal.zzciz.zzjjj
            java.lang.Object r1 = r1.get()
            java.lang.Long r1 = (java.lang.Long) r1
            long r1 = r1.longValue()
            long r5 = java.lang.Math.max(r3, r1)
            com.google.android.gms.internal.zzcju r1 = r19.zzayq()
            com.google.android.gms.internal.zzcjx r1 = r1.zzjln
            com.google.android.gms.common.util.zze r2 = r0.zzdir
            long r2 = r2.currentTimeMillis()
            r1.set(r2)
        L_0x01ff:
            com.google.android.gms.internal.zzcjj r1 = r19.zzayp()
            com.google.android.gms.internal.zzcjl r1 = r1.zzbba()
            java.lang.Long r2 = java.lang.Long.valueOf(r5)
            java.lang.String r3 = "Upload scheduled in approximately ms"
            r1.zzj(r3, r2)
            com.google.android.gms.internal.zzcnj r1 = r19.zzbbu()
            r1.zzs(r5)
            return
        L_0x0218:
            com.google.android.gms.internal.zzcjj r1 = r19.zzayp()
            com.google.android.gms.internal.zzcjl r1 = r1.zzbba()
            java.lang.String r2 = "Nothing to upload or uploading impossible"
            r1.log(r2)
            com.google.android.gms.internal.zzcjs r1 = r19.zzbbt()
            r1.unregister()
            com.google.android.gms.internal.zzcnj r1 = r19.zzbbu()
            r1.cancel()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzckj.zzbca():void");
    }

    private final boolean zzbcd() {
        zzayo().zzwj();
        zzyk();
        return this.zzjoo;
    }

    private final void zzbce() {
        zzayo().zzwj();
        if (this.zzjoz || this.zzjpa || this.zzjpb) {
            zzayp().zzbba().zzd("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzjoz), Boolean.valueOf(this.zzjpa), Boolean.valueOf(this.zzjpb));
            return;
        }
        zzayp().zzbba().log("Stopping uploading service(s)");
        List<Runnable> list = this.zzjou;
        if (list != null) {
            for (Runnable run : list) {
                run.run();
            }
            this.zzjou.clear();
        }
    }

    private final Boolean zzc(zzcie zzcie) {
        try {
            if (zzcie.zzayx() != -2147483648L) {
                if (zzcie.zzayx() == ((long) zzbih.zzdd(this.zzaiq).getPackageInfo(zzcie.getAppId(), 0).versionCode)) {
                    return true;
                }
            } else {
                String str = zzbih.zzdd(this.zzaiq).getPackageInfo(zzcie.getAppId(), 0).versionName;
                if (zzcie.zzwo() != null && zzcie.zzwo().equals(str)) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:144:0x056f A[Catch:{ IOException -> 0x0572, all -> 0x05d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x0598 A[Catch:{ IOException -> 0x0572, all -> 0x05d7 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzc(com.google.android.gms.internal.zzcix r33, com.google.android.gms.internal.zzcif r34) {
        /*
            r32 = this;
            r11 = r32
            r0 = r33
            r12 = r34
            com.google.android.gms.common.internal.zzbq.checkNotNull(r34)
            java.lang.String r1 = r12.packageName
            com.google.android.gms.common.internal.zzbq.zzgv(r1)
            long r13 = java.lang.System.nanoTime()
            com.google.android.gms.internal.zzcke r1 = r32.zzayo()
            r1.zzwj()
            r32.zzyk()
            java.lang.String r15 = r12.packageName
            r32.zzayl()
            boolean r1 = com.google.android.gms.internal.zzcno.zzd((com.google.android.gms.internal.zzcix) r33, (com.google.android.gms.internal.zzcif) r34)
            if (r1 != 0) goto L_0x0028
            return
        L_0x0028:
            boolean r1 = r12.zzjfv
            if (r1 != 0) goto L_0x0030
            r11.zzg(r12)
            return
        L_0x0030:
            com.google.android.gms.internal.zzckd r1 = r32.zzaym()
            java.lang.String r2 = r0.name
            boolean r1 = r1.zzan(r15, r2)
            java.lang.String r2 = "_err"
            r10 = 0
            r29 = 1
            if (r1 == 0) goto L_0x00ce
            com.google.android.gms.internal.zzcjj r1 = r32.zzayp()
            com.google.android.gms.internal.zzcjl r1 = r1.zzbaw()
            java.lang.Object r3 = com.google.android.gms.internal.zzcjj.zzjs(r15)
            com.google.android.gms.internal.zzcjh r4 = r32.zzayk()
            java.lang.String r5 = r0.name
            java.lang.String r4 = r4.zzjp(r5)
            java.lang.String r5 = "Dropping blacklisted event. appId"
            r1.zze(r5, r3, r4)
            com.google.android.gms.internal.zzcno r1 = r32.zzayl()
            boolean r1 = r1.zzks(r15)
            if (r1 != 0) goto L_0x0070
            com.google.android.gms.internal.zzcno r1 = r32.zzayl()
            boolean r1 = r1.zzkt(r15)
            if (r1 == 0) goto L_0x0071
        L_0x0070:
            r10 = 1
        L_0x0071:
            if (r10 != 0) goto L_0x008a
            java.lang.String r1 = r0.name
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x008a
            com.google.android.gms.internal.zzcno r2 = r32.zzayl()
            r4 = 11
            java.lang.String r6 = r0.name
            r7 = 0
            java.lang.String r5 = "_ev"
            r3 = r15
            r2.zza((java.lang.String) r3, (int) r4, (java.lang.String) r5, (java.lang.String) r6, (int) r7)
        L_0x008a:
            if (r10 == 0) goto L_0x00cd
            com.google.android.gms.internal.zzcil r0 = r32.zzayj()
            com.google.android.gms.internal.zzcie r0 = r0.zzjj(r15)
            if (r0 == 0) goto L_0x00cd
            long r1 = r0.zzaze()
            long r3 = r0.zzazd()
            long r1 = java.lang.Math.max(r1, r3)
            com.google.android.gms.common.util.zze r3 = r11.zzdir
            long r3 = r3.currentTimeMillis()
            long r3 = r3 - r1
            long r1 = java.lang.Math.abs(r3)
            com.google.android.gms.internal.zzcja<java.lang.Long> r3 = com.google.android.gms.internal.zzciz.zzjjm
            java.lang.Object r3 = r3.get()
            java.lang.Long r3 = (java.lang.Long) r3
            long r3 = r3.longValue()
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x00cd
            com.google.android.gms.internal.zzcjj r1 = r32.zzayp()
            com.google.android.gms.internal.zzcjl r1 = r1.zzbaz()
            java.lang.String r2 = "Fetching config for blacklisted app"
            r1.log(r2)
            r11.zzb((com.google.android.gms.internal.zzcie) r0)
        L_0x00cd:
            return
        L_0x00ce:
            com.google.android.gms.internal.zzcjj r1 = r32.zzayp()
            r9 = 2
            boolean r1 = r1.zzae(r9)
            if (r1 == 0) goto L_0x00ee
            com.google.android.gms.internal.zzcjj r1 = r32.zzayp()
            com.google.android.gms.internal.zzcjl r1 = r1.zzbba()
            com.google.android.gms.internal.zzcjh r3 = r32.zzayk()
            java.lang.String r3 = r3.zzb((com.google.android.gms.internal.zzcix) r0)
            java.lang.String r4 = "Logging event"
            r1.zzj(r4, r3)
        L_0x00ee:
            com.google.android.gms.internal.zzcil r1 = r32.zzayj()
            r1.beginTransaction()
            r11.zzg(r12)     // Catch:{ all -> 0x05d7 }
            java.lang.String r1 = "_iap"
            java.lang.String r3 = r0.name     // Catch:{ all -> 0x05d7 }
            boolean r1 = r1.equals(r3)     // Catch:{ all -> 0x05d7 }
            if (r1 != 0) goto L_0x010c
            java.lang.String r1 = "ecommerce_purchase"
            java.lang.String r3 = r0.name     // Catch:{ all -> 0x05d7 }
            boolean r1 = r1.equals(r3)     // Catch:{ all -> 0x05d7 }
            if (r1 == 0) goto L_0x0121
        L_0x010c:
            boolean r1 = r11.zza((java.lang.String) r15, (com.google.android.gms.internal.zzcix) r0)     // Catch:{ all -> 0x05d7 }
            if (r1 != 0) goto L_0x0121
            com.google.android.gms.internal.zzcil r0 = r32.zzayj()     // Catch:{ all -> 0x05d7 }
            r0.setTransactionSuccessful()     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcil r0 = r32.zzayj()
            r0.endTransaction()
            return
        L_0x0121:
            java.lang.String r1 = r0.name     // Catch:{ all -> 0x05d7 }
            boolean r16 = com.google.android.gms.internal.zzcno.zzkh(r1)     // Catch:{ all -> 0x05d7 }
            java.lang.String r1 = r0.name     // Catch:{ all -> 0x05d7 }
            boolean r1 = r2.equals(r1)     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcil r2 = r32.zzayj()     // Catch:{ all -> 0x05d7 }
            long r3 = r32.zzbbx()     // Catch:{ all -> 0x05d7 }
            r6 = 1
            r8 = 0
            r17 = 0
            r5 = r15
            r7 = r16
            r9 = r1
            r30 = r13
            r13 = 0
            r10 = r17
            com.google.android.gms.internal.zzcim r2 = r2.zza(r3, r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x05d7 }
            long r3 = r2.zzjhf     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcja<java.lang.Integer> r5 = com.google.android.gms.internal.zzciz.zzjix     // Catch:{ all -> 0x05d7 }
            java.lang.Object r5 = r5.get()     // Catch:{ all -> 0x05d7 }
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch:{ all -> 0x05d7 }
            int r5 = r5.intValue()     // Catch:{ all -> 0x05d7 }
            long r5 = (long) r5     // Catch:{ all -> 0x05d7 }
            long r3 = r3 - r5
            r5 = 1000(0x3e8, double:4.94E-321)
            r7 = 1
            r9 = 0
            int r14 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r14 <= 0) goto L_0x018b
            long r3 = r3 % r5
            int r0 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r0 != 0) goto L_0x017c
            com.google.android.gms.internal.zzcjj r0 = r32.zzayp()     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcjl r0 = r0.zzbau()     // Catch:{ all -> 0x05d7 }
            java.lang.String r1 = "Data loss. Too many events logged. appId, count"
            java.lang.Object r3 = com.google.android.gms.internal.zzcjj.zzjs(r15)     // Catch:{ all -> 0x05d7 }
            long r4 = r2.zzjhf     // Catch:{ all -> 0x05d7 }
            java.lang.Long r2 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x05d7 }
            r0.zze(r1, r3, r2)     // Catch:{ all -> 0x05d7 }
        L_0x017c:
            com.google.android.gms.internal.zzcil r0 = r32.zzayj()     // Catch:{ all -> 0x05d7 }
            r0.setTransactionSuccessful()     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcil r0 = r32.zzayj()
            r0.endTransaction()
            return
        L_0x018b:
            if (r16 == 0) goto L_0x01db
            long r3 = r2.zzjhe     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcja<java.lang.Integer> r14 = com.google.android.gms.internal.zzciz.zzjiz     // Catch:{ all -> 0x05d7 }
            java.lang.Object r14 = r14.get()     // Catch:{ all -> 0x05d7 }
            java.lang.Integer r14 = (java.lang.Integer) r14     // Catch:{ all -> 0x05d7 }
            int r14 = r14.intValue()     // Catch:{ all -> 0x05d7 }
            long r13 = (long) r14     // Catch:{ all -> 0x05d7 }
            long r3 = r3 - r13
            int r13 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r13 <= 0) goto L_0x01db
            long r3 = r3 % r5
            int r1 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r1 != 0) goto L_0x01bd
            com.google.android.gms.internal.zzcjj r1 = r32.zzayp()     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcjl r1 = r1.zzbau()     // Catch:{ all -> 0x05d7 }
            java.lang.String r3 = "Data loss. Too many public events logged. appId, count"
            java.lang.Object r4 = com.google.android.gms.internal.zzcjj.zzjs(r15)     // Catch:{ all -> 0x05d7 }
            long r5 = r2.zzjhe     // Catch:{ all -> 0x05d7 }
            java.lang.Long r2 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x05d7 }
            r1.zze(r3, r4, r2)     // Catch:{ all -> 0x05d7 }
        L_0x01bd:
            com.google.android.gms.internal.zzcno r2 = r32.zzayl()     // Catch:{ all -> 0x05d7 }
            r4 = 16
            java.lang.String r5 = "_ev"
            java.lang.String r6 = r0.name     // Catch:{ all -> 0x05d7 }
            r7 = 0
            r3 = r15
            r2.zza((java.lang.String) r3, (int) r4, (java.lang.String) r5, (java.lang.String) r6, (int) r7)     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcil r0 = r32.zzayj()     // Catch:{ all -> 0x05d7 }
            r0.setTransactionSuccessful()     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcil r0 = r32.zzayj()
            r0.endTransaction()
            return
        L_0x01db:
            if (r1 == 0) goto L_0x0225
            long r3 = r2.zzjhh     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcik r1 = r11.zzjns     // Catch:{ all -> 0x05d7 }
            java.lang.String r5 = r12.packageName     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcja<java.lang.Integer> r6 = com.google.android.gms.internal.zzciz.zzjiy     // Catch:{ all -> 0x05d7 }
            int r1 = r1.zzb(r5, r6)     // Catch:{ all -> 0x05d7 }
            r5 = 1000000(0xf4240, float:1.401298E-39)
            int r1 = java.lang.Math.min(r5, r1)     // Catch:{ all -> 0x05d7 }
            r13 = 0
            int r1 = java.lang.Math.max(r13, r1)     // Catch:{ all -> 0x05d7 }
            long r5 = (long) r1     // Catch:{ all -> 0x05d7 }
            long r3 = r3 - r5
            int r1 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r1 <= 0) goto L_0x0226
            int r0 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r0 != 0) goto L_0x0216
            com.google.android.gms.internal.zzcjj r0 = r32.zzayp()     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcjl r0 = r0.zzbau()     // Catch:{ all -> 0x05d7 }
            java.lang.String r1 = "Too many error events logged. appId, count"
            java.lang.Object r3 = com.google.android.gms.internal.zzcjj.zzjs(r15)     // Catch:{ all -> 0x05d7 }
            long r4 = r2.zzjhh     // Catch:{ all -> 0x05d7 }
            java.lang.Long r2 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x05d7 }
            r0.zze(r1, r3, r2)     // Catch:{ all -> 0x05d7 }
        L_0x0216:
            com.google.android.gms.internal.zzcil r0 = r32.zzayj()     // Catch:{ all -> 0x05d7 }
            r0.setTransactionSuccessful()     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcil r0 = r32.zzayj()
            r0.endTransaction()
            return
        L_0x0225:
            r13 = 0
        L_0x0226:
            com.google.android.gms.internal.zzciu r1 = r0.zzjhr     // Catch:{ all -> 0x05d7 }
            android.os.Bundle r14 = r1.zzbao()     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcno r1 = r32.zzayl()     // Catch:{ all -> 0x05d7 }
            java.lang.String r2 = "_o"
            java.lang.String r3 = r0.zzjgm     // Catch:{ all -> 0x05d7 }
            r1.zza((android.os.Bundle) r14, (java.lang.String) r2, (java.lang.Object) r3)     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcno r1 = r32.zzayl()     // Catch:{ all -> 0x05d7 }
            boolean r1 = r1.zzkq(r15)     // Catch:{ all -> 0x05d7 }
            java.lang.String r6 = "_r"
            if (r1 == 0) goto L_0x025b
            com.google.android.gms.internal.zzcno r1 = r32.zzayl()     // Catch:{ all -> 0x05d7 }
            java.lang.String r2 = "_dbg"
            java.lang.Long r3 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x05d7 }
            r1.zza((android.os.Bundle) r14, (java.lang.String) r2, (java.lang.Object) r3)     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcno r1 = r32.zzayl()     // Catch:{ all -> 0x05d7 }
            java.lang.Long r2 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x05d7 }
            r1.zza((android.os.Bundle) r14, (java.lang.String) r6, (java.lang.Object) r2)     // Catch:{ all -> 0x05d7 }
        L_0x025b:
            com.google.android.gms.internal.zzcil r1 = r32.zzayj()     // Catch:{ all -> 0x05d7 }
            long r1 = r1.zzjk(r15)     // Catch:{ all -> 0x05d7 }
            int r3 = (r1 > r9 ? 1 : (r1 == r9 ? 0 : -1))
            if (r3 <= 0) goto L_0x027c
            com.google.android.gms.internal.zzcjj r3 = r32.zzayp()     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcjl r3 = r3.zzbaw()     // Catch:{ all -> 0x05d7 }
            java.lang.String r4 = "Data lost. Too many events stored on disk, deleted. appId"
            java.lang.Object r5 = com.google.android.gms.internal.zzcjj.zzjs(r15)     // Catch:{ all -> 0x05d7 }
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ all -> 0x05d7 }
            r3.zze(r4, r5, r1)     // Catch:{ all -> 0x05d7 }
        L_0x027c:
            com.google.android.gms.internal.zzcis r8 = new com.google.android.gms.internal.zzcis     // Catch:{ all -> 0x05d7 }
            java.lang.String r3 = r0.zzjgm     // Catch:{ all -> 0x05d7 }
            java.lang.String r5 = r0.name     // Catch:{ all -> 0x05d7 }
            long r0 = r0.zzjib     // Catch:{ all -> 0x05d7 }
            r17 = 0
            r19 = r0
            r1 = r8
            r2 = r32
            r4 = r15
            r0 = r6
            r6 = r19
            r10 = r8
            r8 = r17
            r13 = r10
            r10 = r14
            r1.<init>((com.google.android.gms.internal.zzckj) r2, (java.lang.String) r3, (java.lang.String) r4, (java.lang.String) r5, (long) r6, (long) r8, (android.os.Bundle) r10)     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcil r1 = r32.zzayj()     // Catch:{ all -> 0x05d7 }
            java.lang.String r2 = r13.name     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcit r1 = r1.zzae(r15, r2)     // Catch:{ all -> 0x05d7 }
            if (r1 != 0) goto L_0x0308
            com.google.android.gms.internal.zzcil r1 = r32.zzayj()     // Catch:{ all -> 0x05d7 }
            long r1 = r1.zzjn(r15)     // Catch:{ all -> 0x05d7 }
            r3 = 500(0x1f4, double:2.47E-321)
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 < 0) goto L_0x02e9
            if (r16 == 0) goto L_0x02e9
            com.google.android.gms.internal.zzcjj r0 = r32.zzayp()     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcjl r0 = r0.zzbau()     // Catch:{ all -> 0x05d7 }
            java.lang.String r1 = "Too many event names used, ignoring event. appId, name, supported count"
            java.lang.Object r2 = com.google.android.gms.internal.zzcjj.zzjs(r15)     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcjh r3 = r32.zzayk()     // Catch:{ all -> 0x05d7 }
            java.lang.String r4 = r13.name     // Catch:{ all -> 0x05d7 }
            java.lang.String r3 = r3.zzjp(r4)     // Catch:{ all -> 0x05d7 }
            r4 = 500(0x1f4, float:7.0E-43)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x05d7 }
            r0.zzd(r1, r2, r3, r4)     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcno r2 = r32.zzayl()     // Catch:{ all -> 0x05d7 }
            r4 = 8
            r5 = 0
            r6 = 0
            r7 = 0
            r3 = r15
            r2.zza((java.lang.String) r3, (int) r4, (java.lang.String) r5, (java.lang.String) r6, (int) r7)     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcil r0 = r32.zzayj()
            r0.endTransaction()
            return
        L_0x02e9:
            com.google.android.gms.internal.zzcit r1 = new com.google.android.gms.internal.zzcit     // Catch:{ all -> 0x05d7 }
            java.lang.String r2 = r13.name     // Catch:{ all -> 0x05d7 }
            r18 = 0
            r20 = 0
            long r3 = r13.timestamp     // Catch:{ all -> 0x05d7 }
            r24 = 0
            r26 = 0
            r27 = 0
            r28 = 0
            r5 = r15
            r15 = r1
            r16 = r5
            r17 = r2
            r22 = r3
            r15.<init>(r16, r17, r18, r20, r22, r24, r26, r27, r28)     // Catch:{ all -> 0x05d7 }
            r8 = r13
            goto L_0x0314
        L_0x0308:
            long r2 = r1.zzjhu     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcis r8 = r13.zza((com.google.android.gms.internal.zzckj) r11, (long) r2)     // Catch:{ all -> 0x05d7 }
            long r2 = r8.timestamp     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcit r1 = r1.zzbb(r2)     // Catch:{ all -> 0x05d7 }
        L_0x0314:
            com.google.android.gms.internal.zzcil r2 = r32.zzayj()     // Catch:{ all -> 0x05d7 }
            r2.zza((com.google.android.gms.internal.zzcit) r1)     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcke r1 = r32.zzayo()     // Catch:{ all -> 0x05d7 }
            r1.zzwj()     // Catch:{ all -> 0x05d7 }
            r32.zzyk()     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.common.internal.zzbq.checkNotNull(r8)     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.common.internal.zzbq.checkNotNull(r34)     // Catch:{ all -> 0x05d7 }
            java.lang.String r1 = r8.zzcm     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.common.internal.zzbq.zzgv(r1)     // Catch:{ all -> 0x05d7 }
            java.lang.String r1 = r8.zzcm     // Catch:{ all -> 0x05d7 }
            java.lang.String r2 = r12.packageName     // Catch:{ all -> 0x05d7 }
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.common.internal.zzbq.checkArgument(r1)     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcoe r1 = new com.google.android.gms.internal.zzcoe     // Catch:{ all -> 0x05d7 }
            r1.<init>()     // Catch:{ all -> 0x05d7 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r29)     // Catch:{ all -> 0x05d7 }
            r1.zzjup = r2     // Catch:{ all -> 0x05d7 }
            java.lang.String r2 = "android"
            r1.zzjux = r2     // Catch:{ all -> 0x05d7 }
            java.lang.String r2 = r12.packageName     // Catch:{ all -> 0x05d7 }
            r1.zzcm = r2     // Catch:{ all -> 0x05d7 }
            java.lang.String r2 = r12.zzjfs     // Catch:{ all -> 0x05d7 }
            r1.zzjfs = r2     // Catch:{ all -> 0x05d7 }
            java.lang.String r2 = r12.zzina     // Catch:{ all -> 0x05d7 }
            r1.zzina = r2     // Catch:{ all -> 0x05d7 }
            long r2 = r12.zzjfr     // Catch:{ all -> 0x05d7 }
            r4 = -2147483648(0xffffffff80000000, double:NaN)
            r6 = 0
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 != 0) goto L_0x0362
            r2 = r6
            goto L_0x0369
        L_0x0362:
            long r2 = r12.zzjfr     // Catch:{ all -> 0x05d7 }
            int r3 = (int) r2     // Catch:{ all -> 0x05d7 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x05d7 }
        L_0x0369:
            r1.zzjvi = r2     // Catch:{ all -> 0x05d7 }
            long r2 = r12.zzjft     // Catch:{ all -> 0x05d7 }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x05d7 }
            r1.zzjva = r2     // Catch:{ all -> 0x05d7 }
            java.lang.String r2 = r12.zzjfl     // Catch:{ all -> 0x05d7 }
            r1.zzjfl = r2     // Catch:{ all -> 0x05d7 }
            long r2 = r12.zzjfu     // Catch:{ all -> 0x05d7 }
            r4 = 0
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 != 0) goto L_0x0381
            r2 = r6
            goto L_0x0387
        L_0x0381:
            long r2 = r12.zzjfu     // Catch:{ all -> 0x05d7 }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x05d7 }
        L_0x0387:
            r1.zzjve = r2     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcju r2 = r32.zzayq()     // Catch:{ all -> 0x05d7 }
            java.lang.String r3 = r12.packageName     // Catch:{ all -> 0x05d7 }
            android.util.Pair r2 = r2.zzju(r3)     // Catch:{ all -> 0x05d7 }
            if (r2 == 0) goto L_0x03b0
            java.lang.Object r3 = r2.first     // Catch:{ all -> 0x05d7 }
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3     // Catch:{ all -> 0x05d7 }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x05d7 }
            if (r3 != 0) goto L_0x03b0
            boolean r3 = r12.zzjfx     // Catch:{ all -> 0x05d7 }
            if (r3 == 0) goto L_0x03fb
            java.lang.Object r3 = r2.first     // Catch:{ all -> 0x05d7 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x05d7 }
            r1.zzjvc = r3     // Catch:{ all -> 0x05d7 }
            java.lang.Object r2 = r2.second     // Catch:{ all -> 0x05d7 }
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ all -> 0x05d7 }
            r1.zzjvd = r2     // Catch:{ all -> 0x05d7 }
            goto L_0x03fb
        L_0x03b0:
            com.google.android.gms.internal.zzcir r2 = r32.zzayf()     // Catch:{ all -> 0x05d7 }
            android.content.Context r3 = r11.zzaiq     // Catch:{ all -> 0x05d7 }
            boolean r2 = r2.zzec(r3)     // Catch:{ all -> 0x05d7 }
            if (r2 != 0) goto L_0x03fb
            android.content.Context r2 = r11.zzaiq     // Catch:{ all -> 0x05d7 }
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch:{ all -> 0x05d7 }
            java.lang.String r3 = "android_id"
            java.lang.String r2 = android.provider.Settings.Secure.getString(r2, r3)     // Catch:{ all -> 0x05d7 }
            if (r2 != 0) goto L_0x03e0
            com.google.android.gms.internal.zzcjj r2 = r32.zzayp()     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcjl r2 = r2.zzbaw()     // Catch:{ all -> 0x05d7 }
            java.lang.String r3 = "null secure ID. appId"
            java.lang.String r7 = r1.zzcm     // Catch:{ all -> 0x05d7 }
            java.lang.Object r7 = com.google.android.gms.internal.zzcjj.zzjs(r7)     // Catch:{ all -> 0x05d7 }
            r2.zzj(r3, r7)     // Catch:{ all -> 0x05d7 }
            java.lang.String r2 = "null"
            goto L_0x03f9
        L_0x03e0:
            boolean r3 = r2.isEmpty()     // Catch:{ all -> 0x05d7 }
            if (r3 == 0) goto L_0x03f9
            com.google.android.gms.internal.zzcjj r3 = r32.zzayp()     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcjl r3 = r3.zzbaw()     // Catch:{ all -> 0x05d7 }
            java.lang.String r7 = "empty secure ID. appId"
            java.lang.String r9 = r1.zzcm     // Catch:{ all -> 0x05d7 }
            java.lang.Object r9 = com.google.android.gms.internal.zzcjj.zzjs(r9)     // Catch:{ all -> 0x05d7 }
            r3.zzj(r7, r9)     // Catch:{ all -> 0x05d7 }
        L_0x03f9:
            r1.zzjvl = r2     // Catch:{ all -> 0x05d7 }
        L_0x03fb:
            com.google.android.gms.internal.zzcir r2 = r32.zzayf()     // Catch:{ all -> 0x05d7 }
            r2.zzyk()     // Catch:{ all -> 0x05d7 }
            java.lang.String r2 = android.os.Build.MODEL     // Catch:{ all -> 0x05d7 }
            r1.zzjuy = r2     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcir r2 = r32.zzayf()     // Catch:{ all -> 0x05d7 }
            r2.zzyk()     // Catch:{ all -> 0x05d7 }
            java.lang.String r2 = android.os.Build.VERSION.RELEASE     // Catch:{ all -> 0x05d7 }
            r1.zzda = r2     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcir r2 = r32.zzayf()     // Catch:{ all -> 0x05d7 }
            long r2 = r2.zzbal()     // Catch:{ all -> 0x05d7 }
            int r3 = (int) r2     // Catch:{ all -> 0x05d7 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x05d7 }
            r1.zzjuz = r2     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcir r2 = r32.zzayf()     // Catch:{ all -> 0x05d7 }
            java.lang.String r2 = r2.zzbam()     // Catch:{ all -> 0x05d7 }
            r1.zzjho = r2     // Catch:{ all -> 0x05d7 }
            r1.zzjvb = r6     // Catch:{ all -> 0x05d7 }
            r1.zzjus = r6     // Catch:{ all -> 0x05d7 }
            r1.zzjut = r6     // Catch:{ all -> 0x05d7 }
            r1.zzjuu = r6     // Catch:{ all -> 0x05d7 }
            long r2 = r12.zzjfw     // Catch:{ all -> 0x05d7 }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x05d7 }
            r1.zzfqm = r2     // Catch:{ all -> 0x05d7 }
            boolean r2 = r32.isEnabled()     // Catch:{ all -> 0x05d7 }
            if (r2 == 0) goto L_0x0448
            boolean r2 = com.google.android.gms.internal.zzcik.zzazv()     // Catch:{ all -> 0x05d7 }
            if (r2 == 0) goto L_0x0448
            r1.zzjvn = r6     // Catch:{ all -> 0x05d7 }
        L_0x0448:
            com.google.android.gms.internal.zzcil r2 = r32.zzayj()     // Catch:{ all -> 0x05d7 }
            java.lang.String r3 = r12.packageName     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcie r2 = r2.zzjj(r3)     // Catch:{ all -> 0x05d7 }
            if (r2 != 0) goto L_0x04b0
            com.google.android.gms.internal.zzcie r2 = new com.google.android.gms.internal.zzcie     // Catch:{ all -> 0x05d7 }
            java.lang.String r3 = r12.packageName     // Catch:{ all -> 0x05d7 }
            r2.<init>(r11, r3)     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcje r3 = r32.zzaye()     // Catch:{ all -> 0x05d7 }
            java.lang.String r3 = r3.zzbaq()     // Catch:{ all -> 0x05d7 }
            r2.zziy(r3)     // Catch:{ all -> 0x05d7 }
            java.lang.String r3 = r12.zzjfn     // Catch:{ all -> 0x05d7 }
            r2.zzjb(r3)     // Catch:{ all -> 0x05d7 }
            java.lang.String r3 = r12.zzjfl     // Catch:{ all -> 0x05d7 }
            r2.zziz(r3)     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcju r3 = r32.zzayq()     // Catch:{ all -> 0x05d7 }
            java.lang.String r6 = r12.packageName     // Catch:{ all -> 0x05d7 }
            java.lang.String r3 = r3.zzjv(r6)     // Catch:{ all -> 0x05d7 }
            r2.zzja(r3)     // Catch:{ all -> 0x05d7 }
            r2.zzaq(r4)     // Catch:{ all -> 0x05d7 }
            r2.zzal(r4)     // Catch:{ all -> 0x05d7 }
            r2.zzam(r4)     // Catch:{ all -> 0x05d7 }
            java.lang.String r3 = r12.zzina     // Catch:{ all -> 0x05d7 }
            r2.setAppVersion(r3)     // Catch:{ all -> 0x05d7 }
            long r6 = r12.zzjfr     // Catch:{ all -> 0x05d7 }
            r2.zzan(r6)     // Catch:{ all -> 0x05d7 }
            java.lang.String r3 = r12.zzjfs     // Catch:{ all -> 0x05d7 }
            r2.zzjc(r3)     // Catch:{ all -> 0x05d7 }
            long r6 = r12.zzjft     // Catch:{ all -> 0x05d7 }
            r2.zzao(r6)     // Catch:{ all -> 0x05d7 }
            long r6 = r12.zzjfu     // Catch:{ all -> 0x05d7 }
            r2.zzap(r6)     // Catch:{ all -> 0x05d7 }
            boolean r3 = r12.zzjfv     // Catch:{ all -> 0x05d7 }
            r2.setMeasurementEnabled(r3)     // Catch:{ all -> 0x05d7 }
            long r6 = r12.zzjfw     // Catch:{ all -> 0x05d7 }
            r2.zzaz(r6)     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcil r3 = r32.zzayj()     // Catch:{ all -> 0x05d7 }
            r3.zza((com.google.android.gms.internal.zzcie) r2)     // Catch:{ all -> 0x05d7 }
        L_0x04b0:
            java.lang.String r3 = r2.getAppInstanceId()     // Catch:{ all -> 0x05d7 }
            r1.zzjfk = r3     // Catch:{ all -> 0x05d7 }
            java.lang.String r2 = r2.zzayu()     // Catch:{ all -> 0x05d7 }
            r1.zzjfn = r2     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcil r2 = r32.zzayj()     // Catch:{ all -> 0x05d7 }
            java.lang.String r3 = r12.packageName     // Catch:{ all -> 0x05d7 }
            java.util.List r2 = r2.zzji(r3)     // Catch:{ all -> 0x05d7 }
            int r3 = r2.size()     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcog[] r3 = new com.google.android.gms.internal.zzcog[r3]     // Catch:{ all -> 0x05d7 }
            r1.zzjur = r3     // Catch:{ all -> 0x05d7 }
            r10 = 0
        L_0x04cf:
            int r3 = r2.size()     // Catch:{ all -> 0x05d7 }
            if (r10 >= r3) goto L_0x0508
            com.google.android.gms.internal.zzcog r3 = new com.google.android.gms.internal.zzcog     // Catch:{ all -> 0x05d7 }
            r3.<init>()     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcog[] r6 = r1.zzjur     // Catch:{ all -> 0x05d7 }
            r6[r10] = r3     // Catch:{ all -> 0x05d7 }
            java.lang.Object r6 = r2.get(r10)     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcnn r6 = (com.google.android.gms.internal.zzcnn) r6     // Catch:{ all -> 0x05d7 }
            java.lang.String r6 = r6.name     // Catch:{ all -> 0x05d7 }
            r3.name = r6     // Catch:{ all -> 0x05d7 }
            java.lang.Object r6 = r2.get(r10)     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcnn r6 = (com.google.android.gms.internal.zzcnn) r6     // Catch:{ all -> 0x05d7 }
            long r6 = r6.zzjsi     // Catch:{ all -> 0x05d7 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x05d7 }
            r3.zzjvr = r6     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcno r6 = r32.zzayl()     // Catch:{ all -> 0x05d7 }
            java.lang.Object r7 = r2.get(r10)     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcnn r7 = (com.google.android.gms.internal.zzcnn) r7     // Catch:{ all -> 0x05d7 }
            java.lang.Object r7 = r7.value     // Catch:{ all -> 0x05d7 }
            r6.zza((com.google.android.gms.internal.zzcog) r3, (java.lang.Object) r7)     // Catch:{ all -> 0x05d7 }
            int r10 = r10 + 1
            goto L_0x04cf
        L_0x0508:
            com.google.android.gms.internal.zzcil r2 = r32.zzayj()     // Catch:{ IOException -> 0x0572 }
            long r1 = r2.zza((com.google.android.gms.internal.zzcoe) r1)     // Catch:{ IOException -> 0x0572 }
            com.google.android.gms.internal.zzcil r3 = r32.zzayj()     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzciu r6 = r8.zzjhr     // Catch:{ all -> 0x05d7 }
            if (r6 == 0) goto L_0x0568
            com.google.android.gms.internal.zzciu r6 = r8.zzjhr     // Catch:{ all -> 0x05d7 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x05d7 }
        L_0x051e:
            boolean r7 = r6.hasNext()     // Catch:{ all -> 0x05d7 }
            if (r7 == 0) goto L_0x0532
            java.lang.Object r7 = r6.next()     // Catch:{ all -> 0x05d7 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x05d7 }
            boolean r7 = r0.equals(r7)     // Catch:{ all -> 0x05d7 }
            if (r7 == 0) goto L_0x051e
        L_0x0530:
            r10 = 1
            goto L_0x0569
        L_0x0532:
            com.google.android.gms.internal.zzckd r0 = r32.zzaym()     // Catch:{ all -> 0x05d7 }
            java.lang.String r6 = r8.zzcm     // Catch:{ all -> 0x05d7 }
            java.lang.String r7 = r8.name     // Catch:{ all -> 0x05d7 }
            boolean r0 = r0.zzao(r6, r7)     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcil r12 = r32.zzayj()     // Catch:{ all -> 0x05d7 }
            long r13 = r32.zzbbx()     // Catch:{ all -> 0x05d7 }
            java.lang.String r15 = r8.zzcm     // Catch:{ all -> 0x05d7 }
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            com.google.android.gms.internal.zzcim r6 = r12.zza(r13, r15, r16, r17, r18, r19, r20)     // Catch:{ all -> 0x05d7 }
            if (r0 == 0) goto L_0x0568
            long r6 = r6.zzjhi     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcik r0 = r11.zzjns     // Catch:{ all -> 0x05d7 }
            java.lang.String r9 = r8.zzcm     // Catch:{ all -> 0x05d7 }
            int r0 = r0.zzje(r9)     // Catch:{ all -> 0x05d7 }
            long r9 = (long) r0     // Catch:{ all -> 0x05d7 }
            int r0 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r0 >= 0) goto L_0x0568
            goto L_0x0530
        L_0x0568:
            r10 = 0
        L_0x0569:
            boolean r0 = r3.zza((com.google.android.gms.internal.zzcis) r8, (long) r1, (boolean) r10)     // Catch:{ all -> 0x05d7 }
            if (r0 == 0) goto L_0x0586
            r11.zzjoy = r4     // Catch:{ all -> 0x05d7 }
            goto L_0x0586
        L_0x0572:
            r0 = move-exception
            com.google.android.gms.internal.zzcjj r2 = r32.zzayp()     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcjl r2 = r2.zzbau()     // Catch:{ all -> 0x05d7 }
            java.lang.String r3 = "Data loss. Failed to insert raw event metadata. appId"
            java.lang.String r1 = r1.zzcm     // Catch:{ all -> 0x05d7 }
            java.lang.Object r1 = com.google.android.gms.internal.zzcjj.zzjs(r1)     // Catch:{ all -> 0x05d7 }
            r2.zze(r3, r1, r0)     // Catch:{ all -> 0x05d7 }
        L_0x0586:
            com.google.android.gms.internal.zzcil r0 = r32.zzayj()     // Catch:{ all -> 0x05d7 }
            r0.setTransactionSuccessful()     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcjj r0 = r32.zzayp()     // Catch:{ all -> 0x05d7 }
            r1 = 2
            boolean r0 = r0.zzae(r1)     // Catch:{ all -> 0x05d7 }
            if (r0 == 0) goto L_0x05ad
            com.google.android.gms.internal.zzcjj r0 = r32.zzayp()     // Catch:{ all -> 0x05d7 }
            com.google.android.gms.internal.zzcjl r0 = r0.zzbba()     // Catch:{ all -> 0x05d7 }
            java.lang.String r1 = "Event recorded"
            com.google.android.gms.internal.zzcjh r2 = r32.zzayk()     // Catch:{ all -> 0x05d7 }
            java.lang.String r2 = r2.zza((com.google.android.gms.internal.zzcis) r8)     // Catch:{ all -> 0x05d7 }
            r0.zzj(r1, r2)     // Catch:{ all -> 0x05d7 }
        L_0x05ad:
            com.google.android.gms.internal.zzcil r0 = r32.zzayj()
            r0.endTransaction()
            r32.zzbca()
            com.google.android.gms.internal.zzcjj r0 = r32.zzayp()
            com.google.android.gms.internal.zzcjl r0 = r0.zzbba()
            long r1 = java.lang.System.nanoTime()
            long r1 = r1 - r30
            r3 = 500000(0x7a120, double:2.47033E-318)
            long r1 = r1 + r3
            r3 = 1000000(0xf4240, double:4.940656E-318)
            long r1 = r1 / r3
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            java.lang.String r2 = "Background event processing time, ms"
            r0.zzj(r2, r1)
            return
        L_0x05d7:
            r0 = move-exception
            com.google.android.gms.internal.zzcil r1 = r32.zzayj()
            r1.endTransaction()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzckj.zzc(com.google.android.gms.internal.zzcix, com.google.android.gms.internal.zzcif):void");
    }

    public static zzckj zzed(Context context) {
        zzbq.checkNotNull(context);
        zzbq.checkNotNull(context.getApplicationContext());
        if (zzjnr == null) {
            synchronized (zzckj.class) {
                if (zzjnr == null) {
                    zzjnr = new zzckj(new zzclj(context));
                }
            }
        }
        return zzjnr;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00f0  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00fe  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0128  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0136  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x013c  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x013f  */
    /* JADX WARNING: Removed duplicated region for block: B:57:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzg(com.google.android.gms.internal.zzcif r9) {
        /*
            r8 = this;
            com.google.android.gms.internal.zzcke r0 = r8.zzayo()
            r0.zzwj()
            r8.zzyk()
            com.google.android.gms.common.internal.zzbq.checkNotNull(r9)
            java.lang.String r0 = r9.packageName
            com.google.android.gms.common.internal.zzbq.zzgv(r0)
            com.google.android.gms.internal.zzcil r0 = r8.zzayj()
            java.lang.String r1 = r9.packageName
            com.google.android.gms.internal.zzcie r0 = r0.zzjj(r1)
            com.google.android.gms.internal.zzcju r1 = r8.zzayq()
            java.lang.String r2 = r9.packageName
            java.lang.String r1 = r1.zzjv(r2)
            r2 = 1
            if (r0 != 0) goto L_0x0040
            com.google.android.gms.internal.zzcie r0 = new com.google.android.gms.internal.zzcie
            java.lang.String r3 = r9.packageName
            r0.<init>(r8, r3)
            com.google.android.gms.internal.zzcje r3 = r8.zzaye()
            java.lang.String r3 = r3.zzbaq()
            r0.zziy(r3)
            r0.zzja(r1)
        L_0x003e:
            r1 = 1
            goto L_0x005a
        L_0x0040:
            java.lang.String r3 = r0.zzayt()
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x0059
            r0.zzja(r1)
            com.google.android.gms.internal.zzcje r1 = r8.zzaye()
            java.lang.String r1 = r1.zzbaq()
            r0.zziy(r1)
            goto L_0x003e
        L_0x0059:
            r1 = 0
        L_0x005a:
            java.lang.String r3 = r9.zzjfl
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0074
            java.lang.String r3 = r9.zzjfl
            java.lang.String r4 = r0.getGmpAppId()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0074
            java.lang.String r1 = r9.zzjfl
            r0.zziz(r1)
            r1 = 1
        L_0x0074:
            java.lang.String r3 = r9.zzjfn
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x008e
            java.lang.String r3 = r9.zzjfn
            java.lang.String r4 = r0.zzayu()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x008e
            java.lang.String r1 = r9.zzjfn
            r0.zzjb(r1)
            r1 = 1
        L_0x008e:
            long r3 = r9.zzjft
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x00a6
            long r3 = r9.zzjft
            long r5 = r0.zzayz()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x00a6
            long r3 = r9.zzjft
            r0.zzao(r3)
            r1 = 1
        L_0x00a6:
            java.lang.String r3 = r9.zzina
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x00c0
            java.lang.String r3 = r9.zzina
            java.lang.String r4 = r0.zzwo()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x00c0
            java.lang.String r1 = r9.zzina
            r0.setAppVersion(r1)
            r1 = 1
        L_0x00c0:
            long r3 = r9.zzjfr
            long r5 = r0.zzayx()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x00d0
            long r3 = r9.zzjfr
            r0.zzan(r3)
            r1 = 1
        L_0x00d0:
            java.lang.String r3 = r9.zzjfs
            if (r3 == 0) goto L_0x00e6
            java.lang.String r3 = r9.zzjfs
            java.lang.String r4 = r0.zzayy()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x00e6
            java.lang.String r1 = r9.zzjfs
            r0.zzjc(r1)
            r1 = 1
        L_0x00e6:
            long r3 = r9.zzjfu
            long r5 = r0.zzaza()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x00f6
            long r3 = r9.zzjfu
            r0.zzap(r3)
            r1 = 1
        L_0x00f6:
            boolean r3 = r9.zzjfv
            boolean r4 = r0.zzazb()
            if (r3 == r4) goto L_0x0104
            boolean r1 = r9.zzjfv
            r0.setMeasurementEnabled(r1)
            r1 = 1
        L_0x0104:
            java.lang.String r3 = r9.zzjgi
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x011e
            java.lang.String r3 = r9.zzjgi
            java.lang.String r4 = r0.zzazm()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x011e
            java.lang.String r1 = r9.zzjgi
            r0.zzjd(r1)
            r1 = 1
        L_0x011e:
            long r3 = r9.zzjfw
            long r5 = r0.zzazo()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x012e
            long r3 = r9.zzjfw
            r0.zzaz(r3)
            r1 = 1
        L_0x012e:
            boolean r3 = r9.zzjfx
            boolean r4 = r0.zzazp()
            if (r3 == r4) goto L_0x013c
            boolean r9 = r9.zzjfx
            r0.zzbq(r9)
            goto L_0x013d
        L_0x013c:
            r2 = r1
        L_0x013d:
            if (r2 == 0) goto L_0x0146
            com.google.android.gms.internal.zzcil r9 = r8.zzayj()
            r9.zza((com.google.android.gms.internal.zzcie) r0)
        L_0x0146:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzckj.zzg(com.google.android.gms.internal.zzcif):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0076, code lost:
        if (r4 != null) goto L_0x0078;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0204, code lost:
        if (r4 == null) goto L_0x022c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0228, code lost:
        if (r4 == null) goto L_0x022c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x023e A[Catch:{ SQLiteException -> 0x09cb, all -> 0x0a06 }] */
    /* JADX WARNING: Removed duplicated region for block: B:191:0x04e7 A[Catch:{ SQLiteException -> 0x09cb, all -> 0x0a06 }] */
    /* JADX WARNING: Removed duplicated region for block: B:217:0x05c4 A[Catch:{ SQLiteException -> 0x09cb, all -> 0x0a06 }] */
    /* JADX WARNING: Removed duplicated region for block: B:223:0x05dc A[Catch:{ SQLiteException -> 0x09cb, all -> 0x0a06 }] */
    /* JADX WARNING: Removed duplicated region for block: B:226:0x05fc A[Catch:{ SQLiteException -> 0x09cb, all -> 0x0a06 }] */
    /* JADX WARNING: Removed duplicated region for block: B:269:0x0772 A[Catch:{ SQLiteException -> 0x09cb, all -> 0x0a06 }] */
    /* JADX WARNING: Removed duplicated region for block: B:270:0x0774 A[Catch:{ SQLiteException -> 0x09cb, all -> 0x0a06 }] */
    /* JADX WARNING: Removed duplicated region for block: B:273:0x077c A[Catch:{ SQLiteException -> 0x09cb, all -> 0x0a06 }] */
    /* JADX WARNING: Removed duplicated region for block: B:284:0x07a4 A[Catch:{ SQLiteException -> 0x09cb, all -> 0x0a06 }] */
    /* JADX WARNING: Removed duplicated region for block: B:311:0x08b1 A[Catch:{ SQLiteException -> 0x09cb, all -> 0x0a06 }] */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x08ef A[Catch:{ SQLiteException -> 0x09cb, all -> 0x0a06 }] */
    /* JADX WARNING: Removed duplicated region for block: B:321:0x0905 A[Catch:{ SQLiteException -> 0x09cb, all -> 0x0a06 }] */
    /* JADX WARNING: Removed duplicated region for block: B:337:0x0964 A[Catch:{ SQLiteException -> 0x09cb, all -> 0x0a06 }] */
    /* JADX WARNING: Removed duplicated region for block: B:360:0x09ee A[SYNTHETIC, Splitter:B:360:0x09ee] */
    /* JADX WARNING: Removed duplicated region for block: B:367:0x0a02 A[SYNTHETIC, Splitter:B:367:0x0a02] */
    /* JADX WARNING: Removed duplicated region for block: B:388:0x05d9 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0230 A[Catch:{ SQLiteException -> 0x09cb, all -> 0x0a06 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzg(java.lang.String r38, long r39) {
        /*
            r37 = this;
            r1 = r37
            java.lang.String r2 = "_lte"
            com.google.android.gms.internal.zzcil r3 = r37.zzayj()
            r3.beginTransaction()
            com.google.android.gms.internal.zzckj$zza r3 = new com.google.android.gms.internal.zzckj$zza     // Catch:{ all -> 0x0a06 }
            r4 = 0
            r3.<init>(r1, r4)     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcil r5 = r37.zzayj()     // Catch:{ all -> 0x0a06 }
            long r6 = r1.zzjox     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.common.internal.zzbq.checkNotNull(r3)     // Catch:{ all -> 0x0a06 }
            r5.zzwj()     // Catch:{ all -> 0x0a06 }
            r5.zzyk()     // Catch:{ all -> 0x0a06 }
            r8 = -1
            r10 = 2
            r11 = 0
            r12 = 1
            android.database.sqlite.SQLiteDatabase r15 = r5.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            boolean r13 = android.text.TextUtils.isEmpty(r4)     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r14 = ""
            if (r13 == 0) goto L_0x008b
            int r13 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r13 == 0) goto L_0x0044
            java.lang.String[] r4 = new java.lang.String[r10]     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r16 = java.lang.String.valueOf(r6)     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            r4[r11] = r16     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r16 = java.lang.String.valueOf(r39)     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            r4[r12] = r16     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            goto L_0x004c
        L_0x0044:
            java.lang.String[] r4 = new java.lang.String[r12]     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r16 = java.lang.String.valueOf(r39)     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            r4[r11] = r16     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
        L_0x004c:
            if (r13 == 0) goto L_0x0050
            java.lang.String r14 = "rowid <= ? and "
        L_0x0050:
            int r13 = r14.length()     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            int r13 = r13 + 148
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            r10.<init>(r13)     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r13 = "select app_id, metadata_fingerprint from raw_events where "
            r10.append(r13)     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            r10.append(r14)     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r13 = "app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;"
            r10.append(r13)     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r10 = r10.toString()     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            android.database.Cursor r4 = r15.rawQuery(r10, r4)     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            boolean r10 = r4.moveToFirst()     // Catch:{ SQLiteException -> 0x020b }
            if (r10 != 0) goto L_0x007d
            if (r4 == 0) goto L_0x022c
        L_0x0078:
            r4.close()     // Catch:{ all -> 0x0a06 }
            goto L_0x022c
        L_0x007d:
            java.lang.String r10 = r4.getString(r11)     // Catch:{ SQLiteException -> 0x020b }
            java.lang.String r13 = r4.getString(r12)     // Catch:{ SQLiteException -> 0x0208 }
            r4.close()     // Catch:{ SQLiteException -> 0x0208 }
            r22 = r13
            goto L_0x00d8
        L_0x008b:
            int r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r4 == 0) goto L_0x009c
            r10 = 2
            java.lang.String[] r13 = new java.lang.String[r10]     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            r10 = 0
            r13[r11] = r10     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r10 = java.lang.String.valueOf(r6)     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            r13[r12] = r10     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            goto L_0x00a1
        L_0x009c:
            r10 = 0
            java.lang.String[] r13 = new java.lang.String[]{r10}     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
        L_0x00a1:
            if (r4 == 0) goto L_0x00a5
            java.lang.String r14 = " and rowid <= ?"
        L_0x00a5:
            int r4 = r14.length()     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            int r4 = r4 + 84
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            r10.<init>(r4)     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r4 = "select metadata_fingerprint from raw_events where app_id = ?"
            r10.append(r4)     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            r10.append(r14)     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r4 = " order by rowid limit 1;"
            r10.append(r4)     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r4 = r10.toString()     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            android.database.Cursor r4 = r15.rawQuery(r4, r13)     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            boolean r10 = r4.moveToFirst()     // Catch:{ SQLiteException -> 0x020b }
            if (r10 != 0) goto L_0x00ce
            if (r4 == 0) goto L_0x022c
            goto L_0x0078
        L_0x00ce:
            java.lang.String r13 = r4.getString(r11)     // Catch:{ SQLiteException -> 0x020b }
            r4.close()     // Catch:{ SQLiteException -> 0x020b }
            r22 = r13
            r10 = 0
        L_0x00d8:
            java.lang.String r14 = "raw_events_metadata"
            java.lang.String r13 = "metadata"
            java.lang.String[] r16 = new java.lang.String[]{r13}     // Catch:{ SQLiteException -> 0x0208 }
            java.lang.String r17 = "app_id = ? and metadata_fingerprint = ?"
            r13 = 2
            java.lang.String[] r8 = new java.lang.String[r13]     // Catch:{ SQLiteException -> 0x0208 }
            r8[r11] = r10     // Catch:{ SQLiteException -> 0x0208 }
            r8[r12] = r22     // Catch:{ SQLiteException -> 0x0208 }
            r18 = 0
            r19 = 0
            java.lang.String r20 = "rowid"
            java.lang.String r21 = "2"
            r13 = r15
            r9 = r15
            r15 = r16
            r16 = r17
            r17 = r8
            android.database.Cursor r4 = r13.query(r14, r15, r16, r17, r18, r19, r20, r21)     // Catch:{ SQLiteException -> 0x0208 }
            boolean r8 = r4.moveToFirst()     // Catch:{ SQLiteException -> 0x0208 }
            if (r8 != 0) goto L_0x0118
            com.google.android.gms.internal.zzcjj r6 = r5.zzayp()     // Catch:{ SQLiteException -> 0x0208 }
            com.google.android.gms.internal.zzcjl r6 = r6.zzbau()     // Catch:{ SQLiteException -> 0x0208 }
            java.lang.String r7 = "Raw event metadata record is missing. appId"
            java.lang.Object r8 = com.google.android.gms.internal.zzcjj.zzjs(r10)     // Catch:{ SQLiteException -> 0x0208 }
            r6.zzj(r7, r8)     // Catch:{ SQLiteException -> 0x0208 }
            if (r4 == 0) goto L_0x022c
            goto L_0x0078
        L_0x0118:
            byte[] r8 = r4.getBlob(r11)     // Catch:{ SQLiteException -> 0x0208 }
            int r13 = r8.length     // Catch:{ SQLiteException -> 0x0208 }
            com.google.android.gms.internal.zzflj r8 = com.google.android.gms.internal.zzflj.zzo(r8, r11, r13)     // Catch:{ SQLiteException -> 0x0208 }
            com.google.android.gms.internal.zzcoe r13 = new com.google.android.gms.internal.zzcoe     // Catch:{ SQLiteException -> 0x0208 }
            r13.<init>()     // Catch:{ SQLiteException -> 0x0208 }
            r13.zza((com.google.android.gms.internal.zzflj) r8)     // Catch:{ IOException -> 0x01f1 }
            boolean r8 = r4.moveToNext()     // Catch:{ SQLiteException -> 0x0208 }
            if (r8 == 0) goto L_0x0140
            com.google.android.gms.internal.zzcjj r8 = r5.zzayp()     // Catch:{ SQLiteException -> 0x0208 }
            com.google.android.gms.internal.zzcjl r8 = r8.zzbaw()     // Catch:{ SQLiteException -> 0x0208 }
            java.lang.String r14 = "Get multiple raw event metadata records, expected one. appId"
            java.lang.Object r15 = com.google.android.gms.internal.zzcjj.zzjs(r10)     // Catch:{ SQLiteException -> 0x0208 }
            r8.zzj(r14, r15)     // Catch:{ SQLiteException -> 0x0208 }
        L_0x0140:
            r4.close()     // Catch:{ SQLiteException -> 0x0208 }
            r3.zzb(r13)     // Catch:{ SQLiteException -> 0x0208 }
            r8 = 3
            r13 = -1
            int r15 = (r6 > r13 ? 1 : (r6 == r13 ? 0 : -1))
            if (r15 == 0) goto L_0x0161
            java.lang.String r13 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?"
            java.lang.String[] r14 = new java.lang.String[r8]     // Catch:{ SQLiteException -> 0x0208 }
            r14[r11] = r10     // Catch:{ SQLiteException -> 0x0208 }
            r14[r12] = r22     // Catch:{ SQLiteException -> 0x0208 }
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ SQLiteException -> 0x0208 }
            r7 = 2
            r14[r7] = r6     // Catch:{ SQLiteException -> 0x0208 }
            r16 = r13
            r17 = r14
            goto L_0x016e
        L_0x0161:
            java.lang.String r6 = "app_id = ? and metadata_fingerprint = ?"
            r7 = 2
            java.lang.String[] r13 = new java.lang.String[r7]     // Catch:{ SQLiteException -> 0x0208 }
            r13[r11] = r10     // Catch:{ SQLiteException -> 0x0208 }
            r13[r12] = r22     // Catch:{ SQLiteException -> 0x0208 }
            r16 = r6
            r17 = r13
        L_0x016e:
            java.lang.String r14 = "raw_events"
            java.lang.String r6 = "rowid"
            java.lang.String r7 = "name"
            java.lang.String r13 = "timestamp"
            java.lang.String r15 = "data"
            java.lang.String[] r15 = new java.lang.String[]{r6, r7, r13, r15}     // Catch:{ SQLiteException -> 0x0208 }
            r18 = 0
            r19 = 0
            java.lang.String r20 = "rowid"
            r21 = 0
            r13 = r9
            android.database.Cursor r4 = r13.query(r14, r15, r16, r17, r18, r19, r20, r21)     // Catch:{ SQLiteException -> 0x0208 }
            boolean r6 = r4.moveToFirst()     // Catch:{ SQLiteException -> 0x0208 }
            if (r6 != 0) goto L_0x01a4
            com.google.android.gms.internal.zzcjj r6 = r5.zzayp()     // Catch:{ SQLiteException -> 0x0208 }
            com.google.android.gms.internal.zzcjl r6 = r6.zzbaw()     // Catch:{ SQLiteException -> 0x0208 }
            java.lang.String r7 = "Raw event data disappeared while in transaction. appId"
            java.lang.Object r8 = com.google.android.gms.internal.zzcjj.zzjs(r10)     // Catch:{ SQLiteException -> 0x0208 }
            r6.zzj(r7, r8)     // Catch:{ SQLiteException -> 0x0208 }
            if (r4 == 0) goto L_0x022c
            goto L_0x0078
        L_0x01a4:
            long r6 = r4.getLong(r11)     // Catch:{ SQLiteException -> 0x0208 }
            byte[] r9 = r4.getBlob(r8)     // Catch:{ SQLiteException -> 0x0208 }
            int r13 = r9.length     // Catch:{ SQLiteException -> 0x0208 }
            com.google.android.gms.internal.zzflj r9 = com.google.android.gms.internal.zzflj.zzo(r9, r11, r13)     // Catch:{ SQLiteException -> 0x0208 }
            com.google.android.gms.internal.zzcob r13 = new com.google.android.gms.internal.zzcob     // Catch:{ SQLiteException -> 0x0208 }
            r13.<init>()     // Catch:{ SQLiteException -> 0x0208 }
            r13.zza((com.google.android.gms.internal.zzflj) r9)     // Catch:{ IOException -> 0x01d4 }
            java.lang.String r9 = r4.getString(r12)     // Catch:{ SQLiteException -> 0x0208 }
            r13.name = r9     // Catch:{ SQLiteException -> 0x0208 }
            r9 = 2
            long r14 = r4.getLong(r9)     // Catch:{ SQLiteException -> 0x0208 }
            java.lang.Long r9 = java.lang.Long.valueOf(r14)     // Catch:{ SQLiteException -> 0x0208 }
            r13.zzjuj = r9     // Catch:{ SQLiteException -> 0x0208 }
            boolean r6 = r3.zza(r6, r13)     // Catch:{ SQLiteException -> 0x0208 }
            if (r6 != 0) goto L_0x01e7
            if (r4 == 0) goto L_0x022c
            goto L_0x0078
        L_0x01d4:
            r0 = move-exception
            r6 = r0
            com.google.android.gms.internal.zzcjj r7 = r5.zzayp()     // Catch:{ SQLiteException -> 0x0208 }
            com.google.android.gms.internal.zzcjl r7 = r7.zzbau()     // Catch:{ SQLiteException -> 0x0208 }
            java.lang.String r9 = "Data loss. Failed to merge raw event. appId"
            java.lang.Object r13 = com.google.android.gms.internal.zzcjj.zzjs(r10)     // Catch:{ SQLiteException -> 0x0208 }
            r7.zze(r9, r13, r6)     // Catch:{ SQLiteException -> 0x0208 }
        L_0x01e7:
            boolean r6 = r4.moveToNext()     // Catch:{ SQLiteException -> 0x0208 }
            if (r6 != 0) goto L_0x01a4
            if (r4 == 0) goto L_0x022c
            goto L_0x0078
        L_0x01f1:
            r0 = move-exception
            r6 = r0
            com.google.android.gms.internal.zzcjj r7 = r5.zzayp()     // Catch:{ SQLiteException -> 0x0208 }
            com.google.android.gms.internal.zzcjl r7 = r7.zzbau()     // Catch:{ SQLiteException -> 0x0208 }
            java.lang.String r8 = "Data loss. Failed to merge raw event metadata. appId"
            java.lang.Object r9 = com.google.android.gms.internal.zzcjj.zzjs(r10)     // Catch:{ SQLiteException -> 0x0208 }
            r7.zze(r8, r9, r6)     // Catch:{ SQLiteException -> 0x0208 }
            if (r4 == 0) goto L_0x022c
            goto L_0x0078
        L_0x0208:
            r0 = move-exception
            r6 = r0
            goto L_0x0217
        L_0x020b:
            r0 = move-exception
            r6 = r0
            goto L_0x0216
        L_0x020e:
            r0 = move-exception
            r1 = r0
            r4 = 0
            goto L_0x0a00
        L_0x0213:
            r0 = move-exception
            r6 = r0
            r4 = 0
        L_0x0216:
            r10 = 0
        L_0x0217:
            com.google.android.gms.internal.zzcjj r5 = r5.zzayp()     // Catch:{ all -> 0x09fe }
            com.google.android.gms.internal.zzcjl r5 = r5.zzbau()     // Catch:{ all -> 0x09fe }
            java.lang.String r7 = "Data loss. Error selecting raw event. appId"
            java.lang.Object r8 = com.google.android.gms.internal.zzcjj.zzjs(r10)     // Catch:{ all -> 0x09fe }
            r5.zze(r7, r8, r6)     // Catch:{ all -> 0x09fe }
            if (r4 == 0) goto L_0x022c
            goto L_0x0078
        L_0x022c:
            java.util.List<com.google.android.gms.internal.zzcob> r4 = r3.zzaoz     // Catch:{ all -> 0x0a06 }
            if (r4 == 0) goto L_0x023b
            java.util.List<com.google.android.gms.internal.zzcob> r4 = r3.zzaoz     // Catch:{ all -> 0x0a06 }
            boolean r4 = r4.isEmpty()     // Catch:{ all -> 0x0a06 }
            if (r4 == 0) goto L_0x0239
            goto L_0x023b
        L_0x0239:
            r4 = 0
            goto L_0x023c
        L_0x023b:
            r4 = 1
        L_0x023c:
            if (r4 != 0) goto L_0x09ee
            com.google.android.gms.internal.zzcoe r4 = r3.zzjpe     // Catch:{ all -> 0x0a06 }
            java.util.List<com.google.android.gms.internal.zzcob> r5 = r3.zzaoz     // Catch:{ all -> 0x0a06 }
            int r5 = r5.size()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcob[] r5 = new com.google.android.gms.internal.zzcob[r5]     // Catch:{ all -> 0x0a06 }
            r4.zzjuq = r5     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcik r5 = r1.zzjns     // Catch:{ all -> 0x0a06 }
            java.lang.String r6 = r4.zzcm     // Catch:{ all -> 0x0a06 }
            boolean r5 = r5.zzjh(r6)     // Catch:{ all -> 0x0a06 }
            r8 = 0
            r9 = 0
            r10 = 0
            r13 = 0
        L_0x0257:
            java.util.List<com.google.android.gms.internal.zzcob> r15 = r3.zzaoz     // Catch:{ all -> 0x0a06 }
            int r15 = r15.size()     // Catch:{ all -> 0x0a06 }
            r16 = 1
            if (r8 >= r15) goto L_0x053a
            java.util.List<com.google.android.gms.internal.zzcob> r15 = r3.zzaoz     // Catch:{ all -> 0x0a06 }
            java.lang.Object r15 = r15.get(r8)     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcob r15 = (com.google.android.gms.internal.zzcob) r15     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzckd r6 = r37.zzaym()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoe r7 = r3.zzjpe     // Catch:{ all -> 0x0a06 }
            java.lang.String r7 = r7.zzcm     // Catch:{ all -> 0x0a06 }
            java.lang.String r12 = r15.name     // Catch:{ all -> 0x0a06 }
            boolean r6 = r6.zzan(r7, r12)     // Catch:{ all -> 0x0a06 }
            java.lang.String r7 = "_err"
            if (r6 == 0) goto L_0x02e5
            com.google.android.gms.internal.zzcjj r6 = r37.zzayp()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcjl r6 = r6.zzbaw()     // Catch:{ all -> 0x0a06 }
            java.lang.String r12 = "Dropping blacklisted raw event. appId"
            com.google.android.gms.internal.zzcoe r11 = r3.zzjpe     // Catch:{ all -> 0x0a06 }
            java.lang.String r11 = r11.zzcm     // Catch:{ all -> 0x0a06 }
            java.lang.Object r11 = com.google.android.gms.internal.zzcjj.zzjs(r11)     // Catch:{ all -> 0x0a06 }
            r20 = r10
            com.google.android.gms.internal.zzcjh r10 = r37.zzayk()     // Catch:{ all -> 0x0a06 }
            r21 = r2
            java.lang.String r2 = r15.name     // Catch:{ all -> 0x0a06 }
            java.lang.String r2 = r10.zzjp(r2)     // Catch:{ all -> 0x0a06 }
            r6.zze(r12, r11, r2)     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcno r2 = r37.zzayl()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoe r6 = r3.zzjpe     // Catch:{ all -> 0x0a06 }
            java.lang.String r6 = r6.zzcm     // Catch:{ all -> 0x0a06 }
            boolean r2 = r2.zzks(r6)     // Catch:{ all -> 0x0a06 }
            if (r2 != 0) goto L_0x02bd
            com.google.android.gms.internal.zzcno r2 = r37.zzayl()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoe r6 = r3.zzjpe     // Catch:{ all -> 0x0a06 }
            java.lang.String r6 = r6.zzcm     // Catch:{ all -> 0x0a06 }
            boolean r2 = r2.zzkt(r6)     // Catch:{ all -> 0x0a06 }
            if (r2 == 0) goto L_0x02bb
            goto L_0x02bd
        L_0x02bb:
            r2 = 0
            goto L_0x02be
        L_0x02bd:
            r2 = 1
        L_0x02be:
            if (r2 != 0) goto L_0x02df
            java.lang.String r2 = r15.name     // Catch:{ all -> 0x0a06 }
            boolean r2 = r7.equals(r2)     // Catch:{ all -> 0x0a06 }
            if (r2 != 0) goto L_0x02df
            com.google.android.gms.internal.zzcno r23 = r37.zzayl()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoe r2 = r3.zzjpe     // Catch:{ all -> 0x0a06 }
            java.lang.String r2 = r2.zzcm     // Catch:{ all -> 0x0a06 }
            r25 = 11
            java.lang.String r26 = "_ev"
            java.lang.String r6 = r15.name     // Catch:{ all -> 0x0a06 }
            r28 = 0
            r24 = r2
            r27 = r6
            r23.zza((java.lang.String) r24, (int) r25, (java.lang.String) r26, (java.lang.String) r27, (int) r28)     // Catch:{ all -> 0x0a06 }
        L_0x02df:
            r23 = r8
            r10 = r20
            goto L_0x0532
        L_0x02e5:
            r21 = r2
            r20 = r10
            com.google.android.gms.internal.zzckd r2 = r37.zzaym()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoe r6 = r3.zzjpe     // Catch:{ all -> 0x0a06 }
            java.lang.String r6 = r6.zzcm     // Catch:{ all -> 0x0a06 }
            java.lang.String r10 = r15.name     // Catch:{ all -> 0x0a06 }
            boolean r2 = r2.zzao(r6, r10)     // Catch:{ all -> 0x0a06 }
            if (r2 != 0) goto L_0x030f
            r37.zzayl()     // Catch:{ all -> 0x0a06 }
            java.lang.String r6 = r15.name     // Catch:{ all -> 0x0a06 }
            boolean r6 = com.google.android.gms.internal.zzcno.zzku(r6)     // Catch:{ all -> 0x0a06 }
            if (r6 == 0) goto L_0x0305
            goto L_0x030f
        L_0x0305:
            r23 = r8
            r24 = r9
            r25 = r13
            r10 = r20
            goto L_0x04d7
        L_0x030f:
            com.google.android.gms.internal.zzcoc[] r6 = r15.zzjui     // Catch:{ all -> 0x0a06 }
            if (r6 != 0) goto L_0x0318
            r6 = 0
            com.google.android.gms.internal.zzcoc[] r10 = new com.google.android.gms.internal.zzcoc[r6]     // Catch:{ all -> 0x0a06 }
            r15.zzjui = r10     // Catch:{ all -> 0x0a06 }
        L_0x0318:
            com.google.android.gms.internal.zzcoc[] r6 = r15.zzjui     // Catch:{ all -> 0x0a06 }
            int r10 = r6.length     // Catch:{ all -> 0x0a06 }
            r23 = r8
            r11 = 0
            r12 = 0
            r22 = 0
        L_0x0321:
            java.lang.String r8 = "_r"
            r24 = r9
            java.lang.String r9 = "_c"
            if (r12 >= r10) goto L_0x0358
            r25 = r10
            r10 = r6[r12]     // Catch:{ all -> 0x0a06 }
            r26 = r6
            java.lang.String r6 = r10.name     // Catch:{ all -> 0x0a06 }
            boolean r6 = r9.equals(r6)     // Catch:{ all -> 0x0a06 }
            if (r6 == 0) goto L_0x033f
            java.lang.Long r6 = java.lang.Long.valueOf(r16)     // Catch:{ all -> 0x0a06 }
            r10.zzjum = r6     // Catch:{ all -> 0x0a06 }
            r11 = 1
            goto L_0x034f
        L_0x033f:
            java.lang.String r6 = r10.name     // Catch:{ all -> 0x0a06 }
            boolean r6 = r8.equals(r6)     // Catch:{ all -> 0x0a06 }
            if (r6 == 0) goto L_0x034f
            java.lang.Long r6 = java.lang.Long.valueOf(r16)     // Catch:{ all -> 0x0a06 }
            r10.zzjum = r6     // Catch:{ all -> 0x0a06 }
            r22 = 1
        L_0x034f:
            int r12 = r12 + 1
            r9 = r24
            r10 = r25
            r6 = r26
            goto L_0x0321
        L_0x0358:
            if (r11 != 0) goto L_0x0394
            if (r2 == 0) goto L_0x0394
            com.google.android.gms.internal.zzcjj r6 = r37.zzayp()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcjl r6 = r6.zzbba()     // Catch:{ all -> 0x0a06 }
            java.lang.String r10 = "Marking event as conversion"
            com.google.android.gms.internal.zzcjh r11 = r37.zzayk()     // Catch:{ all -> 0x0a06 }
            java.lang.String r12 = r15.name     // Catch:{ all -> 0x0a06 }
            java.lang.String r11 = r11.zzjp(r12)     // Catch:{ all -> 0x0a06 }
            r6.zzj(r10, r11)     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoc[] r6 = r15.zzjui     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoc[] r10 = r15.zzjui     // Catch:{ all -> 0x0a06 }
            int r10 = r10.length     // Catch:{ all -> 0x0a06 }
            r11 = 1
            int r10 = r10 + r11
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r6, r10)     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoc[] r6 = (com.google.android.gms.internal.zzcoc[]) r6     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoc r10 = new com.google.android.gms.internal.zzcoc     // Catch:{ all -> 0x0a06 }
            r10.<init>()     // Catch:{ all -> 0x0a06 }
            r10.name = r9     // Catch:{ all -> 0x0a06 }
            java.lang.Long r11 = java.lang.Long.valueOf(r16)     // Catch:{ all -> 0x0a06 }
            r10.zzjum = r11     // Catch:{ all -> 0x0a06 }
            int r11 = r6.length     // Catch:{ all -> 0x0a06 }
            r12 = 1
            int r11 = r11 - r12
            r6[r11] = r10     // Catch:{ all -> 0x0a06 }
            r15.zzjui = r6     // Catch:{ all -> 0x0a06 }
        L_0x0394:
            if (r22 != 0) goto L_0x03ce
            com.google.android.gms.internal.zzcjj r6 = r37.zzayp()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcjl r6 = r6.zzbba()     // Catch:{ all -> 0x0a06 }
            java.lang.String r10 = "Marking event as real-time"
            com.google.android.gms.internal.zzcjh r11 = r37.zzayk()     // Catch:{ all -> 0x0a06 }
            java.lang.String r12 = r15.name     // Catch:{ all -> 0x0a06 }
            java.lang.String r11 = r11.zzjp(r12)     // Catch:{ all -> 0x0a06 }
            r6.zzj(r10, r11)     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoc[] r6 = r15.zzjui     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoc[] r10 = r15.zzjui     // Catch:{ all -> 0x0a06 }
            int r10 = r10.length     // Catch:{ all -> 0x0a06 }
            r11 = 1
            int r10 = r10 + r11
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r6, r10)     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoc[] r6 = (com.google.android.gms.internal.zzcoc[]) r6     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoc r10 = new com.google.android.gms.internal.zzcoc     // Catch:{ all -> 0x0a06 }
            r10.<init>()     // Catch:{ all -> 0x0a06 }
            r10.name = r8     // Catch:{ all -> 0x0a06 }
            java.lang.Long r11 = java.lang.Long.valueOf(r16)     // Catch:{ all -> 0x0a06 }
            r10.zzjum = r11     // Catch:{ all -> 0x0a06 }
            int r11 = r6.length     // Catch:{ all -> 0x0a06 }
            r12 = 1
            int r11 = r11 - r12
            r6[r11] = r10     // Catch:{ all -> 0x0a06 }
            r15.zzjui = r6     // Catch:{ all -> 0x0a06 }
        L_0x03ce:
            com.google.android.gms.internal.zzcil r25 = r37.zzayj()     // Catch:{ all -> 0x0a06 }
            long r26 = r37.zzbbx()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoe r6 = r3.zzjpe     // Catch:{ all -> 0x0a06 }
            java.lang.String r6 = r6.zzcm     // Catch:{ all -> 0x0a06 }
            r29 = 0
            r30 = 0
            r31 = 0
            r32 = 0
            r33 = 1
            r28 = r6
            com.google.android.gms.internal.zzcim r6 = r25.zza(r26, r28, r29, r30, r31, r32, r33)     // Catch:{ all -> 0x0a06 }
            long r10 = r6.zzjhi     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcik r6 = r1.zzjns     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoe r12 = r3.zzjpe     // Catch:{ all -> 0x0a06 }
            java.lang.String r12 = r12.zzcm     // Catch:{ all -> 0x0a06 }
            int r6 = r6.zzje(r12)     // Catch:{ all -> 0x0a06 }
            r25 = r13
            long r12 = (long) r6     // Catch:{ all -> 0x0a06 }
            int r6 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r6 <= 0) goto L_0x0431
            r6 = 0
        L_0x03fe:
            com.google.android.gms.internal.zzcoc[] r10 = r15.zzjui     // Catch:{ all -> 0x0a06 }
            int r10 = r10.length     // Catch:{ all -> 0x0a06 }
            if (r6 >= r10) goto L_0x042e
            com.google.android.gms.internal.zzcoc[] r10 = r15.zzjui     // Catch:{ all -> 0x0a06 }
            r10 = r10[r6]     // Catch:{ all -> 0x0a06 }
            java.lang.String r10 = r10.name     // Catch:{ all -> 0x0a06 }
            boolean r10 = r8.equals(r10)     // Catch:{ all -> 0x0a06 }
            if (r10 == 0) goto L_0x042b
            com.google.android.gms.internal.zzcoc[] r8 = r15.zzjui     // Catch:{ all -> 0x0a06 }
            int r8 = r8.length     // Catch:{ all -> 0x0a06 }
            r10 = 1
            int r8 = r8 - r10
            com.google.android.gms.internal.zzcoc[] r10 = new com.google.android.gms.internal.zzcoc[r8]     // Catch:{ all -> 0x0a06 }
            if (r6 <= 0) goto L_0x041e
            com.google.android.gms.internal.zzcoc[] r11 = r15.zzjui     // Catch:{ all -> 0x0a06 }
            r12 = 0
            java.lang.System.arraycopy(r11, r12, r10, r12, r6)     // Catch:{ all -> 0x0a06 }
        L_0x041e:
            if (r6 >= r8) goto L_0x0428
            com.google.android.gms.internal.zzcoc[] r11 = r15.zzjui     // Catch:{ all -> 0x0a06 }
            int r12 = r6 + 1
            int r8 = r8 - r6
            java.lang.System.arraycopy(r11, r12, r10, r6, r8)     // Catch:{ all -> 0x0a06 }
        L_0x0428:
            r15.zzjui = r10     // Catch:{ all -> 0x0a06 }
            goto L_0x042e
        L_0x042b:
            int r6 = r6 + 1
            goto L_0x03fe
        L_0x042e:
            r10 = r20
            goto L_0x0432
        L_0x0431:
            r10 = 1
        L_0x0432:
            java.lang.String r6 = r15.name     // Catch:{ all -> 0x0a06 }
            boolean r6 = com.google.android.gms.internal.zzcno.zzkh(r6)     // Catch:{ all -> 0x0a06 }
            if (r6 == 0) goto L_0x04d7
            if (r2 == 0) goto L_0x04d7
            com.google.android.gms.internal.zzcil r27 = r37.zzayj()     // Catch:{ all -> 0x0a06 }
            long r28 = r37.zzbbx()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoe r2 = r3.zzjpe     // Catch:{ all -> 0x0a06 }
            java.lang.String r2 = r2.zzcm     // Catch:{ all -> 0x0a06 }
            r31 = 0
            r32 = 0
            r33 = 1
            r34 = 0
            r35 = 0
            r30 = r2
            com.google.android.gms.internal.zzcim r2 = r27.zza(r28, r30, r31, r32, r33, r34, r35)     // Catch:{ all -> 0x0a06 }
            long r11 = r2.zzjhg     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcik r2 = r1.zzjns     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoe r6 = r3.zzjpe     // Catch:{ all -> 0x0a06 }
            java.lang.String r6 = r6.zzcm     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcja<java.lang.Integer> r8 = com.google.android.gms.internal.zzciz.zzjja     // Catch:{ all -> 0x0a06 }
            int r2 = r2.zzb(r6, r8)     // Catch:{ all -> 0x0a06 }
            long r13 = (long) r2     // Catch:{ all -> 0x0a06 }
            int r2 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r2 <= 0) goto L_0x04d7
            com.google.android.gms.internal.zzcjj r2 = r37.zzayp()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcjl r2 = r2.zzbaw()     // Catch:{ all -> 0x0a06 }
            java.lang.String r6 = "Too many conversions. Not logging as conversion. appId"
            com.google.android.gms.internal.zzcoe r8 = r3.zzjpe     // Catch:{ all -> 0x0a06 }
            java.lang.String r8 = r8.zzcm     // Catch:{ all -> 0x0a06 }
            java.lang.Object r8 = com.google.android.gms.internal.zzcjj.zzjs(r8)     // Catch:{ all -> 0x0a06 }
            r2.zzj(r6, r8)     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoc[] r2 = r15.zzjui     // Catch:{ all -> 0x0a06 }
            int r6 = r2.length     // Catch:{ all -> 0x0a06 }
            r8 = 0
            r11 = 0
            r12 = 0
        L_0x0486:
            if (r12 >= r6) goto L_0x04a0
            r13 = r2[r12]     // Catch:{ all -> 0x0a06 }
            java.lang.String r14 = r13.name     // Catch:{ all -> 0x0a06 }
            boolean r14 = r9.equals(r14)     // Catch:{ all -> 0x0a06 }
            if (r14 == 0) goto L_0x0494
            r8 = r13
            goto L_0x049d
        L_0x0494:
            java.lang.String r13 = r13.name     // Catch:{ all -> 0x0a06 }
            boolean r13 = r7.equals(r13)     // Catch:{ all -> 0x0a06 }
            if (r13 == 0) goto L_0x049d
            r11 = 1
        L_0x049d:
            int r12 = r12 + 1
            goto L_0x0486
        L_0x04a0:
            if (r11 == 0) goto L_0x04b5
            if (r8 == 0) goto L_0x04b5
            com.google.android.gms.internal.zzcoc[] r2 = r15.zzjui     // Catch:{ all -> 0x0a06 }
            r6 = 1
            com.google.android.gms.internal.zzcoc[] r7 = new com.google.android.gms.internal.zzcoc[r6]     // Catch:{ all -> 0x0a06 }
            r6 = 0
            r7[r6] = r8     // Catch:{ all -> 0x0a06 }
            java.lang.Object[] r2 = com.google.android.gms.common.util.zzb.zza(r2, r7)     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoc[] r2 = (com.google.android.gms.internal.zzcoc[]) r2     // Catch:{ all -> 0x0a06 }
            r15.zzjui = r2     // Catch:{ all -> 0x0a06 }
            goto L_0x04d7
        L_0x04b5:
            if (r8 == 0) goto L_0x04c2
            r8.name = r7     // Catch:{ all -> 0x0a06 }
            r6 = 10
            java.lang.Long r2 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0a06 }
            r8.zzjum = r2     // Catch:{ all -> 0x0a06 }
            goto L_0x04d7
        L_0x04c2:
            com.google.android.gms.internal.zzcjj r2 = r37.zzayp()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcjl r2 = r2.zzbau()     // Catch:{ all -> 0x0a06 }
            java.lang.String r6 = "Did not find conversion parameter. appId"
            com.google.android.gms.internal.zzcoe r7 = r3.zzjpe     // Catch:{ all -> 0x0a06 }
            java.lang.String r7 = r7.zzcm     // Catch:{ all -> 0x0a06 }
            java.lang.Object r7 = com.google.android.gms.internal.zzcjj.zzjs(r7)     // Catch:{ all -> 0x0a06 }
            r2.zzj(r6, r7)     // Catch:{ all -> 0x0a06 }
        L_0x04d7:
            if (r5 == 0) goto L_0x052a
            java.lang.String r2 = "_e"
            java.lang.String r6 = r15.name     // Catch:{ all -> 0x0a06 }
            boolean r2 = r2.equals(r6)     // Catch:{ all -> 0x0a06 }
            if (r2 == 0) goto L_0x052a
            com.google.android.gms.internal.zzcoc[] r2 = r15.zzjui     // Catch:{ all -> 0x0a06 }
            if (r2 == 0) goto L_0x0517
            com.google.android.gms.internal.zzcoc[] r2 = r15.zzjui     // Catch:{ all -> 0x0a06 }
            int r2 = r2.length     // Catch:{ all -> 0x0a06 }
            if (r2 != 0) goto L_0x04ed
            goto L_0x0517
        L_0x04ed:
            r37.zzayl()     // Catch:{ all -> 0x0a06 }
            java.lang.String r2 = "_et"
            java.lang.Object r2 = com.google.android.gms.internal.zzcno.zzb((com.google.android.gms.internal.zzcob) r15, (java.lang.String) r2)     // Catch:{ all -> 0x0a06 }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ all -> 0x0a06 }
            if (r2 != 0) goto L_0x0510
            com.google.android.gms.internal.zzcjj r2 = r37.zzayp()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcjl r2 = r2.zzbaw()     // Catch:{ all -> 0x0a06 }
            java.lang.String r6 = "Engagement event does not include duration. appId"
            com.google.android.gms.internal.zzcoe r7 = r3.zzjpe     // Catch:{ all -> 0x0a06 }
            java.lang.String r7 = r7.zzcm     // Catch:{ all -> 0x0a06 }
            java.lang.Object r7 = com.google.android.gms.internal.zzcjj.zzjs(r7)     // Catch:{ all -> 0x0a06 }
        L_0x050c:
            r2.zzj(r6, r7)     // Catch:{ all -> 0x0a06 }
            goto L_0x052a
        L_0x0510:
            long r6 = r2.longValue()     // Catch:{ all -> 0x0a06 }
            long r13 = r25 + r6
            goto L_0x052c
        L_0x0517:
            com.google.android.gms.internal.zzcjj r2 = r37.zzayp()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcjl r2 = r2.zzbaw()     // Catch:{ all -> 0x0a06 }
            java.lang.String r6 = "Engagement event does not contain any parameters. appId"
            com.google.android.gms.internal.zzcoe r7 = r3.zzjpe     // Catch:{ all -> 0x0a06 }
            java.lang.String r7 = r7.zzcm     // Catch:{ all -> 0x0a06 }
            java.lang.Object r7 = com.google.android.gms.internal.zzcjj.zzjs(r7)     // Catch:{ all -> 0x0a06 }
            goto L_0x050c
        L_0x052a:
            r13 = r25
        L_0x052c:
            com.google.android.gms.internal.zzcob[] r2 = r4.zzjuq     // Catch:{ all -> 0x0a06 }
            int r9 = r24 + 1
            r2[r24] = r15     // Catch:{ all -> 0x0a06 }
        L_0x0532:
            int r8 = r23 + 1
            r2 = r21
            r11 = 0
            r12 = 1
            goto L_0x0257
        L_0x053a:
            r21 = r2
            r24 = r9
            r20 = r10
            r25 = r13
            java.util.List<com.google.android.gms.internal.zzcob> r2 = r3.zzaoz     // Catch:{ all -> 0x0a06 }
            int r2 = r2.size()     // Catch:{ all -> 0x0a06 }
            r9 = r24
            if (r9 >= r2) goto L_0x0556
            com.google.android.gms.internal.zzcob[] r2 = r4.zzjuq     // Catch:{ all -> 0x0a06 }
            java.lang.Object[] r2 = java.util.Arrays.copyOf(r2, r9)     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcob[] r2 = (com.google.android.gms.internal.zzcob[]) r2     // Catch:{ all -> 0x0a06 }
            r4.zzjuq = r2     // Catch:{ all -> 0x0a06 }
        L_0x0556:
            if (r5 == 0) goto L_0x0612
            com.google.android.gms.internal.zzcil r2 = r37.zzayj()     // Catch:{ all -> 0x0a06 }
            java.lang.String r5 = r4.zzcm     // Catch:{ all -> 0x0a06 }
            r6 = r21
            com.google.android.gms.internal.zzcnn r2 = r2.zzag(r5, r6)     // Catch:{ all -> 0x0a06 }
            if (r2 == 0) goto L_0x058c
            java.lang.Object r5 = r2.value     // Catch:{ all -> 0x0a06 }
            if (r5 != 0) goto L_0x056b
            goto L_0x058c
        L_0x056b:
            com.google.android.gms.internal.zzcnn r5 = new com.google.android.gms.internal.zzcnn     // Catch:{ all -> 0x0a06 }
            java.lang.String r8 = r4.zzcm     // Catch:{ all -> 0x0a06 }
            java.lang.String r9 = "auto"
            java.lang.String r10 = "_lte"
            com.google.android.gms.common.util.zze r7 = r1.zzdir     // Catch:{ all -> 0x0a06 }
            long r11 = r7.currentTimeMillis()     // Catch:{ all -> 0x0a06 }
            java.lang.Object r2 = r2.value     // Catch:{ all -> 0x0a06 }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ all -> 0x0a06 }
            long r13 = r2.longValue()     // Catch:{ all -> 0x0a06 }
            long r13 = r13 + r25
            java.lang.Long r13 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x0a06 }
            r7 = r5
            r7.<init>(r8, r9, r10, r11, r13)     // Catch:{ all -> 0x0a06 }
            goto L_0x05a5
        L_0x058c:
            com.google.android.gms.internal.zzcnn r5 = new com.google.android.gms.internal.zzcnn     // Catch:{ all -> 0x0a06 }
            java.lang.String r2 = r4.zzcm     // Catch:{ all -> 0x0a06 }
            java.lang.String r29 = "auto"
            java.lang.String r30 = "_lte"
            com.google.android.gms.common.util.zze r7 = r1.zzdir     // Catch:{ all -> 0x0a06 }
            long r31 = r7.currentTimeMillis()     // Catch:{ all -> 0x0a06 }
            java.lang.Long r33 = java.lang.Long.valueOf(r25)     // Catch:{ all -> 0x0a06 }
            r27 = r5
            r28 = r2
            r27.<init>(r28, r29, r30, r31, r33)     // Catch:{ all -> 0x0a06 }
        L_0x05a5:
            com.google.android.gms.internal.zzcog r2 = new com.google.android.gms.internal.zzcog     // Catch:{ all -> 0x0a06 }
            r2.<init>()     // Catch:{ all -> 0x0a06 }
            r2.name = r6     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.common.util.zze r7 = r1.zzdir     // Catch:{ all -> 0x0a06 }
            long r7 = r7.currentTimeMillis()     // Catch:{ all -> 0x0a06 }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x0a06 }
            r2.zzjvr = r7     // Catch:{ all -> 0x0a06 }
            java.lang.Object r7 = r5.value     // Catch:{ all -> 0x0a06 }
            java.lang.Long r7 = (java.lang.Long) r7     // Catch:{ all -> 0x0a06 }
            r2.zzjum = r7     // Catch:{ all -> 0x0a06 }
            r7 = 0
        L_0x05bf:
            com.google.android.gms.internal.zzcog[] r8 = r4.zzjur     // Catch:{ all -> 0x0a06 }
            int r8 = r8.length     // Catch:{ all -> 0x0a06 }
            if (r7 >= r8) goto L_0x05d9
            com.google.android.gms.internal.zzcog[] r8 = r4.zzjur     // Catch:{ all -> 0x0a06 }
            r8 = r8[r7]     // Catch:{ all -> 0x0a06 }
            java.lang.String r8 = r8.name     // Catch:{ all -> 0x0a06 }
            boolean r8 = r6.equals(r8)     // Catch:{ all -> 0x0a06 }
            if (r8 == 0) goto L_0x05d6
            com.google.android.gms.internal.zzcog[] r6 = r4.zzjur     // Catch:{ all -> 0x0a06 }
            r6[r7] = r2     // Catch:{ all -> 0x0a06 }
            r6 = 1
            goto L_0x05da
        L_0x05d6:
            int r7 = r7 + 1
            goto L_0x05bf
        L_0x05d9:
            r6 = 0
        L_0x05da:
            if (r6 != 0) goto L_0x05f6
            com.google.android.gms.internal.zzcog[] r6 = r4.zzjur     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcog[] r7 = r4.zzjur     // Catch:{ all -> 0x0a06 }
            int r7 = r7.length     // Catch:{ all -> 0x0a06 }
            r8 = 1
            int r7 = r7 + r8
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r6, r7)     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcog[] r6 = (com.google.android.gms.internal.zzcog[]) r6     // Catch:{ all -> 0x0a06 }
            r4.zzjur = r6     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcog[] r6 = r4.zzjur     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoe r7 = r3.zzjpe     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcog[] r7 = r7.zzjur     // Catch:{ all -> 0x0a06 }
            int r7 = r7.length     // Catch:{ all -> 0x0a06 }
            r8 = 1
            int r7 = r7 - r8
            r6[r7] = r2     // Catch:{ all -> 0x0a06 }
        L_0x05f6:
            r6 = 0
            int r2 = (r25 > r6 ? 1 : (r25 == r6 ? 0 : -1))
            if (r2 <= 0) goto L_0x0612
            com.google.android.gms.internal.zzcil r2 = r37.zzayj()     // Catch:{ all -> 0x0a06 }
            r2.zza((com.google.android.gms.internal.zzcnn) r5)     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcjj r2 = r37.zzayp()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcjl r2 = r2.zzbaz()     // Catch:{ all -> 0x0a06 }
            java.lang.String r6 = "Updated lifetime engagement user property with value. Value"
            java.lang.Object r5 = r5.value     // Catch:{ all -> 0x0a06 }
            r2.zzj(r6, r5)     // Catch:{ all -> 0x0a06 }
        L_0x0612:
            java.lang.String r2 = r4.zzcm     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcog[] r5 = r4.zzjur     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcob[] r6 = r4.zzjuq     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoa[] r2 = r1.zza((java.lang.String) r2, (com.google.android.gms.internal.zzcog[]) r5, (com.google.android.gms.internal.zzcob[]) r6)     // Catch:{ all -> 0x0a06 }
            r4.zzjvh = r2     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcja<java.lang.Boolean> r2 = com.google.android.gms.internal.zzciz.zzjim     // Catch:{ all -> 0x0a06 }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x0a06 }
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ all -> 0x0a06 }
            boolean r2 = r2.booleanValue()     // Catch:{ all -> 0x0a06 }
            if (r2 == 0) goto L_0x0895
            com.google.android.gms.internal.zzcik r2 = r1.zzjns     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoe r5 = r3.zzjpe     // Catch:{ all -> 0x0a06 }
            java.lang.String r5 = r5.zzcm     // Catch:{ all -> 0x0a06 }
            java.lang.String r6 = "1"
            com.google.android.gms.internal.zzckd r2 = r2.zzaym()     // Catch:{ all -> 0x0a06 }
            java.lang.String r7 = "measurement.event_sampling_enabled"
            java.lang.String r2 = r2.zzam(r5, r7)     // Catch:{ all -> 0x0a06 }
            boolean r2 = r6.equals(r2)     // Catch:{ all -> 0x0a06 }
            if (r2 == 0) goto L_0x0895
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ all -> 0x0a06 }
            r2.<init>()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcob[] r5 = r4.zzjuq     // Catch:{ all -> 0x0a06 }
            int r5 = r5.length     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcob[] r5 = new com.google.android.gms.internal.zzcob[r5]     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcno r6 = r37.zzayl()     // Catch:{ all -> 0x0a06 }
            java.security.SecureRandom r6 = r6.zzbcr()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcob[] r7 = r4.zzjuq     // Catch:{ all -> 0x0a06 }
            int r8 = r7.length     // Catch:{ all -> 0x0a06 }
            r9 = 0
            r10 = 0
        L_0x065b:
            if (r9 >= r8) goto L_0x0863
            r11 = r7[r9]     // Catch:{ all -> 0x0a06 }
            java.lang.String r12 = r11.name     // Catch:{ all -> 0x0a06 }
            java.lang.String r13 = "_ep"
            boolean r12 = r12.equals(r13)     // Catch:{ all -> 0x0a06 }
            java.lang.String r13 = "_efs"
            java.lang.String r14 = "_sr"
            if (r12 == 0) goto L_0x06ca
            r37.zzayl()     // Catch:{ all -> 0x0a06 }
            java.lang.String r12 = "_en"
            java.lang.Object r12 = com.google.android.gms.internal.zzcno.zzb((com.google.android.gms.internal.zzcob) r11, (java.lang.String) r12)     // Catch:{ all -> 0x0a06 }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ all -> 0x0a06 }
            java.lang.Object r15 = r2.get(r12)     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcit r15 = (com.google.android.gms.internal.zzcit) r15     // Catch:{ all -> 0x0a06 }
            if (r15 != 0) goto L_0x068f
            com.google.android.gms.internal.zzcil r15 = r37.zzayj()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoe r1 = r3.zzjpe     // Catch:{ all -> 0x0a06 }
            java.lang.String r1 = r1.zzcm     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcit r15 = r15.zzae(r1, r12)     // Catch:{ all -> 0x0a06 }
            r2.put(r12, r15)     // Catch:{ all -> 0x0a06 }
        L_0x068f:
            java.lang.Long r1 = r15.zzjhw     // Catch:{ all -> 0x0a06 }
            if (r1 != 0) goto L_0x0700
            java.lang.Long r1 = r15.zzjhx     // Catch:{ all -> 0x0a06 }
            long r23 = r1.longValue()     // Catch:{ all -> 0x0a06 }
            int r1 = (r23 > r16 ? 1 : (r23 == r16 ? 0 : -1))
            if (r1 <= 0) goto L_0x06aa
            r37.zzayl()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoc[] r1 = r11.zzjui     // Catch:{ all -> 0x0a06 }
            java.lang.Long r12 = r15.zzjhx     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoc[] r1 = com.google.android.gms.internal.zzcno.zza((com.google.android.gms.internal.zzcoc[]) r1, (java.lang.String) r14, (java.lang.Object) r12)     // Catch:{ all -> 0x0a06 }
            r11.zzjui = r1     // Catch:{ all -> 0x0a06 }
        L_0x06aa:
            java.lang.Boolean r1 = r15.zzjhy     // Catch:{ all -> 0x0a06 }
            if (r1 == 0) goto L_0x06c5
            java.lang.Boolean r1 = r15.zzjhy     // Catch:{ all -> 0x0a06 }
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x0a06 }
            if (r1 == 0) goto L_0x06c5
            r37.zzayl()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoc[] r1 = r11.zzjui     // Catch:{ all -> 0x0a06 }
            java.lang.Long r12 = java.lang.Long.valueOf(r16)     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoc[] r1 = com.google.android.gms.internal.zzcno.zza((com.google.android.gms.internal.zzcoc[]) r1, (java.lang.String) r13, (java.lang.Object) r12)     // Catch:{ all -> 0x0a06 }
            r11.zzjui = r1     // Catch:{ all -> 0x0a06 }
        L_0x06c5:
            int r1 = r10 + 1
            r5[r10] = r11     // Catch:{ all -> 0x0a06 }
            goto L_0x06ff
        L_0x06ca:
            java.lang.String r1 = "_dbg"
            java.lang.Long r12 = java.lang.Long.valueOf(r16)     // Catch:{ all -> 0x0a06 }
            boolean r1 = zza((com.google.android.gms.internal.zzcob) r11, (java.lang.String) r1, (java.lang.Object) r12)     // Catch:{ all -> 0x0a06 }
            if (r1 != 0) goto L_0x06e5
            com.google.android.gms.internal.zzckd r1 = r37.zzaym()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoe r12 = r3.zzjpe     // Catch:{ all -> 0x0a06 }
            java.lang.String r12 = r12.zzcm     // Catch:{ all -> 0x0a06 }
            java.lang.String r15 = r11.name     // Catch:{ all -> 0x0a06 }
            int r1 = r1.zzap(r12, r15)     // Catch:{ all -> 0x0a06 }
            goto L_0x06e6
        L_0x06e5:
            r1 = 1
        L_0x06e6:
            if (r1 > 0) goto L_0x070c
            com.google.android.gms.internal.zzcjj r12 = r37.zzayp()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcjl r12 = r12.zzbaw()     // Catch:{ all -> 0x0a06 }
            java.lang.String r13 = "Sample rate must be positive. event, rate"
            java.lang.String r14 = r11.name     // Catch:{ all -> 0x0a06 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0a06 }
            r12.zze(r13, r14, r1)     // Catch:{ all -> 0x0a06 }
            int r1 = r10 + 1
            r5[r10] = r11     // Catch:{ all -> 0x0a06 }
        L_0x06ff:
            r10 = r1
        L_0x0700:
            r15 = r3
            r23 = r4
            r24 = r6
            r21 = r7
            r22 = r8
        L_0x0709:
            r3 = 0
            goto L_0x0854
        L_0x070c:
            java.lang.String r12 = r11.name     // Catch:{ all -> 0x0a06 }
            java.lang.Object r12 = r2.get(r12)     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcit r12 = (com.google.android.gms.internal.zzcit) r12     // Catch:{ all -> 0x0a06 }
            if (r12 != 0) goto L_0x0761
            com.google.android.gms.internal.zzcil r12 = r37.zzayj()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoe r15 = r3.zzjpe     // Catch:{ all -> 0x0a06 }
            java.lang.String r15 = r15.zzcm     // Catch:{ all -> 0x0a06 }
            r21 = r7
            java.lang.String r7 = r11.name     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcit r12 = r12.zzae(r15, r7)     // Catch:{ all -> 0x0a06 }
            if (r12 != 0) goto L_0x0763
            com.google.android.gms.internal.zzcjj r7 = r37.zzayp()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcjl r7 = r7.zzbaw()     // Catch:{ all -> 0x0a06 }
            java.lang.String r12 = "Event being bundled has no eventAggregate. appId, eventName"
            com.google.android.gms.internal.zzcoe r15 = r3.zzjpe     // Catch:{ all -> 0x0a06 }
            java.lang.String r15 = r15.zzcm     // Catch:{ all -> 0x0a06 }
            r22 = r8
            java.lang.String r8 = r11.name     // Catch:{ all -> 0x0a06 }
            r7.zze(r12, r15, r8)     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcit r12 = new com.google.android.gms.internal.zzcit     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoe r7 = r3.zzjpe     // Catch:{ all -> 0x0a06 }
            java.lang.String r7 = r7.zzcm     // Catch:{ all -> 0x0a06 }
            java.lang.String r8 = r11.name     // Catch:{ all -> 0x0a06 }
            r26 = 1
            r28 = 1
            java.lang.Long r15 = r11.zzjuj     // Catch:{ all -> 0x0a06 }
            long r30 = r15.longValue()     // Catch:{ all -> 0x0a06 }
            r32 = 0
            r34 = 0
            r35 = 0
            r36 = 0
            r23 = r12
            r24 = r7
            r25 = r8
            r23.<init>(r24, r25, r26, r28, r30, r32, r34, r35, r36)     // Catch:{ all -> 0x0a06 }
            goto L_0x0765
        L_0x0761:
            r21 = r7
        L_0x0763:
            r22 = r8
        L_0x0765:
            r37.zzayl()     // Catch:{ all -> 0x0a06 }
            java.lang.String r7 = "_eid"
            java.lang.Object r7 = com.google.android.gms.internal.zzcno.zzb((com.google.android.gms.internal.zzcob) r11, (java.lang.String) r7)     // Catch:{ all -> 0x0a06 }
            java.lang.Long r7 = (java.lang.Long) r7     // Catch:{ all -> 0x0a06 }
            if (r7 == 0) goto L_0x0774
            r8 = 1
            goto L_0x0775
        L_0x0774:
            r8 = 0
        L_0x0775:
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)     // Catch:{ all -> 0x0a06 }
            r15 = 1
            if (r1 != r15) goto L_0x07a4
            int r1 = r10 + 1
            r5[r10] = r11     // Catch:{ all -> 0x0a06 }
            boolean r7 = r8.booleanValue()     // Catch:{ all -> 0x0a06 }
            if (r7 == 0) goto L_0x079c
            java.lang.Long r7 = r12.zzjhw     // Catch:{ all -> 0x0a06 }
            if (r7 != 0) goto L_0x0792
            java.lang.Long r7 = r12.zzjhx     // Catch:{ all -> 0x0a06 }
            if (r7 != 0) goto L_0x0792
            java.lang.Boolean r7 = r12.zzjhy     // Catch:{ all -> 0x0a06 }
            if (r7 == 0) goto L_0x079c
        L_0x0792:
            r7 = 0
            com.google.android.gms.internal.zzcit r8 = r12.zza(r7, r7, r7)     // Catch:{ all -> 0x0a06 }
            java.lang.String r7 = r11.name     // Catch:{ all -> 0x0a06 }
            r2.put(r7, r8)     // Catch:{ all -> 0x0a06 }
        L_0x079c:
            r10 = r1
            r15 = r3
            r23 = r4
        L_0x07a0:
            r24 = r6
            goto L_0x0709
        L_0x07a4:
            int r15 = r6.nextInt(r1)     // Catch:{ all -> 0x0a06 }
            if (r15 != 0) goto L_0x07e1
            r37.zzayl()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoc[] r7 = r11.zzjui     // Catch:{ all -> 0x0a06 }
            r15 = r3
            r23 = r4
            long r3 = (long) r1     // Catch:{ all -> 0x0a06 }
            java.lang.Long r1 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoc[] r1 = com.google.android.gms.internal.zzcno.zza((com.google.android.gms.internal.zzcoc[]) r7, (java.lang.String) r14, (java.lang.Object) r1)     // Catch:{ all -> 0x0a06 }
            r11.zzjui = r1     // Catch:{ all -> 0x0a06 }
            int r1 = r10 + 1
            r5[r10] = r11     // Catch:{ all -> 0x0a06 }
            boolean r7 = r8.booleanValue()     // Catch:{ all -> 0x0a06 }
            if (r7 == 0) goto L_0x07d0
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x0a06 }
            r4 = 0
            com.google.android.gms.internal.zzcit r12 = r12.zza(r4, r3, r4)     // Catch:{ all -> 0x0a06 }
        L_0x07d0:
            java.lang.String r3 = r11.name     // Catch:{ all -> 0x0a06 }
            java.lang.Long r4 = r11.zzjuj     // Catch:{ all -> 0x0a06 }
            long r7 = r4.longValue()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcit r4 = r12.zzbc(r7)     // Catch:{ all -> 0x0a06 }
            r2.put(r3, r4)     // Catch:{ all -> 0x0a06 }
            r10 = r1
            goto L_0x07a0
        L_0x07e1:
            r15 = r3
            r23 = r4
            long r3 = r12.zzjhv     // Catch:{ all -> 0x0a06 }
            r24 = r6
            java.lang.Long r6 = r11.zzjuj     // Catch:{ all -> 0x0a06 }
            long r25 = r6.longValue()     // Catch:{ all -> 0x0a06 }
            long r25 = r25 - r3
            long r3 = java.lang.Math.abs(r25)     // Catch:{ all -> 0x0a06 }
            r25 = 86400000(0x5265c00, double:4.2687272E-316)
            int r6 = (r3 > r25 ? 1 : (r3 == r25 ? 0 : -1))
            if (r6 < 0) goto L_0x0844
            r37.zzayl()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoc[] r3 = r11.zzjui     // Catch:{ all -> 0x0a06 }
            java.lang.Long r4 = java.lang.Long.valueOf(r16)     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoc[] r3 = com.google.android.gms.internal.zzcno.zza((com.google.android.gms.internal.zzcoc[]) r3, (java.lang.String) r13, (java.lang.Object) r4)     // Catch:{ all -> 0x0a06 }
            r11.zzjui = r3     // Catch:{ all -> 0x0a06 }
            r37.zzayl()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoc[] r3 = r11.zzjui     // Catch:{ all -> 0x0a06 }
            long r6 = (long) r1     // Catch:{ all -> 0x0a06 }
            java.lang.Long r1 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoc[] r1 = com.google.android.gms.internal.zzcno.zza((com.google.android.gms.internal.zzcoc[]) r3, (java.lang.String) r14, (java.lang.Object) r1)     // Catch:{ all -> 0x0a06 }
            r11.zzjui = r1     // Catch:{ all -> 0x0a06 }
            int r1 = r10 + 1
            r5[r10] = r11     // Catch:{ all -> 0x0a06 }
            boolean r3 = r8.booleanValue()     // Catch:{ all -> 0x0a06 }
            if (r3 == 0) goto L_0x0832
            java.lang.Long r3 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0a06 }
            r4 = 1
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r4)     // Catch:{ all -> 0x0a06 }
            r4 = 0
            com.google.android.gms.internal.zzcit r12 = r12.zza(r4, r3, r6)     // Catch:{ all -> 0x0a06 }
        L_0x0832:
            java.lang.String r3 = r11.name     // Catch:{ all -> 0x0a06 }
            java.lang.Long r4 = r11.zzjuj     // Catch:{ all -> 0x0a06 }
            long r6 = r4.longValue()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcit r4 = r12.zzbc(r6)     // Catch:{ all -> 0x0a06 }
            r2.put(r3, r4)     // Catch:{ all -> 0x0a06 }
            r10 = r1
            goto L_0x0709
        L_0x0844:
            boolean r1 = r8.booleanValue()     // Catch:{ all -> 0x0a06 }
            if (r1 == 0) goto L_0x0709
            java.lang.String r1 = r11.name     // Catch:{ all -> 0x0a06 }
            r3 = 0
            com.google.android.gms.internal.zzcit r4 = r12.zza(r7, r3, r3)     // Catch:{ all -> 0x0a06 }
            r2.put(r1, r4)     // Catch:{ all -> 0x0a06 }
        L_0x0854:
            int r9 = r9 + 1
            r1 = r37
            r3 = r15
            r7 = r21
            r8 = r22
            r4 = r23
            r6 = r24
            goto L_0x065b
        L_0x0863:
            r15 = r3
            r1 = r4
            r3 = 0
            com.google.android.gms.internal.zzcob[] r4 = r1.zzjuq     // Catch:{ all -> 0x0a06 }
            int r4 = r4.length     // Catch:{ all -> 0x0a06 }
            if (r10 >= r4) goto L_0x0873
            java.lang.Object[] r4 = java.util.Arrays.copyOf(r5, r10)     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcob[] r4 = (com.google.android.gms.internal.zzcob[]) r4     // Catch:{ all -> 0x0a06 }
            r1.zzjuq = r4     // Catch:{ all -> 0x0a06 }
        L_0x0873:
            java.util.Set r2 = r2.entrySet()     // Catch:{ all -> 0x0a06 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0a06 }
        L_0x087b:
            boolean r4 = r2.hasNext()     // Catch:{ all -> 0x0a06 }
            if (r4 == 0) goto L_0x0898
            java.lang.Object r4 = r2.next()     // Catch:{ all -> 0x0a06 }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcil r5 = r37.zzayj()     // Catch:{ all -> 0x0a06 }
            java.lang.Object r4 = r4.getValue()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcit r4 = (com.google.android.gms.internal.zzcit) r4     // Catch:{ all -> 0x0a06 }
            r5.zza((com.google.android.gms.internal.zzcit) r4)     // Catch:{ all -> 0x0a06 }
            goto L_0x087b
        L_0x0895:
            r15 = r3
            r1 = r4
            r3 = 0
        L_0x0898:
            r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            java.lang.Long r2 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x0a06 }
            r1.zzjut = r2     // Catch:{ all -> 0x0a06 }
            r4 = -9223372036854775808
            java.lang.Long r2 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x0a06 }
            r1.zzjuu = r2     // Catch:{ all -> 0x0a06 }
            r2 = 0
        L_0x08ac:
            com.google.android.gms.internal.zzcob[] r4 = r1.zzjuq     // Catch:{ all -> 0x0a06 }
            int r4 = r4.length     // Catch:{ all -> 0x0a06 }
            if (r2 >= r4) goto L_0x08e0
            com.google.android.gms.internal.zzcob[] r4 = r1.zzjuq     // Catch:{ all -> 0x0a06 }
            r4 = r4[r2]     // Catch:{ all -> 0x0a06 }
            java.lang.Long r5 = r4.zzjuj     // Catch:{ all -> 0x0a06 }
            long r5 = r5.longValue()     // Catch:{ all -> 0x0a06 }
            java.lang.Long r7 = r1.zzjut     // Catch:{ all -> 0x0a06 }
            long r7 = r7.longValue()     // Catch:{ all -> 0x0a06 }
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 >= 0) goto L_0x08c9
            java.lang.Long r5 = r4.zzjuj     // Catch:{ all -> 0x0a06 }
            r1.zzjut = r5     // Catch:{ all -> 0x0a06 }
        L_0x08c9:
            java.lang.Long r5 = r4.zzjuj     // Catch:{ all -> 0x0a06 }
            long r5 = r5.longValue()     // Catch:{ all -> 0x0a06 }
            java.lang.Long r7 = r1.zzjuu     // Catch:{ all -> 0x0a06 }
            long r7 = r7.longValue()     // Catch:{ all -> 0x0a06 }
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 <= 0) goto L_0x08dd
            java.lang.Long r4 = r4.zzjuj     // Catch:{ all -> 0x0a06 }
            r1.zzjuu = r4     // Catch:{ all -> 0x0a06 }
        L_0x08dd:
            int r2 = r2 + 1
            goto L_0x08ac
        L_0x08e0:
            r2 = r15
            com.google.android.gms.internal.zzcoe r4 = r2.zzjpe     // Catch:{ all -> 0x0a06 }
            java.lang.String r4 = r4.zzcm     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcil r5 = r37.zzayj()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcie r5 = r5.zzjj(r4)     // Catch:{ all -> 0x0a06 }
            if (r5 != 0) goto L_0x0905
            com.google.android.gms.internal.zzcjj r3 = r37.zzayp()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcjl r3 = r3.zzbau()     // Catch:{ all -> 0x0a06 }
            java.lang.String r5 = "Bundling raw events w/o app info. appId"
            com.google.android.gms.internal.zzcoe r6 = r2.zzjpe     // Catch:{ all -> 0x0a06 }
            java.lang.String r6 = r6.zzcm     // Catch:{ all -> 0x0a06 }
            java.lang.Object r6 = com.google.android.gms.internal.zzcjj.zzjs(r6)     // Catch:{ all -> 0x0a06 }
            r3.zzj(r5, r6)     // Catch:{ all -> 0x0a06 }
            goto L_0x095f
        L_0x0905:
            com.google.android.gms.internal.zzcob[] r6 = r1.zzjuq     // Catch:{ all -> 0x0a06 }
            int r6 = r6.length     // Catch:{ all -> 0x0a06 }
            if (r6 <= 0) goto L_0x095f
            long r6 = r5.zzayw()     // Catch:{ all -> 0x0a06 }
            r8 = 0
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 == 0) goto L_0x0919
            java.lang.Long r10 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0a06 }
            goto L_0x091a
        L_0x0919:
            r10 = r3
        L_0x091a:
            r1.zzjuw = r10     // Catch:{ all -> 0x0a06 }
            long r8 = r5.zzayv()     // Catch:{ all -> 0x0a06 }
            r10 = 0
            int r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r12 != 0) goto L_0x0927
            goto L_0x0928
        L_0x0927:
            r6 = r8
        L_0x0928:
            int r8 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r8 == 0) goto L_0x0930
            java.lang.Long r3 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0a06 }
        L_0x0930:
            r1.zzjuv = r3     // Catch:{ all -> 0x0a06 }
            r5.zzazf()     // Catch:{ all -> 0x0a06 }
            long r6 = r5.zzazc()     // Catch:{ all -> 0x0a06 }
            int r3 = (int) r6     // Catch:{ all -> 0x0a06 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0a06 }
            r1.zzjvf = r3     // Catch:{ all -> 0x0a06 }
            java.lang.Long r3 = r1.zzjut     // Catch:{ all -> 0x0a06 }
            long r6 = r3.longValue()     // Catch:{ all -> 0x0a06 }
            r5.zzal(r6)     // Catch:{ all -> 0x0a06 }
            java.lang.Long r3 = r1.zzjuu     // Catch:{ all -> 0x0a06 }
            long r6 = r3.longValue()     // Catch:{ all -> 0x0a06 }
            r5.zzam(r6)     // Catch:{ all -> 0x0a06 }
            java.lang.String r3 = r5.zzazn()     // Catch:{ all -> 0x0a06 }
            r1.zzjgi = r3     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcil r3 = r37.zzayj()     // Catch:{ all -> 0x0a06 }
            r3.zza((com.google.android.gms.internal.zzcie) r5)     // Catch:{ all -> 0x0a06 }
        L_0x095f:
            com.google.android.gms.internal.zzcob[] r3 = r1.zzjuq     // Catch:{ all -> 0x0a06 }
            int r3 = r3.length     // Catch:{ all -> 0x0a06 }
            if (r3 <= 0) goto L_0x09ab
            com.google.android.gms.internal.zzckd r3 = r37.zzaym()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcoe r5 = r2.zzjpe     // Catch:{ all -> 0x0a06 }
            java.lang.String r5 = r5.zzcm     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcny r3 = r3.zzka(r5)     // Catch:{ all -> 0x0a06 }
            if (r3 == 0) goto L_0x097c
            java.lang.Long r5 = r3.zzjtx     // Catch:{ all -> 0x0a06 }
            if (r5 != 0) goto L_0x0977
            goto L_0x097c
        L_0x0977:
            java.lang.Long r3 = r3.zzjtx     // Catch:{ all -> 0x0a06 }
        L_0x0979:
            r1.zzjvm = r3     // Catch:{ all -> 0x0a06 }
            goto L_0x09a2
        L_0x097c:
            com.google.android.gms.internal.zzcoe r3 = r2.zzjpe     // Catch:{ all -> 0x0a06 }
            java.lang.String r3 = r3.zzjfl     // Catch:{ all -> 0x0a06 }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x0a06 }
            if (r3 == 0) goto L_0x098d
            r5 = -1
            java.lang.Long r3 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0a06 }
            goto L_0x0979
        L_0x098d:
            com.google.android.gms.internal.zzcjj r3 = r37.zzayp()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcjl r3 = r3.zzbaw()     // Catch:{ all -> 0x0a06 }
            java.lang.String r5 = "Did not find measurement config or missing version info. appId"
            com.google.android.gms.internal.zzcoe r6 = r2.zzjpe     // Catch:{ all -> 0x0a06 }
            java.lang.String r6 = r6.zzcm     // Catch:{ all -> 0x0a06 }
            java.lang.Object r6 = com.google.android.gms.internal.zzcjj.zzjs(r6)     // Catch:{ all -> 0x0a06 }
            r3.zzj(r5, r6)     // Catch:{ all -> 0x0a06 }
        L_0x09a2:
            com.google.android.gms.internal.zzcil r3 = r37.zzayj()     // Catch:{ all -> 0x0a06 }
            r10 = r20
            r3.zza((com.google.android.gms.internal.zzcoe) r1, (boolean) r10)     // Catch:{ all -> 0x0a06 }
        L_0x09ab:
            com.google.android.gms.internal.zzcil r1 = r37.zzayj()     // Catch:{ all -> 0x0a06 }
            java.util.List<java.lang.Long> r2 = r2.zzjpf     // Catch:{ all -> 0x0a06 }
            r1.zzai(r2)     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcil r1 = r37.zzayj()     // Catch:{ all -> 0x0a06 }
            android.database.sqlite.SQLiteDatabase r2 = r1.getWritableDatabase()     // Catch:{ all -> 0x0a06 }
            java.lang.String r3 = "delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)"
            r5 = 2
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x09cb }
            r6 = 0
            r5[r6] = r4     // Catch:{ SQLiteException -> 0x09cb }
            r6 = 1
            r5[r6] = r4     // Catch:{ SQLiteException -> 0x09cb }
            r2.execSQL(r3, r5)     // Catch:{ SQLiteException -> 0x09cb }
            goto L_0x09de
        L_0x09cb:
            r0 = move-exception
            r2 = r0
            com.google.android.gms.internal.zzcjj r1 = r1.zzayp()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcjl r1 = r1.zzbau()     // Catch:{ all -> 0x0a06 }
            java.lang.String r3 = "Failed to remove unused event metadata. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzcjj.zzjs(r4)     // Catch:{ all -> 0x0a06 }
            r1.zze(r3, r4, r2)     // Catch:{ all -> 0x0a06 }
        L_0x09de:
            com.google.android.gms.internal.zzcil r1 = r37.zzayj()     // Catch:{ all -> 0x0a06 }
            r1.setTransactionSuccessful()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcil r1 = r37.zzayj()
            r1.endTransaction()
            r1 = 1
            return r1
        L_0x09ee:
            com.google.android.gms.internal.zzcil r1 = r37.zzayj()     // Catch:{ all -> 0x0a06 }
            r1.setTransactionSuccessful()     // Catch:{ all -> 0x0a06 }
            com.google.android.gms.internal.zzcil r1 = r37.zzayj()
            r1.endTransaction()
            r1 = 0
            return r1
        L_0x09fe:
            r0 = move-exception
            r1 = r0
        L_0x0a00:
            if (r4 == 0) goto L_0x0a05
            r4.close()     // Catch:{ all -> 0x0a06 }
        L_0x0a05:
            throw r1     // Catch:{ all -> 0x0a06 }
        L_0x0a06:
            r0 = move-exception
            r1 = r0
            com.google.android.gms.internal.zzcil r2 = r37.zzayj()
            r2.endTransaction()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzckj.zzg(java.lang.String, long):boolean");
    }

    private final zzcif zzke(String str) {
        zzcjl zzbaz;
        String str2;
        Object obj;
        String str3 = str;
        zzcie zzjj = zzayj().zzjj(str3);
        if (zzjj == null || TextUtils.isEmpty(zzjj.zzwo())) {
            zzbaz = zzayp().zzbaz();
            str2 = "No app data available; dropping";
            obj = str3;
        } else {
            Boolean zzc = zzc(zzjj);
            if (zzc == null || zzc.booleanValue()) {
                return new zzcif(str, zzjj.getGmpAppId(), zzjj.zzwo(), zzjj.zzayx(), zzjj.zzayy(), zzjj.zzayz(), zzjj.zzaza(), (String) null, zzjj.zzazb(), false, zzjj.zzayu(), zzjj.zzazo(), 0, 0, zzjj.zzazp());
            }
            zzbaz = zzayp().zzbau();
            str2 = "App version does not match; dropping. appId";
            obj = zzcjj.zzjs(str);
        }
        zzbaz.zzj(str2, obj);
        return null;
    }

    public final Context getContext() {
        return this.zzaiq;
    }

    public final boolean isEnabled() {
        zzayo().zzwj();
        zzyk();
        boolean z = false;
        if (this.zzjns.zzazr()) {
            return false;
        }
        Boolean zzjf = this.zzjns.zzjf("firebase_analytics_collection_enabled");
        if (zzjf != null) {
            z = zzjf.booleanValue();
        } else if (!zzbz.zzakr()) {
            z = true;
        }
        return zzayq().zzbs(z);
    }

    /* access modifiers changed from: protected */
    public final void start() {
        zzayo().zzwj();
        zzayj().zzazy();
        if (zzayq().zzjln.get() == 0) {
            zzayq().zzjln.set(this.zzdir.currentTimeMillis());
        }
        if (Long.valueOf(zzayq().zzjls.get()).longValue() == 0) {
            zzayp().zzbba().zzj("Persisting first open", Long.valueOf(this.zzjgk));
            zzayq().zzjls.set(this.zzjgk);
        }
        if (zzbbn()) {
            if (!TextUtils.isEmpty(zzaye().getGmpAppId())) {
                String zzbbe = zzayq().zzbbe();
                if (zzbbe == null) {
                    zzayq().zzjw(zzaye().getGmpAppId());
                } else if (!zzbbe.equals(zzaye().getGmpAppId())) {
                    zzayp().zzbay().log("Rechecking which service to use due to a GMP App Id change");
                    zzayq().zzbbh();
                    this.zzjoi.disconnect();
                    this.zzjoi.zzzh();
                    zzayq().zzjw(zzaye().getGmpAppId());
                    zzayq().zzjls.set(this.zzjgk);
                    zzayq().zzjlt.zzjy((String) null);
                }
            }
            zzayd().zzjx(zzayq().zzjlt.zzbbj());
            if (!TextUtils.isEmpty(zzaye().getGmpAppId())) {
                zzclk zzayd = zzayd();
                zzayd.zzwj();
                zzayd.zzyk();
                if (zzayd.zzjev.zzbbn()) {
                    zzayd.zzayg().zzbcj();
                    String zzbbi = zzayd.zzayq().zzbbi();
                    if (!TextUtils.isEmpty(zzbbi)) {
                        zzayd.zzayf().zzyk();
                        if (!zzbbi.equals(Build.VERSION.RELEASE)) {
                            Bundle bundle = new Bundle();
                            bundle.putString("_po", zzbbi);
                            zzayd.zzd("auto", "_ou", bundle);
                        }
                    }
                }
                zzayg().zza((AtomicReference<String>) new AtomicReference());
            }
        } else if (isEnabled()) {
            if (!zzayl().zzeh("android.permission.INTERNET")) {
                zzayp().zzbau().log("App is missing INTERNET permission");
            }
            if (!zzayl().zzeh("android.permission.ACCESS_NETWORK_STATE")) {
                zzayp().zzbau().log("App is missing ACCESS_NETWORK_STATE permission");
            }
            if (!zzbih.zzdd(this.zzaiq).zzaoe()) {
                if (!zzcka.zzbj(this.zzaiq)) {
                    zzayp().zzbau().log("AppMeasurementReceiver not registered/enabled");
                }
                if (!zzcmy.zzg(this.zzaiq, false)) {
                    zzayp().zzbau().log("AppMeasurementService not registered/enabled");
                }
            }
            zzayp().zzbau().log("Uploading is not possible. App measurement disabled");
        }
        zzbca();
    }

    /* access modifiers changed from: protected */
    public final void zza(int i, Throwable th, byte[] bArr) {
        zzcil zzayj;
        zzayo().zzwj();
        zzyk();
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                this.zzjpa = false;
                zzbce();
                throw th2;
            }
        }
        List<Long> list = this.zzjot;
        this.zzjot = null;
        boolean z = true;
        if ((i == 200 || i == 204) && th == null) {
            try {
                zzayq().zzjln.set(this.zzdir.currentTimeMillis());
                zzayq().zzjlo.set(0);
                zzbca();
                zzayp().zzbba().zze("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                zzayj().beginTransaction();
                try {
                    for (Long longValue : list) {
                        zzayj = zzayj();
                        long longValue2 = longValue.longValue();
                        zzayj.zzwj();
                        zzayj.zzyk();
                        if (zzayj.getWritableDatabase().delete("queue", "rowid=?", new String[]{String.valueOf(longValue2)}) != 1) {
                            throw new SQLiteException("Deleted fewer rows from queue than expected");
                        }
                    }
                    zzayj().setTransactionSuccessful();
                    zzayj().endTransaction();
                    if (!zzbbs().zzaax() || !zzbbz()) {
                        this.zzjox = -1;
                        zzbca();
                    } else {
                        zzbby();
                    }
                    this.zzjoy = 0;
                } catch (SQLiteException e) {
                    zzayj().zzayp().zzbau().zzj("Failed to delete a bundle in a queue table", e);
                    throw e;
                } catch (Throwable th3) {
                    zzayj().endTransaction();
                    throw th3;
                }
            } catch (SQLiteException e2) {
                zzayp().zzbau().zzj("Database error while trying to delete uploaded bundles", e2);
                this.zzjoy = this.zzdir.elapsedRealtime();
                zzayp().zzbba().zzj("Disable upload, time", Long.valueOf(this.zzjoy));
            }
        } else {
            zzayp().zzbba().zze("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
            zzayq().zzjlo.set(this.zzdir.currentTimeMillis());
            if (i != 503) {
                if (i != 429) {
                    z = false;
                }
            }
            if (z) {
                zzayq().zzjlp.set(this.zzdir.currentTimeMillis());
            }
            zzbca();
        }
        this.zzjpa = false;
        zzbce();
    }

    public final byte[] zza(zzcix zzcix, String str) {
        zzcnn zzcnn;
        List<zzcnn> list;
        String str2;
        long j;
        zzcjl zzbaw;
        String str3;
        Object zzjs;
        zzcix zzcix2 = zzcix;
        String str4 = str;
        zzyk();
        zzayo().zzwj();
        zzaxz();
        zzbq.checkNotNull(zzcix);
        zzbq.zzgv(str);
        zzcod zzcod = new zzcod();
        zzayj().beginTransaction();
        try {
            zzcie zzjj = zzayj().zzjj(str4);
            if (zzjj == null) {
                zzayp().zzbaz().zzj("Log and bundle not available. package_name", str4);
            } else if (!zzjj.zzazb()) {
                zzayp().zzbaz().zzj("Log and bundle disabled. package_name", str4);
            } else {
                if (("_iap".equals(zzcix2.name) || FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(zzcix2.name)) && !zza(str4, zzcix2)) {
                    zzayp().zzbaw().zzj("Failed to handle purchase event at single event bundle creation. appId", zzcjj.zzjs(str));
                }
                boolean zzjh = this.zzjns.zzjh(str4);
                Long l = 0L;
                if (zzjh && "_e".equals(zzcix2.name)) {
                    if (zzcix2.zzjhr != null) {
                        if (zzcix2.zzjhr.size() != 0) {
                            if (zzcix2.zzjhr.getLong("_et") == null) {
                                zzbaw = zzayp().zzbaw();
                                str3 = "The engagement event does not include duration. appId";
                                zzjs = zzcjj.zzjs(str);
                                zzbaw.zzj(str3, zzjs);
                            } else {
                                l = zzcix2.zzjhr.getLong("_et");
                            }
                        }
                    }
                    zzbaw = zzayp().zzbaw();
                    str3 = "The engagement event does not contain any parameters. appId";
                    zzjs = zzcjj.zzjs(str);
                    zzbaw.zzj(str3, zzjs);
                }
                zzcoe zzcoe = new zzcoe();
                zzcod.zzjun = new zzcoe[]{zzcoe};
                zzcoe.zzjup = 1;
                zzcoe.zzjux = "android";
                zzcoe.zzcm = zzjj.getAppId();
                zzcoe.zzjfs = zzjj.zzayy();
                zzcoe.zzina = zzjj.zzwo();
                long zzayx = zzjj.zzayx();
                zzcoe.zzjvi = zzayx == -2147483648L ? null : Integer.valueOf((int) zzayx);
                zzcoe.zzjva = Long.valueOf(zzjj.zzayz());
                zzcoe.zzjfl = zzjj.getGmpAppId();
                zzcoe.zzjve = Long.valueOf(zzjj.zzaza());
                if (isEnabled() && zzcik.zzazv() && this.zzjns.zzjg(zzcoe.zzcm)) {
                    zzcoe.zzjvn = null;
                }
                Pair<String, Boolean> zzju = zzayq().zzju(zzjj.getAppId());
                if (zzjj.zzazp() && zzju != null && !TextUtils.isEmpty((CharSequence) zzju.first)) {
                    zzcoe.zzjvc = (String) zzju.first;
                    zzcoe.zzjvd = (Boolean) zzju.second;
                }
                zzayf().zzyk();
                zzcoe.zzjuy = Build.MODEL;
                zzayf().zzyk();
                zzcoe.zzda = Build.VERSION.RELEASE;
                zzcoe.zzjuz = Integer.valueOf((int) zzayf().zzbal());
                zzcoe.zzjho = zzayf().zzbam();
                zzcoe.zzjfk = zzjj.getAppInstanceId();
                zzcoe.zzjfn = zzjj.zzayu();
                List<zzcnn> zzji = zzayj().zzji(zzjj.getAppId());
                zzcoe.zzjur = new zzcog[zzji.size()];
                if (zzjh) {
                    zzcnn = zzayj().zzag(zzcoe.zzcm, "_lte");
                    if (zzcnn != null) {
                        if (zzcnn.value != null) {
                            if (l.longValue() > 0) {
                                zzcnn = new zzcnn(zzcoe.zzcm, "auto", "_lte", this.zzdir.currentTimeMillis(), Long.valueOf(((Long) zzcnn.value).longValue() + l.longValue()));
                            }
                            str2 = "_lte";
                            list = zzji;
                        }
                    }
                    str2 = "_lte";
                    list = zzji;
                    zzcnn = new zzcnn(zzcoe.zzcm, "auto", "_lte", this.zzdir.currentTimeMillis(), l);
                } else {
                    str2 = "_lte";
                    list = zzji;
                    zzcnn = null;
                }
                int i = 0;
                zzcog zzcog = null;
                while (i < list.size()) {
                    zzcog zzcog2 = new zzcog();
                    zzcoe.zzjur[i] = zzcog2;
                    zzcog2.name = list.get(i).name;
                    zzcod zzcod2 = zzcod;
                    zzcie zzcie = zzjj;
                    zzcog2.zzjvr = Long.valueOf(list.get(i).zzjsi);
                    zzayl().zza(zzcog2, list.get(i).value);
                    if (zzjh && str2.equals(zzcog2.name)) {
                        zzcog2.zzjum = (Long) zzcnn.value;
                        zzcog2.zzjvr = Long.valueOf(this.zzdir.currentTimeMillis());
                        zzcog = zzcog2;
                    }
                    i++;
                    zzcod = zzcod2;
                    zzjj = zzcie;
                }
                zzcod zzcod3 = zzcod;
                zzcie zzcie2 = zzjj;
                if (zzjh && zzcog == null) {
                    zzcog zzcog3 = new zzcog();
                    zzcog3.name = str2;
                    zzcog3.zzjvr = Long.valueOf(this.zzdir.currentTimeMillis());
                    zzcog3.zzjum = (Long) zzcnn.value;
                    zzcoe.zzjur = (zzcog[]) Arrays.copyOf(zzcoe.zzjur, zzcoe.zzjur.length + 1);
                    zzcoe.zzjur[zzcoe.zzjur.length - 1] = zzcog3;
                }
                if (l.longValue() > 0) {
                    zzayj().zza(zzcnn);
                }
                Bundle zzbao = zzcix2.zzjhr.zzbao();
                if ("_iap".equals(zzcix2.name)) {
                    zzbao.putLong("_c", 1);
                    zzayp().zzbaz().log("Marking in-app purchase as real-time");
                    zzbao.putLong("_r", 1);
                }
                zzbao.putString("_o", zzcix2.zzjgm);
                if (zzayl().zzkq(zzcoe.zzcm)) {
                    zzayl().zza(zzbao, "_dbg", (Object) 1L);
                    zzayl().zza(zzbao, "_r", (Object) 1L);
                }
                zzcit zzae = zzayj().zzae(str4, zzcix2.name);
                if (zzae == null) {
                    zzayj().zza(new zzcit(str, zzcix2.name, 1, 0, zzcix2.zzjib, 0, (Long) null, (Long) null, (Boolean) null));
                    j = 0;
                } else {
                    long j2 = zzae.zzjhu;
                    zzayj().zza(zzae.zzbb(zzcix2.zzjib).zzban());
                    j = j2;
                }
                zzcoe zzcoe2 = zzcoe;
                zzcod zzcod4 = zzcod3;
                zzcis zzcis = new zzcis(this, zzcix2.zzjgm, str, zzcix2.name, zzcix2.zzjib, j, zzbao);
                zzcob zzcob = new zzcob();
                zzcoe2.zzjuq = new zzcob[]{zzcob};
                zzcob.zzjuj = Long.valueOf(zzcis.timestamp);
                zzcob.name = zzcis.name;
                zzcob.zzjuk = Long.valueOf(zzcis.zzjhq);
                zzcob.zzjui = new zzcoc[zzcis.zzjhr.size()];
                Iterator<String> it = zzcis.zzjhr.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    String next = it.next();
                    zzcoc zzcoc = new zzcoc();
                    zzcob.zzjui[i2] = zzcoc;
                    zzcoc.name = next;
                    zzayl().zza(zzcoc, zzcis.zzjhr.get(next));
                    i2++;
                }
                zzcoe2.zzjvh = zza(zzcie2.getAppId(), zzcoe2.zzjur, zzcoe2.zzjuq);
                zzcoe2.zzjut = zzcob.zzjuj;
                zzcoe2.zzjuu = zzcob.zzjuj;
                long zzayw = zzcie2.zzayw();
                zzcoe2.zzjuw = zzayw != 0 ? Long.valueOf(zzayw) : null;
                long zzayv = zzcie2.zzayv();
                if (zzayv != 0) {
                    zzayw = zzayv;
                }
                zzcoe2.zzjuv = zzayw != 0 ? Long.valueOf(zzayw) : null;
                zzcie2.zzazf();
                zzcoe2.zzjvf = Integer.valueOf((int) zzcie2.zzazc());
                zzcoe2.zzjvb = 12211L;
                zzcoe2.zzjus = Long.valueOf(this.zzdir.currentTimeMillis());
                zzcoe2.zzjvg = Boolean.TRUE;
                zzcie zzcie3 = zzcie2;
                zzcie3.zzal(zzcoe2.zzjut.longValue());
                zzcie3.zzam(zzcoe2.zzjuu.longValue());
                zzayj().zza(zzcie3);
                zzayj().setTransactionSuccessful();
                zzayj().endTransaction();
                try {
                    int zzhs = zzcod4.zzhs();
                    byte[] bArr = new byte[zzhs];
                    zzflk zzp = zzflk.zzp(bArr, 0, zzhs);
                    zzcod4.zza(zzp);
                    zzp.zzcyx();
                    return zzayl().zzr(bArr);
                } catch (IOException e) {
                    zzayp().zzbau().zze("Data loss. Failed to bundle and serialize. appId", zzcjj.zzjs(str), e);
                    return null;
                }
            }
            return new byte[0];
        } finally {
            zzayj().endTransaction();
        }
    }

    public final zzcia zzayb() {
        zza((zzclh) this.zzjof);
        return this.zzjof;
    }

    public final zzcih zzayc() {
        zza((zzcli) this.zzjon);
        return this.zzjon;
    }

    public final zzclk zzayd() {
        zza((zzcli) this.zzjoe);
        return this.zzjoe;
    }

    public final zzcje zzaye() {
        zza((zzcli) this.zzjok);
        return this.zzjok;
    }

    public final zzcir zzayf() {
        zza((zzcli) this.zzjoj);
        return this.zzjoj;
    }

    public final zzcme zzayg() {
        zza((zzcli) this.zzjoi);
        return this.zzjoi;
    }

    public final zzcma zzayh() {
        zza((zzcli) this.zzjod);
        return this.zzjod;
    }

    public final zzcjf zzayi() {
        zza((zzcli) this.zzjoh);
        return this.zzjoh;
    }

    public final zzcil zzayj() {
        zza((zzcli) this.zzjog);
        return this.zzjog;
    }

    public final zzcjh zzayk() {
        zza((zzclh) this.zzjob);
        return this.zzjob;
    }

    public final zzcno zzayl() {
        zza((zzclh) this.zzjoa);
        return this.zzjoa;
    }

    public final zzckd zzaym() {
        zza((zzcli) this.zzjnx);
        return this.zzjnx;
    }

    public final zzcnd zzayn() {
        zza((zzcli) this.zzjnw);
        return this.zzjnw;
    }

    public final zzcke zzayo() {
        zza((zzcli) this.zzjnv);
        return this.zzjnv;
    }

    public final zzcjj zzayp() {
        zza((zzcli) this.zzjnu);
        return this.zzjnu;
    }

    public final zzcju zzayq() {
        zza((zzclh) this.zzjnt);
        return this.zzjnt;
    }

    public final zzcik zzayr() {
        return this.zzjns;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzcii zzcii, zzcif zzcif) {
        zzcjl zzbau;
        String str;
        Object zzjs;
        String zzjr;
        Object value;
        zzcjl zzbau2;
        String str2;
        Object zzjs2;
        String zzjr2;
        Object obj;
        zzbq.checkNotNull(zzcii);
        zzbq.zzgv(zzcii.packageName);
        zzbq.checkNotNull(zzcii.zzjgm);
        zzbq.checkNotNull(zzcii.zzjgn);
        zzbq.zzgv(zzcii.zzjgn.name);
        zzayo().zzwj();
        zzyk();
        if (!TextUtils.isEmpty(zzcif.zzjfl)) {
            if (!zzcif.zzjfv) {
                zzg(zzcif);
                return;
            }
            zzcii zzcii2 = new zzcii(zzcii);
            boolean z = false;
            zzcii2.zzjgp = false;
            zzayj().beginTransaction();
            try {
                zzcii zzah = zzayj().zzah(zzcii2.packageName, zzcii2.zzjgn.name);
                if (zzah != null && !zzah.zzjgm.equals(zzcii2.zzjgm)) {
                    zzayp().zzbaw().zzd("Updating a conditional user property with different origin. name, origin, origin (from DB)", zzayk().zzjr(zzcii2.zzjgn.name), zzcii2.zzjgm, zzah.zzjgm);
                }
                if (zzah != null && zzah.zzjgp) {
                    zzcii2.zzjgm = zzah.zzjgm;
                    zzcii2.zzjgo = zzah.zzjgo;
                    zzcii2.zzjgs = zzah.zzjgs;
                    zzcii2.zzjgq = zzah.zzjgq;
                    zzcii2.zzjgt = zzah.zzjgt;
                    zzcii2.zzjgp = zzah.zzjgp;
                    zzcii2.zzjgn = new zzcnl(zzcii2.zzjgn.name, zzah.zzjgn.zzjsi, zzcii2.zzjgn.getValue(), zzah.zzjgn.zzjgm);
                } else if (TextUtils.isEmpty(zzcii2.zzjgq)) {
                    zzcii2.zzjgn = new zzcnl(zzcii2.zzjgn.name, zzcii2.zzjgo, zzcii2.zzjgn.getValue(), zzcii2.zzjgn.zzjgm);
                    zzcii2.zzjgp = true;
                    z = true;
                }
                if (zzcii2.zzjgp) {
                    zzcnl zzcnl = zzcii2.zzjgn;
                    zzcnn zzcnn = new zzcnn(zzcii2.packageName, zzcii2.zzjgm, zzcnl.name, zzcnl.zzjsi, zzcnl.getValue());
                    if (zzayj().zza(zzcnn)) {
                        zzbau2 = zzayp().zzbaz();
                        str2 = "User property updated immediately";
                        zzjs2 = zzcii2.packageName;
                        zzjr2 = zzayk().zzjr(zzcnn.name);
                        obj = zzcnn.value;
                    } else {
                        zzbau2 = zzayp().zzbau();
                        str2 = "(2)Too many active user properties, ignoring";
                        zzjs2 = zzcjj.zzjs(zzcii2.packageName);
                        zzjr2 = zzayk().zzjr(zzcnn.name);
                        obj = zzcnn.value;
                    }
                    zzbau2.zzd(str2, zzjs2, zzjr2, obj);
                    if (z && zzcii2.zzjgt != null) {
                        zzc(new zzcix(zzcii2.zzjgt, zzcii2.zzjgo), zzcif);
                    }
                }
                if (zzayj().zza(zzcii2)) {
                    zzbau = zzayp().zzbaz();
                    str = "Conditional property added";
                    zzjs = zzcii2.packageName;
                    zzjr = zzayk().zzjr(zzcii2.zzjgn.name);
                    value = zzcii2.zzjgn.getValue();
                } else {
                    zzbau = zzayp().zzbau();
                    str = "Too many conditional properties, ignoring";
                    zzjs = zzcjj.zzjs(zzcii2.packageName);
                    zzjr = zzayk().zzjr(zzcii2.zzjgn.name);
                    value = zzcii2.zzjgn.getValue();
                }
                zzbau.zzd(str, zzjs, zzjr, value);
                zzayj().setTransactionSuccessful();
            } finally {
                zzayj().endTransaction();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzcix zzcix, zzcif zzcif) {
        List<zzcii> list;
        List<zzcii> list2;
        List<zzcii> list3;
        zzcjl zzbau;
        String str;
        Object zzjs;
        String zzjr;
        Object obj;
        zzcix zzcix2 = zzcix;
        zzcif zzcif2 = zzcif;
        zzbq.checkNotNull(zzcif);
        zzbq.zzgv(zzcif2.packageName);
        zzayo().zzwj();
        zzyk();
        String str2 = zzcif2.packageName;
        long j = zzcix2.zzjib;
        zzayl();
        if (zzcno.zzd(zzcix, zzcif)) {
            if (!zzcif2.zzjfv) {
                zzg(zzcif2);
                return;
            }
            zzayj().beginTransaction();
            try {
                zzcil zzayj = zzayj();
                zzbq.zzgv(str2);
                zzayj.zzwj();
                zzayj.zzyk();
                int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
                if (i < 0) {
                    zzayj.zzayp().zzbaw().zze("Invalid time querying timed out conditional properties", zzcjj.zzjs(str2), Long.valueOf(j));
                    list = Collections.emptyList();
                } else {
                    list = zzayj.zzd("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str2, String.valueOf(j)});
                }
                for (zzcii next : list) {
                    if (next != null) {
                        zzayp().zzbaz().zzd("User property timed out", next.packageName, zzayk().zzjr(next.zzjgn.name), next.zzjgn.getValue());
                        if (next.zzjgr != null) {
                            zzc(new zzcix(next.zzjgr, j), zzcif2);
                        }
                        zzayj().zzai(str2, next.zzjgn.name);
                    }
                }
                zzcil zzayj2 = zzayj();
                zzbq.zzgv(str2);
                zzayj2.zzwj();
                zzayj2.zzyk();
                if (i < 0) {
                    zzayj2.zzayp().zzbaw().zze("Invalid time querying expired conditional properties", zzcjj.zzjs(str2), Long.valueOf(j));
                    list2 = Collections.emptyList();
                } else {
                    list2 = zzayj2.zzd("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str2, String.valueOf(j)});
                }
                ArrayList arrayList = new ArrayList(list2.size());
                for (zzcii next2 : list2) {
                    if (next2 != null) {
                        zzayp().zzbaz().zzd("User property expired", next2.packageName, zzayk().zzjr(next2.zzjgn.name), next2.zzjgn.getValue());
                        zzayj().zzaf(str2, next2.zzjgn.name);
                        if (next2.zzjgv != null) {
                            arrayList.add(next2.zzjgv);
                        }
                        zzayj().zzai(str2, next2.zzjgn.name);
                    }
                }
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                int i2 = 0;
                while (i2 < size) {
                    Object obj2 = arrayList2.get(i2);
                    i2++;
                    zzc(new zzcix((zzcix) obj2, j), zzcif2);
                }
                zzcil zzayj3 = zzayj();
                String str3 = zzcix2.name;
                zzbq.zzgv(str2);
                zzbq.zzgv(str3);
                zzayj3.zzwj();
                zzayj3.zzyk();
                if (i < 0) {
                    zzayj3.zzayp().zzbaw().zzd("Invalid time querying triggered conditional properties", zzcjj.zzjs(str2), zzayj3.zzayk().zzjp(str3), Long.valueOf(j));
                    list3 = Collections.emptyList();
                } else {
                    list3 = zzayj3.zzd("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str2, str3, String.valueOf(j)});
                }
                ArrayList arrayList3 = new ArrayList(list3.size());
                for (zzcii next3 : list3) {
                    if (next3 != null) {
                        zzcnl zzcnl = next3.zzjgn;
                        zzcnn zzcnn = new zzcnn(next3.packageName, next3.zzjgm, zzcnl.name, j, zzcnl.getValue());
                        if (zzayj().zza(zzcnn)) {
                            zzbau = zzayp().zzbaz();
                            str = "User property triggered";
                            zzjs = next3.packageName;
                            zzjr = zzayk().zzjr(zzcnn.name);
                            obj = zzcnn.value;
                        } else {
                            zzbau = zzayp().zzbau();
                            str = "Too many active user properties, ignoring";
                            zzjs = zzcjj.zzjs(next3.packageName);
                            zzjr = zzayk().zzjr(zzcnn.name);
                            obj = zzcnn.value;
                        }
                        zzbau.zzd(str, zzjs, zzjr, obj);
                        if (next3.zzjgt != null) {
                            arrayList3.add(next3.zzjgt);
                        }
                        next3.zzjgn = new zzcnl(zzcnn);
                        next3.zzjgp = true;
                        zzayj().zza(next3);
                    }
                }
                zzc(zzcix, zzcif);
                ArrayList arrayList4 = arrayList3;
                int size2 = arrayList4.size();
                int i3 = 0;
                while (i3 < size2) {
                    Object obj3 = arrayList4.get(i3);
                    i3++;
                    zzc(new zzcix((zzcix) obj3, j), zzcif2);
                }
                zzayj().setTransactionSuccessful();
            } finally {
                zzayj().endTransaction();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzcix zzcix, String str) {
        zzcix zzcix2 = zzcix;
        String str2 = str;
        zzcie zzjj = zzayj().zzjj(str2);
        if (zzjj == null || TextUtils.isEmpty(zzjj.zzwo())) {
            zzayp().zzbaz().zzj("No app data available; dropping event", str2);
            return;
        }
        Boolean zzc = zzc(zzjj);
        if (zzc == null) {
            if (!"_ui".equals(zzcix2.name)) {
                zzayp().zzbaw().zzj("Could not find package. appId", zzcjj.zzjs(str));
            }
        } else if (!zzc.booleanValue()) {
            zzayp().zzbau().zzj("App version does not match; dropping event. appId", zzcjj.zzjs(str));
            return;
        }
        zzcif zzcif2 = new zzcif(str, zzjj.getGmpAppId(), zzjj.zzwo(), zzjj.zzayx(), zzjj.zzayy(), zzjj.zzayz(), zzjj.zzaza(), (String) null, zzjj.zzazb(), false, zzjj.zzayu(), zzjj.zzazo(), 0, 0, zzjj.zzazp());
        zzb(zzcix2, zzcif2);
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzcli zzcli) {
        this.zzjov++;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzcnl zzcnl, zzcif zzcif) {
        zzayo().zzwj();
        zzyk();
        if (!TextUtils.isEmpty(zzcif.zzjfl)) {
            if (!zzcif.zzjfv) {
                zzg(zzcif);
                return;
            }
            int zzkk = zzayl().zzkk(zzcnl.name);
            if (zzkk != 0) {
                zzayl();
                zzayl().zza(zzcif.packageName, zzkk, "_ev", zzcno.zza(zzcnl.name, 24, true), zzcnl.name != null ? zzcnl.name.length() : 0);
                return;
            }
            int zzl = zzayl().zzl(zzcnl.name, zzcnl.getValue());
            if (zzl != 0) {
                zzayl();
                String zza2 = zzcno.zza(zzcnl.name, 24, true);
                Object value = zzcnl.getValue();
                zzayl().zza(zzcif.packageName, zzl, "_ev", zza2, (value == null || (!(value instanceof String) && !(value instanceof CharSequence))) ? 0 : String.valueOf(value).length());
                return;
            }
            Object zzm = zzayl().zzm(zzcnl.name, zzcnl.getValue());
            if (zzm != null) {
                zzcnn zzcnn = new zzcnn(zzcif.packageName, zzcnl.zzjgm, zzcnl.name, zzcnl.zzjsi, zzm);
                zzayp().zzbaz().zze("Setting user property", zzayk().zzjr(zzcnn.name), zzm);
                zzayj().beginTransaction();
                try {
                    zzg(zzcif);
                    boolean zza3 = zzayj().zza(zzcnn);
                    zzayj().setTransactionSuccessful();
                    if (zza3) {
                        zzayp().zzbaz().zze("User property set", zzayk().zzjr(zzcnn.name), zzcnn.value);
                    } else {
                        zzayp().zzbau().zze("Too many unique user properties are set. Ignoring user property", zzayk().zzjr(zzcnn.name), zzcnn.value);
                        zzayl().zza(zzcif.packageName, 9, (String) null, (String) null, 0);
                    }
                } finally {
                    zzayj().endTransaction();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x011c A[Catch:{ all -> 0x015f, all -> 0x0168 }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x012a A[Catch:{ all -> 0x015f, all -> 0x0168 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(java.lang.String r7, int r8, java.lang.Throwable r9, byte[] r10, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r11) {
        /*
            r6 = this;
            com.google.android.gms.internal.zzcke r0 = r6.zzayo()
            r0.zzwj()
            r6.zzyk()
            com.google.android.gms.common.internal.zzbq.zzgv(r7)
            r0 = 0
            if (r10 != 0) goto L_0x0012
            byte[] r10 = new byte[r0]     // Catch:{ all -> 0x0168 }
        L_0x0012:
            com.google.android.gms.internal.zzcjj r1 = r6.zzayp()     // Catch:{ all -> 0x0168 }
            com.google.android.gms.internal.zzcjl r1 = r1.zzbba()     // Catch:{ all -> 0x0168 }
            java.lang.String r2 = "onConfigFetched. Response size"
            int r3 = r10.length     // Catch:{ all -> 0x0168 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0168 }
            r1.zzj(r2, r3)     // Catch:{ all -> 0x0168 }
            com.google.android.gms.internal.zzcil r1 = r6.zzayj()     // Catch:{ all -> 0x0168 }
            r1.beginTransaction()     // Catch:{ all -> 0x0168 }
            com.google.android.gms.internal.zzcil r1 = r6.zzayj()     // Catch:{ all -> 0x015f }
            com.google.android.gms.internal.zzcie r1 = r1.zzjj(r7)     // Catch:{ all -> 0x015f }
            r2 = 200(0xc8, float:2.8E-43)
            r3 = 304(0x130, float:4.26E-43)
            r4 = 1
            if (r8 == r2) goto L_0x0040
            r2 = 204(0xcc, float:2.86E-43)
            if (r8 == r2) goto L_0x0040
            if (r8 != r3) goto L_0x0044
        L_0x0040:
            if (r9 != 0) goto L_0x0044
            r2 = 1
            goto L_0x0045
        L_0x0044:
            r2 = 0
        L_0x0045:
            if (r1 != 0) goto L_0x005a
            com.google.android.gms.internal.zzcjj r8 = r6.zzayp()     // Catch:{ all -> 0x015f }
            com.google.android.gms.internal.zzcjl r8 = r8.zzbaw()     // Catch:{ all -> 0x015f }
            java.lang.String r9 = "App does not exist in onConfigFetched. appId"
            java.lang.Object r7 = com.google.android.gms.internal.zzcjj.zzjs(r7)     // Catch:{ all -> 0x015f }
            r8.zzj(r9, r7)     // Catch:{ all -> 0x015f }
            goto L_0x0153
        L_0x005a:
            r5 = 404(0x194, float:5.66E-43)
            if (r2 != 0) goto L_0x00b8
            if (r8 != r5) goto L_0x0061
            goto L_0x00b8
        L_0x0061:
            com.google.android.gms.common.util.zze r10 = r6.zzdir     // Catch:{ all -> 0x015f }
            long r10 = r10.currentTimeMillis()     // Catch:{ all -> 0x015f }
            r1.zzas(r10)     // Catch:{ all -> 0x015f }
            com.google.android.gms.internal.zzcil r10 = r6.zzayj()     // Catch:{ all -> 0x015f }
            r10.zza((com.google.android.gms.internal.zzcie) r1)     // Catch:{ all -> 0x015f }
            com.google.android.gms.internal.zzcjj r10 = r6.zzayp()     // Catch:{ all -> 0x015f }
            com.google.android.gms.internal.zzcjl r10 = r10.zzbba()     // Catch:{ all -> 0x015f }
            java.lang.String r11 = "Fetching config failed. code, error"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x015f }
            r10.zze(r11, r1, r9)     // Catch:{ all -> 0x015f }
            com.google.android.gms.internal.zzckd r9 = r6.zzaym()     // Catch:{ all -> 0x015f }
            r9.zzkc(r7)     // Catch:{ all -> 0x015f }
            com.google.android.gms.internal.zzcju r7 = r6.zzayq()     // Catch:{ all -> 0x015f }
            com.google.android.gms.internal.zzcjx r7 = r7.zzjlo     // Catch:{ all -> 0x015f }
            com.google.android.gms.common.util.zze r9 = r6.zzdir     // Catch:{ all -> 0x015f }
            long r9 = r9.currentTimeMillis()     // Catch:{ all -> 0x015f }
            r7.set(r9)     // Catch:{ all -> 0x015f }
            r7 = 503(0x1f7, float:7.05E-43)
            if (r8 == r7) goto L_0x00a2
            r7 = 429(0x1ad, float:6.01E-43)
            if (r8 != r7) goto L_0x00a1
            goto L_0x00a2
        L_0x00a1:
            r4 = 0
        L_0x00a2:
            if (r4 == 0) goto L_0x00b3
            com.google.android.gms.internal.zzcju r7 = r6.zzayq()     // Catch:{ all -> 0x015f }
            com.google.android.gms.internal.zzcjx r7 = r7.zzjlp     // Catch:{ all -> 0x015f }
            com.google.android.gms.common.util.zze r8 = r6.zzdir     // Catch:{ all -> 0x015f }
            long r8 = r8.currentTimeMillis()     // Catch:{ all -> 0x015f }
            r7.set(r8)     // Catch:{ all -> 0x015f }
        L_0x00b3:
            r6.zzbca()     // Catch:{ all -> 0x015f }
            goto L_0x0153
        L_0x00b8:
            r9 = 0
            if (r11 == 0) goto L_0x00c4
            java.lang.String r2 = "Last-Modified"
            java.lang.Object r11 = r11.get(r2)     // Catch:{ all -> 0x015f }
            java.util.List r11 = (java.util.List) r11     // Catch:{ all -> 0x015f }
            goto L_0x00c5
        L_0x00c4:
            r11 = r9
        L_0x00c5:
            if (r11 == 0) goto L_0x00d4
            int r2 = r11.size()     // Catch:{ all -> 0x015f }
            if (r2 <= 0) goto L_0x00d4
            java.lang.Object r11 = r11.get(r0)     // Catch:{ all -> 0x015f }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x015f }
            goto L_0x00d5
        L_0x00d4:
            r11 = r9
        L_0x00d5:
            if (r8 == r5) goto L_0x00f1
            if (r8 != r3) goto L_0x00da
            goto L_0x00f1
        L_0x00da:
            com.google.android.gms.internal.zzckd r9 = r6.zzaym()     // Catch:{ all -> 0x015f }
            boolean r9 = r9.zzb(r7, r10, r11)     // Catch:{ all -> 0x015f }
            if (r9 != 0) goto L_0x010a
            com.google.android.gms.internal.zzcil r7 = r6.zzayj()     // Catch:{ all -> 0x0168 }
        L_0x00e8:
            r7.endTransaction()     // Catch:{ all -> 0x0168 }
            r6.zzjoz = r0
            r6.zzbce()
            return
        L_0x00f1:
            com.google.android.gms.internal.zzckd r11 = r6.zzaym()     // Catch:{ all -> 0x015f }
            com.google.android.gms.internal.zzcny r11 = r11.zzka(r7)     // Catch:{ all -> 0x015f }
            if (r11 != 0) goto L_0x010a
            com.google.android.gms.internal.zzckd r11 = r6.zzaym()     // Catch:{ all -> 0x015f }
            boolean r9 = r11.zzb(r7, r9, r9)     // Catch:{ all -> 0x015f }
            if (r9 != 0) goto L_0x010a
            com.google.android.gms.internal.zzcil r7 = r6.zzayj()     // Catch:{ all -> 0x0168 }
            goto L_0x00e8
        L_0x010a:
            com.google.android.gms.common.util.zze r9 = r6.zzdir     // Catch:{ all -> 0x015f }
            long r2 = r9.currentTimeMillis()     // Catch:{ all -> 0x015f }
            r1.zzar(r2)     // Catch:{ all -> 0x015f }
            com.google.android.gms.internal.zzcil r9 = r6.zzayj()     // Catch:{ all -> 0x015f }
            r9.zza((com.google.android.gms.internal.zzcie) r1)     // Catch:{ all -> 0x015f }
            if (r8 != r5) goto L_0x012a
            com.google.android.gms.internal.zzcjj r8 = r6.zzayp()     // Catch:{ all -> 0x015f }
            com.google.android.gms.internal.zzcjl r8 = r8.zzbax()     // Catch:{ all -> 0x015f }
            java.lang.String r9 = "Config not found. Using empty config. appId"
            r8.zzj(r9, r7)     // Catch:{ all -> 0x015f }
            goto L_0x0140
        L_0x012a:
            com.google.android.gms.internal.zzcjj r7 = r6.zzayp()     // Catch:{ all -> 0x015f }
            com.google.android.gms.internal.zzcjl r7 = r7.zzbba()     // Catch:{ all -> 0x015f }
            java.lang.String r9 = "Successfully fetched config. Got network response. code, size"
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x015f }
            int r10 = r10.length     // Catch:{ all -> 0x015f }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x015f }
            r7.zze(r9, r8, r10)     // Catch:{ all -> 0x015f }
        L_0x0140:
            com.google.android.gms.internal.zzcjn r7 = r6.zzbbs()     // Catch:{ all -> 0x015f }
            boolean r7 = r7.zzaax()     // Catch:{ all -> 0x015f }
            if (r7 == 0) goto L_0x00b3
            boolean r7 = r6.zzbbz()     // Catch:{ all -> 0x015f }
            if (r7 == 0) goto L_0x00b3
            r6.zzbby()     // Catch:{ all -> 0x015f }
        L_0x0153:
            com.google.android.gms.internal.zzcil r7 = r6.zzayj()     // Catch:{ all -> 0x015f }
            r7.setTransactionSuccessful()     // Catch:{ all -> 0x015f }
            com.google.android.gms.internal.zzcil r7 = r6.zzayj()     // Catch:{ all -> 0x0168 }
            goto L_0x00e8
        L_0x015f:
            r7 = move-exception
            com.google.android.gms.internal.zzcil r8 = r6.zzayj()     // Catch:{ all -> 0x0168 }
            r8.endTransaction()     // Catch:{ all -> 0x0168 }
            throw r7     // Catch:{ all -> 0x0168 }
        L_0x0168:
            r7 = move-exception
            r6.zzjoz = r0
            r6.zzbce()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzckj.zzb(java.lang.String, int, java.lang.Throwable, byte[], java.util.Map):void");
    }

    /* access modifiers changed from: protected */
    public final boolean zzbbn() {
        zzyk();
        zzayo().zzwj();
        Boolean bool = this.zzjop;
        if (bool == null || this.zzjoq == 0 || (bool != null && !bool.booleanValue() && Math.abs(this.zzdir.elapsedRealtime() - this.zzjoq) > 1000)) {
            this.zzjoq = this.zzdir.elapsedRealtime();
            boolean z = false;
            if (zzayl().zzeh("android.permission.INTERNET") && zzayl().zzeh("android.permission.ACCESS_NETWORK_STATE") && (zzbih.zzdd(this.zzaiq).zzaoe() || (zzcka.zzbj(this.zzaiq) && zzcmy.zzg(this.zzaiq, false)))) {
                z = true;
            }
            Boolean valueOf = Boolean.valueOf(z);
            this.zzjop = valueOf;
            if (valueOf.booleanValue()) {
                this.zzjop = Boolean.valueOf(zzayl().zzkn(zzaye().getGmpAppId()));
            }
        }
        return this.zzjop.booleanValue();
    }

    public final zzcjj zzbbo() {
        zzcjj zzcjj = this.zzjnu;
        if (zzcjj == null || !zzcjj.isInitialized()) {
            return null;
        }
        return this.zzjnu;
    }

    /* access modifiers changed from: package-private */
    public final zzcke zzbbp() {
        return this.zzjnv;
    }

    public final AppMeasurement zzbbq() {
        return this.zzjny;
    }

    public final FirebaseAnalytics zzbbr() {
        return this.zzjnz;
    }

    public final zzcjn zzbbs() {
        zza((zzcli) this.zzjoc);
        return this.zzjoc;
    }

    /* access modifiers changed from: package-private */
    public final long zzbbw() {
        Long valueOf = Long.valueOf(zzayq().zzjls.get());
        return valueOf.longValue() == 0 ? this.zzjgk : Math.min(this.zzjgk, valueOf.longValue());
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:81|82) */
    /* JADX WARNING: Code restructure failed: missing block: B:82:?, code lost:
        zzayp().zzbau().zze("Failed to parse upload URL. Not uploading. appId", com.google.android.gms.internal.zzcjj.zzjs(r5), r6);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:81:0x0254 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzbby() {
        /*
            r17 = this;
            r1 = r17
            com.google.android.gms.internal.zzcke r0 = r17.zzayo()
            r0.zzwj()
            r17.zzyk()
            r0 = 1
            r1.zzjpb = r0
            r2 = 0
            com.google.android.gms.internal.zzcme r3 = r17.zzayg()     // Catch:{ all -> 0x028b }
            java.lang.Boolean r3 = r3.zzbck()     // Catch:{ all -> 0x028b }
            if (r3 != 0) goto L_0x002d
            com.google.android.gms.internal.zzcjj r0 = r17.zzayp()     // Catch:{ all -> 0x028b }
            com.google.android.gms.internal.zzcjl r0 = r0.zzbaw()     // Catch:{ all -> 0x028b }
            java.lang.String r3 = "Upload data called on the client side before use of service was decided"
        L_0x0024:
            r0.log(r3)     // Catch:{ all -> 0x028b }
        L_0x0027:
            r1.zzjpb = r2
            r17.zzbce()
            return
        L_0x002d:
            boolean r3 = r3.booleanValue()     // Catch:{ all -> 0x028b }
            if (r3 == 0) goto L_0x003e
            com.google.android.gms.internal.zzcjj r0 = r17.zzayp()     // Catch:{ all -> 0x028b }
            com.google.android.gms.internal.zzcjl r0 = r0.zzbau()     // Catch:{ all -> 0x028b }
            java.lang.String r3 = "Upload called in the client side when service should be used"
            goto L_0x0024
        L_0x003e:
            long r3 = r1.zzjoy     // Catch:{ all -> 0x028b }
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x004a
        L_0x0046:
            r17.zzbca()     // Catch:{ all -> 0x028b }
            goto L_0x0027
        L_0x004a:
            com.google.android.gms.internal.zzcke r3 = r17.zzayo()     // Catch:{ all -> 0x028b }
            r3.zzwj()     // Catch:{ all -> 0x028b }
            java.util.List<java.lang.Long> r3 = r1.zzjot     // Catch:{ all -> 0x028b }
            if (r3 == 0) goto L_0x0057
            r3 = 1
            goto L_0x0058
        L_0x0057:
            r3 = 0
        L_0x0058:
            if (r3 == 0) goto L_0x0065
            com.google.android.gms.internal.zzcjj r0 = r17.zzayp()     // Catch:{ all -> 0x028b }
            com.google.android.gms.internal.zzcjl r0 = r0.zzbba()     // Catch:{ all -> 0x028b }
            java.lang.String r3 = "Uploading requested multiple times"
            goto L_0x0024
        L_0x0065:
            com.google.android.gms.internal.zzcjn r3 = r17.zzbbs()     // Catch:{ all -> 0x028b }
            boolean r3 = r3.zzaax()     // Catch:{ all -> 0x028b }
            if (r3 != 0) goto L_0x007d
            com.google.android.gms.internal.zzcjj r0 = r17.zzayp()     // Catch:{ all -> 0x028b }
            com.google.android.gms.internal.zzcjl r0 = r0.zzbba()     // Catch:{ all -> 0x028b }
            java.lang.String r3 = "Network not connected, ignoring upload request"
            r0.log(r3)     // Catch:{ all -> 0x028b }
            goto L_0x0046
        L_0x007d:
            com.google.android.gms.common.util.zze r3 = r1.zzdir     // Catch:{ all -> 0x028b }
            long r3 = r3.currentTimeMillis()     // Catch:{ all -> 0x028b }
            long r7 = com.google.android.gms.internal.zzcik.zzazt()     // Catch:{ all -> 0x028b }
            long r7 = r3 - r7
            r9 = 0
            r1.zzg(r9, r7)     // Catch:{ all -> 0x028b }
            com.google.android.gms.internal.zzcju r7 = r17.zzayq()     // Catch:{ all -> 0x028b }
            com.google.android.gms.internal.zzcjx r7 = r7.zzjln     // Catch:{ all -> 0x028b }
            long r7 = r7.get()     // Catch:{ all -> 0x028b }
            int r10 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r10 == 0) goto L_0x00b2
            com.google.android.gms.internal.zzcjj r5 = r17.zzayp()     // Catch:{ all -> 0x028b }
            com.google.android.gms.internal.zzcjl r5 = r5.zzbaz()     // Catch:{ all -> 0x028b }
            java.lang.String r6 = "Uploading events. Elapsed time since last upload attempt (ms)"
            long r7 = r3 - r7
            long r7 = java.lang.Math.abs(r7)     // Catch:{ all -> 0x028b }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x028b }
            r5.zzj(r6, r7)     // Catch:{ all -> 0x028b }
        L_0x00b2:
            com.google.android.gms.internal.zzcil r5 = r17.zzayj()     // Catch:{ all -> 0x028b }
            java.lang.String r5 = r5.zzazw()     // Catch:{ all -> 0x028b }
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x028b }
            r7 = -1
            if (r6 != 0) goto L_0x0267
            long r10 = r1.zzjox     // Catch:{ all -> 0x028b }
            int r6 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r6 != 0) goto L_0x00d2
            com.google.android.gms.internal.zzcil r6 = r17.zzayj()     // Catch:{ all -> 0x028b }
            long r6 = r6.zzbad()     // Catch:{ all -> 0x028b }
            r1.zzjox = r6     // Catch:{ all -> 0x028b }
        L_0x00d2:
            com.google.android.gms.internal.zzcik r6 = r1.zzjns     // Catch:{ all -> 0x028b }
            com.google.android.gms.internal.zzcja<java.lang.Integer> r7 = com.google.android.gms.internal.zzciz.zzjit     // Catch:{ all -> 0x028b }
            int r6 = r6.zzb(r5, r7)     // Catch:{ all -> 0x028b }
            com.google.android.gms.internal.zzcik r7 = r1.zzjns     // Catch:{ all -> 0x028b }
            com.google.android.gms.internal.zzcja<java.lang.Integer> r8 = com.google.android.gms.internal.zzciz.zzjiu     // Catch:{ all -> 0x028b }
            int r7 = r7.zzb(r5, r8)     // Catch:{ all -> 0x028b }
            int r7 = java.lang.Math.max(r2, r7)     // Catch:{ all -> 0x028b }
            com.google.android.gms.internal.zzcil r8 = r17.zzayj()     // Catch:{ all -> 0x028b }
            java.util.List r6 = r8.zzl(r5, r6, r7)     // Catch:{ all -> 0x028b }
            boolean r7 = r6.isEmpty()     // Catch:{ all -> 0x028b }
            if (r7 != 0) goto L_0x0027
            java.util.Iterator r7 = r6.iterator()     // Catch:{ all -> 0x028b }
        L_0x00f8:
            boolean r8 = r7.hasNext()     // Catch:{ all -> 0x028b }
            if (r8 == 0) goto L_0x0113
            java.lang.Object r8 = r7.next()     // Catch:{ all -> 0x028b }
            android.util.Pair r8 = (android.util.Pair) r8     // Catch:{ all -> 0x028b }
            java.lang.Object r8 = r8.first     // Catch:{ all -> 0x028b }
            com.google.android.gms.internal.zzcoe r8 = (com.google.android.gms.internal.zzcoe) r8     // Catch:{ all -> 0x028b }
            java.lang.String r10 = r8.zzjvc     // Catch:{ all -> 0x028b }
            boolean r10 = android.text.TextUtils.isEmpty(r10)     // Catch:{ all -> 0x028b }
            if (r10 != 0) goto L_0x00f8
            java.lang.String r7 = r8.zzjvc     // Catch:{ all -> 0x028b }
            goto L_0x0114
        L_0x0113:
            r7 = r9
        L_0x0114:
            if (r7 == 0) goto L_0x013f
            r8 = 0
        L_0x0117:
            int r10 = r6.size()     // Catch:{ all -> 0x028b }
            if (r8 >= r10) goto L_0x013f
            java.lang.Object r10 = r6.get(r8)     // Catch:{ all -> 0x028b }
            android.util.Pair r10 = (android.util.Pair) r10     // Catch:{ all -> 0x028b }
            java.lang.Object r10 = r10.first     // Catch:{ all -> 0x028b }
            com.google.android.gms.internal.zzcoe r10 = (com.google.android.gms.internal.zzcoe) r10     // Catch:{ all -> 0x028b }
            java.lang.String r11 = r10.zzjvc     // Catch:{ all -> 0x028b }
            boolean r11 = android.text.TextUtils.isEmpty(r11)     // Catch:{ all -> 0x028b }
            if (r11 != 0) goto L_0x013c
            java.lang.String r10 = r10.zzjvc     // Catch:{ all -> 0x028b }
            boolean r10 = r10.equals(r7)     // Catch:{ all -> 0x028b }
            if (r10 != 0) goto L_0x013c
            java.util.List r6 = r6.subList(r2, r8)     // Catch:{ all -> 0x028b }
            goto L_0x013f
        L_0x013c:
            int r8 = r8 + 1
            goto L_0x0117
        L_0x013f:
            com.google.android.gms.internal.zzcod r7 = new com.google.android.gms.internal.zzcod     // Catch:{ all -> 0x028b }
            r7.<init>()     // Catch:{ all -> 0x028b }
            int r8 = r6.size()     // Catch:{ all -> 0x028b }
            com.google.android.gms.internal.zzcoe[] r8 = new com.google.android.gms.internal.zzcoe[r8]     // Catch:{ all -> 0x028b }
            r7.zzjun = r8     // Catch:{ all -> 0x028b }
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ all -> 0x028b }
            int r10 = r6.size()     // Catch:{ all -> 0x028b }
            r8.<init>(r10)     // Catch:{ all -> 0x028b }
            boolean r10 = com.google.android.gms.internal.zzcik.zzazv()     // Catch:{ all -> 0x028b }
            if (r10 == 0) goto L_0x0165
            com.google.android.gms.internal.zzcik r10 = r1.zzjns     // Catch:{ all -> 0x028b }
            boolean r10 = r10.zzjg(r5)     // Catch:{ all -> 0x028b }
            if (r10 == 0) goto L_0x0165
            r10 = 1
            goto L_0x0166
        L_0x0165:
            r10 = 0
        L_0x0166:
            r11 = 0
        L_0x0167:
            com.google.android.gms.internal.zzcoe[] r12 = r7.zzjun     // Catch:{ all -> 0x028b }
            int r12 = r12.length     // Catch:{ all -> 0x028b }
            if (r11 >= r12) goto L_0x01b2
            com.google.android.gms.internal.zzcoe[] r12 = r7.zzjun     // Catch:{ all -> 0x028b }
            java.lang.Object r13 = r6.get(r11)     // Catch:{ all -> 0x028b }
            android.util.Pair r13 = (android.util.Pair) r13     // Catch:{ all -> 0x028b }
            java.lang.Object r13 = r13.first     // Catch:{ all -> 0x028b }
            com.google.android.gms.internal.zzcoe r13 = (com.google.android.gms.internal.zzcoe) r13     // Catch:{ all -> 0x028b }
            r12[r11] = r13     // Catch:{ all -> 0x028b }
            java.lang.Object r12 = r6.get(r11)     // Catch:{ all -> 0x028b }
            android.util.Pair r12 = (android.util.Pair) r12     // Catch:{ all -> 0x028b }
            java.lang.Object r12 = r12.second     // Catch:{ all -> 0x028b }
            java.lang.Long r12 = (java.lang.Long) r12     // Catch:{ all -> 0x028b }
            r8.add(r12)     // Catch:{ all -> 0x028b }
            com.google.android.gms.internal.zzcoe[] r12 = r7.zzjun     // Catch:{ all -> 0x028b }
            r12 = r12[r11]     // Catch:{ all -> 0x028b }
            r13 = 12211(0x2fb3, double:6.033E-320)
            java.lang.Long r13 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x028b }
            r12.zzjvb = r13     // Catch:{ all -> 0x028b }
            com.google.android.gms.internal.zzcoe[] r12 = r7.zzjun     // Catch:{ all -> 0x028b }
            r12 = r12[r11]     // Catch:{ all -> 0x028b }
            java.lang.Long r13 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x028b }
            r12.zzjus = r13     // Catch:{ all -> 0x028b }
            com.google.android.gms.internal.zzcoe[] r12 = r7.zzjun     // Catch:{ all -> 0x028b }
            r12 = r12[r11]     // Catch:{ all -> 0x028b }
            java.lang.Boolean r13 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x028b }
            r12.zzjvg = r13     // Catch:{ all -> 0x028b }
            if (r10 != 0) goto L_0x01af
            com.google.android.gms.internal.zzcoe[] r12 = r7.zzjun     // Catch:{ all -> 0x028b }
            r12 = r12[r11]     // Catch:{ all -> 0x028b }
            r12.zzjvn = r9     // Catch:{ all -> 0x028b }
        L_0x01af:
            int r11 = r11 + 1
            goto L_0x0167
        L_0x01b2:
            com.google.android.gms.internal.zzcjj r6 = r17.zzayp()     // Catch:{ all -> 0x028b }
            r10 = 2
            boolean r6 = r6.zzae(r10)     // Catch:{ all -> 0x028b }
            if (r6 == 0) goto L_0x01c5
            com.google.android.gms.internal.zzcjh r6 = r17.zzayk()     // Catch:{ all -> 0x028b }
            java.lang.String r9 = r6.zza((com.google.android.gms.internal.zzcod) r7)     // Catch:{ all -> 0x028b }
        L_0x01c5:
            com.google.android.gms.internal.zzcno r6 = r17.zzayl()     // Catch:{ all -> 0x028b }
            byte[] r14 = r6.zzb(r7)     // Catch:{ all -> 0x028b }
            com.google.android.gms.internal.zzcja<java.lang.String> r6 = com.google.android.gms.internal.zzciz.zzjjd     // Catch:{ all -> 0x028b }
            java.lang.Object r6 = r6.get()     // Catch:{ all -> 0x028b }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x028b }
            java.net.URL r13 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0254 }
            r13.<init>(r6)     // Catch:{ MalformedURLException -> 0x0254 }
            boolean r10 = r8.isEmpty()     // Catch:{ MalformedURLException -> 0x0254 }
            if (r10 != 0) goto L_0x01e2
            r10 = 1
            goto L_0x01e3
        L_0x01e2:
            r10 = 0
        L_0x01e3:
            com.google.android.gms.common.internal.zzbq.checkArgument(r10)     // Catch:{ MalformedURLException -> 0x0254 }
            java.util.List<java.lang.Long> r10 = r1.zzjot     // Catch:{ MalformedURLException -> 0x0254 }
            if (r10 == 0) goto L_0x01f8
            com.google.android.gms.internal.zzcjj r8 = r17.zzayp()     // Catch:{ MalformedURLException -> 0x0254 }
            com.google.android.gms.internal.zzcjl r8 = r8.zzbau()     // Catch:{ MalformedURLException -> 0x0254 }
            java.lang.String r10 = "Set uploading progress before finishing the previous upload"
            r8.log(r10)     // Catch:{ MalformedURLException -> 0x0254 }
            goto L_0x01ff
        L_0x01f8:
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ MalformedURLException -> 0x0254 }
            r10.<init>(r8)     // Catch:{ MalformedURLException -> 0x0254 }
            r1.zzjot = r10     // Catch:{ MalformedURLException -> 0x0254 }
        L_0x01ff:
            com.google.android.gms.internal.zzcju r8 = r17.zzayq()     // Catch:{ MalformedURLException -> 0x0254 }
            com.google.android.gms.internal.zzcjx r8 = r8.zzjlo     // Catch:{ MalformedURLException -> 0x0254 }
            r8.set(r3)     // Catch:{ MalformedURLException -> 0x0254 }
            java.lang.String r3 = "?"
            com.google.android.gms.internal.zzcoe[] r4 = r7.zzjun     // Catch:{ MalformedURLException -> 0x0254 }
            int r4 = r4.length     // Catch:{ MalformedURLException -> 0x0254 }
            if (r4 <= 0) goto L_0x0215
            com.google.android.gms.internal.zzcoe[] r3 = r7.zzjun     // Catch:{ MalformedURLException -> 0x0254 }
            r3 = r3[r2]     // Catch:{ MalformedURLException -> 0x0254 }
            java.lang.String r3 = r3.zzcm     // Catch:{ MalformedURLException -> 0x0254 }
        L_0x0215:
            com.google.android.gms.internal.zzcjj r4 = r17.zzayp()     // Catch:{ MalformedURLException -> 0x0254 }
            com.google.android.gms.internal.zzcjl r4 = r4.zzbba()     // Catch:{ MalformedURLException -> 0x0254 }
            java.lang.String r7 = "Uploading data. app, uncompressed size, data"
            int r8 = r14.length     // Catch:{ MalformedURLException -> 0x0254 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ MalformedURLException -> 0x0254 }
            r4.zzd(r7, r3, r8, r9)     // Catch:{ MalformedURLException -> 0x0254 }
            r1.zzjpa = r0     // Catch:{ MalformedURLException -> 0x0254 }
            com.google.android.gms.internal.zzcjn r11 = r17.zzbbs()     // Catch:{ MalformedURLException -> 0x0254 }
            com.google.android.gms.internal.zzckm r0 = new com.google.android.gms.internal.zzckm     // Catch:{ MalformedURLException -> 0x0254 }
            r0.<init>(r1)     // Catch:{ MalformedURLException -> 0x0254 }
            r11.zzwj()     // Catch:{ MalformedURLException -> 0x0254 }
            r11.zzyk()     // Catch:{ MalformedURLException -> 0x0254 }
            com.google.android.gms.common.internal.zzbq.checkNotNull(r13)     // Catch:{ MalformedURLException -> 0x0254 }
            com.google.android.gms.common.internal.zzbq.checkNotNull(r14)     // Catch:{ MalformedURLException -> 0x0254 }
            com.google.android.gms.common.internal.zzbq.checkNotNull(r0)     // Catch:{ MalformedURLException -> 0x0254 }
            com.google.android.gms.internal.zzcke r3 = r11.zzayo()     // Catch:{ MalformedURLException -> 0x0254 }
            com.google.android.gms.internal.zzcjr r4 = new com.google.android.gms.internal.zzcjr     // Catch:{ MalformedURLException -> 0x0254 }
            r15 = 0
            r10 = r4
            r12 = r5
            r16 = r0
            r10.<init>(r11, r12, r13, r14, r15, r16)     // Catch:{ MalformedURLException -> 0x0254 }
            r3.zzi(r4)     // Catch:{ MalformedURLException -> 0x0254 }
            goto L_0x0027
        L_0x0254:
            com.google.android.gms.internal.zzcjj r0 = r17.zzayp()     // Catch:{ all -> 0x028b }
            com.google.android.gms.internal.zzcjl r0 = r0.zzbau()     // Catch:{ all -> 0x028b }
            java.lang.String r3 = "Failed to parse upload URL. Not uploading. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzcjj.zzjs(r5)     // Catch:{ all -> 0x028b }
            r0.zze(r3, r4, r6)     // Catch:{ all -> 0x028b }
            goto L_0x0027
        L_0x0267:
            r1.zzjox = r7     // Catch:{ all -> 0x028b }
            com.google.android.gms.internal.zzcil r0 = r17.zzayj()     // Catch:{ all -> 0x028b }
            long r5 = com.google.android.gms.internal.zzcik.zzazt()     // Catch:{ all -> 0x028b }
            long r3 = r3 - r5
            java.lang.String r0 = r0.zzba(r3)     // Catch:{ all -> 0x028b }
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x028b }
            if (r3 != 0) goto L_0x0027
            com.google.android.gms.internal.zzcil r3 = r17.zzayj()     // Catch:{ all -> 0x028b }
            com.google.android.gms.internal.zzcie r0 = r3.zzjj(r0)     // Catch:{ all -> 0x028b }
            if (r0 == 0) goto L_0x0027
            r1.zzb((com.google.android.gms.internal.zzcie) r0)     // Catch:{ all -> 0x028b }
            goto L_0x0027
        L_0x028b:
            r0 = move-exception
            r1.zzjpb = r2
            r17.zzbce()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzckj.zzbby():void");
    }

    /* access modifiers changed from: package-private */
    public final void zzbcb() {
        this.zzjow++;
    }

    /* access modifiers changed from: package-private */
    public final void zzbcc() {
        zzcjl zzbau;
        Integer valueOf;
        Integer valueOf2;
        String str;
        zzayo().zzwj();
        zzyk();
        if (!this.zzjoo) {
            zzayp().zzbay().log("This instance being marked as an uploader");
            zzayo().zzwj();
            zzyk();
            if (zzbcd() && zzbbv()) {
                int zza2 = zza(this.zzjos);
                int zzbar = zzaye().zzbar();
                zzayo().zzwj();
                if (zza2 > zzbar) {
                    zzayp().zzbau().zze("Panic: can't downgrade version. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzbar));
                } else if (zza2 < zzbar) {
                    if (zza(zzbar, this.zzjos)) {
                        zzayp().zzbba().zze("Storage version upgraded. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzbar));
                    } else {
                        zzayp().zzbau().zze("Storage version upgrade failed. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzbar));
                    }
                }
            }
            this.zzjoo = true;
            zzbca();
        }
    }

    public final void zzbt(boolean z) {
        zzbca();
    }

    /* access modifiers changed from: package-private */
    public final void zzc(zzcii zzcii, zzcif zzcif) {
        zzbq.checkNotNull(zzcii);
        zzbq.zzgv(zzcii.packageName);
        zzbq.checkNotNull(zzcii.zzjgn);
        zzbq.zzgv(zzcii.zzjgn.name);
        zzayo().zzwj();
        zzyk();
        if (!TextUtils.isEmpty(zzcif.zzjfl)) {
            if (!zzcif.zzjfv) {
                zzg(zzcif);
                return;
            }
            zzayj().beginTransaction();
            try {
                zzg(zzcif);
                zzcii zzah = zzayj().zzah(zzcii.packageName, zzcii.zzjgn.name);
                if (zzah != null) {
                    zzayp().zzbaz().zze("Removing conditional user property", zzcii.packageName, zzayk().zzjr(zzcii.zzjgn.name));
                    zzayj().zzai(zzcii.packageName, zzcii.zzjgn.name);
                    if (zzah.zzjgp) {
                        zzayj().zzaf(zzcii.packageName, zzcii.zzjgn.name);
                    }
                    if (zzcii.zzjgv != null) {
                        Bundle bundle = null;
                        if (zzcii.zzjgv.zzjhr != null) {
                            bundle = zzcii.zzjgv.zzjhr.zzbao();
                        }
                        Bundle bundle2 = bundle;
                        zzc(zzayl().zza(zzcii.zzjgv.name, bundle2, zzah.zzjgm, zzcii.zzjgv.zzjib, true, false), zzcif);
                    }
                } else {
                    zzayp().zzbaw().zze("Conditional user property doesn't exist", zzcjj.zzjs(zzcii.packageName), zzayk().zzjr(zzcii.zzjgn.name));
                }
                zzayj().setTransactionSuccessful();
            } finally {
                zzayj().endTransaction();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzc(zzcnl zzcnl, zzcif zzcif) {
        zzayo().zzwj();
        zzyk();
        if (!TextUtils.isEmpty(zzcif.zzjfl)) {
            if (!zzcif.zzjfv) {
                zzg(zzcif);
                return;
            }
            zzayp().zzbaz().zzj("Removing user property", zzayk().zzjr(zzcnl.name));
            zzayj().beginTransaction();
            try {
                zzg(zzcif);
                zzayj().zzaf(zzcif.packageName, zzcnl.name);
                zzayj().setTransactionSuccessful();
                zzayp().zzbaz().zzj("User property removed", zzayk().zzjr(zzcnl.name));
            } finally {
                zzayj().endTransaction();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzd(zzcif zzcif) {
        zzayj().zzjj(zzcif.packageName);
        zzcil zzayj = zzayj();
        String str = zzcif.packageName;
        zzbq.zzgv(str);
        zzayj.zzwj();
        zzayj.zzyk();
        try {
            SQLiteDatabase writableDatabase = zzayj.getWritableDatabase();
            String[] strArr = {str};
            int delete = writableDatabase.delete("apps", "app_id=?", strArr) + 0 + writableDatabase.delete("events", "app_id=?", strArr) + writableDatabase.delete("user_attributes", "app_id=?", strArr) + writableDatabase.delete("conditional_properties", "app_id=?", strArr) + writableDatabase.delete("raw_events", "app_id=?", strArr) + writableDatabase.delete("raw_events_metadata", "app_id=?", strArr) + writableDatabase.delete("queue", "app_id=?", strArr) + writableDatabase.delete("audience_filter_values", "app_id=?", strArr) + writableDatabase.delete("main_event_params", "app_id=?", strArr);
            if (delete > 0) {
                zzayj.zzayp().zzbba().zze("Reset analytics data. app, records", str, Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzayj.zzayp().zzbau().zze("Error resetting analytics data. appId, error", zzcjj.zzjs(str), e);
        }
        zzf(zza(this.zzaiq, zzcif.packageName, zzcif.zzjfl, zzcif.zzjfv, zzcif.zzjfx));
    }

    /* access modifiers changed from: package-private */
    public final void zzd(zzcii zzcii) {
        zzcif zzke = zzke(zzcii.packageName);
        if (zzke != null) {
            zzb(zzcii, zzke);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zze(zzcif zzcif) {
        zzayo().zzwj();
        zzyk();
        zzbq.zzgv(zzcif.packageName);
        zzg(zzcif);
    }

    /* access modifiers changed from: package-private */
    public final void zze(zzcii zzcii) {
        zzcif zzke = zzke(zzcii.packageName);
        if (zzke != null) {
            zzc(zzcii, zzke);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:105:0x02eb A[Catch:{ SQLiteException -> 0x0136, all -> 0x038b }] */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x035f A[Catch:{ SQLiteException -> 0x0136, all -> 0x038b }] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x01be A[Catch:{ SQLiteException -> 0x0136, all -> 0x038b }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x01cb A[Catch:{ SQLiteException -> 0x0136, all -> 0x038b }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x01d9 A[Catch:{ SQLiteException -> 0x0136, all -> 0x038b }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x026e A[Catch:{ SQLiteException -> 0x0136, all -> 0x038b }] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0299  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x02be A[Catch:{ SQLiteException -> 0x0136, all -> 0x038b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzf(com.google.android.gms.internal.zzcif r21) {
        /*
            r20 = this;
            r1 = r20
            r2 = r21
            java.lang.String r3 = "_sysu"
            java.lang.String r4 = "_sys"
            java.lang.String r5 = "_pfo"
            java.lang.String r6 = "_uwa"
            java.lang.String r0 = "app_id=?"
            com.google.android.gms.internal.zzcke r7 = r20.zzayo()
            r7.zzwj()
            r20.zzyk()
            com.google.android.gms.common.internal.zzbq.checkNotNull(r21)
            java.lang.String r7 = r2.packageName
            com.google.android.gms.common.internal.zzbq.zzgv(r7)
            java.lang.String r7 = r2.zzjfl
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            if (r7 == 0) goto L_0x0029
            return
        L_0x0029:
            com.google.android.gms.internal.zzcil r7 = r20.zzayj()
            java.lang.String r8 = r2.packageName
            com.google.android.gms.internal.zzcie r7 = r7.zzjj(r8)
            r8 = 0
            if (r7 == 0) goto L_0x005c
            java.lang.String r10 = r7.getGmpAppId()
            boolean r10 = android.text.TextUtils.isEmpty(r10)
            if (r10 == 0) goto L_0x005c
            java.lang.String r10 = r2.zzjfl
            boolean r10 = android.text.TextUtils.isEmpty(r10)
            if (r10 != 0) goto L_0x005c
            r7.zzar(r8)
            com.google.android.gms.internal.zzcil r10 = r20.zzayj()
            r10.zza((com.google.android.gms.internal.zzcie) r7)
            com.google.android.gms.internal.zzckd r7 = r20.zzaym()
            java.lang.String r10 = r2.packageName
            r7.zzkd(r10)
        L_0x005c:
            boolean r7 = r2.zzjfv
            if (r7 != 0) goto L_0x0064
            r20.zzg(r21)
            return
        L_0x0064:
            long r10 = r2.zzjgk
            int r7 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r7 != 0) goto L_0x0070
            com.google.android.gms.common.util.zze r7 = r1.zzdir
            long r10 = r7.currentTimeMillis()
        L_0x0070:
            int r7 = r2.zzjgl
            r15 = 0
            r14 = 1
            if (r7 == 0) goto L_0x0090
            if (r7 == r14) goto L_0x0090
            com.google.android.gms.internal.zzcjj r12 = r20.zzayp()
            com.google.android.gms.internal.zzcjl r12 = r12.zzbaw()
            java.lang.String r13 = r2.packageName
            java.lang.Object r13 = com.google.android.gms.internal.zzcjj.zzjs(r13)
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            java.lang.String r8 = "Incorrect app type, assuming installed app. appId, appType"
            r12.zze(r8, r13, r7)
            r7 = 0
        L_0x0090:
            com.google.android.gms.internal.zzcil r8 = r20.zzayj()
            r8.beginTransaction()
            com.google.android.gms.internal.zzcil r8 = r20.zzayj()     // Catch:{ all -> 0x038b }
            java.lang.String r9 = r2.packageName     // Catch:{ all -> 0x038b }
            com.google.android.gms.internal.zzcie r8 = r8.zzjj(r9)     // Catch:{ all -> 0x038b }
            if (r8 == 0) goto L_0x0149
            java.lang.String r12 = r8.getGmpAppId()     // Catch:{ all -> 0x038b }
            if (r12 == 0) goto L_0x0149
            java.lang.String r12 = r8.getGmpAppId()     // Catch:{ all -> 0x038b }
            java.lang.String r13 = r2.zzjfl     // Catch:{ all -> 0x038b }
            boolean r12 = r12.equals(r13)     // Catch:{ all -> 0x038b }
            if (r12 != 0) goto L_0x0149
            com.google.android.gms.internal.zzcjj r12 = r20.zzayp()     // Catch:{ all -> 0x038b }
            com.google.android.gms.internal.zzcjl r12 = r12.zzbaw()     // Catch:{ all -> 0x038b }
            java.lang.String r13 = "New GMP App Id passed in. Removing cached database data. appId"
            java.lang.String r16 = r8.getAppId()     // Catch:{ all -> 0x038b }
            java.lang.Object r9 = com.google.android.gms.internal.zzcjj.zzjs(r16)     // Catch:{ all -> 0x038b }
            r12.zzj(r13, r9)     // Catch:{ all -> 0x038b }
            com.google.android.gms.internal.zzcil r9 = r20.zzayj()     // Catch:{ all -> 0x038b }
            java.lang.String r8 = r8.getAppId()     // Catch:{ all -> 0x038b }
            r9.zzyk()     // Catch:{ all -> 0x038b }
            r9.zzwj()     // Catch:{ all -> 0x038b }
            com.google.android.gms.common.internal.zzbq.zzgv(r8)     // Catch:{ all -> 0x038b }
            android.database.sqlite.SQLiteDatabase r12 = r9.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0136 }
            java.lang.String[] r13 = new java.lang.String[r14]     // Catch:{ SQLiteException -> 0x0136 }
            r13[r15] = r8     // Catch:{ SQLiteException -> 0x0136 }
            java.lang.String r14 = "events"
            int r14 = r12.delete(r14, r0, r13)     // Catch:{ SQLiteException -> 0x0136 }
            int r14 = r14 + r15
            java.lang.String r15 = "user_attributes"
            int r15 = r12.delete(r15, r0, r13)     // Catch:{ SQLiteException -> 0x0136 }
            int r14 = r14 + r15
            java.lang.String r15 = "conditional_properties"
            int r15 = r12.delete(r15, r0, r13)     // Catch:{ SQLiteException -> 0x0136 }
            int r14 = r14 + r15
            java.lang.String r15 = "apps"
            int r15 = r12.delete(r15, r0, r13)     // Catch:{ SQLiteException -> 0x0136 }
            int r14 = r14 + r15
            java.lang.String r15 = "raw_events"
            int r15 = r12.delete(r15, r0, r13)     // Catch:{ SQLiteException -> 0x0136 }
            int r14 = r14 + r15
            java.lang.String r15 = "raw_events_metadata"
            int r15 = r12.delete(r15, r0, r13)     // Catch:{ SQLiteException -> 0x0136 }
            int r14 = r14 + r15
            java.lang.String r15 = "event_filters"
            int r15 = r12.delete(r15, r0, r13)     // Catch:{ SQLiteException -> 0x0136 }
            int r14 = r14 + r15
            java.lang.String r15 = "property_filters"
            int r15 = r12.delete(r15, r0, r13)     // Catch:{ SQLiteException -> 0x0136 }
            int r14 = r14 + r15
            java.lang.String r15 = "audience_filter_values"
            int r0 = r12.delete(r15, r0, r13)     // Catch:{ SQLiteException -> 0x0136 }
            int r14 = r14 + r0
            if (r14 <= 0) goto L_0x0148
            com.google.android.gms.internal.zzcjj r0 = r9.zzayp()     // Catch:{ SQLiteException -> 0x0136 }
            com.google.android.gms.internal.zzcjl r0 = r0.zzbba()     // Catch:{ SQLiteException -> 0x0136 }
            java.lang.String r12 = "Deleted application data. app, records"
            java.lang.Integer r13 = java.lang.Integer.valueOf(r14)     // Catch:{ SQLiteException -> 0x0136 }
            r0.zze(r12, r8, r13)     // Catch:{ SQLiteException -> 0x0136 }
            goto L_0x0148
        L_0x0136:
            r0 = move-exception
            com.google.android.gms.internal.zzcjj r9 = r9.zzayp()     // Catch:{ all -> 0x038b }
            com.google.android.gms.internal.zzcjl r9 = r9.zzbau()     // Catch:{ all -> 0x038b }
            java.lang.String r12 = "Error deleting application data. appId, error"
            java.lang.Object r8 = com.google.android.gms.internal.zzcjj.zzjs(r8)     // Catch:{ all -> 0x038b }
            r9.zze(r12, r8, r0)     // Catch:{ all -> 0x038b }
        L_0x0148:
            r8 = 0
        L_0x0149:
            if (r8 == 0) goto L_0x01b8
            long r12 = r8.zzayx()     // Catch:{ all -> 0x038b }
            r14 = -2147483648(0xffffffff80000000, double:NaN)
            java.lang.String r0 = "_pv"
            int r9 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r9 == 0) goto L_0x0185
            long r12 = r8.zzayx()     // Catch:{ all -> 0x038b }
            long r14 = r2.zzjfr     // Catch:{ all -> 0x038b }
            int r9 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r9 == 0) goto L_0x01b8
            android.os.Bundle r9 = new android.os.Bundle     // Catch:{ all -> 0x038b }
            r9.<init>()     // Catch:{ all -> 0x038b }
            java.lang.String r8 = r8.zzwo()     // Catch:{ all -> 0x038b }
            r9.putString(r0, r8)     // Catch:{ all -> 0x038b }
            com.google.android.gms.internal.zzcix r0 = new com.google.android.gms.internal.zzcix     // Catch:{ all -> 0x038b }
            java.lang.String r13 = "_au"
            com.google.android.gms.internal.zzciu r14 = new com.google.android.gms.internal.zzciu     // Catch:{ all -> 0x038b }
            r14.<init>(r9)     // Catch:{ all -> 0x038b }
            java.lang.String r15 = "auto"
            r12 = r0
            r9 = 1
            r8 = 0
            r16 = r10
            r12.<init>(r13, r14, r15, r16)     // Catch:{ all -> 0x038b }
        L_0x0181:
            r1.zzb((com.google.android.gms.internal.zzcix) r0, (com.google.android.gms.internal.zzcif) r2)     // Catch:{ all -> 0x038b }
            goto L_0x01b9
        L_0x0185:
            r9 = 1
            r15 = 0
            java.lang.String r12 = r8.zzwo()     // Catch:{ all -> 0x038b }
            if (r12 == 0) goto L_0x01b9
            java.lang.String r12 = r8.zzwo()     // Catch:{ all -> 0x038b }
            java.lang.String r13 = r2.zzina     // Catch:{ all -> 0x038b }
            boolean r12 = r12.equals(r13)     // Catch:{ all -> 0x038b }
            if (r12 != 0) goto L_0x01b9
            android.os.Bundle r12 = new android.os.Bundle     // Catch:{ all -> 0x038b }
            r12.<init>()     // Catch:{ all -> 0x038b }
            java.lang.String r8 = r8.zzwo()     // Catch:{ all -> 0x038b }
            r12.putString(r0, r8)     // Catch:{ all -> 0x038b }
            com.google.android.gms.internal.zzcix r0 = new com.google.android.gms.internal.zzcix     // Catch:{ all -> 0x038b }
            java.lang.String r13 = "_au"
            com.google.android.gms.internal.zzciu r14 = new com.google.android.gms.internal.zzciu     // Catch:{ all -> 0x038b }
            r14.<init>(r12)     // Catch:{ all -> 0x038b }
            java.lang.String r8 = "auto"
            r12 = r0
            r15 = r8
            r16 = r10
            r12.<init>(r13, r14, r15, r16)     // Catch:{ all -> 0x038b }
            goto L_0x0181
        L_0x01b8:
            r9 = 1
        L_0x01b9:
            r20.zzg(r21)     // Catch:{ all -> 0x038b }
            if (r7 != 0) goto L_0x01cb
            com.google.android.gms.internal.zzcil r0 = r20.zzayj()     // Catch:{ all -> 0x038b }
            java.lang.String r8 = r2.packageName     // Catch:{ all -> 0x038b }
            java.lang.String r12 = "_f"
        L_0x01c6:
            com.google.android.gms.internal.zzcit r0 = r0.zzae(r8, r12)     // Catch:{ all -> 0x038b }
            goto L_0x01d7
        L_0x01cb:
            if (r7 != r9) goto L_0x01d6
            com.google.android.gms.internal.zzcil r0 = r20.zzayj()     // Catch:{ all -> 0x038b }
            java.lang.String r8 = r2.packageName     // Catch:{ all -> 0x038b }
            java.lang.String r12 = "_v"
            goto L_0x01c6
        L_0x01d6:
            r0 = 0
        L_0x01d7:
            if (r0 != 0) goto L_0x035f
            r12 = 3600000(0x36ee80, double:1.7786363E-317)
            long r14 = r10 / r12
            r18 = r10
            r9 = 1
            long r14 = r14 + r9
            long r14 = r14 * r12
            java.lang.String r0 = "_r"
            java.lang.String r11 = "_c"
            if (r7 != 0) goto L_0x0303
            com.google.android.gms.internal.zzcnl r7 = new com.google.android.gms.internal.zzcnl     // Catch:{ all -> 0x038b }
            java.lang.String r13 = "_fot"
            java.lang.Long r16 = java.lang.Long.valueOf(r14)     // Catch:{ all -> 0x038b }
            java.lang.String r17 = "auto"
            r12 = r7
            r14 = r18
            r12.<init>(r13, r14, r16, r17)     // Catch:{ all -> 0x038b }
            r1.zzb((com.google.android.gms.internal.zzcnl) r7, (com.google.android.gms.internal.zzcif) r2)     // Catch:{ all -> 0x038b }
            com.google.android.gms.internal.zzcke r7 = r20.zzayo()     // Catch:{ all -> 0x038b }
            r7.zzwj()     // Catch:{ all -> 0x038b }
            r20.zzyk()     // Catch:{ all -> 0x038b }
            android.os.Bundle r7 = new android.os.Bundle     // Catch:{ all -> 0x038b }
            r7.<init>()     // Catch:{ all -> 0x038b }
            r7.putLong(r11, r9)     // Catch:{ all -> 0x038b }
            r7.putLong(r0, r9)     // Catch:{ all -> 0x038b }
            r11 = 0
            r7.putLong(r6, r11)     // Catch:{ all -> 0x038b }
            r7.putLong(r5, r11)     // Catch:{ all -> 0x038b }
            r7.putLong(r4, r11)     // Catch:{ all -> 0x038b }
            r7.putLong(r3, r11)     // Catch:{ all -> 0x038b }
            android.content.Context r0 = r1.zzaiq     // Catch:{ all -> 0x038b }
            android.content.pm.PackageManager r0 = r0.getPackageManager()     // Catch:{ all -> 0x038b }
            if (r0 != 0) goto L_0x023e
            com.google.android.gms.internal.zzcjj r0 = r20.zzayp()     // Catch:{ all -> 0x038b }
            com.google.android.gms.internal.zzcjl r0 = r0.zzbau()     // Catch:{ all -> 0x038b }
            java.lang.String r3 = "PackageManager is null, first open report might be inaccurate. appId"
            java.lang.String r4 = r2.packageName     // Catch:{ all -> 0x038b }
            java.lang.Object r4 = com.google.android.gms.internal.zzcjj.zzjs(r4)     // Catch:{ all -> 0x038b }
            r0.zzj(r3, r4)     // Catch:{ all -> 0x038b }
            goto L_0x02d0
        L_0x023e:
            android.content.Context r0 = r1.zzaiq     // Catch:{ NameNotFoundException -> 0x024e }
            com.google.android.gms.internal.zzbig r0 = com.google.android.gms.internal.zzbih.zzdd(r0)     // Catch:{ NameNotFoundException -> 0x024e }
            java.lang.String r11 = r2.packageName     // Catch:{ NameNotFoundException -> 0x024e }
            r14 = 0
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r11, r14)     // Catch:{ NameNotFoundException -> 0x024c }
            goto L_0x0264
        L_0x024c:
            r0 = move-exception
            goto L_0x0250
        L_0x024e:
            r0 = move-exception
            r14 = 0
        L_0x0250:
            com.google.android.gms.internal.zzcjj r11 = r20.zzayp()     // Catch:{ all -> 0x038b }
            com.google.android.gms.internal.zzcjl r11 = r11.zzbau()     // Catch:{ all -> 0x038b }
            java.lang.String r12 = "Package info is null, first open report might be inaccurate. appId"
            java.lang.String r13 = r2.packageName     // Catch:{ all -> 0x038b }
            java.lang.Object r13 = com.google.android.gms.internal.zzcjj.zzjs(r13)     // Catch:{ all -> 0x038b }
            r11.zze(r12, r13, r0)     // Catch:{ all -> 0x038b }
            r0 = 0
        L_0x0264:
            if (r0 == 0) goto L_0x0299
            long r11 = r0.firstInstallTime     // Catch:{ all -> 0x038b }
            r15 = 0
            int r13 = (r11 > r15 ? 1 : (r11 == r15 ? 0 : -1))
            if (r13 == 0) goto L_0x0299
            long r11 = r0.firstInstallTime     // Catch:{ all -> 0x038b }
            long r14 = r0.lastUpdateTime     // Catch:{ all -> 0x038b }
            int r0 = (r11 > r14 ? 1 : (r11 == r14 ? 0 : -1))
            if (r0 == 0) goto L_0x027b
            r7.putLong(r6, r9)     // Catch:{ all -> 0x038b }
            r15 = 0
            goto L_0x027c
        L_0x027b:
            r15 = 1
        L_0x027c:
            com.google.android.gms.internal.zzcnl r0 = new com.google.android.gms.internal.zzcnl     // Catch:{ all -> 0x038b }
            java.lang.String r13 = "_fi"
            if (r15 == 0) goto L_0x0284
            r11 = r9
            goto L_0x0286
        L_0x0284:
            r11 = 0
        L_0x0286:
            java.lang.Long r16 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x038b }
            java.lang.String r6 = "auto"
            r12 = r0
            r11 = 0
            r14 = r18
            r17 = r6
            r12.<init>(r13, r14, r16, r17)     // Catch:{ all -> 0x038b }
            r1.zzb((com.google.android.gms.internal.zzcnl) r0, (com.google.android.gms.internal.zzcif) r2)     // Catch:{ all -> 0x038b }
            goto L_0x029a
        L_0x0299:
            r11 = 0
        L_0x029a:
            android.content.Context r0 = r1.zzaiq     // Catch:{ NameNotFoundException -> 0x02a7 }
            com.google.android.gms.internal.zzbig r0 = com.google.android.gms.internal.zzbih.zzdd(r0)     // Catch:{ NameNotFoundException -> 0x02a7 }
            java.lang.String r6 = r2.packageName     // Catch:{ NameNotFoundException -> 0x02a7 }
            android.content.pm.ApplicationInfo r0 = r0.getApplicationInfo(r6, r11)     // Catch:{ NameNotFoundException -> 0x02a7 }
            goto L_0x02bc
        L_0x02a7:
            r0 = move-exception
            com.google.android.gms.internal.zzcjj r6 = r20.zzayp()     // Catch:{ all -> 0x038b }
            com.google.android.gms.internal.zzcjl r6 = r6.zzbau()     // Catch:{ all -> 0x038b }
            java.lang.String r11 = "Application info is null, first open report might be inaccurate. appId"
            java.lang.String r12 = r2.packageName     // Catch:{ all -> 0x038b }
            java.lang.Object r12 = com.google.android.gms.internal.zzcjj.zzjs(r12)     // Catch:{ all -> 0x038b }
            r6.zze(r11, r12, r0)     // Catch:{ all -> 0x038b }
            r0 = 0
        L_0x02bc:
            if (r0 == 0) goto L_0x02d0
            int r6 = r0.flags     // Catch:{ all -> 0x038b }
            r8 = 1
            r6 = r6 & r8
            if (r6 == 0) goto L_0x02c7
            r7.putLong(r4, r9)     // Catch:{ all -> 0x038b }
        L_0x02c7:
            int r0 = r0.flags     // Catch:{ all -> 0x038b }
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x02d0
            r7.putLong(r3, r9)     // Catch:{ all -> 0x038b }
        L_0x02d0:
            com.google.android.gms.internal.zzcil r0 = r20.zzayj()     // Catch:{ all -> 0x038b }
            java.lang.String r3 = r2.packageName     // Catch:{ all -> 0x038b }
            com.google.android.gms.common.internal.zzbq.zzgv(r3)     // Catch:{ all -> 0x038b }
            r0.zzwj()     // Catch:{ all -> 0x038b }
            r0.zzyk()     // Catch:{ all -> 0x038b }
            java.lang.String r4 = "first_open_count"
            long r3 = r0.zzal(r3, r4)     // Catch:{ all -> 0x038b }
            r11 = 0
            int r0 = (r3 > r11 ? 1 : (r3 == r11 ? 0 : -1))
            if (r0 < 0) goto L_0x02ee
            r7.putLong(r5, r3)     // Catch:{ all -> 0x038b }
        L_0x02ee:
            com.google.android.gms.internal.zzcix r0 = new com.google.android.gms.internal.zzcix     // Catch:{ all -> 0x038b }
            java.lang.String r13 = "_f"
            com.google.android.gms.internal.zzciu r14 = new com.google.android.gms.internal.zzciu     // Catch:{ all -> 0x038b }
            r14.<init>(r7)     // Catch:{ all -> 0x038b }
            java.lang.String r15 = "auto"
            r12 = r0
            r16 = r18
            r12.<init>(r13, r14, r15, r16)     // Catch:{ all -> 0x038b }
        L_0x02ff:
            r1.zzb((com.google.android.gms.internal.zzcix) r0, (com.google.android.gms.internal.zzcif) r2)     // Catch:{ all -> 0x038b }
            goto L_0x0340
        L_0x0303:
            r3 = 1
            if (r7 != r3) goto L_0x0340
            com.google.android.gms.internal.zzcnl r3 = new com.google.android.gms.internal.zzcnl     // Catch:{ all -> 0x038b }
            java.lang.String r13 = "_fvt"
            java.lang.Long r16 = java.lang.Long.valueOf(r14)     // Catch:{ all -> 0x038b }
            java.lang.String r17 = "auto"
            r12 = r3
            r14 = r18
            r12.<init>(r13, r14, r16, r17)     // Catch:{ all -> 0x038b }
            r1.zzb((com.google.android.gms.internal.zzcnl) r3, (com.google.android.gms.internal.zzcif) r2)     // Catch:{ all -> 0x038b }
            com.google.android.gms.internal.zzcke r3 = r20.zzayo()     // Catch:{ all -> 0x038b }
            r3.zzwj()     // Catch:{ all -> 0x038b }
            r20.zzyk()     // Catch:{ all -> 0x038b }
            android.os.Bundle r3 = new android.os.Bundle     // Catch:{ all -> 0x038b }
            r3.<init>()     // Catch:{ all -> 0x038b }
            r3.putLong(r11, r9)     // Catch:{ all -> 0x038b }
            r3.putLong(r0, r9)     // Catch:{ all -> 0x038b }
            com.google.android.gms.internal.zzcix r0 = new com.google.android.gms.internal.zzcix     // Catch:{ all -> 0x038b }
            java.lang.String r13 = "_v"
            com.google.android.gms.internal.zzciu r14 = new com.google.android.gms.internal.zzciu     // Catch:{ all -> 0x038b }
            r14.<init>(r3)     // Catch:{ all -> 0x038b }
            java.lang.String r15 = "auto"
            r12 = r0
            r16 = r18
            r12.<init>(r13, r14, r15, r16)     // Catch:{ all -> 0x038b }
            goto L_0x02ff
        L_0x0340:
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x038b }
            r0.<init>()     // Catch:{ all -> 0x038b }
            java.lang.String r3 = "_et"
            r0.putLong(r3, r9)     // Catch:{ all -> 0x038b }
            com.google.android.gms.internal.zzcix r3 = new com.google.android.gms.internal.zzcix     // Catch:{ all -> 0x038b }
            java.lang.String r13 = "_e"
            com.google.android.gms.internal.zzciu r14 = new com.google.android.gms.internal.zzciu     // Catch:{ all -> 0x038b }
            r14.<init>(r0)     // Catch:{ all -> 0x038b }
            java.lang.String r15 = "auto"
            r12 = r3
            r16 = r18
            r12.<init>(r13, r14, r15, r16)     // Catch:{ all -> 0x038b }
        L_0x035b:
            r1.zzb((com.google.android.gms.internal.zzcix) r3, (com.google.android.gms.internal.zzcif) r2)     // Catch:{ all -> 0x038b }
            goto L_0x037c
        L_0x035f:
            r18 = r10
            boolean r0 = r2.zzjgj     // Catch:{ all -> 0x038b }
            if (r0 == 0) goto L_0x037c
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x038b }
            r0.<init>()     // Catch:{ all -> 0x038b }
            com.google.android.gms.internal.zzcix r3 = new com.google.android.gms.internal.zzcix     // Catch:{ all -> 0x038b }
            java.lang.String r13 = "_cd"
            com.google.android.gms.internal.zzciu r14 = new com.google.android.gms.internal.zzciu     // Catch:{ all -> 0x038b }
            r14.<init>(r0)     // Catch:{ all -> 0x038b }
            java.lang.String r15 = "auto"
            r12 = r3
            r16 = r18
            r12.<init>(r13, r14, r15, r16)     // Catch:{ all -> 0x038b }
            goto L_0x035b
        L_0x037c:
            com.google.android.gms.internal.zzcil r0 = r20.zzayj()     // Catch:{ all -> 0x038b }
            r0.setTransactionSuccessful()     // Catch:{ all -> 0x038b }
            com.google.android.gms.internal.zzcil r0 = r20.zzayj()
            r0.endTransaction()
            return
        L_0x038b:
            r0 = move-exception
            com.google.android.gms.internal.zzcil r2 = r20.zzayj()
            r2.endTransaction()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzckj.zzf(com.google.android.gms.internal.zzcif):void");
    }

    /* access modifiers changed from: package-private */
    public final void zzj(Runnable runnable) {
        zzayo().zzwj();
        if (this.zzjou == null) {
            this.zzjou = new ArrayList();
        }
        this.zzjou.add(runnable);
    }

    public final String zzkf(String str) {
        try {
            return (String) zzayo().zzc(new zzckl(this, str)).get(NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            zzayp().zzbau().zze("Failed to get app instance id. appId", zzcjj.zzjs(str), e);
            return null;
        }
    }

    public final zze zzxx() {
        return this.zzdir;
    }

    /* access modifiers changed from: package-private */
    public final void zzyk() {
        if (!this.initialized) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
    }
}
