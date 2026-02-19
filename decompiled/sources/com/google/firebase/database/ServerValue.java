package com.google.firebase.database;

import com.google.android.gms.measurement.AppMeasurement;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ServerValue {
    public static final Map<String, String> TIMESTAMP;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(".sv", AppMeasurement.Param.TIMESTAMP);
        TIMESTAMP = Collections.unmodifiableMap(hashMap);
    }
}
