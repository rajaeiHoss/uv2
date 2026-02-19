package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.zzf;
import com.google.android.gms.games.multiplayer.InvitationBuffer;

public final class LoadMatchesResponse {
    private final InvitationBuffer zzigd;
    private final TurnBasedMatchBuffer zzige;
    private final TurnBasedMatchBuffer zzigf;
    private final TurnBasedMatchBuffer zzigg;

    public LoadMatchesResponse(Bundle bundle) {
        DataHolder zzc = zzc(bundle, 0);
        if (zzc != null) {
            this.zzigd = new InvitationBuffer(zzc);
        } else {
            this.zzigd = null;
        }
        DataHolder zzc2 = zzc(bundle, 1);
        if (zzc2 != null) {
            this.zzige = new TurnBasedMatchBuffer(zzc2);
        } else {
            this.zzige = null;
        }
        DataHolder zzc3 = zzc(bundle, 2);
        if (zzc3 != null) {
            this.zzigf = new TurnBasedMatchBuffer(zzc3);
        } else {
            this.zzigf = null;
        }
        DataHolder zzc4 = zzc(bundle, 3);
        if (zzc4 != null) {
            this.zzigg = new TurnBasedMatchBuffer(zzc4);
        } else {
            this.zzigg = null;
        }
    }

    private static DataHolder zzc(Bundle bundle, int i) {
        String str;
        if (i == 0) {
            str = "TURN_STATUS_INVITED";
        } else if (i == 1) {
            str = "TURN_STATUS_MY_TURN";
        } else if (i == 2) {
            str = "TURN_STATUS_THEIR_TURN";
        } else if (i != 3) {
            StringBuilder sb = new StringBuilder(38);
            sb.append("Unknown match turn status: ");
            sb.append(i);
            zzf.zzw("MatchTurnStatus", sb.toString());
            str = "TURN_STATUS_UNKNOWN";
        } else {
            str = "TURN_STATUS_COMPLETE";
        }
        if (!bundle.containsKey(str)) {
            return null;
        }
        return (DataHolder) bundle.getParcelable(str);
    }

    @Deprecated
    public final void close() {
        release();
    }

    public final TurnBasedMatchBuffer getCompletedMatches() {
        return this.zzigg;
    }

    public final InvitationBuffer getInvitations() {
        return this.zzigd;
    }

    public final TurnBasedMatchBuffer getMyTurnMatches() {
        return this.zzige;
    }

    public final TurnBasedMatchBuffer getTheirTurnMatches() {
        return this.zzigf;
    }

    public final boolean hasData() {
        InvitationBuffer invitationBuffer = this.zzigd;
        if (invitationBuffer != null && invitationBuffer.getCount() > 0) {
            return true;
        }
        TurnBasedMatchBuffer turnBasedMatchBuffer = this.zzige;
        if (turnBasedMatchBuffer != null && turnBasedMatchBuffer.getCount() > 0) {
            return true;
        }
        TurnBasedMatchBuffer turnBasedMatchBuffer2 = this.zzigf;
        if (turnBasedMatchBuffer2 != null && turnBasedMatchBuffer2.getCount() > 0) {
            return true;
        }
        TurnBasedMatchBuffer turnBasedMatchBuffer3 = this.zzigg;
        return turnBasedMatchBuffer3 != null && turnBasedMatchBuffer3.getCount() > 0;
    }

    public final void release() {
        InvitationBuffer invitationBuffer = this.zzigd;
        if (invitationBuffer != null) {
            invitationBuffer.release();
        }
        TurnBasedMatchBuffer turnBasedMatchBuffer = this.zzige;
        if (turnBasedMatchBuffer != null) {
            turnBasedMatchBuffer.release();
        }
        TurnBasedMatchBuffer turnBasedMatchBuffer2 = this.zzigf;
        if (turnBasedMatchBuffer2 != null) {
            turnBasedMatchBuffer2.release();
        }
        TurnBasedMatchBuffer turnBasedMatchBuffer3 = this.zzigg;
        if (turnBasedMatchBuffer3 != null) {
            turnBasedMatchBuffer3.release();
        }
    }
}
