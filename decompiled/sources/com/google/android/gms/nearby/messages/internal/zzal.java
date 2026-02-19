package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.PublishOptions;

final /* synthetic */ class zzal implements zzbd {
    private final zzak zzkdf;
    private final Message zzkdg;
    private final zzbe zzkdh;
    private final PublishOptions zzkdi;

    zzal(zzak zzak, Message message, zzbe zzbe, PublishOptions publishOptions) {
        this.zzkdf = zzak;
        this.zzkdg = message;
        this.zzkdh = zzbe;
        this.zzkdi = publishOptions;
    }

    public final void zza(zzah zzah, zzci zzci) throws RemoteException {
        this.zzkdf.zza(this.zzkdg, this.zzkdh, this.zzkdi, zzah, zzci);
    }
}
