package com.google.android.gms.vision.text;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.util.SparseArray;
import com.google.android.gms.internal.zzdld;
import com.google.android.gms.internal.zzdll;
import com.google.android.gms.internal.zzdln;
import com.google.android.gms.internal.zzdlr;
import com.google.android.gms.internal.zzdls;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public final class TextRecognizer extends Detector<TextBlock> {
    private final zzdlr zzlid;

    public static class Builder {
        private Context mContext;
        private zzdls zzlie = new zzdls();

        public Builder(Context context) {
            this.mContext = context;
        }

        public TextRecognizer build() {
            return new TextRecognizer(new zzdlr(this.mContext, this.zzlie));
        }
    }

    private TextRecognizer() {
        throw new IllegalStateException("Default constructor called");
    }

    private TextRecognizer(zzdlr zzdlr) {
        this.zzlid = zzdlr;
    }

    private static SparseArray<TextBlock> zza(zzdll[] zzdllArr) {
        SparseArray sparseArray = new SparseArray();
        for (zzdll zzdll : zzdllArr) {
            SparseArray sparseArray2 = (SparseArray) sparseArray.get(zzdll.zzlio);
            if (sparseArray2 == null) {
                sparseArray2 = new SparseArray();
                sparseArray.append(zzdll.zzlio, sparseArray2);
            }
            sparseArray2.append(zzdll.zzlip, zzdll);
        }
        SparseArray<TextBlock> sparseArray3 = new SparseArray<>(sparseArray.size());
        for (int i = 0; i < sparseArray.size(); i++) {
            sparseArray3.append(sparseArray.keyAt(i), new TextBlock((SparseArray) sparseArray.valueAt(i)));
        }
        return sparseArray3;
    }

    public final SparseArray<TextBlock> detect(Frame frame) {
        byte[] bArr;
        Bitmap decodeByteArray;
        int i;
        zzdln zzdln = new zzdln(new Rect());
        if (frame != null) {
            zzdld zzc = zzdld.zzc(frame);
            if (frame.getBitmap() != null) {
                decodeByteArray = frame.getBitmap();
            } else {
                Frame.Metadata metadata = frame.getMetadata();
                ByteBuffer grayscaleImageData = frame.getGrayscaleImageData();
                int format = metadata.getFormat();
                int i2 = zzc.width;
                int i3 = zzc.height;
                if (!grayscaleImageData.hasArray() || grayscaleImageData.arrayOffset() != 0) {
                    byte[] bArr2 = new byte[grayscaleImageData.capacity()];
                    grayscaleImageData.get(bArr2);
                    bArr = bArr2;
                } else {
                    bArr = grayscaleImageData.array();
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                new YuvImage(bArr, format, i2, i3, (int[]) null).compressToJpeg(new Rect(0, 0, i2, i3), 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                decodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            }
            Bitmap bitmap = decodeByteArray;
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (zzc.rotation != 0) {
                Matrix matrix = new Matrix();
                int i4 = zzc.rotation;
                if (i4 == 0) {
                    i = 0;
                } else if (i4 == 1) {
                    i = 90;
                } else if (i4 == 2) {
                    i = 180;
                } else if (i4 == 3) {
                    i = 270;
                } else {
                    throw new IllegalArgumentException("Unsupported rotation degree.");
                }
                matrix.postRotate((float) i);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
            }
            if (zzc.rotation == 1 || zzc.rotation == 3) {
                zzc.width = height;
                zzc.height = width;
            }
            if (!zzdln.zzliq.isEmpty()) {
                Rect rect = zzdln.zzliq;
                int width2 = frame.getMetadata().getWidth();
                int height2 = frame.getMetadata().getHeight();
                int i5 = zzc.rotation;
                if (i5 == 1) {
                    rect = new Rect(height2 - rect.bottom, rect.left, height2 - rect.top, rect.right);
                } else if (i5 == 2) {
                    rect = new Rect(width2 - rect.right, height2 - rect.bottom, width2 - rect.left, height2 - rect.top);
                } else if (i5 == 3) {
                    rect = new Rect(rect.top, width2 - rect.right, rect.bottom, width2 - rect.left);
                }
                zzdln.zzliq.set(rect);
            }
            zzc.rotation = 0;
            return zza(this.zzlid.zza(bitmap, zzc, zzdln));
        }
        throw new IllegalArgumentException("No frame supplied.");
    }

    public final boolean isOperational() {
        return this.zzlid.isOperational();
    }

    public final void release() {
        super.release();
        this.zzlid.zzbln();
    }
}
