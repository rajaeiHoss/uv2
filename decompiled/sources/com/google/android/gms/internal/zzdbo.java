package com.google.android.gms.internal;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Pair;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.tagmanager.TagManagerService;
import com.google.android.gms.tagmanager.zzce;
import com.google.android.gms.tagmanager.zzch;
import com.google.android.gms.tagmanager.zzck;
import com.google.android.gms.tagmanager.zzcn;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class zzdbo {
    private static final Pattern zzkyk = Pattern.compile("(gtm-[a-z0-9]{1,10})\\.json", 2);
    private static volatile zzdbo zzkyl;
    private static zzc zzkyu = new zzdbp();
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public volatile boolean zzarf = false;
    private String zzknc;
    /* access modifiers changed from: private */
    public final ExecutorService zzkvr;
    private final ScheduledExecutorService zzkvs;
    /* access modifiers changed from: private */
    public final zzcn zzkvt;
    private final zzce zzkwc;
    /* access modifiers changed from: private */
    public final zzdcj zzkym;
    /* access modifiers changed from: private */
    public final zzdat zzkyn;
    private final zza zzkyo;
    /* access modifiers changed from: private */
    public final Object zzkyp = new Object();
    private String zzkyq;
    /* access modifiers changed from: private */
    public int zzkyr = 1;
    /* access modifiers changed from: private */
    public final Queue<Runnable> zzkys = new LinkedList();
    private volatile boolean zzkyt = false;

    public static class zza {
        private final Context mContext;

        public zza(Context context) {
            this.mContext = context;
        }

        public final String[] zzbjg() throws IOException {
            return this.mContext.getAssets().list("");
        }

        public final String[] zzmv(String str) throws IOException {
            return this.mContext.getAssets().list(str);
        }
    }

    class zzb extends zzdaf {
        zzb() {
        }

        /* synthetic */ zzb(zzdbo zzdbo, zzdbp zzdbp) {
            this();
        }

        public final void zza(boolean z, String str) throws RemoteException {
            zzdbo.this.zzkvr.execute(new zzdca(this, z, str));
        }

        /* access modifiers changed from: package-private */
        public final void zzb(boolean z, String str) {
            if (zzdbo.this.zzkyr == 2) {
                if (z) {
                    int unused = zzdbo.this.zzkyr = 3;
                    StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 18);
                    sb.append("Container ");
                    sb.append(str);
                    sb.append(" loaded.");
                    zzdal.v(sb.toString());
                } else {
                    int unused2 = zzdbo.this.zzkyr = 4;
                    String valueOf = String.valueOf(str);
                    zzdal.e(valueOf.length() != 0 ? "Error loading container:".concat(valueOf) : new String("Error loading container:"));
                }
                while (!zzdbo.this.zzkys.isEmpty()) {
                    zzdbo.this.zzkvr.execute((Runnable) zzdbo.this.zzkys.remove());
                }
                return;
            }
            zzdal.zzcz("Container load callback completed after timeout");
        }
    }

    public interface zzc {
        zzdbo zzb(Context context, zzcn zzcn, zzce zzce);
    }

    zzdbo(Context context, zzcn zzcn, zzce zzce, zzdcj zzdcj, ExecutorService executorService, ScheduledExecutorService scheduledExecutorService, zzdat zzdat, zza zza2) {
        zzbq.checkNotNull(context);
        zzbq.checkNotNull(zzcn);
        this.mContext = context;
        this.zzkvt = zzcn;
        this.zzkwc = zzce;
        this.zzkym = zzdcj;
        this.zzkvr = executorService;
        this.zzkvs = scheduledExecutorService;
        this.zzkyn = zzdat;
        this.zzkyo = zza2;
    }

    public static zzdbo zza(Context context, zzcn zzcn, zzce zzce) {
        zzbq.checkNotNull(context);
        zzbq.checkNotNull(context);
        zzdbo zzdbo = zzkyl;
        if (zzdbo == null) {
            synchronized (zzdbo.class) {
                zzdbo = zzkyl;
                if (zzdbo == null) {
                    zzdbo zzb2 = zzkyu.zzb(context, zzcn, zzce);
                    zzkyl = zzb2;
                    zzdbo = zzb2;
                }
            }
        }
        return zzdbo;
    }

    private static boolean zza(Context context, Class<? extends Service> cls) {
        try {
            ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context, cls), 4);
            return serviceInfo != null && serviceInfo.enabled;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public final Pair<String, String> zzg(String[] strArr) {
        String str;
        String str2;
        zzdal.v("Looking up container asset.");
        String str3 = this.zzknc;
        if (str3 != null && (str2 = this.zzkyq) != null) {
            return Pair.create(str3, str2);
        }
        try {
            String[] zzmv = this.zzkyo.zzmv("containers");
            boolean z = false;
            for (int i = 0; i < zzmv.length; i++) {
                str = null;
                Pattern pattern = zzkyk;
                Matcher matcher = pattern.matcher(zzmv[i]);
                if (!matcher.matches()) {
                    str = String.format("Ignoring container asset %s (does not match %s)", new Object[]{zzmv[i], pattern.pattern()});
                } else if (!z) {
                    this.zzknc = matcher.group(1);
                    String str4 = File.separator;
                    String str5 = zzmv[i];
                    StringBuilder sb = new StringBuilder(String.valueOf(str4).length() + 10 + String.valueOf(str5).length());
                    sb.append("containers");
                    sb.append(str4);
                    sb.append(str5);
                    this.zzkyq = sb.toString();
                    String valueOf = String.valueOf(this.zzknc);
                    zzdal.v(valueOf.length() != 0 ? "Asset found for container ".concat(valueOf) : new String("Asset found for container "));
                    z = true;
                } else {
                    String valueOf2 = String.valueOf(zzmv[i]);
                    str = valueOf2.length() != 0 ? "Extra container asset found, will not be loaded: ".concat(valueOf2) : new String("Extra container asset found, will not be loaded: ");
                }
                if (str != null) {
                    zzdal.zzcz(str);
                }
            }
            if (!z) {
                zzdal.zzcz("No container asset found in /assets/containers. Checking top level /assets directory for container assets.");
                try {
                    String[] zzbjg = this.zzkyo.zzbjg();
                    for (int i2 = 0; i2 < zzbjg.length; i2++) {
                        Matcher matcher2 = zzkyk.matcher(zzbjg[i2]);
                        if (matcher2.matches()) {
                            if (!z) {
                                String group = matcher2.group(1);
                                this.zzknc = group;
                                this.zzkyq = zzbjg[i2];
                                String valueOf3 = String.valueOf(group);
                                zzdal.v(valueOf3.length() != 0 ? "Asset found for container ".concat(valueOf3) : new String("Asset found for container "));
                                zzdal.zzcz("Loading container assets from top level /assets directory. Please move the container asset to /assets/containers");
                                z = true;
                            } else {
                                String valueOf4 = String.valueOf(zzbjg[i2]);
                                zzdal.zzcz(valueOf4.length() != 0 ? "Extra container asset found, will not be loaded: ".concat(valueOf4) : new String("Extra container asset found, will not be loaded: "));
                            }
                        }
                    }
                } catch (IOException e) {
                    zzdal.zzb("Failed to enumerate assets.", e);
                    return Pair.create((String) null, (String) null);
                }
            }
            return Pair.create(this.zzknc, this.zzkyq);
        } catch (IOException e2) {
            zzdal.zzb(String.format("Failed to enumerate assets in folder %s", new Object[]{"containers"}), e2);
            return Pair.create((String) null, (String) null);
        }
    }

    public final void initialize() {
        zzdal.v("Initializing Tag Manager.");
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this.zzkyp) {
            if (!this.zzarf) {
                try {
                    if (!zza(this.mContext, (Class<? extends Service>) TagManagerService.class)) {
                        zzdal.zzcz("Tag Manager fails to initialize (TagManagerService not enabled in the manifest)");
                        this.zzarf = true;
                        return;
                    }
                    Pair<String, String> zzg = zzg((String[]) null);
                    String str = (String) zzg.first;
                    String str2 = (String) zzg.second;
                    if (str == null || str2 == null) {
                        zzdal.zzcz("Tag Manager's event handler WILL NOT be installed (no container loaded)");
                    } else {
                        String valueOf = String.valueOf(str);
                        zzdal.zzcy(valueOf.length() != 0 ? "Loading container ".concat(valueOf) : new String("Loading container "));
                        this.zzkvr.execute(new zzdbu(this, str, str2, (String) null));
                        this.zzkvs.schedule(new zzdbv(this), 5000, TimeUnit.MILLISECONDS);
                        if (!this.zzkyt) {
                            zzdal.zzcy("Installing Tag Manager event handler.");
                            this.zzkyt = true;
                            this.zzkvt.zza((zzck) new zzdbq(this));
                            try {
                                this.zzkvt.zza((zzch) new zzdbs(this));
                            } catch (RemoteException e) {
                                zzczq.zza("Error communicating with measurement proxy: ", e, this.mContext);
                            }
                            this.mContext.registerComponentCallbacks(new zzdbx(this));
                            zzdal.zzcy("Tag Manager event handler installed.");
                        }
                    }
                    this.zzarf = true;
                    StringBuilder sb = new StringBuilder(53);
                    sb.append("Tag Manager initilization took ");
                    sb.append(System.currentTimeMillis() - currentTimeMillis);
                    sb.append("ms");
                    zzdal.zzcy(sb.toString());
                } catch (RemoteException e2) {
                    zzczq.zza("Error communicating with measurement proxy: ", e2, this.mContext);
                } catch (Throwable th) {
                    this.zzarf = true;
                    throw th;
                }
            }
        }
    }

    public final void zzf(String[] strArr) {
        zzdal.v("Initializing Tag Manager.");
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this.zzkyp) {
            if (!this.zzarf) {
                try {
                    if (!zza(this.mContext, (Class<? extends Service>) TagManagerService.class)) {
                        zzdal.zzcz("Tag Manager fails to initialize (TagManagerService not enabled in the manifest)");
                        this.zzarf = true;
                        return;
                    }
                    Pair<String, String> zzg = zzg((String[]) null);
                    String str = (String) zzg.first;
                    String str2 = (String) zzg.second;
                    if (str == null || str2 == null) {
                        zzdal.zzcz("Tag Manager's event handler WILL NOT be installed (no container loaded)");
                    } else {
                        String valueOf = String.valueOf(str);
                        zzdal.zzcy(valueOf.length() != 0 ? "Loading container ".concat(valueOf) : new String("Loading container "));
                        this.zzkvr.execute(new zzdbu(this, str, str2, (String) null));
                        this.zzkvs.schedule(new zzdbv(this), 5000, TimeUnit.MILLISECONDS);
                        if (!this.zzkyt) {
                            zzdal.zzcy("Installing Tag Manager event handler.");
                            this.zzkyt = true;
                            this.zzkvt.zza((zzck) new zzdbq(this));
                            try {
                                this.zzkvt.zza((zzch) new zzdbs(this));
                            } catch (RemoteException e) {
                                zzczq.zza("Error communicating with measurement proxy: ", e, this.mContext);
                            }
                            this.mContext.registerComponentCallbacks(new zzdbx(this));
                            zzdal.zzcy("Tag Manager event handler installed.");
                        }
                    }
                    this.zzarf = true;
                    long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                    StringBuilder sb = new StringBuilder(53);
                    sb.append("Tag Manager initilization took ");
                    sb.append(currentTimeMillis2);
                    sb.append("ms");
                    zzdal.zzcy(sb.toString());
                } catch (RemoteException e2) {
                    zzczq.zza("Error communicating with measurement proxy: ", e2, this.mContext);
                } catch (Throwable th) {
                    this.zzarf = true;
                    throw th;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzr(Uri uri) {
        this.zzkvr.execute(new zzdbz(this, uri));
    }
}
