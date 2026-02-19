package com.google.android.gms.internal;

import android.net.Uri;
import com.google.android.gms.common.internal.zzbq;
import java.util.List;
import java.util.Map;

public final class zzdhy extends zzdcr {
    private final zzczy zzlan;

    public zzdhy(zzczy zzczy) {
        this.zzlan = zzczy;
    }

    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        String str;
        boolean z = true;
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length > 0);
        zzdjq<?> zzdjq = zzdjqArr[0];
        zzbq.checkArgument(!(zzdjq instanceof zzdjw));
        zzdjq<?> zzdjwArg = zzdjqArr.length > 1 ? zzdjqArr[1] : zzdjw.zzlcz;
        zzbq.checkArgument(zzdjwArg == zzdjw.zzlcz || (zzdjwArg instanceof zzdjx));
        zzdjq<?> zzdjwArg2 = zzdjqArr.length > 2 ? zzdjqArr[2] : zzdjw.zzlcz;
        if (zzdjwArg2 != zzdjw.zzlcz && (zzdjwArg2 instanceof zzdjw)) {
            z = false;
        }
        zzbq.checkArgument(z);
        Uri.Builder buildUpon = Uri.parse(zzdcq.zzd(zzdjq)).buildUpon();
        if (zzdjwArg != zzdjw.zzlcz) {
            for (Object value : (List) ((zzdjx) zzdjwArg).value()) {
                zzdjq zzdjq2 = (zzdjq) value;
                zzbq.checkArgument(zzdjq2 instanceof zzdka);
                for (Object entryObj : ((Map) ((zzdka) zzdjq2).value()).entrySet()) {
                    Map.Entry entry = (Map.Entry) entryObj;
                    buildUpon.appendQueryParameter(((String) entry.getKey()).toString(), zzdcq.zzd(zzdke.zza(zzdbb, (zzdjq) entry.getValue())));
                }
            }
        }
        String uri = buildUpon.build().toString();
        if (zzdjwArg2 == zzdjw.zzlcz) {
            this.zzlan.zzlr(uri);
            String valueOf = String.valueOf(uri);
            str = valueOf.length() != 0 ? "SendPixel: url = ".concat(valueOf) : new String("SendPixel: url = ");
        } else {
            String zzd = zzdcq.zzd(zzdjwArg2);
            this.zzlan.zzax(uri, zzd);
            StringBuilder sb = new StringBuilder(String.valueOf(uri).length() + 30 + String.valueOf(zzd).length());
            sb.append("SendPixel: url = ");
            sb.append(uri);
            sb.append(", uniqueId = ");
            sb.append(zzd);
            str = sb.toString();
        }
        zzdal.v(str);
        return zzdjw.zzlcz;
    }
}
