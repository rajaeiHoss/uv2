package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

public final class MonochromeRectangleDetector {
    private static final int MAX_MODULES = 32;
    private final BitMatrix image;

    public MonochromeRectangleDetector(BitMatrix bitMatrix) {
        this.image = bitMatrix;
    }

    private int[] blackWhiteRange(int i, int i2, int i3, int i4, boolean z) {
        int i5 = (i3 + i4) >> 1;
        int i6 = i5;
        while (i6 >= i3) {
            BitMatrix bitMatrix = this.image;
            if (!z ? !bitMatrix.get(i, i6) : !bitMatrix.get(i6, i)) {
                int i7 = i6;
                while (true) {
                    i7--;
                    if (i7 < i3) {
                        break;
                    }
                    BitMatrix bitMatrix2 = this.image;
                    if (z) {
                        if (bitMatrix2.get(i7, i)) {
                            break;
                        }
                    } else if (bitMatrix2.get(i, i7)) {
                        break;
                    }
                }
                int i8 = i6 - i7;
                if (i7 < i3 || i8 > i2) {
                    break;
                }
                i6 = i7;
            } else {
                i6--;
            }
        }
        int i9 = i6 + 1;
        while (i5 < i4) {
            BitMatrix bitMatrix3 = this.image;
            if (!z ? !bitMatrix3.get(i, i5) : !bitMatrix3.get(i5, i)) {
                int i10 = i5;
                while (true) {
                    i10++;
                    if (i10 >= i4) {
                        break;
                    }
                    BitMatrix bitMatrix4 = this.image;
                    if (z) {
                        if (bitMatrix4.get(i10, i)) {
                            break;
                        }
                    } else if (bitMatrix4.get(i, i10)) {
                        break;
                    }
                }
                int i11 = i10 - i5;
                if (i10 >= i4 || i11 > i2) {
                    break;
                }
                i5 = i10;
            } else {
                i5++;
            }
        }
        int i12 = i5 - 1;
        if (i12 <= i9) {
            return null;
        }
        return new int[]{i9, i12};
    }

    private ResultPoint findCornerFromCenter(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) throws NotFoundException {
        boolean z;
        int i10;
        int i11;
        int i12;
        int i13;
        MonochromeRectangleDetector monochromeRectangleDetector;
        int i14 = i;
        int i15 = i5;
        int[] iArr = null;
        int i16 = i8;
        int i17 = i14;
        int i18 = i15;
        while (i18 < i16 && i18 >= i7 && i17 < i4 && i17 >= i3) {
            if (i2 == 0) {
                z = true;
                monochromeRectangleDetector = this;
                i13 = i18;
                i12 = i9;
                i11 = i3;
                i10 = i4;
            } else {
                z = false;
                monochromeRectangleDetector = this;
                i13 = i17;
                i12 = i9;
                i11 = i7;
                i10 = i8;
            }
            int[] blackWhiteRange = monochromeRectangleDetector.blackWhiteRange(i13, i12, i11, i10, z);
            if (blackWhiteRange != null) {
                i18 += i6;
                i17 += i2;
                iArr = blackWhiteRange;
            } else if (iArr == null) {
                throw NotFoundException.getNotFoundInstance();
            } else if (i2 == 0) {
                int i19 = i18 - i6;
                if (iArr[0] >= i14) {
                    return new ResultPoint((float) iArr[1], (float) i19);
                }
                if (iArr[1] <= i14) {
                    return new ResultPoint((float) iArr[0], (float) i19);
                }
                return new ResultPoint((float) (i6 > 0 ? iArr[0] : iArr[1]), (float) i19);
            } else {
                int i20 = i17 - i2;
                if (iArr[0] >= i15) {
                    return new ResultPoint((float) i20, (float) iArr[1]);
                }
                if (iArr[1] <= i15) {
                    return new ResultPoint((float) i20, (float) iArr[0]);
                }
                return new ResultPoint((float) i20, (float) (i2 < 0 ? iArr[0] : iArr[1]));
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public ResultPoint[] detect() throws NotFoundException {
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int i = height >> 1;
        int i2 = width >> 1;
        int max = Math.max(1, height / 256);
        int i3 = -max;
        int i4 = i2 >> 1;
        int i5 = i2;
        int i6 = width;
        int i7 = i;
        int i8 = i3;
        int i9 = height;
        int i10 = max;
        int max2 = Math.max(1, width / 256);
        int i11 = -max2;
        int y = ((int) findCornerFromCenter(i5, 0, 0, i6, i7, i3, 0, i9, i4).getY()) - 1;
        int i12 = max2;
        int i13 = i >> 1;
        ResultPoint findCornerFromCenter = findCornerFromCenter(i5, i11, 0, i6, i7, 0, y, i9, i13);
        int x = ((int) findCornerFromCenter.getX()) - 1;
        ResultPoint findCornerFromCenter2 = findCornerFromCenter(i5, i12, x, i6, i7, 0, y, i9, i13);
        int x2 = ((int) findCornerFromCenter2.getX()) + 1;
        ResultPoint findCornerFromCenter3 = findCornerFromCenter(i5, 0, x, x2, i7, i10, y, i9, i4);
        return new ResultPoint[]{findCornerFromCenter(i5, 0, x, x2, i7, i8, y, ((int) findCornerFromCenter3.getY()) + 1, i2 >> 2), findCornerFromCenter, findCornerFromCenter2, findCornerFromCenter3};
    }
}
