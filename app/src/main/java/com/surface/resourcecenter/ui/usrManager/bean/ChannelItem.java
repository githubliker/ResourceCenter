package com.surface.resourcecenter.ui.usrManager.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yangzhipeng on 2018/6/22.
 */

public class ChannelItem {


    private String name;

    private String mode;
    private String role;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
