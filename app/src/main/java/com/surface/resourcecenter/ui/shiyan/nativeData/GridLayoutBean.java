package com.surface.resourcecenter.ui.shiyan.nativeData;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class GridLayoutBean {
    private GridLayout gridLayout;
    private TextView textView;
    private TextView reMark;
    private CheckBox result;
    private Button save;
    private Button update;
    private ArrayList<EditText> shiYanData;

    public ArrayList<EditText> getShiYanData() {
        return shiYanData;
    }

    public void setShiYanData(ArrayList<EditText> shiYanData) {
        this.shiYanData = shiYanData;
    }

    public Button getSave() {
        return save;
    }

    public void setSave(Button save) {
        this.save = save;
    }

    public Button getUpdate() {
        return update;
    }

    public void setUpdate(Button update) {
        this.update = update;
    }

    public TextView getReMark() {
        return reMark;
    }

    public void setReMark(TextView reMark) {
        this.reMark = reMark;
    }

    public CheckBox getResult() {
        return result;
    }

    public void setResult(CheckBox result) {
        this.result = result;
    }

    public GridLayout getGridLayout() {
        return gridLayout;
    }

    public void setGridLayout(GridLayout gridLayout) {
        this.gridLayout = gridLayout;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}
