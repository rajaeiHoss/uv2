package org.jivesoftware.smack.packet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Privacy extends IQ {
    private String activeName;
    private boolean declineActiveList = false;
    private boolean declineDefaultList = false;
    private String defaultName;
    private Map<String, List<PrivacyItem>> itemLists = new HashMap();

    public List setPrivacyList(String str, List<PrivacyItem> list) {
        getItemLists().put(str, list);
        return list;
    }

    public List<PrivacyItem> setActivePrivacyList() {
        setActiveName(getDefaultName());
        return getItemLists().get(getActiveName());
    }

    public void deletePrivacyList(String str) {
        getItemLists().remove(str);
        if (getDefaultName() != null && str.equals(getDefaultName())) {
            setDefaultName((String) null);
        }
    }

    public List<PrivacyItem> getActivePrivacyList() {
        if (getActiveName() == null) {
            return null;
        }
        return getItemLists().get(getActiveName());
    }

    public List<PrivacyItem> getDefaultPrivacyList() {
        if (getDefaultName() == null) {
            return null;
        }
        return getItemLists().get(getDefaultName());
    }

    public List<PrivacyItem> getPrivacyList(String str) {
        return getItemLists().get(str);
    }

    public PrivacyItem getItem(String str, int i) {
        Iterator<PrivacyItem> it = getPrivacyList(str).iterator();
        PrivacyItem privacyItem = null;
        while (privacyItem == null && it.hasNext()) {
            PrivacyItem next = it.next();
            if (next.getOrder() == i) {
                privacyItem = next;
            }
        }
        return privacyItem;
    }

    public boolean changeDefaultList(String str) {
        if (!getItemLists().containsKey(str)) {
            return false;
        }
        setDefaultName(str);
        return true;
    }

    public void deleteList(String str) {
        getItemLists().remove(str);
    }

    public String getActiveName() {
        return this.activeName;
    }

    public void setActiveName(String str) {
        this.activeName = str;
    }

    public String getDefaultName() {
        return this.defaultName;
    }

    public void setDefaultName(String str) {
        this.defaultName = str;
    }

    public Map<String, List<PrivacyItem>> getItemLists() {
        return this.itemLists;
    }

    public boolean isDeclineActiveList() {
        return this.declineActiveList;
    }

    public void setDeclineActiveList(boolean z) {
        this.declineActiveList = z;
    }

    public boolean isDeclineDefaultList() {
        return this.declineDefaultList;
    }

    public void setDeclineDefaultList(boolean z) {
        this.declineDefaultList = z;
    }

    public Set<String> getPrivacyListNames() {
        return this.itemLists.keySet();
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<query xmlns=\"jabber:iq:privacy\">");
        if (isDeclineActiveList()) {
            sb.append("<active/>");
        } else if (getActiveName() != null) {
            sb.append("<active name=\"");
            sb.append(getActiveName());
            sb.append("\"/>");
        }
        if (isDeclineDefaultList()) {
            sb.append("<default/>");
        } else if (getDefaultName() != null) {
            sb.append("<default name=\"");
            sb.append(getDefaultName());
            sb.append("\"/>");
        }
        for (Map.Entry next : getItemLists().entrySet()) {
            String str = (String) next.getKey();
            List<PrivacyItem> list = (List) next.getValue();
            if (list.isEmpty()) {
                sb.append("<list name=\"");
                sb.append(str);
                sb.append("\"/>");
            } else {
                sb.append("<list name=\"");
                sb.append(str);
                sb.append("\">");
            }
            for (PrivacyItem xml : list) {
                sb.append(xml.toXML());
            }
            if (!list.isEmpty()) {
                sb.append("</list>");
            }
        }
        sb.append(getExtensionsXML());
        sb.append("</query>");
        return sb.toString();
    }
}
