package sse.bupt.cn.translator.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import sse.bupt.cn.translator.model.MenuItem;

public class MenuPreferenceWriter {
    private static final String TAG = "MenuPreferenceWriter";

    private List<MenuItem> items;

    private SharedPreferences preferences;

    public MenuPreferenceWriter(Context context, List<MenuItem> items) {
        Log.i(TAG, "---construct a menu preference writer");
        preferences = context.getSharedPreferences("menu", Context.MODE_PRIVATE);
        this.items = items;
    }

    public void work() throws JSONException {
        JSONObject root = new JSONObject();

        JSONArray jsonItems = new JSONArray();

        int size = items.size();

        for (int index = 0; index < size; index++) {
            JSONObject object = new JSONObject();
            object.put("articleName", items.get(index).getMenuName());
            object.put("articlePath", items.get(index).getPath());
            object.put("lastViewPages", items.get(index).getLastViewPages());
            object.put("lastViewTime", items.get(index).getLastViewTime());
            jsonItems.put(index, object);
            Log.i(TAG, "---write into JSONArray " + object.toString() + "---");
        }
        root.put("items", jsonItems);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("menu", root.toString());
        editor.commit();
        Log.i(TAG, "---write to preference " + root.toString() + "---");
    }
}
