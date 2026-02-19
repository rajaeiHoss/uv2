package org.jivesoftware.smackx.workgroup;

import java.util.Map;

public class WorkgroupInvitation {
    protected String groupChatName;
    protected String invitationSender;
    protected String issuingWorkgroupName;
    protected String messageBody;
    protected Map metaData;
    protected String sessionID;
    protected String uniqueID;

    public WorkgroupInvitation(String str, String str2, String str3, String str4, String str5, String str6) {
        this(str, str2, str3, str4, str5, str6, (Map) null);
    }

    public WorkgroupInvitation(String str, String str2, String str3, String str4, String str5, String str6, Map map) {
        this.uniqueID = str;
        this.sessionID = str4;
        this.groupChatName = str2;
        this.issuingWorkgroupName = str3;
        this.messageBody = str5;
        this.invitationSender = str6;
        this.metaData = map;
    }

    public String getUniqueID() {
        return this.uniqueID;
    }

    public String getSessionID() {
        return this.sessionID;
    }

    public String getGroupChatName() {
        return this.groupChatName;
    }

    public String getWorkgroupName() {
        return this.issuingWorkgroupName;
    }

    public String getMessageBody() {
        return this.messageBody;
    }

    public String getInvitationSender() {
        return this.invitationSender;
    }

    public Map getMetaData() {
        return this.metaData;
    }
}
