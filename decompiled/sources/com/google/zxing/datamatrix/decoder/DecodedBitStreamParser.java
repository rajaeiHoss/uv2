package com.google.zxing.datamatrix.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import kotlin.text.Typography;

final class DecodedBitStreamParser {
    private static final int ANSIX12_ENCODE = 4;
    private static final int ASCII_ENCODE = 1;
    private static final int BASE256_ENCODE = 6;
    private static final char[] C40_BASIC_SET_CHARS = {'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final int C40_ENCODE = 2;
    private static final char[] C40_SHIFT2_SET_CHARS = {'!', Typography.quote, '#', Typography.dollar, '%', Typography.amp, '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', Typography.less, '=', Typography.greater, '?', '@', '[', '\\', ']', '^', '_'};
    private static final int EDIFACT_ENCODE = 5;
    private static final int PAD_ENCODE = 0;
    private static final char[] TEXT_BASIC_SET_CHARS = {'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final int TEXT_ENCODE = 3;
    private static final char[] TEXT_SHIFT3_SET_CHARS = {'\'', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '{', '|', '}', '~', 127};

    private DecodedBitStreamParser() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:2:0x001b  */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x0020  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.zxing.common.DecoderResult decode(byte[] r7) throws com.google.zxing.FormatException {
        /*
            com.google.zxing.common.BitSource r0 = new com.google.zxing.common.BitSource
            r0.<init>(r7)
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r2 = 100
            r1.<init>(r2)
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r3 = 0
            r2.<init>(r3)
            java.util.Vector r3 = new java.util.Vector
            r4 = 1
            r3.<init>(r4)
            r5 = 1
        L_0x0019:
            if (r5 != r4) goto L_0x0020
            int r5 = decodeAsciiSegment(r0, r1, r2)
            goto L_0x0048
        L_0x0020:
            r6 = 2
            if (r5 == r6) goto L_0x0044
            r6 = 3
            if (r5 == r6) goto L_0x0040
            r6 = 4
            if (r5 == r6) goto L_0x003c
            r6 = 5
            if (r5 == r6) goto L_0x0038
            r6 = 6
            if (r5 != r6) goto L_0x0033
            decodeBase256Segment(r0, r1, r3)
            goto L_0x0047
        L_0x0033:
            com.google.zxing.FormatException r7 = com.google.zxing.FormatException.getFormatInstance()
            throw r7
        L_0x0038:
            decodeEdifactSegment(r0, r1)
            goto L_0x0047
        L_0x003c:
            decodeAnsiX12Segment(r0, r1)
            goto L_0x0047
        L_0x0040:
            decodeTextSegment(r0, r1)
            goto L_0x0047
        L_0x0044:
            decodeC40Segment(r0, r1)
        L_0x0047:
            r5 = 1
        L_0x0048:
            if (r5 == 0) goto L_0x0050
            int r6 = r0.available()
            if (r6 > 0) goto L_0x0019
        L_0x0050:
            int r0 = r2.length()
            if (r0 <= 0) goto L_0x005d
            java.lang.String r0 = r2.toString()
            r1.append(r0)
        L_0x005d:
            com.google.zxing.common.DecoderResult r0 = new com.google.zxing.common.DecoderResult
            java.lang.String r1 = r1.toString()
            boolean r2 = r3.isEmpty()
            r4 = 0
            if (r2 == 0) goto L_0x006b
            r3 = r4
        L_0x006b:
            r0.<init>(r7, r1, r3, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.decode(byte[]):com.google.zxing.common.DecoderResult");
    }

    private static void decodeAnsiX12Segment(BitSource bitSource, StringBuffer stringBuffer) throws FormatException {
        int readBits;
        int i;
        char c;
        int[] iArr = new int[3];
        while (bitSource.available() != 8 && (readBits = bitSource.readBits(8)) != 254) {
            parseTwoBytes(readBits, bitSource.readBits(8), iArr);
            for (int i2 = 0; i2 < 3; i2++) {
                int i3 = iArr[i2];
                if (i3 == 0) {
                    c = 13;
                } else if (i3 == 1) {
                    c = '*';
                } else if (i3 == 2) {
                    c = Typography.greater;
                } else if (i3 == 3) {
                    c = ' ';
                } else {
                    if (i3 < 14) {
                        i = i3 + 44;
                    } else if (i3 < 40) {
                        i = i3 + 51;
                    } else {
                        throw FormatException.getFormatInstance();
                    }
                    c = (char) i;
                }
                stringBuffer.append(c);
            }
            if (bitSource.available() <= 0) {
                return;
            }
        }
    }

    private static int decodeAsciiSegment(BitSource bitSource, StringBuffer stringBuffer, StringBuffer stringBuffer2) throws FormatException {
        String str;
        boolean z = false;
        do {
            int readBits = bitSource.readBits(8);
            if (readBits == 0) {
                throw FormatException.getFormatInstance();
            } else if (readBits <= 128) {
                if (z) {
                    readBits += 128;
                }
                stringBuffer.append((char) (readBits - 1));
                return 1;
            } else if (readBits == 129) {
                return 0;
            } else {
                if (readBits <= 229) {
                    int i = readBits - 130;
                    if (i < 10) {
                        stringBuffer.append('0');
                    }
                    stringBuffer.append(i);
                } else if (readBits == 230) {
                    return 2;
                } else {
                    if (readBits == 231) {
                        return 6;
                    }
                    if (!(readBits == 232 || readBits == 233 || readBits == 234)) {
                        if (readBits == 235) {
                            z = true;
                        } else {
                            str = null;
                            if (readBits == 236) {
                                str = "[)>\u001e05\u001d";
                            } else if (readBits == 237) {
                                str = "[)>\u001e06\u001d";
                            } else if (readBits == 238) {
                                return 4;
                            } else {
                                if (readBits == 239) {
                                    return 3;
                                }
                                if (readBits == 240) {
                                    return 5;
                                }
                                if (readBits != 241 && readBits >= 242) {
                                    throw FormatException.getFormatInstance();
                                }
                            }
                            if (str != null) {
                                stringBuffer.append(str);
                                stringBuffer2.insert(0, "\u001e\u0004");
                            }
                        }
                    }
                }
            }
        } while (bitSource.available() > 0);
        return 1;
    }

    private static void decodeBase256Segment(BitSource bitSource, StringBuffer stringBuffer, Vector vector) throws FormatException {
        int readBits = bitSource.readBits(8);
        if (readBits == 0) {
            readBits = bitSource.available() / 8;
        } else if (readBits >= 250) {
            readBits = ((readBits - 249) * 250) + bitSource.readBits(8);
        }
        byte[] bArr = new byte[readBits];
        int i = 0;
        while (i < readBits) {
            if (bitSource.available() >= 8) {
                bArr[i] = unrandomize255State(bitSource.readBits(8), i);
                i++;
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        vector.addElement(bArr);
        try {
            stringBuffer.append(new String(bArr, "ISO8859_1"));
        } catch (UnsupportedEncodingException e) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Platform does not support required encoding: ");
            stringBuffer2.append(e);
            throw new RuntimeException(stringBuffer2.toString());
        }
    }

    private static void decodeC40Segment(BitSource bitSource, StringBuffer stringBuffer) throws FormatException {
        int readBits;
        char c;
        int i;
        char c2;
        int[] iArr = new int[3];
        boolean z = false;
        while (bitSource.available() != 8 && (readBits = bitSource.readBits(8)) != 254) {
            parseTwoBytes(readBits, bitSource.readBits(8), iArr);
            int i2 = 0;
            for (int i3 = 0; i3 < 3; i3++) {
                int i4 = iArr[i3];
                if (i2 != 0) {
                    if (i2 != 1) {
                        if (i2 != 2) {
                            if (i2 != 3) {
                                throw FormatException.getFormatInstance();
                            }
                            char out = (char) (i4 + (z ? 224 : 96));
                            stringBuffer.append(out);
                            z = false;
                            i2 = 0;
                        } else if (i4 < 27) {
                            char out2 = C40_SHIFT2_SET_CHARS[i4];
                            if (z) {
                                out2 = (char) (out2 + 128);
                                z = false;
                            }
                            stringBuffer.append(out2);
                            i2 = 0;
                        } else if (i4 == 27) {
                            throw FormatException.getFormatInstance();
                        } else if (i4 == 30) {
                            z = true;
                            i2 = 0;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    } else {
                        char out3 = (char) (z ? i4 + 128 : i4);
                        stringBuffer.append(out3);
                        z = false;
                        i2 = 0;
                    }
                } else if (i4 < 3) {
                    i2 = i4 + 1;
                } else if (z) {
                    stringBuffer.append((char) (C40_BASIC_SET_CHARS[i4] + 128));
                    z = false;
                } else {
                    stringBuffer.append(C40_BASIC_SET_CHARS[i4]);
                }
            }
            if (bitSource.available() <= 0) {
                return;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x000b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void decodeEdifactSegment(com.google.zxing.common.BitSource r5, java.lang.StringBuffer r6) {
        /*
            r0 = 0
            r1 = 0
        L_0x0002:
            int r2 = r5.available()
            r3 = 16
            if (r2 > r3) goto L_0x000b
            return
        L_0x000b:
            r2 = 0
        L_0x000c:
            r3 = 4
            if (r2 >= r3) goto L_0x0027
            r3 = 6
            int r3 = r5.readBits(r3)
            r4 = 11111(0x2b67, float:1.557E-41)
            if (r3 != r4) goto L_0x0019
            r1 = 1
        L_0x0019:
            if (r1 != 0) goto L_0x0024
            r4 = r3 & 32
            if (r4 != 0) goto L_0x0021
            r3 = r3 | 64
        L_0x0021:
            r6.append(r3)
        L_0x0024:
            int r2 = r2 + 1
            goto L_0x000c
        L_0x0027:
            if (r1 != 0) goto L_0x002f
            int r2 = r5.available()
            if (r2 > 0) goto L_0x0002
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.decodeEdifactSegment(com.google.zxing.common.BitSource, java.lang.StringBuffer):void");
    }

    private static void decodeTextSegment(BitSource bitSource, StringBuffer stringBuffer) throws FormatException {
        int readBits;
        char c;
        int[] iArr = new int[3];
        boolean z = false;
        while (bitSource.available() != 8 && (readBits = bitSource.readBits(8)) != 254) {
            parseTwoBytes(readBits, bitSource.readBits(8), iArr);
            int i = 0;
            for (int i2 = 0; i2 < 3; i2++) {
                int i3 = iArr[i2];
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                throw FormatException.getFormatInstance();
                            }
                            char out = TEXT_SHIFT3_SET_CHARS[i3];
                            if (z) {
                                out = (char) (out + 128);
                                z = false;
                            }
                            stringBuffer.append(out);
                            i = 0;
                        } else if (i3 < 27) {
                            char out2 = C40_SHIFT2_SET_CHARS[i3];
                            if (z) {
                                out2 = (char) (out2 + 128);
                                z = false;
                            }
                            stringBuffer.append(out2);
                            i = 0;
                        } else if (i3 == 27) {
                            throw FormatException.getFormatInstance();
                        } else if (i3 == 30) {
                            z = true;
                            i = 0;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    } else {
                        char out3 = (char) (z ? i3 + 128 : i3);
                        stringBuffer.append(out3);
                        z = false;
                        i = 0;
                    }
                } else if (i3 < 3) {
                    i = i3 + 1;
                } else if (z) {
                    stringBuffer.append((char) (TEXT_BASIC_SET_CHARS[i3] + 128));
                    z = false;
                } else {
                    stringBuffer.append(TEXT_BASIC_SET_CHARS[i3]);
                }
            }
            if (bitSource.available() <= 0) {
                return;
            }
        }
    }

    private static void parseTwoBytes(int i, int i2, int[] iArr) {
        int i3 = ((i << 8) + i2) - 1;
        int i4 = i3 / 1600;
        iArr[0] = i4;
        int i5 = i3 - (i4 * 1600);
        int i6 = i5 / 40;
        iArr[1] = i6;
        iArr[2] = i5 - (i6 * 40);
    }

    private static byte unrandomize255State(int i, int i2) {
        int i3 = i - (((i2 * 149) % 255) + 1);
        if (i3 < 0) {
            i3 += 256;
        }
        return (byte) i3;
    }
}
