package com.google.android.gms.fitness.service

import android.os.RemoteException
import com.google.android.gms.fitness.data.DataPoint

interface SensorEventDispatcher {
    @Throws(RemoteException::class)
    fun publish(dataPoint: DataPoint)

    @Throws(RemoteException::class)
    fun publish(dataPoints: List<DataPoint>)
}
