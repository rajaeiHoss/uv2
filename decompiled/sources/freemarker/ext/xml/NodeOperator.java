package freemarker.ext.xml;

import java.util.List;

interface NodeOperator {
    void process(Object obj, String str, String str2, List list);
}
