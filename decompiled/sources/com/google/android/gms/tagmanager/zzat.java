package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.tagmanager.DataLayer;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

final class zzat implements DataLayer.zzc {
    /* access modifiers changed from: private */
    public static final String zzkot = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' STRING NOT NULL, '%s' BLOB NOT NULL, '%s' INTEGER NOT NULL);", new Object[]{"datalayer", "ID", "key", FirebaseAnalytics.Param.VALUE, "expires"});
    /* access modifiers changed from: private */
    public final Context mContext;
    private zze zzata;
    private final Executor zzkou;
    private zzax zzkov;
    private int zzkow;

    public zzat(Context context) {
        this(context, zzi.zzanq(), "google_tagmanager.db", 2000, Executors.newSingleThreadExecutor());
    }

    private zzat(Context context, zze zze, String str, int i, Executor executor) {
        this.mContext = context;
        this.zzata = zze;
        this.zzkow = 2000;
        this.zzkou = executor;
        this.zzkov = new zzax(this, context, str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x001f A[SYNTHETIC, Splitter:B:13:0x001f] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0029 A[SYNTHETIC, Splitter:B:22:0x0029] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] zzaj(java.lang.Object r3) {
        /*
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            r1 = 0
            java.io.ObjectOutputStream r2 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x0026, all -> 0x001c }
            r2.<init>(r0)     // Catch:{ IOException -> 0x0026, all -> 0x001c }
            r2.writeObject(r3)     // Catch:{ IOException -> 0x0027, all -> 0x0019 }
            byte[] r3 = r0.toByteArray()     // Catch:{ IOException -> 0x0027, all -> 0x0019 }
            r2.close()     // Catch:{ IOException -> 0x0018 }
            r0.close()     // Catch:{ IOException -> 0x0018 }
        L_0x0018:
            return r3
        L_0x0019:
            r3 = move-exception
            r1 = r2
            goto L_0x001d
        L_0x001c:
            r3 = move-exception
        L_0x001d:
            if (r1 == 0) goto L_0x0022
            r1.close()     // Catch:{ IOException -> 0x0025 }
        L_0x0022:
            r0.close()     // Catch:{ IOException -> 0x0025 }
        L_0x0025:
            throw r3
        L_0x0026:
            r2 = r1
        L_0x0027:
            if (r2 == 0) goto L_0x002c
            r2.close()     // Catch:{ IOException -> 0x002f }
        L_0x002c:
            r0.close()     // Catch:{ IOException -> 0x002f }
        L_0x002f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzat.zzaj(java.lang.Object):byte[]");
    }

    /* access modifiers changed from: private */
    public final synchronized void zzb(List<zzay> list, long j) {
        long currentTimeMillis = this.zzata.currentTimeMillis();
        String[] strArr = null;
        try {
            zzbh(currentTimeMillis);
            int zzbgg = (zzbgg() - this.zzkow) + list.size();
            if (zzbgg > 0) {
                List<String> zzez = zzez(zzbgg);
                int size = zzez.size();
                StringBuilder sb = new StringBuilder(64);
                sb.append("DataLayer store full, deleting ");
                sb.append(size);
                sb.append(" entries to make room.");
                zzdj.zzcy(sb.toString());
                strArr = (String[]) zzez.toArray(new String[0]);
                if (strArr != null) {
                    if (strArr.length != 0) {
                        SQLiteDatabase zzlq = zzlq("Error opening database for deleteEntries.");
                        if (zzlq != null) {
                            zzlq.delete("datalayer", String.format("%s in (%s)", new Object[]{"ID", TextUtils.join(",", Collections.nCopies(strArr.length, "?"))}), strArr);
                        }
                    }
                }
            }
        } catch (SQLiteException unused) {
            String valueOf = String.valueOf(Arrays.toString(strArr));
            zzdj.zzcz(valueOf.length() != 0 ? "Error deleting entries ".concat(valueOf) : new String("Error deleting entries "));
        } catch (Throwable th) {
            zzbgh();
            throw th;
        }
        zzc(list, currentTimeMillis + j);
        zzbgh();
    }

    /* access modifiers changed from: private */
    public final List<DataLayer.zza> zzbge() {
        try {
            zzbh(this.zzata.currentTimeMillis());
            List<zzay> zzbgf = zzbgf();
            ArrayList arrayList = new ArrayList();
            for (zzay next : zzbgf) {
                arrayList.add(new DataLayer.zza(next.zzbkr, zzy(next.zzkpc)));
            }
            return arrayList;
        } finally {
            zzbgh();
        }
    }

    private final List<zzay> zzbgf() {
        SQLiteDatabase zzlq = zzlq("Error opening database for loadSerialized.");
        ArrayList arrayList = new ArrayList();
        if (zzlq == null) {
            return arrayList;
        }
        Cursor query = zzlq.query("datalayer", new String[]{"key", FirebaseAnalytics.Param.VALUE}, (String) null, (String[]) null, (String) null, (String) null, "ID", (String) null);
        while (query.moveToNext()) {
            try {
                arrayList.add(new zzay(query.getString(0), query.getBlob(1)));
            } finally {
                query.close();
            }
        }
        return arrayList;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.String[], android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r2v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r2v2, types: [android.database.Cursor] */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0029, code lost:
        if (r2 == 0) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002c, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r2 != 0) goto L_0x001e;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzbgg() {
        /*
            r4 = this;
            java.lang.String r0 = "Error opening database for getNumStoredEntries."
            android.database.sqlite.SQLiteDatabase r0 = r4.zzlq(r0)
            r1 = 0
            if (r0 != 0) goto L_0x000a
            return r1
        L_0x000a:
            r2 = 0
            java.lang.String r3 = "SELECT COUNT(*) from datalayer"
            android.database.Cursor r2 = r0.rawQuery(r3, r2)     // Catch:{ SQLiteException -> 0x0024 }
            boolean r0 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x0024 }
            if (r0 == 0) goto L_0x001c
            long r0 = r2.getLong(r1)     // Catch:{ SQLiteException -> 0x0024 }
            int r1 = (int) r0
        L_0x001c:
            if (r2 == 0) goto L_0x002c
        L_0x001e:
            r2.close()
            goto L_0x002c
        L_0x0022:
            r0 = move-exception
            goto L_0x002d
        L_0x0024:
            java.lang.String r0 = "Error getting numStoredEntries"
            com.google.android.gms.tagmanager.zzdj.zzcz(r0)     // Catch:{ all -> 0x0022 }
            if (r2 == 0) goto L_0x002c
            goto L_0x001e
        L_0x002c:
            return r1
        L_0x002d:
            if (r2 == 0) goto L_0x0032
            r2.close()
        L_0x0032:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzat.zzbgg():int");
    }

    private final void zzbgh() {
        try {
            this.zzkov.close();
        } catch (SQLiteException unused) {
        }
    }

    private final void zzbh(long j) {
        SQLiteDatabase zzlq = zzlq("Error opening database for deleteOlderThan.");
        if (zzlq != null) {
            try {
                int delete = zzlq.delete("datalayer", "expires <= ?", new String[]{Long.toString(j)});
                StringBuilder sb = new StringBuilder(33);
                sb.append("Deleted ");
                sb.append(delete);
                sb.append(" expired items");
                zzdj.v(sb.toString());
            } catch (SQLiteException unused) {
                zzdj.zzcz("Error deleting old entries.");
            }
        }
    }

    private final void zzc(List<zzay> list, long j) {
        SQLiteDatabase zzlq = zzlq("Error opening database for writeEntryToDatabase.");
        if (zzlq != null) {
            for (zzay next : list) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("expires", Long.valueOf(j));
                contentValues.put("key", next.zzbkr);
                contentValues.put(FirebaseAnalytics.Param.VALUE, next.zzkpc);
                zzlq.insert("datalayer", (String) null, contentValues);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004e, code lost:
        if (r2 != null) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0071, code lost:
        if (r2 == null) goto L_0x0076;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0073, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0076, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.List<java.lang.String> zzez(int r14) {
        /*
            r13 = this;
            java.lang.String r0 = "ID"
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            if (r14 > 0) goto L_0x000f
            java.lang.String r14 = "Invalid maxEntries specified. Skipping."
            com.google.android.gms.tagmanager.zzdj.zzcz(r14)
            return r1
        L_0x000f:
            java.lang.String r2 = "Error opening database for peekEntryIds."
            android.database.sqlite.SQLiteDatabase r3 = r13.zzlq(r2)
            if (r3 != 0) goto L_0x0018
            return r1
        L_0x0018:
            r2 = 0
            java.lang.String r4 = "datalayer"
            java.lang.String[] r5 = new java.lang.String[]{r0}     // Catch:{ SQLiteException -> 0x0053 }
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            java.lang.String r10 = "%s ASC"
            r11 = 1
            java.lang.Object[] r11 = new java.lang.Object[r11]     // Catch:{ SQLiteException -> 0x0053 }
            r12 = 0
            r11[r12] = r0     // Catch:{ SQLiteException -> 0x0053 }
            java.lang.String r10 = java.lang.String.format(r10, r11)     // Catch:{ SQLiteException -> 0x0053 }
            java.lang.String r11 = java.lang.Integer.toString(r14)     // Catch:{ SQLiteException -> 0x0053 }
            android.database.Cursor r2 = r3.query(r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ SQLiteException -> 0x0053 }
            boolean r14 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x0053 }
            if (r14 == 0) goto L_0x004e
        L_0x003d:
            long r3 = r2.getLong(r12)     // Catch:{ SQLiteException -> 0x0053 }
            java.lang.String r14 = java.lang.String.valueOf(r3)     // Catch:{ SQLiteException -> 0x0053 }
            r1.add(r14)     // Catch:{ SQLiteException -> 0x0053 }
            boolean r14 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x0053 }
            if (r14 != 0) goto L_0x003d
        L_0x004e:
            if (r2 == 0) goto L_0x0076
            goto L_0x0073
        L_0x0051:
            r14 = move-exception
            goto L_0x0077
        L_0x0053:
            r14 = move-exception
            java.lang.String r0 = "Error in peekEntries fetching entryIds: "
            java.lang.String r14 = r14.getMessage()     // Catch:{ all -> 0x0051 }
            java.lang.String r14 = java.lang.String.valueOf(r14)     // Catch:{ all -> 0x0051 }
            int r3 = r14.length()     // Catch:{ all -> 0x0051 }
            if (r3 == 0) goto L_0x0069
            java.lang.String r14 = r0.concat(r14)     // Catch:{ all -> 0x0051 }
            goto L_0x006e
        L_0x0069:
            java.lang.String r14 = new java.lang.String     // Catch:{ all -> 0x0051 }
            r14.<init>(r0)     // Catch:{ all -> 0x0051 }
        L_0x006e:
            com.google.android.gms.tagmanager.zzdj.zzcz(r14)     // Catch:{ all -> 0x0051 }
            if (r2 == 0) goto L_0x0076
        L_0x0073:
            r2.close()
        L_0x0076:
            return r1
        L_0x0077:
            if (r2 == 0) goto L_0x007c
            r2.close()
        L_0x007c:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzat.zzez(int):java.util.List");
    }

    /* access modifiers changed from: private */
    public final void zzlp(String str) {
        SQLiteDatabase zzlq = zzlq("Error opening database for clearKeysWithPrefix.");
        if (zzlq != null) {
            try {
                int delete = zzlq.delete("datalayer", "key = ? OR key LIKE ?", new String[]{str, String.valueOf(str).concat(".%")});
                StringBuilder sb = new StringBuilder(25);
                sb.append("Cleared ");
                sb.append(delete);
                sb.append(" items");
                zzdj.v(sb.toString());
            } catch (SQLiteException e) {
                String valueOf = String.valueOf(e);
                StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 44 + String.valueOf(valueOf).length());
                sb2.append("Error deleting entries with key prefix: ");
                sb2.append(str);
                sb2.append(" (");
                sb2.append(valueOf);
                sb2.append(").");
                zzdj.zzcz(sb2.toString());
            } finally {
                zzbgh();
            }
        }
    }

    private final SQLiteDatabase zzlq(String str) {
        try {
            return this.zzkov.getWritableDatabase();
        } catch (SQLiteException unused) {
            zzdj.zzcz(str);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x001e A[SYNTHETIC, Splitter:B:13:0x001e] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0028 A[SYNTHETIC, Splitter:B:22:0x0028] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0032 A[SYNTHETIC, Splitter:B:31:0x0032] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Object zzy(byte[] r3) {
        /*
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream
            r0.<init>(r3)
            r3 = 0
            java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch:{ IOException -> 0x002f, ClassNotFoundException -> 0x0025, all -> 0x0018 }
            r1.<init>(r0)     // Catch:{ IOException -> 0x002f, ClassNotFoundException -> 0x0025, all -> 0x0018 }
            java.lang.Object r3 = r1.readObject()     // Catch:{ IOException -> 0x0030, ClassNotFoundException -> 0x0026, all -> 0x0016 }
            r1.close()     // Catch:{ IOException -> 0x0015 }
            r0.close()     // Catch:{ IOException -> 0x0015 }
        L_0x0015:
            return r3
        L_0x0016:
            r3 = move-exception
            goto L_0x001c
        L_0x0018:
            r1 = move-exception
            r2 = r1
            r1 = r3
            r3 = r2
        L_0x001c:
            if (r1 == 0) goto L_0x0021
            r1.close()     // Catch:{ IOException -> 0x0024 }
        L_0x0021:
            r0.close()     // Catch:{ IOException -> 0x0024 }
        L_0x0024:
            throw r3
        L_0x0025:
            r1 = r3
        L_0x0026:
            if (r1 == 0) goto L_0x002b
            r1.close()     // Catch:{ IOException -> 0x002e }
        L_0x002b:
            r0.close()     // Catch:{ IOException -> 0x002e }
        L_0x002e:
            return r3
        L_0x002f:
            r1 = r3
        L_0x0030:
            if (r1 == 0) goto L_0x0035
            r1.close()     // Catch:{ IOException -> 0x0038 }
        L_0x0035:
            r0.close()     // Catch:{ IOException -> 0x0038 }
        L_0x0038:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzat.zzy(byte[]):java.lang.Object");
    }

    public final void zza(zzaq zzaq) {
        this.zzkou.execute(new zzav(this, zzaq));
    }

    public final void zza(List<DataLayer.zza> list, long j) {
        ArrayList arrayList = new ArrayList();
        for (DataLayer.zza next : list) {
            arrayList.add(new zzay(next.zzbkr, zzaj(next.mValue)));
        }
        this.zzkou.execute(new zzau(this, arrayList, j));
    }

    public final void zzlo(String str) {
        this.zzkou.execute(new zzaw(this, str));
    }
}
