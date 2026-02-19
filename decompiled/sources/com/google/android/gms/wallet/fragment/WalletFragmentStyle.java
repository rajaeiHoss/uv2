package com.google.android.gms.wallet.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.google.android.gms.R;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class WalletFragmentStyle extends zzbgl {
    public static final Parcelable.Creator<WalletFragmentStyle> CREATOR = new zzg();
    private Bundle zzlpm;
    private int zzlpn;

    @Retention(RetentionPolicy.SOURCE)
    public @interface BuyButtonAppearance {
        public static final int ANDROID_PAY_DARK = 4;
        public static final int ANDROID_PAY_LIGHT = 5;
        public static final int ANDROID_PAY_LIGHT_WITH_BORDER = 6;
        @Deprecated
        public static final int GOOGLE_WALLET_CLASSIC = 1;
        @Deprecated
        public static final int GOOGLE_WALLET_GRAYSCALE = 2;
        @Deprecated
        public static final int GOOGLE_WALLET_MONOCHROME = 3;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BuyButtonText {
        public static final int BUY_WITH = 5;
        public static final int DONATE_WITH = 7;
        public static final int LOGO_ONLY = 6;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Dimension {
        public static final int MATCH_PARENT = -1;
        public static final int UNIT_DIP = 1;
        public static final int UNIT_IN = 4;
        public static final int UNIT_MM = 5;
        public static final int UNIT_PT = 3;
        public static final int UNIT_PX = 0;
        public static final int UNIT_SP = 2;
        public static final int WRAP_CONTENT = -2;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LogoImageType {
        public static final int ANDROID_PAY = 3;
        @Deprecated
        public static final int GOOGLE_WALLET_CLASSIC = 1;
        @Deprecated
        public static final int GOOGLE_WALLET_MONOCHROME = 2;
    }

    public WalletFragmentStyle() {
        Bundle bundle = new Bundle();
        this.zzlpm = bundle;
        bundle.putInt("buyButtonAppearanceDefault", 4);
        this.zzlpm.putInt("maskedWalletDetailsLogoImageTypeDefault", 3);
    }

    WalletFragmentStyle(Bundle bundle, int i) {
        this.zzlpm = bundle;
        this.zzlpn = i;
    }

    private final void zza(TypedArray typedArray, int i, String str) {
        TypedValue peekValue;
        long j;
        if (!this.zzlpm.containsKey(str) && (peekValue = typedArray.peekValue(i)) != null) {
            Bundle bundle = this.zzlpm;
            int i2 = peekValue.type;
            if (i2 == 5) {
                j = zzr(128, peekValue.data);
            } else if (i2 == 16) {
                j = zzfp(peekValue.data);
            } else {
                int i3 = peekValue.type;
                StringBuilder sb = new StringBuilder(38);
                sb.append("Unexpected dimension type: ");
                sb.append(i3);
                throw new IllegalArgumentException(sb.toString());
            }
            bundle.putLong(str, j);
        }
    }

    private final void zza(TypedArray typedArray, int i, String str, String str2) {
        TypedValue peekValue;
        if (!this.zzlpm.containsKey(str) && !this.zzlpm.containsKey(str2) && (peekValue = typedArray.peekValue(i)) != null) {
            if (peekValue.type < 28 || peekValue.type > 31) {
                this.zzlpm.putInt(str2, peekValue.resourceId);
            } else {
                this.zzlpm.putInt(str, peekValue.data);
            }
        }
    }

    private static long zzb(int i, float f) {
        if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5) {
            return zzr(i, Float.floatToIntBits(f));
        }
        StringBuilder sb = new StringBuilder(30);
        sb.append("Unrecognized unit: ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString());
    }

    private final void zzb(TypedArray typedArray, int i, String str) {
        TypedValue peekValue;
        if (!this.zzlpm.containsKey(str) && (peekValue = typedArray.peekValue(i)) != null) {
            this.zzlpm.putInt(str, peekValue.data);
        }
    }

    private static long zzfp(int i) {
        if (i >= 0) {
            return zzb(0, (float) i);
        }
        if (i == -1 || i == -2) {
            return zzr(129, i);
        }
        StringBuilder sb = new StringBuilder(39);
        sb.append("Unexpected dimension value: ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString());
    }

    private static long zzr(int i, int i2) {
        return (((long) i2) & 4294967295L) | (((long) i) << 32);
    }

    public final WalletFragmentStyle setBuyButtonAppearance(int i) {
        this.zzlpm.putInt("buyButtonAppearance", i);
        return this;
    }

    public final WalletFragmentStyle setBuyButtonHeight(int i) {
        this.zzlpm.putLong("buyButtonHeight", zzfp(i));
        return this;
    }

    public final WalletFragmentStyle setBuyButtonHeight(int i, float f) {
        this.zzlpm.putLong("buyButtonHeight", zzb(i, f));
        return this;
    }

    public final WalletFragmentStyle setBuyButtonText(int i) {
        this.zzlpm.putInt("buyButtonText", i);
        return this;
    }

    public final WalletFragmentStyle setBuyButtonWidth(int i) {
        this.zzlpm.putLong("buyButtonWidth", zzfp(i));
        return this;
    }

    public final WalletFragmentStyle setBuyButtonWidth(int i, float f) {
        this.zzlpm.putLong("buyButtonWidth", zzb(i, f));
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsBackgroundColor(int i) {
        this.zzlpm.remove("maskedWalletDetailsBackgroundResource");
        this.zzlpm.putInt("maskedWalletDetailsBackgroundColor", i);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsBackgroundResource(int i) {
        this.zzlpm.remove("maskedWalletDetailsBackgroundColor");
        this.zzlpm.putInt("maskedWalletDetailsBackgroundResource", i);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsButtonBackgroundColor(int i) {
        this.zzlpm.remove("maskedWalletDetailsButtonBackgroundResource");
        this.zzlpm.putInt("maskedWalletDetailsButtonBackgroundColor", i);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsButtonBackgroundResource(int i) {
        this.zzlpm.remove("maskedWalletDetailsButtonBackgroundColor");
        this.zzlpm.putInt("maskedWalletDetailsButtonBackgroundResource", i);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsButtonTextAppearance(int i) {
        this.zzlpm.putInt("maskedWalletDetailsButtonTextAppearance", i);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsHeaderTextAppearance(int i) {
        this.zzlpm.putInt("maskedWalletDetailsHeaderTextAppearance", i);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsLogoImageType(int i) {
        this.zzlpm.putInt("maskedWalletDetailsLogoImageType", i);
        return this;
    }

    @Deprecated
    public final WalletFragmentStyle setMaskedWalletDetailsLogoTextColor(int i) {
        this.zzlpm.putInt("maskedWalletDetailsLogoTextColor", i);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsTextAppearance(int i) {
        this.zzlpm.putInt("maskedWalletDetailsTextAppearance", i);
        return this;
    }

    public final WalletFragmentStyle setStyleResourceId(int i) {
        this.zzlpn = i;
        return this;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzlpm, false);
        zzbgo.zzc(parcel, 3, this.zzlpn);
        zzbgo.zzai(parcel, zze);
    }

    public final int zza(String str, DisplayMetrics displayMetrics, int i) {
        if (!this.zzlpm.containsKey(str)) {
            return i;
        }
        long j = this.zzlpm.getLong(str);
        int i2 = (int) (j >>> 32);
        int i3 = (int) j;
        int i4 = 5;
        if (i2 == 0) {
            i4 = 0;
        } else if (i2 == 1) {
            i4 = 1;
        } else if (i2 == 2) {
            i4 = 2;
        } else if (i2 == 3) {
            i4 = 3;
        } else if (i2 == 4) {
            i4 = 4;
        } else if (i2 != 5) {
            if (i2 == 128) {
                return TypedValue.complexToDimensionPixelSize(i3, displayMetrics);
            }
            if (i2 == 129) {
                return i3;
            }
            StringBuilder sb = new StringBuilder(36);
            sb.append("Unexpected unit or type: ");
            sb.append(i2);
            throw new IllegalStateException(sb.toString());
        }
        return Math.round(TypedValue.applyDimension(i4, Float.intBitsToFloat(i3), displayMetrics));
    }

    public final void zzet(Context context) {
        int i = this.zzlpn;
        if (i <= 0) {
            i = R.style.WalletFragmentDefaultStyle;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, R.styleable.WalletFragmentStyle);
        zza(obtainStyledAttributes, R.styleable.WalletFragmentStyle_buyButtonWidth, "buyButtonWidth");
        zza(obtainStyledAttributes, R.styleable.WalletFragmentStyle_buyButtonHeight, "buyButtonHeight");
        zzb(obtainStyledAttributes, R.styleable.WalletFragmentStyle_buyButtonText, "buyButtonText");
        zzb(obtainStyledAttributes, R.styleable.WalletFragmentStyle_buyButtonAppearance, "buyButtonAppearance");
        zzb(obtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance, "maskedWalletDetailsTextAppearance");
        zzb(obtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance, "maskedWalletDetailsHeaderTextAppearance");
        zza(obtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground, "maskedWalletDetailsBackgroundColor", "maskedWalletDetailsBackgroundResource");
        zzb(obtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance, "maskedWalletDetailsButtonTextAppearance");
        zza(obtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground, "maskedWalletDetailsButtonBackgroundColor", "maskedWalletDetailsButtonBackgroundResource");
        zzb(obtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor, "maskedWalletDetailsLogoTextColor");
        zzb(obtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType, "maskedWalletDetailsLogoImageType");
        obtainStyledAttributes.recycle();
    }
}
