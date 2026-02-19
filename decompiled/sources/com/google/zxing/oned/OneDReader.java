package com.google.zxing.oned;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;

public abstract class OneDReader implements Reader {
    protected static final int INTEGER_MATH_SHIFT = 8;
    protected static final int PATTERN_MATCH_RESULT_SCALE_FACTOR = 256;

    /* JADX WARNING: Removed duplicated region for block: B:46:0x0092 A[Catch:{ ReaderException -> 0x00e1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x00e0 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.zxing.Result doDecode(com.google.zxing.BinaryBitmap r21, java.util.Hashtable r22) throws com.google.zxing.NotFoundException {
        /*
            r20 = this;
            r0 = r22
            int r1 = r21.getWidth()
            int r2 = r21.getHeight()
            com.google.zxing.common.BitArray r3 = new com.google.zxing.common.BitArray
            r3.<init>(r1)
            int r4 = r2 >> 1
            r6 = 1
            if (r0 == 0) goto L_0x001e
            com.google.zxing.DecodeHintType r7 = com.google.zxing.DecodeHintType.TRY_HARDER
            boolean r7 = r0.containsKey(r7)
            if (r7 == 0) goto L_0x001e
            r7 = 1
            goto L_0x001f
        L_0x001e:
            r7 = 0
        L_0x001f:
            if (r7 == 0) goto L_0x0024
            r8 = 8
            goto L_0x0025
        L_0x0024:
            r8 = 5
        L_0x0025:
            int r8 = r2 >> r8
            int r8 = java.lang.Math.max(r6, r8)
            if (r7 == 0) goto L_0x002f
            r7 = r2
            goto L_0x0031
        L_0x002f:
            r7 = 15
        L_0x0031:
            r9 = 0
        L_0x0032:
            if (r9 >= r7) goto L_0x00fe
            int r10 = r9 + 1
            int r11 = r10 >> 1
            r9 = r9 & 1
            if (r9 != 0) goto L_0x003e
            r9 = 1
            goto L_0x003f
        L_0x003e:
            r9 = 0
        L_0x003f:
            if (r9 == 0) goto L_0x0042
            goto L_0x0043
        L_0x0042:
            int r11 = -r11
        L_0x0043:
            int r11 = r11 * r8
            int r11 = r11 + r4
            if (r11 < 0) goto L_0x00fe
            if (r11 >= r2) goto L_0x00fe
            r9 = r21
            com.google.zxing.common.BitArray r3 = r9.getBlackRow(r11, r3)     // Catch:{ NotFoundException -> 0x00f1 }
            r12 = 0
        L_0x0051:
            r13 = 2
            if (r12 >= r13) goto L_0x00f1
            if (r12 != r6) goto L_0x008a
            r3.reverse()
            if (r0 == 0) goto L_0x008a
            com.google.zxing.DecodeHintType r13 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK
            boolean r13 = r0.containsKey(r13)
            if (r13 == 0) goto L_0x008a
            java.util.Hashtable r13 = new java.util.Hashtable
            r13.<init>()
            java.util.Enumeration r14 = r0.keys()
        L_0x006c:
            boolean r15 = r14.hasMoreElements()
            if (r15 == 0) goto L_0x0086
            java.lang.Object r15 = r14.nextElement()
            com.google.zxing.DecodeHintType r5 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK
            boolean r5 = r15.equals(r5)
            if (r5 != 0) goto L_0x006c
            java.lang.Object r5 = r0.get(r15)
            r13.put(r15, r5)
            goto L_0x006c
        L_0x0086:
            r5 = r20
            r0 = r13
            goto L_0x008c
        L_0x008a:
            r5 = r20
        L_0x008c:
            com.google.zxing.Result r13 = r5.decodeRow(r11, r3, r0)     // Catch:{ ReaderException -> 0x00e1 }
            if (r12 != r6) goto L_0x00e0
            com.google.zxing.ResultMetadataType r14 = com.google.zxing.ResultMetadataType.ORIENTATION     // Catch:{ ReaderException -> 0x00e1 }
            java.lang.Integer r15 = new java.lang.Integer     // Catch:{ ReaderException -> 0x00e1 }
            r6 = 180(0xb4, float:2.52E-43)
            r15.<init>(r6)     // Catch:{ ReaderException -> 0x00e1 }
            r13.putMetadata(r14, r15)     // Catch:{ ReaderException -> 0x00e1 }
            com.google.zxing.ResultPoint[] r6 = r13.getResultPoints()     // Catch:{ ReaderException -> 0x00e1 }
            com.google.zxing.ResultPoint r14 = new com.google.zxing.ResultPoint     // Catch:{ ReaderException -> 0x00e1 }
            float r15 = (float) r1
            r16 = 0
            r17 = r6[r16]     // Catch:{ ReaderException -> 0x00da }
            float r17 = r17.getX()     // Catch:{ ReaderException -> 0x00da }
            float r17 = r15 - r17
            r18 = 1065353216(0x3f800000, float:1.0)
            r22 = r0
            float r0 = r17 - r18
            r17 = r6[r16]     // Catch:{ ReaderException -> 0x00dc }
            r19 = r1
            float r1 = r17.getY()     // Catch:{ ReaderException -> 0x00de }
            r14.<init>(r0, r1)     // Catch:{ ReaderException -> 0x00de }
            r6[r16] = r14     // Catch:{ ReaderException -> 0x00de }
            com.google.zxing.ResultPoint r0 = new com.google.zxing.ResultPoint     // Catch:{ ReaderException -> 0x00de }
            r1 = 1
            r14 = r6[r1]     // Catch:{ ReaderException -> 0x00e8 }
            float r14 = r14.getX()     // Catch:{ ReaderException -> 0x00e8 }
            float r15 = r15 - r14
            float r15 = r15 - r18
            r14 = r6[r1]     // Catch:{ ReaderException -> 0x00e8 }
            float r14 = r14.getY()     // Catch:{ ReaderException -> 0x00e8 }
            r0.<init>(r15, r14)     // Catch:{ ReaderException -> 0x00e8 }
            r6[r1] = r0     // Catch:{ ReaderException -> 0x00e8 }
            goto L_0x00e0
        L_0x00da:
            r22 = r0
        L_0x00dc:
            r19 = r1
        L_0x00de:
            r1 = 1
            goto L_0x00e8
        L_0x00e0:
            return r13
        L_0x00e1:
            r22 = r0
            r19 = r1
            r1 = 1
            r16 = 0
        L_0x00e8:
            int r12 = r12 + 1
            r0 = r22
            r1 = r19
            r6 = 1
            goto L_0x0051
        L_0x00f1:
            r5 = r20
            r19 = r1
            r1 = 1
            r16 = 0
            r9 = r10
            r1 = r19
            r6 = 1
            goto L_0x0032
        L_0x00fe:
            r5 = r20
            com.google.zxing.NotFoundException r0 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.OneDReader.doDecode(com.google.zxing.BinaryBitmap, java.util.Hashtable):com.google.zxing.Result");
    }

    protected static int patternMatchVariance(int[] iArr, int[] iArr2, int i) {
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            i2 += iArr[i4];
            i3 += iArr2[i4];
        }
        if (i2 < i3) {
            return Integer.MAX_VALUE;
        }
        int i5 = (i2 << 8) / i3;
        int i6 = (i * i5) >> 8;
        int i7 = 0;
        for (int i8 = 0; i8 < length; i8++) {
            int i9 = iArr[i8] << 8;
            int i10 = iArr2[i8] * i5;
            int i11 = i9 > i10 ? i9 - i10 : i10 - i9;
            if (i11 > i6) {
                return Integer.MAX_VALUE;
            }
            i7 += i11;
        }
        return i7 / i2;
    }

    protected static void recordPattern(BitArray bitArray, int i, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = 0;
        }
        int size = bitArray.getSize();
        if (i < size) {
            boolean z = !bitArray.get(i);
            while (i < size) {
                if (bitArray.get(i) ^ z) {
                    iArr[i2] = iArr[i2] + 1;
                } else {
                    i2++;
                    if (i2 == length) {
                        break;
                    }
                    iArr[i2] = 1;
                    z = !z;
                }
                i++;
            }
            if (i2 == length) {
                return;
            }
            if (i2 != length - 1 || i != size) {
                throw NotFoundException.getNotFoundInstance();
            }
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    protected static void recordPatternInReverse(BitArray bitArray, int i, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        boolean z = bitArray.get(i);
        while (i > 0 && length >= 0) {
            i--;
            if (bitArray.get(i) != z) {
                length--;
                z = !z;
            }
        }
        if (length < 0) {
            recordPattern(bitArray, i + 1, iArr);
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, FormatException {
        return decode(binaryBitmap, (Hashtable) null);
    }

    public Result decode(BinaryBitmap binaryBitmap, Hashtable hashtable) throws NotFoundException, FormatException {
        try {
            return doDecode(binaryBitmap, hashtable);
        } catch (NotFoundException e) {
            if (!(hashtable != null && hashtable.containsKey(DecodeHintType.TRY_HARDER)) || !binaryBitmap.isRotateSupported()) {
                throw e;
            }
            BinaryBitmap rotateCounterClockwise = binaryBitmap.rotateCounterClockwise();
            Result doDecode = doDecode(rotateCounterClockwise, hashtable);
            Hashtable resultMetadata = doDecode.getResultMetadata();
            int i = 270;
            if (resultMetadata != null && resultMetadata.containsKey(ResultMetadataType.ORIENTATION)) {
                i = (((Integer) resultMetadata.get(ResultMetadataType.ORIENTATION)).intValue() + 270) % 360;
            }
            doDecode.putMetadata(ResultMetadataType.ORIENTATION, new Integer(i));
            ResultPoint[] resultPoints = doDecode.getResultPoints();
            int height = rotateCounterClockwise.getHeight();
            for (int i2 = 0; i2 < resultPoints.length; i2++) {
                resultPoints[i2] = new ResultPoint((((float) height) - resultPoints[i2].getY()) - 1.0f, resultPoints[i2].getX());
            }
            return doDecode;
        }
    }

    public abstract Result decodeRow(int i, BitArray bitArray, Hashtable hashtable) throws NotFoundException, ChecksumException, FormatException;

    public void reset() {
    }
}
