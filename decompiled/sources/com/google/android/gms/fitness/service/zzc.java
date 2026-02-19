package com.google.android.gms.fitness.service;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.zzt;
import java.util.List;

final class zzc implements SensorEventDispatcher {
    private final zzt zzhoz;

    zzc(zzt zzt) {
        this.zzhoz = (zzt) zzbq.checkNotNull(zzt);
    }

    public final void publish(DataPoint dataPoint) throws RemoteException {
        dataPoint.zzars();
        this.zzhoz.zzc(dataPoint);
    }

    public final void publish(List<DataPoint> list) throws RemoteException {
        for (DataPoint publish : list) {
            publish(publish);
        }
    }
}
