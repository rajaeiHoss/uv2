package com.google.firebase.database;

import android.text.TextUtils;
import com.google.android.gms.internal.zzegp;
import com.google.android.gms.internal.zzegu;
import com.google.android.gms.internal.zzegx;
import com.google.android.gms.internal.zzeia;
import com.google.android.gms.internal.zzeib;
import com.google.android.gms.internal.zzepb;
import com.google.android.gms.internal.zzepd;
import com.google.android.gms.internal.zzepf;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FirebaseDatabase {
    private static final Map<String, Map<zzeia, FirebaseDatabase>> zzmwp = new HashMap();
    private final FirebaseApp zzmwq;
    private final zzeia zzmwr;
    private final zzegp zzmws;
    /* access modifiers changed from: private */
    public zzegx zzmwt;

    private FirebaseDatabase(FirebaseApp firebaseApp, zzeia zzeia, zzegp zzegp) {
        this.zzmwq = firebaseApp;
        this.zzmwr = zzeia;
        this.zzmws = zzegp;
    }

    public static FirebaseDatabase getInstance() {
        FirebaseApp instance = FirebaseApp.getInstance();
        if (instance != null) {
            return getInstance(instance, instance.getOptions().getDatabaseUrl());
        }
        throw new DatabaseException("You must call FirebaseApp.initialize() first.");
    }

    public static FirebaseDatabase getInstance(FirebaseApp firebaseApp) {
        return getInstance(firebaseApp, firebaseApp.getOptions().getDatabaseUrl());
    }

    public static synchronized FirebaseDatabase getInstance(FirebaseApp firebaseApp, String str) {
        FirebaseDatabase firebaseDatabase;
        synchronized (FirebaseDatabase.class) {
            if (!TextUtils.isEmpty(str)) {
                Map<String, Map<zzeia, FirebaseDatabase>> map = zzmwp;
                Map map2 = map.get(firebaseApp.getName());
                if (map2 == null) {
                    map2 = new HashMap();
                    map.put(firebaseApp.getName(), map2);
                }
                zzepb zzqj = zzepd.zzqj(str);
                if (zzqj.zzmxa.isEmpty()) {
                    firebaseDatabase = (FirebaseDatabase) map2.get(zzqj.zzmwr);
                    if (firebaseDatabase == null) {
                        zzegp zzegp = new zzegp();
                        if (!firebaseApp.zzbsu()) {
                            zzegp.zzqd(firebaseApp.getName());
                        }
                        zzegp.zzd(firebaseApp);
                        FirebaseDatabase firebaseDatabase2 = new FirebaseDatabase(firebaseApp, zzqj.zzmwr, zzegp);
                        map2.put(zzqj.zzmwr, firebaseDatabase2);
                        firebaseDatabase = firebaseDatabase2;
                    }
                } else {
                    String zzegu = zzqj.zzmxa.toString();
                    StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 113 + String.valueOf(zzegu).length());
                    sb.append("Specified Database URL '");
                    sb.append(str);
                    sb.append("' is invalid. It should point to the root of a Firebase Database but it includes a path: ");
                    sb.append(zzegu);
                    throw new DatabaseException(sb.toString());
                }
            } else {
                throw new DatabaseException("Failed to get FirebaseDatabase instance: Specify DatabaseURL within FirebaseApp or from your getInstance() call.");
            }
        }
        return firebaseDatabase;
    }

    public static FirebaseDatabase getInstance(String str) {
        FirebaseApp instance = FirebaseApp.getInstance();
        if (instance != null) {
            return getInstance(instance, str);
        }
        throw new DatabaseException("You must call FirebaseApp.initialize() first.");
    }

    public static String getSdkVersion() {
        return "3.0.0";
    }

    private final synchronized void zzbvd() {
        if (this.zzmwt == null) {
            this.zzmwt = zzeib.zza(this.zzmws, this.zzmwr, this);
        }
    }

    private final void zzpn(String str) {
        if (this.zzmwt != null) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 77);
            sb.append("Calls to ");
            sb.append(str);
            sb.append("() must be made before any other usage of FirebaseDatabase instance.");
            throw new DatabaseException(sb.toString());
        }
    }

    public FirebaseApp getApp() {
        return this.zzmwq;
    }

    public DatabaseReference getReference() {
        zzbvd();
        return new DatabaseReference(this.zzmwt, zzegu.zzbyn());
    }

    public DatabaseReference getReference(String str) {
        zzbvd();
        Objects.requireNonNull(str, "Can't pass null for argument 'pathString' in FirebaseDatabase.getReference()");
        zzepf.zzqo(str);
        return new DatabaseReference(this.zzmwt, new zzegu(str));
    }

    public DatabaseReference getReferenceFromUrl(String str) {
        zzbvd();
        Objects.requireNonNull(str, "Can't pass null for argument 'url' in FirebaseDatabase.getReferenceFromUrl()");
        zzepb zzqj = zzepd.zzqj(str);
        if (zzqj.zzmwr.host.equals(this.zzmwt.zzbyv().host)) {
            return new DatabaseReference(this.zzmwt, zzqj.zzmxa);
        }
        String databaseReference = getReference().toString();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 93 + String.valueOf(databaseReference).length());
        sb.append("Invalid URL (");
        sb.append(str);
        sb.append(") passed to getReference().  URL was expected to match configured Database URL: ");
        sb.append(databaseReference);
        throw new DatabaseException(sb.toString());
    }

    public void goOffline() {
        zzbvd();
        zzeib.zzk(this.zzmwt);
    }

    public void goOnline() {
        zzbvd();
        zzeib.zzl(this.zzmwt);
    }

    public void purgeOutstandingWrites() {
        zzbvd();
        this.zzmwt.zzp(new zzg(this));
    }

    public synchronized void setLogLevel(Logger.Level level) {
        zzpn("setLogLevel");
        this.zzmws.setLogLevel(level);
    }

    public synchronized void setPersistenceCacheSizeBytes(long j) {
        zzpn("setPersistenceCacheSizeBytes");
        this.zzmws.setPersistenceCacheSizeBytes(j);
    }

    public synchronized void setPersistenceEnabled(boolean z) {
        zzpn("setPersistenceEnabled");
        this.zzmws.setPersistenceEnabled(z);
    }
}
