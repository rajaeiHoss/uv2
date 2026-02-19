package org.jivesoftware.smackx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RemoteRosterEntry {
    private final List<String> groupNames;
    private String name;
    private String user;

    public RemoteRosterEntry(String str, String str2, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        this.groupNames = arrayList;
        this.user = str;
        this.name = str2;
        if (strArr != null) {
            arrayList.addAll(Arrays.asList(strArr));
        }
    }

    public String getUser() {
        return this.user;
    }

    public String getName() {
        return this.name;
    }

    public Iterator<String> getGroupNames() {
        Iterator<String> it;
        synchronized (this.groupNames) {
            it = Collections.unmodifiableList(this.groupNames).iterator();
        }
        return it;
    }

    public String[] getGroupArrayNames() {
        String[] strArr;
        synchronized (this.groupNames) {
            strArr = (String[]) Collections.unmodifiableList(this.groupNames).toArray(new String[this.groupNames.size()]);
        }
        return strArr;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<item jid=\"");
        sb.append(this.user);
        sb.append("\"");
        if (this.name != null) {
            sb.append(" name=\"");
            sb.append(this.name);
            sb.append("\"");
        }
        sb.append(">");
        synchronized (this.groupNames) {
            for (String append : this.groupNames) {
                sb.append("<group>");
                sb.append(append);
                sb.append("</group>");
            }
        }
        sb.append("</item>");
        return sb.toString();
    }
}
