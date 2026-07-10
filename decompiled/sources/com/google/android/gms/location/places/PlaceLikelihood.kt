package com.google.android.gms.location.places

import com.google.android.gms.common.data.Freezable

interface PlaceLikelihood : Freezable<PlaceLikelihood> {
    val likelihood: Float

    val place: Place
}
