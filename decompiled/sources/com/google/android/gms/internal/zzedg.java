package com.google.android.gms.internal;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.connection.idl.zzc;
import com.google.firebase.database.connection.idl.zzf;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;

public final class zzedg implements zzegw {
    /* access modifiers changed from: private */
    public final Context zzajv;
    private final FirebaseApp zzmxo;
    private final Set<String> zzmxt = new HashSet();

    public zzedg(FirebaseApp firebaseApp) {
        this.zzmxo = firebaseApp;
        if (firebaseApp != null) {
            this.zzajv = firebaseApp.getApplicationContext();
            return;
        }
        Log.e("FirebaseDatabase", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        Log.e("FirebaseDatabase", "ERROR: You must call FirebaseApp.initializeApp() before using Firebase Database.");
        Log.e("FirebaseDatabase", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        throw new RuntimeException("You need to call FirebaseApp.initializeApp() before using Firebase Database.");
    }

    public final zzeew zza(zzegm zzegm, zzees zzees, zzeeu zzeeu, zzeex zzeex) {
        zzf zza = zzf.zza(this.zzajv, new zzc(zzeeu, zzegm.zzbyh(), (List<String>) null, zzegm.isPersistenceEnabled(), FirebaseDatabase.getSdkVersion(), zzegm.zzbwo(), zzbvj()), zzees, zzeex);
        this.zzmxo.zza((FirebaseApp.zza) new zzedj(this, zza));
        return zza;
    }

    public final zzegd zza(ScheduledExecutorService scheduledExecutorService) {
        return new zzeda(this.zzmxo, scheduledExecutorService);
    }

    public final zzegt zza(zzegm zzegm) {
        return new zzedf();
    }

    public final zzeki zza(zzegm zzegm, String str) {
        String zzbyk = zzegm.zzbyk();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(zzbyk).length());
        sb.append(str);
        sb.append("_");
        sb.append(zzbyk);
        String sb2 = sb.toString();
        if (!this.zzmxt.contains(sb2)) {
            this.zzmxt.add(sb2);
            return new zzekf(zzegm, new zzedk(this.zzajv, zzegm, sb2), new zzekg(zzegm.zzbyi()));
        }
        StringBuilder sb3 = new StringBuilder(String.valueOf(zzbyk).length() + 47);
        sb3.append("SessionPersistenceKey '");
        sb3.append(zzbyk);
        sb3.append("' has already been used.");
        throw new DatabaseException(sb3.toString());
    }

    public final zzemn zza(zzegm zzegm, zzemo zzemo, List<String> list) {
        return new zzemj(zzemo, (List<String>) null);
    }

    public final zzeig zzb(zzegm zzegm) {
        return new zzedh(this, zzegm.zzqb("RunLoop"));
    }

    public final File zzbvj() {
        return this.zzajv.getApplicationContext().getDir("sslcache", 0);
    }

    public final String zzc(zzegm zzegm) {
        int i = Build.VERSION.SDK_INT;
        StringBuilder sb = new StringBuilder(19);
        sb.append(i);
        sb.append("/Android");
        return sb.toString();
    }
}
