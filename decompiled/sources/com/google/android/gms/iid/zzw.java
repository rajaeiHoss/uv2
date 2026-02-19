package com.google.android.gms.iid;

import android.os.Bundle;
import android.util.Log;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

final class zzw extends zzu<Bundle> {
    zzw(int i, int i2, Bundle bundle) {
        super(i, 1, bundle);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaww() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final void zzx(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle(DataPacketExtension.ELEMENT_NAME);
        if (bundle2 == null) {
            bundle2 = Bundle.EMPTY;
        }
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(this);
            String valueOf2 = String.valueOf(bundle2);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 16 + String.valueOf(valueOf2).length());
            sb.append("Finishing ");
            sb.append(valueOf);
            sb.append(" with ");
            sb.append(valueOf2);
            Log.d("MessengerIpcClient", sb.toString());
        }
        this.zzgyc.setResult(bundle2);
    }
}
