package com.google.zxing.pdf417;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.pdf417.decoder.Decoder;
import com.google.zxing.pdf417.detector.Detector;
import com.google.zxing.qrcode.QRCodeReader;
import java.util.Hashtable;

public final class PDF417Reader implements Reader {
    private static final ResultPoint[] NO_POINTS = new ResultPoint[0];
    private final Decoder decoder = new Decoder();

    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, FormatException {
        return decode(binaryBitmap, (Hashtable) null);
    }

    public Result decode(BinaryBitmap binaryBitmap, Hashtable hashtable) throws NotFoundException, FormatException {
        ResultPoint[] resultPointArr;
        DecoderResult decoderResult;
        if (hashtable == null || !hashtable.containsKey(DecodeHintType.PURE_BARCODE)) {
            DetectorResult detect = new Detector(binaryBitmap).detect();
            DecoderResult decode = this.decoder.decode(detect.getBits());
            resultPointArr = detect.getPoints();
            decoderResult = decode;
        } else {
            decoderResult = this.decoder.decode(QRCodeReader.extractPureBits(binaryBitmap.getBlackMatrix()));
            resultPointArr = NO_POINTS;
        }
        return new Result(decoderResult.getText(), decoderResult.getRawBytes(), resultPointArr, BarcodeFormat.PDF417);
    }

    public void reset() {
    }
}
