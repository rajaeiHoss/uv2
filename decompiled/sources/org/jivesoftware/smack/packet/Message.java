package org.jivesoftware.smack.packet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.jivesoftware.smack.util.StringUtils;

public class Message extends Packet {
    private final Set<Body> bodies = new HashSet();
    private String language;
    private final Set<Subject> subjects = new HashSet();
    private String thread = null;
    private Type type = Type.normal;

    public Message() {
    }

    public Message(String str) {
        setTo(str);
    }

    public Message(String str, Type type2) {
        setTo(str);
        this.type = type2;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type2) {
        if (type2 != null) {
            this.type = type2;
            return;
        }
        throw new IllegalArgumentException("Type cannot be null.");
    }

    public String getSubject() {
        return getSubject((String) null);
    }

    public String getSubject(String str) {
        Subject messageSubject = getMessageSubject(str);
        if (messageSubject == null) {
            return null;
        }
        return messageSubject.subject;
    }

    private Subject getMessageSubject(String str) {
        String determineLanguage = determineLanguage(str);
        for (Subject next : this.subjects) {
            if (determineLanguage.equals(next.language)) {
                return next;
            }
        }
        return null;
    }

    public Collection<Subject> getSubjects() {
        return Collections.unmodifiableCollection(this.subjects);
    }

    public void setSubject(String str) {
        if (str == null) {
            removeSubject("");
        } else {
            addSubject((String) null, str);
        }
    }

    public Subject addSubject(String str, String str2) {
        Subject subject = new Subject(determineLanguage(str), str2);
        this.subjects.add(subject);
        return subject;
    }

    public boolean removeSubject(String str) {
        String determineLanguage = determineLanguage(str);
        for (Subject next : this.subjects) {
            if (determineLanguage.equals(next.language)) {
                return this.subjects.remove(next);
            }
        }
        return false;
    }

    public boolean removeSubject(Subject subject) {
        return this.subjects.remove(subject);
    }

    public Collection<String> getSubjectLanguages() {
        Subject messageSubject = getMessageSubject((String) null);
        ArrayList arrayList = new ArrayList();
        for (Subject next : this.subjects) {
            if (!next.equals(messageSubject)) {
                arrayList.add(next.language);
            }
        }
        return Collections.unmodifiableCollection(arrayList);
    }

    public String getBody() {
        return getBody((String) null);
    }

    public String getBody(String str) {
        Body messageBody = getMessageBody(str);
        if (messageBody == null) {
            return null;
        }
        return messageBody.message;
    }

    private Body getMessageBody(String str) {
        String determineLanguage = determineLanguage(str);
        for (Body next : this.bodies) {
            if (determineLanguage.equals(next.language)) {
                return next;
            }
        }
        return null;
    }

    public Collection<Body> getBodies() {
        return Collections.unmodifiableCollection(this.bodies);
    }

    public void setBody(String str) {
        if (str == null) {
            removeBody("");
        } else {
            addBody((String) null, str);
        }
    }

    public Body addBody(String str, String str2) {
        Body body = new Body(determineLanguage(str), str2);
        this.bodies.add(body);
        return body;
    }

    public boolean removeBody(String str) {
        String determineLanguage = determineLanguage(str);
        for (Body next : this.bodies) {
            if (determineLanguage.equals(next.language)) {
                return this.bodies.remove(next);
            }
        }
        return false;
    }

    public boolean removeBody(Body body) {
        return this.bodies.remove(body);
    }

    public Collection<String> getBodyLanguages() {
        Body messageBody = getMessageBody((String) null);
        ArrayList arrayList = new ArrayList();
        for (Body next : this.bodies) {
            if (!next.equals(messageBody)) {
                arrayList.add(next.language);
            }
        }
        return Collections.unmodifiableCollection(arrayList);
    }

    public String getThread() {
        return this.thread;
    }

    public void setThread(String str) {
        this.thread = str;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    private String determineLanguage(String str) {
        String str2;
        if ("".equals(str)) {
            str = null;
        }
        if (str != null || (str2 = this.language) == null) {
            return str == null ? getDefaultLanguage() : str;
        }
        return str2;
    }

    public String toXML() {
        XMPPError error;
        StringBuilder sb = new StringBuilder();
        sb.append("<message");
        if (getXmlns() != null) {
            sb.append(" xmlns=\"");
            sb.append(getXmlns());
            sb.append("\"");
        }
        if (this.language != null) {
            sb.append(" xml:lang=\"");
            sb.append(getLanguage());
            sb.append("\"");
        }
        if (getPacketID() != null) {
            sb.append(" id=\"");
            sb.append(getPacketID());
            sb.append("\"");
        }
        if (getTo() != null) {
            sb.append(" to=\"");
            sb.append(StringUtils.escapeForXML(getTo()));
            sb.append("\"");
        }
        if (getFrom() != null) {
            sb.append(" from=\"");
            sb.append(StringUtils.escapeForXML(getFrom()));
            sb.append("\"");
        }
        if (this.type != Type.normal) {
            sb.append(" type=\"");
            sb.append(this.type);
            sb.append("\"");
        }
        sb.append(">");
        Subject messageSubject = getMessageSubject((String) null);
        if (messageSubject != null) {
            sb.append("<subject>");
            sb.append(StringUtils.escapeForXML(messageSubject.subject));
            sb.append("</subject>");
        }
        for (Subject next : getSubjects()) {
            if (!next.equals(messageSubject)) {
                sb.append("<subject xml:lang=\"");
                sb.append(next.language);
                sb.append("\">");
                sb.append(StringUtils.escapeForXML(next.subject));
                sb.append("</subject>");
            }
        }
        Body messageBody = getMessageBody((String) null);
        if (messageBody != null) {
            sb.append("<body>");
            sb.append(StringUtils.escapeForXML(messageBody.message));
            sb.append("</body>");
        }
        for (Body next2 : getBodies()) {
            if (!next2.equals(messageBody)) {
                sb.append("<body xml:lang=\"");
                sb.append(next2.getLanguage());
                sb.append("\">");
                sb.append(StringUtils.escapeForXML(next2.getMessage()));
                sb.append("</body>");
            }
        }
        if (this.thread != null) {
            sb.append("<thread>");
            sb.append(this.thread);
            sb.append("</thread>");
        }
        if (this.type == Type.error && (error = getError()) != null) {
            sb.append(error.toXML());
        }
        sb.append(getExtensionsXML());
        sb.append("</message>");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        String str;
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            Message message = (Message) obj;
            if (super.equals(message) && this.bodies.size() == message.bodies.size() && this.bodies.containsAll(message.bodies) && ((str = this.language) == null ? message.language == null : str.equals(message.language)) && this.subjects.size() == message.subjects.size() && this.subjects.containsAll(message.subjects)) {
                String str2 = this.thread;
                if (str2 == null ? message.thread != null : !str2.equals(message.thread)) {
                    return false;
                }
                if (this.type == message.type) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public int hashCode() {
        Type type2 = this.type;
        int i = 0;
        int hashCode = (((type2 != null ? type2.hashCode() : 0) * 31) + this.subjects.hashCode()) * 31;
        String str = this.thread;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.language;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return ((hashCode2 + i) * 31) + this.bodies.hashCode();
    }

    public static class Subject {
        /* access modifiers changed from: private */
        public String language;
        /* access modifiers changed from: private */
        public String subject;

        private Subject(String str, String str2) {
            Objects.requireNonNull(str, "Language cannot be null.");
            Objects.requireNonNull(str2, "Subject cannot be null.");
            this.language = str;
            this.subject = str2;
        }

        public String getLanguage() {
            return this.language;
        }

        public String getSubject() {
            return this.subject;
        }

        public int hashCode() {
            return ((this.language.hashCode() + 31) * 31) + this.subject.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Subject subject2 = (Subject) obj;
            if (!this.language.equals(subject2.language) || !this.subject.equals(subject2.subject)) {
                return false;
            }
            return true;
        }
    }

    public static class Body {
        /* access modifiers changed from: private */
        public String language;
        /* access modifiers changed from: private */
        public String message;

        private Body(String str, String str2) {
            Objects.requireNonNull(str, "Language cannot be null.");
            Objects.requireNonNull(str2, "Message cannot be null.");
            this.language = str;
            this.message = str2;
        }

        public String getLanguage() {
            return this.language;
        }

        public String getMessage() {
            return this.message;
        }

        public int hashCode() {
            return ((this.language.hashCode() + 31) * 31) + this.message.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Body body = (Body) obj;
            if (!this.language.equals(body.language) || !this.message.equals(body.message)) {
                return false;
            }
            return true;
        }
    }

    public enum Type {
        normal,
        chat,
        groupchat,
        headline,
        error;

        public static Type fromString(String str) {
            try {
                return valueOf(str);
            } catch (Exception unused) {
                return normal;
            }
        }
    }
}
