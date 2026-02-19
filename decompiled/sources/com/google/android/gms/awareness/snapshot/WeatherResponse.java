package com.google.android.gms.awareness.snapshot;

import com.google.android.gms.awareness.state.Weather;
import com.google.android.gms.common.api.Response;

public class WeatherResponse extends Response<WeatherResult> {
    public Weather getWeather() {
        return ((WeatherResult) getResult()).getWeather();
    }
}
