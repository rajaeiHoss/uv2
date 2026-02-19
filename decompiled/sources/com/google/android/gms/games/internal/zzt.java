package com.google.android.gms.games.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.zzc;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.google.android.gms.games.GamesActivityResultCodes;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.video.VideoCapabilities;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.nearby.connection.ConnectionsStatusCodes;
import com.google.android.gms.safetynet.SafetyNetStatusCodes;

public abstract class zzt extends zzew implements zzs {
    public zzt() {
        attachInterface(this, "com.google.android.gms.games.internal.IGamesCallbacks");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 6001) {
            onP2PConnected(parcel.readString());
        } else if (i == 6002) {
            onP2PDisconnected(parcel.readString());
        } else if (i == 13001) {
            zzat((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
        } else if (i == 13002) {
            zzdk(parcel.readInt());
        } else if (i == 17001) {
            zzav((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
        } else if (i != 17002) {
            switch (i) {
                case FitnessStatusCodes.CONFLICTING_DATA_TYPE:
                    zzg(parcel.readInt(), parcel.readString());
                    break;
                case FitnessStatusCodes.INCONSISTENT_DATA_TYPE:
                    zzf((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                    break;
                case FitnessStatusCodes.DATA_TYPE_NOT_FOUND:
                    zzh(parcel.readInt(), parcel.readString());
                    break;
                case FitnessStatusCodes.APP_MISMATCH:
                    zzh((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                    break;
                case FitnessStatusCodes.UNKNOWN_AUTH_ERROR:
                    zza((DataHolder) zzex.zza(parcel, DataHolder.CREATOR), (DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                    break;
                case FitnessStatusCodes.MISSING_BLE_PERMISSION:
                    zzi((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                    break;
                case FitnessStatusCodes.UNSUPPORTED_PLATFORM:
                    zzj((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                    break;
                case FitnessStatusCodes.TRANSIENT_ERROR:
                    zzk((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                    break;
                case FitnessStatusCodes.EQUIVALENT_SESSION_ENDED:
                    zzl((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                    break;
                case FitnessStatusCodes.APP_NOT_FIT_ENABLED:
                    zzm((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                    break;
                case FitnessStatusCodes.API_EXCEPTION:
                    zzn((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                    break;
                default:
                    switch (i) {
                        case 5016:
                            zzako();
                            break;
                        case 5017:
                            zzp((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                            break;
                        case FitnessStatusCodes.DATA_SOURCE_NOT_FOUND:
                            zzx((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                            break;
                        case FitnessStatusCodes.DATASET_TIMESTAMP_INCONSISTENT_WITH_UPDATE_TIME_RANGE:
                            zzy((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                            break;
                        case FitnessStatusCodes.INVALID_SESSION_TIMESTAMPS:
                            onLeftRoom(parcel.readInt(), parcel.readString());
                            break;
                        case FitnessStatusCodes.INVALID_DATA_POINT:
                            zzz((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                            break;
                        case FitnessStatusCodes.INVALID_TIMESTAMP:
                            zzaa((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                            break;
                        case FitnessStatusCodes.DATA_TYPE_NOT_ALLOWED_FOR_API:
                            zzab((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                            break;
                        case FitnessStatusCodes.REQUIRES_APP_WHITELISTING:
                            zzac((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 5025:
                            zzad((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 5026:
                            zza((DataHolder) zzex.zza(parcel, DataHolder.CREATOR), parcel.createStringArray());
                            break;
                        case 5027:
                            zzb((DataHolder) zzex.zza(parcel, DataHolder.CREATOR), parcel.createStringArray());
                            break;
                        case 5028:
                            zzc((DataHolder) zzex.zza(parcel, DataHolder.CREATOR), parcel.createStringArray());
                            break;
                        case 5029:
                            zzd((DataHolder) zzex.zza(parcel, DataHolder.CREATOR), parcel.createStringArray());
                            break;
                        case 5030:
                            zze((DataHolder) zzex.zza(parcel, DataHolder.CREATOR), parcel.createStringArray());
                            break;
                        case 5031:
                            zzf((DataHolder) zzex.zza(parcel, DataHolder.CREATOR), parcel.createStringArray());
                            break;
                        case 5032:
                            onRealTimeMessageReceived((RealTimeMessage) zzex.zza(parcel, RealTimeMessage.CREATOR));
                            break;
                        case 5033:
                            zzb(parcel.readInt(), parcel.readInt(), parcel.readString());
                            break;
                        case 5034:
                            zza(parcel.readInt(), parcel.readString(), zzex.zza(parcel));
                            break;
                        case 5035:
                            zzaf((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 5036:
                            zzdi(parcel.readInt());
                            break;
                        case 5037:
                            zzq((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 5038:
                            zzae((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 5039:
                            zzag((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 5040:
                            zzdj(parcel.readInt());
                            break;
                        case 9001:
                            zzo((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 11001:
                            zzd(parcel.readInt(), (Bundle) zzex.zza(parcel, Bundle.CREATOR));
                            break;
                        case SafetyNetStatusCodes.SAFE_BROWSING_MISSING_API_KEY /*12001*/:
                            zzal((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 14001:
                            zza((DataHolder[]) parcel.createTypedArray(DataHolder.CREATOR));
                            break;
                        case 15001:
                            zzau((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 19006:
                            zzaw((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 19007:
                            zzg(parcel.readInt(), (Bundle) zzex.zza(parcel, Bundle.CREATOR));
                            break;
                        case 19008:
                            zzdn(parcel.readInt());
                            break;
                        case 19009:
                            zzdo(parcel.readInt());
                            break;
                        case 19010:
                            zzdp(parcel.readInt());
                            break;
                        case 20001:
                            zzax((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 20002:
                            zzay((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 20003:
                            zzaz((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 20004:
                            zzba((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 20005:
                            zzbb((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 20006:
                            zzbc((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 20007:
                            zzbd((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 20008:
                            zzbe((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 20009:
                            zzbf((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                            break;
                        case 20010:
                            zzb(parcel.readInt(), parcel.readString(), parcel.readString());
                            break;
                        case 20011:
                            zzk(parcel.readInt(), parcel.readString());
                            break;
                        case 20012:
                            zzaj((Status) zzex.zza(parcel, Status.CREATOR));
                            break;
                        case 20013:
                            zzak((Status) zzex.zza(parcel, Status.CREATOR));
                            break;
                        case 20014:
                            zzal((Status) zzex.zza(parcel, Status.CREATOR));
                            break;
                        case 20015:
                            zzam((Status) zzex.zza(parcel, Status.CREATOR));
                            break;
                        case 20016:
                            zzdq(parcel.readInt());
                            break;
                        case 20017:
                            zza(parcel.readInt(), (Uri) zzex.zza(parcel, Uri.CREATOR));
                            break;
                        case 20018:
                            zzdr(parcel.readInt());
                            break;
                        case 20019:
                            onCaptureOverlayStateChanged(parcel.readInt());
                            break;
                        case 20020:
                            zzh(parcel.readInt(), (Bundle) zzex.zza(parcel, Bundle.CREATOR));
                            break;
                        default:
                            switch (i) {
                                case 8001:
                                    zzah((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                                    break;
                                case 8002:
                                    zzb(parcel.readInt(), (Bundle) zzex.zza(parcel, Bundle.CREATOR));
                                    break;
                                case 8003:
                                    zzs((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                                    break;
                                case ConnectionsStatusCodes.STATUS_CONNECTION_REJECTED /*8004*/:
                                    zzt((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                                    break;
                                case ConnectionsStatusCodes.STATUS_NOT_CONNECTED_TO_ENDPOINT /*8005*/:
                                    zzu((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                                    break;
                                case 8006:
                                    zzv((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                                    break;
                                case ConnectionsStatusCodes.STATUS_BLUETOOTH_ERROR /*8007*/:
                                    zzi(parcel.readInt(), parcel.readString());
                                    break;
                                case ConnectionsStatusCodes.STATUS_ALREADY_HAVE_ACTIVE_STRATEGY /*8008*/:
                                    zzw((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                                    break;
                                case ConnectionsStatusCodes.STATUS_OUT_OF_ORDER_API_CALL /*8009*/:
                                    onTurnBasedMatchRemoved(parcel.readString());
                                    break;
                                case 8010:
                                    onInvitationRemoved(parcel.readString());
                                    break;
                                default:
                                    switch (i) {
                                        case 10001:
                                            zzr((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                                            break;
                                        case GamesActivityResultCodes.RESULT_SIGN_IN_FAILED:
                                            onRequestRemoved(parcel.readString());
                                            break;
                                        case GamesActivityResultCodes.RESULT_LICENSE_FAILED:
                                            zzai((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                                            break;
                                        case GamesActivityResultCodes.RESULT_APP_MISCONFIGURED:
                                            zzaj((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                                            break;
                                        case GamesActivityResultCodes.RESULT_LEFT_ROOM:
                                            zzc(parcel.readInt(), (Bundle) zzex.zza(parcel, Bundle.CREATOR));
                                            break;
                                        case GamesActivityResultCodes.RESULT_NETWORK_FAILURE:
                                            zzak((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                                            break;
                                        default:
                                            switch (i) {
                                                case SafetyNetStatusCodes.VERIFY_APPS_NOT_AVAILABLE /*12003*/:
                                                    zze(parcel.readInt(), (Bundle) zzex.zza(parcel, Bundle.CREATOR));
                                                    break;
                                                case SafetyNetStatusCodes.VERIFY_APPS_INTERNAL_ERROR /*12004*/:
                                                    zza((DataHolder) zzex.zza(parcel, DataHolder.CREATOR), (zzc) zzex.zza(parcel, zzc.CREATOR));
                                                    break;
                                                case SafetyNetStatusCodes.VERIFY_APPS_NOT_ENABLED /*12005*/:
                                                    zzam((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                                                    break;
                                                case SafetyNetStatusCodes.UNSUPPORTED_SDK_VERSION /*12006*/:
                                                    zzan((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                                                    break;
                                                case SafetyNetStatusCodes.RECAPTCHA_INVALID_SITEKEY /*12007*/:
                                                    zzao((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                                                    break;
                                                case SafetyNetStatusCodes.RECAPTCHA_INVALID_KEYTYPE /*12008*/:
                                                    zzar((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                                                    break;
                                                default:
                                                    switch (i) {
                                                        case SafetyNetStatusCodes.INVALID_ADMIN_APPLICATION /*12011*/:
                                                            zzg((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                                                            break;
                                                        case SafetyNetStatusCodes.SINGLE_USER_SERVICE_NOT_AVAILABLE /*12012*/:
                                                            zzj(parcel.readInt(), parcel.readString());
                                                            break;
                                                        case SafetyNetStatusCodes.RECAPTCHA_INVALID_PACKAGE_NAME /*12013*/:
                                                            zzas((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                                                            break;
                                                        case SafetyNetStatusCodes.SAFE_BROWSING_BLACKLIST_PARSE_ERROR /*12014*/:
                                                            zzap((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                                                            break;
                                                        case SafetyNetStatusCodes.PACKAGE_WARNING_IN_PROGRESS /*12015*/:
                                                            zzf(parcel.readInt(), (Bundle) zzex.zza(parcel, Bundle.CREATOR));
                                                            break;
                                                        case 12016:
                                                            zzaq((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
                                                            break;
                                                        case SafetyNetStatusCodes.REMOVE_HARMFULAPP_ACTION_NOT_RESOLVED /*12017*/:
                                                            zza((DataHolder) zzex.zza(parcel, DataHolder.CREATOR), parcel.readString(), (zzc) zzex.zza(parcel, zzc.CREATOR), (zzc) zzex.zza(parcel, zzc.CREATOR), (zzc) zzex.zza(parcel, zzc.CREATOR));
                                                            break;
                                                        default:
                                                            switch (i) {
                                                                case 19001:
                                                                    zza(parcel.readInt(), (VideoCapabilities) zzex.zza(parcel, VideoCapabilities.CREATOR));
                                                                    break;
                                                                case 19002:
                                                                    zzi(parcel.readInt(), zzex.zza(parcel));
                                                                    break;
                                                                case 19003:
                                                                    zza(parcel.readInt(), zzex.zza(parcel), zzex.zza(parcel));
                                                                    break;
                                                                case 19004:
                                                                    zzdm(parcel.readInt());
                                                                    break;
                                                                default:
                                                                    return false;
                                                            }
                                                    }
                                            }
                                    }
                            }
                    }
            }
        } else {
            zzdl(parcel.readInt());
        }
        parcel2.writeNoException();
        return true;
    }
}
