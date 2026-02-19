package org.jivesoftware.smack.packet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Registration extends IQ {
    private Map<String, String> attributes = new HashMap();
    private String instructions = null;
    private boolean registered = false;
    private boolean remove = false;
    private List<String> requiredFields = new ArrayList();

    public String getInstructions() {
        return this.instructions;
    }

    public void setInstructions(String str) {
        this.instructions = str;
    }

    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    public void addRequiredField(String str) {
        this.requiredFields.add(str);
    }

    public List<String> getRequiredFields() {
        return this.requiredFields;
    }

    public void addAttribute(String str, String str2) {
        this.attributes.put(str, str2);
    }

    public void setRegistered(boolean z) {
        this.registered = z;
    }

    public boolean isRegistered() {
        return this.registered;
    }

    public String getField(String str) {
        return this.attributes.get(str);
    }

    public List<String> getFieldNames() {
        return new ArrayList(this.attributes.keySet());
    }

    public void setUsername(String str) {
        this.attributes.put("username", str);
    }

    public void setPassword(String str) {
        this.attributes.put("password", str);
    }

    public void setRemove(boolean z) {
        this.remove = z;
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<query xmlns=\"jabber:iq:register\">");
        if (this.instructions != null && !this.remove) {
            sb.append("<instructions>");
            sb.append(this.instructions);
            sb.append("</instructions>");
        }
        Map<String, String> map = this.attributes;
        if (map != null && map.size() > 0 && !this.remove) {
            for (String next : this.attributes.keySet()) {
                sb.append("<");
                sb.append(next);
                sb.append(">");
                sb.append(this.attributes.get(next));
                sb.append("</");
                sb.append(next);
                sb.append(">");
            }
        } else if (this.remove) {
            sb.append("</remove>");
        }
        sb.append(getExtensionsXML());
        sb.append("</query>");
        return sb.toString();
    }
}
