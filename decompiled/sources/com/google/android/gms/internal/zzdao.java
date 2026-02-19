package com.google.android.gms.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzg;
import com.google.android.gms.common.util.zzi;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

final class zzdao implements zzdac {
    /* access modifiers changed from: private */
    public static final String zzdzv = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT UNIQUE, '%s' TEXT, '%s' TEXT);", new Object[]{"gtm_hits", "hit_id", "hit_time", "hit_url", "hit_first_send_time", "hit_method", "hit_unique_id", "hit_headers", "hit_body"});
    /* access modifiers changed from: private */
    public static final String zzkxb = String.format("CREATE TABLE IF NOT EXISTS %s ('%s' TEXT UNIQUE);", new Object[]{"gtm_hit_unique_ids", "hit_unique_id"});
    /* access modifiers changed from: private */
    public static final String zzkxc = String.format("CREATE TRIGGER IF NOT EXISTS %s DELETE ON %s FOR EACH ROW WHEN OLD.%s NOTNULL BEGIN     INSERT OR IGNORE INTO %s (%s) VALUES (OLD.%s); END;", new Object[]{"save_unique_on_delete", "gtm_hits", "hit_unique_id", "gtm_hit_unique_ids", "hit_unique_id", "hit_unique_id"});
    /* access modifiers changed from: private */
    public static final String zzkxd = String.format("CREATE TRIGGER IF NOT EXISTS %s BEFORE INSERT ON %s FOR EACH ROW WHEN NEW.%s NOT NULL BEGIN     SELECT RAISE(ABORT, 'Duplicate unique ID.')     WHERE EXISTS (SELECT 1 FROM %s WHERE %s = NEW.%s); END;", new Object[]{"check_unique_on_insert", "gtm_hits", "hit_unique_id", "gtm_hit_unique_ids", "hit_unique_id", "hit_unique_id"});
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public zze zzata;
    /* access modifiers changed from: private */
    public final String zzkrd;
    private long zzkre;
    private final int zzkrf;
    private final zzdaq zzkxe;
    private volatile zzczt zzkxf;
    private final zzdad zzkxg;

    zzdao(zzdad zzdad, Context context) {
        this(zzdad, context, "gtm_urls.db", 2000);
    }

    private zzdao(zzdad zzdad, Context context, String str, int i) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.zzkrd = str;
        this.zzkxg = zzdad;
        this.zzata = zzi.zzanq();
        this.zzkxe = new zzdaq(this, applicationContext, str);
        this.zzkxf = new zzdbk(applicationContext, new zzdap(this));
        this.zzkre = 0;
        this.zzkrf = 2000;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0049, code lost:
        if (r9 == null) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004c, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0023, code lost:
        if (r9 != null) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0025, code lost:
        r9.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzbhf() {
        /*
            r10 = this;
            java.lang.String r0 = "Error opening database for getNumStoredHits."
            android.database.sqlite.SQLiteDatabase r1 = r10.zzlq(r0)
            r0 = 0
            if (r1 != 0) goto L_0x000a
            return r0
        L_0x000a:
            r9 = 0
            java.lang.String r2 = "gtm_hits"
            java.lang.String r3 = "hit_id"
            java.lang.String r4 = "hit_first_send_time"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4}     // Catch:{ SQLiteException -> 0x002b }
            java.lang.String r4 = "hit_first_send_time=0"
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r9 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x002b }
            int r0 = r9.getCount()     // Catch:{ SQLiteException -> 0x002b }
            if (r9 == 0) goto L_0x004c
        L_0x0025:
            r9.close()
            goto L_0x004c
        L_0x0029:
            r0 = move-exception
            goto L_0x004d
        L_0x002b:
            r1 = move-exception
            java.lang.String r2 = "Error getting num untried hits: "
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0029 }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x0029 }
            int r3 = r1.length()     // Catch:{ all -> 0x0029 }
            if (r3 == 0) goto L_0x0041
            java.lang.String r1 = r2.concat(r1)     // Catch:{ all -> 0x0029 }
            goto L_0x0046
        L_0x0041:
            java.lang.String r1 = new java.lang.String     // Catch:{ all -> 0x0029 }
            r1.<init>(r2)     // Catch:{ all -> 0x0029 }
        L_0x0046:
            com.google.android.gms.internal.zzdal.zzcz(r1)     // Catch:{ all -> 0x0029 }
            if (r9 == 0) goto L_0x004c
            goto L_0x0025
        L_0x004c:
            return r0
        L_0x004d:
            if (r9 == 0) goto L_0x0052
            r9.close()
        L_0x0052:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdao.zzbhf():int");
    }

    private final void zze(String[] strArr) {
        SQLiteDatabase zzlq;
        if (strArr != null && strArr.length != 0 && (zzlq = zzlq("Error opening database for deleteHits.")) != null) {
            boolean z = true;
            try {
                zzlq.delete("gtm_hits", String.format("HIT_ID in (%s)", new Object[]{TextUtils.join(",", Collections.nCopies(strArr.length, "?"))}), strArr);
                zzdad zzdad = this.zzkxg;
                if (zzmp("gtm_hits") != 0) {
                    z = false;
                }
                zzdad.zzbz(z);
            } catch (SQLiteException e) {
                String valueOf = String.valueOf(e.getMessage());
                zzdal.zzcz(valueOf.length() != 0 ? "Error deleting hits: ".concat(valueOf) : new String("Error deleting hits: "));
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
    private final java.util.List<java.lang.String> zzfe(int r14) {
        /*
            r13 = this;
            java.lang.String r0 = "hit_id"
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            if (r14 > 0) goto L_0x000f
            java.lang.String r14 = "Invalid maxHits specified. Skipping"
            com.google.android.gms.internal.zzdal.zzcz(r14)
            return r1
        L_0x000f:
            java.lang.String r2 = "Error opening database for peekHitIds."
            android.database.sqlite.SQLiteDatabase r3 = r13.zzlq(r2)
            if (r3 != 0) goto L_0x0018
            return r1
        L_0x0018:
            r2 = 0
            java.lang.String r4 = "gtm_hits"
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
            java.lang.String r0 = "Error in peekHits fetching hitIds: "
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
            com.google.android.gms.internal.zzdal.zzcz(r14)     // Catch:{ all -> 0x0051 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdao.zzfe(int):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01f1, code lost:
        r14.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01f8, code lost:
        r14.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01c1, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01c2, code lost:
        r14 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01c7, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01e2, code lost:
        r0 = "Error in peekHits fetching hitIds: ".concat(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x01e7, code lost:
        r0 = new java.lang.String("Error in peekHits fetching hitIds: ");
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x01f1  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01f8  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x017c A[Catch:{ all -> 0x01b7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0181 A[Catch:{ all -> 0x01b7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0198 A[Catch:{ all -> 0x01b7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01b3  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01ba  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x01c1 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:6:0x003a] */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x01e2 A[Catch:{ all -> 0x01f5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x01e7 A[Catch:{ all -> 0x01f5 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.List<com.google.android.gms.internal.zzczx> zzff(int r25) {
        /*
            r24 = this;
            java.lang.String r0 = "%s ASC"
            java.lang.String r1 = "hit_id"
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.lang.String r3 = "Error opening database for peekHits"
            r4 = r24
            android.database.sqlite.SQLiteDatabase r3 = r4.zzlq(r3)
            if (r3 != 0) goto L_0x0014
            return r2
        L_0x0014:
            java.lang.String r6 = "gtm_hits"
            java.lang.String r5 = "hit_time"
            java.lang.String r7 = "hit_first_send_time"
            java.lang.String[] r7 = new java.lang.String[]{r1, r5, r7}     // Catch:{ SQLiteException -> 0x01d0, all -> 0x01cd }
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r15 = 1
            java.lang.Object[] r5 = new java.lang.Object[r15]     // Catch:{ SQLiteException -> 0x01d0, all -> 0x01cd }
            r13 = 0
            r5[r13] = r1     // Catch:{ SQLiteException -> 0x01d0, all -> 0x01cd }
            java.lang.String r12 = java.lang.String.format(r0, r5)     // Catch:{ SQLiteException -> 0x01d0, all -> 0x01cd }
            r16 = 40
            java.lang.String r17 = java.lang.Integer.toString(r16)     // Catch:{ SQLiteException -> 0x01d0, all -> 0x01cd }
            r5 = r3
            r14 = 0
            r13 = r17
            android.database.Cursor r13 = r5.query(r6, r7, r8, r9, r10, r11, r12, r13)     // Catch:{ SQLiteException -> 0x01d0, all -> 0x01cd }
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x01c7, all -> 0x01c1 }
            r12.<init>()     // Catch:{ SQLiteException -> 0x01c7, all -> 0x01c1 }
            boolean r2 = r13.moveToFirst()     // Catch:{ SQLiteException -> 0x01be, all -> 0x01c1 }
            r11 = 2
            if (r2 == 0) goto L_0x006c
        L_0x0046:
            com.google.android.gms.internal.zzczx r2 = new com.google.android.gms.internal.zzczx     // Catch:{ SQLiteException -> 0x0067, all -> 0x0063 }
            long r18 = r13.getLong(r14)     // Catch:{ SQLiteException -> 0x0067, all -> 0x0063 }
            long r20 = r13.getLong(r15)     // Catch:{ SQLiteException -> 0x0067, all -> 0x0063 }
            long r22 = r13.getLong(r11)     // Catch:{ SQLiteException -> 0x0067, all -> 0x0063 }
            r17 = r2
            r17.<init>(r18, r20, r22)     // Catch:{ SQLiteException -> 0x0067, all -> 0x0063 }
            r12.add(r2)     // Catch:{ SQLiteException -> 0x0067, all -> 0x0063 }
            boolean r2 = r13.moveToNext()     // Catch:{ SQLiteException -> 0x0067, all -> 0x0063 }
            if (r2 != 0) goto L_0x0046
            goto L_0x006c
        L_0x0063:
            r0 = move-exception
            r14 = r13
            goto L_0x01f6
        L_0x0067:
            r0 = move-exception
            r2 = r12
            r14 = r13
            goto L_0x01d2
        L_0x006c:
            if (r13 == 0) goto L_0x0071
            r13.close()
        L_0x0071:
            java.lang.String r6 = "gtm_hits"
            java.lang.String r2 = "hit_url"
            java.lang.String r5 = "hit_method"
            java.lang.String r7 = "hit_headers"
            java.lang.String r8 = "hit_body"
            java.lang.String[] r7 = new java.lang.String[]{r1, r2, r5, r7, r8}     // Catch:{ SQLiteException -> 0x0168, all -> 0x0164 }
            r8 = 0
            r9 = 0
            r10 = 0
            r2 = 0
            java.lang.Object[] r5 = new java.lang.Object[r15]     // Catch:{ SQLiteException -> 0x0168, all -> 0x0164 }
            r5[r14] = r1     // Catch:{ SQLiteException -> 0x0168, all -> 0x0164 }
            java.lang.String r0 = java.lang.String.format(r0, r5)     // Catch:{ SQLiteException -> 0x0168, all -> 0x0164 }
            java.lang.String r1 = java.lang.Integer.toString(r16)     // Catch:{ SQLiteException -> 0x0168, all -> 0x0164 }
            r5 = r3
            r3 = 2
            r11 = r2
            r2 = r12
            r12 = r0
            r16 = r13
            r13 = r1
            android.database.Cursor r13 = r5.query(r6, r7, r8, r9, r10, r11, r12, r13)     // Catch:{ SQLiteException -> 0x0160, all -> 0x015c }
            boolean r0 = r13.moveToFirst()     // Catch:{ SQLiteException -> 0x015a }
            if (r0 == 0) goto L_0x0154
            r1 = 0
        L_0x00a2:
            r0 = r13
            android.database.sqlite.SQLiteCursor r0 = (android.database.sqlite.SQLiteCursor) r0     // Catch:{ SQLiteException -> 0x015a }
            android.database.CursorWindow r0 = r0.getWindow()     // Catch:{ SQLiteException -> 0x015a }
            int r0 = r0.getNumRows()     // Catch:{ SQLiteException -> 0x015a }
            if (r0 <= 0) goto L_0x0131
            java.lang.Object r0 = r2.get(r1)     // Catch:{ SQLiteException -> 0x015a }
            com.google.android.gms.internal.zzczx r0 = (com.google.android.gms.internal.zzczx) r0     // Catch:{ SQLiteException -> 0x015a }
            java.lang.String r5 = r13.getString(r15)     // Catch:{ SQLiteException -> 0x015a }
            r0.zzlv(r5)     // Catch:{ SQLiteException -> 0x015a }
            java.lang.Object r0 = r2.get(r1)     // Catch:{ SQLiteException -> 0x015a }
            com.google.android.gms.internal.zzczx r0 = (com.google.android.gms.internal.zzczx) r0     // Catch:{ SQLiteException -> 0x015a }
            java.lang.String r5 = r13.getString(r3)     // Catch:{ SQLiteException -> 0x015a }
            r0.zzmn(r5)     // Catch:{ SQLiteException -> 0x015a }
            java.lang.Object r0 = r2.get(r1)     // Catch:{ SQLiteException -> 0x015a }
            com.google.android.gms.internal.zzczx r0 = (com.google.android.gms.internal.zzczx) r0     // Catch:{ SQLiteException -> 0x015a }
            r5 = 4
            java.lang.String r5 = r13.getString(r5)     // Catch:{ SQLiteException -> 0x015a }
            r0.zzmo(r5)     // Catch:{ SQLiteException -> 0x015a }
            r0 = 3
            java.lang.String r0 = r13.getString(r0)     // Catch:{ JSONException -> 0x010e }
            if (r0 == 0) goto L_0x0103
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ JSONException -> 0x010e }
            r5.<init>(r0)     // Catch:{ JSONException -> 0x010e }
            org.json.JSONArray r0 = r5.names()     // Catch:{ JSONException -> 0x010e }
            java.util.HashMap r6 = new java.util.HashMap     // Catch:{ JSONException -> 0x010e }
            r6.<init>()     // Catch:{ JSONException -> 0x010e }
            r7 = 0
        L_0x00ed:
            int r8 = r0.length()     // Catch:{ JSONException -> 0x010e }
            if (r7 >= r8) goto L_0x0104
            java.lang.String r8 = r0.getString(r7)     // Catch:{ JSONException -> 0x010e }
            java.lang.Object r9 = r5.opt(r8)     // Catch:{ JSONException -> 0x010e }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ JSONException -> 0x010e }
            r6.put(r8, r9)     // Catch:{ JSONException -> 0x010e }
            int r7 = r7 + 1
            goto L_0x00ed
        L_0x0103:
            r6 = 0
        L_0x0104:
            java.lang.Object r0 = r2.get(r1)     // Catch:{ SQLiteException -> 0x015a }
            com.google.android.gms.internal.zzczx r0 = (com.google.android.gms.internal.zzczx) r0     // Catch:{ SQLiteException -> 0x015a }
            r0.zzad(r6)     // Catch:{ SQLiteException -> 0x015a }
            goto L_0x014c
        L_0x010e:
            r0 = move-exception
            java.lang.String r5 = "Failed to read headers for hitId %d: %s"
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch:{ SQLiteException -> 0x015a }
            java.lang.Object r7 = r2.get(r1)     // Catch:{ SQLiteException -> 0x015a }
            com.google.android.gms.internal.zzczx r7 = (com.google.android.gms.internal.zzczx) r7     // Catch:{ SQLiteException -> 0x015a }
            long r7 = r7.zzbgr()     // Catch:{ SQLiteException -> 0x015a }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ SQLiteException -> 0x015a }
            r6[r14] = r7     // Catch:{ SQLiteException -> 0x015a }
            java.lang.String r0 = r0.getMessage()     // Catch:{ SQLiteException -> 0x015a }
            r6[r15] = r0     // Catch:{ SQLiteException -> 0x015a }
            java.lang.String r0 = java.lang.String.format(r5, r6)     // Catch:{ SQLiteException -> 0x015a }
            com.google.android.gms.internal.zzdal.zzcz(r0)     // Catch:{ SQLiteException -> 0x015a }
            goto L_0x014e
        L_0x0131:
            java.lang.String r0 = "HitString for hitId %d too large. Hit will be deleted."
            java.lang.Object[] r5 = new java.lang.Object[r15]     // Catch:{ SQLiteException -> 0x015a }
            java.lang.Object r6 = r2.get(r1)     // Catch:{ SQLiteException -> 0x015a }
            com.google.android.gms.internal.zzczx r6 = (com.google.android.gms.internal.zzczx) r6     // Catch:{ SQLiteException -> 0x015a }
            long r6 = r6.zzbgr()     // Catch:{ SQLiteException -> 0x015a }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ SQLiteException -> 0x015a }
            r5[r14] = r6     // Catch:{ SQLiteException -> 0x015a }
            java.lang.String r0 = java.lang.String.format(r0, r5)     // Catch:{ SQLiteException -> 0x015a }
            com.google.android.gms.internal.zzdal.zzcz(r0)     // Catch:{ SQLiteException -> 0x015a }
        L_0x014c:
            int r1 = r1 + 1
        L_0x014e:
            boolean r0 = r13.moveToNext()     // Catch:{ SQLiteException -> 0x015a }
            if (r0 != 0) goto L_0x00a2
        L_0x0154:
            if (r13 == 0) goto L_0x0159
            r13.close()
        L_0x0159:
            return r2
        L_0x015a:
            r0 = move-exception
            goto L_0x016c
        L_0x015c:
            r0 = move-exception
            r13 = r16
            goto L_0x01b8
        L_0x0160:
            r0 = move-exception
            r13 = r16
            goto L_0x016c
        L_0x0164:
            r0 = move-exception
            r16 = r13
            goto L_0x01b8
        L_0x0168:
            r0 = move-exception
            r2 = r12
            r16 = r13
        L_0x016c:
            java.lang.String r1 = "Error in peekHits fetching hit url: "
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x01b7 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x01b7 }
            int r3 = r0.length()     // Catch:{ all -> 0x01b7 }
            if (r3 == 0) goto L_0x0181
            java.lang.String r0 = r1.concat(r0)     // Catch:{ all -> 0x01b7 }
            goto L_0x0186
        L_0x0181:
            java.lang.String r0 = new java.lang.String     // Catch:{ all -> 0x01b7 }
            r0.<init>(r1)     // Catch:{ all -> 0x01b7 }
        L_0x0186:
            com.google.android.gms.internal.zzdal.zzcz(r0)     // Catch:{ all -> 0x01b7 }
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x01b7 }
            r0.<init>()     // Catch:{ all -> 0x01b7 }
            r12 = r2
            java.util.ArrayList r12 = (java.util.ArrayList) r12     // Catch:{ all -> 0x01b7 }
            int r1 = r12.size()     // Catch:{ all -> 0x01b7 }
            r2 = 0
        L_0x0196:
            if (r14 >= r1) goto L_0x01b1
            java.lang.Object r3 = r12.get(r14)     // Catch:{ all -> 0x01b7 }
            int r14 = r14 + 1
            com.google.android.gms.internal.zzczx r3 = (com.google.android.gms.internal.zzczx) r3     // Catch:{ all -> 0x01b7 }
            java.lang.String r5 = r3.zzbgt()     // Catch:{ all -> 0x01b7 }
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x01b7 }
            if (r5 == 0) goto L_0x01ad
            if (r2 != 0) goto L_0x01b1
            r2 = 1
        L_0x01ad:
            r0.add(r3)     // Catch:{ all -> 0x01b7 }
            goto L_0x0196
        L_0x01b1:
            if (r13 == 0) goto L_0x01b6
            r13.close()
        L_0x01b6:
            return r0
        L_0x01b7:
            r0 = move-exception
        L_0x01b8:
            if (r13 == 0) goto L_0x01bd
            r13.close()
        L_0x01bd:
            throw r0
        L_0x01be:
            r0 = move-exception
            r2 = r12
            goto L_0x01c8
        L_0x01c1:
            r0 = move-exception
            r16 = r13
            r14 = r16
            goto L_0x01f6
        L_0x01c7:
            r0 = move-exception
        L_0x01c8:
            r16 = r13
            r14 = r16
            goto L_0x01d2
        L_0x01cd:
            r0 = move-exception
            r14 = 0
            goto L_0x01f6
        L_0x01d0:
            r0 = move-exception
            r14 = 0
        L_0x01d2:
            java.lang.String r1 = "Error in peekHits fetching hitIds: "
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x01f5 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x01f5 }
            int r3 = r0.length()     // Catch:{ all -> 0x01f5 }
            if (r3 == 0) goto L_0x01e7
            java.lang.String r0 = r1.concat(r0)     // Catch:{ all -> 0x01f5 }
            goto L_0x01ec
        L_0x01e7:
            java.lang.String r0 = new java.lang.String     // Catch:{ all -> 0x01f5 }
            r0.<init>(r1)     // Catch:{ all -> 0x01f5 }
        L_0x01ec:
            com.google.android.gms.internal.zzdal.zzcz(r0)     // Catch:{ all -> 0x01f5 }
            if (r14 == 0) goto L_0x01f4
            r14.close()
        L_0x01f4:
            return r2
        L_0x01f5:
            r0 = move-exception
        L_0x01f6:
            if (r14 == 0) goto L_0x01fb
            r14.close()
        L_0x01fb:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdao.zzff(int):java.util.List");
    }

    /* access modifiers changed from: private */
    public final void zzh(long j, long j2) {
        SQLiteDatabase zzlq = zzlq("Error opening database for getNumStoredHits.");
        if (zzlq != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hit_first_send_time", Long.valueOf(j2));
            try {
                zzlq.update("gtm_hits", contentValues, "hit_id=?", new String[]{String.valueOf(j)});
            } catch (SQLiteException e) {
                String message = e.getMessage();
                StringBuilder sb = new StringBuilder(String.valueOf(message).length() + 70);
                sb.append("Error setting HIT_FIRST_DISPATCH_TIME for hitId ");
                sb.append(j);
                sb.append(": ");
                sb.append(message);
                zzdal.zzcz(sb.toString());
                zzp(j);
            }
        }
    }

    private final SQLiteDatabase zzlq(String str) {
        try {
            return this.zzkxe.getWritableDatabase();
        } catch (SQLiteException e) {
            Context context = this.mContext;
            zzdal.zzc(str, e);
            zzdal.v(zzg.zza(context, e) ? "Crash reported successfully." : "Failed to report crash");
            return null;
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.String[], android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r2v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r2v2, types: [android.database.Cursor] */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0030, code lost:
        if (r2 != 0) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0032, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0056, code lost:
        if (r2 == 0) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0059, code lost:
        return r1;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzmp(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.String r0 = "Error opening database for getNumRecords."
            android.database.sqlite.SQLiteDatabase r0 = r5.zzlq(r0)
            r1 = 0
            if (r0 != 0) goto L_0x000a
            return r1
        L_0x000a:
            r2 = 0
            java.lang.String r3 = "SELECT COUNT(*) from "
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ SQLiteException -> 0x0038 }
            int r4 = r6.length()     // Catch:{ SQLiteException -> 0x0038 }
            if (r4 == 0) goto L_0x001c
            java.lang.String r6 = r3.concat(r6)     // Catch:{ SQLiteException -> 0x0038 }
            goto L_0x0021
        L_0x001c:
            java.lang.String r6 = new java.lang.String     // Catch:{ SQLiteException -> 0x0038 }
            r6.<init>(r3)     // Catch:{ SQLiteException -> 0x0038 }
        L_0x0021:
            android.database.Cursor r2 = r0.rawQuery(r6, r2)     // Catch:{ SQLiteException -> 0x0038 }
            boolean r6 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x0038 }
            if (r6 == 0) goto L_0x0030
            long r0 = r2.getLong(r1)     // Catch:{ SQLiteException -> 0x0038 }
            int r1 = (int) r0
        L_0x0030:
            if (r2 == 0) goto L_0x0059
        L_0x0032:
            r2.close()
            goto L_0x0059
        L_0x0036:
            r6 = move-exception
            goto L_0x005a
        L_0x0038:
            r6 = move-exception
            java.lang.String r0 = "Error getting numStoredRecords: "
            java.lang.String r6 = r6.getMessage()     // Catch:{ all -> 0x0036 }
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x0036 }
            int r3 = r6.length()     // Catch:{ all -> 0x0036 }
            if (r3 == 0) goto L_0x004e
            java.lang.String r6 = r0.concat(r6)     // Catch:{ all -> 0x0036 }
            goto L_0x0053
        L_0x004e:
            java.lang.String r6 = new java.lang.String     // Catch:{ all -> 0x0036 }
            r6.<init>(r0)     // Catch:{ all -> 0x0036 }
        L_0x0053:
            com.google.android.gms.internal.zzdal.zzcz(r6)     // Catch:{ all -> 0x0036 }
            if (r2 == 0) goto L_0x0059
            goto L_0x0032
        L_0x0059:
            return r1
        L_0x005a:
            if (r2 == 0) goto L_0x005f
            r2.close()
        L_0x005f:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdao.zzmp(java.lang.String):int");
    }

    /* access modifiers changed from: private */
    public final void zzp(long j) {
        zze(new String[]{String.valueOf(j)});
    }

    public final void dispatch() {
        zzdal.v("GTM Dispatch running...");
        if (this.zzkxf.zzbgj()) {
            List<zzczx> zzff = zzff(40);
            if (zzff.isEmpty()) {
                zzdal.v("...nothing to dispatch");
                this.zzkxg.zzbz(true);
                return;
            }
            this.zzkxf.zzam(zzff);
            if (zzbhf() > 0) {
                zzdbe.zzbje().dispatch();
            }
        }
    }

    public final void zza(long j, String str, String str2, String str3, Map<String, String> map, String str4) {
        String str5 = str;
        Map<String, String> map2 = map;
        long currentTimeMillis = this.zzata.currentTimeMillis();
        if (currentTimeMillis > this.zzkre + 86400000) {
            this.zzkre = currentTimeMillis;
            SQLiteDatabase zzlq = zzlq("Error opening database for deleteStaleHits.");
            if (zzlq != null) {
                int delete = zzlq.delete("gtm_hits", "HIT_TIME < ?", new String[]{Long.toString(this.zzata.currentTimeMillis() - 2592000000L)});
                StringBuilder sb = new StringBuilder(31);
                sb.append("Removed ");
                sb.append(delete);
                sb.append(" stale hits.");
                zzdal.v(sb.toString());
                this.zzkxg.zzbz(zzmp("gtm_hits") == 0);
            }
        }
        int zzmp = (zzmp("gtm_hits") - this.zzkrf) + 1;
        if (zzmp > 0) {
            List<String> zzfe = zzfe(zzmp);
            int size = zzfe.size();
            StringBuilder sb2 = new StringBuilder(51);
            sb2.append("Store full, deleting ");
            sb2.append(size);
            sb2.append(" hits to make room.");
            zzdal.v(sb2.toString());
            zze((String[]) zzfe.toArray(new String[0]));
        }
        SQLiteDatabase zzlq2 = zzlq("Error opening database for putHit");
        if (zzlq2 != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hit_time", Long.valueOf(j));
            contentValues.put("hit_url", str);
            contentValues.put("hit_first_send_time", 0);
            contentValues.put("hit_method", str2 == null ? "GET" : str2);
            contentValues.put("hit_unique_id", str3);
            contentValues.put("hit_headers", map2 == null ? null : new JSONObject(map2).toString());
            contentValues.put("hit_body", str4);
            try {
                zzlq2.insertOrThrow("gtm_hits", (String) null, contentValues);
                StringBuilder sb3 = new StringBuilder(String.valueOf(str).length() + 19);
                sb3.append("Hit stored (url = ");
                sb3.append(str);
                sb3.append(")");
                zzdal.v(sb3.toString());
                this.zzkxg.zzbz(false);
            } catch (SQLiteConstraintException unused) {
                String valueOf = String.valueOf(str);
                zzdal.v(valueOf.length() != 0 ? "Hit has already been sent: ".concat(valueOf) : new String("Hit has already been sent: "));
            } catch (SQLiteException e) {
                String valueOf2 = String.valueOf(e.getMessage());
                zzdal.zzcz(valueOf2.length() != 0 ? "Error storing hit: ".concat(valueOf2) : new String("Error storing hit: "));
            }
        }
        if (zzdat.zzbja().isPreview()) {
            zzdal.v("Sending hits immediately under preview.");
            dispatch();
        }
    }
}
