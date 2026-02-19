package com.google.android.gms.vision;

import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.vision.Detector;

public abstract class FocusingProcessor<T> implements Detector.Processor<T> {
    private Detector<T> zzlfj;
    private Tracker<T> zzlfy;
    private int zzlfz = 3;
    private boolean zzlga = false;
    private int zzlgb;
    private int zzlgc = 0;

    public FocusingProcessor(Detector<T> detector, Tracker<T> tracker) {
        this.zzlfj = detector;
        this.zzlfy = tracker;
    }

    public void receiveDetections(Detector.Detections<T> detections) {
        SparseArray<T> detectedItems = detections.getDetectedItems();
        if (detectedItems.size() == 0) {
            if (this.zzlgc == this.zzlfz) {
                this.zzlfy.onDone();
                this.zzlga = false;
            } else {
                this.zzlfy.onMissing(detections);
            }
            this.zzlgc++;
            return;
        }
        this.zzlgc = 0;
        if (this.zzlga) {
            T t = detectedItems.get(this.zzlgb);
            if (t != null) {
                this.zzlfy.onUpdate(detections, t);
                return;
            } else {
                this.zzlfy.onDone();
                this.zzlga = false;
            }
        }
        int selectFocus = selectFocus(detections);
        T t2 = detectedItems.get(selectFocus);
        if (t2 == null) {
            StringBuilder sb = new StringBuilder(35);
            sb.append("Invalid focus selected: ");
            sb.append(selectFocus);
            Log.w("FocusingProcessor", sb.toString());
            return;
        }
        this.zzlga = true;
        this.zzlgb = selectFocus;
        this.zzlfj.setFocus(selectFocus);
        this.zzlfy.onNewItem(this.zzlgb, t2);
        this.zzlfy.onUpdate(detections, t2);
    }

    public void release() {
        this.zzlfy.onDone();
    }

    public abstract int selectFocus(Detector.Detections<T> detections);

    /* access modifiers changed from: protected */
    public final void zzfl(int i) {
        if (i >= 0) {
            this.zzlfz = i;
            return;
        }
        StringBuilder sb = new StringBuilder(28);
        sb.append("Invalid max gap: ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString());
    }
}
