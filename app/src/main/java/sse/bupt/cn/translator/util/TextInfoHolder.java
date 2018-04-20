package sse.bupt.cn.translator.util;

import android.util.Log;

import java.util.Date;
import java.util.List;

import sse.bupt.cn.translator.model.Text;

public class TextInfoHolder {
    private static final String TAG = "TextInfoHolder";

    private static List<Text> texts;

    private static Date lastViewTime;

    private static int index;

    private static int lastViewPages;

    public static List<Text> getTexts() {
        return texts;
    }

    public static void setTexts(List<Text> texts) {
        TextInfoHolder.texts = texts;
    }

    public static Date getLastViewTime() {
        Log.i(TAG, "getLastViewTime: " + lastViewTime);
        return lastViewTime;
    }

    public static void setLastViewTime(Date lastViewTime) {
        Log.i(TAG, "setLastViewTime: " + lastViewTime.toString());
        TextInfoHolder.lastViewTime = lastViewTime;
    }

    public static int getIndex() {
        Log.i(TAG, "getIndex: " + index);
        return index;
    }

    public static void setIndex(int index) {
        Log.i(TAG, "setIndex: " + index);
        TextInfoHolder.index = index;
    }

    public static int getLastViewPages() {
        Log.i(TAG, "getLastViewPages: " + lastViewPages);
        return lastViewPages;
    }

    public static void setLastViewPages(int lastViewPages) {
        Log.i(TAG, "setLastViewPages: " + lastViewPages);
        TextInfoHolder.lastViewPages = lastViewPages;
    }
}
