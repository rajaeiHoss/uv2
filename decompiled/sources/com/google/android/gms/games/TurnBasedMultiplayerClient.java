package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzcm;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.api.zzp;
import com.google.android.gms.games.internal.zzg;
import com.google.android.gms.games.internal.zzn;
import com.google.android.gms.games.internal.zzo;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchUpdateCallback;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.tasks.Task;
import java.util.List;

public class TurnBasedMultiplayerClient extends zzp {
    private static final zzn<TurnBasedMatch> zzhvo = new zzcv();
    private static final zzbo<TurnBasedMultiplayer.LoadMatchesResult, LoadMatchesResponse> zzhvp = new zzce();
    private static final zzo<TurnBasedMultiplayer.LoadMatchesResult> zzhvq = new zzcf();
    private static final zzbo<TurnBasedMultiplayer.LoadMatchResult, TurnBasedMatch> zzhvr = new zzcg();
    private static final zzbo<TurnBasedMultiplayer.CancelMatchResult, String> zzhvs = new zzch();
    private static final com.google.android.gms.games.internal.zzp zzhvt = new com.google.android.gms.games.zzci();
    private static final zzbo<TurnBasedMultiplayer.LeaveMatchResult, Void> zzhvu = new zzcj();
    private static final zzbo<TurnBasedMultiplayer.LeaveMatchResult, TurnBasedMatch> zzhvv = new com.google.android.gms.games.zzck();
    private static final com.google.android.gms.games.internal.zzp zzhvw = new zzcl();
    private static final zzbo<TurnBasedMultiplayer.UpdateMatchResult, TurnBasedMatch> zzhvx = new com.google.android.gms.games.zzcm();
    private static final zzbo<TurnBasedMultiplayer.InitiateMatchResult, TurnBasedMatch> zzhvy = new zzcn();

    public static class MatchOutOfDateApiException extends ApiException {
        protected final TurnBasedMatch match;

        MatchOutOfDateApiException(Status status, TurnBasedMatch turnBasedMatch) {
            super(status);
            this.match = turnBasedMatch;
        }

        public TurnBasedMatch getMatch() {
            return this.match;
        }
    }

    TurnBasedMultiplayerClient(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    TurnBasedMultiplayerClient(Context context, Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    private static Task<Void> zzf(PendingResult<TurnBasedMultiplayer.LeaveMatchResult> pendingResult) {
        return zzg.zza(pendingResult, zzhvt, zzhvu, zzhvv, zzhvo);
    }

    private static Task<TurnBasedMatch> zzg(PendingResult<TurnBasedMultiplayer.UpdateMatchResult> pendingResult) {
        com.google.android.gms.games.internal.zzp zzp = zzhvw;
        zzbo<TurnBasedMultiplayer.UpdateMatchResult, TurnBasedMatch> zzbo = zzhvx;
        return zzg.zza(pendingResult, zzp, zzbo, zzbo, zzhvo);
    }

    public Task<TurnBasedMatch> acceptInvitation(String str) {
        return zzg.zza(Games.TurnBasedMultiplayer.acceptInvitation(zzahw(), str), zzhvy);
    }

    public Task<String> cancelMatch(String str) {
        return zzg.zza(Games.TurnBasedMultiplayer.cancelMatch(zzahw(), str), zzhvs);
    }

    public Task<TurnBasedMatch> createMatch(TurnBasedMatchConfig turnBasedMatchConfig) {
        return zzg.zza(Games.TurnBasedMultiplayer.createMatch(zzahw(), turnBasedMatchConfig), zzhvy);
    }

    public Task<Void> declineInvitation(String str) {
        return zzb(new zzcr(this, str));
    }

    public Task<Void> dismissInvitation(String str) {
        return zzb(new zzcs(this, str));
    }

    public Task<Void> dismissMatch(String str) {
        return zzb(new zzcu(this, str));
    }

    public Task<TurnBasedMatch> finishMatch(String str) {
        return zzg(Games.TurnBasedMultiplayer.finishMatch(zzahw(), str));
    }

    public Task<TurnBasedMatch> finishMatch(String str, byte[] bArr, List<ParticipantResult> list) {
        return zzg(Games.TurnBasedMultiplayer.finishMatch(zzahw(), str, bArr, list));
    }

    public Task<TurnBasedMatch> finishMatch(String str, byte[] bArr, ParticipantResult... participantResultArr) {
        return zzg(Games.TurnBasedMultiplayer.finishMatch(zzahw(), str, bArr, participantResultArr));
    }

    public Task<Intent> getInboxIntent() {
        return zza(new zzcd(this));
    }

    public Task<Integer> getMaxMatchDataSize() {
        return zza(new zzct(this));
    }

    public Task<Intent> getSelectOpponentsIntent(int i, int i2) {
        return getSelectOpponentsIntent(i, i2, true);
    }

    public Task<Intent> getSelectOpponentsIntent(int i, int i2, boolean z) {
        return zza(new zzcq(this, i, i2, z));
    }

    public Task<Void> leaveMatch(String str) {
        return zzf(Games.TurnBasedMultiplayer.leaveMatch(zzahw(), str));
    }

    public Task<Void> leaveMatchDuringTurn(String str, String str2) {
        return zzf(Games.TurnBasedMultiplayer.leaveMatchDuringTurn(zzahw(), str, str2));
    }

    public Task<AnnotatedData<TurnBasedMatch>> loadMatch(String str) {
        return zzg.zzb(Games.TurnBasedMultiplayer.loadMatch(zzahw(), str), zzhvr);
    }

    public Task<AnnotatedData<LoadMatchesResponse>> loadMatchesByStatus(int i, int[] iArr) {
        return zzg.zza(Games.TurnBasedMultiplayer.loadMatchesByStatus(zzahw(), i, iArr), zzhvp, zzhvq);
    }

    public Task<AnnotatedData<LoadMatchesResponse>> loadMatchesByStatus(int[] iArr) {
        return zzg.zza(Games.TurnBasedMultiplayer.loadMatchesByStatus(zzahw(), iArr), zzhvp, zzhvq);
    }

    public Task<Void> registerTurnBasedMatchUpdateCallback(TurnBasedMatchUpdateCallback turnBasedMatchUpdateCallback) {
        zzci zza = zza(turnBasedMatchUpdateCallback, TurnBasedMatchUpdateCallback.class.getSimpleName());
        return zza(new zzco(this, zza, zza), new zzcp(this, zza.zzakx()));
    }

    public Task<TurnBasedMatch> rematch(String str) {
        return zzg.zza(Games.TurnBasedMultiplayer.rematch(zzahw(), str), zzhvy);
    }

    public Task<TurnBasedMatch> takeTurn(String str, byte[] bArr, String str2) {
        return zzg(Games.TurnBasedMultiplayer.takeTurn(zzahw(), str, bArr, str2));
    }

    public Task<TurnBasedMatch> takeTurn(String str, byte[] bArr, String str2, List<ParticipantResult> list) {
        return zzg(Games.TurnBasedMultiplayer.takeTurn(zzahw(), str, bArr, str2, list));
    }

    public Task<TurnBasedMatch> takeTurn(String str, byte[] bArr, String str2, ParticipantResult... participantResultArr) {
        return zzg(Games.TurnBasedMultiplayer.takeTurn(zzahw(), str, bArr, str2, participantResultArr));
    }

    public Task<Boolean> unregisterTurnBasedMatchUpdateCallback(TurnBasedMatchUpdateCallback turnBasedMatchUpdateCallback) {
        return zza((zzck<?>) zzcm.zzb(turnBasedMatchUpdateCallback, TurnBasedMatchUpdateCallback.class.getSimpleName()));
    }
}
