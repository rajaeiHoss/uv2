package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.nearby.connection.Connections;
import com.google.android.gms.nearby.connection.Payload;

final class zzcpq extends zzcps<Connections.MessageListener> {
    private /* synthetic */ zzctp zzjya;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcpq(zzcpp zzcpp, zzctp zzctp) {
        super();
        this.zzjya = zzctp;
    }

    public final void zzu(Connections.MessageListener messageListener) {
        Payload zza = zzcud.zza(this.zzjya.zzbdk());
        if (zza == null) {
            Log.w("NearbyConnectionsClient", String.format("Failed to convert incoming ParcelablePayload %d to Payload.", new Object[]{Long.valueOf(this.zzjya.zzbdk().getId())}));
        } else if (zza.getType() == 1) {
            messageListener.onMessageReceived(this.zzjya.zzbde(), zza.asBytes(), this.zzjya.zzbdl());
        }
    }
}
