package com.google.android.gms.common.api.internal;

import android.app.Activity;
import androidx.collection.ArraySet;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzbq;

public class zzah extends zzo {
    private zzbm zzfsq;
    private final ArraySet<zzh<?>> zzfxa = new ArraySet<>();

    private zzah(zzcf zzcf) {
        super(zzcf);
        this.zzgam.zza("ConnectionlessLifecycleHelper", (LifecycleCallback) this);
    }

    public static void zza(Activity activity, zzbm zzbm, zzh<?> zzh) {
        zzcf zzo = zzo(activity);
        zzah zzah = (zzah) zzo.zza("ConnectionlessLifecycleHelper", zzah.class);
        if (zzah == null) {
            zzah = new zzah(zzo);
        }
        zzah.zzfsq = zzbm;
        zzbq.checkNotNull(zzh, "ApiKey cannot be null");
        zzah.zzfxa.add(zzh);
        zzbm.zza(zzah);
    }

    private final void zzajg() {
        if (!this.zzfxa.isEmpty()) {
            this.zzfsq.zza(this);
        }
    }

    public final void onResume() {
        super.onResume();
        zzajg();
    }

    public final void onStart() {
        super.onStart();
        zzajg();
    }

    public final void onStop() {
        super.onStop();
        this.zzfsq.zzb(this);
    }

    /* access modifiers changed from: protected */
    public final void zza(ConnectionResult connectionResult, int i) {
        this.zzfsq.zza(connectionResult, i);
    }

    /* access modifiers changed from: protected */
    public final void zzaih() {
        this.zzfsq.zzaih();
    }

    /* access modifiers changed from: package-private */
    public final ArraySet<zzh<?>> zzajf() {
        return this.zzfxa;
    }
}
