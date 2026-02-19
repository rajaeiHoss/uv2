package freemarker.ext.rhino;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import java.util.List;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class RhinoFunctionModel extends RhinoScriptableModel implements TemplateMethodModelEx {
    private final Scriptable fnThis;

    public RhinoFunctionModel(Function function, Scriptable scriptable, BeansWrapper beansWrapper) {
        super(function, beansWrapper);
        this.fnThis = scriptable;
    }

    public Object exec(List list) throws TemplateModelException {
        Context currentContext = Context.getCurrentContext();
        Object[] array = list.toArray();
        BeansWrapper wrapper = getWrapper();
        for (int i = 0; i < array.length; i++) {
            array[i] = wrapper.unwrap((TemplateModel) array[i]);
        }
        return wrapper.wrap(getScriptable().call(currentContext, ScriptableObject.getTopLevelScope(this.fnThis), this.fnThis, array));
    }
}
