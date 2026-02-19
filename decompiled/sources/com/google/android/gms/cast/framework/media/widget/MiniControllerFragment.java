package com.google.android.gms.cast.framework.media.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.google.android.gms.R;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.cast.framework.media.uicontroller.UIMediaController;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbei;

public class MiniControllerFragment extends Fragment implements ControlButtonsContainer {
    private static final zzbei zzeui = new zzbei("MiniControllerFragment");
    private int zzfiy;
    private int zzfiz;
    private int zzfja;
    private int zzfjb;
    private int zzfjc;
    private int zzfjd;
    private int zzfje;
    private int zzfjf;
    private int zzfjg;
    private int zzfjh;
    private int[] zzfjm;
    private ImageView[] zzfjn = new ImageView[3];
    private UIMediaController zzfjt;
    private boolean zzfjw;
    private int zzfjx;
    private int zzfjy;
    private TextView zzfjz;
    private int zzfka;
    private int zzfkb;
    private int zzfkc;
    private int zzfkd;
    private int zzfke;
    private int zzfkf;

    private final void zza(RelativeLayout relativeLayout, int i, int i2) {
        ImageView imageView = (ImageView) relativeLayout.findViewById(i);
        int i3 = this.zzfjm[i2];
        if (i3 == R.id.cast_button_type_empty) {
            imageView.setVisibility(4);
        } else if (i3 == R.id.cast_button_type_custom) {
        } else {
            if (i3 == R.id.cast_button_type_play_pause_toggle) {
                int i4 = this.zzfiy;
                int i5 = this.zzfiz;
                int i6 = this.zzfja;
                if (this.zzfkc == 1) {
                    i4 = this.zzfkd;
                    i5 = this.zzfke;
                    i6 = this.zzfkf;
                }
                Drawable zza = zzb.zza(getContext(), this.zzfjh, i4);
                Drawable zza2 = zzb.zza(getContext(), this.zzfjh, i5);
                Drawable zza3 = zzb.zza(getContext(), this.zzfjh, i6);
                imageView.setImageDrawable(zza2);
                ProgressBar progressBar = new ProgressBar(getContext());
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(8, i);
                layoutParams.addRule(6, i);
                layoutParams.addRule(5, i);
                layoutParams.addRule(7, i);
                layoutParams.addRule(15);
                progressBar.setLayoutParams(layoutParams);
                progressBar.setVisibility(8);
                Drawable indeterminateDrawable = progressBar.getIndeterminateDrawable();
                int i7 = this.zzfkb;
                if (!(i7 == 0 || indeterminateDrawable == null)) {
                    indeterminateDrawable.setColorFilter(i7, PorterDuff.Mode.SRC_IN);
                }
                relativeLayout.addView(progressBar);
                this.zzfjt.bindImageViewToPlayPauseToggle(imageView, zza, zza2, zza3, progressBar, true);
            } else if (i3 == R.id.cast_button_type_skip_previous) {
                imageView.setImageDrawable(zzb.zza(getContext(), this.zzfjh, this.zzfjb));
                imageView.setContentDescription(getResources().getString(R.string.cast_skip_prev));
                this.zzfjt.bindViewToSkipPrev(imageView, 0);
            } else if (i3 == R.id.cast_button_type_skip_next) {
                imageView.setImageDrawable(zzb.zza(getContext(), this.zzfjh, this.zzfjc));
                imageView.setContentDescription(getResources().getString(R.string.cast_skip_next));
                this.zzfjt.bindViewToSkipNext(imageView, 0);
            } else if (i3 == R.id.cast_button_type_rewind_30_seconds) {
                imageView.setImageDrawable(zzb.zza(getContext(), this.zzfjh, this.zzfjd));
                imageView.setContentDescription(getResources().getString(R.string.cast_rewind_30));
                this.zzfjt.bindViewToRewind(imageView, NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);
            } else if (i3 == R.id.cast_button_type_forward_30_seconds) {
                imageView.setImageDrawable(zzb.zza(getContext(), this.zzfjh, this.zzfje));
                imageView.setContentDescription(getResources().getString(R.string.cast_forward_30));
                this.zzfjt.bindViewToForward(imageView, NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);
            } else if (i3 == R.id.cast_button_type_mute_toggle) {
                imageView.setImageDrawable(zzb.zza(getContext(), this.zzfjh, this.zzfjf));
                this.zzfjt.bindImageViewToMuteToggle(imageView);
            } else if (i3 == R.id.cast_button_type_closed_caption) {
                imageView.setImageDrawable(zzb.zza(getContext(), this.zzfjh, this.zzfjg));
                this.zzfjt.bindViewToClosedCaption(imageView);
            }
        }
    }

    public final ImageView getButtonImageViewAt(int i) throws IndexOutOfBoundsException {
        return this.zzfjn[i];
    }

    public final int getButtonSlotCount() {
        return 3;
    }

    public final int getButtonTypeAt(int i) throws IndexOutOfBoundsException {
        return this.zzfjm[i];
    }

    public UIMediaController getUIMediaController() {
        return this.zzfjt;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.zzfjt = new UIMediaController(getActivity());
        View inflate = layoutInflater.inflate(R.layout.cast_mini_controller, viewGroup);
        inflate.setVisibility(8);
        this.zzfjt.bindViewVisibilityToMediaSession(inflate, 8);
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.container_current);
        int i = this.zzfka;
        if (i != 0) {
            relativeLayout.setBackgroundResource(i);
        }
        ImageView imageView = (ImageView) inflate.findViewById(R.id.icon_view);
        TextView textView = (TextView) inflate.findViewById(R.id.title_view);
        if (this.zzfjx != 0) {
            textView.setTextAppearance(getActivity(), this.zzfjx);
        }
        TextView textView2 = (TextView) inflate.findViewById(R.id.subtitle_view);
        this.zzfjz = textView2;
        if (this.zzfjy != 0) {
            textView2.setTextAppearance(getActivity(), this.zzfjy);
        }
        ProgressBar progressBar = (ProgressBar) inflate.findViewById(R.id.progressBar);
        if (this.zzfkb != 0) {
            ((LayerDrawable) progressBar.getProgressDrawable()).setColorFilter(this.zzfkb, PorterDuff.Mode.SRC_IN);
        }
        this.zzfjt.bindTextViewToMetadataOfCurrentItem(textView, MediaMetadata.KEY_TITLE);
        this.zzfjt.bindTextViewToSmartSubtitle(this.zzfjz);
        this.zzfjt.bindProgressBar(progressBar);
        this.zzfjt.bindViewToLaunchExpandedController(relativeLayout);
        if (this.zzfjw) {
            this.zzfjt.bindImageViewToImageOfCurrentItem(imageView, new ImageHints(2, getResources().getDimensionPixelSize(R.dimen.cast_mini_controller_icon_width), getResources().getDimensionPixelSize(R.dimen.cast_mini_controller_icon_height)), R.drawable.cast_album_art_placeholder);
        } else {
            imageView.setVisibility(8);
        }
        this.zzfjn[0] = (ImageView) relativeLayout.findViewById(R.id.button_0);
        this.zzfjn[1] = (ImageView) relativeLayout.findViewById(R.id.button_1);
        this.zzfjn[2] = (ImageView) relativeLayout.findViewById(R.id.button_2);
        zza(relativeLayout, R.id.button_0, 0);
        zza(relativeLayout, R.id.button_1, 1);
        zza(relativeLayout, R.id.button_2, 2);
        return inflate;
    }

    public void onDestroy() {
        UIMediaController uIMediaController = this.zzfjt;
        if (uIMediaController != null) {
            uIMediaController.dispose();
            this.zzfjt = null;
        }
        super.onDestroy();
    }

    public void onInflate(Context context, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(context, attributeSet, bundle);
        if (this.zzfjm == null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CastMiniController, R.attr.castMiniControllerStyle, R.style.CastMiniController);
            this.zzfjw = obtainStyledAttributes.getBoolean(R.styleable.CastMiniController_castShowImageThumbnail, true);
            this.zzfjx = obtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castTitleTextAppearance, 0);
            this.zzfjy = obtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castSubtitleTextAppearance, 0);
            this.zzfka = obtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castBackground, 0);
            this.zzfkb = obtainStyledAttributes.getColor(R.styleable.CastMiniController_castProgressBarColor, 0);
            this.zzfjh = obtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castButtonColor, 0);
            this.zzfiy = obtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castPlayButtonDrawable, 0);
            this.zzfiz = obtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castPauseButtonDrawable, 0);
            this.zzfja = obtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castStopButtonDrawable, 0);
            this.zzfkd = obtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castPlayButtonDrawable, 0);
            this.zzfke = obtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castPauseButtonDrawable, 0);
            this.zzfkf = obtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castStopButtonDrawable, 0);
            this.zzfjb = obtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castSkipPreviousButtonDrawable, 0);
            this.zzfjc = obtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castSkipNextButtonDrawable, 0);
            this.zzfjd = obtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castRewind30ButtonDrawable, 0);
            this.zzfje = obtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castForward30ButtonDrawable, 0);
            this.zzfjf = obtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castMuteToggleButtonDrawable, 0);
            this.zzfjg = obtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castClosedCaptionsButtonDrawable, 0);
            int resourceId = obtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castControlButtons, 0);
            if (resourceId != 0) {
                TypedArray obtainTypedArray = context.getResources().obtainTypedArray(resourceId);
                zzbq.checkArgument(obtainTypedArray.length() == 3);
                this.zzfjm = new int[obtainTypedArray.length()];
                for (int i = 0; i < obtainTypedArray.length(); i++) {
                    this.zzfjm[i] = obtainTypedArray.getResourceId(i, 0);
                }
                obtainTypedArray.recycle();
                if (this.zzfjw) {
                    this.zzfjm[0] = R.id.cast_button_type_empty;
                }
                this.zzfkc = 0;
                for (int i2 : this.zzfjm) {
                    if (i2 != R.id.cast_button_type_empty) {
                        this.zzfkc++;
                    }
                }
            } else {
                zzeui.zzf("Unable to read attribute castControlButtons.", new Object[0]);
                this.zzfjm = new int[]{R.id.cast_button_type_empty, R.id.cast_button_type_empty, R.id.cast_button_type_empty};
            }
            obtainStyledAttributes.recycle();
        }
    }
}
