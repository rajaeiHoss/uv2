package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzcm;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.api.zzp;
import com.google.android.gms.games.internal.zzg;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.games.multiplayer.InvitationCallback;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.tasks.Task;

public class InvitationsClient extends zzp {
    private static final zzbo<Invitations.LoadInvitationsResult, InvitationBuffer> zzhso = new zzaa();

    InvitationsClient(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    InvitationsClient(Context context, Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public Task<Intent> getInvitationInboxIntent() {
        return zza(new zzx(this));
    }

    public Task<AnnotatedData<InvitationBuffer>> loadInvitations() {
        return loadInvitations(0);
    }

    public Task<AnnotatedData<InvitationBuffer>> loadInvitations(int i) {
        return zzg.zzc(Games.Invitations.loadInvitations(zzahw(), i), zzhso);
    }

    public Task<Void> registerInvitationCallback(InvitationCallback invitationCallback) {
        zzci zza = zza(invitationCallback, InvitationCallback.class.getSimpleName());
        return zza(new zzy(this, zza, zza), new zzz(this, zza.zzakx()));
    }

    public Task<Boolean> unregisterInvitationCallback(InvitationCallback invitationCallback) {
        return zza((zzck<?>) zzcm.zzb(invitationCallback, InvitationCallback.class.getSimpleName()));
    }
}
