package freemarker.template;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class SimpleDate implements TemplateDateModel {
    private final Date date;
    private final int type;

    public SimpleDate(java.sql.Date date2) {
        this(date2, 2);
    }

    public SimpleDate(Time time) {
        this(time, 1);
    }

    public SimpleDate(Timestamp timestamp) {
        this(timestamp, 3);
    }

    public SimpleDate(Date date2, int i) {
        if (date2 != null) {
            this.date = date2;
            this.type = i;
            return;
        }
        throw new IllegalArgumentException("date == null");
    }

    public Date getAsDate() {
        return this.date;
    }

    public int getDateType() {
        return this.type;
    }

    public String toString() {
        return this.date.toString();
    }
}
