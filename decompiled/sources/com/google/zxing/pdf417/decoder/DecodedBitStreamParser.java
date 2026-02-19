package com.google.zxing.pdf417.decoder;

import kotlin.text.Typography;

final class DecodedBitStreamParser {
    private static final int AL = 28;
    private static final int ALPHA = 0;
    private static final int AS = 27;
    private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
    private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
    private static final int BYTE_COMPACTION_MODE_LATCH = 901;
    private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
    private static final String[] EXP900 = {"000000000000000000000000000000000000000000001", "000000000000000000000000000000000000000000900", "000000000000000000000000000000000000000810000", "000000000000000000000000000000000000729000000", "000000000000000000000000000000000656100000000", "000000000000000000000000000000590490000000000", "000000000000000000000000000531441000000000000", "000000000000000000000000478296900000000000000", "000000000000000000000430467210000000000000000", "000000000000000000387420489000000000000000000", "000000000000000348678440100000000000000000000", "000000000000313810596090000000000000000000000", "000000000282429536481000000000000000000000000", "000000254186582832900000000000000000000000000", "000228767924549610000000000000000000000000000", "205891132094649000000000000000000000000000000"};
    private static final int LL = 27;
    private static final int LOWER = 1;
    private static final int MACRO_PDF417_TERMINATOR = 922;
    private static final int MAX_NUMERIC_CODEWORDS = 15;
    private static final int MIXED = 2;
    private static final char[] MIXED_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', Typography.amp, 13, 9, ',', ':', '#', '-', '.', Typography.dollar, '/', '+', '%', '*', '=', '^'};
    private static final int ML = 28;
    private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
    private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
    private static final int PAL = 29;
    private static final int PL = 25;
    private static final int PS = 29;
    private static final int PUNCT = 3;
    private static final char[] PUNCT_CHARS = {';', Typography.less, Typography.greater, '@', '[', '\\', '}', '_', '`', '~', '!', 13, 9, ',', ':', 10, '-', '.', Typography.dollar, '/', Typography.quote, '|', '*', '(', ')', '?', '{', '}', '\''};
    private static final int PUNCT_SHIFT = 4;
    private static final int TEXT_COMPACTION_MODE_LATCH = 900;

    private DecodedBitStreamParser() {
    }

    private static StringBuffer add(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer(5);
        StringBuffer stringBuffer2 = new StringBuffer(5);
        StringBuffer stringBuffer3 = new StringBuffer(str.length());
        for (int i = 0; i < str.length(); i++) {
            stringBuffer3.append('0');
        }
        int length = str.length() - 3;
        int i2 = 0;
        while (length > -1) {
            stringBuffer.setLength(0);
            stringBuffer.append(str.charAt(length));
            int i3 = length + 1;
            stringBuffer.append(str.charAt(i3));
            int i4 = length + 2;
            stringBuffer.append(str.charAt(i4));
            stringBuffer2.setLength(0);
            stringBuffer2.append(str2.charAt(length));
            stringBuffer2.append(str2.charAt(i3));
            stringBuffer2.append(str2.charAt(i4));
            int parseInt = Integer.parseInt(stringBuffer.toString()) + Integer.parseInt(stringBuffer2.toString()) + i2;
            int i5 = parseInt % 1000;
            stringBuffer3.setCharAt(i4, (char) ((i5 % 10) + 48));
            stringBuffer3.setCharAt(i3, (char) (((i5 / 10) % 10) + 48));
            stringBuffer3.setCharAt(length, (char) ((i5 / 100) + 48));
            length -= 3;
            i2 = parseInt / 1000;
        }
        return stringBuffer3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0057 A[LOOP:1: B:22:0x0055->B:23:0x0057, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int byteCompaction(int r24, int[] r25, int r26, java.lang.StringBuffer r27) {
        /*
            r0 = r24
            r1 = r27
            r2 = 922(0x39a, float:1.292E-42)
            r4 = 923(0x39b, float:1.293E-42)
            r5 = 928(0x3a0, float:1.3E-42)
            r6 = 902(0x386, float:1.264E-42)
            r7 = 900(0x384, double:4.447E-321)
            r11 = 924(0x39c, float:1.295E-42)
            r12 = 901(0x385, float:1.263E-42)
            r13 = 900(0x384, float:1.261E-42)
            r14 = 6
            r16 = 0
            if (r0 != r12) goto L_0x0083
            char[] r0 = new char[r14]
            int[] r9 = new int[r14]
            r10 = r26
            r15 = 0
            r17 = 0
            r19 = 0
        L_0x0024:
            r3 = r25[r16]
            if (r10 >= r3) goto L_0x0071
            if (r19 != 0) goto L_0x0071
            int r3 = r10 + 1
            r10 = r25[r10]
            if (r10 >= r13) goto L_0x003b
            r9[r15] = r10
            int r15 = r15 + 1
            long r17 = r17 * r7
            long r7 = (long) r10
            long r17 = r17 + r7
        L_0x0039:
            r10 = r3
            goto L_0x004e
        L_0x003b:
            if (r10 == r13) goto L_0x0049
            if (r10 == r12) goto L_0x0049
            if (r10 == r6) goto L_0x0049
            if (r10 == r11) goto L_0x0049
            if (r10 == r5) goto L_0x0049
            if (r10 == r4) goto L_0x0049
            if (r10 != r2) goto L_0x0039
        L_0x0049:
            int r3 = r3 + -1
            r10 = r3
            r19 = 1
        L_0x004e:
            int r3 = r15 % 5
            if (r3 != 0) goto L_0x006d
            if (r15 <= 0) goto L_0x006d
            r3 = 0
        L_0x0055:
            if (r3 >= r14) goto L_0x0069
            int r7 = 5 - r3
            r22 = 256(0x100, double:1.265E-321)
            long r14 = r17 % r22
            int r15 = (int) r14
            char r14 = (char) r15
            r0[r7] = r14
            r7 = 8
            long r17 = r17 >> r7
            int r3 = r3 + 1
            r14 = 6
            goto L_0x0055
        L_0x0069:
            r1.append(r0)
            r15 = 0
        L_0x006d:
            r7 = 900(0x384, double:4.447E-321)
            r14 = 6
            goto L_0x0024
        L_0x0071:
            int r0 = r15 / 5
            int r0 = r0 * 5
        L_0x0075:
            if (r0 >= r15) goto L_0x0080
            r2 = r9[r0]
            char r2 = (char) r2
            r1.append(r2)
            int r0 = r0 + 1
            goto L_0x0075
        L_0x0080:
            r0 = r10
            goto L_0x00fc
        L_0x0083:
            if (r0 != r11) goto L_0x00fa
            r0 = r26
            r3 = 0
            r7 = 0
            r9 = 0
        L_0x008b:
            r14 = r25[r16]
            if (r0 >= r14) goto L_0x00fc
            if (r3 != 0) goto L_0x00fc
            int r14 = r0 + 1
            r0 = r25[r0]
            if (r0 >= r13) goto L_0x00a7
            int r7 = r7 + 1
            r17 = 900(0x384, double:4.447E-321)
            long r9 = r9 * r17
            r24 = r3
            long r2 = (long) r0
            long r9 = r9 + r2
            r3 = r24
            r0 = r14
            r2 = 922(0x39a, float:1.292E-42)
            goto L_0x00c6
        L_0x00a7:
            r24 = r3
            r17 = 900(0x384, double:4.447E-321)
            if (r0 == r13) goto L_0x00c0
            if (r0 == r12) goto L_0x00c0
            if (r0 == r6) goto L_0x00c0
            if (r0 == r11) goto L_0x00c0
            if (r0 == r5) goto L_0x00c0
            if (r0 == r4) goto L_0x00c0
            r2 = 922(0x39a, float:1.292E-42)
            if (r0 != r2) goto L_0x00bc
            goto L_0x00c2
        L_0x00bc:
            r3 = r24
            r0 = r14
            goto L_0x00c6
        L_0x00c0:
            r2 = 922(0x39a, float:1.292E-42)
        L_0x00c2:
            int r14 = r14 + -1
            r0 = r14
            r3 = 1
        L_0x00c6:
            int r14 = r7 % 5
            if (r14 != 0) goto L_0x00f0
            if (r7 <= 0) goto L_0x00f0
            r8 = 6
            char[] r14 = new char[r8]
            r15 = 0
        L_0x00d0:
            if (r15 >= r8) goto L_0x00e8
            int r19 = 5 - r15
            r20 = 255(0xff, double:1.26E-321)
            r24 = r3
            long r2 = r9 & r20
            int r3 = (int) r2
            char r2 = (char) r3
            r14[r19] = r2
            r2 = 8
            long r9 = r9 >> r2
            int r15 = r15 + 1
            r3 = r24
            r2 = 922(0x39a, float:1.292E-42)
            goto L_0x00d0
        L_0x00e8:
            r24 = r3
            r2 = 8
            r1.append(r14)
            goto L_0x00f5
        L_0x00f0:
            r24 = r3
            r2 = 8
            r8 = 6
        L_0x00f5:
            r3 = r24
            r2 = 922(0x39a, float:1.292E-42)
            goto L_0x008b
        L_0x00fa:
            r0 = r26
        L_0x00fc:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.byteCompaction(int, int[], int, java.lang.StringBuffer):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0028, code lost:
        r1 = textCompaction(r4, r2, r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.zxing.common.DecoderResult decode(int[] r4) throws com.google.zxing.FormatException {
        /*
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r1 = 100
            r0.<init>(r1)
            r1 = 1
            r1 = r4[r1]
            r2 = 2
        L_0x000b:
            r3 = 0
            r3 = r4[r3]
            if (r2 >= r3) goto L_0x0039
            r3 = 913(0x391, float:1.28E-42)
            if (r1 == r3) goto L_0x0023
            r3 = 924(0x39c, float:1.295E-42)
            if (r1 == r3) goto L_0x0023
            switch(r1) {
                case 900: goto L_0x0028;
                case 901: goto L_0x0023;
                case 902: goto L_0x001e;
                default: goto L_0x001b;
            }
        L_0x001b:
            int r2 = r2 + -1
            goto L_0x0028
        L_0x001e:
            int r1 = numericCompaction(r4, r2, r0)
            goto L_0x002c
        L_0x0023:
            int r1 = byteCompaction(r1, r4, r2, r0)
            goto L_0x002c
        L_0x0028:
            int r1 = textCompaction(r4, r2, r0)
        L_0x002c:
            int r2 = r4.length
            if (r1 >= r2) goto L_0x0034
            int r2 = r1 + 1
            r1 = r4[r1]
            goto L_0x000b
        L_0x0034:
            com.google.zxing.FormatException r4 = com.google.zxing.FormatException.getFormatInstance()
            throw r4
        L_0x0039:
            com.google.zxing.common.DecoderResult r4 = new com.google.zxing.common.DecoderResult
            java.lang.String r0 = r0.toString()
            r1 = 0
            r4.<init>(r1, r0, r1, r1)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.decode(int[]):com.google.zxing.common.DecoderResult");
    }

    private static String decodeBase900toBase10(int[] iArr, int i) {
        int i2 = 0;
        String str = null;
        StringBuffer stringBuffer = null;
        for (int i3 = 0; i3 < i; i3++) {
            StringBuffer multiply = multiply(EXP900[(i - i3) - 1], iArr[i3]);
            stringBuffer = stringBuffer == null ? multiply : add(stringBuffer.toString(), multiply.toString());
        }
        while (true) {
            if (i2 >= stringBuffer.length()) {
                break;
            } else if (stringBuffer.charAt(i2) == '1') {
                str = stringBuffer.toString().substring(i2 + 1);
                break;
            } else {
                i2++;
            }
        }
        return str == null ? stringBuffer.toString() : str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00a3 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void decodeTextCompaction(int[] r16, int[] r17, int r18, java.lang.StringBuffer r19) {
        /*
            r0 = r19
            r2 = r18
            r3 = 0
            r4 = 0
            r5 = 0
        L_0x0007:
            if (r3 >= r2) goto L_0x00a7
            r6 = r16[r3]
            r7 = 27
            r8 = 3
            r9 = 32
            r10 = 2
            r11 = 1
            r12 = 913(0x391, float:1.28E-42)
            r13 = 28
            r14 = 4
            r15 = 26
            r1 = 29
            if (r4 == 0) goto L_0x0082
            if (r4 == r11) goto L_0x006c
            if (r4 == r10) goto L_0x004d
            if (r4 == r8) goto L_0x0036
            if (r4 == r14) goto L_0x0027
            goto L_0x009d
        L_0x0027:
            if (r6 >= r1) goto L_0x0030
            char[] r1 = PUNCT_CHARS
            char r9 = r1[r6]
            r4 = r5
            goto L_0x009e
        L_0x0030:
            if (r6 != r1) goto L_0x0033
            goto L_0x0040
        L_0x0033:
            r4 = r5
            goto L_0x009d
        L_0x0036:
            if (r6 >= r1) goto L_0x003e
            char[] r1 = PUNCT_CHARS
            char r9 = r1[r6]
            goto L_0x009e
        L_0x003e:
            if (r6 != r1) goto L_0x0043
        L_0x0040:
            r4 = 0
            goto L_0x009d
        L_0x0043:
            if (r6 != r12) goto L_0x009d
            r1 = r17[r3]
        L_0x0047:
            char r1 = (char) r1
            r0.append(r1)
            goto L_0x009d
        L_0x004d:
            r10 = 25
            if (r6 >= r10) goto L_0x0057
            char[] r1 = MIXED_CHARS
            char r9 = r1[r6]
            goto L_0x009e
        L_0x0057:
            if (r6 != r10) goto L_0x005b
            r4 = 3
            goto L_0x009d
        L_0x005b:
            if (r6 != r15) goto L_0x005e
            goto L_0x009e
        L_0x005e:
            if (r6 != r7) goto L_0x0061
            goto L_0x009d
        L_0x0061:
            if (r6 != r13) goto L_0x0064
            goto L_0x0040
        L_0x0064:
            if (r6 != r1) goto L_0x0067
            goto L_0x0095
        L_0x0067:
            if (r6 != r12) goto L_0x009d
            r1 = r17[r3]
            goto L_0x0047
        L_0x006c:
            if (r6 >= r15) goto L_0x0071
            int r6 = r6 + 97
            goto L_0x0086
        L_0x0071:
            if (r6 != r15) goto L_0x0074
            goto L_0x009e
        L_0x0074:
            if (r6 != r13) goto L_0x0077
            goto L_0x0040
        L_0x0077:
            if (r6 != r13) goto L_0x007a
            goto L_0x0091
        L_0x007a:
            if (r6 != r1) goto L_0x007d
            goto L_0x0095
        L_0x007d:
            if (r6 != r12) goto L_0x009d
            r1 = r17[r3]
            goto L_0x0047
        L_0x0082:
            if (r6 >= r15) goto L_0x0088
            int r6 = r6 + 65
        L_0x0086:
            char r9 = (char) r6
            goto L_0x009e
        L_0x0088:
            if (r6 != r15) goto L_0x008b
            goto L_0x009e
        L_0x008b:
            if (r6 != r7) goto L_0x008f
            r4 = 1
            goto L_0x009d
        L_0x008f:
            if (r6 != r13) goto L_0x0093
        L_0x0091:
            r4 = 2
            goto L_0x009d
        L_0x0093:
            if (r6 != r1) goto L_0x0098
        L_0x0095:
            r5 = r4
            r4 = 4
            goto L_0x009d
        L_0x0098:
            if (r6 != r12) goto L_0x009d
            r1 = r17[r3]
            goto L_0x0047
        L_0x009d:
            r9 = 0
        L_0x009e:
            if (r9 == 0) goto L_0x00a3
            r0.append(r9)
        L_0x00a3:
            int r3 = r3 + 1
            goto L_0x0007
        L_0x00a7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.decodeTextCompaction(int[], int[], int, java.lang.StringBuffer):void");
    }

    private static StringBuffer multiply(String str, int i) {
        StringBuffer stringBuffer = new StringBuffer(str.length());
        for (int i2 = 0; i2 < str.length(); i2++) {
            stringBuffer.append('0');
        }
        int i3 = i / 100;
        int i4 = (i / 10) % 10;
        int i5 = i % 10;
        for (int i6 = 0; i6 < i5; i6++) {
            stringBuffer = add(stringBuffer.toString(), str);
        }
        for (int i7 = 0; i7 < i4; i7++) {
            String stringBuffer2 = stringBuffer.toString();
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(str);
            stringBuffer3.append('0');
            stringBuffer = add(stringBuffer2, stringBuffer3.toString().substring(1));
        }
        for (int i8 = 0; i8 < i3; i8++) {
            String stringBuffer4 = stringBuffer.toString();
            StringBuffer stringBuffer5 = new StringBuffer();
            stringBuffer5.append(str);
            stringBuffer5.append("00");
            stringBuffer = add(stringBuffer4, stringBuffer5.toString().substring(2));
        }
        return stringBuffer;
    }

    private static int numericCompaction(int[] iArr, int i, StringBuffer stringBuffer) {
        int[] iArr2 = new int[15];
        boolean z = false;
        int i2 = 0;
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i3 == iArr[0]) {
                z = true;
            }
            if (i4 < 900) {
                iArr2[i2] = i4;
                i2++;
            } else if (i4 == 900 || i4 == 901 || i4 == BYTE_COMPACTION_MODE_LATCH_6 || i4 == BEGIN_MACRO_PDF417_CONTROL_BLOCK || i4 == BEGIN_MACRO_PDF417_OPTIONAL_FIELD || i4 == MACRO_PDF417_TERMINATOR) {
                i3--;
                z = true;
            }
            if (i2 % 15 == 0 || i4 == 902 || z) {
                stringBuffer.append(decodeBase900toBase10(iArr2, i2));
                i2 = 0;
            }
            i = i3;
        }
        return i;
    }

    private static int textCompaction(int[] iArr, int i, StringBuffer stringBuffer) {
        int[] iArr2 = new int[(iArr[0] << 1)];
        int[] iArr3 = new int[(iArr[0] << 1)];
        boolean z = false;
        int i2 = 0;
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i4 < 900) {
                iArr2[i2] = i4 / 30;
                iArr2[i2 + 1] = i4 % 30;
                i2 += 2;
            } else if (i4 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                if (i4 != BYTE_COMPACTION_MODE_LATCH_6) {
                    switch (i4) {
                        case 900:
                        case 901:
                        case 902:
                            break;
                    }
                }
                i = i3 - 1;
                z = true;
            } else {
                iArr2[i2] = MODE_SHIFT_TO_BYTE_COMPACTION_MODE;
                iArr3[i2] = i4;
                i2++;
            }
            i = i3;
        }
        decodeTextCompaction(iArr2, iArr3, i2, stringBuffer);
        return i;
    }
}
