package com.google.zxing.qrcode;

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
import com.google.zxing.qrcode.decoder.Decoder;
import com.google.zxing.qrcode.detector.Detector;
import java.util.Hashtable;

public class QRCodeReader implements Reader {
    private static final ResultPoint[] NO_POINTS = new ResultPoint[0];
    private final Decoder decoder = new Decoder();

    public static BitMatrix extractPureBits(BitMatrix bitMatrix) throws NotFoundException {
        int height = bitMatrix.getHeight();
        int width = bitMatrix.getWidth();
        int min = Math.min(height, width);
        int[] topLeftOnBit = bitMatrix.getTopLeftOnBit();
        if (topLeftOnBit != null) {
            int i = topLeftOnBit[0];
            int i2 = 1;
            int i3 = topLeftOnBit[1];
            while (i < min && i3 < min && bitMatrix.get(i, i3)) {
                i++;
                i3++;
            }
            if (i == min || i3 == min) {
                throw NotFoundException.getNotFoundInstance();
            }
            int i4 = i - topLeftOnBit[0];
            if (i4 != 0) {
                int i5 = width - 1;
                while (i5 > i && !bitMatrix.get(i5, i3)) {
                    i5--;
                }
                if (i5 > i) {
                    int i6 = (i5 + 1) - i;
                    if (i6 % i4 == 0) {
                        int i7 = (i6 / i4) + 1;
                        if (i4 != 1) {
                            i2 = i4 >> 1;
                        }
                        int i8 = i - i2;
                        int i9 = i3 - i2;
                        int i10 = (i7 - 1) * i4;
                        if (i8 + i10 >= width || i10 + i9 >= height) {
                            throw NotFoundException.getNotFoundInstance();
                        }
                        BitMatrix bitMatrix2 = new BitMatrix(i7);
                        for (int i11 = 0; i11 < i7; i11++) {
                            int i12 = (i11 * i4) + i9;
                            for (int i13 = 0; i13 < i7; i13++) {
                                if (bitMatrix.get((i13 * i4) + i8, i12)) {
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
            DetectorResult detect = new Detector(binaryBitmap.getBlackMatrix()).detect(hashtable);
            DecoderResult decode = this.decoder.decode(detect.getBits(), hashtable);
            resultPointArr = detect.getPoints();
            decoderResult = decode;
        } else {
            decoderResult = this.decoder.decode(extractPureBits(binaryBitmap.getBlackMatrix()), hashtable);
            resultPointArr = NO_POINTS;
        }
        Result result = new Result(decoderResult.getText(), decoderResult.getRawBytes(), resultPointArr, BarcodeFormat.QR_CODE);
        if (decoderResult.getByteSegments() != null) {
            result.putMetadata(ResultMetadataType.BYTE_SEGMENTS, decoderResult.getByteSegments());
        }
        if (decoderResult.getECLevel() != null) {
            result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, decoderResult.getECLevel().toString());
        }
        return result;
    }

    /* access modifiers changed from: protected */
    public Decoder getDecoder() {
        return this.decoder;
    }

    public void reset() {
    }
}
