package org.jivesoftware.smack.packet;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.util.StringUtils;

public class Authentication extends IQ {
    private String digest = null;
    private String password = null;
    private String resource = null;
    private String username = null;

    public Authentication() {
        setType(IQ.Type.SET);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public String getDigest() {
        return this.digest;
    }

    public void setDigest(String str, String str2) {
        this.digest = StringUtils.hash(str + str2);
    }

    public void setDigest(String str) {
        this.digest = str;
    }

    public String getResource() {
        return this.resource;
    }

    public void setResource(String str) {
        this.resource = str;
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<query xmlns=\"jabber:iq:auth\">");
        String str = this.username;
        if (str != null) {
            if (str.equals("")) {
                sb.append("<username/>");
            } else {
                sb.append("<username>");
                sb.append(this.username);
                sb.append("</username>");
            }
        }
        String str2 = this.digest;
        if (str2 != null) {
            if (str2.equals("")) {
                sb.append("<digest/>");
            } else {
                sb.append("<digest>");
                sb.append(this.digest);
                sb.append("</digest>");
            }
        }
        String str3 = this.password;
        if (str3 != null && this.digest == null) {
            if (str3.equals("")) {
                sb.append("<password/>");
            } else {
                sb.append("<password>");
                sb.append(StringUtils.escapeForXML(this.password));
                sb.append("</password>");
            }
        }
        String str4 = this.resource;
        if (str4 != null) {
            if (str4.equals("")) {
                sb.append("<resource/>");
            } else {
                sb.append("<resource>");
                sb.append(this.resource);
                sb.append("</resource>");
            }
        }
        sb.append("</query>");
        return sb.toString();
    }
}
