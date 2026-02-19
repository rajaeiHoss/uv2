package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;

public final class zzeba extends zzev implements zzeaz {
    zzeba(IBinder iBinder) {
        super(iBinder, "com.google.firebase.auth.api.internal.IFirebaseAuthService");
    }

    public final void zza(zzeax zzeax) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzeax);
        zzb(16, zzbc);
    }

    public final void zza(zzece zzece, zzeax zzeax) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzece);
        zzex.zza(zzbc, (IInterface) zzeax);
        zzb(22, zzbc);
    }

    public final void zza(zzeci zzeci, zzeax zzeax) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzeci);
        zzex.zza(zzbc, (IInterface) zzeax);
        zzb(3, zzbc);
    }

    public final void zza(EmailAuthCredential emailAuthCredential, zzeax zzeax) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) emailAuthCredential);
        zzex.zza(zzbc, (IInterface) zzeax);
        zzb(29, zzbc);
    }

    public final void zza(PhoneAuthCredential phoneAuthCredential, zzeax zzeax) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) phoneAuthCredential);
        zzex.zza(zzbc, (IInterface) zzeax);
        zzb(23, zzbc);
    }

    public final void zza(String str, zzeax zzeax) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzex.zza(zzbc, (IInterface) zzeax);
        zzb(1, zzbc);
    }

    public final void zza(String str, zzeci zzeci, zzeax zzeax) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzex.zza(zzbc, (Parcelable) zzeci);
        zzex.zza(zzbc, (IInterface) zzeax);
        zzb(12, zzbc);
    }

    public final void zza(String str, ActionCodeSettings actionCodeSettings, zzeax zzeax) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzex.zza(zzbc, (Parcelable) actionCodeSettings);
        zzex.zza(zzbc, (IInterface) zzeax);
        zzb(26, zzbc);
    }

    public final void zza(String str, PhoneAuthCredential phoneAuthCredential, zzeax zzeax) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzex.zza(zzbc, (Parcelable) phoneAuthCredential);
        zzex.zza(zzbc, (IInterface) zzeax);
        zzb(24, zzbc);
    }

    public final void zza(String str, UserProfileChangeRequest userProfileChangeRequest, zzeax zzeax) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzex.zza(zzbc, (Parcelable) userProfileChangeRequest);
        zzex.zza(zzbc, (IInterface) zzeax);
        zzb(4, zzbc);
    }

    public final void zza(String str, String str2, zzeax zzeax) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzex.zza(zzbc, (IInterface) zzeax);
        zzb(5, zzbc);
    }

    public final void zza(String str, String str2, String str3, zzeax zzeax) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzbc.writeString(str3);
        zzex.zza(zzbc, (IInterface) zzeax);
        zzb(11, zzbc);
    }

    public final void zzb(String str, zzeax zzeax) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzex.zza(zzbc, (IInterface) zzeax);
        zzb(2, zzbc);
    }

    public final void zzb(String str, ActionCodeSettings actionCodeSettings, zzeax zzeax) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzex.zza(zzbc, (Parcelable) actionCodeSettings);
        zzex.zza(zzbc, (IInterface) zzeax);
        zzb(28, zzbc);
    }

    public final void zzb(String str, String str2, zzeax zzeax) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzex.zza(zzbc, (IInterface) zzeax);
        zzb(6, zzbc);
    }

    public final void zzc(String str, zzeax zzeax) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzex.zza(zzbc, (IInterface) zzeax);
        zzb(9, zzbc);
    }

    public final void zzc(String str, String str2, zzeax zzeax) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzex.zza(zzbc, (IInterface) zzeax);
        zzb(7, zzbc);
    }

    public final void zzd(String str, zzeax zzeax) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzex.zza(zzbc, (IInterface) zzeax);
        zzb(13, zzbc);
    }

    public final void zzd(String str, String str2, zzeax zzeax) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzex.zza(zzbc, (IInterface) zzeax);
        zzb(8, zzbc);
    }

    public final void zze(String str, zzeax zzeax) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzex.zza(zzbc, (IInterface) zzeax);
        zzb(15, zzbc);
    }

    public final void zze(String str, String str2, zzeax zzeax) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzex.zza(zzbc, (IInterface) zzeax);
        zzb(14, zzbc);
    }

    public final void zzf(String str, zzeax zzeax) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzex.zza(zzbc, (IInterface) zzeax);
        zzb(17, zzbc);
    }

    public final void zzf(String str, String str2, zzeax zzeax) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzex.zza(zzbc, (IInterface) zzeax);
        zzb(21, zzbc);
    }

    public final void zzg(String str, zzeax zzeax) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzex.zza(zzbc, (IInterface) zzeax);
        zzb(19, zzbc);
    }

    public final void zzh(String str, zzeax zzeax) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzex.zza(zzbc, (IInterface) zzeax);
        zzb(20, zzbc);
    }

    public final void zzi(String str, zzeax zzeax) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzex.zza(zzbc, (IInterface) zzeax);
        zzb(27, zzbc);
    }
}
