package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.api.zzad;
import com.google.android.gms.games.internal.api.zzah;
import com.google.android.gms.games.internal.api.zzax;
import com.google.android.gms.games.internal.api.zzay;
import com.google.android.gms.games.internal.api.zzaz;
import com.google.android.gms.games.internal.api.zzbj;
import com.google.android.gms.games.internal.api.zzbu;
import com.google.android.gms.games.internal.api.zzbv;
import com.google.android.gms.games.internal.api.zzcd;
import com.google.android.gms.games.internal.api.zzcr;
import com.google.android.gms.games.internal.api.zzcs;
import com.google.android.gms.games.internal.api.zzcw;
import com.google.android.gms.games.internal.api.zzdt;
import com.google.android.gms.games.internal.api.zzo;
import com.google.android.gms.games.internal.api.zzq;
import com.google.android.gms.games.internal.api.zzy;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.games.multiplayer.Multiplayer;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.games.quest.Quests;
import com.google.android.gms.games.request.Requests;
import com.google.android.gms.games.snapshot.Snapshots;
import com.google.android.gms.games.stats.Stats;
import com.google.android.gms.games.video.Videos;
import com.google.android.gms.internal.zzccr;
import com.google.android.gms.internal.zzccz;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Games {
    @Deprecated
    public static final Api<GamesOptions> API;
    @Deprecated
    public static final Achievements Achievements = new com.google.android.gms.games.internal.api.zza();
    public static final String EXTRA_PLAYER_IDS = "players";
    public static final String EXTRA_STATUS = "status";
    @Deprecated
    public static final Events Events = new zzq();
    @Deprecated
    public static final GamesMetadata GamesMetadata = new zzy();
    @Deprecated
    public static final Invitations Invitations = new zzad();
    @Deprecated
    public static final Leaderboards Leaderboards = new zzah();
    @Deprecated
    public static final Notifications Notifications = new zzay();
    @Deprecated
    public static final Players Players = new zzaz();
    @Deprecated
    public static final Quests Quests = new zzbj();
    @Deprecated
    public static final RealTimeMultiplayer RealTimeMultiplayer = new zzbu();
    @Deprecated
    public static final Requests Requests = new zzbv();
    public static final Scope SCOPE_GAMES = new Scope(Scopes.GAMES);
    public static final Scope SCOPE_GAMES_LITE = new Scope("https://www.googleapis.com/auth/games_lite");
    @Deprecated
    public static final Snapshots Snapshots = new zzcd();
    @Deprecated
    public static final Stats Stats = new zzcs();
    @Deprecated
    public static final TurnBasedMultiplayer TurnBasedMultiplayer = new zzcw();
    @Deprecated
    public static final Videos Videos = new zzdt();
    static final Api.zzf<GamesClientImpl> zzegu;
    private static final Api.zza<GamesClientImpl, GamesOptions> zzegv;
    private static final Api.zza<GamesClientImpl, GamesOptions> zzhrs;
    public static final Scope zzhrt = new Scope("https://www.googleapis.com/auth/games.firstparty");
    private static Api<GamesOptions> zzhru;
    private static zzccr zzhrv = new zzo();
    private static Multiplayer zzhrw = new zzax();
    private static zzccz zzhrx = new zzcr();

    @Deprecated
    public static final class GamesOptions implements GoogleSignInOptionsExtension, Api.ApiOptions.HasGoogleSignInAccountOptions, Api.ApiOptions.Optional {
        public final boolean zzhrz;
        public final boolean zzhsa;
        public final int zzhsb;
        public final boolean zzhsc;
        public final int zzhsd;
        public final String zzhse;
        public final ArrayList<String> zzhsf;
        public final boolean zzhsg;
        public final boolean zzhsh;
        public final boolean zzhsi;
        public final GoogleSignInAccount zzhsj;

        @Deprecated
        public static final class Builder {
            private boolean zzhrz;
            private boolean zzhsa;
            private int zzhsb;
            private boolean zzhsc;
            private int zzhsd;
            private String zzhse;
            private ArrayList<String> zzhsf;
            private boolean zzhsg;
            private boolean zzhsh;
            private boolean zzhsi;
            GoogleSignInAccount zzhsj;

            private Builder() {
                this.zzhrz = false;
                this.zzhsa = true;
                this.zzhsb = 17;
                this.zzhsc = false;
                this.zzhsd = 4368;
                this.zzhse = null;
                this.zzhsf = new ArrayList<>();
                this.zzhsg = false;
                this.zzhsh = false;
                this.zzhsi = false;
                this.zzhsj = null;
            }

            private Builder(GamesOptions gamesOptions) {
                this.zzhrz = false;
                this.zzhsa = true;
                this.zzhsb = 17;
                this.zzhsc = false;
                this.zzhsd = 4368;
                this.zzhse = null;
                this.zzhsf = new ArrayList<>();
                this.zzhsg = false;
                this.zzhsh = false;
                this.zzhsi = false;
                this.zzhsj = null;
                if (gamesOptions != null) {
                    this.zzhrz = gamesOptions.zzhrz;
                    this.zzhsa = gamesOptions.zzhsa;
                    this.zzhsb = gamesOptions.zzhsb;
                    this.zzhsc = gamesOptions.zzhsc;
                    this.zzhsd = gamesOptions.zzhsd;
                    this.zzhse = gamesOptions.zzhse;
                    this.zzhsf = gamesOptions.zzhsf;
                    this.zzhsg = gamesOptions.zzhsg;
                    this.zzhsh = gamesOptions.zzhsh;
                    this.zzhsi = gamesOptions.zzhsi;
                    this.zzhsj = gamesOptions.zzhsj;
                }
            }

            /* synthetic */ Builder(GamesOptions gamesOptions, zzi zzi) {
                this((GamesOptions) null);
            }

            /* synthetic */ Builder(zzi zzi) {
                this();
            }

            public final GamesOptions build() {
                return new GamesOptions(this.zzhrz, this.zzhsa, this.zzhsb, this.zzhsc, this.zzhsd, this.zzhse, this.zzhsf, this.zzhsg, this.zzhsh, this.zzhsi, this.zzhsj, (zzi) null);
            }

            public final Builder setSdkVariant(int i) {
                this.zzhsd = i;
                return this;
            }

            public final Builder setShowConnectingPopup(boolean z) {
                this.zzhsa = z;
                this.zzhsb = 17;
                return this;
            }

            public final Builder setShowConnectingPopup(boolean z, int i) {
                this.zzhsa = z;
                this.zzhsb = i;
                return this;
            }
        }

        private GamesOptions(boolean z, boolean z2, int i, boolean z3, int i2, String str, ArrayList<String> arrayList, boolean z4, boolean z5, boolean z6, GoogleSignInAccount googleSignInAccount) {
            this.zzhrz = z;
            this.zzhsa = z2;
            this.zzhsb = i;
            this.zzhsc = z3;
            this.zzhsd = i2;
            this.zzhse = str;
            this.zzhsf = arrayList;
            this.zzhsg = z4;
            this.zzhsh = z5;
            this.zzhsi = z6;
            this.zzhsj = googleSignInAccount;
        }

        /* synthetic */ GamesOptions(boolean z, boolean z2, int i, boolean z3, int i2, String str, ArrayList arrayList, boolean z4, boolean z5, boolean z6, GoogleSignInAccount googleSignInAccount, zzi zzi) {
            this(z, z2, i, z3, i2, str, arrayList, z4, z5, z6, googleSignInAccount);
        }

        public static Builder builder() {
            return new Builder((zzi) null);
        }

        public final boolean equals(Object obj) {
            String str;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof GamesOptions)) {
                return false;
            }
            GamesOptions gamesOptions = (GamesOptions) obj;
            if (this.zzhrz == gamesOptions.zzhrz && this.zzhsa == gamesOptions.zzhsa && this.zzhsb == gamesOptions.zzhsb && this.zzhsc == gamesOptions.zzhsc && this.zzhsd == gamesOptions.zzhsd && ((str = this.zzhse) != null ? str.equals(gamesOptions.zzhse) : gamesOptions.zzhse == null) && this.zzhsf.equals(gamesOptions.zzhsf) && this.zzhsg == gamesOptions.zzhsg && this.zzhsh == gamesOptions.zzhsh && this.zzhsi == gamesOptions.zzhsi) {
                GoogleSignInAccount googleSignInAccount = this.zzhsj;
                GoogleSignInAccount googleSignInAccount2 = gamesOptions.zzhsj;
                return googleSignInAccount != null ? googleSignInAccount.equals(googleSignInAccount2) : googleSignInAccount2 == null;
            }
            return false;
        }

        public final int getExtensionType() {
            return 1;
        }

        public final GoogleSignInAccount getGoogleSignInAccount() {
            return this.zzhsj;
        }

        public final List<Scope> getImpliedScopes() {
            return Collections.singletonList(this.zzhsg ? Games.SCOPE_GAMES : Games.SCOPE_GAMES_LITE);
        }

        public final int hashCode() {
            int i = ((((((((((this.zzhrz ? 1 : 0) + 1) * 31) + (this.zzhsa ? 1 : 0)) * 31) + this.zzhsb) * 31) + (this.zzhsc ? 1 : 0)) * 31) + this.zzhsd) * 31;
            String str = this.zzhse;
            int i2 = 0;
            int hashCode = (((((((((i + (str == null ? 0 : str.hashCode())) * 31) + this.zzhsf.hashCode()) * 31) + (this.zzhsg ? 1 : 0)) * 31) + (this.zzhsh ? 1 : 0)) * 31) + (this.zzhsi ? 1 : 0)) * 31;
            GoogleSignInAccount googleSignInAccount = this.zzhsj;
            if (googleSignInAccount != null) {
                i2 = googleSignInAccount.hashCode();
            }
            return hashCode + i2;
        }

        public final Bundle toBundle() {
            return zzasu();
        }

        public final Bundle zzasu() {
            Bundle bundle = new Bundle();
            bundle.putBoolean("com.google.android.gms.games.key.isHeadless", this.zzhrz);
            bundle.putBoolean("com.google.android.gms.games.key.showConnectingPopup", this.zzhsa);
            bundle.putInt("com.google.android.gms.games.key.connectingPopupGravity", this.zzhsb);
            bundle.putBoolean("com.google.android.gms.games.key.retryingSignIn", this.zzhsc);
            bundle.putInt("com.google.android.gms.games.key.sdkVariant", this.zzhsd);
            bundle.putString("com.google.android.gms.games.key.forceResolveAccountKey", this.zzhse);
            bundle.putStringArrayList("com.google.android.gms.games.key.proxyApis", this.zzhsf);
            bundle.putBoolean("com.google.android.gms.games.key.requireGooglePlus", this.zzhsg);
            bundle.putBoolean("com.google.android.gms.games.key.unauthenticated", this.zzhsh);
            bundle.putBoolean("com.google.android.gms.games.key.skipWelcomePopup", this.zzhsi);
            return bundle;
        }
    }

    @Deprecated
    public interface GetServerAuthCodeResult extends Result {
        String getCode();
    }

    public static abstract class zza<R extends Result> extends zzm<R, GamesClientImpl> {
        public zza(GoogleApiClient googleApiClient) {
            super(Games.zzegu, googleApiClient);
        }
    }

    static abstract class zzb extends Api.zza<GamesClientImpl, GamesOptions> {
        private zzb() {
        }

        /* synthetic */ zzb(zzi zzi) {
            this();
        }

        public final int getPriority() {
            return 1;
        }

        public final GamesClientImpl zza(Context context, Looper looper, zzr zzr, GamesOptions gamesOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            if (gamesOptions == null) {
                gamesOptions = new GamesOptions.Builder((zzi) null).build();
            }
            return new GamesClientImpl(context, looper, zzr, gamesOptions, connectionCallbacks, onConnectionFailedListener);
        }
    }

    static abstract class zzc extends zza<GetServerAuthCodeResult> {
        private zzc(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* synthetic */ zzc(GoogleApiClient googleApiClient, zzi zzi) {
            this(googleApiClient);
        }

        public final GetServerAuthCodeResult zzb(Status status) {
            return new com.google.android.gms.games.zzm(this, status);
        }
    }

    static abstract class zzd extends zza<Status> {
        private zzd(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* synthetic */ zzd(GoogleApiClient googleApiClient, zzi zzi) {
            this(googleApiClient);
        }

        public final Status zzb(Status status) {
            return status;
        }
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [com.google.android.gms.internal.zzccr, com.google.android.gms.games.internal.api.zzo] */
    /* JADX WARNING: type inference failed for: r0v17, types: [com.google.android.gms.games.internal.api.zzcr, com.google.android.gms.internal.zzccz] */
    static {
        Api.zzf<GamesClientImpl> zzf = new Api.zzf<>();
        zzegu = zzf;
        zzi zzi = new zzi();
        zzegv = zzi;
        zzj zzj = new zzj();
        zzhrs = zzj;
        API = new Api<>("Games.API", zzi, zzf);
        zzhru = new Api<>("Games.API_1P", zzj, zzf);
    }

    private Games() {
    }

    public static AchievementsClient getAchievementsClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new AchievementsClient(activity, zzd(googleSignInAccount));
    }

    public static AchievementsClient getAchievementsClient(Context context, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new AchievementsClient(context, zzd(googleSignInAccount));
    }

    @Deprecated
    public static String getAppId(GoogleApiClient googleApiClient) {
        return zza(googleApiClient, true).zzaub();
    }

    @Deprecated
    public static String getCurrentAccountName(GoogleApiClient googleApiClient) {
        return zza(googleApiClient, true).zzatf();
    }

    public static EventsClient getEventsClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new EventsClient(activity, zzd(googleSignInAccount));
    }

    public static EventsClient getEventsClient(Context context, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new EventsClient(context, zzd(googleSignInAccount));
    }

    public static GamesClient getGamesClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new GamesClient(activity, zzd(googleSignInAccount));
    }

    public static GamesClient getGamesClient(Context context, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new GamesClient(context, zzd(googleSignInAccount));
    }

    public static GamesMetadataClient getGamesMetadataClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new GamesMetadataClient(activity, zzd(googleSignInAccount));
    }

    public static GamesMetadataClient getGamesMetadataClient(Context context, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new GamesMetadataClient(context, zzd(googleSignInAccount));
    }

    @Deprecated
    public static PendingResult<GetServerAuthCodeResult> getGamesServerAuthCode(GoogleApiClient googleApiClient, String str) {
        zzbq.zzh(str, "Please provide a valid serverClientId");
        return googleApiClient.zze(new zzk(googleApiClient, str));
    }

    public static InvitationsClient getInvitationsClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new InvitationsClient(activity, zzd(googleSignInAccount));
    }

    public static InvitationsClient getInvitationsClient(Context context, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new InvitationsClient(context, zzd(googleSignInAccount));
    }

    public static LeaderboardsClient getLeaderboardsClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new LeaderboardsClient(activity, zzd(googleSignInAccount));
    }

    public static LeaderboardsClient getLeaderboardsClient(Context context, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new LeaderboardsClient(context, zzd(googleSignInAccount));
    }

    public static NotificationsClient getNotificationsClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new NotificationsClient(activity, zzd(googleSignInAccount));
    }

    public static NotificationsClient getNotificationsClient(Context context, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new NotificationsClient(context, zzd(googleSignInAccount));
    }

    public static PlayerStatsClient getPlayerStatsClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new PlayerStatsClient(activity, zzd(googleSignInAccount));
    }

    public static PlayerStatsClient getPlayerStatsClient(Context context, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new PlayerStatsClient(context, zzd(googleSignInAccount));
    }

    public static PlayersClient getPlayersClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new PlayersClient(activity, zzd(googleSignInAccount));
    }

    public static PlayersClient getPlayersClient(Context context, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new PlayersClient(context, zzd(googleSignInAccount));
    }

    public static RealTimeMultiplayerClient getRealTimeMultiplayerClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new RealTimeMultiplayerClient(activity, zzd(googleSignInAccount));
    }

    public static RealTimeMultiplayerClient getRealTimeMultiplayerClient(Context context, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new RealTimeMultiplayerClient(context, zzd(googleSignInAccount));
    }

    @Deprecated
    public static int getSdkVariant(GoogleApiClient googleApiClient) {
        return zza(googleApiClient, true).zzaua();
    }

    @Deprecated
    public static Intent getSettingsIntent(GoogleApiClient googleApiClient) {
        return zza(googleApiClient, true).zzaty();
    }

    public static SnapshotsClient getSnapshotsClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new SnapshotsClient(activity, zzd(googleSignInAccount));
    }

    public static SnapshotsClient getSnapshotsClient(Context context, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new SnapshotsClient(context, zzd(googleSignInAccount));
    }

    public static TurnBasedMultiplayerClient getTurnBasedMultiplayerClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new TurnBasedMultiplayerClient(activity, zzd(googleSignInAccount));
    }

    public static TurnBasedMultiplayerClient getTurnBasedMultiplayerClient(Context context, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new TurnBasedMultiplayerClient(context, zzd(googleSignInAccount));
    }

    public static VideosClient getVideosClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new VideosClient(activity, zzd(googleSignInAccount));
    }

    public static VideosClient getVideosClient(Context context, GoogleSignInAccount googleSignInAccount) {
        zzbq.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new VideosClient(context, zzd(googleSignInAccount));
    }

    @Deprecated
    public static void setGravityForPopups(GoogleApiClient googleApiClient, int i) {
        GamesClientImpl zza2 = zza(googleApiClient, false);
        if (zza2 != null) {
            zza2.zzds(i);
        }
    }

    @Deprecated
    public static void setViewForPopups(GoogleApiClient googleApiClient, View view) {
        zzbq.checkNotNull(view);
        GamesClientImpl zza2 = zza(googleApiClient, false);
        if (zza2 != null) {
            zza2.zzz(view);
        }
    }

    @Deprecated
    public static PendingResult<Status> signOut(GoogleApiClient googleApiClient) {
        return googleApiClient.zze(new zzl(googleApiClient));
    }

    public static GamesClientImpl zza(GoogleApiClient googleApiClient, boolean z) {
        zzbq.checkArgument(googleApiClient != null, "GoogleApiClient parameter is required.");
        zzbq.zza(googleApiClient.isConnected(), (Object) "GoogleApiClient must be connected.");
        return zzb(googleApiClient, z);
    }

    public static GamesClientImpl zzb(GoogleApiClient googleApiClient, boolean z) {
        Api<GamesOptions> api = API;
        zzbq.zza(googleApiClient.zza((Api<?>) api), (Object) "GoogleApiClient is not configured to use the Games Api. Pass Games.API into GoogleApiClient.Builder#addApi() to use this feature.");
        boolean hasConnectedApi = googleApiClient.hasConnectedApi(api);
        if (z && !hasConnectedApi) {
            throw new IllegalStateException("GoogleApiClient has an optional Games.API and is not connected to Games. Use GoogleApiClient.hasConnectedApi(Games.API) to guard this call.");
        } else if (hasConnectedApi) {
            return (GamesClientImpl) googleApiClient.zza(zzegu);
        } else {
            return null;
        }
    }

    private static GamesOptions zzd(GoogleSignInAccount googleSignInAccount) {
        GamesOptions.Builder builder = new GamesOptions.Builder((GamesOptions) null, (zzi) null);
        builder.zzhsj = googleSignInAccount;
        return builder.setSdkVariant(1052947).build();
    }

    public static GamesClientImpl zzg(GoogleApiClient googleApiClient) {
        return zza(googleApiClient, true);
    }
}
