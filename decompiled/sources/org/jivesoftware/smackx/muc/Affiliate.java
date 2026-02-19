package org.jivesoftware.smackx.muc;

import org.jivesoftware.smackx.packet.MUCAdmin;
import org.jivesoftware.smackx.packet.MUCOwner;

public class Affiliate {
    private String affiliation;
    private String jid;
    private String nick;
    private String role;

    Affiliate(MUCOwner.Item item) {
        this.jid = item.getJid();
        this.affiliation = item.getAffiliation();
        this.role = item.getRole();
        this.nick = item.getNick();
    }

    Affiliate(MUCAdmin.Item item) {
        this.jid = item.getJid();
        this.affiliation = item.getAffiliation();
        this.role = item.getRole();
        this.nick = item.getNick();
    }

    public String getJid() {
        return this.jid;
    }

    public String getAffiliation() {
        return this.affiliation;
    }

    public String getRole() {
        return this.role;
    }

    public String getNick() {
        return this.nick;
    }
}
