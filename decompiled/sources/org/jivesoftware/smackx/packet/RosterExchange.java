package org.jivesoftware.smackx.packet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterGroup;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smackx.GroupChatInvitation;
import org.jivesoftware.smackx.RemoteRosterEntry;

public class RosterExchange implements PacketExtension {
    private List remoteRosterEntries = new ArrayList();

    public String getElementName() {
        return GroupChatInvitation.ELEMENT_NAME;
    }

    public String getNamespace() {
        return "jabber:x:roster";
    }

    public RosterExchange() {
    }

    public RosterExchange(Roster roster) {
        for (RosterEntry addRosterEntry : roster.getEntries()) {
            addRosterEntry(addRosterEntry);
        }
    }

    public void addRosterEntry(RosterEntry rosterEntry) {
        ArrayList arrayList = new ArrayList();
        for (RosterGroup name : rosterEntry.getGroups()) {
            arrayList.add(name.getName());
        }
        addRosterEntry(new RemoteRosterEntry(rosterEntry.getUser(), rosterEntry.getName(), (String[]) arrayList.toArray(new String[arrayList.size()])));
    }

    public void addRosterEntry(RemoteRosterEntry remoteRosterEntry) {
        synchronized (this.remoteRosterEntries) {
            this.remoteRosterEntries.add(remoteRosterEntry);
        }
    }

    public Iterator getRosterEntries() {
        Iterator it;
        synchronized (this.remoteRosterEntries) {
            it = Collections.unmodifiableList(new ArrayList(this.remoteRosterEntries)).iterator();
        }
        return it;
    }

    public int getEntryCount() {
        return this.remoteRosterEntries.size();
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(getElementName());
        sb.append(" xmlns=\"");
        sb.append(getNamespace());
        sb.append("\">");
        Iterator rosterEntries = getRosterEntries();
        while (rosterEntries.hasNext()) {
            sb.append(((RemoteRosterEntry) rosterEntries.next()).toXML());
        }
        sb.append("</");
        sb.append(getElementName());
        sb.append(">");
        return sb.toString();
    }
}
