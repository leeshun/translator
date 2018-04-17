package sse.bupt.cn.translator.responsehandler;

import android.os.Handler;
import android.util.Log;

import com.android.volley.VolleyError;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sse.bupt.cn.translator.model.MenuItem;
import sse.bupt.cn.translator.network.handler.StringRequestHandler;

public class MenuItemHandler implements StringRequestHandler {
    private static final String TAG = "MenuItemHandler";

    private Handler handler;

    public MenuItemHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void onSuccess(String response) {
        JSONObject root;
        try {
            root = new JSONObject(response);
        } catch (JSONException e) {
            Log.i(TAG, "---json parse error---");
            //TODO(leeshun) add json parse error message
            return;
        }
        try {
            JSONArray values = root.getJSONArray("menu");
            List<MenuItem> items = new ArrayList<>();
            int size =values.length();
            for (int index = 0; index < size;index++) {
                MenuItem item = new MenuItem();
                item.setMenuName(values.getString(index));
                items.add(item);
            }

            //TODO(leeshun) dispatch this list to main activity
        } catch (JSONException e) {
            Log.i(TAG, "---json don't contain menu key---");
            //TODO(leeshun) add json don't contain menu key
            return;
        }
    }

    @Override
    public void onFail(VolleyError error) {
        Log.i(TAG, "---" + error.getMessage() + "---");
        //TODO(leeshun) handle the error message
    }
}
