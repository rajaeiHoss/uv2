package freemarker.core;

import freemarker.template.Template;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

public class FreeMarkerTree extends JTree {
    public FreeMarkerTree(Template template) {
        super(template.getRootTreeNode());
    }

    public void setTemplate(Template template) {
        setModel(new DefaultTreeModel(template.getRootTreeNode()));
        invalidate();
    }

    public String convertValueToText(Object obj, boolean z, boolean z2, boolean z3, int i, boolean z4) {
        if (obj instanceof TemplateElement) {
            return ((TemplateElement) obj).getDescription();
        }
        return obj.toString();
    }
}
