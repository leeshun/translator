package sse.bupt.cn.translator.model;

import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.Date;

public class MenuItem implements Comparable<MenuItem> {
    private String menuName;

    private String path;

    private int lastViewPages;

    private Date lastViewTime;

    public MenuItem() {
        this.lastViewPages = 0;
        this.lastViewTime = Calendar.getInstance().getTime();
    }

    public MenuItem(String menuName, String path, int lastViewPages, Date lastViewTime) {
        this.menuName = menuName;
        this.path = path;
        if (lastViewPages < 0) {
            this.lastViewPages = 0;
        } else {
            this.lastViewPages = lastViewPages;
        }
        if (lastViewTime == null) {
            this.lastViewTime = Calendar.getInstance().getTime();
        } else {
            this.lastViewTime = lastViewTime;
        }
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getLastViewPages() {
        return lastViewPages;
    }

    public void setLastViewPages(int lastViewPages) {
        if (lastViewPages < 0) {
            this.lastViewPages = 0;
        } else {
            this.lastViewPages = lastViewPages;
        }
    }

    public Date getLastViewTime() {
        return lastViewTime;
    }

    public void setLastViewTime(Date lastViewTime) {
        if (lastViewTime == null) {
            this.lastViewTime = Calendar.getInstance().getTime();
        } else {
            this.lastViewTime = lastViewTime;
        }
    }


    @Override
    public int compareTo(@NonNull MenuItem o) {
        if (this.lastViewTime.before(o.lastViewTime)) {
            return 1;
        } else if (this.lastViewTime.after(o.lastViewTime)) {
            return -1;
        } else {
            return 0;
        }
    }
}
