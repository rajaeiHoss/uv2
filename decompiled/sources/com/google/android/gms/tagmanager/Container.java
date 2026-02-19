package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzbp;
import com.google.android.gms.internal.zzbr;
import com.google.android.gms.internal.zzbs;
import com.google.android.gms.internal.zzdkh;
import com.google.android.gms.internal.zzdkl;
import com.google.android.gms.internal.zzdkp;
import com.google.android.gms.tagmanager.zzei;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Container {
    private final Context mContext;
    private final String zzknc;
    private final DataLayer zzknd;
    private zzfc zzkne;
    private Map<String, FunctionCallMacroCallback> zzknf = new HashMap();
    private Map<String, FunctionCallTagCallback> zzkng = new HashMap();
    private volatile long zzknh;
    private volatile String zzkni = "";

    public interface FunctionCallMacroCallback {
        Object getValue(String str, Map<String, Object> map);
    }

    public interface FunctionCallTagCallback {
        void execute(String str, Map<String, Object> map);
    }

    class zza implements zzan {
        private zza() {
        }

        public final Object zze(String str, Map<String, Object> map) {
            FunctionCallMacroCallback zzlh = Container.this.zzlh(str);
            if (zzlh == null) {
                return null;
            }
            return zzlh.getValue(str, map);
        }
    }

    class zzb implements zzan {
        private zzb() {
        }

        public final Object zze(String str, Map<String, Object> map) {
            FunctionCallTagCallback zzli = Container.this.zzli(str);
            if (zzli != null) {
                zzli.execute(str, map);
            }
            return zzgk.zzbik();
        }
    }

    Container(Context context, DataLayer dataLayer, String str, long j, zzbs zzbs) {
        this.mContext = context;
        this.zzknd = dataLayer;
        this.zzknc = str;
        this.zzknh = j;
        zzbp zzbp = zzbs.zzyi;
        Objects.requireNonNull(zzbp);
        try {
            zza(zzdkh.zza(zzbp));
        } catch (zzdkp e) {
            String valueOf = String.valueOf(zzbp);
            String zzdkp = e.toString();
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 46 + String.valueOf(zzdkp).length());
            sb.append("Not loading resource: ");
            sb.append(valueOf);
            sb.append(" because it is invalid: ");
            sb.append(zzdkp);
            zzdj.e(sb.toString());
        }
        if (zzbs.zzyh != null) {
            zza(zzbs.zzyh);
        }
    }

    Container(Context context, DataLayer dataLayer, String str, long j, zzdkl zzdkl) {
        this.mContext = context;
        this.zzknd = dataLayer;
        this.zzknc = str;
        this.zzknh = 0;
        zza(zzdkl);
    }

    private final void zza(zzdkl zzdkl) {
        this.zzkni = zzdkl.getVersion();
        zzei.zzbhh().zzbhi().equals(zzei.zza.CONTAINER_DEBUG);
        zzdkl zzdkl2 = zzdkl;
        zza(new zzfc(this.mContext, zzdkl2, this.zzknd, new zza(), new zzb(), new zzdr()));
        if (getBoolean("_gtm.loadEventEnabled")) {
            this.zzknd.pushEvent("gtm.load", DataLayer.mapOf("gtm.id", this.zzknc));
        }
    }

    private final synchronized void zza(zzfc zzfc) {
        this.zzkne = zzfc;
    }

    private final void zza(zzbr[] zzbrArr) {
        ArrayList arrayList = new ArrayList();
        for (zzbr add : zzbrArr) {
            arrayList.add(add);
        }
        zzbfm().zzan(arrayList);
    }

    private final synchronized zzfc zzbfm() {
        return this.zzkne;
    }

    public boolean getBoolean(String str) {
        String sb;
        zzfc zzbfm = zzbfm();
        if (zzbfm == null) {
            sb = "getBoolean called for closed container.";
        } else {
            try {
                return zzgk.zzh(zzbfm.zzmc(str).getObject()).booleanValue();
            } catch (Exception e) {
                String message = e.getMessage();
                StringBuilder sb2 = new StringBuilder(String.valueOf(message).length() + 66);
                sb2.append("Calling getBoolean() threw an exception: ");
                sb2.append(message);
                sb2.append(" Returning default value.");
                sb = sb2.toString();
            }
        }
        zzdj.e(sb);
        return zzgk.zzbii().booleanValue();
    }

    public String getContainerId() {
        return this.zzknc;
    }

    public double getDouble(String str) {
        String sb;
        zzfc zzbfm = zzbfm();
        if (zzbfm == null) {
            sb = "getDouble called for closed container.";
        } else {
            try {
                return zzgk.zzg(zzbfm.zzmc(str).getObject()).doubleValue();
            } catch (Exception e) {
                String message = e.getMessage();
                StringBuilder sb2 = new StringBuilder(String.valueOf(message).length() + 65);
                sb2.append("Calling getDouble() threw an exception: ");
                sb2.append(message);
                sb2.append(" Returning default value.");
                sb = sb2.toString();
            }
        }
        zzdj.e(sb);
        return zzgk.zzbih().doubleValue();
    }

    public long getLastRefreshTime() {
        return this.zzknh;
    }

    public long getLong(String str) {
        String sb;
        zzfc zzbfm = zzbfm();
        if (zzbfm == null) {
            sb = "getLong called for closed container.";
        } else {
            try {
                return zzgk.zzf(zzbfm.zzmc(str).getObject()).longValue();
            } catch (Exception e) {
                String message = e.getMessage();
                StringBuilder sb2 = new StringBuilder(String.valueOf(message).length() + 63);
                sb2.append("Calling getLong() threw an exception: ");
                sb2.append(message);
                sb2.append(" Returning default value.");
                sb = sb2.toString();
            }
        }
        zzdj.e(sb);
        return zzgk.zzbig().longValue();
    }

    public String getString(String str) {
        String sb;
        zzfc zzbfm = zzbfm();
        if (zzbfm == null) {
            sb = "getString called for closed container.";
        } else {
            try {
                return zzgk.zzd(zzbfm.zzmc(str).getObject());
            } catch (Exception e) {
                String message = e.getMessage();
                StringBuilder sb2 = new StringBuilder(String.valueOf(message).length() + 65);
                sb2.append("Calling getString() threw an exception: ");
                sb2.append(message);
                sb2.append(" Returning default value.");
                sb = sb2.toString();
            }
        }
        zzdj.e(sb);
        return zzgk.zzbik();
    }

    public boolean isDefault() {
        return getLastRefreshTime() == 0;
    }

    public void registerFunctionCallMacroCallback(String str, FunctionCallMacroCallback functionCallMacroCallback) {
        Objects.requireNonNull(functionCallMacroCallback, "Macro handler must be non-null");
        synchronized (this.zzknf) {
            this.zzknf.put(str, functionCallMacroCallback);
        }
    }

    public void registerFunctionCallTagCallback(String str, FunctionCallTagCallback functionCallTagCallback) {
        Objects.requireNonNull(functionCallTagCallback, "Tag callback must be non-null");
        synchronized (this.zzkng) {
            this.zzkng.put(str, functionCallTagCallback);
        }
    }

    /* access modifiers changed from: package-private */
    public final void release() {
        this.zzkne = null;
    }

    public void unregisterFunctionCallMacroCallback(String str) {
        synchronized (this.zzknf) {
            this.zzknf.remove(str);
        }
    }

    public void unregisterFunctionCallTagCallback(String str) {
        synchronized (this.zzkng) {
            this.zzkng.remove(str);
        }
    }

    public final String zzbfl() {
        return this.zzkni;
    }

    /* access modifiers changed from: package-private */
    public final FunctionCallMacroCallback zzlh(String str) {
        FunctionCallMacroCallback functionCallMacroCallback;
        synchronized (this.zzknf) {
            functionCallMacroCallback = this.zzknf.get(str);
        }
        return functionCallMacroCallback;
    }

    public final FunctionCallTagCallback zzli(String str) {
        FunctionCallTagCallback functionCallTagCallback;
        synchronized (this.zzkng) {
            functionCallTagCallback = this.zzkng.get(str);
        }
        return functionCallTagCallback;
    }

    public final void zzlj(String str) {
        zzbfm().zzlj(str);
    }
}
