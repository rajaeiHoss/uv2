package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.StringUtils;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Vector;
import kotlin.text.Typography;
import org.kxml2.wap.Wbxml;

final class DecodedBitStreamParser {
    private static final char[] ALPHANUMERIC_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', Typography.dollar, '%', '*', '+', '-', '.', '/', ':'};

    private DecodedBitStreamParser() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x00d4 A[LOOP:0: B:1:0x0018->B:46:0x00d4, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00be A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.zxing.common.DecoderResult decode(byte[] r16, com.google.zxing.qrcode.decoder.Version r17, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel r18, java.util.Hashtable r19) throws com.google.zxing.FormatException {
        /*
            r0 = r16
            com.google.zxing.common.BitSource r7 = new com.google.zxing.common.BitSource
            r7.<init>(r0)
            java.lang.StringBuffer r8 = new java.lang.StringBuffer
            r1 = 50
            r8.<init>(r1)
            java.util.Vector r9 = new java.util.Vector
            r10 = 1
            r9.<init>(r10)
            r11 = 0
            r1 = 0
            r13 = r11
            r12 = 0
        L_0x0018:
            int r1 = r7.available()
            r2 = 4
            if (r1 >= r2) goto L_0x0023
            com.google.zxing.qrcode.decoder.Mode r1 = com.google.zxing.qrcode.decoder.Mode.TERMINATOR
        L_0x0021:
            r14 = r1
            goto L_0x002c
        L_0x0023:
            int r1 = r7.readBits(r2)     // Catch:{ IllegalArgumentException -> 0x00d8 }
            com.google.zxing.qrcode.decoder.Mode r1 = com.google.zxing.qrcode.decoder.Mode.forBits(r1)     // Catch:{ IllegalArgumentException -> 0x00d8 }
            goto L_0x0021
        L_0x002c:
            com.google.zxing.qrcode.decoder.Mode r1 = com.google.zxing.qrcode.decoder.Mode.TERMINATOR
            boolean r1 = r14.equals(r1)
            if (r1 != 0) goto L_0x0066
            com.google.zxing.qrcode.decoder.Mode r1 = com.google.zxing.qrcode.decoder.Mode.FNC1_FIRST_POSITION
            boolean r1 = r14.equals(r1)
            if (r1 != 0) goto L_0x00b3
            com.google.zxing.qrcode.decoder.Mode r1 = com.google.zxing.qrcode.decoder.Mode.FNC1_SECOND_POSITION
            boolean r1 = r14.equals(r1)
            if (r1 == 0) goto L_0x0046
            goto L_0x00b3
        L_0x0046:
            com.google.zxing.qrcode.decoder.Mode r1 = com.google.zxing.qrcode.decoder.Mode.STRUCTURED_APPEND
            boolean r1 = r14.equals(r1)
            if (r1 == 0) goto L_0x0054
            r1 = 16
            r7.readBits(r1)
            goto L_0x0066
        L_0x0054:
            com.google.zxing.qrcode.decoder.Mode r1 = com.google.zxing.qrcode.decoder.Mode.ECI
            boolean r1 = r14.equals(r1)
            if (r1 == 0) goto L_0x006e
            int r1 = parseECIValue(r7)
            com.google.zxing.common.CharacterSetECI r13 = com.google.zxing.common.CharacterSetECI.getCharacterSetECIByValue(r1)
            if (r13 == 0) goto L_0x0069
        L_0x0066:
            r15 = r17
            goto L_0x00b6
        L_0x0069:
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance()
            throw r0
        L_0x006e:
            r15 = r17
            int r1 = r14.getCharacterCountBits(r15)
            int r3 = r7.readBits(r1)
            com.google.zxing.qrcode.decoder.Mode r1 = com.google.zxing.qrcode.decoder.Mode.NUMERIC
            boolean r1 = r14.equals(r1)
            if (r1 == 0) goto L_0x0084
            decodeNumericSegment(r7, r8, r3)
            goto L_0x00b6
        L_0x0084:
            com.google.zxing.qrcode.decoder.Mode r1 = com.google.zxing.qrcode.decoder.Mode.ALPHANUMERIC
            boolean r1 = r14.equals(r1)
            if (r1 == 0) goto L_0x0090
            decodeAlphanumericSegment(r7, r8, r3, r12)
            goto L_0x00b6
        L_0x0090:
            com.google.zxing.qrcode.decoder.Mode r1 = com.google.zxing.qrcode.decoder.Mode.BYTE
            boolean r1 = r14.equals(r1)
            if (r1 == 0) goto L_0x00a2
            r1 = r7
            r2 = r8
            r4 = r13
            r5 = r9
            r6 = r19
            decodeByteSegment(r1, r2, r3, r4, r5, r6)
            goto L_0x00b6
        L_0x00a2:
            com.google.zxing.qrcode.decoder.Mode r1 = com.google.zxing.qrcode.decoder.Mode.KANJI
            boolean r1 = r14.equals(r1)
            if (r1 == 0) goto L_0x00ae
            decodeKanjiSegment(r7, r8, r3)
            goto L_0x00b6
        L_0x00ae:
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance()
            throw r0
        L_0x00b3:
            r15 = r17
            r12 = 1
        L_0x00b6:
            com.google.zxing.qrcode.decoder.Mode r1 = com.google.zxing.qrcode.decoder.Mode.TERMINATOR
            boolean r1 = r14.equals(r1)
            if (r1 == 0) goto L_0x00d4
            com.google.zxing.common.DecoderResult r1 = new com.google.zxing.common.DecoderResult
            java.lang.String r2 = r8.toString()
            boolean r3 = r9.isEmpty()
            if (r3 == 0) goto L_0x00ce
            r3 = r18
            r9 = r11
            goto L_0x00d0
        L_0x00ce:
            r3 = r18
        L_0x00d0:
            r1.<init>(r0, r2, r9, r3)
            return r1
        L_0x00d4:
            r3 = r18
            goto L_0x0018
        L_0x00d8:
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.decoder.DecodedBitStreamParser.decode(byte[], com.google.zxing.qrcode.decoder.Version, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel, java.util.Hashtable):com.google.zxing.common.DecoderResult");
    }

    private static void decodeAlphanumericSegment(BitSource bitSource, StringBuffer stringBuffer, int i, boolean z) throws FormatException {
        while (i > 1) {
            int readBits = bitSource.readBits(11);
            stringBuffer.append(toAlphaNumericChar(readBits / 45));
            stringBuffer.append(toAlphaNumericChar(readBits % 45));
            i -= 2;
        }
        if (i == 1) {
            stringBuffer.append(toAlphaNumericChar(bitSource.readBits(6)));
        }
        if (z) {
            for (int length = stringBuffer.length(); length < stringBuffer.length(); length++) {
                if (stringBuffer.charAt(length) == '%') {
                    if (length < stringBuffer.length() - 1) {
                        int i2 = length + 1;
                        if (stringBuffer.charAt(i2) == '%') {
                            stringBuffer.deleteCharAt(i2);
                        }
                    }
                    stringBuffer.setCharAt(length, (char) 29);
                }
            }
        }
    }

    private static void decodeByteSegment(BitSource bitSource, StringBuffer stringBuffer, int i, CharacterSetECI characterSetECI, Vector vector, Hashtable hashtable) throws FormatException {
        byte[] bArr = new byte[i];
        if ((i << 3) <= bitSource.available()) {
            for (int i2 = 0; i2 < i; i2++) {
                bArr[i2] = (byte) bitSource.readBits(8);
            }
            try {
                stringBuffer.append(new String(bArr, characterSetECI == null ? StringUtils.guessEncoding(bArr, hashtable) : characterSetECI.getEncodingName()));
                vector.addElement(bArr);
            } catch (UnsupportedEncodingException unused) {
                throw FormatException.getFormatInstance();
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static void decodeKanjiSegment(BitSource bitSource, StringBuffer stringBuffer, int i) throws FormatException {
        byte[] bArr = new byte[(i * 2)];
        int i2 = 0;
        while (i > 0) {
            int readBits = bitSource.readBits(13);
            int i3 = (readBits % Wbxml.EXT_0) | ((readBits / Wbxml.EXT_0) << 8);
            int i4 = i3 + (i3 < 7936 ? 33088 : 49472);
            bArr[i2] = (byte) (i4 >> 8);
            bArr[i2 + 1] = (byte) i4;
            i2 += 2;
            i--;
        }
        try {
            stringBuffer.append(new String(bArr, StringUtils.SHIFT_JIS));
        } catch (UnsupportedEncodingException unused) {
            throw FormatException.getFormatInstance();
        }
    }

    private static void decodeNumericSegment(BitSource bitSource, StringBuffer stringBuffer, int i) throws FormatException {
        int readBits;
        while (i >= 3) {
            int readBits2 = bitSource.readBits(10);
            if (readBits2 < 1000) {
                stringBuffer.append(toAlphaNumericChar(readBits2 / 100));
                stringBuffer.append(toAlphaNumericChar((readBits2 / 10) % 10));
                stringBuffer.append(toAlphaNumericChar(readBits2 % 10));
                i -= 3;
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (i == 2) {
            int readBits3 = bitSource.readBits(7);
            if (readBits3 < 100) {
                stringBuffer.append(toAlphaNumericChar(readBits3 / 10));
                readBits = readBits3 % 10;
            } else {
                throw FormatException.getFormatInstance();
            }
        } else if (i == 1) {
            readBits = bitSource.readBits(4);
            if (readBits >= 10) {
                throw FormatException.getFormatInstance();
            }
        } else {
            return;
        }
        stringBuffer.append(toAlphaNumericChar(readBits));
    }

    private static int parseECIValue(BitSource bitSource) {
        int readBits = bitSource.readBits(8);
        if ((readBits & 128) == 0) {
            return readBits & 127;
        }
        if ((readBits & Wbxml.EXT_0) == 128) {
            return bitSource.readBits(8) | ((readBits & 63) << 8);
        }
        if ((readBits & 224) == 192) {
            return bitSource.readBits(16) | ((readBits & 31) << 16);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Bad ECI bits starting with byte ");
        stringBuffer.append(readBits);
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    private static char toAlphaNumericChar(int i) throws FormatException {
        char[] cArr = ALPHANUMERIC_CHARS;
        if (i < cArr.length) {
            return cArr[i];
        }
        throw FormatException.getFormatInstance();
    }
}
