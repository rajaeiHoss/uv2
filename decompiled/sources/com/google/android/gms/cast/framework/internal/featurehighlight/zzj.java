package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import com.google.android.gms.R;
import com.google.android.gms.internal.zzdog;

final class zzj {
    private final Rect zzfde = new Rect();
    private final int zzfdf;
    private final int zzfdg;
    private final int zzfdh;
    private final int zzfdi;
    private final zza zzfdj;

    zzj(zza zza) {
        this.zzfdj = (zza) zzdog.checkNotNull(zza);
        Resources resources = zza.getResources();
        this.zzfdf = resources.getDimensionPixelSize(R.dimen.cast_libraries_material_featurehighlight_inner_radius);
        this.zzfdg = resources.getDimensionPixelOffset(R.dimen.cast_libraries_material_featurehighlight_inner_margin);
        this.zzfdh = resources.getDimensionPixelSize(R.dimen.cast_libraries_material_featurehighlight_text_max_width);
        this.zzfdi = resources.getDimensionPixelSize(R.dimen.cast_libraries_material_featurehighlight_text_horizontal_offset);
    }

    private final int zza(View view, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i5 = i4 - i;
        int i6 = i2 - i4;
        int i7 = i4 - (i3 / 2);
        int i8 = this.zzfdi;
        int i9 = i5 <= i6 ? i7 + i8 : i7 - i8;
        return i9 - marginLayoutParams.leftMargin < i ? i + marginLayoutParams.leftMargin : (i9 + i3) + marginLayoutParams.rightMargin > i2 ? (i2 - i3) - marginLayoutParams.rightMargin : i9;
    }

    private final void zza(View view, int i, int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(View.MeasureSpec.makeMeasureSpec(Math.min((i - marginLayoutParams.leftMargin) - marginLayoutParams.rightMargin, this.zzfdh), BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(i2, Integer.MIN_VALUE));
    }

    /* access modifiers changed from: package-private */
    public final void zza(Rect rect, Rect rect2) {
        View zzafb = this.zzfdj.zzafb();
        boolean z = false;
        if (rect.isEmpty() || rect2.isEmpty()) {
            zzafb.layout(0, 0, 0, 0);
        } else {
            int centerY = rect.centerY();
            int centerX = rect.centerX();
            if (centerY < rect2.centerY()) {
                z = true;
            }
            int max = Math.max(this.zzfdf * 2, rect.height()) / 2;
            int i = this.zzfdg;
            int i2 = centerY + max + i;
            if (z) {
                zza(zzafb, rect2.width(), rect2.bottom - i2);
                int zza = zza(zzafb, rect2.left, rect2.right, zzafb.getMeasuredWidth(), centerX);
                zzafb.layout(zza, i2, zzafb.getMeasuredWidth() + zza, zzafb.getMeasuredHeight() + i2);
            } else {
                int i3 = (centerY - max) - i;
                zza(zzafb, rect2.width(), i3 - rect2.top);
                int zza2 = zza(zzafb, rect2.left, rect2.right, zzafb.getMeasuredWidth(), centerX);
                zzafb.layout(zza2, i3 - zzafb.getMeasuredHeight(), zzafb.getMeasuredWidth() + zza2, i3);
            }
        }
        this.zzfde.set(zzafb.getLeft(), zzafb.getTop(), zzafb.getRight(), zzafb.getBottom());
        this.zzfdj.zzafc().zzb(rect, this.zzfde);
        this.zzfdj.zzafd().zzc(rect);
    }
}
