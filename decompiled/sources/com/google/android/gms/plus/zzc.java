package com.google.android.gms.plus;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.util.zzv;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.internal.PlusCommonExtras;
import com.google.android.gms.plus.internal.zzh;
import com.google.android.gms.plus.internal.zzn;

final class zzc extends Api.zza<zzh, Plus.PlusOptions> {
    zzc() {
    }

    public final int getPriority() {
        return 2;
    }

    public final zzh zza(Context context, Looper looper, zzr zzr, Plus.PlusOptions plusOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        if (plusOptions == null) {
            plusOptions = new Plus.PlusOptions((zzc) null);
        }
        return new zzh(context, looper, zzr, new zzn(zzr.zzamd().name, zzv.zzc(zzr.zzamg()), (String[]) plusOptions.zzkhl.toArray(new String[0]), new String[0], context.getPackageName(), context.getPackageName(), (String) null, new PlusCommonExtras()), connectionCallbacks, onConnectionFailedListener);
    }
}
