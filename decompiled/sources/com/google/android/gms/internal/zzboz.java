package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.DriveResourceClient;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.events.ListenerToken;
import com.google.android.gms.drive.events.OnChangeListener;
import com.google.android.gms.drive.events.OpenFileCallback;
import com.google.android.gms.drive.events.zzj;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.zzr;
import com.google.android.gms.drive.zzt;
import com.google.android.gms.tasks.Task;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzboz extends DriveResourceClient {
    private static final AtomicInteger zzgvf = new AtomicInteger();
    private final DriveApi zzguh = new zzbmu();

    public zzboz(Activity activity, Drive.zza zza) {
        super(activity, zza);
    }

    public zzboz(Context context, Drive.zza zza) {
        super(context, zza);
    }

    static final /* synthetic */ ListenerToken zza(zzci zzci, Task task) throws Exception {
        if (task.isSuccessful()) {
            return new zzblv(zzci.zzakx());
        }
        throw task.getException();
    }

    static final /* synthetic */ ListenerToken zza(zzblv zzblv, Task task) throws Exception {
        if (task.isSuccessful()) {
            return zzblv;
        }
        throw task.getException();
    }

    private static void zzcu(int i) {
        if (i != 268435456 && i != 536870912 && i != 805306368) {
            throw new IllegalArgumentException("Invalid openMode provided");
        }
    }

    public final Task<ListenerToken> addChangeListener(DriveResource driveResource, OnChangeListener onChangeListener) {
        zzbq.checkNotNull(driveResource.getDriveId());
        zzbq.checkNotNull(onChangeListener, "listener");
        zzbqe zzbqe = new zzbqe(this, onChangeListener, driveResource.getDriveId());
        int incrementAndGet = zzgvf.incrementAndGet();
        StringBuilder sb = new StringBuilder(27);
        sb.append("OnChangeListener");
        sb.append(incrementAndGet);
        zzci zza = zza(zzbqe, sb.toString());
        return zza(new zzbpi(this, zza, driveResource, zzbqe), new zzbpj(this, zza.zzakx(), driveResource, zzbqe)).continueWith(new zzbpb(zza));
    }

    public final Task<Void> addChangeSubscription(DriveResource driveResource) {
        zzbq.checkNotNull(driveResource.getDriveId());
        zzbq.checkArgument(zzj.zza(1, driveResource.getDriveId()));
        return zzb(new zzbpk(this, driveResource));
    }

    public final Task<Boolean> cancelOpenFileCallback(ListenerToken listenerToken) {
        if (listenerToken instanceof zzblv) {
            return zza((zzck<?>) ((zzblv) listenerToken).zzaqi());
        }
        throw new IllegalArgumentException("Unrecognized ListenerToken");
    }

    public final Task<Void> commitContents(DriveContents driveContents, MetadataChangeSet metadataChangeSet) {
        return commitContents(driveContents, metadataChangeSet, (zzr) new zzt().build());
    }

    public final Task<Void> commitContents(DriveContents driveContents, MetadataChangeSet metadataChangeSet, ExecutionOptions executionOptions) {
        zzbq.checkNotNull(executionOptions, "Execution options cannot be null.");
        boolean z = true;
        zzbq.checkArgument(!driveContents.zzapn(), "DriveContents is already closed");
        if (driveContents.getMode() == 268435456) {
            z = false;
        }
        zzbq.checkArgument(z, "Cannot commit contents opened in MODE_READ_ONLY.");
        zzbq.checkNotNull(driveContents.getDriveId(), "Only DriveContents obtained through DriveFile.open can be committed.");
        zzr zzb = zzr.zzb(executionOptions);
        if (!ExecutionOptions.zzcq(zzb.zzapq()) || driveContents.zzapl().zzapd()) {
            if (metadataChangeSet == null) {
                metadataChangeSet = MetadataChangeSet.zzgqo;
            }
            return zzb(new zzbps(this, zzb, driveContents, metadataChangeSet));
        }
        throw new IllegalStateException("DriveContents must be valid for conflict detection.");
    }

    public final Task<DriveContents> createContents() {
        return zzb(new zzbpp(this));
    }

    public final Task<DriveFile> createFile(DriveFolder driveFolder, MetadataChangeSet metadataChangeSet, DriveContents driveContents) {
        return zzb(new zzbpu(this, metadataChangeSet, driveContents, driveFolder));
    }

    public final Task<DriveFile> createFile(DriveFolder driveFolder, MetadataChangeSet metadataChangeSet, DriveContents driveContents, ExecutionOptions executionOptions) {
        zzbq.checkNotNull(executionOptions, "executionOptions cannot be null");
        return zzb(new zzbpv(this, metadataChangeSet, driveContents, driveFolder, executionOptions));
    }

    public final Task<DriveFolder> createFolder(DriveFolder driveFolder, MetadataChangeSet metadataChangeSet) {
        zzbq.checkNotNull(metadataChangeSet, "MetadataChangeSet must be provided.");
        if (metadataChangeSet.getMimeType() == null || metadataChangeSet.getMimeType().equals(DriveFolder.MIME_TYPE)) {
            return zzb(new zzbpw(this, metadataChangeSet, driveFolder));
        }
        throw new IllegalArgumentException("The mimetype must be of type application/vnd.google-apps.folder");
    }

    public final Task<Void> delete(DriveResource driveResource) {
        zzbq.checkNotNull(driveResource.getDriveId());
        return zzb(new zzbqb(this, driveResource));
    }

    public final Task<Void> discardContents(DriveContents driveContents) {
        zzbq.checkArgument(!driveContents.zzapn(), "DriveContents is already closed");
        driveContents.zzapm();
        return zzb(new zzbpt(this, driveContents));
    }

    public final Task<DriveFolder> getAppFolder() {
        return zza(new zzbpq(this));
    }

    public final Task<Metadata> getMetadata(DriveResource driveResource) {
        zzbq.checkNotNull(driveResource.getDriveId());
        return zza(new zzbpx(this, driveResource));
    }

    public final Task<DriveFolder> getRootFolder() {
        return zza(new zzbpf(this));
    }

    public final Task<MetadataBuffer> listChildren(DriveFolder driveFolder) {
        return zzbj.zza(this.zzguh.query(zzahw(), zzbok.zza((Query) null, driveFolder.getDriveId())), zzbpd.zzgui);
    }

    public final Task<MetadataBuffer> listParents(DriveResource driveResource) {
        zzbq.checkNotNull(driveResource.getDriveId());
        return zza(new zzbpz(this, driveResource));
    }

    public final Task<DriveContents> openFile(DriveFile driveFile, int i) {
        zzcu(i);
        return zza(new zzbpm(this, driveFile, i));
    }

    public final Task<ListenerToken> openFile(DriveFile driveFile, int i, OpenFileCallback openFileCallback) {
        zzcu(i);
        int incrementAndGet = zzgvf.incrementAndGet();
        StringBuilder sb = new StringBuilder(27);
        sb.append("OpenFileCallback");
        sb.append(incrementAndGet);
        zzci zza = zza(openFileCallback, sb.toString());
        zzck zzakx = zza.zzakx();
        zzblv zzblv = new zzblv(zzakx);
        return zza(new zzbpn(this, zza, driveFile, i, zzblv, zza), new zzbpo(this, zzakx, zzblv)).continueWith(new zzbpc(zzblv));
    }

    public final Task<MetadataBuffer> query(Query query) {
        return zzbj.zza(this.zzguh.query(zzahw(), query), zzbpa.zzgui);
    }

    public final Task<MetadataBuffer> queryChildren(DriveFolder driveFolder, Query query) {
        return zzbj.zza(this.zzguh.query(zzahw(), zzbok.zza(query, driveFolder.getDriveId())), zzbpe.zzgui);
    }

    public final Task<Boolean> removeChangeListener(ListenerToken listenerToken) {
        zzbq.checkNotNull(listenerToken, "Token is required to unregister listener.");
        if (listenerToken instanceof zzblv) {
            return zza((zzck<?>) ((zzblv) listenerToken).zzaqi());
        }
        throw new IllegalStateException("Could not recover key from ListenerToken");
    }

    public final Task<Void> removeChangeSubscription(DriveResource driveResource) {
        zzbq.checkNotNull(driveResource.getDriveId());
        zzbq.checkArgument(zzj.zza(1, driveResource.getDriveId()));
        return zzb(new zzbpl(this, driveResource));
    }

    public final Task<DriveContents> reopenContentsForWrite(DriveContents driveContents) {
        boolean z = true;
        zzbq.checkArgument(!driveContents.zzapn(), "DriveContents is already closed");
        if (driveContents.getMode() != 268435456) {
            z = false;
        }
        zzbq.checkArgument(z, "This method can only be called on contents that are currently opened in MODE_READ_ONLY.");
        driveContents.zzapm();
        return zza(new zzbpr(this, driveContents));
    }

    public final Task<Void> setParents(DriveResource driveResource, Set<DriveId> set) {
        zzbq.checkNotNull(driveResource.getDriveId());
        zzbq.checkNotNull(set);
        return zzb(new zzbqa(this, driveResource, new ArrayList(set)));
    }

    public final Task<Void> trash(DriveResource driveResource) {
        zzbq.checkNotNull(driveResource.getDriveId());
        return zzb(new zzbpg(this, driveResource));
    }

    public final Task<Void> untrash(DriveResource driveResource) {
        zzbq.checkNotNull(driveResource.getDriveId());
        return zzb(new zzbph(this, driveResource));
    }

    public final Task<Metadata> updateMetadata(DriveResource driveResource, MetadataChangeSet metadataChangeSet) {
        zzbq.checkNotNull(driveResource.getDriveId());
        zzbq.checkNotNull(metadataChangeSet);
        return zzb(new zzbpy(this, metadataChangeSet, driveResource));
    }
}
