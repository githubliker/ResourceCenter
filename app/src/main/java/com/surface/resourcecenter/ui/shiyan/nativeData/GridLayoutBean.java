package com.surface.resourcecenter.ui.shiyan.nativeData;

import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.TextView;

public class GridLayoutBean {
    private GridLayout gridLayout;
    private TextView textView;
    private TextView reMark;
    private CheckBox result;

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
