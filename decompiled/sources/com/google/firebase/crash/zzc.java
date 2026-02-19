package com.google.firebase.crash;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.util.zzg;
import com.google.android.gms.internal.zzccp;
import com.google.android.gms.internal.zzect;
import com.google.android.gms.internal.zzecv;
import com.google.android.gms.internal.zzecw;
import com.google.android.gms.internal.zzecz;
import com.google.firebase.FirebaseApp;

public final class zzc {
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final FirebaseApp zzmpb;
    /* access modifiers changed from: private */
    public String zzmuw = null;

    zzc(FirebaseApp firebaseApp, String str) {
        this.mContext = firebaseApp.getApplicationContext();
        this.zzmpb = firebaseApp;
    }

    public final zzect zzbuz() {
        zzecz.initialize(this.mContext);
        zzect zzect = null;
        if (((Boolean) zzccp.zzaso().zzb(zzecz.zzmvk)).booleanValue()) {
            try {
                zzecv.zzbva().zzch(this.mContext);
                zzect = zzecv.zzbva().zzbvb();
                String valueOf = String.valueOf(zzecv.zzbva());
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 33);
                sb.append("FirebaseCrash reporting loaded - ");
                sb.append(valueOf);
                Log.i("FirebaseCrash", sb.toString());
                return zzect;
            } catch (zzecw e) {
                Log.e("FirebaseCrash", "Failed to load crash reporting", e);
                zzg.zza(this.mContext, e);
                return zzect;
            }
        } else {
            Log.w("FirebaseCrash", "Crash reporting is disabled");
            return null;
        }
    }
}
