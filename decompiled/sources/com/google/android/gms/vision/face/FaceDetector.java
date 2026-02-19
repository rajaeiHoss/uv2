package com.google.android.gms.vision.face;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.internal.zzdld;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.internal.client.zza;
import com.google.android.gms.vision.zzc;
import java.nio.ByteBuffer;
import java.util.HashSet;

public final class FaceDetector extends Detector<Face> {
    public static final int ACCURATE_MODE = 1;
    public static final int ALL_CLASSIFICATIONS = 1;
    public static final int ALL_LANDMARKS = 1;
    public static final int FAST_MODE = 0;
    public static final int NO_CLASSIFICATIONS = 0;
    public static final int NO_LANDMARKS = 0;
    private boolean mIsActive;
    private final Object mLock;
    private final zzc zzlha;
    private final zza zzlhb;

    public static class Builder {
        private final Context mContext;
        private int zzgpd = 0;
        private int zzlhc = 0;
        private boolean zzlhd = false;
        private int zzlhe = 0;
        private boolean zzlhf = true;
        private float zzlhg = -1.0f;

        public Builder(Context context) {
            this.mContext = context;
        }

        public FaceDetector build() {
            com.google.android.gms.vision.face.internal.client.zzc zzc = new com.google.android.gms.vision.face.internal.client.zzc();
            zzc.mode = this.zzgpd;
            zzc.zzlhp = this.zzlhc;
            zzc.zzlhq = this.zzlhe;
            zzc.zzlhr = this.zzlhd;
            zzc.zzlhs = this.zzlhf;
            zzc.zzlht = this.zzlhg;
            return new FaceDetector(new zza(this.mContext, zzc));
        }

        public Builder setClassificationType(int i) {
            if (i == 0 || i == 1) {
                this.zzlhe = i;
                return this;
            }
            StringBuilder sb = new StringBuilder(40);
            sb.append("Invalid classification type: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }

        public Builder setLandmarkType(int i) {
            if (i == 0 || i == 1) {
                this.zzlhc = i;
                return this;
            }
            StringBuilder sb = new StringBuilder(34);
            sb.append("Invalid landmark type: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }

        public Builder setMinFaceSize(float f) {
            if (f < 0.0f || f > 1.0f) {
                StringBuilder sb = new StringBuilder(47);
                sb.append("Invalid proportional face size: ");
                sb.append(f);
                throw new IllegalArgumentException(sb.toString());
            }
            this.zzlhg = f;
            return this;
        }

        public Builder setMode(int i) {
            if (i == 0 || i == 1) {
                this.zzgpd = i;
                return this;
            }
            StringBuilder sb = new StringBuilder(25);
            sb.append("Invalid mode: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }

        public Builder setProminentFaceOnly(boolean z) {
            this.zzlhd = z;
            return this;
        }

        public Builder setTrackingEnabled(boolean z) {
            this.zzlhf = z;
            return this;
        }
    }

    private FaceDetector() {
        this.zzlha = new zzc();
        this.mLock = new Object();
        this.mIsActive = true;
        throw new IllegalStateException("Default constructor called");
    }

    private FaceDetector(zza zza) {
        this.zzlha = new zzc();
        this.mLock = new Object();
        this.mIsActive = true;
        this.zzlhb = zza;
    }

    public final SparseArray<Face> detect(Frame frame) {
        Face[] zzb;
        if (frame != null) {
            ByteBuffer grayscaleImageData = frame.getGrayscaleImageData();
            synchronized (this.mLock) {
                if (this.mIsActive) {
                    zzb = this.zzlhb.zzb(grayscaleImageData, zzdld.zzc(frame));
                } else {
                    throw new RuntimeException("Cannot use detector after release()");
                }
            }
            HashSet hashSet = new HashSet();
            SparseArray<Face> sparseArray = new SparseArray<>(zzb.length);
            int i = 0;
            for (Face face : zzb) {
                int id = face.getId();
                i = Math.max(i, id);
                if (hashSet.contains(Integer.valueOf(id))) {
                    id = i + 1;
                    i = id;
                }
                hashSet.add(Integer.valueOf(id));
                sparseArray.append(this.zzlha.zzfm(id), face);
            }
            return sparseArray;
        }
        throw new IllegalArgumentException("No frame supplied.");
    }

    /* access modifiers changed from: protected */
    public final void finalize() throws Throwable {
        try {
            synchronized (this.mLock) {
                if (this.mIsActive) {
                    Log.w("FaceDetector", "FaceDetector was not released with FaceDetector.release()");
                    release();
                }
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
            throw th;
        }
    }

    public final boolean isOperational() {
        return this.zzlhb.isOperational();
    }

    public final void release() {
        super.release();
        synchronized (this.mLock) {
            if (this.mIsActive) {
                this.zzlhb.zzbln();
                this.mIsActive = false;
            }
        }
    }

    public final boolean setFocus(int i) {
        boolean zzfo;
        int zzfn = this.zzlha.zzfn(i);
        synchronized (this.mLock) {
            if (this.mIsActive) {
                zzfo = this.zzlhb.zzfo(zzfn);
            } else {
                throw new RuntimeException("Cannot use detector after release()");
            }
        }
        return zzfo;
    }
}
