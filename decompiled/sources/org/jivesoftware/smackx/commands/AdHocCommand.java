package org.jivesoftware.smackx.commands;

import java.util.List;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.packet.AdHocCommandData;

public abstract class AdHocCommand {
    private AdHocCommandData data = new AdHocCommandData();

    public enum Action {
        execute,
        cancel,
        prev,
        next,
        complete,
        unknown
    }

    public enum Status {
        executing,
        completed,
        canceled
    }

    public abstract void cancel() throws XMPPException;

    public abstract void complete(Form form) throws XMPPException;

    public abstract void execute() throws XMPPException;

    public abstract String getOwnerJID();

    public abstract void next(Form form) throws XMPPException;

    public abstract void prev() throws XMPPException;

    public static SpecificErrorCondition getSpecificErrorCondition(XMPPError xMPPError) {
        for (SpecificErrorCondition specificErrorCondition : SpecificErrorCondition.values()) {
            if (xMPPError.getExtension(specificErrorCondition.toString(), AdHocCommandData.SpecificError.namespace) != null) {
                return specificErrorCondition;
            }
        }
        return null;
    }

    public void setName(String str) {
        this.data.setName(str);
    }

    public String getName() {
        return this.data.getName();
    }

    public void setNode(String str) {
        this.data.setNode(str);
    }

    public String getNode() {
        return this.data.getNode();
    }

    public List<AdHocCommandNote> getNotes() {
        return this.data.getNotes();
    }

    /* access modifiers changed from: protected */
    public void addNote(AdHocCommandNote adHocCommandNote) {
        this.data.addNote(adHocCommandNote);
    }

    public String getRaw() {
        return this.data.getChildElementXML();
    }

    public Form getForm() {
        if (this.data.getForm() == null) {
            return null;
        }
        return new Form(this.data.getForm());
    }

    /* access modifiers changed from: protected */
    public void setForm(Form form) {
        this.data.setForm(form.getDataFormToSend());
    }

    /* access modifiers changed from: protected */
    public List<Action> getActions() {
        return this.data.getActions();
    }

    /* access modifiers changed from: protected */
    public void addActionAvailable(Action action) {
        this.data.addAction(action);
    }

    /* access modifiers changed from: protected */
    public Action getExecuteAction() {
        return this.data.getExecuteAction();
    }

    /* access modifiers changed from: protected */
    public void setExecuteAction(Action action) {
        this.data.setExecuteAction(action);
    }

    public Status getStatus() {
        return this.data.getStatus();
    }

    /* access modifiers changed from: package-private */
    public void setData(AdHocCommandData adHocCommandData) {
        this.data = adHocCommandData;
    }

    /* access modifiers changed from: package-private */
    public AdHocCommandData getData() {
        return this.data;
    }

    /* access modifiers changed from: protected */
    public boolean isValidAction(Action action) {
        return getActions().contains(action) || Action.cancel.equals(action);
    }

    public enum SpecificErrorCondition {
        badAction("bad-action"),
        malformedAction("malformed-action"),
        badLocale("bad-locale"),
        badPayload("bad-payload"),
        badSessionid("bad-sessionid"),
        sessionExpired("session-expired");
        
        private String value;

        private SpecificErrorCondition(String str) {
            this.value = str;
        }

        public String toString() {
            return this.value;
        }
    }
}
