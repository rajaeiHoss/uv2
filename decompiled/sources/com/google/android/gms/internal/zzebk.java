package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.PhoneAuthCredential;

final class zzebk extends zzeay {
    final /* synthetic */ zzebh zzmsb;

    zzebk(zzebh zzebh) {
        this.zzmsb = zzebh;
    }

    private final void zza(zzebq zzebq) {
        this.zzmsb.zzmrp.execute(new zzebp(this, zzebq));
    }

    public final void onFailure(Status status) throws RemoteException {
        if (this.zzmsb.zzmrg == 8) {
            boolean unused = this.zzmsb.zzleo = true;
            this.zzmsb.zzmrx = false;
            zza((zzebq) new zzebo(this, status));
            return;
        }
        this.zzmsb.zzay(status);
        this.zzmsb.zzax(status);
    }

    public final void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        boolean z = this.zzmsb.zzmrg == 8;
        int i = this.zzmsb.zzmrg;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        zzbq.zza(z, (Object) sb.toString());
        boolean unused = this.zzmsb.zzleo = true;
        this.zzmsb.zzmrx = true;
        zza((zzebq) new zzebm(this, phoneAuthCredential));
    }

    public final void zza(Status status, PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        if (this.zzmsb.zzmrq != null) {
            boolean unused = this.zzmsb.zzleo = true;
            this.zzmsb.zzmrq.zza(status, phoneAuthCredential);
            return;
        }
        onFailure(status);
    }

    public final void zza(zzebs zzebs) throws RemoteException {
        boolean z = this.zzmsb.zzmrg == 3;
        int i = this.zzmsb.zzmrg;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        zzbq.zza(z, (Object) sb.toString());
        this.zzmsb.zzmrt = zzebs;
        this.zzmsb.zzbub();
    }

    public final void zza(zzebw zzebw, zzebu zzebu) throws RemoteException {
        boolean z = this.zzmsb.zzmrg == 2;
        int i = this.zzmsb.zzmrg;
        StringBuilder sb = new StringBuilder(37);
        sb.append("Unexpected response type: ");
        sb.append(i);
        zzbq.zza(z, (Object) sb.toString());
        this.zzmsb.zzmrr = zzebw;
        this.zzmsb.zzmrs = zzebu;
        this.zzmsb.zzbub();
    }

    public final void zza(zzecc zzecc) throws RemoteException {
        boolean z = this.zzmsb.zzmrg == 4;
        int i = this.zzmsb.zzmrg;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        zzbq.zza(z, (Object) sb.toString());
        this.zzmsb.zzmru = zzecc;
        this.zzmsb.zzbub();
    }

    public final void zzb(zzebw zzebw) throws RemoteException {
        boolean z = true;
        if (this.zzmsb.zzmrg != 1) {
            z = false;
        }
        int i = this.zzmsb.zzmrg;
        StringBuilder sb = new StringBuilder(37);
        sb.append("Unexpected response type: ");
        sb.append(i);
        zzbq.zza(z, (Object) sb.toString());
        this.zzmsb.zzmrr = zzebw;
        this.zzmsb.zzbub();
    }

    public final void zzbtw() throws RemoteException {
        boolean z = this.zzmsb.zzmrg == 5;
        int i = this.zzmsb.zzmrg;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        zzbq.zza(z, (Object) sb.toString());
        this.zzmsb.zzbub();
    }

    public final void zzbtx() throws RemoteException {
        boolean z = this.zzmsb.zzmrg == 6;
        int i = this.zzmsb.zzmrg;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        zzbq.zza(z, (Object) sb.toString());
        this.zzmsb.zzbub();
    }

    public final void zzbty() throws RemoteException {
        boolean z = this.zzmsb.zzmrg == 9;
        int i = this.zzmsb.zzmrg;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        zzbq.zza(z, (Object) sb.toString());
        this.zzmsb.zzbub();
    }

    public final void zzpc(String str) throws RemoteException {
        boolean z = this.zzmsb.zzmrg == 7;
        int i = this.zzmsb.zzmrg;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        zzbq.zza(z, (Object) sb.toString());
        this.zzmsb.zzmrv = str;
        this.zzmsb.zzbub();
    }

    public final void zzpd(String str) throws RemoteException {
        boolean z = this.zzmsb.zzmrg == 8;
        int i = this.zzmsb.zzmrg;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        zzbq.zza(z, (Object) sb.toString());
        this.zzmsb.zzmpu = str;
        zza((zzebq) new zzebl(this, str));
    }

    public final void zzpe(String str) throws RemoteException {
        boolean z = this.zzmsb.zzmrg == 8;
        int i = this.zzmsb.zzmrg;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        zzbq.zza(z, (Object) sb.toString());
        this.zzmsb.zzmpu = str;
        boolean unused = this.zzmsb.zzleo = true;
        this.zzmsb.zzmrx = true;
        zza((zzebq) new zzebn(this, str));
    }
}
