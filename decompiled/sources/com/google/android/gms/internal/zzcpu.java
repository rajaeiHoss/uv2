package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.nearby.connection.Payload;
import com.google.android.gms.nearby.connection.PayloadCallback;

final class zzcpu extends zzcps<PayloadCallback> {
    private /* synthetic */ zzctp zzjya;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcpu(zzcpt zzcpt, zzctp zzctp) {
        super();
        this.zzjya = zzctp;
    }

    public final void zzu(PayloadCallback payloadCallback) {
        Payload zza = zzcud.zza(this.zzjya.zzbdk());
        if (zza == null) {
            Log.w("NearbyConnectionsClient", String.format("Failed to convert incoming ParcelablePayload %d to Payload.", new Object[]{Long.valueOf(this.zzjya.zzbdk().getId())}));
            return;
        }
        payloadCallback.onPayloadReceived(this.zzjya.zzbde(), zza);
    }
}
