package com.google.android.gms.cast.framework;

import android.app.Activity;
import android.content.Context;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import androidx.mediarouter.app.MediaRouteButton;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.internal.zzban;
import com.google.android.gms.internal.zzbar;

public interface IntroductoryOverlay {

    public static class Builder {
        private final Activity mActivity;
        private final View zzfax;
        private int zzfay;
        private String zzfaz;
        private OnOverlayDismissedListener zzfba;
        private boolean zzfbb;
        private float zzfbc;
        private String zzfbd;

        public Builder(Activity activity, MenuItem menuItem) {
            this.mActivity = (Activity) zzbq.checkNotNull(activity);
            this.zzfax = ((MenuItem) zzbq.checkNotNull(menuItem)).getActionView();
        }

        public Builder(Activity activity, MediaRouteButton mediaRouteButton) {
            this.mActivity = (Activity) zzbq.checkNotNull(activity);
            this.zzfax = (View) zzbq.checkNotNull(mediaRouteButton);
        }

        public IntroductoryOverlay build() {
            return zzs.zzans() ? new zzban(this) : new zzbar(this);
        }

        public final Activity getActivity() {
            return this.mActivity;
        }

        public Builder setButtonText(int i) {
            this.zzfbd = this.mActivity.getResources().getString(i);
            return this;
        }

        public Builder setButtonText(String str) {
            this.zzfbd = str;
            return this;
        }

        public Builder setFocusRadius(float f) {
            this.zzfbc = f;
            return this;
        }

        public Builder setFocusRadiusId(int i) {
            this.zzfbc = this.mActivity.getResources().getDimension(i);
            return this;
        }

        public Builder setOnOverlayDismissedListener(OnOverlayDismissedListener onOverlayDismissedListener) {
            this.zzfba = onOverlayDismissedListener;
            return this;
        }

        public Builder setOverlayColor(int i) {
            this.zzfay = this.mActivity.getResources().getColor(i);
            return this;
        }

        public Builder setSingleTime() {
            this.zzfbb = true;
            return this;
        }

        public Builder setTitleText(int i) {
            this.zzfaz = this.mActivity.getResources().getString(i);
            return this;
        }

        public Builder setTitleText(String str) {
            this.zzfaz = str;
            return this;
        }

        public final View zzaem() {
            return this.zzfax;
        }

        public final OnOverlayDismissedListener zzaen() {
            return this.zzfba;
        }

        public final int zzaeo() {
            return this.zzfay;
        }

        public final boolean zzaep() {
            return this.zzfbb;
        }

        public final String zzaeq() {
            return this.zzfaz;
        }

        public final String zzaer() {
            return this.zzfbd;
        }

        public final float zzaes() {
            return this.zzfbc;
        }
    }

    public interface OnOverlayDismissedListener {
        void onOverlayDismissed();
    }

    public static class zza {
        public static void zzbw(Context context) {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("googlecast-introOverlayShown", true).apply();
        }

        public static boolean zzbx(Context context) {
            return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("googlecast-introOverlayShown", false);
        }
    }

    void remove();

    void show();
}
