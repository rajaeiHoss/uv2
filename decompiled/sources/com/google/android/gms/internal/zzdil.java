package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import org.json.JSONException;

final class zzdil implements zzdii {
    zzdil() {
    }

    public final zzdin zzac(byte[] bArr) throws zzdib {
        if (bArr == null) {
            throw new zzdib("Cannot parse a null byte[]");
        } else if (bArr.length != 0) {
            try {
                zzdjk zzmz = zzdic.zzmz(new String(bArr));
                if (zzmz != null) {
                    zzdal.v("The runtime configuration was successfully parsed from the resource");
                }
                return new zzdin(Status.zzftq, 0, (zzdio) null, zzmz);
            } catch (JSONException unused) {
                throw new zzdib("The resource data is corrupted. The runtime configuration cannot be extracted from the JSON data");
            } catch (zzdib unused2) {
                throw new zzdib("The resource data is invalid. The runtime  configuration cannot be extracted from the JSON data");
            }
        } else {
            throw new zzdib("Cannot parse a 0 length byte[]");
        }
    }
}
