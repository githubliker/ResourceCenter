package com.surface.resourcecenter.ui.dispatch.adapter.bean;

public class standardItem {
    String itemName;
    boolean itemStatus;

    public standardItem(String name,boolean status){
        itemName = name;
        itemStatus = status;
    }
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(boolean itemStatus) {
        this.itemStatus = itemStatus;
    }
}
