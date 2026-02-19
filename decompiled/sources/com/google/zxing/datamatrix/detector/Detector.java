package com.google.zxing.datamatrix.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.Collections;
import com.google.zxing.common.Comparator;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.detector.WhiteRectangleDetector;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public final class Detector {
    private static final Integer[] INTEGERS = {new Integer(0), new Integer(1), new Integer(2), new Integer(3), new Integer(4)};
    private final BitMatrix image;
    private final WhiteRectangleDetector rectangleDetector;

    private static class ResultPointsAndTransitions {
        private final ResultPoint from;
        private final ResultPoint to;
        private final int transitions;

        private ResultPointsAndTransitions(ResultPoint resultPoint, ResultPoint resultPoint2, int i) {
            this.from = resultPoint;
            this.to = resultPoint2;
            this.transitions = i;
        }

        public ResultPoint getFrom() {
            return this.from;
        }

        public ResultPoint getTo() {
            return this.to;
        }

        public int getTransitions() {
            return this.transitions;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.from);
            stringBuffer.append("/");
            stringBuffer.append(this.to);
            stringBuffer.append('/');
            stringBuffer.append(this.transitions);
            return stringBuffer.toString();
        }
    }

    private static class ResultPointsAndTransitionsComparator implements Comparator {
        private ResultPointsAndTransitionsComparator() {
        }

        public int compare(Object obj, Object obj2) {
            return ((ResultPointsAndTransitions) obj).getTransitions() - ((ResultPointsAndTransitions) obj2).getTransitions();
        }
    }

    public Detector(BitMatrix bitMatrix) {
        this.image = bitMatrix;
        this.rectangleDetector = new WhiteRectangleDetector(bitMatrix);
    }

    private ResultPoint correctTopRight(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i) {
        float f = (float) i;
        float distance = ((float) distance(resultPoint, resultPoint2)) / f;
        float distance2 = (float) distance(resultPoint3, resultPoint4);
        ResultPoint resultPoint5 = new ResultPoint(resultPoint4.getX() + (((resultPoint4.getX() - resultPoint3.getX()) / distance2) * distance), resultPoint4.getY() + (distance * ((resultPoint4.getY() - resultPoint3.getY()) / distance2)));
        float distance3 = ((float) distance(resultPoint, resultPoint2)) / f;
        float distance4 = (float) distance(resultPoint2, resultPoint4);
        ResultPoint resultPoint6 = new ResultPoint(resultPoint4.getX() + (((resultPoint4.getX() - resultPoint2.getX()) / distance4) * distance3), resultPoint4.getY() + (distance3 * ((resultPoint4.getY() - resultPoint2.getY()) / distance4)));
        if (isValid(resultPoint5)) {
            return (isValid(resultPoint6) && Math.abs(transitionsBetween(resultPoint3, resultPoint5).getTransitions() - transitionsBetween(resultPoint2, resultPoint5).getTransitions()) > Math.abs(transitionsBetween(resultPoint3, resultPoint6).getTransitions() - transitionsBetween(resultPoint2, resultPoint6).getTransitions())) ? resultPoint6 : resultPoint5;
        }
        if (isValid(resultPoint6)) {
            return resultPoint6;
        }
        return null;
    }

    private static int distance(ResultPoint resultPoint, ResultPoint resultPoint2) {
        return round((float) Math.sqrt((double) (((resultPoint.getX() - resultPoint2.getX()) * (resultPoint.getX() - resultPoint2.getX())) + ((resultPoint.getY() - resultPoint2.getY()) * (resultPoint.getY() - resultPoint2.getY())))));
    }

    private static void increment(Hashtable hashtable, ResultPoint resultPoint) {
        Integer num = (Integer) hashtable.get(resultPoint);
        hashtable.put(resultPoint, num == null ? INTEGERS[1] : INTEGERS[num.intValue() + 1]);
    }

    private boolean isValid(ResultPoint resultPoint) {
        return resultPoint.getX() >= 0.0f && resultPoint.getX() < ((float) this.image.width) && resultPoint.getY() > 0.0f && resultPoint.getY() < ((float) this.image.height);
    }

    private static int round(float f) {
        return (int) (f + 0.5f);
    }

    private static BitMatrix sampleGrid(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i) throws NotFoundException {
        float f = ((float) i) - 0.5f;
        return GridSampler.getInstance().sampleGrid(bitMatrix, i, 0.5f, 0.5f, f, 0.5f, f, f, 0.5f, f, resultPoint.getX(), resultPoint.getY(), resultPoint4.getX(), resultPoint4.getY(), resultPoint3.getX(), resultPoint3.getY(), resultPoint2.getX(), resultPoint2.getY());
    }

    private ResultPointsAndTransitions transitionsBetween(ResultPoint resultPoint, ResultPoint resultPoint2) {
        int x = (int) resultPoint.getX();
        int y = (int) resultPoint.getY();
        int x2 = (int) resultPoint2.getX();
        int y2 = (int) resultPoint2.getY();
        int i = 0;
        int i2 = 1;
        boolean z = Math.abs(y2 - y) > Math.abs(x2 - x);
        if (z) {
            int i3 = y;
            y = x;
            x = i3;
            int i4 = y2;
            y2 = x2;
            x2 = i4;
        }
        int abs = Math.abs(x2 - x);
        int abs2 = Math.abs(y2 - y);
        int i5 = (-abs) >> 1;
        int i6 = y < y2 ? 1 : -1;
        if (x >= x2) {
            i2 = -1;
        }
        boolean z2 = this.image.get(z ? y : x, z ? x : y);
        while (x != x2) {
            boolean z3 = this.image.get(z ? y : x, z ? x : y);
            if (z3 != z2) {
                i++;
                z2 = z3;
            }
            i5 += abs2;
            if (i5 > 0) {
                if (y == y2) {
                    break;
                }
                y += i6;
                i5 -= abs;
            }
            x += i2;
        }
        return new ResultPointsAndTransitions(resultPoint, resultPoint2, i);
    }

    public DetectorResult detect() throws NotFoundException {
        ResultPoint[] detect = this.rectangleDetector.detect();
        ResultPoint resultPoint = detect[0];
        ResultPoint resultPoint2 = detect[1];
        ResultPoint resultPoint3 = detect[2];
        ResultPoint resultPoint4 = detect[3];
        Vector vector = new Vector(4);
        vector.addElement(transitionsBetween(resultPoint, resultPoint2));
        vector.addElement(transitionsBetween(resultPoint, resultPoint3));
        vector.addElement(transitionsBetween(resultPoint2, resultPoint4));
        vector.addElement(transitionsBetween(resultPoint3, resultPoint4));
        ResultPoint resultPoint5 = null;
        Collections.insertionSort(vector, new ResultPointsAndTransitionsComparator());
        ResultPointsAndTransitions resultPointsAndTransitions = (ResultPointsAndTransitions) vector.elementAt(0);
        ResultPointsAndTransitions resultPointsAndTransitions2 = (ResultPointsAndTransitions) vector.elementAt(1);
        Hashtable hashtable = new Hashtable();
        increment(hashtable, resultPointsAndTransitions.getFrom());
        increment(hashtable, resultPointsAndTransitions.getTo());
        increment(hashtable, resultPointsAndTransitions2.getFrom());
        increment(hashtable, resultPointsAndTransitions2.getTo());
        Enumeration keys = hashtable.keys();
        ResultPoint resultPoint6 = null;
        ResultPoint resultPoint7 = null;
        while (keys.hasMoreElements()) {
            ResultPoint resultPoint8 = (ResultPoint) keys.nextElement();
            if (((Integer) hashtable.get(resultPoint8)).intValue() == 2) {
                resultPoint6 = resultPoint8;
            } else if (resultPoint5 == null) {
                resultPoint5 = resultPoint8;
            } else {
                resultPoint7 = resultPoint8;
            }
        }
        if (resultPoint5 == null || resultPoint6 == null || resultPoint7 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        ResultPoint[] resultPointArr = {resultPoint5, resultPoint6, resultPoint7};
        ResultPoint.orderBestPatterns(resultPointArr);
        ResultPoint resultPoint9 = resultPointArr[0];
        ResultPoint resultPoint10 = resultPointArr[1];
        ResultPoint resultPoint11 = resultPointArr[2];
        ResultPoint resultPoint12 = !hashtable.containsKey(resultPoint) ? resultPoint : !hashtable.containsKey(resultPoint2) ? resultPoint2 : !hashtable.containsKey(resultPoint3) ? resultPoint3 : resultPoint4;
        int min = Math.min(transitionsBetween(resultPoint11, resultPoint12).getTransitions(), transitionsBetween(resultPoint9, resultPoint12).getTransitions());
        if ((min & 1) == 1) {
            min++;
        }
        ResultPoint correctTopRight = correctTopRight(resultPoint10, resultPoint9, resultPoint11, resultPoint12, min + 2);
        if (correctTopRight != null) {
            resultPoint12 = correctTopRight;
        }
        int max = Math.max(transitionsBetween(resultPoint11, resultPoint12).getTransitions(), transitionsBetween(resultPoint9, resultPoint12).getTransitions()) + 1;
        if ((max & 1) == 1) {
            max++;
        }
        return new DetectorResult(sampleGrid(this.image, resultPoint11, resultPoint10, resultPoint9, resultPoint12, max), new ResultPoint[]{resultPoint11, resultPoint10, resultPoint9, resultPoint12});
    }
}
