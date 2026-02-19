package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.PerspectiveTransform;
import com.google.zxing.qrcode.decoder.Version;
import java.util.Hashtable;

public class Detector {
    private final BitMatrix image;
    private ResultPointCallback resultPointCallback;

    public Detector(BitMatrix bitMatrix) {
        this.image = bitMatrix;
    }

    private float calculateModuleSizeOneWay(ResultPoint resultPoint, ResultPoint resultPoint2) {
        float sizeOfBlackWhiteBlackRunBothWays = sizeOfBlackWhiteBlackRunBothWays((int) resultPoint.getX(), (int) resultPoint.getY(), (int) resultPoint2.getX(), (int) resultPoint2.getY());
        float sizeOfBlackWhiteBlackRunBothWays2 = sizeOfBlackWhiteBlackRunBothWays((int) resultPoint2.getX(), (int) resultPoint2.getY(), (int) resultPoint.getX(), (int) resultPoint.getY());
        return Float.isNaN(sizeOfBlackWhiteBlackRunBothWays) ? sizeOfBlackWhiteBlackRunBothWays2 / 7.0f : Float.isNaN(sizeOfBlackWhiteBlackRunBothWays2) ? sizeOfBlackWhiteBlackRunBothWays / 7.0f : (sizeOfBlackWhiteBlackRunBothWays + sizeOfBlackWhiteBlackRunBothWays2) / 14.0f;
    }

    protected static int computeDimension(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, float f) throws NotFoundException {
        int round = ((round(ResultPoint.distance(resultPoint, resultPoint2) / f) + round(ResultPoint.distance(resultPoint, resultPoint3) / f)) >> 1) + 7;
        int i = round & 3;
        if (i == 0) {
            return round + 1;
        }
        if (i == 2) {
            return round - 1;
        }
        if (i != 3) {
            return round;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int round(float f) {
        return (int) (f + 0.5f);
    }

    private static BitMatrix sampleGrid(BitMatrix bitMatrix, PerspectiveTransform perspectiveTransform, int i) throws NotFoundException {
        return GridSampler.getInstance().sampleGrid(bitMatrix, i, perspectiveTransform);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005f, code lost:
        if (r0.image.get(r17, r1) != false) goto L_0x006e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006c, code lost:
        if (r0.image.get(r17, r1) == false) goto L_0x006e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x009e, code lost:
        r1 = (double) ((r8 * r8) + (r10 * r10));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private float sizeOfBlackWhiteBlackRun(int r20, int r21, int r22, int r23) {
        /*
            r19 = this;
            r0 = r19
            int r1 = r23 - r21
            int r1 = java.lang.Math.abs(r1)
            int r2 = r22 - r20
            int r2 = java.lang.Math.abs(r2)
            r3 = 0
            r4 = 1
            if (r1 <= r2) goto L_0x0014
            r1 = 1
            goto L_0x0015
        L_0x0014:
            r1 = 0
        L_0x0015:
            if (r1 == 0) goto L_0x0020
            r5 = r20
            r2 = r21
            r7 = r22
            r6 = r23
            goto L_0x0028
        L_0x0020:
            r2 = r20
            r5 = r21
            r6 = r22
            r7 = r23
        L_0x0028:
            int r8 = r6 - r2
            int r9 = java.lang.Math.abs(r8)
            int r10 = r7 - r5
            int r11 = java.lang.Math.abs(r10)
            int r12 = -r9
            int r12 = r12 >> r4
            r13 = -1
            if (r5 >= r7) goto L_0x003b
            r14 = 1
            goto L_0x003c
        L_0x003b:
            r14 = -1
        L_0x003c:
            if (r2 >= r6) goto L_0x003f
            r13 = 1
        L_0x003f:
            r15 = r2
            r16 = r5
        L_0x0042:
            if (r15 == r6) goto L_0x009e
            if (r1 == 0) goto L_0x0049
            r17 = r16
            goto L_0x004b
        L_0x0049:
            r17 = r15
        L_0x004b:
            r18 = r1
            if (r1 == 0) goto L_0x0051
            r1 = r15
            goto L_0x0053
        L_0x0051:
            r1 = r16
        L_0x0053:
            if (r3 != r4) goto L_0x0062
            com.google.zxing.common.BitMatrix r4 = r0.image
            r20 = r6
            r6 = r17
            boolean r1 = r4.get(r6, r1)
            if (r1 == 0) goto L_0x0070
            goto L_0x006e
        L_0x0062:
            r20 = r6
            r6 = r17
            com.google.zxing.common.BitMatrix r4 = r0.image
            boolean r1 = r4.get(r6, r1)
            if (r1 != 0) goto L_0x0070
        L_0x006e:
            int r3 = r3 + 1
        L_0x0070:
            r1 = 3
            if (r3 != r1) goto L_0x0089
            int r15 = r15 - r2
            r1 = r16
            int r16 = r1 - r5
            if (r13 >= 0) goto L_0x007c
            int r15 = r15 + 1
        L_0x007c:
            int r15 = r15 * r15
            int r16 = r16 * r16
            int r15 = r15 + r16
            double r1 = (double) r15
        L_0x0083:
            double r1 = java.lang.Math.sqrt(r1)
            float r1 = (float) r1
            return r1
        L_0x0089:
            r1 = r16
            int r12 = r12 + r11
            if (r12 <= 0) goto L_0x0095
            if (r1 != r7) goto L_0x0091
            goto L_0x009e
        L_0x0091:
            int r16 = r1 + r14
            int r12 = r12 - r9
            goto L_0x0097
        L_0x0095:
            r16 = r1
        L_0x0097:
            int r15 = r15 + r13
            r6 = r20
            r1 = r18
            r4 = 1
            goto L_0x0042
        L_0x009e:
            int r8 = r8 * r8
            int r10 = r10 * r10
            int r8 = r8 + r10
            double r1 = (double) r8
            goto L_0x0083
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.detector.Detector.sizeOfBlackWhiteBlackRun(int, int, int, int):float");
    }

    private float sizeOfBlackWhiteBlackRunBothWays(int i, int i2, int i3, int i4) {
        float f;
        float sizeOfBlackWhiteBlackRun = sizeOfBlackWhiteBlackRun(i, i2, i3, i4);
        int i5 = i - (i3 - i);
        int i6 = 0;
        float f2 = 1.0f;
        if (i5 < 0) {
            f = ((float) i) / ((float) (i - i5));
            i5 = 0;
        } else if (i5 > this.image.getWidth()) {
            f = ((float) (this.image.getWidth() - i)) / ((float) (i5 - i));
            i5 = this.image.getWidth();
        } else {
            f = 1.0f;
        }
        float f3 = (float) i2;
        int i7 = (int) (f3 - (((float) (i4 - i2)) * f));
        if (i7 < 0) {
            f2 = f3 / ((float) (i2 - i7));
        } else if (i7 > this.image.getHeight()) {
            f2 = ((float) (this.image.getHeight() - i2)) / ((float) (i7 - i2));
            i6 = this.image.getHeight();
        } else {
            i6 = i7;
        }
        return sizeOfBlackWhiteBlackRun + sizeOfBlackWhiteBlackRun(i, i2, (int) (((float) i) + (((float) (i5 - i)) * f2)), i6);
    }

    /* access modifiers changed from: protected */
    public float calculateModuleSize(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3) {
        return (calculateModuleSizeOneWay(resultPoint, resultPoint2) + calculateModuleSizeOneWay(resultPoint, resultPoint3)) / 2.0f;
    }

    public PerspectiveTransform createTransform(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i) {
        float f;
        float f2;
        float f3;
        float f4 = ((float) i) - 3.5f;
        if (resultPoint4 != null) {
            f2 = resultPoint4.getX();
            f = resultPoint4.getY();
            f3 = f4 - 3.0f;
        } else {
            f2 = (resultPoint2.getX() - resultPoint.getX()) + resultPoint3.getX();
            f = (resultPoint2.getY() - resultPoint.getY()) + resultPoint3.getY();
            f3 = f4;
        }
        return PerspectiveTransform.quadrilateralToQuadrilateral(3.5f, 3.5f, f4, 3.5f, f3, f3, 3.5f, f4, resultPoint.getX(), resultPoint.getY(), resultPoint2.getX(), resultPoint2.getY(), f2, f, resultPoint3.getX(), resultPoint3.getY());
    }

    public DetectorResult detect() throws NotFoundException, FormatException {
        return detect((Hashtable) null);
    }

    public DetectorResult detect(Hashtable hashtable) throws NotFoundException, FormatException {
        ResultPointCallback resultPointCallback2 = hashtable == null ? null : (ResultPointCallback) hashtable.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        this.resultPointCallback = resultPointCallback2;
        return processFinderPatternInfo(new FinderPatternFinder(this.image, resultPointCallback2).find(hashtable));
    }

    /* access modifiers changed from: protected */
    public AlignmentPattern findAlignmentInRegion(float f, int i, int i2, float f2) throws NotFoundException {
        int i3 = (int) (f2 * f);
        int max = Math.max(0, i - i3);
        int min = Math.min(this.image.getWidth() - 1, i + i3) - max;
        float f3 = 3.0f * f;
        if (((float) min) >= f3) {
            int max2 = Math.max(0, i2 - i3);
            int min2 = Math.min(this.image.getHeight() - 1, i2 + i3) - max2;
            if (((float) min2) >= f3) {
                return new AlignmentPatternFinder(this.image, max, max2, min, min2, f, this.resultPointCallback).find();
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* access modifiers changed from: protected */
    public BitMatrix getImage() {
        return this.image;
    }

    /* access modifiers changed from: protected */
    public ResultPointCallback getResultPointCallback() {
        return this.resultPointCallback;
    }

    /* access modifiers changed from: protected */
    public DetectorResult processFinderPatternInfo(FinderPatternInfo finderPatternInfo) throws NotFoundException, FormatException {
        ResultPoint[] resultPointArr;
        FinderPattern topLeft = finderPatternInfo.getTopLeft();
        FinderPattern topRight = finderPatternInfo.getTopRight();
        FinderPattern bottomLeft = finderPatternInfo.getBottomLeft();
        float calculateModuleSize = calculateModuleSize(topLeft, topRight, bottomLeft);
        if (calculateModuleSize >= 1.0f) {
            int computeDimension = computeDimension(topLeft, topRight, bottomLeft, calculateModuleSize);
            Version provisionalVersionForDimension = Version.getProvisionalVersionForDimension(computeDimension);
            int dimensionForVersion = provisionalVersionForDimension.getDimensionForVersion() - 7;
            AlignmentPattern alignmentPattern = null;
            if (provisionalVersionForDimension.getAlignmentPatternCenters().length > 0) {
                float x = (topRight.getX() - topLeft.getX()) + bottomLeft.getX();
                float y = (topRight.getY() - topLeft.getY()) + bottomLeft.getY();
                float f = 1.0f - (3.0f / ((float) dimensionForVersion));
                int x2 = (int) (topLeft.getX() + ((x - topLeft.getX()) * f));
                int y2 = (int) (topLeft.getY() + (f * (y - topLeft.getY())));
                int i = 4;
                while (true) {
                    if (i > 16) {
                        break;
                    }
                    try {
                        alignmentPattern = findAlignmentInRegion(calculateModuleSize, x2, y2, (float) i);
                        break;
                    } catch (NotFoundException unused) {
                        i <<= 1;
                    }
                }
            }
            AlignmentPattern alignmentPattern2 = alignmentPattern;
            BitMatrix sampleGrid = sampleGrid(this.image, createTransform(topLeft, topRight, bottomLeft, alignmentPattern2, computeDimension), computeDimension);
            if (alignmentPattern2 == null) {
                resultPointArr = new ResultPoint[]{bottomLeft, topLeft, topRight};
            } else {
                resultPointArr = new ResultPoint[]{bottomLeft, topLeft, topRight, alignmentPattern2};
            }
            return new DetectorResult(sampleGrid, resultPointArr);
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
