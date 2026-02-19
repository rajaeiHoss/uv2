package freemarker.ext.beans;

import freemarker.template.TemplateCollectionModel;
import java.util.Set;

class SetAdapter extends CollectionAdapter implements Set {
    SetAdapter(TemplateCollectionModel templateCollectionModel, BeansWrapper beansWrapper) {
        super(templateCollectionModel, beansWrapper);
    }
}
