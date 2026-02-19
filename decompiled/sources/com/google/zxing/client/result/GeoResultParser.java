package com.google.zxing.client.result;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.zxing.Result;

final class GeoResultParser extends ResultParser {
    private GeoResultParser() {
    }

    public static GeoParsedResult parse(Result result) {
        String str;
        String str2;
        double d;
        double d2;
        String text = result.getText();
        if (text != null && (text.startsWith("geo:") || text.startsWith("GEO:"))) {
            int indexOf = text.indexOf(63, 4);
            if (indexOf < 0) {
                str = text.substring(4);
                str2 = null;
            } else {
                String substring = text.substring(indexOf + 1);
                str = text.substring(4, indexOf);
                str2 = substring;
            }
            int indexOf2 = str.indexOf(44);
            if (indexOf2 < 0) {
                return null;
            }
            int i = indexOf2 + 1;
            int indexOf3 = str.indexOf(44, i);
            try {
                double parseDouble = Double.parseDouble(str.substring(0, indexOf2));
                if (parseDouble <= 90.0d) {
                    if (parseDouble >= -90.0d) {
                        if (indexOf3 < 0) {
                            d2 = Double.parseDouble(str.substring(i));
                            d = 0.0d;
                        } else {
                            double parseDouble2 = Double.parseDouble(str.substring(i, indexOf3));
                            d = Double.parseDouble(str.substring(indexOf3 + 1));
                            d2 = parseDouble2;
                        }
                        if (d2 <= 180.0d && d2 >= -180.0d && d >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                            return new GeoParsedResult(parseDouble, d2, d, str2);
                        }
                    }
                }
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }
}
