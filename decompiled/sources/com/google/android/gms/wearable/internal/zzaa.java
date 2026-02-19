package com.google.android.gms.wearable.internal;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzcm;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzc;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.CapabilityClient;
import com.google.android.gms.wearable.CapabilityInfo;
import java.util.Map;

public final class zzaa extends CapabilityClient {
    private final CapabilityApi zzlsu = new zzo();

    public zzaa(Activity activity, GoogleApi.zza zza) {
        super(activity, zza);
    }

    public zzaa(Context context, GoogleApi.zza zza) {
        super(context, zza);
    }

    private final Task<Void> zza(zzci<CapabilityClient.OnCapabilityChangedListener> zzci, CapabilityClient.OnCapabilityChangedListener onCapabilityChangedListener, IntentFilter[] intentFilterArr) {
        return zza(new zzaf(onCapabilityChangedListener, intentFilterArr, zzci), new zzag(onCapabilityChangedListener, zzci.zzakx()));
    }

    public final Task<Void> addListener(CapabilityClient.OnCapabilityChangedListener onCapabilityChangedListener, Uri uri, int i) {
        zzc.zzb(onCapabilityChangedListener, "listener must not be null");
        zzc.zzb(uri, "uri must not be null");
        zzbq.checkArgument(i == 0 || i == 1, "invalid filter type");
        return zza(zzcm.zzb(onCapabilityChangedListener, getLooper(), "CapabilityListener"), onCapabilityChangedListener, new IntentFilter[]{zzgj.zza("com.google.android.gms.wearable.CAPABILITY_CHANGED", uri, i)});
    }

    public final Task<Void> addListener(CapabilityClient.OnCapabilityChangedListener onCapabilityChangedListener, String str) {
        zzc.zzb(onCapabilityChangedListener, "listener must not be null");
        zzc.zzb(str, "capability must not be null");
        IntentFilter zzoe = zzgj.zzoe("com.google.android.gms.wearable.CAPABILITY_CHANGED");
        if (!str.startsWith("/")) {
            String valueOf = String.valueOf(str);
            str = valueOf.length() != 0 ? "/".concat(valueOf) : new String("/");
        }
        zzoe.addDataPath(str, 0);
        IntentFilter[] intentFilterArr = {zzoe};
        Looper looper = getLooper();
        String valueOf2 = String.valueOf(str);
        return zza(zzcm.zzb(onCapabilityChangedListener, looper, valueOf2.length() != 0 ? "CapabilityListener:".concat(valueOf2) : new String("CapabilityListener:")), new zzae(onCapabilityChangedListener, str), intentFilterArr);
    }

    public final Task<Void> addLocalCapability(String str) {
        zzc.zzb(str, "capability must not be null");
        return zzbj.zzb(this.zzlsu.addLocalCapability(zzahw(), str));
    }

    public final Task<Map<String, CapabilityInfo>> getAllCapabilities(int i) {
        return zzbj.zza(this.zzlsu.getAllCapabilities(zzahw(), i), zzac.zzgui);
    }

    public final Task<CapabilityInfo> getCapability(String str, int i) {
        zzc.zzb(str, "capability must not be null");
        return zzbj.zza(this.zzlsu.getCapability(zzahw(), str, i), zzab.zzgui);
    }

    public final Task<Boolean> removeListener(CapabilityClient.OnCapabilityChangedListener onCapabilityChangedListener) {
        zzc.zzb(onCapabilityChangedListener, "listener must not be null");
        return zza((zzck<?>) zzcm.zzb(onCapabilityChangedListener, getLooper(), "CapabilityListener").zzakx());
    }

    public final Task<Boolean> removeListener(CapabilityClient.OnCapabilityChangedListener onCapabilityChangedListener, String str) {
        zzc.zzb(onCapabilityChangedListener, "listener must not be null");
        zzc.zzb(str, "capability must not be null");
        if (!str.startsWith("/")) {
            String valueOf = String.valueOf(str);
            str = valueOf.length() != 0 ? "/".concat(valueOf) : new String("/");
        }
        Looper looper = getLooper();
        String valueOf2 = String.valueOf(str);
        return zza((zzck<?>) zzcm.zzb(onCapabilityChangedListener, looper, valueOf2.length() != 0 ? "CapabilityListener:".concat(valueOf2) : new String("CapabilityListener:")).zzakx());
    }

    public final Task<Void> removeLocalCapability(String str) {
        zzc.zzb(str, "capability must not be null");
        return zzbj.zzb(this.zzlsu.removeLocalCapability(zzahw(), str));
    }
}
