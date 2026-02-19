package org.jivesoftware.smackx.packet;

import java.util.Date;
import java.util.Objects;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.util.StringUtils;

public class StreamInitiation extends IQ {
    private Feature featureNegotiation;
    private File file;
    private String id;
    private String mimeType;

    public void setSesssionID(String str) {
        this.id = str;
    }

    public String getSessionID() {
        return this.id;
    }

    public void setMimeType(String str) {
        this.mimeType = str;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public void setFile(File file2) {
        this.file = file2;
    }

    public File getFile() {
        return this.file;
    }

    public void setFeatureNegotiationForm(DataForm dataForm) {
        this.featureNegotiation = new Feature(dataForm);
    }

    public DataForm getFeatureNegotiationForm() {
        return this.featureNegotiation.getData();
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        if (getType().equals(IQ.Type.SET)) {
            sb.append("<si xmlns=\"http://jabber.org/protocol/si\" ");
            if (getSessionID() != null) {
                sb.append("id=\"");
                sb.append(getSessionID());
                sb.append("\" ");
            }
            if (getMimeType() != null) {
                sb.append("mime-type=\"");
                sb.append(getMimeType());
                sb.append("\" ");
            }
            sb.append("profile=\"http://jabber.org/protocol/si/profile/file-transfer\">");
            String xml = this.file.toXML();
            if (xml != null) {
                sb.append(xml);
            }
        } else if (getType().equals(IQ.Type.RESULT)) {
            sb.append("<si xmlns=\"http://jabber.org/protocol/si\">");
        } else {
            throw new IllegalArgumentException("IQ Type not understood");
        }
        Feature feature = this.featureNegotiation;
        if (feature != null) {
            sb.append(feature.toXML());
        }
        sb.append("</si>");
        return sb.toString();
    }

    public static class File implements PacketExtension {
        private Date date;
        private String desc;
        private String hash;
        private boolean isRanged;
        private final String name;
        private final long size;

        public String getElementName() {
            return "file";
        }

        public String getNamespace() {
            return "http://jabber.org/protocol/si/profile/file-transfer";
        }

        public File(String str, long j) {
            Objects.requireNonNull(str, "name cannot be null");
            this.name = str;
            this.size = j;
        }

        public String getName() {
            return this.name;
        }

        public long getSize() {
            return this.size;
        }

        public void setHash(String str) {
            this.hash = str;
        }

        public String getHash() {
            return this.hash;
        }

        public void setDate(Date date2) {
            this.date = date2;
        }

        public Date getDate() {
            return this.date;
        }

        public void setDesc(String str) {
            this.desc = str;
        }

        public String getDesc() {
            return this.desc;
        }

        public void setRanged(boolean z) {
            this.isRanged = z;
        }

        public boolean isRanged() {
            return this.isRanged;
        }

        public String toXML() {
            StringBuilder sb = new StringBuilder();
            sb.append("<");
            sb.append(getElementName());
            sb.append(" xmlns=\"");
            sb.append(getNamespace());
            sb.append("\" ");
            if (getName() != null) {
                sb.append("name=\"");
                sb.append(StringUtils.escapeForXML(getName()));
                sb.append("\" ");
            }
            if (getSize() > 0) {
                sb.append("size=\"");
                sb.append(getSize());
                sb.append("\" ");
            }
            if (getDate() != null) {
                synchronized (Packet.XEP_0082_UTC_FORMAT) {
                    sb.append("date=\"");
                    sb.append(Packet.XEP_0082_UTC_FORMAT.format(this.date));
                    sb.append("\" ");
                }
            }
            if (getHash() != null) {
                sb.append("hash=\"");
                sb.append(getHash());
                sb.append("\" ");
            }
            String str = this.desc;
            if ((str == null || str.length() <= 0) && !this.isRanged) {
                sb.append("/>");
            } else {
                sb.append(">");
                if (getDesc() != null && this.desc.length() > 0) {
                    sb.append("<desc>");
                    sb.append(StringUtils.escapeForXML(getDesc()));
                    sb.append("</desc>");
                }
                if (isRanged()) {
                    sb.append("<range/>");
                }
                sb.append("</");
                sb.append(getElementName());
                sb.append(">");
            }
            return sb.toString();
        }
    }

    public class Feature implements PacketExtension {
        private final DataForm data;

        public String getElementName() {
            return "feature";
        }

        public String getNamespace() {
            return "http://jabber.org/protocol/feature-neg";
        }

        public Feature(DataForm dataForm) {
            this.data = dataForm;
        }

        public DataForm getData() {
            return this.data;
        }

        public String toXML() {
            return "<feature xmlns=\"http://jabber.org/protocol/feature-neg\">" + this.data.toXML() + "</feature>";
        }
    }
}
