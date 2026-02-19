package org.jivesoftware.smackx.packet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smackx.commands.AdHocCommand;
import org.jivesoftware.smackx.commands.AdHocCommandNote;

public class AdHocCommandData extends IQ {
    private AdHocCommand.Action action;
    private ArrayList<AdHocCommand.Action> actions = new ArrayList<>();
    private AdHocCommand.Action executeAction;
    private DataForm form;
    private String id;
    private String lang;
    private String name;
    private String node;
    private List<AdHocCommandNote> notes = new ArrayList();
    private String sessionID;
    private AdHocCommand.Status status;

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<command xmlns=\"http://jabber.org/protocol/commands\"");
        sb.append(" node=\"");
        sb.append(this.node);
        sb.append("\"");
        String str = this.sessionID;
        if (str != null && !str.equals("")) {
            sb.append(" sessionid=\"");
            sb.append(this.sessionID);
            sb.append("\"");
        }
        if (this.status != null) {
            sb.append(" status=\"");
            sb.append(this.status);
            sb.append("\"");
        }
        if (this.action != null) {
            sb.append(" action=\"");
            sb.append(this.action);
            sb.append("\"");
        }
        String str2 = this.lang;
        if (str2 != null && !str2.equals("")) {
            sb.append(" lang=\"");
            sb.append(this.lang);
            sb.append("\"");
        }
        sb.append(">");
        if (getType() == IQ.Type.RESULT) {
            sb.append("<actions");
            if (this.executeAction != null) {
                sb.append(" execute=\"");
                sb.append(this.executeAction);
                sb.append("\"");
            }
            if (this.actions.size() == 0) {
                sb.append("/>");
            } else {
                sb.append(">");
                Iterator<AdHocCommand.Action> it = this.actions.iterator();
                while (it.hasNext()) {
                    sb.append("<");
                    sb.append(it.next());
                    sb.append("/>");
                }
                sb.append("</actions>");
            }
        }
        DataForm dataForm = this.form;
        if (dataForm != null) {
            sb.append(dataForm.toXML());
        }
        for (AdHocCommandNote next : this.notes) {
            sb.append("<note type=\"");
            sb.append(next.getType().toString());
            sb.append("\">");
            sb.append(next.getValue());
            sb.append("</note>");
        }
        sb.append("</command>");
        return sb.toString();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getNode() {
        return this.node;
    }

    public void setNode(String str) {
        this.node = str;
    }

    public List<AdHocCommandNote> getNotes() {
        return this.notes;
    }

    public void addNote(AdHocCommandNote adHocCommandNote) {
        this.notes.add(adHocCommandNote);
    }

    public void remveNote(AdHocCommandNote adHocCommandNote) {
        this.notes.remove(adHocCommandNote);
    }

    public DataForm getForm() {
        return this.form;
    }

    public void setForm(DataForm dataForm) {
        this.form = dataForm;
    }

    public AdHocCommand.Action getAction() {
        return this.action;
    }

    public void setAction(AdHocCommand.Action action2) {
        this.action = action2;
    }

    public AdHocCommand.Status getStatus() {
        return this.status;
    }

    public void setStatus(AdHocCommand.Status status2) {
        this.status = status2;
    }

    public List<AdHocCommand.Action> getActions() {
        return this.actions;
    }

    public void addAction(AdHocCommand.Action action2) {
        this.actions.add(action2);
    }

    public void setExecuteAction(AdHocCommand.Action action2) {
        this.executeAction = action2;
    }

    public AdHocCommand.Action getExecuteAction() {
        return this.executeAction;
    }

    public void setSessionID(String str) {
        this.sessionID = str;
    }

    public String getSessionID() {
        return this.sessionID;
    }

    public static class SpecificError implements PacketExtension {
        public static final String namespace = "http://jabber.org/protocol/commands";
        public AdHocCommand.SpecificErrorCondition condition;

        public String getNamespace() {
            return namespace;
        }

        public SpecificError(AdHocCommand.SpecificErrorCondition specificErrorCondition) {
            this.condition = specificErrorCondition;
        }

        public String getElementName() {
            return this.condition.toString();
        }

        public AdHocCommand.SpecificErrorCondition getCondition() {
            return this.condition;
        }

        public String toXML() {
            return "<" + getElementName() + " xmlns=\"" + getNamespace() + "\"/>";
        }
    }
}
