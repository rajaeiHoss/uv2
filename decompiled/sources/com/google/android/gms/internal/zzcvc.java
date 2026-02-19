package com.google.android.gms.internal;

import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.nearby.messages.internal.Update;
import java.util.List;

final class zzcvc extends zzcvf<MessageListener> {
    private /* synthetic */ List zzket;

    zzcvc(zzcvb zzcvb, List list) {
        this.zzket = list;
    }

    public final void zzu(MessageListener messageListener) {
        zzcvb.zza((Iterable<Update>) this.zzket, messageListener);
    }
}
