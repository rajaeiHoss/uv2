package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import com.google.android.gms.cast.AdBreakInfo;
import java.util.List;

public final class zzbbm extends View {
    private List<AdBreakInfo> zzewe;
    private final int zzfhh;
    private int zzfhi = 1;
    private Paint zzfhj;

    public zzbbm(Context context) {
        super(context);
        Context context2 = getContext();
        this.zzfhh = (int) (context2 == null ? Math.round(3.0d) : Math.round(((double) context2.getResources().getDisplayMetrics().density) * 3.0d));
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0068, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void onDraw(android.graphics.Canvas r10) {
        /*
            r9 = this;
            monitor-enter(r9)
            super.onDraw(r10)     // Catch:{ all -> 0x0069 }
            java.util.List<com.google.android.gms.cast.AdBreakInfo> r0 = r9.zzewe     // Catch:{ all -> 0x0069 }
            if (r0 == 0) goto L_0x0067
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0069 }
            if (r0 == 0) goto L_0x000f
            goto L_0x0067
        L_0x000f:
            int r0 = r9.getMeasuredHeight()     // Catch:{ all -> 0x0069 }
            float r0 = (float) r0     // Catch:{ all -> 0x0069 }
            r1 = 1073741824(0x40000000, float:2.0)
            float r0 = r0 / r1
            int r0 = java.lang.Math.round(r0)     // Catch:{ all -> 0x0069 }
            int r1 = r9.getMeasuredWidth()     // Catch:{ all -> 0x0069 }
            int r2 = r9.getPaddingLeft()     // Catch:{ all -> 0x0069 }
            int r1 = r1 - r2
            int r2 = r9.getPaddingRight()     // Catch:{ all -> 0x0069 }
            int r1 = r1 - r2
            java.util.List<com.google.android.gms.cast.AdBreakInfo> r2 = r9.zzewe     // Catch:{ all -> 0x0069 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0069 }
        L_0x002f:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x0069 }
            if (r3 == 0) goto L_0x0065
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x0069 }
            com.google.android.gms.cast.AdBreakInfo r3 = (com.google.android.gms.cast.AdBreakInfo) r3     // Catch:{ all -> 0x0069 }
            if (r3 == 0) goto L_0x002f
            long r3 = r3.getPlaybackPositionInMs()     // Catch:{ all -> 0x0069 }
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 < 0) goto L_0x002f
            int r5 = r9.zzfhi     // Catch:{ all -> 0x0069 }
            long r6 = (long) r5     // Catch:{ all -> 0x0069 }
            int r8 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r8 > 0) goto L_0x002f
            double r3 = (double) r3     // Catch:{ all -> 0x0069 }
            double r6 = (double) r1     // Catch:{ all -> 0x0069 }
            double r3 = r3 * r6
            double r5 = (double) r5     // Catch:{ all -> 0x0069 }
            double r3 = r3 / r5
            int r3 = (int) r3     // Catch:{ all -> 0x0069 }
            int r4 = r9.getPaddingLeft()     // Catch:{ all -> 0x0069 }
            int r4 = r4 + r3
            float r3 = (float) r4     // Catch:{ all -> 0x0069 }
            float r4 = (float) r0     // Catch:{ all -> 0x0069 }
            int r5 = r9.zzfhh     // Catch:{ all -> 0x0069 }
            float r5 = (float) r5     // Catch:{ all -> 0x0069 }
            android.graphics.Paint r6 = r9.zzfhj     // Catch:{ all -> 0x0069 }
            r10.drawCircle(r3, r4, r5, r6)     // Catch:{ all -> 0x0069 }
            goto L_0x002f
        L_0x0065:
            monitor-exit(r9)
            return
        L_0x0067:
            monitor-exit(r9)
            return
        L_0x0069:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbbm.onDraw(android.graphics.Canvas):void");
    }

    public final synchronized void zzb(List<AdBreakInfo> list, int i) {
        this.zzewe = list;
        Paint paint = new Paint(1);
        this.zzfhj = paint;
        paint.setColor(-1);
        this.zzfhj.setStyle(Paint.Style.FILL);
        invalidate();
    }

    public final synchronized void zzbi(int i) {
        this.zzfhi = i;
    }
}
