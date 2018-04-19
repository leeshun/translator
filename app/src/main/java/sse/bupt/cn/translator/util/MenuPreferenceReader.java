package sse.bupt.cn.translator.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sse.bupt.cn.translator.model.MenuItem;

public class MenuPreferenceReader {
    private static final String TAG = "MenuPreferenceReader";

    private SharedPreferences preferences;

    private Context context;

    private List<MenuItem> items;


    public MenuPreferenceReader(Context context) {
        preferences = context.getSharedPreferences("menu", Context.MODE_PRIVATE);
        items = new ArrayList<>();
        try {
            initialize();
        } catch (JSONException e) {
            Log.i(TAG, "---" + e.getMessage() + "---");
            items = null;
        }
    }

    public List<MenuItem> getItems() {
        return items;
    }

    private void initialize() throws JSONException {
        Log.i(TAG, "---initialize---");
        String rawInternt = preferences.getString("menu", "");

        JSONObject itemObject = new JSONObject(rawInternt);

        JSONArray itemArray = itemObject.getJSONArray("items");
        int size = itemArray.length();
        JSONObject object;
        for (int index = 0; index < size; index++) {
            MenuItem item = new MenuItem();
            object = itemArray.getJSONObject(index);
            item.setMenuName(object.getString("articleName"));
            item.setPath(object.getString("articlePath"));
            item.setLastViewPages(object.getInt("lastViewPages"));
            item.setLastViewTime(new Date(object.getString("lastViewTime")));
            Log.i(TAG, "---initialize the " + index + " item,which is " + item.toString() + "---");
            items.add(item);
        }
        Log.i(TAG, "---read success,which size is " + items.size() + "---");
    }
}
