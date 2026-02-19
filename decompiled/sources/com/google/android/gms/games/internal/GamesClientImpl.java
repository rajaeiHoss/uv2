package com.google.android.gms.games.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.BinderWrapper;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameBuffer;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesClientStatusCodes;
import com.google.android.gms.games.GamesMetadata;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.Players;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardEntity;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardScoreEntity;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchBuffer;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.games.quest.Milestone;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.games.quest.QuestBuffer;
import com.google.android.gms.games.quest.QuestEntity;
import com.google.android.gms.games.quest.QuestUpdateListener;
import com.google.android.gms.games.quest.Quests;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import com.google.android.gms.games.request.Requests;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.games.snapshot.SnapshotEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;
import com.google.android.gms.games.snapshot.Snapshots;
import com.google.android.gms.games.stats.PlayerStats;
import com.google.android.gms.games.stats.PlayerStatsBuffer;
import com.google.android.gms.games.stats.Stats;
import com.google.android.gms.games.video.CaptureState;
import com.google.android.gms.games.video.VideoCapabilities;
import com.google.android.gms.games.video.Videos;
import com.google.android.gms.internal.zzcct;
import com.google.android.gms.internal.zzccv;
import com.google.android.gms.internal.zzccw;
import com.google.android.gms.internal.zzcyt;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GamesClientImpl extends com.google.android.gms.common.internal.zzab<zzw> {
    private zzccv zzhwr = new com.google.android.gms.games.internal.zzd(this);
    private final String zzhws;
    private PlayerEntity zzhwt;
    private GameEntity zzhwu;
    private final com.google.android.gms.games.internal.zzaa zzhwv;
    private boolean zzhww = false;
    private final Binder zzhwx;
    private final long zzhwy;
    private final Games.GamesOptions zzhwz;
    private boolean zzhxa = false;
    private Bundle zzhxb;

    static final class CaptureStreamingUrlResultImpl implements Videos.CaptureStreamingUrlResult {
        private final String zzag = null;

        public final Status getStatus() {
            throw new NoSuchMethodError();
        }

        public final String getUrl() {
            return this.zzag;
        }
    }

    static class zzaRoom extends zzc {
        private final ArrayList<String> zzhxe = new ArrayList<>();

        zzaRoom() {
            super((DataHolder) null);
        }

        zzaRoom(DataHolder dataHolder, String[] strArr) {
            super(dataHolder);
            for (String add : strArr) {
                this.zzhxe.add(add);
            }
        }

        /* access modifiers changed from: protected */
        public final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room) {
            zza(roomStatusUpdateListener, room, this.zzhxe);
        }

        /* access modifiers changed from: protected */
        public void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
        }
    }

    static final class zzaa extends zzcr implements TurnBasedMultiplayer.InitiateMatchResult {
        zzaa(DataHolder dataHolder) {
            super(dataHolder);
        }
    }

    static final class zzab extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzci<OnInvitationReceivedListener> zzgut;

        zzab(com.google.android.gms.common.api.internal.zzci<OnInvitationReceivedListener> zzci) {
            this.zzgut = zzci;
        }

        public final void onInvitationRemoved(String str) {
            this.zzgut.zza(new zzad(str));
        }

        public final void zzq(DataHolder dataHolder) {
            InvitationBuffer invitationBuffer = new InvitationBuffer(dataHolder);
            try {
                Invitation invitation = invitationBuffer.getCount() > 0 ? (Invitation) ((Invitation) invitationBuffer.get(0)).freeze() : null;
                if (invitation != null) {
                    this.zzgut.zza(new zzac(invitation));
                }
            } finally {
                invitationBuffer.release();
            }
        }
    }

    static final class zzac implements com.google.android.gms.common.api.internal.zzcl<OnInvitationReceivedListener> {
        private final Invitation zzhxp;

        zzac(Invitation invitation) {
            this.zzhxp = invitation;
        }

        public final void zzajh() {
        }

        public final void zzu(OnInvitationReceivedListener onInvitationReceivedListener) {
            onInvitationReceivedListener.onInvitationReceived(this.zzhxp);
        }
    }

    static final class zzad implements com.google.android.gms.common.api.internal.zzcl<OnInvitationReceivedListener> {
        private final String zzeha;

        zzad(String str) {
            this.zzeha = str;
        }

        public final void zzajh() {
        }

        public final void zzu(OnInvitationReceivedListener onInvitationReceivedListener) {
            onInvitationReceivedListener.onInvitationRemoved(this.zzeha);
        }
    }

    static final class zzae extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<Invitations.LoadInvitationsResult> zzgbf;

        zzae(com.google.android.gms.common.api.internal.zzn<Invitations.LoadInvitationsResult> zzn) {
            this.zzgbf = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zzp(DataHolder dataHolder) {
            this.zzgbf.setResult(new zzao(dataHolder));
        }
    }

    static final class zzaf extends zzb {
        public zzaf(DataHolder dataHolder) {
            super(dataHolder);
        }

        public final void zza(RoomUpdateListener roomUpdateListener, Room room, int i) {
            roomUpdateListener.onJoinedRoom(i, room);
        }
    }

    static final class zzag extends zzwResult implements Leaderboards.LeaderboardMetadataResult {
        private final LeaderboardBuffer zzhxq;

        zzag(DataHolder dataHolder) {
            super(dataHolder);
            this.zzhxq = new LeaderboardBuffer(dataHolder);
        }

        public final LeaderboardBuffer getLeaderboards() {
            return this.zzhxq;
        }
    }

    static final class zzah extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<Leaderboards.LoadScoresResult> zzgbf;

        zzah(com.google.android.gms.common.api.internal.zzn<Leaderboards.LoadScoresResult> zzn) {
            this.zzgbf = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zza(DataHolder dataHolder, DataHolder dataHolder2) {
            this.zzgbf.setResult(new zzaw(dataHolder, dataHolder2));
        }
    }

    static final class zzai extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<Leaderboards.LeaderboardMetadataResult> zzgbf;

        zzai(com.google.android.gms.common.api.internal.zzn<Leaderboards.LeaderboardMetadataResult> zzn) {
            this.zzgbf = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zzh(DataHolder dataHolder) {
            this.zzgbf.setResult(new zzag(dataHolder));
        }
    }

    static final class zzaj extends zzcr implements TurnBasedMultiplayer.LeaveMatchResult {
        zzaj(DataHolder dataHolder) {
            super(dataHolder);
        }
    }

    static final class zzak implements com.google.android.gms.common.api.internal.zzcl<RoomUpdateListener> {
        private final int zzcc;
        private final String zzhxr;

        zzak(int i, String str) {
            this.zzcc = i;
            this.zzhxr = str;
        }

        public final void zzajh() {
        }

        public final void zzu(RoomUpdateListener roomUpdateListener) {
            roomUpdateListener.onLeftRoom(this.zzcc, this.zzhxr);
        }
    }

    static final class zzal extends zzwResult implements Achievements.LoadAchievementsResult {
        private final AchievementBuffer zzhxs;

        zzal(DataHolder dataHolder) {
            super(dataHolder);
            this.zzhxs = new AchievementBuffer(dataHolder);
        }

        public final AchievementBuffer getAchievements() {
            return this.zzhxs;
        }
    }

    static final class zzam extends zzwResult implements Events.LoadEventsResult {
        private final EventBuffer zzhxt;

        zzam(DataHolder dataHolder) {
            super(dataHolder);
            this.zzhxt = new EventBuffer(dataHolder);
        }

        public final EventBuffer getEvents() {
            return this.zzhxt;
        }
    }

    static final class zzan extends zzwResult implements GamesMetadata.LoadGamesResult {
        private final GameBuffer zzhxu;

        zzan(DataHolder dataHolder) {
            super(dataHolder);
            this.zzhxu = new GameBuffer(dataHolder);
        }

        public final GameBuffer getGames() {
            return this.zzhxu;
        }
    }

    static final class zzao extends zzwResult implements Invitations.LoadInvitationsResult {
        private final InvitationBuffer zzhxv;

        zzao(DataHolder dataHolder) {
            super(dataHolder);
            this.zzhxv = new InvitationBuffer(dataHolder);
        }

        public final InvitationBuffer getInvitations() {
            return this.zzhxv;
        }
    }

    static final class zzap extends zzcr implements TurnBasedMultiplayer.LoadMatchResult {
        zzap(DataHolder dataHolder) {
            super(dataHolder);
        }
    }

    static final class zzaq implements TurnBasedMultiplayer.LoadMatchesResult {
        private final Status mStatus;
        private final LoadMatchesResponse zzhxw;

        zzaq(Status status, Bundle bundle) {
            this.mStatus = status;
            this.zzhxw = new LoadMatchesResponse(bundle);
        }

        public final LoadMatchesResponse getMatches() {
            return this.zzhxw;
        }

        public final Status getStatus() {
            return this.mStatus;
        }

        public final void release() {
            this.zzhxw.release();
        }
    }

    static final class zzar extends zzwResult implements Leaderboards.LoadPlayerScoreResult {
        private final LeaderboardScoreEntity zzhxx;

        zzar(DataHolder dataHolder) {
            super(dataHolder);
            LeaderboardScoreBuffer leaderboardScoreBuffer = new LeaderboardScoreBuffer(dataHolder);
            try {
                if (leaderboardScoreBuffer.getCount() > 0) {
                    this.zzhxx = (LeaderboardScoreEntity) ((LeaderboardScore) leaderboardScoreBuffer.get(0)).freeze();
                } else {
                    this.zzhxx = null;
                }
            } finally {
                leaderboardScoreBuffer.release();
            }
        }

        public final LeaderboardScore getScore() {
            return this.zzhxx;
        }
    }

    static final class zzas extends zzwResult implements Stats.LoadPlayerStatsResult {
        private final PlayerStats zzhxy;

        zzas(DataHolder dataHolder) {
            super(dataHolder);
            PlayerStatsBuffer playerStatsBuffer = new PlayerStatsBuffer(dataHolder);
            try {
                if (playerStatsBuffer.getCount() > 0) {
                    this.zzhxy = new com.google.android.gms.games.stats.zza((PlayerStats) playerStatsBuffer.get(0));
                } else {
                    this.zzhxy = null;
                }
            } finally {
                playerStatsBuffer.release();
            }
        }

        public final PlayerStats getPlayerStats() {
            return this.zzhxy;
        }
    }

    static final class zzat extends zzwResult implements Players.LoadPlayersResult {
        private final PlayerBuffer zzhxz;

        zzat(DataHolder dataHolder) {
            super(dataHolder);
            this.zzhxz = new PlayerBuffer(dataHolder);
        }

        public final PlayerBuffer getPlayers() {
            return this.zzhxz;
        }
    }

    static final class zzau extends zzwResult implements Quests.LoadQuestsResult {
        private final DataHolder zzfxb;

        zzau(DataHolder dataHolder) {
            super(dataHolder);
            this.zzfxb = dataHolder;
        }

        public final QuestBuffer getQuests() {
            return new QuestBuffer(this.zzfxb);
        }
    }

    static final class zzav implements Requests.LoadRequestsResult {
        private final Status mStatus;
        private final Bundle zzhya;

        zzav(Status status, Bundle bundle) {
            this.mStatus = status;
            this.zzhya = bundle;
        }

        public final GameRequestBuffer getRequests(int i) {
            String str;
            if (i == 1) {
                str = "GIFT";
            } else if (i != 2) {
                StringBuilder sb = new StringBuilder(33);
                sb.append("Unknown request type: ");
                sb.append(i);
                com.google.android.gms.games.internal.zzf.zzw("RequestType", sb.toString());
                str = "UNKNOWN_TYPE";
            } else {
                str = "WISH";
            }
            if (!this.zzhya.containsKey(str)) {
                return null;
            }
            return new GameRequestBuffer((DataHolder) this.zzhya.get(str));
        }

        public final Status getStatus() {
            return this.mStatus;
        }

        public final void release() {
            for (String parcelable : this.zzhya.keySet()) {
                DataHolder dataHolder = (DataHolder) this.zzhya.getParcelable(parcelable);
                if (dataHolder != null) {
                    dataHolder.close();
                }
            }
        }
    }

    static final class zzaw extends zzwResult implements Leaderboards.LoadScoresResult {
        private final LeaderboardEntity zzhyb;
        private final LeaderboardScoreBuffer zzhyc;

        /* JADX INFO: finally extract failed */
        zzaw(DataHolder dataHolder, DataHolder dataHolder2) {
            super(dataHolder2);
            LeaderboardBuffer leaderboardBuffer = new LeaderboardBuffer(dataHolder);
            try {
                if (leaderboardBuffer.getCount() > 0) {
                    this.zzhyb = (LeaderboardEntity) ((Leaderboard) leaderboardBuffer.get(0)).freeze();
                } else {
                    this.zzhyb = null;
                }
                leaderboardBuffer.release();
                this.zzhyc = new LeaderboardScoreBuffer(dataHolder2);
            } catch (Throwable th) {
                leaderboardBuffer.release();
                throw th;
            }
        }

        public final Leaderboard getLeaderboard() {
            return this.zzhyb;
        }

        public final LeaderboardScoreBuffer getScores() {
            return this.zzhyc;
        }
    }

    static final class zzax extends zzwResult implements Snapshots.LoadSnapshotsResult {
        zzax(DataHolder dataHolder) {
            super(dataHolder);
        }

        public final SnapshotMetadataBuffer getSnapshots() {
            return new SnapshotMetadataBuffer(this.zzfxb);
        }
    }

    static final class zzay implements com.google.android.gms.common.api.internal.zzcl<OnTurnBasedMatchUpdateReceivedListener> {
        private final String zzhyd;

        zzay(String str) {
            this.zzhyd = str;
        }

        public final void zzajh() {
        }

        public final void zzu(OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener) {
            onTurnBasedMatchUpdateReceivedListener.onTurnBasedMatchRemoved(this.zzhyd);
        }
    }

    static final class zzaz extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzci<OnTurnBasedMatchUpdateReceivedListener> zzgut;

        zzaz(com.google.android.gms.common.api.internal.zzci<OnTurnBasedMatchUpdateReceivedListener> zzci) {
            this.zzgut = zzci;
        }

        public final void onTurnBasedMatchRemoved(String str) {
            this.zzgut.zza(new zzay(str));
        }

        public final void zzw(DataHolder dataHolder) {
            TurnBasedMatchBuffer turnBasedMatchBuffer = new TurnBasedMatchBuffer(dataHolder);
            try {
                TurnBasedMatch turnBasedMatch = turnBasedMatchBuffer.getCount() > 0 ? (TurnBasedMatch) ((TurnBasedMatch) turnBasedMatchBuffer.get(0)).freeze() : null;
                if (turnBasedMatch != null) {
                    this.zzgut.zza(new zzba(turnBasedMatch));
                }
            } finally {
                turnBasedMatchBuffer.release();
            }
        }
    }

    static abstract class zzb extends com.google.android.gms.common.api.internal.zzai<RoomUpdateListener> {
        zzb() {
            super((DataHolder) null);
        }

        zzb(DataHolder dataHolder) {
            super(dataHolder);
        }

        /* access modifiers changed from: protected */
        public abstract void zza(RoomUpdateListener roomUpdateListener, Room room, int i);

        /* access modifiers changed from: protected */
        public final void zza(RoomUpdateListener roomUpdateListener, DataHolder dataHolder) {
            zza(roomUpdateListener, GamesClientImpl.zzbg(dataHolder), dataHolder.getStatusCode());
        }
    }

    static final class zzba implements com.google.android.gms.common.api.internal.zzcl<OnTurnBasedMatchUpdateReceivedListener> {
        private final TurnBasedMatch zzhye;

        zzba(TurnBasedMatch turnBasedMatch) {
            this.zzhye = turnBasedMatch;
        }

        public final void zzajh() {
        }

        public final void zzu(OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener) {
            onTurnBasedMatchUpdateReceivedListener.onTurnBasedMatchReceived(this.zzhye);
        }
    }

    static final class zzbb implements com.google.android.gms.common.api.internal.zzcl<RealTimeMessageReceivedListener> {
        private final RealTimeMessage zzhyf;

        zzbb(RealTimeMessage realTimeMessage) {
            this.zzhyf = realTimeMessage;
        }

        public final void zzajh() {
        }

        public final void zzu(RealTimeMessageReceivedListener realTimeMessageReceivedListener) {
            realTimeMessageReceivedListener.onRealTimeMessageReceived(this.zzhyf);
        }
    }

    static final class zzbc extends zzwResult implements Snapshots.OpenSnapshotResult {
        private final Snapshot zzhyg;
        private final String zzhyh;
        private final Snapshot zzhyi;
        private final com.google.android.gms.drive.zzc zzhyj;
        private final SnapshotContents zzhyk;

        zzbc(DataHolder dataHolder, com.google.android.gms.drive.zzc zzc) {
            this(dataHolder, (String) null, zzc, (com.google.android.gms.drive.zzc) null, (com.google.android.gms.drive.zzc) null);
        }

        /* JADX INFO: finally extract failed */
        zzbc(DataHolder dataHolder, String str, com.google.android.gms.drive.zzc zzc, com.google.android.gms.drive.zzc zzc2, com.google.android.gms.drive.zzc zzc3) {
            super(dataHolder);
            SnapshotMetadataBuffer snapshotMetadataBuffer = new SnapshotMetadataBuffer(dataHolder);
            Snapshot snapshot = null;
            Snapshot conflictingSnapshot = null;
            String conflictId = null;
            com.google.android.gms.drive.zzc resolutionContents = null;
            SnapshotContents resolutionSnapshotContents = null;
            try {
                if (snapshotMetadataBuffer.getCount() == 0) {
                    snapshot = null;
                } else {
                    boolean z = true;
                    if (snapshotMetadataBuffer.getCount() == 1) {
                        if (dataHolder.getStatusCode() == 4004) {
                            z = false;
                        }
                        com.google.android.gms.common.internal.zzc.checkState(z);
                        snapshot = new SnapshotEntity(new SnapshotMetadataEntity((SnapshotMetadata) snapshotMetadataBuffer.get(0)), new com.google.android.gms.games.snapshot.zza(zzc));
                    } else {
                        snapshot = new SnapshotEntity(new SnapshotMetadataEntity((SnapshotMetadata) snapshotMetadataBuffer.get(0)), new com.google.android.gms.games.snapshot.zza(zzc));
                        conflictingSnapshot = new SnapshotEntity(new SnapshotMetadataEntity((SnapshotMetadata) snapshotMetadataBuffer.get(1)), new com.google.android.gms.games.snapshot.zza(zzc2));
                        conflictId = str;
                        resolutionContents = zzc3;
                        if (zzc3 != null) {
                            resolutionSnapshotContents = new com.google.android.gms.games.snapshot.zza(zzc3);
                        }
                    }
                }
            } catch (Throwable th) {
                snapshotMetadataBuffer.release();
                throw th;
            }
            snapshotMetadataBuffer.release();
            this.zzhyg = snapshot;
            this.zzhyi = conflictingSnapshot;
            this.zzhyh = conflictId;
            this.zzhyj = resolutionContents;
            this.zzhyk = resolutionSnapshotContents;
        }

        public final String getConflictId() {
            return this.zzhyh;
        }

        public final Snapshot getConflictingSnapshot() {
            return this.zzhyi;
        }

        public final SnapshotContents getResolutionSnapshotContents() {
            return this.zzhyk;
        }

        public final Snapshot getSnapshot() {
            return this.zzhyg;
        }
    }

    static final class zzbd implements com.google.android.gms.common.api.internal.zzcl<RoomStatusUpdateListener> {
        private final String zzhyl;

        zzbd(String str) {
            this.zzhyl = str;
        }

        public final void zzajh() {
        }

        public final void zzu(RoomStatusUpdateListener roomStatusUpdateListener) {
            roomStatusUpdateListener.onP2PConnected(this.zzhyl);
        }
    }

    static final class zzbe implements com.google.android.gms.common.api.internal.zzcl<RoomStatusUpdateListener> {
        private final String zzhyl;

        zzbe(String str) {
            this.zzhyl = str;
        }

        public final void zzajh() {
        }

        public final void zzu(RoomStatusUpdateListener roomStatusUpdateListener) {
            roomStatusUpdateListener.onP2PDisconnected(this.zzhyl);
        }
    }

    static final class zzbf extends zzaRoom {
        zzbf(DataHolder dataHolder, String[] strArr) {
            super(dataHolder, strArr);
        }

        /* access modifiers changed from: protected */
        public final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeersConnected(room, arrayList);
        }
    }

    static final class zzbg extends zzaRoom {
        zzbg(DataHolder dataHolder, String[] strArr) {
            super(dataHolder, strArr);
        }

        /* access modifiers changed from: protected */
        public final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeerDeclined(room, arrayList);
        }
    }

    static final class zzbh extends zzaRoom {
        zzbh(DataHolder dataHolder, String[] strArr) {
            super(dataHolder, strArr);
        }

        /* access modifiers changed from: protected */
        public final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeersDisconnected(room, arrayList);
        }
    }

    static final class zzbi extends zzaRoom {
        zzbi(DataHolder dataHolder, String[] strArr) {
            super(dataHolder, strArr);
        }

        /* access modifiers changed from: protected */
        public final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeerInvitedToRoom(room, arrayList);
        }
    }

    static final class zzbj extends zzaRoom {
        zzbj(DataHolder dataHolder, String[] strArr) {
            super(dataHolder, strArr);
        }

        /* access modifiers changed from: protected */
        public final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeerJoined(room, arrayList);
        }
    }

    static final class zzbk extends zzaRoom {
        zzbk(DataHolder dataHolder, String[] strArr) {
            super(dataHolder, strArr);
        }

        /* access modifiers changed from: protected */
        public final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeerLeft(room, arrayList);
        }
    }

    static final class zzbl extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<Leaderboards.LoadPlayerScoreResult> zzgbf;

        zzbl(com.google.android.gms.common.api.internal.zzn<Leaderboards.LoadPlayerScoreResult> zzn) {
            this.zzgbf = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zzah(DataHolder dataHolder) {
            this.zzgbf.setResult(new zzar(dataHolder));
        }
    }

    static final class zzbm extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<Stats.LoadPlayerStatsResult> zzgbf;

        public zzbm(com.google.android.gms.common.api.internal.zzn<Stats.LoadPlayerStatsResult> zzn) {
            this.zzgbf = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zzau(DataHolder dataHolder) {
            this.zzgbf.setResult(new zzas(dataHolder));
        }
    }

    static final class zzbn extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<Players.LoadPlayersResult> zzgbf;

        zzbn(com.google.android.gms.common.api.internal.zzn<Players.LoadPlayersResult> zzn) {
            this.zzgbf = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zzj(DataHolder dataHolder) {
            this.zzgbf.setResult(new zzat(dataHolder));
        }

        public final void zzk(DataHolder dataHolder) {
            this.zzgbf.setResult(new zzat(dataHolder));
        }
    }

    static final class zzbo extends com.google.android.gms.games.internal.zzb {
        private final com.google.android.gms.games.internal.zzaa zzhwv;

        public zzbo(com.google.android.gms.games.internal.zzaa zzaa) {
            this.zzhwv = zzaa;
        }

        public final com.google.android.gms.games.internal.zzy zzatc() {
            return new com.google.android.gms.games.internal.zzy(this.zzhwv.zziad);
        }
    }

    static final class zzbp extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<Quests.AcceptQuestResult> zzhym;

        public zzbp(com.google.android.gms.common.api.internal.zzn<Quests.AcceptQuestResult> zzn) {
            this.zzhym = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zzao(DataHolder dataHolder) {
            this.zzhym.setResult(new zzd(dataHolder));
        }
    }

    static final class zzbq implements com.google.android.gms.common.api.internal.zzcl<QuestUpdateListener> {
        private final Quest zzhxf;

        zzbq(Quest quest) {
            this.zzhxf = quest;
        }

        public final void zzajh() {
        }

        public final void zzu(QuestUpdateListener questUpdateListener) {
            questUpdateListener.onQuestCompleted(this.zzhxf);
        }
    }

    static final class zzbr extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<Quests.ClaimMilestoneResult> zzhyn;
        private final String zzhyo;

        public zzbr(com.google.android.gms.common.api.internal.zzn<Quests.ClaimMilestoneResult> zzn, String str) {
            this.zzhyn = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
            this.zzhyo = (String) com.google.android.gms.common.internal.zzbq.checkNotNull(str, "MilestoneId must not be null");
        }

        public final void zzan(DataHolder dataHolder) {
            this.zzhyn.setResult(new zzp(dataHolder, this.zzhyo));
        }
    }

    static final class zzbs extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzci<QuestUpdateListener> zzgut;

        zzbs(com.google.android.gms.common.api.internal.zzci<QuestUpdateListener> zzci) {
            this.zzgut = zzci;
        }

        private static Quest zzbi(DataHolder dataHolder) {
            QuestBuffer questBuffer = new QuestBuffer(dataHolder);
            try {
                return questBuffer.getCount() > 0 ? (Quest) ((Quest) questBuffer.get(0)).freeze() : null;
            } finally {
                questBuffer.release();
            }
        }

        public final void zzap(DataHolder dataHolder) {
            Quest zzbi = zzbi(dataHolder);
            if (zzbi != null) {
                this.zzgut.zza(new zzbq(zzbi));
            }
        }
    }

    static final class zzbt extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<Quests.LoadQuestsResult> zzhyp;

        public zzbt(com.google.android.gms.common.api.internal.zzn<Quests.LoadQuestsResult> zzn) {
            this.zzhyp = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zzar(DataHolder dataHolder) {
            this.zzhyp.setResult(new zzau(dataHolder));
        }
    }

    static final class zzbu implements com.google.android.gms.common.api.internal.zzcl<RealTimeMultiplayer.ReliableMessageSentCallback> {
        private final int zzcc;
        private final String zzhyq;
        private final int zzhyr;

        zzbu(int i, int i2, String str) {
            this.zzcc = i;
            this.zzhyr = i2;
            this.zzhyq = str;
        }

        public final void zzajh() {
        }

        public final void zzu(RealTimeMultiplayer.ReliableMessageSentCallback reliableMessageSentCallback) {
            if (reliableMessageSentCallback != null) {
                reliableMessageSentCallback.onRealTimeMessageSent(this.zzcc, this.zzhyr, this.zzhyq);
            }
        }
    }

    static final class zzbv extends com.google.android.gms.games.internal.zza {
        private com.google.android.gms.common.api.internal.zzci<RealTimeMultiplayer.ReliableMessageSentCallback> zzhys;

        public zzbv(com.google.android.gms.common.api.internal.zzci<RealTimeMultiplayer.ReliableMessageSentCallback> zzci) {
            this.zzhys = zzci;
        }

        public final void zzb(int i, int i2, String str) {
            com.google.android.gms.common.api.internal.zzci<RealTimeMultiplayer.ReliableMessageSentCallback> zzci = this.zzhys;
            if (zzci != null) {
                zzci.zza(new zzbu(i, i2, str));
            }
        }
    }

    static final class zzbw extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzci<OnRequestReceivedListener> zzgut;

        zzbw(com.google.android.gms.common.api.internal.zzci<OnRequestReceivedListener> zzci) {
            this.zzgut = zzci;
        }

        public final void onRequestRemoved(String str) {
            this.zzgut.zza(new zzby(str));
        }

        public final void zzr(DataHolder dataHolder) {
            GameRequestBuffer gameRequestBuffer = new GameRequestBuffer(dataHolder);
            try {
                GameRequest gameRequest = gameRequestBuffer.getCount() > 0 ? (GameRequest) ((GameRequest) gameRequestBuffer.get(0)).freeze() : null;
                if (gameRequest != null) {
                    this.zzgut.zza(new zzbx(gameRequest));
                }
            } finally {
                gameRequestBuffer.release();
            }
        }
    }

    static final class zzbx implements com.google.android.gms.common.api.internal.zzcl<OnRequestReceivedListener> {
        private final GameRequest zzhyt;

        zzbx(GameRequest gameRequest) {
            this.zzhyt = gameRequest;
        }

        public final void zzajh() {
        }

        public final void zzu(OnRequestReceivedListener onRequestReceivedListener) {
            onRequestReceivedListener.onRequestReceived(this.zzhyt);
        }
    }

    static final class zzby implements com.google.android.gms.common.api.internal.zzcl<OnRequestReceivedListener> {
        private final String zzcwj;

        zzby(String str) {
            this.zzcwj = str;
        }

        public final void zzajh() {
        }

        public final void zzu(OnRequestReceivedListener onRequestReceivedListener) {
            onRequestReceivedListener.onRequestRemoved(this.zzcwj);
        }
    }

    static final class zzbz extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<Requests.LoadRequestsResult> zzhyu;

        public zzbz(com.google.android.gms.common.api.internal.zzn<Requests.LoadRequestsResult> zzn) {
            this.zzhyu = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zzc(int i, Bundle bundle) {
            bundle.setClassLoader(getClass().getClassLoader());
            this.zzhyu.setResult(new zzav(GamesStatusCodes.zzdg(i), bundle));
        }
    }

    static abstract class zzc extends com.google.android.gms.common.api.internal.zzai<RoomStatusUpdateListener> {
        zzc(DataHolder dataHolder) {
            super(dataHolder);
        }

        /* access modifiers changed from: protected */
        public abstract void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room);

        /* access modifiers changed from: protected */
        public final void zza(RoomStatusUpdateListener roomStatusUpdateListener, DataHolder dataHolder) {
            zza(roomStatusUpdateListener, GamesClientImpl.zzbg(dataHolder));
        }
    }

    static final class zzca extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<Requests.UpdateRequestsResult> zzhyv;

        public zzca(com.google.android.gms.common.api.internal.zzn<Requests.UpdateRequestsResult> zzn) {
            this.zzhyv = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zzai(DataHolder dataHolder) {
            this.zzhyv.setResult(new zzcw(dataHolder));
        }
    }

    static final class zzcb extends zzc {
        zzcb(DataHolder dataHolder) {
            super(dataHolder);
        }

        public final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room) {
            roomStatusUpdateListener.onRoomAutoMatching(room);
        }
    }

    static final class zzcc extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzci<? extends RoomUpdateListener> zzhyw;
        private final com.google.android.gms.common.api.internal.zzci<? extends RoomStatusUpdateListener> zzhyx;
        private final com.google.android.gms.common.api.internal.zzci<? extends RealTimeMessageReceivedListener> zzhyy;

        public zzcc(com.google.android.gms.common.api.internal.zzci<? extends RoomUpdateListener> zzci) {
            this.zzhyw = (com.google.android.gms.common.api.internal.zzci) com.google.android.gms.common.internal.zzbq.checkNotNull(zzci, "Callbacks must not be null");
            this.zzhyx = null;
            this.zzhyy = null;
        }

        public zzcc(com.google.android.gms.common.api.internal.zzci<? extends RoomUpdateListener> zzci, com.google.android.gms.common.api.internal.zzci<? extends RoomStatusUpdateListener> zzci2, com.google.android.gms.common.api.internal.zzci<? extends RealTimeMessageReceivedListener> zzci3) {
            this.zzhyw = (com.google.android.gms.common.api.internal.zzci) com.google.android.gms.common.internal.zzbq.checkNotNull(zzci, "Callbacks must not be null");
            this.zzhyx = zzci2;
            this.zzhyy = zzci3;
        }

        public final void onLeftRoom(int i, String str) {
            this.zzhyw.zza(new zzak(i, str));
        }

        public final void onP2PConnected(String str) {
            com.google.android.gms.common.api.internal.zzci<? extends RoomStatusUpdateListener> zzci = this.zzhyx;
            if (zzci != null) {
                zzci.zza(new zzbd(str));
            }
        }

        public final void onP2PDisconnected(String str) {
            com.google.android.gms.common.api.internal.zzci<? extends RoomStatusUpdateListener> zzci = this.zzhyx;
            if (zzci != null) {
                zzci.zza(new zzbe(str));
            }
        }

        public final void onRealTimeMessageReceived(RealTimeMessage realTimeMessage) {
            com.google.android.gms.common.api.internal.zzci<? extends RealTimeMessageReceivedListener> zzci = this.zzhyy;
            if (zzci != null) {
                zzci.zza(new zzbb(realTimeMessage));
            }
        }

        public final void zza(DataHolder dataHolder, String[] strArr) {
            com.google.android.gms.common.api.internal.zzci<? extends RoomStatusUpdateListener> zzci = this.zzhyx;
            if (zzci != null) {
                zzci.zza(new zzbi(dataHolder, strArr));
            }
        }

        public final void zzaa(DataHolder dataHolder) {
            com.google.android.gms.common.api.internal.zzci<? extends RoomStatusUpdateListener> zzci = this.zzhyx;
            if (zzci != null) {
                zzci.zza(new zzcb(dataHolder));
            }
        }

        public final void zzab(DataHolder dataHolder) {
            this.zzhyw.zza(new zzcd(dataHolder));
        }

        public final void zzac(DataHolder dataHolder) {
            com.google.android.gms.common.api.internal.zzci<? extends RoomStatusUpdateListener> zzci = this.zzhyx;
            if (zzci != null) {
                zzci.zza(new zzr(dataHolder));
            }
        }

        public final void zzad(DataHolder dataHolder) {
            com.google.android.gms.common.api.internal.zzci<? extends RoomStatusUpdateListener> zzci = this.zzhyx;
            if (zzci != null) {
                zzci.zza(new zzt(dataHolder));
            }
        }

        public final void zzb(DataHolder dataHolder, String[] strArr) {
            com.google.android.gms.common.api.internal.zzci<? extends RoomStatusUpdateListener> zzci = this.zzhyx;
            if (zzci != null) {
                zzci.zza(new zzbj(dataHolder, strArr));
            }
        }

        public final void zzc(DataHolder dataHolder, String[] strArr) {
            com.google.android.gms.common.api.internal.zzci<? extends RoomStatusUpdateListener> zzci = this.zzhyx;
            if (zzci != null) {
                zzci.zza(new zzbk(dataHolder, strArr));
            }
        }

        public final void zzd(DataHolder dataHolder, String[] strArr) {
            com.google.android.gms.common.api.internal.zzci<? extends RoomStatusUpdateListener> zzci = this.zzhyx;
            if (zzci != null) {
                zzci.zza(new zzbg(dataHolder, strArr));
            }
        }

        public final void zze(DataHolder dataHolder, String[] strArr) {
            com.google.android.gms.common.api.internal.zzci<? extends RoomStatusUpdateListener> zzci = this.zzhyx;
            if (zzci != null) {
                zzci.zza(new zzbf(dataHolder, strArr));
            }
        }

        public final void zzf(DataHolder dataHolder, String[] strArr) {
            com.google.android.gms.common.api.internal.zzci<? extends RoomStatusUpdateListener> zzci = this.zzhyx;
            if (zzci != null) {
                zzci.zza(new zzbh(dataHolder, strArr));
            }
        }

        public final void zzx(DataHolder dataHolder) {
            this.zzhyw.zza(new zzcf(dataHolder));
        }

        public final void zzy(DataHolder dataHolder) {
            this.zzhyw.zza(new zzaf(dataHolder));
        }

        public final void zzz(DataHolder dataHolder) {
            com.google.android.gms.common.api.internal.zzci<? extends RoomStatusUpdateListener> zzci = this.zzhyx;
            if (zzci != null) {
                zzci.zza(new zzce(dataHolder));
            }
        }
    }

    static final class zzcd extends zzb {
        zzcd(DataHolder dataHolder) {
            super(dataHolder);
        }

        public final void zza(RoomUpdateListener roomUpdateListener, Room room, int i) {
            roomUpdateListener.onRoomConnected(i, room);
        }
    }

    static final class zzce extends zzc {
        zzce(DataHolder dataHolder) {
            super(dataHolder);
        }

        public final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room) {
            roomStatusUpdateListener.onRoomConnecting(room);
        }
    }

    static final class zzcf extends zzb {
        public zzcf(DataHolder dataHolder) {
            super(dataHolder);
        }

        public final void zza(RoomUpdateListener roomUpdateListener, Room room, int i) {
            roomUpdateListener.onRoomCreated(i, room);
        }
    }

    static final class zzcg extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<Status> zzgbf;

        public zzcg(com.google.android.gms.common.api.internal.zzn<Status> zzn) {
            this.zzgbf = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zzako() {
            this.zzgbf.setResult(GamesStatusCodes.zzdg(0));
        }
    }

    static final class zzch extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<Snapshots.CommitSnapshotResult> zzhyz;

        public zzch(com.google.android.gms.common.api.internal.zzn<Snapshots.CommitSnapshotResult> zzn) {
            this.zzhyz = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zzam(DataHolder dataHolder) {
            this.zzhyz.setResult(new zzq(dataHolder));
        }
    }

    static final class zzci extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<Snapshots.DeleteSnapshotResult> zzgbf;

        public zzci(com.google.android.gms.common.api.internal.zzn<Snapshots.DeleteSnapshotResult> zzn) {
            this.zzgbf = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zzj(int i, String str) {
            this.zzgbf.setResult(new zzsResult(i, str));
        }
    }

    static final class zzcj extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<Snapshots.OpenSnapshotResult> zzhza;

        public zzcj(com.google.android.gms.common.api.internal.zzn<Snapshots.OpenSnapshotResult> zzn) {
            this.zzhza = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zza(DataHolder dataHolder, com.google.android.gms.drive.zzc zzc) {
            this.zzhza.setResult(new zzbc(dataHolder, zzc));
        }

        public final void zza(DataHolder dataHolder, String str, com.google.android.gms.drive.zzc zzc, com.google.android.gms.drive.zzc zzc2, com.google.android.gms.drive.zzc zzc3) {
            this.zzhza.setResult(new zzbc(dataHolder, str, zzc, zzc2, zzc3));
        }
    }

    static final class zzck extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<Snapshots.LoadSnapshotsResult> zzhzb;

        public zzck(com.google.android.gms.common.api.internal.zzn<Snapshots.LoadSnapshotsResult> zzn) {
            this.zzhzb = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zzal(DataHolder dataHolder) {
            this.zzhzb.setResult(new zzax(dataHolder));
        }
    }

    static final class zzcl extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<Leaderboards.SubmitScoreResult> zzgbf;

        public zzcl(com.google.android.gms.common.api.internal.zzn<Leaderboards.SubmitScoreResult> zzn) {
            this.zzgbf = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zzi(DataHolder dataHolder) {
            this.zzgbf.setResult(new zzcm(dataHolder));
        }
    }

    static final class zzcm extends zzwResult implements Leaderboards.SubmitScoreResult {
        private final ScoreSubmissionData zzhzc;

        public zzcm(DataHolder dataHolder) {
            super(dataHolder);
            try {
                this.zzhzc = new ScoreSubmissionData(dataHolder);
            } finally {
                dataHolder.close();
            }
        }

        public final ScoreSubmissionData getScoreData() {
            return this.zzhzc;
        }
    }

    static final class zzcn extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<TurnBasedMultiplayer.CancelMatchResult> zzhzd;

        public zzcn(com.google.android.gms.common.api.internal.zzn<TurnBasedMultiplayer.CancelMatchResult> zzn) {
            this.zzhzd = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zzi(int i, String str) {
            this.zzhzd.setResult(new zzg(GamesStatusCodes.zzdg(i), str));
        }
    }

    static final class zzco extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<TurnBasedMultiplayer.InitiateMatchResult> zzhze;

        public zzco(com.google.android.gms.common.api.internal.zzn<TurnBasedMultiplayer.InitiateMatchResult> zzn) {
            this.zzhze = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zzt(DataHolder dataHolder) {
            this.zzhze.setResult(new zzaa(dataHolder));
        }
    }

    static final class zzcp extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<TurnBasedMultiplayer.LeaveMatchResult> zzhzf;

        public zzcp(com.google.android.gms.common.api.internal.zzn<TurnBasedMultiplayer.LeaveMatchResult> zzn) {
            this.zzhzf = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zzv(DataHolder dataHolder) {
            this.zzhzf.setResult(new zzaj(dataHolder));
        }
    }

    static final class zzcq extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<TurnBasedMultiplayer.LoadMatchResult> zzhzg;

        public zzcq(com.google.android.gms.common.api.internal.zzn<TurnBasedMultiplayer.LoadMatchResult> zzn) {
            this.zzhzg = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zzs(DataHolder dataHolder) {
            this.zzhzg.setResult(new zzap(dataHolder));
        }
    }

    static abstract class zzcr extends zzwResult {
        private TurnBasedMatch zzhye;

        zzcr(DataHolder dataHolder) {
            super(dataHolder);
            TurnBasedMatchBuffer turnBasedMatchBuffer = new TurnBasedMatchBuffer(dataHolder);
            try {
                if (turnBasedMatchBuffer.getCount() > 0) {
                    this.zzhye = (TurnBasedMatch) ((TurnBasedMatch) turnBasedMatchBuffer.get(0)).freeze();
                } else {
                    this.zzhye = null;
                }
            } finally {
                turnBasedMatchBuffer.release();
            }
        }

        public TurnBasedMatch getMatch() {
            return this.zzhye;
        }
    }

    static final class zzcs extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<TurnBasedMultiplayer.UpdateMatchResult> zzhzh;

        public zzcs(com.google.android.gms.common.api.internal.zzn<TurnBasedMultiplayer.UpdateMatchResult> zzn) {
            this.zzhzh = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zzu(DataHolder dataHolder) {
            this.zzhzh.setResult(new zzcv(dataHolder));
        }
    }

    static final class zzct extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<TurnBasedMultiplayer.LoadMatchesResult> zzhzi;

        public zzct(com.google.android.gms.common.api.internal.zzn<TurnBasedMultiplayer.LoadMatchesResult> zzn) {
            this.zzhzi = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zzb(int i, Bundle bundle) {
            bundle.setClassLoader(getClass().getClassLoader());
            this.zzhzi.setResult(new zzaq(GamesStatusCodes.zzdg(i), bundle));
        }
    }

    static final class zzcu implements Achievements.UpdateAchievementResult {
        private final Status mStatus;
        private final String zzhwd;

        zzcu(int i, String str) {
            this.mStatus = GamesStatusCodes.zzdg(i);
            this.zzhwd = str;
        }

        public final String getAchievementId() {
            return this.zzhwd;
        }

        public final Status getStatus() {
            return this.mStatus;
        }
    }

    static final class zzcv extends zzcr implements TurnBasedMultiplayer.UpdateMatchResult {
        zzcv(DataHolder dataHolder) {
            super(dataHolder);
        }
    }

    static final class zzcw extends zzwResult implements Requests.UpdateRequestsResult {
        private final zzccw zzhzj;

        zzcw(DataHolder dataHolder) {
            super(dataHolder);
            this.zzhzj = zzccw.zzbj(dataHolder);
        }

        public final Set<String> getRequestIds() {
            return this.zzhzj.getRequestIds();
        }

        public final int getRequestOutcome(String str) {
            return this.zzhzj.getRequestOutcome(str);
        }
    }

    static final class zzd extends zzwResult implements Quests.AcceptQuestResult {
        private final Quest zzhxf;

        zzd(DataHolder dataHolder) {
            super(dataHolder);
            QuestBuffer questBuffer = new QuestBuffer(dataHolder);
            try {
                if (questBuffer.getCount() > 0) {
                    this.zzhxf = new QuestEntity((Quest) questBuffer.get(0));
                } else {
                    this.zzhxf = null;
                }
            } finally {
                questBuffer.release();
            }
        }

        public final Quest getQuest() {
            return this.zzhxf;
        }
    }

    static final class zze extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<Achievements.UpdateAchievementResult> zzgbf;

        zze(com.google.android.gms.common.api.internal.zzn<Achievements.UpdateAchievementResult> zzn) {
            this.zzgbf = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zzh(int i, String str) {
            this.zzgbf.setResult(new zzcu(i, str));
        }
    }

    static final class zzf extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<Achievements.LoadAchievementsResult> zzgbf;

        zzf(com.google.android.gms.common.api.internal.zzn<Achievements.LoadAchievementsResult> zzn) {
            this.zzgbf = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zzf(DataHolder dataHolder) {
            this.zzgbf.setResult(new zzal(dataHolder));
        }
    }

    static final class zzg implements TurnBasedMultiplayer.CancelMatchResult {
        private final Status mStatus;
        private final String zzhxg;

        zzg(Status status, String str) {
            this.mStatus = status;
            this.zzhxg = str;
        }

        public final String getMatchId() {
            return this.zzhxg;
        }

        public final Status getStatus() {
            return this.mStatus;
        }
    }

    static final class zzh extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<Videos.CaptureAvailableResult> zzgbf;

        zzh(com.google.android.gms.common.api.internal.zzn<Videos.CaptureAvailableResult> zzn) {
            this.zzgbf = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zzi(int i, boolean z) {
            this.zzgbf.setResult(new zzi(new Status(i), z));
        }
    }

    static final class zzi implements Videos.CaptureAvailableResult {
        private final Status mStatus;
        private final boolean zzhxh;

        zzi(Status status, boolean z) {
            this.mStatus = status;
            this.zzhxh = z;
        }

        public final Status getStatus() {
            return this.mStatus;
        }

        public final boolean isAvailable() {
            return this.zzhxh;
        }
    }

    static final class zzj extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<Videos.CaptureCapabilitiesResult> zzgbf;

        zzj(com.google.android.gms.common.api.internal.zzn<Videos.CaptureCapabilitiesResult> zzn) {
            this.zzgbf = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zza(int i, VideoCapabilities videoCapabilities) {
            this.zzgbf.setResult(new zzk(new Status(i), videoCapabilities));
        }
    }

    static final class zzk implements Videos.CaptureCapabilitiesResult {
        private final Status mStatus;
        private final VideoCapabilities zzhxi;

        zzk(Status status, VideoCapabilities videoCapabilities) {
            this.mStatus = status;
            this.zzhxi = videoCapabilities;
        }

        public final VideoCapabilities getCapabilities() {
            return this.zzhxi;
        }

        public final Status getStatus() {
            return this.mStatus;
        }
    }

    static final class zzl extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzci<Videos.CaptureOverlayStateListener> zzgut;

        zzl(com.google.android.gms.common.api.internal.zzci<Videos.CaptureOverlayStateListener> zzci) {
            this.zzgut = (com.google.android.gms.common.api.internal.zzci) com.google.android.gms.common.internal.zzbq.checkNotNull(zzci, "Callback must not be null");
        }

        public final void onCaptureOverlayStateChanged(int i) {
            this.zzgut.zza(new zzm(i));
        }
    }

    static final class zzm implements com.google.android.gms.common.api.internal.zzcl<Videos.CaptureOverlayStateListener> {
        private final int zzhxj;

        zzm(int i) {
            this.zzhxj = i;
        }

        public final void zzajh() {
        }

        public final void zzu(Videos.CaptureOverlayStateListener captureOverlayStateListener) {
            captureOverlayStateListener.onCaptureOverlayStateChanged(this.zzhxj);
        }
    }

    static final class zzn extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<Videos.CaptureStateResult> zzgbf;

        public zzn(com.google.android.gms.common.api.internal.zzn<Videos.CaptureStateResult> zzn) {
            this.zzgbf = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zzh(int i, Bundle bundle) {
            this.zzgbf.setResult(new zzo(new Status(i), CaptureState.zzq(bundle)));
        }
    }

    static final class zzo implements Videos.CaptureStateResult {
        private final Status mStatus;
        private final CaptureState zzhxk;

        zzo(Status status, CaptureState captureState) {
            this.mStatus = status;
            this.zzhxk = captureState;
        }

        public final CaptureState getCaptureState() {
            return this.zzhxk;
        }

        public final Status getStatus() {
            return this.mStatus;
        }
    }

    static final class zzp extends zzwResult implements Quests.ClaimMilestoneResult {
        private final Quest zzhxf;
        private final Milestone zzhxl;

        zzp(DataHolder dataHolder, String str) {
            super(dataHolder);
            QuestBuffer questBuffer = new QuestBuffer(dataHolder);
            try {
                if (questBuffer.getCount() > 0) {
                    QuestEntity questEntity = new QuestEntity((Quest) questBuffer.get(0));
                    this.zzhxf = questEntity;
                    List<Milestone> zzavw = questEntity.zzavw();
                    int size = zzavw.size();
                    for (int i = 0; i < size; i++) {
                        if (zzavw.get(i).getMilestoneId().equals(str)) {
                            this.zzhxl = zzavw.get(i);
                            return;
                        }
                    }
                    this.zzhxl = null;
                } else {
                    this.zzhxl = null;
                    this.zzhxf = null;
                }
                questBuffer.release();
            } finally {
                questBuffer.release();
            }
        }

        public final Milestone getMilestone() {
            return this.zzhxl;
        }

        public final Quest getQuest() {
            return this.zzhxf;
        }
    }

    static final class zzq extends zzwResult implements Snapshots.CommitSnapshotResult {
        private final SnapshotMetadata zzhxm;

        zzq(DataHolder dataHolder) {
            super(dataHolder);
            SnapshotMetadataBuffer snapshotMetadataBuffer = new SnapshotMetadataBuffer(dataHolder);
            try {
                if (snapshotMetadataBuffer.getCount() > 0) {
                    this.zzhxm = new SnapshotMetadataEntity((SnapshotMetadata) snapshotMetadataBuffer.get(0));
                } else {
                    this.zzhxm = null;
                }
            } finally {
                snapshotMetadataBuffer.release();
            }
        }

        public final SnapshotMetadata getSnapshotMetadata() {
            return this.zzhxm;
        }
    }

    static final class zzr extends zzc {
        zzr(DataHolder dataHolder) {
            super(dataHolder);
        }

        public final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room) {
            roomStatusUpdateListener.onConnectedToRoom(room);
        }
    }

    static final class zzsResult implements Snapshots.DeleteSnapshotResult {
        private final Status mStatus;
        private final String zzhxn;

        zzsResult(int i, String str) {
            this.mStatus = GamesStatusCodes.zzdg(i);
            this.zzhxn = str;
        }

        public final String getSnapshotId() {
            return this.zzhxn;
        }

        public final Status getStatus() {
            return this.mStatus;
        }
    }

    static final class zzt extends zzc {
        zzt(DataHolder dataHolder) {
            super(dataHolder);
        }

        public final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room) {
            roomStatusUpdateListener.onDisconnectedFromRoom(room);
        }
    }

    static final class zzu extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<Events.LoadEventsResult> zzgbf;

        zzu(com.google.android.gms.common.api.internal.zzn<Events.LoadEventsResult> zzn) {
            this.zzgbf = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zzg(DataHolder dataHolder) {
            this.zzgbf.setResult(new zzam(dataHolder));
        }
    }

    class zzv extends zzcct {
        public zzv() {
            super(GamesClientImpl.this.getContext().getMainLooper(), 1000);
        }

        /* access modifiers changed from: protected */
        public final void zzu(String str, int i) {
            try {
                if (GamesClientImpl.this.isConnected()) {
                    ((zzw) GamesClientImpl.this.zzalw()).zzp(str, i);
                    return;
                }
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 89);
                sb.append("Unable to increment event ");
                sb.append(str);
                sb.append(" by ");
                sb.append(i);
                sb.append(" because the games client is no longer connected");
                com.google.android.gms.games.internal.zzf.zzw("GamesClientImpl", sb.toString());
            } catch (RemoteException e) {
                GamesClientImpl.zzd(e);
            } catch (SecurityException e2) {
                GamesClientImpl.zza(e2);
            }
        }
    }

    static abstract class zzwResult extends com.google.android.gms.common.api.internal.zzaj {
        protected zzwResult(DataHolder dataHolder) {
            super(dataHolder, GamesStatusCodes.zzdg(dataHolder.getStatusCode()));
        }
    }

    static final class zzx extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<GamesMetadata.LoadGamesResult> zzgbf;

        zzx(com.google.android.gms.common.api.internal.zzn<GamesMetadata.LoadGamesResult> zzn) {
            this.zzgbf = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zzl(DataHolder dataHolder) {
            this.zzgbf.setResult(new zzan(dataHolder));
        }
    }

    static final class zzy extends com.google.android.gms.games.internal.zza {
        private final com.google.android.gms.common.api.internal.zzn<Games.GetServerAuthCodeResult> zzgbf;

        public zzy(com.google.android.gms.common.api.internal.zzn<Games.GetServerAuthCodeResult> zzn) {
            this.zzgbf = (com.google.android.gms.common.api.internal.zzn) com.google.android.gms.common.internal.zzbq.checkNotNull(zzn, "Holder must not be null");
        }

        public final void zzg(int i, String str) {
            this.zzgbf.setResult(new zzz(GamesStatusCodes.zzdg(i), str));
        }
    }

    static final class zzz implements Games.GetServerAuthCodeResult {
        private final Status mStatus;
        private final String zzhxo;

        zzz(Status status, String str) {
            this.mStatus = status;
            this.zzhxo = str;
        }

        public final String getCode() {
            return this.zzhxo;
        }

        public final Status getStatus() {
            return this.mStatus;
        }
    }

    public GamesClientImpl(Context context, Looper looper, com.google.android.gms.common.internal.zzr zzr2, Games.GamesOptions gamesOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 1, zzr2, connectionCallbacks, onConnectionFailedListener);
        this.zzhws = zzr2.zzami();
        this.zzhwx = new Binder();
        this.zzhwv = new com.google.android.gms.games.internal.zzad(this, zzr2.zzame());
        this.zzhwy = (long) hashCode();
        this.zzhwz = gamesOptions;
        if (gamesOptions.zzhsh) {
            return;
        }
        if (zzr2.zzamk() != null || (context instanceof Activity)) {
            zzz(zzr2.zzamk());
        }
    }

    private static <R> void zza(com.google.android.gms.common.api.internal.zzn<R> zzn2, SecurityException securityException) {
        if (zzn2 != null) {
            zzn2.zzu(GamesClientStatusCodes.zzdg(4));
        }
    }

    /* access modifiers changed from: private */
    public static void zza(SecurityException securityException) {
        com.google.android.gms.games.internal.zzf.zzd("GamesClientImpl", "Is player signed out?", securityException);
    }

    /* access modifiers changed from: private */
    public static Room zzbg(DataHolder dataHolder) {
        com.google.android.gms.games.multiplayer.realtime.zzb zzb2 = new com.google.android.gms.games.multiplayer.realtime.zzb(dataHolder);
        try {
            return zzb2.getCount() > 0 ? (Room) ((Room) zzb2.get(0)).freeze() : null;
        } finally {
            zzb2.release();
        }
    }

    /* access modifiers changed from: private */
    public static void zzd(RemoteException remoteException) {
        com.google.android.gms.games.internal.zzf.zzc("GamesClientImpl", "service died", remoteException);
    }

    public final void disconnect() {
        this.zzhww = false;
        if (isConnected()) {
            try {
                zzw zzw2 = (zzw) zzalw();
                zzw2.zzaur();
                this.zzhwr.flush();
                zzw2.zzac(this.zzhwy);
            } catch (RemoteException unused) {
                com.google.android.gms.games.internal.zzf.zzv("GamesClientImpl", "Failed to notify client disconnect.");
            }
        }
        super.disconnect();
    }

    public final String getAppId() throws RemoteException {
        return ((zzw) zzalw()).getAppId();
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        super.onConnectionFailed(connectionResult);
        this.zzhww = false;
    }

    public final int zza(com.google.android.gms.common.api.internal.zzci<RealTimeMultiplayer.ReliableMessageSentCallback> zzci2, byte[] bArr, String str, String str2) throws RemoteException {
        return ((zzw) zzalw()).zza((zzs) new zzbv(zzci2), bArr, str, str2);
    }

    public final int zza(byte[] bArr, String str, String[] strArr) {
        com.google.android.gms.common.internal.zzbq.checkNotNull(strArr, "Participant IDs must not be null");
        try {
            com.google.android.gms.common.internal.zzbq.checkNotNull(strArr, "Participant IDs must not be null");
            return ((zzw) zzalw()).zzb(bArr, str, strArr);
        } catch (RemoteException e) {
            zzd(e);
            return -1;
        }
    }

    public final Intent zza(int i, byte[] bArr, int i2, Bitmap bitmap, String str) {
        try {
            Intent zza2 = ((zzw) zzalw()).zza(i, bArr, i2, str);
            com.google.android.gms.common.internal.zzbq.checkNotNull(bitmap, "Must provide a non null icon");
            zza2.putExtra("com.google.android.gms.games.REQUEST_ITEM_ICON", bitmap);
            return zza2;
        } catch (RemoteException e) {
            zzd(e);
            return null;
        }
    }

    public final Intent zza(PlayerEntity playerEntity) throws RemoteException {
        return ((zzw) zzalw()).zza(playerEntity);
    }

    public final Intent zza(Room room, int i) throws RemoteException {
        return ((zzw) zzalw()).zza((RoomEntity) room.freeze(), i);
    }

    public final Intent zza(String str, boolean z, boolean z2, int i) throws RemoteException {
        return ((zzw) zzalw()).zza(str, z, z2, i);
    }

    /* access modifiers changed from: protected */
    public final void zza(int i, IBinder iBinder, Bundle bundle, int i2) {
        if (i == 0 && bundle != null) {
            bundle.setClassLoader(GamesClientImpl.class.getClassLoader());
            boolean z = bundle.getBoolean("show_welcome_popup");
            this.zzhww = z;
            this.zzhxa = z;
            this.zzhwt = (PlayerEntity) bundle.getParcelable("com.google.android.gms.games.current_player");
            this.zzhwu = (GameEntity) bundle.getParcelable("com.google.android.gms.games.current_game");
        }
        super.zza(i, iBinder, bundle, i2);
    }

    public final void zza(IBinder iBinder, Bundle bundle) {
        if (isConnected()) {
            try {
                ((zzw) zzalw()).zza(iBinder, bundle);
            } catch (RemoteException e) {
                zzd(e);
            }
        }
    }

    public final void zza(zzw zzw2) {
        super.zza(zzw2);
        if (this.zzhww) {
            this.zzhwv.zzaux();
            this.zzhww = false;
        }
        if (!this.zzhwz.zzhrz && !this.zzhwz.zzhsh) {
            try {
                zzw2.zza((com.google.android.gms.games.internal.zzu) new zzbo(this.zzhwv), this.zzhwy);
            } catch (RemoteException e) {
                zzd(e);
            }
        }
    }

    public final void zza(com.google.android.gms.common.api.internal.zzci<? extends RoomUpdateListener> zzci2, com.google.android.gms.common.api.internal.zzci<? extends RoomStatusUpdateListener> zzci3, com.google.android.gms.common.api.internal.zzci<? extends RealTimeMessageReceivedListener> zzci4, RoomConfig roomConfig) throws RemoteException {
        ((zzw) zzalw()).zza(new zzcc(zzci2, zzci3, zzci4), this.zzhwx, roomConfig.getVariant(), roomConfig.getInvitedPlayerIds(), roomConfig.getAutoMatchCriteria(), false, this.zzhwy);
    }

    public final void zza(com.google.android.gms.common.api.internal.zzci<? extends RoomUpdateListener> zzci2, String str) {
        try {
            ((zzw) zzalw()).zza((zzs) new zzcc(zzci2), str);
        } catch (RemoteException e) {
            zzd(e);
        }
    }

    public final void zza(com.google.android.gms.common.api.internal.zzn<Invitations.LoadInvitationsResult> zzn2, int i) throws RemoteException {
        try {
            ((zzw) zzalw()).zza((zzs) new zzae(zzn2), i);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zza(com.google.android.gms.common.api.internal.zzn<Requests.LoadRequestsResult> zzn2, int i, int i2, int i3) throws RemoteException {
        try {
            ((zzw) zzalw()).zza((zzs) new zzbz(zzn2), i, i2, i3);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zza(com.google.android.gms.common.api.internal.zzn<Players.LoadPlayersResult> zzn2, int i, boolean z, boolean z2) throws RemoteException {
        try {
            ((zzw) zzalw()).zza((zzs) new zzbn(zzn2), i, z, z2);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zza(com.google.android.gms.common.api.internal.zzn<TurnBasedMultiplayer.LoadMatchesResult> zzn2, int i, int[] iArr) throws RemoteException {
        try {
            ((zzw) zzalw()).zza((zzs) new zzct(zzn2), i, iArr);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zza(com.google.android.gms.common.api.internal.zzn<Leaderboards.LoadScoresResult> zzn2, LeaderboardScoreBuffer leaderboardScoreBuffer, int i, int i2) throws RemoteException {
        try {
            ((zzw) zzalw()).zza((zzs) new zzah(zzn2), leaderboardScoreBuffer.zzavp().asBundle(), i, i2);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zza(com.google.android.gms.common.api.internal.zzn<TurnBasedMultiplayer.InitiateMatchResult> zzn2, TurnBasedMatchConfig turnBasedMatchConfig) throws RemoteException {
        try {
            ((zzw) zzalw()).zza((zzs) new zzco(zzn2), turnBasedMatchConfig.getVariant(), turnBasedMatchConfig.zzavv(), turnBasedMatchConfig.getInvitedPlayerIds(), turnBasedMatchConfig.getAutoMatchCriteria());
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zza(com.google.android.gms.common.api.internal.zzn<Snapshots.CommitSnapshotResult> zzn2, Snapshot snapshot, SnapshotMetadataChange snapshotMetadataChange) throws RemoteException {
        SnapshotContents snapshotContents = snapshot.getSnapshotContents();
        com.google.android.gms.common.internal.zzbq.zza(!snapshotContents.isClosed(), (Object) "Snapshot already closed");
        BitmapTeleporter zzavy = snapshotMetadataChange.zzavy();
        if (zzavy != null) {
            zzavy.setTempDir(getContext().getCacheDir());
        }
        com.google.android.gms.drive.zzc zzapl = snapshotContents.zzapl();
        snapshotContents.close();
        try {
            ((zzw) zzalw()).zza((zzs) new zzch(zzn2), snapshot.getMetadata().getSnapshotId(), (com.google.android.gms.games.snapshot.zze) snapshotMetadataChange, zzapl);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zza(com.google.android.gms.common.api.internal.zzn<Achievements.UpdateAchievementResult> zzn2, String str) throws RemoteException {
        try {
            ((zzw) zzalw()).zza((zzs) zzn2 == null ? null : new zze(zzn2), str, this.zzhwv.zziad.zziae, this.zzhwv.zziad.zzauy());
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zza(com.google.android.gms.common.api.internal.zzn<Achievements.UpdateAchievementResult> zzn2, String str, int i) throws RemoteException {
        try {
            ((zzw) zzalw()).zza((zzs) zzn2 == null ? null : new zze(zzn2), str, i, this.zzhwv.zziad.zziae, this.zzhwv.zziad.zzauy());
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zza(com.google.android.gms.common.api.internal.zzn<Leaderboards.LoadScoresResult> zzn2, String str, int i, int i2, int i3, boolean z) throws RemoteException {
        try {
            ((zzw) zzalw()).zza(new zzah(zzn2), str, i, i2, i3, z);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zza(com.google.android.gms.common.api.internal.zzn<Players.LoadPlayersResult> zzn2, String str, int i, boolean z, boolean z2) throws RemoteException {
        str.hashCode();
        if (!str.equals("played_with")) {
            String valueOf = String.valueOf(str);
            throw new IllegalArgumentException(valueOf.length() != 0 ? "Invalid player collection: ".concat(valueOf) : new String("Invalid player collection: "));
        }
        try {
            ((zzw) zzalw()).zza((zzs) new zzbn(zzn2), str, i, z, z2);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zza(com.google.android.gms.common.api.internal.zzn<Leaderboards.SubmitScoreResult> zzn2, String str, long j, String str2) throws RemoteException {
        try {
            ((zzw) zzalw()).zza((zzs) zzn2 == null ? null : new zzcl(zzn2), str, j, str2);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zza(com.google.android.gms.common.api.internal.zzn<TurnBasedMultiplayer.LeaveMatchResult> zzn2, String str, String str2) throws RemoteException {
        try {
            ((zzw) zzalw()).zza((zzs) new zzcp(zzn2), str, str2);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zza(com.google.android.gms.common.api.internal.zzn<Leaderboards.LoadPlayerScoreResult> zzn2, String str, String str2, int i, int i2) throws RemoteException {
        try {
            ((zzw) zzalw()).zza((zzs) new zzbl(zzn2), (String) null, str2, i, i2);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zza(com.google.android.gms.common.api.internal.zzn<Snapshots.OpenSnapshotResult> zzn2, String str, String str2, SnapshotMetadataChange snapshotMetadataChange, SnapshotContents snapshotContents) throws RemoteException {
        com.google.android.gms.common.internal.zzbq.zza(!snapshotContents.isClosed(), (Object) "SnapshotContents already closed");
        BitmapTeleporter zzavy = snapshotMetadataChange.zzavy();
        if (zzavy != null) {
            zzavy.setTempDir(getContext().getCacheDir());
        }
        com.google.android.gms.drive.zzc zzapl = snapshotContents.zzapl();
        snapshotContents.close();
        try {
            ((zzw) zzalw()).zza((zzs) new zzcj(zzn2), str, str2, (com.google.android.gms.games.snapshot.zze) snapshotMetadataChange, zzapl);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zza(com.google.android.gms.common.api.internal.zzn<Players.LoadPlayersResult> zzn2, String str, boolean z) throws RemoteException {
        try {
            ((zzw) zzalw()).zzb((zzs) new zzbn(zzn2), str, z);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zza(com.google.android.gms.common.api.internal.zzn<Snapshots.OpenSnapshotResult> zzn2, String str, boolean z, int i) throws RemoteException {
        try {
            ((zzw) zzalw()).zza((zzs) new zzcj(zzn2), str, z, i);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zza(com.google.android.gms.common.api.internal.zzn<TurnBasedMultiplayer.UpdateMatchResult> zzn2, String str, byte[] bArr, String str2, ParticipantResult[] participantResultArr) throws RemoteException {
        try {
            ((zzw) zzalw()).zza((zzs) new zzcs(zzn2), str, bArr, str2, participantResultArr);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zza(com.google.android.gms.common.api.internal.zzn<TurnBasedMultiplayer.UpdateMatchResult> zzn2, String str, byte[] bArr, ParticipantResult[] participantResultArr) throws RemoteException {
        try {
            ((zzw) zzalw()).zza((zzs) new zzcs(zzn2), str, bArr, participantResultArr);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zza(com.google.android.gms.common.api.internal.zzn<Players.LoadPlayersResult> zzn2, boolean z) throws RemoteException {
        try {
            ((zzw) zzalw()).zzc((zzs) new zzbn(zzn2), z);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zza(com.google.android.gms.common.api.internal.zzn<Events.LoadEventsResult> zzn2, boolean z, String... strArr) throws RemoteException {
        this.zzhwr.flush();
        try {
            ((zzw) zzalw()).zza((zzs) new zzu(zzn2), z, strArr);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zza(com.google.android.gms.common.api.internal.zzn<Quests.LoadQuestsResult> zzn2, int[] iArr, int i, boolean z) throws RemoteException {
        this.zzhwr.flush();
        try {
            ((zzw) zzalw()).zza((zzs) new zzbt(zzn2), iArr, i, z);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zza(com.google.android.gms.common.api.internal.zzn<Requests.UpdateRequestsResult> zzn2, String[] strArr) throws RemoteException {
        try {
            ((zzw) zzalw()).zza((zzs) new zzca(zzn2), strArr);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zza(com.google.android.gms.common.internal.zzj zzj2) {
        this.zzhwt = null;
        this.zzhwu = null;
        super.zza(zzj2);
    }

    public final void zza(com.google.android.gms.common.internal.zzp zzp2) {
        try {
            zzg((com.google.android.gms.common.api.internal.zzn<Status>) new com.google.android.gms.games.internal.zze(this, zzp2));
        } catch (RemoteException unused) {
            zzp2.zzako();
        }
    }

    public final void zza(Snapshot snapshot) throws RemoteException {
        SnapshotContents snapshotContents = snapshot.getSnapshotContents();
        com.google.android.gms.common.internal.zzbq.zza(!snapshotContents.isClosed(), (Object) "Snapshot already closed");
        com.google.android.gms.drive.zzc zzapl = snapshotContents.zzapl();
        snapshotContents.close();
        ((zzw) zzalw()).zza(zzapl);
    }

    /* access modifiers changed from: protected */
    public final Bundle zzabt() {
        String locale = getContext().getResources().getConfiguration().locale.toString();
        Bundle zzasu = this.zzhwz.zzasu();
        zzasu.putString("com.google.android.gms.games.key.gamePackageName", this.zzhws);
        zzasu.putString("com.google.android.gms.games.key.desiredLocale", locale);
        zzasu.putParcelable("com.google.android.gms.games.key.popupWindowToken", new BinderWrapper(this.zzhwv.zziad.zziae));
        zzasu.putInt("com.google.android.gms.games.key.API_VERSION", 6);
        zzasu.putBundle("com.google.android.gms.games.key.signInOptions", zzcyt.zza(zzamr()));
        return zzasu;
    }

    public final boolean zzacc() {
        return true;
    }

    public final Bundle zzagp() {
        try {
            Bundle zzagp = ((zzw) zzalw()).zzagp();
            if (zzagp != null) {
                zzagp.setClassLoader(GamesClientImpl.class.getClassLoader());
                this.zzhxb = zzagp;
            }
            return zzagp;
        } catch (RemoteException e) {
            zzd(e);
            return null;
        }
    }

    public final Bundle zzatd() {
        Bundle zzagp = zzagp();
        if (zzagp == null) {
            zzagp = this.zzhxb;
        }
        this.zzhxb = null;
        return zzagp;
    }

    public final String zzate() throws RemoteException {
        return ((zzw) zzalw()).zzate();
    }

    public final String zzatf() {
        try {
            return zzate();
        } catch (RemoteException e) {
            zzd(e);
            return null;
        }
    }

    /* JADX INFO: finally extract failed */
    public final Player zzatg() throws RemoteException {
        zzalv();
        synchronized (this) {
            if (this.zzhwt == null) {
                PlayerBuffer playerBuffer = new PlayerBuffer(((zzw) zzalw()).zzauu());
                try {
                    if (playerBuffer.getCount() > 0) {
                        this.zzhwt = (PlayerEntity) ((Player) playerBuffer.get(0)).freeze();
                    }
                    playerBuffer.release();
                } catch (Throwable th) {
                    playerBuffer.release();
                    throw th;
                }
            }
        }
        return this.zzhwt;
    }

    public final Player zzath() {
        try {
            return zzatg();
        } catch (RemoteException e) {
            zzd(e);
            return null;
        }
    }

    /* JADX INFO: finally extract failed */
    public final Game zzati() throws RemoteException {
        zzalv();
        synchronized (this) {
            if (this.zzhwu == null) {
                GameBuffer gameBuffer = new GameBuffer(((zzw) zzalw()).zzauv());
                try {
                    if (gameBuffer.getCount() > 0) {
                        this.zzhwu = (GameEntity) ((Game) gameBuffer.get(0)).freeze();
                    }
                    gameBuffer.release();
                } catch (Throwable th) {
                    gameBuffer.release();
                    throw th;
                }
            }
        }
        return this.zzhwu;
    }

    public final Game zzatj() {
        try {
            return zzati();
        } catch (RemoteException e) {
            zzd(e);
            return null;
        }
    }

    public final Intent zzatk() throws RemoteException {
        return ((zzw) zzalw()).zzatk();
    }

    public final Intent zzatl() {
        try {
            return zzatk();
        } catch (RemoteException e) {
            zzd(e);
            return null;
        }
    }

    public final Intent zzatm() {
        try {
            return ((zzw) zzalw()).zzatm();
        } catch (RemoteException e) {
            zzd(e);
            return null;
        }
    }

    public final Intent zzatn() {
        try {
            return ((zzw) zzalw()).zzatn();
        } catch (RemoteException e) {
            zzd(e);
            return null;
        }
    }

    public final Intent zzato() {
        try {
            return ((zzw) zzalw()).zzato();
        } catch (RemoteException e) {
            zzd(e);
            return null;
        }
    }

    public final void zzatp() throws RemoteException {
        ((zzw) zzalw()).zzad(this.zzhwy);
    }

    public final void zzatq() {
        try {
            zzatp();
        } catch (RemoteException e) {
            zzd(e);
        }
    }

    public final void zzatr() throws RemoteException {
        ((zzw) zzalw()).zzae(this.zzhwy);
    }

    public final void zzats() {
        try {
            zzatr();
        } catch (RemoteException e) {
            zzd(e);
        }
    }

    public final void zzatt() {
        try {
            ((zzw) zzalw()).zzag(this.zzhwy);
        } catch (RemoteException e) {
            zzd(e);
        }
    }

    public final void zzatu() {
        try {
            ((zzw) zzalw()).zzaf(this.zzhwy);
        } catch (RemoteException e) {
            zzd(e);
        }
    }

    public final Intent zzatv() throws RemoteException {
        return ((zzw) zzalw()).zzatv();
    }

    public final Intent zzatw() {
        try {
            return zzatv();
        } catch (RemoteException e) {
            zzd(e);
            return null;
        }
    }

    public final Intent zzatx() throws RemoteException {
        return ((zzw) zzalw()).zzatx();
    }

    public final Intent zzaty() {
        try {
            return zzatx();
        } catch (RemoteException e) {
            zzd(e);
            return null;
        }
    }

    public final int zzatz() throws RemoteException {
        return ((zzw) zzalw()).zzatz();
    }

    public final int zzaua() {
        try {
            return zzatz();
        } catch (RemoteException e) {
            zzd(e);
            return 4368;
        }
    }

    public final String zzaub() {
        try {
            return getAppId();
        } catch (RemoteException e) {
            zzd(e);
            return null;
        }
    }

    public final int zzauc() throws RemoteException {
        return ((zzw) zzalw()).zzauc();
    }

    public final int zzaud() {
        try {
            return zzauc();
        } catch (RemoteException e) {
            zzd(e);
            return -1;
        }
    }

    public final Intent zzaue() {
        try {
            return ((zzw) zzalw()).zzaue();
        } catch (RemoteException e) {
            zzd(e);
            return null;
        }
    }

    public final int zzauf() {
        try {
            return ((zzw) zzalw()).zzauf();
        } catch (RemoteException e) {
            zzd(e);
            return -1;
        }
    }

    public final int zzaug() {
        try {
            return ((zzw) zzalw()).zzaug();
        } catch (RemoteException e) {
            zzd(e);
            return -1;
        }
    }

    public final int zzauh() throws RemoteException {
        return ((zzw) zzalw()).zzauh();
    }

    public final int zzaui() {
        try {
            return zzauh();
        } catch (RemoteException e) {
            zzd(e);
            return -1;
        }
    }

    public final int zzauj() throws RemoteException {
        return ((zzw) zzalw()).zzauj();
    }

    public final int zzauk() {
        try {
            return zzauj();
        } catch (RemoteException e) {
            zzd(e);
            return -1;
        }
    }

    public final Intent zzaul() throws RemoteException {
        return ((zzw) zzalw()).zzauw();
    }

    public final Intent zzaum() {
        try {
            return zzaul();
        } catch (RemoteException e) {
            zzd(e);
            return null;
        }
    }

    public final boolean zzaun() throws RemoteException {
        return ((zzw) zzalw()).zzaun();
    }

    public final boolean zzauo() {
        try {
            return zzaun();
        } catch (RemoteException e) {
            zzd(e);
            return false;
        }
    }

    public final void zzaup() throws RemoteException {
        ((zzw) zzalw()).zzah(this.zzhwy);
    }

    public final void zzauq() {
        try {
            zzaup();
        } catch (RemoteException e) {
            zzd(e);
        }
    }

    public final void zzaur() {
        if (isConnected()) {
            try {
                ((zzw) zzalw()).zzaur();
            } catch (RemoteException e) {
                zzd(e);
            }
        }
    }

    public final int zzb(com.google.android.gms.common.api.internal.zzci<RealTimeMultiplayer.ReliableMessageSentCallback> zzci2, byte[] bArr, String str, String str2) {
        try {
            return zza(zzci2, bArr, str, str2);
        } catch (RemoteException e) {
            zzd(e);
            return -1;
        }
    }

    public final Intent zzb(int i, int i2, boolean z) throws RemoteException {
        return ((zzw) zzalw()).zzb(i, i2, z);
    }

    public final Intent zzb(PlayerEntity playerEntity) {
        try {
            return zza(playerEntity);
        } catch (RemoteException e) {
            zzd(e);
            return null;
        }
    }

    public final Intent zzb(Room room, int i) {
        try {
            return zza(room, i);
        } catch (RemoteException e) {
            zzd(e);
            return null;
        }
    }

    public final Intent zzb(String str, boolean z, boolean z2, int i) {
        try {
            return zza(str, z, z2, i);
        } catch (RemoteException e) {
            zzd(e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final Set<Scope> zzb(Set<Scope> set) {
        HashSet hashSet = new HashSet(set);
        boolean contains = set.contains(Games.SCOPE_GAMES);
        boolean contains2 = set.contains(Games.SCOPE_GAMES_LITE);
        if (set.contains(Games.zzhrt)) {
            com.google.android.gms.common.internal.zzbq.zza(!contains, "Cannot have both %s and %s!", Scopes.GAMES, "https://www.googleapis.com/auth/games.firstparty");
        } else {
            com.google.android.gms.common.internal.zzbq.zza(contains || contains2, "Games APIs requires %s function.", "https://www.googleapis.com/auth/games_lite");
            if (contains2 && contains) {
                hashSet.remove(Games.SCOPE_GAMES_LITE);
            }
        }
        return hashSet;
    }

    public final void zzb(com.google.android.gms.common.api.internal.zzci<? extends RoomUpdateListener> zzci2, com.google.android.gms.common.api.internal.zzci<? extends RoomStatusUpdateListener> zzci3, com.google.android.gms.common.api.internal.zzci<? extends RealTimeMessageReceivedListener> zzci4, RoomConfig roomConfig) {
        try {
            zza(zzci2, zzci3, zzci4, roomConfig);
        } catch (RemoteException e) {
            zzd(e);
        }
    }

    public final void zzb(com.google.android.gms.common.api.internal.zzn<Videos.CaptureAvailableResult> zzn2, int i) throws RemoteException {
        try {
            ((zzw) zzalw()).zzb((zzs) new zzh(zzn2), i);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zzb(com.google.android.gms.common.api.internal.zzn<Achievements.UpdateAchievementResult> zzn2, String str) throws RemoteException {
        try {
            ((zzw) zzalw()).zzb(zzn2 == null ? null : new zze(zzn2), str, this.zzhwv.zziad.zziae, this.zzhwv.zziad.zzauy());
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zzb(com.google.android.gms.common.api.internal.zzn<Achievements.UpdateAchievementResult> zzn2, String str, int i) throws RemoteException {
        try {
            ((zzw) zzalw()).zzb(zzn2 == null ? null : new zze(zzn2), str, i, this.zzhwv.zziad.zziae, this.zzhwv.zziad.zzauy());
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zzb(com.google.android.gms.common.api.internal.zzn<Leaderboards.LoadScoresResult> zzn2, String str, int i, int i2, int i3, boolean z) throws RemoteException {
        try {
            ((zzw) zzalw()).zzb(new zzah(zzn2), str, i, i2, i3, z);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zzb(com.google.android.gms.common.api.internal.zzn<Quests.ClaimMilestoneResult> zzn2, String str, String str2) throws RemoteException {
        this.zzhwr.flush();
        try {
            ((zzw) zzalw()).zzb((zzs) new zzbr(zzn2, str2), str, str2);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zzb(com.google.android.gms.common.api.internal.zzn<Leaderboards.LeaderboardMetadataResult> zzn2, String str, boolean z) throws RemoteException {
        try {
            ((zzw) zzalw()).zza((zzs) new zzai(zzn2), str, z);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zzb(com.google.android.gms.common.api.internal.zzn<Leaderboards.LeaderboardMetadataResult> zzn2, boolean z) throws RemoteException {
        try {
            ((zzw) zzalw()).zzb((zzs) new zzai(zzn2), z);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zzb(com.google.android.gms.common.api.internal.zzn<Quests.LoadQuestsResult> zzn2, boolean z, String[] strArr) throws RemoteException {
        this.zzhwr.flush();
        try {
            ((zzw) zzalw()).zza((zzs) new zzbt(zzn2), strArr, z);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zzb(com.google.android.gms.common.api.internal.zzn<Requests.UpdateRequestsResult> zzn2, String[] strArr) throws RemoteException {
        try {
            ((zzw) zzalw()).zzb((zzs) new zzca(zzn2), strArr);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zzb(Snapshot snapshot) {
        try {
            zza(snapshot);
        } catch (RemoteException e) {
            zzd(e);
        }
    }

    public final void zzb(String str, com.google.android.gms.common.api.internal.zzn<Games.GetServerAuthCodeResult> zzn2) throws RemoteException {
        com.google.android.gms.common.internal.zzbq.zzh(str, "Please provide a valid serverClientId");
        try {
            ((zzw) zzalw()).zza(str, (zzs) new zzy(zzn2));
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final String zzbm(boolean z) throws RemoteException {
        PlayerEntity playerEntity = this.zzhwt;
        return playerEntity != null ? playerEntity.getPlayerId() : ((zzw) zzalw()).zzaut();
    }

    public final String zzbn(boolean z) {
        try {
            return zzbm(true);
        } catch (RemoteException e) {
            zzd(e);
            return null;
        }
    }

    public final Intent zzc(int i, int i2, boolean z) {
        try {
            return zzb(i, i2, z);
        } catch (RemoteException e) {
            zzd(e);
            return null;
        }
    }

    public final Intent zzc(int[] iArr) {
        try {
            return ((zzw) zzalw()).zzc(iArr);
        } catch (RemoteException e) {
            zzd(e);
            return null;
        }
    }

    public final void zzc(com.google.android.gms.common.api.internal.zzci<? extends RoomUpdateListener> zzci2, com.google.android.gms.common.api.internal.zzci<? extends RoomStatusUpdateListener> zzci3, com.google.android.gms.common.api.internal.zzci<? extends RealTimeMessageReceivedListener> zzci4, RoomConfig roomConfig) throws RemoteException {
        ((zzw) zzalw()).zza((zzs) new zzcc(zzci2, zzci3, zzci4), (IBinder) this.zzhwx, roomConfig.getInvitationId(), false, this.zzhwy);
    }

    public final void zzc(com.google.android.gms.common.api.internal.zzn<TurnBasedMultiplayer.InitiateMatchResult> zzn2, String str) throws RemoteException {
        try {
            ((zzw) zzalw()).zzb((zzs) new zzco(zzn2), str);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zzc(com.google.android.gms.common.api.internal.zzn<Achievements.LoadAchievementsResult> zzn2, boolean z) throws RemoteException {
        try {
            ((zzw) zzalw()).zza((zzs) new zzf(zzn2), z);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final int zzd(byte[] bArr, String str) throws RemoteException {
        return ((zzw) zzalw()).zzb(bArr, str, (String[]) null);
    }

    public final Intent zzd(int i, int i2, boolean z) throws RemoteException {
        return ((zzw) zzalw()).zzd(i, i2, z);
    }

    /* access modifiers changed from: protected */
    public final zzw zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.games.internal.IGamesService");
        return queryLocalInterface instanceof zzw ? (zzw) queryLocalInterface : new com.google.android.gms.games.internal.zzx(iBinder);
    }

    public final void zzd(com.google.android.gms.common.api.internal.zzci<? extends RoomUpdateListener> zzci2, com.google.android.gms.common.api.internal.zzci<? extends RoomStatusUpdateListener> zzci3, com.google.android.gms.common.api.internal.zzci<? extends RealTimeMessageReceivedListener> zzci4, RoomConfig roomConfig) {
        try {
            zzc(zzci2, zzci3, zzci4, roomConfig);
        } catch (RemoteException e) {
            zzd(e);
        }
    }

    public final void zzd(com.google.android.gms.common.api.internal.zzn<TurnBasedMultiplayer.InitiateMatchResult> zzn2, String str) throws RemoteException {
        try {
            ((zzw) zzalw()).zzc((zzs) new zzco(zzn2), str);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zzd(com.google.android.gms.common.api.internal.zzn<Events.LoadEventsResult> zzn2, boolean z) throws RemoteException {
        this.zzhwr.flush();
        try {
            ((zzw) zzalw()).zze((zzs) new zzu(zzn2), z);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zzds(int i) {
        this.zzhwv.zziad.gravity = i;
    }

    public final void zzdt(int i) throws RemoteException {
        ((zzw) zzalw()).zzdt(i);
    }

    public final void zzdu(int i) {
        try {
            zzdt(i);
        } catch (RemoteException e) {
            zzd(e);
        }
    }

    public final int zze(byte[] bArr, String str) {
        try {
            return zzd(bArr, str);
        } catch (RemoteException e) {
            zzd(e);
            return -1;
        }
    }

    public final Intent zze(int i, int i2, boolean z) {
        try {
            return zzd(i, i2, z);
        } catch (RemoteException e) {
            zzd(e);
            return null;
        }
    }

    public final void zze(com.google.android.gms.common.api.internal.zzci<OnInvitationReceivedListener> zzci2) throws RemoteException {
        ((zzw) zzalw()).zza((zzs) new zzab(zzci2), this.zzhwy);
    }

    public final void zze(com.google.android.gms.common.api.internal.zzn<TurnBasedMultiplayer.LeaveMatchResult> zzn2, String str) throws RemoteException {
        try {
            ((zzw) zzalw()).zze((zzs) new zzcp(zzn2), str);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zze(com.google.android.gms.common.api.internal.zzn<Stats.LoadPlayerStatsResult> zzn2, boolean z) throws RemoteException {
        try {
            ((zzw) zzalw()).zzf((zzs) new zzbm(zzn2), z);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zzf(com.google.android.gms.common.api.internal.zzci<OnInvitationReceivedListener> zzci2) {
        try {
            zze(zzci2);
        } catch (RemoteException e) {
            zzd(e);
        }
    }

    public final void zzf(com.google.android.gms.common.api.internal.zzn<GamesMetadata.LoadGamesResult> zzn2) throws RemoteException {
        try {
            ((zzw) zzalw()).zzb(new zzx(zzn2));
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zzf(com.google.android.gms.common.api.internal.zzn<TurnBasedMultiplayer.CancelMatchResult> zzn2, String str) throws RemoteException {
        try {
            ((zzw) zzalw()).zzd((zzs) new zzcn(zzn2), str);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zzf(com.google.android.gms.common.api.internal.zzn<Snapshots.LoadSnapshotsResult> zzn2, boolean z) throws RemoteException {
        try {
            ((zzw) zzalw()).zzd((zzs) new zzck(zzn2), z);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zzg(com.google.android.gms.common.api.internal.zzci<OnTurnBasedMatchUpdateReceivedListener> zzci2) throws RemoteException {
        ((zzw) zzalw()).zzb((zzs) new zzaz(zzci2), this.zzhwy);
    }

    public final void zzg(com.google.android.gms.common.api.internal.zzn<Status> zzn2) throws RemoteException {
        this.zzhwr.flush();
        try {
            ((zzw) zzalw()).zza((zzs) new zzcg(zzn2));
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zzg(com.google.android.gms.common.api.internal.zzn<TurnBasedMultiplayer.LoadMatchResult> zzn2, String str) throws RemoteException {
        try {
            ((zzw) zzalw()).zzf((zzs) new zzcq(zzn2), str);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zzh(com.google.android.gms.common.api.internal.zzci<OnTurnBasedMatchUpdateReceivedListener> zzci2) {
        try {
            zzg(zzci2);
        } catch (RemoteException e) {
            zzd(e);
        }
    }

    public final void zzh(com.google.android.gms.common.api.internal.zzn<Videos.CaptureCapabilitiesResult> zzn2) throws RemoteException {
        try {
            ((zzw) zzalw()).zzc((zzs) new zzj(zzn2));
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zzh(com.google.android.gms.common.api.internal.zzn<Quests.AcceptQuestResult> zzn2, String str) throws RemoteException {
        this.zzhwr.flush();
        try {
            ((zzw) zzalw()).zzh(new zzbp(zzn2), str);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    /* access modifiers changed from: protected */
    public final String zzhm() {
        return "com.google.android.gms.games.service.START";
    }

    /* access modifiers changed from: protected */
    public final String zzhn() {
        return "com.google.android.gms.games.internal.IGamesService";
    }

    public final void zzhy(String str) throws RemoteException {
        ((zzw) zzalw()).zzic(str);
    }

    public final void zzhz(String str) {
        try {
            zzhy(str);
        } catch (RemoteException e) {
            zzd(e);
        }
    }

    public final void zzi(com.google.android.gms.common.api.internal.zzci<QuestUpdateListener> zzci2) {
        try {
            ((zzw) zzalw()).zzd((zzs) new zzbs(zzci2), this.zzhwy);
        } catch (RemoteException e) {
            zzd(e);
        }
    }

    public final void zzi(com.google.android.gms.common.api.internal.zzn<Videos.CaptureStateResult> zzn2) throws RemoteException {
        try {
            ((zzw) zzalw()).zzd(new zzn(zzn2));
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final void zzi(com.google.android.gms.common.api.internal.zzn<Snapshots.DeleteSnapshotResult> zzn2, String str) throws RemoteException {
        try {
            ((zzw) zzalw()).zzg(new zzci(zzn2), str);
        } catch (SecurityException e) {
            zza(zzn2, e);
        }
    }

    public final Intent zzia(String str) {
        try {
            return ((zzw) zzalw()).zzia(str);
        } catch (RemoteException e) {
            zzd(e);
            return null;
        }
    }

    public final void zzib(String str) {
        try {
            ((zzw) zzalw()).zza(str, this.zzhwv.zziad.zziae, this.zzhwv.zziad.zzauy());
        } catch (RemoteException e) {
            zzd(e);
        }
    }

    public final Intent zzj(String str, int i, int i2) {
        try {
            return ((zzw) zzalw()).zzk(str, i, i2);
        } catch (RemoteException e) {
            zzd(e);
            return null;
        }
    }

    public final void zzj(com.google.android.gms.common.api.internal.zzci<OnRequestReceivedListener> zzci2) {
        try {
            ((zzw) zzalw()).zzc((zzs) new zzbw(zzci2), this.zzhwy);
        } catch (RemoteException e) {
            zzd(e);
        }
    }

    public final void zzk(com.google.android.gms.common.api.internal.zzci<Videos.CaptureOverlayStateListener> zzci2) throws RemoteException {
        ((zzw) zzalw()).zze((zzs) new zzl(zzci2), this.zzhwy);
    }

    public final void zzl(com.google.android.gms.common.api.internal.zzci<Videos.CaptureOverlayStateListener> zzci2) {
        try {
            zzk(zzci2);
        } catch (RemoteException e) {
            zzd(e);
        }
    }

    public final void zzp(String str, int i) {
        this.zzhwr.zzp(str, i);
    }

    public final void zzq(String str, int i) throws RemoteException {
        ((zzw) zzalw()).zzq(str, i);
    }

    public final void zzr(String str, int i) {
        try {
            zzq(str, i);
        } catch (RemoteException e) {
            zzd(e);
        }
    }

    public final void zzs(String str, int i) throws RemoteException {
        ((zzw) zzalw()).zzs(str, i);
    }

    public final void zzt(String str, int i) {
        try {
            zzs(str, i);
        } catch (RemoteException e) {
            zzd(e);
        }
    }

    public final void zzz(View view) {
        this.zzhwv.zzaa(view);
    }
}
