package com.google.android.gms.ads.internal;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzaer;
import com.google.android.gms.internal.zzafy;
import com.google.android.gms.internal.zzafz;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzajc;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzmi;
import com.google.android.gms.internal.zzoi;
import com.google.android.gms.internal.zzvp;
import com.google.android.gms.internal.zzvq;
import com.google.android.gms.internal.zzwi;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@zzabh
public final class zzax extends zzmi {
    private static final Object sLock = new Object();
    private static zzax zzare;
    private final Context mContext;
    private final Object mLock = new Object();
    private boolean zzarf;
    private zzala zzarg;

    private zzax(Context context, zzala zzala) {
        this.mContext = context;
        this.zzarg = zzala;
        this.zzarf = false;
    }

    public static zzax zza(Context context, zzala zzala) {
        zzax zzax;
        synchronized (sLock) {
            if (zzare == null) {
                zzare = new zzax(context.getApplicationContext(), zzala);
            }
            zzax = zzare;
        }
        return zzax;
    }

    public final void initialize() {
        synchronized (sLock) {
            if (this.zzarf) {
                zzahw.zzcz("Mobile ads is initialized already.");
                return;
            }
            this.zzarf = true;
            zzoi.initialize(this.mContext);
            zzbt.zzep().zzd(this.mContext, this.zzarg);
            zzbt.zzer().initialize(this.mContext);
        }
    }

    public final void setAppMuted(boolean z) {
        zzbt.zzfj().setAppMuted(z);
    }

    public final void setAppVolume(float f) {
        zzbt.zzfj().setAppVolume(f);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Runnable runnable) {
        Context context = this.mContext;
        zzbq.zzgn("Adapters must be initialized on the main thread.");
        Map<String, zzvq> zzpt = zzbt.zzep().zzqe().zzqv().zzpt();
        if (zzpt != null && !zzpt.isEmpty()) {
            if (runnable != null) {
                try {
                    runnable.run();
                } catch (Throwable th) {
                    zzahw.zzc("Could not initialize rewarded ads.", th);
                    return;
                }
            }
            zzaer zzou = zzaer.zzou();
            if (zzou != null) {
                Collection<zzvq> values = zzpt.values();
                Map<String, List<String>> hashMap = new HashMap<>();
                IObjectWrapper zzz = zzn.zzz(context);
                for (zzvq zzvq : values) {
                    for (zzvp next : zzvq.zzchv) {
                        String str = next.zzchk;
                        for (String next2 : next.zzchd) {
                            if (!hashMap.containsKey(next2)) {
                                hashMap.put(next2, new ArrayList<>());
                            }
                            if (str != null) {
                                hashMap.get(next2).add(str);
                            }
                        }
                    }
                }
                for (Map.Entry<String, List<String>> entry : hashMap.entrySet()) {
                    String str2 = entry.getKey();
                    try {
                        zzafy zzbq = zzou.zzbq(str2);
                        if (zzbq != null) {
                            zzwi zzpc = zzbq.zzpc();
                            if (!zzpc.isInitialized()) {
                                if (zzpc.zzmt()) {
                                    zzpc.zza(zzz, (zzafz) zzbq.zzpd(), entry.getValue());
                                    String valueOf = String.valueOf(str2);
                                    zzahw.zzby(valueOf.length() != 0 ? "Initialized rewarded video mediation adapter ".concat(valueOf) : new String("Initialized rewarded video mediation adapter "));
                                }
                            }
                        }
                    } catch (Throwable th2) {
                        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 56);
                        sb.append("Failed to initialize rewarded video mediation adapter \"");
                        sb.append(str2);
                        sb.append("\"");
                        zzahw.zzc(sb.toString(), th2);
                    }
                }
            }
        }
    }

    public final void zza(String str, IObjectWrapper iObjectWrapper) {
        if (!TextUtils.isEmpty(str)) {
            zzoi.initialize(this.mContext);
            boolean booleanValue = ((Boolean) zzlc.zzio().zzd(zzoi.zzbti)).booleanValue() | ((Boolean) zzlc.zzio().zzd(zzoi.zzboy)).booleanValue();
            zzay zzay = null;
            if (((Boolean) zzlc.zzio().zzd(zzoi.zzboy)).booleanValue()) {
                booleanValue = true;
                zzay = new zzay(this, (Runnable) zzn.zzy(iObjectWrapper));
            }
            if (booleanValue) {
                zzbt.zzet().zza(this.mContext, this.zzarg, str, zzay);
            }
        }
    }

    public final void zzb(IObjectWrapper iObjectWrapper, String str) {
        if (iObjectWrapper == null) {
            zzahw.e("Wrapped context is null. Failed to open debug menu.");
            return;
        }
        Context context = (Context) zzn.zzy(iObjectWrapper);
        if (context == null) {
            zzahw.e("Context is null. Failed to open debug menu.");
            return;
        }
        zzajc zzajc = new zzajc(context);
        zzajc.setAdUnitId(str);
        zzajc.zzcq(this.zzarg.zzcu);
        zzajc.showDialog();
    }

    public final float zzdp() {
        return zzbt.zzfj().zzdp();
    }

    public final boolean zzdq() {
        return zzbt.zzfj().zzdq();
    }

    public final void zzu(String str) {
        zzoi.initialize(this.mContext);
        if (!TextUtils.isEmpty(str)) {
            if (((Boolean) zzlc.zzio().zzd(zzoi.zzbti)).booleanValue()) {
                zzbt.zzet().zza(this.mContext, this.zzarg, str, (Runnable) null);
            }
        }
    }
}
