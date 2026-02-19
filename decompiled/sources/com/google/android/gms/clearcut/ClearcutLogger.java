package com.google.android.gms.clearcut;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.internal.zzbfi;
import com.google.android.gms.internal.zzbfn;
import com.google.android.gms.internal.zzbft;
import com.google.android.gms.internal.zzbfv;
import com.google.android.gms.internal.zzfmr;
import com.google.android.gms.phenotype.ExperimentTokens;
import java.util.ArrayList;
import java.util.TimeZone;

public final class ClearcutLogger {
    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> API;
    private static Api.zzf<zzbfn> zzegu = new Api.zzf<>();
    private static Api.zza<zzbfn, Api.ApiOptions.NoOptions> zzegv;
    private static final ExperimentTokens[] zzfoz = new ExperimentTokens[0];
    private static final String[] zzfpa = new String[0];
    private static final byte[][] zzfpb = new byte[0][];
    /* access modifiers changed from: private */
    public final String packageName;
    /* access modifiers changed from: private */
    public final zze zzdir;
    /* access modifiers changed from: private */
    public final int zzfpc;
    /* access modifiers changed from: private */
    public String zzfpd;
    /* access modifiers changed from: private */
    public int zzfpe = -1;
    private String zzfpf;
    private String zzfpg;
    /* access modifiers changed from: private */
    public final boolean zzfph;
    private int zzfpi = 0;
    /* access modifiers changed from: private */
    public final com.google.android.gms.clearcut.zzb zzfpj;
    /* access modifiers changed from: private */
    public zzc zzfpk;
    /* access modifiers changed from: private */
    public final zza zzfpl;

    public class LogEventBuilder {
        private String zzfpd;
        private int zzfpe;
        private String zzfpf;
        private String zzfpg;
        private int zzfpi;
        private final zzb zzfpm;
        private ArrayList<Integer> zzfpn;
        private ArrayList<String> zzfpo;
        private ArrayList<Integer> zzfpp;
        private ArrayList<ExperimentTokens> zzfpq;
        private ArrayList<byte[]> zzfpr;
        private boolean zzfps;
        private final zzfmr zzfpt;
        private boolean zzfpu;

        private LogEventBuilder(ClearcutLogger clearcutLogger, byte[] bArr) {
            this(bArr, (zzb) null);
        }

        private LogEventBuilder(byte[] bArr, zzb zzb) {
            this.zzfpe = ClearcutLogger.this.zzfpe;
            this.zzfpd = ClearcutLogger.this.zzfpd;
            this.zzfpf = null;
            this.zzfpg = null;
            this.zzfpi = 0;
            this.zzfpn = null;
            this.zzfpo = null;
            this.zzfpp = null;
            this.zzfpq = null;
            this.zzfpr = null;
            this.zzfps = true;
            zzfmr zzfmr = new zzfmr();
            this.zzfpt = zzfmr;
            this.zzfpu = false;
            this.zzfpf = null;
            this.zzfpg = null;
            zzfmr.zzpyu = ClearcutLogger.this.zzdir.currentTimeMillis();
            zzfmr.zzpyv = ClearcutLogger.this.zzdir.elapsedRealtime();
            zzc unused = ClearcutLogger.this.zzfpk;
            zzfmr.zzpzg = (long) (TimeZone.getDefault().getOffset(zzfmr.zzpyu) / 1000);
            if (bArr != null) {
                zzfmr.zzpzb = bArr;
            }
            this.zzfpm = null;
        }

        /* synthetic */ LogEventBuilder(ClearcutLogger clearcutLogger, byte[] bArr, zza zza) {
            this(clearcutLogger, bArr);
        }

        public void log() {
            if (!this.zzfpu) {
                this.zzfpu = true;
                com.google.android.gms.clearcut.zze logEvent = new com.google.android.gms.clearcut.zze(new zzbfv(ClearcutLogger.this.packageName, ClearcutLogger.this.zzfpc, this.zzfpe, this.zzfpd, this.zzfpf, this.zzfpg, ClearcutLogger.this.zzfph, 0), this.zzfpt, (zzb) null, (zzb) null, ClearcutLogger.zzb((ArrayList<Integer>) null), (String[]) null, ClearcutLogger.zzb((ArrayList<Integer>) null), (byte[][]) null, (ExperimentTokens[]) null, this.zzfps);
                zzbfv zzbfv = logEvent.zzfpz;
                if (ClearcutLogger.this.zzfpl.zzg(zzbfv.zzfpd, zzbfv.zzfpe)) {
                    ClearcutLogger.this.zzfpj.zza(logEvent);
                } else {
                    PendingResults.zza(Status.zzftq, (GoogleApiClient) null);
                }
            } else {
                throw new IllegalStateException("do not reuse LogEventBuilder");
            }
        }
    }

    public interface zza {
        boolean zzg(String str, int i);
    }

    public interface zzb {
        byte[] zzahc();
    }

    public static class zzc {
    }

    static {
        com.google.android.gms.clearcut.zza apiBuilder = new com.google.android.gms.clearcut.zza();
        zzegv = apiBuilder;
        API = new Api<>("ClearcutLogger.API", apiBuilder, zzegu);
    }

    private ClearcutLogger(Context context, int i, String str, String str2, String str3, boolean z, com.google.android.gms.clearcut.zzb zzb2, zze zze, zzc zzc2, zza zza2) {
        this.packageName = context.getPackageName();
        this.zzfpc = zzca(context);
        this.zzfpe = -1;
        this.zzfpd = str;
        this.zzfpf = null;
        this.zzfpg = null;
        this.zzfph = true;
        this.zzfpj = zzb2;
        this.zzdir = zze;
        this.zzfpk = new zzc();
        this.zzfpi = 0;
        this.zzfpl = zza2;
        zzbq.checkArgument(true, "can't be anonymous with an upload account");
    }

    public static ClearcutLogger anonymousLogger(Context context, String str) {
        return new ClearcutLogger(context, -1, str, (String) null, (String) null, true, zzbfi.zzcb(context), zzi.zzanq(), (zzc) null, new zzbft(context));
    }

    /* access modifiers changed from: private */
    public static int[] zzb(ArrayList<Integer> arrayList) {
        if (arrayList == null) {
            return null;
        }
        int[] iArr = new int[arrayList.size()];
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            iArr[i2] = ((Integer) obj).intValue();
            i2++;
        }
        return iArr;
    }

    private static int zzca(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.wtf("ClearcutLogger", "This can't happen.", e);
            return 0;
        }
    }

    public final LogEventBuilder newEvent(byte[] bArr) {
        return new LogEventBuilder(this, bArr, (zza) null);
    }
}
