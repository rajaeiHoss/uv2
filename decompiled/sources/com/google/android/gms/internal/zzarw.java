package com.google.android.gms.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.google.android.gms.analytics.zza;
import com.google.android.gms.analytics.zzg;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

final class zzarw extends zzari {
    private boolean mStarted;
    private final zzart zzeab;
    private final zzatf zzeac;
    private final zzate zzead;
    private final zzaro zzeae;
    private long zzeaf = Long.MIN_VALUE;
    private final zzasn zzeag;
    private final zzasn zzeah;
    private final zzatp zzeai;
    private long zzeaj;
    private boolean zzeak;

    protected zzarw(zzark zzark, zzarm zzarm) {
        super(zzark);
        zzbq.checkNotNull(zzarm);
        this.zzead = new zzate(zzark);
        this.zzeab = new zzart(zzark);
        this.zzeac = new zzatf(zzark);
        this.zzeae = new zzaro(zzark);
        this.zzeai = new zzatp(zzxx());
        this.zzeag = new zzarx(this, zzark);
        this.zzeah = new zzary(this, zzark);
    }

    private final void zza(zzarn zzarn, zzaqm zzaqm) {
        zzbq.checkNotNull(zzarn);
        zzbq.checkNotNull(zzaqm);
        zza zza = new zza(zzxw());
        zza.zzdk(zzarn.zzys());
        zza.enableAdvertisingIdCollection(zzarn.zzyt());
        zzg zzvs = zza.zzvs();
        zzaqu zzaqu = (zzaqu) zzvs.zzb(zzaqu.class);
        zzaqu.zzdv(DataPacketExtension.ELEMENT_NAME);
        zzaqu.zzan(true);
        zzvs.zza((zzi) zzaqm);
        zzaqp zzaqp = (zzaqp) zzvs.zzb(zzaqp.class);
        zzaql zzaql = (zzaql) zzvs.zzb(zzaql.class);
        for (Map.Entry next : zzarn.zzjq().entrySet()) {
            String str = (String) next.getKey();
            String str2 = (String) next.getValue();
            if ("an".equals(str)) {
                zzaql.setAppName(str2);
            } else if ("av".equals(str)) {
                zzaql.setAppVersion(str2);
            } else if ("aid".equals(str)) {
                zzaql.setAppId(str2);
            } else if ("aiid".equals(str)) {
                zzaql.setAppInstallerId(str2);
            } else if ("uid".equals(str)) {
                zzaqu.setUserId(str2);
            } else {
                zzaqp.set(str, str2);
            }
        }
        zzb("Sending installation campaign to", zzarn.zzys(), zzaqm);
        zzvs.zzl(zzyf().zzaba());
        zzvs.zzwa();
    }

    private final boolean zzeh(String str) {
        return zzbih.zzdd(getContext()).checkCallingOrSelfPermission(str) == 0;
    }

    private final long zzza() {
        zzk.zzwj();
        zzyk();
        try {
            return this.zzeab.zzza();
        } catch (SQLiteException e) {
            zze("Failed to get min/max hit times from local store", e);
            return 0;
        }
    }

    /* access modifiers changed from: private */
    public final void zzzf() {
        zzb((zzasr) new zzasa(this));
    }

    /* access modifiers changed from: private */
    public final void zzzg() {
        try {
            this.zzeab.zzyz();
            zzzk();
        } catch (SQLiteException e) {
            zzd("Failed to delete stale hits", e);
        }
        this.zzeah.zzs(86400000);
    }

    private final void zzzh() {
        if (!this.zzeak && zzasl.zzzv() && !this.zzeae.isConnected()) {
            if (this.zzeai.zzu(zzast.zzecz.get().longValue())) {
                this.zzeai.start();
                zzea("Connecting to service");
                if (this.zzeae.connect()) {
                    zzea("Connected to service");
                    this.zzeai.clear();
                    onServiceConnected();
                }
            }
        }
    }

    private final boolean zzzi() {
        zzk.zzwj();
        zzyk();
        zzea("Dispatching a batch of local hits");
        boolean z = !this.zzeae.isConnected();
        boolean z2 = !this.zzeac.zzaax();
        if (!z || !z2) {
            long max = (long) Math.max(zzasl.zzzz(), zzasl.zzaaa());
            ArrayList arrayList = new ArrayList();
            long j = 0;
            while (true) {
                this.zzeab.beginTransaction();
                arrayList.clear();
                try {
                    List<zzasy> zzo = this.zzeab.zzo(max);
                    if (zzo.isEmpty()) {
                        zzea("Store is empty, nothing to dispatch");
                        zzzm();
                        try {
                            this.zzeab.setTransactionSuccessful();
                            this.zzeab.endTransaction();
                            return false;
                        } catch (SQLiteException e) {
                            zze("Failed to commit local dispatch transaction", e);
                            zzzm();
                            return false;
                        }
                    } else {
                        zza("Hits loaded from store. count", Integer.valueOf(zzo.size()));
                        for (zzasy zzaam : zzo) {
                            if (zzaam.zzaam() == j) {
                                zzd("Database contains successfully uploaded hit", Long.valueOf(j), Integer.valueOf(zzo.size()));
                                zzzm();
                                try {
                                    this.zzeab.setTransactionSuccessful();
                                    this.zzeab.endTransaction();
                                    return false;
                                } catch (SQLiteException e2) {
                                    zze("Failed to commit local dispatch transaction", e2);
                                    zzzm();
                                    return false;
                                }
                            }
                        }
                        if (this.zzeae.isConnected()) {
                            zzea("Service connected, sending hits to the service");
                            while (!zzo.isEmpty()) {
                                zzasy zzasy = zzo.get(0);
                                if (this.zzeae.zzb(zzasy)) {
                                    j = Math.max(j, zzasy.zzaam());
                                    zzo.remove(zzasy);
                                    zzb("Hit sent do device AnalyticsService for delivery", zzasy);
                                    try {
                                        this.zzeab.zzp(zzasy.zzaam());
                                        arrayList.add(Long.valueOf(zzasy.zzaam()));
                                    } catch (SQLiteException e3) {
                                        zze("Failed to remove hit that was send for delivery", e3);
                                        zzzm();
                                        try {
                                            this.zzeab.setTransactionSuccessful();
                                            this.zzeab.endTransaction();
                                            return false;
                                        } catch (SQLiteException e4) {
                                            zze("Failed to commit local dispatch transaction", e4);
                                            zzzm();
                                            return false;
                                        }
                                    } catch (Throwable th) {
                                        try {
                                            this.zzeab.setTransactionSuccessful();
                                            this.zzeab.endTransaction();
                                            throw th;
                                        } catch (SQLiteException e5) {
                                            zze("Failed to commit local dispatch transaction", e5);
                                            zzzm();
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                        if (this.zzeac.zzaax()) {
                            List<Long> zzu = this.zzeac.zzu(zzo);
                            for (Long longValue : zzu) {
                                j = Math.max(j, longValue.longValue());
                            }
                            try {
                                this.zzeab.zzs(zzu);
                                arrayList.addAll(zzu);
                            } catch (SQLiteException e6) {
                                zze("Failed to remove successfully uploaded hits", e6);
                                zzzm();
                                try {
                                    this.zzeab.setTransactionSuccessful();
                                    this.zzeab.endTransaction();
                                    return false;
                                } catch (SQLiteException e7) {
                                    zze("Failed to commit local dispatch transaction", e7);
                                    zzzm();
                                    return false;
                                }
                            }
                        }
                        if (arrayList.isEmpty()) {
                            try {
                                this.zzeab.setTransactionSuccessful();
                                this.zzeab.endTransaction();
                                return false;
                            } catch (SQLiteException e8) {
                                zze("Failed to commit local dispatch transaction", e8);
                                zzzm();
                                return false;
                            }
                        } else {
                            try {
                                this.zzeab.setTransactionSuccessful();
                                this.zzeab.endTransaction();
                            } catch (SQLiteException e9) {
                                zze("Failed to commit local dispatch transaction", e9);
                                zzzm();
                                return false;
                            }
                        }
                    }
                } catch (SQLiteException e10) {
                    zzd("Failed to read hits from persisted store", e10);
                    zzzm();
                    try {
                        this.zzeab.setTransactionSuccessful();
                        this.zzeab.endTransaction();
                        return false;
                    } catch (SQLiteException e11) {
                        zze("Failed to commit local dispatch transaction", e11);
                        zzzm();
                        return false;
                    }
                }
            }
        } else {
            zzea("No network or service available. Will retry later");
            return false;
        }
    }

    private final void zzzl() {
        zzasq zzyd = zzyd();
        if (zzyd.zzaaj() && !zzyd.zzea()) {
            long zzza = zzza();
            if (zzza != 0 && Math.abs(zzxx().currentTimeMillis() - zzza) <= zzast.zzeby.get().longValue()) {
                zza("Dispatch alarm scheduled (ms)", Long.valueOf(zzasl.zzzy()));
                zzyd.schedule();
            }
        }
    }

    private final void zzzm() {
        if (this.zzeag.zzea()) {
            zzea("All hits dispatched or no network/service. Going to power save mode");
        }
        this.zzeag.cancel();
        zzasq zzyd = zzyd();
        if (zzyd.zzea()) {
            zzyd.cancel();
        }
    }

    private final long zzzn() {
        long j = this.zzeaf;
        if (j != Long.MIN_VALUE) {
            return j;
        }
        long longValue = zzast.zzebt.get().longValue();
        zzatu zzye = zzye();
        zzye.zzyk();
        if (!zzye.zzeez) {
            return longValue;
        }
        zzatu zzye2 = zzye();
        zzye2.zzyk();
        return ((long) zzye2.zzedg) * 1000;
    }

    private final void zzzo() {
        zzyk();
        zzk.zzwj();
        this.zzeak = true;
        this.zzeae.disconnect();
        zzzk();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0044 A[LOOP:1: B:15:0x0044->B:23:?, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0040 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onServiceConnected() {
        /*
            r5 = this;
            com.google.android.gms.analytics.zzk.zzwj()
            com.google.android.gms.analytics.zzk.zzwj()
            r5.zzyk()
            boolean r0 = com.google.android.gms.internal.zzasl.zzzv()
            if (r0 != 0) goto L_0x0014
            java.lang.String r0 = "Service client disabled. Can't dispatch local hits to device AnalyticsService"
            r5.zzed(r0)
        L_0x0014:
            com.google.android.gms.internal.zzaro r0 = r5.zzeae
            boolean r0 = r0.isConnected()
            if (r0 != 0) goto L_0x0022
            java.lang.String r0 = "Service not connected"
            r5.zzea(r0)
            return
        L_0x0022:
            com.google.android.gms.internal.zzart r0 = r5.zzeab
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x007d
            java.lang.String r0 = "Dispatching local hits to device AnalyticsService"
            r5.zzea(r0)
        L_0x002f:
            com.google.android.gms.internal.zzart r0 = r5.zzeab     // Catch:{ SQLiteException -> 0x0074 }
            int r1 = com.google.android.gms.internal.zzasl.zzzz()     // Catch:{ SQLiteException -> 0x0074 }
            long r1 = (long) r1     // Catch:{ SQLiteException -> 0x0074 }
            java.util.List r0 = r0.zzo(r1)     // Catch:{ SQLiteException -> 0x0074 }
            boolean r1 = r0.isEmpty()     // Catch:{ SQLiteException -> 0x0074 }
            if (r1 == 0) goto L_0x0044
            r5.zzzk()     // Catch:{ SQLiteException -> 0x0074 }
            return
        L_0x0044:
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x002f
            r1 = 0
            java.lang.Object r1 = r0.get(r1)
            com.google.android.gms.internal.zzasy r1 = (com.google.android.gms.internal.zzasy) r1
            com.google.android.gms.internal.zzaro r2 = r5.zzeae
            boolean r2 = r2.zzb((com.google.android.gms.internal.zzasy) r1)
            if (r2 != 0) goto L_0x005d
            r5.zzzk()
            return
        L_0x005d:
            r0.remove(r1)
            com.google.android.gms.internal.zzart r2 = r5.zzeab     // Catch:{ SQLiteException -> 0x006a }
            long r3 = r1.zzaam()     // Catch:{ SQLiteException -> 0x006a }
            r2.zzp(r3)     // Catch:{ SQLiteException -> 0x006a }
            goto L_0x0044
        L_0x006a:
            r0 = move-exception
            java.lang.String r1 = "Failed to remove hit that was send for delivery"
            r5.zze(r1, r0)
            r5.zzzm()
            return
        L_0x0074:
            r0 = move-exception
            java.lang.String r1 = "Failed to read hits from store"
            r5.zze(r1, r0)
            r5.zzzm()
        L_0x007d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzarw.onServiceConnected():void");
    }

    /* access modifiers changed from: package-private */
    public final void start() {
        zzyk();
        zzbq.zza(!this.mStarted, (Object) "Analytics backend already started");
        this.mStarted = true;
        zzya().zzd(new zzarz(this));
    }

    public final long zza(zzarn zzarn, boolean z) {
        zzbq.checkNotNull(zzarn);
        zzyk();
        zzk.zzwj();
        try {
            this.zzeab.beginTransaction();
            zzart zzart = this.zzeab;
            long zzyr = zzarn.zzyr();
            String zzxe = zzarn.zzxe();
            zzbq.zzgv(zzxe);
            zzart.zzyk();
            zzk.zzwj();
            int i = 0;
            int delete = zzart.getWritableDatabase().delete("properties", "app_uid=? AND cid<>?", new String[]{String.valueOf(zzyr), zzxe});
            if (delete > 0) {
                zzart.zza("Deleted property records", Integer.valueOf(delete));
            }
            long zza = this.zzeab.zza(zzarn.zzyr(), zzarn.zzxe(), zzarn.zzys());
            zzarn.zzm(1 + zza);
            zzart zzart2 = this.zzeab;
            zzbq.checkNotNull(zzarn);
            zzart2.zzyk();
            zzk.zzwj();
            SQLiteDatabase writableDatabase = zzart2.getWritableDatabase();
            Map<String, String> zzjq = zzarn.zzjq();
            zzbq.checkNotNull(zzjq);
            Uri.Builder builder = new Uri.Builder();
            for (Map.Entry next : zzjq.entrySet()) {
                builder.appendQueryParameter((String) next.getKey(), (String) next.getValue());
            }
            String encodedQuery = builder.build().getEncodedQuery();
            if (encodedQuery == null) {
                encodedQuery = "";
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_uid", Long.valueOf(zzarn.zzyr()));
            contentValues.put("cid", zzarn.zzxe());
            contentValues.put("tid", zzarn.zzys());
            if (zzarn.zzyt()) {
                i = 1;
            }
            contentValues.put("adid", Integer.valueOf(i));
            contentValues.put("hits_count", Long.valueOf(zzarn.zzyu()));
            contentValues.put("params", encodedQuery);
            try {
                if (writableDatabase.insertWithOnConflict("properties", (String) null, contentValues, 5) == -1) {
                    zzart2.zzee("Failed to insert/update a property (got -1)");
                }
            } catch (SQLiteException e) {
                zzart2.zze("Error storing a property", e);
            }
            this.zzeab.setTransactionSuccessful();
            try {
                this.zzeab.endTransaction();
            } catch (SQLiteException e2) {
                zze("Failed to end transaction", e2);
            }
            return zza;
        } catch (SQLiteException e3) {
            zze("Failed to update Analytics property", e3);
            try {
                this.zzeab.endTransaction();
            } catch (SQLiteException e4) {
                zze("Failed to end transaction", e4);
            }
            return -1;
        } catch (RuntimeException e5) {
            try {
                this.zzeab.endTransaction();
            } catch (SQLiteException e6) {
                zze("Failed to end transaction", e6);
            }
            throw e5;
        } catch (Error e7) {
            try {
                this.zzeab.endTransaction();
            } catch (SQLiteException e8) {
                zze("Failed to end transaction", e8);
            }
            throw e7;
        }
    }

    public final void zza(zzasy zzasy) {
        Pair<String, Long> zzabh;
        zzbq.checkNotNull(zzasy);
        zzk.zzwj();
        zzyk();
        if (this.zzeak) {
            zzeb("Hit delivery not possible. Missing network permissions. See http://goo.gl/8Rd3yj for instructions");
        } else {
            zza("Delivering hit", zzasy);
        }
        if (TextUtils.isEmpty(zzasy.zzaar()) && (zzabh = zzyf().zzabf().zzabh()) != null) {
            String str = (String) zzabh.first;
            String valueOf = String.valueOf((Long) zzabh.second);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(str).length());
            sb.append(valueOf);
            sb.append(":");
            sb.append(str);
            String sb2 = sb.toString();
            HashMap hashMap = new HashMap(zzasy.zzjq());
            hashMap.put("_m", sb2);
            zzasy = new zzasy(this, hashMap, zzasy.zzaan(), zzasy.zzaap(), zzasy.zzaam(), zzasy.zzaal(), zzasy.zzaao());
        }
        zzzh();
        if (this.zzeae.zzb(zzasy)) {
            zzeb("Hit sent to the device AnalyticsService for delivery");
            return;
        }
        try {
            this.zzeab.zzc(zzasy);
            zzzk();
        } catch (SQLiteException e) {
            zze("Delivery failed to save hit to a database", e);
            zzxy().zza(zzasy, "deliver: failed to insert hit to database");
        }
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzarn zzarn) {
        zzk.zzwj();
        zzb("Sending first hit to property", zzarn.zzys());
        if (!zzyf().zzabb().zzu(zzasl.zzaaf())) {
            String zzabe = zzyf().zzabe();
            if (!TextUtils.isEmpty(zzabe)) {
                zzaqm zza = zzatt.zza(zzxy(), zzabe);
                zzb("Found relevant installation campaign", zza);
                zza(zzarn, zza);
            }
        }
    }

    public final void zzb(zzasr zzasr) {
        long j = this.zzeaj;
        zzk.zzwj();
        zzyk();
        long zzabc = zzyf().zzabc();
        zzb("Dispatching local hits. Elapsed time since last dispatch (ms)", Long.valueOf(zzabc != 0 ? Math.abs(zzxx().currentTimeMillis() - zzabc) : -1));
        zzzh();
        try {
            zzzi();
            zzyf().zzabd();
            zzzk();
            if (zzasr != null) {
                zzasr.zze((Throwable) null);
            }
            if (this.zzeaj != j) {
                this.zzead.zzaaw();
            }
        } catch (Throwable th) {
            zze("Local dispatch failed", th);
            zzyf().zzabd();
            zzzk();
            if (zzasr != null) {
                zzasr.zze(th);
            }
        }
    }

    public final void zzei(String str) {
        zzbq.zzgv(str);
        zzk.zzwj();
        zzaqm zza = zzatt.zza(zzxy(), str);
        if (zza == null) {
            zzd("Parsing failed. Ignoring invalid campaign data", str);
            return;
        }
        String zzabe = zzyf().zzabe();
        if (str.equals(zzabe)) {
            zzed("Ignoring duplicate install campaign");
        } else if (!TextUtils.isEmpty(zzabe)) {
            zzd("Ignoring multiple install campaigns. original, new", zzabe, str);
        } else {
            zzyf().zzel(str);
            if (zzyf().zzabb().zzu(zzasl.zzaaf())) {
                zzd("Campaign received too late, ignoring", zza);
                return;
            }
            zzb("Received installation campaign", zza);
            for (zzarn zza2 : this.zzeab.zzq(0)) {
                zza(zza2, zza);
            }
        }
    }

    public final void zzr(long j) {
        zzk.zzwj();
        zzyk();
        if (j < 0) {
            j = 0;
        }
        this.zzeaf = j;
        zzzk();
    }

    /* access modifiers changed from: protected */
    public final void zzwk() {
        this.zzeab.initialize();
        this.zzeac.initialize();
        this.zzeae.initialize();
    }

    public final void zzxr() {
        zzk.zzwj();
        zzyk();
        zzea("Delete all hits from local store");
        try {
            zzart zzart = this.zzeab;
            zzk.zzwj();
            zzart.zzyk();
            zzart.getWritableDatabase().delete("hits2", (String) null, (String[]) null);
            zzart zzart2 = this.zzeab;
            zzk.zzwj();
            zzart2.zzyk();
            zzart2.getWritableDatabase().delete("properties", (String) null, (String[]) null);
            zzzk();
        } catch (SQLiteException e) {
            zzd("Failed to delete hits from store", e);
        }
        zzzh();
        if (this.zzeae.zzyv()) {
            zzea("Device service unavailable. Can't clear hits stored on the device service.");
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzxv() {
        zzk.zzwj();
        this.zzeaj = zzxx().currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    public final void zzze() {
        zzyk();
        zzk.zzwj();
        Context context = zzxw().getContext();
        if (!zzatk.zzbj(context)) {
            zzed("AnalyticsReceiver is not registered or is disabled. Register the receiver for reliable dispatching on non-Google Play devices. See http://goo.gl/8Rd3yj for instructions.");
        } else if (!zzatl.zzbn(context)) {
            zzee("AnalyticsService is not registered or is disabled. Analytics service at risk of not starting. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!CampaignTrackingReceiver.zzbj(context)) {
            zzed("CampaignTrackingReceiver is not registered, not exported or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.");
        }
        zzyf().zzaba();
        if (!zzeh("android.permission.ACCESS_NETWORK_STATE")) {
            zzee("Missing required android.permission.ACCESS_NETWORK_STATE. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            zzzo();
        }
        if (!zzeh("android.permission.INTERNET")) {
            zzee("Missing required android.permission.INTERNET. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            zzzo();
        }
        if (zzatl.zzbn(getContext())) {
            zzea("AnalyticsService registered in the app manifest and enabled");
        } else {
            zzed("AnalyticsService not registered in the app manifest. Hits might not be delivered reliably. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!this.zzeak && !this.zzeab.isEmpty()) {
            zzzh();
        }
        zzzk();
    }

    public final void zzzj() {
        zzk.zzwj();
        zzyk();
        zzeb("Sync dispatching local hits");
        long j = this.zzeaj;
        zzzh();
        try {
            zzzi();
            zzyf().zzabd();
            zzzk();
            if (this.zzeaj != j) {
                this.zzead.zzaaw();
            }
        } catch (Throwable th) {
            zze("Sync local dispatch failed", th);
            zzzk();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0073, code lost:
        if (r4 > 0) goto L_0x007e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzzk() {
        /*
            r8 = this;
            com.google.android.gms.analytics.zzk.zzwj()
            r8.zzyk()
            boolean r0 = r8.zzeak
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0017
            long r4 = r8.zzzn()
            int r0 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r0 <= 0) goto L_0x0017
            r0 = 1
            goto L_0x0018
        L_0x0017:
            r0 = 0
        L_0x0018:
            if (r0 != 0) goto L_0x0023
            com.google.android.gms.internal.zzate r0 = r8.zzead
            r0.unregister()
            r8.zzzm()
            return
        L_0x0023:
            com.google.android.gms.internal.zzart r0 = r8.zzeab
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0034
            com.google.android.gms.internal.zzate r0 = r8.zzead
            r0.unregister()
            r8.zzzm()
            return
        L_0x0034:
            com.google.android.gms.internal.zzasu<java.lang.Boolean> r0 = com.google.android.gms.internal.zzast.zzecu
            java.lang.Object r0 = r0.get()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L_0x004d
            com.google.android.gms.internal.zzate r0 = r8.zzead
            r0.zzaau()
            com.google.android.gms.internal.zzate r0 = r8.zzead
            boolean r1 = r0.isConnected()
        L_0x004d:
            if (r1 == 0) goto L_0x00a8
            r8.zzzl()
            long r0 = r8.zzzn()
            com.google.android.gms.internal.zzath r4 = r8.zzyf()
            long r4 = r4.zzabc()
            int r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r6 == 0) goto L_0x0076
            com.google.android.gms.common.util.zze r6 = r8.zzxx()
            long r6 = r6.currentTimeMillis()
            long r6 = r6 - r4
            long r4 = java.lang.Math.abs(r6)
            long r4 = r0 - r4
            int r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r6 <= 0) goto L_0x0076
            goto L_0x007e
        L_0x0076:
            long r2 = com.google.android.gms.internal.zzasl.zzzx()
            long r4 = java.lang.Math.min(r2, r0)
        L_0x007e:
            java.lang.Long r0 = java.lang.Long.valueOf(r4)
            java.lang.String r1 = "Dispatch scheduled (ms)"
            r8.zza(r1, r0)
            com.google.android.gms.internal.zzasn r0 = r8.zzeag
            boolean r0 = r0.zzea()
            if (r0 == 0) goto L_0x00a2
            r0 = 1
            com.google.android.gms.internal.zzasn r2 = r8.zzeag
            long r2 = r2.zzaag()
            long r4 = r4 + r2
            long r0 = java.lang.Math.max(r0, r4)
            com.google.android.gms.internal.zzasn r2 = r8.zzeag
            r2.zzt(r0)
            return
        L_0x00a2:
            com.google.android.gms.internal.zzasn r0 = r8.zzeag
            r0.zzs(r4)
            return
        L_0x00a8:
            r8.zzzm()
            r8.zzzl()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzarw.zzzk():void");
    }
}
