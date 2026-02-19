package com.google.android.gms.common.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public final class zzby extends Button {
    public zzby(Context context) {
        this(context, (AttributeSet) null);
    }

    private zzby(Context context, AttributeSet attributeSet) {
        super(context, (AttributeSet) null, 16842824);
    }

    private static int zzf(int i, int i2, int i3, int i4) {
        if (i == 0) {
            return i2;
        }
        if (i == 1) {
            return i3;
        }
        if (i == 2) {
            return i4;
        }
        StringBuilder sb = new StringBuilder(33);
        sb.append("Unknown color scheme: ");
        sb.append(i);
        throw new IllegalStateException(sb.toString());
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(android.content.res.Resources r8, int r9, int r10) {
        /*
            r7 = this;
            android.graphics.Typeface r0 = android.graphics.Typeface.DEFAULT_BOLD
            r7.setTypeface(r0)
            r0 = 1096810496(0x41600000, float:14.0)
            r7.setTextSize(r0)
            android.util.DisplayMetrics r0 = r8.getDisplayMetrics()
            float r0 = r0.density
            r1 = 1111490560(0x42400000, float:48.0)
            float r0 = r0 * r1
            r1 = 1056964608(0x3f000000, float:0.5)
            float r0 = r0 + r1
            int r0 = (int) r0
            r7.setMinHeight(r0)
            r7.setMinWidth(r0)
            int r0 = com.google.android.gms.R.drawable.common_google_signin_btn_icon_dark
            int r1 = com.google.android.gms.R.drawable.common_google_signin_btn_icon_light
            int r2 = com.google.android.gms.R.drawable.common_google_signin_btn_icon_light
            int r0 = zzf(r10, r0, r1, r2)
            int r1 = com.google.android.gms.R.drawable.common_google_signin_btn_text_dark
            int r2 = com.google.android.gms.R.drawable.common_google_signin_btn_text_light
            int r3 = com.google.android.gms.R.drawable.common_google_signin_btn_text_light
            int r1 = zzf(r10, r1, r2, r3)
            java.lang.String r2 = "Unknown button size: "
            r3 = 32
            r4 = 2
            r5 = 1
            if (r9 == 0) goto L_0x0054
            if (r9 == r5) goto L_0x0054
            if (r9 != r4) goto L_0x003f
            goto L_0x0055
        L_0x003f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>(r3)
            r10.append(r2)
            r10.append(r9)
            java.lang.String r9 = r10.toString()
            r8.<init>(r9)
            throw r8
        L_0x0054:
            r0 = r1
        L_0x0055:
            android.graphics.drawable.Drawable r0 = r8.getDrawable(r0)
            android.graphics.drawable.Drawable r0 = androidx.core.graphics.drawable.DrawableCompat.wrap(r0)
            int r1 = com.google.android.gms.R.color.common_google_signin_btn_tint
            android.content.res.ColorStateList r1 = r8.getColorStateList(r1)
            androidx.core.graphics.drawable.DrawableCompat.setTintList(r0, r1)
            android.graphics.PorterDuff$Mode r1 = android.graphics.PorterDuff.Mode.SRC_ATOP
            androidx.core.graphics.drawable.DrawableCompat.setTintMode(r0, r1)
            r7.setBackgroundDrawable(r0)
            int r0 = com.google.android.gms.R.color.common_google_signin_btn_text_dark
            int r1 = com.google.android.gms.R.color.common_google_signin_btn_text_light
            int r6 = com.google.android.gms.R.color.common_google_signin_btn_text_light
            int r10 = zzf(r10, r0, r1, r6)
            android.content.res.ColorStateList r10 = r8.getColorStateList(r10)
            java.lang.Object r10 = com.google.android.gms.common.internal.zzbq.checkNotNull(r10)
            android.content.res.ColorStateList r10 = (android.content.res.ColorStateList) r10
            r7.setTextColor(r10)
            r10 = 0
            if (r9 == 0) goto L_0x00a8
            if (r9 == r5) goto L_0x00a5
            if (r9 != r4) goto L_0x0090
            r7.setText(r10)
            goto L_0x00b1
        L_0x0090:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>(r3)
            r10.append(r2)
            r10.append(r9)
            java.lang.String r9 = r10.toString()
            r8.<init>(r9)
            throw r8
        L_0x00a5:
            int r9 = com.google.android.gms.R.string.common_signin_button_text_long
            goto L_0x00aa
        L_0x00a8:
            int r9 = com.google.android.gms.R.string.common_signin_button_text
        L_0x00aa:
            java.lang.String r8 = r8.getString(r9)
            r7.setText(r8)
        L_0x00b1:
            r7.setTransformationMethod(r10)
            android.content.Context r8 = r7.getContext()
            boolean r8 = com.google.android.gms.common.util.zzj.zzcu(r8)
            if (r8 == 0) goto L_0x00c3
            r8 = 19
            r7.setGravity(r8)
        L_0x00c3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zzby.zza(android.content.res.Resources, int, int):void");
    }
}
