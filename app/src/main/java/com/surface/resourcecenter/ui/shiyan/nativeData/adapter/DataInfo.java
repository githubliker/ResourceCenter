package com.surface.resourcecenter.ui.shiyan.nativeData.adapter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by kelin on 16-11-18.
 */

public class DataInfo {

    public enum Status {
        CHECK_IN,
        BLANK;

        private static final List<Status> VALUES =
                Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static Status randomStatus() {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }
    private String Date;
    private String status;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
