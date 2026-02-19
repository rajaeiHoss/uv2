package com.kenai.jbosh;

final class CMSessionParams {
    private final AttrAccept accept;
    private final AttrAck ack;
    private final boolean ackingRequests;
    private final AttrCharsets charsets;
    private final AttrHold hold;
    private final AttrInactivity inactivity;
    private final AttrMaxPause maxPause;
    private final AttrPolling polling;
    private final AttrRequests requests;
    private final AttrSessionID sid;
    private final AttrVersion ver;
    private final AttrWait wait;

    private CMSessionParams(AttrSessionID attrSessionID, AttrWait attrWait, AttrVersion attrVersion, AttrPolling attrPolling, AttrInactivity attrInactivity, AttrRequests attrRequests, AttrHold attrHold, AttrAccept attrAccept, AttrMaxPause attrMaxPause, AttrAck attrAck, AttrCharsets attrCharsets, boolean z) {
        this.sid = attrSessionID;
        this.wait = attrWait;
        this.ver = attrVersion;
        this.polling = attrPolling;
        this.inactivity = attrInactivity;
        this.requests = attrRequests;
        this.hold = attrHold;
        this.accept = attrAccept;
        this.maxPause = attrMaxPause;
        this.ack = attrAck;
        this.charsets = attrCharsets;
        this.ackingRequests = z;
    }

    static CMSessionParams fromSessionInit(AbstractBody abstractBody, AbstractBody abstractBody2) throws BOSHException {
        AttrAck createFromString = AttrAck.createFromString(abstractBody2.getAttribute(Attributes.ACK));
        return new CMSessionParams(AttrSessionID.createFromString(getRequiredAttribute(abstractBody2, Attributes.SID)), AttrWait.createFromString(getRequiredAttribute(abstractBody2, Attributes.WAIT)), AttrVersion.createFromString(abstractBody2.getAttribute(Attributes.VER)), AttrPolling.createFromString(abstractBody2.getAttribute(Attributes.POLLING)), AttrInactivity.createFromString(abstractBody2.getAttribute(Attributes.INACTIVITY)), AttrRequests.createFromString(abstractBody2.getAttribute(Attributes.REQUESTS)), AttrHold.createFromString(abstractBody2.getAttribute(Attributes.HOLD)), AttrAccept.createFromString(abstractBody2.getAttribute(Attributes.ACCEPT)), AttrMaxPause.createFromString(abstractBody2.getAttribute(Attributes.MAXPAUSE)), createFromString, AttrCharsets.createFromString(abstractBody2.getAttribute(Attributes.CHARSETS)), createFromString != null && ((String) createFromString.getValue()).equals(abstractBody.getAttribute(Attributes.RID)));
    }

    private static String getRequiredAttribute(AbstractBody abstractBody, BodyQName bodyQName) throws BOSHException {
        String attribute = abstractBody.getAttribute(bodyQName);
        if (attribute != null) {
            return attribute;
        }
        throw new BOSHException("Connection Manager session creation response did not include required '" + bodyQName.getLocalPart() + "' attribute");
    }

    /* access modifiers changed from: package-private */
    public AttrSessionID getSessionID() {
        return this.sid;
    }

    /* access modifiers changed from: package-private */
    public AttrWait getWait() {
        return this.wait;
    }

    /* access modifiers changed from: package-private */
    public AttrVersion getVersion() {
        return this.ver;
    }

    /* access modifiers changed from: package-private */
    public AttrPolling getPollingInterval() {
        return this.polling;
    }

    /* access modifiers changed from: package-private */
    public AttrInactivity getInactivityPeriod() {
        return this.inactivity;
    }

    /* access modifiers changed from: package-private */
    public AttrRequests getRequests() {
        return this.requests;
    }

    /* access modifiers changed from: package-private */
    public AttrHold getHold() {
        return this.hold;
    }

    /* access modifiers changed from: package-private */
    public AttrAccept getAccept() {
        return this.accept;
    }

    /* access modifiers changed from: package-private */
    public AttrMaxPause getMaxPause() {
        return this.maxPause;
    }

    /* access modifiers changed from: package-private */
    public AttrAck getAck() {
        return this.ack;
    }

    /* access modifiers changed from: package-private */
    public AttrCharsets getCharsets() {
        return this.charsets;
    }

    /* access modifiers changed from: package-private */
    public boolean isAckingRequests() {
        return this.ackingRequests;
    }
}
