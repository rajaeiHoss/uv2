package com.google.android.gms.vision;

import android.util.SparseArray;
import com.google.android.gms.vision.Detector;
import java.util.HashSet;

public class MultiProcessor<T> implements Detector.Processor<T> {
    /* access modifiers changed from: private */
    public int zzlfz;
    /* access modifiers changed from: private */
    public Factory<T> zzlgl;
    private SparseArray<zza> zzlgm;

    public static class Builder<T> {
        private MultiProcessor<T> zzlgn;

        public Builder(Factory<T> factory) {
            MultiProcessor<T> multiProcessor = new MultiProcessor<>();
            this.zzlgn = multiProcessor;
            if (factory != null) {
                Factory unused = multiProcessor.zzlgl = factory;
                return;
            }
            throw new IllegalArgumentException("No factory supplied.");
        }

        public MultiProcessor<T> build() {
            return this.zzlgn;
        }

        public Builder<T> setMaxGapFrames(int i) {
            if (i >= 0) {
                int unused = this.zzlgn.zzlfz = i;
                return this;
            }
            StringBuilder sb = new StringBuilder(28);
            sb.append("Invalid max gap: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public interface Factory<T> {
        Tracker<T> create(T t);
    }

    class zza {
        /* access modifiers changed from: private */
        public Tracker<T> zzlfy;
        /* access modifiers changed from: private */
        public int zzlgc;

        private zza() {
            this.zzlgc = 0;
        }

        void zzb() {
            this.zzlgc++;
        }
    }

    private MultiProcessor() {
        this.zzlgm = new SparseArray<>();
        this.zzlfz = 3;
    }

    private final void zza(Detector.Detections<T> detections) {
        SparseArray<T> detectedItems = detections.getDetectedItems();
        for (int i = 0; i < detectedItems.size(); i++) {
            int keyAt = detectedItems.keyAt(i);
            T valueAt = detectedItems.valueAt(i);
            zza zza2 = this.zzlgm.get(keyAt);
            int unused = zza2.zzlgc = 0;
            zza2.zzlfy.onUpdate(detections, valueAt);
        }
    }

    public void receiveDetections(Detector.Detections<T> detections) {
        SparseArray<T> detectedItems = detections.getDetectedItems();
        for (int i = 0; i < detectedItems.size(); i++) {
            int keyAt = detectedItems.keyAt(i);
            T valueAt = detectedItems.valueAt(i);
            if (this.zzlgm.get(keyAt) == null) {
                zza zza2 = new zza();
                Tracker unused = zza2.zzlfy = this.zzlgl.create(valueAt);
                zza2.zzlfy.onNewItem(keyAt, valueAt);
                this.zzlgm.append(keyAt, zza2);
            }
        }
        SparseArray<T> detectedItems2 = detections.getDetectedItems();
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i2 = 0; i2 < this.zzlgm.size(); i2++) {
            int keyAt2 = this.zzlgm.keyAt(i2);
            if (detectedItems2.get(keyAt2) == null) {
                zza valueAt2 = this.zzlgm.valueAt(i2);
                valueAt2.zzb();
                if (valueAt2.zzlgc >= this.zzlfz) {
                    valueAt2.zzlfy.onDone();
                    hashSet.add(Integer.valueOf(keyAt2));
                } else {
                    valueAt2.zzlfy.onMissing(detections);
                }
            }
        }
        for (Integer intValue : hashSet) {
            this.zzlgm.delete(intValue.intValue());
        }
        zza(detections);
    }

    public void release() {
        for (int i = 0; i < this.zzlgm.size(); i++) {
            this.zzlgm.valueAt(i).zzlfy.onDone();
        }
        this.zzlgm.clear();
    }
}
