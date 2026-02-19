package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzcq;
import com.google.android.gms.common.internal.zzar;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.events.OpenFileCallback;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbpn extends zzcq<zzbnq, OpenFileCallback> {
    private /* synthetic */ DriveFile zzgvk;
    private /* synthetic */ int zzgvl;
    private /* synthetic */ zzblv zzgvm;
    private /* synthetic */ zzci zzgvn;
    private /* synthetic */ zzboz zzgvo;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbpn(zzboz zzboz, zzci zzci, DriveFile driveFile, int i, zzblv zzblv, zzci zzci2) {
        super(zzci);
        this.zzgvo = zzboz;
        this.zzgvk = driveFile;
        this.zzgvl = i;
        this.zzgvm = zzblv;
        this.zzgvn = zzci2;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzbnq zzbnq, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        this.zzgvm.zza(zzar.zzal(((zzbrk) zzbnq.zzalw()).zza(new zzbsz(this.zzgvk.getDriveId(), this.zzgvl, 0), (zzbrm) new zzbqg(this.zzgvo, this.zzgvm, this.zzgvn)).zzgwo));
        taskCompletionSource.setResult(null);
    }
}
