package com.example.d2pockets.item_instance;

import java.util.List;

public class ArmoryInstance {
    private String name;
    private String icon;  // it is url
    private List<String> mods;  //urls
    private String partName;   //helmet, arms, chest, legs, class item
    private String elementalType;  //arc, void, solar, stasis, kinetic

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<String> getMods() {
        return mods;
    }

    public void setMods(List<String> mods) {
        this.mods = mods;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getElementalType() {
        return elementalType;
    }

    public void setElementalType(String elementalType) {
        this.elementalType = elementalType;
    }

    public ArmoryInstance(String name, String icon, List<String> mods, String partName, String elementalType) {
        this.name = name;
        this.icon = icon;
        this.mods = mods;
        this.partName = partName;
        this.elementalType = elementalType;
    }


}

