package com.google.zxing.datamatrix;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.datamatrix.decoder.Decoder;
import com.google.zxing.datamatrix.detector.Detector;
import java.util.Hashtable;

public final class DataMatrixReader implements Reader {
    private static final ResultPoint[] NO_POINTS = new ResultPoint[0];
    private final Decoder decoder = new Decoder();

    private static BitMatrix extractPureBits(BitMatrix bitMatrix) throws NotFoundException {
        int height = bitMatrix.getHeight();
        int width = bitMatrix.getWidth();
        int min = Math.min(height, width);
        int[] topLeftOnBit = bitMatrix.getTopLeftOnBit();
        if (topLeftOnBit != null) {
            int i = topLeftOnBit[0];
            int i2 = topLeftOnBit[1];
            while (i < min && i2 < min && bitMatrix.get(i, i2)) {
                i++;
            }
            if (i != min) {
                int i3 = i - topLeftOnBit[0];
                int i4 = width - 1;
                while (i4 >= 0 && !bitMatrix.get(i4, i2)) {
                    i4--;
                }
                if (i4 >= 0) {
                    int i5 = (i4 + 1) - i;
                    if (i5 % i3 == 0) {
                        int i6 = (i5 / i3) + 2;
                        int i7 = i3 >> 1;
                        int i8 = i - i7;
                        int i9 = (i2 + i3) - i7;
                        int i10 = (i6 - 1) * i3;
                        if (i8 + i10 >= width || i10 + i9 >= height) {
                            throw NotFoundException.getNotFoundInstance();
                        }
                        BitMatrix bitMatrix2 = new BitMatrix(i6);
                        for (int i11 = 0; i11 < i6; i11++) {
                            int i12 = (i11 * i3) + i9;
                            for (int i13 = 0; i13 < i6; i13++) {
                                if (bitMatrix.get((i13 * i3) + i8, i12)) {
                                    bitMatrix2.set(i13, i11);
                                }
                            }
                        }
                        return bitMatrix2;
                    }
                    throw NotFoundException.getNotFoundInstance();
                }
                throw NotFoundException.getNotFoundInstance();
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, ChecksumException, FormatException {
        return decode(binaryBitmap, (Hashtable) null);
    }

    public Result decode(BinaryBitmap binaryBitmap, Hashtable hashtable) throws NotFoundException, ChecksumException, FormatException {
        ResultPoint[] resultPointArr;
        DecoderResult decoderResult;
        if (hashtable == null || !hashtable.containsKey(DecodeHintType.PURE_BARCODE)) {
            DetectorResult detect = new Detector(binaryBitmap.getBlackMatrix()).detect();
            DecoderResult decode = this.decoder.decode(detect.getBits());
            resultPointArr = detect.getPoints();
            decoderResult = decode;
        } else {
            decoderResult = this.decoder.decode(extractPureBits(binaryBitmap.getBlackMatrix()));
            resultPointArr = NO_POINTS;
        }
        Result result = new Result(decoderResult.getText(), decoderResult.getRawBytes(), resultPointArr, BarcodeFormat.DATA_MATRIX);
        if (decoderResult.getByteSegments() != null) {
            result.putMetadata(ResultMetadataType.BYTE_SEGMENTS, decoderResult.getByteSegments());
        }
        if (decoderResult.getECLevel() != null) {
            result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, decoderResult.getECLevel().toString());
        }
        return result;
    }

    public void reset() {
    }
}
