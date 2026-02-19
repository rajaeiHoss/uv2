package com.google.zxing.common.reedsolomon;

public final class ReedSolomonDecoder {
    private final GF256 field;

    public ReedSolomonDecoder(GF256 gf256) {
        this.field = gf256;
    }

    private int[] findErrorLocations(GF256Poly gF256Poly) throws ReedSolomonException {
        int degree = gF256Poly.getDegree();
        int i = 0;
        if (degree == 1) {
            return new int[]{gF256Poly.getCoefficient(1)};
        }
        int[] iArr = new int[degree];
        for (int i2 = 1; i2 < 256 && i < degree; i2++) {
            if (gF256Poly.evaluateAt(i2) == 0) {
                iArr[i] = this.field.inverse(i2);
                i++;
            }
        }
        if (i == degree) {
            return iArr;
        }
        throw new ReedSolomonException("Error locator degree does not match number of roots");
    }

    private int[] findErrorMagnitudes(GF256Poly gF256Poly, int[] iArr, boolean z) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i = 0; i < length; i++) {
            int inverse = this.field.inverse(iArr[i]);
            int i2 = 1;
            for (int i3 = 0; i3 < length; i3++) {
                if (i != i3) {
                    int multiply = this.field.multiply(iArr[i3], inverse);
                    i2 = this.field.multiply(i2, (multiply & 1) == 0 ? multiply | 1 : multiply & -2);
                }
            }
            iArr2[i] = this.field.multiply(gF256Poly.evaluateAt(inverse), this.field.inverse(i2));
            if (z) {
                iArr2[i] = this.field.multiply(iArr2[i], inverse);
            }
        }
        return iArr2;
    }

    private GF256Poly[] runEuclideanAlgorithm(GF256Poly gF256Poly, GF256Poly gF256Poly2, int i) throws ReedSolomonException {
        if (gF256Poly.getDegree() < gF256Poly2.getDegree()) {
            GF256Poly gF256Poly3 = gF256Poly2;
            gF256Poly2 = gF256Poly;
            gF256Poly = gF256Poly3;
        }
        GF256Poly one = this.field.getOne();
        GF256Poly zero = this.field.getZero();
        GF256Poly zero2 = this.field.getZero();
        GF256Poly gF256Poly4 = gF256Poly2;
        GF256Poly gF256Poly5 = gF256Poly;
        GF256Poly gF256Poly6 = gF256Poly4;
        GF256Poly gF256Poly7 = zero2;
        GF256Poly one2 = this.field.getOne();
        while (gF256Poly6.getDegree() >= i / 2) {
            if (!gF256Poly6.isZero()) {
                GF256Poly zero3 = this.field.getZero();
                int inverse = this.field.inverse(gF256Poly6.getCoefficient(gF256Poly6.getDegree()));
                while (gF256Poly5.getDegree() >= gF256Poly6.getDegree() && !gF256Poly5.isZero()) {
                    int degree = gF256Poly5.getDegree() - gF256Poly6.getDegree();
                    int multiply = this.field.multiply(gF256Poly5.getCoefficient(gF256Poly5.getDegree()), inverse);
                    zero3 = zero3.addOrSubtract(this.field.buildMonomial(degree, multiply));
                    gF256Poly5 = gF256Poly5.addOrSubtract(gF256Poly6.multiplyByMonomial(degree, multiply));
                }
                GF256Poly gF256Poly8 = gF256Poly5;
                gF256Poly5 = gF256Poly6;
                gF256Poly6 = gF256Poly8;
                GF256Poly gF256Poly9 = zero;
                zero = zero3.multiply(zero).addOrSubtract(one);
                one = gF256Poly9;
                GF256Poly addOrSubtract = zero3.multiply(one2).addOrSubtract(gF256Poly7);
                gF256Poly7 = one2;
                one2 = addOrSubtract;
            } else {
                throw new ReedSolomonException("r_{i-1} was zero");
            }
        }
        int coefficient = one2.getCoefficient(0);
        if (coefficient != 0) {
            int inverse2 = this.field.inverse(coefficient);
            return new GF256Poly[]{one2.multiply(inverse2), gF256Poly6.multiply(inverse2)};
        }
        throw new ReedSolomonException("sigmaTilde(0) was zero");
    }

    public void decode(int[] iArr, int i) throws ReedSolomonException {
        GF256Poly gF256Poly = new GF256Poly(this.field, iArr);
        int[] iArr2 = new int[i];
        boolean equals = this.field.equals(GF256.DATA_MATRIX_FIELD);
        int i2 = 0;
        boolean z = true;
        for (int i3 = 0; i3 < i; i3++) {
            int evaluateAt = gF256Poly.evaluateAt(this.field.exp(equals ? i3 + 1 : i3));
            iArr2[(i - 1) - i3] = evaluateAt;
            if (evaluateAt != 0) {
                z = false;
            }
        }
        if (!z) {
            GF256Poly[] runEuclideanAlgorithm = runEuclideanAlgorithm(this.field.buildMonomial(i, 1), new GF256Poly(this.field, iArr2), i);
            GF256Poly gF256Poly2 = runEuclideanAlgorithm[0];
            GF256Poly gF256Poly3 = runEuclideanAlgorithm[1];
            int[] findErrorLocations = findErrorLocations(gF256Poly2);
            int[] findErrorMagnitudes = findErrorMagnitudes(gF256Poly3, findErrorLocations, equals);
            while (i2 < findErrorLocations.length) {
                int length = (iArr.length - 1) - this.field.log(findErrorLocations[i2]);
                if (length >= 0) {
                    iArr[length] = GF256.addOrSubtract(iArr[length], findErrorMagnitudes[i2]);
                    i2++;
                } else {
                    throw new ReedSolomonException("Bad error location");
                }
            }
        }
    }
}
