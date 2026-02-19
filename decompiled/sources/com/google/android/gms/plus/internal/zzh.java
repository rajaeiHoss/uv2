package com.google.android.gms.plus.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzaq;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzcwn;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public final class zzh extends zzab<zzf> {
    private Person zzkht;
    private final com.google.android.gms.plus.internal.zzn zzkhu;

    public zzh(Context context, Looper looper, zzr zzr, com.google.android.gms.plus.internal.zzn zzn, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 2, zzr, connectionCallbacks, onConnectionFailedListener);
        this.zzkhu = zzn;
    }

    public final String getAccountName() {
        zzalv();
        try {
            return ((zzf) zzalw()).getAccountName();
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    public final zzaq zza(zzn<People.LoadPeopleResult> zzn, int i, String str) {
        zzalv();
        zzj zzj = new zzj(zzn);
        try {
            return ((zzf) zzalw()).zza(zzj, 1, i, -1, str);
        } catch (RemoteException unused) {
            zzj.zza(DataHolder.zzbz(8), (String) null);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(int i, IBinder iBinder, Bundle bundle, int i2) {
        if (i == 0 && bundle != null && bundle.containsKey("loaded_person")) {
            this.zzkht = zzcwn.zzx(bundle.getByteArray("loaded_person"));
        }
        super.zza(i, iBinder, bundle, i2);
    }

    public final void zza(zzn<People.LoadPeopleResult> zzn, Collection<String> collection) {
        zzalv();
        zzj zzj = new zzj(zzn);
        try {
            ((zzf) zzalw()).zza(zzj, new ArrayList(collection));
        } catch (RemoteException unused) {
            zzj.zza(DataHolder.zzbz(8), (String) null);
        }
    }

    /* access modifiers changed from: protected */
    public final Bundle zzabt() {
        Bundle zzbeq = this.zzkhu.zzbeq();
        zzbeq.putStringArray("request_visible_actions", this.zzkhu.zzbeo());
        zzbeq.putString("auth_package", this.zzkhu.zzbep());
        return zzbeq;
    }

    public final boolean zzacc() {
        Set<Scope> zzc = zzamr().zzc((Api<?>) Plus.API);
        if (zzc == null || zzc.isEmpty()) {
            return false;
        }
        return zzc.size() != 1 || !zzc.contains(new Scope("plus_one_placeholder_scope"));
    }

    public final void zzbem() {
        zzalv();
        try {
            this.zzkht = null;
            ((zzf) zzalw()).zzbem();
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    public final Person zzben() {
        zzalv();
        return this.zzkht;
    }

    public final void zzc(zzn<People.LoadPeopleResult> zzn, String[] strArr) {
        zza(zzn, Arrays.asList(strArr));
    }

    /* access modifiers changed from: protected */
    public final zzf zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.plus.internal.IPlusService");
        return queryLocalInterface instanceof zzf ? (zzf) queryLocalInterface : new zzg(iBinder);
    }

    /* access modifiers changed from: protected */
    public final String zzhm() {
        return "com.google.android.gms.plus.service.START";
    }

    /* access modifiers changed from: protected */
    public final String zzhn() {
        return "com.google.android.gms.plus.internal.IPlusService";
    }

    public final void zzj(zzn<People.LoadPeopleResult> zzn) {
        zzalv();
        zzj zzj = new zzj(zzn);
        try {
            ((zzf) zzalw()).zza(zzj, 2, 1, -1, (String) null);
        } catch (RemoteException unused) {
            zzj.zza(DataHolder.zzbz(8), (String) null);
        }
    }

    public final void zzk(zzn<Status> zzn) {
        zzalv();
        zzbem();
        zzk zzk = new zzk(zzn);
        try {
            ((zzf) zzalw()).zza(zzk);
        } catch (RemoteException unused) {
            zzk.zzk(8, (Bundle) null);
        }
    }
}
