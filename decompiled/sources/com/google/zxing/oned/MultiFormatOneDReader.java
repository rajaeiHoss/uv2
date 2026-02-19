package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.BitArray;
import com.google.zxing.oned.rss.RSS14Reader;
import com.google.zxing.oned.rss.expanded.RSSExpandedReader;
import java.util.Hashtable;
import java.util.Vector;

public final class MultiFormatOneDReader extends OneDReader {
    private final Vector readers;

    public MultiFormatOneDReader(Hashtable hashtable) {
        Vector vector = hashtable == null ? null : (Vector) hashtable.get(DecodeHintType.POSSIBLE_FORMATS);
        boolean z = (hashtable == null || hashtable.get(DecodeHintType.ASSUME_CODE_39_CHECK_DIGIT) == null) ? false : true;
        Vector vector2 = new Vector();
        this.readers = vector2;
        if (vector != null) {
            if (vector.contains(BarcodeFormat.EAN_13) || vector.contains(BarcodeFormat.UPC_A) || vector.contains(BarcodeFormat.EAN_8) || vector.contains(BarcodeFormat.UPC_E)) {
                vector2.addElement(new MultiFormatUPCEANReader(hashtable));
            }
            if (vector.contains(BarcodeFormat.CODE_39)) {
                vector2.addElement(new Code39Reader(z));
            }
            if (vector.contains(BarcodeFormat.CODE_93)) {
                vector2.addElement(new Code93Reader());
            }
            if (vector.contains(BarcodeFormat.CODE_128)) {
                vector2.addElement(new Code128Reader());
            }
            if (vector.contains(BarcodeFormat.ITF)) {
                vector2.addElement(new ITFReader());
            }
            if (vector.contains(BarcodeFormat.CODABAR)) {
                vector2.addElement(new CodaBarReader());
            }
            if (vector.contains(BarcodeFormat.RSS14)) {
                vector2.addElement(new RSS14Reader());
            }
            if (vector.contains(BarcodeFormat.RSS_EXPANDED)) {
                vector2.addElement(new RSSExpandedReader());
            }
        }
        if (vector2.isEmpty()) {
            vector2.addElement(new MultiFormatUPCEANReader(hashtable));
            vector2.addElement(new Code39Reader());
            vector2.addElement(new Code93Reader());
            vector2.addElement(new Code128Reader());
            vector2.addElement(new ITFReader());
            vector2.addElement(new RSS14Reader());
            vector2.addElement(new RSSExpandedReader());
        }
    }

    public Result decodeRow(int i, BitArray bitArray, Hashtable hashtable) throws NotFoundException {
        int i2 = 0;
        while (i2 < this.readers.size()) {
            try {
                return ((OneDReader) this.readers.elementAt(i2)).decodeRow(i, bitArray, hashtable);
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
