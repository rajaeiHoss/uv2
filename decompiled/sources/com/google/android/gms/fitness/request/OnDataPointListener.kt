package com.google.android.gms.fitness.request

import com.google.android.gms.fitness.data.DataPoint

interface OnDataPointListener {
    fun onDataPoint(dataPoint: DataPoint)
}
