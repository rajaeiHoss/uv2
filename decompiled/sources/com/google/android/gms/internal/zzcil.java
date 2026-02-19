package com.google.android.gms.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;
import org.jivesoftware.smackx.workgroup.MetaData;

final class zzcil extends zzcli {
    /* access modifiers changed from: private */
    public static final String[] zzjgw = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzjgx = {"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    /* access modifiers changed from: private */
    public static final String[] zzjgy = {"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzjgz = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzjha = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzjhb = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private final zzcio zzjhc = new zzcio(this, getContext(), "google_app_measurement.db");
    /* access modifiers changed from: private */
    public final zzcni zzjhd = new zzcni(zzxx());

    zzcil(zzckj zzckj) {
        super(zzckj);
    }

    private final long zza(String str, String[] strArr, long j) {
        Cursor cursor = null;
        try {
            Cursor rawQuery = getWritableDatabase().rawQuery(str, strArr);
            if (rawQuery.moveToFirst()) {
                long j2 = rawQuery.getLong(0);
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return j2;
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return j;
        } catch (SQLiteException e) {
            zzayp().zzbau().zze("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private final Object zza(Cursor cursor, int i) {
        int type = cursor.getType(i);
        if (type == 0) {
            zzayp().zzbau().log("Loaded invalid null value from database");
            return null;
        } else if (type == 1) {
            return Long.valueOf(cursor.getLong(i));
        } else {
            if (type == 2) {
                return Double.valueOf(cursor.getDouble(i));
            }
            if (type == 3) {
                return cursor.getString(i);
            }
            if (type != 4) {
                zzayp().zzbau().zzj("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                return null;
            }
            zzayp().zzbau().log("Loaded invalid blob type value, ignoring it");
            return null;
        }
    }

    private static void zza(ContentValues contentValues, String str, Object obj) {
        zzbq.zzgv(str);
        zzbq.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else if (obj instanceof Double) {
            contentValues.put(str, (Double) obj);
        } else {
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    static void zza(zzcjj zzcjj, SQLiteDatabase sQLiteDatabase) {
        if (zzcjj != null) {
            File file = new File(sQLiteDatabase.getPath());
            if (!file.setReadable(false, false)) {
                zzcjj.zzbaw().log("Failed to turn off database read permission");
            }
            if (!file.setWritable(false, false)) {
                zzcjj.zzbaw().log("Failed to turn off database write permission");
            }
            if (!file.setReadable(true, true)) {
                zzcjj.zzbaw().log("Failed to turn on database read permission for owner");
            }
            if (!file.setWritable(true, true)) {
                zzcjj.zzbaw().log("Failed to turn on database write permission for owner");
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Monitor must not be null");
    }

    static void zza(zzcjj zzcjj, SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String[] strArr) throws SQLiteException {
        if (zzcjj != null) {
            if (!zza(zzcjj, sQLiteDatabase, str)) {
                sQLiteDatabase.execSQL(str2);
            }
            try {
                zza(zzcjj, sQLiteDatabase, str, str3, strArr);
            } catch (SQLiteException e) {
                zzcjj.zzbau().zzj("Failed to verify columns on table that was just created", str);
                throw e;
            }
        } else {
            throw new IllegalArgumentException("Monitor must not be null");
        }
    }

    private static void zza(zzcjj zzcjj, SQLiteDatabase sQLiteDatabase, String str, String str2, String[] strArr) throws SQLiteException {
        if (zzcjj != null) {
            Set<String> zzb = zzb(sQLiteDatabase, str);
            String[] split = str2.split(",");
            int length = split.length;
            int i = 0;
            while (i < length) {
                String str3 = split[i];
                if (zzb.remove(str3)) {
                    i++;
                } else {
                    StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 35 + String.valueOf(str3).length());
                    sb.append("Table ");
                    sb.append(str);
                    sb.append(" is missing required column: ");
                    sb.append(str3);
                    throw new SQLiteException(sb.toString());
                }
            }
            if (strArr != null) {
                for (int i2 = 0; i2 < strArr.length; i2 += 2) {
                    if (!zzb.remove(strArr[i2])) {
                        sQLiteDatabase.execSQL(strArr[i2 + 1]);
                    }
                }
            }
            if (!zzb.isEmpty()) {
                zzcjj.zzbaw().zze("Table has extra columns. table, columns", str, TextUtils.join(", ", zzb));
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Monitor must not be null");
    }

    private static boolean zza(zzcjj zzcjj, SQLiteDatabase sQLiteDatabase, String str) {
        if (zzcjj != null) {
            Cursor cursor = null;
            try {
                SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
                Cursor query = sQLiteDatabase2.query("SQLITE_MASTER", new String[]{"name"}, "name=?", new String[]{str}, (String) null, (String) null, (String) null);
                boolean moveToFirst = query.moveToFirst();
                if (query != null) {
                    query.close();
                }
                return moveToFirst;
            } catch (SQLiteException e) {
                zzcjj.zzbaw().zze("Error querying for table", str, e);
                if (cursor != null) {
                    cursor.close();
                }
                return false;
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } else {
            throw new IllegalArgumentException("Monitor must not be null");
        }
    }

    private final boolean zza(String str, int i, zzcns zzcns) {
        zzyk();
        zzwj();
        zzbq.zzgv(str);
        zzbq.checkNotNull(zzcns);
        if (TextUtils.isEmpty(zzcns.zzjsy)) {
            zzayp().zzbaw().zzd("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zzcjj.zzjs(str), Integer.valueOf(i), String.valueOf(zzcns.zzjsx));
            return false;
        }
        try {
            int zzhs = zzcns.zzhs();
            byte[] bArr = new byte[zzhs];
            zzflk zzp = zzflk.zzp(bArr, 0, zzhs);
            zzcns.zza(zzp);
            zzp.zzcyx();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", zzcns.zzjsx);
            contentValues.put("event_name", zzcns.zzjsy);
            contentValues.put(DataPacketExtension.ELEMENT_NAME, bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("event_filters", (String) null, contentValues, 5) != -1) {
                    return true;
                }
                zzayp().zzbau().zzj("Failed to insert event filter (got -1). appId", zzcjj.zzjs(str));
                return true;
            } catch (SQLiteException e) {
                zzayp().zzbau().zze("Error storing event filter. appId", zzcjj.zzjs(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzayp().zzbau().zze("Configuration loss. Failed to serialize event filter. appId", zzcjj.zzjs(str), e2);
            return false;
        }
    }

    private final boolean zza(String str, int i, zzcnv zzcnv) {
        zzyk();
        zzwj();
        zzbq.zzgv(str);
        zzbq.checkNotNull(zzcnv);
        if (TextUtils.isEmpty(zzcnv.zzjtn)) {
            zzayp().zzbaw().zzd("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zzcjj.zzjs(str), Integer.valueOf(i), String.valueOf(zzcnv.zzjsx));
            return false;
        }
        try {
            int zzhs = zzcnv.zzhs();
            byte[] bArr = new byte[zzhs];
            zzflk zzp = zzflk.zzp(bArr, 0, zzhs);
            zzcnv.zza(zzp);
            zzp.zzcyx();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", zzcnv.zzjsx);
            contentValues.put("property_name", zzcnv.zzjtn);
            contentValues.put(DataPacketExtension.ELEMENT_NAME, bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("property_filters", (String) null, contentValues, 5) != -1) {
                    return true;
                }
                zzayp().zzbau().zzj("Failed to insert property filter (got -1). appId", zzcjj.zzjs(str));
                return false;
            } catch (SQLiteException e) {
                zzayp().zzbau().zze("Error storing property filter. appId", zzcjj.zzjs(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzayp().zzbau().zze("Configuration loss. Failed to serialize property filter. appId", zzcjj.zzjs(str), e2);
            return false;
        }
    }

    private static Set<String> zzb(SQLiteDatabase sQLiteDatabase, String str) {
        HashSet hashSet = new HashSet();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 22);
        sb.append("SELECT * FROM ");
        sb.append(str);
        sb.append(" LIMIT 0");
        Cursor rawQuery = sQLiteDatabase.rawQuery(sb.toString(), (String[]) null);
        try {
            Collections.addAll(hashSet, rawQuery.getColumnNames());
            return hashSet;
        } finally {
            rawQuery.close();
        }
    }

    private final boolean zzbae() {
        return getContext().getDatabasePath("google_app_measurement.db").exists();
    }

    private final long zzc(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().rawQuery(str, strArr);
            if (cursor.moveToFirst()) {
                long j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
                return j;
            }
            throw new SQLiteException("Database returned empty set");
        } catch (SQLiteException e) {
            zzayp().zzbau().zze("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private final boolean zze(String str, List<Integer> list) {
        zzbq.zzgv(str);
        zzyk();
        zzwj();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        try {
            long zzc = zzc("select count(1) from audience_filter_values where app_id=?", new String[]{str});
            int max = Math.max(0, Math.min(2000, zzayr().zzb(str, zzciz.zzjjs)));
            if (zzc <= ((long) max)) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Integer num = list.get(i);
                if (num == null || !(num instanceof Integer)) {
                    return false;
                }
                arrayList.add(Integer.toString(num.intValue()));
            }
            String join = TextUtils.join(",", arrayList);
            StringBuilder sb = new StringBuilder(String.valueOf(join).length() + 2);
            sb.append("(");
            sb.append(join);
            sb.append(")");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder(String.valueOf(sb2).length() + 140);
            sb3.append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ");
            sb3.append(sb2);
            sb3.append(" order by rowid desc limit -1 offset ?)");
            return writableDatabase.delete("audience_filter_values", sb3.toString(), new String[]{str, Integer.toString(max)}) > 0;
        } catch (SQLiteException e) {
            zzayp().zzbau().zze("Database error querying filters. appId", zzcjj.zzjs(str), e);
            return false;
        }
    }

    public final void beginTransaction() {
        zzyk();
        getWritableDatabase().beginTransaction();
    }

    public final void endTransaction() {
        zzyk();
        getWritableDatabase().endTransaction();
    }

    /* access modifiers changed from: package-private */
    public final SQLiteDatabase getWritableDatabase() {
        zzwj();
        try {
            return this.zzjhc.getWritableDatabase();
        } catch (SQLiteException e) {
            zzayp().zzbaw().zzj("Error opening database", e);
            throw e;
        }
    }

    public final void setTransactionSuccessful() {
        zzyk();
        getWritableDatabase().setTransactionSuccessful();
    }

    public final long zza(zzcoe zzcoe) throws IOException {
        long j;
        zzwj();
        zzyk();
        zzbq.checkNotNull(zzcoe);
        zzbq.zzgv(zzcoe.zzcm);
        try {
            int zzhs = zzcoe.zzhs();
            byte[] bArr = new byte[zzhs];
            zzflk zzp = zzflk.zzp(bArr, 0, zzhs);
            zzcoe.zza(zzp);
            zzp.zzcyx();
            zzcno zzayl = zzayl();
            zzbq.checkNotNull(bArr);
            zzayl.zzwj();
            MessageDigest zzeq = zzcno.zzeq("MD5");
            if (zzeq == null) {
                zzayl.zzayp().zzbau().log("Failed to get MD5");
                j = 0;
            } else {
                j = zzcno.zzt(zzeq.digest(bArr));
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzcoe.zzcm);
            contentValues.put("metadata_fingerprint", Long.valueOf(j));
            contentValues.put(MetaData.ELEMENT_NAME, bArr);
            try {
                getWritableDatabase().insertWithOnConflict("raw_events_metadata", (String) null, contentValues, 4);
                return j;
            } catch (SQLiteException e) {
                zzayp().zzbau().zze("Error storing raw event metadata. appId", zzcjj.zzjs(zzcoe.zzcm), e);
                throw e;
            }
        } catch (IOException e2) {
            zzayp().zzbau().zze("Data loss. Failed to serialize event metadata. appId", zzcjj.zzjs(zzcoe.zzcm), e2);
            throw e2;
        }
    }

    public final zzcim zza(long j, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        zzbq.zzgv(str);
        zzwj();
        zzyk();
        String[] strArr = {str};
        zzcim zzcim = new zzcim();
        Cursor cursor = null;
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            cursor = writableDatabase.query("apps", new String[]{"day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count"}, "app_id=?", new String[]{str}, (String) null, (String) null, (String) null);
            if (!cursor.moveToFirst()) {
                zzayp().zzbaw().zzj("Not updating daily counts, app is not known. appId", zzcjj.zzjs(str));
                if (cursor != null) {
                    cursor.close();
                }
                return zzcim;
            }
            if (cursor.getLong(0) == j) {
                zzcim.zzjhf = cursor.getLong(1);
                zzcim.zzjhe = cursor.getLong(2);
                zzcim.zzjhg = cursor.getLong(3);
                zzcim.zzjhh = cursor.getLong(4);
                zzcim.zzjhi = cursor.getLong(5);
            }
            if (z) {
                zzcim.zzjhf++;
            }
            if (z2) {
                zzcim.zzjhe++;
            }
            if (z3) {
                zzcim.zzjhg++;
            }
            if (z4) {
                zzcim.zzjhh++;
            }
            if (z5) {
                zzcim.zzjhi++;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("day", Long.valueOf(j));
            contentValues.put("daily_public_events_count", Long.valueOf(zzcim.zzjhe));
            contentValues.put("daily_events_count", Long.valueOf(zzcim.zzjhf));
            contentValues.put("daily_conversions_count", Long.valueOf(zzcim.zzjhg));
            contentValues.put("daily_error_events_count", Long.valueOf(zzcim.zzjhh));
            contentValues.put("daily_realtime_events_count", Long.valueOf(zzcim.zzjhi));
            writableDatabase.update("apps", contentValues, "app_id=?", strArr);
            if (cursor != null) {
                cursor.close();
            }
            return zzcim;
        } catch (SQLiteException e) {
            zzayp().zzbau().zze("Error updating daily counts. appId", zzcjj.zzjs(str), e);
            if (cursor != null) {
                cursor.close();
            }
            return zzcim;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public final void zza(zzcie zzcie) {
        zzbq.checkNotNull(zzcie);
        zzwj();
        zzyk();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzcie.getAppId());
        contentValues.put("app_instance_id", zzcie.getAppInstanceId());
        contentValues.put("gmp_app_id", zzcie.getGmpAppId());
        contentValues.put("resettable_device_id_hash", zzcie.zzayt());
        contentValues.put("last_bundle_index", Long.valueOf(zzcie.zzazc()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzcie.zzayv()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzcie.zzayw()));
        contentValues.put("app_version", zzcie.zzwo());
        contentValues.put("app_store", zzcie.zzayy());
        contentValues.put("gmp_version", Long.valueOf(zzcie.zzayz()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzcie.zzaza()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzcie.zzazb()));
        contentValues.put("day", Long.valueOf(zzcie.zzazg()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzcie.zzazh()));
        contentValues.put("daily_events_count", Long.valueOf(zzcie.zzazi()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzcie.zzazj()));
        contentValues.put("config_fetched_time", Long.valueOf(zzcie.zzazd()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzcie.zzaze()));
        contentValues.put("app_version_int", Long.valueOf(zzcie.zzayx()));
        contentValues.put("firebase_instance_id", zzcie.zzayu());
        contentValues.put("daily_error_events_count", Long.valueOf(zzcie.zzazl()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzcie.zzazk()));
        contentValues.put("health_monitor_sample", zzcie.zzazm());
        contentValues.put("android_id", Long.valueOf(zzcie.zzazo()));
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(zzcie.zzazp()));
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (((long) writableDatabase.update("apps", contentValues, "app_id = ?", new String[]{zzcie.getAppId()})) == 0 && writableDatabase.insertWithOnConflict("apps", (String) null, contentValues, 5) == -1) {
                zzayp().zzbau().zzj("Failed to insert/update app (got -1). appId", zzcjj.zzjs(zzcie.getAppId()));
            }
        } catch (SQLiteException e) {
            zzayp().zzbau().zze("Error storing app. appId", zzcjj.zzjs(zzcie.getAppId()), e);
        }
    }

    public final void zza(zzcit zzcit) {
        zzbq.checkNotNull(zzcit);
        zzwj();
        zzyk();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzcit.zzcm);
        contentValues.put("name", zzcit.name);
        contentValues.put("lifetime_count", Long.valueOf(zzcit.zzjhs));
        contentValues.put("current_bundle_count", Long.valueOf(zzcit.zzjht));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzcit.zzjhu));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzcit.zzjhv));
        contentValues.put("last_sampled_complex_event_id", zzcit.zzjhw);
        contentValues.put("last_sampling_rate", zzcit.zzjhx);
        contentValues.put("last_exempt_from_sampling", (zzcit.zzjhy == null || !zzcit.zzjhy.booleanValue()) ? null : 1L);
        try {
            if (getWritableDatabase().insertWithOnConflict("events", (String) null, contentValues, 5) == -1) {
                zzayp().zzbau().zzj("Failed to insert/update event aggregates (got -1). appId", zzcjj.zzjs(zzcit.zzcm));
            }
        } catch (SQLiteException e) {
            zzayp().zzbau().zze("Error storing event aggregates. appId", zzcjj.zzjs(zzcit.zzcm), e);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0094, code lost:
        r9.zze(r12, r13, r10);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(java.lang.String r17, com.google.android.gms.internal.zzcnr[] r18) {
        /*
            r16 = this;
            r1 = r16
            r0 = r17
            r2 = r18
            java.lang.String r3 = "app_id=? and audience_id=?"
            java.lang.String r4 = "event_filters"
            java.lang.String r5 = "app_id=?"
            java.lang.String r6 = "property_filters"
            r16.zzyk()
            r16.zzwj()
            com.google.android.gms.common.internal.zzbq.zzgv(r17)
            com.google.android.gms.common.internal.zzbq.checkNotNull(r18)
            android.database.sqlite.SQLiteDatabase r7 = r16.getWritableDatabase()
            r7.beginTransaction()
            r16.zzyk()     // Catch:{ all -> 0x0132 }
            r16.zzwj()     // Catch:{ all -> 0x0132 }
            com.google.android.gms.common.internal.zzbq.zzgv(r17)     // Catch:{ all -> 0x0132 }
            android.database.sqlite.SQLiteDatabase r8 = r16.getWritableDatabase()     // Catch:{ all -> 0x0132 }
            r9 = 1
            java.lang.String[] r10 = new java.lang.String[r9]     // Catch:{ all -> 0x0132 }
            r11 = 0
            r10[r11] = r0     // Catch:{ all -> 0x0132 }
            r8.delete(r6, r5, r10)     // Catch:{ all -> 0x0132 }
            java.lang.String[] r10 = new java.lang.String[r9]     // Catch:{ all -> 0x0132 }
            r10[r11] = r0     // Catch:{ all -> 0x0132 }
            r8.delete(r4, r5, r10)     // Catch:{ all -> 0x0132 }
            int r5 = r2.length     // Catch:{ all -> 0x0132 }
            r8 = 0
        L_0x0040:
            if (r8 >= r5) goto L_0x0116
            r10 = r2[r8]     // Catch:{ all -> 0x0132 }
            r16.zzyk()     // Catch:{ all -> 0x0132 }
            r16.zzwj()     // Catch:{ all -> 0x0132 }
            com.google.android.gms.common.internal.zzbq.zzgv(r17)     // Catch:{ all -> 0x0132 }
            com.google.android.gms.common.internal.zzbq.checkNotNull(r10)     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.zzcns[] r12 = r10.zzjsv     // Catch:{ all -> 0x0132 }
            com.google.android.gms.common.internal.zzbq.checkNotNull(r12)     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.zzcnv[] r12 = r10.zzjsu     // Catch:{ all -> 0x0132 }
            com.google.android.gms.common.internal.zzbq.checkNotNull(r12)     // Catch:{ all -> 0x0132 }
            java.lang.Integer r12 = r10.zzjst     // Catch:{ all -> 0x0132 }
            if (r12 != 0) goto L_0x0072
            com.google.android.gms.internal.zzcjj r10 = r16.zzayp()     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.zzcjl r10 = r10.zzbaw()     // Catch:{ all -> 0x0132 }
            java.lang.String r12 = "Audience with no ID. appId"
            java.lang.Object r13 = com.google.android.gms.internal.zzcjj.zzjs(r17)     // Catch:{ all -> 0x0132 }
            r10.zzj(r12, r13)     // Catch:{ all -> 0x0132 }
        L_0x006f:
            r13 = 1
            goto L_0x0111
        L_0x0072:
            java.lang.Integer r12 = r10.zzjst     // Catch:{ all -> 0x0132 }
            int r12 = r12.intValue()     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.zzcns[] r13 = r10.zzjsv     // Catch:{ all -> 0x0132 }
            int r14 = r13.length     // Catch:{ all -> 0x0132 }
            r15 = 0
        L_0x007c:
            if (r15 >= r14) goto L_0x009c
            r9 = r13[r15]     // Catch:{ all -> 0x0132 }
            java.lang.Integer r9 = r9.zzjsx     // Catch:{ all -> 0x0132 }
            if (r9 != 0) goto L_0x0098
            com.google.android.gms.internal.zzcjj r9 = r16.zzayp()     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.zzcjl r9 = r9.zzbaw()     // Catch:{ all -> 0x0132 }
            java.lang.String r12 = "Event filter with no ID. Audience definition ignored. appId, audienceId"
            java.lang.Object r13 = com.google.android.gms.internal.zzcjj.zzjs(r17)     // Catch:{ all -> 0x0132 }
            java.lang.Integer r10 = r10.zzjst     // Catch:{ all -> 0x0132 }
        L_0x0094:
            r9.zze(r12, r13, r10)     // Catch:{ all -> 0x0132 }
            goto L_0x006f
        L_0x0098:
            int r15 = r15 + 1
            r9 = 1
            goto L_0x007c
        L_0x009c:
            com.google.android.gms.internal.zzcnv[] r9 = r10.zzjsu     // Catch:{ all -> 0x0132 }
            int r13 = r9.length     // Catch:{ all -> 0x0132 }
            r14 = 0
        L_0x00a0:
            if (r14 >= r13) goto L_0x00bc
            r15 = r9[r14]     // Catch:{ all -> 0x0132 }
            java.lang.Integer r15 = r15.zzjsx     // Catch:{ all -> 0x0132 }
            if (r15 != 0) goto L_0x00b9
            com.google.android.gms.internal.zzcjj r9 = r16.zzayp()     // Catch:{ all -> 0x0132 }
            com.google.android.gms.internal.zzcjl r9 = r9.zzbaw()     // Catch:{ all -> 0x0132 }
            java.lang.String r12 = "Property filter with no ID. Audience definition ignored. appId, audienceId"
            java.lang.Object r13 = com.google.android.gms.internal.zzcjj.zzjs(r17)     // Catch:{ all -> 0x0132 }
            java.lang.Integer r10 = r10.zzjst     // Catch:{ all -> 0x0132 }
            goto L_0x0094
        L_0x00b9:
            int r14 = r14 + 1
            goto L_0x00a0
        L_0x00bc:
            com.google.android.gms.internal.zzcns[] r9 = r10.zzjsv     // Catch:{ all -> 0x0132 }
            int r13 = r9.length     // Catch:{ all -> 0x0132 }
            r14 = 0
        L_0x00c0:
            if (r14 >= r13) goto L_0x00cf
            r15 = r9[r14]     // Catch:{ all -> 0x0132 }
            boolean r15 = r1.zza((java.lang.String) r0, (int) r12, (com.google.android.gms.internal.zzcns) r15)     // Catch:{ all -> 0x0132 }
            if (r15 != 0) goto L_0x00cc
            r9 = 0
            goto L_0x00d0
        L_0x00cc:
            int r14 = r14 + 1
            goto L_0x00c0
        L_0x00cf:
            r9 = 1
        L_0x00d0:
            if (r9 == 0) goto L_0x00e5
            com.google.android.gms.internal.zzcnv[] r10 = r10.zzjsu     // Catch:{ all -> 0x0132 }
            int r13 = r10.length     // Catch:{ all -> 0x0132 }
            r14 = 0
        L_0x00d6:
            if (r14 >= r13) goto L_0x00e5
            r15 = r10[r14]     // Catch:{ all -> 0x0132 }
            boolean r15 = r1.zza((java.lang.String) r0, (int) r12, (com.google.android.gms.internal.zzcnv) r15)     // Catch:{ all -> 0x0132 }
            if (r15 != 0) goto L_0x00e2
            r9 = 0
            goto L_0x00e5
        L_0x00e2:
            int r14 = r14 + 1
            goto L_0x00d6
        L_0x00e5:
            if (r9 != 0) goto L_0x006f
            r16.zzyk()     // Catch:{ all -> 0x0132 }
            r16.zzwj()     // Catch:{ all -> 0x0132 }
            com.google.android.gms.common.internal.zzbq.zzgv(r17)     // Catch:{ all -> 0x0132 }
            android.database.sqlite.SQLiteDatabase r9 = r16.getWritableDatabase()     // Catch:{ all -> 0x0132 }
            r10 = 2
            java.lang.String[] r13 = new java.lang.String[r10]     // Catch:{ all -> 0x0132 }
            r13[r11] = r0     // Catch:{ all -> 0x0132 }
            java.lang.String r14 = java.lang.String.valueOf(r12)     // Catch:{ all -> 0x0132 }
            r15 = 1
            r13[r15] = r14     // Catch:{ all -> 0x0132 }
            r9.delete(r6, r3, r13)     // Catch:{ all -> 0x0132 }
            java.lang.String[] r10 = new java.lang.String[r10]     // Catch:{ all -> 0x0132 }
            r10[r11] = r0     // Catch:{ all -> 0x0132 }
            java.lang.String r12 = java.lang.String.valueOf(r12)     // Catch:{ all -> 0x0132 }
            r13 = 1
            r10[r13] = r12     // Catch:{ all -> 0x0132 }
            r9.delete(r4, r3, r10)     // Catch:{ all -> 0x0132 }
        L_0x0111:
            int r8 = r8 + 1
            r9 = 1
            goto L_0x0040
        L_0x0116:
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x0132 }
            r3.<init>()     // Catch:{ all -> 0x0132 }
            int r4 = r2.length     // Catch:{ all -> 0x0132 }
        L_0x011c:
            if (r11 >= r4) goto L_0x0128
            r5 = r2[r11]     // Catch:{ all -> 0x0132 }
            java.lang.Integer r5 = r5.zzjst     // Catch:{ all -> 0x0132 }
            r3.add(r5)     // Catch:{ all -> 0x0132 }
            int r11 = r11 + 1
            goto L_0x011c
        L_0x0128:
            r1.zze(r0, r3)     // Catch:{ all -> 0x0132 }
            r7.setTransactionSuccessful()     // Catch:{ all -> 0x0132 }
            r7.endTransaction()
            return
        L_0x0132:
            r0 = move-exception
            r7.endTransaction()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcil.zza(java.lang.String, com.google.android.gms.internal.zzcnr[]):void");
    }

    public final boolean zza(zzcii zzcii) {
        zzbq.checkNotNull(zzcii);
        zzwj();
        zzyk();
        if (zzag(zzcii.packageName, zzcii.zzjgn.name) == null) {
            if (zzc("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{zzcii.packageName}) >= 1000) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzcii.packageName);
        contentValues.put("origin", zzcii.zzjgm);
        contentValues.put("name", zzcii.zzjgn.name);
        zza(contentValues, FirebaseAnalytics.Param.VALUE, zzcii.zzjgn.getValue());
        contentValues.put("active", Boolean.valueOf(zzcii.zzjgp));
        contentValues.put("trigger_event_name", zzcii.zzjgq);
        contentValues.put("trigger_timeout", Long.valueOf(zzcii.zzjgs));
        zzayl();
        contentValues.put("timed_out_event", zzcno.zza((Parcelable) zzcii.zzjgr));
        contentValues.put("creation_timestamp", Long.valueOf(zzcii.zzjgo));
        zzayl();
        contentValues.put("triggered_event", zzcno.zza((Parcelable) zzcii.zzjgt));
        contentValues.put("triggered_timestamp", Long.valueOf(zzcii.zzjgn.zzjsi));
        contentValues.put("time_to_live", Long.valueOf(zzcii.zzjgu));
        zzayl();
        contentValues.put("expired_event", zzcno.zza((Parcelable) zzcii.zzjgv));
        try {
            if (getWritableDatabase().insertWithOnConflict("conditional_properties", (String) null, contentValues, 5) == -1) {
                zzayp().zzbau().zzj("Failed to insert/update conditional user property (got -1)", zzcjj.zzjs(zzcii.packageName));
            }
        } catch (SQLiteException e) {
            zzayp().zzbau().zze("Error storing conditional user property", zzcjj.zzjs(zzcii.packageName), e);
        }
        return true;
    }

    public final boolean zza(zzcis zzcis, long j, boolean z) {
        zzcjl zzbau;
        Object zzjs;
        String str;
        zzwj();
        zzyk();
        zzbq.checkNotNull(zzcis);
        zzbq.zzgv(zzcis.zzcm);
        zzcob zzcob = new zzcob();
        zzcob.zzjuk = Long.valueOf(zzcis.zzjhq);
        zzcob.zzjui = new zzcoc[zzcis.zzjhr.size()];
        Iterator<String> it = zzcis.zzjhr.iterator();
        int i = 0;
        while (it.hasNext()) {
            String next = it.next();
            zzcoc zzcoc = new zzcoc();
            int i2 = i + 1;
            zzcob.zzjui[i] = zzcoc;
            zzcoc.name = next;
            zzayl().zza(zzcoc, zzcis.zzjhr.get(next));
            i = i2;
        }
        try {
            int zzhs = zzcob.zzhs();
            byte[] bArr = new byte[zzhs];
            zzflk zzp = zzflk.zzp(bArr, 0, zzhs);
            zzcob.zza(zzp);
            zzp.zzcyx();
            zzayp().zzbba().zze("Saving event, name, data size", zzayk().zzjp(zzcis.name), Integer.valueOf(zzhs));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzcis.zzcm);
            contentValues.put("name", zzcis.name);
            contentValues.put(AppMeasurement.Param.TIMESTAMP, Long.valueOf(zzcis.timestamp));
            contentValues.put("metadata_fingerprint", Long.valueOf(j));
            contentValues.put(DataPacketExtension.ELEMENT_NAME, bArr);
            contentValues.put("realtime", Integer.valueOf(z ? 1 : 0));
            try {
                if (getWritableDatabase().insert("raw_events", (String) null, contentValues) != -1) {
                    return true;
                }
                zzayp().zzbau().zzj("Failed to insert raw event (got -1). appId", zzcjj.zzjs(zzcis.zzcm));
                return false;
            } catch (SQLiteException e) {
                zzayp().zzbau().zze("Error storing raw event. appId", zzcjj.zzjs(zzcis.zzcm), e);
                return false;
            }
        } catch (IOException e2) {
            zzayp().zzbau().zze("Data loss. Failed to serialize event params/data. appId", zzcjj.zzjs(zzcis.zzcm), e2);
            return false;
        }
    }

    public final boolean zza(zzcnn zzcnn) {
        zzbq.checkNotNull(zzcnn);
        zzwj();
        zzyk();
        if (zzag(zzcnn.zzcm, zzcnn.name) == null) {
            if (zzcno.zzkh(zzcnn.name)) {
                if (zzc("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzcnn.zzcm}) >= 25) {
                    return false;
                }
            } else {
                if (zzc("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzcnn.zzcm, zzcnn.zzjgm}) >= 25) {
                    return false;
                }
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzcnn.zzcm);
        contentValues.put("origin", zzcnn.zzjgm);
        contentValues.put("name", zzcnn.name);
        contentValues.put("set_timestamp", Long.valueOf(zzcnn.zzjsi));
        zza(contentValues, FirebaseAnalytics.Param.VALUE, zzcnn.value);
        try {
            if (getWritableDatabase().insertWithOnConflict("user_attributes", (String) null, contentValues, 5) == -1) {
                zzayp().zzbau().zzj("Failed to insert/update user property (got -1). appId", zzcjj.zzjs(zzcnn.zzcm));
            }
        } catch (SQLiteException e) {
            zzayp().zzbau().zze("Error storing user property. appId", zzcjj.zzjs(zzcnn.zzcm), e);
        }
        return true;
    }

    public final boolean zza(zzcoe zzcoe, boolean z) {
        zzcjl zzbau;
        Object zzjs;
        String str;
        zzwj();
        zzyk();
        zzbq.checkNotNull(zzcoe);
        zzbq.zzgv(zzcoe.zzcm);
        zzbq.checkNotNull(zzcoe.zzjuu);
        zzazy();
        long currentTimeMillis = zzxx().currentTimeMillis();
        if (zzcoe.zzjuu.longValue() < currentTimeMillis - zzcik.zzazs() || zzcoe.zzjuu.longValue() > zzcik.zzazs() + currentTimeMillis) {
            zzayp().zzbaw().zzd("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzcjj.zzjs(zzcoe.zzcm), Long.valueOf(currentTimeMillis), zzcoe.zzjuu);
        }
        try {
            int zzhs = zzcoe.zzhs();
            byte[] bArr = new byte[zzhs];
            zzflk zzp = zzflk.zzp(bArr, 0, zzhs);
            zzcoe.zza(zzp);
            zzp.zzcyx();
            byte[] zzr = zzayl().zzr(bArr);
            zzayp().zzbba().zzj("Saving bundle, size", Integer.valueOf(zzr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzcoe.zzcm);
            contentValues.put("bundle_end_timestamp", zzcoe.zzjuu);
            contentValues.put(DataPacketExtension.ELEMENT_NAME, zzr);
            contentValues.put("has_realtime", Integer.valueOf(z ? 1 : 0));
            try {
                if (getWritableDatabase().insert("queue", (String) null, contentValues) != -1) {
                    return true;
                }
                zzayp().zzbau().zzj("Failed to insert bundle (got -1). appId", zzcjj.zzjs(zzcoe.zzcm));
                return false;
            } catch (SQLiteException e) {
                zzayp().zzbau().zze("Error storing bundle. appId", zzcjj.zzjs(zzcoe.zzcm), e);
                return false;
            }
        } catch (IOException e2) {
            zzayp().zzbau().zze("Data loss. Failed to serialize bundle. appId", zzcjj.zzjs(zzcoe.zzcm), e2);
            return false;
        }
    }

    public final boolean zza(String str, Long l, long j, zzcob zzcob) {
        zzwj();
        zzyk();
        zzbq.checkNotNull(zzcob);
        zzbq.zzgv(str);
        zzbq.checkNotNull(l);
        try {
            int zzhs = zzcob.zzhs();
            byte[] bArr = new byte[zzhs];
            zzflk zzp = zzflk.zzp(bArr, 0, zzhs);
            zzcob.zza(zzp);
            zzp.zzcyx();
            zzayp().zzbba().zze("Saving complex main event, appId, data size", zzayk().zzjp(str), Integer.valueOf(zzhs));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("event_id", l);
            contentValues.put("children_to_process", Long.valueOf(j));
            contentValues.put("main_event", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("main_event_params", (String) null, contentValues, 5) != -1) {
                    return true;
                }
                zzayp().zzbau().zzj("Failed to insert complex main event (got -1). appId", zzcjj.zzjs(str));
                return false;
            } catch (SQLiteException e) {
                zzayp().zzbau().zze("Error storing complex main event. appId", zzcjj.zzjs(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzayp().zzbau().zzd("Data loss. Failed to serialize event params/data. appId, eventId", zzcjj.zzjs(str), l, e2);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:56:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x010d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzcit zzae(java.lang.String r22, java.lang.String r23) {
        /*
            r21 = this;
            r15 = r23
            com.google.android.gms.common.internal.zzbq.zzgv(r22)
            com.google.android.gms.common.internal.zzbq.zzgv(r23)
            r21.zzwj()
            r21.zzyk()
            r16 = 0
            android.database.sqlite.SQLiteDatabase r1 = r21.getWritableDatabase()     // Catch:{ SQLiteException -> 0x00e6, all -> 0x00e4 }
            java.lang.String r2 = "events"
            java.lang.String r3 = "lifetime_count"
            java.lang.String r4 = "current_bundle_count"
            java.lang.String r5 = "last_fire_timestamp"
            java.lang.String r6 = "last_bundled_timestamp"
            java.lang.String r7 = "last_sampled_complex_event_id"
            java.lang.String r8 = "last_sampling_rate"
            java.lang.String r9 = "last_exempt_from_sampling"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4, r5, r6, r7, r8, r9}     // Catch:{ SQLiteException -> 0x00e6, all -> 0x00e4 }
            java.lang.String r4 = "app_id=? and name=?"
            r0 = 2
            java.lang.String[] r5 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x00e6, all -> 0x00e4 }
            r9 = 0
            r5[r9] = r22     // Catch:{ SQLiteException -> 0x00e6, all -> 0x00e4 }
            r10 = 1
            r5[r10] = r15     // Catch:{ SQLiteException -> 0x00e6, all -> 0x00e4 }
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r14 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x00e6, all -> 0x00e4 }
            boolean r1 = r14.moveToFirst()     // Catch:{ SQLiteException -> 0x00e0, all -> 0x00da }
            if (r1 != 0) goto L_0x0046
            if (r14 == 0) goto L_0x0045
            r14.close()
        L_0x0045:
            return r16
        L_0x0046:
            long r4 = r14.getLong(r9)     // Catch:{ SQLiteException -> 0x00e0, all -> 0x00da }
            long r6 = r14.getLong(r10)     // Catch:{ SQLiteException -> 0x00e0, all -> 0x00da }
            long r11 = r14.getLong(r0)     // Catch:{ SQLiteException -> 0x00e0, all -> 0x00da }
            r0 = 3
            boolean r1 = r14.isNull(r0)     // Catch:{ SQLiteException -> 0x00e0, all -> 0x00da }
            if (r1 == 0) goto L_0x005c
            r0 = 0
            goto L_0x0060
        L_0x005c:
            long r0 = r14.getLong(r0)     // Catch:{ SQLiteException -> 0x00e0, all -> 0x00da }
        L_0x0060:
            r17 = r0
            r0 = 4
            boolean r1 = r14.isNull(r0)     // Catch:{ SQLiteException -> 0x00e0, all -> 0x00da }
            if (r1 == 0) goto L_0x006c
            r0 = r16
            goto L_0x0074
        L_0x006c:
            long r0 = r14.getLong(r0)     // Catch:{ SQLiteException -> 0x00e0, all -> 0x00da }
            java.lang.Long r0 = java.lang.Long.valueOf(r0)     // Catch:{ SQLiteException -> 0x00e0, all -> 0x00da }
        L_0x0074:
            r1 = 5
            boolean r2 = r14.isNull(r1)     // Catch:{ SQLiteException -> 0x00e0, all -> 0x00da }
            if (r2 == 0) goto L_0x007e
            r13 = r16
            goto L_0x0087
        L_0x007e:
            long r1 = r14.getLong(r1)     // Catch:{ SQLiteException -> 0x00e0, all -> 0x00da }
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ SQLiteException -> 0x00e0, all -> 0x00da }
            r13 = r1
        L_0x0087:
            r1 = 6
            boolean r2 = r14.isNull(r1)     // Catch:{ SQLiteException -> 0x00e0, all -> 0x00da }
            if (r2 != 0) goto L_0x00a2
            long r1 = r14.getLong(r1)     // Catch:{ SQLiteException -> 0x00a0 }
            r19 = 1
            int r3 = (r1 > r19 ? 1 : (r1 == r19 ? 0 : -1))
            if (r3 != 0) goto L_0x0099
            r9 = 1
        L_0x0099:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r9)     // Catch:{ SQLiteException -> 0x00a0 }
            r19 = r1
            goto L_0x00a4
        L_0x00a0:
            r0 = move-exception
            goto L_0x00e9
        L_0x00a2:
            r19 = r16
        L_0x00a4:
            com.google.android.gms.internal.zzcit r20 = new com.google.android.gms.internal.zzcit     // Catch:{ SQLiteException -> 0x00e0, all -> 0x00da }
            r1 = r20
            r2 = r22
            r3 = r23
            r8 = r11
            r10 = r17
            r12 = r0
            r17 = r14
            r14 = r19
            r1.<init>(r2, r3, r4, r6, r8, r10, r12, r13, r14)     // Catch:{ SQLiteException -> 0x00d6, all -> 0x00d4 }
            boolean r0 = r17.moveToNext()     // Catch:{ SQLiteException -> 0x00d6, all -> 0x00d4 }
            if (r0 == 0) goto L_0x00ce
            com.google.android.gms.internal.zzcjj r0 = r21.zzayp()     // Catch:{ SQLiteException -> 0x00d6, all -> 0x00d4 }
            com.google.android.gms.internal.zzcjl r0 = r0.zzbau()     // Catch:{ SQLiteException -> 0x00d6, all -> 0x00d4 }
            java.lang.String r1 = "Got multiple records for event aggregates, expected one. appId"
            java.lang.Object r2 = com.google.android.gms.internal.zzcjj.zzjs(r22)     // Catch:{ SQLiteException -> 0x00d6, all -> 0x00d4 }
            r0.zzj(r1, r2)     // Catch:{ SQLiteException -> 0x00d6, all -> 0x00d4 }
        L_0x00ce:
            if (r17 == 0) goto L_0x00d3
            r17.close()
        L_0x00d3:
            return r20
        L_0x00d4:
            r0 = move-exception
            goto L_0x00dd
        L_0x00d6:
            r0 = move-exception
            r14 = r17
            goto L_0x00e9
        L_0x00da:
            r0 = move-exception
            r17 = r14
        L_0x00dd:
            r16 = r17
            goto L_0x010b
        L_0x00e0:
            r0 = move-exception
            r17 = r14
            goto L_0x00e9
        L_0x00e4:
            r0 = move-exception
            goto L_0x010b
        L_0x00e6:
            r0 = move-exception
            r14 = r16
        L_0x00e9:
            com.google.android.gms.internal.zzcjj r1 = r21.zzayp()     // Catch:{ all -> 0x0108 }
            com.google.android.gms.internal.zzcjl r1 = r1.zzbau()     // Catch:{ all -> 0x0108 }
            java.lang.String r2 = "Error querying events. appId"
            java.lang.Object r3 = com.google.android.gms.internal.zzcjj.zzjs(r22)     // Catch:{ all -> 0x0108 }
            com.google.android.gms.internal.zzcjh r4 = r21.zzayk()     // Catch:{ all -> 0x0108 }
            java.lang.String r4 = r4.zzjp(r15)     // Catch:{ all -> 0x0108 }
            r1.zzd(r2, r3, r4, r0)     // Catch:{ all -> 0x0108 }
            if (r14 == 0) goto L_0x0107
            r14.close()
        L_0x0107:
            return r16
        L_0x0108:
            r0 = move-exception
            r16 = r14
        L_0x010b:
            if (r16 == 0) goto L_0x0110
            r16.close()
        L_0x0110:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcil.zzae(java.lang.String, java.lang.String):com.google.android.gms.internal.zzcit");
    }

    public final void zzaf(String str, String str2) {
        zzbq.zzgv(str);
        zzbq.zzgv(str2);
        zzwj();
        zzyk();
        try {
            zzayp().zzbba().zzj("Deleted user attribute rows", Integer.valueOf(getWritableDatabase().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2})));
        } catch (SQLiteException e) {
            zzayp().zzbau().zzd("Error deleting user attribute. appId", zzcjj.zzjs(str), zzayk().zzjr(str2), e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzcnn zzag(java.lang.String r19, java.lang.String r20) {
        /*
            r18 = this;
            r8 = r20
            com.google.android.gms.common.internal.zzbq.zzgv(r19)
            com.google.android.gms.common.internal.zzbq.zzgv(r20)
            r18.zzwj()
            r18.zzyk()
            r9 = 0
            android.database.sqlite.SQLiteDatabase r10 = r18.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0082, all -> 0x007e }
            java.lang.String r11 = "user_attributes"
            java.lang.String r0 = "set_timestamp"
            java.lang.String r1 = "value"
            java.lang.String r2 = "origin"
            java.lang.String[] r12 = new java.lang.String[]{r0, r1, r2}     // Catch:{ SQLiteException -> 0x0082, all -> 0x007e }
            java.lang.String r13 = "app_id=? and name=?"
            r0 = 2
            java.lang.String[] r14 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0082, all -> 0x007e }
            r1 = 0
            r14[r1] = r19     // Catch:{ SQLiteException -> 0x0082, all -> 0x007e }
            r2 = 1
            r14[r2] = r8     // Catch:{ SQLiteException -> 0x0082, all -> 0x007e }
            r15 = 0
            r16 = 0
            r17 = 0
            android.database.Cursor r10 = r10.query(r11, r12, r13, r14, r15, r16, r17)     // Catch:{ SQLiteException -> 0x0082, all -> 0x007e }
            boolean r3 = r10.moveToFirst()     // Catch:{ SQLiteException -> 0x007a, all -> 0x0076 }
            if (r3 != 0) goto L_0x003f
            if (r10 == 0) goto L_0x003e
            r10.close()
        L_0x003e:
            return r9
        L_0x003f:
            long r5 = r10.getLong(r1)     // Catch:{ SQLiteException -> 0x007a, all -> 0x0076 }
            r11 = r18
            java.lang.Object r7 = r11.zza((android.database.Cursor) r10, (int) r2)     // Catch:{ SQLiteException -> 0x0074 }
            java.lang.String r3 = r10.getString(r0)     // Catch:{ SQLiteException -> 0x0074 }
            com.google.android.gms.internal.zzcnn r0 = new com.google.android.gms.internal.zzcnn     // Catch:{ SQLiteException -> 0x0074 }
            r1 = r0
            r2 = r19
            r4 = r20
            r1.<init>(r2, r3, r4, r5, r7)     // Catch:{ SQLiteException -> 0x0074 }
            boolean r1 = r10.moveToNext()     // Catch:{ SQLiteException -> 0x0074 }
            if (r1 == 0) goto L_0x006e
            com.google.android.gms.internal.zzcjj r1 = r18.zzayp()     // Catch:{ SQLiteException -> 0x0074 }
            com.google.android.gms.internal.zzcjl r1 = r1.zzbau()     // Catch:{ SQLiteException -> 0x0074 }
            java.lang.String r2 = "Got multiple records for user property, expected one. appId"
            java.lang.Object r3 = com.google.android.gms.internal.zzcjj.zzjs(r19)     // Catch:{ SQLiteException -> 0x0074 }
            r1.zzj(r2, r3)     // Catch:{ SQLiteException -> 0x0074 }
        L_0x006e:
            if (r10 == 0) goto L_0x0073
            r10.close()
        L_0x0073:
            return r0
        L_0x0074:
            r0 = move-exception
            goto L_0x0086
        L_0x0076:
            r0 = move-exception
            r11 = r18
            goto L_0x00a6
        L_0x007a:
            r0 = move-exception
            r11 = r18
            goto L_0x0086
        L_0x007e:
            r0 = move-exception
            r11 = r18
            goto L_0x00a7
        L_0x0082:
            r0 = move-exception
            r11 = r18
            r10 = r9
        L_0x0086:
            com.google.android.gms.internal.zzcjj r1 = r18.zzayp()     // Catch:{ all -> 0x00a5 }
            com.google.android.gms.internal.zzcjl r1 = r1.zzbau()     // Catch:{ all -> 0x00a5 }
            java.lang.String r2 = "Error querying user property. appId"
            java.lang.Object r3 = com.google.android.gms.internal.zzcjj.zzjs(r19)     // Catch:{ all -> 0x00a5 }
            com.google.android.gms.internal.zzcjh r4 = r18.zzayk()     // Catch:{ all -> 0x00a5 }
            java.lang.String r4 = r4.zzjr(r8)     // Catch:{ all -> 0x00a5 }
            r1.zzd(r2, r3, r4, r0)     // Catch:{ all -> 0x00a5 }
            if (r10 == 0) goto L_0x00a4
            r10.close()
        L_0x00a4:
            return r9
        L_0x00a5:
            r0 = move-exception
        L_0x00a6:
            r9 = r10
        L_0x00a7:
            if (r9 == 0) goto L_0x00ac
            r9.close()
        L_0x00ac:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcil.zzag(java.lang.String, java.lang.String):com.google.android.gms.internal.zzcnn");
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x011d  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0125  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzcii zzah(java.lang.String r30, java.lang.String r31) {
        /*
            r29 = this;
            r7 = r31
            com.google.android.gms.common.internal.zzbq.zzgv(r30)
            com.google.android.gms.common.internal.zzbq.zzgv(r31)
            r29.zzwj()
            r29.zzyk()
            r8 = 0
            android.database.sqlite.SQLiteDatabase r9 = r29.getWritableDatabase()     // Catch:{ SQLiteException -> 0x00fe, all -> 0x00fa }
            java.lang.String r10 = "conditional_properties"
            java.lang.String r11 = "origin"
            java.lang.String r12 = "value"
            java.lang.String r13 = "active"
            java.lang.String r14 = "trigger_event_name"
            java.lang.String r15 = "trigger_timeout"
            java.lang.String r16 = "timed_out_event"
            java.lang.String r17 = "creation_timestamp"
            java.lang.String r18 = "triggered_event"
            java.lang.String r19 = "triggered_timestamp"
            java.lang.String r20 = "time_to_live"
            java.lang.String r21 = "expired_event"
            java.lang.String[] r11 = new java.lang.String[]{r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21}     // Catch:{ SQLiteException -> 0x00fe, all -> 0x00fa }
            java.lang.String r12 = "app_id=? and name=?"
            r0 = 2
            java.lang.String[] r13 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x00fe, all -> 0x00fa }
            r1 = 0
            r13[r1] = r30     // Catch:{ SQLiteException -> 0x00fe, all -> 0x00fa }
            r2 = 1
            r13[r2] = r7     // Catch:{ SQLiteException -> 0x00fe, all -> 0x00fa }
            r14 = 0
            r15 = 0
            r16 = 0
            android.database.Cursor r9 = r9.query(r10, r11, r12, r13, r14, r15, r16)     // Catch:{ SQLiteException -> 0x00fe, all -> 0x00fa }
            boolean r3 = r9.moveToFirst()     // Catch:{ SQLiteException -> 0x00f6, all -> 0x00f2 }
            if (r3 != 0) goto L_0x004e
            if (r9 == 0) goto L_0x004d
            r9.close()
        L_0x004d:
            return r8
        L_0x004e:
            java.lang.String r16 = r9.getString(r1)     // Catch:{ SQLiteException -> 0x00f6, all -> 0x00f2 }
            r10 = r29
            java.lang.Object r5 = r10.zza((android.database.Cursor) r9, (int) r2)     // Catch:{ SQLiteException -> 0x00f0 }
            int r0 = r9.getInt(r0)     // Catch:{ SQLiteException -> 0x00f0 }
            if (r0 == 0) goto L_0x0061
            r20 = 1
            goto L_0x0063
        L_0x0061:
            r20 = 0
        L_0x0063:
            r0 = 3
            java.lang.String r21 = r9.getString(r0)     // Catch:{ SQLiteException -> 0x00f0 }
            r0 = 4
            long r23 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.internal.zzcno r0 = r29.zzayl()     // Catch:{ SQLiteException -> 0x00f0 }
            r1 = 5
            byte[] r1 = r9.getBlob(r1)     // Catch:{ SQLiteException -> 0x00f0 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcix> r2 = com.google.android.gms.internal.zzcix.CREATOR     // Catch:{ SQLiteException -> 0x00f0 }
            android.os.Parcelable r0 = r0.zzb((byte[]) r1, r2)     // Catch:{ SQLiteException -> 0x00f0 }
            r22 = r0
            com.google.android.gms.internal.zzcix r22 = (com.google.android.gms.internal.zzcix) r22     // Catch:{ SQLiteException -> 0x00f0 }
            r0 = 6
            long r18 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.internal.zzcno r0 = r29.zzayl()     // Catch:{ SQLiteException -> 0x00f0 }
            r1 = 7
            byte[] r1 = r9.getBlob(r1)     // Catch:{ SQLiteException -> 0x00f0 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcix> r2 = com.google.android.gms.internal.zzcix.CREATOR     // Catch:{ SQLiteException -> 0x00f0 }
            android.os.Parcelable r0 = r0.zzb((byte[]) r1, r2)     // Catch:{ SQLiteException -> 0x00f0 }
            r25 = r0
            com.google.android.gms.internal.zzcix r25 = (com.google.android.gms.internal.zzcix) r25     // Catch:{ SQLiteException -> 0x00f0 }
            r0 = 8
            long r3 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x00f0 }
            r0 = 9
            long r26 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.internal.zzcno r0 = r29.zzayl()     // Catch:{ SQLiteException -> 0x00f0 }
            r1 = 10
            byte[] r1 = r9.getBlob(r1)     // Catch:{ SQLiteException -> 0x00f0 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcix> r2 = com.google.android.gms.internal.zzcix.CREATOR     // Catch:{ SQLiteException -> 0x00f0 }
            android.os.Parcelable r0 = r0.zzb((byte[]) r1, r2)     // Catch:{ SQLiteException -> 0x00f0 }
            r28 = r0
            com.google.android.gms.internal.zzcix r28 = (com.google.android.gms.internal.zzcix) r28     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.internal.zzcnl r17 = new com.google.android.gms.internal.zzcnl     // Catch:{ SQLiteException -> 0x00f0 }
            r1 = r17
            r2 = r31
            r6 = r16
            r1.<init>(r2, r3, r5, r6)     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.internal.zzcii r0 = new com.google.android.gms.internal.zzcii     // Catch:{ SQLiteException -> 0x00f0 }
            r14 = r0
            r15 = r30
            r14.<init>(r15, r16, r17, r18, r20, r21, r22, r23, r25, r26, r28)     // Catch:{ SQLiteException -> 0x00f0 }
            boolean r1 = r9.moveToNext()     // Catch:{ SQLiteException -> 0x00f0 }
            if (r1 == 0) goto L_0x00ea
            com.google.android.gms.internal.zzcjj r1 = r29.zzayp()     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.internal.zzcjl r1 = r1.zzbau()     // Catch:{ SQLiteException -> 0x00f0 }
            java.lang.String r2 = "Got multiple records for conditional property, expected one"
            java.lang.Object r3 = com.google.android.gms.internal.zzcjj.zzjs(r30)     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.internal.zzcjh r4 = r29.zzayk()     // Catch:{ SQLiteException -> 0x00f0 }
            java.lang.String r4 = r4.zzjr(r7)     // Catch:{ SQLiteException -> 0x00f0 }
            r1.zze(r2, r3, r4)     // Catch:{ SQLiteException -> 0x00f0 }
        L_0x00ea:
            if (r9 == 0) goto L_0x00ef
            r9.close()
        L_0x00ef:
            return r0
        L_0x00f0:
            r0 = move-exception
            goto L_0x0102
        L_0x00f2:
            r0 = move-exception
            r10 = r29
            goto L_0x0122
        L_0x00f6:
            r0 = move-exception
            r10 = r29
            goto L_0x0102
        L_0x00fa:
            r0 = move-exception
            r10 = r29
            goto L_0x0123
        L_0x00fe:
            r0 = move-exception
            r10 = r29
            r9 = r8
        L_0x0102:
            com.google.android.gms.internal.zzcjj r1 = r29.zzayp()     // Catch:{ all -> 0x0121 }
            com.google.android.gms.internal.zzcjl r1 = r1.zzbau()     // Catch:{ all -> 0x0121 }
            java.lang.String r2 = "Error querying conditional property"
            java.lang.Object r3 = com.google.android.gms.internal.zzcjj.zzjs(r30)     // Catch:{ all -> 0x0121 }
            com.google.android.gms.internal.zzcjh r4 = r29.zzayk()     // Catch:{ all -> 0x0121 }
            java.lang.String r4 = r4.zzjr(r7)     // Catch:{ all -> 0x0121 }
            r1.zzd(r2, r3, r4, r0)     // Catch:{ all -> 0x0121 }
            if (r9 == 0) goto L_0x0120
            r9.close()
        L_0x0120:
            return r8
        L_0x0121:
            r0 = move-exception
        L_0x0122:
            r8 = r9
        L_0x0123:
            if (r8 == 0) goto L_0x0128
            r8.close()
        L_0x0128:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcil.zzah(java.lang.String, java.lang.String):com.google.android.gms.internal.zzcii");
    }

    public final int zzai(String str, String str2) {
        zzbq.zzgv(str);
        zzbq.zzgv(str2);
        zzwj();
        zzyk();
        try {
            return getWritableDatabase().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzayp().zzbau().zzd("Error deleting conditional property", zzcjj.zzjs(str), zzayk().zzjr(str2), e);
            return 0;
        }
    }

    public final void zzai(List<Long> list) {
        zzbq.checkNotNull(list);
        zzwj();
        zzyk();
        StringBuilder sb = new StringBuilder("rowid in (");
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(list.get(i).longValue());
        }
        sb.append(")");
        int delete = getWritableDatabase().delete("raw_events", sb.toString(), (String[]) null);
        if (delete != list.size()) {
            zzayp().zzbau().zze("Deleted fewer rows from raw events table than expected", Integer.valueOf(delete), Integer.valueOf(list.size()));
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.zzcns>> zzaj(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            r12.zzyk()
            r12.zzwj()
            com.google.android.gms.common.internal.zzbq.zzgv(r13)
            com.google.android.gms.common.internal.zzbq.zzgv(r14)
            androidx.collection.ArrayMap r0 = new androidx.collection.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r12.getWritableDatabase()
            r9 = 0
            java.lang.String r2 = "event_filters"
            java.lang.String r3 = "audience_id"
            java.lang.String r4 = "data"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4}     // Catch:{ SQLiteException -> 0x0095, all -> 0x0093 }
            java.lang.String r4 = "app_id=? AND event_name=?"
            r5 = 2
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x0095, all -> 0x0093 }
            r10 = 0
            r5[r10] = r13     // Catch:{ SQLiteException -> 0x0095, all -> 0x0093 }
            r11 = 1
            r5[r11] = r14     // Catch:{ SQLiteException -> 0x0095, all -> 0x0093 }
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r14 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x0095, all -> 0x0093 }
            boolean r1 = r14.moveToFirst()     // Catch:{ SQLiteException -> 0x0091 }
            if (r1 != 0) goto L_0x0042
            java.util.Map r13 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x0091 }
            if (r14 == 0) goto L_0x0041
            r14.close()
        L_0x0041:
            return r13
        L_0x0042:
            byte[] r1 = r14.getBlob(r11)     // Catch:{ SQLiteException -> 0x0091 }
            int r2 = r1.length     // Catch:{ SQLiteException -> 0x0091 }
            com.google.android.gms.internal.zzflj r1 = com.google.android.gms.internal.zzflj.zzo(r1, r10, r2)     // Catch:{ SQLiteException -> 0x0091 }
            com.google.android.gms.internal.zzcns r2 = new com.google.android.gms.internal.zzcns     // Catch:{ SQLiteException -> 0x0091 }
            r2.<init>()     // Catch:{ SQLiteException -> 0x0091 }
            r2.zza((com.google.android.gms.internal.zzflj) r1)     // Catch:{ IOException -> 0x0073 }
            int r1 = r14.getInt(r10)     // Catch:{ SQLiteException -> 0x0091 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch:{ SQLiteException -> 0x0091 }
            java.lang.Object r3 = r0.get(r3)     // Catch:{ SQLiteException -> 0x0091 }
            java.util.List r3 = (java.util.List) r3     // Catch:{ SQLiteException -> 0x0091 }
            if (r3 != 0) goto L_0x006f
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0091 }
            r3.<init>()     // Catch:{ SQLiteException -> 0x0091 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ SQLiteException -> 0x0091 }
            r0.put(r1, r3)     // Catch:{ SQLiteException -> 0x0091 }
        L_0x006f:
            r3.add(r2)     // Catch:{ SQLiteException -> 0x0091 }
            goto L_0x0085
        L_0x0073:
            r1 = move-exception
            com.google.android.gms.internal.zzcjj r2 = r12.zzayp()     // Catch:{ SQLiteException -> 0x0091 }
            com.google.android.gms.internal.zzcjl r2 = r2.zzbau()     // Catch:{ SQLiteException -> 0x0091 }
            java.lang.String r3 = "Failed to merge filter. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzcjj.zzjs(r13)     // Catch:{ SQLiteException -> 0x0091 }
            r2.zze(r3, r4, r1)     // Catch:{ SQLiteException -> 0x0091 }
        L_0x0085:
            boolean r1 = r14.moveToNext()     // Catch:{ SQLiteException -> 0x0091 }
            if (r1 != 0) goto L_0x0042
            if (r14 == 0) goto L_0x0090
            r14.close()
        L_0x0090:
            return r0
        L_0x0091:
            r0 = move-exception
            goto L_0x0097
        L_0x0093:
            r13 = move-exception
            goto L_0x00b0
        L_0x0095:
            r0 = move-exception
            r14 = r9
        L_0x0097:
            com.google.android.gms.internal.zzcjj r1 = r12.zzayp()     // Catch:{ all -> 0x00ae }
            com.google.android.gms.internal.zzcjl r1 = r1.zzbau()     // Catch:{ all -> 0x00ae }
            java.lang.String r2 = "Database error querying filters. appId"
            java.lang.Object r13 = com.google.android.gms.internal.zzcjj.zzjs(r13)     // Catch:{ all -> 0x00ae }
            r1.zze(r2, r13, r0)     // Catch:{ all -> 0x00ae }
            if (r14 == 0) goto L_0x00ad
            r14.close()
        L_0x00ad:
            return r9
        L_0x00ae:
            r13 = move-exception
            r9 = r14
        L_0x00b0:
            if (r9 == 0) goto L_0x00b5
            r9.close()
        L_0x00b5:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcil.zzaj(java.lang.String, java.lang.String):java.util.Map");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.zzcnv>> zzak(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            r12.zzyk()
            r12.zzwj()
            com.google.android.gms.common.internal.zzbq.zzgv(r13)
            com.google.android.gms.common.internal.zzbq.zzgv(r14)
            androidx.collection.ArrayMap r0 = new androidx.collection.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r12.getWritableDatabase()
            r9 = 0
            java.lang.String r2 = "property_filters"
            java.lang.String r3 = "audience_id"
            java.lang.String r4 = "data"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4}     // Catch:{ SQLiteException -> 0x0095, all -> 0x0093 }
            java.lang.String r4 = "app_id=? AND property_name=?"
            r5 = 2
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x0095, all -> 0x0093 }
            r10 = 0
            r5[r10] = r13     // Catch:{ SQLiteException -> 0x0095, all -> 0x0093 }
            r11 = 1
            r5[r11] = r14     // Catch:{ SQLiteException -> 0x0095, all -> 0x0093 }
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r14 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x0095, all -> 0x0093 }
            boolean r1 = r14.moveToFirst()     // Catch:{ SQLiteException -> 0x0091 }
            if (r1 != 0) goto L_0x0042
            java.util.Map r13 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x0091 }
            if (r14 == 0) goto L_0x0041
            r14.close()
        L_0x0041:
            return r13
        L_0x0042:
            byte[] r1 = r14.getBlob(r11)     // Catch:{ SQLiteException -> 0x0091 }
            int r2 = r1.length     // Catch:{ SQLiteException -> 0x0091 }
            com.google.android.gms.internal.zzflj r1 = com.google.android.gms.internal.zzflj.zzo(r1, r10, r2)     // Catch:{ SQLiteException -> 0x0091 }
            com.google.android.gms.internal.zzcnv r2 = new com.google.android.gms.internal.zzcnv     // Catch:{ SQLiteException -> 0x0091 }
            r2.<init>()     // Catch:{ SQLiteException -> 0x0091 }
            r2.zza((com.google.android.gms.internal.zzflj) r1)     // Catch:{ IOException -> 0x0073 }
            int r1 = r14.getInt(r10)     // Catch:{ SQLiteException -> 0x0091 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch:{ SQLiteException -> 0x0091 }
            java.lang.Object r3 = r0.get(r3)     // Catch:{ SQLiteException -> 0x0091 }
            java.util.List r3 = (java.util.List) r3     // Catch:{ SQLiteException -> 0x0091 }
            if (r3 != 0) goto L_0x006f
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0091 }
            r3.<init>()     // Catch:{ SQLiteException -> 0x0091 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ SQLiteException -> 0x0091 }
            r0.put(r1, r3)     // Catch:{ SQLiteException -> 0x0091 }
        L_0x006f:
            r3.add(r2)     // Catch:{ SQLiteException -> 0x0091 }
            goto L_0x0085
        L_0x0073:
            r1 = move-exception
            com.google.android.gms.internal.zzcjj r2 = r12.zzayp()     // Catch:{ SQLiteException -> 0x0091 }
            com.google.android.gms.internal.zzcjl r2 = r2.zzbau()     // Catch:{ SQLiteException -> 0x0091 }
            java.lang.String r3 = "Failed to merge filter"
            java.lang.Object r4 = com.google.android.gms.internal.zzcjj.zzjs(r13)     // Catch:{ SQLiteException -> 0x0091 }
            r2.zze(r3, r4, r1)     // Catch:{ SQLiteException -> 0x0091 }
        L_0x0085:
            boolean r1 = r14.moveToNext()     // Catch:{ SQLiteException -> 0x0091 }
            if (r1 != 0) goto L_0x0042
            if (r14 == 0) goto L_0x0090
            r14.close()
        L_0x0090:
            return r0
        L_0x0091:
            r0 = move-exception
            goto L_0x0097
        L_0x0093:
            r13 = move-exception
            goto L_0x00b0
        L_0x0095:
            r0 = move-exception
            r14 = r9
        L_0x0097:
            com.google.android.gms.internal.zzcjj r1 = r12.zzayp()     // Catch:{ all -> 0x00ae }
            com.google.android.gms.internal.zzcjl r1 = r1.zzbau()     // Catch:{ all -> 0x00ae }
            java.lang.String r2 = "Database error querying filters. appId"
            java.lang.Object r13 = com.google.android.gms.internal.zzcjj.zzjs(r13)     // Catch:{ all -> 0x00ae }
            r1.zze(r2, r13, r0)     // Catch:{ all -> 0x00ae }
            if (r14 == 0) goto L_0x00ad
            r14.close()
        L_0x00ad:
            return r9
        L_0x00ae:
            r13 = move-exception
            r9 = r14
        L_0x00b0:
            if (r9 == 0) goto L_0x00b5
            r9.close()
        L_0x00b5:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcil.zzak(java.lang.String, java.lang.String):java.util.Map");
    }

    /* access modifiers changed from: protected */
    public final long zzal(String str, String str2) {
        long zza;
        String str3 = str;
        String str4 = str2;
        zzbq.zzgv(str);
        zzbq.zzgv(str2);
        zzwj();
        zzyk();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        long j = 0;
        try {
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 32);
            sb.append("select ");
            sb.append(str4);
            sb.append(" from app2 where app_id=?");
            try {
                zza = zza(sb.toString(), new String[]{str3}, -1);
                if (zza == -1) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("app_id", str3);
                    contentValues.put("first_open_count", 0);
                    contentValues.put("previous_install_count", 0);
                    if (writableDatabase.insertWithOnConflict("app2", (String) null, contentValues, 5) == -1) {
                        zzayp().zzbau().zze("Failed to insert column (got -1). appId", zzcjj.zzjs(str), str4);
                        writableDatabase.endTransaction();
                        return -1;
                    }
                    zza = 0;
                }
            } catch (SQLiteException e) {
                zzayp().zzbau().zzd("Error inserting column. appId", zzcjj.zzjs(str), str4, e);
                writableDatabase.endTransaction();
                return j;
            }
            try {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("app_id", str3);
                contentValues2.put(str4, Long.valueOf(1 + zza));
                if (((long) writableDatabase.update("app2", contentValues2, "app_id = ?", new String[]{str3})) == 0) {
                    zzayp().zzbau().zze("Failed to update column (got 0). appId", zzcjj.zzjs(str), str4);
                    writableDatabase.endTransaction();
                    return -1;
                }
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
                return zza;
            } catch (SQLiteException e2) {
                zzayp().zzbau().zzd("Error inserting column. appId", zzcjj.zzjs(str), str4, e2);
                writableDatabase.endTransaction();
                return zza;
            }
        } catch (SQLiteException e3) {
            zzayp().zzbau().zzd("Error inserting column. appId", zzcjj.zzjs(str), str4, e3);
            writableDatabase.endTransaction();
            return j;
        } catch (Throwable th) {
            writableDatabase.endTransaction();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zzazq() {
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0041  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzazw() {
        /*
            r6 = this;
            android.database.sqlite.SQLiteDatabase r0 = r6.getWritableDatabase()
            r1 = 0
            java.lang.String r2 = "select app_id from queue order by has_realtime desc, rowid asc limit 1;"
            android.database.Cursor r0 = r0.rawQuery(r2, r1)     // Catch:{ SQLiteException -> 0x0029, all -> 0x0024 }
            boolean r2 = r0.moveToFirst()     // Catch:{ SQLiteException -> 0x0022 }
            if (r2 == 0) goto L_0x001c
            r2 = 0
            java.lang.String r1 = r0.getString(r2)     // Catch:{ SQLiteException -> 0x0022 }
            if (r0 == 0) goto L_0x001b
            r0.close()
        L_0x001b:
            return r1
        L_0x001c:
            if (r0 == 0) goto L_0x0021
            r0.close()
        L_0x0021:
            return r1
        L_0x0022:
            r2 = move-exception
            goto L_0x002b
        L_0x0024:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x003f
        L_0x0029:
            r2 = move-exception
            r0 = r1
        L_0x002b:
            com.google.android.gms.internal.zzcjj r3 = r6.zzayp()     // Catch:{ all -> 0x003e }
            com.google.android.gms.internal.zzcjl r3 = r3.zzbau()     // Catch:{ all -> 0x003e }
            java.lang.String r4 = "Database error getting next bundle app id"
            r3.zzj(r4, r2)     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x003d
            r0.close()
        L_0x003d:
            return r1
        L_0x003e:
            r1 = move-exception
        L_0x003f:
            if (r0 == 0) goto L_0x0044
            r0.close()
        L_0x0044:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcil.zzazw():java.lang.String");
    }

    public final boolean zzazx() {
        return zzc("select count(1) > 0 from queue where has_realtime = 1", (String[]) null) != 0;
    }

    /* access modifiers changed from: package-private */
    public final void zzazy() {
        int delete;
        zzwj();
        zzyk();
        if (zzbae()) {
            long j = zzayq().zzjlq.get();
            long elapsedRealtime = zzxx().elapsedRealtime();
            if (Math.abs(elapsedRealtime - j) > zzciz.zzjjl.get().longValue()) {
                zzayq().zzjlq.set(elapsedRealtime);
                zzwj();
                zzyk();
                if (zzbae() && (delete = getWritableDatabase().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(zzxx().currentTimeMillis()), String.valueOf(zzcik.zzazs())})) > 0) {
                    zzayp().zzbba().zzj("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
                }
            }
        }
    }

    public final long zzazz() {
        return zza("select max(bundle_end_timestamp) from queue", (String[]) null, 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x008f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.util.Pair<com.google.android.gms.internal.zzcob, java.lang.Long> zzb(java.lang.String r8, java.lang.Long r9) {
        /*
            r7 = this;
            r7.zzwj()
            r7.zzyk()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r7.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0076, all -> 0x0074 }
            java.lang.String r2 = "select main_event, children_to_process from main_event_params where app_id=? and event_id=?"
            r3 = 2
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x0076, all -> 0x0074 }
            r4 = 0
            r3[r4] = r8     // Catch:{ SQLiteException -> 0x0076, all -> 0x0074 }
            java.lang.String r5 = java.lang.String.valueOf(r9)     // Catch:{ SQLiteException -> 0x0076, all -> 0x0074 }
            r6 = 1
            r3[r6] = r5     // Catch:{ SQLiteException -> 0x0076, all -> 0x0074 }
            android.database.Cursor r1 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x0076, all -> 0x0074 }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0072 }
            if (r2 != 0) goto L_0x0037
            com.google.android.gms.internal.zzcjj r8 = r7.zzayp()     // Catch:{ SQLiteException -> 0x0072 }
            com.google.android.gms.internal.zzcjl r8 = r8.zzbba()     // Catch:{ SQLiteException -> 0x0072 }
            java.lang.String r9 = "Main event not found"
            r8.log(r9)     // Catch:{ SQLiteException -> 0x0072 }
            if (r1 == 0) goto L_0x0036
            r1.close()
        L_0x0036:
            return r0
        L_0x0037:
            byte[] r2 = r1.getBlob(r4)     // Catch:{ SQLiteException -> 0x0072 }
            long r5 = r1.getLong(r6)     // Catch:{ SQLiteException -> 0x0072 }
            java.lang.Long r3 = java.lang.Long.valueOf(r5)     // Catch:{ SQLiteException -> 0x0072 }
            int r5 = r2.length     // Catch:{ SQLiteException -> 0x0072 }
            com.google.android.gms.internal.zzflj r2 = com.google.android.gms.internal.zzflj.zzo(r2, r4, r5)     // Catch:{ SQLiteException -> 0x0072 }
            com.google.android.gms.internal.zzcob r4 = new com.google.android.gms.internal.zzcob     // Catch:{ SQLiteException -> 0x0072 }
            r4.<init>()     // Catch:{ SQLiteException -> 0x0072 }
            r4.zza((com.google.android.gms.internal.zzflj) r2)     // Catch:{ IOException -> 0x005a }
            android.util.Pair r8 = android.util.Pair.create(r4, r3)     // Catch:{ SQLiteException -> 0x0072 }
            if (r1 == 0) goto L_0x0059
            r1.close()
        L_0x0059:
            return r8
        L_0x005a:
            r2 = move-exception
            com.google.android.gms.internal.zzcjj r3 = r7.zzayp()     // Catch:{ SQLiteException -> 0x0072 }
            com.google.android.gms.internal.zzcjl r3 = r3.zzbau()     // Catch:{ SQLiteException -> 0x0072 }
            java.lang.String r4 = "Failed to merge main event. appId, eventId"
            java.lang.Object r8 = com.google.android.gms.internal.zzcjj.zzjs(r8)     // Catch:{ SQLiteException -> 0x0072 }
            r3.zzd(r4, r8, r9, r2)     // Catch:{ SQLiteException -> 0x0072 }
            if (r1 == 0) goto L_0x0071
            r1.close()
        L_0x0071:
            return r0
        L_0x0072:
            r8 = move-exception
            goto L_0x0078
        L_0x0074:
            r8 = move-exception
            goto L_0x008d
        L_0x0076:
            r8 = move-exception
            r1 = r0
        L_0x0078:
            com.google.android.gms.internal.zzcjj r9 = r7.zzayp()     // Catch:{ all -> 0x008b }
            com.google.android.gms.internal.zzcjl r9 = r9.zzbau()     // Catch:{ all -> 0x008b }
            java.lang.String r2 = "Error selecting main event"
            r9.zzj(r2, r8)     // Catch:{ all -> 0x008b }
            if (r1 == 0) goto L_0x008a
            r1.close()
        L_0x008a:
            return r0
        L_0x008b:
            r8 = move-exception
            r0 = r1
        L_0x008d:
            if (r0 == 0) goto L_0x0092
            r0.close()
        L_0x0092:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcil.zzb(java.lang.String, java.lang.Long):android.util.Pair");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzba(long r5) {
        /*
            r4 = this;
            r4.zzwj()
            r4.zzyk()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r4.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0042, all -> 0x0040 }
            java.lang.String r2 = "select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x0042, all -> 0x0040 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x0042, all -> 0x0040 }
            r6 = 0
            r3[r6] = r5     // Catch:{ SQLiteException -> 0x0042, all -> 0x0040 }
            android.database.Cursor r5 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x0042, all -> 0x0040 }
            boolean r1 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x003e }
            if (r1 != 0) goto L_0x0034
            com.google.android.gms.internal.zzcjj r6 = r4.zzayp()     // Catch:{ SQLiteException -> 0x003e }
            com.google.android.gms.internal.zzcjl r6 = r6.zzbba()     // Catch:{ SQLiteException -> 0x003e }
            java.lang.String r1 = "No expired configs for apps with pending events"
            r6.log(r1)     // Catch:{ SQLiteException -> 0x003e }
            if (r5 == 0) goto L_0x0033
            r5.close()
        L_0x0033:
            return r0
        L_0x0034:
            java.lang.String r6 = r5.getString(r6)     // Catch:{ SQLiteException -> 0x003e }
            if (r5 == 0) goto L_0x003d
            r5.close()
        L_0x003d:
            return r6
        L_0x003e:
            r6 = move-exception
            goto L_0x0044
        L_0x0040:
            r6 = move-exception
            goto L_0x0059
        L_0x0042:
            r6 = move-exception
            r5 = r0
        L_0x0044:
            com.google.android.gms.internal.zzcjj r1 = r4.zzayp()     // Catch:{ all -> 0x0057 }
            com.google.android.gms.internal.zzcjl r1 = r1.zzbau()     // Catch:{ all -> 0x0057 }
            java.lang.String r2 = "Error selecting expired configs"
            r1.zzj(r2, r6)     // Catch:{ all -> 0x0057 }
            if (r5 == 0) goto L_0x0056
            r5.close()
        L_0x0056:
            return r0
        L_0x0057:
            r6 = move-exception
            r0 = r5
        L_0x0059:
            if (r0 == 0) goto L_0x005e
            r0.close()
        L_0x005e:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcil.zzba(long):java.lang.String");
    }

    public final long zzbaa() {
        return zza("select max(timestamp) from raw_events", (String[]) null, 0);
    }

    public final boolean zzbab() {
        return zzc("select count(1) > 0 from raw_events", (String[]) null) != 0;
    }

    public final boolean zzbac() {
        return zzc("select count(1) > 0 from raw_events where realtime = 1", (String[]) null) != 0;
    }

    public final long zzbad() {
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().rawQuery("select rowid from raw_events order by rowid desc limit 1;", (String[]) null);
            if (!cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                return -1;
            }
            long j = cursor.getLong(0);
            if (cursor != null) {
                cursor.close();
            }
            return j;
        } catch (SQLiteException e) {
            zzayp().zzbau().zzj("Error querying raw events", e);
            if (cursor != null) {
                cursor.close();
            }
            return -1;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public final List<zzcii> zzd(String str, String[] strArr) {
        zzwj();
        zzyk();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().query("conditional_properties", new String[]{"app_id", "origin", "name", FirebaseAnalytics.Param.VALUE, "active", "trigger_event_name", "trigger_timeout", "timed_out_event", "creation_timestamp", "triggered_event", "triggered_timestamp", "time_to_live", "expired_event"}, str, strArr, (String) null, (String) null, "rowid", NativeContentAd.ASSET_HEADLINE);
            if (!cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                return arrayList;
            }
            while (true) {
                if (arrayList.size() < 1000) {
                    boolean z = false;
                    String string = cursor.getString(0);
                    String string2 = cursor.getString(1);
                    String string3 = cursor.getString(2);
                    Object zza = zza(cursor, 3);
                    if (cursor.getInt(4) != 0) {
                        z = true;
                    }
                    String string4 = cursor.getString(5);
                    long j = cursor.getLong(6);
                    long j2 = cursor.getLong(8);
                    long j3 = cursor.getLong(10);
                    boolean z2 = z;
                    zzcii zzcii2 = new zzcii(string, string2, new zzcnl(string3, j3, zza, string2), j2, z2, string4, (zzcix) zzayl().zzb(cursor.getBlob(7), zzcix.CREATOR), j, (zzcix) zzayl().zzb(cursor.getBlob(9), zzcix.CREATOR), cursor.getLong(11), (zzcix) zzayl().zzb(cursor.getBlob(12), zzcix.CREATOR));
                    arrayList.add(zzcii2);
                    if (!cursor.moveToNext()) {
                        break;
                    }
                } else {
                    zzayp().zzbau().zzj("Read more than the max allowed conditional properties, ignoring extra", 1000);
                    break;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return arrayList;
        } catch (SQLiteException e) {
            zzayp().zzbau().zzj("Error querying conditional user property value", e);
            List<zzcii> emptyList = Collections.emptyList();
            if (cursor != null) {
                cursor.close();
            }
            return emptyList;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00f8, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00f9, code lost:
        r12 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0100, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0101, code lost:
        r12 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0104, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0105, code lost:
        r12 = r21;
        r11 = r22;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0100 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:1:0x000f] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x011f  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0127  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.internal.zzcnn> zzh(java.lang.String r22, java.lang.String r23, java.lang.String r24) {
        /*
            r21 = this;
            com.google.android.gms.common.internal.zzbq.zzgv(r22)
            r21.zzwj()
            r21.zzyk()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0104, all -> 0x0100 }
            r3 = 3
            r2.<init>(r3)     // Catch:{ SQLiteException -> 0x0104, all -> 0x0100 }
            r11 = r22
            r2.add(r11)     // Catch:{ SQLiteException -> 0x00fc, all -> 0x0100 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x00fc, all -> 0x0100 }
            java.lang.String r5 = "app_id=?"
            r4.<init>(r5)     // Catch:{ SQLiteException -> 0x00fc, all -> 0x0100 }
            boolean r5 = android.text.TextUtils.isEmpty(r23)     // Catch:{ SQLiteException -> 0x00fc, all -> 0x0100 }
            if (r5 != 0) goto L_0x0032
            r5 = r23
            r2.add(r5)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            java.lang.String r6 = " and origin=?"
            r4.append(r6)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            goto L_0x0034
        L_0x0032:
            r5 = r23
        L_0x0034:
            boolean r6 = android.text.TextUtils.isEmpty(r24)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            if (r6 != 0) goto L_0x004c
            java.lang.String r6 = java.lang.String.valueOf(r24)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            java.lang.String r7 = "*"
            java.lang.String r6 = r6.concat(r7)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            r2.add(r6)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            java.lang.String r6 = " and name glob ?"
            r4.append(r6)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
        L_0x004c:
            int r6 = r2.size()     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            java.lang.Object[] r2 = r2.toArray(r6)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            r16 = r2
            java.lang.String[] r16 = (java.lang.String[]) r16     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            android.database.sqlite.SQLiteDatabase r12 = r21.getWritableDatabase()     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            java.lang.String r13 = "user_attributes"
            java.lang.String r2 = "name"
            java.lang.String r6 = "set_timestamp"
            java.lang.String r7 = "value"
            java.lang.String r8 = "origin"
            java.lang.String[] r14 = new java.lang.String[]{r2, r6, r7, r8}     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            java.lang.String r15 = r4.toString()     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            r17 = 0
            r18 = 0
            java.lang.String r19 = "rowid"
            java.lang.String r20 = "1001"
            android.database.Cursor r2 = r12.query(r13, r14, r15, r16, r17, r18, r19, r20)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x0100 }
            boolean r4 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x00f4, all -> 0x00f0 }
            if (r4 != 0) goto L_0x0088
            if (r2 == 0) goto L_0x0087
            r2.close()
        L_0x0087:
            return r0
        L_0x0088:
            int r4 = r0.size()     // Catch:{ SQLiteException -> 0x00f4, all -> 0x00f0 }
            r6 = 1000(0x3e8, float:1.401E-42)
            if (r4 < r6) goto L_0x00a4
            com.google.android.gms.internal.zzcjj r3 = r21.zzayp()     // Catch:{ SQLiteException -> 0x00f4, all -> 0x00f0 }
            com.google.android.gms.internal.zzcjl r3 = r3.zzbau()     // Catch:{ SQLiteException -> 0x00f4, all -> 0x00f0 }
            java.lang.String r4 = "Read more than the max allowed user properties, ignoring excess"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ SQLiteException -> 0x00f4, all -> 0x00f0 }
            r3.zzj(r4, r6)     // Catch:{ SQLiteException -> 0x00f4, all -> 0x00f0 }
            r12 = r21
            goto L_0x00e3
        L_0x00a4:
            r4 = 0
            java.lang.String r7 = r2.getString(r4)     // Catch:{ SQLiteException -> 0x00f4, all -> 0x00f0 }
            r4 = 1
            long r8 = r2.getLong(r4)     // Catch:{ SQLiteException -> 0x00f4, all -> 0x00f0 }
            r4 = 2
            r12 = r21
            java.lang.Object r10 = r12.zza((android.database.Cursor) r2, (int) r4)     // Catch:{ SQLiteException -> 0x00ee }
            java.lang.String r13 = r2.getString(r3)     // Catch:{ SQLiteException -> 0x00ee }
            if (r10 != 0) goto L_0x00cf
            com.google.android.gms.internal.zzcjj r4 = r21.zzayp()     // Catch:{ SQLiteException -> 0x00eb }
            com.google.android.gms.internal.zzcjl r4 = r4.zzbau()     // Catch:{ SQLiteException -> 0x00eb }
            java.lang.String r5 = "(2)Read invalid user property value, ignoring it"
            java.lang.Object r6 = com.google.android.gms.internal.zzcjj.zzjs(r22)     // Catch:{ SQLiteException -> 0x00eb }
            r14 = r24
            r4.zzd(r5, r6, r13, r14)     // Catch:{ SQLiteException -> 0x00eb }
            goto L_0x00dd
        L_0x00cf:
            r14 = r24
            com.google.android.gms.internal.zzcnn r15 = new com.google.android.gms.internal.zzcnn     // Catch:{ SQLiteException -> 0x00eb }
            r4 = r15
            r5 = r22
            r6 = r13
            r4.<init>(r5, r6, r7, r8, r10)     // Catch:{ SQLiteException -> 0x00eb }
            r0.add(r15)     // Catch:{ SQLiteException -> 0x00eb }
        L_0x00dd:
            boolean r4 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x00eb }
            if (r4 != 0) goto L_0x00e9
        L_0x00e3:
            if (r2 == 0) goto L_0x00e8
            r2.close()
        L_0x00e8:
            return r0
        L_0x00e9:
            r5 = r13
            goto L_0x0088
        L_0x00eb:
            r0 = move-exception
            r5 = r13
            goto L_0x010c
        L_0x00ee:
            r0 = move-exception
            goto L_0x010c
        L_0x00f0:
            r0 = move-exception
            r12 = r21
            goto L_0x0124
        L_0x00f4:
            r0 = move-exception
            r12 = r21
            goto L_0x010c
        L_0x00f8:
            r0 = move-exception
            r12 = r21
            goto L_0x010b
        L_0x00fc:
            r0 = move-exception
            r12 = r21
            goto L_0x0109
        L_0x0100:
            r0 = move-exception
            r12 = r21
            goto L_0x0125
        L_0x0104:
            r0 = move-exception
            r12 = r21
            r11 = r22
        L_0x0109:
            r5 = r23
        L_0x010b:
            r2 = r1
        L_0x010c:
            com.google.android.gms.internal.zzcjj r3 = r21.zzayp()     // Catch:{ all -> 0x0123 }
            com.google.android.gms.internal.zzcjl r3 = r3.zzbau()     // Catch:{ all -> 0x0123 }
            java.lang.String r4 = "(2)Error querying user properties"
            java.lang.Object r6 = com.google.android.gms.internal.zzcjj.zzjs(r22)     // Catch:{ all -> 0x0123 }
            r3.zzd(r4, r6, r5, r0)     // Catch:{ all -> 0x0123 }
            if (r2 == 0) goto L_0x0122
            r2.close()
        L_0x0122:
            return r1
        L_0x0123:
            r0 = move-exception
        L_0x0124:
            r1 = r2
        L_0x0125:
            if (r1 == 0) goto L_0x012a
            r1.close()
        L_0x012a:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcil.zzh(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    public final List<zzcii> zzi(String str, String str2, String str3) {
        zzbq.zzgv(str);
        zzwj();
        zzyk();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat("*"));
            sb.append(" and name glob ?");
        }
        return zzd(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.internal.zzcnn> zzji(java.lang.String r14) {
        /*
            r13 = this;
            com.google.android.gms.common.internal.zzbq.zzgv(r14)
            r13.zzwj()
            r13.zzyk()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r13.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0084, all -> 0x0082 }
            java.lang.String r3 = "user_attributes"
            java.lang.String r4 = "name"
            java.lang.String r5 = "origin"
            java.lang.String r6 = "set_timestamp"
            java.lang.String r7 = "value"
            java.lang.String[] r4 = new java.lang.String[]{r4, r5, r6, r7}     // Catch:{ SQLiteException -> 0x0084, all -> 0x0082 }
            java.lang.String r5 = "app_id=?"
            r11 = 1
            java.lang.String[] r6 = new java.lang.String[r11]     // Catch:{ SQLiteException -> 0x0084, all -> 0x0082 }
            r12 = 0
            r6[r12] = r14     // Catch:{ SQLiteException -> 0x0084, all -> 0x0082 }
            r7 = 0
            r8 = 0
            java.lang.String r9 = "rowid"
            java.lang.String r10 = "1000"
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x0084, all -> 0x0082 }
            boolean r3 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x0080 }
            if (r3 != 0) goto L_0x003f
            if (r2 == 0) goto L_0x003e
            r2.close()
        L_0x003e:
            return r0
        L_0x003f:
            java.lang.String r7 = r2.getString(r12)     // Catch:{ SQLiteException -> 0x0080 }
            java.lang.String r3 = r2.getString(r11)     // Catch:{ SQLiteException -> 0x0080 }
            if (r3 != 0) goto L_0x004b
            java.lang.String r3 = ""
        L_0x004b:
            r6 = r3
            r3 = 2
            long r8 = r2.getLong(r3)     // Catch:{ SQLiteException -> 0x0080 }
            r3 = 3
            java.lang.Object r10 = r13.zza((android.database.Cursor) r2, (int) r3)     // Catch:{ SQLiteException -> 0x0080 }
            if (r10 != 0) goto L_0x006a
            com.google.android.gms.internal.zzcjj r3 = r13.zzayp()     // Catch:{ SQLiteException -> 0x0080 }
            com.google.android.gms.internal.zzcjl r3 = r3.zzbau()     // Catch:{ SQLiteException -> 0x0080 }
            java.lang.String r4 = "Read invalid user property value, ignoring it. appId"
            java.lang.Object r5 = com.google.android.gms.internal.zzcjj.zzjs(r14)     // Catch:{ SQLiteException -> 0x0080 }
            r3.zzj(r4, r5)     // Catch:{ SQLiteException -> 0x0080 }
            goto L_0x0074
        L_0x006a:
            com.google.android.gms.internal.zzcnn r3 = new com.google.android.gms.internal.zzcnn     // Catch:{ SQLiteException -> 0x0080 }
            r4 = r3
            r5 = r14
            r4.<init>(r5, r6, r7, r8, r10)     // Catch:{ SQLiteException -> 0x0080 }
            r0.add(r3)     // Catch:{ SQLiteException -> 0x0080 }
        L_0x0074:
            boolean r3 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x0080 }
            if (r3 != 0) goto L_0x003f
            if (r2 == 0) goto L_0x007f
            r2.close()
        L_0x007f:
            return r0
        L_0x0080:
            r0 = move-exception
            goto L_0x0086
        L_0x0082:
            r14 = move-exception
            goto L_0x009f
        L_0x0084:
            r0 = move-exception
            r2 = r1
        L_0x0086:
            com.google.android.gms.internal.zzcjj r3 = r13.zzayp()     // Catch:{ all -> 0x009d }
            com.google.android.gms.internal.zzcjl r3 = r3.zzbau()     // Catch:{ all -> 0x009d }
            java.lang.String r4 = "Error querying user properties. appId"
            java.lang.Object r14 = com.google.android.gms.internal.zzcjj.zzjs(r14)     // Catch:{ all -> 0x009d }
            r3.zze(r4, r14, r0)     // Catch:{ all -> 0x009d }
            if (r2 == 0) goto L_0x009c
            r2.close()
        L_0x009c:
            return r1
        L_0x009d:
            r14 = move-exception
            r1 = r2
        L_0x009f:
            if (r1 == 0) goto L_0x00a4
            r1.close()
        L_0x00a4:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcil.zzji(java.lang.String):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x010d A[Catch:{ SQLiteException -> 0x0182 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0111 A[Catch:{ SQLiteException -> 0x0182 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0145 A[Catch:{ SQLiteException -> 0x0182 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0148 A[Catch:{ SQLiteException -> 0x0182 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0157 A[Catch:{ SQLiteException -> 0x0182 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x016b A[Catch:{ SQLiteException -> 0x0182 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x017e  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x01a7  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x01af  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzcie zzjj(java.lang.String r30) {
        /*
            r29 = this;
            r1 = r30
            com.google.android.gms.common.internal.zzbq.zzgv(r30)
            r29.zzwj()
            r29.zzyk()
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r29.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0190, all -> 0x018c }
            java.lang.String r4 = "apps"
            java.lang.String r5 = "app_instance_id"
            java.lang.String r6 = "gmp_app_id"
            java.lang.String r7 = "resettable_device_id_hash"
            java.lang.String r8 = "last_bundle_index"
            java.lang.String r9 = "last_bundle_start_timestamp"
            java.lang.String r10 = "last_bundle_end_timestamp"
            java.lang.String r11 = "app_version"
            java.lang.String r12 = "app_store"
            java.lang.String r13 = "gmp_version"
            java.lang.String r14 = "dev_cert_hash"
            java.lang.String r15 = "measurement_enabled"
            java.lang.String r16 = "day"
            java.lang.String r17 = "daily_public_events_count"
            java.lang.String r18 = "daily_events_count"
            java.lang.String r19 = "daily_conversions_count"
            java.lang.String r20 = "config_fetched_time"
            java.lang.String r21 = "failed_config_fetch_time"
            java.lang.String r22 = "app_version_int"
            java.lang.String r23 = "firebase_instance_id"
            java.lang.String r24 = "daily_error_events_count"
            java.lang.String r25 = "daily_realtime_events_count"
            java.lang.String r26 = "health_monitor_sample"
            java.lang.String r27 = "android_id"
            java.lang.String r28 = "adid_reporting_enabled"
            java.lang.String[] r5 = new java.lang.String[]{r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28}     // Catch:{ SQLiteException -> 0x0190, all -> 0x018c }
            java.lang.String r6 = "app_id=?"
            r0 = 1
            java.lang.String[] r7 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0190, all -> 0x018c }
            r11 = 0
            r7[r11] = r1     // Catch:{ SQLiteException -> 0x0190, all -> 0x018c }
            r8 = 0
            r9 = 0
            r10 = 0
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x0190, all -> 0x018c }
            boolean r4 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x0188, all -> 0x0184 }
            if (r4 != 0) goto L_0x0061
            if (r3 == 0) goto L_0x0060
            r3.close()
        L_0x0060:
            return r2
        L_0x0061:
            com.google.android.gms.internal.zzcie r4 = new com.google.android.gms.internal.zzcie     // Catch:{ SQLiteException -> 0x0188, all -> 0x0184 }
            r5 = r29
            com.google.android.gms.internal.zzckj r6 = r5.zzjev     // Catch:{ SQLiteException -> 0x0182 }
            r4.<init>(r6, r1)     // Catch:{ SQLiteException -> 0x0182 }
            java.lang.String r6 = r3.getString(r11)     // Catch:{ SQLiteException -> 0x0182 }
            r4.zziy(r6)     // Catch:{ SQLiteException -> 0x0182 }
            java.lang.String r6 = r3.getString(r0)     // Catch:{ SQLiteException -> 0x0182 }
            r4.zziz(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r6 = 2
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r4.zzja(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r6 = 3
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r4.zzaq(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r6 = 4
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r4.zzal(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r6 = 5
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r4.zzam(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r6 = 6
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r4.setAppVersion(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r6 = 7
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r4.zzjc(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r6 = 8
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r4.zzao(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r6 = 9
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r4.zzap(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r6 = 10
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x0182 }
            if (r7 != 0) goto L_0x00cb
            int r6 = r3.getInt(r6)     // Catch:{ SQLiteException -> 0x0182 }
            if (r6 == 0) goto L_0x00c9
            goto L_0x00cb
        L_0x00c9:
            r6 = 0
            goto L_0x00cc
        L_0x00cb:
            r6 = 1
        L_0x00cc:
            r4.setMeasurementEnabled(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r6 = 11
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r4.zzat(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r6 = 12
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r4.zzau(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r6 = 13
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r4.zzav(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r6 = 14
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r4.zzaw(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r6 = 15
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r4.zzar(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r6 = 16
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r4.zzas(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r6 = 17
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x0182 }
            if (r7 == 0) goto L_0x0111
            r6 = -2147483648(0xffffffff80000000, double:NaN)
            goto L_0x0116
        L_0x0111:
            int r6 = r3.getInt(r6)     // Catch:{ SQLiteException -> 0x0182 }
            long r6 = (long) r6     // Catch:{ SQLiteException -> 0x0182 }
        L_0x0116:
            r4.zzan(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r6 = 18
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r4.zzjb(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r6 = 19
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r4.zzay(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r6 = 20
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r4.zzax(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r6 = 21
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r4.zzjd(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r6 = 22
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x0182 }
            if (r7 == 0) goto L_0x0148
            r6 = 0
            goto L_0x014c
        L_0x0148:
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0182 }
        L_0x014c:
            r4.zzaz(r6)     // Catch:{ SQLiteException -> 0x0182 }
            r6 = 23
            boolean r7 = r3.isNull(r6)     // Catch:{ SQLiteException -> 0x0182 }
            if (r7 != 0) goto L_0x015f
            int r6 = r3.getInt(r6)     // Catch:{ SQLiteException -> 0x0182 }
            if (r6 == 0) goto L_0x015e
            goto L_0x015f
        L_0x015e:
            r0 = 0
        L_0x015f:
            r4.zzbq(r0)     // Catch:{ SQLiteException -> 0x0182 }
            r4.zzays()     // Catch:{ SQLiteException -> 0x0182 }
            boolean r0 = r3.moveToNext()     // Catch:{ SQLiteException -> 0x0182 }
            if (r0 == 0) goto L_0x017c
            com.google.android.gms.internal.zzcjj r0 = r29.zzayp()     // Catch:{ SQLiteException -> 0x0182 }
            com.google.android.gms.internal.zzcjl r0 = r0.zzbau()     // Catch:{ SQLiteException -> 0x0182 }
            java.lang.String r6 = "Got multiple records for app, expected one. appId"
            java.lang.Object r7 = com.google.android.gms.internal.zzcjj.zzjs(r30)     // Catch:{ SQLiteException -> 0x0182 }
            r0.zzj(r6, r7)     // Catch:{ SQLiteException -> 0x0182 }
        L_0x017c:
            if (r3 == 0) goto L_0x0181
            r3.close()
        L_0x0181:
            return r4
        L_0x0182:
            r0 = move-exception
            goto L_0x0194
        L_0x0184:
            r0 = move-exception
            r5 = r29
            goto L_0x01ac
        L_0x0188:
            r0 = move-exception
            r5 = r29
            goto L_0x0194
        L_0x018c:
            r0 = move-exception
            r5 = r29
            goto L_0x01ad
        L_0x0190:
            r0 = move-exception
            r5 = r29
            r3 = r2
        L_0x0194:
            com.google.android.gms.internal.zzcjj r4 = r29.zzayp()     // Catch:{ all -> 0x01ab }
            com.google.android.gms.internal.zzcjl r4 = r4.zzbau()     // Catch:{ all -> 0x01ab }
            java.lang.String r6 = "Error querying app. appId"
            java.lang.Object r1 = com.google.android.gms.internal.zzcjj.zzjs(r30)     // Catch:{ all -> 0x01ab }
            r4.zze(r6, r1, r0)     // Catch:{ all -> 0x01ab }
            if (r3 == 0) goto L_0x01aa
            r3.close()
        L_0x01aa:
            return r2
        L_0x01ab:
            r0 = move-exception
        L_0x01ac:
            r2 = r3
        L_0x01ad:
            if (r2 == 0) goto L_0x01b2
            r2.close()
        L_0x01b2:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcil.zzjj(java.lang.String):com.google.android.gms.internal.zzcie");
    }

    public final long zzjk(String str) {
        zzbq.zzgv(str);
        zzwj();
        zzyk();
        try {
            return (long) getWritableDatabase().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, String.valueOf(Math.max(0, Math.min(1000000, zzayr().zzb(str, zzciz.zzjjc))))});
        } catch (SQLiteException e) {
            zzayp().zzbau().zze("Error deleting over the limit events. appId", zzcjj.zzjs(str), e);
            return 0;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0073  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] zzjl(java.lang.String r11) {
        /*
            r10 = this;
            com.google.android.gms.common.internal.zzbq.zzgv(r11)
            r10.zzwj()
            r10.zzyk()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r10.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0056, all -> 0x0054 }
            java.lang.String r2 = "apps"
            java.lang.String r3 = "remote_config"
            java.lang.String[] r3 = new java.lang.String[]{r3}     // Catch:{ SQLiteException -> 0x0056, all -> 0x0054 }
            java.lang.String r4 = "app_id=?"
            r5 = 1
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x0056, all -> 0x0054 }
            r9 = 0
            r5[r9] = r11     // Catch:{ SQLiteException -> 0x0056, all -> 0x0054 }
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x0056, all -> 0x0054 }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0052 }
            if (r2 != 0) goto L_0x0031
            if (r1 == 0) goto L_0x0030
            r1.close()
        L_0x0030:
            return r0
        L_0x0031:
            byte[] r2 = r1.getBlob(r9)     // Catch:{ SQLiteException -> 0x0052 }
            boolean r3 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x0052 }
            if (r3 == 0) goto L_0x004c
            com.google.android.gms.internal.zzcjj r3 = r10.zzayp()     // Catch:{ SQLiteException -> 0x0052 }
            com.google.android.gms.internal.zzcjl r3 = r3.zzbau()     // Catch:{ SQLiteException -> 0x0052 }
            java.lang.String r4 = "Got multiple records for app config, expected one. appId"
            java.lang.Object r5 = com.google.android.gms.internal.zzcjj.zzjs(r11)     // Catch:{ SQLiteException -> 0x0052 }
            r3.zzj(r4, r5)     // Catch:{ SQLiteException -> 0x0052 }
        L_0x004c:
            if (r1 == 0) goto L_0x0051
            r1.close()
        L_0x0051:
            return r2
        L_0x0052:
            r2 = move-exception
            goto L_0x0058
        L_0x0054:
            r11 = move-exception
            goto L_0x0071
        L_0x0056:
            r2 = move-exception
            r1 = r0
        L_0x0058:
            com.google.android.gms.internal.zzcjj r3 = r10.zzayp()     // Catch:{ all -> 0x006f }
            com.google.android.gms.internal.zzcjl r3 = r3.zzbau()     // Catch:{ all -> 0x006f }
            java.lang.String r4 = "Error querying remote config. appId"
            java.lang.Object r11 = com.google.android.gms.internal.zzcjj.zzjs(r11)     // Catch:{ all -> 0x006f }
            r3.zze(r4, r11, r2)     // Catch:{ all -> 0x006f }
            if (r1 == 0) goto L_0x006e
            r1.close()
        L_0x006e:
            return r0
        L_0x006f:
            r11 = move-exception
            r0 = r1
        L_0x0071:
            if (r0 == 0) goto L_0x0076
            r0.close()
        L_0x0076:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcil.zzjl(java.lang.String):byte[]");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0098  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, com.google.android.gms.internal.zzcof> zzjm(java.lang.String r12) {
        /*
            r11 = this;
            r11.zzyk()
            r11.zzwj()
            com.google.android.gms.common.internal.zzbq.zzgv(r12)
            android.database.sqlite.SQLiteDatabase r0 = r11.getWritableDatabase()
            r8 = 0
            java.lang.String r1 = "audience_filter_values"
            java.lang.String r2 = "audience_id"
            java.lang.String r3 = "current_results"
            java.lang.String[] r2 = new java.lang.String[]{r2, r3}     // Catch:{ SQLiteException -> 0x007b, all -> 0x0079 }
            java.lang.String r3 = "app_id=?"
            r9 = 1
            java.lang.String[] r4 = new java.lang.String[r9]     // Catch:{ SQLiteException -> 0x007b, all -> 0x0079 }
            r10 = 0
            r4[r10] = r12     // Catch:{ SQLiteException -> 0x007b, all -> 0x0079 }
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x007b, all -> 0x0079 }
            boolean r1 = r0.moveToFirst()     // Catch:{ SQLiteException -> 0x0077 }
            if (r1 != 0) goto L_0x0033
            if (r0 == 0) goto L_0x0032
            r0.close()
        L_0x0032:
            return r8
        L_0x0033:
            androidx.collection.ArrayMap r1 = new androidx.collection.ArrayMap     // Catch:{ SQLiteException -> 0x0077 }
            r1.<init>()     // Catch:{ SQLiteException -> 0x0077 }
        L_0x0038:
            int r2 = r0.getInt(r10)     // Catch:{ SQLiteException -> 0x0077 }
            byte[] r3 = r0.getBlob(r9)     // Catch:{ SQLiteException -> 0x0077 }
            int r4 = r3.length     // Catch:{ SQLiteException -> 0x0077 }
            com.google.android.gms.internal.zzflj r3 = com.google.android.gms.internal.zzflj.zzo(r3, r10, r4)     // Catch:{ SQLiteException -> 0x0077 }
            com.google.android.gms.internal.zzcof r4 = new com.google.android.gms.internal.zzcof     // Catch:{ SQLiteException -> 0x0077 }
            r4.<init>()     // Catch:{ SQLiteException -> 0x0077 }
            r4.zza((com.google.android.gms.internal.zzflj) r3)     // Catch:{ IOException -> 0x0055 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x0077 }
            r1.put(r2, r4)     // Catch:{ SQLiteException -> 0x0077 }
            goto L_0x006b
        L_0x0055:
            r3 = move-exception
            com.google.android.gms.internal.zzcjj r4 = r11.zzayp()     // Catch:{ SQLiteException -> 0x0077 }
            com.google.android.gms.internal.zzcjl r4 = r4.zzbau()     // Catch:{ SQLiteException -> 0x0077 }
            java.lang.String r5 = "Failed to merge filter results. appId, audienceId, error"
            java.lang.Object r6 = com.google.android.gms.internal.zzcjj.zzjs(r12)     // Catch:{ SQLiteException -> 0x0077 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x0077 }
            r4.zzd(r5, r6, r2, r3)     // Catch:{ SQLiteException -> 0x0077 }
        L_0x006b:
            boolean r2 = r0.moveToNext()     // Catch:{ SQLiteException -> 0x0077 }
            if (r2 != 0) goto L_0x0038
            if (r0 == 0) goto L_0x0076
            r0.close()
        L_0x0076:
            return r1
        L_0x0077:
            r1 = move-exception
            goto L_0x007d
        L_0x0079:
            r12 = move-exception
            goto L_0x0096
        L_0x007b:
            r1 = move-exception
            r0 = r8
        L_0x007d:
            com.google.android.gms.internal.zzcjj r2 = r11.zzayp()     // Catch:{ all -> 0x0094 }
            com.google.android.gms.internal.zzcjl r2 = r2.zzbau()     // Catch:{ all -> 0x0094 }
            java.lang.String r3 = "Database error querying filter results. appId"
            java.lang.Object r12 = com.google.android.gms.internal.zzcjj.zzjs(r12)     // Catch:{ all -> 0x0094 }
            r2.zze(r3, r12, r1)     // Catch:{ all -> 0x0094 }
            if (r0 == 0) goto L_0x0093
            r0.close()
        L_0x0093:
            return r8
        L_0x0094:
            r12 = move-exception
            r8 = r0
        L_0x0096:
            if (r8 == 0) goto L_0x009b
            r8.close()
        L_0x009b:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcil.zzjm(java.lang.String):java.util.Map");
    }

    public final long zzjn(String str) {
        zzbq.zzgv(str);
        return zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b0 A[LOOP:0: B:18:0x0052->B:38:0x00b0, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00b2 A[EDGE_INSN: B:53:0x00b2->B:39:0x00b2 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<android.util.Pair<com.google.android.gms.internal.zzcoe, java.lang.Long>> zzl(java.lang.String r13, int r14, int r15) {
        /*
            r12 = this;
            r12.zzwj()
            r12.zzyk()
            r0 = 1
            r1 = 0
            if (r14 <= 0) goto L_0x000c
            r2 = 1
            goto L_0x000d
        L_0x000c:
            r2 = 0
        L_0x000d:
            com.google.android.gms.common.internal.zzbq.checkArgument(r2)
            if (r15 <= 0) goto L_0x0014
            r2 = 1
            goto L_0x0015
        L_0x0014:
            r2 = 0
        L_0x0015:
            com.google.android.gms.common.internal.zzbq.checkArgument(r2)
            com.google.android.gms.common.internal.zzbq.zzgv(r13)
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r12.getWritableDatabase()     // Catch:{ SQLiteException -> 0x00ba }
            java.lang.String r4 = "queue"
            java.lang.String r5 = "rowid"
            java.lang.String r6 = "data"
            java.lang.String[] r5 = new java.lang.String[]{r5, r6}     // Catch:{ SQLiteException -> 0x00ba }
            java.lang.String r6 = "app_id=?"
            java.lang.String[] r7 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x00ba }
            r7[r1] = r13     // Catch:{ SQLiteException -> 0x00ba }
            r8 = 0
            r9 = 0
            java.lang.String r10 = "rowid"
            java.lang.String r11 = java.lang.String.valueOf(r14)     // Catch:{ SQLiteException -> 0x00ba }
            android.database.Cursor r2 = r3.query(r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ SQLiteException -> 0x00ba }
            boolean r14 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x00ba }
            if (r14 != 0) goto L_0x004c
            java.util.List r13 = java.util.Collections.emptyList()     // Catch:{ SQLiteException -> 0x00ba }
            if (r2 == 0) goto L_0x004b
            r2.close()
        L_0x004b:
            return r13
        L_0x004c:
            java.util.ArrayList r14 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x00ba }
            r14.<init>()     // Catch:{ SQLiteException -> 0x00ba }
            r3 = 0
        L_0x0052:
            long r4 = r2.getLong(r1)     // Catch:{ SQLiteException -> 0x00ba }
            byte[] r6 = r2.getBlob(r0)     // Catch:{ IOException -> 0x009a }
            com.google.android.gms.internal.zzcno r7 = r12.zzayl()     // Catch:{ IOException -> 0x009a }
            byte[] r6 = r7.zzs(r6)     // Catch:{ IOException -> 0x009a }
            boolean r7 = r14.isEmpty()     // Catch:{ SQLiteException -> 0x00ba }
            if (r7 != 0) goto L_0x006c
            int r7 = r6.length     // Catch:{ SQLiteException -> 0x00ba }
            int r7 = r7 + r3
            if (r7 > r15) goto L_0x00b2
        L_0x006c:
            int r7 = r6.length     // Catch:{ SQLiteException -> 0x00ba }
            com.google.android.gms.internal.zzflj r7 = com.google.android.gms.internal.zzflj.zzo(r6, r1, r7)     // Catch:{ SQLiteException -> 0x00ba }
            com.google.android.gms.internal.zzcoe r8 = new com.google.android.gms.internal.zzcoe     // Catch:{ SQLiteException -> 0x00ba }
            r8.<init>()     // Catch:{ SQLiteException -> 0x00ba }
            r8.zza((com.google.android.gms.internal.zzflj) r7)     // Catch:{ IOException -> 0x0087 }
            int r6 = r6.length     // Catch:{ SQLiteException -> 0x00ba }
            int r3 = r3 + r6
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ SQLiteException -> 0x00ba }
            android.util.Pair r4 = android.util.Pair.create(r8, r4)     // Catch:{ SQLiteException -> 0x00ba }
            r14.add(r4)     // Catch:{ SQLiteException -> 0x00ba }
            goto L_0x00aa
        L_0x0087:
            r4 = move-exception
            com.google.android.gms.internal.zzcjj r5 = r12.zzayp()     // Catch:{ SQLiteException -> 0x00ba }
            com.google.android.gms.internal.zzcjl r5 = r5.zzbau()     // Catch:{ SQLiteException -> 0x00ba }
            java.lang.String r6 = "Failed to merge queued bundle. appId"
            java.lang.Object r7 = com.google.android.gms.internal.zzcjj.zzjs(r13)     // Catch:{ SQLiteException -> 0x00ba }
        L_0x0096:
            r5.zze(r6, r7, r4)     // Catch:{ SQLiteException -> 0x00ba }
            goto L_0x00aa
        L_0x009a:
            r4 = move-exception
            com.google.android.gms.internal.zzcjj r5 = r12.zzayp()     // Catch:{ SQLiteException -> 0x00ba }
            com.google.android.gms.internal.zzcjl r5 = r5.zzbau()     // Catch:{ SQLiteException -> 0x00ba }
            java.lang.String r6 = "Failed to unzip queued bundle. appId"
            java.lang.Object r7 = com.google.android.gms.internal.zzcjj.zzjs(r13)     // Catch:{ SQLiteException -> 0x00ba }
            goto L_0x0096
        L_0x00aa:
            boolean r4 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x00ba }
            if (r4 == 0) goto L_0x00b2
            if (r3 <= r15) goto L_0x0052
        L_0x00b2:
            if (r2 == 0) goto L_0x00b7
            r2.close()
        L_0x00b7:
            return r14
        L_0x00b8:
            r13 = move-exception
            goto L_0x00d6
        L_0x00ba:
            r14 = move-exception
            com.google.android.gms.internal.zzcjj r15 = r12.zzayp()     // Catch:{ all -> 0x00b8 }
            com.google.android.gms.internal.zzcjl r15 = r15.zzbau()     // Catch:{ all -> 0x00b8 }
            java.lang.String r0 = "Error querying bundles. appId"
            java.lang.Object r13 = com.google.android.gms.internal.zzcjj.zzjs(r13)     // Catch:{ all -> 0x00b8 }
            r15.zze(r0, r13, r14)     // Catch:{ all -> 0x00b8 }
            java.util.List r13 = java.util.Collections.emptyList()     // Catch:{ all -> 0x00b8 }
            if (r2 == 0) goto L_0x00d5
            r2.close()
        L_0x00d5:
            return r13
        L_0x00d6:
            if (r2 == 0) goto L_0x00db
            r2.close()
        L_0x00db:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcil.zzl(java.lang.String, int, int):java.util.List");
    }
}
