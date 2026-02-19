package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.Iterator;

public abstract class AbstractDataBuffer<T> implements DataBuffer<T> {
    protected final DataHolder zzfxb;

    protected AbstractDataBuffer(DataHolder dataHolder) {
        this.zzfxb = dataHolder;
    }

    @Deprecated
    public final void close() {
        release();
    }

    public abstract T get(int i);

    public int getCount() {
        DataHolder dataHolder = this.zzfxb;
        if (dataHolder == null) {
            return 0;
        }
        return dataHolder.zzgcq;
    }

    @Deprecated
    public boolean isClosed() {
        DataHolder dataHolder = this.zzfxb;
        return dataHolder == null || dataHolder.isClosed();
    }

    public Iterator<T> iterator() {
        return new zzb(this);
    }

    public void release() {
        DataHolder dataHolder = this.zzfxb;
        if (dataHolder != null) {
            dataHolder.close();
        }
    }

    public Iterator<T> singleRefIterator() {
        return new zzh(this);
    }

    public final Bundle zzahs() {
        return this.zzfxb.zzahs();
    }
}
