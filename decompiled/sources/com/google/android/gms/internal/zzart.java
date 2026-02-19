package com.google.android.gms.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzo;
import java.io.Closeable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class zzart extends zzari implements Closeable {
    /* access modifiers changed from: private */
    public static final String zzdzv = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER);", new Object[]{"hits2", "hit_id", "hit_time", "hit_url", "hit_string", "hit_app_id"});
    private static final String zzdzw = String.format("SELECT MAX(%s) FROM %s WHERE 1;", new Object[]{"hit_time", "hits2"});
    private final zzaru zzdzx;
    private final zzatp zzdzy = new zzatp(zzxx());
    /* access modifiers changed from: private */
    public final zzatp zzdzz = new zzatp(zzxx());

    zzart(zzark zzark) {
        super(zzark);
        this.zzdzx = new zzaru(this, zzark.getContext(), "google_analytics_v4.db");
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
            } else if (rawQuery == null) {
                return 0;
            } else {
                rawQuery.close();
                return 0;
            }
        } catch (SQLiteException e) {
            zzd("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private final long zzc(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().rawQuery(str, (String[]) null);
            if (cursor.moveToFirst()) {
                long j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
                return j;
            }
            throw new SQLiteException("Database returned empty set");
        } catch (SQLiteException e) {
            zzd("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private final Map<String, String> zzef(String str) {
        if (TextUtils.isEmpty(str)) {
            return new HashMap(0);
        }
        try {
            if (!str.startsWith("?")) {
                String valueOf = String.valueOf(str);
                str = valueOf.length() != 0 ? "?".concat(valueOf) : new String("?");
            }
            return zzo.zza(new URI(str), "UTF-8");
        } catch (URISyntaxException e) {
            zze("Error parsing hit parameters", e);
            return new HashMap(0);
        }
    }

    private final Map<String, String> zzeg(String str) {
        if (TextUtils.isEmpty(str)) {
            return new HashMap(0);
        }
        try {
            String valueOf = String.valueOf(str);
            return zzo.zza(new URI(valueOf.length() != 0 ? "?".concat(valueOf) : new String("?")), "UTF-8");
        } catch (URISyntaxException e) {
            zze("Error parsing property parameters", e);
            return new HashMap(0);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x005b, code lost:
        if (r10 != null) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0066, code lost:
        if (r10 == null) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0068, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006b, code lost:
        return r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.List<java.lang.Long> zzn(long r14) {
        /*
            r13 = this;
            java.lang.String r0 = "hit_id"
            com.google.android.gms.analytics.zzk.zzwj()
            r13.zzyk()
            r1 = 0
            int r3 = (r14 > r1 ? 1 : (r14 == r1 ? 0 : -1))
            if (r3 > 0) goto L_0x0013
            java.util.List r14 = java.util.Collections.emptyList()
            return r14
        L_0x0013:
            android.database.sqlite.SQLiteDatabase r1 = r13.getWritableDatabase()
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            r10 = 0
            java.lang.String r2 = "hits2"
            java.lang.String[] r3 = new java.lang.String[]{r0}     // Catch:{ SQLiteException -> 0x0060 }
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            java.lang.String r8 = "%s ASC"
            r11 = 1
            java.lang.Object[] r11 = new java.lang.Object[r11]     // Catch:{ SQLiteException -> 0x0060 }
            r12 = 0
            r11[r12] = r0     // Catch:{ SQLiteException -> 0x0060 }
            java.lang.String r8 = java.lang.String.format(r8, r11)     // Catch:{ SQLiteException -> 0x0060 }
            java.lang.String r14 = java.lang.Long.toString(r14)     // Catch:{ SQLiteException -> 0x0060 }
            r0 = r1
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r14
            android.database.Cursor r10 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x0060 }
            boolean r14 = r10.moveToFirst()     // Catch:{ SQLiteException -> 0x0060 }
            if (r14 == 0) goto L_0x005b
        L_0x004a:
            long r14 = r10.getLong(r12)     // Catch:{ SQLiteException -> 0x0060 }
            java.lang.Long r14 = java.lang.Long.valueOf(r14)     // Catch:{ SQLiteException -> 0x0060 }
            r9.add(r14)     // Catch:{ SQLiteException -> 0x0060 }
            boolean r14 = r10.moveToNext()     // Catch:{ SQLiteException -> 0x0060 }
            if (r14 != 0) goto L_0x004a
        L_0x005b:
            if (r10 == 0) goto L_0x006b
            goto L_0x0068
        L_0x005e:
            r14 = move-exception
            goto L_0x006c
        L_0x0060:
            r14 = move-exception
            java.lang.String r15 = "Error selecting hit ids"
            r13.zzd(r15, r14)     // Catch:{ all -> 0x005e }
            if (r10 == 0) goto L_0x006b
        L_0x0068:
            r10.close()
        L_0x006b:
            return r9
        L_0x006c:
            if (r10 == 0) goto L_0x0071
            r10.close()
        L_0x0071:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzart.zzn(long):java.util.List");
    }

    private final long zzyu() {
        zzk.zzwj();
        zzyk();
        return zzc("SELECT COUNT(*) FROM hits2", (String[]) null);
    }

    /* access modifiers changed from: private */
    public static String zzzb() {
        return "google_analytics_v4.db";
    }

    public final void beginTransaction() {
        zzyk();
        getWritableDatabase().beginTransaction();
    }

    public final void close() {
        String str;
        try {
            this.zzdzx.close();
        } catch (SQLiteException e) {
            str = "Sql error closing database";
            zze(str, e);
        } catch (IllegalStateException e2) {
            str = "Error closing database";
            zze(str, e2);
        }
    }

    public final void endTransaction() {
        zzyk();
        getWritableDatabase().endTransaction();
    }

    /* access modifiers changed from: package-private */
    public final SQLiteDatabase getWritableDatabase() {
        try {
            return this.zzdzx.getWritableDatabase();
        } catch (SQLiteException e) {
            zzd("Error opening database", e);
            throw e;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean isEmpty() {
        return zzyu() == 0;
    }

    public final void setTransactionSuccessful() {
        zzyk();
        getWritableDatabase().setTransactionSuccessful();
    }

    public final long zza(long j, String str, String str2) {
        zzbq.zzgv(str);
        zzbq.zzgv(str2);
        zzyk();
        zzk.zzwj();
        return zza("SELECT hits_count FROM properties WHERE app_uid=? AND cid=? AND tid=?", new String[]{String.valueOf(j), str, str2}, 0);
    }

    public final void zzc(zzasy zzasy) {
        zzbq.checkNotNull(zzasy);
        zzk.zzwj();
        zzyk();
        zzbq.checkNotNull(zzasy);
        Uri.Builder builder = new Uri.Builder();
        for (Map.Entry next : zzasy.zzjq().entrySet()) {
            String str = (String) next.getKey();
            if (!"ht".equals(str) && !"qt".equals(str) && !"AppUID".equals(str)) {
                builder.appendQueryParameter(str, (String) next.getValue());
            }
        }
        String encodedQuery = builder.build().getEncodedQuery();
        if (encodedQuery == null) {
            encodedQuery = "";
        }
        if (encodedQuery.length() > 8192) {
            zzxy().zza(zzasy, "Hit length exceeds the maximum allowed size");
            return;
        }
        int intValue = zzast.zzebq.get().intValue();
        long zzyu = zzyu();
        if (zzyu > ((long) (intValue - 1))) {
            List<Long> zzn = zzn((zzyu - ((long) intValue)) + 1);
            zzd("Store full, deleting hits to make room, count", Integer.valueOf(zzn.size()));
            zzs(zzn);
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hit_string", encodedQuery);
        contentValues.put("hit_time", Long.valueOf(zzasy.zzaan()));
        contentValues.put("hit_app_id", Integer.valueOf(zzasy.zzaal()));
        contentValues.put("hit_url", zzasy.zzaap() ? zzasl.zzaab() : zzasl.zzaac());
        try {
            long insert = writableDatabase.insert("hits2", (String) null, contentValues);
            if (insert == -1) {
                zzee("Failed to insert a hit (got -1)");
            } else {
                zzb("Hit saved to database. db-id, hit", Long.valueOf(insert), zzasy);
            }
        } catch (SQLiteException e) {
            zze("Error storing a hit", e);
        }
    }

    public final List<zzasy> zzo(long j) {
        zzbq.checkArgument(j >= 0);
        zzk.zzwj();
        zzyk();
        Cursor cursor = null;
        try {
            Cursor query = getWritableDatabase().query("hits2", new String[]{"hit_id", "hit_time", "hit_string", "hit_url", "hit_app_id"}, (String) null, (String[]) null, (String) null, (String) null, String.format("%s ASC", new Object[]{"hit_id"}), Long.toString(j));
            ArrayList arrayList = new ArrayList();
            if (query.moveToFirst()) {
                do {
                    long j2 = query.getLong(0);
                    arrayList.add(new zzasy(this, zzef(query.getString(2)), query.getLong(1), zzatt.zzer(query.getString(3)), j2, query.getInt(4)));
                } while (query.moveToNext());
            }
            if (query != null) {
                query.close();
            }
            return arrayList;
        } catch (SQLiteException e) {
            zze("Error loading hits from the database", e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public final void zzp(long j) {
        zzk.zzwj();
        zzyk();
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Long.valueOf(j));
        zza("Deleting hit, id", Long.valueOf(j));
        zzs(arrayList);
    }

    public final List<zzarn> zzq(long j) {
        zzyk();
        zzk.zzwj();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor cursor = null;
        try {
            int intValue = zzast.zzebs.get().intValue();
            Cursor query = writableDatabase.query("properties", new String[]{"cid", "tid", "adid", "hits_count", "params"}, "app_uid=?", new String[]{"0"}, (String) null, (String) null, (String) null, String.valueOf(intValue));
            ArrayList arrayList = new ArrayList();
            if (query.moveToFirst()) {
                do {
                    String string = query.getString(0);
                    String string2 = query.getString(1);
                    boolean z = query.getInt(2) != 0;
                    long j2 = (long) query.getInt(3);
                    Map<String, String> zzeg = zzeg(query.getString(4));
                    if (!TextUtils.isEmpty(string)) {
                        if (!TextUtils.isEmpty(string2)) {
                            arrayList.add(new zzarn(0, string, string2, z, j2, zzeg));
                        }
                    }
                    zzc("Read property with empty client id or tracker id", string, string2);
                } while (query.moveToNext());
            }
            if (arrayList.size() >= intValue) {
                zzed("Sending hits to too many properties. Campaign report might be incorrect");
            }
            if (query != null) {
                query.close();
            }
            return arrayList;
        } catch (SQLiteException e) {
            zze("Error loading hits from the database", e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public final void zzs(List<Long> list) {
        zzbq.checkNotNull(list);
        zzk.zzwj();
        zzyk();
        if (!list.isEmpty()) {
            StringBuilder sb = new StringBuilder("hit_id");
            sb.append(" in (");
            for (int i = 0; i < list.size(); i++) {
                Long l = list.get(i);
                if (l == null || l.longValue() == 0) {
                    throw new SQLiteException("Invalid hit id");
                }
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(l);
            }
            sb.append(")");
            String sb2 = sb.toString();
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                zza("Deleting dispatched hits. count", Integer.valueOf(list.size()));
                int delete = writableDatabase.delete("hits2", sb2, (String[]) null);
                if (delete != list.size()) {
                    zzb("Deleted fewer hits then expected", Integer.valueOf(list.size()), Integer.valueOf(delete), sb2);
                }
            } catch (SQLiteException e) {
                zze("Error deleting hits", e);
                throw e;
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zzwk() {
    }

    public final int zzyz() {
        zzk.zzwj();
        zzyk();
        if (!this.zzdzy.zzu(86400000)) {
            return 0;
        }
        this.zzdzy.start();
        zzea("Deleting stale hits (if any)");
        int delete = getWritableDatabase().delete("hits2", "hit_time < ?", new String[]{Long.toString(zzxx().currentTimeMillis() - 2592000000L)});
        zza("Deleted stale hits, count", Integer.valueOf(delete));
        return delete;
    }

    public final long zzza() {
        zzk.zzwj();
        zzyk();
        return zza(zzdzw, (String[]) null, 0);
    }
}
