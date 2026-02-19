package freemarker.template.utility;

import freemarker.template.utility.DateUtil;
import java.util.Date;
import java.util.TimeZone;

class J2SE14TimeZoneOffsetCalculator implements DateUtil.TimeZoneOffsetCalculator {
    J2SE14TimeZoneOffsetCalculator() {
    }

    public int getOffset(TimeZone timeZone, Date date) {
        return timeZone.getOffset(date.getTime());
    }
}
