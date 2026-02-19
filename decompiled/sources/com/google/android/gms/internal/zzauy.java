package com.google.android.gms.internal;

import android.accounts.Account;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndexApi;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.plus.PlusShare;
import java.util.ArrayList;
import java.util.List;

public final class zzauy {
    public static zzauo zza(Action action, long j, String str, int i) {
        int i2;
        Bundle bundle = new Bundle();
        bundle.putAll(action.zzabs());
        Bundle bundle2 = bundle.getBundle("object");
        Uri parse = bundle2.containsKey("id") ? Uri.parse(bundle2.getString("id")) : null;
        String string = bundle2.getString("name");
        String string2 = bundle2.getString(AppMeasurement.Param.TYPE);
        Intent zza = zzauz.zza(str, Uri.parse(bundle2.getString(PlusShare.KEY_CALL_TO_ACTION_URL)));
        zzaty zza2 = zzauo.zza(zza, string, parse, string2, (List<AppIndexApi.AppIndexingLink>) null);
        if (bundle.containsKey(".private:ssbContext")) {
            zza2.zza(zzauc.zzg(bundle.getByteArray(".private:ssbContext")));
            bundle.remove(".private:ssbContext");
        }
        if (bundle.containsKey(".private:accountName")) {
            zza2.zzb(new Account(bundle.getString(".private:accountName"), "com.google"));
            bundle.remove(".private:accountName");
        }
        boolean z = false;
        if (!bundle.containsKey(".private:isContextOnly") || !bundle.getBoolean(".private:isContextOnly")) {
            i2 = 0;
        } else {
            i2 = 4;
            bundle.remove(".private:isContextOnly");
        }
        if (bundle.containsKey(".private:isDeviceOnly")) {
            z = bundle.getBoolean(".private:isDeviceOnly", false);
            bundle.remove(".private:isDeviceOnly");
        }
        zza2.zza(new zzauc(zzfls.zzc(zze(bundle)), new zzauk(".private:action").zzaq(true).zzev(".private:action").zzeu("blob").zzabq()));
        return new zzaup().zza(zzauo.zza(str, zza)).zzv(j).zzax(i2).zza(zza2.zzabp()).zzas(z).zzay(i).zzabr();
    }

    private static zzcdl zzb(String str, Bundle bundle) {
        zzcdl zzcdl = new zzcdl();
        zzcdl.name = str;
        zzcdl.zzilg = new zzcdn();
        zzcdl.zzilg.zzill = zze(bundle);
        return zzcdl;
    }

    private static zzcdm zze(Bundle bundle) {
        ArrayList arrayList = new ArrayList();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            zzcdl zzb = null;
            if (obj instanceof String) {
                zzb = zzm(str, (String) obj);
            } else if (obj instanceof Bundle) {
                zzb = zzb(str, (Bundle) obj);
            } else {
                int i = 0;
                if (obj instanceof String[]) {
                    String[] strArr = (String[]) obj;
                    int length = strArr.length;
                    while (i < length) {
                        String str2 = strArr[i];
                        if (str2 != null) {
                            arrayList.add(zzm(str, str2));
                        }
                        i++;
                    }
                } else if (obj instanceof Bundle[]) {
                    Bundle[] bundleArr = (Bundle[]) obj;
                    int length2 = bundleArr.length;
                    while (i < length2) {
                        Bundle bundle2 = bundleArr[i];
                        if (bundle2 != null) {
                            arrayList.add(zzb(str, bundle2));
                        }
                        i++;
                    }
                } else if (obj instanceof Boolean) {
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    zzcdl zzcdl = new zzcdl();
                    zzcdl.name = str;
                    zzcdl.zzilg = new zzcdn();
                    zzcdl.zzilg.zzili = booleanValue;
                    arrayList.add(zzcdl);
                } else {
                    String valueOf = String.valueOf(obj);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 19);
                    sb.append("Unsupported value: ");
                    sb.append(valueOf);
                    Log.e("SearchIndex", sb.toString());
                }
            }
            if (zzb != null) {
                arrayList.add(zzb);
            }
        }
        zzcdm zzcdm = new zzcdm();
        if (bundle.containsKey(AppMeasurement.Param.TYPE)) {
            zzcdm.type = bundle.getString(AppMeasurement.Param.TYPE);
        }
        zzcdm.zzilh = (zzcdl[]) arrayList.toArray(new zzcdl[arrayList.size()]);
        return zzcdm;
    }

    private static zzcdl zzm(String str, String str2) {
        zzcdl zzcdl = new zzcdl();
        zzcdl.name = str;
        zzcdl.zzilg = new zzcdn();
        zzcdl.zzilg.zzgim = str2;
        return zzcdl;
    }
}
