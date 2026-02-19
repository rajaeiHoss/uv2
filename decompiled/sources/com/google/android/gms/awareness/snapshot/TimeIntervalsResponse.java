package com.google.android.gms.awareness.snapshot;

import com.google.android.gms.awareness.state.TimeIntervals;
import com.google.android.gms.common.api.Response;

public class TimeIntervalsResponse extends Response<TimeIntervalsResult> {
    public TimeIntervals getTimeIntervals() {
        return ((TimeIntervalsResult) getResult()).getTimeIntervals();
    }
}
