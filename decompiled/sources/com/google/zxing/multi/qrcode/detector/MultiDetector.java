package com.google.zxing.multi.qrcode.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.qrcode.detector.Detector;
import com.google.zxing.qrcode.detector.FinderPatternInfo;
import java.util.Hashtable;
import java.util.Vector;

public final class MultiDetector extends Detector {
    private static final DetectorResult[] EMPTY_DETECTOR_RESULTS = new DetectorResult[0];

    public MultiDetector(BitMatrix bitMatrix) {
        super(bitMatrix);
    }

    public DetectorResult[] detectMulti(Hashtable hashtable) throws NotFoundException {
        FinderPatternInfo[] findMulti = new MultiFinderPatternFinder(getImage()).findMulti(hashtable);
        if (findMulti == null || findMulti.length == 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        Vector vector = new Vector();
        for (int i = 0; i < findMulti.length; i++) {
            try {
                vector.addElement(processFinderPatternInfo(findMulti[i]));
            } catch (ReaderException unused) {
            }
        }
        if (vector.isEmpty()) {
            return EMPTY_DETECTOR_RESULTS;
        }
        DetectorResult[] detectorResultArr = new DetectorResult[vector.size()];
        for (int i2 = 0; i2 < vector.size(); i2++) {
            detectorResultArr[i2] = (DetectorResult) vector.elementAt(i2);
        }
        return detectorResultArr;
    }
}
