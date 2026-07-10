package com.google.android.gms.location

import android.location.Location

interface LocationListener {
    fun onLocationChanged(location: Location)
}
