package com.google.android.gms.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.support.v4.media.session.PlaybackStateCompat;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseException;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class zzedk implements zzekj {
    private static final Charset zzfnl = Charset.forName("UTF-8");
    private final SQLiteDatabase zzmxy;
    private final zzemm zzmxz;
    private boolean zzmya;
    private long zzmyb = 0;

    public zzedk(Context context, zzegm zzegm, String str) {
        try {
            String encode = URLEncoder.encode(str, "utf-8");
            this.zzmxz = zzegm.zzqb("Persistence");
            this.zzmxy = zzah(context, encode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int zza(zzegu zzegu, List<String> list, int i) {
        int i2 = i + 1;
        String zzc = zzc(zzegu);
        if (list.get(i).startsWith(zzc)) {
            while (i2 < list.size() && list.get(i2).equals(zza(zzegu, i2 - i))) {
                i2++;
            }
            if (i2 < list.size()) {
                String str = list.get(i2);
                String valueOf = String.valueOf(zzc);
                if (str.startsWith(".part-".length() != 0 ? valueOf.concat(".part-") : new String(valueOf))) {
                    throw new IllegalStateException("Run did not finish with all parts");
                }
            }
            return i2 - i;
        }
        throw new IllegalStateException("Extracting split nodes needs to start with path prefix");
    }

    private final int zza(String str, zzegu zzegu) {
        String zzc = zzc(zzegu);
        String zzpo = zzpo(zzc);
        return this.zzmxy.delete(str, "path >= ? AND path < ?", new String[]{zzc, zzpo});
    }

    private final Cursor zza(zzegu zzegu, String[] strArr) {
        String zzc = zzc(zzegu);
        String zzpo = zzpo(zzc);
        String[] strArr2 = new String[(zzegu.size() + 3)];
        StringBuilder sb = new StringBuilder("(");
        int i = 0;
        zzegu zzegu2 = zzegu;
        while (true) {
            boolean isEmpty = zzegu2.isEmpty();
            sb.append("path");
            if (isEmpty) {
                break;
            }
            sb.append(" = ? OR ");
            strArr2[i] = zzc(zzegu2);
            zzegu2 = zzegu2.zzbys();
            i++;
        }
        sb.append(" = ?)");
        strArr2[i] = zzc(zzegu.zzbyn());
        String valueOf = String.valueOf(sb.toString());
        String concat = " OR (path > ? AND path < ?)".length() != 0 ? valueOf.concat(" OR (path > ? AND path < ?)") : new String(valueOf);
        strArr2[zzegu.size() + 1] = zzc;
        strArr2[zzegu.size() + 2] = zzpo;
        return this.zzmxy.query("serverCache", strArr, concat, strArr2, (String) null, (String) null, "path");
    }

    private static String zza(zzegu zzegu, int i) {
        String valueOf = String.valueOf(zzc(zzegu));
        String valueOf2 = String.valueOf(String.format(".part-%04d", new Object[]{Integer.valueOf(i)}));
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    private final void zza(zzegu zzegu, long j, String str, byte[] bArr) {
        String str2 = str;
        byte[] bArr2 = bArr;
        zzbvo();
        this.zzmxy.delete("writes", "id = ?", new String[]{String.valueOf(j)});
        if (bArr2.length >= 262144) {
            List<byte[]> zzh = zzh(bArr2, 262144);
            for (int i = 0; i < zzh.size(); i++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("id", Long.valueOf(j));
                contentValues.put("path", zzc(zzegu));
                contentValues.put(AppMeasurement.Param.TYPE, str2);
                contentValues.put("part", Integer.valueOf(i));
                contentValues.put("node", zzh.get(i));
                this.zzmxy.insertWithOnConflict("writes", (String) null, contentValues, 5);
            }
            return;
        }
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("id", Long.valueOf(j));
        contentValues2.put("path", zzc(zzegu));
        contentValues2.put(AppMeasurement.Param.TYPE, str2);
        contentValues2.put("part", (Integer) null);
        contentValues2.put("node", bArr2);
        this.zzmxy.insertWithOnConflict("writes", (String) null, contentValues2, 5);
    }

    private final void zza(zzegu zzegu, zzegu zzegu2, zzekw<Long> zzekw, zzekw<Long> zzekw2, zzekk zzekk, List<zzepa<zzegu, zzenn>> list) {
        zzekw<Long> zzekw3 = zzekw2;
        zzekk zzekk2 = zzekk;
        if (zzekw.getValue() != null) {
            int intValue = ((Integer) zzekk2.zza(0, new zzedl(this, zzekw3))).intValue();
            if (intValue > 0) {
                zzegu zzh = zzegu.zzh(zzegu2);
                if (this.zzmxz.zzcbu()) {
                    this.zzmxz.zzb(String.format("Need to rewrite %d nodes below path %s", new Object[]{Integer.valueOf(intValue), zzh}), (Throwable) null, new Object[0]);
                }
                zzekk2.zza(null, new zzedm(this, zzekw2, list, zzegu2, zzb(zzh)));
                return;
            }
            return;
        }
        Iterator<Map.Entry<zzemq, zzekw<Long>>> it = zzekw.zzcag().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            zzemq zzemq = (zzemq) next.getKey();
            zzekk zzd = zzekk2.zzd((zzemq) next.getKey());
            zzegu zzegu3 = zzegu2;
            zzekw<Long> zze = zzekw3.zze(zzemq);
            zza(zzegu, zzegu2.zza(zzemq), (zzekw) next.getValue(), zze, zzd, list);
        }
    }

    private final void zza(zzegu zzegu, zzenn zzenn, boolean z) {
        int i;
        int i2;
        long currentTimeMillis = System.currentTimeMillis();
        if (!z) {
            i = zza("serverCache", zzegu);
            i2 = zzc(zzegu, zzenn);
        } else {
            Iterator it = zzenn.iterator();
            int i3 = 0;
            int i4 = 0;
            while (it.hasNext()) {
                zzenm zzenm = (zzenm) it.next();
                i4 += zza("serverCache", zzegu.zza(zzenm.zzccx()));
                i3 += zzc(zzegu.zza(zzenm.zzccx()), zzenm.zzbve());
            }
            i2 = i3;
            i = i4;
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.zzmxz.zzcbu()) {
            this.zzmxz.zzb(String.format("Persisted a total of %d rows and deleted %d rows for a set at %s in %dms", new Object[]{Integer.valueOf(i2), Integer.valueOf(i), zzegu.toString(), Long.valueOf(currentTimeMillis2)}), (Throwable) null, new Object[0]);
        }
    }

    private static SQLiteDatabase zzah(Context context, String str) {
        try {
            SQLiteDatabase writableDatabase = new zzedn(context, str).getWritableDatabase();
            writableDatabase.rawQuery("PRAGMA locking_mode = EXCLUSIVE", (String[]) null).close();
            writableDatabase.beginTransaction();
            writableDatabase.endTransaction();
            return writableDatabase;
        } catch (SQLiteException e) {
            if (e instanceof SQLiteDatabaseLockedException) {
                throw new DatabaseException("Failed to gain exclusive lock to Firebase Database's offline persistence. This generally means you are using Firebase Database from multiple processes in your app. Keep in mind that multi-process Android apps execute the code in your Application class in all processes, so you may need to avoid initializing FirebaseDatabase in your Application class. If you are intentionally using Firebase Database from multiple processes, you can only enable offline persistence (i.e. call setPersistenceEnabled(true)) in one of them.", e);
            }
            throw e;
        }
    }

    private static zzenn zzan(byte[] bArr) {
        try {
            return zzenq.zza(zzeor.zzqi(new String(bArr, zzfnl)), zzene.zzcco());
        } catch (IOException e) {
            String str = new String(bArr, zzfnl);
            throw new RuntimeException(str.length() != 0 ? "Could not deserialize node: ".concat(str) : new String("Could not deserialize node: "), e);
        }
    }

    private static byte[] zzat(List<byte[]> list) {
        int i = 0;
        for (byte[] length : list) {
            i += length.length;
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        for (byte[] next : list) {
            System.arraycopy(next, 0, bArr, i2, next.length);
            i2 += next.length;
        }
        return bArr;
    }

    /* JADX INFO: finally extract failed */
    private final zzenn zzb(zzegu zzegu) {
        long j;
        zzegu zzegu2;
        zzenn zzenn;
        int i;
        zzegu zzegu3;
        zzegu zzegu4 = zzegu;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        Cursor zza = zza(zzegu4, new String[]{"path", FirebaseAnalytics.Param.VALUE});
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        long currentTimeMillis3 = System.currentTimeMillis();
        while (zza.moveToNext()) {
            try {
                arrayList.add(zza.getString(0));
                arrayList2.add(zza.getBlob(1));
            } catch (Throwable th) {
                zza.close();
                throw th;
            }
        }
        zza.close();
        long currentTimeMillis4 = System.currentTimeMillis() - currentTimeMillis3;
        long currentTimeMillis5 = System.currentTimeMillis();
        zzenn zzcco = zzene.zzcco();
        HashMap<zzegu, zzenn> hashMap = new HashMap<>();
        int i2 = 0;
        boolean z = false;
        while (true) {
            long j2 = currentTimeMillis4;
            if (i2 < arrayList2.size()) {
                if (((String) arrayList.get(i2)).endsWith(".part-0000")) {
                    String str = (String) arrayList.get(i2);
                    j = currentTimeMillis2;
                    zzegu zzegu5 = new zzegu(str.substring(0, str.length() - 10));
                    int zza2 = zza(zzegu5, (List<String>) arrayList, i2);
                    if (this.zzmxz.zzcbu()) {
                        zzemm zzemm = this.zzmxz;
                        StringBuilder sb = new StringBuilder(42);
                        sb.append("Loading split node with ");
                        sb.append(zza2);
                        sb.append(" parts.");
                        zzegu3 = zzegu5;
                        zzemm.zzb(sb.toString(), (Throwable) null, new Object[0]);
                    } else {
                        zzegu3 = zzegu5;
                    }
                    int i3 = zza2 + i2;
                    zzenn = zzan(zzat(arrayList2.subList(i2, i3)));
                    i2 = i3 - 1;
                    zzegu2 = zzegu3;
                } else {
                    j = currentTimeMillis2;
                    zzenn = zzan((byte[]) arrayList2.get(i2));
                    zzegu2 = new zzegu((String) arrayList.get(i2));
                }
                if (zzegu2.zzbyt() != null && zzegu2.zzbyt().zzcca()) {
                    hashMap.put(zzegu2, zzenn);
                } else if (zzegu2.zzi(zzegu4)) {
                    zzepd.zzb(!z, "Descendants of path must come after ancestors.");
                    zzcco = zzenn.zzan(zzegu.zza(zzegu2, zzegu4));
                } else if (zzegu4.zzi(zzegu2)) {
                    zzcco = zzcco.zzl(zzegu.zza(zzegu4, zzegu2), zzenn);
                    i = 1;
                    z = true;
                    i2 += i;
                    currentTimeMillis4 = j2;
                    currentTimeMillis2 = j;
                } else {
                    throw new IllegalStateException(String.format("Loading an unrelated row with path %s for %s", new Object[]{zzegu2, zzegu4}));
                }
                i = 1;
                i2 += i;
                currentTimeMillis4 = j2;
                currentTimeMillis2 = j;
            } else {
                long j3 = currentTimeMillis2;
                for (Map.Entry<zzegu, zzenn> entry : hashMap.entrySet()) {
                    zzcco = zzcco.zzl(zzegu.zza(zzegu4, entry.getKey()), entry.getValue());
                }
                long currentTimeMillis6 = System.currentTimeMillis() - currentTimeMillis5;
                long currentTimeMillis7 = System.currentTimeMillis() - currentTimeMillis;
                if (this.zzmxz.zzcbu()) {
                    this.zzmxz.zzb(String.format("Loaded a total of %d rows for a total of %d nodes at %s in %dms (Query: %dms, Loading: %dms, Serializing: %dms)", new Object[]{Integer.valueOf(arrayList2.size()), Integer.valueOf(zzeoy.zzo(zzcco)), zzegu4, Long.valueOf(currentTimeMillis7), Long.valueOf(j3), Long.valueOf(j2), Long.valueOf(currentTimeMillis6)}), (Throwable) null, new Object[0]);
                }
                return zzcco;
            }
        }
    }

    private static byte[] zzbi(Object obj) {
        try {
            return zzeor.zzbx(obj).getBytes(zzfnl);
        } catch (IOException e) {
            throw new RuntimeException("Could not serialize leaf node", e);
        }
    }

    private final void zzbvo() {
        zzepd.zzb(this.zzmya, "Transaction expected to already be in progress.");
    }

    private final int zzc(zzegu zzegu, zzenn zzenn) {
        long zzn = zzeoy.zzn(zzenn);
        if (!(zzenn instanceof zzems) || zzn <= PlaybackStateCompat.ACTION_PREPARE) {
            zzd(zzegu, zzenn);
            return 1;
        }
        int i = 0;
        if (this.zzmxz.zzcbu()) {
            this.zzmxz.zzb(String.format("Node estimated serialized size at path %s of %d bytes exceeds limit of %d bytes. Splitting up.", new Object[]{zzegu, Long.valueOf(zzn), 16384}), (Throwable) null, new Object[0]);
        }
        Iterator it = zzenn.iterator();
        while (it.hasNext()) {
            zzenm zzenm = (zzenm) it.next();
            i += zzc(zzegu.zza(zzenm.zzccx()), zzenm.zzbve());
        }
        if (!zzenn.zzcce().isEmpty()) {
            zzd(zzegu.zza(zzemq.zzcby()), zzenn.zzcce());
            i++;
        }
        zzd(zzegu, zzene.zzcco());
        return i + 1;
    }

    private static String zzc(zzegu zzegu) {
        return zzegu.isEmpty() ? "/" : String.valueOf(zzegu.toString()).concat("/");
    }

    private final void zzd(zzegu zzegu, zzenn zzenn) {
        byte[] zzbi = zzbi(zzenn.getValue(true));
        if (zzbi.length >= 262144) {
            List<byte[]> zzh = zzh(zzbi, 262144);
            if (this.zzmxz.zzcbu()) {
                zzemm zzemm = this.zzmxz;
                int size = zzh.size();
                StringBuilder sb = new StringBuilder(45);
                sb.append("Saving huge leaf node with ");
                sb.append(size);
                sb.append(" parts.");
                zzemm.zzb(sb.toString(), (Throwable) null, new Object[0]);
            }
            for (int i = 0; i < zzh.size(); i++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("path", zza(zzegu, i));
                contentValues.put(FirebaseAnalytics.Param.VALUE, zzh.get(i));
                this.zzmxy.insertWithOnConflict("serverCache", (String) null, contentValues, 5);
            }
            return;
        }
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("path", zzc(zzegu));
        contentValues2.put(FirebaseAnalytics.Param.VALUE, zzbi);
        this.zzmxy.insertWithOnConflict("serverCache", (String) null, contentValues2, 5);
    }

    private static List<byte[]> zzh(byte[] bArr, int i) {
        int length = ((bArr.length - 1) / 262144) + 1;
        ArrayList arrayList = new ArrayList(length);
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 262144;
            int min = Math.min(262144, bArr.length - i3);
            byte[] bArr2 = new byte[min];
            System.arraycopy(bArr, i3, bArr2, 0, min);
            arrayList.add(bArr2);
        }
        return arrayList;
    }

    private static String zzpo(String str) {
        String substring = str.substring(0, str.length() - 1);
        StringBuilder sb = new StringBuilder(String.valueOf(substring).length() + 1);
        sb.append(substring);
        sb.append('0');
        return sb.toString();
    }

    private static String zzr(Collection<Long> collection) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Long longValue : collection) {
            long longValue2 = longValue.longValue();
            if (!z) {
                sb.append(",");
            }
            z = false;
            sb.append(longValue2);
        }
        return sb.toString();
    }

    public final void beginTransaction() {
        zzepd.zzb(!this.zzmya, "runInTransaction called when an existing transaction is already in progress.");
        if (this.zzmxz.zzcbu()) {
            this.zzmxz.zzb("Starting transaction.", (Throwable) null, new Object[0]);
        }
        this.zzmxy.beginTransaction();
        this.zzmya = true;
        this.zzmyb = System.currentTimeMillis();
    }

    public final void endTransaction() {
        this.zzmxy.endTransaction();
        this.zzmya = false;
        long currentTimeMillis = System.currentTimeMillis() - this.zzmyb;
        if (this.zzmxz.zzcbu()) {
            this.zzmxz.zzb(String.format("Transaction completed. Elapsed: %dms", new Object[]{Long.valueOf(currentTimeMillis)}), (Throwable) null, new Object[0]);
        }
    }

    public final void setTransactionSuccessful() {
        this.zzmxy.setTransactionSuccessful();
    }

    public final zzenn zza(zzegu zzegu) {
        return zzb(zzegu);
    }

    public final void zza(long j, Set<zzemq> set) {
        zzbvo();
        long currentTimeMillis = System.currentTimeMillis();
        String valueOf = String.valueOf(j);
        this.zzmxy.delete("trackedKeys", "id = ?", new String[]{valueOf});
        for (zzemq asString : set) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", Long.valueOf(j));
            contentValues.put("key", asString.asString());
            this.zzmxy.insertWithOnConflict("trackedKeys", (String) null, contentValues, 5);
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.zzmxz.zzcbu()) {
            this.zzmxz.zzb(String.format("Set %d tracked query keys for tracked query %d in %dms", new Object[]{Integer.valueOf(set.size()), Long.valueOf(j), Long.valueOf(currentTimeMillis2)}), (Throwable) null, new Object[0]);
        }
    }

    public final void zza(long j, Set<zzemq> set, Set<zzemq> set2) {
        zzbvo();
        long currentTimeMillis = System.currentTimeMillis();
        String valueOf = String.valueOf(j);
        for (zzemq asString : set2) {
            this.zzmxy.delete("trackedKeys", "id = ? AND key = ?", new String[]{valueOf, asString.asString()});
        }
        for (zzemq asString2 : set) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", Long.valueOf(j));
            contentValues.put("key", asString2.asString());
            this.zzmxy.insertWithOnConflict("trackedKeys", (String) null, contentValues, 5);
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.zzmxz.zzcbu()) {
            this.zzmxz.zzb(String.format("Updated tracked query keys (%d added, %d removed) for tracked query id %d in %dms", new Object[]{Integer.valueOf(set.size()), Integer.valueOf(set2.size()), Long.valueOf(j), Long.valueOf(currentTimeMillis2)}), (Throwable) null, new Object[0]);
        }
    }

    public final void zza(zzegu zzegu, zzegi zzegi) {
        zzbvo();
        long currentTimeMillis = System.currentTimeMillis();
        Iterator<Map.Entry<zzegu, zzenn>> it = zzegi.iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            Map.Entry next = it.next();
            i += zza("serverCache", zzegu.zzh((zzegu) next.getKey()));
            i2 += zzc(zzegu.zzh((zzegu) next.getKey()), (zzenn) next.getValue());
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.zzmxz.zzcbu()) {
            this.zzmxz.zzb(String.format("Persisted a total of %d rows and deleted %d rows for a merge at %s in %dms", new Object[]{Integer.valueOf(i2), Integer.valueOf(i), zzegu.toString(), Long.valueOf(currentTimeMillis2)}), (Throwable) null, new Object[0]);
        }
    }

    public final void zza(zzegu zzegu, zzegi zzegi, long j) {
        zzbvo();
        long currentTimeMillis = System.currentTimeMillis();
        zza(zzegu, j, "m", zzbi(zzegi.zzcu(true)));
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.zzmxz.zzcbu()) {
            this.zzmxz.zzb(String.format("Persisted user merge in %dms", new Object[]{Long.valueOf(currentTimeMillis2)}), (Throwable) null, new Object[0]);
        }
    }

    public final void zza(zzegu zzegu, zzekk zzekk) {
        int i;
        int i2;
        zzemm zzemm;
        StringBuilder sb;
        String str;
        zzegu zzegu2 = zzegu;
        zzekk zzekk2 = zzekk;
        if (zzekk.zzcab()) {
            zzbvo();
            long currentTimeMillis = System.currentTimeMillis();
            Cursor zza = zza(zzegu2, new String[]{"rowid", "path"});
            zzekw zzekw = new zzekw(null);
            zzekw zzekw2 = new zzekw(null);
            while (zza.moveToNext()) {
                long j = zza.getLong(0);
                zzegu zzegu3 = new zzegu(zza.getString(1));
                zzemm = null;
                sb = null;
                str = null;
                if (!zzegu2.zzi(zzegu3)) {
                    zzemm = this.zzmxz;
                    String valueOf = String.valueOf(zzegu);
                    String valueOf2 = String.valueOf(zzegu3);
                    sb = new StringBuilder(String.valueOf(valueOf).length() + 67 + String.valueOf(valueOf2).length());
                    sb.append("We are pruning at ");
                    sb.append(valueOf);
                    sb.append(" but we have data stored higher up at ");
                    sb.append(valueOf2);
                    str = ". Ignoring.";
                } else {
                    zzegu zza2 = zzegu.zza(zzegu2, zzegu3);
                    if (zzekk2.zzv(zza2)) {
                        zzekw = zzekw.zzb(zza2, Long.valueOf(j));
                    } else if (zzekk2.zzw(zza2)) {
                        zzekw2 = zzekw2.zzb(zza2, Long.valueOf(j));
                    } else {
                        zzemm = this.zzmxz;
                        String valueOf3 = String.valueOf(zzegu);
                        String valueOf4 = String.valueOf(zzegu3);
                        sb = new StringBuilder(String.valueOf(valueOf3).length() + 88 + String.valueOf(valueOf4).length());
                        sb.append("We are pruning at ");
                        sb.append(valueOf3);
                        sb.append(" and have data at ");
                        sb.append(valueOf4);
                        str = " that isn't marked for pruning or keeping. Ignoring.";
                    }
                }
                if (zzemm != null && sb != null && str != null) {
                    sb.append(str);
                    zzemm.zzf(sb.toString(), (Throwable) null);
                }
            }
            if (!zzekw.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                zza(zzegu, zzegu.zzbyn(), zzekw, zzekw2, zzekk, arrayList);
                Collection values = zzekw.values();
                String zzr = zzr(values);
                StringBuilder sb2 = new StringBuilder(String.valueOf(zzr).length() + 11);
                sb2.append("rowid IN (");
                sb2.append(zzr);
                sb2.append(")");
                this.zzmxy.delete("serverCache", sb2.toString(), (String[]) null);
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                int i3 = 0;
                while (i3 < size) {
                    Object obj = arrayList2.get(i3);
                    i3++;
                    zzepa zzepa = (zzepa) obj;
                    zzc(zzegu2.zzh((zzegu) zzepa.getFirst()), (zzenn) zzepa.zzcdp());
                }
                i2 = values.size();
                i = arrayList.size();
            } else {
                i2 = 0;
                i = 0;
            }
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            if (this.zzmxz.zzcbu()) {
                this.zzmxz.zzb(String.format("Pruned %d rows with %d nodes resaved in %dms", new Object[]{Integer.valueOf(i2), Integer.valueOf(i), Long.valueOf(currentTimeMillis2)}), (Throwable) null, new Object[0]);
            }
        }
    }

    public final void zza(zzegu zzegu, zzenn zzenn) {
        zzbvo();
        zza(zzegu, zzenn, false);
    }

    public final void zza(zzegu zzegu, zzenn zzenn, long j) {
        zzbvo();
        long currentTimeMillis = System.currentTimeMillis();
        zza(zzegu, j, "o", zzbi(zzenn.getValue(true)));
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.zzmxz.zzcbu()) {
            this.zzmxz.zzb(String.format("Persisted user overwrite in %dms", new Object[]{Long.valueOf(currentTimeMillis2)}), (Throwable) null, new Object[0]);
        }
    }

    public final void zza(zzeko zzeko) {
        zzbvo();
        long currentTimeMillis = System.currentTimeMillis();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Long.valueOf(zzeko.id));
        contentValues.put("path", zzc(zzeko.zznkr.zzbvh()));
        contentValues.put("queryParams", zzeko.zznkr.zzcbh().zzcbf());
        contentValues.put("lastUse", Long.valueOf(zzeko.zznks));
        contentValues.put("complete", Boolean.valueOf(zzeko.complete));
        contentValues.put("active", Boolean.valueOf(zzeko.zzjgp));
        this.zzmxy.insertWithOnConflict("trackedQueries", (String) null, contentValues, 5);
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.zzmxz.zzcbu()) {
            this.zzmxz.zzb(String.format("Saved new tracked query in %dms", new Object[]{Long.valueOf(currentTimeMillis2)}), (Throwable) null, new Object[0]);
        }
    }

    public final void zzb(zzegu zzegu, zzenn zzenn) {
        zzbvo();
        zza(zzegu, zzenn, true);
    }

    public final void zzbm(long j) {
        zzbvo();
        long currentTimeMillis = System.currentTimeMillis();
        int delete = this.zzmxy.delete("writes", "id = ?", new String[]{String.valueOf(j)});
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.zzmxz.zzcbu()) {
            this.zzmxz.zzb(String.format("Deleted %d write(s) with writeId %d in %dms", new Object[]{Integer.valueOf(delete), Long.valueOf(j), Long.valueOf(currentTimeMillis2)}), (Throwable) null, new Object[0]);
        }
    }

    public final void zzbn(long j) {
        zzbvo();
        String valueOf = String.valueOf(j);
        this.zzmxy.delete("trackedQueries", "id = ?", new String[]{valueOf});
        this.zzmxy.delete("trackedKeys", "id = ?", new String[]{valueOf});
    }

    public final void zzbo(long j) {
        zzbvo();
        long currentTimeMillis = System.currentTimeMillis();
        ContentValues contentValues = new ContentValues();
        contentValues.put("active", false);
        contentValues.put("lastUse", Long.valueOf(j));
        this.zzmxy.updateWithOnConflict("trackedQueries", contentValues, "active = 1", new String[0], 5);
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.zzmxz.zzcbu()) {
            this.zzmxz.zzb(String.format("Reset active tracked queries in %dms", new Object[]{Long.valueOf(currentTimeMillis2)}), (Throwable) null, new Object[0]);
        }
    }

    public final Set<zzemq> zzbp(long j) {
        return zzg(Collections.singleton(Long.valueOf(j)));
    }

    public final List<zzejn> zzbvk() {
        byte[] bArr;
        zzejn zzejn;
        String[] strArr = {"id", "path", AppMeasurement.Param.TYPE, "part", "node"};
        long currentTimeMillis = System.currentTimeMillis();
        Cursor query = this.zzmxy.query("writes", strArr, (String) null, (String[]) null, (String) null, (String) null, "id, part");
        ArrayList arrayList = new ArrayList();
        while (query.moveToNext()) {
            try {
                long j = query.getLong(0);
                zzegu zzegu = new zzegu(query.getString(1));
                String string = query.getString(2);
                if (query.isNull(3)) {
                    bArr = query.getBlob(4);
                } else {
                    ArrayList arrayList2 = new ArrayList();
                    do {
                        arrayList2.add(query.getBlob(4));
                        if (!query.moveToNext() || query.getLong(0) != j) {
                            query.moveToPrevious();
                            bArr = zzat(arrayList2);
                        }
                        arrayList2.add(query.getBlob(4));
                        break;
                    } while (query.getLong(0) != j);
                    query.moveToPrevious();
                    bArr = zzat(arrayList2);
                }
                Object zzqi = zzeor.zzqi(new String(bArr, zzfnl));
                if ("o".equals(string)) {
                    zzejn = new zzejn(j, zzegu, zzenq.zza(zzqi, zzene.zzcco()), true);
                } else if ("m".equals(string)) {
                    zzejn = new zzejn(j, zzegu, zzegi.zzam((Map) zzqi));
                } else {
                    String valueOf = String.valueOf(string);
                    throw new IllegalStateException(valueOf.length() != 0 ? "Got invalid write type: ".concat(valueOf) : new String("Got invalid write type: "));
                }
                arrayList.add(zzejn);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load writes", e);
            } catch (Throwable th) {
                query.close();
                throw th;
            }
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.zzmxz.zzcbu()) {
            this.zzmxz.zzb(String.format("Loaded %d writes in %dms", new Object[]{Integer.valueOf(arrayList.size()), Long.valueOf(currentTimeMillis2)}), (Throwable) null, new Object[0]);
        }
        query.close();
        return arrayList;
    }

    public final long zzbvl() {
        Cursor rawQuery = this.zzmxy.rawQuery(String.format("SELECT sum(length(%s) + length(%s)) FROM %s", new Object[]{FirebaseAnalytics.Param.VALUE, "path", "serverCache"}), (String[]) null);
        try {
            if (rawQuery.moveToFirst()) {
                return rawQuery.getLong(0);
            }
            throw new IllegalStateException("Couldn't read database result!");
        } finally {
            rawQuery.close();
        }
    }

    public final List<zzeko> zzbvm() {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor query = this.zzmxy.query("trackedQueries", new String[]{"id", "path", "queryParams", "lastUse", "complete", "active"}, (String) null, (String[]) null, (String) null, (String) null, "id");
        ArrayList arrayList = new ArrayList();
        while (query.moveToNext()) {
            try {
                arrayList.add(new zzeko(query.getLong(0), new zzelu(new zzegu(query.getString(1)), zzelr.zzao(zzeor.zzqh(query.getString(2)))), query.getLong(3), query.getInt(4) != 0, query.getInt(5) != 0));
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (Throwable th) {
                query.close();
                throw th;
            }
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.zzmxz.zzcbu()) {
            this.zzmxz.zzb(String.format("Loaded %d tracked queries in %dms", new Object[]{Integer.valueOf(arrayList.size()), Long.valueOf(currentTimeMillis2)}), (Throwable) null, new Object[0]);
        }
        query.close();
        return arrayList;
    }

    public final void zzbvn() {
        zzbvo();
        long currentTimeMillis = System.currentTimeMillis();
        int delete = this.zzmxy.delete("writes", (String) null, (String[]) null);
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.zzmxz.zzcbu()) {
            this.zzmxz.zzb(String.format("Deleted %d (all) write(s) in %dms", new Object[]{Integer.valueOf(delete), Long.valueOf(currentTimeMillis2)}), (Throwable) null, new Object[0]);
        }
    }

    public final Set<zzemq> zzg(Set<Long> set) {
        long currentTimeMillis = System.currentTimeMillis();
        String zzr = zzr(set);
        StringBuilder sb = new StringBuilder(String.valueOf(zzr).length() + 8);
        sb.append("id IN (");
        sb.append(zzr);
        sb.append(")");
        String sb2 = sb.toString();
        Cursor query = this.zzmxy.query(true, "trackedKeys", new String[]{"key"}, sb2, (String[]) null, (String) null, (String) null, (String) null, (String) null);
        HashSet hashSet = new HashSet();
        while (query.moveToNext()) {
            try {
                hashSet.add(zzemq.zzqf(query.getString(0)));
            } finally {
                query.close();
            }
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.zzmxz.zzcbu()) {
            this.zzmxz.zzb(String.format("Loaded %d tracked queries keys for tracked queries %s in %dms", new Object[]{Integer.valueOf(hashSet.size()), set.toString(), Long.valueOf(currentTimeMillis2)}), (Throwable) null, new Object[0]);
        }
        return hashSet;
    }
}
