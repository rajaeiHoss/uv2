package com.google.android.gms.wallet.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.google.android.gms.R;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class WalletFragmentOptions extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<WalletFragmentOptions> CREATOR = new zzf();
    /* access modifiers changed from: private */
    public int mTheme;
    /* access modifiers changed from: private */
    public int zzgpd;
    /* access modifiers changed from: private */
    public int zzlod;
    /* access modifiers changed from: private */
    public WalletFragmentStyle zzlpk;

    public final class Builder {
        private Builder() {
        }

        public final WalletFragmentOptions build() {
            return WalletFragmentOptions.this;
        }

        public final Builder setEnvironment(int i) {
            int unused = WalletFragmentOptions.this.zzlod = i;
            return this;
        }

        public final Builder setFragmentStyle(int i) {
            WalletFragmentStyle unused = WalletFragmentOptions.this.zzlpk = new WalletFragmentStyle().setStyleResourceId(i);
            return this;
        }

        public final Builder setFragmentStyle(WalletFragmentStyle walletFragmentStyle) {
            WalletFragmentStyle unused = WalletFragmentOptions.this.zzlpk = walletFragmentStyle;
            return this;
        }

        public final Builder setMode(int i) {
            int unused = WalletFragmentOptions.this.zzgpd = i;
            return this;
        }

        public final Builder setTheme(int i) {
            int unused = WalletFragmentOptions.this.mTheme = i;
            return this;
        }
    }

    private WalletFragmentOptions() {
        this.zzlod = 3;
        this.zzlpk = new WalletFragmentStyle();
    }

    WalletFragmentOptions(int i, int i2, WalletFragmentStyle walletFragmentStyle, int i3) {
        this.zzlod = i;
        this.mTheme = i2;
        this.zzlpk = walletFragmentStyle;
        this.zzgpd = i3;
    }

    public static Builder newBuilder() {
        return new WalletFragmentOptions().new Builder();
    }

    public static WalletFragmentOptions zza(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.WalletFragmentOptions);
        int i = obtainStyledAttributes.getInt(R.styleable.WalletFragmentOptions_appTheme, 0);
        int i2 = obtainStyledAttributes.getInt(R.styleable.WalletFragmentOptions_environment, 1);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.WalletFragmentOptions_fragmentStyle, 0);
        int i3 = obtainStyledAttributes.getInt(R.styleable.WalletFragmentOptions_fragmentMode, 1);
        obtainStyledAttributes.recycle();
        WalletFragmentOptions walletFragmentOptions = new WalletFragmentOptions();
        walletFragmentOptions.mTheme = i;
        walletFragmentOptions.zzlod = i2;
        WalletFragmentStyle styleResourceId = new WalletFragmentStyle().setStyleResourceId(resourceId);
        walletFragmentOptions.zzlpk = styleResourceId;
        styleResourceId.zzet(context);
        walletFragmentOptions.zzgpd = i3;
        return walletFragmentOptions;
    }

    public final int getEnvironment() {
        return this.zzlod;
    }

    public final WalletFragmentStyle getFragmentStyle() {
        return this.zzlpk;
    }

    public final int getMode() {
        return this.zzgpd;
    }

    public final int getTheme() {
        return this.mTheme;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, getEnvironment());
        zzbgo.zzc(parcel, 3, getTheme());
        zzbgo.zza(parcel, 4, (Parcelable) getFragmentStyle(), i, false);
        zzbgo.zzc(parcel, 5, getMode());
        zzbgo.zzai(parcel, zze);
    }

    public final void zzet(Context context) {
        WalletFragmentStyle walletFragmentStyle = this.zzlpk;
        if (walletFragmentStyle != null) {
            walletFragmentStyle.zzet(context);
        }
    }
}
