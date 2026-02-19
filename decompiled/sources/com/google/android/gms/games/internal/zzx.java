package com.google.android.gms.games.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.zzc;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.google.android.gms.games.GamesActivityResultCodes;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import com.google.android.gms.games.snapshot.zze;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.location.places.PlacesStatusCodes;
import com.google.android.gms.nearby.connection.ConnectionsStatusCodes;
import com.google.android.gms.safetynet.SafetyNetStatusCodes;

public final class zzx extends zzev implements zzw {
    zzx(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.games.internal.IGamesService");
    }

    public final String getAppId() throws RemoteException {
        Parcel zza = zza(FitnessStatusCodes.DATA_TYPE_NOT_FOUND, zzbc());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    public final int zza(zzs zzs, byte[] bArr, String str, String str2) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeByteArray(bArr);
        zzbc.writeString(str);
        zzbc.writeString(str2);
        Parcel zza = zza(5033, zzbc);
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    public final Intent zza(int i, byte[] bArr, int i2, String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeInt(i);
        zzbc.writeByteArray(bArr);
        zzbc.writeInt(i2);
        zzbc.writeString(str);
        Parcel zza = zza(10012, zzbc);
        Intent intent = (Intent) zzex.zza(zza, Intent.CREATOR);
        zza.recycle();
        return intent;
    }

    public final Intent zza(PlayerEntity playerEntity) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) playerEntity);
        Parcel zza = zza(15503, zzbc);
        Intent intent = (Intent) zzex.zza(zza, Intent.CREATOR);
        zza.recycle();
        return intent;
    }

    public final Intent zza(RoomEntity roomEntity, int i) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) roomEntity);
        zzbc.writeInt(i);
        Parcel zza = zza(9011, zzbc);
        Intent intent = (Intent) zzex.zza(zza, Intent.CREATOR);
        zza.recycle();
        return intent;
    }

    public final Intent zza(String str, boolean z, boolean z2, int i) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzex.zza(zzbc, z);
        zzex.zza(zzbc, z2);
        zzbc.writeInt(i);
        Parcel zza = zza(SafetyNetStatusCodes.SAFE_BROWSING_MISSING_API_KEY, zzbc);
        Intent intent = (Intent) zzex.zza(zza, Intent.CREATOR);
        zza.recycle();
        return intent;
    }

    public final void zza(IBinder iBinder, Bundle bundle) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeStrongBinder(iBinder);
        zzex.zza(zzbc, (Parcelable) bundle);
        zzb(FitnessStatusCodes.UNKNOWN_AUTH_ERROR, zzbc);
    }

    public final void zza(zzc zzc) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzc);
        zzb(12019, zzbc);
    }

    public final void zza(zzs zzs) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzb(FitnessStatusCodes.INCONSISTENT_DATA_TYPE, zzbc);
    }

    public final void zza(zzs zzs, int i) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeInt(i);
        zzb(10016, zzbc);
    }

    public final void zza(zzs zzs, int i, int i2, int i3) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeInt(i);
        zzbc.writeInt(i2);
        zzbc.writeInt(i3);
        zzb(10009, zzbc);
    }

    public final void zza(zzs zzs, int i, int i2, String[] strArr, Bundle bundle) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeInt(i);
        zzbc.writeInt(i2);
        zzbc.writeStringArray(strArr);
        zzex.zza(zzbc, (Parcelable) bundle);
        zzb(ConnectionsStatusCodes.STATUS_CONNECTION_REJECTED, zzbc);
    }

    public final void zza(zzs zzs, int i, boolean z, boolean z2) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeInt(i);
        zzex.zza(zzbc, z);
        zzex.zza(zzbc, z2);
        zzb(FitnessStatusCodes.INCONSISTENT_PACKAGE_NAME, zzbc);
    }

    public final void zza(zzs zzs, int i, int[] iArr) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeInt(i);
        zzbc.writeIntArray(iArr);
        zzb(10018, zzbc);
    }

    public final void zza(zzs zzs, long j) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeLong(j);
        zzb(5058, zzbc);
    }

    public final void zza(zzs zzs, Bundle bundle, int i, int i2) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzex.zza(zzbc, (Parcelable) bundle);
        zzbc.writeInt(i);
        zzbc.writeInt(i2);
        zzb(FitnessStatusCodes.INVALID_DATA_POINT, zzbc);
    }

    public final void zza(zzs zzs, IBinder iBinder, int i, String[] strArr, Bundle bundle, boolean z, long j) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeStrongBinder(iBinder);
        zzbc.writeInt(i);
        zzbc.writeStringArray(strArr);
        zzex.zza(zzbc, (Parcelable) bundle);
        zzex.zza(zzbc, false);
        zzbc.writeLong(j);
        zzb(5030, zzbc);
    }

    public final void zza(zzs zzs, IBinder iBinder, String str, boolean z, long j) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeStrongBinder(iBinder);
        zzbc.writeString(str);
        zzex.zza(zzbc, false);
        zzbc.writeLong(j);
        zzb(5031, zzbc);
    }

    public final void zza(zzs zzs, String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeString(str);
        zzb(5032, zzbc);
    }

    public final void zza(zzs zzs, String str, int i, int i2, int i3, boolean z) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeString(str);
        zzbc.writeInt(i);
        zzbc.writeInt(i2);
        zzbc.writeInt(i3);
        zzex.zza(zzbc, z);
        zzb(FitnessStatusCodes.DATASET_TIMESTAMP_INCONSISTENT_WITH_UPDATE_TIME_RANGE, zzbc);
    }

    public final void zza(zzs zzs, String str, int i, IBinder iBinder, Bundle bundle) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeString(str);
        zzbc.writeInt(i);
        zzbc.writeStrongBinder(iBinder);
        zzex.zza(zzbc, (Parcelable) bundle);
        zzb(5025, zzbc);
    }

    public final void zza(zzs zzs, String str, int i, boolean z, boolean z2) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeString(str);
        zzbc.writeInt(i);
        zzex.zza(zzbc, z);
        zzex.zza(zzbc, z2);
        zzb(9020, zzbc);
    }

    public final void zza(zzs zzs, String str, long j, String str2) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeString(str);
        zzbc.writeLong(j);
        zzbc.writeString(str2);
        zzb(GamesStatusCodes.STATUS_INVALID_REAL_TIME_ROOM_ID, zzbc);
    }

    public final void zza(zzs zzs, String str, IBinder iBinder, Bundle bundle) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeString(str);
        zzbc.writeStrongBinder(iBinder);
        zzex.zza(zzbc, (Parcelable) bundle);
        zzb(FitnessStatusCodes.DATA_TYPE_NOT_ALLOWED_FOR_API, zzbc);
    }

    public final void zza(zzs zzs, String str, zze zze, zzc zzc) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeString(str);
        zzex.zza(zzbc, (Parcelable) zze);
        zzex.zza(zzbc, (Parcelable) zzc);
        zzb(SafetyNetStatusCodes.RECAPTCHA_INVALID_SITEKEY, zzbc);
    }

    public final void zza(zzs zzs, String str, String str2) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzb(ConnectionsStatusCodes.STATUS_ENDPOINT_UNKNOWN, zzbc);
    }

    public final void zza(zzs zzs, String str, String str2, int i, int i2) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeString((String) null);
        zzbc.writeString(str2);
        zzbc.writeInt(i);
        zzbc.writeInt(i2);
        zzb(8001, zzbc);
    }

    public final void zza(zzs zzs, String str, String str2, zze zze, zzc zzc) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzex.zza(zzbc, (Parcelable) zze);
        zzex.zza(zzbc, (Parcelable) zzc);
        zzb(12033, zzbc);
    }

    public final void zza(zzs zzs, String str, boolean z) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeString(str);
        zzex.zza(zzbc, z);
        zzb(GamesStatusCodes.STATUS_MATCH_ERROR_INVALID_MATCH_RESULTS, zzbc);
    }

    public final void zza(zzs zzs, String str, boolean z, int i) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeString(str);
        zzex.zza(zzbc, z);
        zzbc.writeInt(i);
        zzb(15001, zzbc);
    }

    public final void zza(zzs zzs, String str, byte[] bArr, String str2, ParticipantResult[] participantResultArr) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeString(str);
        zzbc.writeByteArray(bArr);
        zzbc.writeString(str2);
        zzbc.writeTypedArray(participantResultArr, 0);
        zzb(ConnectionsStatusCodes.STATUS_BLUETOOTH_ERROR, zzbc);
    }

    public final void zza(zzs zzs, String str, byte[] bArr, ParticipantResult[] participantResultArr) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeString(str);
        zzbc.writeByteArray(bArr);
        zzbc.writeTypedArray(participantResultArr, 0);
        zzb(ConnectionsStatusCodes.STATUS_ALREADY_HAVE_ACTIVE_STRATEGY, zzbc);
    }

    public final void zza(zzs zzs, boolean z) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzex.zza(zzbc, z);
        zzb(GamesStatusCodes.STATUS_MULTIPLAYER_ERROR_NOT_TRUSTED_TESTER, zzbc);
    }

    public final void zza(zzs zzs, boolean z, String[] strArr) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzex.zza(zzbc, z);
        zzbc.writeStringArray(strArr);
        zzb(12031, zzbc);
    }

    public final void zza(zzs zzs, int[] iArr, int i, boolean z) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeIntArray(iArr);
        zzbc.writeInt(i);
        zzex.zza(zzbc, z);
        zzb(SafetyNetStatusCodes.OS_VERSION_NOT_SUPPORTED, zzbc);
    }

    public final void zza(zzs zzs, String[] strArr) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeStringArray(strArr);
        zzb(GamesActivityResultCodes.RESULT_NETWORK_FAILURE, zzbc);
    }

    public final void zza(zzs zzs, String[] strArr, boolean z) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeStringArray(strArr);
        zzex.zza(zzbc, z);
        zzb(12029, zzbc);
    }

    public final void zza(zzu zzu, long j) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzu);
        zzbc.writeLong(j);
        zzb(15501, zzbc);
    }

    public final void zza(String str, IBinder iBinder, Bundle bundle) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzbc.writeStrongBinder(iBinder);
        zzex.zza(zzbc, (Parcelable) bundle);
        zzb(13002, zzbc);
    }

    public final void zza(String str, zzs zzs) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzex.zza(zzbc, (IInterface) zzs);
        zzb(20001, zzbc);
    }

    public final void zzac(long j) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeLong(j);
        zzb(FitnessStatusCodes.CONFLICTING_DATA_TYPE, zzbc);
    }

    public final void zzad(long j) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeLong(j);
        zzb(5059, zzbc);
    }

    public final void zzae(long j) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeLong(j);
        zzb(ConnectionsStatusCodes.STATUS_PAYLOAD_IO_ERROR, zzbc);
    }

    public final void zzaf(long j) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeLong(j);
        zzb(GamesActivityResultCodes.RESULT_SIGN_IN_FAILED, zzbc);
    }

    public final void zzag(long j) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeLong(j);
        zzb(SafetyNetStatusCodes.SINGLE_USER_SERVICE_NOT_AVAILABLE, zzbc);
    }

    public final Bundle zzagp() throws RemoteException {
        Parcel zza = zza(FitnessStatusCodes.APP_MISMATCH, zzbc());
        Bundle bundle = (Bundle) zzex.zza(zza, Bundle.CREATOR);
        zza.recycle();
        return bundle;
    }

    public final void zzah(long j) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeLong(j);
        zzb(22027, zzbc);
    }

    public final String zzate() throws RemoteException {
        Parcel zza = zza(FitnessStatusCodes.UNSUPPORTED_PLATFORM, zzbc());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    public final Intent zzatk() throws RemoteException {
        Parcel zza = zza(9003, zzbc());
        Intent intent = (Intent) zzex.zza(zza, Intent.CREATOR);
        zza.recycle();
        return intent;
    }

    public final Intent zzatm() throws RemoteException {
        Parcel zza = zza(PlacesStatusCodes.RATE_LIMIT_EXCEEDED, zzbc());
        Intent intent = (Intent) zzex.zza(zza, Intent.CREATOR);
        zza.recycle();
        return intent;
    }

    public final Intent zzatn() throws RemoteException {
        Parcel zza = zza(9006, zzbc());
        Intent intent = (Intent) zzex.zza(zza, Intent.CREATOR);
        zza.recycle();
        return intent;
    }

    public final Intent zzato() throws RemoteException {
        Parcel zza = zza(PlacesStatusCodes.KEY_EXPIRED, zzbc());
        Intent intent = (Intent) zzex.zza(zza, Intent.CREATOR);
        zza.recycle();
        return intent;
    }

    public final Intent zzatv() throws RemoteException {
        Parcel zza = zza(9010, zzbc());
        Intent intent = (Intent) zzex.zza(zza, Intent.CREATOR);
        zza.recycle();
        return intent;
    }

    public final Intent zzatx() throws RemoteException {
        Parcel zza = zza(9012, zzbc());
        Intent intent = (Intent) zzex.zza(zza, Intent.CREATOR);
        zza.recycle();
        return intent;
    }

    public final int zzatz() throws RemoteException {
        Parcel zza = zza(9019, zzbc());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    public final int zzauc() throws RemoteException {
        Parcel zza = zza(8024, zzbc());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    public final Intent zzaue() throws RemoteException {
        Parcel zza = zza(10015, zzbc());
        Intent intent = (Intent) zzex.zza(zza, Intent.CREATOR);
        zza.recycle();
        return intent;
    }

    public final int zzauf() throws RemoteException {
        Parcel zza = zza(10013, zzbc());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    public final int zzaug() throws RemoteException {
        Parcel zza = zza(10023, zzbc());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    public final int zzauh() throws RemoteException {
        Parcel zza = zza(12035, zzbc());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    public final int zzauj() throws RemoteException {
        Parcel zza = zza(12036, zzbc());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    public final boolean zzaun() throws RemoteException {
        Parcel zza = zza(22030, zzbc());
        boolean zza2 = zzex.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final void zzaur() throws RemoteException {
        zzb(FitnessStatusCodes.MISSING_BLE_PERMISSION, zzbc());
    }

    public final String zzaut() throws RemoteException {
        Parcel zza = zza(FitnessStatusCodes.AGGREGATION_NOT_SUPPORTED, zzbc());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    public final DataHolder zzauu() throws RemoteException {
        Parcel zza = zza(FitnessStatusCodes.UNSUPPORTED_ACCOUNT, zzbc());
        DataHolder dataHolder = (DataHolder) zzex.zza(zza, DataHolder.CREATOR);
        zza.recycle();
        return dataHolder;
    }

    public final DataHolder zzauv() throws RemoteException {
        Parcel zza = zza(5502, zzbc());
        DataHolder dataHolder = (DataHolder) zzex.zza(zza, DataHolder.CREATOR);
        zza.recycle();
        return dataHolder;
    }

    public final Intent zzauw() throws RemoteException {
        Parcel zza = zza(19002, zzbc());
        Intent intent = (Intent) zzex.zza(zza, Intent.CREATOR);
        zza.recycle();
        return intent;
    }

    public final int zzb(byte[] bArr, String str, String[] strArr) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeByteArray(bArr);
        zzbc.writeString(str);
        zzbc.writeStringArray(strArr);
        Parcel zza = zza(5034, zzbc);
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    public final Intent zzb(int i, int i2, boolean z) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeInt(i);
        zzbc.writeInt(i2);
        zzex.zza(zzbc, z);
        Parcel zza = zza(PlacesStatusCodes.INVALID_APP, zzbc);
        Intent intent = (Intent) zzex.zza(zza, Intent.CREATOR);
        zza.recycle();
        return intent;
    }

    public final void zzb(zzs zzs) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzb(5026, zzbc);
    }

    public final void zzb(zzs zzs, int i) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeInt(i);
        zzb(22016, zzbc);
    }

    public final void zzb(zzs zzs, long j) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeLong(j);
        zzb(ConnectionsStatusCodes.STATUS_ENDPOINT_IO_ERROR, zzbc);
    }

    public final void zzb(zzs zzs, String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeString(str);
        zzb(ConnectionsStatusCodes.STATUS_NOT_CONNECTED_TO_ENDPOINT, zzbc);
    }

    public final void zzb(zzs zzs, String str, int i, int i2, int i3, boolean z) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeString(str);
        zzbc.writeInt(i);
        zzbc.writeInt(i2);
        zzbc.writeInt(i3);
        zzex.zza(zzbc, z);
        zzb(FitnessStatusCodes.INVALID_SESSION_TIMESTAMPS, zzbc);
    }

    public final void zzb(zzs zzs, String str, int i, IBinder iBinder, Bundle bundle) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeString(str);
        zzbc.writeInt(i);
        zzbc.writeStrongBinder(iBinder);
        zzex.zza(zzbc, (Parcelable) bundle);
        zzb(GamesStatusCodes.STATUS_PARTICIPANT_NOT_CONNECTED, zzbc);
    }

    public final void zzb(zzs zzs, String str, IBinder iBinder, Bundle bundle) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeString(str);
        zzbc.writeStrongBinder(iBinder);
        zzex.zza(zzbc, (Parcelable) bundle);
        zzb(FitnessStatusCodes.REQUIRES_APP_WHITELISTING, zzbc);
    }

    public final void zzb(zzs zzs, String str, String str2) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzb(SafetyNetStatusCodes.SAFE_BROWSING_API_NOT_INITIALIZED, zzbc);
    }

    public final void zzb(zzs zzs, String str, boolean z) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeString(str);
        zzex.zza(zzbc, z);
        zzb(13006, zzbc);
    }

    public final void zzb(zzs zzs, boolean z) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzex.zza(zzbc, z);
        zzb(GamesStatusCodes.STATUS_MATCH_ERROR_OUT_OF_DATE_VERSION, zzbc);
    }

    public final void zzb(zzs zzs, String[] strArr) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeStringArray(strArr);
        zzb(GamesActivityResultCodes.RESULT_SEND_REQUEST_FAILED, zzbc);
    }

    public final Intent zzc(int[] iArr) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeIntArray(iArr);
        Parcel zza = zza(12030, zzbc);
        Intent intent = (Intent) zzex.zza(zza, Intent.CREATOR);
        zza.recycle();
        return intent;
    }

    public final void zzc(zzs zzs) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzb(21007, zzbc);
    }

    public final void zzc(zzs zzs, long j) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeLong(j);
        zzb(10001, zzbc);
    }

    public final void zzc(zzs zzs, String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeString(str);
        zzb(8006, zzbc);
    }

    public final void zzc(zzs zzs, boolean z) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzex.zza(zzbc, z);
        zzb(8027, zzbc);
    }

    public final Intent zzd(int i, int i2, boolean z) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeInt(i);
        zzbc.writeInt(i2);
        zzex.zza(zzbc, z);
        Parcel zza = zza(GamesStatusCodes.STATUS_VIDEO_OUT_OF_DISK_SPACE, zzbc);
        Intent intent = (Intent) zzex.zza(zza, Intent.CREATOR);
        zza.recycle();
        return intent;
    }

    public final void zzd(zzs zzs) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzb(22028, zzbc);
    }

    public final void zzd(zzs zzs, long j) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeLong(j);
        zzb(SafetyNetStatusCodes.INVALID_ADMIN_APPLICATION, zzbc);
    }

    public final void zzd(zzs zzs, String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeString(str);
        zzb(ConnectionsStatusCodes.STATUS_OUT_OF_ORDER_API_CALL, zzbc);
    }

    public final void zzd(zzs zzs, boolean z) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzex.zza(zzbc, z);
        zzb(SafetyNetStatusCodes.SAFE_BROWSING_API_NOT_AVAILABLE, zzbc);
    }

    public final void zzdt(int i) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeInt(i);
        zzb(5036, zzbc);
    }

    public final void zze(zzs zzs, long j) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeLong(j);
        zzb(22026, zzbc);
    }

    public final void zze(zzs zzs, String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeString(str);
        zzb(8010, zzbc);
    }

    public final void zze(zzs zzs, boolean z) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzex.zza(zzbc, z);
        zzb(12016, zzbc);
    }

    public final void zzf(zzs zzs, String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeString(str);
        zzb(8014, zzbc);
    }

    public final void zzf(zzs zzs, boolean z) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzex.zza(zzbc, z);
        zzb(17001, zzbc);
    }

    public final void zzg(zzs zzs, String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeString(str);
        zzb(12020, zzbc);
    }

    public final void zzh(zzs zzs, String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzs);
        zzbc.writeString(str);
        zzb(SafetyNetStatusCodes.RECAPTCHA_INVALID_KEYTYPE, zzbc);
    }

    public final Intent zzia(String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        Parcel zza = zza(12034, zzbc);
        Intent intent = (Intent) zzex.zza(zza, Intent.CREATOR);
        zza.recycle();
        return intent;
    }

    public final void zzic(String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzb(8002, zzbc);
    }

    public final Intent zzk(String str, int i, int i2) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzbc.writeInt(i);
        zzbc.writeInt(i2);
        Parcel zza = zza(18001, zzbc);
        Intent intent = (Intent) zzex.zza(zza, Intent.CREATOR);
        zza.recycle();
        return intent;
    }

    public final void zzp(String str, int i) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzbc.writeInt(i);
        zzb(SafetyNetStatusCodes.REMOVE_HARMFULAPP_ACTION_NOT_RESOLVED, zzbc);
    }

    public final void zzq(String str, int i) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzbc.writeInt(i);
        zzb(5029, zzbc);
    }

    public final void zzs(String str, int i) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzbc.writeInt(i);
        zzb(5028, zzbc);
    }
}
