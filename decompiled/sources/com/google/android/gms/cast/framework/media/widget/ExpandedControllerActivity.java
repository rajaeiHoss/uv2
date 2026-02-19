package com.google.android.gms.cast.framework.media.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.android.gms.R;
import com.google.android.gms.cast.AdBreakClipInfo;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIMediaController;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.internal.zzbax;
import com.google.android.gms.internal.zzbbm;
import com.google.android.gms.internal.zzbbt;

public class ExpandedControllerActivity extends AppCompatActivity implements ControlButtonsContainer {
    private SessionManager zzezv;
    private final RemoteMediaClient.Listener zzfhm = new zza(this, (zza) null);
    private SeekBar zzfhy;
    private final SessionManagerListener<CastSession> zzfit = new zzb(this, (zza) null);
    private int zzfiu;
    private int zzfiv;
    private int zzfiw;
    private int zzfix;
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
    /* access modifiers changed from: private */
    public TextView zzfji;
    private ImageView zzfjj;
    private ImageView zzfjk;
    private zzbbm zzfjl;
    private int[] zzfjm;
    private ImageView[] zzfjn = new ImageView[4];
    private View zzfjo;
    /* access modifiers changed from: private */
    public ImageView zzfjp;
    /* access modifiers changed from: private */
    public TextView zzfjq;
    private TextView zzfjr;
    private zzbax zzfjs;
    private UIMediaController zzfjt;
    /* access modifiers changed from: private */
    public boolean zzfju;

    class zza implements RemoteMediaClient.Listener {
        private zza() {
        }

        /* synthetic */ zza(ExpandedControllerActivity expandedControllerActivity, zza zza) {
            this();
        }

        public final void onAdBreakStatusUpdated() {
            ExpandedControllerActivity.this.zzagf();
        }

        public final void onMetadataUpdated() {
            ExpandedControllerActivity.this.zzagd();
        }

        public final void onPreloadStatusUpdated() {
        }

        public final void onQueueStatusUpdated() {
        }

        public final void onSendingRemoteMediaRequest() {
            ExpandedControllerActivity.this.zzfji.setText(ExpandedControllerActivity.this.getResources().getString(R.string.cast_expanded_controller_loading));
        }

        public final void onStatusUpdated() {
            RemoteMediaClient zzc = ExpandedControllerActivity.this.getRemoteMediaClient();
            if (zzc != null && zzc.hasMediaSession()) {
                boolean unused = ExpandedControllerActivity.this.zzfju = false;
                ExpandedControllerActivity.this.zzage();
                ExpandedControllerActivity.this.zzagf();
            } else if (!ExpandedControllerActivity.this.zzfju) {
                ExpandedControllerActivity.this.finish();
            }
        }
    }

    class zzb implements SessionManagerListener<CastSession> {
        private zzb() {
        }

        /* synthetic */ zzb(ExpandedControllerActivity expandedControllerActivity, zza zza) {
            this();
        }

        public final void onSessionEnded(CastSession castSession, int i) {
            ExpandedControllerActivity.this.finish();
        }

        public final void onSessionEnding(CastSession castSession) {
        }

        public final void onSessionResumeFailed(CastSession castSession, int i) {
        }

        public final void onSessionResumed(CastSession castSession, boolean z) {
        }

        public final void onSessionResuming(CastSession castSession, String str) {
        }

        public final void onSessionStartFailed(CastSession castSession, int i) {
        }

        public final void onSessionStarted(CastSession castSession, String str) {
        }

        public final void onSessionStarting(CastSession castSession) {
        }

        public final void onSessionSuspended(CastSession castSession, int i) {
        }
    }

    /* access modifiers changed from: private */
    public final RemoteMediaClient getRemoteMediaClient() {
        CastSession currentCastSession = this.zzezv.getCurrentCastSession();
        if (currentCastSession == null || !currentCastSession.isConnected()) {
            return null;
        }
        return currentCastSession.getRemoteMediaClient();
    }

    private final void zza(View view, int i, int i2, UIMediaController uIMediaController) {
        ImageView imageView = (ImageView) view.findViewById(i);
        if (i2 == R.id.cast_button_type_empty) {
            imageView.setVisibility(4);
        } else if (i2 == R.id.cast_button_type_custom) {
        } else {
            if (i2 == R.id.cast_button_type_play_pause_toggle) {
                imageView.setBackgroundResource(this.zzfiu);
                Drawable zzb2 = com.google.android.gms.cast.framework.media.widget.zzb.zzb(this, this.zzfjh, this.zzfiz);
                Drawable zzb3 = com.google.android.gms.cast.framework.media.widget.zzb.zzb(this, this.zzfjh, this.zzfiy);
                Drawable zzb4 = com.google.android.gms.cast.framework.media.widget.zzb.zzb(this, this.zzfjh, this.zzfja);
                imageView.setImageDrawable(zzb3);
                uIMediaController.bindImageViewToPlayPauseToggle(imageView, zzb3, zzb2, zzb4, (View) null, false);
            } else if (i2 == R.id.cast_button_type_skip_previous) {
                imageView.setBackgroundResource(this.zzfiu);
                imageView.setImageDrawable(com.google.android.gms.cast.framework.media.widget.zzb.zzb(this, this.zzfjh, this.zzfjb));
                imageView.setContentDescription(getResources().getString(R.string.cast_skip_prev));
                uIMediaController.bindViewToSkipPrev(imageView, 0);
            } else if (i2 == R.id.cast_button_type_skip_next) {
                imageView.setBackgroundResource(this.zzfiu);
                imageView.setImageDrawable(com.google.android.gms.cast.framework.media.widget.zzb.zzb(this, this.zzfjh, this.zzfjc));
                imageView.setContentDescription(getResources().getString(R.string.cast_skip_next));
                uIMediaController.bindViewToSkipNext(imageView, 0);
            } else if (i2 == R.id.cast_button_type_rewind_30_seconds) {
                imageView.setBackgroundResource(this.zzfiu);
                imageView.setImageDrawable(com.google.android.gms.cast.framework.media.widget.zzb.zzb(this, this.zzfjh, this.zzfjd));
                imageView.setContentDescription(getResources().getString(R.string.cast_rewind_30));
                uIMediaController.bindViewToRewind(imageView, NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);
            } else if (i2 == R.id.cast_button_type_forward_30_seconds) {
                imageView.setBackgroundResource(this.zzfiu);
                imageView.setImageDrawable(com.google.android.gms.cast.framework.media.widget.zzb.zzb(this, this.zzfjh, this.zzfje));
                imageView.setContentDescription(getResources().getString(R.string.cast_forward_30));
                uIMediaController.bindViewToForward(imageView, NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);
            } else if (i2 == R.id.cast_button_type_mute_toggle) {
                imageView.setBackgroundResource(this.zzfiu);
                imageView.setImageDrawable(com.google.android.gms.cast.framework.media.widget.zzb.zzb(this, this.zzfjh, this.zzfjf));
                uIMediaController.bindImageViewToMuteToggle(imageView);
            } else if (i2 == R.id.cast_button_type_closed_caption) {
                imageView.setBackgroundResource(this.zzfiu);
                imageView.setImageDrawable(com.google.android.gms.cast.framework.media.widget.zzb.zzb(this, this.zzfjh, this.zzfjg));
                uIMediaController.bindViewToClosedCaption(imageView);
            }
        }
    }

    private final ColorStateList zzagc() {
        int color = getResources().getColor(this.zzfiv);
        TypedValue typedValue = new TypedValue();
        getResources().getValue(R.dimen.cast_expanded_controller_seekbar_disabled_alpha, typedValue, true);
        int[] iArr = {color, Color.argb((int) (typedValue.getFloat() * ((float) Color.alpha(color))), Color.red(color), Color.green(color), Color.blue(color))};
        return new ColorStateList(new int[][]{new int[]{16842910}, new int[]{-16842910}}, iArr);
    }

    /* access modifiers changed from: private */
    public final void zzagd() {
        MediaInfo mediaInfo;
        MediaMetadata metadata;
        ActionBar supportActionBar;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession() && (mediaInfo = remoteMediaClient.getMediaInfo()) != null && (metadata = mediaInfo.getMetadata()) != null && (supportActionBar = getSupportActionBar()) != null) {
            supportActionBar.setTitle((CharSequence) metadata.getString(MediaMetadata.KEY_TITLE));
        }
    }

    /* access modifiers changed from: private */
    public final void zzage() {
        CastDevice castDevice;
        CastSession currentCastSession = this.zzezv.getCurrentCastSession();
        if (!(currentCastSession == null || (castDevice = currentCastSession.getCastDevice()) == null)) {
            String friendlyName = castDevice.getFriendlyName();
            if (!TextUtils.isEmpty(friendlyName)) {
                this.zzfji.setText(getResources().getString(R.string.cast_casting_to_device, new Object[]{friendlyName}));
                return;
            }
        }
        this.zzfji.setText("");
    }

    /* access modifiers changed from: private */
    public final void zzagf() {
        String str;
        Drawable drawable;
        Bitmap bitmap;
        Bitmap zza2;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        String str2 = null;
        MediaInfo mediaInfo = remoteMediaClient == null ? null : remoteMediaClient.getMediaInfo();
        MediaStatus mediaStatus = remoteMediaClient == null ? null : remoteMediaClient.getMediaStatus();
        if (mediaStatus != null && mediaStatus.isPlayingAd()) {
            if (zzs.zzant() && this.zzfjk.getVisibility() == 8 && (drawable = this.zzfjj.getDrawable()) != null && (drawable instanceof BitmapDrawable) && (bitmap = ((BitmapDrawable) drawable).getBitmap()) != null && (zza2 = com.google.android.gms.cast.framework.media.widget.zzb.zza(this, bitmap, 0.25f, 7.5f)) != null) {
                this.zzfjk.setImageBitmap(zza2);
                this.zzfjk.setVisibility(0);
            }
            AdBreakClipInfo currentAdBreakClip = mediaStatus.getCurrentAdBreakClip();
            if (currentAdBreakClip != null) {
                String title = currentAdBreakClip.getTitle();
                str2 = currentAdBreakClip.getImageUrl();
                str = title;
            } else {
                str = null;
            }
            this.zzfjq.setVisibility(0);
            if (!TextUtils.isEmpty(str2)) {
                this.zzfjs.zzl(Uri.parse(str2));
            } else {
                this.zzfjp.setVisibility(8);
            }
            TextView textView = this.zzfjr;
            if (TextUtils.isEmpty(str)) {
                str = getResources().getString(R.string.cast_ad_label);
            }
            textView.setText(str);
            this.zzfhy.setEnabled(false);
            this.zzfjo.setVisibility(0);
        } else {
            this.zzfhy.setEnabled(true);
            this.zzfjo.setVisibility(8);
            if (zzs.zzant()) {
                this.zzfjk.setVisibility(8);
                this.zzfjk.setImageBitmap((Bitmap) null);
            }
        }
        if (mediaInfo != null) {
            this.zzfjl.zzbi(this.zzfhy.getMax());
            this.zzfjl.zzb(mediaInfo.getAdBreaks(), -1);
        }
    }

    public final ImageView getButtonImageViewAt(int i) throws IndexOutOfBoundsException {
        return this.zzfjn[i];
    }

    public final int getButtonSlotCount() {
        return 4;
    }

    public final int getButtonTypeAt(int i) throws IndexOutOfBoundsException {
        return this.zzfjm[i];
    }

    public SeekBar getSeekBar() {
        return this.zzfhy;
    }

    public TextView getStatusTextView() {
        return this.zzfji;
    }

    public UIMediaController getUIMediaController() {
        return this.zzfjt;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        SessionManager sessionManager = CastContext.getSharedInstance(this).getSessionManager();
        this.zzezv = sessionManager;
        if (sessionManager.getCurrentCastSession() == null) {
            finish();
        }
        UIMediaController uIMediaController = new UIMediaController(this);
        this.zzfjt = uIMediaController;
        uIMediaController.setPostRemoteMediaClientListener(this.zzfhm);
        setContentView(R.layout.cast_expanded_controller_activity);
        TypedArray obtainStyledAttributes = obtainStyledAttributes(new int[]{androidx.appcompat.R.attr.selectableItemBackgroundBorderless, androidx.appcompat.R.attr.colorControlActivated});
        this.zzfiu = obtainStyledAttributes.getResourceId(0, 0);
        this.zzfiv = obtainStyledAttributes.getResourceId(1, 0);
        obtainStyledAttributes.recycle();
        ColorStateList colorStateList = null;
        TypedArray obtainStyledAttributes2 = obtainStyledAttributes((AttributeSet) null, R.styleable.CastExpandedController, R.attr.castExpandedControllerStyle, R.style.CastExpandedController);
        this.zzfjh = obtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castButtonColor, 0);
        this.zzfiw = obtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castSeekBarProgressDrawable, 0);
        this.zzfix = obtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castSeekBarThumbDrawable, 0);
        this.zzfiy = obtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castPlayButtonDrawable, 0);
        this.zzfiz = obtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castPauseButtonDrawable, 0);
        this.zzfja = obtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castStopButtonDrawable, 0);
        this.zzfjb = obtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castSkipPreviousButtonDrawable, 0);
        this.zzfjc = obtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castSkipNextButtonDrawable, 0);
        this.zzfjd = obtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castRewind30ButtonDrawable, 0);
        this.zzfje = obtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castForward30ButtonDrawable, 0);
        this.zzfjf = obtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castMuteToggleButtonDrawable, 0);
        this.zzfjg = obtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castClosedCaptionsButtonDrawable, 0);
        int resourceId = obtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castControlButtons, 0);
        if (resourceId != 0) {
            TypedArray obtainTypedArray = getResources().obtainTypedArray(resourceId);
            zzbq.checkArgument(obtainTypedArray.length() == 4);
            this.zzfjm = new int[obtainTypedArray.length()];
            for (int i = 0; i < obtainTypedArray.length(); i++) {
                this.zzfjm[i] = obtainTypedArray.getResourceId(i, 0);
            }
            obtainTypedArray.recycle();
        } else {
            this.zzfjm = new int[]{R.id.cast_button_type_empty, R.id.cast_button_type_empty, R.id.cast_button_type_empty, R.id.cast_button_type_empty};
        }
        obtainStyledAttributes2.recycle();
        View findViewById = findViewById(R.id.expanded_controller_layout);
        UIMediaController uIMediaController2 = this.zzfjt;
        this.zzfjj = (ImageView) findViewById.findViewById(R.id.background_image_view);
        this.zzfjk = (ImageView) findViewById.findViewById(R.id.blurred_background_image_view);
        View findViewById2 = findViewById.findViewById(R.id.background_place_holder_image_view);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        uIMediaController2.bindImageViewToImageOfCurrentItem(this.zzfjj, new ImageHints(4, displayMetrics.widthPixels, displayMetrics.heightPixels), findViewById2);
        this.zzfji = (TextView) findViewById.findViewById(R.id.status_text);
        uIMediaController2.bindViewToLoadingIndicator((ProgressBar) findViewById.findViewById(R.id.loading_indicator));
        TextView textView = (TextView) findViewById.findViewById(R.id.start_text);
        TextView textView2 = (TextView) findViewById.findViewById(R.id.end_text);
        ImageView imageView = (ImageView) findViewById.findViewById(R.id.live_stream_indicator);
        this.zzfhy = (SeekBar) findViewById.findViewById(R.id.seek_bar);
        Drawable drawable = getResources().getDrawable(this.zzfiw);
        if (drawable != null) {
            if (this.zzfiw == R.drawable.cast_expanded_controller_seekbar_track) {
                colorStateList = zzagc();
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                Drawable wrap = DrawableCompat.wrap(layerDrawable.findDrawableByLayerId(16908301));
                DrawableCompat.setTintList(wrap, colorStateList);
                layerDrawable.setDrawableByLayerId(16908301, wrap);
                layerDrawable.findDrawableByLayerId(16908288).setColorFilter(getResources().getColor(R.color.cast_expanded_controller_seek_bar_progress_background_tint_color), PorterDuff.Mode.SRC_IN);
            }
            this.zzfhy.setProgressDrawable(drawable);
        }
        Drawable drawable2 = getResources().getDrawable(this.zzfix);
        if (drawable2 != null) {
            if (this.zzfix == R.drawable.cast_expanded_controller_seekbar_thumb) {
                if (colorStateList == null) {
                    colorStateList = zzagc();
                }
                drawable2 = DrawableCompat.wrap(drawable2);
                DrawableCompat.setTintList(drawable2, colorStateList);
            }
            this.zzfhy.setThumb(drawable2);
        }
        if (zzs.zzanx()) {
            this.zzfhy.setSplitTrack(false);
        }
        SeekBar seekBar = (SeekBar) findViewById.findViewById(R.id.live_stream_seek_bar);
        uIMediaController2.bindTextViewToStreamPosition(textView, true);
        uIMediaController2.bindTextViewToStreamDuration(textView2, imageView);
        uIMediaController2.bindSeekBar(this.zzfhy);
        uIMediaController2.bindViewToUIController(seekBar, new zzbbt(seekBar, this.zzfhy));
        this.zzfjn[0] = (ImageView) findViewById.findViewById(R.id.button_0);
        this.zzfjn[1] = (ImageView) findViewById.findViewById(R.id.button_1);
        this.zzfjn[2] = (ImageView) findViewById.findViewById(R.id.button_2);
        this.zzfjn[3] = (ImageView) findViewById.findViewById(R.id.button_3);
        zza(findViewById, R.id.button_0, this.zzfjm[0], uIMediaController2);
        zza(findViewById, R.id.button_1, this.zzfjm[1], uIMediaController2);
        zza(findViewById, R.id.button_play_pause_toggle, R.id.cast_button_type_play_pause_toggle, uIMediaController2);
        zza(findViewById, R.id.button_2, this.zzfjm[2], uIMediaController2);
        zza(findViewById, R.id.button_3, this.zzfjm[3], uIMediaController2);
        View findViewById3 = findViewById(R.id.ad_container);
        this.zzfjo = findViewById3;
        this.zzfjp = (ImageView) findViewById3.findViewById(R.id.ad_image_view);
        this.zzfjr = (TextView) this.zzfjo.findViewById(R.id.ad_label);
        this.zzfjq = (TextView) this.zzfjo.findViewById(R.id.ad_in_progress_label);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById.findViewById(R.id.seek_bar_controls);
        zzbbm zzbbm = new zzbbm(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(0, R.id.end_text);
        layoutParams.addRule(1, R.id.start_text);
        layoutParams.addRule(6, R.id.seek_bar);
        layoutParams.addRule(7, R.id.seek_bar);
        layoutParams.addRule(5, R.id.seek_bar);
        layoutParams.addRule(8, R.id.seek_bar);
        zzbbm.setLayoutParams(layoutParams);
        if (zzs.zzant()) {
            zzbbm.setPaddingRelative(this.zzfhy.getPaddingStart(), this.zzfhy.getPaddingTop(), this.zzfhy.getPaddingEnd(), this.zzfhy.getPaddingBottom());
        } else {
            zzbbm.setPadding(this.zzfhy.getPaddingLeft(), this.zzfhy.getPaddingTop(), this.zzfhy.getPaddingRight(), this.zzfhy.getPaddingBottom());
        }
        zzbbm.setContentDescription(getResources().getString(R.string.cast_seek_bar));
        zzbbm.setBackgroundColor(0);
        relativeLayout.addView(zzbbm);
        this.zzfjl = zzbbm;
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.quantum_ic_keyboard_arrow_down_white_36);
        }
        zzage();
        zzagd();
        zzbax zzbax = new zzbax(getApplicationContext(), new ImageHints(-1, this.zzfjp.getWidth(), this.zzfjp.getHeight()));
        this.zzfjs = zzbax;
        zzbax.zza(new com.google.android.gms.cast.framework.media.widget.zza(this));
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.zzfjs.clear();
        UIMediaController uIMediaController = this.zzfjt;
        if (uIMediaController != null) {
            uIMediaController.setPostRemoteMediaClientListener((RemoteMediaClient.Listener) null);
            this.zzfjt.dispose();
        }
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return true;
        }
        finish();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        CastContext.getSharedInstance(this).getSessionManager().removeSessionManagerListener(this.zzfit, CastSession.class);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        CastContext.getSharedInstance(this).getSessionManager().addSessionManagerListener(this.zzfit, CastSession.class);
        CastSession currentCastSession = CastContext.getSharedInstance(this).getSessionManager().getCurrentCastSession();
        if (currentCastSession == null || (!currentCastSession.isConnected() && !currentCastSession.isConnecting())) {
            finish();
        }
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        this.zzfju = remoteMediaClient == null || !remoteMediaClient.hasMediaSession();
        zzage();
        zzagf();
        super.onResume();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            int systemUiVisibility = getWindow().getDecorView().getSystemUiVisibility() ^ 2;
            if (zzs.zzans()) {
                systemUiVisibility ^= 4;
            }
            if (zzs.zzanv()) {
                systemUiVisibility ^= 4096;
            }
            getWindow().getDecorView().setSystemUiVisibility(systemUiVisibility);
            if (zzs.zzanu()) {
                setImmersive(true);
            }
        }
    }
}
