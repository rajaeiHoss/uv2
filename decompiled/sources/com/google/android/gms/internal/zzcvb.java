package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.nearby.messages.internal.Update;
import com.google.android.gms.nearby.messages.internal.zzaf;
import com.google.android.gms.nearby.messages.internal.zzn;
import java.util.Collections;
import java.util.List;

public final class zzcvb extends zzn {
    private final zzci<MessageListener> zzkes;

    public zzcvb(zzci<MessageListener> zzci) {
        this.zzkes = zzci;
    }

    public static void zza(Intent intent, MessageListener messageListener) {
        Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.nearby.messages.UPDATES");
        List<Update> updates = bundleExtra == null ? Collections.<Update>emptyList() : bundleExtra.getParcelableArrayList("com.google.android.gms.nearby.messages.UPDATES");
        zza(updates, messageListener);
    }

    public static void zza(Iterable<Update> iterable, MessageListener messageListener) {
        for (Update next : iterable) {
            if (next.zzeu(1)) {
                messageListener.onFound(next.zzkda);
            }
            if (next.zzeu(2)) {
                messageListener.onLost(next.zzkda);
            }
            if (next.zzeu(4)) {
                messageListener.onDistanceChanged(next.zzkda, next.zzkeo);
            }
            if (next.zzeu(8)) {
                messageListener.onBleSignalChanged(next.zzkda, next.zzkep);
            }
            if (next.zzeu(16)) {
                Message message = next.zzkda;
                zzcux zzcux = next.zzkeq;
            }
        }
    }

    public final void zza(zzaf zzaf) {
    }

    public final void zzaj(List<Update> list) throws RemoteException {
        this.zzkes.zza(new zzcvc(this, list));
    }

    public final void zzb(zzaf zzaf) {
    }
}
