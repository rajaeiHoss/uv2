package org.jivesoftware.smackx.packet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.jivesoftware.smack.packet.IQ;

public class Time extends IQ {
    private static DateFormat displayFormat = DateFormat.getDateTimeInstance();
    private static SimpleDateFormat utcFormat = new SimpleDateFormat("yyyyMMdd'T'HH:mm:ss");
    private String display = null;
    private String tz = null;
    private String utc = null;

    public Time() {
    }

    public Time(Calendar calendar) {
        TimeZone timeZone = calendar.getTimeZone();
        this.tz = calendar.getTimeZone().getID();
        this.display = displayFormat.format(calendar.getTime());
        this.utc = utcFormat.format(new Date(calendar.getTimeInMillis() - ((long) timeZone.getOffset(calendar.getTimeInMillis()))));
    }

    public Date getTime() {
        if (this.utc == null) {
            return null;
        }
        try {
            Calendar instance = Calendar.getInstance();
            instance.setTime(new Date(utcFormat.parse(this.utc).getTime() + ((long) instance.getTimeZone().getOffset(instance.getTimeInMillis()))));
            return instance.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setTime(Date date) {
        this.utc = utcFormat.format(new Date(date.getTime() - ((long) TimeZone.getDefault().getOffset(date.getTime()))));
    }

    public String getUtc() {
        return this.utc;
    }

    public void setUtc(String str) {
        this.utc = str;
    }

    public String getTz() {
        return this.tz;
    }

    public void setTz(String str) {
        this.tz = str;
    }

    public String getDisplay() {
        return this.display;
    }

    public void setDisplay(String str) {
        this.display = str;
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<query xmlns=\"jabber:iq:time\">");
        if (this.utc != null) {
            sb.append("<utc>");
            sb.append(this.utc);
            sb.append("</utc>");
        }
        if (this.tz != null) {
            sb.append("<tz>");
            sb.append(this.tz);
            sb.append("</tz>");
        }
        if (this.display != null) {
            sb.append("<display>");
            sb.append(this.display);
            sb.append("</display>");
        }
        sb.append("</query>");
        return sb.toString();
    }
}
