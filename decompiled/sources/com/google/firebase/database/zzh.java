package com.google.firebase.database;

import com.google.android.gms.internal.zzegu;
import com.google.android.gms.internal.zzegx;
import com.google.android.gms.internal.zzeng;
import com.google.android.gms.internal.zzenn;

public final class zzh {
    public static DataSnapshot zza(DatabaseReference databaseReference, zzeng zzeng) {
        return new DataSnapshot(databaseReference, zzeng);
    }

    public static DatabaseReference zza(zzegx zzegx, zzegu zzegu) {
        return new DatabaseReference(zzegx, zzegu);
    }

    public static MutableData zza(zzenn zzenn) {
        return new MutableData(zzenn);
    }
}
