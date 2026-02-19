package org.jivesoftware.smackx.workgroup.agent;

public class TransferRequest extends OfferContent {
    private String inviter;
    private String reason;
    private String room;

    /* access modifiers changed from: package-private */
    public boolean isInvitation() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isTransfer() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean isUserRequest() {
        return false;
    }

    public TransferRequest(String str, String str2, String str3) {
        this.inviter = str;
        this.room = str2;
        this.reason = str3;
    }

    public String getInviter() {
        return this.inviter;
    }

    public String getRoom() {
        return this.room;
    }

    public String getReason() {
        return this.reason;
    }
}
