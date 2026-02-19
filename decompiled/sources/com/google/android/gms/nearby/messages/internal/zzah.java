package com.google.android.gms.nearby.messages.internal;

import android.app.Activity;
import android.app.Application;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzcvb;
import com.google.android.gms.internal.zzcvd;
import com.google.android.gms.internal.zzcvg;
import com.google.android.gms.internal.zzcvi;
import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.nearby.messages.MessagesOptions;
import com.google.android.gms.nearby.messages.PublishOptions;
import com.google.android.gms.nearby.messages.StatusCallback;
import com.google.android.gms.nearby.messages.SubscribeOptions;

public final class zzah extends zzab<zzs> {
    private final int zzkaz;
    private final zzcvi<zzck, IBinder> zzkdb = new zzcvi<>();
    private final ClientAppContext zzkdc;

    zzah(Context context, Looper looper, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, zzr zzr, MessagesOptions messagesOptions) {
        super(context, looper, 62, zzr, connectionCallbacks, onConnectionFailedListener);
        int i;
        String zzami = zzr.zzami();
        int zzee = zzee(context);
        int i2 = zzee;
        if (messagesOptions != null) {
            ClientAppContext clientAppContext = new ClientAppContext(zzami, messagesOptions.zzkav, messagesOptions.zzkaw, messagesOptions.zzkay, i2);
            this.zzkdc = clientAppContext;
            i = messagesOptions.zzkax;
        } else {
            ClientAppContext clientAppContext = new ClientAppContext(zzami, (String) null, false, (String) null, i2);
            this.zzkdc = clientAppContext;
            i = -1;
        }
        this.zzkaz = i;
        if (zzee == 1) {
            Activity activity = (Activity) context;
            if (Log.isLoggable("NearbyMessagesClient", 2)) {
                Log.v("NearbyMessagesClient", String.format("Registering ClientLifecycleSafetyNet's ActivityLifecycleCallbacks for %s", new Object[]{activity.getPackageName()}));
            }
            activity.getApplication().registerActivityLifecycleCallbacks(new zzaj(activity, this));
        }
    }

    static int zzee(Context context) {
        if (context instanceof Activity) {
            return 1;
        }
        if (context instanceof Application) {
            return 2;
        }
        return context instanceof Service ? 3 : 0;
    }

    public final void disconnect() {
        try {
            zzet(2);
        } catch (RemoteException e) {
            if (Log.isLoggable("NearbyMessagesClient", 2)) {
                Log.v("NearbyMessagesClient", String.format("Failed to emit CLIENT_DISCONNECTED from override of GmsClient#disconnect(): %s", new Object[]{e}));
            }
        }
        this.zzkdb.clear();
        super.disconnect();
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzci<zzn<Status>> zzci, PendingIntent pendingIntent) throws RemoteException {
        ((zzs) zzalw()).zza(new zzcg((IBinder) null, new zzcvd(zzci), pendingIntent));
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public final void zza(zzci<zzn<Status>> zzci, PendingIntent pendingIntent, com.google.android.gms.nearby.messages.internal.zzab zzab, SubscribeOptions subscribeOptions) throws RemoteException {
        zza(zzci, pendingIntent, zzab, subscribeOptions, this.zzkdc.zzkcv);
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzci<zzn<Status>> zzci, PendingIntent pendingIntent, com.google.android.gms.nearby.messages.internal.zzab zzab, SubscribeOptions subscribeOptions, int i) throws RemoteException {
        SubscribeOptions subscribeOptions2 = subscribeOptions;
        zzci<zzn<Status>> zzci2 = zzci;
        ((zzs) zzalw()).zza(new SubscribeRequest((IBinder) null, subscribeOptions.getStrategy(), new zzcvd(zzci), subscribeOptions.getFilter(), pendingIntent, (byte[]) null, zzab, subscribeOptions2.zzkbr, subscribeOptions2.zzkbs, this.zzkdc.zzkcv));
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzci<zzn<Status>> zzci, zzci<MessageListener> zzci2) throws RemoteException {
        zzcvd zzcvd = new zzcvd(zzci);
        if (!this.zzkdb.containsKey(zzci2.zzakx())) {
            zzcvd.zzap(new Status(0));
            return;
        }
        ((zzs) zzalw()).zza(new zzcg(this.zzkdb.get(zzci2.zzakx()), zzcvd, (PendingIntent) null));
        this.zzkdb.remove(zzci2.zzakx());
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public final void zza(zzci<zzn<Status>> zzci, zzci<MessageListener> zzci2, com.google.android.gms.nearby.messages.internal.zzab zzab, SubscribeOptions subscribeOptions, byte[] bArr) throws RemoteException {
        zza(zzci, zzci2, zzab, subscribeOptions, (byte[]) null, this.zzkdc.zzkcv);
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzci<zzn<Status>> zzci, zzci<MessageListener> zzci2, com.google.android.gms.nearby.messages.internal.zzab zzab, SubscribeOptions subscribeOptions, byte[] bArr, int i) throws RemoteException {
        if (!this.zzkdb.containsKey(zzci2.zzakx())) {
            this.zzkdb.zzf(zzci2.zzakx(), new zzcvb(zzci2));
        } else {
            zzci<MessageListener> zzci3 = zzci2;
        }
        zzci<zzn<Status>> zzci4 = zzci;
        ((zzs) zzalw()).zza(new SubscribeRequest(this.zzkdb.get(zzci2.zzakx()), subscribeOptions.getStrategy(), new zzcvd(zzci), subscribeOptions.getFilter(), (PendingIntent) null, (byte[]) null, zzab, subscribeOptions.zzkbr, i));
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzci<zzn<Status>> zzci, zzaf zzaf) throws RemoteException {
        ((zzs) zzalw()).zza(new zzce(zzaf, new zzcvd(zzci)));
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public final void zza(zzci<zzn<Status>> zzci, zzaf zzaf, zzv zzv, PublishOptions publishOptions) throws RemoteException {
        zza(zzci, zzaf, zzv, publishOptions, this.zzkdc.zzkcv);
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzci<zzn<Status>> zzci, zzaf zzaf, zzv zzv, PublishOptions publishOptions, int i) throws RemoteException {
        ((zzs) zzalw()).zza(new zzbz(zzaf, publishOptions.getStrategy(), new zzcvd(zzci), zzv, i));
    }

    /* access modifiers changed from: protected */
    public final Bundle zzabt() {
        Bundle zzabt = super.zzabt();
        zzabt.putInt("NearbyPermissions", this.zzkaz);
        zzabt.putParcelable("ClientAppContext", this.zzkdc);
        return zzabt;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzci<zzn<Status>> zzci, zzci<StatusCallback> zzci2) throws RemoteException {
        if (!this.zzkdb.containsKey(zzci2.zzakx())) {
            this.zzkdb.zzf(zzci2.zzakx(), new zzcvg(zzci2));
        }
        zzcb zzcb = new zzcb(new zzcvd(zzci), this.zzkdb.get(zzci2.zzakx()));
        zzcb.zzkeh = true;
        ((zzs) zzalw()).zza(zzcb);
    }

    /* access modifiers changed from: package-private */
    public final void zzc(zzci<zzn<Status>> zzci, zzci<StatusCallback> zzci2) throws RemoteException {
        zzcvd zzcvd = new zzcvd(zzci);
        if (!this.zzkdb.containsKey(zzci2.zzakx())) {
            zzcvd.zzap(new Status(0));
            return;
        }
        zzcb zzcb = new zzcb(zzcvd, this.zzkdb.get(zzci2.zzakx()));
        zzcb.zzkeh = false;
        ((zzs) zzalw()).zza(zzcb);
        this.zzkdb.remove(zzci2.zzakx());
    }

    /* access modifiers changed from: protected */
    public final zzs zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.nearby.messages.internal.INearbyMessagesService");
        return queryLocalInterface instanceof zzs ? (zzs) queryLocalInterface : new zzt(iBinder);
    }

    /* access modifiers changed from: package-private */
    public final void zzet(int i) throws RemoteException {
        String str;
        if (i == 1) {
            str = "ACTIVITY_STOPPED";
        } else if (i == 2) {
            str = "CLIENT_DISCONNECTED";
        } else if (Log.isLoggable("NearbyMessagesClient", 5)) {
            Log.w("NearbyMessagesClient", String.format("Received unknown/unforeseen client lifecycle event %d, can't do anything with it.", new Object[]{Integer.valueOf(i)}));
            return;
        } else {
            return;
        }
        if (isConnected()) {
            zzj zzj = new zzj(i);
            if (Log.isLoggable("NearbyMessagesClient", 3)) {
                Log.d("NearbyMessagesClient", String.format("Emitting client lifecycle event %s", new Object[]{str}));
            }
            ((zzs) zzalw()).zza(zzj);
        } else if (Log.isLoggable("NearbyMessagesClient", 3)) {
            Log.d("NearbyMessagesClient", String.format("Failed to emit client lifecycle event %s due to GmsClient being disconnected", new Object[]{str}));
        }
    }

    /* access modifiers changed from: protected */
    public final String zzhm() {
        return "com.google.android.gms.nearby.messages.service.NearbyMessagesService.START";
    }

    /* access modifiers changed from: protected */
    public final String zzhn() {
        return "com.google.android.gms.nearby.messages.internal.INearbyMessagesService";
    }
}
