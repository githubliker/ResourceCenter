package com.surface.resourcecenter.ui.home.adapter.bean;

public class HomeItem {
    private String itemName;
    private int itemId;

    public HomeItem(String itemName,int itemId){
        this.itemId = itemId;
        this.itemName = itemName;
    }
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
