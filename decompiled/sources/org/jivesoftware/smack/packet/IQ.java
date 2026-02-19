package org.jivesoftware.smack.packet;

import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.Form;

public abstract class IQ extends Packet {
    private Type type = Type.GET;

    public abstract String getChildElementXML();

    public Type getType() {
        return this.type;
    }

    public void setType(Type type2) {
        if (type2 == null) {
            this.type = Type.GET;
        } else {
            this.type = type2;
        }
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<iq ");
        if (getPacketID() != null) {
            sb.append("id=\"" + getPacketID() + "\" ");
        }
        if (getTo() != null) {
            sb.append("to=\"");
            sb.append(StringUtils.escapeForXML(getTo()));
            sb.append("\" ");
        }
        if (getFrom() != null) {
            sb.append("from=\"");
            sb.append(StringUtils.escapeForXML(getFrom()));
            sb.append("\" ");
        }
        if (this.type == null) {
            sb.append("type=\"get\">");
        } else {
            sb.append("type=\"");
            sb.append(getType());
            sb.append("\">");
        }
        String childElementXML = getChildElementXML();
        if (childElementXML != null) {
            sb.append(childElementXML);
        }
        XMPPError error = getError();
        if (error != null) {
            sb.append(error.toXML());
        }
        sb.append("</iq>");
        return sb.toString();
    }

    public static IQ createResultIQ(IQ iq) {
        if (iq.getType() == Type.GET || iq.getType() == Type.SET) {
            IQ resultIQ = new IQ() {
                public String getChildElementXML() {
                    return null;
                }
            };
            resultIQ.setType(Type.RESULT);
            resultIQ.setPacketID(iq.getPacketID());
            resultIQ.setFrom(iq.getTo());
            resultIQ.setTo(iq.getFrom());
            return resultIQ;
        }
        throw new IllegalArgumentException("IQ must be of type 'set' or 'get'. Original IQ: " + iq.toXML());
    }

    public static IQ createErrorResponse(IQ iq, XMPPError xMPPError) {
        if (iq.getType() == Type.GET || iq.getType() == Type.SET) {
            final IQ request = iq;
            IQ errorIQ = new IQ() {
                public String getChildElementXML() {
                    return request.getChildElementXML();
                }
            };
            errorIQ.setType(Type.ERROR);
            errorIQ.setPacketID(iq.getPacketID());
            errorIQ.setFrom(iq.getTo());
            errorIQ.setTo(iq.getFrom());
            errorIQ.setError(xMPPError);
            return errorIQ;
        }
        throw new IllegalArgumentException("IQ must be of type 'set' or 'get'. Original IQ: " + iq.toXML());
    }

    public static class Type {
        public static final Type ERROR = new Type("error");
        public static final Type GET = new Type("get");
        public static final Type RESULT = new Type(Form.TYPE_RESULT);
        public static final Type SET = new Type("set");
        private String value;

        public static Type fromString(String str) {
            if (str == null) {
                return null;
            }
            String lowerCase = str.toLowerCase();
            Type type = GET;
            if (type.toString().equals(lowerCase)) {
                return type;
            }
            Type type2 = SET;
            if (type2.toString().equals(lowerCase)) {
                return type2;
            }
            Type type3 = ERROR;
            if (type3.toString().equals(lowerCase)) {
                return type3;
            }
            Type type4 = RESULT;
            if (type4.toString().equals(lowerCase)) {
                return type4;
            }
            return null;
        }

        private Type(String str) {
            this.value = str;
        }

        public String toString() {
            return this.value;
        }
    }
}
