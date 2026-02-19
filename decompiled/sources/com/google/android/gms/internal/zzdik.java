package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import org.json.JSONException;

final class zzdik implements zzdii {
    zzdik() {
    }

    public final zzdin zzac(byte[] bArr) throws zzdib {
        if (bArr == null) {
            throw new zzdib("Cannot parse a null byte[]");
        } else if (bArr.length != 0) {
            try {
                zzdjc zzmy = zzdic.zzmy(new String(bArr));
                if (zzmy != null) {
                    zzdal.v("The container was successfully parsed from the resource");
                }
                return new zzdin(Status.zzftq, 0, new zzdio(zzmy), zzdij.zzlbj.zzac(bArr).zzbjv());
            } catch (JSONException unused) {
                throw new zzdib("The resource data is corrupted. The container cannot be extracted from the JSON data");
            } catch (zzdib unused2) {
                throw new zzdib("The resource data is invalid. The container cannot be extracted from the JSON data");
            }
        } else {
            throw new zzdib("Cannot parse a 0 length byte[]");
        }
    }
}
