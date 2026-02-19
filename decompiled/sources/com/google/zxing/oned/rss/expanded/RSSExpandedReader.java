package com.google.zxing.oned.rss.expanded;

import androidx.media2.subtitle.Cea708CCParser;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import com.google.zxing.oned.rss.AbstractRSSReader;
import com.google.zxing.oned.rss.DataCharacter;
import com.google.zxing.oned.rss.FinderPattern;
import com.google.zxing.oned.rss.RSSUtils;
import com.google.zxing.oned.rss.expanded.decoders.AbstractExpandedDecoder;
import java.util.Hashtable;
import java.util.Vector;
import org.kxml2.wap.Wbxml;

public final class RSSExpandedReader extends AbstractRSSReader {
    private static final int[] EVEN_TOTAL_SUBSET = {4, 20, 52, 104, 204};
    private static final int[][] FINDER_PATTERNS = {new int[]{1, 8, 4, 1}, new int[]{3, 6, 4, 1}, new int[]{3, 4, 6, 1}, new int[]{3, 2, 8, 1}, new int[]{2, 6, 5, 1}, new int[]{2, 2, 9, 1}};
    private static final int[][] FINDER_PATTERN_SEQUENCES;
    private static final int FINDER_PAT_A = 0;
    private static final int FINDER_PAT_B = 1;
    private static final int FINDER_PAT_C = 2;
    private static final int FINDER_PAT_D = 3;
    private static final int FINDER_PAT_E = 4;
    private static final int FINDER_PAT_F = 5;
    private static final int[] GSUM = {0, 348, 1388, 2948, 3988};
    private static final int LONGEST_SEQUENCE_SIZE;
    private static final int MAX_PAIRS = 11;
    private static final int[] SYMBOL_WIDEST = {7, 5, 4, 3, 1};
    private static final int[][] WEIGHTS = {new int[]{1, 3, 9, 27, 81, 32, 96, 77}, new int[]{20, 60, 180, 118, 143, 7, 21, 63}, new int[]{189, Cea708CCParser.Const.CODE_C1_SPC, 13, 39, 117, 140, 209, 205}, new int[]{Wbxml.EXT_1, Cea708CCParser.Const.CODE_C1_DF5, 49, 147, 19, 57, 171, 91}, new int[]{62, 186, 136, 197, 169, 85, 44, 132}, new int[]{185, 133, 188, 142, 4, 12, 36, 108}, new int[]{113, 128, 173, 97, 80, 29, 87, 50}, new int[]{150, 28, 84, 41, 123, Cea708CCParser.Const.CODE_C1_DF6, 52, Cea708CCParser.Const.CODE_C1_DF4}, new int[]{46, 138, 203, 187, 139, 206, Wbxml.LITERAL_AC, 166}, new int[]{76, 17, 51, Cea708CCParser.Const.CODE_C1_DF1, 37, 111, 122, Cea708CCParser.Const.CODE_C1_DF3}, new int[]{43, 129, 176, 106, 107, 110, 119, Cea708CCParser.Const.CODE_C1_SPL}, new int[]{16, 48, Cea708CCParser.Const.CODE_C1_SPA, 10, 30, 90, 59, 177}, new int[]{109, 116, 137, 200, 178, 112, 125, 164}, new int[]{70, 210, 208, 202, 184, 130, 179, 115}, new int[]{134, 191, Cea708CCParser.Const.CODE_C1_SWA, 31, 93, 68, 204, 190}, new int[]{148, 22, 66, 198, 172, 94, 71, 2}, new int[]{6, 18, 54, 162, 64, Wbxml.EXT_0, Cea708CCParser.Const.CODE_C1_DF2, 40}, new int[]{120, 149, 25, 75, 14, 42, 126, 167}, new int[]{79, 26, 78, 23, 69, 207, 199, 175}, new int[]{103, 98, 83, 38, 114, 131, 182, 124}, new int[]{161, 61, 183, 127, 170, 88, 53, 159}, new int[]{55, 165, 73, 8, 24, 72, 5, 15}, new int[]{45, 135, Wbxml.EXT_2, 160, 58, 174, 100, 89}};
    private final int[] currentSequence = new int[LONGEST_SEQUENCE_SIZE];
    private final Vector pairs = new Vector(11);
    private final int[] startEnd = new int[2];

    static {
        int[][] iArr = {new int[]{0, 0}, new int[]{0, 1, 1}, new int[]{0, 2, 1, 3}, new int[]{0, 4, 1, 3, 2}, new int[]{0, 4, 1, 3, 3, 5}, new int[]{0, 4, 1, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 2, 3, 3}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 4}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 5}};
        FINDER_PATTERN_SEQUENCES = iArr;
        LONGEST_SEQUENCE_SIZE = iArr[iArr.length - 1].length;
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:70:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void adjustOddEvenCounts(int r11) throws com.google.zxing.NotFoundException {
        /*
            r10 = this;
            int[] r0 = r10.oddCounts
            int r0 = count(r0)
            int[] r1 = r10.evenCounts
            int r1 = count(r1)
            int r2 = r0 + r1
            int r2 = r2 - r11
            r11 = r0 & 1
            r3 = 0
            r4 = 1
            if (r11 != r4) goto L_0x0017
            r11 = 1
            goto L_0x0018
        L_0x0017:
            r11 = 0
        L_0x0018:
            r5 = r1 & 1
            if (r5 != 0) goto L_0x001e
            r5 = 1
            goto L_0x001f
        L_0x001e:
            r5 = 0
        L_0x001f:
            r6 = 4
            r7 = 13
            if (r0 <= r7) goto L_0x0027
            r8 = 0
            r9 = 1
            goto L_0x002d
        L_0x0027:
            if (r0 >= r6) goto L_0x002b
            r8 = 1
            goto L_0x002c
        L_0x002b:
            r8 = 0
        L_0x002c:
            r9 = 0
        L_0x002d:
            if (r1 <= r7) goto L_0x0031
            r6 = 1
            goto L_0x0035
        L_0x0031:
            if (r1 >= r6) goto L_0x0034
            r3 = 1
        L_0x0034:
            r6 = 0
        L_0x0035:
            if (r2 != r4) goto L_0x004d
            if (r11 == 0) goto L_0x0043
            if (r5 != 0) goto L_0x003e
            r4 = r8
        L_0x003c:
            r9 = 1
            goto L_0x0078
        L_0x003e:
            com.google.zxing.NotFoundException r11 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x0043:
            if (r5 == 0) goto L_0x0048
            r4 = r8
        L_0x0046:
            r6 = 1
            goto L_0x0078
        L_0x0048:
            com.google.zxing.NotFoundException r11 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x004d:
            r7 = -1
            if (r2 != r7) goto L_0x0064
            if (r11 == 0) goto L_0x005a
            if (r5 != 0) goto L_0x0055
            goto L_0x0078
        L_0x0055:
            com.google.zxing.NotFoundException r11 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x005a:
            if (r5 == 0) goto L_0x005f
            r4 = r8
            r3 = 1
            goto L_0x0078
        L_0x005f:
            com.google.zxing.NotFoundException r11 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x0064:
            if (r2 != 0) goto L_0x00b2
            if (r11 == 0) goto L_0x0075
            if (r5 == 0) goto L_0x0070
            if (r0 >= r1) goto L_0x006d
            goto L_0x0046
        L_0x006d:
            r4 = r8
            r3 = 1
            goto L_0x003c
        L_0x0070:
            com.google.zxing.NotFoundException r11 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x0075:
            if (r5 != 0) goto L_0x00ad
            r4 = r8
        L_0x0078:
            if (r4 == 0) goto L_0x0089
            if (r9 != 0) goto L_0x0084
            int[] r11 = r10.oddCounts
            float[] r0 = r10.oddRoundingErrors
            increment(r11, r0)
            goto L_0x0089
        L_0x0084:
            com.google.zxing.NotFoundException r11 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x0089:
            if (r9 == 0) goto L_0x0092
            int[] r11 = r10.oddCounts
            float[] r0 = r10.oddRoundingErrors
            decrement(r11, r0)
        L_0x0092:
            if (r3 == 0) goto L_0x00a3
            if (r6 != 0) goto L_0x009e
            int[] r11 = r10.evenCounts
            float[] r0 = r10.oddRoundingErrors
            increment(r11, r0)
            goto L_0x00a3
        L_0x009e:
            com.google.zxing.NotFoundException r11 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x00a3:
            if (r6 == 0) goto L_0x00ac
            int[] r11 = r10.evenCounts
            float[] r0 = r10.evenRoundingErrors
            decrement(r11, r0)
        L_0x00ac:
            return
        L_0x00ad:
            com.google.zxing.NotFoundException r11 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        L_0x00b2:
            com.google.zxing.NotFoundException r11 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.expanded.RSSExpandedReader.adjustOddEvenCounts(int):void");
    }

    private boolean checkChecksum() {
        ExpandedPair expandedPair = (ExpandedPair) this.pairs.elementAt(0);
        DataCharacter leftChar = expandedPair.getLeftChar();
        int checksumPortion = expandedPair.getRightChar().getChecksumPortion();
        int i = 2;
        for (int i2 = 1; i2 < this.pairs.size(); i2++) {
            ExpandedPair expandedPair2 = (ExpandedPair) this.pairs.elementAt(i2);
            checksumPortion += expandedPair2.getLeftChar().getChecksumPortion();
            i++;
            if (expandedPair2.getRightChar() != null) {
                checksumPortion += expandedPair2.getRightChar().getChecksumPortion();
                i++;
            }
        }
        return ((i + -4) * 211) + (checksumPortion % 211) == leftChar.getValue();
    }

    private boolean checkPairSequence(Vector vector, FinderPattern finderPattern) throws NotFoundException {
        boolean z;
        int size = vector.size() + 1;
        if (size <= this.currentSequence.length) {
            for (int i = 0; i < vector.size(); i++) {
                this.currentSequence[i] = ((ExpandedPair) vector.elementAt(i)).getFinderPattern().getValue();
            }
            this.currentSequence[size - 1] = finderPattern.getValue();
            int i2 = 0;
            while (true) {
                int[][] iArr = FINDER_PATTERN_SEQUENCES;
                if (i2 < iArr.length) {
                    int[] iArr2 = iArr[i2];
                    if (iArr2.length >= size) {
                        int i3 = 0;
                        while (true) {
                            if (i3 >= size) {
                                z = true;
                                break;
                            } else if (this.currentSequence[i3] != iArr2[i3]) {
                                z = false;
                                break;
                            } else {
                                i3++;
                            }
                        }
                        if (z) {
                            return size == iArr2.length;
                        }
                    }
                    i2++;
                } else {
                    throw NotFoundException.getNotFoundInstance();
                }
            }
        } else {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private static Result constructResult(Vector vector) throws NotFoundException {
        String parseInformation = AbstractExpandedDecoder.createDecoder(BitArrayBuilder.buildBitArray(vector)).parseInformation();
        ResultPoint[] resultPoints = ((ExpandedPair) vector.elementAt(0)).getFinderPattern().getResultPoints();
        ResultPoint[] resultPoints2 = ((ExpandedPair) vector.lastElement()).getFinderPattern().getResultPoints();
        return new Result(parseInformation, (byte[]) null, new ResultPoint[]{resultPoints[0], resultPoints[1], resultPoints2[0], resultPoints2[1]}, BarcodeFormat.RSS_EXPANDED);
    }

    private void findNextPair(BitArray bitArray, Vector vector, int i) throws NotFoundException {
        int[] iArr = this.decodeFinderCounters;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        int size = bitArray.getSize();
        if (i < 0) {
            i = vector.isEmpty() ? 0 : ((ExpandedPair) vector.lastElement()).getFinderPattern().getStartEnd()[1];
        }
        boolean z = vector.size() % 2 != 0;
        boolean z2 = false;
        while (i < size) {
            z2 = !bitArray.get(i);
            if (!z2) {
                break;
            }
            i++;
        }
        boolean z3 = z2;
        int i2 = 0;
        int i3 = i;
        while (i < size) {
            if (bitArray.get(i) ^ z3) {
                iArr[i2] = iArr[i2] + 1;
            } else {
                if (i2 == 3) {
                    if (z) {
                        reverseCounters(iArr);
                    }
                    if (isFinderPattern(iArr)) {
                        int[] iArr2 = this.startEnd;
                        iArr2[0] = i3;
                        iArr2[1] = i;
                        return;
                    }
                    if (z) {
                        reverseCounters(iArr);
                    }
                    i3 += iArr[0] + iArr[1];
                    iArr[0] = iArr[2];
                    iArr[1] = iArr[3];
                    iArr[2] = 0;
                    iArr[3] = 0;
                    i2--;
                } else {
                    i2++;
                }
                iArr[i2] = 1;
                z3 = !z3;
            }
            i++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int getNextSecondBar(BitArray bitArray, int i) {
        boolean z = bitArray.get(i);
        while (i < bitArray.size && bitArray.get(i) == z) {
            i++;
        }
        boolean z2 = !z;
        while (i < bitArray.size && bitArray.get(i) == z2) {
            i++;
        }
        return i;
    }

    private static boolean isNotA1left(FinderPattern finderPattern, boolean z, boolean z2) {
        return finderPattern.getValue() != 0 || !z || !z2;
    }

    private FinderPattern parseFoundFinderPattern(BitArray bitArray, int i, boolean z) {
        int i2;
        int i3;
        int i4;
        if (z) {
            int i5 = this.startEnd[0] - 1;
            while (i5 >= 0 && !bitArray.get(i5)) {
                i5--;
            }
            int i6 = i5 + 1;
            int[] iArr = this.startEnd;
            i4 = iArr[0] - i6;
            i2 = iArr[1];
            i3 = i6;
        } else {
            int[] iArr2 = this.startEnd;
            int i7 = iArr2[0];
            int i8 = iArr2[1] + 1;
            while (bitArray.get(i8) && i8 < bitArray.size) {
                i8++;
            }
            i2 = i8;
            i3 = i7;
            i4 = i8 - this.startEnd[1];
        }
        int[] iArr3 = this.decodeFinderCounters;
        for (int length = iArr3.length - 1; length > 0; length--) {
            iArr3[length] = iArr3[length - 1];
        }
        iArr3[0] = i4;
        try {
            return new FinderPattern(parseFinderValue(iArr3, FINDER_PATTERNS), new int[]{i3, i2}, i3, i2, i);
        } catch (NotFoundException unused) {
            return null;
        }
    }

    private static void reverseCounters(int[] iArr) {
        int length = iArr.length;
        for (int i = 0; i < length / 2; i++) {
            int i2 = iArr[i];
            int i3 = (length - i) - 1;
            iArr[i] = iArr[i3];
            iArr[i3] = i2;
        }
    }

    /* access modifiers changed from: package-private */
    public DataCharacter decodeDataCharacter(BitArray bitArray, FinderPattern finderPattern, boolean z, boolean z2) throws NotFoundException {
        BitArray bitArray2 = bitArray;
        int[] iArr = this.dataCharacterCounters;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
        iArr[5] = 0;
        iArr[6] = 0;
        iArr[7] = 0;
        int[] startEnd2 = finderPattern.getStartEnd();
        if (z2) {
            recordPatternInReverse(bitArray2, startEnd2[0], iArr);
        } else {
            recordPattern(bitArray2, startEnd2[1] + 1, iArr);
            int i = 0;
            for (int length = iArr.length - 1; i < length; length--) {
                int i2 = iArr[i];
                iArr[i] = iArr[length];
                iArr[length] = i2;
                i++;
            }
        }
        float count = ((float) count(iArr)) / ((float) 17);
        int[] iArr2 = this.oddCounts;
        int[] iArr3 = this.evenCounts;
        float[] fArr = this.oddRoundingErrors;
        float[] fArr2 = this.evenRoundingErrors;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            float f = (((float) iArr[i3]) * 1.0f) / count;
            int i4 = (int) (0.5f + f);
            if (i4 < 1) {
                i4 = 1;
            } else if (i4 > 8) {
                i4 = 8;
            }
            int i5 = i3 >> 1;
            if ((i3 & 1) == 0) {
                iArr2[i5] = i4;
                fArr[i5] = f - ((float) i4);
            } else {
                iArr3[i5] = i4;
                fArr2[i5] = f - ((float) i4);
            }
        }
        adjustOddEvenCounts(17);
        int value = (((finderPattern.getValue() * 4) + (z ? 0 : 2)) + (z2 ^ true ? 1 : 0)) - 1;
        int i6 = 0;
        int i7 = 0;
        for (int length2 = iArr2.length - 1; length2 >= 0; length2--) {
            if (isNotA1left(finderPattern, z, z2)) {
                i6 += iArr2[length2] * WEIGHTS[value][length2 * 2];
            }
            i7 += iArr2[length2];
        }
        int i8 = 0;
        for (int length3 = iArr3.length - 1; length3 >= 0; length3--) {
            if (isNotA1left(finderPattern, z, z2)) {
                i8 += iArr3[length3] * WEIGHTS[value][(length3 * 2) + 1];
            }
            int i9 = iArr3[length3];
        }
        int i10 = i6 + i8;
        if ((i7 & 1) != 0 || i7 > 13 || i7 < 4) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i11 = (13 - i7) / 2;
        int i12 = SYMBOL_WIDEST[i11];
        return new DataCharacter((RSSUtils.getRSSvalue(iArr2, i12, true) * EVEN_TOTAL_SUBSET[i11]) + RSSUtils.getRSSvalue(iArr3, 9 - i12, false) + GSUM[i11], i10);
    }

    public Result decodeRow(int i, BitArray bitArray, Hashtable hashtable) throws NotFoundException {
        reset();
        decodeRow2pairs(i, bitArray);
        return constructResult(this.pairs);
    }

    /* access modifiers changed from: package-private */
    public Vector decodeRow2pairs(int i, BitArray bitArray) throws NotFoundException {
        while (true) {
            ExpandedPair retrieveNextPair = retrieveNextPair(bitArray, this.pairs, i);
            this.pairs.addElement(retrieveNextPair);
            if (retrieveNextPair.mayBeLast()) {
                if (checkChecksum()) {
                    return this.pairs;
                }
                if (retrieveNextPair.mustBeLast()) {
                    throw NotFoundException.getNotFoundInstance();
                }
            }
        }
    }

    public void reset() {
        this.pairs.setSize(0);
    }

    /* access modifiers changed from: package-private */
    public ExpandedPair retrieveNextPair(BitArray bitArray, Vector vector, int i) throws NotFoundException {
        FinderPattern parseFoundFinderPattern;
        DataCharacter dataCharacter;
        boolean z = vector.size() % 2 == 0;
        int i2 = -1;
        boolean z2 = true;
        do {
            findNextPair(bitArray, vector, i2);
            parseFoundFinderPattern = parseFoundFinderPattern(bitArray, i, z);
            if (parseFoundFinderPattern == null) {
                i2 = getNextSecondBar(bitArray, this.startEnd[0]);
                continue;
            } else {
                z2 = false;
                continue;
            }
        } while (z2);
        boolean checkPairSequence = checkPairSequence(vector, parseFoundFinderPattern);
        DataCharacter decodeDataCharacter = decodeDataCharacter(bitArray, parseFoundFinderPattern, z, true);
        try {
            dataCharacter = decodeDataCharacter(bitArray, parseFoundFinderPattern, z, false);
        } catch (NotFoundException e) {
            if (checkPairSequence) {
                dataCharacter = null;
            } else {
                throw e;
            }
        }
        return new ExpandedPair(decodeDataCharacter, dataCharacter, parseFoundFinderPattern, checkPairSequence);
    }
}
