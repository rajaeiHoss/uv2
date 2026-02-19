package com.google.android.gms.cast.framework.media.uicontroller;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.gms.R;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.TracksChooserDialogFragment;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbbn;
import com.google.android.gms.internal.zzbbo;
import com.google.android.gms.internal.zzbbq;
import com.google.android.gms.internal.zzbbs;
import com.google.android.gms.internal.zzbbv;
import com.google.android.gms.internal.zzbbw;
import com.google.android.gms.internal.zzbbx;
import com.google.android.gms.internal.zzbby;
import com.google.android.gms.internal.zzbca;
import com.google.android.gms.internal.zzbcb;
import com.google.android.gms.internal.zzbcc;
import com.google.android.gms.internal.zzbcd;
import com.google.android.gms.internal.zzbce;
import com.google.android.gms.internal.zzbcf;
import com.google.android.gms.internal.zzbcg;
import com.google.android.gms.internal.zzbch;
import com.google.android.gms.internal.zzbci;
import com.google.android.gms.internal.zzbcj;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbei;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

public class UIMediaController implements SessionManagerListener<CastSession>, RemoteMediaClient.Listener {
    private static final zzbei zzeui = new zzbei("UIMediaController");
    private final Activity mActivity;
    private final SessionManager zzezv;
    private RemoteMediaClient zzfaq;
    private final Map<View, List<UIController>> zzfhk = new HashMap();
    private final Set<zzbci> zzfhl = new HashSet();
    private RemoteMediaClient.Listener zzfhm;

    public UIMediaController(Activity activity) {
        this.mActivity = activity;
        CastContext zzbu = CastContext.zzbu(activity);
        SessionManager sessionManager = zzbu != null ? zzbu.getSessionManager() : null;
        this.zzezv = sessionManager;
        if (sessionManager != null) {
            SessionManager sessionManager2 = CastContext.getSharedInstance(activity).getSessionManager();
            sessionManager2.addSessionManagerListener(this, CastSession.class);
            zza(sessionManager2.getCurrentCastSession());
        }
    }

    private final void zza(View view, UIController uIController) {
        if (this.zzezv != null) {
            List list = this.zzfhk.get(view);
            if (list == null) {
                list = new ArrayList();
                this.zzfhk.put(view, list);
            }
            list.add(uIController);
            if (isActive()) {
                uIController.onSessionConnected(this.zzezv.getCurrentCastSession());
                zzafx();
            }
        }
    }

    private final void zza(Session session) {
        if (!isActive() && (session instanceof CastSession) && session.isConnected()) {
            CastSession castSession = (CastSession) session;
            RemoteMediaClient remoteMediaClient = castSession.getRemoteMediaClient();
            this.zzfaq = remoteMediaClient;
            if (remoteMediaClient != null) {
                remoteMediaClient.addListener(this);
                for (List<UIController> it : this.zzfhk.values()) {
                    for (UIController onSessionConnected : it) {
                        onSessionConnected.onSessionConnected(castSession);
                    }
                }
                zzafx();
            }
        }
    }

    private final void zzafw() {
        if (isActive()) {
            for (List<UIController> it : this.zzfhk.values()) {
                for (UIController onSessionEnded : it) {
                    onSessionEnded.onSessionEnded();
                }
            }
            this.zzfaq.removeListener(this);
            this.zzfaq = null;
        }
    }

    private final void zzafx() {
        for (List<UIController> it : this.zzfhk.values()) {
            for (UIController onMediaStatusUpdated : it) {
                onMediaStatusUpdated.onMediaStatusUpdated();
            }
        }
    }

    @Deprecated
    public void bindImageViewToImageOfCurrentItem(ImageView imageView, int i, int i2) {
        zzbq.zzgn("Must be called from the main thread.");
        zza(imageView, new zzbbq(imageView, this.mActivity, new ImageHints(i, 0, 0), i2, (View) null));
    }

    @Deprecated
    public void bindImageViewToImageOfCurrentItem(ImageView imageView, int i, View view) {
        zzbq.zzgn("Must be called from the main thread.");
        zza(imageView, new zzbbq(imageView, this.mActivity, new ImageHints(i, 0, 0), 0, view));
    }

    public void bindImageViewToImageOfCurrentItem(ImageView imageView, ImageHints imageHints, int i) {
        zzbq.zzgn("Must be called from the main thread.");
        zza(imageView, new zzbbq(imageView, this.mActivity, imageHints, i, (View) null));
    }

    public void bindImageViewToImageOfCurrentItem(ImageView imageView, ImageHints imageHints, View view) {
        zzbq.zzgn("Must be called from the main thread.");
        zza(imageView, new zzbbq(imageView, this.mActivity, imageHints, 0, view));
    }

    @Deprecated
    public void bindImageViewToImageOfPreloadedItem(ImageView imageView, int i, int i2) {
        zzbq.zzgn("Must be called from the main thread.");
        zza(imageView, new zzbbo(imageView, this.mActivity, new ImageHints(i, 0, 0), i2));
    }

    public void bindImageViewToImageOfPreloadedItem(ImageView imageView, ImageHints imageHints, int i) {
        zzbq.zzgn("Must be called from the main thread.");
        zza(imageView, new zzbbo(imageView, this.mActivity, imageHints, i));
    }

    public void bindImageViewToMuteToggle(ImageView imageView) {
        zzbq.zzgn("Must be called from the main thread.");
        imageView.setOnClickListener(new zzi(this));
        zza(imageView, new zzbby(imageView, this.mActivity));
    }

    public void bindImageViewToPlayPauseToggle(ImageView imageView, Drawable drawable, Drawable drawable2, Drawable drawable3, View view, boolean z) {
        zzbq.zzgn("Must be called from the main thread.");
        imageView.setOnClickListener(new zza(this));
        zza(imageView, new zzbca(imageView, this.mActivity, drawable, drawable2, drawable3, view, z));
    }

    public void bindProgressBar(ProgressBar progressBar) {
        bindProgressBar(progressBar, 1000);
    }

    public void bindProgressBar(ProgressBar progressBar, long j) {
        zzbq.zzgn("Must be called from the main thread.");
        zza(progressBar, new zzbcb(progressBar, j));
    }

    public void bindSeekBar(SeekBar seekBar) {
        bindSeekBar(seekBar, 1000);
    }

    public void bindSeekBar(SeekBar seekBar, long j) {
        zzbq.zzgn("Must be called from the main thread.");
        seekBar.setOnSeekBarChangeListener(new zzf(this));
        zza(seekBar, new zzbcc(seekBar, j));
    }

    public void bindTextViewToMetadataOfCurrentItem(TextView textView, String str) {
        zzbq.zzgn("Must be called from the main thread.");
        bindTextViewToMetadataOfCurrentItem(textView, (List<String>) Collections.singletonList(str));
    }

    public void bindTextViewToMetadataOfCurrentItem(TextView textView, List<String> list) {
        zzbq.zzgn("Must be called from the main thread.");
        zza(textView, new zzbbx(textView, list));
    }

    public void bindTextViewToMetadataOfPreloadedItem(TextView textView, String str) {
        zzbq.zzgn("Must be called from the main thread.");
        bindTextViewToMetadataOfPreloadedItem(textView, (List<String>) Collections.singletonList(str));
    }

    public void bindTextViewToMetadataOfPreloadedItem(TextView textView, List<String> list) {
        zzbq.zzgn("Must be called from the main thread.");
        zza(textView, new zzbbw(textView, list));
    }

    public void bindTextViewToSmartSubtitle(TextView textView) {
        zzbq.zzgn("Must be called from the main thread.");
        zza(textView, new zzbcg(textView));
    }

    public void bindTextViewToStreamDuration(TextView textView) {
        zzbq.zzgn("Must be called from the main thread.");
        zza(textView, new zzbch(textView, this.mActivity.getString(R.string.cast_invalid_stream_duration_text), (View) null));
    }

    public void bindTextViewToStreamDuration(TextView textView, View view) {
        zzbq.zzgn("Must be called from the main thread.");
        zza(textView, new zzbch(textView, this.mActivity.getString(R.string.cast_invalid_stream_duration_text), view));
    }

    public void bindTextViewToStreamPosition(TextView textView, boolean z) {
        bindTextViewToStreamPosition(textView, z, 1000);
    }

    public void bindTextViewToStreamPosition(TextView textView, boolean z, long j) {
        zzbq.zzgn("Must be called from the main thread.");
        zzbci zzbci = new zzbci(textView, j, this.mActivity.getString(R.string.cast_invalid_stream_position_text));
        if (z) {
            this.zzfhl.add(zzbci);
        }
        zza(textView, zzbci);
    }

    public void bindViewToClosedCaption(View view) {
        zzbq.zzgn("Must be called from the main thread.");
        view.setOnClickListener(new zzh(this));
        zza(view, new zzbbn(view, this.mActivity));
    }

    public void bindViewToForward(View view, long j) {
        zzbq.zzgn("Must be called from the main thread.");
        view.setOnClickListener(new zzd(this, j));
        zza(view, new zzbcd(view));
    }

    public void bindViewToLaunchExpandedController(View view) {
        zzbq.zzgn("Must be called from the main thread.");
        view.setOnClickListener(new zzg(this));
        zza(view, new zzbbs(view));
    }

    public void bindViewToLoadingIndicator(View view) {
        zzbq.zzgn("Must be called from the main thread.");
        zza(view, new zzbbv(view));
    }

    public void bindViewToRewind(View view, long j) {
        zzbq.zzgn("Must be called from the main thread.");
        view.setOnClickListener(new zze(this, j));
        zza(view, new zzbcd(view));
    }

    public void bindViewToSkipNext(View view, int i) {
        zzbq.zzgn("Must be called from the main thread.");
        view.setOnClickListener(new zzb(this));
        zza(view, new zzbce(view, i));
    }

    public void bindViewToSkipPrev(View view, int i) {
        zzbq.zzgn("Must be called from the main thread.");
        view.setOnClickListener(new zzc(this));
        zza(view, new zzbcf(view, i));
    }

    public void bindViewToUIController(View view, UIController uIController) {
        zzbq.zzgn("Must be called from the main thread.");
        zza(view, uIController);
    }

    public void bindViewVisibilityToMediaSession(View view, int i) {
        zzbq.zzgn("Must be called from the main thread.");
        zza(view, new zzbck(view, i));
    }

    public void bindViewVisibilityToPreloadingEvent(View view, int i) {
        zzbq.zzgn("Must be called from the main thread.");
        zza(view, new zzbcj(view, i));
    }

    public void dispose() {
        zzbq.zzgn("Must be called from the main thread.");
        zzafw();
        this.zzfhk.clear();
        SessionManager sessionManager = this.zzezv;
        if (sessionManager != null) {
            sessionManager.removeSessionManagerListener(this, CastSession.class);
        }
        this.zzfhm = null;
    }

    public RemoteMediaClient getRemoteMediaClient() {
        zzbq.zzgn("Must be called from the main thread.");
        return this.zzfaq;
    }

    public boolean isActive() {
        zzbq.zzgn("Must be called from the main thread.");
        return this.zzfaq != null;
    }

    public void onAdBreakStatusUpdated() {
        zzafx();
        RemoteMediaClient.Listener listener = this.zzfhm;
        if (listener != null) {
            listener.onAdBreakStatusUpdated();
        }
    }

    /* access modifiers changed from: protected */
    public void onClosedCaptionClicked(View view) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession()) {
            Activity activity = this.mActivity;
            if (activity instanceof FragmentActivity) {
                FragmentActivity fragmentActivity = (FragmentActivity) activity;
                FragmentTransaction beginTransaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
                Fragment findFragmentByTag = fragmentActivity.getSupportFragmentManager().findFragmentByTag("TRACKS_CHOOSER_DIALOG_TAG");
                if (findFragmentByTag != null) {
                    beginTransaction.remove(findFragmentByTag);
                }
                beginTransaction.addToBackStack((String) null);
                TracksChooserDialogFragment newInstance = TracksChooserDialogFragment.newInstance(remoteMediaClient.getMediaInfo(), remoteMediaClient.getMediaStatus().getActiveTrackIds());
                if (newInstance != null) {
                    newInstance.show(beginTransaction, "TRACKS_CHOOSER_DIALOG_TAG");
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onForwardClicked(View view, long j) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession()) {
            remoteMediaClient.seek(remoteMediaClient.getApproximateStreamPosition() + j);
        }
    }

    /* access modifiers changed from: protected */
    public void onLaunchExpandedControllerClicked(View view) {
        ComponentName componentName = new ComponentName(this.mActivity.getApplicationContext(), CastContext.getSharedInstance(this.mActivity).getCastOptions().getCastMediaOptions().getExpandedControllerActivityClassName());
        Intent intent = new Intent();
        intent.setComponent(componentName);
        this.mActivity.startActivity(intent);
    }

    public void onMetadataUpdated() {
        zzafx();
        RemoteMediaClient.Listener listener = this.zzfhm;
        if (listener != null) {
            listener.onMetadataUpdated();
        }
    }

    /* access modifiers changed from: protected */
    public void onMuteToggleClicked(ImageView imageView) {
        CastSession currentCastSession = CastContext.getSharedInstance(this.mActivity.getApplicationContext()).getSessionManager().getCurrentCastSession();
        if (currentCastSession != null && currentCastSession.isConnected()) {
            try {
                currentCastSession.setMute(!currentCastSession.isMute());
            } catch (IOException | IllegalArgumentException e) {
                zzeui.zzc("Unable to call CastSession.setMute(boolean).", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onPlayPauseToggleClicked(ImageView imageView) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession()) {
            remoteMediaClient.togglePlayback();
        }
    }

    public void onPreloadStatusUpdated() {
        zzafx();
        RemoteMediaClient.Listener listener = this.zzfhm;
        if (listener != null) {
            listener.onPreloadStatusUpdated();
        }
    }

    public void onQueueStatusUpdated() {
        zzafx();
        RemoteMediaClient.Listener listener = this.zzfhm;
        if (listener != null) {
            listener.onQueueStatusUpdated();
        }
    }

    /* access modifiers changed from: protected */
    public void onRewindClicked(View view, long j) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession()) {
            remoteMediaClient.seek(remoteMediaClient.getApproximateStreamPosition() - j);
        }
    }

    /* access modifiers changed from: protected */
    public void onSeekBarProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            for (zzbci zzy : this.zzfhl) {
                zzy.zzy((long) i);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onSeekBarStartTrackingTouch(SeekBar seekBar) {
        if (this.zzfhk.containsKey(seekBar)) {
            for (UIController uIController : this.zzfhk.get(seekBar)) {
                if (uIController instanceof zzbcc) {
                    ((zzbcc) uIController).zzbf(false);
                }
            }
        }
        for (zzbci zzbf : this.zzfhl) {
            zzbf.zzbf(false);
        }
    }

    /* access modifiers changed from: protected */
    public void onSeekBarStopTrackingTouch(SeekBar seekBar) {
        if (this.zzfhk.containsKey(seekBar)) {
            for (UIController uIController : this.zzfhk.get(seekBar)) {
                if (uIController instanceof zzbcc) {
                    ((zzbcc) uIController).zzbf(true);
                }
            }
        }
        for (zzbci zzbf : this.zzfhl) {
            zzbf.zzbf(true);
        }
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession()) {
            remoteMediaClient.seek((long) seekBar.getProgress());
        }
    }

    public void onSendingRemoteMediaRequest() {
        for (List<UIController> it : this.zzfhk.values()) {
            for (UIController onSendingRemoteMediaRequest : it) {
                onSendingRemoteMediaRequest.onSendingRemoteMediaRequest();
            }
        }
        RemoteMediaClient.Listener listener = this.zzfhm;
        if (listener != null) {
            listener.onSendingRemoteMediaRequest();
        }
    }

    public void onSessionEnded(CastSession castSession, int i) {
        zzafw();
    }

    public void onSessionEnding(CastSession castSession) {
    }

    public void onSessionResumeFailed(CastSession castSession, int i) {
        zzafw();
    }

    public void onSessionResumed(CastSession castSession, boolean z) {
        zza(castSession);
    }

    public void onSessionResuming(CastSession castSession, String str) {
    }

    public void onSessionStartFailed(CastSession castSession, int i) {
        zzafw();
    }

    public void onSessionStarted(CastSession castSession, String str) {
        zza(castSession);
    }

    public void onSessionStarting(CastSession castSession) {
    }

    public void onSessionSuspended(CastSession castSession, int i) {
    }

    /* access modifiers changed from: protected */
    public void onSkipNextClicked(View view) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession()) {
            remoteMediaClient.queueNext((JSONObject) null);
        }
    }

    /* access modifiers changed from: protected */
    public void onSkipPrevClicked(View view) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession()) {
            remoteMediaClient.queuePrev((JSONObject) null);
        }
    }

    public void onStatusUpdated() {
        zzafx();
        RemoteMediaClient.Listener listener = this.zzfhm;
        if (listener != null) {
            listener.onStatusUpdated();
        }
    }

    public void setPostRemoteMediaClientListener(RemoteMediaClient.Listener listener) {
        zzbq.zzgn("Must be called from the main thread.");
        this.zzfhm = listener;
    }
}
