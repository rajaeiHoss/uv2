package com.google.android.gms.internal;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@zzabh
public final class zzxf extends zzwv {
    private final NativeContentAdMapper zzckg;

    public zzxf(NativeContentAdMapper nativeContentAdMapper) {
        this.zzckg = nativeContentAdMapper;
    }

    public final String getAdvertiser() {
        return this.zzckg.getAdvertiser();
    }

    public final String getBody() {
        return this.zzckg.getBody();
    }

    public final String getCallToAction() {
        return this.zzckg.getCallToAction();
    }

    public final Bundle getExtras() {
        return this.zzckg.getExtras();
    }

    public final String getHeadline() {
        return this.zzckg.getHeadline();
    }

    public final List getImages() {
        List<NativeAd.Image> images = this.zzckg.getImages();
        if (images == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (NativeAd.Image next : images) {
            arrayList.add(new zzpj(next.getDrawable(), next.getUri(), next.getScale()));
        }
        return arrayList;
    }

    public final boolean getOverrideClickHandling() {
        return this.zzckg.getOverrideClickHandling();
    }

    public final boolean getOverrideImpressionRecording() {
        return this.zzckg.getOverrideImpressionRecording();
    }

    public final zzmm getVideoController() {
        if (this.zzckg.getVideoController() != null) {
            return this.zzckg.getVideoController().zzbh();
        }
        return null;
    }

    public final void recordImpression() {
        this.zzckg.recordImpression();
    }

    public final void zzb(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        this.zzckg.trackViews((View) zzn.zzy(iObjectWrapper), (HashMap) zzn.zzy(iObjectWrapper2), (HashMap) zzn.zzy(iObjectWrapper3));
    }

    public final void zzh(IObjectWrapper iObjectWrapper) {
        this.zzckg.handleClick((View) zzn.zzy(iObjectWrapper));
    }

    public final void zzi(IObjectWrapper iObjectWrapper) {
        this.zzckg.trackView((View) zzn.zzy(iObjectWrapper));
    }

    public final void zzj(IObjectWrapper iObjectWrapper) {
        this.zzckg.untrackView((View) zzn.zzy(iObjectWrapper));
    }

    public final IObjectWrapper zzkh() {
        return null;
    }

    public final zzqo zzki() {
        return null;
    }

    public final zzqs zzkj() {
        NativeAd.Image logo = this.zzckg.getLogo();
        if (logo != null) {
            return new zzpj(logo.getDrawable(), logo.getUri(), logo.getScale());
        }
        return null;
    }

    public final IObjectWrapper zzmw() {
        View adChoicesContent = this.zzckg.getAdChoicesContent();
        if (adChoicesContent == null) {
            return null;
        }
        return zzn.zzz(adChoicesContent);
    }

    public final IObjectWrapper zzmx() {
        View zzvq = this.zzckg.zzvq();
        if (zzvq == null) {
            return null;
        }
        return zzn.zzz(zzvq);
    }
}
