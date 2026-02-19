package com.google.android.gms.internal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

final class zzaru extends SQLiteOpenHelper {
    private /* synthetic */ zzart zzeaa;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaru(zzart zzart, Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        this.zzeaa = zzart;
    }

    private static void zza(SQLiteDatabase sQLiteDatabase) {
        Set<String> zzb = zzb(sQLiteDatabase, "properties");
        String[] strArr = {"app_uid", "cid", "tid", "params", "adid", "hits_count"};
        for (int i = 0; i < 6; i++) {
            String str = strArr[i];
            if (!zzb.remove(str)) {
                String valueOf = String.valueOf(str);
                throw new SQLiteException(valueOf.length() != 0 ? "Database properties is missing required column: ".concat(valueOf) : new String("Database properties is missing required column: "));
            }
        }
        if (!zzb.isEmpty()) {
            throw new SQLiteException("Database properties table has extra columns");
        }
    }

    private final boolean zza(SQLiteDatabase sQLiteDatabase, String str) {
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
            this.zzeaa.zzc("Error querying for table", str, e);
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
    }

    private static Set<String> zzb(SQLiteDatabase sQLiteDatabase, String str) {
        HashSet hashSet = new HashSet();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 22);
        sb.append("SELECT * FROM ");
        sb.append(str);
        sb.append(" LIMIT 0");
        Cursor rawQuery = sQLiteDatabase.rawQuery(sb.toString(), (String[]) null);
        try {
            String[] columnNames = rawQuery.getColumnNames();
            for (String add : columnNames) {
                hashSet.add(add);
            }
            return hashSet;
        } finally {
            rawQuery.close();
        }
    }

    public final SQLiteDatabase getWritableDatabase() {
        if (this.zzeaa.zzdzz.zzu(3600000)) {
            try {
                return super.getWritableDatabase();
            } catch (SQLiteException unused) {
                this.zzeaa.zzdzz.start();
                this.zzeaa.zzee("Opening the database failed, dropping the table and recreating it");
                this.zzeaa.getContext().getDatabasePath(zzart.zzzb()).delete();
                try {
                    SQLiteDatabase writableDatabase = super.getWritableDatabase();
                    this.zzeaa.zzdzz.clear();
                    return writableDatabase;
                } catch (SQLiteException e) {
                    this.zzeaa.zze("Failed to open freshly created database", e);
                    throw e;
                }
            }
        } else {
            throw new SQLiteException("Database open failed");
        }
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        String path = sQLiteDatabase.getPath();
        if (zzass.version() >= 9) {
            File file = new File(path);
            file.setReadable(false, false);
            file.setWritable(false, false);
            file.setReadable(true, true);
            file.setWritable(true, true);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0086  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onOpen(android.database.sqlite.SQLiteDatabase r6) {
        /*
            r5 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 15
            if (r0 >= r1) goto L_0x0019
            r0 = 0
            java.lang.String r1 = "PRAGMA journal_mode=memory"
            android.database.Cursor r0 = r6.rawQuery(r1, r0)
            r0.moveToFirst()     // Catch:{ all -> 0x0014 }
            r0.close()
            goto L_0x0019
        L_0x0014:
            r6 = move-exception
            r0.close()
            throw r6
        L_0x0019:
            java.lang.String r0 = "hits2"
            boolean r1 = r5.zza(r6, r0)
            if (r1 != 0) goto L_0x0029
            java.lang.String r0 = com.google.android.gms.internal.zzart.zzdzv
        L_0x0025:
            r6.execSQL(r0)
            goto L_0x0078
        L_0x0029:
            java.util.Set r0 = zzb(r6, r0)
            java.lang.String r1 = "hit_id"
            java.lang.String r2 = "hit_string"
            java.lang.String r3 = "hit_time"
            java.lang.String r4 = "hit_url"
            java.lang.String[] r1 = new java.lang.String[]{r1, r2, r3, r4}
            r2 = 0
        L_0x003a:
            r3 = 4
            if (r2 >= r3) goto L_0x0065
            r3 = r1[r2]
            boolean r4 = r0.remove(r3)
            if (r4 != 0) goto L_0x0062
            android.database.sqlite.SQLiteException r6 = new android.database.sqlite.SQLiteException
            java.lang.String r0 = "Database hits2 is missing required column: "
            java.lang.String r1 = java.lang.String.valueOf(r3)
            int r2 = r1.length()
            if (r2 == 0) goto L_0x0058
            java.lang.String r0 = r0.concat(r1)
            goto L_0x005e
        L_0x0058:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r0)
            r0 = r1
        L_0x005e:
            r6.<init>(r0)
            throw r6
        L_0x0062:
            int r2 = r2 + 1
            goto L_0x003a
        L_0x0065:
            java.lang.String r1 = "hit_app_id"
            boolean r1 = r0.remove(r1)
            r1 = r1 ^ 1
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x008a
            if (r1 == 0) goto L_0x0078
            java.lang.String r0 = "ALTER TABLE hits2 ADD COLUMN hit_app_id INTEGER"
            goto L_0x0025
        L_0x0078:
            java.lang.String r0 = "properties"
            boolean r0 = r5.zza(r6, r0)
            if (r0 != 0) goto L_0x0086
            java.lang.String r0 = "CREATE TABLE IF NOT EXISTS properties ( app_uid INTEGER NOT NULL, cid TEXT NOT NULL, tid TEXT NOT NULL, params TEXT NOT NULL, adid INTEGER NOT NULL, hits_count INTEGER NOT NULL, PRIMARY KEY (app_uid, cid, tid)) ;"
            r6.execSQL(r0)
            return
        L_0x0086:
            zza(r6)
            return
        L_0x008a:
            android.database.sqlite.SQLiteException r6 = new android.database.sqlite.SQLiteException
            java.lang.String r0 = "Database hits2 has extra columns"
            r6.<init>(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaru.onOpen(android.database.sqlite.SQLiteDatabase):void");
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
