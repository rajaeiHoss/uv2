package com.google.zxing.multi;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import java.util.Hashtable;
import java.util.Vector;

public final class GenericMultipleBarcodeReader implements MultipleBarcodeReader {
    private static final int MIN_DIMENSION_TO_RECUR = 100;
    private final Reader delegate;

    public GenericMultipleBarcodeReader(Reader reader) {
        this.delegate = reader;
    }

    private void doDecodeMultiple(BinaryBitmap binaryBitmap, Hashtable hashtable, Vector vector, int i, int i2) {
        boolean z;
        float f;
        float f2;
        BinaryBitmap binaryBitmap2 = binaryBitmap;
        Vector vector2 = vector;
        int i3 = i;
        int i4 = i2;
        try {
            Result decode = this.delegate.decode(binaryBitmap2, hashtable);
            int i5 = 0;
            while (true) {
                if (i5 >= vector.size()) {
                    z = false;
                    break;
                } else if (((Result) vector2.elementAt(i5)).getText().equals(decode.getText())) {
                    z = true;
                    break;
                } else {
                    i5++;
                }
            }
            if (!z) {
                vector2.addElement(translateResultPoints(decode, i3, i4));
                ResultPoint[] resultPoints = decode.getResultPoints();
                if (resultPoints != null && resultPoints.length != 0) {
                    int width = binaryBitmap.getWidth();
                    int height = binaryBitmap.getHeight();
                    float f3 = (float) width;
                    float f4 = (float) height;
                    float f5 = 0.0f;
                    float f6 = 0.0f;
                    for (ResultPoint resultPoint : resultPoints) {
                        float x = resultPoint.getX();
                        float y = resultPoint.getY();
                        if (x < f3) {
                            f3 = x;
                        }
                        if (y < f4) {
                            f4 = y;
                        }
                        if (x > f6) {
                            f6 = x;
                        }
                        if (y > f5) {
                            f5 = y;
                        }
                    }
                    if (f3 > 100.0f) {
                        f2 = f5;
                        f = f6;
                        doDecodeMultiple(binaryBitmap2.crop(0, 0, (int) f3, height), hashtable, vector, i, i2);
                    } else {
                        f2 = f5;
                        f = f6;
                    }
                    if (f4 > 100.0f) {
                        doDecodeMultiple(binaryBitmap2.crop(0, 0, width, (int) f4), hashtable, vector, i, i2);
                    }
                    float f7 = f;
                    if (f7 < ((float) (width - 100))) {
                        int i6 = (int) f7;
                        doDecodeMultiple(binaryBitmap2.crop(i6, 0, width - i6, height), hashtable, vector, i3 + i6, i2);
                    }
                    float f8 = f2;
                    if (f8 < ((float) (height - 100))) {
                        int i7 = (int) f8;
                        doDecodeMultiple(binaryBitmap2.crop(0, i7, width, height - i7), hashtable, vector, i, i4 + i7);
                    }
                }
            }
        } catch (ReaderException unused) {
        }
    }

    private static Result translateResultPoints(Result result, int i, int i2) {
        ResultPoint[] resultPoints = result.getResultPoints();
        ResultPoint[] resultPointArr = new ResultPoint[resultPoints.length];
        for (int i3 = 0; i3 < resultPoints.length; i3++) {
            ResultPoint resultPoint = resultPoints[i3];
            resultPointArr[i3] = new ResultPoint(resultPoint.getX() + ((float) i), resultPoint.getY() + ((float) i2));
        }
        return new Result(result.getText(), result.getRawBytes(), resultPointArr, result.getBarcodeFormat());
    }

    public Result[] decodeMultiple(BinaryBitmap binaryBitmap) throws NotFoundException {
        return decodeMultiple(binaryBitmap, (Hashtable) null);
    }

    public Result[] decodeMultiple(BinaryBitmap binaryBitmap, Hashtable hashtable) throws NotFoundException {
        Vector vector = new Vector();
        doDecodeMultiple(binaryBitmap, hashtable, vector, 0, 0);
        if (!vector.isEmpty()) {
            int size = vector.size();
            Result[] resultArr = new Result[size];
            for (int i = 0; i < size; i++) {
                resultArr[i] = (Result) vector.elementAt(i);
            }
            return resultArr;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
