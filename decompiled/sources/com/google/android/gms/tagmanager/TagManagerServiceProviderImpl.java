package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzdah;
import com.google.android.gms.internal.zzdcb;

public class TagManagerServiceProviderImpl extends zzcu {
    private static volatile zzdcb zzkug;

    public zzdah getService(IObjectWrapper iObjectWrapper, zzcn zzcn, zzce zzce) throws RemoteException {
        zzdcb zzdcb = zzkug;
        if (zzdcb == null) {
            synchronized (TagManagerServiceProviderImpl.class) {
                zzdcb = zzkug;
                if (zzdcb == null) {
                    zzdcb = new zzdcb((Context) zzn.zzy(iObjectWrapper), zzcn, zzce);
                    zzkug = zzdcb;
                }
            }
        }
        return zzdcb;
    }
}
