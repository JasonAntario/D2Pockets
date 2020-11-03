package com.example.d2pockets.item_instance;

import java.util.List;

public class WeaponItem {
    private String name;
    private String icon;  // it is url
    private List<String> perks;  //urls
    private String bulletType;   //primary, special, heavy

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

    public List<String> getPerks() {
        return perks;
    }

    public void setPerks(List<String> perks) {
        this.perks = perks;
    }

    public String getBulletType() {
        return bulletType;
    }

    public void setBulletType(String bulletType) {
        this.bulletType = bulletType;
    }

    public String getElementalType() {
        return elementalType;
    }

    public void setElementalType(String elementalType) {
        this.elementalType = elementalType;
    }

    private String elementalType;  //arc, void, solar, stasis, kinetic

    public WeaponItem(String name, String icon, List<String> perks, String bulletType, String elementalType) {
        this.name = name;
        this.icon = icon;
        this.perks = perks;
        this.bulletType = bulletType;
        this.elementalType = elementalType;
    }
    //TODO add more parameters (or not)

}
