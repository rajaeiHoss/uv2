package com.kenai.jbosh;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ComposableBody extends AbstractBody {
    private static final Pattern BOSH_START = Pattern.compile("<body(?:[\t\n\r ][^>]*?)?(/>|>)", 64);
    private final Map<BodyQName, String> attrs;
    private final AtomicReference<String> computed;
    /* access modifiers changed from: private */
    public final String payload;

    public static final class Builder {
        private boolean doMapCopy;
        private Map<BodyQName, String> map;
        private String payloadXML;

        private Builder() {
        }

        /* access modifiers changed from: private */
        public static Builder fromBody(ComposableBody composableBody) {
            Builder builder = new Builder();
            builder.map = composableBody.getAttributes();
            builder.doMapCopy = true;
            builder.payloadXML = composableBody.payload;
            return builder;
        }

        public Builder setPayloadXML(String str) {
            if (str != null) {
                this.payloadXML = str;
                return this;
            }
            throw new IllegalArgumentException("payload XML argument cannot be null");
        }

        public Builder setAttribute(BodyQName bodyQName, String str) {
            if (this.map == null) {
                this.map = new HashMap();
            } else if (this.doMapCopy) {
                this.map = new HashMap(this.map);
                this.doMapCopy = false;
            }
            if (str == null) {
                this.map.remove(bodyQName);
            } else {
                this.map.put(bodyQName, str);
            }
            return this;
        }

        public Builder setNamespaceDefinition(String str, String str2) {
            return setAttribute(BodyQName.createWithPrefix("http://www.w3.org/XML/1998/namespace", str, "xmlns"), str2);
        }

        public ComposableBody build() {
            if (this.map == null) {
                this.map = new HashMap();
            }
            if (this.payloadXML == null) {
                this.payloadXML = "";
            }
            return new ComposableBody(this.map, this.payloadXML);
        }
    }

    private ComposableBody(Map<BodyQName, String> map, String str) {
        this.computed = new AtomicReference<>();
        this.attrs = map;
        this.payload = str;
    }

    static ComposableBody fromStaticBody(StaticBody staticBody) throws BOSHException {
        String str;
        String xml = staticBody.toXML();
        Pattern pattern = BOSH_START;
        Matcher matcher = pattern.matcher(xml);
        if (matcher.find()) {
            if (">".equals(matcher.group(1))) {
                int end = matcher.end();
                int lastIndexOf = xml.lastIndexOf("</");
                if (lastIndexOf < end) {
                    lastIndexOf = end;
                }
                str = xml.substring(end, lastIndexOf);
            } else {
                str = "";
            }
            return new ComposableBody(staticBody.getAttributes(), str);
        }
        throw new BOSHException("Could not locate 'body' element in XML.  The raw XML did not match the pattern: " + pattern);
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder rebuild() {
        return Builder.fromBody(this);
    }

    public Map<BodyQName, String> getAttributes() {
        return Collections.unmodifiableMap(this.attrs);
    }

    public String toXML() {
        String str = this.computed.get();
        if (str != null) {
            return str;
        }
        String computeXML = computeXML();
        this.computed.set(computeXML);
        return computeXML;
    }

    public String getPayloadXML() {
        return this.payload;
    }

    private String escape(String str) {
        return str.replace("'", "&apos;");
    }

    private String computeXML() {
        BodyQName bodyQName = getBodyQName();
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(bodyQName.getLocalPart());
        for (Map.Entry next : this.attrs.entrySet()) {
            sb.append(" ");
            BodyQName bodyQName2 = (BodyQName) next.getKey();
            String prefix = bodyQName2.getPrefix();
            if (prefix != null && prefix.length() > 0) {
                sb.append(prefix);
                sb.append(":");
            }
            sb.append(bodyQName2.getLocalPart());
            sb.append("='");
            sb.append(escape((String) next.getValue()));
            sb.append("'");
        }
        sb.append(" ");
        sb.append("xmlns");
        sb.append("='");
        sb.append(bodyQName.getNamespaceURI());
        sb.append("'>");
        String str = this.payload;
        if (str != null) {
            sb.append(str);
        }
        sb.append("</body>");
        return sb.toString();
    }
}
