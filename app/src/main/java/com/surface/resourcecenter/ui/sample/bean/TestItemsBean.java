package com.surface.resourcecenter.ui.sample.bean;

public class TestItemsBean {

    /**
     * id : 198
     * name : 绕组对地及绕组间直流绝缘电阻测量、吸收比测量
     * type : 1
     * sign : rzddjydz
     */

    private String id;
    private String name;
    private String type;
    private String sign;
    private boolean isChecked;
    private String area;
    private String person;
    private String instrument;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
