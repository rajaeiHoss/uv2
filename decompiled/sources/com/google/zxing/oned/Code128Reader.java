package com.google.zxing.oned;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class Code128Reader extends OneDReader {
    private static final int CODE_CODE_A = 101;
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_A = 101;
    private static final int CODE_FNC_4_B = 100;
    static final int[][] CODE_PATTERNS = {new int[]{2, 1, 2, 2, 2, 2}, new int[]{2, 2, 2, 1, 2, 2}, new int[]{2, 2, 2, 2, 2, 1}, new int[]{1, 2, 1, 2, 2, 3}, new int[]{1, 2, 1, 3, 2, 2}, new int[]{1, 3, 1, 2, 2, 2}, new int[]{1, 2, 2, 2, 1, 3}, new int[]{1, 2, 2, 3, 1, 2}, new int[]{1, 3, 2, 2, 1, 2}, new int[]{2, 2, 1, 2, 1, 3}, new int[]{2, 2, 1, 3, 1, 2}, new int[]{2, 3, 1, 2, 1, 2}, new int[]{1, 1, 2, 2, 3, 2}, new int[]{1, 2, 2, 1, 3, 2}, new int[]{1, 2, 2, 2, 3, 1}, new int[]{1, 1, 3, 2, 2, 2}, new int[]{1, 2, 3, 1, 2, 2}, new int[]{1, 2, 3, 2, 2, 1}, new int[]{2, 2, 3, 2, 1, 1}, new int[]{2, 2, 1, 1, 3, 2}, new int[]{2, 2, 1, 2, 3, 1}, new int[]{2, 1, 3, 2, 1, 2}, new int[]{2, 2, 3, 1, 1, 2}, new int[]{3, 1, 2, 1, 3, 1}, new int[]{3, 1, 1, 2, 2, 2}, new int[]{3, 2, 1, 1, 2, 2}, new int[]{3, 2, 1, 2, 2, 1}, new int[]{3, 1, 2, 2, 1, 2}, new int[]{3, 2, 2, 1, 1, 2}, new int[]{3, 2, 2, 2, 1, 1}, new int[]{2, 1, 2, 1, 2, 3}, new int[]{2, 1, 2, 3, 2, 1}, new int[]{2, 3, 2, 1, 2, 1}, new int[]{1, 1, 1, 3, 2, 3}, new int[]{1, 3, 1, 1, 2, 3}, new int[]{1, 3, 1, 3, 2, 1}, new int[]{1, 1, 2, 3, 1, 3}, new int[]{1, 3, 2, 1, 1, 3}, new int[]{1, 3, 2, 3, 1, 1}, new int[]{2, 1, 1, 3, 1, 3}, new int[]{2, 3, 1, 1, 1, 3}, new int[]{2, 3, 1, 3, 1, 1}, new int[]{1, 1, 2, 1, 3, 3}, new int[]{1, 1, 2, 3, 3, 1}, new int[]{1, 3, 2, 1, 3, 1}, new int[]{1, 1, 3, 1, 2, 3}, new int[]{1, 1, 3, 3, 2, 1}, new int[]{1, 3, 3, 1, 2, 1}, new int[]{3, 1, 3, 1, 2, 1}, new int[]{2, 1, 1, 3, 3, 1}, new int[]{2, 3, 1, 1, 3, 1}, new int[]{2, 1, 3, 1, 1, 3}, new int[]{2, 1, 3, 3, 1, 1}, new int[]{2, 1, 3, 1, 3, 1}, new int[]{3, 1, 1, 1, 2, 3}, new int[]{3, 1, 1, 3, 2, 1}, new int[]{3, 3, 1, 1, 2, 1}, new int[]{3, 1, 2, 1, 1, 3}, new int[]{3, 1, 2, 3, 1, 1}, new int[]{3, 3, 2, 1, 1, 1}, new int[]{3, 1, 4, 1, 1, 1}, new int[]{2, 2, 1, 4, 1, 1}, new int[]{4, 3, 1, 1, 1, 1}, new int[]{1, 1, 1, 2, 2, 4}, new int[]{1, 1, 1, 4, 2, 2}, new int[]{1, 2, 1, 1, 2, 4}, new int[]{1, 2, 1, 4, 2, 1}, new int[]{1, 4, 1, 1, 2, 2}, new int[]{1, 4, 1, 2, 2, 1}, new int[]{1, 1, 2, 2, 1, 4}, new int[]{1, 1, 2, 4, 1, 2}, new int[]{1, 2, 2, 1, 1, 4}, new int[]{1, 2, 2, 4, 1, 1}, new int[]{1, 4, 2, 1, 1, 2}, new int[]{1, 4, 2, 2, 1, 1}, new int[]{2, 4, 1, 2, 1, 1}, new int[]{2, 2, 1, 1, 1, 4}, new int[]{4, 1, 3, 1, 1, 1}, new int[]{2, 4, 1, 1, 1, 2}, new int[]{1, 3, 4, 1, 1, 1}, new int[]{1, 1, 1, 2, 4, 2}, new int[]{1, 2, 1, 1, 4, 2}, new int[]{1, 2, 1, 2, 4, 1}, new int[]{1, 1, 4, 2, 1, 2}, new int[]{1, 2, 4, 1, 1, 2}, new int[]{1, 2, 4, 2, 1, 1}, new int[]{4, 1, 1, 2, 1, 2}, new int[]{4, 2, 1, 1, 1, 2}, new int[]{4, 2, 1, 2, 1, 1}, new int[]{2, 1, 2, 1, 4, 1}, new int[]{2, 1, 4, 1, 2, 1}, new int[]{4, 1, 2, 1, 2, 1}, new int[]{1, 1, 1, 1, 4, 3}, new int[]{1, 1, 1, 3, 4, 1}, new int[]{1, 3, 1, 1, 4, 1}, new int[]{1, 1, 4, 1, 1, 3}, new int[]{1, 1, 4, 3, 1, 1}, new int[]{4, 1, 1, 1, 1, 3}, new int[]{4, 1, 1, 3, 1, 1}, new int[]{1, 1, 3, 1, 4, 1}, new int[]{1, 1, 4, 1, 3, 1}, new int[]{3, 1, 1, 1, 4, 1}, new int[]{4, 1, 1, 1, 3, 1}, new int[]{2, 1, 1, 4, 1, 2}, new int[]{2, 1, 1, 2, 1, 4}, new int[]{2, 1, 1, 2, 3, 2}, new int[]{2, 3, 3, 1, 1, 1, 2}};
    private static final int CODE_SHIFT = 98;
    private static final int CODE_START_A = 103;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final int MAX_AVG_VARIANCE = 64;
    private static final int MAX_INDIVIDUAL_VARIANCE = 179;

    private static int decodeCode(BitArray bitArray, int[] iArr, int i) throws NotFoundException {
        recordPattern(bitArray, i, iArr);
        int i2 = 64;
        int i3 = -1;
        int i4 = 0;
        while (true) {
            int[][] iArr2 = CODE_PATTERNS;
            if (i4 >= iArr2.length) {
                break;
            }
            int patternMatchVariance = patternMatchVariance(iArr, iArr2[i4], MAX_INDIVIDUAL_VARIANCE);
            if (patternMatchVariance < i2) {
                i3 = i4;
                i2 = patternMatchVariance;
            }
            i4++;
        }
        if (i3 >= 0) {
            return i3;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int[] findStartPattern(BitArray bitArray) throws NotFoundException {
        int size = bitArray.getSize();
        int i = 0;
        while (i < size && !bitArray.get(i)) {
            i++;
        }
        int[] iArr = new int[6];
        int i2 = i;
        boolean z = false;
        int i3 = 0;
        while (i < size) {
            if (bitArray.get(i) ^ z) {
                iArr[i3] = iArr[i3] + 1;
            } else {
                if (i3 == 5) {
                    int i4 = 64;
                    int i5 = -1;
                    for (int i6 = 103; i6 <= 105; i6++) {
                        int patternMatchVariance = patternMatchVariance(iArr, CODE_PATTERNS[i6], MAX_INDIVIDUAL_VARIANCE);
                        if (patternMatchVariance < i4) {
                            i5 = i6;
                            i4 = patternMatchVariance;
                        }
                    }
                    if (i5 < 0 || !bitArray.isRange(Math.max(0, i2 - ((i - i2) / 2)), i2, false)) {
                        i2 += iArr[0] + iArr[1];
                        for (int i7 = 2; i7 < 6; i7++) {
                            iArr[i7 - 2] = iArr[i7];
                        }
                        iArr[4] = 0;
                        iArr[5] = 0;
                        i3--;
                    } else {
                        return new int[]{i2, i, i5};
                    }
                } else {
                    i3++;
                }
                iArr[i3] = 1;
                z = !z;
            }
            i++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0087, code lost:
        r9 = 'd';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008f, code lost:
        r9 = r11 + 32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0091, code lost:
        r8.append((char) r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00a4, code lost:
        if (r11 != 106) goto L_0x00b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00a7, code lost:
        r15 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00b0, code lost:
        r9 = 'c';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00b2, code lost:
        r23 = r9;
        r9 = r7;
        r7 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00db, code lost:
        if (r16 == false) goto L_0x00e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00dd, code lost:
        switch(r7) {
            case 99: goto L_0x00e7;
            case 100: goto L_0x00e4;
            case 101: goto L_0x00e1;
            default: goto L_0x00e0;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00e1, code lost:
        r7 = 'c';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00e4, code lost:
        r7 = 'e';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00e7, code lost:
        r7 = 'd';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00e9, code lost:
        r16 = r9;
        r12 = r10;
        r10 = r21;
        r9 = 0;
        r13 = 6;
        r23 = r17;
        r17 = r11;
        r11 = r23;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.Result decodeRow(int r25, com.google.zxing.common.BitArray r26, java.util.Hashtable r27) throws com.google.zxing.NotFoundException, com.google.zxing.FormatException, com.google.zxing.ChecksumException {
        /*
            r24 = this;
            r0 = r26
            int[] r1 = findStartPattern(r26)
            r2 = 2
            r3 = r1[r2]
            r4 = 101(0x65, float:1.42E-43)
            r5 = 99
            r6 = 100
            switch(r3) {
                case 103: goto L_0x001d;
                case 104: goto L_0x001a;
                case 105: goto L_0x0017;
                default: goto L_0x0012;
            }
        L_0x0012:
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance()
            throw r0
        L_0x0017:
            r7 = 99
            goto L_0x001f
        L_0x001a:
            r7 = 100
            goto L_0x001f
        L_0x001d:
            r7 = 101(0x65, float:1.42E-43)
        L_0x001f:
            java.lang.StringBuffer r8 = new java.lang.StringBuffer
            r9 = 20
            r8.<init>(r9)
            r9 = 0
            r10 = r1[r9]
            r11 = 1
            r12 = r1[r11]
            r13 = 6
            int[] r14 = new int[r13]
            r11 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 1
            r23 = r12
            r12 = r10
            r10 = r23
        L_0x003e:
            if (r15 != 0) goto L_0x00f8
            int r11 = decodeCode(r0, r14, r10)
            r12 = 106(0x6a, float:1.49E-43)
            if (r11 == r12) goto L_0x004a
            r19 = 1
        L_0x004a:
            if (r11 == r12) goto L_0x0052
            int r18 = r18 + 1
            int r20 = r18 * r11
            int r3 = r3 + r20
        L_0x0052:
            r21 = r10
        L_0x0054:
            if (r9 >= r13) goto L_0x005d
            r22 = r14[r9]
            int r21 = r21 + r22
            int r9 = r9 + 1
            goto L_0x0054
        L_0x005d:
            switch(r11) {
                case 103: goto L_0x0067;
                case 104: goto L_0x0067;
                case 105: goto L_0x0067;
                default: goto L_0x0060;
            }
        L_0x0060:
            r9 = 96
            switch(r7) {
                case 99: goto L_0x00b8;
                case 100: goto L_0x008d;
                case 101: goto L_0x006c;
                default: goto L_0x0065;
            }
        L_0x0065:
            goto L_0x00da
        L_0x0067:
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance()
            throw r0
        L_0x006c:
            r13 = 64
            if (r11 >= r13) goto L_0x0071
            goto L_0x008f
        L_0x0071:
            if (r11 >= r9) goto L_0x0076
            int r9 = r11 + -64
            goto L_0x0091
        L_0x0076:
            if (r11 == r12) goto L_0x007a
            r19 = 0
        L_0x007a:
            if (r11 == r12) goto L_0x008a
            switch(r11) {
                case 98: goto L_0x0086;
                case 99: goto L_0x0084;
                case 100: goto L_0x0082;
                default: goto L_0x007f;
            }
        L_0x007f:
            r9 = r7
            r7 = 0
            goto L_0x00b2
        L_0x0082:
            r7 = 0
            goto L_0x0087
        L_0x0084:
            r7 = 0
            goto L_0x00b0
        L_0x0086:
            r7 = 1
        L_0x0087:
            r9 = 100
            goto L_0x00b2
        L_0x008a:
            r9 = r7
            r7 = 0
            goto L_0x00a7
        L_0x008d:
            if (r11 >= r9) goto L_0x0096
        L_0x008f:
            int r9 = r11 + 32
        L_0x0091:
            char r9 = (char) r9
            r8.append(r9)
            goto L_0x00da
        L_0x0096:
            if (r11 == r12) goto L_0x009a
            r19 = 0
        L_0x009a:
            r9 = 98
            if (r11 == r9) goto L_0x00af
            if (r11 == r5) goto L_0x00ad
            if (r11 == r4) goto L_0x00a9
            r9 = r7
            r7 = 0
            if (r11 == r12) goto L_0x00a7
            goto L_0x00b2
        L_0x00a7:
            r15 = 1
            goto L_0x00b2
        L_0x00a9:
            r7 = 0
            r9 = 101(0x65, float:1.42E-43)
            goto L_0x00b2
        L_0x00ad:
            r7 = 0
            goto L_0x00b0
        L_0x00af:
            r7 = 1
        L_0x00b0:
            r9 = 99
        L_0x00b2:
            r23 = r9
            r9 = r7
            r7 = r23
            goto L_0x00db
        L_0x00b8:
            if (r11 >= r6) goto L_0x00c7
            r9 = 10
            if (r11 >= r9) goto L_0x00c3
            r9 = 48
            r8.append(r9)
        L_0x00c3:
            r8.append(r11)
            goto L_0x00da
        L_0x00c7:
            if (r11 == r12) goto L_0x00cb
            r19 = 0
        L_0x00cb:
            if (r11 == r6) goto L_0x00d8
            if (r11 == r4) goto L_0x00d5
            if (r11 == r12) goto L_0x00d2
            goto L_0x00da
        L_0x00d2:
            r9 = 0
            r15 = 1
            goto L_0x00db
        L_0x00d5:
            r7 = 101(0x65, float:1.42E-43)
            goto L_0x00da
        L_0x00d8:
            r7 = 100
        L_0x00da:
            r9 = 0
        L_0x00db:
            if (r16 == 0) goto L_0x00e9
            switch(r7) {
                case 99: goto L_0x00e7;
                case 100: goto L_0x00e4;
                case 101: goto L_0x00e1;
                default: goto L_0x00e0;
            }
        L_0x00e0:
            goto L_0x00e9
        L_0x00e1:
            r7 = 99
            goto L_0x00e9
        L_0x00e4:
            r7 = 101(0x65, float:1.42E-43)
            goto L_0x00e9
        L_0x00e7:
            r7 = 100
        L_0x00e9:
            r16 = r9
            r12 = r10
            r10 = r21
            r9 = 0
            r13 = 6
            r23 = r17
            r17 = r11
            r11 = r23
            goto L_0x003e
        L_0x00f8:
            int r4 = r26.getSize()
        L_0x00fc:
            if (r10 >= r4) goto L_0x0107
            boolean r6 = r0.get(r10)
            if (r6 == 0) goto L_0x0107
            int r10 = r10 + 1
            goto L_0x00fc
        L_0x0107:
            int r6 = r10 - r12
            int r6 = r6 / r2
            int r6 = r6 + r10
            int r4 = java.lang.Math.min(r4, r6)
            r6 = 0
            boolean r0 = r0.isRange(r10, r4, r6)
            if (r0 == 0) goto L_0x0170
            int r18 = r18 * r11
            int r3 = r3 - r18
            int r3 = r3 % 103
            if (r3 != r11) goto L_0x016b
            int r0 = r8.length()
            if (r0 <= 0) goto L_0x0130
            if (r19 == 0) goto L_0x0130
            if (r7 != r5) goto L_0x012b
            int r3 = r0 + -2
            goto L_0x012d
        L_0x012b:
            int r3 = r0 + -1
        L_0x012d:
            r8.delete(r3, r0)
        L_0x0130:
            java.lang.String r0 = r8.toString()
            int r3 = r0.length()
            if (r3 == 0) goto L_0x0166
            r3 = 1
            r4 = r1[r3]
            r3 = 0
            r1 = r1[r3]
            int r4 = r4 + r1
            float r1 = (float) r4
            r3 = 1073741824(0x40000000, float:2.0)
            float r1 = r1 / r3
            int r10 = r10 + r12
            float r4 = (float) r10
            float r4 = r4 / r3
            com.google.zxing.Result r3 = new com.google.zxing.Result
            r5 = 0
            com.google.zxing.ResultPoint[] r2 = new com.google.zxing.ResultPoint[r2]
            com.google.zxing.ResultPoint r6 = new com.google.zxing.ResultPoint
            r7 = r25
            float r7 = (float) r7
            r6.<init>(r1, r7)
            r1 = 0
            r2[r1] = r6
            com.google.zxing.ResultPoint r1 = new com.google.zxing.ResultPoint
            r1.<init>(r4, r7)
            r4 = 1
            r2[r4] = r1
            com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.CODE_128
            r3.<init>(r0, r5, r2, r1)
            return r3
        L_0x0166:
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance()
            throw r0
        L_0x016b:
            com.google.zxing.ChecksumException r0 = com.google.zxing.ChecksumException.getChecksumInstance()
            throw r0
        L_0x0170:
            com.google.zxing.NotFoundException r0 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code128Reader.decodeRow(int, com.google.zxing.common.BitArray, java.util.Hashtable):com.google.zxing.Result");
    }
}
