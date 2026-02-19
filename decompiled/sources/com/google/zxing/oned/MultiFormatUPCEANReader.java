package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;
import java.util.Vector;

public final class MultiFormatUPCEANReader extends OneDReader {
    private final Vector readers;

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MultiFormatUPCEANReader(java.util.Hashtable r3) {
        /*
            r2 = this;
            r2.<init>()
            if (r3 != 0) goto L_0x0007
            r3 = 0
            goto L_0x000f
        L_0x0007:
            com.google.zxing.DecodeHintType r0 = com.google.zxing.DecodeHintType.POSSIBLE_FORMATS
            java.lang.Object r3 = r3.get(r0)
            java.util.Vector r3 = (java.util.Vector) r3
        L_0x000f:
            java.util.Vector r0 = new java.util.Vector
            r0.<init>()
            r2.readers = r0
            if (r3 == 0) goto L_0x0057
            com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.EAN_13
            boolean r1 = r3.contains(r1)
            if (r1 == 0) goto L_0x0029
            com.google.zxing.oned.EAN13Reader r1 = new com.google.zxing.oned.EAN13Reader
            r1.<init>()
        L_0x0025:
            r0.addElement(r1)
            goto L_0x0037
        L_0x0029:
            com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.UPC_A
            boolean r1 = r3.contains(r1)
            if (r1 == 0) goto L_0x0037
            com.google.zxing.oned.UPCAReader r1 = new com.google.zxing.oned.UPCAReader
            r1.<init>()
            goto L_0x0025
        L_0x0037:
            com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.EAN_8
            boolean r1 = r3.contains(r1)
            if (r1 == 0) goto L_0x0047
            com.google.zxing.oned.EAN8Reader r1 = new com.google.zxing.oned.EAN8Reader
            r1.<init>()
            r0.addElement(r1)
        L_0x0047:
            com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.UPC_E
            boolean r3 = r3.contains(r1)
            if (r3 == 0) goto L_0x0057
            com.google.zxing.oned.UPCEReader r3 = new com.google.zxing.oned.UPCEReader
            r3.<init>()
            r0.addElement(r3)
        L_0x0057:
            boolean r3 = r0.isEmpty()
            if (r3 == 0) goto L_0x0075
            com.google.zxing.oned.EAN13Reader r3 = new com.google.zxing.oned.EAN13Reader
            r3.<init>()
            r0.addElement(r3)
            com.google.zxing.oned.EAN8Reader r3 = new com.google.zxing.oned.EAN8Reader
            r3.<init>()
            r0.addElement(r3)
            com.google.zxing.oned.UPCEReader r3 = new com.google.zxing.oned.UPCEReader
            r3.<init>()
            r0.addElement(r3)
        L_0x0075:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.MultiFormatUPCEANReader.<init>(java.util.Hashtable):void");
    }

    public Result decodeRow(int i, BitArray bitArray, Hashtable hashtable) throws NotFoundException {
        int[] findStartGuardPattern = UPCEANReader.findStartGuardPattern(bitArray);
        boolean z = false;
        int i2 = 0;
        while (i2 < this.readers.size()) {
            try {
                Result decodeRow = ((UPCEANReader) this.readers.elementAt(i2)).decodeRow(i, bitArray, findStartGuardPattern, hashtable);
                boolean z2 = BarcodeFormat.EAN_13.equals(decodeRow.getBarcodeFormat()) && decodeRow.getText().charAt(0) == '0';
                Vector vector = hashtable == null ? null : (Vector) hashtable.get(DecodeHintType.POSSIBLE_FORMATS);
                if (vector == null || vector.contains(BarcodeFormat.UPC_A)) {
                    z = true;
                }
                return (!z2 || !z) ? decodeRow : new Result(decodeRow.getText().substring(1), (byte[]) null, decodeRow.getResultPoints(), BarcodeFormat.UPC_A);
            } catch (ReaderException unused) {
                i2++;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public void reset() {
        int size = this.readers.size();
        for (int i = 0; i < size; i++) {
            ((Reader) this.readers.elementAt(i)).reset();
        }
    }
}
