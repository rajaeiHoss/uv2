package com.google.android.gms.common.data;

import java.util.ArrayList;

public abstract class zzg<T> extends AbstractDataBuffer<T> {
    private boolean zzgcy = false;
    private ArrayList<Integer> zzgcz;

    protected zzg(DataHolder dataHolder) {
        super(dataHolder);
    }

    private final void zzalk() {
        synchronized (this) {
            if (!this.zzgcy) {
                int i = this.zzfxb.zzgcq;
                ArrayList<Integer> arrayList = new ArrayList<>();
                this.zzgcz = arrayList;
                if (i > 0) {
                    arrayList.add(0);
                    String zzalj = zzalj();
                    String zzd = this.zzfxb.zzd(zzalj, 0, this.zzfxb.zzby(0));
                    int i2 = 1;
                    while (i2 < i) {
                        int zzby = this.zzfxb.zzby(i2);
                        String zzd2 = this.zzfxb.zzd(zzalj, i2, zzby);
                        if (zzd2 != null) {
                            if (!zzd2.equals(zzd)) {
                                this.zzgcz.add(Integer.valueOf(i2));
                                zzd = zzd2;
                            }
                            i2++;
                        } else {
                            StringBuilder sb = new StringBuilder(String.valueOf(zzalj).length() + 78);
                            sb.append("Missing value for markerColumn: ");
                            sb.append(zzalj);
                            sb.append(", at row: ");
                            sb.append(i2);
                            sb.append(", for window: ");
                            sb.append(zzby);
                            throw new NullPointerException(sb.toString());
                        }
                    }
                }
                this.zzgcy = true;
            }
        }
    }

    private final int zzcb(int i) {
        if (i >= 0 && i < this.zzgcz.size()) {
            return this.zzgcz.get(i).intValue();
        }
        StringBuilder sb = new StringBuilder(53);
        sb.append("Position ");
        sb.append(i);
        sb.append(" is out of bounds for this buffer");
        throw new IllegalArgumentException(sb.toString());
    }

    public final T get(int i) {
        int i2;
        zzalk();
        int zzcb = zzcb(i);
        if (i < 0 || i == this.zzgcz.size()) {
            i2 = 0;
        } else {
            i2 = (i == this.zzgcz.size() - 1 ? this.zzfxb.zzgcq : this.zzgcz.get(i + 1).intValue()) - this.zzgcz.get(i).intValue();
            if (i2 == 1) {
                this.zzfxb.zzby(zzcb(i));
            }
        }
        return zzl(zzcb, i2);
    }

    public int getCount() {
        zzalk();
        return this.zzgcz.size();
    }

    /* access modifiers changed from: protected */
    public abstract String zzalj();

    /* access modifiers changed from: protected */
    public abstract T zzl(int i, int i2);
}
