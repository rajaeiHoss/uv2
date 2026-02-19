package com.google.zxing.common.reedsolomon;

import java.util.Vector;

public final class ReedSolomonEncoder {
    private final Vector cachedGenerators;
    private final GF256 field;

    public ReedSolomonEncoder(GF256 gf256) {
        if (GF256.QR_CODE_FIELD.equals(gf256)) {
            this.field = gf256;
            Vector vector = new Vector();
            this.cachedGenerators = vector;
            vector.addElement(new GF256Poly(gf256, new int[]{1}));
            return;
        }
        throw new IllegalArgumentException("Only QR Code is supported at this time");
    }

    private GF256Poly buildGenerator(int i) {
        if (i >= this.cachedGenerators.size()) {
            Vector vector = this.cachedGenerators;
            GF256Poly gF256Poly = (GF256Poly) vector.elementAt(vector.size() - 1);
            for (int size = this.cachedGenerators.size(); size <= i; size++) {
                GF256 gf256 = this.field;
                gF256Poly = gF256Poly.multiply(new GF256Poly(gf256, new int[]{1, gf256.exp(size - 1)}));
                this.cachedGenerators.addElement(gF256Poly);
            }
        }
        return (GF256Poly) this.cachedGenerators.elementAt(i);
    }

    public void encode(int[] iArr, int i) {
        if (i != 0) {
            int length = iArr.length - i;
            if (length > 0) {
                GF256Poly buildGenerator = buildGenerator(i);
                int[] iArr2 = new int[length];
                System.arraycopy(iArr, 0, iArr2, 0, length);
                int[] coefficients = new GF256Poly(this.field, iArr2).multiplyByMonomial(i, 1).divide(buildGenerator)[1].getCoefficients();
                int length2 = i - coefficients.length;
                for (int i2 = 0; i2 < length2; i2++) {
                    iArr[length + i2] = 0;
                }
                System.arraycopy(coefficients, 0, iArr, length + length2, coefficients.length);
                return;
            }
            throw new IllegalArgumentException("No data bytes provided");
        }
        throw new IllegalArgumentException("No error correction bytes");
    }
}
