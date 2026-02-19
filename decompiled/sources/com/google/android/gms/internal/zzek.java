package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public final class zzek extends zzeu {
    private List<Long> zzakp = null;

    public zzek(zzdm zzdm, String str, String str2, zzba zzba, int i, int i2) {
        super(zzdm, str, str2, zzba, i, 31);
    }

    /* access modifiers changed from: protected */
    public final void zzaw() throws IllegalAccessException, InvocationTargetException {
        this.zzakm.zzdw = -1L;
        this.zzakm.zzdx = -1L;
        if (this.zzakp == null) {
            this.zzakp = (List) this.zzaku.invoke((Object) null, new Object[]{this.zzagq.getContext()});
        }
        List<Long> list = this.zzakp;
        if (list != null && list.size() == 2) {
            synchronized (this.zzakm) {
                this.zzakm.zzdw = Long.valueOf(this.zzakp.get(0).longValue());
                this.zzakm.zzdx = Long.valueOf(this.zzakp.get(1).longValue());
            }
        }
    }
}
