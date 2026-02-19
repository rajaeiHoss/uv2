package org.jivesoftware.smackx.workgroup.packet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.jivesoftware.smack.packet.IQ;

public class Transcripts extends IQ {
    /* access modifiers changed from: private */
    public static final SimpleDateFormat UTC_FORMAT;
    private List<TranscriptSummary> summaries;
    private String userID;

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd'T'HH:mm:ss");
        UTC_FORMAT = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+0"));
    }

    public Transcripts(String str) {
        this.userID = str;
        this.summaries = new ArrayList();
    }

    public Transcripts(String str, List<TranscriptSummary> list) {
        this.userID = str;
        this.summaries = list;
    }

    public String getUserID() {
        return this.userID;
    }

    public List<TranscriptSummary> getSummaries() {
        return Collections.unmodifiableList(this.summaries);
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<transcripts xmlns=\"http://jivesoftware.com/protocol/workgroup\" userID=\"");
        sb.append(this.userID);
        sb.append("\">");
        for (TranscriptSummary xml : this.summaries) {
            sb.append(xml.toXML());
        }
        sb.append("</transcripts>");
        return sb.toString();
    }

    public static class TranscriptSummary {
        private List<AgentDetail> agentDetails;
        private Date joinTime;
        private Date leftTime;
        private String sessionID;

        public TranscriptSummary(String str, Date date, Date date2, List<AgentDetail> list) {
            this.sessionID = str;
            this.joinTime = date;
            this.leftTime = date2;
            this.agentDetails = list;
        }

        public String getSessionID() {
            return this.sessionID;
        }

        public Date getJoinTime() {
            return this.joinTime;
        }

        public Date getLeftTime() {
            return this.leftTime;
        }

        public List<AgentDetail> getAgentDetails() {
            return this.agentDetails;
        }

        public String toXML() {
            StringBuilder sb = new StringBuilder();
            sb.append("<transcript sessionID=\"");
            sb.append(this.sessionID);
            sb.append("\">");
            if (this.joinTime != null) {
                sb.append("<joinTime>");
                sb.append(Transcripts.UTC_FORMAT.format(this.joinTime));
                sb.append("</joinTime>");
            }
            if (this.leftTime != null) {
                sb.append("<leftTime>");
                sb.append(Transcripts.UTC_FORMAT.format(this.leftTime));
                sb.append("</leftTime>");
            }
            sb.append("<agents>");
            for (AgentDetail xml : this.agentDetails) {
                sb.append(xml.toXML());
            }
            sb.append("</agents></transcript>");
            return sb.toString();
        }
    }

    public static class AgentDetail {
        private String agentJID;
        private Date joinTime;
        private Date leftTime;

        public AgentDetail(String str, Date date, Date date2) {
            this.agentJID = str;
            this.joinTime = date;
            this.leftTime = date2;
        }

        public String getAgentJID() {
            return this.agentJID;
        }

        public Date getJoinTime() {
            return this.joinTime;
        }

        public Date getLeftTime() {
            return this.leftTime;
        }

        public String toXML() {
            StringBuilder sb = new StringBuilder();
            sb.append("<agent>");
            if (this.agentJID != null) {
                sb.append("<agentJID>");
                sb.append(this.agentJID);
                sb.append("</agentJID>");
            }
            if (this.joinTime != null) {
                sb.append("<joinTime>");
                sb.append(Transcripts.UTC_FORMAT.format(this.joinTime));
                sb.append("</joinTime>");
            }
            if (this.leftTime != null) {
                sb.append("<leftTime>");
                sb.append(Transcripts.UTC_FORMAT.format(this.leftTime));
                sb.append("</leftTime>");
            }
            sb.append("</agent>");
            return sb.toString();
        }
    }
}
