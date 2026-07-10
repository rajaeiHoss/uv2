package com.google.android.gms.maps

import android.location.Location

interface LocationSource {
    interface OnLocationChangedListener {
        fun onLocationChanged(location: Location)
    }

    fun activate(listener: OnLocationChangedListener)

    fun deactivate()
}
