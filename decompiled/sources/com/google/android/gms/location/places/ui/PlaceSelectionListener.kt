package com.google.android.gms.location.places.ui

import com.google.android.gms.common.api.Status
import com.google.android.gms.location.places.Place

interface PlaceSelectionListener {
    fun onError(status: Status)

    fun onPlaceSelected(place: Place)
}
