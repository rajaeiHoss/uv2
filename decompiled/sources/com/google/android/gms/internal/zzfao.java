package com.google.android.gms.internal;

import android.content.Context;
import android.util.Log;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class zzfao implements Runnable {
    private Context mContext;
    private zzfas zzose;
    private zzfap zzosm;
    private zzfap zzosn;
    private zzfap zzoso;

    public zzfao(Context context, zzfap zzfap, zzfap zzfap2, zzfap zzfap3, zzfas zzfas) {
        this.mContext = context;
        this.zzosm = zzfap;
        this.zzosn = zzfap2;
        this.zzoso = zzfap3;
        this.zzose = zzfas;
    }

    private static zzfat zza(zzfap zzfap) {
        zzfat zzfat = new zzfat();
        if (zzfap.zzcnl() != null) {
            Map<String, Map<String, byte[]>> zzcnl = zzfap.zzcnl();
            ArrayList arrayList = new ArrayList();
            for (String next : zzcnl.keySet()) {
                ArrayList arrayList2 = new ArrayList();
                Map<String, byte[]> map = zzcnl.get(next);
                for (String str : map.keySet()) {
                    zzfau zzfau = new zzfau();
                    zzfau.key = str;
                    zzfau.zzosz = (byte[]) map.get(str);
                    arrayList2.add(zzfau);
                }
                zzfaw zzfaw = new zzfaw();
                zzfaw.zzkal = next;
                zzfaw.zzote = (zzfau[]) arrayList2.toArray(new zzfau[arrayList2.size()]);
                arrayList.add(zzfaw);
            }
            zzfat.zzosw = (zzfaw[]) arrayList.toArray(new zzfaw[arrayList.size()]);
        }
        if (zzfap.zzaol() != null) {
            List<byte[]> zzaol = zzfap.zzaol();
            zzfat.zzosx = (byte[][]) zzaol.toArray(new byte[zzaol.size()][]);
        }
        zzfat.timestamp = zzfap.getTimestamp();
        return zzfat;
    }

    public final void run() {
        zzfax zzfax = new zzfax();
        zzfap zzfap = this.zzosm;
        if (zzfap != null) {
            zzfax.zzotf = zza(zzfap);
        }
        zzfap zzfap2 = this.zzosn;
        if (zzfap2 != null) {
            zzfax.zzotg = zza(zzfap2);
        }
        zzfap zzfap3 = this.zzoso;
        if (zzfap3 != null) {
            zzfax.zzoth = zza(zzfap3);
        }
        if (this.zzose != null) {
            zzfav zzfav = new zzfav();
            zzfav.zzota = this.zzose.getLastFetchStatus();
            zzfav.zzotb = this.zzose.isDeveloperModeEnabled();
            zzfav.zzotc = this.zzose.zzcnp();
            zzfax.zzoti = zzfav;
        }
        zzfas zzfas = this.zzose;
        if (!(zzfas == null || zzfas.zzcnn() == null)) {
            ArrayList arrayList = new ArrayList();
            Map<String, zzfam> zzcnn = this.zzose.zzcnn();
            for (String next : zzcnn.keySet()) {
                if (zzcnn.get(next) != null) {
                    zzfay zzfay = new zzfay();
                    zzfay.zzkal = next;
                    zzfay.zzotl = zzcnn.get(next).zzcnk();
                    zzfay.resourceId = zzcnn.get(next).getResourceId();
                    arrayList.add(zzfay);
                }
            }
            zzfax.zzotj = (zzfay[]) arrayList.toArray(new zzfay[arrayList.size()]);
        }
        byte[] zzc = zzfls.zzc(zzfax);
        try {
            FileOutputStream openFileOutput = this.mContext.openFileOutput("persisted_config", 0);
            openFileOutput.write(zzc);
            openFileOutput.close();
        } catch (IOException e) {
            Log.e("AsyncPersisterTask", "Could not persist config.", e);
        }
    }
}
