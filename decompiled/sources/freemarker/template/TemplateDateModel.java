package freemarker.template;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public interface TemplateDateModel extends TemplateModel {
    public static final int DATE = 2;
    public static final int DATETIME = 3;
    public static final int TIME = 1;
    public static final List TYPE_NAMES = Collections.unmodifiableList(Arrays.asList(new String[]{"UNKNOWN", "TIME", "DATE", "DATETIME"}));
    public static final int UNKNOWN = 0;

    Date getAsDate() throws TemplateModelException;

    int getDateType();
}
