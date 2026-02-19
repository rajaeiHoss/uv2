package com.google.zxing.common;

import com.google.zxing.NotFoundException;

public final class DefaultGridSampler extends GridSampler {
    public BitMatrix sampleGrid(BitMatrix bitMatrix, int i, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) throws NotFoundException {
        BitMatrix bitMatrix2 = bitMatrix;
        int i2 = i;
        return sampleGrid(bitMatrix, i, PerspectiveTransform.quadrilateralToQuadrilateral(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16));
    }

    public BitMatrix sampleGrid(BitMatrix bitMatrix, int i, PerspectiveTransform perspectiveTransform) throws NotFoundException {
        BitMatrix bitMatrix2 = new BitMatrix(i);
        int i2 = i << 1;
        float[] fArr = new float[i2];
        for (int i3 = 0; i3 < i; i3++) {
            float f = ((float) i3) + 0.5f;
            for (int i4 = 0; i4 < i2; i4 += 2) {
                fArr[i4] = ((float) (i4 >> 1)) + 0.5f;
                fArr[i4 + 1] = f;
            }
            perspectiveTransform.transformPoints(fArr);
            checkAndNudgePoints(bitMatrix, fArr);
            int i5 = 0;
            while (i5 < i2) {
                try {
                    if (bitMatrix.get((int) fArr[i5], (int) fArr[i5 + 1])) {
                        bitMatrix2.set(i5 >> 1, i3);
                    }
                    i5 += 2;
                } catch (ArrayIndexOutOfBoundsException unused) {
                    throw NotFoundException.getNotFoundInstance();
                }
            }
        }
        return bitMatrix2;
    }
}
