package freemarker.ext.beans;

import freemarker.ext.util.ModelFactory;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateModel;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class DateModel extends BeanModel implements TemplateDateModel {
    static final ModelFactory FACTORY = new ModelFactory() {
        public TemplateModel create(Object obj, ObjectWrapper objectWrapper) {
            return new DateModel((Date) obj, (BeansWrapper) objectWrapper);
        }
    };
    private final int type;

    public DateModel(Date date, BeansWrapper beansWrapper) {
        super(date, beansWrapper);
        if (date instanceof java.sql.Date) {
            this.type = 2;
        } else if (date instanceof Time) {
            this.type = 1;
        } else if (date instanceof Timestamp) {
            this.type = 3;
        } else {
            this.type = beansWrapper.getDefaultDateType();
        }
    }

    public Date getAsDate() {
        return (Date) this.object;
    }

    public int getDateType() {
        return this.type;
    }
}
