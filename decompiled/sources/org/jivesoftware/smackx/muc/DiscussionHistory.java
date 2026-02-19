package org.jivesoftware.smackx.muc;

import java.util.Date;
import org.jivesoftware.smackx.packet.MUCInitialPresence;

public class DiscussionHistory {
    private int maxChars = -1;
    private int maxStanzas = -1;
    private int seconds = -1;
    private Date since;

    public int getMaxChars() {
        return this.maxChars;
    }

    public int getMaxStanzas() {
        return this.maxStanzas;
    }

    public int getSeconds() {
        return this.seconds;
    }

    public Date getSince() {
        return this.since;
    }

    public void setMaxChars(int i) {
        this.maxChars = i;
    }

    public void setMaxStanzas(int i) {
        this.maxStanzas = i;
    }

    public void setSeconds(int i) {
        this.seconds = i;
    }

    public void setSince(Date date) {
        this.since = date;
    }

    private boolean isConfigured() {
        return this.maxChars > -1 || this.maxStanzas > -1 || this.seconds > -1 || this.since != null;
    }

    /* access modifiers changed from: package-private */
    public MUCInitialPresence.History getMUCHistory() {
        if (!isConfigured()) {
            return null;
        }
        MUCInitialPresence.History history = new MUCInitialPresence.History();
        int i = this.maxChars;
        if (i > -1) {
            history.setMaxChars(i);
        }
        int i2 = this.maxStanzas;
        if (i2 > -1) {
            history.setMaxStanzas(i2);
        }
        int i3 = this.seconds;
        if (i3 > -1) {
            history.setSeconds(i3);
        }
        Date date = this.since;
        if (date != null) {
            history.setSince(date);
        }
        return history;
    }
}
