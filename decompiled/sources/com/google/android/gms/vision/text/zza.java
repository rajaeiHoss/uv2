package com.google.android.gms.vision.text;

import java.util.Comparator;
import java.util.Map;

final class zza implements Comparator<Map.Entry<String, Integer>> {
    zza(TextBlock textBlock) {
    }

    public final int compare(Map.Entry<String, Integer> entry, Map.Entry<String, Integer> entry2) {
        return entry.getValue().compareTo(entry2.getValue());
    }
}
