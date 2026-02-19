package com.google.android.gms.awareness.state;

import com.google.android.gms.internal.zzayq;
import com.google.android.gms.internal.zzbgl;
import java.util.List;

public interface BeaconState {

    public interface BeaconInfo {
        byte[] getContent();

        String getNamespace();

        String getType();
    }

    public static abstract class TypeFilter extends zzbgl {
        protected TypeFilter() {
        }

        public static TypeFilter with(String str, String str2) {
            return new zzayq(str, str2);
        }

        public static TypeFilter with(String str, String str2, byte[] bArr) {
            return new zzayq(str, str2, bArr);
        }
    }

    List<BeaconInfo> getBeaconInfo();
}
