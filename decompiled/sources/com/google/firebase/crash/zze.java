package com.google.firebase.crash;

import android.app.Application;
import android.util.Log;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.common.api.internal.zzk;
import com.google.android.gms.common.api.internal.zzl;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzecr;
import com.google.android.gms.internal.zzect;
import com.google.android.gms.internal.zzecv;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.iid.FirebaseInstanceId;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

final class zze implements Runnable {
    private /* synthetic */ zzc zzmux;
    private /* synthetic */ Future zzmuy;
    private /* synthetic */ long zzmuz = NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
    private /* synthetic */ zzg zzmva;

    zze(zzc zzc, Future future, long j, zzg zzg) {
        this.zzmux = zzc;
        this.zzmuy = future;
        this.zzmva = zzg;
    }

    public final void run() {
        zzect zzect;
        boolean z = true;
        try {
            zzect = (zzect) this.zzmuy.get(this.zzmuz, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            Log.e("FirebaseCrash", "Failed to initialize crash reporting", e);
            this.zzmuy.cancel(true);
            zzect = null;
        }
        if (zzect == null) {
            this.zzmva.zzbuy();
            return;
        }
        try {
            FirebaseOptions options = this.zzmux.zzmpb.getOptions();
            zzect.zza(zzn.zzz(this.zzmux.mContext), new zzecr(options.getApplicationId(), options.getApiKey()));
            if (this.zzmux.zzmuw == null) {
                String unused = this.zzmux.zzmuw = FirebaseInstanceId.getInstance().getId();
            }
            zzect.zzpl(this.zzmux.zzmuw);
            zzect.zzas(new ArrayList());
            zzk.zza((Application) this.zzmux.mContext.getApplicationContext());
            if (zzk.zzaij().zzaik()) {
                z = false;
            }
            zzect.zzcp(z);
            zzk.zzaij().zza((zzl) new zzf(this));
            String valueOf = String.valueOf(zzecv.zzbva());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 36);
            sb.append("FirebaseCrash reporting initialized ");
            sb.append(valueOf);
            Log.i("FirebaseCrash", sb.toString());
            this.zzmva.zzc(zzect);
        } catch (Exception e2) {
            String valueOf2 = String.valueOf(e2.getMessage());
            Log.e("FirebaseCrash", valueOf2.length() != 0 ? "Failed to initialize crash reporting: ".concat(valueOf2) : new String("Failed to initialize crash reporting: "));
            com.google.android.gms.common.util.zzg.zza(this.zzmux.mContext, e2);
            this.zzmva.zzbuy();
        }
    }
}
