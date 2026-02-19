package org.jivesoftware.smackx.muc;

import java.util.Iterator;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.packet.DiscoverInfo;

public class RoomInfo {
    private String description = "";
    private boolean membersOnly;
    private boolean moderated;
    private boolean nonanonymous;
    private int occupantsCount = -1;
    private boolean passwordProtected;
    private boolean persistent;
    private String room;
    private String subject = "";

    RoomInfo(DiscoverInfo discoverInfo) {
        this.room = discoverInfo.getFrom();
        this.membersOnly = discoverInfo.containsFeature("muc_membersonly");
        this.moderated = discoverInfo.containsFeature("muc_moderated");
        this.nonanonymous = discoverInfo.containsFeature("muc_nonanonymous");
        this.passwordProtected = discoverInfo.containsFeature("muc_passwordprotected");
        this.persistent = discoverInfo.containsFeature("muc_persistent");
        Form formFrom = Form.getFormFrom(discoverInfo);
        if (formFrom != null) {
            this.description = formFrom.getField("muc#roominfo_description").getValues().next();
            Iterator<String> values = formFrom.getField("muc#roominfo_subject").getValues();
            if (values.hasNext()) {
                this.subject = values.next();
            } else {
                this.subject = "";
            }
            this.occupantsCount = Integer.parseInt(formFrom.getField("muc#roominfo_occupants").getValues().next());
        }
    }

    public String getRoom() {
        return this.room;
    }

    public String getDescription() {
        return this.description;
    }

    public String getSubject() {
        return this.subject;
    }

    public int getOccupantsCount() {
        return this.occupantsCount;
    }

    public boolean isMembersOnly() {
        return this.membersOnly;
    }

    public boolean isModerated() {
        return this.moderated;
    }

    public boolean isNonanonymous() {
        return this.nonanonymous;
    }

    public boolean isPasswordProtected() {
        return this.passwordProtected;
    }

    public boolean isPersistent() {
        return this.persistent;
    }
}
