package com.google.android.gms.awareness.snapshot

import com.google.android.gms.common.api.Result
import com.google.android.gms.location.places.PlaceLikelihood

interface PlacesResult : Result {
    val placeLikelihoods: List<PlaceLikelihood>
}
