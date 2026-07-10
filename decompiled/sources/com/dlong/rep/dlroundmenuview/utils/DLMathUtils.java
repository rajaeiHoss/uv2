package com.dlong.rep.dlroundmenuview.utils;

public class DLMathUtils {
    public static double getDistanceFromTwoSpot(float f, float f2, float f3, float f4) {
        float f5 = f > f3 ? f - f3 : f3 - f;
        int i = (f2 > f4 ? 1 : (f2 == f4 ? 0 : -1));
        float f6 = f4 - f2;
        return Math.sqrt((double) ((f5 * f5) + (f6 * f6)));
    }

    public static double getRotationBetweenLines(float centerX, float centerY, float touchX, float touchY) {
        double degrees = Math.toDegrees(Math.atan2((double) (touchX - centerX), (double) (centerY - touchY)));
        return degrees < 0.0d ? degrees + 360.0d : degrees;
    }
}
