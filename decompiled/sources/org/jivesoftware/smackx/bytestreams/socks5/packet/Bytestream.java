package org.jivesoftware.smackx.bytestreams.socks5.packet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.PacketExtension;

public class Bytestream extends IQ {
    private Mode mode = Mode.tcp;
    private String sessionID;
    private final List<StreamHost> streamHosts = new ArrayList();
    private Activate toActivate;
    private StreamHostUsed usedHost;

    public Bytestream() {
    }

    public Bytestream(String str) {
        setSessionID(str);
    }

    public void setSessionID(String str) {
        this.sessionID = str;
    }

    public String getSessionID() {
        return this.sessionID;
    }

    public void setMode(Mode mode2) {
        this.mode = mode2;
    }

    public Mode getMode() {
        return this.mode;
    }

    public StreamHost addStreamHost(String str, String str2) {
        return addStreamHost(str, str2, 0);
    }

    public StreamHost addStreamHost(String str, String str2, int i) {
        StreamHost streamHost = new StreamHost(str, str2);
        streamHost.setPort(i);
        addStreamHost(streamHost);
        return streamHost;
    }

    public void addStreamHost(StreamHost streamHost) {
        this.streamHosts.add(streamHost);
    }

    public Collection<StreamHost> getStreamHosts() {
        return Collections.unmodifiableCollection(this.streamHosts);
    }

    public StreamHost getStreamHost(String str) {
        if (str == null) {
            return null;
        }
        for (StreamHost next : this.streamHosts) {
            if (next.getJID().equals(str)) {
                return next;
            }
        }
        return null;
    }

    public int countStreamHosts() {
        return this.streamHosts.size();
    }

    public void setUsedHost(String str) {
        this.usedHost = new StreamHostUsed(str);
    }

    public StreamHostUsed getUsedHost() {
        return this.usedHost;
    }

    public Activate getToActivate() {
        return this.toActivate;
    }

    public void setToActivate(String str) {
        this.toActivate = new Activate(str);
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<query xmlns=\"http://jabber.org/protocol/bytestreams\"");
        if (getType().equals(IQ.Type.SET)) {
            if (getSessionID() != null) {
                sb.append(" sid=\"");
                sb.append(getSessionID());
                sb.append("\"");
            }
            if (getMode() != null) {
                sb.append(" mode = \"");
                sb.append(getMode());
                sb.append("\"");
            }
            sb.append(">");
            if (getToActivate() == null) {
                for (StreamHost xml : getStreamHosts()) {
                    sb.append(xml.toXML());
                }
            } else {
                sb.append(getToActivate().toXML());
            }
        } else if (getType().equals(IQ.Type.RESULT)) {
            sb.append(">");
            if (getUsedHost() != null) {
                sb.append(getUsedHost().toXML());
            } else if (countStreamHosts() > 0) {
                for (StreamHost xml2 : this.streamHosts) {
                    sb.append(xml2.toXML());
                }
            }
        } else if (!getType().equals(IQ.Type.GET)) {
            return null;
        } else {
            sb.append("/>");
            return sb.toString();
        }
        sb.append("</query>");
        return sb.toString();
    }

    public static class StreamHost implements PacketExtension {
        public static String ELEMENTNAME = "streamhost";
        public static String NAMESPACE = "";
        private final String JID;
        private final String addy;
        private int port = 0;

        public StreamHost(String str, String str2) {
            this.JID = str;
            this.addy = str2;
        }

        public String getJID() {
            return this.JID;
        }

        public String getAddress() {
            return this.addy;
        }

        public void setPort(int i) {
            this.port = i;
        }

        public int getPort() {
            return this.port;
        }

        public String getNamespace() {
            return NAMESPACE;
        }

        public String getElementName() {
            return ELEMENTNAME;
        }

        public String toXML() {
            StringBuilder sb = new StringBuilder();
            sb.append("<");
            sb.append(getElementName());
            sb.append(" ");
            sb.append("jid=\"");
            sb.append(getJID());
            sb.append("\" ");
            sb.append("host=\"");
            sb.append(getAddress());
            sb.append("\" ");
            if (getPort() != 0) {
                sb.append("port=\"");
                sb.append(getPort());
                sb.append("\"");
            } else {
                sb.append("zeroconf=\"_jabber.bytestreams\"");
            }
            sb.append("/>");
            return sb.toString();
        }
    }

    public static class StreamHostUsed implements PacketExtension {
        public static String ELEMENTNAME = "streamhost-used";
        private final String JID;
        public String NAMESPACE = "";

        public StreamHostUsed(String str) {
            this.JID = str;
        }

        public String getJID() {
            return this.JID;
        }

        public String getNamespace() {
            return this.NAMESPACE;
        }

        public String getElementName() {
            return ELEMENTNAME;
        }

        public String toXML() {
            return "<" + getElementName() + " " + "jid=\"" + getJID() + "\" " + "/>";
        }
    }

    public static class Activate implements PacketExtension {
        public static String ELEMENTNAME = "activate";
        public String NAMESPACE = "";
        private final String target;

        public Activate(String str) {
            this.target = str;
        }

        public String getTarget() {
            return this.target;
        }

        public String getNamespace() {
            return this.NAMESPACE;
        }

        public String getElementName() {
            return ELEMENTNAME;
        }

        public String toXML() {
            return "<" + getElementName() + ">" + getTarget() + "</" + getElementName() + ">";
        }
    }

    public enum Mode {
        tcp,
        udp;

        public static Mode fromName(String str) {
            try {
                return valueOf(str);
            } catch (Exception unused) {
                return tcp;
            }
        }
    }
}
