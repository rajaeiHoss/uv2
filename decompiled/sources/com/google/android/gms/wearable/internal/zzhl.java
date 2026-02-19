package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.zzcl;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEventBuffer;

final class zzhl implements zzcl<DataApi.DataListener> {
    private /* synthetic */ DataHolder zzlrq;

    zzhl(DataHolder dataHolder) {
        this.zzlrq = dataHolder;
    }

    public final void zzajh() {
        this.zzlrq.close();
    }

    public final void zzu(DataApi.DataListener dataListener) {
        try {
            dataListener.onDataChanged(new DataEventBuffer(this.zzlrq));
        } finally {
            this.zzlrq.close();
        }
    }
}
