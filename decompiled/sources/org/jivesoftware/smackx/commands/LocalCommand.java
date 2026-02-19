package org.jivesoftware.smackx.commands;

import org.jivesoftware.smackx.packet.AdHocCommandData;

public abstract class LocalCommand extends AdHocCommand {
    private long creationDate = System.currentTimeMillis();
    private int currenStage = -1;
    private String ownerJID;
    private String sessionID;

    public abstract boolean hasPermission(String str);

    public abstract boolean isLastStage();

    public void setSessionID(String str) {
        this.sessionID = str;
        getData().setSessionID(str);
    }

    public String getSessionID() {
        return this.sessionID;
    }

    public void setOwnerJID(String str) {
        this.ownerJID = str;
    }

    public String getOwnerJID() {
        return this.ownerJID;
    }

    public long getCreationDate() {
        return this.creationDate;
    }

    public int getCurrentStage() {
        return this.currenStage;
    }

    /* access modifiers changed from: package-private */
    public void setData(AdHocCommandData adHocCommandData) {
        adHocCommandData.setSessionID(this.sessionID);
        super.setData(adHocCommandData);
    }

    /* access modifiers changed from: package-private */
    public void incrementStage() {
        this.currenStage++;
    }

    /* access modifiers changed from: package-private */
    public void decrementStage() {
        this.currenStage--;
    }
}
