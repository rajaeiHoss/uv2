package freemarker.core;

import freemarker.template.SimpleSequence;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNodeModel;
import freemarker.template.TemplateSequenceModel;
import freemarker.template.utility.Collections12;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import javax.swing.tree.TreeNode;

public abstract class TemplateElement extends TemplateObject implements TreeNode {
    TemplateElement nestedBlock;
    List nestedElements;
    TemplateElement parent;

    /* access modifiers changed from: package-private */
    public abstract void accept(Environment environment) throws TemplateException, IOException;

    /* access modifiers changed from: protected */
    public abstract String dump(boolean z);

    public String getNodeNamespace() {
        return null;
    }

    public String getNodeType() {
        return "element";
    }

    public TemplateNodeModel getParentNode() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean heedsOpeningWhitespace() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean heedsTrailingWhitespace() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isIgnorable() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isShownInStackTrace() {
        return true;
    }

    public final String getDescription() {
        return dump(false);
    }

    public final String getCanonicalForm() {
        return dump(true);
    }

    public TemplateSequenceModel getChildNodes() {
        if (this.nestedElements != null) {
            return new SimpleSequence((Collection) this.nestedElements);
        }
        SimpleSequence simpleSequence = new SimpleSequence();
        TemplateElement templateElement = this.nestedBlock;
        if (templateElement != null) {
            simpleSequence.add((Object) templateElement);
        }
        return simpleSequence;
    }

    public String getNodeName() {
        String name = getClass().getName();
        return name.substring(name.lastIndexOf(46) + 1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r1.nestedElements;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isLeaf() {
        /*
            r1 = this;
            freemarker.core.TemplateElement r0 = r1.nestedBlock
            if (r0 != 0) goto L_0x0010
            java.util.List r0 = r1.nestedElements
            if (r0 == 0) goto L_0x000e
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0010
        L_0x000e:
            r0 = 1
            goto L_0x0011
        L_0x0010:
            r0 = 0
        L_0x0011:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.core.TemplateElement.isLeaf():boolean");
    }

    public boolean getAllowsChildren() {
        return !isLeaf();
    }

    public int getIndex(TreeNode treeNode) {
        TemplateElement templateElement = this.nestedBlock;
        if (templateElement instanceof MixedContent) {
            return templateElement.getIndex(treeNode);
        }
        if (templateElement == null) {
            List list = this.nestedElements;
            if (list != null) {
                return list.indexOf(treeNode);
            }
            return -1;
        } else if (treeNode == templateElement) {
            return 0;
        } else {
            return -1;
        }
    }

    public int getChildCount() {
        TemplateElement templateElement = this.nestedBlock;
        if (templateElement instanceof MixedContent) {
            return templateElement.getChildCount();
        }
        if (templateElement != null) {
            return 1;
        }
        List list = this.nestedElements;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public Enumeration children() {
        TemplateElement templateElement = this.nestedBlock;
        if (templateElement instanceof MixedContent) {
            return templateElement.children();
        }
        if (templateElement != null) {
            return Collections.enumeration(Collections12.singletonList(templateElement));
        }
        List list = this.nestedElements;
        if (list != null) {
            return Collections.enumeration(list);
        }
        return Collections.enumeration(Collections.EMPTY_LIST);
    }

    public TreeNode getChildAt(int i) {
        TemplateElement templateElement = this.nestedBlock;
        if (templateElement instanceof MixedContent) {
            return templateElement.getChildAt(i);
        }
        if (templateElement == null) {
            List list = this.nestedElements;
            if (list != null) {
                return (TreeNode) list.get(i);
            }
            throw new ArrayIndexOutOfBoundsException("element has no children");
        } else if (i == 0) {
            return templateElement;
        } else {
            throw new ArrayIndexOutOfBoundsException("invalid index");
        }
    }

    public void setChildAt(int i, TemplateElement templateElement) {
        TemplateElement templateElement2 = this.nestedBlock;
        if (templateElement2 instanceof MixedContent) {
            templateElement2.setChildAt(i, templateElement);
        } else if (templateElement2 == null) {
            List list = this.nestedElements;
            if (list != null) {
                list.set(i, templateElement);
                templateElement.parent = this;
                return;
            }
            throw new IndexOutOfBoundsException("element has no children");
        } else if (i == 0) {
            this.nestedBlock = templateElement;
            templateElement.parent = this;
        } else {
            throw new IndexOutOfBoundsException("invalid index");
        }
    }

    public TreeNode getParent() {
        return this.parent;
    }

    /* access modifiers changed from: package-private */
    public void setParentRecursively(TemplateElement templateElement) {
        this.parent = templateElement;
        List list = this.nestedElements;
        int size = list == null ? 0 : list.size();
        for (int i = 0; i < size; i++) {
            ((TemplateElement) this.nestedElements.get(i)).setParentRecursively(this);
        }
        TemplateElement templateElement2 = this.nestedBlock;
        if (templateElement2 != null) {
            templateElement2.setParentRecursively(this);
        }
    }

    /* access modifiers changed from: package-private */
    public TemplateElement postParseCleanup(boolean z) throws ParseException {
        if (this.nestedElements != null) {
            for (int i = 0; i < this.nestedElements.size(); i++) {
                TemplateElement postParseCleanup = ((TemplateElement) this.nestedElements.get(i)).postParseCleanup(z);
                this.nestedElements.set(i, postParseCleanup);
                postParseCleanup.parent = this;
            }
            if (z) {
                Iterator it = this.nestedElements.iterator();
                while (it.hasNext()) {
                    if (((TemplateElement) it.next()).isIgnorable()) {
                        it.remove();
                    }
                }
            }
            List list = this.nestedElements;
            if (list instanceof ArrayList) {
                ((ArrayList) list).trimToSize();
            }
        }
        TemplateElement templateElement = this.nestedBlock;
        if (templateElement != null) {
            TemplateElement postParseCleanup2 = templateElement.postParseCleanup(z);
            this.nestedBlock = postParseCleanup2;
            if (postParseCleanup2.isIgnorable()) {
                this.nestedBlock = null;
            } else {
                this.nestedBlock.parent = this;
            }
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public TemplateElement prevTerminalNode() {
        TemplateElement previousSibling = previousSibling();
        if (previousSibling != null) {
            return previousSibling.getLastLeaf();
        }
        TemplateElement templateElement = this.parent;
        if (templateElement != null) {
            return templateElement.prevTerminalNode();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public TemplateElement nextTerminalNode() {
        TemplateElement nextSibling = nextSibling();
        if (nextSibling != null) {
            return nextSibling.getFirstLeaf();
        }
        TemplateElement templateElement = this.parent;
        if (templateElement != null) {
            return templateElement.nextTerminalNode();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public TemplateElement previousSibling() {
        List list;
        TemplateElement templateElement = this.parent;
        if (templateElement == null || (list = templateElement.nestedElements) == null) {
            return null;
        }
        int size = list.size() - 1;
        while (size >= 0) {
            if (list.get(size) != this) {
                size--;
            } else if (size > 0) {
                return (TemplateElement) list.get(size - 1);
            } else {
                return null;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public TemplateElement nextSibling() {
        List list;
        TemplateElement templateElement = this.parent;
        if (templateElement == null || (list = templateElement.nestedElements) == null) {
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == this) {
                int i2 = i + 1;
                if (i2 < list.size()) {
                    return (TemplateElement) list.get(i2);
                }
                return null;
            }
        }
        return null;
    }

    private TemplateElement getFirstChild() {
        TemplateElement templateElement = this.nestedBlock;
        if (templateElement != null) {
            return templateElement;
        }
        List list = this.nestedElements;
        if (list == null || list.size() <= 0) {
            return null;
        }
        return (TemplateElement) this.nestedElements.get(0);
    }

    private TemplateElement getLastChild() {
        TemplateElement templateElement = this.nestedBlock;
        if (templateElement != null) {
            return templateElement;
        }
        List list = this.nestedElements;
        if (list == null || list.size() <= 0) {
            return null;
        }
        List list2 = this.nestedElements;
        return (TemplateElement) list2.get(list2.size() - 1);
    }

    private TemplateElement getFirstLeaf() {
        TemplateElement templateElement = this;
        while (!templateElement.isLeaf() && !(templateElement instanceof Macro) && !(templateElement instanceof BlockAssignment)) {
            templateElement = templateElement.getFirstChild();
        }
        return templateElement;
    }

    private TemplateElement getLastLeaf() {
        TemplateElement templateElement = this;
        while (!templateElement.isLeaf() && !(templateElement instanceof Macro) && !(templateElement instanceof BlockAssignment)) {
            templateElement = templateElement.getLastChild();
        }
        return templateElement;
    }
}
