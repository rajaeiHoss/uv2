package com.dlong.rep.dlroundmenuview.utils;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public class DLMathUtils {
    public static double getDistanceFromTwoSpot(float f, float f2, float f3, float f4) {
        float f5 = f > f3 ? f - f3 : f3 - f;
        int i = (f2 > f4 ? 1 : (f2 == f4 ? 0 : -1));
        float f6 = f4 - f2;
        return Math.sqrt((double) ((f5 * f5) + (f6 * f6)));
    }

    public static double getRotationBetweenLines(float f, float f2, float f3, float f4) {
        double d = ((double) (f2 - f2)) / ((double) ((2.0f * f) - f));
        double d2 = ((double) (f4 - f2)) / ((double) (f3 - f));
        double atan = (Math.atan(Math.abs(d - d2) / ((d * d2) + 1.0d)) / 3.141592653589793d) * 180.0d;
        double d3 = 90.0d;
        int i = (f3 > f ? 1 : (f3 == f ? 0 : -1));
        if (i <= 0 || f4 >= f2) {
            if (i <= 0 || f4 <= f2) {
                d3 = 270.0d;
                int i2 = (f3 > f ? 1 : (f3 == f ? 0 : -1));
                if (i2 >= 0 || f4 <= f2) {
                    if (i2 >= 0 || f4 >= f2) {
                        if ((i != 0 || f4 >= f2) && i == 0 && f4 > f2) {
                            return 180.0d;
                        }
                        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                    }
                }
            }
            return atan + d3;
        }
        return d3 - atan;
    }
}
