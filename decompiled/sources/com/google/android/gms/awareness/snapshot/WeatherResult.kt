package com.google.android.gms.awareness.snapshot

import com.google.android.gms.awareness.state.Weather
import com.google.android.gms.common.api.Result

interface WeatherResult : Result {
    val weather: Weather
}
