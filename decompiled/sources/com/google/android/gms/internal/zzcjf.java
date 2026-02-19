package com.google.android.gms.internal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.util.zze;

public final class zzcjf extends zzcli {
    private final zzcjg zzjka = new zzcjg(this, getContext(), "google_app_measurement_local.db");
    private boolean zzjkb;

    zzcjf(zzckj zzckj) {
        super(zzckj);
    }

    private final SQLiteDatabase getWritableDatabase() throws SQLiteException {
        if (this.zzjkb) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zzjka.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzjkb = true;
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v1, types: [boolean, int] */
    /* JADX WARNING: type inference failed for: r8v0 */
    /* JADX WARNING: type inference failed for: r8v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r3v2 */
    /* JADX WARNING: type inference failed for: r8v3, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r8v4, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r8v5 */
    /* JADX WARNING: type inference failed for: r8v7 */
    /* JADX WARNING: type inference failed for: r8v8 */
    /* JADX WARNING: type inference failed for: r8v9 */
    /* JADX WARNING: type inference failed for: r8v13 */
    /* JADX WARNING: type inference failed for: r8v14 */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0037, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0038, code lost:
        r13 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003b, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00c7, code lost:
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00db, code lost:
        if (r8.inTransaction() != false) goto L_0x00dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00dd, code lost:
        r8.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00f0, code lost:
        r13.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00f5, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00f9, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00fa, code lost:
        r10 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0107, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0132, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0137, code lost:
        r10.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0129 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:47:? A[ExcHandler: SQLiteDatabaseLockedException (unused android.database.sqlite.SQLiteDatabaseLockedException), SYNTHETIC, Splitter:B:9:0x002f] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00d7 A[SYNTHETIC, Splitter:B:55:0x00d7] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00f0  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0123  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0132  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0137  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x0129 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0129 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzb(int r18, byte[] r19) {
        /*
            r17 = this;
            r1 = r17
            java.lang.String r2 = "Error writing entry to local database"
            r17.zzwj()
            boolean r0 = r1.zzjkb
            r3 = 0
            if (r0 == 0) goto L_0x000d
            return r3
        L_0x000d:
            android.content.ContentValues r4 = new android.content.ContentValues
            r4.<init>()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r18)
            java.lang.String r5 = "type"
            r4.put(r5, r0)
            java.lang.String r0 = "entry"
            r5 = r19
            r4.put(r0, r5)
            r5 = 5
            r6 = 0
            r7 = 5
        L_0x0025:
            if (r6 >= r5) goto L_0x013b
            r8 = 0
            r9 = 1
            android.database.sqlite.SQLiteDatabase r10 = r17.getWritableDatabase()     // Catch:{ SQLiteFullException -> 0x0110, SQLiteDatabaseLockedException -> 0x00fd, SQLiteException -> 0x00d2, all -> 0x00cd }
            if (r10 != 0) goto L_0x003e
            r1.zzjkb = r9     // Catch:{ SQLiteFullException -> 0x003b, SQLiteDatabaseLockedException -> 0x00c7, SQLiteException -> 0x0037 }
            if (r10 == 0) goto L_0x0036
            r10.close()
        L_0x0036:
            return r3
        L_0x0037:
            r0 = move-exception
            r13 = r8
            goto L_0x00c5
        L_0x003b:
            r0 = move-exception
            goto L_0x0113
        L_0x003e:
            r10.beginTransaction()     // Catch:{ SQLiteFullException -> 0x00ca, SQLiteDatabaseLockedException -> 0x00c7, SQLiteException -> 0x00c2, all -> 0x00be }
            r11 = 0
            java.lang.String r0 = "select count(1) from messages"
            android.database.Cursor r13 = r10.rawQuery(r0, r8)     // Catch:{ SQLiteFullException -> 0x00ca, SQLiteDatabaseLockedException -> 0x00c7, SQLiteException -> 0x00c2, all -> 0x00be }
            if (r13 == 0) goto L_0x005f
            boolean r0 = r13.moveToFirst()     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x0059, all -> 0x0056 }
            if (r0 == 0) goto L_0x005f
            long r11 = r13.getLong(r3)     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x0059, all -> 0x0056 }
            goto L_0x005f
        L_0x0056:
            r0 = move-exception
            goto L_0x00fb
        L_0x0059:
            r0 = move-exception
            goto L_0x00c5
        L_0x005b:
            r0 = move-exception
            r8 = r13
            goto L_0x0113
        L_0x005f:
            java.lang.String r0 = "messages"
            r14 = 100000(0x186a0, double:4.94066E-319)
            int r16 = (r11 > r14 ? 1 : (r11 == r14 ? 0 : -1))
            if (r16 < 0) goto L_0x00a6
            com.google.android.gms.internal.zzcjj r16 = r17.zzayp()     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x0059, all -> 0x0056 }
            com.google.android.gms.internal.zzcjl r5 = r16.zzbau()     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x0059, all -> 0x0056 }
            java.lang.String r8 = "Data loss, local db full"
            r5.log(r8)     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x0059, all -> 0x0056 }
            long r14 = r14 - r11
            r11 = 1
            long r14 = r14 + r11
            java.lang.String r5 = "rowid in (select rowid from messages order by rowid asc limit ?)"
            java.lang.String[] r8 = new java.lang.String[r9]     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x0059, all -> 0x0056 }
            java.lang.String r11 = java.lang.Long.toString(r14)     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x0059, all -> 0x0056 }
            r8[r3] = r11     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x0059, all -> 0x0056 }
            int r5 = r10.delete(r0, r5, r8)     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x0059, all -> 0x0056 }
            long r11 = (long) r5     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x0059, all -> 0x0056 }
            int r5 = (r11 > r14 ? 1 : (r11 == r14 ? 0 : -1))
            if (r5 == 0) goto L_0x00a6
            com.google.android.gms.internal.zzcjj r5 = r17.zzayp()     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x0059, all -> 0x0056 }
            com.google.android.gms.internal.zzcjl r5 = r5.zzbau()     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x0059, all -> 0x0056 }
            java.lang.String r8 = "Different delete count than expected in local db. expected, received, difference"
            java.lang.Long r3 = java.lang.Long.valueOf(r14)     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x0059, all -> 0x0056 }
            java.lang.Long r9 = java.lang.Long.valueOf(r11)     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x0059, all -> 0x0056 }
            long r14 = r14 - r11
            java.lang.Long r11 = java.lang.Long.valueOf(r14)     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x0059, all -> 0x0056 }
            r5.zzd(r8, r3, r9, r11)     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x0059, all -> 0x0056 }
        L_0x00a6:
            r3 = 0
            r10.insertOrThrow(r0, r3, r4)     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x0059, all -> 0x0056 }
            r10.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x0059, all -> 0x0056 }
            r10.endTransaction()     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x0059, all -> 0x0056 }
            if (r13 == 0) goto L_0x00b5
            r13.close()
        L_0x00b5:
            if (r10 == 0) goto L_0x00ba
            r10.close()
        L_0x00ba:
            r2 = 1
            return r2
        L_0x00bc:
            r8 = r13
            goto L_0x00ff
        L_0x00be:
            r0 = move-exception
            r3 = r8
            goto L_0x0130
        L_0x00c2:
            r0 = move-exception
            r3 = r8
            r13 = r3
        L_0x00c5:
            r8 = r10
            goto L_0x00d5
        L_0x00c7:
            r3 = r8
            r8 = r3
            goto L_0x00ff
        L_0x00ca:
            r0 = move-exception
            r3 = r8
            goto L_0x0113
        L_0x00cd:
            r0 = move-exception
            r3 = r8
            r10 = r8
            goto L_0x0130
        L_0x00d2:
            r0 = move-exception
            r3 = r8
            r13 = r8
        L_0x00d5:
            if (r8 == 0) goto L_0x00e0
            boolean r3 = r8.inTransaction()     // Catch:{ all -> 0x00f9 }
            if (r3 == 0) goto L_0x00e0
            r8.endTransaction()     // Catch:{ all -> 0x00f9 }
        L_0x00e0:
            com.google.android.gms.internal.zzcjj r3 = r17.zzayp()     // Catch:{ all -> 0x00f9 }
            com.google.android.gms.internal.zzcjl r3 = r3.zzbau()     // Catch:{ all -> 0x00f9 }
            r3.zzj(r2, r0)     // Catch:{ all -> 0x00f9 }
            r3 = 1
            r1.zzjkb = r3     // Catch:{ all -> 0x00f9 }
            if (r13 == 0) goto L_0x00f3
            r13.close()
        L_0x00f3:
            if (r8 == 0) goto L_0x0129
            r8.close()
            goto L_0x0129
        L_0x00f9:
            r0 = move-exception
            r10 = r8
        L_0x00fb:
            r8 = r13
            goto L_0x0130
        L_0x00fd:
            r3 = r8
            r10 = r8
        L_0x00ff:
            long r11 = (long) r7
            android.os.SystemClock.sleep(r11)     // Catch:{ all -> 0x012f }
            int r7 = r7 + 20
            if (r8 == 0) goto L_0x010a
            r8.close()
        L_0x010a:
            if (r10 == 0) goto L_0x0129
        L_0x010c:
            r10.close()
            goto L_0x0129
        L_0x0110:
            r0 = move-exception
            r3 = r8
            r10 = r8
        L_0x0113:
            com.google.android.gms.internal.zzcjj r3 = r17.zzayp()     // Catch:{ all -> 0x012f }
            com.google.android.gms.internal.zzcjl r3 = r3.zzbau()     // Catch:{ all -> 0x012f }
            r3.zzj(r2, r0)     // Catch:{ all -> 0x012f }
            r3 = 1
            r1.zzjkb = r3     // Catch:{ all -> 0x012f }
            if (r8 == 0) goto L_0x0126
            r8.close()
        L_0x0126:
            if (r10 == 0) goto L_0x0129
            goto L_0x010c
        L_0x0129:
            int r6 = r6 + 1
            r3 = 0
            r5 = 5
            goto L_0x0025
        L_0x012f:
            r0 = move-exception
        L_0x0130:
            if (r8 == 0) goto L_0x0135
            r8.close()
        L_0x0135:
            if (r10 == 0) goto L_0x013a
            r10.close()
        L_0x013a:
            throw r0
        L_0x013b:
            com.google.android.gms.internal.zzcjj r0 = r17.zzayp()
            com.google.android.gms.internal.zzcjl r0 = r0.zzbaw()
            java.lang.String r2 = "Failed to write entry to local database"
            r0.log(r2)
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcjf.zzb(int, byte[]):boolean");
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final void resetAnalyticsData() {
        zzwj();
        try {
            int delete = getWritableDatabase().delete("messages", (String) null, (String[]) null) + 0;
            if (delete > 0) {
                zzayp().zzbba().zzj("Reset local analytics data. records", Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzayp().zzbau().zzj("Error resetting local analytics data. error", e);
        }
    }

    public final boolean zza(zzcix zzcix) {
        Parcel obtain = Parcel.obtain();
        zzcix.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zzb(0, marshall);
        }
        zzayp().zzbaw().log("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean zza(zzcnl zzcnl) {
        Parcel obtain = Parcel.obtain();
        zzcnl.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zzb(1, marshall);
        }
        zzayp().zzbaw().log("User property too long for local database. Sending directly to service");
        return false;
    }

    public final /* bridge */ /* synthetic */ void zzaxz() {
        super.zzaxz();
    }

    public final /* bridge */ /* synthetic */ void zzaya() {
        super.zzaya();
    }

    public final /* bridge */ /* synthetic */ zzcia zzayb() {
        return super.zzayb();
    }

    public final /* bridge */ /* synthetic */ zzcih zzayc() {
        return super.zzayc();
    }

    public final /* bridge */ /* synthetic */ zzclk zzayd() {
        return super.zzayd();
    }

    public final /* bridge */ /* synthetic */ zzcje zzaye() {
        return super.zzaye();
    }

    public final /* bridge */ /* synthetic */ zzcir zzayf() {
        return super.zzayf();
    }

    public final /* bridge */ /* synthetic */ zzcme zzayg() {
        return super.zzayg();
    }

    public final /* bridge */ /* synthetic */ zzcma zzayh() {
        return super.zzayh();
    }

    public final /* bridge */ /* synthetic */ zzcjf zzayi() {
        return super.zzayi();
    }

    public final /* bridge */ /* synthetic */ zzcil zzayj() {
        return super.zzayj();
    }

    public final /* bridge */ /* synthetic */ zzcjh zzayk() {
        return super.zzayk();
    }

    public final /* bridge */ /* synthetic */ zzcno zzayl() {
        return super.zzayl();
    }

    public final /* bridge */ /* synthetic */ zzckd zzaym() {
        return super.zzaym();
    }

    public final /* bridge */ /* synthetic */ zzcnd zzayn() {
        return super.zzayn();
    }

    public final /* bridge */ /* synthetic */ zzcke zzayo() {
        return super.zzayo();
    }

    public final /* bridge */ /* synthetic */ zzcjj zzayp() {
        return super.zzayp();
    }

    public final /* bridge */ /* synthetic */ zzcju zzayq() {
        return super.zzayq();
    }

    public final /* bridge */ /* synthetic */ zzcik zzayr() {
        return super.zzayr();
    }

    /* access modifiers changed from: protected */
    public final boolean zzazq() {
        return false;
    }

    public final boolean zzc(zzcii zzcii) {
        zzayl();
        byte[] zza = zzcno.zza((Parcelable) zzcii);
        if (zza.length <= 131072) {
            return zzb(2, zza);
        }
        zzayp().zzbaw().log("Conditional user property too long for local database. Sending directly to service");
        return false;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:53|54|55|56) */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:68|69|70|71) */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:38|39|40|41|161) */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x016c, code lost:
        r5 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0180, code lost:
        if (r15.inTransaction() != false) goto L_0x0182;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0182, code lost:
        r15.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0194, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x01a4, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x01ca, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x01cb, code lost:
        r3 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x01cc, code lost:
        r5 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x01cf, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x01d4, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0038, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003b, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        zzayp().zzbau().log("Failed to load event from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r13.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        zzayp().zzbau().log("Failed to load user property from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        r13.recycle();
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:?, code lost:
        zzayp().zzbau().log("Failed to load user property from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:?, code lost:
        r13.recycle();
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0165, code lost:
        r0 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:38:0x0096 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x00c8 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:68:0x00f9 */
    /* JADX WARNING: Removed duplicated region for block: B:102:? A[ExcHandler: SQLiteDatabaseLockedException (unused android.database.sqlite.SQLiteDatabaseLockedException), SYNTHETIC, Splitter:B:12:0x0030] */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x017c A[SYNTHETIC, Splitter:B:115:0x017c] */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0194  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x01a4  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x01bf  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x01cf  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x01d4  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x01c5 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x01c5 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x01c5 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0165 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:12:0x0030] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.internal.zzbgl> zzep(int r20) {
        /*
            r19 = this;
            r1 = r19
            java.lang.String r2 = "Error reading entries from local database"
            r19.zzwj()
            boolean r0 = r1.zzjkb
            r3 = 0
            if (r0 == 0) goto L_0x000d
            return r3
        L_0x000d:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            android.content.Context r0 = r19.getContext()
            java.lang.String r5 = "google_app_measurement_local.db"
            java.io.File r0 = r0.getDatabasePath(r5)
            boolean r0 = r0.exists()
            if (r0 != 0) goto L_0x0023
            return r4
        L_0x0023:
            r5 = 5
            r6 = 0
            r7 = 0
            r8 = 5
        L_0x0027:
            if (r7 >= r5) goto L_0x01d8
            r9 = 1
            android.database.sqlite.SQLiteDatabase r15 = r19.getWritableDatabase()     // Catch:{ SQLiteFullException -> 0x01ad, SQLiteDatabaseLockedException -> 0x019a, SQLiteException -> 0x0177, all -> 0x0174 }
            if (r15 != 0) goto L_0x003e
            r1.zzjkb = r9     // Catch:{ SQLiteFullException -> 0x003b, SQLiteDatabaseLockedException -> 0x016c, SQLiteException -> 0x0038, all -> 0x0165 }
            if (r15 == 0) goto L_0x0037
            r15.close()
        L_0x0037:
            return r3
        L_0x0038:
            r0 = move-exception
            goto L_0x016a
        L_0x003b:
            r0 = move-exception
            goto L_0x0172
        L_0x003e:
            r15.beginTransaction()     // Catch:{ SQLiteFullException -> 0x0170, SQLiteDatabaseLockedException -> 0x016c, SQLiteException -> 0x0168, all -> 0x0165 }
            java.lang.String r11 = "messages"
            java.lang.String r0 = "rowid"
            java.lang.String r10 = "type"
            java.lang.String r12 = "entry"
            java.lang.String[] r12 = new java.lang.String[]{r0, r10, r12}     // Catch:{ SQLiteFullException -> 0x0170, SQLiteDatabaseLockedException -> 0x016c, SQLiteException -> 0x0168, all -> 0x0165 }
            r13 = 0
            r14 = 0
            r0 = 0
            r16 = 0
            java.lang.String r17 = "rowid asc"
            r10 = 100
            java.lang.String r18 = java.lang.Integer.toString(r10)     // Catch:{ SQLiteFullException -> 0x0170, SQLiteDatabaseLockedException -> 0x016c, SQLiteException -> 0x0168, all -> 0x0165 }
            r10 = r15
            r5 = r15
            r15 = r0
            android.database.Cursor r10 = r10.query(r11, r12, r13, r14, r15, r16, r17, r18)     // Catch:{ SQLiteFullException -> 0x0161, SQLiteDatabaseLockedException -> 0x016d, SQLiteException -> 0x015d, all -> 0x015a }
            r11 = -1
        L_0x0063:
            boolean r0 = r10.moveToNext()     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            if (r0 == 0) goto L_0x011e
            long r11 = r10.getLong(r6)     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            int r0 = r10.getInt(r9)     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            r13 = 2
            byte[] r14 = r10.getBlob(r13)     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            if (r0 != 0) goto L_0x00ab
            android.os.Parcel r13 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            int r0 = r14.length     // Catch:{ zzbgn -> 0x0096 }
            r13.unmarshall(r14, r6, r0)     // Catch:{ zzbgn -> 0x0096 }
            r13.setDataPosition(r6)     // Catch:{ zzbgn -> 0x0096 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcix> r0 = com.google.android.gms.internal.zzcix.CREATOR     // Catch:{ zzbgn -> 0x0096 }
            java.lang.Object r0 = r0.createFromParcel(r13)     // Catch:{ zzbgn -> 0x0096 }
            com.google.android.gms.internal.zzcix r0 = (com.google.android.gms.internal.zzcix) r0     // Catch:{ zzbgn -> 0x0096 }
            r13.recycle()     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            if (r0 == 0) goto L_0x0063
        L_0x0090:
            r4.add(r0)     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            goto L_0x0063
        L_0x0094:
            r0 = move-exception
            goto L_0x00a7
        L_0x0096:
            com.google.android.gms.internal.zzcjj r0 = r19.zzayp()     // Catch:{ all -> 0x0094 }
            com.google.android.gms.internal.zzcjl r0 = r0.zzbau()     // Catch:{ all -> 0x0094 }
            java.lang.String r14 = "Failed to load event from local database"
            r0.log(r14)     // Catch:{ all -> 0x0094 }
            r13.recycle()     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            goto L_0x0063
        L_0x00a7:
            r13.recycle()     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            throw r0     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
        L_0x00ab:
            java.lang.String r15 = "Failed to load user property from local database"
            if (r0 != r9) goto L_0x00de
            android.os.Parcel r13 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            int r0 = r14.length     // Catch:{ zzbgn -> 0x00c8 }
            r13.unmarshall(r14, r6, r0)     // Catch:{ zzbgn -> 0x00c8 }
            r13.setDataPosition(r6)     // Catch:{ zzbgn -> 0x00c8 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcnl> r0 = com.google.android.gms.internal.zzcnl.CREATOR     // Catch:{ zzbgn -> 0x00c8 }
            java.lang.Object r0 = r0.createFromParcel(r13)     // Catch:{ zzbgn -> 0x00c8 }
            com.google.android.gms.internal.zzcnl r0 = (com.google.android.gms.internal.zzcnl) r0     // Catch:{ zzbgn -> 0x00c8 }
            r13.recycle()     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            goto L_0x00d7
        L_0x00c6:
            r0 = move-exception
            goto L_0x00da
        L_0x00c8:
            com.google.android.gms.internal.zzcjj r0 = r19.zzayp()     // Catch:{ all -> 0x00c6 }
            com.google.android.gms.internal.zzcjl r0 = r0.zzbau()     // Catch:{ all -> 0x00c6 }
            r0.log(r15)     // Catch:{ all -> 0x00c6 }
            r13.recycle()     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            r0 = r3
        L_0x00d7:
            if (r0 == 0) goto L_0x0063
            goto L_0x0090
        L_0x00da:
            r13.recycle()     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            throw r0     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
        L_0x00de:
            if (r0 != r13) goto L_0x010f
            android.os.Parcel r13 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            int r0 = r14.length     // Catch:{ zzbgn -> 0x00f9 }
            r13.unmarshall(r14, r6, r0)     // Catch:{ zzbgn -> 0x00f9 }
            r13.setDataPosition(r6)     // Catch:{ zzbgn -> 0x00f9 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcii> r0 = com.google.android.gms.internal.zzcii.CREATOR     // Catch:{ zzbgn -> 0x00f9 }
            java.lang.Object r0 = r0.createFromParcel(r13)     // Catch:{ zzbgn -> 0x00f9 }
            com.google.android.gms.internal.zzcii r0 = (com.google.android.gms.internal.zzcii) r0     // Catch:{ zzbgn -> 0x00f9 }
            r13.recycle()     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            goto L_0x0108
        L_0x00f7:
            r0 = move-exception
            goto L_0x010b
        L_0x00f9:
            com.google.android.gms.internal.zzcjj r0 = r19.zzayp()     // Catch:{ all -> 0x00f7 }
            com.google.android.gms.internal.zzcjl r0 = r0.zzbau()     // Catch:{ all -> 0x00f7 }
            r0.log(r15)     // Catch:{ all -> 0x00f7 }
            r13.recycle()     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            r0 = r3
        L_0x0108:
            if (r0 == 0) goto L_0x0063
            goto L_0x0090
        L_0x010b:
            r13.recycle()     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            throw r0     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
        L_0x010f:
            com.google.android.gms.internal.zzcjj r0 = r19.zzayp()     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            com.google.android.gms.internal.zzcjl r0 = r0.zzbau()     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            java.lang.String r13 = "Unknown record type in local database"
            r0.log(r13)     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            goto L_0x0063
        L_0x011e:
            java.lang.String r0 = "messages"
            java.lang.String r13 = "rowid <= ?"
            java.lang.String[] r14 = new java.lang.String[r9]     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            java.lang.String r11 = java.lang.Long.toString(r11)     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            r14[r6] = r11     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            int r0 = r5.delete(r0, r13, r14)     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            int r11 = r4.size()     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            if (r0 >= r11) goto L_0x0141
            com.google.android.gms.internal.zzcjj r0 = r19.zzayp()     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            com.google.android.gms.internal.zzcjl r0 = r0.zzbau()     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            java.lang.String r11 = "Fewer entries removed from local database than expected"
            r0.log(r11)     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
        L_0x0141:
            r5.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            r5.endTransaction()     // Catch:{ SQLiteFullException -> 0x0158, SQLiteDatabaseLockedException -> 0x016e, SQLiteException -> 0x0156, all -> 0x0152 }
            if (r10 == 0) goto L_0x014c
            r10.close()
        L_0x014c:
            if (r5 == 0) goto L_0x0151
            r5.close()
        L_0x0151:
            return r4
        L_0x0152:
            r0 = move-exception
            r3 = r10
            goto L_0x01cd
        L_0x0156:
            r0 = move-exception
            goto L_0x015f
        L_0x0158:
            r0 = move-exception
            goto L_0x0163
        L_0x015a:
            r0 = move-exception
            goto L_0x01cd
        L_0x015d:
            r0 = move-exception
            r10 = r3
        L_0x015f:
            r15 = r5
            goto L_0x017a
        L_0x0161:
            r0 = move-exception
            r10 = r3
        L_0x0163:
            r15 = r5
            goto L_0x01b0
        L_0x0165:
            r0 = move-exception
            goto L_0x01cc
        L_0x0168:
            r0 = move-exception
            r5 = r15
        L_0x016a:
            r10 = r3
            goto L_0x017a
        L_0x016c:
            r5 = r15
        L_0x016d:
            r10 = r3
        L_0x016e:
            r15 = r5
            goto L_0x019c
        L_0x0170:
            r0 = move-exception
            r5 = r15
        L_0x0172:
            r10 = r3
            goto L_0x01b0
        L_0x0174:
            r0 = move-exception
            r5 = r3
            goto L_0x01cd
        L_0x0177:
            r0 = move-exception
            r10 = r3
            r15 = r10
        L_0x017a:
            if (r15 == 0) goto L_0x0185
            boolean r5 = r15.inTransaction()     // Catch:{ all -> 0x01ca }
            if (r5 == 0) goto L_0x0185
            r15.endTransaction()     // Catch:{ all -> 0x01ca }
        L_0x0185:
            com.google.android.gms.internal.zzcjj r5 = r19.zzayp()     // Catch:{ all -> 0x01ca }
            com.google.android.gms.internal.zzcjl r5 = r5.zzbau()     // Catch:{ all -> 0x01ca }
            r5.zzj(r2, r0)     // Catch:{ all -> 0x01ca }
            r1.zzjkb = r9     // Catch:{ all -> 0x01ca }
            if (r10 == 0) goto L_0x0197
            r10.close()
        L_0x0197:
            if (r15 == 0) goto L_0x01c5
            goto L_0x01a9
        L_0x019a:
            r10 = r3
            r15 = r10
        L_0x019c:
            long r11 = (long) r8
            android.os.SystemClock.sleep(r11)     // Catch:{ all -> 0x01ca }
            int r8 = r8 + 20
            if (r10 == 0) goto L_0x01a7
            r10.close()
        L_0x01a7:
            if (r15 == 0) goto L_0x01c5
        L_0x01a9:
            r15.close()
            goto L_0x01c5
        L_0x01ad:
            r0 = move-exception
            r10 = r3
            r15 = r10
        L_0x01b0:
            com.google.android.gms.internal.zzcjj r5 = r19.zzayp()     // Catch:{ all -> 0x01ca }
            com.google.android.gms.internal.zzcjl r5 = r5.zzbau()     // Catch:{ all -> 0x01ca }
            r5.zzj(r2, r0)     // Catch:{ all -> 0x01ca }
            r1.zzjkb = r9     // Catch:{ all -> 0x01ca }
            if (r10 == 0) goto L_0x01c2
            r10.close()
        L_0x01c2:
            if (r15 == 0) goto L_0x01c5
            goto L_0x01a9
        L_0x01c5:
            int r7 = r7 + 1
            r5 = 5
            goto L_0x0027
        L_0x01ca:
            r0 = move-exception
            r3 = r10
        L_0x01cc:
            r5 = r15
        L_0x01cd:
            if (r3 == 0) goto L_0x01d2
            r3.close()
        L_0x01d2:
            if (r5 == 0) goto L_0x01d7
            r5.close()
        L_0x01d7:
            throw r0
        L_0x01d8:
            com.google.android.gms.internal.zzcjj r0 = r19.zzayp()
            com.google.android.gms.internal.zzcjl r0 = r0.zzbaw()
            java.lang.String r2 = "Failed to read events from database in reasonable time"
            r0.log(r2)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcjf.zzep(int):java.util.List");
    }

    public final /* bridge */ /* synthetic */ void zzwj() {
        super.zzwj();
    }

    public final /* bridge */ /* synthetic */ zze zzxx() {
        return super.zzxx();
    }
}
