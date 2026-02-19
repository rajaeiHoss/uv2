package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;
import java.util.Collections;
import java.util.List;

final class zzec implements zzcc {
    /* access modifiers changed from: private */
    public static final String zzdzv = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL,'%s' INTEGER NOT NULL);", new Object[]{"gtm_hits", "hit_id", "hit_time", "hit_url", "hit_first_send_time"});
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public zze zzata;
    private final zzee zzkra;
    private volatile zzbe zzkrb;
    private final zzcd zzkrc;
    /* access modifiers changed from: private */
    public final String zzkrd;
    private long zzkre;
    private final int zzkrf;

    zzec(zzcd zzcd, Context context) {
        this(zzcd, context, "gtm_urls.db", 2000);
    }

    private zzec(zzcd zzcd, Context context, String str, int i) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.zzkrd = str;
        this.zzkrc = zzcd;
        this.zzata = zzi.zzanq();
        this.zzkra = new zzee(this, applicationContext, str);
        this.zzkrb = new zzfv(applicationContext, new zzed(this));
        this.zzkre = 0;
        this.zzkrf = 2000;
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
    private final int zzbhe() {
        /*
            r4 = this;
            java.lang.String r0 = "Error opening database for getNumStoredHits."
            android.database.sqlite.SQLiteDatabase r0 = r4.zzlq(r0)
            r1 = 0
            if (r0 != 0) goto L_0x000a
            return r1
        L_0x000a:
            r2 = 0
            java.lang.String r3 = "SELECT COUNT(*) from gtm_hits"
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
            java.lang.String r0 = "Error getting numStoredHits"
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzec.zzbhe():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0030, code lost:
        if (r9 == null) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0033, code lost:
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
            if (r9 == 0) goto L_0x0033
        L_0x0025:
            r9.close()
            goto L_0x0033
        L_0x0029:
            r0 = move-exception
            goto L_0x0034
        L_0x002b:
            java.lang.String r1 = "Error getting num untried hits"
            com.google.android.gms.tagmanager.zzdj.zzcz(r1)     // Catch:{ all -> 0x0029 }
            if (r9 == 0) goto L_0x0033
            goto L_0x0025
        L_0x0033:
            return r0
        L_0x0034:
            if (r9 == 0) goto L_0x0039
            r9.close()
        L_0x0039:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzec.zzbhf():int");
    }

    private final void zze(String[] strArr) {
        SQLiteDatabase zzlq;
        if (strArr != null && strArr.length != 0 && (zzlq = zzlq("Error opening database for deleteHits.")) != null) {
            boolean z = true;
            try {
                zzlq.delete("gtm_hits", String.format("HIT_ID in (%s)", new Object[]{TextUtils.join(",", Collections.nCopies(strArr.length, "?"))}), strArr);
                zzcd zzcd = this.zzkrc;
                if (zzbhe() != 0) {
                    z = false;
                }
                zzcd.zzbz(z);
            } catch (SQLiteException unused) {
                zzdj.zzcz("Error deleting hits");
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
            com.google.android.gms.tagmanager.zzdj.zzcz(r14)
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzec.zzfe(int):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:70:0x013f, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0140, code lost:
        r14 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0143, code lost:
        r0 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00fa A[Catch:{ all -> 0x0135 }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00ff A[Catch:{ all -> 0x0135 }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0116 A[Catch:{ all -> 0x0135 }] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0131  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0138  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x013f A[ExcHandler: all (th java.lang.Throwable), Splitter:B:6:0x003a] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x015c A[Catch:{ all -> 0x016f }] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0161 A[Catch:{ all -> 0x016f }] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x016b  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0172  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.List<com.google.android.gms.tagmanager.zzbx> zzff(int r19) {
        /*
            r18 = this;
            java.lang.String r0 = "%s ASC"
            java.lang.String r1 = "hit_id"
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.lang.String r3 = "Error opening database for peekHits"
            r4 = r18
            android.database.sqlite.SQLiteDatabase r3 = r4.zzlq(r3)
            if (r3 != 0) goto L_0x0014
            return r2
        L_0x0014:
            java.lang.String r6 = "gtm_hits"
            java.lang.String r5 = "hit_time"
            java.lang.String r7 = "hit_first_send_time"
            java.lang.String[] r7 = new java.lang.String[]{r1, r5, r7}     // Catch:{ SQLiteException -> 0x014a, all -> 0x0147 }
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r15 = 1
            java.lang.Object[] r5 = new java.lang.Object[r15]     // Catch:{ SQLiteException -> 0x014a, all -> 0x0147 }
            r13 = 0
            r5[r13] = r1     // Catch:{ SQLiteException -> 0x014a, all -> 0x0147 }
            java.lang.String r12 = java.lang.String.format(r0, r5)     // Catch:{ SQLiteException -> 0x014a, all -> 0x0147 }
            r16 = 40
            java.lang.String r17 = java.lang.Integer.toString(r16)     // Catch:{ SQLiteException -> 0x014a, all -> 0x0147 }
            r5 = r3
            r14 = 0
            r13 = r17
            android.database.Cursor r13 = r5.query(r6, r7, r8, r9, r10, r11, r12, r13)     // Catch:{ SQLiteException -> 0x014a, all -> 0x0147 }
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0143, all -> 0x013f }
            r12.<init>()     // Catch:{ SQLiteException -> 0x0143, all -> 0x013f }
            boolean r2 = r13.moveToFirst()     // Catch:{ SQLiteException -> 0x013c, all -> 0x013f }
            if (r2 == 0) goto L_0x006b
        L_0x0045:
            com.google.android.gms.tagmanager.zzbx r2 = new com.google.android.gms.tagmanager.zzbx     // Catch:{ SQLiteException -> 0x0066, all -> 0x0062 }
            long r6 = r13.getLong(r14)     // Catch:{ SQLiteException -> 0x0066, all -> 0x0062 }
            long r8 = r13.getLong(r15)     // Catch:{ SQLiteException -> 0x0066, all -> 0x0062 }
            r5 = 2
            long r10 = r13.getLong(r5)     // Catch:{ SQLiteException -> 0x0066, all -> 0x0062 }
            r5 = r2
            r5.<init>(r6, r8, r10)     // Catch:{ SQLiteException -> 0x0066, all -> 0x0062 }
            r12.add(r2)     // Catch:{ SQLiteException -> 0x0066, all -> 0x0062 }
            boolean r2 = r13.moveToNext()     // Catch:{ SQLiteException -> 0x0066, all -> 0x0062 }
            if (r2 != 0) goto L_0x0045
            goto L_0x006b
        L_0x0062:
            r0 = move-exception
            r14 = r13
            goto L_0x0170
        L_0x0066:
            r0 = move-exception
            r2 = r12
            r14 = r13
            goto L_0x014c
        L_0x006b:
            if (r13 == 0) goto L_0x0070
            r13.close()
        L_0x0070:
            java.lang.String r6 = "gtm_hits"
            java.lang.String r2 = "hit_url"
            java.lang.String[] r7 = new java.lang.String[]{r1, r2}     // Catch:{ SQLiteException -> 0x00e7, all -> 0x00e4 }
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            java.lang.Object[] r2 = new java.lang.Object[r15]     // Catch:{ SQLiteException -> 0x00e7, all -> 0x00e4 }
            r2[r14] = r1     // Catch:{ SQLiteException -> 0x00e7, all -> 0x00e4 }
            java.lang.String r0 = java.lang.String.format(r0, r2)     // Catch:{ SQLiteException -> 0x00e7, all -> 0x00e4 }
            java.lang.String r1 = java.lang.Integer.toString(r16)     // Catch:{ SQLiteException -> 0x00e7, all -> 0x00e4 }
            r5 = r3
            r2 = r12
            r12 = r0
            r3 = r13
            r13 = r1
            android.database.Cursor r13 = r5.query(r6, r7, r8, r9, r10, r11, r12, r13)     // Catch:{ SQLiteException -> 0x00e1, all -> 0x00de }
            boolean r0 = r13.moveToFirst()     // Catch:{ SQLiteException -> 0x00dc }
            if (r0 == 0) goto L_0x00d6
            r0 = 0
        L_0x0098:
            r1 = r13
            android.database.sqlite.SQLiteCursor r1 = (android.database.sqlite.SQLiteCursor) r1     // Catch:{ SQLiteException -> 0x00dc }
            android.database.CursorWindow r1 = r1.getWindow()     // Catch:{ SQLiteException -> 0x00dc }
            int r1 = r1.getNumRows()     // Catch:{ SQLiteException -> 0x00dc }
            if (r1 <= 0) goto L_0x00b3
            java.lang.Object r1 = r2.get(r0)     // Catch:{ SQLiteException -> 0x00dc }
            com.google.android.gms.tagmanager.zzbx r1 = (com.google.android.gms.tagmanager.zzbx) r1     // Catch:{ SQLiteException -> 0x00dc }
            java.lang.String r3 = r13.getString(r15)     // Catch:{ SQLiteException -> 0x00dc }
            r1.zzlv(r3)     // Catch:{ SQLiteException -> 0x00dc }
            goto L_0x00ce
        L_0x00b3:
            java.lang.String r1 = "HitString for hitId %d too large.  Hit will be deleted."
            java.lang.Object[] r3 = new java.lang.Object[r15]     // Catch:{ SQLiteException -> 0x00dc }
            java.lang.Object r5 = r2.get(r0)     // Catch:{ SQLiteException -> 0x00dc }
            com.google.android.gms.tagmanager.zzbx r5 = (com.google.android.gms.tagmanager.zzbx) r5     // Catch:{ SQLiteException -> 0x00dc }
            long r5 = r5.zzbgr()     // Catch:{ SQLiteException -> 0x00dc }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ SQLiteException -> 0x00dc }
            r3[r14] = r5     // Catch:{ SQLiteException -> 0x00dc }
            java.lang.String r1 = java.lang.String.format(r1, r3)     // Catch:{ SQLiteException -> 0x00dc }
            com.google.android.gms.tagmanager.zzdj.zzcz(r1)     // Catch:{ SQLiteException -> 0x00dc }
        L_0x00ce:
            int r0 = r0 + 1
            boolean r1 = r13.moveToNext()     // Catch:{ SQLiteException -> 0x00dc }
            if (r1 != 0) goto L_0x0098
        L_0x00d6:
            if (r13 == 0) goto L_0x00db
            r13.close()
        L_0x00db:
            return r2
        L_0x00dc:
            r0 = move-exception
            goto L_0x00ea
        L_0x00de:
            r0 = move-exception
            r13 = r3
            goto L_0x0136
        L_0x00e1:
            r0 = move-exception
            r13 = r3
            goto L_0x00ea
        L_0x00e4:
            r0 = move-exception
            r3 = r13
            goto L_0x0136
        L_0x00e7:
            r0 = move-exception
            r2 = r12
            r3 = r13
        L_0x00ea:
            java.lang.String r1 = "Error in peekHits fetching hit url: "
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0135 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0135 }
            int r3 = r0.length()     // Catch:{ all -> 0x0135 }
            if (r3 == 0) goto L_0x00ff
            java.lang.String r0 = r1.concat(r0)     // Catch:{ all -> 0x0135 }
            goto L_0x0104
        L_0x00ff:
            java.lang.String r0 = new java.lang.String     // Catch:{ all -> 0x0135 }
            r0.<init>(r1)     // Catch:{ all -> 0x0135 }
        L_0x0104:
            com.google.android.gms.tagmanager.zzdj.zzcz(r0)     // Catch:{ all -> 0x0135 }
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0135 }
            r0.<init>()     // Catch:{ all -> 0x0135 }
            r12 = r2
            java.util.ArrayList r12 = (java.util.ArrayList) r12     // Catch:{ all -> 0x0135 }
            int r1 = r12.size()     // Catch:{ all -> 0x0135 }
            r2 = 0
        L_0x0114:
            if (r14 >= r1) goto L_0x012f
            java.lang.Object r3 = r12.get(r14)     // Catch:{ all -> 0x0135 }
            int r14 = r14 + 1
            com.google.android.gms.tagmanager.zzbx r3 = (com.google.android.gms.tagmanager.zzbx) r3     // Catch:{ all -> 0x0135 }
            java.lang.String r5 = r3.zzbgt()     // Catch:{ all -> 0x0135 }
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x0135 }
            if (r5 == 0) goto L_0x012b
            if (r2 != 0) goto L_0x012f
            r2 = 1
        L_0x012b:
            r0.add(r3)     // Catch:{ all -> 0x0135 }
            goto L_0x0114
        L_0x012f:
            if (r13 == 0) goto L_0x0134
            r13.close()
        L_0x0134:
            return r0
        L_0x0135:
            r0 = move-exception
        L_0x0136:
            if (r13 == 0) goto L_0x013b
            r13.close()
        L_0x013b:
            throw r0
        L_0x013c:
            r0 = move-exception
            r2 = r12
            goto L_0x0144
        L_0x013f:
            r0 = move-exception
            r3 = r13
            r14 = r3
            goto L_0x0170
        L_0x0143:
            r0 = move-exception
        L_0x0144:
            r3 = r13
            r14 = r3
            goto L_0x014c
        L_0x0147:
            r0 = move-exception
            r14 = 0
            goto L_0x0170
        L_0x014a:
            r0 = move-exception
            r14 = 0
        L_0x014c:
            java.lang.String r1 = "Error in peekHits fetching hitIds: "
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x016f }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x016f }
            int r3 = r0.length()     // Catch:{ all -> 0x016f }
            if (r3 == 0) goto L_0x0161
            java.lang.String r0 = r1.concat(r0)     // Catch:{ all -> 0x016f }
            goto L_0x0166
        L_0x0161:
            java.lang.String r0 = new java.lang.String     // Catch:{ all -> 0x016f }
            r0.<init>(r1)     // Catch:{ all -> 0x016f }
        L_0x0166:
            com.google.android.gms.tagmanager.zzdj.zzcz(r0)     // Catch:{ all -> 0x016f }
            if (r14 == 0) goto L_0x016e
            r14.close()
        L_0x016e:
            return r2
        L_0x016f:
            r0 = move-exception
        L_0x0170:
            if (r14 == 0) goto L_0x0175
            r14.close()
        L_0x0175:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzec.zzff(int):java.util.List");
    }

    /* access modifiers changed from: private */
    public final void zzh(long j, long j2) {
        SQLiteDatabase zzlq = zzlq("Error opening database for getNumStoredHits.");
        if (zzlq != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hit_first_send_time", Long.valueOf(j2));
            try {
                zzlq.update("gtm_hits", contentValues, "hit_id=?", new String[]{String.valueOf(j)});
            } catch (SQLiteException unused) {
                StringBuilder sb = new StringBuilder(69);
                sb.append("Error setting HIT_FIRST_DISPATCH_TIME for hitId: ");
                sb.append(j);
                zzdj.zzcz(sb.toString());
                zzp(j);
            }
        }
    }

    private final SQLiteDatabase zzlq(String str) {
        try {
            return this.zzkra.getWritableDatabase();
        } catch (SQLiteException unused) {
            zzdj.zzcz(str);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public final void zzp(long j) {
        zze(new String[]{String.valueOf(j)});
    }

    public final void dispatch() {
        zzdj.v("GTM Dispatch running...");
        if (this.zzkrb.zzbgj()) {
            List<zzbx> zzff = zzff(40);
            if (zzff.isEmpty()) {
                zzdj.v("...nothing to dispatch");
                this.zzkrc.zzbz(true);
                return;
            }
            this.zzkrb.zzam(zzff);
            if (zzbhf() > 0) {
                zzfo.zzbhz().dispatch();
            }
        }
    }

    public final void zzb(long j, String str) {
        long currentTimeMillis = this.zzata.currentTimeMillis();
        if (currentTimeMillis > this.zzkre + 86400000) {
            this.zzkre = currentTimeMillis;
            SQLiteDatabase zzlq = zzlq("Error opening database for deleteStaleHits.");
            if (zzlq != null) {
                zzlq.delete("gtm_hits", "HIT_TIME < ?", new String[]{Long.toString(this.zzata.currentTimeMillis() - 2592000000L)});
                this.zzkrc.zzbz(zzbhe() == 0);
            }
        }
        int zzbhe = (zzbhe() - this.zzkrf) + 1;
        if (zzbhe > 0) {
            List<String> zzfe = zzfe(zzbhe);
            int size = zzfe.size();
            StringBuilder sb = new StringBuilder(51);
            sb.append("Store full, deleting ");
            sb.append(size);
            sb.append(" hits to make room.");
            zzdj.v(sb.toString());
            zze((String[]) zzfe.toArray(new String[0]));
        }
        SQLiteDatabase zzlq2 = zzlq("Error opening database for putHit");
        if (zzlq2 != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hit_time", Long.valueOf(j));
            contentValues.put("hit_url", str);
            contentValues.put("hit_first_send_time", 0);
            try {
                zzlq2.insert("gtm_hits", (String) null, contentValues);
                this.zzkrc.zzbz(false);
            } catch (SQLiteException unused) {
                zzdj.zzcz("Error storing hit");
            }
        }
    }
}
