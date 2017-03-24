package com.bisai.sinyu.DB;

/**
 * Created by sinyu on 2017/3/1.
 */
public class ParkingInfo {
    String name;
    int total;
    int yiyong;
    String content;
    String time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getYiyong() {
        return yiyong;
    }

    public void setYiyong(int yiyong) {
        this.yiyong = yiyong;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    int state;
    int collect;
}
