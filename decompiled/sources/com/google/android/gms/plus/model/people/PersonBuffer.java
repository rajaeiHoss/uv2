package com.google.android.gms.plus.model.people;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzd;
import com.google.android.gms.internal.zzcwn;
import com.google.android.gms.internal.zzcwy;

@Deprecated
public final class PersonBuffer extends AbstractDataBuffer<Person> {
    private final zzd<zzcwn> zzkjv;

    public PersonBuffer(DataHolder dataHolder) {
        super(dataHolder);
        if (dataHolder.zzahs() == null || !dataHolder.zzahs().getBoolean("com.google.android.gms.plus.IsSafeParcelable", false)) {
            this.zzkjv = null;
        } else {
            this.zzkjv = new zzd<>(dataHolder, zzcwn.CREATOR);
        }
    }

    @Deprecated
    public final Person get(int i) {
        zzd<zzcwn> zzd = this.zzkjv;
        return zzd != null ? (Person) zzd.get(i) : new zzcwy(this.zzfxb, i);
    }
}
