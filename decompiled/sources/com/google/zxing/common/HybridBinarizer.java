package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import java.lang.reflect.Array;
import kotlin.UByte;

public final class HybridBinarizer extends GlobalHistogramBinarizer {
    private static final int MINIMUM_DIMENSION = 40;
    private BitMatrix matrix = null;

    public HybridBinarizer(LuminanceSource luminanceSource) {
        super(luminanceSource);
    }

    private void binarizeEntireImage() throws NotFoundException {
        if (this.matrix == null) {
            LuminanceSource luminanceSource = getLuminanceSource();
            if (luminanceSource.getWidth() < 40 || luminanceSource.getHeight() < 40) {
                this.matrix = super.getBlackMatrix();
                return;
            }
            byte[] matrix2 = luminanceSource.getMatrix();
            int width = luminanceSource.getWidth();
            int height = luminanceSource.getHeight();
            int i = width >> 3;
            if ((width & 7) != 0) {
                i++;
            }
            int i2 = i;
            int i3 = height >> 3;
            if ((height & 7) != 0) {
                i3++;
            }
            int i4 = i3;
            int[][] calculateBlackPoints = calculateBlackPoints(matrix2, i2, i4, width, height);
            BitMatrix bitMatrix = new BitMatrix(width, height);
            this.matrix = bitMatrix;
            calculateThresholdForBlock(matrix2, i2, i4, width, height, calculateBlackPoints, bitMatrix);
        }
    }

    private static int[][] calculateBlackPoints(byte[] bArr, int i, int i2, int i3, int i4) {
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        int[] iArr = new int[2];
        iArr[1] = i5;
        int i9 = 0;
        iArr[0] = i6;
        int[][] iArr2 = (int[][]) Array.newInstance(int.class, iArr);
        int i10 = 0;
        while (i10 < i6) {
            int i11 = i10 << 3;
            if (i11 + 8 >= i8) {
                i11 = i8 - 8;
            }
            int i12 = 0;
            while (i12 < i5) {
                int i13 = i12 << 3;
                if (i13 + 8 >= i7) {
                    i13 = i7 - 8;
                }
                int i14 = 0;
                int b = 0;
                int b2 = UByte.MAX_VALUE;
                int i15 = 0;
                while (true) {
                    if (i14 >= 8) {
                        break;
                    }
                    int i16 = ((i11 + i14) * i7) + i13;
                    for (int i17 = 8; i9 < i17; i17 = 8) {
                        int b3 = bArr[i16 + i9] & UByte.MAX_VALUE;
                        i15 += b3;
                        if (b3 < b2) {
                            b2 = b3;
                        }
                        if (b3 > b) {
                            b = b3;
                        }
                        i9++;
                    }
                    i14++;
                    i9 = 0;
                }
                iArr2[i10][i12] = b - b2 > 24 ? i15 >> 6 : b == 0 ? 1 : b2 >> 1;
                i12++;
                i9 = 0;
            }
            i10++;
            i9 = 0;
        }
        return iArr2;
    }

    private static void calculateThresholdForBlock(byte[] bArr, int i, int i2, int i3, int i4, int[][] iArr, BitMatrix bitMatrix) {
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        int i9 = 0;
        while (i9 < i6) {
            int i10 = i9 << 3;
            if (i10 + 8 >= i8) {
                i10 = i8 - 8;
            }
            int i11 = i10;
            int i12 = 0;
            while (i12 < i5) {
                int i13 = i12 << 3;
                if (i13 + 8 >= i7) {
                    i13 = i7 - 8;
                }
                int i14 = i13;
                int i15 = i12 > 1 ? i12 : 2;
                if (i15 >= i5 - 2) {
                    i15 = i5 - 3;
                }
                int i16 = i9 > 1 ? i9 : 2;
                if (i16 >= i6 - 2) {
                    i16 = i6 - 3;
                }
                int i17 = 0;
                for (int i18 = -2; i18 <= 2; i18++) {
                    int[] iArr2 = iArr[i16 + i18];
                    i17 = i17 + iArr2[i15 - 2] + iArr2[i15 - 1] + iArr2[i15] + iArr2[i15 + 1] + iArr2[i15 + 2];
                }
                threshold8x8Block(bArr, i14, i11, i17 / 25, i3, bitMatrix);
                i12++;
            }
            i9++;
        }
    }

    private static void threshold8x8Block(byte[] bArr, int i, int i2, int i3, int i4, BitMatrix bitMatrix) {
        for (int i5 = 0; i5 < 8; i5++) {
            int i6 = i2 + i5;
            int i7 = (i6 * i4) + i;
            for (int i8 = 0; i8 < 8; i8++) {
                if ((bArr[i7 + i8] & UByte.MAX_VALUE) < i3) {
                    bitMatrix.set(i + i8, i6);
                }
            }
        }
    }

    public Binarizer createBinarizer(LuminanceSource luminanceSource) {
        return new HybridBinarizer(luminanceSource);
    }

    public BitMatrix getBlackMatrix() throws NotFoundException {
        binarizeEntireImage();
        return this.matrix;
    }
}
