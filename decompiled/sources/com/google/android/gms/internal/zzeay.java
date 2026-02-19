package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.PhoneAuthCredential;

public abstract class zzeay extends zzew implements zzeax {
    public zzeay() {
        attachInterface(this, "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                zzb((zzebw) zzex.zza(parcel, zzebw.CREATOR));
                break;
            case 2:
                zza((zzebw) zzex.zza(parcel, zzebw.CREATOR), (zzebu) zzex.zza(parcel, zzebu.CREATOR));
                break;
            case 3:
                zza((zzebs) zzex.zza(parcel, zzebs.CREATOR));
                break;
            case 4:
                zza((zzecc) zzex.zza(parcel, zzecc.CREATOR));
                break;
            case 5:
                onFailure((Status) zzex.zza(parcel, Status.CREATOR));
                break;
            case 6:
                zzbtw();
                break;
            case 7:
                zzbtx();
                break;
            case 8:
                zzpc(parcel.readString());
                break;
            case 9:
                zzpd(parcel.readString());
                break;
            case 10:
                onVerificationCompleted((PhoneAuthCredential) zzex.zza(parcel, PhoneAuthCredential.CREATOR));
                break;
            case 11:
                zzpe(parcel.readString());
                break;
            case 12:
                zza((Status) zzex.zza(parcel, Status.CREATOR), (PhoneAuthCredential) zzex.zza(parcel, PhoneAuthCredential.CREATOR));
                break;
            case 13:
                zzbty();
                break;
            default:
                return false;
        }
        return true;
    }
}
