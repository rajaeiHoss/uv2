package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

@zzabh
public final class zzpu extends zzpz {
    private Object mLock;
    private zzwr zzbys;
    private zzwu zzbyt;
    private final zzpw zzbyu;
    private zzpv zzbyv;
    private boolean zzbyw;

    private zzpu(Context context, zzpw zzpw, zzcv zzcv, zzpx zzpx) {
        super(context, zzpw, (zzaan) null, zzcv, (JSONObject) null, zzpx, (zzala) null, (String) null);
        this.zzbyw = false;
        this.mLock = new Object();
        this.zzbyu = zzpw;
    }

    public zzpu(Context context, zzpw zzpw, zzcv zzcv, zzwr zzwr, zzpx zzpx) {
        this(context, zzpw, zzcv, zzpx);
        this.zzbys = zzwr;
    }

    public zzpu(Context context, zzpw zzpw, zzcv zzcv, zzwu zzwu, zzpx zzpx) {
        this(context, zzpw, zzcv, zzpx);
        this.zzbyt = zzwu;
    }

    private static HashMap<String, View> zzd(Map<String, WeakReference<View>> map) {
        HashMap<String, View> hashMap = new HashMap<>();
        if (map == null) {
            return hashMap;
        }
        synchronized (map) {
            for (Map.Entry next : map.entrySet()) {
                View view = (View) ((WeakReference) next.getValue()).get();
                if (view != null) {
                    hashMap.put((String) next.getKey(), view);
                }
            }
        }
        return hashMap;
    }

    public final View zza(View.OnClickListener onClickListener, boolean z) {
        IObjectWrapper iObjectWrapper = null;
        synchronized (this.mLock) {
            zzpv zzpv = this.zzbyv;
            if (zzpv != null) {
                View zza = zzpv.zza(onClickListener, z);
                return zza;
            }
            try {
                zzwr zzwr = this.zzbys;
                if (zzwr != null) {
                    iObjectWrapper = zzwr.zzmw();
                } else {
                    zzwu zzwu = this.zzbyt;
                    if (zzwu != null) {
                        iObjectWrapper = zzwu.zzmw();
                    }
                    iObjectWrapper = null;
                }
            } catch (RemoteException e) {
                zzahw.zzc("Failed to call getAdChoicesContent", e);
            }
            if (iObjectWrapper == null) {
                return null;
            }
            View view = (View) zzn.zzy(iObjectWrapper);
            return view;
        }
    }

    public final void zza(View view, Map<String, WeakReference<View>> map) {
        zzpw zzpw = null;
        zzbq.zzgn("recordImpression must be called on the main UI thread.");
        synchronized (this.mLock) {
            this.zzbza = true;
            zzpv zzpv = this.zzbyv;
            if (zzpv != null) {
                zzpv.zza(view, map);
                this.zzbyu.recordImpression();
            } else {
                try {
                    zzwr zzwr = this.zzbys;
                    if (zzwr == null || zzwr.getOverrideImpressionRecording()) {
                        zzwu zzwu = this.zzbyt;
                        if (zzwu != null && !zzwu.getOverrideImpressionRecording()) {
                            this.zzbyt.recordImpression();
                            zzpw = this.zzbyu;
                        }
                    } else {
                        this.zzbys.recordImpression();
                        zzpw = this.zzbyu;
                    }
                    if (zzpw != null) {
                        zzpw.recordImpression();
                    }
                } catch (RemoteException e) {
                    zzahw.zzc("Failed to call recordImpression", e);
                }
            }
        }
    }

    public final void zza(View view, Map<String, WeakReference<View>> map, Bundle bundle, View view2) {
        zzbq.zzgn("performClick must be called on the main UI thread.");
        synchronized (this.mLock) {
            zzpv zzpv = this.zzbyv;
            if (zzpv != null) {
                zzpv.zza(view, map, bundle, view2);
                this.zzbyu.onAdClicked();
            } else {
                try {
                    zzwr zzwr = this.zzbys;
                    if (zzwr != null && !zzwr.getOverrideClickHandling()) {
                        this.zzbys.zzh(zzn.zzz(view));
                        this.zzbyu.onAdClicked();
                    }
                    zzwu zzwu = this.zzbyt;
                    if (zzwu != null && !zzwu.getOverrideClickHandling()) {
                        this.zzbyt.zzh(zzn.zzz(view));
                        this.zzbyu.onAdClicked();
                    }
                } catch (RemoteException e) {
                    zzahw.zzc("Failed to call performClick", e);
                }
            }
        }
    }

    public final void zza(View view, Map<String, WeakReference<View>> map, Map<String, WeakReference<View>> map2, View.OnTouchListener onTouchListener, View.OnClickListener onClickListener) {
        synchronized (this.mLock) {
            this.zzbyw = true;
            HashMap<String, View> zzd = zzd(map);
            HashMap<String, View> zzd2 = zzd(map2);
            try {
                zzwr zzwr = this.zzbys;
                if (zzwr != null) {
                    zzwr.zzb(zzn.zzz(view), zzn.zzz(zzd), zzn.zzz(zzd2));
                    this.zzbys.zzi(zzn.zzz(view));
                } else {
                    zzwu zzwu = this.zzbyt;
                    if (zzwu != null) {
                        zzwu.zzb(zzn.zzz(view), zzn.zzz(zzd), zzn.zzz(zzd2));
                        this.zzbyt.zzi(zzn.zzz(view));
                    }
                }
            } catch (RemoteException e) {
                zzahw.zzc("Failed to call prepareAd", e);
            }
            this.zzbyw = false;
        }
    }

    public final void zzb(View view, Map<String, WeakReference<View>> map) {
        synchronized (this.mLock) {
            try {
                zzwr zzwr = this.zzbys;
                if (zzwr != null) {
                    zzwr.zzj(zzn.zzz(view));
                } else {
                    zzwu zzwu = this.zzbyt;
                    if (zzwu != null) {
                        zzwu.zzj(zzn.zzz(view));
                    }
                }
            } catch (RemoteException e) {
                zzahw.zzc("Failed to call untrackView", e);
            }
        }
    }

    public final void zzc(zzpv zzpv) {
        synchronized (this.mLock) {
            this.zzbyv = zzpv;
        }
    }

    public final boolean zzkm() {
        synchronized (this.mLock) {
            zzpv zzpv = this.zzbyv;
            if (zzpv != null) {
                boolean zzkm = zzpv.zzkm();
                return zzkm;
            }
            boolean zzcv = this.zzbyu.zzcv();
            return zzcv;
        }
    }

    public final boolean zzkn() {
        synchronized (this.mLock) {
            zzpv zzpv = this.zzbyv;
            if (zzpv != null) {
                boolean zzkn = zzpv.zzkn();
                return zzkn;
            }
            boolean zzcw = this.zzbyu.zzcw();
            return zzcw;
        }
    }

    public final boolean zzko() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzbyw;
        }
        return z;
    }

    public final zzpv zzkp() {
        zzpv zzpv;
        synchronized (this.mLock) {
            zzpv = this.zzbyv;
        }
        return zzpv;
    }

    public final zzaof zzkq() {
        return null;
    }

    public final void zzkr() {
    }

    public final void zzks() {
    }
}
