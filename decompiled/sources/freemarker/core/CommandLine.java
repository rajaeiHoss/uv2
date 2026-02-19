package freemarker.core;

import freemarker.template.Configuration;
import freemarker.template.Version;
import freemarker.template.utility.DateUtil;

public class CommandLine {
    public static void main(String[] strArr) {
        Version version = Configuration.getVersion();
        System.out.println();
        System.out.print("FreeMarker version ");
        System.out.print(version);
        if (!version.toString().endsWith("Z") && version.getBuildDate() != null) {
            System.out.print(" (built on ");
            System.out.print(DateUtil.dateToISO8601String(version.getBuildDate(), true, true, true, 6, DateUtil.UTC, new DateUtil.TrivialDateToISO8601CalendarFactory()));
            System.out.print(")");
        }
        System.out.println();
        if (version.isGAECompliant() != null) {
            System.out.print("Google App Engine complian variant: ");
            System.out.println(version.isGAECompliant().booleanValue() ? "Yes" : "No");
        }
        System.out.println();
        System.out.println("Copyright (c) 2003 The Visigoth Software Society.");
        System.out.println("All rights reserved.");
        System.out.println();
        System.out.println("This is Free software. Please read the LICENSE.txt comes with ");
        System.out.println("the distribution for more details.");
        System.out.println();
        System.out.println("For more information and for updates visit our WWW site:");
        System.out.println("http://freemarker.org/");
        System.out.println();
    }
}
