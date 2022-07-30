package com.surface.resourcecenter.ui.home.adapter.bean;

public class TaskItem {
    private String startPersion;
    private String currentPersion;
    private String startTime;
    private String updateTime;
    private String sampleName;
    private String sampleStatus;

    public TaskItem(String sampleName,String startPersion,String currentPersion,String startTime,String updateTime,String sampleStatus){
        this.sampleName = sampleName;
        this.startPersion = startPersion;
        this.currentPersion = currentPersion;
        this.startTime = startTime;
        this.updateTime = updateTime;
        this.sampleStatus = sampleStatus;
    }
    public String getStartPersion() {
        return startPersion;
    }

    public void setStartPersion(String startPersion) {
        this.startPersion = startPersion;
    }

    public String getCurrentPersion() {
        return currentPersion;
    }

    public void setCurrentPersion(String currentPersion) {
        this.currentPersion = currentPersion;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSampleStatus() {
        return sampleStatus;
    }

    public void setSampleStatus(String sampleStatus) {
        this.sampleStatus = sampleStatus;
    }
}
