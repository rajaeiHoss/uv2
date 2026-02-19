package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;
import kotlin.text.Typography;
import org.xbill.DNS.Type;
import org.xbill.DNS.WKSRecord;

final class GeneralAppIdDecoder {
    private final StringBuffer buffer = new StringBuffer();
    private final CurrentParsingState current = new CurrentParsingState();
    private final BitArray information;

    GeneralAppIdDecoder(BitArray bitArray) {
        this.information = bitArray;
    }

    private DecodedChar decodeAlphanumeric(int i) {
        int extractNumericValueFromBitArray = extractNumericValueFromBitArray(i, 5);
        if (extractNumericValueFromBitArray == 15) {
            return new DecodedChar(i + 5, Typography.dollar);
        }
        if (extractNumericValueFromBitArray >= 5 && extractNumericValueFromBitArray < 15) {
            return new DecodedChar(i + 5, (char) ((extractNumericValueFromBitArray + 48) - 5));
        }
        int extractNumericValueFromBitArray2 = extractNumericValueFromBitArray(i, 6);
        if (extractNumericValueFromBitArray2 >= 32 && extractNumericValueFromBitArray2 < 58) {
            return new DecodedChar(i + 6, (char) (extractNumericValueFromBitArray2 + 33));
        }
        switch (extractNumericValueFromBitArray2) {
            case 58:
                return new DecodedChar(i + 6, '*');
            case 59:
                return new DecodedChar(i + 6, ',');
            case 60:
                return new DecodedChar(i + 6, '-');
            case 61:
                return new DecodedChar(i + 6, '.');
            case 62:
                return new DecodedChar(i + 6, '/');
            default:
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Decoding invalid alphanumeric value: ");
                stringBuffer.append(extractNumericValueFromBitArray2);
                throw new RuntimeException(stringBuffer.toString());
        }
    }

    private DecodedChar decodeIsoIec646(int i) {
        int extractNumericValueFromBitArray = extractNumericValueFromBitArray(i, 5);
        if (extractNumericValueFromBitArray == 15) {
            return new DecodedChar(i + 5, Typography.dollar);
        }
        if (extractNumericValueFromBitArray >= 5 && extractNumericValueFromBitArray < 15) {
            return new DecodedChar(i + 5, (char) ((extractNumericValueFromBitArray + 48) - 5));
        }
        int extractNumericValueFromBitArray2 = extractNumericValueFromBitArray(i, 7);
        if (extractNumericValueFromBitArray2 >= 64 && extractNumericValueFromBitArray2 < 90) {
            return new DecodedChar(i + 7, (char) (extractNumericValueFromBitArray2 + 1));
        }
        if (extractNumericValueFromBitArray2 >= 90 && extractNumericValueFromBitArray2 < 116) {
            return new DecodedChar(i + 7, (char) (extractNumericValueFromBitArray2 + 7));
        }
        int extractNumericValueFromBitArray3 = extractNumericValueFromBitArray(i, 8);
        switch (extractNumericValueFromBitArray3) {
            case 232:
                return new DecodedChar(i + 8, '!');
            case 233:
                return new DecodedChar(i + 8, Typography.quote);
            case 234:
                return new DecodedChar(i + 8, '%');
            case 235:
                return new DecodedChar(i + 8, Typography.amp);
            case 236:
                return new DecodedChar(i + 8, '\'');
            case 237:
                return new DecodedChar(i + 8, '(');
            case 238:
                return new DecodedChar(i + 8, ')');
            case 239:
                return new DecodedChar(i + 8, '*');
            case 240:
                return new DecodedChar(i + 8, '+');
            case 241:
                return new DecodedChar(i + 8, ',');
            case 242:
                return new DecodedChar(i + 8, '-');
            case WKSRecord.Service.SUR_MEAS /*243*/:
                return new DecodedChar(i + 8, '.');
            case 244:
                return new DecodedChar(i + 8, '/');
            case WKSRecord.Service.LINK /*245*/:
                return new DecodedChar(i + 8, ':');
            case 246:
                return new DecodedChar(i + 8, ';');
            case 247:
                return new DecodedChar(i + 8, Typography.less);
            case 248:
                return new DecodedChar(i + 8, '=');
            case Type.TKEY /*249*/:
                return new DecodedChar(i + 8, Typography.greater);
            case 250:
                return new DecodedChar(i + 8, '?');
            case Type.IXFR /*251*/:
                return new DecodedChar(i + 8, '_');
            case 252:
                return new DecodedChar(i + 8, ' ');
            default:
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Decoding invalid ISO/IEC 646 value: ");
                stringBuffer.append(extractNumericValueFromBitArray3);
                throw new RuntimeException(stringBuffer.toString());
        }
    }

    private DecodedNumeric decodeNumeric(int i) {
        int i2 = i + 7;
        if (i2 > this.information.size) {
            int extractNumericValueFromBitArray = extractNumericValueFromBitArray(i, 4);
            return extractNumericValueFromBitArray == 0 ? new DecodedNumeric(this.information.size, 10, 10) : new DecodedNumeric(this.information.size, extractNumericValueFromBitArray - 1, 10);
        }
        int extractNumericValueFromBitArray2 = extractNumericValueFromBitArray(i, 7) - 8;
        return new DecodedNumeric(i2, extractNumericValueFromBitArray2 / 11, extractNumericValueFromBitArray2 % 11);
    }

    static int extractNumericValueFromBitArray(BitArray bitArray, int i, int i2) {
        if (i2 <= 32) {
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                if (bitArray.get(i + i4)) {
                    i3 |= 1 << ((i2 - i4) - 1);
                }
            }
            return i3;
        }
        throw new IllegalArgumentException("extractNumberValueFromBitArray can't handle more than 32 bits");
    }

    private boolean isAlphaOr646ToNumericLatch(int i) {
        int i2 = i + 3;
        if (i2 > this.information.size) {
            return false;
        }
        while (i < i2) {
            if (this.information.get(i)) {
                return false;
            }
            i++;
        }
        return true;
    }

    private boolean isAlphaTo646ToAlphaLatch(int i) {
        int i2;
        if (i + 1 > this.information.size) {
            return false;
        }
        int i3 = 0;
        while (i3 < 5 && (i2 = i3 + i) < this.information.size) {
            if (i3 == 2) {
                if (!this.information.get(i + 2)) {
                    return false;
                }
            } else if (this.information.get(i2)) {
                return false;
            }
            i3++;
        }
        return true;
    }

    private boolean isNumericToAlphaNumericLatch(int i) {
        int i2;
        if (i + 1 > this.information.size) {
            return false;
        }
        int i3 = 0;
        while (i3 < 4 && (i2 = i3 + i) < this.information.size) {
            if (this.information.get(i2)) {
                return false;
            }
            i3++;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0020, code lost:
        r6 = extractNumericValueFromBitArray(r6, 6);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isStillAlpha(int r6) {
        /*
            r5 = this;
            int r0 = r6 + 5
            com.google.zxing.common.BitArray r1 = r5.information
            int r1 = r1.size
            r2 = 0
            if (r0 <= r1) goto L_0x000a
            return r2
        L_0x000a:
            r0 = 5
            int r1 = r5.extractNumericValueFromBitArray(r6, r0)
            r3 = 1
            r4 = 16
            if (r1 < r0) goto L_0x0017
            if (r1 >= r4) goto L_0x0017
            return r3
        L_0x0017:
            int r0 = r6 + 6
            com.google.zxing.common.BitArray r1 = r5.information
            int r1 = r1.size
            if (r0 <= r1) goto L_0x0020
            return r2
        L_0x0020:
            r0 = 6
            int r6 = r5.extractNumericValueFromBitArray(r6, r0)
            if (r6 < r4) goto L_0x002c
            r0 = 63
            if (r6 >= r0) goto L_0x002c
            r2 = 1
        L_0x002c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.expanded.decoders.GeneralAppIdDecoder.isStillAlpha(int):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0037, code lost:
        r5 = extractNumericValueFromBitArray(r5, 8);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isStillIsoIec646(int r5) {
        /*
            r4 = this;
            int r0 = r5 + 5
            com.google.zxing.common.BitArray r1 = r4.information
            int r1 = r1.size
            r2 = 0
            if (r0 <= r1) goto L_0x000a
            return r2
        L_0x000a:
            r0 = 5
            int r1 = r4.extractNumericValueFromBitArray(r5, r0)
            r3 = 1
            if (r1 < r0) goto L_0x0017
            r0 = 16
            if (r1 >= r0) goto L_0x0017
            return r3
        L_0x0017:
            int r0 = r5 + 7
            com.google.zxing.common.BitArray r1 = r4.information
            int r1 = r1.size
            if (r0 <= r1) goto L_0x0020
            return r2
        L_0x0020:
            r0 = 7
            int r0 = r4.extractNumericValueFromBitArray(r5, r0)
            r1 = 64
            if (r0 < r1) goto L_0x002e
            r1 = 116(0x74, float:1.63E-43)
            if (r0 >= r1) goto L_0x002e
            return r3
        L_0x002e:
            int r0 = r5 + 8
            com.google.zxing.common.BitArray r1 = r4.information
            int r1 = r1.size
            if (r0 <= r1) goto L_0x0037
            return r2
        L_0x0037:
            r0 = 8
            int r5 = r4.extractNumericValueFromBitArray(r5, r0)
            r0 = 232(0xe8, float:3.25E-43)
            if (r5 < r0) goto L_0x0046
            r0 = 253(0xfd, float:3.55E-43)
            if (r5 >= r0) goto L_0x0046
            r2 = 1
        L_0x0046:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.expanded.decoders.GeneralAppIdDecoder.isStillIsoIec646(int):boolean");
    }

    private boolean isStillNumeric(int i) {
        if (i + 7 > this.information.size) {
            return i + 4 <= this.information.size;
        }
        int i2 = i;
        while (true) {
            int i3 = i + 3;
            if (i2 >= i3) {
                return this.information.get(i3);
            }
            if (this.information.get(i2)) {
                return true;
            }
            i2++;
        }
    }

    private BlockParsedResult parseAlphaBlock() {
        int i;
        CurrentParsingState currentParsingState;
        while (isStillAlpha(this.current.position)) {
            DecodedChar decodeAlphanumeric = decodeAlphanumeric(this.current.position);
            this.current.position = decodeAlphanumeric.getNewPosition();
            if (decodeAlphanumeric.isFNC1()) {
                return new BlockParsedResult(new DecodedInformation(this.current.position, this.buffer.toString()), true);
            }
            this.buffer.append(decodeAlphanumeric.getValue());
        }
        if (isAlphaOr646ToNumericLatch(this.current.position)) {
            this.current.position += 3;
            this.current.setNumeric();
        } else if (isAlphaTo646ToAlphaLatch(this.current.position)) {
            if (this.current.position + 5 < this.information.size) {
                currentParsingState = this.current;
                i = currentParsingState.position + 5;
            } else {
                currentParsingState = this.current;
                i = this.information.size;
            }
            currentParsingState.position = i;
            this.current.setIsoIec646();
        }
        return new BlockParsedResult(false);
    }

    private DecodedInformation parseBlocks() {
        BlockParsedResult parseAlphaBlock;
        boolean isFinished;
        do {
            int i = this.current.position;
            parseAlphaBlock = this.current.isAlpha() ? parseAlphaBlock() : this.current.isIsoIec646() ? parseIsoIec646Block() : parseNumericBlock();
            isFinished = parseAlphaBlock.isFinished();
            if (!(i != this.current.position) && !isFinished) {
                break;
            }
        } while (!isFinished);
        return parseAlphaBlock.getDecodedInformation();
    }

    private BlockParsedResult parseIsoIec646Block() {
        int i;
        CurrentParsingState currentParsingState;
        while (isStillIsoIec646(this.current.position)) {
            DecodedChar decodeIsoIec646 = decodeIsoIec646(this.current.position);
            this.current.position = decodeIsoIec646.getNewPosition();
            if (decodeIsoIec646.isFNC1()) {
                return new BlockParsedResult(new DecodedInformation(this.current.position, this.buffer.toString()), true);
            }
            this.buffer.append(decodeIsoIec646.getValue());
        }
        if (isAlphaOr646ToNumericLatch(this.current.position)) {
            this.current.position += 3;
            this.current.setNumeric();
        } else if (isAlphaTo646ToAlphaLatch(this.current.position)) {
            if (this.current.position + 5 < this.information.size) {
                currentParsingState = this.current;
                i = currentParsingState.position + 5;
            } else {
                currentParsingState = this.current;
                i = this.information.size;
            }
            currentParsingState.position = i;
            this.current.setAlpha();
        }
        return new BlockParsedResult(false);
    }

    private BlockParsedResult parseNumericBlock() {
        while (isStillNumeric(this.current.position)) {
            DecodedNumeric decodeNumeric = decodeNumeric(this.current.position);
            this.current.position = decodeNumeric.getNewPosition();
            if (decodeNumeric.isFirstDigitFNC1()) {
                return new BlockParsedResult(decodeNumeric.isSecondDigitFNC1() ? new DecodedInformation(this.current.position, this.buffer.toString()) : new DecodedInformation(this.current.position, this.buffer.toString(), decodeNumeric.getSecondDigit()), true);
            }
            this.buffer.append(decodeNumeric.getFirstDigit());
            if (decodeNumeric.isSecondDigitFNC1()) {
                return new BlockParsedResult(new DecodedInformation(this.current.position, this.buffer.toString()), true);
            }
            this.buffer.append(decodeNumeric.getSecondDigit());
        }
        if (isNumericToAlphaNumericLatch(this.current.position)) {
            this.current.setAlpha();
            this.current.position += 4;
        }
        return new BlockParsedResult(false);
    }

    /* access modifiers changed from: package-private */
    public String decodeAllCodes(StringBuffer stringBuffer, int i) throws NotFoundException {
        String str = null;
        while (true) {
            DecodedInformation decodeGeneralPurposeField = decodeGeneralPurposeField(i, str);
            stringBuffer.append(FieldParser.parseFieldsInGeneralPurpose(decodeGeneralPurposeField.getNewString()));
            String valueOf = decodeGeneralPurposeField.isRemaining() ? String.valueOf(decodeGeneralPurposeField.getRemainingValue()) : null;
            if (i == decodeGeneralPurposeField.getNewPosition()) {
                return stringBuffer.toString();
            }
            i = decodeGeneralPurposeField.getNewPosition();
            str = valueOf;
        }
    }

    /* access modifiers changed from: package-private */
    public DecodedInformation decodeGeneralPurposeField(int i, String str) {
        this.buffer.setLength(0);
        if (str != null) {
            this.buffer.append(str);
        }
        this.current.position = i;
        DecodedInformation parseBlocks = parseBlocks();
        return (parseBlocks == null || !parseBlocks.isRemaining()) ? new DecodedInformation(this.current.position, this.buffer.toString()) : new DecodedInformation(this.current.position, this.buffer.toString(), parseBlocks.getRemainingValue());
    }

    /* access modifiers changed from: package-private */
    public int extractNumericValueFromBitArray(int i, int i2) {
        return extractNumericValueFromBitArray(this.information, i, i2);
    }
}
