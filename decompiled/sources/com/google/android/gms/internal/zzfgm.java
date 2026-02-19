package com.google.android.gms.internal;

import com.google.android.gms.internal.zzfjc;

public abstract class zzfgm<MessageType extends zzfjc> implements zzfjl<MessageType> {
    private static final zzfhm zzpns = zzfhm.zzczf();

    public final MessageType zzc(zzfhb zzfhb, zzfhm zzfhm) throws zzfie {
        MessageType message = (MessageType) zze(zzfhb, zzfhm);
        if (message == null || message.isInitialized()) {
            return message;
        }
        throw (!(message instanceof zzfgj) ? message instanceof zzfgl ? new zzfkm((zzfgl) message) : new zzfkm(message) : new zzfkm((zzfgj) message)).zzdbz().zzi(message);
    }
}
