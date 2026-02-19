package org.jivesoftware.smackx.workgroup.ext.macros;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MacroGroup {
    private List<MacroGroup> macroGroups = new ArrayList();
    private List<Macro> macros = new ArrayList();
    private String title;

    public void addMacro(Macro macro) {
        this.macros.add(macro);
    }

    public void removeMacro(Macro macro) {
        this.macros.remove(macro);
    }

    public Macro getMacroByTitle(String str) {
        for (Macro macro : Collections.unmodifiableList(this.macros)) {
            if (macro.getTitle().equalsIgnoreCase(str)) {
                return macro;
            }
        }
        return null;
    }

    public void addMacroGroup(MacroGroup macroGroup) {
        this.macroGroups.add(macroGroup);
    }

    public void removeMacroGroup(MacroGroup macroGroup) {
        this.macroGroups.remove(macroGroup);
    }

    public Macro getMacro(int i) {
        return this.macros.get(i);
    }

    public MacroGroup getMacroGroupByTitle(String str) {
        for (MacroGroup macroGroup : Collections.unmodifiableList(this.macroGroups)) {
            if (macroGroup.getTitle().equalsIgnoreCase(str)) {
                return macroGroup;
            }
        }
        return null;
    }

    public MacroGroup getMacroGroup(int i) {
        return this.macroGroups.get(i);
    }

    public List<Macro> getMacros() {
        return this.macros;
    }

    public void setMacros(List<Macro> list) {
        this.macros = list;
    }

    public List<MacroGroup> getMacroGroups() {
        return this.macroGroups;
    }

    public void setMacroGroups(List<MacroGroup> list) {
        this.macroGroups = list;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<macrogroup>");
        sb.append("<title>" + getTitle() + "</title>");
        sb.append("<macros>");
        for (Macro next : getMacros()) {
            sb.append("<macro>");
            sb.append("<title>" + next.getTitle() + "</title>");
            sb.append("<type>" + next.getType() + "</type>");
            sb.append("<description>" + next.getDescription() + "</description>");
            sb.append("<response>" + next.getResponse() + "</response>");
            sb.append("</macro>");
        }
        sb.append("</macros>");
        if (getMacroGroups().size() > 0) {
            sb.append("<macroGroups>");
            for (MacroGroup xml : getMacroGroups()) {
                sb.append(xml.toXML());
            }
            sb.append("</macroGroups>");
        }
        sb.append("</macrogroup>");
        return sb.toString();
    }
}
